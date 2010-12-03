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

import java.util.ArrayList;

/**
 * This class implements the simulation core bus / front-side bus. Simulator
 * users can attach I/O module classes that extend the PLPSimBusModule
 * abstract class. This object is ideally used by the simulation core during
 * memory access. For example, the PLPTool MIPS simulation core attaches
 * its memory module to the bus, and performs bus write and read during
 * the MEM stage of the pipeline. All modules attached to the bus can 
 * be accessed through this class' methods, or directly using the getModRef
 * function.
 *
 * @see PLPSimCore
 * @see PLPMIPSSim
 * @see PLPSimBusModule
 * @see PLPSimMemModule
 * @see getModRef(int)
 * @author wira
 */
public class PLPSimBus {
    /**
     * The modules attached to the bus.
     */
    private ArrayList<PLPSimBusModule> bus_modules;

    /**
     * The default constructor for the class will start with a bus with
     * nothing attached to it. This may be preferable for CPUs that do not
     * use this bus to write to main memory.
     *
     * @see PLPSimBus(PLPSimCore)
     */
    public PLPSimBus() {
        bus_modules = new ArrayList<PLPSimBusModule>();
    }

    /**
     * Passing the simulator core object will attach the main memory to
     * the bus.
     *
     * @param PLPSimCore The simulator core
     * @see PLPSimBus()
     */
    public PLPSimBus(PLPSimCore sim) {
        bus_modules = new ArrayList<PLPSimBusModule>();
        bus_modules.add(sim.memory);
    }

    /**
     * Attaches a module to the bus.
     *
     * @param module PLPSimBusModule-based class to be added
     * @return Index of the new added module, or -1 if error occurred
     */
    public int add(PLPSimBusModule module) {
        boolean ret = bus_modules.add(module);

        if(ret)
            return bus_modules.indexOf(module);
        else
            return PLPMsg.E("Failed to attach module " + module,
                            PLPMsg.PLP_ERROR_RETURN, this);
    }

    /**
     * Removes a module specified by the index from this bus.
     *
     * @param index
     * @return Always returns PLP_OK
     */
    public int remove(int index) {
        bus_modules.remove(index);

        return PLPMsg.PLP_OK;
    }

    /**
     * Issue a read to the bus. This will return a value from specified address
     * starting with the module with lowest index (main memory preferably).
     * If there's any address space overlap, the module with lower index will
     * be read.
     *
     * @param addr Address to read from
     * @return Data with successful read, -1 otherwise
     */
    public long read(long addr) {
        Object[] modules = bus_modules.toArray();
        for(int i = 0; i < modules.length; i++) {
            if(addr >= ((PLPSimBusModule)modules[i]).startAddr() &&
               addr <= ((PLPSimBusModule)modules[i]).endAddr())
                return ((PLPSimBusModule)modules[i]).read(addr);
        }

        return PLPMsg.E("read(" + String.format("0x%08x", addr) + "):" +
                            " This address is not in any module's address space" +
                            " or is unitialiazed.",
                            PLPMsg.PLP_ERROR_RETURN, this);
    }

    /**
     * Issue a write to the bus. The write will be issued to all modules
     * if there's an address overlap.
     *
     * @param addr Address to write to
     * @param data Data to write
     * @param isInstr Specify whether the data is instruction or not
     * @return PLP_OK on successful operation, error code otherwise
     */
    public int write(long addr, long data, boolean isInstr) {
        boolean noMapping = true;
        Object[] modules = bus_modules.toArray();
        for(int i = modules.length - 1; i >= 0; i--) {
            if(addr >= ((PLPSimBusModule)modules[i]).startAddr() &&
               addr <= ((PLPSimBusModule)modules[i]).endAddr()) {
                ((PLPSimBusModule)modules[i]).write(addr, data, isInstr);
                noMapping = false;
            }
        }

        if(noMapping)
            return PLPMsg.E("write(" + String.format("0x%08x", addr) + "):" +
                            " This address is not in any module's address space.",
                            PLPMsg.PLP_SIM_BUS_MAPPING_ERROR, this);
        else
            return PLPMsg.PLP_OK;
    }

    /**
     * Evaluate all modules attached to the bus.
     *
     * @return PLP_OK, or error code
     */
    public int eval() {
        int ret = PLPMsg.PLP_OK;
        Object[] modules = bus_modules.toArray();
        for(int i = 0; i < modules.length; i++)
            ret += ((PLPSimBusModule)modules[i]).eval();

        return ret;
    }

    /**
     * Evaluate module that has the specified index
     *
     * @param index Index of the module
     * @return PLP_OK, or error code
     */
    public int eval(int index) {
        Object[] modules = bus_modules.toArray();
        try {
        return ((PLPSimBusModule)modules[index]).eval();

        } catch(Exception e) {
            return PLPMsg.E("eval(" + index + "): error: " + e,
                            PLPMsg.PLP_SIM_BUS_ERROR, this);
        }
    }

    /**
     * Execute a GUI evaluation on the module that has the specified index
     *
     * @param index Index of the module
     * @param x Object to draw to, left to the module to implement
     * @return PLP_OK, or error code
     */
    public int gui_eval(int index, Object x) {
        Object[] modules = bus_modules.toArray();
        try {
        return ((PLPSimBusModule)modules[index]).gui_eval(x);

        } catch(Exception e) {
            return PLPMsg.E("eval(" + index + "): error: " + e,
                            PLPMsg.PLP_SIM_BUS_ERROR, this);
        }
    }

