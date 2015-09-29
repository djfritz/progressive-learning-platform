/*
    Copyright 2011-2014 David Fritz, Brian Gordon, Wira Mulia

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

package plptool.gui.frames;

import plptool.Constants;

import java.awt.Color;
import plptool.gui.ProjectDriver;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.text.html.HTML.Tag;

import plptool.*;

/**
 *
 * @author wira
 */
public class ConsoleFrame extends javax.swing.JFrame {

    ProjectDriver plp;
    ASMExplorer asmexplorer;
    boolean simmode = false;

    /** Creates new form PLPConsole */
    public ConsoleFrame(ProjectDriver plp) {
        this.plp = plp;
        initComponents();
        systemInfo();
        PLPToolbox.attachDebugConsoleMagicComboListener(this, plp, false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmd = new javax.swing.JTextField();
        jscroll = new javax.swing.JScrollPane();
        out = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(plptool.gui.PLPToolApp.class).getContext().getResourceMap(ConsoleFrame.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setAlwaysOnTop(true);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setName("Form"); // NOI18N

        cmd.setBackground(resourceMap.getColor("cmd.background")); // NOI18N
        cmd.setForeground(resourceMap.getColor("cmd.foreground")); // NOI18N
        cmd.setText(resourceMap.getString("cmd.text")); // NOI18N
        cmd.setBorder(javax.swing.BorderFactory.createLineBorder(resourceMap.getColor("cmd.border.lineColor"))); // NOI18N
        cmd.setName("cmd"); // NOI18N
        cmd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmdKeyPressed(evt);
            }
        });

        jscroll.setName("jscroll"); // NOI18N

