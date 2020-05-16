/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anaco
 */
public class Modelo extends database{
    
    //Por defecto estipula 1920 como el minimo ano de nacimiento .
    private Year min_Ano_Nacimiento = Year.of(1920);
    
    public Modelo(){}

    public Year getMin_Ano_Nacimiento() {
        return min_Ano_Nacimiento;
    }

    public void setMin_Ano_Nacimiento(Year min_Ano_Nacimiento) {
        this.min_Ano_Nacimiento = min_Ano_Nacimiento;
    }
    
    public boolean agregarEmpleado(String nombre, String apellido, Year ano_nacimiento, String NIF) throws SQLException{
        
        //Primero llama al metodo trim() con todos los datos para despues pasarlo al metodo validarDatos
        nombre = nombre.trim();
        apellido = apellido.trim();
        NIF = NIF.trim();
        
        if (!validarDatosEmpleado(nombre, apellido, ano_nacimiento, NIF)) {
            return false;
        }
        
        String query = "INSERT INTO empleado VALUES (?, ?, ?, ?)";
        PreparedStatement pstm = this.getConexion().prepareStatement(query);
        pstm.setString(1, nombre);
        pstm.setString(2, apellido);
        pstm.setInt(3, Integer.parseInt(ano_nacimiento.toString()));
        pstm.setString(4, NIF);
        pstm.execute();
        pstm.close();
        
        return true;
        
        
    }
    
    private boolean validarDatosEmpleado(String nombre, String apellido, Year ano_nacimiento, String NIF){
        boolean flag = true;

        if (nombre.equals("")|| apellido.equals("") || !comprobarDNI(NIF)) {
            flag = false;
        }
    return flag;
    }
        
    private boolean comprobarDNI(String a) {
        return a.matches("[0-9]{8}[a-zA-Z]");
    }
    
    public boolean agregarProyecto(LocalDate fecha_inicio, LocalDate fecha_fin, String titulo, String descripcion) throws SQLException{
        
        titulo = titulo.trim();
        
        if (!validarDatosProyecto(titulo, fecha_inicio, fecha_fin, descripcion)) {
            return false;
        }

        PreparedStatement pstm = this.getConexion().prepareCall("{call insertProyecto (?, ?, ?, ?)}");
        pstm.setString(1, titulo);
        pstm.setString(2, fecha_inicio.toString());
        pstm.setString(3, fecha_fin.toString());
        pstm.setString(4, descripcion);
        pstm.execute();
        pstm.close();

        return true;
        
    }
    
    private boolean validarDatosProyecto(String titulo, LocalDate fecha_inicio, LocalDate fecha_fin, String descripcion){
        
        return (titulo.equals("") || fecha_fin.isBefore(fecha_inicio) || descripcion.length() > 500) ? false: true;
        
//        if (titulo.equals("") || fecha_fin.isBefore(fecha_inicio) || descripcion.length() > 500){
//            return false;
//        }else{
//            return true;
//        }
        
    }
    
    public void asociarEmpleadoProyecto(String NifEmpleado, Proyecto proyecto) throws SQLException{

        String query = "INSERT INTO trabaja VALUES (?, ?)";
        PreparedStatement pstm = this.getConexion().prepareStatement(query);
        pstm.setString(1, NifEmpleado);
        pstm.setInt(2, proyecto.getId_Proyecto());
        pstm.execute();


        pstm.close();
        
    }
    
    public void eliminarEmpleado(String NifEmpleado) throws SQLException{
        
        PreparedStatement pstm = this.getConexion().prepareStatement("DELETE FROM empleado WHERE NIF = ?");
        pstm.setString(1, NifEmpleado);
        pstm.execute();
        pstm.close();
    }
    
