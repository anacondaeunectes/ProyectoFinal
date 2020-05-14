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
        
            /*------------------------------ Consultar ProyectodeEmpleado ------------------------------*/
            
        this.vista.menuItem_ProyectoDeEmpleado_Consultar.setActionCommand("vista_ProyectoDeEmpleado_Consultar");
        this.vista.menuItem_ProyectoDeEmpleado_Consultar.addActionListener(this);
        
        this.vista.btn_Filtrar_ProyectoDeEmpleado_Consultar.setActionCommand("accion_Btn_Filtrar_ProyectoDeEmpleado_Consultar");
        this.vista.btn_Filtrar_ProyectoDeEmpleado_Consultar.addActionListener(this);
        
        this.vista.placeHolder_FiltrarNombre_ProyectoDeEmpleado_Consultar = new TextPrompt("Nombre", this.vista.txt_FiltrarNombre_ProyectoDeEmpleado_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNombre_ProyectoDeEmpleado_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNombre_ProyectoDeEmpleado_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarApellido_ProyectoDeEmpleado_Consultar = new TextPrompt("Apellido", this.vista.txt_FiltrarApellido_ProyectoDeEmpleado_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarApellido_ProyectoDeEmpleado_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarApellido_ProyectoDeEmpleado_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarNacimiento_ProyectoDeEmpleado_Consultar = new TextPrompt("Ano nacimiento", this.vista.txt_FiltrarNacimiento_ProyectoDeEmpleado_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNacimiento_ProyectoDeEmpleado_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNacimiento_ProyectoDeEmpleado_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarNIF_ProyectoDeEmpleado_Consultar = new TextPrompt("NIF", this.vista.txt_FiltrarNIF_ProyectoDeEmpleado_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNIF_ProyectoDeEmpleado_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNIF_ProyectoDeEmpleado_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
            /*------------------------------ Consultar EmpleadoEnProyecto------------------------------*/
            
        this.vista.menuItem_EmpleadoEnProyecto_Consultar.setActionCommand("vista_EmpleadoEnProyecto_Consultar");
        this.vista.menuItem_EmpleadoEnProyecto_Consultar.addActionListener(this);
        
        this.vista.btn_Filtrar_EmpleadoEnProyecto_Consultar.setActionCommand("accion_Btn_Filtrar_EmpleadoEnProyecto_Consultar");
        this.vista.btn_Filtrar_EmpleadoEnProyecto_Consultar.addActionListener(this);
        
        this.vista.placeHolder_FiltrarTitulo_EmpleadoEnProyecto_Consultar = new TextPrompt("Titulo", this.vista.txt_FiltrarTitulo_EmpleadoEnProyecto_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarTitulo_EmpleadoEnProyecto_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarTitulo_EmpleadoEnProyecto_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarFechaInicio_EmpleadoEnProyecto_Consultar = new TextPrompt("Fecha inicio", this.vista.txt_FiltrarFechaInicio_EmpleadoEnProyecto_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarFechaInicio_EmpleadoEnProyecto_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarFechaInicio_EmpleadoEnProyecto_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarFechaFin_EmpleadoEnProyecto_Consultar = new TextPrompt("Fecha Fin", this.vista.txt_FiltrarFechaFin_EmpleadoEnProyecto_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarFechaFin_EmpleadoEnProyecto_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarFechaFin_EmpleadoEnProyecto_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarId_EmpleadoEnProyecto_Consultar = new TextPrompt("Id", this.vista.txt_FiltrarId_EmpleadoEnProyecto_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarId_EmpleadoEnProyecto_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarId_EmpleadoEnProyecto_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarDescripcion_EmpleadoEnProyecto_Consultar = new TextPrompt("Palabra clave Descripcion", this.vista.txt_FiltrarDescripcion_EmpleadoEnProyecto_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarDescripcion_EmpleadoEnProyecto_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarDescripcion_EmpleadoEnProyecto_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
            /*------------------------------ Consultar ListaEmpleado ------------------------------*/
            
        this.vista.menuItem_ListaEmpleado_Consultar.setActionCommand("vista_ListaEmpleado_Consultar");
        this.vista.menuItem_ListaEmpleado_Consultar.addActionListener(this);
        
        this.vista.btn_Filtrar_ListaEmpleado_Consultar.setActionCommand("accion_Btn_Filtrar_ListaEmpleado_Consultar");
        this.vista.btn_Filtrar_ListaEmpleado_Consultar.addActionListener(this);
        
        this.vista.checkBox_Nombre_ListaEmpleado_Consultar.setActionCommand("accion_CheckBox_Nombre_ListaEmpleado_Consultar");
        this.vista.checkBox_Nombre_ListaEmpleado_Consultar.addActionListener(this);
        
        this.vista.checkBox_Apellido_ListaEmpleado_Consultar.setActionCommand("accion_CheckBox_Apellido_ListaEmpleado_Consultar");
        this.vista.checkBox_Apellido_ListaEmpleado_Consultar.addActionListener(this);
        
        this.vista.checkBox_Nacimiento_ListaEmpleado_Consultar.setActionCommand("accion_CheckBox_Nacimiento_ListaEmpleado_Consultar");
        this.vista.checkBox_Nacimiento_ListaEmpleado_Consultar.addActionListener(this);
        
        this.vista.checkBox_NIF_ListaEmpleado_Consultar.setActionCommand("accion_CheckBox_NIF_ListaEmpleado_Consultar");
        this.vista.checkBox_NIF_ListaEmpleado_Consultar.addActionListener(this);
        
        this.vista.placeHolder_FiltrarNombre_ListaEmpleado_Consultar = new TextPrompt("Nombre", this.vista.txt_FiltrarNombre_ListaEmpleado_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNombre_ListaEmpleado_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNombre_ListaEmpleado_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarApellido_ListaEmpleado_Consultar = new TextPrompt("Apellido", this.vista.txt_FiltrarApellido_ListaEmpleado_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarApellido_ListaEmpleado_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarApellido_ListaEmpleado_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarNacimiento_ListaEmpleado_Consultar = new TextPrompt("Ano nacimiento", this.vista.txt_FiltrarNacimiento_ListaEmpleado_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNacimiento_ListaEmpleado_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNacimiento_ListaEmpleado_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarNIF_ListaEmpleado_Consultar = new TextPrompt("NIF", this.vista.txt_FiltrarNIF_ListaEmpleado_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNIF_ListaEmpleado_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNIF_ListaEmpleado_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
            /*------------------------------ Consultar ListaProyecto ------------------------------*/
            
        this.vista.menuItem_ListaProyecto_Consultar.setActionCommand("vista_ListaProyecto_Consultar");
        this.vista.menuItem_ListaProyecto_Consultar.addActionListener(this);
        
        this.vista.btn_Filtrar_ListaProyecto_Consultar.setActionCommand("accion_Btn_Filtrar_ListaProyecto_Consultar");
        this.vista.btn_Filtrar_ListaProyecto_Consultar.addActionListener(this);
        
        this.vista.checkBox_Titulo_ListaProyecto_Consultar.setActionCommand("accion_CheckBox_Titulo_ListaProyecto_Consultar");
        this.vista.checkBox_Titulo_ListaProyecto_Consultar.addActionListener(this);
        
        this.vista.checkBox_FechaInicio_ListaProyecto_Consultar.setActionCommand("accion_CheckBox_FechaInicio_ListaProyecto_Consultar");
        this.vista.checkBox_FechaInicio_ListaProyecto_Consultar.addActionListener(this);
        
        this.vista.checkBox_FechaFin_ListaProyecto_Consultar.setActionCommand("accion_CheckBox_FechaFin_ListaProyecto_Consultar");
        this.vista.checkBox_FechaFin_ListaProyecto_Consultar.addActionListener(this);
        
        this.vista.checkBox_Id_ListaProyecto_Consultar.setActionCommand("accion_CheckBox_Id_ListaProyecto_Consultar");
        this.vista.checkBox_Id_ListaProyecto_Consultar.addActionListener(this);
        
        this.vista.checkBox_Descripcion_ListaProyecto_Consultar.setActionCommand("accion_CheckBox_Descripcion_ListaProyecto_Consultar");
        this.vista.checkBox_Descripcion_ListaProyecto_Consultar.addActionListener(this);
        
        this.vista.placeHolder_FiltrarTitulo_ListaProyecto_Consultar = new TextPrompt("Titulo", this.vista.txt_FiltrarTitulo_ListaProyecto_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarTitulo_ListaProyecto_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarTitulo_ListaProyecto_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarFechaInicio_ListaProyecto_Consultar = new TextPrompt("Fecha inicio", this.vista.txt_FiltrarFechaInicio_ListaProyecto_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarFechaInicio_ListaProyecto_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarFechaInicio_ListaProyecto_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarFechaFin_ListaProyecto_Consultar = new TextPrompt("Fecha Fin", this.vista.txt_FiltrarFechaFin_ListaProyecto_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarFechaFin_ListaProyecto_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarFechaFin_ListaProyecto_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarId_ListaProyecto_Consultar = new TextPrompt("Id", this.vista.txt_FiltrarID_ListaProyecto_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarId_ListaProyecto_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarId_ListaProyecto_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarDescripcion_ListaProyecto_Consultar = new TextPrompt("Palabra clave Descripcion", this.vista.txt_FiltrarDescripcion_ListaProyecto_Consultar, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarDescripcion_ListaProyecto_Consultar.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarDescripcion_ListaProyecto_Consultar.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch ( e.getActionCommand() ){
            
               /*------------------------------ Consultar ProyectodeEmpleado ------------------------------*/
                
            case "vista_ProyectoDeEmpleado_Consultar":
                switchPanels(this.vista.panel_ProyectoDeEmpleado_Consultar);
                break;
                
            case "accion_Btn_Filtrar_ProyectoDeEmpleado_Consultar":
                this.vista.tabla_ProyectoDeEmpleado_Consultar.setModel(modelo.getTablaEmpleado(true, true, true, true,
                    this.vista.txt_FiltrarNombre_ProyectoDeEmpleado_Consultar.getText(),
                    this.vista.txt_FiltrarApellido_ProyectoDeEmpleado_Consultar.getText(),
                    this.vista.txt_FiltrarNacimiento_ProyectoDeEmpleado_Consultar.getText(),
                    this.vista.txt_FiltrarNIF_ProyectoDeEmpleado_Consultar.getText()));
                break;
        
                /*------------------------------ Consultar EmpleadoEnProyecto------------------------------*/
            
            case "vista_EmpleadoEnProyecto_Consultar":
                switchPanels(this.vista.panel_EmpleadoEnProyecto_Consultar);
                break;
                
            case "accion_Btn_Filtrar_EmpleadoEnProyecto_Consultar":
                System.out.println("hhhh");
                this.vista.tabla_EmpleadoEnProyecto_Consultar.setModel(modelo.getTablaProyecto(true, true, true, true, true,
                        this.vista.txt_FiltrarTitulo_EmpleadoEnProyecto_Consultar.getText(),
                        this.vista.txt_FiltrarFechaInicio_EmpleadoEnProyecto_Consultar.getText(),
                        this.vista.txt_FiltrarFechaFin_EmpleadoEnProyecto_Consultar.getText(),
                        this.vista.txt_FiltrarId_EmpleadoEnProyecto_Consultar.getText(),
                        this.vista.txt_FiltrarDescripcion_EmpleadoEnProyecto_Consultar.getText()));
                break;
                
                /*------------------------------ Consultar ListaEmpleado ------------------------------*/
                
            case "vista_ListaEmpleado_Consultar":
                switchPanels(this.vista.panel_ListaEmpleado_Consultar);
                break;
                
            case "accion_Btn_Filtrar_ListaEmpleado_Consultar":
            System.out.println("hhhh");
            this.vista.tabla_ListaEmpleado_Consultar.setModel(modelo.getTablaEmpleado(this.vista.checkBox_Nombre_ListaEmpleado_Consultar.isSelected(),
                    this.vista.checkBox_Apellido_ListaEmpleado_Consultar.isSelected(),
                    this.vista.checkBox_Nacimiento_ListaEmpleado_Consultar.isSelected(),
                    this.vista.checkBox_NIF_ListaEmpleado_Consultar.isSelected(),
                    this.vista.txt_FiltrarNombre_ListaEmpleado_Consultar.getText(),
                    this.vista.txt_FiltrarApellido_ListaEmpleado_Consultar.getText(),
                    this.vista.txt_FiltrarNacimiento_ListaEmpleado_Consultar.getText(),
                    this.vista.txt_FiltrarNIF_ListaEmpleado_Consultar.getText()));
            break;
                
            case "accion_CheckBox_Nombre_ListaEmpleado_Consultar":
                this.vista.txt_FiltrarNombre_ListaEmpleado_Consultar.setEnabled(this.vista.checkBox_Nombre_ListaEmpleado_Consultar.isSelected()? true : false);
                break;
                
            case "accion_CheckBox_Apellido_ListaEmpleado_Consultar":
                this.vista.txt_FiltrarApellido_ListaEmpleado_Consultar.setEnabled(this.vista.checkBox_Apellido_ListaEmpleado_Consultar.isSelected()? true : false);
                break;
                
            case "accion_CheckBox_Nacimiento_ListaEmpleado_Consultar":
                this.vista.txt_FiltrarNacimiento_ListaEmpleado_Consultar.setEnabled(this.vista.checkBox_Nacimiento_ListaEmpleado_Consultar.isSelected()? true : false);
                break;
                
            case "accion_CheckBox_NIF_ListaEmpleado_Consultar":
                this.vista.txt_FiltrarNIF_ListaEmpleado_Consultar.setEnabled(this.vista.checkBox_NIF_ListaEmpleado_Consultar.isSelected()? true : false);
                break;
                
                /*------------------------------ Consultar ListaProyecto ------------------------------*/
                
            case "vista_ListaProyecto_Consultar":
                switchPanels(this.vista.panel_ListaProyecto_Consultar);
                break;
                
            case "accion_Btn_Filtrar_ListaProyecto_Consultar":
                this.vista.tabla_ListaProyecto_Consultar.setModel(modelo.getTablaProyecto(this.vista.checkBox_Titulo_ListaProyecto_Consultar.isSelected(),
                        this.vista.checkBox_FechaInicio_ListaProyecto_Consultar.isSelected(),
                        this.vista.checkBox_FechaFin_ListaProyecto_Consultar.isSelected(),
                        this.vista.checkBox_Id_ListaProyecto_Consultar.isSelected(),
                        this.vista.checkBox_Descripcion_ListaProyecto_Consultar.isSelected(),
                        this.vista.txt_FiltrarTitulo_ListaProyecto_Consultar.getText(),
                        this.vista.txt_FiltrarFechaInicio_ListaProyecto_Consultar.getText(),
                        this.vista.txt_FiltrarFechaFin_ListaProyecto_Consultar.getText(),
                        this.vista.txt_FiltrarID_ListaProyecto_Consultar.getText(),
                        this.vista.txt_FiltrarDescripcion_ListaProyecto_Consultar.getText()));
                break;
                
            case "accion_CheckBox_Titulo_ListaProyecto_Consultar":
                this.vista.txt_FiltrarTitulo_ListaProyecto_Consultar.setEnabled(this.vista.checkBox_Titulo_ListaProyecto_Consultar.isSelected()? true : false);
                break;
                
            case "accion_CheckBox_FechaInicio_ListaProyecto_Consultar":
                this.vista.txt_FiltrarFechaInicio_ListaProyecto_Consultar.setEnabled(this.vista.checkBox_FechaInicio_ListaProyecto_Consultar.isSelected()? true : false);
                break;
                
            case "accion_CheckBox_FechaFin_ListaProyecto_Consultar":
                this.vista.txt_FiltrarFechaFin_ListaProyecto_Consultar.setEnabled(this.vista.checkBox_FechaFin_ListaProyecto_Consultar.isSelected()? true : false);
                break;
                
            case "accion_CheckBox_Id_ListaProyecto_Consultar":
                this.vista.txt_FiltrarID_ListaProyecto_Consultar.setEnabled(this.vista.checkBox_Id_ListaProyecto_Consultar.isSelected()? true : false);
                break;
                
            case "accion_CheckBox_Descripcion_ListaProyecto_Consultar":
                this.vista.txt_FiltrarDescripcion_ListaProyecto_Consultar.setEnabled(this.vista.checkBox_Descripcion_ListaProyecto_Consultar.isSelected()? true : false);
                break;
        
        }
        
    }
    
}
