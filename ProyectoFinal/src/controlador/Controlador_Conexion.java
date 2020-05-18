/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Modelo;
import vista.Interfaz_Admin;

/**
 *
 * @author anaco
 */
public class Controlador_Conexion extends Controlador implements ActionListener, WindowListener{
    
    public Controlador_Conexion(Interfaz_Admin vista, Modelo modelo){
        super(vista, modelo);
    }
    
    public void iniciar(){
        
        System.out.println(modelo.getPassword());
        this.vista.txt_Ip_Conexion.setText(modelo.getUrl());
        this.vista.txt_BaseDatos_Conexion.setText(modelo.getDb());
        this.vista.txt_Usuario_Conexion.setText(modelo.getUser());
        
        
    
//        this.vista.btn_Conectar_Conexion.setActionCommand("accion_btn_Conectar_Conexion");
        this.vista.btn_Conectar_Conexion.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //se llama al metodo "loadConexion". En caso de que devuelva "true" se procede a intentar establecer conexion (openConexion()) y en caso de que se lea mal el archivo, se informara al usuario 
        if(modelo.loadConexion(this.vista.txt_Ip_Conexion.getText(),
                this.vista.txt_BaseDatos_Conexion.getText(),
                this.vista.txt_Usuario_Conexion.getText(),
                String.valueOf(this.vista.txt_Contrasena_Conexion.getPassword()))
        ){
            try {
                modelo.openConexion();
                //En caso de que se establezca correctamente la conexion, se llama al metodo storeConexion() para intentar renovar las propiedades de la configuracion y que aparezcan por defecto esas la proxima vez que se ejecute la aplicacion
                if(!modelo.storeConexion(this.vista.txt_Ip_Conexion.getText(),
                    this.vista.txt_BaseDatos_Conexion.getText(),
                    this.vista.txt_Usuario_Conexion.getText())){
                        System.out.println("Error al tratar de modificar la configuracion de la conexión");
                }
                this.vista.dialog_Conexion.dispose();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                JOptionPane.showMessageDialog(vista.dialog_Conexion, "sql: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }   
            
        }else{
            JOptionPane.showMessageDialog(vista.dialog_Conexion, "Error al leer las propiedades de la configuración", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        this.vista.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
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
