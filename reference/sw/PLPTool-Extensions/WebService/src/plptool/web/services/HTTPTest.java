/*
    Copyright 2014 Wira Mulia

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */

package plptool.web.services;

import plptool.web.*;

import plptool.*;
import plptool.gui.*;

import java.io.*;
import java.net.*;
import java.util.*;

import com.sun.net.httpserver.*;

/**
 *
 * @author wira
 */
public class HTTPTest {
    protected static HttpServer srv;

    private static String defaultPage =
            "<html>\n"
            + "<head><title>PLP Web Service - HTTP Test</title></head>\n"
            + "<body>\n"
            + "<h1>PLP Web Service - HTTP Test</h1>\n"
            + "<a href=\"./ide\">Try the IDE</a><br /><br /><br />\n"
            + "<h2>Powered by:</h2>\n"
            + "<pre>" + Text.copyrightString + "\n\n" + Text.licenseBanner
            + "\n\n" + Text.contactString 
            + "\n\n" + PLPToolApp.getBuildInfo() + "</pre><br />"
            + "</body>\n"
            + "</html>";

    public static void start() {

        int port = 8080;
        String userPort = PLPToolApp.getAttributes().get("Web_Port");
        if(userPort != null)
            port = Integer.parseInt(userPort);
        Msg.M("WebService HTTP Test: running HTTP test server on port " + port);
        try {
            srv = HttpServer.create(new InetSocketAddress(port), 0);
            srv.createContext("/", new PlainMessage(defaultPage));
            srv.createContext("/buildinfo", new PlainMessage(PLPToolApp.getBuildInfo()));
            srv.createContext("/ide", new SimpleIDE());
            srv.setExecutor(null);
            srv.start();
        } catch(IOException e) {
            Msg.E("WebService HTTP Test: I/O Exception", Constants.PLP_GENERAL_IO_ERROR, null);
            Msg.trace(e);
        }
    }

    static class PlainMessage implements HttpHandler {
        private String r;

        public PlainMessage(String r) {
            this.r = r;
        }

        public void handle(HttpExchange t) throws IOException {
            String out = r;
            t.sendResponseHeaders(200, out.getBytes().length);
            OutputStream os = t.getResponseBody();
            os.write(out.getBytes());
            os.close();
        }
    }

    static class SimpleIDE implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String uri = t.getRequestURI().toString();
            Msg.D(t.getRequestMethod() + " " + t.getProtocol() + " path: " +
                    t.getHttpContext().getPath() +
                    " reqURI: " + uri, 2, null);
            String tokens[];
            String source = null;
            plptool.mips.Asm asm = null;
            String err = "";
            if(t.getRequestMethod().equals("POST")) {
                // http://stackoverflow.com/questions/3409348/read-post-request-values-httphandler
                String encoding = "ISO-8859-1";

                // read the query string from the request body
                String qry;
                InputStream in = t.getRequestBody();
                try {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte buf[] = new byte[4096];
                    for (int n = in.read(buf); n > 0; n = in.read(buf)) {
                        out.write(buf, 0, n);
                    }
                    qry = new String(out.toByteArray(), encoding);
                } finally {
                    in.close();
                }
                
                Msg.D("Query: " + qry, 2, null);
                source = qry;
                tokens = source.split("source=", 2);

                if(tokens.length > 1) {
                    source = tokens[1].split("&", 2)[0];
                    source = URLDecoder.decode(source, "UTF-8");
                    Msg.D("Parsed source: " + source, 2, null);
                    asm = new plptool.mips.Asm(source, "$");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(baos);
                    Msg.setLogErrStream(ps);
                    if(asm.preprocess(0) == Constants.PLP_OK)
                        asm.assemble();
                    Msg.setLogErrStream(System.err);
                    err = baos.toString();
                }
            }
            
            String out =
                    "<html>"
                    + "<head><title>PLP Web IDE Test</title></head>"
                    + "<body><h1>Input your PLP CPU program</h1></body>"
                    + "<form action=\"ide\" method=\"post\">"
                    + "<textarea name=\"source\" cols=\"60\" rows=\"20\">"
                    + ((source != null) ? source : "") + "</textarea><br />"
                    + "<input type=\"submit\" name=\"Assemble\"><br />";

            if(source != null && asm.isAssembled()) {
                out += "<pre>" + plptool.mips.Formatter.prettyString(asm) + "</pre>";
            } else if(source != null && !asm.isAssembled())
                out += "<pre>" + err + "</pre>";

            out += "</form>"
                    + "</html>";
            t.sendResponseHeaders(200, out.getBytes().length);
            OutputStream os = t.getResponseBody();
            os.write(out.getBytes());
            os.close();
        }
    }
}


