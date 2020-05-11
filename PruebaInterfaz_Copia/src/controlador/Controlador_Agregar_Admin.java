/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import modelo.Modelo;
import vista.Interfaz_Admin;

/**
 *
 * @author anaco
 */
public class Controlador_Agregar_Admin extends Controlador implements ControladorInterfaz, ActionListener{
    
    public Controlador_Agregar_Admin(Interfaz_Admin vista, Modelo modelo) {
        super(vista, modelo);
    }

    @Override
    public void iniciar() {
        
            /*------------------------------ Agregar Empleado ------------------------------*/
        
        this.vista.menuItem_Agregar_Empleado.setActionCommand( "vista_Agregar_Empleado" );
        this.vista.menuItem_Agregar_Empleado.addActionListener(this);
        
        this.vista.btn_Agregar_Agregar_Empleado.setActionCommand( "accion_Btn_Agregar_Agregar_Empleado" );
        this.vista.btn_Agregar_Agregar_Empleado.addActionListener(this);
        
        this.vista.btn_Cancelar_Agregar_Empleado.setActionCommand( "accion_Btn_Cancelar_Agregar_Empleado" );
        this.vista.btn_Cancelar_Agregar_Empleado.addActionListener(this);
        
            /*------------------------------ Agregar Proyecto------------------------------*/
        
        this.vista.menuItem_Agregar_Proyecto.setActionCommand( "vista_Agregar_Proyecto" );
        this.vista.menuItem_Agregar_Proyecto.addActionListener(this);
        
        this.vista.btn_Agregar_Agregar_Proyecto.setActionCommand( "accion_Btn_Agregar_Agregar_Proyecto" );
        this.vista.btn_Agregar_Agregar_Proyecto.addActionListener(this);
        
        this.vista.btn_Cancelar_Agregar_Proyecto.setActionCommand( "accion_Btn_Cancelar_Agregar_Proyecto" );
        this.vista.btn_Cancelar_Agregar_Proyecto.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        switch ( e.getActionCommand() ){
            
                /*------------------------------ Agregar Empleado ------------------------------*/
            
            case "vista_Agregar_Empleado":
                switchPanels(this.vista.panel_Agregar_Empleado);
                break;
                
            case "accion_Btn_Agregar_Agregar_Empleado":
                if (this.modelo.agregarEmpleado(this.vista.Txt_Nombre_Agregar_Empleado.getText(),
                        this.vista.Txt_Apellido_Agregar_Empleado.getText(),
                        Integer.parseInt(this.vista.Txt_Nacimiento_Agregar_Empleado.getText()),
                        this.vista.Txt_Nif_Agregar_Empleado.getText())){
                    JOptionPane.showMessageDialog(null, "Registro de empleado introducido");
                }else{
                    JOptionPane.showMessageDialog(null, "ERROR - Se ha producido un error. Registro de empleado no introducido","Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
                
            case "accion_Btn_Cancelar_Agregar_Empleado":
                JOptionPane.showMessageDialog(null, "ok2");
                break;
                
                
                /*------------------------------ Agregar Empleado ------------------------------*/  
                
            case "vista_Agregar_Proyecto":
                switchPanels(this.vista.panel_Agregar_Proyecto)
                ;break;
            
            case "accion_Btn_Agregar_Agregar_Proyecto":
                if (this.modelo.agregarProyecto(LocalDate.parse(this.vista.Txt_FechaInicio_Agregar_Proyecto.getText()),
                        LocalDate.parse(this.vista.Txt_FechaFin_Agregar_Proyecto.getText()),
                        this.vista.Txt_Titulo_Agregar_Proyecto.getText(),
                        this.vista.Txt_Descripcion_Agregar_Proyecto.getText())){
                    JOptionPane.showMessageDialog(null, "Registro de proyecto introducido");
                }else{
                    JOptionPane.showMessageDialog(null, "ERROR - Se ha producido un error. Registro de proyecto no introducido","Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
                
            case "accion_Btn_Cancelar_Agregar_Proyecto":
                JOptionPane.showMessageDialog(null, "ok4");
                break;
        }
    }
}
