package skyblue.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Conexion {
    Connection con=null;
   
    public Connection conexion(){
       try{

            //Class.forName("com.mysql.cj.jdbc.Driver");
            //con=DriverManager.getConnection("jdbc:derby://localhost:1527/SkyBlueDB","SkyBlueDB","dreaming"); 
            //con=DriverManager.getConnection("jdbc:derby://localhost:1527/C:\\Users\\Usuario\\Documents\\My Work\\ING Software I\\Proyecto\\Base de Datos\\SQL\\SkyBlueDB","SkyBlueDB","dreaming"); 
            //con=DriverManager.getConnection("jdbc:derby:SkyBlueDB","root",""); 
            
            con = DriverManager.getConnection("jdbc:mysql://localhost/skyBlueDb", "root", "");
          
           System.out.println("Coneccion Establecida.");
           
       }catch(SQLException e){
        System.out.println("error conexion: " + e);
        
       }
     return con;
    }

    
    public Connection conexionA(){
     try{
        //cargar nuestro driver
        //Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        //con=DriverManager.getConnection("jdbc:derby:aerolineas");
        
        con = DriverManager.getConnection("jdbc:mysql://localhost/skyAirLineDb", "root", "");
        
        System.out.println("Coneccion Establecida.");
           
       }catch(SQLException e){
        System.out.println("error conexion: "+e);
        
       }
     return con;
    }
     
    public Connection conexionB(){
       try{
       //cargar nuestro driver
           
        con = DriverManager.getConnection("jdbc:mysql://localhost/skyBankDb", "root", "");    
       
        System.out.println("Coneccion Establecida.");
           
       }catch(SQLException e){
        System.out.println("error conexion: "+e);
        
       }
     return con;
    }
    
    public Connection Close(){
    try{
        con.close();
    }catch(SQLException ex){}
        return con;
    }
  
}
