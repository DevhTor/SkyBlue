/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marleny Pena Tavarez
 */
public class ResultadosBusqueda2 extends javax.swing.JPanel {

    /**
     * Creates new form ResultadosBusqueda2
     */
    public ResultadosBusqueda2() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ResTable1 = new javax.swing.JTable();
        ComprarBTN = new javax.swing.JButton();
        ReservarBTN = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ResTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CancelarBTN = new javax.swing.JButton();
        TotalLBL = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TotalLBL1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TotalLBL2 = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/ResultadosBusqueda.png"))); // NOI18N
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 424, -1));

        ResTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Aerolinea", "Origen", "Destino", "Fecha", "Hora", "Clase", "precio"
            }
        ));
        ResTable1.setShowHorizontalLines(false);
        ResTable1.setShowVerticalLines(false);
        jScrollPane1.setViewportView(ResTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 600, 130));

        ComprarBTN.setText("Comprar");
        ComprarBTN.setToolTipText("");
        add(ComprarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 168, 32));

        ReservarBTN.setText("Reservar");
        ReservarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReservarBTNActionPerformed(evt);
            }
        });
        add(ReservarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 170, 32));

        ResTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Aerolinea", "Origen", "Destino", "Fecha", "Hora", "Clase", "precio"
            }
        ));
        ResTable.setShowHorizontalLines(false);
        ResTable.setShowVerticalLines(false);
        jScrollPane2.setViewportView(ResTable);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 600, 140));

        jLabel1.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel1.setText("Vuelo de Ida");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel2.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel2.setText("Vuelo de vuelta");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        CancelarBTN.setText("Cancelar");
        CancelarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarBTNActionPerformed(evt);
            }
        });
        add(CancelarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 412, 170, 30));

        TotalLBL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        add(TotalLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 90, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Total a pagar:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, 90, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Total:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, -1, 20));

        TotalLBL1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        add(TotalLBL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, 90, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Total:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 370, -1, 20));

        TotalLBL2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        add(TotalLBL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 390, 90, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void ReservarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReservarBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ReservarBTNActionPerformed

    private void CancelarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CancelarBTNActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton CancelarBTN;
    public javax.swing.JButton ComprarBTN;
    public javax.swing.JTable ResTable;
    public javax.swing.JTable ResTable1;
    public javax.swing.JButton ReservarBTN;
    public javax.swing.JLabel TotalLBL;
    public javax.swing.JLabel TotalLBL1;
    public javax.swing.JLabel TotalLBL2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
    public DefaultTableModel model = new DefaultTableModel();
}
