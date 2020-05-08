/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author anaco
 */
public class database {
    
    private Connection conex = null;
    
    public database(){
       try{
         //conn = DriverManager.getConnection( this.url, this.user , this.password );     
         conex = DriverManager.getConnection( "jdbc:mysql://localhost:3306/test", "root" , "Nervion1**" );    
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
   }
    
   public Connection getConexion()
   {
    return this.conex;
   }
    
}
