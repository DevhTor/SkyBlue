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

/**
 *
 * @author Marleny Pena Tavarez
 */
public class CambiarFechaController implements ActionListener {
    
    Tarjeta T = new Tarjeta();
    VueloDA VDA  = new VueloDA();
    VueloController VC = new VueloController();
    UsuarioDA UDA = new UsuarioDA();
    Menu men = new Menu();
    ComprasRealizadas CR = new ComprasRealizadas();
    ResultadosBusqueda RB = new ResultadosBusqueda();
    ResultadosBusqueda2 RB2 = new ResultadosBusqueda2();
    
    ComprasController CC = new ComprasController();
    Itinerario IT = new Itinerario();
    CambiarFecha CF = new CambiarFecha();
   
   
    public CambiarFechaController(Itinerario IT, VueloDA VDA){
      this.IT=IT;
      this.VDA = VDA;
      this.IT.CambiarFVBTN.addActionListener(this);
      this.IT.CancelarVueloBTN.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(IT.CambiarFVBTN == e.getSource()){
             CF.setVisible(true);
             OpcionesCambioController OPC = new OpcionesCambioController(CF);
        }else if(IT.CancelarVueloBTN == e.getSource()){
             OpcionesPago op = new OpcionesPago();
             op.setVisible(true);
             OpcionesController OP = new OpcionesController(op);
        }
    } 
}
