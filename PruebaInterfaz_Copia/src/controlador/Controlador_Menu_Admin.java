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
public class Controlador_Menu_Admin implements ActionListener, MenuListener, MouseListener, KeyListener{
    
    //Instancia la interfaz de la vista con la que va a trabajar este controlador
    Interfaz_Admin vista;
    
    //Instancia el modelo con el que este controlador se va a comunicar
    Modelo modelo = new Modelo();
    
    static ArrayList <Proyecto> List_ComboBox_Asociar = null;
    static JTextComponent editor = null;
    
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
            //com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel
            //com.sun.java.swing.plaf.windows.WindowsLookAndFeel
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
        this.vista.btn_FiltrarEmpleado_Asociar.setActionCommand( "accion_Btn_FiltrarEmpleado_Asociar" );
        this.vista.btn_FiltrarEmpleado_Asociar.addActionListener(this);
        
        this.vista.btn_FiltrarProyecto_Asociar.setActionCommand( "accion_Btn_FiltrarProyecto_Asociar" );
        this.vista.btn_FiltrarProyecto_Asociar.addActionListener(this);
        
        this.vista.btn_Asociar_Asociar.setActionCommand( "accion_Btn_Asociar_Asociar" );
        this.vista.btn_Asociar_Asociar.addActionListener(this);
       
        this.vista.btn_Cancelar_Asociar.setActionCommand( "accion_Btn_Cancelar_Asociar" );
        this.vista.btn_Cancelar_Asociar.addActionListener(this);
        
        //Eti_ComboBox_Asociar
        this.vista.ComboBox_Asociar.getEditor().getEditorComponent().addMouseListener(this);
        this.vista.ComboBox_Asociar.getEditor().getEditorComponent().addKeyListener(this);
        
        
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
                switchPanels(this.vista.panel_Asociar);break;
                
              /*-----------------------------------Fin referente a Menu - Comienzo referente a botones y acciones------------------------------------------*/  
                
             //Agregar Empleado
            case "accion_Btn_Agregar_Agregar_Empleado":
                if (this.modelo.agregarEmpleado(this.vista.Txt_Nombre_Agregar_Empleado.getText(),
                        this.vista.Txt_Apellido_Agregar_Empleado.getText(),
                        Integer.parseInt(this.vista.Txt_Nacimiento_Agregar_Empleado.getText()),
                        this.vista.Txt_Nif_Agregar_Empleado.getText())){
                    System.out.println("Registro introducido");
                }else{
                    System.out.println("error");
                }break;
                
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
            case "accion_Btn_FiltrarEmpleado_Asociar":
                JOptionPane.showMessageDialog(null, "ok12");
                break;
                
            case "accion_Btn_FiltrarProyecto_Asociar":
                JOptionPane.showMessageDialog(null, "ok13");
                break;
                
            case "accion_Btn_Asociar_Asociar":
                JOptionPane.showMessageDialog(null, "ok14");
                break;
                
            case "accion_Btn_Cancelar_Asociar":
                JOptionPane.showMessageDialog(null, "ok15");
                
                break;
            
            //Eti_ComboBox_Asociar
            case "accion_ComboBox_Asociar":
               
                break;
        }
    }
    
    /*-------------Accion MENU_EVENT-------------*/
    @Override
    public void menuSelected(MenuEvent e) {
        switchPanels(this.vista.panel_Asociar);
        editor = (JTextComponent) this.vista.ComboBox_Asociar.getEditor().getEditorComponent();


        //Eti_ComboBox_Asociar
        //Este condicional se encarga de actualizar los items del jComboBox segun los datos que extraiga de la BBDD. La logica de ponerlo aqui es que de esta forma se actualiza cada vez que vuelva a este panel, por si se ha agregado o eliminado algun proyecto 
        //Es decir, si NO esta vacio
        if (!modelo.isProyectoEmpty()) {
            this.vista.ComboBox_Asociar.removeAllItems();
            //MutableComboBoxModel<Proyecto> proy_Model = new DefaultComboBoxModel<>();
            //this.vista.ComboBox_Asociar.setModel(proy_Model);
            List_ComboBox_Asociar = modelo.cargarProyectos();
            //Primero ordena todos los proyectos y luego los inserta conservando ese orden.
            List_ComboBox_Asociar.stream().sorted(Comparator.comparing(Proyecto::getTitulo)).forEach(x -> this.vista.ComboBox_Asociar.addItem(x));
            System.out.println("finish");
        }else{
            System.out.println("esta empty");
        }
       
    }
    
    @Override
    public void menuDeselected(MenuEvent e) {
      
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        
    }

    /*-------------Accion MOUSE_EVENT-------------*/
    @Override
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
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }


    //Eti_ComboBox_Asociar
    @Override
    public void keyReleased(KeyEvent e) {
        //El evento ha de reaccionar tras soltar la tecla ya que asi si que consigue recoger el ultimo caracter escrito. De otra forma, solo recogeria el los caracteres anteriores al ultima caracter escrito
        
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
            this.vista.ComboBox_Asociar.setSelectedItem(List_ComboBox_Asociar.stream().filter(x-> x.getTitulo().startsWith(str)).findFirst().get());
            String sel_Item_Text = this.vista.ComboBox_Asociar.getSelectedItem().toString();
            editor.setText(sel_Item_Text);
            System.out.println(str);
            editor.setSelectionStart(str.length());
            editor.setSelectionEnd(sel_Item_Text.length());
        }

        
        
        //this.vista.ComboBox_Asociar.setSelectedIndex(1);
        //System.out.println(((JTextField)this.vista.ComboBox_Asociar.getEditor().getEditorComponent()).getText());
        
    }

 


}
