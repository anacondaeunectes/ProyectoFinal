/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;
import modelo.Modelo;
import modelo.Proyecto;
import vista.Interfaz_Admin;

/**
 *
 * @author anaco
 */
public class Controlador_Asociar_Admin extends Controlador implements ControladorInterfaz, ActionListener, KeyListener, MouseListener{
    
    ArrayList <Proyecto> List_ComboBox_Asociar = null;
    JTextComponent editor = null;

    public Controlador_Asociar_Admin(Interfaz_Admin vista, Modelo modelo) {
        super(vista, modelo);
    }

    @Override
    public void iniciar() {

        this.vista.menuItem_Asociar.setActionCommand("vista_Asociar");
        this.vista.menuItem_Asociar.addActionListener(this);
        
        this.vista.btn_FiltrarEmpleado_Asociar.setActionCommand( "accion_Btn_FiltrarEmpleado_Asociar" );
        this.vista.btn_FiltrarEmpleado_Asociar.addActionListener(this);
        
        this.vista.btn_FiltrarProyecto_Asociar.setActionCommand( "accion_Btn_FiltrarProyecto_Asociar" );
        this.vista.btn_FiltrarProyecto_Asociar.addActionListener(this);
        
        this.vista.btn_Asociar_Asociar.setActionCommand( "accion_Btn_Asociar_Asociar" );
        this.vista.btn_Asociar_Asociar.addActionListener(this);
       
        this.vista.btn_Cancelar_Asociar.setActionCommand( "accion_Btn_Cancelar_Asociar" );
        this.vista.btn_Cancelar_Asociar.addActionListener(this);
        
        //Eti_ComboBox_Asociar
        this.vista.ComboBox_Asociar.getEditor().getEditorComponent().addKeyListener(this);
        this.vista.ComboBox_Asociar.getEditor().getEditorComponent().addMouseListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch ( e.getActionCommand() ){
            
            //Cambia la vista y ademas, refresca el modelo del jComboBox por lo que si se cambiado de panel para agregar o modificar proyectos, al volver a este panel se reflejarian esos cambios
            case "vista_Asociar":
                switchPanels(this.vista.panel_Asociar);
                
                editor = (JTextComponent) this.vista.ComboBox_Asociar.getEditor().getEditorComponent();
                
                if (!modelo.isProyectoEmpty()) {
                    this.vista.ComboBox_Asociar.removeAllItems();
                    List_ComboBox_Asociar = modelo.cargarProyectos();
                    //Primero ordena todos los proyectos y luego los inserta conservando ese orden.
                    List_ComboBox_Asociar.stream().sorted(Comparator.comparing(Proyecto::getTitulo)).forEach(x -> this.vista.ComboBox_Asociar.addItem(x));
                    System.out.println("finish");
                }else{
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ningun proyecto");
                }
         
                break;
            
            //Accion btn_FiltrarEmpleado_Asociar
            case "accion_Btn_FiltrarEmpleado_Asociar":
                JOptionPane.showMessageDialog(null, "ok12");
                break;
                
            //Accion btn_FiltrarProyecto_Asociar
            case "accion_Btn_FiltrarProyecto_Asociar":
                JOptionPane.showMessageDialog(null, "ok13");
                break;
            
            //Accion btn_Asociar_Asociar
            case "accion_Btn_Asociar_Asociar":
                JOptionPane.showMessageDialog(null, "ok14");
                break;
                
            //Accion btn_Asociar_Asociar
            case "accion_Btn_Cancelar_Asociar":
                JOptionPane.showMessageDialog(null, "ok15");
                break;
        
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
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
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Muestra el listado de items de este ComboBox
        System.out.println("Llega Mouse");
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
    
}
