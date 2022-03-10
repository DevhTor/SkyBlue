
package Model;

import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UsuarioDA extends Thread {
   Conexion con = new Conexion();
   Connection conn = con.conexion();
   Connection conB = con.conexionB();
   
   private String tiempoConexion;
    
    public int validarUsuario(String idUsuario, String password){
       PreparedStatement ps;
       int acceso=0;
        try {
            ps = conn.prepareStatement("Select * from cuentUsuario where  ID=? AND Password=?");
            ps.setString(1, idUsuario);
            ps.setString(2, password);
            ResultSet res = ps.executeQuery();
            if(res.next()){
              acceso=1;
            }else{
              System.out.println("Esta Funcionando");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }

    return acceso;
    }
    
    public boolean existeUsuario(String idUsuario){
        boolean existe = false;
        
         PreparedStatement ps;
        try {
            ps = conn.prepareStatement("Select * from cuentUsuario where  ID=?");
            ps.setString(1, idUsuario);
            ResultSet res = ps.executeQuery();
            if(res.next()==true){
              existe=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return existe;
    }
    
     public boolean existeCliente(String idUsuario){
        boolean existe = false;
        
         PreparedStatement ps;
        try {
            ps = conn.prepareStatement("Select * from Cliente where ID=?");
            ps.setString(1, idUsuario);
            ResultSet res = ps.executeQuery();
            if(res.next()==true){
              existe=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return existe;
    }
     
      public int getCodigoCliente(String idUsuario){
        int resp = 0;
        
         PreparedStatement ps;
        try {
            ps = conn.prepareStatement("Select codigo_Cliente from cuentUsuario where ID = ?");
            ps.setString(1, idUsuario);
            ResultSet res = ps.executeQuery();
            if(res.next()){
                resp = res.getInt("codigo_Cliente");
            }/*else{
              JOptionPane.showMessageDialog(null, "Error al obtener el codigo de cliente");
            }*/
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return resp;
    }
    
    public java.sql.Date SqlDate(java.util.Date date) {
           return new java.sql.Date(date.getTime());
    } 
    
     public java.sql.Time SqlTime(java.util.Date date) {
           return new java.sql.Time(date.getTime());
    } 
    
     public java.sql.Time getSysTime(){
    
     Calendar cal = Calendar.getInstance();
     UsuarioDA uda = new UsuarioDA();
     Date fecha = cal.getTime();
     //java.sql.Date dt = uda.SqlDate(fecha);
     java.sql.Time tm = uda.SqlTime(fecha);
     return tm;
    }
    
      public java.util.Date getStringToDate(String date) throws ParseException{
    
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = formatter.parse(date);
      
       return fecha;
    }
      public java.util.Date getStringToTime(String date) throws ParseException{
    
        DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        Date fecha = formatter.parse(date);
      
       return fecha;
    }
      
     public void crearCliente(String nombre, String apellido, String Pais, String ciudad,
                              String Direccion, String telefono,String email, 
                              String idUsuario, String password, java.util.Date fecha_na, String Cedula,
                              String Codigo_Tarjeta, Date FVencimiento, int clave,
                              String Marca, int Tipo_tarjeta, String Sexo){
       
      
        PreparedStatement ps1;  
        boolean insertado = false;
        String sqlSP="SELECT codigo_Pais FROM pais WHERE nombre = '"+Pais+"'";
        String sqlSC ="SELECT codigo_Ciudad FROM Ciudad WHERE nombre = '"+ciudad+"'";
        String sqlc="INSERT INTO Cliente(nombre_Cliente, Apellido, codigo_Pais, codigo_Ciudad, Direccion, Telefono, Cedula)"
                 + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        try {
             if(existeUsuario(idUsuario)==false){   
                ps1=conn.prepareStatement(sqlc);
                Statement stC = conn.createStatement();
                Statement sst = conn.createStatement();
                Statement st = conn.createStatement();
                ResultSet rsC = stC.executeQuery(sqlSC);
                ResultSet rsU = st.executeQuery(sqlSP);
                int resSC;
                int resSP;
                if(rsU.next()){ 
                    resSP = rsU.getInt("codigo_Pais"); 
                }else{ 
                    resSP = 1;}
                if(rsC.next()){ 
                    resSC = rsC.getInt("codigo_Ciudad");
                }else{
                    resSC = 1;}
              
                ps1.setString(1, nombre);
                ps1.setString(2, apellido);
                ps1.setInt(3, resSP);
                ps1.setInt(4, resSC);
                ps1.setString(5, Direccion);
                ps1.setString(6, telefono);
                ps1.setString(7, Cedula);
                int res1 = ps1.executeUpdate();
                if(res1>0 ){
                    
                     this.crearCuentaUsuario(nombre, apellido, Pais, ciudad, Direccion, telefono, email, idUsuario, password, fecha_na, Cedula);
                     this.guardarTarjeta(Codigo_Tarjeta, Cedula, FVencimiento, clave, Marca, Tipo_tarjeta);
                     this.usuarioTarjeta(Cedula, nombre, apellido, Pais, ciudad, Direccion, Sexo, telefono, fecha_na);
                     this.enviaTarjeta(Codigo_Tarjeta, Marca, Cedula, FVencimiento, clave);
                }
             }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void crearCuentaUsuario(String nombre, String apellido, String Pais, String ciudad, 
                                       String Direccion, String telefono,String email,
                                       String idUsuario, String password, java.util.Date fecha_na, String Cedula){
    
        PreparedStatement ps;
        
        String sqlSP="SELECT codigo_Pais FROM pais WHERE nombre = '"+Pais+"'";
        String sqlCC = "SELECT codigo_Cliente FROM Cliente WHERE "
                    + "nombre_Cliente = '"+nombre+"' and Apellido ='"+apellido+"' and Cedula = '"+Cedula+"'";
        String sql="INSERT INTO cuentUsuario(codigo_Cliente,Fecha_Nacimiento, codigo_Pais, Email, ID, Password)"
                 + "VALUES(?, ?, ?, ?, ?, ?)";
        
        try {
           if(existeUsuario(idUsuario)==false){
              
                ps = conn.prepareStatement(sql);
                Statement st = conn.createStatement();
                ResultSet rsU = st.executeQuery(sqlSP);
                Statement stC = conn.createStatement();
                ResultSet rsC = stC.executeQuery(sqlCC);
                int resSP;
                int resC;
                if(rsU.next()){ 
                    resSP = rsU.getInt("codigo_Pais"); 
                }else{ 
                    resSP = 1;}
                if(rsC.next()){ 
                    resC = rsC.getInt("codigo_Cliente"); 
                }else{
                    resC = 1;}
                ps.setInt(1, resC);
                ps.setDate(2, SqlDate(fecha_na));
                ps.setInt(3, resSP);
                ps.setString(4, email);
                ps.setString(5, idUsuario);
                ps.setString(6, password);
                int res = ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
    }
   
    public void guardarTarjeta(String Codigo_Tarjeta, String Cedula, Date FVencimiento, int clave,
                               String Marca, int Tipo_tarjeta){
    
        String sqlCC = "SELECT codigo_Cliente FROM Cliente WHERE Cedula = '"+Cedula+"'";
        String sqlT="SELECT Codigo_MarcaT FROM MarcaTarjeta WHERE Nombre = '"+Marca+"'";
        String sql="INSERT INTO Tarjeta(Codigo_Tarjeta, Codigo_Cliente, FVencimiento, clave, " +
                    "Marca, Tipo_tarjeta) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;
        try {
                ps = conn.prepareStatement(sql);
                Statement st = conn.createStatement();
                Statement stC = conn.createStatement();
                ResultSet rsU = st.executeQuery(sqlT);
                ResultSet rsCC = stC.executeQuery(sqlCC);
                int resT;
                int resC;
                if(rsU.next()){ 
                    resT = rsU.getInt("Codigo_MarcaT"); 
                }else{ 
                    resT = 1;}
                
                if(rsCC.next()){ 
                    resC = rsCC.getInt("codigo_Cliente"); 
                }else{ 
                    resC = 1;}
                
                ps.setString(1, Codigo_Tarjeta);
                ps.setInt(2, resC);
                ps.setDate(3, SqlDate(FVencimiento));
                ps.setInt(4, clave);
                ps.setInt(5, resT);
                ps.setInt(6, Tipo_tarjeta);
                int res = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public void enviaTarjeta(String codigo_tarjeta, String Tipo_tarjeta, String Titular, Date FExpiracion, int Codigo_Seguridad){
      String sql = "INSERT INTO tarjeta_credito(codigo_tarjeta, Tipo_tarjeta, Titular, FEmision, FExpiracion, Codigo_Seguridad,\n" +
                 "Limite, Sobregiro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      String sql1 ="SELECT Codigo_tipo FROM tipo_tarjeta WHERE Nombre ='"+Tipo_tarjeta+"'";
      PreparedStatement ps;
        try {
               
                ps = conB.prepareStatement(sql);
                Statement st = conB.createStatement();
                ResultSet rsTT = st.executeQuery(sql1);
                int resT;
                if(rsTT.next()){ 
                    resT = rsTT.getInt("Codigo_tipo"); 
                }else{ 
                    resT = 1;}
                
                ps.setString(1, codigo_tarjeta);
                ps.setInt(2, resT);
                ps.setString(3, Titular);
                ps.setDate(4, SqlDate(getStringToDate("2016-12-12")));
                ps.setDate(5, SqlDate(FExpiracion));
                ps.setInt(6, Codigo_Seguridad);
                ps.setInt(7, 40000);
                ps.setInt(8, 75000);
                int res = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
           Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
       }
    
    }
   
    public void usuarioTarjeta(String Cedula, String nombre, String apellido, String Pais, String ciudad,
                              String Direccion, String Sexo, String telefono, java.util.Date fecha_na){
    String sql = "INSERT INTO cliente(Cedula, Nombre, Apellido, Pais, Ciudad, Direccion, "+
                 "Sexo, Telefono, Fecha_Nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String sqlP = "Select Codigo_Pais from pais where Nombre = '"+Pais+"' ";
    String sqlC = "Select Codigo_Ciudad from ciudad where Nombre = '"+ciudad+"'";
    int CPais;
    int CCiudad;
    
     PreparedStatement ps;
        try {
            
                Statement stP = conB.createStatement();
                Statement stC = conB.createStatement();
                ResultSet rsP = stP.executeQuery(sqlP);
                ResultSet rsC = stC.executeQuery(sqlC);
                if(rsP.next()){
                   CPais = rsP.getInt("Codigo_Pais");
                }else{
                   CPais = 0;
                }
                
                if(rsC.next()){
                  CCiudad = rsP.getInt("Codigo_Ciudad ");
                }else{
                  CCiudad = 0;
                }
                ps = conB.prepareStatement(sql);
                ps.setString(1, Cedula);
                ps.setString(2, nombre);
                ps.setString(3, apellido);
                ps.setInt(4, CPais);
                ps.setInt(5, CCiudad);
                ps.setString(6, Direccion);
                ps.setString(7, Sexo);
                ps.setString(8, telefono);
                ps.setDate(9, SqlDate(fecha_na));
                int res = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String setNombreUsuario(String idUsuario){
        String nombreFull= "";
        String nombre ="";
        String apellido= ""; 
        PreparedStatement ps;
        
        try {
        
            
            ps = conn.prepareStatement("Select * from Cliente Where "
                    + "codigo_Cliente = (Select codigo_Cliente From cuentUsuario WHERE ID = ?)");
            ps.setString(1,idUsuario);
            ResultSet res = ps.executeQuery();
            if(res.next()){
                nombre = res.getString("nombre_Cliente");
                apellido = res.getString("Apellido");
                nombreFull=nombre+" "+apellido;
            }else{
               nombreFull = "No se ha establecido un nombre";
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        return nombreFull;
    }
    
    public String setPaisUsuario(String idUsuario){
        String pais ="";
        int codigo =0;
         PreparedStatement ps;
         PreparedStatement ps2;
        try {
            ps2 = conn.prepareStatement("Select codigo_Pais from cuentUsuario where  ID=?");
            ps2.setString(1,idUsuario);
            ResultSet res1 = ps2.executeQuery();
            while(res1.next()){
              codigo = res1.getInt("codigo_Pais");
            }
            ps = conn.prepareStatement("Select * from Pais where codigo_Pais=?");
             ps.setInt(1, codigo);
            ResultSet res = ps.executeQuery();
            if(res.next()){
                pais = res.getString("nombre");
            }else{
                pais = "Pais no establecido";
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pais;
    } 
    
    public void setDatosUsuario(String idUsuario, JTextField nombre, JTextField apellido, JComboBox pais,
                                JComboBox ciudad, JTextField direccion, JTextField telefono, JTextField email,
                                JDateChooser FNacimiento, JTextField cedula, JTextField numeroTarjeta,
                                JDateChooser FVencimiento, JTextField clave, JComboBox Marca){
     String sql = "SELECT * FROM Cliente WHERE codigo_Cliente = " +
                  "(SELECT codigo_Cliente FROM cuentUsuario WHERE ID = '"+idUsuario+"')";
     String sql1 = "SELECT * FROM cuentUsuario WHERE ID = '"+idUsuario+"'";
     String sqlP ="SELECT nombre FROM Pais WHERE codigo_Pais = " +
                  "(SELECT codigo_Pais FROM Cliente WHERE codigo_Cliente = " +
                  "(SELECT codigo_Cliente FROM cuentUsuario WHERE ID = '"+idUsuario+"'))";   
     String sqlC ="SELECT nombre FROM Ciudad WHERE codigo_Ciudad = " +
                  "(SELECT codigo_Ciudad FROM Cliente WHERE codigo_Cliente = " +
                  "(SELECT codigo_Cliente FROM cuentUsuario WHERE ID = '"+idUsuario+"'))";
     String sqlT="SELECT * FROM Tarjeta WHERE codigo_Cliente = " +
                  "(SELECT codigo_Cliente FROM cuentUsuario WHERE ID = '"+idUsuario+"')";
     String sqlTT="SELECT Nombre FROM MarcaTarjeta WHERE Codigo_MarcaT = (SELECT Marca FROM Tarjeta WHERE Codigo_Cliente =\n" +
                  "(SELECT codigo_Cliente FROM cuentUsuario WHERE ID = '"+idUsuario+"'))";
     try {
             PreparedStatement ps = conn.prepareStatement(sql);
             PreparedStatement ps1 = conn.prepareStatement(sql1);
             PreparedStatement psP = conn.prepareStatement(sqlP);
             PreparedStatement psC = conn.prepareStatement(sqlC);
             PreparedStatement psT = conn.prepareStatement(sqlT);
             PreparedStatement psTT = conn.prepareStatement(sqlTT);
             
             ResultSet res1 = ps1.executeQuery();
             ResultSet res = ps.executeQuery();
             ResultSet resP = psP.executeQuery();
             ResultSet resC = psC.executeQuery();
             ResultSet resT = psT.executeQuery();
             ResultSet resTT = psTT.executeQuery();
             if(res.next()){
                 nombre.setText(res.getString("nombre_Cliente"));
                 apellido.setText(res.getString("Apellido"));
                 direccion.setText(res.getString("Direccion"));
                 telefono.setText(res.getString("Telefono"));
                 cedula.setText(res.getString("Cedula")); 
             }
             
             if(resP.next() && resC.next()){
                pais.setSelectedItem(resP.getString("nombre"));
                ciudad.setSelectedItem(resC.getString("nombre"));
             }
             
             if(res1.next()){
                 FNacimiento.setDate(res1.getDate("fecha_Nacimiento"));
                 email.setText(res1.getString("Email"));
             }
             
             if(resT.next()){
                numeroTarjeta.setText(resT.getString("Codigo_Tarjeta"));
                FVencimiento.setDate(resT.getDate("FVencimiento"));
                clave.setText(resT.getString("clave"));
             }
             
             if(resTT.next()){
                Marca.setSelectedItem(resTT.getString("Nombre"));
             }
         } catch (SQLException ex) {
             Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    }
    
    public void actualizarDatosCuenta(String idUsuario, String Pais, String email,
                                      String password, java.util.Date fecha_na){
    
     PreparedStatement ps;
        
        String sqlSP="SELECT codigo_Pais FROM pais WHERE nombre = '"+Pais+"'";
        String sql="UPDATE cuentUsuario SET Fecha_Nacimiento = ?, codigo_Pais = ?, "
                 + "Email = ?, Password = ? WHERE ID = '"+idUsuario+"'";
        
        try {     
                ps = conn.prepareStatement(sql);
                Statement st = conn.createStatement();
                ResultSet rsU = st.executeQuery(sqlSP);
                int resSP;
                if(rsU.next()){ 
                    resSP = rsU.getInt("codigo_Pais"); 
                }else{ 
                    resSP = 1;}
                ps.setDate(1, SqlDate(fecha_na));
                ps.setInt(2, resSP);
                ps.setString(3, email);
                ps.setString(4, password);
                int res = ps.executeUpdate();
            
                if(res >0){
                  JOptionPane.showMessageDialog(null, "Informacion Actualizada");
                }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void actualizaDatosUsuario(String idUsuario, String nombre, String apellido, String Pais, String ciudad, 
                                       String Direccion, String telefono, String Cedula, String email,
                                      String password, java.util.Date fecha_na,  String Codigo_Tarjeta, 
                                      Date FVencimiento, int clave, String Marca){
         
        PreparedStatement ps1;  
        boolean insertado = false;
        String sqlSP="SELECT codigo_Pais FROM pais WHERE nombre = '"+Pais+"'";
        String sqlSC ="SELECT codigo_Ciudad FROM Ciudad WHERE nombre = '"+ciudad+"'";
        String sqlc="UPDATE Cliente SET nombre_Cliente = ?, Apellido = ?, codigo_Pais = ?, "
                   + "codigo_Ciudad = ?, Direccion = ?, Telefono = ?, Cedula =? "
                   + "WHERE codigo_Cliente = (SELECT codigo_Cliente FROM cuentUsuario WHERE ID = '"+idUsuario+"')";
        
        
        try {   
                ps1=conn.prepareStatement(sqlc);
                Statement stC = conn.createStatement();
                Statement st = conn.createStatement();
                ResultSet rsC = stC.executeQuery(sqlSC);
                ResultSet rsU = st.executeQuery(sqlSP);
                int resSC;
                int resSP;
                if(rsU.next()){ 
                    resSP = rsU.getInt("codigo_Pais"); 
                }else{ 
                    resSP = 1;
                }
                if(rsC.next()){ 
                    resSC = rsC.getInt("codigo_Ciudad");
                }else{
                    resSC = 1;
                }
           
                ps1.setString(1, nombre);
                ps1.setString(2, apellido);
                ps1.setInt(3, resSP);
                ps1.setInt(4, resSC);
                ps1.setString(5, Direccion);
                ps1.setString(6, telefono);
                ps1.setString(7, Cedula);
                int res1 = ps1.executeUpdate();
                if(res1>0 ){
                    this.actualizarDatosCuenta(idUsuario, Pais, email, password, fecha_na);
                    this.actualizarDatosTarjeta(idUsuario, Codigo_Tarjeta, FVencimiento, clave, Marca);
                }
             
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public void actualizarDatosTarjeta(String idUsuario, String Codigo_Tarjeta, Date FVencimiento, int clave, String Marca){
     String sql ="UPDATE Tarjeta SET Codigo_Tarjeta = ?, FVencimiento = ?,\n" +
                 "clave = ?, Marca = (SELECT Codigo_MarcaT FROM MarcaTarjeta WHERE Nombre = '"+Marca+"'), " +
                 "Tipo_tarjeta = ? WHERE Codigo_Cliente = (SELECT codigo_Cliente FROM cuentUsuario WHERE ID = '"+idUsuario+"')";
     PreparedStatement ps1;
     try {   
                System.out.println("Datos tarjeta: "+idUsuario+" "+Codigo_Tarjeta+" "+FVencimiento+" "+clave+" "+Marca);
                ps1=conn.prepareStatement(sql);
                ps1.setString(1, Codigo_Tarjeta);
                ps1.setDate(2, SqlDate(FVencimiento));
                ps1.setInt(3, clave);
                ps1.setInt(4, 1);
              
                int res1 = ps1.executeUpdate();
               
             
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public void actualizarPassword(String idUsuario, String Password){
        PreparedStatement ps1;  
        String sqlc="UPDATE cuentUsuario SET Password = ? WHERE ID = '"+idUsuario+"'";
        
        try {   
                ps1=conn.prepareStatement(sqlc);
               
                ps1.setString(1, Password);
                int res1 = ps1.executeUpdate();
                if(res1>0 ){
                    JOptionPane.showMessageDialog(null, "Su clave ha sido actualizada");
                }
             
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public String getCedulaUsuario(String Nombre, String Apellido){
        String cedula ="";
         PreparedStatement ps;
        try {
            ps = conn.prepareStatement("Select Cedula from Cliente where nombre_Cliente=? AND Apellido =?");
            ps.setString(1,Nombre);
            ps.setString(2,Apellido);
            ResultSet res1 = ps.executeQuery();
            while(res1.next()){
              cedula = res1.getString("Cedula");
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cedula;
    } 
    
    public String getNombreUsuario(String idUsuario){
        String nombreFull= "";
        String nombre ="";
        PreparedStatement ps;
        
        try {
        
            
            ps = conn.prepareStatement("Select * from Cliente Where "
                    + "codigo_Cliente = (Select codigo_Cliente From cuentUsuario WHERE ID = ?)");
            ps.setString(1,idUsuario);
            ResultSet res = ps.executeQuery();
            if(res.next()){
                nombre = res.getString("nombre_Cliente");
                nombreFull=nombre;
            }else{
               nombreFull = "No se ha establecido un nombre";
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        return nombreFull;
    }
    
    public String getApellidoUsuario(String idUsuario){
        String nombreFull= "";
        String Apellido ="";
        PreparedStatement ps;
        
        try {
        
            
            ps = conn.prepareStatement("Select * from Cliente Where "
                    + "codigo_Cliente = (Select codigo_Cliente From cuentUsuario WHERE ID = ?)");
            ps.setString(1,idUsuario);
            ResultSet res = ps.executeQuery();
            if(res.next()){
                Apellido = res.getString("Apellido");
                nombreFull= Apellido;
            }else{
               nombreFull = "No se ha establecido un nombre";
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        return nombreFull;
    }
    
    public void setPais(JComboBox JB){
    
        JB.removeAll();
        try{
            Statement st = conn.createStatement();
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
    
    public void setCiudad(JComboBox JB){
    
        JB.removeAll();
        try{
            Statement st = conn.createStatement();
            ResultSet res;
            String query = "SELECT nombre FROM Ciudad";
            res = st.executeQuery(query);
      
            while(res.next()){
                JB.addItem(res.getString("nombre"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
        
    }
    
    public String getConnetionTime(boolean Activo) throws InterruptedException{
         
         int minutos = 0;
         int segundos = 0;
         int horas = 0;
         int milesima = 0;
         String min = "";
         String seg = "";
         String hor ="";
         String tiempo ="";
       
         
         while(Activo==true){
             
           Thread.sleep(1000);
           milesima +=1000;
           
               if(milesima==1000){
                  milesima =0;
                  segundos++;
                   
                   if(segundos ==60){
                      segundos= 0;
                      minutos++;
                      
                      if(minutos == 60){
                         minutos =0;
                         horas++;
                      }
                   }
                }
               
               if(segundos < 10){
                  seg = "0"+segundos;
               }else{
                  seg = String.valueOf(segundos);
               }
                 
               if(minutos < 10){
                  min = "0"+minutos;
               }else{
                  min = String.valueOf(minutos);
               }
               
               if(horas < 10){
                  hor = "0"+horas;
               }else{
                  hor = String.valueOf(horas);
               }
              
                tiempo = hor+":"+min+":"+seg;
                tiempoConexion = tiempo;
          
        }
        
        return tiempoConexion;
    }
    
    public void guardaRegistro(java.sql.Time inicio, java.sql.Time fin, String idUsuario){
      
        int codigo = 0;
        String sql1 = "Select codigo_Cuenta From cuentUsuario Where ID = ?";
        String sql = "INSERT INTO  RegistroAcceso(codigo_Usuario, HoraInicial, HoraFin, DuracionConxion)"
                   + "VALUES(?,?,?,?)";
            
       try{     
            PreparedStatement ps2= conn.prepareStatement(sql1);
            ps2 = conn.prepareStatement(sql1);
            ps2.setString(1,idUsuario);
            ResultSet res1 = ps2.executeQuery();
            while(res1.next()){
                codigo=res1.getInt("codigo_Cuenta");
            }
               PreparedStatement ps= conn.prepareStatement(sql);
               
               ps.setInt(1, codigo);
               ps.setTime(2, inicio);
               ps.setTime(3, fin);
               ps.setString(4, tiempoConexion);
               
              int inMiembro = ps.executeUpdate();
             
               if(inMiembro>0){
                   System.out.println( "Registro Guardado");
               }else{
                   System.out.println( "Registro no Guardado");
               }
        
        } catch (SQLException ex) {
           ex.getMessage();
        } 
        
    }
    
    public void run(){
       try {
           this.getConnetionTime(true);
       } catch (InterruptedException ex) {
           Logger.getLogger(UsuarioDA.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

   
    
}
