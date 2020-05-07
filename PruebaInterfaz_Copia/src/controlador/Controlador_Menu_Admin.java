/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import modelo.Modelo;
import vista.Interfaz_Admin;

/**
 *
 * @author anaco
 */
public class Controlador_Menu_Admin implements ActionListener, MenuListener{
    
    //Instancia la interfaz de la vista con la que va a trabajar este controlador
    Interfaz_Admin vista;
    
    //Instancia el modelo con el que este controlador se va a comunicar
    Modelo modelo;
    
    /**
     * Constructor de este controlador. Requiere de un objeto "Interfaz_Admin" el cual asignara a la instanciación "vista" anterior. Se trata de la interfaz con la que va a operar este controlador
     * @param vista 
     */
    public Controlador_Menu_Admin (Interfaz_Admin vista){
        this.vista = vista;
    }
    
    /**
     * Enum que aglutina los actionCommand de los eventos que controla esta clase.
     */
    
    public void iniciar()
    {
        // Skin tipo WINDOWS
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {}

        
        this.vista.menuItem_Agregar_Empleado.setActionCommand( "vista_Agregar_Empleado" );
        this.vista.menuItem_Agregar_Empleado.addActionListener(this);
       
        this.vista.menuItem_Agregar_Proyecto.setActionCommand( "vista_Agregar_Proyecto" );
        this.vista.menuItem_Agregar_Proyecto.addActionListener(this);
       
        this.vista.menuItem_Modificar_Empleado.setActionCommand( "vista_Modificar_Empleado" );
        this.vista.menuItem_Modificar_Empleado.addActionListener(this);
        
        this.vista.menuItem_Modificar_Proyecto.setActionCommand("vista_Modificar_Proyecto");
        this.vista.menuItem_Modificar_Proyecto.addActionListener(this);

        this.vista.menu_Asociar.setActionCommand("vista_Asociar");
        this.vista.menu_Asociar.addMenuListener(this);
      
        
        /*-----------------------------------Fin referente a Menu - Comienzo referente a botones y acciones------------------------------------------*/
        
        //Agregar Empleado
        this.vista.btn_Agregar_Agregar_Empleado.setActionCommand( "accion_Btn_Agregar_Agregar_Empleado" );
        this.vista.btn_Agregar_Agregar_Empleado.addActionListener(this);
        
        this.vista.btn_Cancelar_Agregar_Empleado.setActionCommand( "accion_Btn_Cancelar_Agregar_Empleado" );
        this.vista.btn_Cancelar_Agregar_Empleado.addActionListener(this);
        
        //Agregar Proyecto
        this.vista.btn_Agregar_Agregar_Proyecto.setActionCommand( "accion_Btn_Agregar_Agregar_Proyecto" );
        this.vista.btn_Agregar_Agregar_Proyecto.addActionListener(this);
        
        this.vista.btn_Cancelar_Agregar_Proyecto.setActionCommand( "accion_Btn_Cancelar_Agregar_Proyecto" );
        this.vista.btn_Cancelar_Agregar_Proyecto.addActionListener(this);
        
        //Modificar Empleado
        this.vista.btn_Filtrar_Modificar_Empleado.setActionCommand( "accion_Btn_Filtrar_Modificar_Empleado" );
        this.vista.btn_Filtrar_Modificar_Empleado.addActionListener(this);
        
        this.vista.btn_Modificar_Modificar_Empleado.setActionCommand( "accion_Btn_Modificar_Modificar_Empleado" );
        this.vista.btn_Modificar_Modificar_Empleado.addActionListener(this);
        
        this.vista.btn_Cancelar_Modificar_Empleado.setActionCommand( "accion_Btn_Cancelar_Modificar_Empleado" );
        this.vista.btn_Cancelar_Modificar_Empleado.addActionListener(this);
        
        //Modificar Proyecto
        this.vista.btn_Filtrar_Modificar_Proyecto.setActionCommand( "accion_Btn_Filtrar_Modificar_Proyecto" );
        this.vista.btn_Filtrar_Modificar_Proyecto.addActionListener(this);
        
        this.vista.btn_ModificarDescripcion_Modificar_Proyecto.setActionCommand( "accion_Btn_ModificarDescripcion_Modificar_Proyecto" );
        this.vista.btn_ModificarDescripcion_Modificar_Proyecto.addActionListener(this);
        
        this.vista.btn_Modificar_Modificar_Proyecto.setActionCommand( "accion_Btn_Modificar_Modificar_Proyecto" );
        this.vista.btn_Modificar_Modificar_Proyecto.addActionListener(this);
        
        this.vista.btn_Cancelar_Modificar_Proyecto.setActionCommand( "accion_Btn_Cancelar_Modificar_Proyecto" );
        this.vista.btn_Cancelar_Modificar_Proyecto.addActionListener(this);
        
        //Asociar
        this.vista.btn_FiltrarEmpleado_Asociar.setActionCommand( "accion_btn_FiltrarEmpleado_Asociar" );
        this.vista.btn_FiltrarEmpleado_Asociar.addActionListener(this);
        
        this.vista.btn_FiltrarProyecto_Asociar.setActionCommand( "accion_btn_FiltrarProyecto_Asociar" );
        this.vista.btn_FiltrarProyecto_Asociar.addActionListener(this);
        
        this.vista.btn_Asociar_Asociar.setActionCommand( "accion_btn_Asociar_Asociar" );
        this.vista.btn_Asociar_Asociar.addActionListener(this);
       
        this.vista.btn_Cancelar_Asociar.setActionCommand( "accion_btn_Cancelar_Asociar" );
        this.vista.btn_Cancelar_Asociar.addActionListener(this);
        
        
        //añade e inicia el jtable con un DefaultTableModel vacio
        //this.vista.__tabla_producto.addMouseListener(this);
        //this.vista.__tabla_producto.setModel( new DefaultTableModel() );
    }
    