    /**
     * Enable all I/O mods.
     *
     * @return Returns 0 on completion.
     */
    public int enableiomods() {
        Object[] modules =bus_modules.toArray();

        for(int i = 0; i < modules.length; i++)
            ((PLPSimBusModule)modules[i]).enable();

        return PLPMsg.PLP_OK;
    }

    /**
     * Disable all I/O mods. Including the main memory if it is attached to
     * the bus. Be careful.
     *
     * @return Returns 0 on completion.
     */
    public int disableiomods() {
        Object[] modules = bus_modules.toArray();

        for(int i = 0; i < modules.length; i++)
            ((PLPSimBusModule)modules[i]).disable();

        return PLPMsg.PLP_OK;
    }

    /**
     * Returns whether the specified module is enabled or not.
     *
     * @param index Index of the module
     * @return Boolean on whether the module is enabled
     */
    public boolean enabled(int index) {
        Object[] modules = bus_modules.toArray();

        try {
        return ((PLPSimBusModule)modules[index]).enabled();

        } catch(Exception e) {
            PLPMsg.E("enabled(" + index + "): error: " + e,
                     PLPMsg.PLP_SIM_BUS_ERROR, this);

            return false;
        }
    }

    /**
     * Enable specified module, allowing evaluations and writes
     *
     * @param index Index of the module
     * @return PLP_OK, or error code
     */
    public int enableio(int index) {
        Object[] modules = bus_modules.toArray();

        try {
        ((PLPSimBusModule)modules[index]).enable();
        return PLPMsg.PLP_OK;

        } catch(Exception e) {
            return PLPMsg.E("enableio(" + index + "): error: " + e,
                            PLPMsg.PLP_SIM_BUS_ERROR, this);
        }
    }

    /**
     * Disable specified module, disallowing evaluations and writes
     *
     * @param index Index of the module
     * @return PLP_OK, or error code
     */
    public int disableio(int index) {
        Object[] modules = bus_modules.toArray();

        try {
        ((PLPSimBusModule)modules[index]).disable();
        return PLPMsg.PLP_OK;

        } catch(Exception e) {
            return PLPMsg.E("disableio(" + index + "): error: " + e,
                            PLPMsg.PLP_SIM_BUS_ERROR, this);
        }
    }

    /**
     * Clear saved values of specified module.
     *
     * @param index Index of the module
     * @return PLP_OK, or error code
     */
    public int cleario(int index) {
        Object[] modules = bus_modules.toArray();

        try {
        ((PLPSimBusModule)modules[index]).clear();
        return PLPMsg.PLP_OK;

        } catch(Exception e) {
            return PLPMsg.E("cleario(" + index + "): error: " + e,
                            PLPMsg.PLP_SIM_BUS_ERROR, this);
        }
    }

    /**
     * Returns the information string for the specified module.
     *
     * @param index Index of the module
     * @return String of introduction
     */
    public String introduceio(int index) {
        Object[] modules = bus_modules.toArray();
        try {

        return ((PLPSimBusModule)modules[index]).introduce();

        } catch(Exception e) {
            PLPMsg.E("introduceio(" + index + "): error: " + e,
                     PLPMsg.PLP_SIM_BUS_ERROR, this);

            return null;
        }
    }

    /**
     * Returns the starting address of the specified module.
     *
     * @param index Index of the module
     * @return Starting address in (long)
     */
    public long iostartaddr(int index) {
        Object[] modules = bus_modules.toArray();
        try {

        return ((PLPSimBusModule)modules[index]).startAddr();

        } catch(Exception e) {
            PLPMsg.E("iostartaddr(" + index + "): error: " + e,
                     PLPMsg.PLP_SIM_BUS_ERROR, this);

            return PLPMsg.PLP_ERROR_RETURN;
        }
    }

    /**
     * Returns the ending address of the specified module
     *
     * @param index Index of the module
     * @return Ending address in (long)
     */
    public long ioendaddr(int index) {
        Object[] modules = bus_modules.toArray();
        try {

        return ((PLPSimBusModule)modules[index]).endAddr();

        } catch(Exception e) {
            PLPMsg.E("ioendaddr(" + index + "): error: " + e,
                     PLPMsg.PLP_SIM_BUS_ERROR, this);

            return PLPMsg.PLP_ERROR_RETURN;
        }
    }

    /**
     * Returns number of modules attached to this bus
     *
     * @return Number of modules in (int)
     */
    public int nummods() {
        Object[] modules = bus_modules.toArray();

        return modules.length;
    }

    /**
     * Returns a reference to the requested module for direct access.
     *
     * @param index Index of the module
     * @return PLPSimBusModule reference of the module
     */
    public PLPSimBusModule getRefMod(int index) {
        Object[] modules = bus_modules.toArray();
        try {

        return (PLPSimBusModule) modules[index];

        } catch(Exception e) {
            PLPMsg.E("getRefMod(" + index + "): error: " + e,
                     PLPMsg.PLP_SIM_BUS_ERROR, this);

            return null;
        }
    }

    @Override public String toString() {
        return "PLPSimBus";
    }
}
