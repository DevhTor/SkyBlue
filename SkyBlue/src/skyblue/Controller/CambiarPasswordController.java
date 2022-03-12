/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyblue.Controller;

import Model.UsuarioDA;
import skyblue.View.CambiarPassword;
import View.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Marleny Pena Tavarez
 */
public class CambiarPasswordController implements ActionListener {

    CambiarPassword CP = new CambiarPassword(); 
    UsuarioDA UDA = new UsuarioDA();
    Menu menu = new Menu();
    public CambiarPasswordController(CambiarPassword CP, UsuarioDA UDA){
      this.CP = CP; 
      this.UDA = UDA;
      this.CP.PAceptar.addActionListener(this);
      this.CP.PCancelar.addActionListener(this);
    }   
    
    public void actionPerformed(ActionEvent e){
        String idUsuario = menu.idUsuarioLBL.getText();
        String password = String.valueOf(CP.password.getPassword());
        String passwordC = String.valueOf(CP.passwordConfirm.getPassword());
        
        if(CP.PAceptar == e.getSource()){
           
           if(password.equals(passwordC) && !password.equals("") && !passwordC.equals("")){
              UDA.actualizarPassword(idUsuario, password);
              CP.setVisible(false);
           }else if(!password.equals(passwordC) && !password.equals("") && !passwordC.equals("")){
             JOptionPane.showMessageDialog(null, "La contraseña no coincide");
             CP.password.setText("");
             CP.passwordConfirm.setText("");
           }else{
             JOptionPane.showMessageDialog(null, "Debe ingresar la cotraseña");
           }
           
        }else{
           CP.setVisible(false);
        }
    
    }

}
 