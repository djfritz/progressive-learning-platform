/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProgramVisualizationFrame.java
 *
 * Created on Nov 10, 2011, 1:35:41 PM
 */

package plptool.mips.visualizer;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.decorators.*;

/**
 * Create the JUNG Visualization graphically in a pop-up window.
 *
 * @author will
 */
public class ProgramVisualizationFrame extends javax.swing.JFrame {

    private ProgramVisualization progVis;
    private ProgramVisualization.programGraph progGraph;


    private Layout layout;
    private BasicVisualizationServer progVisServ;

    /** Creates new form ProgramVisualizationFrame */
    public ProgramVisualizationFrame(ProgramVisualization progVis, ProgramVisualization.programGraph progGraph) {
        initComponents();

        this.progVis = progVis;
        this.progGraph= progGraph;

        Layout<String, String> layout = new ISOMLayout<String,String>(progGraph.buildGraph());
        // tree layout stuff
        //DelegateForest sherwood = new DelegateForest(progGraph.buildGraph());
        //Layout<String, String> layout = new TreeLayout<String,String>(progGraph.buildGraph());
        this.setSize(1024, 768);
        layout.setSize(this.getSize());
        BasicVisualizationServer<String,String> progVisServ = new BasicVisualizationServer<String,String>(layout);
        progVisServ.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

        getContentPane().add(progVisServ);
        progVisServ.setSize(this.getSize());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
