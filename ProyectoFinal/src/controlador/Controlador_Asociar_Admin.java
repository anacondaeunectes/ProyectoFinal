/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.TextPrompt;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;
import modelo.Modelo;
import modelo.Proyecto;
import vista.Interfaz_Admin;

/**
 * Controlador de la funcionalidad Modificar. Al igual que los demas hereda de la clase "Controlador" y, por tanto, implementa la interfaz "ControladorInterfaz"
 * @author anaco
 */
public class Controlador_Asociar_Admin extends Controlador implements ActionListener, KeyListener, MouseListener{
    
    ArrayList <Proyecto> List_ComboBox_Asociar = null;
    JTextComponent editor = null;

    public Controlador_Asociar_Admin(Interfaz_Admin vista, Modelo modelo) {
        super(vista, modelo);
    }

    /**
     * Inicia los componentes y anade los Listeners
     */
    @Override
    public void iniciar() {
            /*------------------------------ Vista y comun ------------------------------*/
        this.vista.menuItem_Asociar.setActionCommand("vista_Asociar");
        this.vista.menuItem_Asociar.addActionListener(this);
        
        this.vista.btn_Asociar_Asociar.setActionCommand( "accion_Btn_Asociar_Asociar" );
        this.vista.btn_Asociar_Asociar.addActionListener(this);
        
        /*------------------------------ Parte superior - Empleado ------------------------------*/
        
        this.vista.tabla_Empleado_Asociar.setModel(this.modelo.getTablaEmpleado(true, true, true, true, "", "", "", ""));
        
        this.vista.btn_FiltrarEmpleado_Asociar.setActionCommand( "accion_Btn_FiltrarEmpleado_Asociar" );
        this.vista.btn_FiltrarEmpleado_Asociar.addActionListener(this);
        
        //PlaceHolders
        this.vista.placeHolder_FiltrarNombre_Empleado_Asociar = new TextPrompt("Nombre", this.vista.txt_FiltrarNombre_Empleado_Asociar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNombre_Empleado_Asociar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNombre_Empleado_Asociar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarApellido_Empleado_Asociar = new TextPrompt("Apellido", this.vista.txt_FiltrarApellido_Empleado_Asociar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarApellido_Empleado_Asociar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarApellido_Empleado_Asociar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarNacimiento_Empleado_Asociar = new TextPrompt("Ano nacimiento", this.vista.txt_FiltrarNacimiento_Empleado_Asociar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNacimiento_Empleado_Asociar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNacimiento_Empleado_Asociar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarNIF_Empleado_Asociar = new TextPrompt("NIF", this.vista.txt_FiltrarNIF_Empleado_Asociar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNIF_Empleado_Asociar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNIF_Empleado_Asociar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        /*------------------------------ Parte inferior - Proyecto ------------------------------*/
        
        //Eti_ComboBox_Asociar
        this.vista.ComboBox_Asociar.getEditor().getEditorComponent().addKeyListener(this);
        this.vista.ComboBox_Asociar.getEditor().getEditorComponent().addMouseListener(this);
        
        this.vista.btn_FiltrarProyecto_Asociar.setActionCommand( "accion_Btn_FiltrarProyecto_Asociar" );
        this.vista.btn_FiltrarProyecto_Asociar.addActionListener(this);
        
        this.vista.placeHolder_FiltrarTitulo_Proyecto_Asociar = new TextPrompt("Titulo", this.vista.txt_FiltrarTitulo_Proyecto_Asociar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarTitulo_Proyecto_Asociar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarTitulo_Proyecto_Asociar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarFechaInicio_Proyecto_Asociar = new TextPrompt("Fecha inicio", this.vista.txt_FiltrarFechaInicio_Proyecto_Asociar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarFechaInicio_Proyecto_Asociar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarFechaInicio_Proyecto_Asociar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarFechaFin_Proyecto_Asociar = new TextPrompt("Fecha Fin", this.vista.txt_FiltrarFechaFin_Proyecto_Asociar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarFechaFin_Proyecto_Asociar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarFechaFin_Proyecto_Asociar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarId_Proyecto_Asociar = new TextPrompt("Id", this.vista.txt_FiltrarId_Proyecto_Asociar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarId_Proyecto_Asociar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarId_Proyecto_Asociar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarDescripcion_Proyecto_Asociar = new TextPrompt("Palabra clave Descripcion", this.vista.txt_FiltrarDescripcion_Proyecto_Asociar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarDescripcion_Proyecto_Asociar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarDescripcion_Proyecto_Asociar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch ( e.getActionCommand() ){
            
            //Cambia la vista y ademas, refresca el modelo del jComboBox por lo que si se cambiado de panel para agregar o modificar proyectos, al volver a este panel se reflejarian esos cambios
            case "vista_Asociar":
                switchPanels(this.vista.panel_Asociar);
                
                editor = (JTextComponent) this.vista.ComboBox_Asociar.getEditor().getEditorComponent();
                
                if (!modelo.isProyectoEmpty()) {
                    this.vista.ComboBox_Asociar.removeAllItems();
                    List_ComboBox_Asociar = modelo.cargarProyectos("", "", "", "", "");
                    //Primero ordena todos los proyectos y luego los inserta conservando ese orden.
                    List_ComboBox_Asociar.stream().sorted(Comparator.comparing(Proyecto::getTitulo)).forEach(x -> this.vista.ComboBox_Asociar.addItem(x));
                }else{
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ningun proyecto");
                }
         
                break;
            
            //Accion btn_FiltrarEmpleado_Asociar
            case "accion_Btn_FiltrarEmpleado_Asociar":
                this.vista.tabla_Empleado_Asociar.setModel(modelo.getTablaEmpleado(true, true, true, true,
                        this.vista.txt_FiltrarNombre_Empleado_Asociar.getText(),
                        this.vista.txt_FiltrarApellido_Empleado_Asociar.getText(), 
                        this.vista.txt_FiltrarNacimiento_Empleado_Asociar.getText(),
                        this.vista.txt_FiltrarNIF_Empleado_Asociar.getText()));
                break;
                
            //Accion btn_FiltrarProyecto_Asociar
            case "accion_Btn_FiltrarProyecto_Asociar":
                this.vista.ComboBox_Asociar.removeAllItems();
                List_ComboBox_Asociar = modelo.cargarProyectos(this.vista.txt_FiltrarTitulo_Proyecto_Asociar.getText(),
                    this.vista.txt_FiltrarFechaInicio_Proyecto_Asociar.getText(),
                    this.vista.txt_FiltrarFechaFin_Proyecto_Asociar.getText(),
                    this.vista.txt_FiltrarId_Proyecto_Asociar.getText(),
                    this.vista.txt_FiltrarDescripcion_Proyecto_Asociar.getText());
                //Primero ordena todos los proyectos y luego los inserta conservando ese orden.
                List_ComboBox_Asociar.stream().sorted(Comparator.comparing(Proyecto::getTitulo)).forEach(x -> this.vista.ComboBox_Asociar.addItem(x));
                break;
            
            //Accion btn_Asociar_Asociar
            case "accion_Btn_Asociar_Asociar":
                List <String> listEmpleados = new ArrayList<>();
                System.out.println("La tabla Asociar debería contener " + this.vista.tabla_Empleado_Asociar.getRowCount() + "filas");
                for (int i = 0; i < this.vista.tabla_Empleado_Asociar.getRowCount(); i++) {
                    
                    for (int j = 0; j < this.vista.tabla_Empleado_Asociar.getSelectedRows().length; j++) {
                     
                        if (i == this.vista.tabla_Empleado_Asociar.getSelectedRows()[j]) {
                            
                            listEmpleados.add( this.vista.tabla_Empleado_Asociar.getValueAt(i, 3).toString() );
                        }
                        
                    }  
                    
                }
                
                List <String> listEmpleadosRepes = new ArrayList<>();

                listEmpleados.stream().forEach(x -> {
                    try{
                        modelo.asociarEmpleadoProyecto(x, (Proyecto) this.vista.ComboBox_Asociar.getSelectedItem());
                    }catch(SQLException ex){
                        listEmpleadosRepes.add(x);
                    }
                });
                
                if (!listEmpleadosRepes.isEmpty()) {
                    String msg = "<html>No se han podido asociar los siguientes empleados: <br><ol>";
                    for (String NIFs : listEmpleadosRepes) {
                        msg = msg + "<li>" + NIFs + "</li>";
                    }
                    JOptionPane.showMessageDialog(vista, msg + "</ol>", "Adventencia", JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(vista, "Empleados asociados correctamente");
                }
                
                
                
                
                /*String[] empleadosSeleccionados = new String[listEmpleados.size()];
                
                for (int i = 0; i < empleadosSeleccionados.length; i++) {
                    empleadosSeleccionados[i] = listEmpleados.get(i);
                    System.out.println(empleadosSeleccionados[i]);
                }
                
                //Finalmente, tanto con el array que contiene la clave de los empleados seleccionados como con el objeto Proyecto elegido en el comboBox, se llama al metodo "asociarEmpleadoProyecto" el cual tira SQLException por lo que se engloba en un try/catch que, en caso de errar, muestra un jOptionPane con el mensaje de error.              
                try {
                    modelo.asociarEmpleadoProyecto(empleadosSeleccionados, (Proyecto) this.vista.ComboBox_Asociar.getSelectedItem());
                } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null, "sql: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
                  
                   
                }*/
        
                break;
                
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //El evento ha de reaccionar tras soltar la tecla ya que asi si que consigue recoger el ultimo caracter escrito. De otra forma, solo recogeria el los caracteres anteriores al ultima caracter escrito
        
        System.out.println("llega");
        String str = editor.getText();
        
        //Si se pulsa "Shift" o "Backspace" no se tiene en cuenta. Para el resto de teclas, sí que se lleva acabo el autocompletado y
        switch (e.getExtendedKeyCode()) {
            case 16:
                //Evita que al usar shift para escribir mayuscula se autoseleccione.
                System.out.println("shift");
                break;
            case 8:
                //Evita que se produzca el autoseleccione.
                System.out.println("backspace");
                break;
            default:
                //es necesario volver a ordenar este Stream para que el metodo findFirst() coja el primero.
                this.vista.ComboBox_Asociar.setSelectedItem(List_ComboBox_Asociar.stream().sorted(Comparator.comparing(Proyecto::getTitulo)).filter(x-> x.getTitulo().startsWith(str)).findFirst().get());
                String sel_Item_Text = this.vista.ComboBox_Asociar.getSelectedItem().toString();
                editor.setText(sel_Item_Text);
                editor.setSelectionStart(str.length());
                editor.setSelectionEnd(sel_Item_Text.length());
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Muestra el listado de items de este ComboBox
        System.out.println("Llega Mouse");
       this.vista.ComboBox_Asociar.showPopup();
       editor.select(0, editor.getText().length());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
