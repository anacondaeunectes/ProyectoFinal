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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;
import modelo.Modelo;
import modelo.Proyecto;
import vista.Interfaz_Admin;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


/**
 *
 * @author anaco
 */
public class Controlador_Componentes_Admin implements ActionListener, MouseListener, KeyListener{
    
    Interfaz_Admin vista;
    Modelo modelo = new Modelo();
    
    static ArrayList <Proyecto> List_ComboBox_Asociar = null;
    static JTextComponent editor = null;
    
    public Controlador_Componentes_Admin(Interfaz_Admin vista){
        
        this.vista = vista;
    }
    
    public void iniciar(){
        
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
        
        this.vista.btn_Eliminar_Modificar_Empleado.setActionCommand( "accion_Btn_Eliminar_Modificar_Empleado" );
        this.vista.btn_Eliminar_Modificar_Empleado.addActionListener(this);
        
        this.vista.btn_Cancelar_Modificar_Empleado.setActionCommand( "accion_Btn_Cancelar_Modificar_Empleado" );
        this.vista.btn_Cancelar_Modificar_Empleado.addActionListener(this);
        
        //Modificar Proyecto
        this.vista.btn_Filtrar_Modificar_Proyecto.setActionCommand( "accion_Btn_Filtrar_Modificar_Proyecto" );
        this.vista.btn_Filtrar_Modificar_Proyecto.addActionListener(this);
        
        this.vista.btn_ModificarDescripcion_Modificar_Proyecto.setActionCommand( "accion_Btn_ModificarDescripcion_Modificar_Proyecto" );
        this.vista.btn_ModificarDescripcion_Modificar_Proyecto.addActionListener(this);
        
        this.vista.btn_Modificar_Modificar_Proyecto.setActionCommand( "accion_Btn_Modificar_Modificar_Proyecto" );
        this.vista.btn_Modificar_Modificar_Proyecto.addActionListener(this);
        
        this.vista.btn_Eliminar_Modificar_Proyecto.setActionCommand( "accion_Btn_Eliminar_Modificar_Proyecto" );
        this.vista.btn_Eliminar_Modificar_Proyecto.addActionListener(this);
        
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
        this.vista.ComboBox_Asociar.getEditor().getEditorComponent().addKeyListener(this);
        this.vista.ComboBox_Asociar.getEditor().getEditorComponent().addMouseListener(this);
        
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch ( e.getActionCommand() ){
        
             //Agregar Empleado
            case "accion_Btn_Agregar_Agregar_Empleado":
                if (this.modelo.agregarEmpleado(this.vista.Txt_Nombre_Agregar_Empleado.getText(),
                        this.vista.Txt_Apellido_Agregar_Empleado.getText(),
                        Integer.parseInt(this.vista.Txt_Nacimiento_Agregar_Empleado.getText()),
                        this.vista.Txt_Nif_Agregar_Empleado.getText())){
                    JOptionPane.showMessageDialog(null, "Registro de empleado introducido");
                }else{
                    JOptionPane.showMessageDialog(null, "ERROR - Se ha producido un error. Registro de empleado no introducido","Error", JOptionPane.ERROR_MESSAGE);
                }break;
                
            case "accion_Btn_Cancelar_Agregar_Empleado":
                JOptionPane.showMessageDialog(null, "ok2");
                break;
            
            //Agregar Proyecto
            case "accion_Btn_Agregar_Agregar_Proyecto":
                if (this.modelo.agregarProyecto(LocalDate.parse(this.vista.Txt_FechaInicio_Agregar_Proyecto.getText()),
                        LocalDate.parse(this.vista.Txt_FechaFin_Agregar_Proyecto.getText()),
                        this.vista.Txt_Titulo_Agregar_Proyecto.getText(),
                        this.vista.Txt_Descripcion_Agregar_Proyecto.getText())){
                    JOptionPane.showMessageDialog(null, "Registro de proyecto introducido");
                }else{
                    JOptionPane.showMessageDialog(null, "ERROR - Se ha producido un error. Registro de proyecto no introducido","Error", JOptionPane.ERROR_MESSAGE);
                }break;
                
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
                
            case "accion_Btn_Eliminar_Modificar_Empleado":
                JOptionPane.showMessageDialog(null, "ok6.5");break;
                
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
                
            case "accion_Btn_Eliminar_Modificar_Proyecto":
                JOptionPane.showMessageDialog(null, "ok10.5");break;
                
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
    
}
