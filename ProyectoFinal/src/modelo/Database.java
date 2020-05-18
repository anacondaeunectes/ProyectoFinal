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
           
      }catch (IOException ex){
           System.out.println("No se podido leer la configuracion de la conexion");
      }
   }

    //Getters y Setters
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Connection getConexion(){
        return this.conex;
    }
    
    
    //Metodos para abrir y cerrar la conexion
    public void openConexion()throws SQLException{
        
        System.out.println("jdbc:mysql://" + this.url+ "/" + this.db + this.user + this.password);
        conex = DriverManager.getConnection( "jdbc:mysql://" + this.url+ "/" + this.db, this.user , this.password );
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
