/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import java.util.Properties;
import javax.swing.table.DefaultTableModel;

public class Modelo extends Database{
    
    /**
     * Metodo encargado de insertar nuevos empleados en la Base de Datos. Ademas, llama al metodo validarDatosEmpleado() para que, en caso de que estos no sean correctos, se cancele la operacion.
     * @param nombre 
     * @param apellido
     * @param ano_nacimiento
     * @param NIF
     * @return <ul>
    *   <li>True: en caso de agregar correctamente al empleado</li>
    *   <li>False: en caso de no agregar correctamente al empleado, ya sea por que no ha validado bien los datos o por un error al introducirlo</li>
    * </ul>
     * @throws SQLException En caso de error por parte de la Base de Datos, se manejara desde el controlador buscando informar desde la vista.
     */
    public boolean agregarEmpleado(String nombre, String apellido, Year ano_nacimiento, String NIF) throws SQLException{
        
        //Primero llama al metodo trim() con todos los datos para despues pasarlo al metodo validarDatos
        nombre = nombre.trim();
        apellido = apellido.trim();
        NIF = NIF.trim();
        
        if (!validarDatosEmpleado(nombre, apellido, ano_nacimiento, NIF)) {
            return false;
        }
        
        String query = "INSERT INTO empleado VALUES (?, ?, ?, ?)";
        //Try-with-resources que se encarga de cerrar el PreparedStatement, ya que implementa la interfaz AutoClosable, se lance o no una excepcion 
        try (PreparedStatement pstm = this.getConexion().prepareStatement(query)) {
            pstm.setString(1, nombre);
            pstm.setString(2, apellido);
            pstm.setInt(3, Integer.parseInt(ano_nacimiento.toString()));
            pstm.setString(4, NIF);
            pstm.execute();
        }
        
        return true;
     
    }
    
    /**
     * Este metodo valida los datos de un empleado. LLama al metodo comprobarDNI() para validar el NIF.
     * @param nombre
     * @param apellido
     * @param ano_nacimiento
     * @param NIF
     * @return <ul>
    *   <li>True: en caso de validar correctamente los datos</li>
    *   <li>False: en caso de errar al validar los datos</li>
    * </ul>
     */
    private boolean validarDatosEmpleado(String nombre, String apellido, Year ano_nacimiento, String NIF){
        boolean flag = true;

        if (nombre.equals("")|| apellido.equals("") || !comprobarDNI(NIF)) {
            flag = false;
        }
    return flag;
    }
       
    /**
     * Metodo que valida un String en funcion del patron que sigue un NIF
     * @param a
     * @return <ul>
    *   <li>True: en caso de SI validar el String</li>
    *   <li>False: en caso de NO validar el String</li>
    * </ul>
     */
    private boolean comprobarDNI(String a) {
        return a.matches("[0-9]{8}[a-zA-Z]");
    }
    
    /**
     * Metodo encargado de insertar nuevos proyectos en la Base de Datos. Ademas, llama al metodo validarDatosProyecto() para que, en caso de que estos no sean correctos, se cancele la operacion.
     * @param fecha_inicio
     * @param fecha_fin
     * @param titulo
     * @param descripcion
     * @return <ul>
    *   <li>True: en caso de leer correctamente el archivo</li>
    *   <li>False: en caso de errar al leer el archivo</li>
    * </ul>
     * @throws SQLException En caso de error por parte de la Base de Datos, se manejara desde el controlador buscando informar desde la vista.
     */
    public boolean agregarProyecto(LocalDate fecha_inicio, LocalDate fecha_fin, String titulo, String descripcion) throws SQLException{
        
        titulo = titulo.trim();
        
        if (!validarDatosProyecto(titulo, fecha_inicio, fecha_fin, descripcion)) {
            return false;
        }
        //Try-with-resources que se encarga de cerrar el CallableStatement se lance o no una excepcion
        try(CallableStatement pstm = this.getConexion().prepareCall("{call insertProyecto (?, ?, ?, ?)}")){
            pstm.setString(1, titulo);
            pstm.setString(2, fecha_inicio.toString());
            pstm.setString(3, fecha_fin.toString());
            pstm.setString(4, descripcion);
            pstm.execute();
        }

        return true;
        
    }
    
    /**
     * Este metodo valida los datos de un proyecto.
     * @param titulo
     * @param fecha_inicio
     * @param fecha_fin
     * @param descripcion
     * @return <ul>
    *   <li>True: en caso de validar correctamente los datos</li>
    *   <li>False: en caso de errar al validar los datos</li>
    * </ul>
     */
    private boolean validarDatosProyecto(String titulo, LocalDate fecha_inicio, LocalDate fecha_fin, String descripcion){
        
        return (titulo.equals("") ||  descripcion.length() > 500) ? false: true;

        
    }
    
