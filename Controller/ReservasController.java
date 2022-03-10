/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.UsuarioDA;
import Model.VueloDA;
import View.OpcionesPago;
import View.Reservaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Marleny Pena Tavarez
 */
public class ReservasController extends MouseAdapter implements ActionListener{
    public static String AerolineaRV;
    public static String origenRV;
    public static String destinoRV;
    public static java.util.Date FechaRV;
    public static java.util.Date HoraRV;
    public static int ClaseRV;
    public static String estadoRV;
    public static int AsientoRV;
    public static int PrecioRV;
    public static int totalRV;
   
     Reservaciones RV = new Reservaciones();
     VueloDA VDA = new VueloDA();
     UsuarioDA uda = new UsuarioDA();
     public  ReservasController(Reservaciones RV, VueloDA VDA){
          this.RV = RV;
          this.RV.ReservasTB.addMouseListener(this);
          this.RV.ComprarRV.addActionListener(this);
     }
     
    
    ReservasController(){}
    @Override
      public void mouseClicked(MouseEvent e) {
         if(RV.ReservasTB.isRowSelected(RV.ReservasTB.getSelectedRow()) == true){
               int precio = Integer.parseInt(RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(),8).toString());
               RV.TotalLBL.setText(String.valueOf(precio));    
          }
    }
      
      public void actionPerformed(ActionEvent e){
       
          if(RV.ComprarRV == e.getSource()){
             if(!RV.TotalLBL.getText().equals("")){
                OpcionesPago op = new OpcionesPago();
                op.setVisible(true);
                OpcionesController oc = new OpcionesController(op);
             }else{
                JOptionPane.showMessageDialog(null, "No se ha elejido un vuelo");
             }
          }
          
        try {
         
            estadoRV =  RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 7).toString();
            AsientoRV = Integer.parseInt(RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 6).toString());
            PrecioRV = Integer.parseInt(RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 8).toString());
            AerolineaRV = RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 0).toString();
            origenRV = RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 1).toString();
            destinoRV = RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 2).toString();
            FechaRV = uda.getStringToDate(RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 3).toString());
            HoraRV = uda.getStringToTime(RV.ReservasTB.getValueAt(RV.ReservasTB.getSelectedRow(), 4).toString());
            ClaseRV = VDA.getClase(AerolineaRV, origenRV, destinoRV, FechaRV, HoraRV);
            totalRV = Integer.parseInt(RV.TotalLBL.getText());
            
       
        } catch (ParseException ex) {
            Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, ex); 
        } catch (SQLException ex) {
            Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
          
                
      }
}

