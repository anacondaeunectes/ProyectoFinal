/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.Modelo;
import vista.Interfaz_Admin;
/**
 *
 * @author anaco
 */
public class Controlador_Global_Admin extends Controlador implements ControladorInterfaz{
    
    Controlador_Agregar_Admin control_Agregar_Admin;
    Controlador_Modificar_Admin control_Modificar_Componentes;
    Controlador_Asociar_Admin control_Asociar_Componentes;
    Controlador_Consultar_Admin control_Consultar_Componentes;

    public Controlador_Global_Admin(Interfaz_Admin vista, Modelo modelo) {
        
        super(vista, modelo);
        this.control_Agregar_Admin = new Controlador_Agregar_Admin(vista, modelo);
        this.control_Modificar_Componentes = new Controlador_Modificar_Admin(vista, modelo);
        this.control_Asociar_Componentes = new Controlador_Asociar_Admin(vista, modelo);   
        this.control_Consultar_Componentes = new Controlador_Consultar_Admin(vista, modelo);
    }
    
    @Override
    public void iniciar() {
        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel
            //com.sun.java.swing.plaf.windows.WindowsLookAndFeel
            SwingUtilities.updateComponentTreeUI(this.vista);
            SwingUtilities.updateComponentTreeUI(this.vista.dialog_ModificarDescripcion_Modificar_Proyecto);
            this.vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {}
        
        control_Agregar_Admin.iniciar();
        control_Modificar_Componentes.iniciar();
        control_Asociar_Componentes.iniciar();
        control_Consultar_Componentes.iniciar();
        
    }
    
    
    
}
