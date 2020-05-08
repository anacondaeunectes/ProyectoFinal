/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.util.ArrayList;

/**
 *
 * @author anaco
 */
public class Modelo extends database{
    
    public Modelo(){}
    
    public boolean agregarEmpleado(String nombre, String apellido, int ano_nacimiento, String NIF){
        
        try {
            
            String query = "INSERT INTO empleado VALUES (?, ?, ?, ?)";
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.setString(1, nombre);
            pstm.setString(2, apellido);
            pstm.setInt(3, ano_nacimiento);
            pstm.setString(4, NIF);
            pstm.execute();
            pstm.close();
            /*PreparedStatement statement = this.getConexion().prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setInt(3, Integer.parseInt(ano_nacimiento.toString()));
            statement.setString(4, NIF);*/
            
            //statement.executeQuery();
            return true;
        } catch (SQLException ex) {
            System.err.println("sql: " + ex.getMessage());
            return false;
        }
        
    }
    
    public boolean agregarProyecto(String titulo, LocalDate fecha_inicio, LocalDate fecha_fin, String descripcion){
        
        try {
            
            String query = "INSERT INTO proyecto VALUES (NULL, ?, ?, ?, ?)";
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.setString(1, titulo);
            //pstm.setString(2, fecha_inicio);
            //pstm.setInt(3, fecha_fin);
            pstm.setString(4, descripcion);
            pstm.execute();
            pstm.close();
            /*PreparedStatement statement = this.getConexion().prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setInt(3, Integer.parseInt(ano_nacimiento.toString()));
            statement.setString(4, NIF);*/
            
            //statement.executeQuery();
            return true;
        } catch (SQLException ex) {
            System.err.println("sql: " + ex.getMessage());
            return false;
        }
        
    }
    
    public boolean isProyectoEmpty(){
        
        PreparedStatement pstm = null;
        
        try {
            String query = "SELECT titulo FROM proyecto";
            pstm = this.getConexion().prepareStatement(query);
            ResultSet rset = pstm.executeQuery();
            if (rset.first()) {
                return false;
            }else {
                pstm.close();
                return true;
            }
                    
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            
            return true;
        }
    
    }
    
    public ArrayList<String> cargarProyectos(){
        
        ArrayList <String> arrLi = new ArrayList<>();
        
        try {
            //OJO, la columna titulo en la bbdd es UNIQUE KEY
            String query = "SELECT titulo FROM proyecto";
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            ResultSet rset = pstm.executeQuery();
            
            while(rset.next()){
                arrLi.add(rset.getString("titulo"));
            }
            
            pstm.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
       return arrLi;
    }
    
    
}