    /**
     * Este metodo permite asociar un unico empleado con un proyecto. La idea es que desde el controlador se llame en bucle a este metodo por cada empleado seleccionado ya que es posible que alguno de ellos ya esté asociado a ese mismo proyecto
     * @param NifEmpleado NIF del empleado seleccionado
     * @param proyecto Objeto proyecto seleccionado
     * @throws SQLException La posible excepcion se controla desde el controlador
     */
    public void asociarEmpleadoProyecto(String NifEmpleado, Proyecto proyecto) throws SQLException{

        String query = "INSERT INTO trabaja VALUES (?, ?)";
        //Try-with-resources que se encarga de cerrar el PreparedStatement se lance o no una excepcion
        try(PreparedStatement pstm = this.getConexion().prepareStatement(query)){
            pstm.setString(1, NifEmpleado);
            pstm.setInt(2, proyecto.getId_Proyecto());
            pstm.execute();
        }
        
    }
    
    /**
     * Este metodo permite disociar varios empleadosde un mismo proyecto. Debido a que en este caso no existe la posibilidad de que se trate de disociar empleados no asociados, ya que solo es posible seleccionar empleados sí asociados, este metodo puede disociar todos de una vez.
     * @param NifEmpleado Array de empleados a disociar
     * @param idProy Id del proyecto seleccionado
     * @throws SQLException La posible excepcion se controla desde el controlador
     */
    public void disociarEmpleadoProyecto(String[] NifEmpleado, int idProy)throws SQLException{
    
        String query;
        
        for (String emp : NifEmpleado) {
            
            query = "DELETE FROM trabaja WHERE ref_empleado = ? AND ref_proyecto  = ?";
            //Try-with-resources que se encarga de cerrar el PreparedStatement se lance o no una excepcion
            try(PreparedStatement pstm = this.getConexion().prepareStatement(query)){
                pstm.setString(1, emp);
                pstm.setInt(2, idProy);
                pstm.execute();
            }
        }
        
    }
    
    /**
     * Este metodo se encarga de eliminar un empleado en funcion de su NIF.
     * @param NifEmpleado
     * @throws SQLException La posible excepcion se controla desde el controlador
     */
    public void eliminarEmpleado(String NifEmpleado) throws SQLException{
        
        //Try-with-resources que se encarga de cerrar el PreparedStatement se lance o no una excepcion
        try(PreparedStatement pstm = this.getConexion().prepareStatement("DELETE FROM empleado WHERE NIF = ?")){
            pstm.setString(1, NifEmpleado);
            pstm.execute();
        }
       
    }
    
    /**
     * Este metodo se encarga de eliminar un empleado en funcion de su id.
     * @param idProy
     * @throws SQLException La posible excepcion se controla desde el controlador
     */
    public void eliminarProyecto(int idProy) throws SQLException{
        
        try(PreparedStatement pstm = this.getConexion().prepareStatement("DELETE FROM proyecto WHERE id_proyecto = ?")){
            pstm.setInt(1, idProy);
            pstm.execute();
        }
    }
    
    /**
     * Metodo que comprueba si hay registros de proyectos en la Base de Datos.
     * @return <ul>
    *   <li>True: en caso de que el resultSet este vacio, es decir, NO haya registros</li>
    *   <li>False: en caso de que el resultSet SI encuentre registros</li>
    * </ul>
     */
    public boolean isProyectoEmpty(){
        
        //Llama a la vista "comprobarProyectoVacio"
        try(PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM comprobarProyectoVacio");
            ResultSet rset = pstm.executeQuery()){
                if (rset.first()) {
                    return false;
                }else {
                    return true;
                }
                    
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return true;
        }
    
    }
    
    /**
     * Este metodo lee todos los proyectos de la Base de Datos y devuelve una lista a servir como modelo del comboBox de la vista "Asociar"
     * @param titulo
     * @param fechaInicio
     * @param fechaFin
     * @param id
     * @param descripcion
     * @return ArrayList de proyectos donde cada uno representa un registro proyecto de la Base de Datos.
     */
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
    
