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
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;

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
    
    public boolean agregarProyecto(LocalDate fecha_inicio, LocalDate fecha_fin, String titulo, String descripcion){
        
        try {
            
            String query = "INSERT INTO proyecto VALUES (?, ?, ?, ?, NULL)";
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.setString(1, fecha_inicio.toString());
            System.out.println(fecha_inicio.toString());
            pstm.setString(2, fecha_fin.toString());
            System.out.println(fecha_fin.toString());
            pstm.setString(3, titulo);
            pstm.setString(4, descripcion);
            pstm.execute();
            pstm.close();

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
    
    public ArrayList<Proyecto> cargarProyectos(){
        
        ArrayList <Proyecto> arrLi = new ArrayList<>();
        
        try {
            //Coge cada proyecto y forma un objeto "Proyecto"
            String query = "SELECT * FROM proyecto";
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            ResultSet rset = pstm.executeQuery();
            
            while(rset.next()){
                arrLi.add(new Proyecto(rset.getString("titulo"), LocalDate.parse(rset.getString("fecha_inicio")), LocalDate.parse(rset.getString("fecha_fin")), rset.getString("descripcion"), rset.getInt("id_proyecto")));
            }
            //arrLi.add(new Proyecto(""));
            
            pstm.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
       return arrLi;
    }
    
    public static String getTablaEmpleado(boolean nombreChecked, boolean apellidoChecked, boolean ano_NacimientoChecked, boolean NIFChecked, String nombre, String apellido, String ano, String NIF){
        DefaultTableModel tableModel;
        ResultSet rSet;
        String query = "";
        String[] nombreColumnas;
    
        /*------------------------ Columnas a obtener - ColumnNames del TableModel ------------------------*/
        if ((nombreChecked == true && apellidoChecked == true && ano_NacimientoChecked == true && NIFChecked == true) || (nombreChecked == false && apellidoChecked == false && ano_NacimientoChecked == false && NIFChecked == false)) {
            nombreColumnas = new String[] {"nombre", "apellido", "ano_nacimiento", "NIF"};
            
            if (nombre != null) {
                
            }
            /*try {
                Statement sentencia = this.getConexion().createStatement();
                rSet = sentencia.executeQuery("SELECT * FROM empleado");
            } catch (Exception e) {
                e.printStackTrace();
            }*/
           
        }else{
            
            List <String> list = new ArrayList<>();
            
            if (nombreChecked) {
                list.add("nombre");
            }
            if (apellidoChecked) {
                list.add("apellido");
            }
            if (ano_NacimientoChecked) {
                list.add("ano_nacimiento");
            }
            if (NIFChecked) {
                list.add("NIF");
            }
            
            //Se transforma el Arraylist "list" a un Array de cara a poder introducirlo en el DefaultTableModel
            nombreColumnas = new String[list.size()];
            list.toArray(nombreColumnas);
            
            //Creamos el String con los campos a seleccionar en la consulta a la BBDD
            String select = "";
            boolean first = true;
            
            for(String a : nombreColumnas){
                
                if (!first) {
                     select = select + ",";
                }
                 
                select = select + a;
                
                first = false;
            }
            
            return select;
            /*try {
                String query = "SELECT" + select + "FROM empleado";
                PreparedStatement pstm = this.getConexion().prepareStatement(query);
                rSet = sentencia.
            } catch (Exception e) {
            }*/
           
        }
    
        tableModel = new DefaultTableModel(null, nombreColumnas);
        
        /*------------------------ Obtencion Datos BBDD segun las columnas elegidas ------------------------*/
        
        String fila[] = new String[nombreColumnas.length];
        
        
        return "";
        //return nombreColumnas;
    }
    
    
    public static void main(String args[]) {
            
            
        
    }
    
    
}
