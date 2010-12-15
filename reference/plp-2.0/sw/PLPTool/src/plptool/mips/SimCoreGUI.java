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

/*
 * PLPMIPSCoreGUI.java
 *
 * Created on Dec 4, 2010, 12:12:09 AM
 */

package plptool.mips;

import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author wira
 */
public class SimCoreGUI extends plptool.PLPSimCoreGUI {

    private plptool.gui.PLPBackend backend;
    private long old_pc;
    private String lastCLCommand = "";

    /** Creates new form PLPMIPSCoreGUI */
    public SimCoreGUI(plptool.gui.PLPBackend backend) {
        super();
        this.sim = backend.sim;
        this.backend = backend;
        plptool.mips.SimCLI.errFrame = backend.g_err;

        sim.bus.enableAllModules();
        sim.bus.eval();

        initComponents();

        // Take over console output
        plptool.PLPMsg.output = simCLOutput;

        renderer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tblRegFile.setDefaultRenderer(tblRegFile.getColumnClass(2), renderer);

        tblRegFile.setValueAt("0: $zero", 0, 0);
        tblRegFile.setValueAt("1: $at", 1, 0);
        tblRegFile.setValueAt("2: $v0", 2, 0);
        tblRegFile.setValueAt("3: $v1", 3, 0);
        tblRegFile.setValueAt("4: $a0", 4, 0);
        tblRegFile.setValueAt("5: $a1", 5, 0);
        tblRegFile.setValueAt("6: $a2", 6, 0);
        tblRegFile.setValueAt("7: $a3", 7, 0);
        tblRegFile.setValueAt("8: $t0", 8, 0);
        tblRegFile.setValueAt("9: $t1", 9, 0);
        tblRegFile.setValueAt("10: $t2", 10, 0);
        tblRegFile.setValueAt("11: $t3", 11, 0);
        tblRegFile.setValueAt("12: $t4", 12, 0);
        tblRegFile.setValueAt("13: $t5", 13, 0);
        tblRegFile.setValueAt("14: $t6", 14, 0);
        tblRegFile.setValueAt("15: $t7", 15, 0);
        tblRegFile.setValueAt("16: $s0", 16, 0);
        tblRegFile.setValueAt("17: $s1", 17, 0);
        tblRegFile.setValueAt("18: $s2", 18, 0);
        tblRegFile.setValueAt("19: $s3", 19, 0);
        tblRegFile.setValueAt("20: $s4", 20, 0);
        tblRegFile.setValueAt("21: $s5", 21, 0);
        tblRegFile.setValueAt("22: $s6", 22, 0);
        tblRegFile.setValueAt("23: $s7", 23, 0);
        tblRegFile.setValueAt("24: $t8", 24, 0);
        tblRegFile.setValueAt("25: $t9", 25, 0);
        tblRegFile.setValueAt("26: $k0", 26, 0);
        tblRegFile.setValueAt("27: $k1", 27, 0);
        tblRegFile.setValueAt("28: $gp", 28, 0);
        tblRegFile.setValueAt("29: $sp", 29, 0);
        tblRegFile.setValueAt("30: $fp", 30, 0);
        tblRegFile.setValueAt("31: $ra", 31, 0);

        old_pc = -1;

        clearProgramMemoryTable();
        fillProgramMemoryTable();

        updateComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        PC = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nextInstr = new javax.swing.JTextField();
        coreMainPane = new javax.swing.JTabbedPane();
        coreVisPane = new javax.swing.JPanel();
        coreRegFilePane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegFile = new javax.swing.JTable();
        coreProgramPane = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProgram = new javax.swing.JTable();
        coreMemMapPane = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMemMap = new javax.swing.JTable();
        coreSimOptsPane = new javax.swing.JPanel();
        lblArchOpts = new javax.swing.JLabel();
        chkEXEXFwd = new javax.swing.JCheckBox();
        chkMEMEXFwd = new javax.swing.JCheckBox();
        lblBranchPrdction = new javax.swing.JLabel();
        rdioBrAlways = new javax.swing.JRadioButton();
        rdioBrNever = new javax.swing.JRadioButton();
        rdioBrLast = new javax.swing.JRadioButton();
        rdioBrRandom = new javax.swing.JRadioButton();
        coreConsolePane = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        simCLOutput = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        simCLConsole = new javax.swing.JTextField();
        simCLExec = new javax.swing.JButton();
        simCLClear = new javax.swing.JButton();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(plptool.gui.PLPToolApp.class).getContext().getResourceMap(SimCoreGUI.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        PC.setEditable(false);
        PC.setText(resourceMap.getString("PC.text")); // NOI18N
        PC.setName("PC"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        nextInstr.setEditable(false);
        nextInstr.setText(resourceMap.getString("nextInstr.text")); // NOI18N
        nextInstr.setName("nextInstr"); // NOI18N

        coreMainPane.setName("coreMainPane"); // NOI18N

        coreVisPane.setName("coreVisPane"); // NOI18N

        javax.swing.GroupLayout coreVisPaneLayout = new javax.swing.GroupLayout(coreVisPane);
        coreVisPane.setLayout(coreVisPaneLayout);
        coreVisPaneLayout.setHorizontalGroup(
            coreVisPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 614, Short.MAX_VALUE)
        );
        coreVisPaneLayout.setVerticalGroup(
            coreVisPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );

        coreMainPane.addTab(resourceMap.getString("coreVisPane.TabConstraints.tabTitle"), coreVisPane); // NOI18N

        coreRegFilePane.setName("coreRegFilePane"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblRegFile.setFont(resourceMap.getFont("tblRegFile.font")); // NOI18N
        tblRegFile.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Register", "Contents", "Edit Contents"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Long.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRegFile.setColumnSelectionAllowed(true);
        tblRegFile.setName("tblRegFile"); // NOI18N
        tblRegFile.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblRegFile);
        tblRegFile.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblRegFile.getColumnModel().getColumn(0).setResizable(false);
        tblRegFile.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblRegFile.columnModel.title0")); // NOI18N
        tblRegFile.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblRegFile.columnModel.title1")); // NOI18N
        tblRegFile.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblRegFile.columnModel.title2")); // NOI18N

        javax.swing.GroupLayout coreRegFilePaneLayout = new javax.swing.GroupLayout(coreRegFilePane);
        coreRegFilePane.setLayout(coreRegFilePaneLayout);
        coreRegFilePaneLayout.setHorizontalGroup(
            coreRegFilePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coreRegFilePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addContainerGap())
        );
        coreRegFilePaneLayout.setVerticalGroup(
            coreRegFilePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, coreRegFilePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap())
        );