    /**
     * Se encarga de realizar "updates" sobre empleados en funcion de su NIF.
     * @param nombre
     * @param apellido
     * @param anoNacimiento
     * @param Nif
     * @return <ul>
    *   <li>True: en caso de que se haya realizado correctamente la modificacion</li>
    *   <li>False: en caso de error, ya sea al validar datos o al introducirlos en la Base de Datos</li>
    * </ul>
     * @throws SQLException La posible excepcion se controla desde el controlador
     */
    public boolean modificarEmpleado(String nombre, String apellido, Year anoNacimiento, String Nif) throws SQLException{
        
        nombre = nombre.trim();
        apellido = apellido.trim();
        Nif = Nif.trim();
        
        if (!validarDatosEmpleado(nombre, apellido, anoNacimiento, Nif)) {
            return false;
        }
        
        //Try-with-resources que se encarga de cerrar el CallableStatement, ya que implementa la interfaz AutoClosable, se lance o no una excepcion
        try(CallableStatement pstm = this.getConexion().prepareCall("{call doUpdateEmpleado (?, ?, ?, ?)}")){
            pstm.setString(1, nombre);
            pstm.setString(2, apellido);
            pstm.setInt(3, Integer.parseInt(anoNacimiento.toString()));
            pstm.setString(4, Nif);

            pstm.execute();
        }
        
        return true;
    }
    
    /**
     * Se encarga de realizar "updates" sobre proyectos en funcion de su id.
     * @param titulo
     * @param fechaInicio
     * @param fechaFin
     * @param id
     * @param descripcion
     * @return <ul>
    *   <li>True: en caso de que se haya realizado correctamente la modificacion</li>
    *   <li>False: en caso de error, ya sea al validar datos o al introducirlos en la Base de Datos</li>
    * </ul>
     * @throws SQLException La posible excepcion se controla desde el controlador
     */
    public boolean modificarProyecto(String titulo, LocalDate fechaInicio, LocalDate fechaFin, int id, String descripcion) throws SQLException{
        
        titulo = titulo.trim();
        
        if (!validarDatosProyecto(titulo, fechaInicio, fechaFin, descripcion)) {
            return false;
        }
    
        //Try-with-resources que se encarga de cerrar el CallableStatement se lance o no una excepcion
        try(CallableStatement pstm = this.getConexion().prepareCall("{call doUpdateProyecto(?, ?, ?, ?, ?)}")){
            pstm.setString(1, titulo);
            pstm.setString(2, fechaInicio.toString());
            pstm.setString(3, fechaFin.toString());
            pstm.setInt(4, id);
            pstm.setString(5, descripcion);

            pstm.execute();
        }

        return true;
    }

    /**
     * Este metodo devuelve el modelo de una tabla fruto de la consulta a la tabla con los empleados de la Base de Datos en funcion de los parametros. En caso de que todos los "checks" sean "false", el resultado sera el mismo a que todos fueran "true"
     * @param nombreChecked Si es "true", se omitirá esta columna
     * @param apellidoChecked Si es "true", se omitirá esta columna
     * @param ano_NacimientoChecked Si es "true", se omitirá esta columna
     * @param NIFChecked Si es "true", se omitirá esta columna
     * @param nombre Si no esta vacio, filtra segun este parametro en la respectiva columna 
     * @param apellido Si no esta vacio, filtra segun este parametro en la respectiva columna
     * @param ano_nacimiento Si no esta vacio, filtra segun este parametro en la respectiva columna
     * @param NIF Si no esta vacio, filtra segun este parametro en la respectiva columna
     * @return Devuelve un DefaultTableModel segun la info introducida en los parametros
     */
   public DefaultTableModel getTablaEmpleado(boolean nombreChecked, boolean apellidoChecked, boolean ano_NacimientoChecked, boolean NIFChecked, String nombre, String apellido, String ano_nacimiento, String NIF){
        
        DefaultTableModel tableModel;
        ResultSet rSet;
        String query = "";
        String whr = "";
        String[] nombreColumnas;
    
            /*------------------------ Columnas a obtener - ColumnNames del TableModel ------------------------*/
        if ((nombreChecked == true && apellidoChecked == true && ano_NacimientoChecked == true && NIFChecked == true) || (nombreChecked == false && apellidoChecked == false && ano_NacimientoChecked == false && NIFChecked == false)) {
            nombreColumnas = new String[] {"nombre", "apellido", "ano_nacimiento", "NIF"};
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
            
            try {
                
                Statement sentencia = this.getConexion().createStatement();
                rSet = sentencia.executeQuery(query);
                
                String[] fila = new String[4];
                while (rSet.next()){

                    fila[0] = rSet.getString(1);
                    fila[1] = rSet.getString(2);
                    fila[2] = String.valueOf(LocalDate.parse(rSet.getString(3)).getYear());
                    fila[3] = rSet.getString(4);
                    tableModel.addRow(fila);
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
 
        }else{
           
            
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
                    }
                   
                }else{
                    whr = whr + " WHERE " + a + " LIKE '" + itVariables.next() + "%'" ;
                }
 
                select = select + a;
                
                first = false;
            }
            
            query = select + " FROM empleado " + whr;

            
            PreparedStatement pstm = null;
            
            try {
                pstm = this.getConexion().prepareStatement(query);
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
            }finally{
                try {
                    pstm.close();
                } catch (SQLException e) {
                }
                
            }
             
        }

