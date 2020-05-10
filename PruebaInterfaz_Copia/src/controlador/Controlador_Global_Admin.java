/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import vista.Interfaz_Admin;
/**
 *
 * @author anaco
 */
public class Controlador_Global_Admin {
    
    Interfaz_Admin vista;
    Controlador_Menu_Admin control_Menu;
    Controlador_Componentes_Admin control_Componentes;

    public Controlador_Global_Admin(Interfaz_Admin vista) {
        this.vista = vista;
        this.control_Menu = new Controlador_Menu_Admin(vista);
        this.control_Componentes = new Controlador_Componentes_Admin(vista);
    }
    
    public void iniciarControladores(){
        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel
            //com.sun.java.swing.plaf.windows.WindowsLookAndFeel
            SwingUtilities.updateComponentTreeUI(this.vista);
            this.vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {}
        
        control_Menu.iniciar();
        control_Componentes.iniciar();
        
        
    }
    
    
    
}
