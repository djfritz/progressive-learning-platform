/*
    Copyright 2012-2013 PLP Contributors

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

package plptool.extras.flowchart;

import plptool.*;
import plptool.gui.PLPToolApp;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.imageio.*;
import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import java.awt.*;

/**
 *
 * @author wira
 */
public class DisplayFlowchart extends javax.swing.JFrame {
    private PLPCPUProgram p;
    private Image i;
    private Image originalI;
    private JLabel fLabel;
    private double zoomFactor;
    private BufferedImage img;
    private final double ZOOM_SCALING = 1.5;
    private generateDiagram generateDiagramThread;

    /** Creates new form ExportDOT */
    public DisplayFlowchart(java.awt.Frame parent) {
        initComponents();
        init();
        this.setLocationRelativeTo(parent);
        this.generateDiagramThread = null;
    }

    private void init() {
        PLPToolbox.attachHideOnEscapeListener(this);
        fLabel = new JLabel();
        scroller.getViewport().add(fLabel);
        zoomFactor = 1;
    }

    public void clearCanvas() {
        fLabel.setIcon(null);
    }

    public void update(PLPCPUProgram p) {
        this.p = p;
        cmbRoutines.removeAllItems();
        for(int i = 0; i < p.getNumberOfRoutines(); i++)
            cmbRoutines.addItem(p.getRoutine(i).getHead().getLabel());
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
        cmbRoutines = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        btnCancel = new javax.swing.JButton();
        btnDisplay = new javax.swing.JButton();
        scroller = new javax.swing.JScrollPane();
        btnZoomOut = new javax.swing.JButton();
        btnOriginal = new javax.swing.JButton();
        btnZoomIn = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        chkColors = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generate Flowchart");

        jLabel1.setText("Select which routine to display:");

        cmbRoutines.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCancel.setText("Close");
        btnCancel.setPreferredSize(new java.awt.Dimension(90, 23));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnDisplay.setText("Display");
        btnDisplay.setPreferredSize(new java.awt.Dimension(85, 23));
        btnDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayActionPerformed(evt);
            }
        });

        btnZoomOut.setText("-");
        btnZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomOutActionPerformed(evt);
            }
        });

        btnOriginal.setText("Original");
        btnOriginal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOriginalActionPerformed(evt);
            }
        });

        btnZoomIn.setText("+");
        btnZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomInActionPerformed(evt);
            }
        });

        btnSave.setText("Save Flowchart");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        chkColors.setText("Use colors");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroller, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnZoomOut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOriginal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnZoomIn)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cmbRoutines, 0, 419, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkColors)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbRoutines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkColors))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroller, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnZoomOut)
                    .addComponent(btnOriginal)
                    .addComponent(btnZoomIn)
                    .addComponent(btnSave))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayActionPerformed
        if(generateDiagramThread == null) {
            generateDiagramThread = new generateDiagram(0);
            generateDiagramThread.start();
        }
    }//GEN-LAST:event_btnDisplayActionPerformed

    private void btnZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomOutActionPerformed
        if(generateDiagramThread == null) {
            generateDiagramThread = new generateDiagram(1);
            generateDiagramThread.start();
        }
    }//GEN-LAST:event_btnZoomOutActionPerformed

    private void btnZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomInActionPerformed
        if(generateDiagramThread == null) {
            generateDiagramThread = new generateDiagram(2);
            generateDiagramThread.start();
        }
    }//GEN-LAST:event_btnZoomInActionPerformed

    private void btnOriginalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOriginalActionPerformed
        zoomFactor = 1;
        fLabel.setIcon(new ImageIcon(originalI));
    }//GEN-LAST:event_btnOriginalActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        File f = PLPToolbox.saveFileDialog(Constants.launchPath,
                PLPToolbox.createFileFilter("png", "PNG image"));
        if(f != null) {
            try {
                ImageIO.write(img, "png", f);
            } catch(IOException e) {
                Msg.error("Failed to write '" + f.getAbsolutePath() + "'",
                        Constants.PLP_GENERAL_IO_ERROR, null);
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    class generateDiagram extends Thread {
        private int function;

        public generateDiagram(int function) {
            this.function = function;
        }

        @Override
        public void run() {
            int lx, ly, nx, ny;
            BufferedImage resized;
            Graphics2D g2;
            switch(function) {
                case 0:
                    int routineIndex = cmbRoutines.getSelectedIndex();
                    if(routineIndex > -1) {
                        String tempDOTPath = PLPToolbox.getTmpDir() + "/flowchart_temp.dot";
                        String tempPNGPath = PLPToolbox.getTmpDir() + "/flowchart_temp.png";
                        String dotPath = PLPToolApp.getAttributes().get("flowchart_dotpath");
                        PLPToolbox.writeFile(p.generateDOT(routineIndex,
                                chkColors.isSelected()), tempDOTPath);
                        if(dotPath != null) {
                            PLPToolbox.execute(dotPath + " -Tpng " + tempDOTPath +
                                    " -o " + tempPNGPath);
                            try {
                                img = ImageIO.read(new File(tempPNGPath));
                                i = java.awt.Toolkit.getDefaultToolkit().createImage(img.getSource());
                                originalI = i;
                                zoomFactor = 1;
                                fLabel.setIcon(new ImageIcon(i));
                            } catch(IOException e) {
                                Msg.error("Unable to read DOT output.", Constants.PLP_GENERAL_IO_ERROR,
                                        null);
                            }
                        }
                    }
                    break;
                case 1:
                    zoomFactor /= ZOOM_SCALING;
                    lx = originalI.getWidth(null);
                    ly = originalI.getHeight(null);
                    nx = (int)(zoomFactor * lx);
                    ny = (int)(zoomFactor * ly);
                    resized = new BufferedImage(nx, ny, img.getType());
                    g2 = resized.createGraphics();
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2.drawImage(img, 0, 0, nx, ny, 0, 0, img.getWidth(), img.getHeight(), null);
                    //i = originalI.getScaledInstance((int)(lx*zoomFactor), (int)(ly*zoomFactor), Image.SCALE_SMOOTH);
                    fLabel.setIcon(new ImageIcon(resized));
                    break;
                case 2:
                    zoomFactor *= ZOOM_SCALING;
                    lx = originalI.getWidth(null);
                    ly = originalI.getHeight(null);
                    nx = (int)(zoomFactor * lx);
                    ny = (int)(zoomFactor * ly);
                    resized = new BufferedImage(nx, ny, img.getType());
                    g2 = resized.createGraphics();
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2.drawImage(img, 0, 0, nx, ny, 0, 0, img.getWidth(), img.getHeight(), null);
                    //i = originalI.getScaledInstance((int)(lx*zoomFactor), (int)(ly*zoomFactor), Image.SCALE_SMOOTH);
                    fLabel.setIcon(new ImageIcon(resized));
                    break;
            }
            generateDiagramThread = null;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDisplay;
    private javax.swing.JButton btnOriginal;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnZoomIn;
    private javax.swing.JButton btnZoomOut;
    private javax.swing.JCheckBox chkColors;
    private javax.swing.JComboBox cmbRoutines;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane scroller;
    // End of variables declaration//GEN-END:variables

}
