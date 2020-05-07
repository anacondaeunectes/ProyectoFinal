/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import modelo.Modelo;
import vista.Interfaz_Admin;

/**
 *
 * @author anaco
 */
public class Controlador_Admin implements ActionListener{
    
    Interfaz_Admin vista;
    
    Modelo modelo;
    
    public Controlador_Admin (Interfaz_Admin vista){
        this.vista = vista;
    }
    
    public enum AccionMVC
    {
        vista_Agregar_Empleado,
        vista_Agregar_Proyecto,
        vista_Modificar_Empleado,
        vista_Modificar_Proyecto
    }
    
    public void iniciar()
    {
        // Skin tipo WINDOWS
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}

        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.menuItem_Agregar_Empleado.setActionCommand( "vista_Agregar_Empleado" );
        this.vista.menuItem_Agregar_Empleado.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.menuItem_Agregar_Proyecto.setActionCommand( "vista_Agregar_Proyecto" );
        this.vista.menuItem_Agregar_Proyecto.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.menuItem_Modificar_Empleado.setActionCommand( "vista_Modificar_Empleado" );
        this.vista.menuItem_Modificar_Empleado.addActionListener(this);
        
        this.vista.menuItem_Modificar_Proyecto.setActionCommand("vista_Modificar_Proyecto");
        this.vista.menuItem_Modificar_Proyecto.addActionListener(this);

        //añade e inicia el jtable con un DefaultTableModel vacio
        //this.vista.__tabla_producto.addMouseListener(this);
        //this.vista.__tabla_producto.setModel( new DefaultTableModel() );
    }
    
    private void switchPanels (JPanel panel){
        this.vista.jLayeredPane1.removeAll();
        this.vista.jLayeredPane1.add(panel);
        this.vista.jLayeredPane1.repaint();
        this.vista.jLayeredPane1.revalidate();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        switch ( AccionMVC.valueOf( e.getActionCommand() ) ){
            case vista_Agregar_Empleado:
                    switchPanels(this.vista.panel_Agregar_Empleado);
                break;
                
            case vista_Agregar_Proyecto:
                    switchPanels(this.vista.panel_Agregar_Proyecto);
                break;
                
            case vista_Modificar_Empleado:
                    switchPanels(this.vista.panel_Modificar_Empleado);
                break;
                
            case vista_Modificar_Proyecto:
                    switchPanels(this.vista.panel_Modificar_Proyecto);
                break;
            
        }
    }
    
}
