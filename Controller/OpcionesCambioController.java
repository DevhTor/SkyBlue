/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.UsuarioDA;
import Model.VueloDA;
import View.CambiarFecha;
import View.ComprasRealizadas;
import View.Itinerario;
import View.Menu;
import View.OpcionesPago;
import View.ResultadosBusqueda;
import View.ResultadosBusqueda2;
import View.Tarjeta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Marleny Pena Tavarez
 */
public class OpcionesCambioController extends MouseAdapter  implements ActionListener {
    
   
    ComprasController CC = new ComprasController();
    Itinerario IT = new Itinerario();
    CambiarFecha CF = new CambiarFecha();
    public static java.util.Date fecha;
    public static java.util.Date hora;
    UsuarioDA UDA = new UsuarioDA();
    
    public OpcionesCambioController(CambiarFecha CF){
      this.CF=CF;
      this.CF.Aceptar.addActionListener(this);
      this.CF.Cancelar.addActionListener(this);
      this.CF.FechasTable.addMouseListener(this);
      
    }
    
   @Override
      public void mouseClicked(MouseEvent e) {
        
          if(CF.FechasTable.isRowSelected(CF.FechasTable.getSelectedRow()) == true){
            
            try {
                fecha = UDA.getStringToDate(CF.FechasTable.getValueAt(CF.FechasTable.getSelectedRow(), 0).toString());
                hora = UDA.getStringToTime(CF.FechasTable.getValueAt(CF.FechasTable.getSelectedRow(), 1).toString());
                
            } catch (ParseException ex) {
               Logger.getLogger(OpcionesCambioController.class.getName()).log(Level.SEVERE, null, ex);
            }   
          
          }else{
            JOptionPane.showMessageDialog(null, "No se ha seleccionado una fecha");
          }
     }
    public void actionPerformed(ActionEvent e){
        
        if(CF.Aceptar == e.getSource()){
              OpcionesPago op = new OpcionesPago();
              op.setVisible(true);
              OpcionesController oc = new OpcionesController(op);
        }else if(CF.Cancelar == e.getSource()){
              CF.setVisible(false);
        }
    
    }
    
}
