/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyblue.Controller;

import View.OpcionesPago;
import skyblue.Controller.OpcionesController;
import View.Tarjeta;
import skyblue.View.Menu;
import View.ResultadosBusqueda;
import View.ResultadosBusqueda2;
import skyblue.Model.VueloDA;
import skyblue.Model.UsuarioDA;
import View.CambiarFecha;
import View.ComprasRealizadas;
import View.Itinerario;
import View.Reservaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Marleny Pena Tavarez
 */
public class TarjetaController implements ActionListener {
    
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

    
    public TarjetaController(Tarjeta t, VueloDA vda){
      this.T = t;
      this.VDA = vda;
      this.T.TAcepto.addActionListener(this);
      this.T.TCancelo.addActionListener(this);
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
             
             //Info Vuelo
             String Aerolinea = CC.Aerolinea;
             String origen = CC.origen;
             String destino = CC.destino;
             java.util.Date Fecha = CC.Fecha; 
             java.util.Date Hora = CC.Hora;
             int Clase = CC.Clase;
             int Precio = CC.Precio;
             int total = CC.total;
                   
             //Info Vuelo ida y vuelta
             //ida
             String Aerolinea1 = CC.Aerolinea1;
             String origen1 = CC.origen1;
             String destino1 = CC.destino1;
             java.util.Date Fecha1 = CC.Fecha1; 
             java.util.Date Hora1 = CC.Hora1;
             int Clase1 = CC.Clase1;
             int Precio1 = CC.Precio1;
             int total1 = CC.total1;
            
             //vuelta
             String Aerolinea2 = CC.Aerolinea2;
             String origen2 = CC.origen2;
             String destino2 = CC.destino2;
             java.util.Date Fecha2 = CC.Fecha2; 
             java.util.Date Hora2 = CC.Hora2;
             int Clase2 = CC.Clase2;
             int Precio2 = CC.Precio2;
             int total2 = CC.total2; 
             
             //VueloIT
             String AerolineaIT = CC.AerolineaIT;
             String origenIT = CC.origenIT;
             String destinoIT = CC.destinoIT;
             String estadoIT = CC.estadoIT;
             java.util.Date FechaIT = CC.FechaIT; 
             java.util.Date HoraIT = CC.HoraIT;
             int ClaseIT = CC.ClaseIT;
             int PrecioIT = CC.PrecioIT;
             int totalIT = CC.totalIT;
             int AsientoIT = CC.AsientoIT;
             
             //Vuelo RV
            /* String AerolineaRV = C2C.AerolineaRV;
             String origenRV = C2C.origenRV;
             String destinoRV = C2C.destinoRV;
             String estadoRV = C2C.estadoRV;
             java.util.Date FechaRV = C2C.FechaRV; 
             java.util.Date HoraRV = C2C.HoraRV;
             int ClaseRV = C2C.ClaseRV;
             int PrecioRV = C2C.PrecioRV;
             int totalRV = C2C.totalRV;
             int AsientoRV = C2C.AsientoRV;*/
             
             java.util.Date FechaCF = OCC.fecha;
             java.util.Date HoraCF = OCC.hora;
             
           
             int tipoTarjeta;
             int codigoEstado;
                     
           switch (T.getTitle()) {
                case "Tarjeta Credito":
                           
                     tipoTarjeta = 1;
                     codigoEstado = 6;
                    if(IT.isVisible() == true && CC.clic == false){
                     
                         try {
                              int codigoBoleto;
                              codigoBoleto = VDA.getCodigoBoleto(AerolineaIT, origenIT, destinoIT, FechaIT, HoraIT, idUsuario, AsientoIT);
                              JOptionPane.showMessageDialog(null, numero+" "+codigo+" "+marca+" "+Fven+" \n"+ 
                                    AerolineaIT+" "+Cedula+" "+origenIT+" "+destinoIT+" \n"+FechaIT+" "+HoraIT
                                    +" \n"+ tipoTarjeta+" "+codigoCliente+" "+idUsuario
                                    +" \n"+PrecioIT+" "+codigoEstado+"  "+codigoBoleto);
                           
                            /* VDA.cancelarVuelo(codigoBoleto, numero, codigo, marca, Fven, 
                                           AerolineaIT, Cedula, origenIT, destinoIT, FechaIT, HoraIT, tipoTarjeta, PrecioIT);
                             */T.setVisible(false);  
                          } catch (SQLException ex) {
                             Logger.getLogger(TarjetaController.class.getName()).log(Level.SEVERE, null, ex);
                          }
                     }else if(IT.isVisible() == true && CC.clic == true){
                        try{
                         VDA.cambiarFecha(numero, codigo, marca, Fven, idUsuario, AerolineaIT, Cedula, origenIT,
                                           destinoIT, FechaIT, HoraIT, FechaCF, HoraCF, tipoTarjeta, PrecioIT, AsientoIT); 
                         T.setVisible(false);
                        } catch (SQLException ex) {
                             Logger.getLogger(TarjetaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                     }
                     else if(RB.isVisible() == true){
                        String estado = "P";
                        VDA.comprar(numero, codigo, marca, Fven, 
                                    Aerolinea, Cedula, origen, destino, Fecha, Hora, cantidad, total, 
                                    tipoTarjeta, codigoCliente, idUsuario, Clase, Precio, codigoEstado, estado);
                         T.setVisible(false);
                         VDA.cambiar(men.MuestraContenido, CR);
                     }else if(RB2.isVisible() == true){
                        String estado = "P";
                        VDA.comprar(numero, codigo, marca, Fven, 
                                    Aerolinea1, Cedula, origen1, destino1, Fecha1, Hora1, cantidad, total1, 
                                    tipoTarjeta, codigoCliente, idUsuario, Clase1, Precio1, codigoEstado, estado);
                        VDA.comprar(numero, codigo, marca, Fven,
                                    Aerolinea2, Cedula, origen2, destino2, Fecha2, Hora2, cantidad, total2, 
                                    tipoTarjeta, codigoCliente, idUsuario, Clase2, Precio2, codigoEstado, estado);
                        T.setVisible(false);
                        VDA.cambiar(men.MuestraContenido, CR);
                     }
                    
                  break;
             case "Tarjeta Debito":
                     
                    tipoTarjeta = 2;
                    codigoEstado = 6;
                          
                   if(IT.isVisible() == true && CC.clic == false){
                         try {
                            int codigoBoleto;
                            codigoBoleto = VDA.getCodigoBoleto(AerolineaIT, origenIT, destinoIT, FechaIT, HoraIT, idUsuario, PrecioIT);
                            VDA.cancelarVuelo(codigoBoleto, numero, codigo, marca, Fven, 
                                           AerolineaIT, Cedula, origenIT, destinoIT, FechaIT, HoraIT, tipoTarjeta, PrecioIT);
        
                            T.setVisible(false);  
                         } catch (SQLException ex) {
                            Logger.getLogger(TarjetaController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                    }else if(IT.isVisible() == true && CC.clic == true ){
                      try{
                         VDA.cambiarFecha(numero, codigo, marca, Fven, idUsuario, AerolineaIT, Cedula, origenIT,
                                           destinoIT, FechaIT, HoraIT, FechaCF, HoraCF, tipoTarjeta, PrecioIT, AsientoIT); 
                         JOptionPane.showMessageDialog(null, FechaCF+" "+HoraCF);
                         T.setVisible(false);
                      } catch (SQLException ex) {
                         Logger.getLogger(TarjetaController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                    }else if(RB.isVisible() == true){
                       String estado = "P";
                       VDA.comprar(numero, codigo, marca, Fven, 
                                   Aerolinea, Cedula, origen, destino, Fecha, Hora, cantidad, total, 
                                   tipoTarjeta, codigoCliente, idUsuario, Clase, Precio, codigoEstado, estado);
                       T.setVisible(false);
                       VDA.cambiar(men.MuestraContenido, CR);
                    }else if(RB2.isVisible() == true){
                       String estado = "P";
                       VDA.comprar(numero, codigo, marca, Fven, 
                                   Aerolinea1, Cedula, origen1, destino1, Fecha1, Hora1, cantidad, total1, 
                                   tipoTarjeta, codigoCliente, idUsuario, Clase1, Precio1, codigoEstado, estado);
                       VDA.comprar(numero, codigo, marca, Fven, 
                                   Aerolinea2, Cedula, origen2, destino2, Fecha2, Hora2, cantidad, total2, 
                                   tipoTarjeta, codigoCliente, idUsuario, Clase2, Precio2, codigoEstado, estado);
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
