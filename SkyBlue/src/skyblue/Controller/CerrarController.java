
package skyblue.Controller;
import skyblue.Model.*;
import skyblue.View.Login;
import skyblue.View.Menu;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import skyblue.View.Configuracion;

public class CerrarController implements ActionListener{
    Menu menu = new Menu();
    Login loginView = new Login();
    UsuarioDA modeloUsuario = new UsuarioDA();
    BuscarVuelo BV = new BuscarVuelo();
    VueloDA VDA = new VueloDA();
    Itinerario it = new Itinerario();
    ComprasRealizadas CR = new ComprasRealizadas();
    ResultadosBusqueda2 RRB = new ResultadosBusqueda2();
    ResultadosBusqueda RB = new ResultadosBusqueda();
    Reservaciones RV = new Reservaciones();
    Configuracion Co = new Configuracion();
    public static int vis;
    public CerrarController(Menu menu, UsuarioDA ModeloUsuario){
      this.modeloUsuario = ModeloUsuario;
      this.menu = menu;
      this.menu.cerrarSesionBTN.addActionListener(this);
      this.menu.BuscarVueloBTN.addActionListener(this);
      this.menu.ItinierarioBTN.addActionListener(this);
      this.menu.ComprasBTN.addActionListener(this);
      this.menu.ReservacionesBTN.addActionListener(this);
      this.menu.ConfiguracionBTN.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
        
      if(menu.cerrarSesionBTN ==e.getSource()){
          int sesion = JOptionPane.showConfirmDialog(null, "¿Deseas salir de la aplicacion?", "Cerrar Sesion", JOptionPane.YES_NO_OPTION);
          if(sesion == JOptionPane.YES_OPTION){
 
               loginView.setVisible(true);
               java.sql.Time tm = modeloUsuario.getSysTime();
                modeloUsuario.stop();
                modeloUsuario.guardaRegistro(LoginController.time, tm, menu.idUsuarioLBL.getText());
                menu.setVisible(false); 
                LoginController lc = new LoginController(loginView, modeloUsuario);
          } 
      }else if(menu.BuscarVueloBTN == e.getSource()){
                vis = 0;
                VueloController VC = new VueloController(BV, VDA);
                VDA.LlenarPais(BV.OrigenCB);
                VDA.LlenarPais(BV.DestinoCB);
                BV.FechaSalida.setMinSelectableDate(modeloUsuario.getSysTime());
                BV.FechaRegreso.setMinSelectableDate(modeloUsuario.getSysTime());
                VDA.cambiar(menu.MuestraContenido, BV);
      }
      else if(menu.ItinierarioBTN == e.getSource()){
             vis = 0;
             VDA.itinerario(it.model, it.ItinerarioTable, modeloUsuario.getCodigoCliente(menu.idUsuarioLBL.getText()));
             it.TotalLBL.setText("");
             RV.TotalLBL.setText("");
             ComprasController CC = new ComprasController(RB, RRB, VDA, it, RV);         
             VDA.cambiar(menu.MuestraContenido, it);
      }
      else if(menu.ComprasBTN == e.getSource()){
             VDA.comprasRealizadas(CR.model, CR.ComprasTable, modeloUsuario.getCodigoCliente(menu.idUsuarioLBL.getText()));
             VDA.cambiar(menu.MuestraContenido, CR);
      }
      else if(menu.ReservacionesBTN == e.getSource()){
             vis = 1;
             VDA.reservas(RV.model, RV.ReservasTB, modeloUsuario.getCodigoCliente(menu.idUsuarioLBL.getText()));
             RV.TotalLBL.setText("");
             it.TotalLBL.setText("");
             ReservasController C2C = new ReservasController(RV, VDA);         
             VDA.cambiar(menu.MuestraContenido, RV);
      }
      else if(menu.ConfiguracionBTN == e.getSource()){
           String idUsuario = menu.idUsuarioLBL.getText();
           ConfiguracionController CC = new ConfiguracionController(Co, modeloUsuario); 
           modeloUsuario.setPais(Co.paisCB);
           modeloUsuario.setCiudad(Co.ciudadCB);
           VDA.LlenarMarca(Co.TTramitada);
           modeloUsuario.setDatosUsuario(idUsuario, Co.nombreTXT, Co.apellidoTXT, 
                                         Co.paisCB, Co.ciudadCB, Co.direccionTXT, 
                                         Co.telefonoTXT, Co.emailTXT, Co.fechaNacDC, 
                                         Co.cedulaTXT, Co.TNumero, Co.TFVencimiento,
                                         Co.TCodigo, Co.TTramitada);
           VDA.cambiar(menu.MuestraContenido, Co);
      }
   
    }
   
}
