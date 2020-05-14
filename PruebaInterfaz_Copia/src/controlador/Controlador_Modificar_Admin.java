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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.Modelo;
import vista.Interfaz_Admin;

/**
 *
 * @author anaco
 */
public class Controlador_Modificar_Admin extends Controlador implements ControladorInterfaz, ActionListener, ListSelectionListener, MouseListener, WindowListener{

    public Controlador_Modificar_Admin(Interfaz_Admin vista, Modelo modelo) {
        super(vista, modelo);
    }

    @Override
    public void iniciar() {
        
            /*------------------------------ Modificar Empleado ------------------------------*/
        this.vista.menuItem_Modificar_Empleado.setActionCommand( "vista_Modificar_Empleado" );
        this.vista.menuItem_Modificar_Empleado.addActionListener(this);
        
        //Carga por defecto el jTable del panel "panel_Modificar_Empleado" con todos los registros de la tabla empleado
        this.vista.tabla_Modificar_Empleado.setModel(modelo.getTablaEmpleado(true, true, true, true, "", "", "", ""));
        
        //this.vista.tabla_Modificar_Empleado.getSelectionModel().addListSelectionListener(this);
        this.vista.tabla_Modificar_Empleado.addMouseListener(this);
        
        this.vista.btn_Filtrar_Modificar_Empleado.setActionCommand( "accion_Btn_Filtrar_Modificar_Empleado" );
        this.vista.btn_Filtrar_Modificar_Empleado.addActionListener(this);
        
        this.vista.btn_Modificar_Modificar_Empleado.setActionCommand( "accion_Btn_Modificar_Modificar_Empleado" );
        this.vista.btn_Modificar_Modificar_Empleado.addActionListener(this);
        
        this.vista.btn_Eliminar_Modificar_Empleado.setActionCommand( "accion_Btn_Eliminar_Modificar_Empleado" );
        this.vista.btn_Eliminar_Modificar_Empleado.addActionListener(this);
        
        this.vista.btn_Cancelar_Modificar_Empleado.setActionCommand( "accion_Btn_Cancelar_Modificar_Empleado" );
        this.vista.btn_Cancelar_Modificar_Empleado.addActionListener(this);
        
        //Coloca unos placeHolder a los jTextField del filtrado
        this.vista.placeHolder_FiltrarNombre_Modificar_Empleado = new TextPrompt("Nombre", this.vista.txt_FiltrarNombre_Modificar_Empleado, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNombre_Modificar_Empleado.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNombre_Modificar_Empleado.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarApellido_Modificar_Empleado = new TextPrompt("Apellido", this.vista.txt_FiltrarApellido_Modificar_Empleado, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarApellido_Modificar_Empleado.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarApellido_Modificar_Empleado.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarNacimiento_Modificar_Empleado = new TextPrompt("Ano nacimiento", this.vista.txt_FiltrarNacimiento_Modificar_Empleado, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNacimiento_Modificar_Empleado.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNacimiento_Modificar_Empleado.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarNIF_Modificar_Empleado = new TextPrompt("NIF", this.vista.txt_FiltrarNIF_Modificar_Empleado, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarNIF_Modificar_Empleado.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarNIF_Modificar_Empleado.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
            /*------------------------------ Modificar Proyecto ------------------------------*/
        
        this.vista.menuItem_Modificar_Proyecto.setActionCommand("vista_Modificar_Proyecto");
        this.vista.menuItem_Modificar_Proyecto.addActionListener(this);
        
        this.vista.tabla_Modificar_Proyecto.setModel(modelo.getTablaProyecto(true, true, true, true, true, "", "", "", "", ""));
        
        //this.vista.tabla_Modificar_Proyecto.getSelectionModel().addListSelectionListener(this);
        this.vista.tabla_Modificar_Proyecto.addMouseListener(this);
        
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
        
        //Botones del jDialog "dialog_ModificarDescripcion_Modificar_Proyecto" asociado al boton "Modificar Descripcion" de este panel
        this.vista.btn_Guardar_ModificarDescripcion_Modificar_Proyecto.setActionCommand("accion_Btn_Guardar_ModificarDescripcion_Modificar_Proyecto");
        this.vista.btn_Guardar_ModificarDescripcion_Modificar_Proyecto.addActionListener(this);
        
        this.vista.btn_Cancelar_ModificarDescripcion_Modificar_Proyecto.setActionCommand("accion_Btn_Cancelar_ModificarDescripcion_Modificar_Proyecto");
        this.vista.btn_Cancelar_ModificarDescripcion_Modificar_Proyecto.addActionListener(this);
        
        //PlaceHolder para los campos de filtrado de proyectos
        this.vista.placeHolder_FiltrarTitulo_Modificar_Proyecto = new TextPrompt("Titulo", this.vista.txt_FiltrarTitulo_Modificar_Proyecto, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarTitulo_Modificar_Proyecto.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarTitulo_Modificar_Proyecto.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarFechaInicio_Modificar_Proyecto = new TextPrompt("Fecha inicio", this.vista.txt_FiltrarFechaInicio_Modificar_Proyecto, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarFechaInicio_Modificar_Proyecto.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarFechaInicio_Modificar_Proyecto.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarFechaFin_Modificar_Proyecto = new TextPrompt("Fecha Fin", this.vista.txt_FiltrarFechaFin_Modificar_Proyecto, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarFechaFin_Modificar_Proyecto.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarFechaFin_Modificar_Proyecto.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarId_Modificar_Proyecto = new TextPrompt("Id", this.vista.txt_FiltrarId_Modificar_Proyecto, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarId_Modificar_Proyecto.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarId_Modificar_Proyecto.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.placeHolder_FiltrarDescripcion_Modificar_Proyecto = new TextPrompt("Palabra clave Descripcion", this.vista.txt_FiltrarDescripcion_Modificar_Proyecto, TextPrompt.Show.FOCUS_LOST);
        this.vista.placeHolder_FiltrarDescripcion_Modificar_Proyecto.changeAlpha(0.5f);
        this.vista.placeHolder_FiltrarDescripcion_Modificar_Proyecto.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 11));
        
        this.vista.dialog_ModificarDescripcion_Modificar_Proyecto.addWindowListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch ( e.getActionCommand() ){
        
                /*------------------------------ Modificar Empleado ------------------------------*/
            
            case "vista_Modificar_Empleado":
                switchPanels(this.vista.panel_Modificar_Empleado);
                break;
                
            case "accion_Btn_Filtrar_Modificar_Empleado":
                //Aplica al jTable de empleados el modelo resultado de llamar al metodo "getTablaProyecto" de la clase Modelo con todas las columnas (true) y con los filtros para esta recogidos de los jTextField respectivos
                this.vista.tabla_Modificar_Empleado.setModel(modelo.getTablaEmpleado(true, true, true, true,
                        this.vista.txt_FiltrarNombre_Modificar_Empleado.getText(),
                        this.vista.txt_FiltrarApellido_Modificar_Empleado.getText(), 
                        this.vista.txt_FiltrarNacimiento_Modificar_Empleado.getText(),
                        this.vista.txt_FiltrarNIF_Modificar_Empleado.getText()));
                break;
                
            case "accion_Btn_Modificar_Modificar_Empleado":
                
        
                try {
                    if (modelo.modificarEmpleado(this.vista.txt_Nombre_Modificar_Empleado.getText(),
                            this.vista.txt_Apellido_Modificar_Empleado.getText(),
                            Integer.parseInt(this.vista.txt_Nacimiento_Modificar_Empleado.getText()),
                            this.vista.tabla_Modificar_Empleado.getValueAt(this.vista.tabla_Modificar_Empleado.getSelectedRow(), 3).toString())
                        )
                    {
                        JOptionPane.showMessageDialog(vista, "Modificación exitosa", "Modificar", JOptionPane.INFORMATION_MESSAGE);
                        this.vista.tabla_Modificar_Empleado.setModel(modelo.getTablaEmpleado(true, true, true, true, "", "", "", ""));
                    }
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vista, "sql: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
                }
        
                break;
                
            case "accion_Btn_Eliminar_Modificar_Empleado":
                JOptionPane.showMessageDialog(null, "ok6.5");
                break;
                
            case "accion_Btn_Cancelar_Modificar_Empleado":
                JOptionPane.showMessageDialog(null, "ok7");
                break;
                
                /*------------------------------ Modificar Proyecto ------------------------------*/
                
            case "vista_Modificar_Proyecto":
                switchPanels(this.vista.panel_Modificar_Proyecto);
                break;
                
            case "accion_Btn_Filtrar_Modificar_Proyecto":
                //Aplica al jTable de proyectos el modelo resultado de llamar al metodo "getTablaProyecto" de la clase Modelo con todas las columnas (true) y con los filtros para esta recogidos de los jTextField respectivos
                this.vista.tabla_Modificar_Proyecto.setModel(modelo.getTablaProyecto(true, true, true, true, true,
                        this.vista.txt_FiltrarTitulo_Modificar_Proyecto.getText(),
                        this.vista.txt_FiltrarFechaInicio_Modificar_Proyecto.getText(), 
                        this.vista.txt_FiltrarFechaFin_Modificar_Proyecto.getText(),
                        this.vista.txt_FiltrarId_Modificar_Proyecto.getText(),
                        this.vista.txt_FiltrarDescripcion_Modificar_Proyecto.getText()));
                break;
               
            
            case "accion_Btn_ModificarDescripcion_Modificar_Proyecto":
                //Rellena el jTextArea con la descripcion del registro seleccionado
                //this.vista.txtArea_ModificarDescripcion_Modificar_Proyecto.setText(this.vista.tabla_Modificar_Proyecto.getValueAt(this.vista.tabla_Modificar_Proyecto.getSelectedRow(), 4).toString());
                //Coloca el cursor al final del texto
                this.vista.txtArea_ModificarDescripcion_Modificar_Proyecto.setCaretPosition(this.vista.txtArea_ModificarDescripcion_Modificar_Proyecto.getText().length());
                //Muestra la ventana (modal)pero antes provoca que esta se centre con respecto a la vista
                this.vista.dialog_ModificarDescripcion_Modificar_Proyecto.setLocationRelativeTo(vista);
                this.vista.dialog_ModificarDescripcion_Modificar_Proyecto.setVisible(true);
                break;
                
            case "accion_Btn_Guardar_ModificarDescripcion_Modificar_Proyecto":
                //Sencillamente, se cierra la pestana ya que el jTextArea va a seguir guardando los cambios
                this.vista.dialog_ModificarDescripcion_Modificar_Proyecto.dispose();
                break;
                
            case "accion_Btn_Cancelar_ModificarDescripcion_Modificar_Proyecto":
                //Primero se restaura el campo con el valor actual del registro y despues se cierra el jDialog
                this.vista.txtArea_ModificarDescripcion_Modificar_Proyecto.setText(this.vista.tabla_Modificar_Proyecto.getValueAt(this.vista.tabla_Modificar_Proyecto.getSelectedRow(), 4).toString());
                this.vista.dialog_ModificarDescripcion_Modificar_Proyecto.dispose();
                break;
                
            case "accion_Btn_Modificar_Modificar_Proyecto":
                try {
                    modelo.modificarProyecto(this.vista.txt_Titulo_Modificar_Proyecto.getText(),
                        this.vista.txt_FechaInicio_Modificar_Proyecto.getText(),
                        this.vista.txt_FechaFin_Modificar_Proyecto.getText(),
                        Integer.parseInt(this.vista.tabla_Modificar_Proyecto.getValueAt(this.vista.tabla_Modificar_Proyecto.getSelectedRow(), 3).toString()),
                        this.vista.txtArea_ModificarDescripcion_Modificar_Proyecto.getText());
                    
                    this.vista.tabla_Modificar_Proyecto.setModel(modelo.getTablaProyecto(true, true, true, true, true,
                        this.vista.txt_FiltrarTitulo_Modificar_Proyecto.getText(),
                        this.vista.txt_FiltrarFechaInicio_Modificar_Proyecto.getText(), 
                        this.vista.txt_FiltrarFechaFin_Modificar_Proyecto.getText(),
                        this.vista.txt_FiltrarId_Modificar_Proyecto.getText(),
                        this.vista.txt_FiltrarDescripcion_Modificar_Proyecto.getText()));
                    
                    
                
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vista, "sql: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
                
            case "accion_Btn_Eliminar_Modificar_Proyecto":
                JOptionPane.showMessageDialog(null, "ok10.5");
                break;
                
            case "accion_Btn_Cancelar_Modificar_Proyecto":
                JOptionPane.showMessageDialog(null, "ok11");
                break;
        }
                
    }

    
    /**
     * Listener que es invocado al seleccionar algun registro en una de las tablas de los dos paneles asociados a la funcionalidad "Modificar"
     * @param e Evento producido
     * @deprecated Debido a que, pese a funcionar bie, lanzaba IndexOutOfBoundsException -> Listener mouseClicked misma implementacion.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        //int rowSelected;
        
        //Rellena los respectivos jTextField con los valores del registro seleccionado la tabla
        //Ya que este controlador es el encargado de toda la funcionalidad de "Modificar" y los dos paneles que se asocian a esta en la vista, se ha deferenciar entre las tablas de ambas. Para ello se usa un condicional para ver si el componente que invoca el evento es una u otra tabla.
        /*if (e.getSource().equals(this.vista.tabla_Modificar_Empleado.getSelectionModel())){
            
            rowSelected = this.vista.tabla_Modificar_Empleado.getSelectedRow();

            this.vista.txt_Nombre_Modificar_Empleado.setText(this.vista.tabla_Modificar_Empleado.getValueAt(rowSelected, 0).toString());
            this.vista.txt_Apellido_Modificar_Empleado.setText(this.vista.tabla_Modificar_Empleado.getValueAt(rowSelected, 1).toString());
            this.vista.txt_Nacimiento_Modificar_Empleado.setText(this.vista.tabla_Modificar_Empleado.getValueAt(rowSelected, 2).toString());
        
        }else if (e.getSource().equals(this.vista.tabla_Modificar_Proyecto.getSelectionModel())){
            
            rowSelected = this.vista.tabla_Modificar_Proyecto.getSelectedRow();

            this.vista.txt_Titulo_Modificar_Proyecto.setText(this.vista.tabla_Modificar_Proyecto.getValueAt(rowSelected, 0).toString());
            this.vista.txt_FechaInicio_Modificar_Proyecto.setText(this.vista.tabla_Modificar_Proyecto.getValueAt(rowSelected, 1).toString());
            this.vista.txt_FechaFin_Modificar_Proyecto.setText(this.vista.tabla_Modificar_Proyecto.getValueAt(rowSelected, 2).toString());
            
        
        }*/
        
    }

    /**
     * Listener invocado al seleccionar algun registro en una de las tablas de los dos paneles asociados a la funcionalidad "Modificar". En funcion de que tabla se use, se rellenaran unos jTextField u otros con la info del registro seleccionado en la tabla
     * @param e Evento producido
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        
        int rowSelected;
        
        if (e.getSource().equals(this.vista.tabla_Modificar_Empleado)) {
            rowSelected = this.vista.tabla_Modificar_Empleado.getSelectedRow();

            this.vista.txt_Nombre_Modificar_Empleado.setText(this.vista.tabla_Modificar_Empleado.getValueAt(rowSelected, 0).toString());
            this.vista.txt_Apellido_Modificar_Empleado.setText(this.vista.tabla_Modificar_Empleado.getValueAt(rowSelected, 1).toString());
            this.vista.txt_Nacimiento_Modificar_Empleado.setText(this.vista.tabla_Modificar_Empleado.getValueAt(rowSelected, 2).toString());
            
        }else if (e.getSource().equals(this.vista.tabla_Modificar_Proyecto)){
            rowSelected = this.vista.tabla_Modificar_Proyecto.getSelectedRow();

            this.vista.txt_Titulo_Modificar_Proyecto.setText(this.vista.tabla_Modificar_Proyecto.getValueAt(rowSelected, 0).toString());
            this.vista.txt_FechaInicio_Modificar_Proyecto.setText(this.vista.tabla_Modificar_Proyecto.getValueAt(rowSelected, 1).toString());
            this.vista.txt_FechaFin_Modificar_Proyecto.setText(this.vista.tabla_Modificar_Proyecto.getValueAt(rowSelected, 2).toString());
            this.vista.txtArea_ModificarDescripcion_Modificar_Proyecto.setText(this.vista.tabla_Modificar_Proyecto.getValueAt(rowSelected, 4).toString());
            
        }
    }
    
    @Override
    public void windowClosed(WindowEvent e) {
        
        this.vista.txtArea_ModificarDescripcion_Modificar_Proyecto.setText(this.vista.tabla_Modificar_Proyecto.getValueAt(this.vista.tabla_Modificar_Proyecto.getSelectedRow(), 4).toString());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
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
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
    
    
    
}
