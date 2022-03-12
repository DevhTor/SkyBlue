/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyblue.Controller;

import Model.UsuarioDA;
import Model.VueloDA;
import View.Itinerario;
import View.Menu;
import View.Tarjeta;
import View.OpcionesPago;
import View.Reservaciones;
import View.ResultadosBusqueda;
import View.ResultadosBusqueda2;
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
public class OpcionesController implements ActionListener {
  
    OpcionesPago OP = new OpcionesPago();
    Tarjeta t = new Tarjeta();
    VueloDA VDA = new VueloDA();
    Reservaciones RV = new Reservaciones();
    ResultadosBusqueda RB = new ResultadosBusqueda();
    ResultadosBusqueda2 RRB = new ResultadosBusqueda2();
    Itinerario IT = new Itinerario();
    UsuarioDA uda = new UsuarioDA();
    ReservasController RCC = new  ReservasController(RV, VDA);
    Menu menu = new Menu();
    CerrarController CC = new CerrarController(menu, uda);
   
     OpcionesController(OpcionesPago op){
      this.OP = op;
      this.OP.TarjetaCred.addActionListener(this);
      this.OP.TarjetaDeb.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(OP.TarjetaCred == e.getSource()){
                 
         
          
          if(CC.vis==1){
              PagarReservasController RC = new PagarReservasController(t,  VDA);
          }else{ 
              JOptionPane.showMessageDialog(null, "Tarjeta");
              TarjetaController TC = new TarjetaController(t,VDA);
          } 
            
           t.setVisible(true);
           t.setTitle("Tarjeta Credito");
           VDA.LlenarMarca(t.TTramitada);
           OP.setVisible(false); 
           
           
        }else{
          
          if(CC.vis==1){
              JOptionPane.showMessageDialog(null, "Reservas");
              PagarReservasController RC = new PagarReservasController(t,  VDA);
          }else{ 
              JOptionPane.showMessageDialog(null, "Tarjeta");
              TarjetaController TC = new TarjetaController(t,VDA);
          } 
           t.setVisible(true);
           t.setTitle("Tarjeta Debito");
           VDA.LlenarMarca(t.TTramitada);
           TarjetaController TC = new TarjetaController(t,VDA);
           OP.setVisible(false); 
        }
     }
}

