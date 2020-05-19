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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import modelo.Modelo;
import vista.Interfaz_Admin;

/**
 * Controlador de la funcionalidad Modificar. Al igual que los demas hereda de la clase "Controlador" y, por tanto, implementa la interfaz "ControladorInterfaz"
 * @author anaco
 */
public class Controlador_Agregar_Admin extends Controlador implements ActionListener, WindowListener{
    
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
        
        this.vista.placeHolder_Nacimiento_Agregar_Empleado = new TextPrompt("(yyyy)", this.vista.Txt_Nacimiento_Agregar_Empleado, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_Nacimiento_Agregar_Empleado.changeAlpha(0.3f);
        this.vista.placeHolder_Nacimiento_Agregar_Empleado.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
            /*------------------------------ Agregar Proyecto------------------------------*/
        
        this.vista.menuItem_Agregar_Proyecto.setActionCommand( "vista_Agregar_Proyecto" );
        this.vista.menuItem_Agregar_Proyecto.addActionListener(this);
        
        this.vista.btn_Agregar_Agregar_Proyecto.setActionCommand( "accion_Btn_Agregar_Agregar_Proyecto" );
        this.vista.btn_Agregar_Agregar_Proyecto.addActionListener(this);
        
        this.vista.placeHolder_FechaInicio_Agregar_Proyecto = new TextPrompt("(yyyy-mm-dd)", this.vista.Txt_FechaInicio_Agregar_Proyecto, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FechaInicio_Agregar_Proyecto.changeAlpha(0.3f);
        this.vista.placeHolder_FechaInicio_Agregar_Proyecto.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FechaFin_Agregar_Proyecto = new TextPrompt("(yyyy-mm-dd)", this.vista.Txt_FechaFin_Agregar_Proyecto, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FechaFin_Agregar_Proyecto.changeAlpha(0.3f);
        this.vista.placeHolder_FechaFin_Agregar_Proyecto.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.addWindowListener(this);
        
//        Year a;
//        
//        if ((a = modelo.leerCfg_MinAnoNacimiento()) != null) {
//            modelo.setMin_Ano_Nacimiento(a);
//        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        switch ( e.getActionCommand() ){
            
                /*------------------------------ Agregar Empleado ------------------------------*/
            
            case "vista_Agregar_Empleado":
                switchPanels(this.vista.panel_Agregar_Empleado);
                break;
                
            case "accion_Btn_Agregar_Agregar_Empleado":
                
                try {
                    
                    if(this.modelo.agregarEmpleado(this.vista.Txt_Nombre_Agregar_Empleado.getText(),
                        this.vista.Txt_Apellido_Agregar_Empleado.getText(),
                        Year.parse(this.vista.Txt_Nacimiento_Agregar_Empleado.getText()),
                        this.vista.Txt_Nif_Agregar_Empleado.getText()))
                    {
                        JOptionPane.showMessageDialog(null, "Registro de empleado introducido correctamente");
                        
                        this.vista.Txt_Nombre_Agregar_Empleado.setText("");
                        this.vista.Txt_Apellido_Agregar_Empleado.setText("");
                        this.vista.Txt_Nacimiento_Agregar_Empleado.setText("");
                        this.vista.Txt_Nif_Agregar_Empleado.setText("");
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Fallo al validar los datos introducidos. Por favor, revíselos: \n \t - Los campos 'Nombre' y 'Apellido' no pueden estar vacios.\n \t - Revise el patrón del NIF.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vista, "sql: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
                } catch (DateTimeParseException rr){
                    JOptionPane.showMessageDialog(vista, "Date Format error: " + rr.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                break;
                
                
                /*------------------------------ Agregar Empleado ------------------------------*/  
                
            case "vista_Agregar_Proyecto":
                switchPanels(this.vista.panel_Agregar_Proyecto)
                ;break;
            
            case "accion_Btn_Agregar_Agregar_Proyecto":
                
                try {
                    
                    if (this.modelo.agregarProyecto(LocalDate.parse(this.vista.Txt_FechaInicio_Agregar_Proyecto.getText()),
                            LocalDate.parse(this.vista.Txt_FechaFin_Agregar_Proyecto.getText()),
                            this.vista.Txt_Titulo_Agregar_Proyecto.getText(),
                            this.vista.Txt_Descripcion_Agregar_Proyecto.getText())
                    ){
                        JOptionPane.showMessageDialog(vista, "Registro de proyecto introducido correctamente");
                        this.vista.Txt_FechaInicio_Agregar_Proyecto.setText("");
                        this.vista.Txt_FechaFin_Agregar_Proyecto.setText("");
                        this.vista.Txt_Titulo_Agregar_Proyecto.setText("");
                        vista.Txt_Descripcion_Agregar_Proyecto.setText("");
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Fallo al validar los datos introducidos. Por favor, revíselos: \n \t - El campo 'Titulo' no puede estar vacio.\n \t - La descripcion no puede superar los 500 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vista, "sql: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
                } catch (DateTimeParseException rr){
                    JOptionPane.showMessageDialog(vista, "Date Format error: " + rr.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                    
                break;

        }
    }
    
    /**
     * Este listener lo único que busca es cerrar la conexion con la Base de Datos al cerrar la ventana principal de la aplicacion.
     * @param e 
     */
    @Override
    public void windowClosing(WindowEvent e) {
        modelo.closeConexion();
    } 
    
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}