    public void eliminarProyecto(int idProy) throws SQLException{
        
        PreparedStatement pstm = this.getConexion().prepareStatement("DELETE FROM proyecto WHERE id_proyecto = ?");
        pstm.setInt(1, idProy);
        pstm.execute();
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
    
    public boolean modificarEmpleado(String nombre, String apellido, int anoNacimiento, String Nif) throws SQLException{
        
        PreparedStatement pstm = this.getConexion().prepareCall("{call doUpdateEmpleado (?, ?, ?, ?)}");
        pstm.setString(1, nombre);
        pstm.setString(2, apellido);
        pstm.setInt(3, anoNacimiento);
        pstm.setString(4, Nif);

        pstm.execute();
        pstm.close();
        
        return true;
    }
    
    public boolean modificarProyecto(String titulo, String fechaInicio, String fechaFin, int id, String descripcion) throws SQLException{
    
        System.out.println(descripcion);
        PreparedStatement pstm = this.getConexion().prepareCall("{call doUpdateProyecto(?, ?, ?, ?, ?)}");
        pstm.setString(1, titulo);
        pstm.setString(2, fechaInicio);
        pstm.setString(3, fechaFin);
        pstm.setInt(4, id);
        pstm.setString(5, descripcion);
        
        
        pstm.execute();
        pstm.close();
        
        return true;
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
                    fila[2] = String.valueOf(LocalDate.parse(rSet.getString(3)).getYear());
                    System.out.println(LocalDate.parse(rSet.getString(3)).toString());
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
                        if (listNombreColumnas.get(0).equals("ano_nacimiento")) {
                            fila[0] = String.valueOf(LocalDate.parse(rSet.getString(1)).getYear());
                        }else{
                            fila[0] = rSet.getString(1);
                        }
                    }
                    if (listNombreColumnas.size() >= 2) {
                        if (listNombreColumnas.get(1).equals("ano_nacimiento")) {
                            fila[1] = String.valueOf(LocalDate.parse(rSet.getString(2)).getYear());
                        }else{
                            fila[1] = rSet.getString(2);
                        }
                    }
                    if (listNombreColumnas.size() >= 3) {
                        if (listNombreColumnas.get(2).equals("ano_nacimiento")) {
                            fila[2] = String.valueOf(LocalDate.parse(rSet.getString(3)).getYear());
                        }else{
                            fila[2] = rSet.getString(3);
                        }
                    }
                    if (listNombreColumnas.size() >= 4) {
                        if (listNombreColumnas.get(3).equals("ano_nacimiento")) {
                            fila[3] = String.valueOf(LocalDate.parse(rSet.getString(4)).getYear());
                        }else{
                            fila[3] = rSet.getString(4);
                        }
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
                
                whr = whr + " descripcion LIKE '%" + descripcion + "%'";
                
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
    
   public DefaultTableModel getProyectosAsocidos(String id) throws SQLException{
   
       String query = "SELECT titulo, fecha_inicio, fecha_fin, id_proyecto, descripcion FROM proyecto WHERE id_proyecto IN (SELECT ref_proyecto FROM trabaja WHERE ref_empleado = ?)";
       PreparedStatement pstm = this.getConexion().prepareCall(query);
       pstm.setString(1, id);
       ResultSet rSet = pstm.executeQuery();
       DefaultTableModel tableModel = new DefaultTableModel(null, new String[]{"Titulo", "Fecha inicio", "Fecha fin", "ID", "Descripcion"});
       String[] fila = new String[tableModel.getColumnCount()];
       while (rSet.next()) {           
           fila[0] = rSet.getString(1);
           fila[1] = rSet.getString(2);
           fila[2] = rSet.getString(3);
           fila[3] = rSet.getString(4);
           fila[4] = rSet.getString(5);
           tableModel.addRow(fila);
       }
       
       return tableModel;
   }
   
   public DefaultTableModel getEmpleadosAsocidos(int id) throws SQLException{
   
       String query = "SELECT * FROM empleado WHERE NIF IN (SELECT ref_empleado FROM trabaja WHERE ref_proyecto = ?)";
       PreparedStatement pstm = this.getConexion().prepareCall(query);
       pstm.setInt(1, id);
       ResultSet rSet = pstm.executeQuery();
       DefaultTableModel tableModel = new DefaultTableModel(null, new String[]{"Nombre", "Apellido", "Año nacimiento", "NIF"});
       String[] fila = new String[tableModel.getColumnCount()];
       while (rSet.next()) {           
           fila[0] = rSet.getString(1);
           fila[1] = rSet.getString(2);
           fila[2] = String.valueOf(LocalDate.parse(rSet.getString(3)).getYear());
           fila[3] = rSet.getString(4);
           tableModel.addRow(fila);
       }
       
       return tableModel;
   }
   
   public String[] datosEdadProyectos()throws SQLException{
       
       String[] resultado = new String[2];
       
       CallableStatement pstm = this.getConexion().prepareCall("{call edad(?, ?)}");
       pstm.registerOutParameter(1, Types.VARCHAR);
       pstm.registerOutParameter(2, Types.VARCHAR);
       pstm.execute();
       ResultSet rSet = pstm.getResultSet();
       
       while (rSet.next()) {           
           System.out.println(rSet.toString());
       }
       
       resultado[0] = pstm.getString(1);
       resultado[1] = pstm.getString(2);
       
       return resultado;
       
   }
    
    public static Year leerCfg_MinAnoNacimiento(){
        Year yyyy = null;
        
        BufferedReader br;
        
        try {
            br = new BufferedReader(new FileReader("cfg/cfg.txt"));
            
            System.out.println(br.readLine());

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return yyyy;
    }
    
    public static boolean sobreescribirCFG_MinAnoNacimiento(Year yyyy){
        
        File file;
    
        BufferedReader br;
        
        FileOutputStream fos;
        
        ObjectOutputStream oos;
        
        try {
            
            file = new File("cfg/cfg.txt");
            
            br = new BufferedReader(new FileReader(file));
            
            fos = new FileOutputStream(file);
            
            oos = new ObjectOutputStream(fos);
            
            oos.writeObject(yyyy);
            
            System.out.println(br.readLine());

            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    

    
    public static void main(String args[]) {
            
            //getTablaEmpleado(true, false, false, true, "Pepe", "", "1970", "123");
            /*Year a = Year.now();
            a = Year.of(1990);
            final Year MIN_ANO_NACIMIENTO = a;
            System.out.println(MIN_ANO_NACIMIENTO.toString());*/
//            sobreescribirCFG_MinAnoNacimiento(Year.of(1999));
//            System.out.println(comprobarDNI("1234578A"));
        
    }
    
    
    
    
}
