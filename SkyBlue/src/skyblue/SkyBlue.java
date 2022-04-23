/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package skyblue;

import skyblue.View.Login;
import skyblue.Controller.LoginController;
import skyblue.Model.UsuarioDA;

/**
 *
 * @author Hector
 */
public class SkyBlue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Login login = new Login();
        UsuarioDA usuarioDA = new UsuarioDA();
        
        LoginController loginController = new LoginController(login, usuarioDA);
        
        login.setVisible(true);
        
   
        
    }
    
}
