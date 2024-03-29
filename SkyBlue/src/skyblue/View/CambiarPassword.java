/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyblue.View;

/**
 *
 * @author Marleny Pena Tavarez
 */
public class CambiarPassword extends javax.swing.JFrame {

    /**
     * Creates new form CambiarPassword
     */
    public CambiarPassword() {
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

        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        nombreUsuaioTXT = new javax.swing.JTextField();
        PAceptar = new javax.swing.JButton();
        PCancelar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        idUsuarioTXT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        passwordConfirm = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Nombre de Usuario");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/skyblue/Botones/images.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PAceptar.setText("Aceptar");
        PAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(PAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 130, 30));

        PCancelar.setText("Cancelar");
        getContentPane().add(PCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 130, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Nombre de Usuario");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        idUsuarioTXT.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(idUsuarioTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 204, 25));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Contraseña");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Confirmar contraseña");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        password.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 204, 25));

        passwordConfirm.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(passwordConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 204, 25));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/skyblue/Botones/CambiarClave.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 420, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/skyblue/Botones/SkyBlue.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PAceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PAceptarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CambiarPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CambiarPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CambiarPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CambiarPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CambiarPassword().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton PAceptar;
    public javax.swing.JButton PCancelar;
    public javax.swing.JTextField idUsuarioTXT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JTextField nombreUsuaioTXT;
    public javax.swing.JPasswordField password;
    public javax.swing.JPasswordField passwordConfirm;
    // End of variables declaration//GEN-END:variables
}