        coreMainPane.addTab(resourceMap.getString("coreRegFilePane.TabConstraints.tabTitle"), coreRegFilePane); // NOI18N

        coreProgramPane.setName("coreProgramPane"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        tblProgram.setFont(resourceMap.getFont("tblProgram.font")); // NOI18N
        tblProgram.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PC", "Breakpoint", "Address", "Instruction (Hex)", "Instruction"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProgram.setName("tblProgram"); // NOI18N
        tblProgram.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblProgram);
        tblProgram.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblProgram.columnModel.title0")); // NOI18N
        tblProgram.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblProgram.columnModel.title1")); // NOI18N
        tblProgram.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblProgram.columnModel.title2")); // NOI18N
        tblProgram.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblProgram.columnModel.title3")); // NOI18N
        tblProgram.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("tblProgram.columnModel.title4")); // NOI18N

        javax.swing.GroupLayout coreProgramPaneLayout = new javax.swing.GroupLayout(coreProgramPane);
        coreProgramPane.setLayout(coreProgramPaneLayout);
        coreProgramPaneLayout.setHorizontalGroup(
            coreProgramPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coreProgramPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addContainerGap())
        );
        coreProgramPaneLayout.setVerticalGroup(
            coreProgramPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coreProgramPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap())
        );

        coreMainPane.addTab(resourceMap.getString("coreProgramPane.TabConstraints.tabTitle"), coreProgramPane); // NOI18N

        coreMemMapPane.setName("coreMemMapPane"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tblMemMap.setFont(resourceMap.getFont("tblMemMap.font")); // NOI18N
        tblMemMap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Index", "Module Name", "Start Address", "End Address", "Enabled"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMemMap.setName("tblMemMap"); // NOI18N
        tblMemMap.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblMemMap);
        tblMemMap.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblMemMap.columnModel.title0")); // NOI18N
        tblMemMap.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblMemMap.columnModel.title1")); // NOI18N
        tblMemMap.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblMemMap.columnModel.title2")); // NOI18N
        tblMemMap.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblMemMap.columnModel.title3")); // NOI18N
        tblMemMap.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("tblMemMap.columnModel.title4")); // NOI18N

        javax.swing.GroupLayout coreMemMapPaneLayout = new javax.swing.GroupLayout(coreMemMapPane);
        coreMemMapPane.setLayout(coreMemMapPaneLayout);
        coreMemMapPaneLayout.setHorizontalGroup(
            coreMemMapPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coreMemMapPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addContainerGap())
        );
        coreMemMapPaneLayout.setVerticalGroup(
            coreMemMapPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coreMemMapPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap())
        );

        coreMainPane.addTab(resourceMap.getString("coreMemMapPane.TabConstraints.tabTitle"), coreMemMapPane); // NOI18N

        coreSimOptsPane.setName("coreSimOptsPane"); // NOI18N

        lblArchOpts.setText(resourceMap.getString("lblArchOpts.text")); // NOI18N
        lblArchOpts.setName("lblArchOpts"); // NOI18N

        chkEXEXFwd.setText(resourceMap.getString("chkEXEXFwd.text")); // NOI18N
        chkEXEXFwd.setName("chkEXEXFwd"); // NOI18N

        chkMEMEXFwd.setText(resourceMap.getString("chkMEMEXFwd.text")); // NOI18N
        chkMEMEXFwd.setName("chkMEMEXFwd"); // NOI18N

        lblBranchPrdction.setText(resourceMap.getString("lblBranchPrdction.text")); // NOI18N
        lblBranchPrdction.setName("lblBranchPrdction"); // NOI18N

        rdioBrAlways.setText(resourceMap.getString("rdioBrAlways.text")); // NOI18N
        rdioBrAlways.setName("rdioBrAlways"); // NOI18N

        rdioBrNever.setText(resourceMap.getString("rdioBrNever.text")); // NOI18N
        rdioBrNever.setName("rdioBrNever"); // NOI18N

        rdioBrLast.setText(resourceMap.getString("rdioBrLast.text")); // NOI18N
        rdioBrLast.setName("rdioBrLast"); // NOI18N

        rdioBrRandom.setText(resourceMap.getString("rdioBrRandom.text")); // NOI18N
        rdioBrRandom.setName("rdioBrRandom"); // NOI18N

        javax.swing.GroupLayout coreSimOptsPaneLayout = new javax.swing.GroupLayout(coreSimOptsPane);
        coreSimOptsPane.setLayout(coreSimOptsPaneLayout);
        coreSimOptsPaneLayout.setHorizontalGroup(
            coreSimOptsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coreSimOptsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(coreSimOptsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdioBrRandom)
                    .addComponent(rdioBrLast)
                    .addComponent(rdioBrNever)
                    .addComponent(chkMEMEXFwd)
                    .addComponent(lblArchOpts)
                    .addComponent(chkEXEXFwd)
                    .addComponent(lblBranchPrdction)
                    .addComponent(rdioBrAlways))
                .addContainerGap(448, Short.MAX_VALUE))
        );
        coreSimOptsPaneLayout.setVerticalGroup(
            coreSimOptsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coreSimOptsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblArchOpts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEXEXFwd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkMEMEXFwd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblBranchPrdction)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdioBrAlways)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdioBrNever)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdioBrLast)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdioBrRandom)
                .addContainerGap(199, Short.MAX_VALUE))
        );

        coreMainPane.addTab(resourceMap.getString("coreSimOptsPane.TabConstraints.tabTitle"), coreSimOptsPane); // NOI18N

        coreConsolePane.setName("coreConsolePane"); // NOI18N

        jScrollPane4.setEnabled(false);
        jScrollPane4.setName("jScrollPane4"); // NOI18N

        simCLOutput.setColumns(20);
        simCLOutput.setEditable(false);
        simCLOutput.setFont(resourceMap.getFont("simCLOutput.font")); // NOI18N
        simCLOutput.setLineWrap(true);
        simCLOutput.setRows(5);
        simCLOutput.setWrapStyleWord(true);
        simCLOutput.setName("simCLOutput"); // NOI18N
        jScrollPane4.setViewportView(simCLOutput);

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        simCLConsole.setText(resourceMap.getString("simCLConsole.text")); // NOI18N
        simCLConsole.setName("simCLConsole"); // NOI18N
        simCLConsole.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                simCLConsoleKeyPressed(evt);
            }
        });

        simCLExec.setText(resourceMap.getString("simCLExec.text")); // NOI18N
        simCLExec.setName("simCLExec"); // NOI18N
        simCLExec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simCLExecActionPerformed(evt);
            }
        });

        simCLClear.setText(resourceMap.getString("simCLClear.text")); // NOI18N
        simCLClear.setName("simCLClear"); // NOI18N
        simCLClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simCLClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout coreConsolePaneLayout = new javax.swing.GroupLayout(coreConsolePane);
        coreConsolePane.setLayout(coreConsolePaneLayout);
        coreConsolePaneLayout.setHorizontalGroup(
            coreConsolePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, coreConsolePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(coreConsolePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                    .addGroup(coreConsolePaneLayout.createSequentialGroup()
                        .addComponent(simCLConsole, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(simCLExec)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(simCLClear))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        coreConsolePaneLayout.setVerticalGroup(
            coreConsolePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, coreConsolePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(coreConsolePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simCLClear)
                    .addComponent(simCLExec)
                    .addComponent(simCLConsole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        coreMainPane.addTab(resourceMap.getString("coreConsolePane.TabConstraints.tabTitle"), coreConsolePane); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PC, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextInstr, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                    .addComponent(coreMainPane, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(PC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextInstr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(coreMainPane, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void simCLClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simCLClearActionPerformed
        // TODO add your handling code here:
        simCLOutput.setText("");
}//GEN-LAST:event_simCLClearActionPerformed

    private void simCLExecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simCLExecActionPerformed
        plptool.PLPMsg.output = simCLOutput;
        SimCLI.init_core = true;
        lastCLCommand = simCLConsole.getText();
        simCLOutput.append("exec: " + simCLConsole.getText() + "\n");
        SimCLI.simCLCommand(simCLConsole.getText(), (SimCore) sim);
        if(simCLConsole.getText().trim().startsWith("asm")) {
            clearProgramMemoryTable();
            fillProgramMemoryTable();
        }
        simCLOutput.append("\n");
        simCLConsole.setText("");
        updateComponents();
}//GEN-LAST:event_simCLExecActionPerformed

    private void simCLConsoleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_simCLConsoleKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
            simCLExecActionPerformed(null);
        else if(simCLConsole.getText().equals("") && evt.getKeyCode() == java.awt.event.KeyEvent.VK_UP)
            simCLConsole.setText(lastCLCommand);
}//GEN-LAST:event_simCLConsoleKeyPressed

    public final void updateComponents() {
        long pc = ((SimCore)sim).id_stage.i_instrAddr;
        if(pc >= 0) {
            PC.setText(String.format("0x%08x", pc));
            nextInstr.setText(MIPSInstr.format(((SimCore)sim).id_stage.i_instruction));
        } else {
            PC.setText(String.format("0xXXXXXXXX"));
            nextInstr.setText("STALL");
        }

        for(int i = 0; i < 32; i++) {
            tblRegFile.setValueAt(((SimCore)sim).regfile.read(i), i, 2);
            tblRegFile.setValueAt(String.format("0x%08x", ((SimCore)sim).regfile.read(i)), i, 1);
        }

        javax.swing.table.DefaultTableModel memMap = (javax.swing.table.DefaultTableModel) tblMemMap.getModel();
        while(memMap.getRowCount() > 0)
                memMap.removeRow(0);
        for(int i = 0; i < sim.bus.getNumOfMods(); i++) {
            Object row[] = new Object[] {i, sim.bus.getRefMod(i),
                                         String.format("0x%08x", sim.bus.getModStartAddress(i)),
                                         String.format("0x%08x", sim.bus.getModEndAddress(i)),
                                         sim.bus.getEnabled(i) };
            memMap.addRow(row);
        }
        tblMemMap.setModel(memMap);

        backend.ioreg.gui_eval();

        updateProgramMemoryTablePC();
    }
/*
    public javax.swing.JTextArea getSimCLOutput() {
        return simCLOutput;
    }

    public javax.swing.JTextField getSimCLConsole() {
        return simCLConsole;
    }
*/
    public final void clearProgramMemoryTable() {
        javax.swing.table.DefaultTableModel program = (javax.swing.table.DefaultTableModel) tblProgram.getModel();

        while(program.getRowCount() > 0)
            program.removeRow(0);

        tblProgram.setModel(program);
    }

    public final void fillProgramMemoryTable() {
        javax.swing.table.DefaultTableModel program = (javax.swing.table.DefaultTableModel) tblProgram.getModel();

        Object[][] objCode = sim.memory.getValueSet();
        Object row[];
        for(int i = 0; i < objCode.length; i++) {
            if((Boolean) objCode[i][2]) {
                row = new Object[]
                      {"", false,
                       String.format("0x%08x", objCode[i][0]),
                       String.format("0x%08x", objCode[i][1]),
                       MIPSInstr.format((Long) objCode[i][1])};
                program.addRow(row);
            }
        }

        tblProgram.setModel(program);
    }

    public final void updateProgramMemoryTablePC() {
        if(((SimCore)sim).pc.eval() != old_pc) {
            javax.swing.table.DefaultTableModel program = (javax.swing.table.DefaultTableModel) tblProgram.getModel();

            for(int i = 0; i < program.getRowCount(); i++) {
                if(((SimCore)sim).id_stage.instrAddr == Long.parseLong(((String) program.getValueAt(i, 2)).substring(2), 16) &&
                   ((SimCore)sim).ex_stage.hot)
                    program.setValueAt("ID>", i, 0);
                else if(((SimCore)sim).ex_stage.instrAddr == Long.parseLong(((String) program.getValueAt(i, 2)).substring(2), 16) &&
                   ((SimCore)sim).mem_stage.hot)
                    program.setValueAt("EX>", i, 0);
                else if(((SimCore)sim).mem_stage.instrAddr == Long.parseLong(((String) program.getValueAt(i, 2)).substring(2), 16) &&
                   ((SimCore)sim).wb_stage.hot)
                    program.setValueAt("MEM>", i, 0);
                else if(((SimCore)sim).wb_stage.instrAddr == Long.parseLong(((String) program.getValueAt(i, 2)).substring(2), 16) &&
                   ((SimCore)sim).wb_stage.instr_retired)
                    program.setValueAt("WB>", i, 0);
                else
                    program.setValueAt("", i, 0);

                if(PC.getText().equals(program.getValueAt(i, 2)) && ((SimCore)sim).stall == 0)
                    program.setValueAt("IF>>>", i, 0);
            }

            tblProgram.setModel(program);
            
            old_pc = ((SimCore)sim).pc.eval();
        }
    }

    private javax.swing.table.DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField PC;
    private javax.swing.JCheckBox chkEXEXFwd;
    private javax.swing.JCheckBox chkMEMEXFwd;
    private javax.swing.JPanel coreConsolePane;
    private javax.swing.JTabbedPane coreMainPane;
    private javax.swing.JPanel coreMemMapPane;
    private javax.swing.JPanel coreProgramPane;
    private javax.swing.JPanel coreRegFilePane;
    private javax.swing.JPanel coreSimOptsPane;
    private javax.swing.JPanel coreVisPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblArchOpts;
    private javax.swing.JLabel lblBranchPrdction;
    private javax.swing.JTextField nextInstr;
    private javax.swing.JRadioButton rdioBrAlways;
    private javax.swing.JRadioButton rdioBrLast;
    private javax.swing.JRadioButton rdioBrNever;
    private javax.swing.JRadioButton rdioBrRandom;
    private javax.swing.JButton simCLClear;
    private javax.swing.JTextField simCLConsole;
    private javax.swing.JButton simCLExec;
    private javax.swing.JTextArea simCLOutput;
    private javax.swing.JTable tblMemMap;
    private javax.swing.JTable tblProgram;
    private javax.swing.JTable tblRegFile;
    // End of variables declaration//GEN-END:variables

}
