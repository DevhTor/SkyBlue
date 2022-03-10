
package Controller;

import Controller.*;
import Model.*;
import View.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SkyBlue {
    public static void main(String[] args) {
        try{
  
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
          }
          catch (Exception e)
           {
            e.printStackTrace();
           }
        
        
        Login loginView = new Login();
        UsuarioDA usuario = new UsuarioDA();

        LoginController lc = new LoginController(loginView, usuario);
        loginView.setVisible(true);
       
    }    
    
}
