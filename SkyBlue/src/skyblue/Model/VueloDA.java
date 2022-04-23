 
package skyblue.Model;

import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import skyblue.Model.UsuarioDA;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class VueloDA {
    Conexion con = new Conexion();
    Connection conn = con.conexion();
    Connection conA = con.conexionA();
    Connection conB = con.conexionB();
    UsuarioDA uda = new UsuarioDA();
   
    //Verificado
    public void LlenarPais(JComboBox JB){
      JB.removeAll();
        try{
            Statement st = conA.createStatement();
            ResultSet res;
            String query = "SELECT nombre FROM pais";
            res = st.executeQuery(query);
      
            while(res.next()){
                JB.addItem(res.getString("nombre"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
        
    }
    public void LlenarMarca(JComboBox JB){
    JB.removeAll();
        try{
            Statement st = conB.createStatement();
            ResultSet res;
            String query = "SELECT Nombre FROM tipo_tarjeta";
            res = st.executeQuery(query);
      
            while(res.next()){
                JB.addItem(res.getString("Nombre"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
    
    }
    public void buscarVuelo(DefaultTableModel model, JTable table, int origen, int destino, Date Fsalida, Date Fregreso, int cantidad){
      
       String sql = "Select (SELECT nombre FROM aerolinea WHERE codigo_Aerolinea = " 
               +"(SELECT codigo_Aerolinea FROM vuelos WHERE Origen ="+origen 
               +" AND destino = "+destino+" AND Fecha_Salida = '"+uda.SqlDate(Fsalida)+"')), "
               + "(SELECT nombre FROM pais WHERE codigo_pais = "+origen+"), "
               + "(SELECT nombre FROM pais WHERE codigo_pais = "+destino+"), "
               + "Fecha_salida, Hora_salida, "
               + "(SELECT Siglas FROM clase WHERE codigo_clase = vuelos.Clase), "
               + " Precio from vuelos WHERE Destino = "+destino
               + " and Origen = "+origen+" and Fecha_salida = '"+uda.SqlDate(Fsalida)+"'"
               + " and Asientos_Disponibles > "+cantidad+" or Asientos_Disponibles = "+cantidad;
       
       try{
       PreparedStatement ps = conA.prepareStatement(sql);   
       ResultSet res = ps.executeQuery();
       ResultSetMetaData rsmd = res.getMetaData();
       ArrayList<Object[]> datos = new ArrayList<>();
       
       while(res.next()){
           Object[] col = new Object[rsmd.getColumnCount()];
           for(int i = 0; i<col.length; i++){
               col[i] = res.getObject(i+1);
           }
           datos.add(col);
       }
        
        model = (DefaultTableModel)table.getModel();
        while(model.getRowCount()>0)model.removeRow(0);
       
        for(int i = 0; i<datos.size(); i++){
               model.addRow(datos.get(i));
           }
       }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, ex.getMessage());
       }
   }
    public void buscarFechas(DefaultTableModel model, JTable table, String Aerolinea, String Origen, String Destino){
    
        String sql ="SELECT Fecha_salida, Hora_salida FROM vuelos "
                +" WHERE codigo_Aerolinea = (SELECT codigo_Aerolinea FROM aerolinea WHERE nombre = '"+Aerolinea+"') AND "
                +"Origen = (SELECT codigo_pais FROM pais WHERE nombre = '"+Origen+"') AND "
                +"Destino = (SELECT codigo_pais FROM pais WHERE nombre = '"+Destino+"') ";
        System.out.println(Aerolinea+" "+Origen+" "+Destino);
      try{
       PreparedStatement ps = conA.prepareStatement(sql);   
       ResultSet res = ps.executeQuery(sql);
       ResultSetMetaData rsmd = res.getMetaData();
       ArrayList<Object[]> datos = new ArrayList<>();
       
       while(res.next()){
           Object[] col = new Object[rsmd.getColumnCount()];
           for(int i = 0; i<col.length; i++){
               col[i] = res.getObject(i+1);
           }
           datos.add(col);
       }
        
        model = (DefaultTableModel)table.getModel();
        while(model.getRowCount()>0)model.removeRow(0);
       
        for(int i = 0; i<datos.size(); i++){
               model.addRow(datos.get(i));
           }
       }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, ex.getMessage());
       }
    }
    
    public boolean existeVuelo(String Aerolinea, String Destino, String Origen, java.util.Date Fecha, java.util.Date Hora){
       boolean existe = false;
       String SQL = "Select * from Vuelo where Origen = (Select codigo_Pais from Pais where nombre = '"+Origen+"') "
               + "and Destino = (Select codigo_Pais from Pais where nombre = '"+Destino+"') "
               + "and Fecha = '"+uda.SqlDate(Fecha)+"' and Hora = '"+uda.SqlTime(Hora)+"' "
               + "and codigo_Aerolinea = (Select codigo_Aerolinea from Aerolinea where nombre_Aerolinea = '"+Aerolinea+"')";
       PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQL);
            ResultSet res = ps.executeQuery();
            if(res.next()==true){
              existe=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return existe;
    }
    public int actualizarMTC(String numeroTarjeta, int totalAPagar){
      int resp = 0;
        try {
            String sql ="UPDATE tarjeta_credito SET Monto = Monto + "+totalAPagar+" WHERE codigo_tarjeta = "+numeroTarjeta;
            
            PreparedStatement ps = conB.prepareStatement(sql);
            
            int res = ps.executeUpdate();
            if(res > 0){
                resp = 1;
            }else{
                JOptionPane.showMessageDialog(null, "Error en la transaccion");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VueloDA.class.getName()).log(Level.SEVERE, null, ex);
        }
     return resp;
    }
    public int actualizarSTD(String numeroTarjeta, int totalAPagar){
       int resp = 0;
        try {
            String sql ="UPDATE tarjeta_debito SET Saldo = Saldo - "+totalAPagar+" WHERE Codigo_td = "+numeroTarjeta;
            
            PreparedStatement ps = conB.prepareStatement(sql);
            
            int res = ps.executeUpdate();
            if(res > 0){
                resp = 1;
            }else{
                JOptionPane.showMessageDialog(null, "Error en la transaccion");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VueloDA.class.getName()).log(Level.SEVERE, null, ex);
        }
      return resp;
    }
    public int actualizarSCB(String numeroTarjeta, int totalAPagar){
     
        int resp =0;
        try {
            String sql ="UPDATE cuenta_bancaria SET Saldo = Saldo - "+totalAPagar+" WHERE Codigo_cuenta = "
                        +"(SELECT Codigo_cuenta FROM tarjeta_debito WHERE Codigo_td = '"+numeroTarjeta+"')";
            
            PreparedStatement ps = conB.prepareStatement(sql);
            
            int res = ps.executeUpdate();
            if(res > 0){
                resp = 1;
            }else{
                JOptionPane.showMessageDialog(null,"Error en la transaccion");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VueloDA.class.getName()).log(Level.SEVERE, null, ex);
        }
      return resp;
    }
    public int actualizarBoleto(int codigoBoleto, String idUsuario, int codigoVuelo){
      int actualizado = 0;
    
      try {
            String sql ="UPDATE Boleto SET numero_Vuelo = "+codigoVuelo+" WHERE "+
                        "numero_Boleto = "+codigoBoleto
                        +" and codigo_Cliente = (SELECT codigo_Cliente FROM cuentUsuario WHERE ID = '"+idUsuario+"')";
                        
            System.out.println(codigoBoleto+" | "+codigoVuelo+" | "+idUsuario);
            PreparedStatement ps = conn.prepareStatement(sql);
            
            int res = ps.executeUpdate();
            if(res > 0){
                actualizado = 1;
                JOptionPane.showMessageDialog(null,"Boleto Actualizado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VueloDA.class.getName()).log(Level.SEVERE, null, ex);
        }
      return actualizado;
    }
    public void actualizarCantidadVuelos(int cantidad, String Aerolinea, String Origen, String Destino, Date fecha, Date Hora){
      try {
            String sql ="UPDATE vuelos SET Asientos_Disponibles = Asientos_Disponibles - "+cantidad
                +" WHERE codigo_Aerolinea = (SELECT codigo_Aerolinea FROM aerolinea WHERE nombre = '"+Aerolinea+"') AND "
                +"Origen = (SELECT codigo_pais FROM pais WHERE nombre = '"+Origen+"') AND "
                +"Destino = (SELECT codigo_pais FROM pais WHERE nombre = '"+Destino+"') AND "
                +"Fecha_salida = '"+uda.SqlDate(fecha)+"' AND "
                +"Hora_salida = '"+uda.SqlTime(Hora)+"'";
            PreparedStatement ps = conA.prepareStatement(sql);
            
            int res = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(VueloDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int actualizarEstadoVuelo(int codigoBoleto, String idUsuario){
    int actualizado = 0;
      try {
            String sql ="UPDATE Boleto SET codigo_Estado = 6"
                        +" WHERE numero_Boleto = "+codigoBoleto
                        +" and codigo_Cliente = (SELECT codigo_Cliente FROM cuentUsuario WHERE ID = '"+idUsuario+"')";
                        
            System.out.println(codigoBoleto+" | "+idUsuario);
            PreparedStatement ps = conn.prepareStatement(sql);
            
            int res = ps.executeUpdate();
            if(res > 0){
                actualizado = 1;
                JOptionPane.showMessageDialog(null,"Boleto Actualizado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VueloDA.class.getName()).log(Level.SEVERE, null, ex);
        }
      return actualizado;
    }
    public int getCodigoBoleto(String Aerolinea, String Origen, String Destino, Date fecha, Date Hora, 
                                String idUsuario, int Asiento)throws SQLException{
        int codigoBoleto = 0;
        String SQL ="SELECT numero_Boleto From Boleto Where "
                  +"numero_Vuelo = (SELECT numero_Vuelo FROM Vuelo WHERE "
                  +"codigo_Aerolinea = (Select codigo_Aerolinea from Aerolinea where nombre_Aerolinea = '"+Aerolinea+"') "
                  +"AND Origen = (Select codigo_Pais from Pais where nombre = '"+Origen+"') " 
                  +"and Destino = (Select codigo_Pais from Pais where nombre = '"+Destino+"') " 
                  +"and Fecha = '"+uda.SqlDate(fecha)+"' and Hora = '"+uda.SqlTime(Hora)+"') "
             +"AND codigo_Cliente = (Select codigo_Cliente from cuentUsuario where ID = '"+idUsuario+"') "
             +"AND Codigo_CuentUsuario = (Select codigo_Cuenta From cuentUsuario Where ID = '"+idUsuario+"') "
             +"AND Asiento  = "+Asiento;
        
        System.out.println("Datos CodigoB: "+Aerolinea+" "+Origen+" "+Destino+" "+fecha+" "+Hora+" "+idUsuario+" "+Asiento);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(SQL);
            if(res.next()){
              codigoBoleto = res.getInt("numero_Boleto");
              return codigoBoleto;
            }
        
        return codigoBoleto;
    
    }
    
    public int getCodigoVuelo(String Aerolinea, String Origen, String Destino, Date fecha, Date Hora)throws SQLException{
      int codigo = 0;
      String SQL ="SELECT numero_Vuelo FROM Vuelo WHERE "
                  +"codigo_Aerolinea = (Select codigo_Aerolinea from Aerolinea where nombre_Aerolinea = '"+Aerolinea+"') "
                  +"AND Origen = (Select codigo_Pais from Pais where nombre = '"+Origen+"') " 
                  +"and Destino = (Select codigo_Pais from Pais where nombre = '"+Destino+"') " 
                  +"and Fecha = '"+uda.SqlDate(fecha)+"' and Hora = '"+uda.SqlTime(Hora)+"' ";
        
        System.out.println("Datos CodigoV: "+Aerolinea+" "+Origen+" "+Destino+" "+fecha+" "+Hora);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(SQL);
            if(res.next()){
              codigo = res.getInt("numero_Vuelo");
              return codigo;
            }
        
      return codigo;
    }
    public int getAsiento( String Aerolinea, String Origen, String Destino, Date fecha, Date Hora) throws SQLException{
       int resp =0;
            String sql ="SELECT Asientos_Disponibles FROM vuelos"
                +" WHERE codigo_Aerolinea = (SELECT codigo_Aerolinea FROM aerolinea WHERE nombre = '"+Aerolinea+"') AND "
                +"Origen = (SELECT codigo_pais FROM pais WHERE nombre = '"+Origen+"') AND "
                +"Destino = (SELECT codigo_pais FROM pais WHERE nombre = '"+Destino+"') AND "
                +"Fecha_salida = '"+uda.SqlDate(fecha)+"' AND "
                +"Hora_salida = '"+uda.SqlTime(Hora)+"'";
        
            Statement st = conA.createStatement();
            ResultSet res = st.executeQuery(sql);
            if(res.next()){
              resp = res.getInt("Asientos_Disponibles");
              return resp;
            }
      return resp;
     }
    public int getGate( String Aerolinea, String Origen, String Destino, Date fecha, Date Hora) throws SQLException{
      int resp = 0;
            String sql ="SELECT Gate FROM vuelos"
                +" WHERE codigo_Aerolinea = (SELECT codigo_Aerolinea FROM aerolinea WHERE nombre = '"+Aerolinea+"') AND "
                +"Origen = (SELECT codigo_pais FROM pais WHERE nombre = '"+Origen+"') AND "
                +"Destino = (SELECT codigo_pais FROM pais WHERE nombre = '"+Destino+"') AND "
                +"Fecha_salida = '"+uda.SqlDate(fecha)+"' AND "
                +"Hora_salida = '"+uda.SqlTime(Hora)+"'";
            
            Statement st = conA.createStatement();
            ResultSet res = st.executeQuery(sql);
            if(res.next()){
              resp = res.getInt("Gate");
              System.out.println("Esta Funcionando getGate "+resp);
              return resp;
            }
        
      return resp;
     }
    public int getClase( String Aerolinea, String Origen, String Destino, Date fecha, Date Hora) throws SQLException{
      int resp = 0;
        
            String sql ="SELECT Clase FROM vuelos"
                +" WHERE codigo_Aerolinea = (SELECT codigo_Aerolinea FROM aerolinea WHERE nombre = '"+Aerolinea+"') AND "
                +"Origen = (SELECT codigo_pais FROM pais WHERE nombre = '"+Origen+"') AND "
                +"Destino = (SELECT codigo_pais FROM pais WHERE nombre = '"+Destino+"') AND "
                +"Fecha_salida = '"+uda.SqlDate(fecha)+"' AND "
                +"Hora_salida = '"+uda.SqlTime(Hora)+"'";
          
            Statement st = conA.createStatement();
            ResultSet res = st.executeQuery(sql);
            if(res.next()){
              resp = res.getInt("Clase");
              System.out.println("Esta Funcionando getClase "+resp);
              return resp;
           }
        
      return resp;
     }
   
    public int validarTarjetaCredito(String numeroTarjeta, int clave, Date FVencimiento, String Marca, String Cedula){
        PreparedStatement ps;
       int acceso=0;
        try {
            ps = conB.prepareStatement("Select * from tarjeta_credito where codigo_tarjeta =? "
                    + "AND Titular =? AND Codigo_Seguridad = ? AND FExpiracion = ? AND Tipo_tarjeta = "
                    + "(Select codigo_Tipo from tipo_tarjeta where Nombre = '"+Marca+"')");
            ps.setString(1, numeroTarjeta);
            ps.setString(2, Cedula);
            ps.setInt(3, clave);
            ps.setDate(4, uda.SqlDate(FVencimiento));
            ResultSet res = ps.executeQuery();
            if(res.next()){
              acceso=1;
            }else{
              System.out.println("Datos: "+numeroTarjeta+" "+Cedula+" "+uda.SqlDate(FVencimiento)+" "+Marca);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }

     return acceso;
    
    } //En la BD Banco
    public int validarTarjetaDebito(String numeroTarjeta, int clave, Date FVencimiento, String Marca, String Cedula){
        PreparedStatement ps;
       int acceso=0;
        try {
            ps = conB.prepareStatement("Select * from tarjeta_debito where Codigo_td =? "
                    + "AND Titular =? AND Codigo_seguridad = ? AND FExpiracion = ? AND Tipo_Tarjeta = "
                    + "(Select codigo_Tipo from tipo_tarjeta where Nombre = '"+Marca+"')");
            ps.setString(1, numeroTarjeta);
            ps.setString(2, Cedula);
            ps.setInt(3, clave);
            ps.setDate(4, uda.SqlDate(FVencimiento));
            ResultSet res = ps.executeQuery();
            if(res.next()){
              acceso=1;
            }else{
              System.out.println("Datos Debito: "+numeroTarjeta+" "+Cedula+" "+uda.SqlDate(FVencimiento)+" "+Marca);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }

     return acceso;
    
    } //En la BD Banco
    public boolean existeTarjeta(String numeroTarjeta, int clave, Date FVencimiento, String Marca, String Cedula){
        boolean existe = false;
         String SQL = "Select * from Tarjeta where Codigo_Tarjeta =? AND FVencimiento =? AND clave =?"
                 + " AND Marca = (Select Codigo_MarcaT from MarcaTarjeta where Nombre = '"+Marca+"')"
                 + " AND Codigo_Cliente = (Select codigo_Cliente where Cedula = "+Cedula+")";
         PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQL);
            ps.setString(1, numeroTarjeta);
            ps.setDate(2, uda.SqlDate(FVencimiento));
            ps.setInt(3, clave);
            ResultSet res = ps.executeQuery();
            if(res.next()==true){
              existe=true;
              System.out.println("Esta Funcionando existeTarjeta");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return existe;
    
    } //en BD Sistema
   
    public void comprar(String numeroTarjeta, int codigo, String marca, Date FVencimiento,
                        String Aerolinea, String Cedula, String Origen, String Destino, Date fecha, Date Hora,
                        int cantidad, int totalAPagar, int tipoTarjeta, int codigoCliente, String nUsuario, int Clase, 
                        int Precio, int codigoEstado, String Estado){
     try {
   
     int Asiento = this.getAsiento(Aerolinea, Origen, Destino, fecha, Hora);
     int Gate = this.getGate(Aerolinea, Origen, Destino, fecha, Hora);
     
     if(tipoTarjeta == 1){
        if(this.validarTarjetaCredito(numeroTarjeta, codigo, FVencimiento, marca, Cedula)==1){
            if(this.actualizarMTC(numeroTarjeta, totalAPagar)==1){
               if(this.existeVuelo(Aerolinea, Destino, Origen, fecha, Hora) == false){
                 this.guardarCopiaVuelo(Aerolinea, Origen, Destino, fecha, Hora);
                 this.guardarBoleto(codigoCliente, nUsuario, Asiento, Clase, Gate, Precio, codigoEstado, Aerolinea, Origen, Destino, fecha, Hora);
                 this.actualizarCantidadVuelos(cantidad, Aerolinea, Origen, Destino, fecha, Hora);
                 int codigoBoleto = this.getCodigoBoleto(Aerolinea, Origen, Destino, fecha, Hora, nUsuario, Asiento);
                 if(codigoEstado == 4){
                    this.actualizarEstadoVuelo(codigoBoleto, nUsuario);
                 }
               }else{
                 this.guardarBoleto(codigoCliente, nUsuario, Asiento, Clase, Gate, Precio, codigoEstado, Aerolinea, Origen, Destino, fecha, Hora);
                 this.actualizarCantidadVuelos(cantidad, Aerolinea, Origen, Destino, fecha, Hora);
                 int codigoBoleto = this.getCodigoBoleto(Aerolinea, Origen, Destino, fecha, Hora, nUsuario, Asiento);
                 if(codigoEstado == 4){
                    this.actualizarEstadoVuelo(codigoBoleto, nUsuario);
                 }
               }           
            }else{
              JOptionPane.showMessageDialog(null, "Error al realzar la transaccion");
            }
        }else{
           JOptionPane.showMessageDialog(null, "Tarjeta no valida");
        }
      }else{
      
         if(this.validarTarjetaDebito(numeroTarjeta, codigo, FVencimiento, marca, Cedula)==1){
            if(this.actualizarSTD(numeroTarjeta, totalAPagar)==1 && this.actualizarSCB(numeroTarjeta, totalAPagar) == 1){
               if(this.existeVuelo(Aerolinea, Destino, Origen, fecha, Hora) == false){
                 this.guardarCopiaVuelo(Aerolinea, Origen, Destino, fecha, Hora);
                 this.guardarBoleto(codigoCliente, nUsuario, Asiento, Clase, Gate, Precio, codigoEstado, Aerolinea, Origen, Destino, fecha, Hora);
                 this.actualizarCantidadVuelos(cantidad, Aerolinea, Origen, Destino, fecha, Hora);
                 int codigoBoleto = this.getCodigoBoleto(Aerolinea, Origen, Destino, fecha, Hora, nUsuario, Asiento);
                 if(codigoEstado == 4){
                    this.actualizarEstadoVuelo(codigoBoleto, nUsuario);
                 }
               }else{
                 this.guardarBoleto(codigoCliente, nUsuario, Asiento, Clase, Gate, Precio, codigoEstado, Aerolinea, Origen, Destino, fecha, Hora);
                 this.actualizarCantidadVuelos(cantidad, Aerolinea, Origen, Destino, fecha, Hora);
                 int codigoBoleto = this.getCodigoBoleto(Aerolinea, Origen, Destino, fecha, Hora, nUsuario, Asiento);
                 if(codigoEstado == 4){
                    this.actualizarEstadoVuelo(codigoBoleto, nUsuario);
                 }
               }           
            }else{
              JOptionPane.showMessageDialog(null, "Error al realzar la transaccion");
            }
        }else{
           JOptionPane.showMessageDialog(null, "Tarjeta no valida");
        }
      }
      } catch (SQLException ex) {
            Logger.getLogger(VueloDA.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
     public void PagarReserva(String numeroTarjeta, int codigo, String marca, Date FVencimiento,
                        String Aerolinea, String Cedula, String Origen, String Destino, Date fecha, Date Hora,
                        int cantidad, int totalAPagar, int tipoTarjeta, int codigoCliente, String nUsuario, int Clase, 
                        int codigoEstado, int Asiento){
     try {
   
     
     int codigoBoleto = this.getCodigoBoleto(Aerolinea, Origen, Destino, fecha, Hora, nUsuario, Asiento);
                 
     if(tipoTarjeta == 1){
        if(this.validarTarjetaCredito(numeroTarjeta, codigo, FVencimiento, marca, Cedula)==1){
            if(this.actualizarMTC(numeroTarjeta, totalAPagar)==1){
               if(this.existeVuelo(Aerolinea, Destino, Origen, fecha, Hora) == true){
                 if(codigoEstado == 4){
                   this.actualizarEstadoVuelo(codigoBoleto, nUsuario);
                 }
               }else{
                 JOptionPane.showMessageDialog(null, "El vuelo no existe");
               }           
            }else{
              JOptionPane.showMessageDialog(null, "Error al realzar la transaccion");
            }
        }else{
           JOptionPane.showMessageDialog(null, "Tarjeta no valida");
        }
      }else{
      
         if(this.validarTarjetaDebito(numeroTarjeta, codigo, FVencimiento, marca, Cedula)==1){
            if(this.actualizarSTD(numeroTarjeta, totalAPagar)==1 && this.actualizarSCB(numeroTarjeta, totalAPagar) == 1){
               if(this.existeVuelo(Aerolinea, Destino, Origen, fecha, Hora) == false){
                 if(codigoEstado == 4){
                    this.actualizarEstadoVuelo(codigoBoleto, nUsuario);
                 }
               }else{
                 JOptionPane.showMessageDialog(null, "El vuelo no existe");
               }           
            }else{
              JOptionPane.showMessageDialog(null, "Error al realzar la transaccion");
            }
        }else{
           JOptionPane.showMessageDialog(null, "Tarjeta no valida");
        }
      }
      } catch (SQLException ex) {
            Logger.getLogger(VueloDA.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    public void guardarCopiaVuelo(String Aerolinea, String Origen, String Destino, Date fecha, Date Hora){
    PreparedStatement ps;
        
        String sqlO="SELECT codigo_Pais FROM Pais WHERE nombre = '"+Origen+"'";
        String sqlD="SELECT codigo_Pais FROM Pais WHERE nombre = '"+Destino+"'";
        String sqlA="SELECT codigo_Aerolinea FROM Aerolinea WHERE nombre_Aerolinea = '"+Aerolinea+"'";
        String sql="INSERT INTO Vuelo(codigo_Aerolinea, Fecha, Hora, Origen, Destino)"
                 + "VALUES(?, ?, ?, ?, ?)";
        
        try {
           if(existeVuelo(Aerolinea, Destino, Origen, fecha, Hora)==false){
              
                ps = conn.prepareStatement(sql);
                Statement st = conn.createStatement();
                Statement sst = conn.createStatement();
                Statement ssst = conn.createStatement();
                ResultSet rsO = st.executeQuery(sqlO);
                ResultSet rsD = sst.executeQuery(sqlD);
                ResultSet rsA = ssst.executeQuery(sqlA);
                int resO;
                int resD;
                int resA;
                if(rsO.next()){ 
                    resO = rsO.getInt("codigo_Pais"); 
                }else{ 
                    resO = 1;
                }
                
                if(rsD.next()){ 
                    resD = rsD.getInt("codigo_Pais"); 
                }else{ 
                    resD= 1;}
                
                if(rsA.next()){ 
                    resA = rsA.getInt("codigo_Aerolinea"); 
                }else{ 
                    resA = 1;}
                ps.setInt(1, resA);
                ps.setDate(2, uda.SqlDate(fecha));
                ps.setTime(3, uda.SqlTime(Hora));
                ps.setInt(4, resO);
                ps.setInt(5, resD);
                int res = ps.executeUpdate();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(VueloDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public void guardarBoleto(int codigoCliente, String nUsuario, int Asiento, int Clase, int Gate, 
                              int Precio, int codigoEstado, String Aerolinea, String Origen, String Destino, 
                              Date fecha, Date Hora){
    
        PreparedStatement ps;
        
        String sqlU="SELECT codigo_Cuenta FROM cuentUsuario WHERE ID = '"+nUsuario+"'";
        //String sqlCC="SELECT codigo_Cliente FROM cuentUsuario WHERE ID = '"+nUsuario+"'";
        String sqlV="SELECT numero_Vuelo FROM Vuelo WHERE"
                + " codigo_Aerolinea = (Select codigo_Aerolinea from Aerolinea where nombre_Aerolinea = '"+Aerolinea+"')"
                +" AND Origen = (Select codigo_Pais from Pais where nombre = '"+Origen+"') "
                + "and Destino = (Select codigo_Pais from Pais where nombre = '"+Destino+"') "
                + "and Fecha = '"+uda.SqlDate(fecha)+"' and Hora = '"+uda.SqlTime(Hora)+"' ";
      
        String sql="INSERT INTO Boleto(numero_Vuelo, codigo_Cliente, Codigo_CuentUsuario, Asiento, codigo_Clase, codigo_Gate, Precio, codigo_Estado)"
                 + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
              
                ps = conn.prepareStatement(sql);
                Statement st = conn.createStatement();
                Statement st2 = conn.createStatement();
                Statement st3 = conn.createStatement();
                Statement st4 = conn.createStatement();
                Statement st5 = conn.createStatement();
                ResultSet rsU= st.executeQuery(sqlU);
                ResultSet rsV = st3.executeQuery(sqlV);
              
                int resU;
                int resCC;
                int resV;
                int resC;
                int resG;
             
                if(rsU.next()){ 
                    resU = rsU.getInt("codigo_Cuenta"); 
                }else{ 
                    resU = 1;}
                 ps.setInt(3, resU);
                
                if(rsV.next()){ 
                    resV = rsV.getInt("numero_Vuelo"); 
                }else{ 
                    resV = 1;}
                ps.setInt(1, resV);
                ps.setInt(2, codigoCliente);
                ps.setInt(4, Asiento);
                ps.setInt(5, Clase);
                ps.setInt(6, Gate);
                ps.setInt(7, Precio);
                ps.setInt(8, codigoEstado);
                
                int res = ps.executeUpdate();
                if(res>0){
                  JOptionPane.showMessageDialog(null,"Boleto Generado");
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(VueloDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void cambiarFecha(String numeroTarjeta, int codigo, String marca, Date FVencimiento, String idUsuario,
                        String Aerolinea, String Cedula, String Origen, String Destino, Date fechaA, Date HoraA,
                        Date fechaB, Date HoraB, int tipoTarjeta, int Precio, int Asiento) throws SQLException{
     int totalAPagar = (Precio*20)/100;
     if(tipoTarjeta == 1){
        if(this.validarTarjetaCredito(numeroTarjeta, codigo, FVencimiento, marca, Cedula)==1){
            if(this.actualizarMTC(numeroTarjeta, totalAPagar)==1){
               if(this.existeVuelo(Aerolinea, Origen, Destino, fechaA, HoraA) == false){
                 this.guardarCopiaVuelo(Aerolinea, Origen, Destino, fechaB, HoraB);
                 int codigoBoleto = this.getCodigoBoleto(Aerolinea, Origen, Destino, fechaA, HoraA, idUsuario, Asiento);
                 int codigoVuelo = this.getCodigoVuelo(Aerolinea, Origen, Destino, fechaB, HoraB);
                 this.actualizarBoleto(codigoBoleto, idUsuario, codigoVuelo);
               }else{
                 int codigoBoleto = this.getCodigoBoleto(Aerolinea, Origen, Destino, fechaA, HoraA, idUsuario, Asiento);
                 int codigoVuelo = this.getCodigoVuelo(Aerolinea, Origen, Destino, fechaB, HoraB);
                 this.actualizarBoleto(codigoBoleto, idUsuario, codigoVuelo);
               }          
            }else{
              JOptionPane.showMessageDialog(null, "Error al realzar la transaccion");
            }
        }else{
           JOptionPane.showMessageDialog(null, "Tarjeta no valida");
        }
      }else{
      
         if(this.validarTarjetaDebito(numeroTarjeta, codigo, FVencimiento, marca, Cedula)==1){
            if(this.actualizarSTD(numeroTarjeta, totalAPagar)==1 && this.actualizarSCB(numeroTarjeta, totalAPagar) == 1){
               if(this.existeVuelo(Aerolinea, Origen, Destino, fechaA, HoraA) == false){
                 this.guardarCopiaVuelo(Aerolinea, Origen, Destino, fechaB, HoraB);
                 int codigoBoleto = this.getCodigoBoleto(Aerolinea, Origen, Destino, fechaA, HoraA, idUsuario, Asiento);
                 int codigoVuelo = this.getCodigoVuelo(Aerolinea, Origen, Destino, fechaB, HoraB);
                 this.actualizarBoleto(codigoBoleto, idUsuario, codigoVuelo);
               }else{
                 int codigoBoleto = this.getCodigoBoleto(Aerolinea, Origen, Destino, fechaA, HoraA, idUsuario, Asiento);
                 int codigoVuelo = this.getCodigoVuelo(Aerolinea, Origen, Destino, fechaB, HoraB);
                 this.actualizarBoleto(codigoBoleto, idUsuario, codigoVuelo);
               }          
            }else{
              JOptionPane.showMessageDialog(null, "Error al realzar la transaccion");
            }
        }else{
           JOptionPane.showMessageDialog(null, "Tarjeta no valida");
        }
      }
    }
    
    public void eliminarBoleto(int codigoBoleto){
     String sql = "DELETE FROM Boleto WHERE numero_Boleto = "+codigoBoleto;
         try {
            Statement st;
            st = conn.createStatement();
       
            int red = st.executeUpdate(sql);
            if(red >0){
                JOptionPane.showMessageDialog(null, "Boleto Eliminado");
            }
         } catch (SQLException ex) {
            Logger.getLogger(VueloDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cancelarVuelo(int codigoBoleto, String numeroTarjeta, int codigo, String marca, Date FVencimiento,
                        String Aerolinea, String Cedula, String Origen, String Destino, Date fecha, Date Hora,
                        int tipoTarjeta, int Precio){
     int totalAPagar = (Precio*30)/100;
     if(tipoTarjeta == 1){
        if(this.validarTarjetaCredito(numeroTarjeta, codigo, FVencimiento, marca, Cedula)==1){
            if(this.actualizarMTC(numeroTarjeta, totalAPagar)==1){
               this.eliminarBoleto(codigoBoleto);
            }else{
              JOptionPane.showMessageDialog(null, "Error al realzar la transaccion");
            }
        }else{
           JOptionPane.showMessageDialog(null, "Tarjeta no valida");
        }
      }else{
      
         if(this.validarTarjetaDebito(numeroTarjeta, codigo, FVencimiento, marca, Cedula)==1){
            if(this.actualizarSTD(numeroTarjeta, totalAPagar)==1 && this.actualizarSCB(numeroTarjeta, totalAPagar) == 1){
               this.eliminarBoleto(codigoBoleto);
            }else{
              JOptionPane.showMessageDialog(null, "Error al realzar la transaccion");
            }
        }else{
           JOptionPane.showMessageDialog(null, "Tarjeta no valida");
        }
      }
    }
    public void itinerario(DefaultTableModel model, JTable table,int codCliente){
      String SQL = "SELECT Aerolinea.nombre_Aerolinea, Pais.nombre, (select nombre from Pais where codigo_Pais = Vuelo.Destino), "
                   +"Vuelo.Fecha, Vuelo.Hora, Clase.clase, Boleto.Asiento, Gate.Gate, Estado.Estado, Boleto.Precio "
                   +"From Vuelo "
                   +"INNER JOIN Boleto ON Vuelo.numero_Vuelo = Boleto.numero_Vuelo " 
                   +"and Boleto.codigo_Cliente = (Select codigo_Cliente from Cliente Where Cliente.codigo_Cliente = "+codCliente+") "
                   +"and (codigo_Estado = 4 or codigo_Estado = 6) "
                   +"INNER JOIN Aerolinea ON Vuelo.codigo_Aerolinea = Aerolinea.codigo_Aerolinea " 
                   +"INNER JOIN Clase ON Boleto.codigo_Clase = Clase.codigo_Clase "
                   +"INNER JOIN Pais ON Vuelo.Origen = Pais.codigo_Pais "
                   +"INNER JOIN Gate ON Boleto.codigo_Gate = Gate.codigo_Gate "
                   +"INNER JOIN Estado ON Boleto.codigo_Estado = Estado.codigo_Estado ";

        
         try{
       PreparedStatement ps = conn.prepareStatement(SQL);   
       ResultSet res = ps.executeQuery();
       ResultSetMetaData rsmd = res.getMetaData();
       ArrayList<Object[]> datos = new ArrayList<>();
       
       while(res.next()){
           Object[] col = new Object[rsmd.getColumnCount()];
           for(int i = 0; i<col.length; i++){
               col[i] = res.getObject(i+1);
           }
           datos.add(col);
       }
        
        model = (DefaultTableModel)table.getModel();
        while(model.getRowCount()>0)model.removeRow(0);
       
        for(int i = 0; i<datos.size(); i++){
               model.addRow(datos.get(i));
           }
       }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, ex.getMessage());
       }
    
    }
    public void comprasRealizadas(DefaultTableModel model, JTable table,int codCliente){
    
         String SQL = "SELECT Aerolinea.nombre_Aerolinea, Pais.nombre, (select nombre from Pais where codigo_Pais = Vuelo.Destino), "
                   +"Vuelo.Fecha, Vuelo.Hora, Clase.clase, Boleto.Asiento, Boleto.Precio "
                   +"From Vuelo "
                   +"INNER JOIN Boleto ON Vuelo.numero_Vuelo = Boleto.numero_Vuelo " 
                   +"and Boleto.codigo_Cliente = (Select codigo_Cliente from Cliente Where Cliente.codigo_Cliente = "+codCliente+") "
                   +"and codigo_Estado = 6 "
                   +"INNER JOIN Aerolinea ON Vuelo.codigo_Aerolinea = Aerolinea.codigo_Aerolinea " 
                   +"INNER JOIN Clase ON Boleto.codigo_Clase = Clase.codigo_Clase "
                   +"INNER JOIN Pais ON Vuelo.Origen = Pais.codigo_Pais ";

        
         try{
       PreparedStatement ps = conn.prepareStatement(SQL);   
       ResultSet res = ps.executeQuery();
       ResultSetMetaData rsmd = res.getMetaData();
       ArrayList<Object[]> datos = new ArrayList<>();
       
       while(res.next()){
           Object[] col = new Object[rsmd.getColumnCount()];
           for(int i = 0; i<col.length; i++){
               col[i] = res.getObject(i+1);
           }
           datos.add(col);
       }
        
        model = (DefaultTableModel)table.getModel();
        while(model.getRowCount()>0)model.removeRow(0);
       
        for(int i = 0; i<datos.size(); i++){
               model.addRow(datos.get(i));
           }
       }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, ex.getMessage());
       }
    }
    public void reservas(DefaultTableModel model, JTable table,int codCliente){
    
        String SQL = "SELECT Aerolinea.nombre_Aerolinea, Pais.nombre, (select nombre from Pais where codigo_Pais = Vuelo.Destino), " +
        "Vuelo.Fecha, Vuelo.Hora, Clase.clase, Boleto.Asiento, Estado.Estado, Boleto.Precio From Vuelo INNER JOIN Boleto ON Vuelo.numero_Vuelo = Boleto.numero_Vuelo " +
        "and codigo_Estado = 4 and Boleto.codigo_Cliente = (Select codigo_Cliente from Cliente Where Cliente.codigo_Cliente = "+codCliente+")" +
        "INNER JOIN Aerolinea ON Vuelo.codigo_Aerolinea = Aerolinea.codigo_Aerolinea " +
        "INNER JOIN Clase ON Boleto.codigo_Clase = Clase.codigo_Clase " +
        "INNER JOIN Pais ON Vuelo.Origen = Pais.codigo_Pais "+
        "INNER JOIN Estado ON Boleto.codigo_Estado = Estado.codigo_Estado";
        
         try{
       PreparedStatement ps = conn.prepareStatement(SQL);   
       ResultSet res = ps.executeQuery();
       ResultSetMetaData rsmd = res.getMetaData();
       ArrayList<Object[]> datos = new ArrayList<>();
       
       while(res.next()){
           Object[] col = new Object[rsmd.getColumnCount()];
           for(int i = 0; i<col.length; i++){
               col[i] = res.getObject(i+1);
           }
           datos.add(col);
       }
        
        model = (DefaultTableModel)table.getModel();
        while(model.getRowCount()>0)model.removeRow(0);
       
        for(int i = 0; i<datos.size(); i++){
               model.addRow(datos.get(i));
           }
       }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, ex.getMessage());
       }
    
    }
    public void cambiar(JPanel principal, JPanel secundario){
       
        secundario.setSize(660, 440);
        principal.removeAll();
        principal.revalidate();
        principal.repaint();
        principal.add(secundario);
    
    }
    
}
