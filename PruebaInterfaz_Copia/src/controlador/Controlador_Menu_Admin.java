/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.MutableComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.text.JTextComponent;
import modelo.Modelo;
import modelo.Proyecto;
import vista.Interfaz_Admin;

/**
 *
 * @author anaco
 */
public class Controlador_Menu_Admin implements ActionListener,  FocusListener{
    
    //Instancia la interfaz de la vista con la que va a trabajar este controlador
    Interfaz_Admin vista;
    
    //Instancia el modelo con el que este controlador se va a comunicar
    Modelo modelo = new Modelo();
    
/*    ArrayList <Proyecto> List_ComboBox_Asociar = null;
    JTextComponent editor = null;*/
    
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
        
        this.vista.menuItem_Agregar_Empleado.setActionCommand( "vista_Agregar_Empleado" );
        this.vista.menuItem_Agregar_Empleado.addActionListener(this);
       
        this.vista.menuItem_Agregar_Proyecto.setActionCommand( "vista_Agregar_Proyecto" );
        this.vista.menuItem_Agregar_Proyecto.addActionListener(this);
       
        this.vista.menuItem_Modificar_Empleado.setActionCommand( "vista_Modificar_Empleado" );
        this.vista.menuItem_Modificar_Empleado.addActionListener(this);
        
        this.vista.menuItem_Modificar_Proyecto.setActionCommand("vista_Modificar_Proyecto");
        this.vista.menuItem_Modificar_Proyecto.addActionListener(this);

        this.vista.menuItem_Asociar.setActionCommand("vista_Asociar");
        this.vista.menuItem_Asociar.addActionListener(this);
        
        this.vista.menuItem_EmpleadoEnProyecto_Consultar.setActionCommand("vista_EmpleadoEnProyecto_Consultar");
        this.vista.menuItem_EmpleadoEnProyecto_Consultar.addActionListener(this);
        
        this.vista.menuItem_ProyectoDeEmpleado_Consultar.setActionCommand("vista_ProyectoDeEmpleado_Consultar");
        this.vista.menuItem_ProyectoDeEmpleado_Consultar.addActionListener(this);
        
        this.vista.menuItem_ListaEmpleado_Consultar.setActionCommand("vista_ListaEmpleado_Consultar");
        this.vista.menuItem_ListaEmpleado_Consultar.addActionListener(this);
        
        this.vista.menuItem_ListaProyecto_Consultar.setActionCommand("vista_ListaProyecto_Consultar");
        this.vista.menuItem_ListaProyecto_Consultar.addActionListener(this);
        
        /*-----------------------------------Fin referente a Menu - Comienzo referente a botones y acciones------------------------------------------*/
        
        
        
        
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
    
    /*-------------Accion ACTION_EVENT-------------*/
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
                switchPanels(this.vista.panel_Asociar);
                
                //Realmente, por temas organizativos esto deberia estar en la clase "Controlador_Componentes_Admin" donde se ejecutaría este codigo a traves de un listener sobre este metodo. A implementar en un futuro
                
                Controlador_Componentes_Admin.editor = (JTextComponent) this.vista.ComboBox_Asociar.getEditor().getEditorComponent();
                
                if (!modelo.isProyectoEmpty()) {
                    this.vista.ComboBox_Asociar.removeAllItems();
                    Controlador_Componentes_Admin.List_ComboBox_Asociar = modelo.cargarProyectos();
                    //Primero ordena todos los proyectos y luego los inserta conservando ese orden.
                    Controlador_Componentes_Admin.List_ComboBox_Asociar.stream().sorted(Comparator.comparing(Proyecto::getTitulo)).forEach(x -> this.vista.ComboBox_Asociar.addItem(x));
                    System.out.println("finish");
                }else{
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ningun proyecto");
                }
                
                
                        
                break;
                
                
            case "vista_EmpleadoEnProyecto_Consultar":
                switchPanels(this.vista.panel_EmpleadoEnProyecto_Consultar);break;
                
            case "vista_ProyectoDeEmpleado_Consultar":
                switchPanels(this.vista.panel_ProyectoDeEmpleado_Consultar);break;
                
            case "vista_ListaEmpleado_Consultar":
                switchPanels(this.vista.panel_ListaEmpleado_Consultar);break;
                
            case "vista_ListaProyecto_Consultar":
                switchPanels(this.vista.panel_ListaProyecto_Consultar);break;
                
              /*-----------------------------------Fin referente a Menu - Comienzo referente a botones y acciones------------------------------------------*/  
                
            
            
            
        }
    }
    

    /*-------------Accion MOUSE_EVENT-------------*/
/*    @Override
    public void mouseClicked(MouseEvent e) {
       
    }
    
    //Eti_ComboBox_Asociar
    @Override
    public void mousePressed(MouseEvent e) {
       //Muestra el listado de items de este ComboBox
       this.vista.ComboBox_Asociar.showPopup();
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
    
    /*-------------Accion KEY_EVENT-------------*/
    /*@Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }*/


    //Eti_ComboBox_Asociar
/*   @Override
    public void keyReleased(KeyEvent e) {
        //El evento ha de reaccionar tras soltar la tecla ya que asi si que consigue recoger el ultimo caracter escrito. De otra forma, solo recogeria el los caracteres anteriores al ultima caracter escrito
        System.out.println("llega");
        String str = editor.getText();
        System.out.println(str);
        
        if (str.length() == 0) {
            System.out.println("vacio");
            this.vista.ComboBox_Asociar.setSelectedIndex(0);
        }else if (e.getExtendedKeyCode() == 16){
            System.out.println("shift");
        }else if(e.getExtendedKeyCode() == 8){
            System.out.println("backspace");
        }else{
            //es necesario volver a ordenar este Stream para que el metodo findFirst() coja el primero.
            this.vista.ComboBox_Asociar.setSelectedItem(List_ComboBox_Asociar.stream().sorted(Comparator.comparing(Proyecto::getTitulo)).filter(x-> x.getTitulo().startsWith(str)).findFirst().get());
            String sel_Item_Text = this.vista.ComboBox_Asociar.getSelectedItem().toString();
            editor.setText(sel_Item_Text);
            System.out.println(str);
            editor.setSelectionStart(str.length());
            editor.setSelectionEnd(sel_Item_Text.length());
        }

        
        
        //this.vista.ComboBox_Asociar.setSelectedIndex(1);
        //System.out.println(((JTextField)this.vista.ComboBox_Asociar.getEditor().getEditorComponent()).getText());
        
    }*/

    @Override
    public void focusGained(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 


}
