
package skyblue.Controller;

import skyblue.Model.UsuarioDA;
import skyblue.Model.VueloDA;
import View.CrearCuenta;
import skyblue.View.Menu;
import skyblue.View.Login;
import View.BuscarVuelo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import skyblue.View.CambiarPassword;

public class LoginController extends MouseAdapter implements ActionListener {
    
    //creacion de objetos
    Login loginView = new Login();  //vista inicio de sesion
    Menu BVV = new Menu();  //
    UsuarioDA modeloUsuario = new UsuarioDA();
    CrearCuenta crearCuentaView = new CrearCuenta();
    VueloDA VDA = new VueloDA();
    BuscarVuelo BV = new BuscarVuelo();
    CambiarPassword CP = new CambiarPassword(); //vista cambiar password
     
    public static java.sql.Time time; //para manejar el tiempo
    
    //constructor
    public LoginController(Login viewLogin, UsuarioDA modeloUsuario){
        System. out. println("Constructor\n"); 
        
        this.loginView = viewLogin; 
        this.modeloUsuario = modeloUsuario;
        
        this.loginView.iniciarSesionBTN.addActionListener(this);
        this.loginView.crearCuentaBTN.addActionListener(this);
        this.loginView.claveOlvidadaLBL.addMouseListener(this);
    }
     @Override
      public void mouseClicked(MouseEvent e) { //evento click
          
        //instanciando el controlador para cambio de password
        CambiarPasswordController CPC = new CambiarPasswordController(CP, modeloUsuario);
        CP.setVisible(true); //mostrando la vista cambio de password
     
     }
     
    public void actionPerformed(ActionEvent e){ 
        
      if(loginView.iniciarSesionBTN == e.getSource()){  //si se oprime iniciar sesion
          
        System. out. println("Inicio De Sesion Presionado\n");   
        
        String nombre = loginView.nombreUsuarioTXT.getText();   //obtener usuario
        String password = new String(loginView.password.getPassword()); //obtener password
        
        int iniciado = modeloUsuario.validarUsuario(nombre, password);  //validar usuario
        
        if(iniciado ==0){   //usuario incorrecto
            
            //alerta usuario incorrecto
            JOptionPane.showMessageDialog(loginView, "Nombre de Usuario o Contraseña incorrecto o el usuario no existe");
        
        }else{  //usuario correcto
            
           BVV.setVisible(true);    //mostrar el menu
           BVV.nombreLBL.setText(modeloUsuario.setNombreUsuario(nombre));   //obtener nombre
           BVV.paisLBL.setText(modeloUsuario.setPaisUsuario(nombre));   //obtener pais
           BVV.idUsuarioLBL.setText(nombre);    //obtener id usuario
           
           time = modeloUsuario.getSysTime(); //obtener tiempo dle sistema
           
           modeloUsuario.start();   //
           
           loginView.setVisible(false); //ocultar vista login
           
           CerrarController cc = new CerrarController(BVV, modeloUsuario); //controlador cerrar
           VueloController VC = new VueloController(BV, VDA);   //controlador vuelo
           
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
