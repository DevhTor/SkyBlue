/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyblue.Controller;

import Model.UsuarioDA;
import Model.VueloDA;
import View.CambiarFecha;
import View.ComprasRealizadas;
import View.Itinerario;
import View.Menu;
import View.Reservaciones;
import View.ResultadosBusqueda;
import View.ResultadosBusqueda2;
import View.Tarjeta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Marleny Pena Tavarez
 */
public class PagarReservasController implements ActionListener {
    Tarjeta T = new Tarjeta();
    VueloDA VDA  = new VueloDA();
    VueloController VC = new VueloController();
    UsuarioDA UDA = new UsuarioDA();
    Menu men = new Menu();
    ComprasRealizadas CR = new ComprasRealizadas();
    ResultadosBusqueda RB = new ResultadosBusqueda();
    ResultadosBusqueda2 RB2 = new ResultadosBusqueda2();
    Itinerario IT = new Itinerario();
    ComprasController CC = new ComprasController();
    Reservaciones RV = new Reservaciones();
    ReservasController C2C = new ReservasController();
    
    public PagarReservasController(Tarjeta t, VueloDA vda){
      this.T = t;
      this.VDA = vda;
      this.T.TAcepto.addActionListener(this);
      this.T.TCancelo.addActionListener(this);
    }

    PagarReservasController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void actionPerformed(ActionEvent e){
           
      if(T.TAcepto == e.getSource()){
       if(T.TFVencimiento.getDate() == null || T.TCodigo.getText().equals("") || T.TNumero.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Hay campos vacios");         
       }else{          
          
            //Info Tarjeta
             String numero = T.TNumero.getText();
             java.util.Date Fven = T.TFVencimiento.getDate();
             int codigo = Integer.parseInt(T.TCodigo.getText());
             String marca = (String) T.TTramitada.getSelectedItem();
             String nombreU =  UDA.getNombreUsuario(men.idUsuarioLBL.getText());
             String apellidoU = UDA.getApellidoUsuario(men.idUsuarioLBL.getText());
             String Cedula = UDA.getCedulaUsuario(nombreU, apellidoU);
             String idUsuario = men.idUsuarioLBL.getText();
             int codigoCliente = UDA.getCodigoCliente(men.idUsuarioLBL.getText());
             int cantidad = VC.cant;
             CambiarFecha CF= new CambiarFecha();
             OpcionesCambioController OCC = new OpcionesCambioController(CF);  
             
             //Vuelo RV
             String AerolineaRV = C2C.AerolineaRV;
             String origenRV = C2C.origenRV;
             String destinoRV = C2C.destinoRV;
             String estadoRV = C2C.estadoRV;
             java.util.Date FechaRV = C2C.FechaRV; 
             java.util.Date HoraRV = C2C.HoraRV;
             int ClaseRV = C2C.ClaseRV;
             int PrecioRV = C2C.PrecioRV;
             int totalRV = C2C.totalRV;
             int AsientoRV = C2C.AsientoRV;
             
             java.util.Date FechaCF = OCC.fecha;
             java.util.Date HoraCF = OCC.hora;
             
           
             int tipoTarjeta;
             int codigoEstado;
                     
           switch (T.getTitle()) {
                case "Tarjeta Credito":
                           
                     tipoTarjeta = 1;
                     codigoEstado = 6;
                   
                  if(RV.isVisible() == true){
                        int codigoEstadoRV = 4;
                        int cantidadRV = 1;
                   /* JOptionPane.showMessageDialog(null, numero+" "+codigo+" "+marca+" "+Fven+" \n"+ 
                                    AerolineaRV+" "+Cedula+" "+origenRV+" "+destinoRV+" \n"+FechaRV+" "+HoraRV+" "+
                                    cantidadRV+" "+totalRV+" \n"+ tipoTarjeta+" "+codigoCliente+" "+idUsuario+" "+
                                    ClaseRV+" \n"+PrecioRV+" "+codigoEstadoRV+" "+estadoRV+" "+AsientoRV);
                    */    
                    VDA.PagarReserva(numero, codigo, marca, Fven, 
                                    AerolineaRV, Cedula, origenRV, destinoRV, FechaRV, HoraRV, cantidadRV, totalRV, 
                                    tipoTarjeta, codigoCliente, idUsuario, ClaseRV, codigoEstadoRV, AsientoRV);
                    T.setVisible(false);
                     
                  }
                    
                  break;
             case "Tarjeta Debito":
                     
                    tipoTarjeta = 2;
                    codigoEstado = 6;
                          
                    if(RV.isVisible() == true){
                         int codigoEstadoRV = 4;
                         int cantidadRV = 1;
                        JOptionPane.showMessageDialog(null, numero+" "+codigo+" "+marca+" "+Fven+" \n"+ 
                                    AerolineaRV+" "+Cedula+" "+origenRV+" "+destinoRV+" \n"+FechaRV+" "+HoraRV+" "+
                                    cantidadRV+" "+totalRV+" \n"+ tipoTarjeta+" "+codigoCliente+" "+idUsuario+" "+
                                    ClaseRV+" \n"+PrecioRV+" "+codigoEstadoRV+" "+estadoRV);
                          
                       VDA.PagarReserva(numero, codigo, marca, Fven, 
                                    AerolineaRV, Cedula, origenRV, destinoRV, FechaRV, HoraRV, cantidadRV, totalRV, 
                                    tipoTarjeta, codigoCliente, idUsuario, ClaseRV, codigoEstadoRV, AsientoRV);
                       
                          T.setVisible(false);
                     
                     }
                  break;
             default:
                 JOptionPane.showMessageDialog(null, "Error de seleccion");
            } 
      
         }
       }else if(T.TCancelo == e.getSource()){
            T.setVisible(false);
           
       } 
    }
    
}
