/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyblue.Controller;

import skyblue.Model.UsuarioDA;
import skyblue.View.Configuracion;
import skyblue.View.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Marleny Pena Tavarez
 */
public class ConfiguracionController implements ActionListener {
    
    Configuracion CO = new Configuracion();
    UsuarioDA modeloUsuario = new UsuarioDA();
    Menu menu = new Menu();
    public ConfiguracionController(Configuracion CO, UsuarioDA ModeloUsuario){
      this.modeloUsuario = ModeloUsuario;
      this.CO = CO;
      this.CO.actualizarInfo.addActionListener(this);
    }
    
     public void actionPerformed(ActionEvent e){
          
          String idUsuario = menu.idUsuarioLBL.getText();
          String nombre = CO.nombreTXT.getText();
          String apellido = CO.apellidoTXT.getText();
          String Pais = (String)CO.paisCB.getSelectedItem();
          String Ciudad = (String)CO.ciudadCB.getSelectedItem();
          String email = CO.emailTXT.getText();
          String Telefono = CO.telefonoTXT.getText();
          String password = String.valueOf(CO.password.getPassword()); 
          String passwordC = String.valueOf(CO.passwordConfirm.getPassword()); 
          String direccion = CO.direccionTXT.getText();
          String Cedula = CO.cedulaTXT.getText();
          java.util.Date fecha_Na =CO.fechaNacDC.getDate();
          String codigoTarjeta = CO.TNumero.getText();
          int clave = Integer.parseInt(CO.TCodigo.getText());
          java.util.Date Fven = CO.TFVencimiento.getDate();
          String Marca = (String)CO.TTramitada.getSelectedItem();
          if(password.equals(passwordC) && !password.equals("") && !passwordC.equals("")){
             modeloUsuario.actualizaDatosUsuario(idUsuario, nombre, apellido, Pais, Ciudad, direccion,
                                                 Telefono, Cedula, email, password, fecha_Na, codigoTarjeta,
                                                 Fven, clave, Marca);
          }else if(!password.equals(passwordC) && !password.equals("") && !passwordC.equals("")){
             JOptionPane.showMessageDialog(null, "La contraseña no coincide");
             CO.password.setText("");
             CO.passwordConfirm.setText("");
          }else{
             JOptionPane.showMessageDialog(null, "Debe ingresar la cotraseña para confirmar el cambio");
          }
     }
    
}
