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
import javax.swing.JOptionPane;
import modelo.Modelo;
import vista.Interfaz_Admin;

/**
 *
 * @author anaco
 */
public class Controlador_Modificar_Admin extends Controlador implements ControladorInterfaz, ActionListener{

    public Controlador_Modificar_Admin(Interfaz_Admin vista, Modelo modelo) {
        super(vista, modelo);
    }

    @Override
    public void iniciar() {
        
            /*------------------------------ Modificar Empleado ------------------------------*/
        this.vista.menuItem_Modificar_Empleado.setActionCommand( "vista_Modificar_Empleado" );
        this.vista.menuItem_Modificar_Empleado.addActionListener(this);
        
        //Carga por defecto el jTable del panel "panel_Modificar_Empleado" con todos los registros de la tabla empleado
        this.vista.tabla_Modificar_Empleado.setModel(modelo.getTablaEmpleado(true, true, true, true, "", "", "", ""));
        
        this.vista.btn_Filtrar_Modificar_Empleado.setActionCommand( "accion_Btn_Filtrar_Modificar_Empleado" );
        this.vista.btn_Filtrar_Modificar_Empleado.addActionListener(this);
        
        this.vista.btn_Modificar_Modificar_Empleado.setActionCommand( "accion_Btn_Modificar_Modificar_Empleado" );
        this.vista.btn_Modificar_Modificar_Empleado.addActionListener(this);
        
        this.vista.btn_Eliminar_Modificar_Empleado.setActionCommand( "accion_Btn_Eliminar_Modificar_Empleado" );
        this.vista.btn_Eliminar_Modificar_Empleado.addActionListener(this);
        
        this.vista.btn_Cancelar_Modificar_Empleado.setActionCommand( "accion_Btn_Cancelar_Modificar_Empleado" );
        this.vista.btn_Cancelar_Modificar_Empleado.addActionListener(this);
        
        //Coloca unos placeHolder a los jTextField del filtrado
        this.vista.placeHolder_FiltrarNombre_Modificar_Empleado = new TextPrompt("Nombre", this.vista.txt_FiltrarNombre_Modificar_Empleado, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNombre_Modificar_Empleado.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNombre_Modificar_Empleado.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarApellido_Modificar_Empleado = new TextPrompt("Apellido", this.vista.txt_FiltrarApellido_Modificar_Empleado, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarApellido_Modificar_Empleado.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarApellido_Modificar_Empleado.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarNacimiento_Modificar_Empleado = new TextPrompt("Ano nacimiento", this.vista.txt_FiltrarNacimiento_Modificar_Empleado, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNacimiento_Modificar_Empleado.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNacimiento_Modificar_Empleado.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarNIF_Modificar_Empleado = new TextPrompt("NIF", this.vista.txt_FiltrarNIF_Modificar_Empleado, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNIF_Modificar_Empleado.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNIF_Modificar_Empleado.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
            /*------------------------------ Modificar Proyecto ------------------------------*/
        
        this.vista.menuItem_Modificar_Proyecto.setActionCommand("vista_Modificar_Proyecto");
        this.vista.menuItem_Modificar_Proyecto.addActionListener(this);
        
        this.vista.tabla_Modificar_Proyecto.setModel(modelo.getTablaProyecto(true, true, true, true, true, "", "", "", "", ""));
        
        this.vista.btn_Filtrar_Modificar_Proyecto.setActionCommand( "accion_Btn_Filtrar_Modificar_Proyecto" );
        this.vista.btn_Filtrar_Modificar_Proyecto.addActionListener(this);
        
        this.vista.btn_ModificarDescripcion_Modificar_Proyecto.setActionCommand( "accion_Btn_ModificarDescripcion_Modificar_Proyecto" );
        this.vista.btn_ModificarDescripcion_Modificar_Proyecto.addActionListener(this);
        
        this.vista.btn_Modificar_Modificar_Proyecto.setActionCommand( "accion_Btn_Modificar_Modificar_Proyecto" );
        this.vista.btn_Modificar_Modificar_Proyecto.addActionListener(this);
        
        this.vista.btn_Eliminar_Modificar_Proyecto.setActionCommand( "accion_Btn_Eliminar_Modificar_Proyecto" );
        this.vista.btn_Eliminar_Modificar_Proyecto.addActionListener(this);
        
        this.vista.btn_Cancelar_Modificar_Proyecto.setActionCommand( "accion_Btn_Cancelar_Modificar_Proyecto" );
        this.vista.btn_Cancelar_Modificar_Proyecto.addActionListener(this);
        
        this.vista.placeHolder_FiltrarTitulo_Modificar_Proyecto = new TextPrompt("Titulo", this.vista.txt_FiltrarTitulo_Modificar_Proyecto, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarTitulo_Modificar_Proyecto.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarTitulo_Modificar_Proyecto.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarFechaInicio_Modificar_Proyecto = new TextPrompt("Fecha inicio", this.vista.txt_FiltrarFechaInicio_Modificar_Proyecto, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarFechaInicio_Modificar_Proyecto.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarFechaInicio_Modificar_Proyecto.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarFechaFin_Modificar_Proyecto = new TextPrompt("Fecha Fin", this.vista.txt_FiltrarFechaFin_Modificar_Proyecto, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarFechaFin_Modificar_Proyecto.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarFechaFin_Modificar_Proyecto.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarId_Modificar_Proyecto = new TextPrompt("Id", this.vista.txt_FiltrarId_Modificar_Proyecto, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarId_Modificar_Proyecto.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarId_Modificar_Proyecto.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarDescripcion_Modificar_Proyecto = new TextPrompt("Palabra clave Descripcion", this.vista.txt_FiltrarDescripcion_Modificar_Proyecto, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarDescripcion_Modificar_Proyecto.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarDescripcion_Modificar_Proyecto.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch ( e.getActionCommand() ){
        
                /*------------------------------ Modificar Empleado ------------------------------*/
            
            case "vista_Modificar_Empleado":
                switchPanels(this.vista.panel_Modificar_Empleado);
                break;
                
            case "accion_Btn_Filtrar_Modificar_Empleado":
                
                this.vista.tabla_Modificar_Empleado.setModel(modelo.getTablaEmpleado(true, true, true, true,
                        this.vista.txt_FiltrarNombre_Modificar_Empleado.getText(),
                        this.vista.txt_FiltrarApellido_Modificar_Empleado.getText(), 
                        this.vista.txt_FiltrarNacimiento_Modificar_Empleado.getText(),
                        this.vista.txt_FiltrarNIF_Modificar_Empleado.getText()));
                break;
                
            case "accion_Btn_Modificar_Modificar_Empleado":
                JOptionPane.showMessageDialog(null, "ok6");
                break;
                
            case "accion_Btn_Eliminar_Modificar_Empleado":
                JOptionPane.showMessageDialog(null, "ok6.5");
                break;
                
            case "accion_Btn_Cancelar_Modificar_Empleado":
                JOptionPane.showMessageDialog(null, "ok7");
                break;
                
                /*------------------------------ Modificar Proyecto ------------------------------*/
                
            case "vista_Modificar_Proyecto":
                switchPanels(this.vista.panel_Modificar_Proyecto);
                break;
                
            case "accion_Btn_Filtrar_Modificar_Proyecto":
                this.vista.tabla_Modificar_Proyecto.setModel(modelo.getTablaProyecto(true, true, true, true, true,
                        this.vista.txt_FiltrarTitulo_Modificar_Proyecto.getText(),
                        this.vista.txt_FiltrarFechaInicio_Modificar_Proyecto.getText(), 
                        this.vista.txt_FiltrarFechaFin_Modificar_Proyecto.getText(),
                        this.vista.txt_FiltrarId_Modificar_Proyecto.getText(),
                        this.vista.txt_FiltrarDescripcion_Modificar_Proyecto.getText()));
                break;
               
            
            case "accion_Btn_ModificarDescripcion_Modificar_Proyecto":
                JOptionPane.showMessageDialog(null, "ok9");
                break;
                
            case "accion_Btn_Modificar_Modificar_Proyecto":
                JOptionPane.showMessageDialog(null, "ok10");
                break;
                
            case "accion_Btn_Eliminar_Modificar_Proyecto":
                JOptionPane.showMessageDialog(null, "ok10.5");
                break;
                
            case "accion_Btn_Cancelar_Modificar_Proyecto":
                JOptionPane.showMessageDialog(null, "ok11");
                break;
        }
                
    }
    
    
    
}
