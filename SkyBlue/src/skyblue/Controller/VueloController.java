
package skyblue.Controller;

import java.awt.event.ActionListener;
import View.BuscarVuelo;
import View.ResultadosBusqueda;
import skyblue.View.Menu;
import skyblue.Model.VueloDA;
import View.ResultadosBusqueda2;
import skyblue.Controller.ComprasController;
import View.Itinerario;
import View.Reservaciones;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.JOptionPane;

public class VueloController implements ActionListener{
    
    public static int cant;
    BuscarVuelo BV = new BuscarVuelo();
    Menu menu = new Menu(); 
    ResultadosBusqueda RB = new ResultadosBusqueda();
    VueloDA VDA = new VueloDA();
    ResultadosBusqueda2 RRB = new ResultadosBusqueda2();
    Itinerario IT = new Itinerario();
    Reservaciones RV = new Reservaciones ();
    
    VueloController(BuscarVuelo BV, VueloDA VDA) {
        this.BV = BV;
        this.VDA = VDA;
        this.BV.BuscarBTN.addActionListener(this);
    }
    VueloController(){}
    
    public void actionPerformed(ActionEvent e){
        int Origen, destino, cantidad;
        Date salida, entrada;
        
        Origen = BV.OrigenCB.getSelectedIndex()+1;
        destino = BV.DestinoCB.getSelectedIndex()+1;
        cantidad = BV.CantidadCB.getSelectedIndex()+1;
        cant = cantidad;
        salida = BV.FechaSalida.getDate();
        entrada = BV.FechaRegreso.getDate();
        boolean ida =  BV.idaRB.isSelected();
        boolean vuelta = BV.vueltaRB.isSelected();
         
        if(BV.OrigenCB.getSelectedIndex()== BV.DestinoCB.getSelectedIndex()){
             JOptionPane.showMessageDialog(null, "El pais de origen y el pais de destino son iguales");
           }
         
         if(salida == null && ida ==true){
            JOptionPane.showMessageDialog(null, "Debe elegir una fecha de salida");
         }else if(salida == null || entrada == null && vuelta == true){
            JOptionPane.showMessageDialog(null, "Debe elegir una fecha de salida y otra para regresar");
         }
         
        if(ida == true){
          VDA.buscarVuelo(RB.model, RB.ResTable, Origen, destino, salida, entrada, cantidad);
          ComprasController COC = new ComprasController(RB, RRB, VDA, IT, RV);
          VDA.cambiar(BV, RB);
        }else if(vuelta == true){
          VDA.buscarVuelo(RRB.model, RRB.ResTable, Origen, destino, salida, entrada, cantidad);
          VDA.buscarVuelo(RRB.model, RRB.ResTable1, destino, Origen, entrada, salida, cantidad);
          ComprasController COC = new ComprasController(RB, RRB, VDA, IT, RV);
          VDA.cambiar(BV, RRB);
       }else if(ida == true && vuelta == true){
          JOptionPane.showMessageDialog(null, "No puede elegir mas de un tipo de vuelo");
       }else{
          JOptionPane.showMessageDialog(null, "Debe elegir el tipo de vuelo");
       }
       
    }
    
}
