/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author atlas
 */
public class Login extends javax.swing.JFrame {

    private Connection conexion = null; //Objeto de la clase connection
    private ResultSet result = null; //Objeto de la clase resultSet
    private Statement statement = null; //Objeto de la clase Statement
    private PreparedStatement ps = null; //Objeto de la clase Prepared Statement

    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public void connection() {
        String url = "jdbc:postgresql://localhost:5432/parkingManagement";
        /*Direccion por default en donde se
        encuentra la base de datos*/
        String user = "postgres";
        String password = ".Eduardo0309.";

        if (conexion != null) {
            return;
        }

        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url, user, password);

            if (conexion != null) //Verificacion para saber si se conecto correctamente
            {
                System.out.println("Conectando a la base de datos\n");
            }

        } catch (Exception e) {
            System.out.println("Error de conexion: " + e.getMessage() + "\n");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        btnIngresar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        userText = new javax.swing.JTextField();
        passText = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CONTRASEÑA:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 150, 30));

        btnNew.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnNew.setText("Registro");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        getContentPane().add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 450, 90, 40));

        btnIngresar.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnIngresar.setText("LOGIN");
        btnIngresar.setFocusTraversalPolicyProvider(true);
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 350, 130, 50));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/login.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 220, 210));

        userText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(userText, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 250, 30));

        passText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(passText, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 250, 30));

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("USUARIO:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 100, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/background.jpg"))); // NOI18N
        jLabel1.setRequestFocusEnabled(false);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        AdminRegister navigationWindow;
        navigationWindow = new AdminRegister();
        navigationWindow.show();
        this.dispose();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        connection();
        try {

            ps = conexion.prepareStatement("SELECT * FROM guardias WHERE usuario = ? AND contrasenia = ?");
            ps.setString(1, userText.getText());
            ps.setString(2, passText.getText());
            result = ps.executeQuery();

            if (result.next()) //Validar si hay un siguiente registro
            {
                JOptionPane.showMessageDialog(null, "Bienvenido...");
                AccessControl navigationWindow;
                navigationWindow = new AccessControl();
                navigationWindow.show();
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectas.");

            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnIngresarActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnNew;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField passText;
    private javax.swing.JTextField userText;
    // End of variables declaration//GEN-END:variables
}
