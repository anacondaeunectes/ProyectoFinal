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
import modelo.Modelo;
import vista.Interfaz_Admin;

/**
 *
 * @author anaco
 */
public class Controlador_Consultar_Admin extends Controlador implements ControladorInterfaz, ActionListener{

    public Controlador_Consultar_Admin(Interfaz_Admin vista, Modelo modelo) {
        super(vista, modelo);
    }

    @Override
    public void iniciar() {
        
            /*------------------------------ Consultar EmpleadoEnProyecto------------------------------*/
            
        this.vista.menuItem_EmpleadoEnProyecto_Consultar.setActionCommand("vista_EmpleadoEnProyecto_Consultar");
        this.vista.menuItem_EmpleadoEnProyecto_Consultar.addActionListener(this);
        
        this.vista.btn_Filtrar_EmpleadoEnProyecto_Consultar.setActionCommand("accion_Btn_Filtrar_EmpleadoEnProyecto_Consultar");
        this.vista.btn_Filtrar_EmpleadoEnProyecto_Consultar.addActionListener(this);
        
        this.vista.checkBox_Nombre_EmpleadoEnProyecto_Consultar.setActionCommand("accion_CheckBox_Nombre_EmpleadoEnProyecto_Consultar");
        this.vista.checkBox_Nombre_EmpleadoEnProyecto_Consultar.addActionListener(this);
        
        this.vista.checkBox_Apellido_EmpleadoEnProyecto_Consultar.setActionCommand("accion_CheckBox_Apellido_EmpleadoEnProyecto_Consultar");
        this.vista.checkBox_Apellido_EmpleadoEnProyecto_Consultar.addActionListener(this);
        
        this.vista.checkBox_Nacimiento_EmpleadoEnProyecto_Consultar.setActionCommand("accion_CheckBox_Nacimiento_EmpleadoEnProyecto_Consultar");
        this.vista.checkBox_Nacimiento_EmpleadoEnProyecto_Consultar.addActionListener(this);
        
        this.vista.checkBox_NIF_EmpleadoEnProyecto_Consultar.setActionCommand("accion_CheckBox_NIF_EmpleadoEnProyecto_Consultar");
        this.vista.checkBox_NIF_EmpleadoEnProyecto_Consultar.addActionListener(this);
                
        this.vista.placeHolder_FiltrarNombre_EmpleadoEnProyecto_Consultar = new TextPrompt("Nombre", this.vista.txt_FiltrarNombre_EmpleadoEnProyecto_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNombre_EmpleadoEnProyecto_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNombre_EmpleadoEnProyecto_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarApellido_EmpleadoEnProyecto_Consultar = new TextPrompt("Apellido", this.vista.txt_FiltrarApellido_EmpleadoEnProyecto_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarApellido_EmpleadoEnProyecto_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarApellido_EmpleadoEnProyecto_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarNacimiento_EmpleadoEnProyecto_Consultar = new TextPrompt("Ano nacimiento", this.vista.txt_FiltrarNacimiento_EmpleadoEnProyecto_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNacimiento_EmpleadoEnProyecto_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNacimiento_EmpleadoEnProyecto_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarNIF_EmpleadoEnProyecto_Consultar = new TextPrompt("NIF", this.vista.txt_FiltrarNIF_EmpleadoEnProyecto_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNIF_EmpleadoEnProyecto_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNIF_EmpleadoEnProyecto_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
            /*------------------------------ Consultar ProyectodeEmpleado ------------------------------*/
            
        this.vista.menuItem_ProyectoDeEmpleado_Consultar.setActionCommand("vista_ProyectoDeEmpleado_Consultar");
        this.vista.menuItem_ProyectoDeEmpleado_Consultar.addActionListener(this);
        
            /*------------------------------ Consultar ListaEmpleado ------------------------------*/
            
        this.vista.menuItem_ListaEmpleado_Consultar.setActionCommand("vista_ListaEmpleado_Consultar");
        this.vista.menuItem_ListaEmpleado_Consultar.addActionListener(this);
        
            /*------------------------------ Consultar ListaProyecto ------------------------------*/
            
        this.vista.menuItem_ListaProyecto_Consultar.setActionCommand("vista_ListaProyecto_Consultar");
        this.vista.menuItem_ListaProyecto_Consultar.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch ( e.getActionCommand() ){
        
                /*------------------------------ Consultar EmpleadoEnProyecto------------------------------*/
            
            case "vista_EmpleadoEnProyecto_Consultar":
                switchPanels(this.vista.panel_EmpleadoEnProyecto_Consultar);
                break;
                
            case "accion_Btn_Filtrar_EmpleadoEnProyecto_Consultar":
                System.out.println("hhhh");
                this.vista.tabla_EmpleadoEnProyecto_Consultar.setModel(modelo.getTablaEmpleado(this.vista.checkBox_Nombre_EmpleadoEnProyecto_Consultar.isSelected(),
                        this.vista.checkBox_Apellido_EmpleadoEnProyecto_Consultar.isSelected(),
                        this.vista.checkBox_Nacimiento_EmpleadoEnProyecto_Consultar.isSelected(),
                        this.vista.checkBox_NIF_EmpleadoEnProyecto_Consultar.isSelected(),
                        this.vista.txt_FiltrarNombre_EmpleadoEnProyecto_Consultar.getText(),
                        this.vista.txt_FiltrarApellido_EmpleadoEnProyecto_Consultar.getText(),
                        this.vista.txt_FiltrarNacimiento_EmpleadoEnProyecto_Consultar.getText(),
                        this.vista.txt_FiltrarNIF_EmpleadoEnProyecto_Consultar.getText()));
                break;
                /*------------------------------ Consultar ProyectodeEmpleado ------------------------------*/
                
            case "vista_ProyectoDeEmpleado_Consultar":
                switchPanels(this.vista.panel_ProyectoDeEmpleado_Consultar);
                break;
                
                /*------------------------------ Consultar ListaEmpleado ------------------------------*/
                
            case "vista_ListaEmpleado_Consultar":
                switchPanels(this.vista.panel_ListaEmpleado_Consultar);
                break;
                
                /*------------------------------ Consultar ListaProyecto ------------------------------*/
                
            case "vista_ListaProyecto_Consultar":
                switchPanels(this.vista.panel_ListaProyecto_Consultar);
                break;
                
            case "accion_CheckBox_Nombre_EmpleadoEnProyecto_Consultar":
                this.vista.txt_FiltrarNombre_EmpleadoEnProyecto_Consultar.setEnabled(this.vista.checkBox_Nombre_EmpleadoEnProyecto_Consultar.isSelected()? true : false);
                break;
                
            case "accion_CheckBox_Apellido_EmpleadoEnProyecto_Consultar":
                this.vista.txt_FiltrarApellido_EmpleadoEnProyecto_Consultar.setEnabled(this.vista.checkBox_Apellido_EmpleadoEnProyecto_Consultar.isSelected()? true : false);
                break;
                
            case "accion_CheckBox_Nacimiento_EmpleadoEnProyecto_Consultar":
                this.vista.txt_FiltrarNacimiento_EmpleadoEnProyecto_Consultar.setEnabled(this.vista.checkBox_Nacimiento_EmpleadoEnProyecto_Consultar.isSelected()? true : false);
                break;
                
            case "accion_CheckBox_NIF_EmpleadoEnProyecto_Consultar":
                this.vista.txt_FiltrarNIF_EmpleadoEnProyecto_Consultar.setEnabled(this.vista.checkBox_NIF_EmpleadoEnProyecto_Consultar.isSelected()? true : false);
                break;
        
        }
        
    }
    
}
