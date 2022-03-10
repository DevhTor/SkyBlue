
package Controller;

import Model.UsuarioDA;
import View.CrearCuenta;
import View.Login;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;

public class CuentaController implements ActionListener{
    Login loginView = new Login();
    CrearCuenta CC = new CrearCuenta();
    UsuarioDA modeloUsuario = new UsuarioDA();
    
    public CuentaController(Login loginView, UsuarioDA modeloUsuario, CrearCuenta CC){
        this.loginView = loginView;
        this.modeloUsuario = modeloUsuario;
        this.CC = CC;
        this.CC.crearCuentaBTN.addActionListener(this);
        this.CC.cancelarBTN.addActionListener(this);
    }
    
     public void actionPerformed(ActionEvent e){
       if(CC.crearCuentaBTN == e.getSource()){
           String nombre, apellido, pais, ciudad, direccion, telefono, email, 
                   idUsuario, password, conPassword, Cedula, numeroTarjeta;
           numeroTarjeta = CC.TNumero.getText();
           java.util.Date FVencimiento =CC.TFVencimiento.getDate();
           int TCodigo = Integer.parseInt(CC.TCodigo.getText());
           String marca = (String) CC.TTramitada.getSelectedItem();
           int tipoTarjeta = 1;
           nombre = CC.nombreTXT.getText();
           apellido = CC.apellidoTXT.getText();
           pais = (String) CC.paisCB.getSelectedItem();
           ciudad = (String) CC.ciudadCB.getSelectedItem();
           direccion = CC.direccionTXT.getText();
           java.util.Date dt = CC.fechaNacDC.getDate();
           telefono = CC.telefonoTXT.getText();
           email = CC.emailTXT.getText();
           idUsuario = CC.nombreUsuaioTXT.getText();
           password =String.valueOf(CC.password.getPassword());
           conPassword = String.valueOf(CC.passwordConfirm.getPassword());
           Cedula = CC.cedulaTXT.getText();
           String Sexo;
           if(CC.Femenino.isSelected()){
              Sexo = "F";
           }else{
              Sexo = "M";
           }
           
          if(modeloUsuario.existeUsuario(idUsuario) == false){
            if(nombre.equals("")||apellido.equals("")||direccion.equals("")||telefono.equals("")||email.equals("")
               ||idUsuario.equals("")||password.equals("")||conPassword.equals("")||numeroTarjeta.equals("")
               ||CC.TCodigo.equals("")||FVencimiento.equals("")){
                 JOptionPane.showMessageDialog(null, "Hay campos vacios");
            }else{
                if(password.equals(conPassword)){
                modeloUsuario.crearCliente(nombre, apellido, pais, ciudad, direccion, telefono, email, idUsuario, 
                              password, dt, Cedula, numeroTarjeta, FVencimiento,  TCodigo,
                              marca, tipoTarjeta, Sexo);
              
                JOptionPane.showMessageDialog(null, "Ceunta creada");
                   CC.setVisible(false);
                   loginView.setVisible(true);
                   LoginController view = new LoginController(loginView, modeloUsuario);
               }else{
                   JOptionPane.showMessageDialog(null, "Error: la contraseña no coincide ");
                   CC.password.setText("");
                   CC.passwordConfirm.setText("");
               }
            }
         }else{
            JOptionPane.showMessageDialog(null, "Ya existe un usuarion con este nombre de usuario");
         }
       }else if(CC.cancelarBTN == e.getSource()){
           loginView.setVisible(true);
           CC.setVisible(false);
       }
         
     }
}
