/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import vista.Interfaz_Admin;
import modelo.Modelo;

/**
 *
 * @author anaco
 */
public class Main {
    
     public static void main(String[] args) {
        //ejecuta el controlador y este la vista
        //new Controlador_Menu_Admin( new Interfaz_Admin() ).iniciar() ;
        new Controlador_Global_Admin(new Interfaz_Admin(), new Modelo()).iniciar();
        
    }
    
}
