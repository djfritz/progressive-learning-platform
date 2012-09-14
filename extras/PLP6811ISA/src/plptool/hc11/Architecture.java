/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plptool.hc11;

import plptool.*;
import plptool.gui.ProjectDriver;

/**
 *
 * @author wira
 */
public class Architecture extends PLPArchitecture {

    public Architecture() {
        super(6811, "hc11", null);
        Msg.M("PLP 68HC11 ISA Implementation is called.");
        hasAssembler = true;
        hasSimCore = true;
    }

    public PLPAsm createAssembler() {
        return new Asm(plp.getAsms());
    }

    public PLPSerialProgrammer createProgrammer() {
        return null;
    }

    public PLPSimCore createSimCore() {
        return new SimCore();
    }

    public PLPSimCoreGUI createSimCoreGUI() {
        return null;
    }

    @Override
    public void newProject(ProjectDriver plp) {

    }

    @Override
    public void cleanup() {
        Msg.M("PLP 68HC11 ISA Implementation is cleaning up.");
    }
}
