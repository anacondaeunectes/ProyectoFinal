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
    
    public Connection getConexion(){
        return this.conex;
    }
    
    
    /**
     * Este metodo conecta la aplicacion a la bbdd. Asigna al objecto Connection "conex" la conexion referente a los campos de este objeto. Notese que la contrasena en nigun momento se almacena durante el proceso. Unicamente es leida desde el campo de texto al establecer la conexion.
     * @param password 
     * @throws SQLException 
     */
    public void openConexion(String password)throws SQLException{
        
        System.out.println("jdbc:mysql://" + this.url+ "/" + this.db + this.user + password);
        conex = DriverManager.getConnection( "jdbc:mysql://" + this.url+ "/" + this.db, this.user , password );
    }
    
    
    /**
     * Este metodo se encarga de cerrar la conexion asignada al objeto "conex" se esta clase.
     */
    public void closeConexion(){
        try {
            System.out.println("Conex cerrada");
            this.conex.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
       
    }
           
    
}
