/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author anaco
 */
public class Database {
    
    private Connection conex = null;
    
    private String url = null;
    private String db = null;
    private String user = null;
    private String password = null;
 
    
    public Database(){
        
        Properties cfg = new Properties();
        
        FileInputStream fis = null;
       try{
           
            fis = new FileInputStream("lib/cfg/conexion.properties");
           
            cfg.load(fis);
           
            this.url = cfg.getProperty("ip");
            this.db = cfg.getProperty("db");
            this.user = cfg.getProperty("user");
            this.password = cfg.getProperty("password");
           
            conex = DriverManager.getConnection( "jdbc:mysql://" + this.url+ "/" + this.db, this.user , this.password );
           
            //conn = DriverManager.getConnection( this.url, this.user , this.password );     
//            conex = DriverManager.getConnection( "jdbc:mysql://localhost:3306/test", "root" , "Nervion1**" );   
            /*ELIMINAR*/
//          conex = DriverManager.getConnection( "jdbc:mysql://79.148.236.236:3306/pgutierrez_UD6", "pgutierrez" , "Nervion1**" );    
      }catch(SQLException e){
         System.err.println(e.getMessage());
      }catch (IOException ex){
           System.out.println("No se podido leer la configuracion de la conexion");
      }
   }
    
   public Connection getConexion()
   {
    return this.conex;
   }
   
   public void closeConexion(){
       try {
           System.out.println("Conex cerrada");
           this.conex.close();
           
       } catch (SQLException e) {
           System.err.println(e.getMessage());
       }
       
   }
           
    
}
