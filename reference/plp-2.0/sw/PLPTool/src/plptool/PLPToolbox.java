/*
    Copyright 2010 David Fritz, Brian Gordon, Wira Mulia

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

package plptool;

/**
 *
 * @author wira
 */
public class PLPToolbox {
    public static long parseNum(String number) {
        try {

        if(number.startsWith("0x") || number.startsWith("0h")) {
            number = number.substring(2);
            return Long.parseLong(number, 16);
        }
        else if(number.startsWith("0b")) {
            number = number.substring(2);
            return Long.parseLong(number, 2);
        }
        else
            return Long.parseLong(number);

        } catch(Exception e) {
            return PLPMsg.E("parseNum(): Argument is not a valid number\n" + e,
                            Constants.PLP_NUMBER_ERROR, null);
        }
    }

    // Convert 32-bit word to printable ASCII
    public static String asciiWord(long word) {
        String tStr = "";
        long tVal;
        for(int j = 3; j >= 0; j--) {
            tVal = word >> (8 * j);
            tVal &= 0xFF;
            if(tVal >= 0x21 && tVal <= 0x7E)
                tStr += (char) tVal + " ";
            else
                tStr += ". ";
        }

        return tStr;
    }
}
