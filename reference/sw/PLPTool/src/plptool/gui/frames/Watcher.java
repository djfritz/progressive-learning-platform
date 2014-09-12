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

import plptool.Config;
import plptool.PLPToolbox;
import plptool.gui.ProjectDriver;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

/**
 *
 * @author wira
 */
public class Watcher extends javax.swing.JFrame {

    ProjectDriver plp;

    /** Creates new form Watcher */
    public Watcher(ProjectDriver plp) {
        this.plp = plp;
        initComponents();

        CustomCellTextField textField = new CustomCellTextField(plp);
        CustomCellEditor ce = new CustomCellEditor(textField);
        tblEntries.setDefaultEditor(String.class, ce);

        cmbType.addItem("Bus");
        cmbType.addItem("Register");
        cmbValueFormat.removeAllItems();
        cmbValueFormat.addItem("Unsigned Decimal");
        cmbValueFormat.addItem("Signed Decimal");
        cmbValueFormat.addItem("Binary");
        cmbValueFormat.addItem("Binary - Least Significant 8 bits");
        cmbValueFormat.addItem("Unicode");
        cmbValueFormat.addItem("Packed 4 ASCII characters");

        updateFontSize();

        this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("resources/toolbar_watcher.png")));
        //plp.g_simsh.attachOptionSynchronizer(this, Constants.PLP_TOOLFRAME_WATCHER);
    }

    public DefaultTableModel getEntries() {
        return (DefaultTableModel) tblEntries.getModel();
    }

    public void setEntries(DefaultTableModel tblModel) {
        tblEntries.setModel(tblModel);
    }

    public static DefaultTableModel getTableInitialModel() {
        DefaultTableModel ret;
        ret = new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Address", "Hex Value", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

        return ret;
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAdd = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox<String>();
        lblAddr = new javax.swing.JLabel();
        txtAddr = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEntries = new javax.swing.JTable();
        btnRemoveSelected = new javax.swing.JButton();
        btnRemoveAll = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbValueFormat = new javax.swing.JComboBox<String>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(plptool.gui.PLPToolApp.class).getContext().getResourceMap(Watcher.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        lblAdd.setText(resourceMap.getString("lblAdd.text")); // NOI18N
        lblAdd.setName("lblAdd"); // NOI18N

        cmbType.setName("cmbType"); // NOI18N

        lblAddr.setText(resourceMap.getString("lblAddr.text")); // NOI18N
        lblAddr.setName("lblAddr"); // NOI18N

        txtAddr.setText(resourceMap.getString("txtAddr.text")); // NOI18N
        txtAddr.setName("txtAddr"); // NOI18N
        txtAddr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAddrKeyPressed(evt);
            }
        });

        btnAdd.setText(resourceMap.getString("btnAdd.text")); // NOI18N
        btnAdd.setName("btnAdd"); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblEntries.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Address", "Hex Value", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEntries.setName("tblEntries"); // NOI18N
        jScrollPane1.setViewportView(tblEntries);
        tblEntries.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblEntries.columnModel.title0")); // NOI18N
        tblEntries.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblEntries.columnModel.title1")); // NOI18N
        tblEntries.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblEntries.columnModel.title2")); // NOI18N
        tblEntries.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblEntries.columnModel.title3")); // NOI18N

        btnRemoveSelected.setText(resourceMap.getString("btnRemoveSelected.text")); // NOI18N
        btnRemoveSelected.setName("btnRemoveSelected"); // NOI18N
        btnRemoveSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveSelectedActionPerformed(evt);
            }
        });

        btnRemoveAll.setText(resourceMap.getString("btnRemoveAll.text")); // NOI18N
        btnRemoveAll.setName("btnRemoveAll"); // NOI18N
        btnRemoveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAllActionPerformed(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        cmbValueFormat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbValueFormat.setName("cmbValueFormat"); // NOI18N
        cmbValueFormat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbValueFormatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAddr)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRemoveAll)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(btnRemoveSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbValueFormat, 0, 650, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAdd)
                    .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAddr)
                    .addComponent(txtAddr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbValueFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemoveSelected)
                    .addComponent(btnRemoveAll))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        long addr, tempAddr;
        DefaultTableModel entries = getTblValues();
                
        switch(cmbType.getSelectedIndex()) {
            case 0:
                if(plp.isAssembled() && (tempAddr = plp.asm.resolveAddress(txtAddr.getText())) != -1)
                    addr = tempAddr;
                else
                    addr = PLPToolbox.parseNum(txtAddr.getText());

                if(plp.sim.bus.isMapped(addr)) {
                    Long data = (Long) plp.sim.bus.read(addr);
                    String label = plp.asm.lookupLabel(addr);
                    Object[] row = {"Bus", String.format("0x%08x", addr) + ((label != null) ? " [" + label + "]" : ""),
                                    (data != null) ? String.format("0x%08x", data & 0xffffffffL) : "Uninitialized",
                                    (data != null) ? convertValue(data) : "Uninitialized"};
                    entries.addRow(row);
                    tblEntries.setModel(entries);
                    plp.setModified();
                }

                break;
            case 1:
                if(plp.getArch().getID() == plptool.ArchRegistry.ISA_PLPMIPS) {
                    plptool.mips.SimCore mipsSim = (plptool.mips.SimCore) plp.sim;

                    Byte reg = ((plptool.mips.Asm) plp.asm).getRegisterNumberFromName(txtAddr.getText());
                    if(reg != null)
                        addr = reg;
                    else
                        addr = PLPToolbox.parseNum(txtAddr.getText());

                    if(addr >= 0 && addr <= mipsSim.regfile.endAddr()) {
                        Long data = (Long) mipsSim.regfile.read(addr);
                        Object[] row = {"Register", (reg != null ? txtAddr.getText() : String.format("0x%08x", addr)),
                                        (data != null) ? String.format("0x%08x", data & 0xffffffffL) : "Uninitialized",
                                        (data != null) ? convertValue(data) : "Uninitialized"};
                        entries.addRow(row);
                        tblEntries.setModel(entries);
                        plp.setModified();
                    }
                }

                break;
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveSelectedActionPerformed
        DefaultTableModel entries = getTblValues();
        int rowToDelete = tblEntries.getSelectedRow();
        if(rowToDelete > -1) {
            entries.removeRow(rowToDelete);
            plp.setModified();
        }
    }//GEN-LAST:event_btnRemoveSelectedActionPerformed

    private void btnRemoveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAllActionPerformed
        DefaultTableModel entries = getTblValues();
        while(tblEntries.getRowCount() > 0)
            entries.removeRow(0);

        plp.setModified();
    }//GEN-LAST:event_btnRemoveAllActionPerformed

    private void txtAddrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddrKeyPressed
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
            btnAddActionPerformed(null);
    }//GEN-LAST:event_txtAddrKeyPressed

    private void cmbValueFormatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbValueFormatActionPerformed
        updateWatcher();
    }//GEN-LAST:event_cmbValueFormatActionPerformed

    public final void updateFontSize() {
        tblEntries.setFont(new java.awt.Font(plptool.Config.devFont, java.awt.Font.PLAIN, plptool.Config.devFontSize));
        tblEntries.setRowHeight(tblEntries.getFontMetrics(tblEntries.getFont()).getHeight() + 5);
    }

    public void updateWatcher() {
        DefaultTableModel entries = getTblValues();

        for(int i = 0; i < entries.getRowCount(); i++) {
            String addr = (String) entries.getValueAt(i, 1);
            if(addr.startsWith("0x")) {
                addr = addr.substring(0, 10);
            }            
            if(entries.getValueAt(i, 0).equals("Bus") && plp.sim.bus.isInitialized(PLPToolbox.parseNum(addr))) {
                String label = plp.asm.lookupLabel(PLPToolbox.parseNum(addr));
                entries.setValueAt(String.format("0x%08x", PLPToolbox.parseNum(addr)) + ((label != null) ? " [" + label + "]" : ""), i, 1);
                Long data = (Long) plp.sim.bus.read(PLPToolbox.parseNum(addr));
                entries.setValueAt((data != null) ? String.format("0x%08x", data & 0xffffffffL) : "Uninitialized", i, 2);
                entries.setValueAt((data != null) ? convertValue(data) : "Uninitialized", i, 3);
            }
            else if(entries.getValueAt(i, 0).equals("Register") && plp.getArch().getStringID().equals("plpmips")) {
                plptool.mips.SimCore mipsSim = (plptool.mips.SimCore) plp.sim;

                long regAddr;

                String entry = (String)entries.getValueAt(i, 1);

                if(entry.startsWith("$")) {
                    regAddr = ((plptool.mips.Asm) plp.asm).getRegisterNumberFromName(entry);
                } else
                    regAddr = PLPToolbox.parseNum((String) entries.getValueAt(i, 1));

                Long data = (Long) mipsSim.regfile.read(regAddr);
                entries.setValueAt((data != null) ? String.format("0x%08x", data & 0xffffffffL) : "Uninitialized", i, 2);
                entries.setValueAt((data != null) ? convertValue(data) : "Uninitialized", i, 3);
            }
        }
    }

    public DefaultTableModel getTblValues() {
        return (DefaultTableModel) tblEntries.getModel();
    }

    public javax.swing.JTable getTable() {
        return tblEntries;
    }

    private String convertValue(long data) {
        switch(cmbValueFormat.getSelectedIndex()) {
            case 0:
                return String.valueOf(data & 0xffffffffL);
            case 1:
                return String.valueOf((int) data);
            case 2:
                return String.format("%32s", Long.toBinaryString(data & 0xffffffffL)).replace(' ', '0');
            case 3:
                return String.format("%8s", Long.toBinaryString(data & 0xffL)).replace(' ', '0');
            case 4:
                return "" + (char) data;
            case 5:
                return PLPToolbox.asciiWord(data);
            default:
                return "null";
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemoveAll;
    private javax.swing.JButton btnRemoveSelected;
    private javax.swing.JComboBox<String> cmbType;
    private javax.swing.JComboBox<String> cmbValueFormat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAdd;
    private javax.swing.JLabel lblAddr;
    private javax.swing.JTable tblEntries;
    private javax.swing.JTextField txtAddr;
    // End of variables declaration//GEN-END:variables

    class CustomCellTextField extends JTextField {
       
        public CustomCellTextField(ProjectDriver plp) {
            super();
            
            addFocusListener(
                new CellFocusListener(plp)
            );
        }        
    }
    
    class CustomCellEditor extends DefaultCellEditor {
        CustomCellTextField textField;
        
        public CustomCellEditor(CustomCellTextField textField) {
            super(textField);
            this.textField = textField;
        }
    }
    
    class CellFocusListener implements FocusListener {
        
        private ProjectDriver plp;
        private DefaultTableModel values;
        private int row;
        private int col;
        Object oldVal;
        
        public CellFocusListener(ProjectDriver plp) {
            this.plp = plp;
        }
        
        public void focusGained(FocusEvent e) {
            row = plp.g_watcher.getTable().getSelectedRow();
            col = plp.g_watcher.getTable().getSelectedColumn();
            oldVal = plp.g_watcher.getTblValues().getValueAt(row, col);
        }
        
        public void focusLost(FocusEvent e) {
            // update simulator state
            values = plp.g_watcher.getTblValues();

            String type = (String) values.getValueAt(row, 0);
            String addr = (String) values.getValueAt(row, 1);
            String val  = (String) values.getValueAt(row, col);
            
            long newVal = PLPToolbox.parseNum(val);
            long address;

            if(newVal == -1) {
                values.setValueAt(oldVal, row, col);
                plp.g_watcher.getTable().setModel(values);
                return;

            }  else {
                if(type.equals("Bus")) {
                    address = PLPToolbox.parseNum(addr.substring(0, 10));
                    plp.sim.bus.write(address, newVal, false);
                } else if(type.equals("Register")) {
                    if(plp.getArch().equals("plpmips")) {
                        if(addr.startsWith("$")) {
                            Byte regNum = ((plptool.mips.Asm) plp.asm).getRegisterNumberFromName(addr);
                            if(regNum != null)
                                address = regNum;
                            else
                                address = -1;

                        }  else
                            address = PLPToolbox.parseNum(addr);

                        if(address != -1)
                            ((plptool.mips.SimCore) (plp.sim)).regfile.write(address, newVal, false);
                    }
                }
            }

            plp.updateComponents(true);
        }
    }
}
