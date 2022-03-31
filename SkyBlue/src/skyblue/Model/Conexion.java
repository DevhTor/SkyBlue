package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection con=null;
   
    public Connection conexion(){
       try{
       //cargar nuestro driver
           Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
         //  con=DriverManager.getConnection("jdbc:derby://localhost:1527/SkyBlueDB","SkyBlueDB","dreaming"); 
         //  con=DriverManager.getConnection("jdbc:derby://localhost:1527/C:\\Users\\Usuario\\Documents\\My Work\\ING Software I\\Proyecto\\Base de Datos\\SQL\\SkyBlueDB","SkyBlueDB","dreaming"); 
             con=DriverManager.getConnection("jdbc:derby:SkyBlueDB","root",""); 
          
           System.out.println("Conecxion Establecida.");
           
       }catch(ClassNotFoundException | SQLException e){
        System.out.println("error conexion: "+e);
        
       }
     return con;
    }
    
    public Connection conexionA(){
     try{
       //cargar nuestro driver
           Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
           con=DriverManager.getConnection("jdbc:derby:aerolineas");
           System.out.println("Conecxion Establecida.");
           
       }catch(ClassNotFoundException | SQLException e){
        System.out.println("error conexion: "+e);
        
       }
     return con;
    }
     
    public Connection conexionB(){
       try{
       //cargar nuestro driver
           Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
           con=DriverManager.getConnection("jdbc:derby:bankdb");
           System.out.println("Conecxion Establecida.");
           
       }catch(ClassNotFoundException | SQLException e){
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