    /**
     * Metodo escargado de intercambiar los paneles del "layeredPane" a mostrar al usuario por la interfaz. 
     * @param panel Panel a mostrar
     */
    private void switchPanels (JPanel panel){
        this.vista.jLayeredPane1.removeAll();
        this.vista.jLayeredPane1.add(panel);
        this.vista.jLayeredPane1.repaint();
        this.vista.jLayeredPane1.revalidate();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        switch ( e.getActionCommand() ){
            case "vista_Agregar_Empleado":
                switchPanels(this.vista.panel_Agregar_Empleado);break;
                
            case "vista_Agregar_Proyecto":
                switchPanels(this.vista.panel_Agregar_Proyecto);break;
                
            case "vista_Modificar_Empleado":
                switchPanels(this.vista.panel_Modificar_Empleado);break;
                
            case "vista_Modificar_Proyecto":
                switchPanels(this.vista.panel_Modificar_Proyecto);break;
                
            case "vista_Asociar":
                switchPanels(this.vista.panel_Asociar);break;
                
              /*-----------------------------------Fin referente a Menu - Comienzo referente a botones y acciones------------------------------------------*/  
                
             //Agregar Empleado
            case "accion_Btn_Agregar_Agregar_Empleado":
                JOptionPane.showMessageDialog(null, "ok1");
                break;
                
            case "accion_Btn_Cancelar_Agregar_Empleado":
                JOptionPane.showMessageDialog(null, "ok2");
                break;
            
            //Agregar Proyecto
            case "accion_Btn_Agregar_Agregar_Proyecto":
                JOptionPane.showMessageDialog(null, "ok3");
                break;
                
            case "accion_Btn_Cancelar_Agregar_Proyecto":
                JOptionPane.showMessageDialog(null, "ok4");
                break;
                
            //Modificar Empleado
            case "accion_Btn_Filtrar_Modificar_Empleado":
                JOptionPane.showMessageDialog(null, "ok5");
                break;
                
            case "accion_Btn_Modificar_Modificar_Empleado":
                JOptionPane.showMessageDialog(null, "ok6");
                break;
                
            case "accion_Btn_Cancelar_Modificar_Empleado":
                JOptionPane.showMessageDialog(null, "ok7");
                break;
             
            //Modificar Proyecto
            case "accion_Btn_Filtrar_Modificar_Proyecto":
                JOptionPane.showMessageDialog(null, "ok8");
                break;
            
            case "accion_Btn_ModificarDescripcion_Modificar_Proyecto":
                JOptionPane.showMessageDialog(null, "ok9");
                break;
                
            case "accion_Btn_Modificar_Modificar_Proyecto":
                JOptionPane.showMessageDialog(null, "ok10");
                break;
                
            case "accion_Btn_Cancelar_Modificar_Proyecto":
                JOptionPane.showMessageDialog(null, "ok11");
                break;
                
            //Asociar
            case "accion_btn_FiltrarEmpleado_Asociar":
                JOptionPane.showMessageDialog(null, "ok12");
                break;
                
            case "accion_btn_FiltrarProyecto_Asociar":
                JOptionPane.showMessageDialog(null, "ok13");
                break;
                
            case "accion_btn_Asociar_Asociar":
                JOptionPane.showMessageDialog(null, "ok14");
                break;
                
            case "accion_btn_Cancelar_Asociar":
                JOptionPane.showMessageDialog(null, "ok15");
                break;
        }
    }
    
       @Override
    public void menuSelected(MenuEvent e) {
        switchPanels(this.vista.panel_Asociar);
        /*this.vista.menu_Asociar.doClick();
        switchPanels(this.vista.panel_Asociar);
        this.vista.panel_Asociar.requestFocusInWindow();
           System.out.println(this.vista.menu_Asociar.isSelected());
        this.vista.menu_Asociar.setSelected(false);
            System.out.println(this.vista.menu_Asociar.isSelected());
            this.vista.pack();
        this.vista.panel_Asociar.requestFocusInWindow();*/
        
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        this.vista.panel_Asociar.requestFocus();
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        
    }


}
