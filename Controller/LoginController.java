
package Controller;

import Model.UsuarioDA;
import Model.VueloDA;
import View.CrearCuenta;
import View.Menu;
import View.Login;
import View.BuscarVuelo;
import View.CambiarPassword;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LoginController extends MouseAdapter implements ActionListener {
    Login loginView = new Login();
    Menu BVV = new Menu();
    UsuarioDA modeloUsuario = new UsuarioDA();
    CrearCuenta crearCuentaView = new CrearCuenta();
    public static java.sql.Time time;
    VueloDA VDA = new VueloDA();
    BuscarVuelo BV = new BuscarVuelo();
     CambiarPassword CP = new CambiarPassword();
    public LoginController(Login viewLogin, UsuarioDA modeloUsuario){
      this.loginView = viewLogin;
      this.modeloUsuario = modeloUsuario;
      this.loginView.iniciarSesionBTN.addActionListener(this);
      this.loginView.crearCuentaBTN.addActionListener(this);
      this.loginView.claveOlvidadaLBL.addMouseListener(this);
    }
     @Override
      public void mouseClicked(MouseEvent e) {
         CambiarPasswordController CPC = new CambiarPasswordController(CP, modeloUsuario);
         CP.setVisible(true);
     
     }
     
    public void actionPerformed(ActionEvent e){
      if(loginView.iniciarSesionBTN == e.getSource()){
        String nombre = loginView.nombreUsuarioTXT.getText();
        String password = new String(loginView.password.getPassword());
        int iniciado = modeloUsuario.validarUsuario(nombre, password);
        if(iniciado ==0){
            JOptionPane.showMessageDialog(loginView, "Nombre de Usuario o Contraseña incorrecto o el usuario no existe");
        }else{
           BVV.setVisible(true);
           BVV.nombreLBL.setText(modeloUsuario.setNombreUsuario(nombre));
           BVV.paisLBL.setText(modeloUsuario.setPaisUsuario(nombre));
           BVV.idUsuarioLBL.setText(nombre);
           time = modeloUsuario.getSysTime();
           modeloUsuario.start();
           loginView.setVisible(false);
           CerrarController cc = new CerrarController(BVV, modeloUsuario);
           VueloController VC = new VueloController(BV, VDA);
           VDA.LlenarPais(BV.OrigenCB);
           VDA.LlenarPais(BV.DestinoCB);
           BV.FechaSalida.setMinSelectableDate(modeloUsuario.getSysTime());
           BV.FechaRegreso.setMinSelectableDate(modeloUsuario.getSysTime());
           VDA.cambiar(BVV.MuestraContenido, BV);
           
        }
      }else if(loginView.crearCuentaBTN == e.getSource()){
          crearCuentaView.setVisible(true);
          modeloUsuario.setPais(crearCuentaView.paisCB);
          modeloUsuario.setCiudad(crearCuentaView.ciudadCB);
          VDA.LlenarMarca(crearCuentaView.TTramitada);
          CuentaController Cu_Co = new CuentaController(loginView, modeloUsuario, crearCuentaView);
          loginView.setVisible(false);
          
      }
      
    }
       
    
 
   
}
