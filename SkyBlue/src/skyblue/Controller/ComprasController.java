
package skyblue.Controller;
import Model.VueloDA;
import Model.UsuarioDA;
import View.ResultadosBusqueda;
import View.ResultadosBusqueda2;
import View.OpcionesPago;
import View.Menu;
import View.BuscarVuelo;
import skyblue.Controller.VueloController;
import View.CambiarFecha;
import View.Itinerario;
import View.Reservaciones;
import View.Tarjeta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ComprasController extends MouseAdapter implements ActionListener, MouseListener{
    
    public static String Aerolinea;
    public static String origen;
    public static String destino;
    public static java.util.Date Fecha;
    public static java.util.Date Hora;
    public static int Clase;
    public static int Precio;
    public static int total;
    
    public static String Aerolinea1;
    public static String origen1;
    public static String destino1;
    public static java.util.Date Fecha1;
    public static java.util.Date Hora1;
    public static int Clase1;
    public static int Precio1;
    public static int total1;
    
    public static String Aerolinea2;
    public static String origen2;
    public static String destino2;
    public static java.util.Date Fecha2;
    public static java.util.Date Hora2;
    public static int Clase2;
    public static int Precio2;
    public static int total2;
    
    public static String AerolineaIT;
    public static String origenIT;
    public static String destinoIT;
    public static java.util.Date FechaIT;
    public static java.util.Date HoraIT;
    public static int ClaseIT;
    public static String estadoIT;
    public static int AsientoIT;
    public static int PrecioIT;
    public static int totalIT;
 /*   
    public static String AerolineaRV;
    public static String origenRV;
    public static String destinoRV;
    public static java.util.Date FechaRV;
    public static java.util.Date HoraRV;
    public static int ClaseRV;
    public static String estadoRV;
    public static int AsientoRV;
    public static int PrecioRV;
    public static int totalRV;*/
    
    public static boolean clic;
    ResultadosBusqueda RB = new ResultadosBusqueda();
    VueloController VC = new VueloController();
    VueloDA VDA = new VueloDA();
    ResultadosBusqueda2 RRB = new ResultadosBusqueda2();
    Itinerario IT = new Itinerario();
   // Reservaciones RV = new Reservaciones();
    UsuarioDA uda = new UsuarioDA();
    Menu menu = new Menu();
    BuscarVuelo BV = new BuscarVuelo();
    
     ComprasController(ResultadosBusqueda RB, ResultadosBusqueda2 RRB, VueloDA VDA, Itinerario IT, Reservaciones RV) {
        this.RB = RB;
        this.RRB = RRB;
        this.VDA = VDA;
        this.IT = IT;
       // this.RV = RV;
        this.RB.ComprarBTN.addActionListener(this);
        this.RB.ResTable.addMouseListener(this);
        this.RRB.ResTable.addMouseListener(this);
        this.RRB.ResTable1.addMouseListener(this);
        this.IT.ItinerarioTable.addMouseListener(this);
        this.IT.CancelarVueloBTN.addActionListener(this);
        this.IT.CambiarFVBTN.addActionListener(this);
       this.RRB.ComprarBTN.addActionListener(this);
       this.RB.CancelarBTN.addActionListener(this);
       this.RRB.CancelarBTN.addActionListener(this);
       this.RRB.ReservarBTN.addActionListener(this);
      /* this.RV.ReservasTB.addMouseListener(this);
       this.RV.ComprarRV.addActionListener(this);*/
       
    }
     
    ComprasController(){}
    @Override
      public void mouseClicked(MouseEvent e) {
        
          if(RB.ResTable.isRowSelected(RB.ResTable.getSelectedRow()) == true){
                int precio = Integer.parseInt(RB.ResTable.getValueAt(RB.ResTable.getSelectedRow(),6).toString());
                int cantidad = VC.cant;
                int total = precio*cantidad;
                RB.TotalLBL.setText(String.valueOf(total));
           }else if(RRB.ResTable.isRowSelected(RRB.ResTable.getSelectedRow()) == true &&
                RRB.ResTable1.isRowSelected(RRB.ResTable1.getSelectedRow()) == true){    
                int cantidad = VC.cant; 
                int precio = Integer.parseInt(RRB.ResTable.getValueAt(RRB.ResTable.getSelectedRow(),6).toString());
                int precio1 = Integer.parseInt(RRB.ResTable1.getValueAt(RRB.ResTable1.getSelectedRow(),6).toString());
                int total = precio*cantidad;
                int total1 = precio1*cantidad;  
                int totalC = total+total1;
                RRB.TotalLBL1.setText(String.valueOf(total));
                RRB.TotalLBL.setText(String.valueOf(total1));
                RRB.TotalLBL2.setText(String.valueOf(totalC));
                
          }else if(RRB.ResTable1.isRowSelected(RRB.ResTable1.getSelectedRow()) == true){
                int precio1 = Integer.parseInt(RRB.ResTable1.getValueAt(RRB.ResTable1.getSelectedRow(),6).toString());
                int cantidad = VC.cant;
                int total1 = precio1*cantidad;
                RRB.TotalLBL.setText(String.valueOf(total1));
          }else if(RRB.ResTable.isRowSelected(RRB.ResTable.getSelectedRow()) == true){
                int precio = Integer.parseInt(RRB.ResTable.getValueAt(RRB.ResTable.getSelectedRow(),6).toString());
                int cantidad = VC.cant;
                int total = precio*cantidad;
                RRB.TotalLBL1.setText(String.valueOf(total));
          }else if(IT.ItinerarioTable.isRowSelected(IT.ItinerarioTable.getSelectedRow()) == true){
                int precio1 = Integer.parseInt(IT.ItinerarioTable.getValueAt(IT.ItinerarioTable.getSelectedRow(),9).toString());
                IT.TotalLBL.setText(String.valueOf(precio1));
          }else{}    
     
    }
    
     public void actionPerformed(ActionEvent e){
          
         if(RB.ComprarBTN == e.getSource() || RRB.ComprarBTN == e.getSource() 
            || IT.CancelarVueloBTN == e.getSource()){
             
             if(!RB.TotalLBL.getText().equals("") || !RRB.TotalLBL2.getText().equals("") 
                || !IT.TotalLBL.getText().equals("")){
                  clic = false;
                  OpcionesPago op = new OpcionesPago();
                  op.setVisible(true);
                  OpcionesController oc = new OpcionesController(op);
                  
             }else{ 
                JOptionPane.showMessageDialog(null, "No se ha elejido un vuelo");
             }
            
         /*}else if(RV.ComprarRV == e.getSource()){
             if(!RV.TotalLBL.getText().equals("")){
                OpcionesPago op = new OpcionesPago();
                op.setVisible(true);
                OpcionesController oc = new OpcionesController(op);
             }else{
                JOptionPane.showMessageDialog(null, "No se ha elejido un vuelo");
             }*/
         }else if(IT.CambiarFVBTN == e.getSource()){
           
             if(!IT.TotalLBL.getText().equals("")){
                  clic = true;
                  CambiarFecha CF = new CambiarFecha();
                  String AerolineaIT = IT.ItinerarioTable.getValueAt(IT.ItinerarioTable.getSelectedRow(), 0).toString();
                  String origenIT = IT.ItinerarioTable.getValueAt(IT.ItinerarioTable.getSelectedRow(), 1).toString();
                  String destinoIT = IT.ItinerarioTable.getValueAt(IT.ItinerarioTable.getSelectedRow(), 2).toString();
                  VDA.buscarFechas(CF.model, CF.FechasTable, AerolineaIT, origenIT, destinoIT);
                  CF.setVisible(true);
                  OpcionesCambioController OCC = new OpcionesCambioController(CF);
                 
             }else{ 
                JOptionPane.showMessageDialog(null, "No se ha elejido un vuelo");
             }
         }else if(RB.ReservarBTN == e.getSource()){
              try {
                 int codigoEstado = 4;
                 String nUsuario = menu.idUsuarioLBL.getText(); 
                 int codigoCliente = uda.getCodigoCliente(menu.idUsuarioLBL.getText());
                 int Precio = Integer.parseInt(RB.ResTable.getValueAt(RB.ResTable.getSelectedRow(), 6).toString());
                 String Aerolinea =RB.ResTable.getValueAt(RB.ResTable.getSelectedRow(), 0).toString();
                 String Origen = RB.ResTable.getValueAt(RB.ResTable.getSelectedRow(), 1).toString();
                 String Destino = RB.ResTable.getValueAt(RB.ResTable.getSelectedRow(), 2).toString(); 
                 Date fecha; 
                 fecha = uda.getStringToDate(RB.ResTable.getValueAt(RB.ResTable.getSelectedRow(), 3).toString());
                 Date Hora = uda.getStringToTime(RB.ResTable.getValueAt(RB.ResTable.getSelectedRow(), 4).toString());
                 int Clase = VDA.getClase(Aerolinea, Origen, Destino, fecha, Hora);
                 int Gate = VDA.getGate(Aerolinea, Origen, Destino, fecha, Hora);
                 int Asiento = VDA.getAsiento(Aerolinea, Origen, Destino, fecha, Hora);
                
                 if(VDA.existeVuelo(Aerolinea, Destino, Origen, fecha, Hora) == true){
                    VDA.guardarBoleto(codigoCliente, nUsuario, Asiento, Clase, Gate, Precio, codigoEstado, Aerolinea, Origen, Destino, fecha, Hora);
                 }else{
                    VDA.guardarCopiaVuelo(Aerolinea, Origen, Destino, fecha, Hora);
                    VDA.guardarBoleto(codigoCliente, nUsuario, Asiento, Clase, Gate, Precio, codigoEstado, Aerolinea, Origen, Destino, fecha, Hora);
                 }
             } catch (ParseException ex) {
                 Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (SQLException ex) {
                 Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
             }
            
         }else if(RB.CancelarBTN == e.getSource()){
            
            VueloController VC = new VueloController(BV, VDA);
            VDA.LlenarPais(BV.OrigenCB);
            VDA.LlenarPais(BV.DestinoCB);
            BV.FechaSalida.setMinSelectableDate(uda.getSysTime());
            BV.FechaRegreso.setMinSelectableDate(uda.getSysTime());
            if(RB.isVisible() == true){
              VDA.cambiar(RB, BV);
            }
    
          }else if(RRB.CancelarBTN == e.getSource()){
            
            VueloController VC = new VueloController(BV, VDA);
            VDA.LlenarPais(BV.OrigenCB);
            VDA.LlenarPais(BV.DestinoCB);
            BV.FechaSalida.setMinSelectableDate(uda.getSysTime());
            BV.FechaRegreso.setMinSelectableDate(uda.getSysTime());
            if(RRB.isVisible() == true){
              VDA.cambiar(RRB, BV);
            }
    
         }else{
              try {
                 int codigoEstado = 4;
                 String nUsuario = menu.idUsuarioLBL.getText(); 
                
                 int codigoCliente = uda.getCodigoCliente(nUsuario);
                 int Precio = Integer.parseInt(RRB.ResTable.getValueAt(RRB.ResTable.getSelectedRow(), 6).toString());
                 String Aerolinea =RRB.ResTable.getValueAt(RRB.ResTable.getSelectedRow(), 0).toString();
                 String Origen = RRB.ResTable.getValueAt(RRB.ResTable.getSelectedRow(), 1).toString();
                 String Destino = RRB.ResTable.getValueAt(RRB.ResTable.getSelectedRow(), 2).toString(); 
                 Date fecha = uda.getStringToDate(RRB.ResTable.getValueAt(RRB.ResTable.getSelectedRow(), 3).toString()); 
                 Date Hora = uda.getStringToTime(RRB.ResTable.getValueAt(RRB.ResTable.getSelectedRow(), 4).toString());
                 int Clase = VDA.getClase(Aerolinea, Origen, Destino, fecha, Hora);
                 int Gate = VDA.getGate(Aerolinea, Origen, Destino, fecha, Hora);
                 int Asiento = VDA.getAsiento(Aerolinea, Origen, Destino, fecha, Hora);
                 
                 int Precio1 = Integer.parseInt(RRB.ResTable1.getValueAt(RRB.ResTable1.getSelectedRow(), 6).toString());
                 String Aerolinea1 =RRB.ResTable1.getValueAt(RRB.ResTable1.getSelectedRow(), 0).toString();
                 String Origen1 = RRB.ResTable1.getValueAt(RRB.ResTable1.getSelectedRow(), 1).toString();
                 String Destino1 = RRB.ResTable1.getValueAt(RRB.ResTable1.getSelectedRow(), 2).toString(); 
                 Date fecha1 = uda.getStringToDate(RRB.ResTable1.getValueAt(RRB.ResTable1.getSelectedRow(), 3).toString()); 
                 Date Hora1 = uda.getStringToTime(RRB.ResTable1.getValueAt(RRB.ResTable1.getSelectedRow(), 4).toString());
                 int Clase1 = VDA.getClase(Aerolinea, Origen, Destino, fecha, Hora);
                 int Gate1 = VDA.getGate(Aerolinea, Origen, Destino, fecha, Hora);
                 int Asiento1 = VDA.getAsiento(Aerolinea, Origen, Destino, fecha, Hora);
                
                 if(VDA.existeVuelo(Aerolinea, Destino, Origen, fecha, Hora) == true && VDA.existeVuelo(Aerolinea1, Destino1, Origen1, fecha1, Hora1) == true){
                    VDA.guardarBoleto(codigoCliente, nUsuario, Asiento, Clase, Gate, Precio, codigoEstado, Aerolinea, Origen, Destino, fecha, Hora);
                    VDA.guardarBoleto(codigoCliente, nUsuario, Asiento1, Clase1, Gate1, Precio1, codigoEstado, Aerolinea1, Origen1, Destino1, fecha1, Hora1);       
                 }else if(VDA.existeVuelo(Aerolinea, Destino, Origen, fecha, Hora) == true && VDA.existeVuelo(Aerolinea1, Destino1, Origen1, fecha1, Hora1) == false){
                    VDA.guardarBoleto(codigoCliente, nUsuario, Asiento, Clase, Gate, Precio, codigoEstado, Aerolinea, Origen, Destino, fecha, Hora);
                    VDA.guardarCopiaVuelo(Aerolinea1, Origen1, Destino1, fecha1, Hora1);
                    VDA.guardarBoleto(codigoCliente, nUsuario, Asiento1, Clase1, Gate1, Precio1, codigoEstado, Aerolinea1, Origen1, Destino1, fecha1, Hora1);                   
                 }else if(VDA.existeVuelo(Aerolinea, Destino, Origen, fecha, Hora) == false && VDA.existeVuelo(Aerolinea1, Destino1, Origen1, fecha1, Hora1) == true){
                   VDA.guardarBoleto(codigoCliente, nUsuario, Asiento1, Clase1, Gate1, Precio1, codigoEstado, Aerolinea1, Origen1, Destino1, fecha1, Hora1);                    
                   VDA.guardarCopiaVuelo(Aerolinea, Origen, Destino, fecha, Hora);
                   VDA.guardarBoleto(codigoCliente, nUsuario, Asiento, Clase, Gate, Precio, codigoEstado, Aerolinea, Origen, Destino, fecha, Hora);
                 }else{
                    VDA.guardarCopiaVuelo(Aerolinea, Origen, Destino, fecha, Hora);
                    VDA.guardarCopiaVuelo(Aerolinea1, Origen1, Destino1, fecha1, Hora1);
                    VDA.guardarBoleto(codigoCliente, nUsuario, Asiento1, Clase1, Gate1, Precio1, codigoEstado, Aerolinea1, Origen1, Destino1, fecha1, Hora1);                   
                    VDA.guardarBoleto(codigoCliente, nUsuario, Asiento, Clase, Gate, Precio, codigoEstado, Aerolinea, Origen, Destino, fecha, Hora);
                 }
             } catch (ParseException ex) {
                 Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (SQLException ex) {
                 Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
             }
         
         }
          try { 
                     AsientoIT = Integer.parseInt(IT.ItinerarioTable.getValueAt(IT.ItinerarioTable.getSelectedRow(), 6).toString());
                     PrecioIT = Integer.parseInt(IT.ItinerarioTable.getValueAt(IT.ItinerarioTable.getSelectedRow(), 9).toString());
                     AerolineaIT = IT.ItinerarioTable.getValueAt(IT.ItinerarioTable.getSelectedRow(), 0).toString();
                     origenIT = IT.ItinerarioTable.getValueAt(IT.ItinerarioTable.getSelectedRow(), 1).toString();
                     destinoIT = IT.ItinerarioTable.getValueAt(IT.ItinerarioTable.getSelectedRow(), 2).toString();
                     FechaIT = uda.getStringToDate(IT.ItinerarioTable.getValueAt(IT.ItinerarioTable.getSelectedRow(), 3).toString());
                     HoraIT = uda.getStringToTime(IT.ItinerarioTable.getValueAt(IT.ItinerarioTable.getSelectedRow(), 4).toString());
                     ClaseIT = VDA.getClase(AerolineaIT, origenIT, destinoIT, FechaIT, HoraIT);
                     estadoIT =  IT.ItinerarioTable.getValueAt(IT.ItinerarioTable.getSelectedRow(), 8).toString();
                     totalIT = Integer.parseInt(IT.TotalLBL.getText());
                    
             /*        estadoRV =  RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 7).toString();
                     AsientoRV = Integer.parseInt(RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 6).toString());
                     PrecioRV = Integer.parseInt(RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 8).toString());
                     AerolineaRV = RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 0).toString();
                     origenRV = RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 1).toString();
                     destinoRV = RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 2).toString();
                     FechaRV = uda.getStringToDate(RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 3).toString());
                     HoraRV = uda.getStringToTime(RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 4).toString());
                     ClaseRV = VDA.getClase(AerolineaRV, origenRV, destinoRV, FechaRV, HoraRV);
                     totalRV = Integer.parseInt(RV.TotalLBL.getText()); 
                 */ 
                     Aerolinea = RB.ResTable.getValueAt(RB.ResTable.getSelectedRow(), 0).toString();
                     origen = RB.ResTable.getValueAt(RB.ResTable.getSelectedRow(), 1).toString();
                     destino = RB.ResTable.getValueAt(RB.ResTable.getSelectedRow(), 2).toString();
                     Fecha = uda.getStringToDate(RB.ResTable.getValueAt(RB.ResTable.getSelectedRow(), 3).toString());
                     Hora = uda.getStringToTime(RB.ResTable.getValueAt(RB.ResTable.getSelectedRow(), 4).toString());
                     Clase = VDA.getClase(Aerolinea, origen, destino, Fecha, Hora);
                     Precio = Integer.parseInt(RB.ResTable.getValueAt(RB.ResTable.getSelectedRow(), 6).toString());
                     total = Integer.parseInt(RB.TotalLBL.getText());
                     
                     Aerolinea1 = RRB.ResTable.getValueAt(RRB.ResTable.getSelectedRow(), 0).toString();
                     origen1 = RRB.ResTable.getValueAt(RRB.ResTable.getSelectedRow(), 1).toString();
                     destino1 = RRB.ResTable.getValueAt(RRB.ResTable.getSelectedRow(), 2).toString();
                     Fecha1 = uda.getStringToDate(RRB.ResTable.getValueAt(RRB.ResTable.getSelectedRow(), 3).toString());
                     Hora1 = uda.getStringToTime(RRB.ResTable.getValueAt(RRB.ResTable.getSelectedRow(), 4).toString());
                     Clase1 = VDA.getClase(Aerolinea1, origen1, destino1, Fecha1, Hora1);
                     Precio1 = Integer.parseInt(RRB.ResTable.getValueAt(RRB.ResTable.getSelectedRow(), 6).toString());
                     total1 = Integer.parseInt(RRB.TotalLBL1.getText());
                     
                     Aerolinea2 = RRB.ResTable1.getValueAt(RRB.ResTable1.getSelectedRow(), 0).toString();
                     origen2 = RRB.ResTable1.getValueAt(RRB.ResTable1.getSelectedRow(), 1).toString();
                     destino2 = RRB.ResTable1.getValueAt(RRB.ResTable1.getSelectedRow(), 2).toString();
                     Fecha2 = uda.getStringToDate(RRB.ResTable1.getValueAt(RRB.ResTable1.getSelectedRow(), 3).toString());
                     Hora2 = uda.getStringToTime(RRB.ResTable1.getValueAt(RRB.ResTable1.getSelectedRow(), 4).toString());
                     Clase2 = VDA.getClase(Aerolinea2, origen2, destino2, Fecha2, Hora2);
                     Precio2 = Integer.parseInt(RRB.ResTable1.getValueAt(RRB.ResTable1.getSelectedRow(), 6).toString());
                     total2 = Integer.parseInt(RRB.TotalLBL.getText());
          
                 } catch (ParseException | SQLException ex) {
                     Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
                 } 
         
    }


}
