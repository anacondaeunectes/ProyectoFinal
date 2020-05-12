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
import java.util.Iterator;
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
    
    public void asociarEmpleadoProyecto(int[] NifEmpleados, Proyecto proyecto) throws SQLException{
        
        String query = "";
        
            PreparedStatement pstm = null;
            
            for(int emp : NifEmpleados){
            
                query = "INSERT INTO trabaja VALUES (?, ?)";
                pstm = this.getConexion().prepareStatement(query);
                pstm.setInt(1, emp);
                pstm.setInt(2, proyecto.getId_Proyecto());
                pstm.execute();
            }
            pstm.close();
        
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
    
    public ArrayList<Proyecto> cargarProyectos(String titulo, String fechaInicio, String fechaFin, String id, String descripcion){
        
        ArrayList <Proyecto> arrLi = new ArrayList<>();
        
        try {
            //Coge cada proyecto y forma un objeto "Proyecto"
            String query = "SELECT * FROM proyecto WHERE titulo LIKE ? AND fecha_inicio LIKE ? AND fecha_fin LIKE ? AND id_proyecto LIKE ? AND descripcion LIKE ?";
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.setString(1, titulo + "%");
            pstm.setString(2, fechaInicio + "%");
            pstm.setString(3, fechaFin + "%");
            pstm.setString(4, id + "%");
            pstm.setString(5, "%" + descripcion + "%");
            System.out.println(query);
            ResultSet rset = pstm.executeQuery();
            
            while(rset.next()){
                arrLi.add(new Proyecto(rset.getString("titulo"), LocalDate.parse(rset.getString("fecha_inicio")), LocalDate.parse(rset.getString("fecha_fin")), rset.getString("descripcion"), rset.getInt("id_proyecto")));
            }
            
            pstm.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
       return arrLi;
    }

        public DefaultTableModel getTablaEmpleado(boolean nombreChecked, boolean apellidoChecked, boolean ano_NacimientoChecked, boolean NIFChecked, String nombre, String apellido, String ano_nacimiento, String NIF){
        DefaultTableModel tableModel;
        ResultSet rSet;
        String query = "";
        String whr = "";
        String[] nombreColumnas;
    
            /*------------------------ Columnas a obtener - ColumnNames del TableModel ------------------------*/
        if ((nombreChecked == true && apellidoChecked == true && ano_NacimientoChecked == true && NIFChecked == true) || (nombreChecked == false && apellidoChecked == false && ano_NacimientoChecked == false && NIFChecked == false)) {
            nombreColumnas = new String[] {"nombre", "apellido", "ano_nacimiento", "NIF"};
            System.out.println("if");
            //Crea el nuevo modelo para la tabla con el nombre de las columnas y ademas, SOBREESCRIBE el metodo "isCellEditable" de la clase para hacer que siempre sea "false" y, por tanto, ineditable.
            tableModel = new DefaultTableModel(null, nombreColumnas){
            @Override
            public boolean isCellEditable(int row, int column){return false;}};
            
            query = "SELECT * FROM empleado";
            
            
            if (nombre != "" || apellido != "" || ano_nacimiento != "" || NIF != "") {
                
                query = query + " WHERE ";
                
                whr = whr + " nombre LIKE '" + nombre + "%' AND ";
                   
                whr = whr + " apellido LIKE '" + apellido + "%' AND ";
                    
                whr = whr + " ano_nacimiento LIKE '" + ano_nacimiento + "%' AND ";
                
                whr = whr + " NIF LIKE '" + NIF + "%'";
                
                query = query + whr;
                
            }
            
            System.out.println(query);
            
            try {
                
                Statement sentencia = this.getConexion().createStatement();
                rSet = sentencia.executeQuery(query);
                
                String[] fila = new String[4];
                while (rSet.next()){

                    fila[0] = rSet.getString(1);
                    fila[1] = rSet.getString(2);
                    fila[2] = rSet.getString(3);
                    fila[3] = rSet.getString(4);
                    tableModel.addRow(fila);
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
 
        }else{
           
            System.out.println("else");
            
            List <String> listNombreColumnas = new ArrayList<>();
            List <String> listVariables = new ArrayList<>();
            //Si los CheckBox estan marcados, se buscara tambien esa columna
            if (nombreChecked) {
                listNombreColumnas.add("nombre");
                listVariables.add(nombre);
            }
            if (apellidoChecked) {
                listNombreColumnas.add("apellido");
                listVariables.add(apellido);
            }
            if (ano_NacimientoChecked) {
                listNombreColumnas.add("ano_nacimiento");
                listVariables.add(ano_nacimiento);
            }
            if (NIFChecked) {
                listNombreColumnas.add("NIF");
                listVariables.add(NIF);
            }
            
            listVariables.stream().forEach(System.out::println);
            
            //Se transforma el Arraylist "list" a un Array de cara a poder introducirlo en el DefaultTableModel
            nombreColumnas = new String[listNombreColumnas.size()];
            listNombreColumnas.toArray(nombreColumnas);
            
            //Crea el nuevo modelo para la tabla con el nombre de las columnas y ademas, SOBREESCRIBE el metodo "isCellEditable" de la clase para hacer que siempre sea "false" y, por tanto, ineditable.
            tableModel = new DefaultTableModel(null, nombreColumnas){
            @Override
            public boolean isCellEditable(int row, int column){return false;}};
            
            //Creamos el String con los campos a seleccionar en la consulta a la BBDD
            String select = "SELECT ";
            boolean first = true;
            Iterator itVariables = listVariables.iterator();
            
            for(String a : nombreColumnas){
                
                //Menos la primera vez, todas
                if (!first) {
                    select = select + ",";
                    if (itVariables.hasNext()) {
                        whr = whr + "AND " + a + " LIKE '" + itVariables.next() + "%'";
                        System.out.println(whr);
                    }
                   
                }else{
                    whr = whr + " WHERE " + a + " LIKE '" + itVariables.next() + "%'" ;
                    System.out.println(whr);
                }
 
                select = select + a;
                
                first = false;
            }
            
            query = select + " FROM empleado " + whr;

            System.out.println(query);
            
            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(query);
                rSet = pstm.executeQuery(query);
                
                String[] fila = new String[listNombreColumnas.size()];
                while (rSet.next()){
                    if (listNombreColumnas.size() >= 1) {
                        System.out.println(rSet.getString(1));
                        fila[0] = rSet.getString(1);
                        
                    }
                    if (listNombreColumnas.size() >= 2) {
                        System.out.println(rSet.getString(2));
                        fila[1] = rSet.getString(2);
                    }
                    if (listNombreColumnas.size() >= 3) {
                        System.out.println(rSet.getString(3));
                        fila[2] = rSet.getString(3);
                    }
                    if (listNombreColumnas.size() >= 4) {
                        System.out.println(rSet.getString(4));
                        fila[3] = rSet.getString(4);
                    }
                    tableModel.addRow(fila);
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
             
        }

        return tableModel;
    }
    
    public DefaultTableModel getTablaProyecto(boolean tituloChecked, boolean fechaInicioChecked, boolean fechaFinChecked, boolean idChecked, boolean descripcionChecked, String titulo, String fechaInicio, String fechaFin, String id, String descripcion){
        DefaultTableModel tableModel;
        ResultSet rSet;
        String query = "";
        String whr = "";
        String[] nombreColumnas;
    
            /*------------------------ Columnas a obtener - ColumnNames del TableModel ------------------------*/
        if ((tituloChecked == true && fechaInicioChecked == true && fechaFinChecked == true && idChecked == true && descripcionChecked == true) || (tituloChecked == false && fechaInicioChecked == false && fechaFinChecked == false && idChecked == false && descripcionChecked == false)) {
            nombreColumnas = new String[] {"titulo", "fecha_inicio", "fecha_fin", "id_proyecto", "descripcion"};
            System.out.println("if");
            //Crea el nuevo modelo para la tabla con el nombre de las columnas y ademas, SOBREESCRIBE el metodo "isCellEditable" de la clase para hacer que siempre sea "false" y, por tanto, ineditable.
            tableModel = new DefaultTableModel(null, nombreColumnas){
            @Override
            public boolean isCellEditable(int row, int column){return false;}};
            
            query = "SELECT titulo, fecha_inicio, fecha_fin, id_proyecto, descripcion FROM proyecto";
            
            
            if (titulo != "" || fechaInicio != "" || fechaFin != "" || id != "" || descripcion != "") {
                
                query = query + " WHERE ";
                
                whr = whr + " titulo LIKE '" + titulo + "%' AND ";
                   
                whr = whr + " fecha_inicio LIKE '" + fechaInicio + "%' AND ";
                    
                whr = whr + " fecha_fin LIKE '" + fechaFin + "%' AND ";
                
                whr = whr + " id_proyecto LIKE '" + id + "%' AND ";
                
                whr = whr + " descripcion LIKE '" + descripcion + "%'";
                
                query = query + whr;
                
            }
            
            System.out.println(query);
            
            try {
                
                Statement sentencia = this.getConexion().createStatement();
                rSet = sentencia.executeQuery(query);
                
                String[] fila = new String[5];
                while (rSet.next()){

                    fila[0] = rSet.getString(1);
                    fila[1] = rSet.getString(2);
                    fila[2] = rSet.getString(3);
                    fila[3] = rSet.getString(4);
                    fila[4] = rSet.getString(5);
                    tableModel.addRow(fila);
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
 
        }else{
           
            System.out.println("else");
            
            List <String> listNombreColumnas = new ArrayList<>();
            List <String> listVariables = new ArrayList<>();
            //Si los CheckBox estan marcados, se buscara tambien esa columna
            if (tituloChecked) {
                listNombreColumnas.add("titulo");
                listVariables.add(titulo);
            }
            if (fechaInicioChecked) {
                listNombreColumnas.add("fecha_inicio");
                listVariables.add(fechaInicio);
            }
            if (fechaFinChecked) {
                listNombreColumnas.add("fecha_fin");
                listVariables.add(fechaFin);
            }
            if (idChecked) {
                listNombreColumnas.add("id_proyecto");
                listVariables.add(id);
            }
            if (descripcionChecked) {
                listNombreColumnas.add("descripcion");
                listVariables.add(descripcion);
            }
            
            listVariables.stream().forEach(System.out::println);
            
            //Se transforma el Arraylist "list" a un Array de cara a poder introducirlo en el DefaultTableModel
            nombreColumnas = new String[listNombreColumnas.size()];
            listNombreColumnas.toArray(nombreColumnas);
            
            //Crea el nuevo modelo para la tabla con el nombre de las columnas y ademas, SOBREESCRIBE el metodo "isCellEditable" de la clase para hacer que siempre sea "false" y, por tanto, ineditable.
            tableModel = new DefaultTableModel(null, nombreColumnas){
            @Override
            public boolean isCellEditable(int row, int column){return false;}};
            
            //Creamos el String con los campos a seleccionar en la consulta a la BBDD
            String select = "SELECT ";
            boolean first = true;
            Iterator itVariables = listVariables.iterator();
            
            for(String a : nombreColumnas){
                
                //Menos la primera vez, todas
                if (!first) {
                    select = select + ",";
                    if (itVariables.hasNext()) {
                        whr = whr + "AND " + a + " LIKE '" + itVariables.next() + "%'";
                        System.out.println(whr);
                    }
                   
                }else{
                    whr = whr + " WHERE " + a + " LIKE '" + itVariables.next() + "%'" ;
                    System.out.println(whr);
                }
 
                select = select + a;
                
                first = false;
            }
            
            query = select + " FROM proyecto " + whr;

            System.out.println(query);
            
            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(query);
                rSet = pstm.executeQuery(query);
                
                String[] fila = new String[listNombreColumnas.size()];
                while (rSet.next()){
                    if (listNombreColumnas.size() >= 1) {
                        //System.out.println(rSet.getString(1));
                        fila[0] = rSet.getString(1);
                        
                    }
                    if (listNombreColumnas.size() >= 2) {
                        //System.out.println(rSet.getString(2));
                        fila[1] = rSet.getString(2);
                    }
                    if (listNombreColumnas.size() >= 3) {
                        //System.out.println(rSet.getString(3));
                        fila[2] = rSet.getString(3);
                    }
                    if (listNombreColumnas.size() >= 4) {
                        //System.out.println(rSet.getString(4));
                        fila[3] = rSet.getString(4);
                    }
                    if (listNombreColumnas.size() >= 5) {
                        //System.out.println(rSet.getString(5));
                        fila[4] = rSet.getString(5);
                    }
                    tableModel.addRow(fila);
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
             
        }

        return tableModel;
    }
    
    public static void main(String args[]) {
            
            //getTablaEmpleado(true, false, false, true, "Pepe", "", "1970", "123");
        
    }
    
    
}