        return tableModel;
    }
    
   /**
    * Este metodo devuelve el modelo de una tabla fruto de la consulta a la tabla con los proyectos de la Base de Datos en funcion de los parametros. En caso de que todos los "checks" sean "false", el resultado sera el mismo a que todos fueran "true"
    * @param tituloChecked Si es "true", se omitirá esta columna
    * @param fechaInicioChecked Si es "true", se omitirá esta columna
    * @param fechaFinChecked Si es "true", se omitirá esta columna
    * @param idChecked Si es "true", se omitirá esta columna
    * @param descripcionChecked Si es "true", se omitirá esta columna
    * @param titulo Si no esta vacio, filtra segun este parametro en la respectiva columna
    * @param fechaInicio Si no esta vacio, filtra segun este parametro en la respectiva columna
    * @param fechaFin Si no esta vacio, filtra segun este parametro en la respectiva columna
    * @param id Si no esta vacio, filtra segun este parametro en la respectiva columna
    * @param descripcion Si no esta vacio, filtra segun este parametro en la respectiva columna
    * @return Devuelve un DefaultTableModel segun la info introducida en los parametros
    */
   public DefaultTableModel getTablaProyecto(boolean tituloChecked, boolean fechaInicioChecked, boolean fechaFinChecked, boolean idChecked, boolean descripcionChecked, String titulo, String fechaInicio, String fechaFin, String id, String descripcion){
        DefaultTableModel tableModel;
        ResultSet rSet;
        String query = "";
        String whr = "";
        String[] nombreColumnas;
    
            /*------------------------ Columnas a obtener - ColumnNames del TableModel ------------------------*/
        if ((tituloChecked == true && fechaInicioChecked == true && fechaFinChecked == true && idChecked == true && descripcionChecked == true) || (tituloChecked == false && fechaInicioChecked == false && fechaFinChecked == false && idChecked == false && descripcionChecked == false)) {
            nombreColumnas = new String[] {"titulo", "fecha_inicio", "fecha_fin", "id_proyecto", "descripcion"};
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
                    }
                   
                }else{
                    whr = whr + " WHERE " + a + " LIKE '" + itVariables.next() + "%'" ;
                }
 
                select = select + a;
                
                first = false;
            }
            
            query = select + " FROM proyecto " + whr;

            
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
    
   /**
    * Este metodo devuelve el modelo de una tabla que contiene los proyectos asociados a un empleado.
    * @param id Clave del empleado
    * @return Un DefaultTableModel con la informacion de todos los proyectos asociados a un empleado
    * @throws SQLException La posible excepcion se controla desde el controlador
    */
   public DefaultTableModel getProyectosAsocidos(String id) throws SQLException{
       
        DefaultTableModel tableModel = null;
        String query = "SELECT titulo, fecha_inicio, fecha_fin, id_proyecto, descripcion FROM proyecto WHERE id_proyecto IN (SELECT ref_proyecto FROM trabaja WHERE ref_empleado = ?)";
       
        try(PreparedStatement pstm = this.getConexion().prepareCall(query)){
            pstm.setString(1, id);
            try(ResultSet rSet = pstm.executeQuery()){
                tableModel = new DefaultTableModel(null, new String[]{"Titulo", "Fecha inicio", "Fecha fin", "ID", "Descripcion"});
                String[] fila = new String[tableModel.getColumnCount()];
                while (rSet.next()) {           
                    fila[0] = rSet.getString(1);
                    fila[1] = rSet.getString(2);
                    fila[2] = rSet.getString(3);
                    fila[3] = rSet.getString(4);
                    fila[4] = rSet.getString(5);
                    tableModel.addRow(fila);
                }
            }
        }
       return tableModel;
   }
   
   /**
    * Este metodo devuelve el modelo de una tabla que contiene los empleados asociados a un proyecto.
    * @param id Clave del proyecto
    * @return Un DefaultTableModel con la informacion de todos los empleados asociados a un proyecto
    * @throws SQLException La posible excepcion se controla desde el controlador
    */
   public DefaultTableModel getEmpleadosAsocidos(int id) throws SQLException{
       
       DefaultTableModel tableModel = null;
       String query = "SELECT * FROM empleado WHERE NIF IN (SELECT ref_empleado FROM trabaja WHERE ref_proyecto = ?)";
       
       try(PreparedStatement pstm = this.getConexion().prepareCall(query)){
            pstm.setInt(1, id);
            try(ResultSet rSet = pstm.executeQuery()){

                tableModel = new DefaultTableModel(null, new String[]{"Nombre", "Apellido", "Año nacimiento", "NIF"}){
                     @Override
                     public boolean isCellEditable(int row, int column){return false;}};

                String[] fila = new String[tableModel.getColumnCount()];
                while (rSet.next()) {           
                    fila[0] = rSet.getString(1);
                    fila[1] = rSet.getString(2);
                    fila[2] = String.valueOf(LocalDate.parse(rSet.getString(3)).getYear());
                    fila[3] = rSet.getString(4);
                    tableModel.addRow(fila);
                }
            }
        }
       
       return tableModel;
   }
   
   /**
    * Este metodo se encarga de llamar al procedimiento "edad" dela Base de Datos para conseguir los titulos proyectos con la mayor y menor media de edad
    * @return Devuelve un Array de tipo String de longitud dos. El primer String es el titulo del proyecto con mayor media de edad y el segundo el titulo del proyecto con menor media
    * @throws SQLException La posible excepcion se controla desde el controlador
    */
   public String[] datosEdadProyectos()throws SQLException{
       
       String[] resultado = new String[2];
       
       try(CallableStatement pstm = this.getConexion().prepareCall("{call edad(?, ?)}")){
            pstm.registerOutParameter(1, Types.VARCHAR);
            pstm.registerOutParameter(2, Types.VARCHAR);
            pstm.execute();

            try(ResultSet rSet = pstm.getResultSet()){

                while (rSet.next()) {           
                    System.out.println(rSet.toString());
                }

                resultado[0] = pstm.getString(1);
                resultado[1] = pstm.getString(2);
            }
       }
       return resultado;
       
   }
   
   /**
    * Este metodo se encarga de leer las propiedades del archivo cfg y asignarlas al objeto al objeto database. La contrasena no se almacena en el archivo. La ha de introducir el usuario.
    * @return <ul>
    *   <li>True: en caso de leer correctamente el archivo</li>
    *   <li>False: en caso de errar al leer el archivo</li>
    * </ul>
    */
    public boolean loadConexionDefault(){
        
            boolean flag = false;

            Properties cfg = new Properties();

            FileInputStream fis = null;

        try{
            fis = new FileInputStream("lib/cfg/conexion.properties");

            cfg.load(fis);

            this.setUrl(cfg.getProperty("ip"));
            this.setDb(cfg.getProperty("db"));
            this.setUser(cfg.getProperty("user"));

            flag = true;
        }catch (IOException ex){
            System.out.println("No se podido leer la configuracion de la conexion");
            System.err.println(ex.getMessage());
            return  false;
        }finally{
            try {
                fis.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        
        return flag;
    }
    
    /**
     * Este metodo modifica las propiedades del archivo cfg segun los parametros pasado al metodo. La idea tras este metodo es que en caso de establecer correctamente una conexion, se modiquen las propiedades de conexion a las nuevas. Solo en caso de una correcta conexion.
     * @param ip
     * @param db
     * @param usuario
     * @return <ul>
    *   <li>True: en caso de escribir correctamente el archivo</li>
    *   <li>False: en caso de errar al escribir el archivo</li>
    * </ul>
     */
    public boolean storeConexionDefault(String ip, String db, String usuario){
        
                    
        FileOutputStream fos = null;
        
        Properties cfg = new Properties();
        
        try {
            fos = new FileOutputStream("lib/cfg/conexion.properties");
            
            cfg.setProperty("ip", ip);
            cfg.setProperty("db", db);
            cfg.setProperty("user", usuario);
            
            cfg.store(fos, "Cambio valores cfg");
            
            return true;
            
        } catch (IOException ex) {
            System.out.println("No se podido leer la configuracion de la conexion");
            System.err.println(ex.getMessage());
            return  false;
        }finally{
            try {
                fos.close();
            } catch (IOException exc) {
                System.err.println(exc.getMessage());
            }
        }
        
    }
    
}