        out.setName("out"); // NOI18N
        jscroll.setViewportView(out);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmd, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
            .addComponent(jscroll, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jscroll, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void systemInfo() {
        String outText = "os_code: " + plptool.PLPToolbox.getOS(false) + "\n";
        outText += "plpfile: " + plp.plpfile + " ";
        outText += "arch: " + plp.getArch() + "\n";
        outText += "jvm: " + System.getProperty("java.home");
        outText += " " + System.getProperty("java.vendor");
        outText += " " + System.getProperty("java.vendor.url");
        outText += " " + System.getProperty("java.version") + "\n";
        outText += "classpath: " + System.getProperty("java.class.path") + "\n";
        outText += "userhome: " + System.getProperty("user.home") + "\n";
        outText += "plptoolconfdir: " + PLPToolbox.getConfDir() + "\n";
        out.setText(outText);
    }

    private void cmdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmdKeyPressed
        try {
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            out.setText("OK");
            String command = cmd.getText();
            String tokens[] = command.split(" ", 2);
            
            if(command.equals("q")) {
                System.exit(0);
            } else if(simmode) {
                if(command.equals("simmode")) {
                    simmode = false;
                    cmd.setBackground(Color.white);
                    cmd.setForeground(Color.black);
                } else {
                    plptool.mips.SimCLI.simCLCommand(command, plp);
                    if(asmexplorer != null) asmexplorer.updateTable();
                    if(plp.g_sim != null) plp.g_sim.updateComponents();
                    if(plp.sim != null) plp.updateComponents(true);
                }
            } else if(command.equals("reset")) {
                plp.g_dev.dispose();
                plp.desimulate();
                plp = new ProjectDriver(Constants.PLP_GUI_START_IDE);
                plp.g_dev.setVisible(true);
            } else if(command.equals("dark")) {
                plptool.Config.devBackground = java.awt.Color.BLACK;
                plptool.Config.devForeground = java.awt.Color.lightGray;
                plp.g_dev.changeFormatting();
            } else if(command.equals("defcolor")) {
                plptool.Config.devBackground = java.awt.Color.WHITE;
                plptool.Config.devForeground = java.awt.Color.BLACK;
                plp.g_dev.changeFormatting();
            } else if(command.equals("sysinfo")) {
                systemInfo();
            } else if(command.equals("openfiletest")) {
                java.io.File f = PLPToolbox.openFileDialog(Constants.launchPath);
                if(f != null)
                    System.out.println("**OPENFILETEST**\n" +
                            PLPToolbox.readFileAsString(f.getAbsolutePath()));
            } else if(command.equals("openfileasbytestest")) {
                java.io.File f = PLPToolbox.openFileDialog(Constants.launchPath);
                if(f != null) {
                    String str = new String(PLPToolbox.readFile(f.getAbsolutePath()));
                    System.out.println("**OPENFILEBYTETEST**\n" + str);
                }
            } else if(command.equals("hide")) {
                this.setVisible(false);
            } else if(command.equals("hloff")) {
                plptool.Config.devSyntaxHighlighting = false;
            } else if(command.equals("hlon")) {
                plptool.Config.devSyntaxHighlighting = true;
            } else if(command.equals("simcore")) {
                out.setText(plp.sim.toString());
            } else if(command.equals("hidetoolbar")) {
                plp.g_dev.setToolbarVisible(false);
            } else if(command.equals("showtoolbar")) {
                plp.g_dev.setToolbarVisible(true);
            }  else if(command.equals("hideoutput")) {
                
            } else if(command.equals("showoutput")) {

            } else if(command.equals("open_asm")) {
                plptool.Msg.info("open_asm:" + plp.getOpenAsm(), null);
            } else if(command.equals("assemble")) {
                plp.g_dev.assemble();
            } else if(command.equals("simulate")) {
                plp.g_dev.simBegin();
            } else if(command.equals("desimulate")) {
                plp.g_dev.simEnd();
            } else if(command.equals("updategui")) {
                if(plp.g_sim != null) {
                    plp.updateComponents(true);
                    plp.g_ioreg.refreshModulesTable();
                    if(plp.g_watcher != null)
                        plp.g_watcher.updateFontSize();
                }
            } else if(command.equals("triggercriterror")) {
                plp.triggerCriticalError();
            } else if(command.equals("closeproject")) {
                if(plp.g_dev != null) {
                    plp.g_dev.closeProject();
                }
            } else if(command.equals("printeditorcontents")) {
                if(plp.g_dev != null) {
                    plp.g_dev.getEditor().print();
                }
            } else if(command.equals("dumpattributes")) {
                java.util.HashMap<String, Object> attributes = plp.getProjectAttributeSet();
                Object[] entrySet = attributes.entrySet().toArray();
                Msg.printPreformattedString("Project attributes:");
                for(int i = 0; i < entrySet.length; i++) {
                    @SuppressWarnings("unchecked")
                    java.util.Map.Entry<String, Object> entry =
                            (java.util.Map.Entry<String, Object>) entrySet[i];
                    Msg.appendLine(entry.getKey() + "::" + entry.getValue());
                }
                Msg.printBuffer();
            } else if(command.equals("dumpappattributes")) {
                java.util.HashMap<String, String> attributes = plptool.gui.PLPToolApp.getAttributes();
                Object[] entrySet = attributes.entrySet().toArray();
                Msg.printPreformattedString("Application attributes:");
                for(int i = 0; i < entrySet.length; i++) {
                    @SuppressWarnings("unchecked")
                    java.util.Map.Entry<String, String> entry =
                            (java.util.Map.Entry<String, String>) entrySet[i];
                    Msg.appendLine(entry.getKey() + "::" + entry.getValue());
                }
                Msg.printBuffer();
            } else if(command.equals("dumpbuilderrors")) {
                java.util.ArrayList<PLPBuildError> errorList;
                Msg.printPreformattedString("Errors from last build:");
                if((errorList = plp.getBuildErrorList()) != null) {
                    for (PLPBuildError e : errorList) {
                        Msg.printPreformattedString(plp.getAsm(e.getSourceIndex()).getAsmFilePath() + ":" +
                                e.getLineNumber() + " " + e.getErrorMessage());
                    }
                }
            } else if(command.equals("errordialog")) {
                Config.cfgErrorDialog ^= true;
                Msg.printPreformattedString("cfgErrorDialog=" + Config.cfgErrorDialog);
            } else if(command.equals("vismem")) {
                if(plp.isSimulating()) {
                    plptool.mips.visualizer.MemoryVisualization memVis = new plptool.mips.visualizer.MemoryVisualization(plp);
                    ((plptool.mips.SimCoreGUI)plp.g_sim).attachMemoryVisualizer(memVis);
                    memVis.setVisible(true);
                }
            } else if(command.equals("vismem with cat")) {
                if(plp.isSimulating()) {
                    plptool.mips.visualizer.MemoryVisualization memVis = new plptool.mips.visualizer.MemoryVisualization(plp);
                    memVis.setBG("http://upload.wikimedia.org/wikipedia/commons/e/ef/Curious_kitten.jpg");
                    ((plptool.mips.SimCoreGUI)plp.g_sim).attachMemoryVisualizer(memVis);
                    memVis.setVisible(true);
                }
            } else if(command.equals("kitten")) {
                javax.swing.text.html.HTMLDocument hDoc = (javax.swing.text.html.HTMLDocument) plp.g_dev.getOutput().getDocument();
                ((javax.swing.text.html.HTMLEditorKit)plp.g_dev.getOutput().getEditorKit()).insertHTML(hDoc, hDoc.getLength(),
                        "<img src=\"http://upload.wikimedia.org/wikipedia/commons/e/ef/Curious_kitten.jpg\" /><br />", 0, 0, null);
            } else if(command.equals("ignoresavedmods")) {
                plptool.Config.simIgnoreSavedSimState = true;
            } else if(command.equals("loadsavedmods")) {
                plptool.Config.simIgnoreSavedSimState = false;
            } else if(command.equals("asmexplorer")) {
                plp.g_dev.setVisible(false);
                plp.desimulate();

                if(asmexplorer == null) {
                    asmexplorer = new ASMExplorer(plp);
                }

                plp.sim = new plptool.mips.SimCore((plptool.mips.Asm) plp.asm, plp.asm.getAddrTable()[0]);
                plp.sim.reset();

                asmexplorer.setVisible(true);
                asmexplorer.updateTable();
            } else if(command.equals("devchangeformatting")) {
                plp.g_dev.changeFormatting();
            } else if(command.equals("refreshprojectview")) {
                plp.refreshProjectView(true);
            } else if(command.equals("updatecomponentswithdev")) {
                plp.updateComponents(true);
            } else if(command.equals("updatecomponents")) {
                plp.updateComponents(false);
            } else if(command.equals("debugrepaint")) {
                plp.g_dev.forceRevalidate();
            } else if(command.equals("simmode") && plp.sim != null) {
                if(!simmode) {
                    simmode = true;
                    cmd.setBackground(Color.black);
                    cmd.setForeground(Color.green);
                }
            } else if(command.equals("loaddmodclass")) {
                final javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
                int retVal = fc.showOpenDialog(null);
                if(retVal == javax.swing.JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fc.getSelectedFile();
                    String shortName = file.getName();
                    plptool.dmf.DynamicModuleFramework.loadModuleClass(file.getAbsolutePath(), shortName.substring(0, shortName.length()-6));
                }          
            } else if(command.equals("listdmodclasses")) {
                Msg.println("Registered dynamic module classes:");
                for(int i = 0; i < plptool.dmf.DynamicModuleFramework.getNumberOfClasses(); i++) {
                    Class c = plptool.dmf.DynamicModuleFramework.getDynamicModuleClass(i);
                    Class sc = c.getSuperclass();
                    Msg.print(i + ":\t" + c.getName());
                    Msg.println((sc != null) ? " extends " + sc.getName() : "");
                }
            } else if(command.equals("listdmods")) {
                out.setText("");
                String prev = "";
                for(int i = 0; i < plptool.dmf.DynamicModuleFramework.getNumberOfModuleInstances(); i++) {
                    prev = out.getText();
                    out.setText(prev + i + ":\t" + plptool.dmf.DynamicModuleFramework.getModuleInstance(i).getClass().getName() +"\n");
                }
            } else if(command.equals("getarchlist")) {
                out.setText("");
                Object[][] archs = ArchRegistry.getArchList();
                for(int i = 0; i < archs.length; i++) {
                    int id = (Integer) archs[i][0];
                    Class c = (Class) archs[i][1];
                    String strID = (String) archs[i][2];
                    Msg.info(id + ": " + c.getCanonicalName() + " \"" + strID + "\"", null);
                }

            } else if(command.startsWith("cmd ")) {
                out.setText("");
                String[] t = command.split(" ", 2);
                if(t.length == 2)
                    plptool.dmf.CallbackRegistry.callback(plptool.dmf.CallbackRegistry.COMMAND, t[1]);

            } else if(tokens.length > 1) {
                if(tokens[0].equals("font")) {
                    plptool.Config.devFont = tokens[1];
                    plp.g_dev.changeFormatting();
                } else if(tokens[0].equals("fontsize") && tokens.length == 2) {
                    plptool.Config.devFontSize = Integer.parseInt(tokens[1]);
                    plp.g_dev.changeFormatting();
                } else if(tokens[0].equals("hl") && tokens.length == 2) {
                    plptool.Config.devSyntaxHighlighting = Boolean.parseBoolean(tokens[1]);
                    out.setText("cfgSyntaxHighlighting " + plptool.Config.devSyntaxHighlighting);
                } else if(tokens[0].equals("open") && tokens.length == 2) {
                    plp.open(tokens[1], true);
                } else if(tokens[0].equals("program") && tokens.length == 2) {
                    plp.program(tokens[1]);
                } else if(tokens[0].equals("checkdmodclass") && tokens.length == 2) {
                    out.setText("dmodclass index: " + plptool.dmf.DynamicModuleFramework.isModuleClassRegistered(tokens[1]) + "\n");
                } else if(tokens[0].equals("newdmod") && tokens.length == 2) {
                    out.setText("Instantiating new object for " + plptool.dmf.DynamicModuleFramework.getDynamicModuleClass(PLPToolbox.parseNumInt(tokens[1])).getName() +"\n");
                    plptool.dmf.DynamicModuleFramework.newModuleInstance(PLPToolbox.parseNumInt(tokens[1]));
                } else if(tokens[0].equals("rmdmod") && tokens.length == 2) {
                    plptool.dmf.DynamicModuleFramework.removeModuleInstance(PLPToolbox.parseNumInt(tokens[1]));
                } else if(tokens[0].equals("setnewarch") && tokens.length == 2) {
                    int archid = Integer.parseInt(tokens[1]);
                    plp.setArch(archid);
                } else if(tokens[0].equals("opencloseport") && tokens.length == 2) {
                   opencloseport(tokens[1]);
                } else if(tokens[0].equals("loadjarurlload") && tokens.length == 2) {
                    PLPToolbox.downloadJARForAutoload(tokens[1], plp, true);
                } else if(tokens[0].equals("debuglevel") && tokens.length == 2) {
                    out.setText("Setting debug level to " + tokens[1]);
                    Constants.debugLevel = Integer.parseInt(tokens[1]);
                } else if(tokens[0].equals("simcl") && asmexplorer != null) {
                    String xcmd = "";
                    for(int i = 1; i < tokens.length; i++)
                        xcmd += tokens[i] + ((i != tokens.length - 1) ? " " : "");
                    plptool.mips.SimCLI.simCLCommand(xcmd, plp);
                    asmexplorer.updateTable();
                } else {
                    out.setText(":(");
                    cmd.setText("");
                    return;
                }
            } else {
                out.setText(":(");
                cmd.setText("");
                return;
            }
            cmd.setText("");
        }
        } catch(Exception e) {
            cmd.setText("");
            if(Constants.debugLevel >= 2)
                e.printStackTrace();
            out.setText(e.toString());
        }
    }//GEN-LAST:event_cmdKeyPressed

   private CommPort commPort;
   private CommPortIdentifier portIdentifier;
   protected InputStream sIn;
   private OutputStream sOut;
   private SerialPort port;

   private void opencloseport(String portName) {
       System.out.println("Resetting " + portName);

       try {
            int baudRate = 57600;
            portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

            if (portIdentifier.isCurrentlyOwned()) {
                Msg.debug("Serial port " + portName + " is in use.", 2, this);
            } else {
                commPort = portIdentifier.open(this.getClass().getName(), 2000);

                if (commPort instanceof SerialPort) {
                    port = (SerialPort) commPort;
                    port.setSerialPortParams(baudRate, SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);

                    port.enableReceiveTimeout(1000);
                    sIn = port.getInputStream();
                    sOut = port.getOutputStream();

                } else {
                    Msg.debug(portName + " is not a serial port.", 2, this);
                }
            }
        } catch (Exception e) {
            Msg.debug("Error opening port.", 2, this);
            System.err.println(e);
        }

        try {
            sIn.close();
            sOut.close();
            port.close();
            commPort.close();

            Msg.info("Port closed.", this);

        } catch(Exception e) {
            Msg.debug("Error closing port.", 2, this);
        }

       System.out.println("Done");
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cmd;
    private javax.swing.JScrollPane jscroll;
    private javax.swing.JTextPane out;
    // End of variables declaration//GEN-END:variables


}



