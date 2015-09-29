/*
    Copyright 2010-2011 David Fritz, Brian Gordon, Wira Mulia

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

package plptool.gui;

import plptool.Msg;
import plptool.Constants;

/**
 *
 * @author wira
 */
public class NumberConverter extends javax.swing.JFrame {

    private boolean standalone;

    /** Creates new form NumberConverter */
    public NumberConverter(boolean standalone) {
        initComponents();

        this.standalone = standalone;

        if(standalone) {
            this.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent winEvt) {
                    System.exit(-1);
                }
            });
        } else {
            javax.swing.KeyStroke escapeKeyStroke = javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0, false);
            javax.swing.Action escapeAction = new javax.swing.AbstractAction() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    setVisible(false);
                }
            };

            getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
            getRootPane().getActionMap().put("ESCAPE", escapeAction);

            this.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent winEvt) {
                    setVisible(false);
                }
            });
        }

        setLocationRelativeTo(null);

        cmbBase.removeAllItems();
        cmbBase.addItem("ASCII (Unicode)");
        cmbBase.addItem("Decimal");
        cmbBase.addItem("Binary");
        cmbBase.addItem("Octal");
        cmbBase.addItem("Hexadecimal");
    }

    private void convert() {
        boolean valid = false;
        long number = -1;

        try {


        switch(cmbBase.getSelectedIndex()) {
            case 0:
                if(txtInput.getText().length() == 1) {
                    number = txtInput.getText().charAt(0);
                    valid = true;
                }

                break;

            case 1:
                number = Long.parseLong(txtInput.getText());
                valid = true;
                break;
            case 2:
                number = Long.parseLong(txtInput.getText(), 2);
                valid = true;
                break;

            case 3:
                number = Long.parseLong(txtInput.getText(), 8);
                valid = true;
                break;

            case 4:
                number = Long.parseLong(txtInput.getText(), 16);
                valid = true;
                break;

         }

        if(valid) {
            txtASCII.setText("" + ((char) number));
            txtBinary.setText(Long.toBinaryString(number));
            txtDecimal.setText("" + number);
            txtOctal.setText(String.format("%o", number));
            txtHex.setText(String.format("%h", number));
        } else
            Msg.error("Can not convert the number '" + txtInput.getText() + "'", Constants.PLP_GENERIC_ERROR, null);

        } catch(Exception e) {
            Msg.error("Can not convert the number '" + txtInput.getText() + "'", Constants.PLP_GENERIC_ERROR, null);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cmbBase = new javax.swing.JComboBox<String>();
        txtInput = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtBinary = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtOctal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtHex = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDecimal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtASCII = new javax.swing.JTextField();
        btnClose = new javax.swing.JButton();
        btnConvert = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(plptool.gui.PLPToolApp.class).getContext().getResourceMap(NumberConverter.class);
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        cmbBase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbBase.setName("cmbBase"); // NOI18N

        txtInput.setText(resourceMap.getString("txtInput.text")); // NOI18N
        txtInput.setName("txtInput"); // NOI18N
        txtInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtInputKeyPressed(evt);
            }
        });

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtBinary.setEditable(false);
        txtBinary.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBinary.setText(resourceMap.getString("txtBinary.text")); // NOI18N
        txtBinary.setName("txtBinary"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtOctal.setEditable(false);
        txtOctal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtOctal.setText(resourceMap.getString("txtOctal.text")); // NOI18N
        txtOctal.setName("txtOctal"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtHex.setEditable(false);
        txtHex.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtHex.setText(resourceMap.getString("txtHex.text")); // NOI18N
        txtHex.setName("txtHex"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        txtDecimal.setEditable(false);
        txtDecimal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDecimal.setText(resourceMap.getString("txtDecimal.text")); // NOI18N
        txtDecimal.setName("txtDecimal"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        txtASCII.setEditable(false);
        txtASCII.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtASCII.setText(resourceMap.getString("txtASCII.text")); // NOI18N
        txtASCII.setName("txtASCII"); // NOI18N

        btnClose.setText(resourceMap.getString("btnClose.text")); // NOI18N
        btnClose.setName("btnClose"); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnConvert.setText(resourceMap.getString("btnConvert.text")); // NOI18N
        btnConvert.setName("btnConvert"); // NOI18N
        btnConvert.setPreferredSize(new java.awt.Dimension(46, 27));
        btnConvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConvertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtInput)
                            .addComponent(cmbBase, 0, 495, Short.MAX_VALUE))))
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(553, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtASCII)
                    .addComponent(txtDecimal)
                    .addComponent(txtHex)
                    .addComponent(txtOctal)
                    .addComponent(txtBinary, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(367, Short.MAX_VALUE)
                .addComponent(btnConvert, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtBinary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtOctal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtHex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDecimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtASCII, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose)
                    .addComponent(btnConvert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        if(standalone)
            System.exit(-1);

        setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void txtInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInputKeyPressed
        if(evt.isActionKey() || evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            convert();
        }
    }//GEN-LAST:event_txtInputKeyPressed

    private void btnConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConvertActionPerformed
            convert();
    }//GEN-LAST:event_btnConvertActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NumberConverter(true).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnConvert;
    private javax.swing.JComboBox<String> cmbBase;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField txtASCII;
    private javax.swing.JTextField txtBinary;
    private javax.swing.JTextField txtDecimal;
    private javax.swing.JTextField txtHex;
    private javax.swing.JTextField txtInput;
    private javax.swing.JTextField txtOctal;
    // End of variables declaration//GEN-END:variables

}
