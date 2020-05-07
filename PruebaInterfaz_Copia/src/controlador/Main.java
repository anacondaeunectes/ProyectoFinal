/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import vista.Interfaz_Admin;

/**
 *
 * @author anaco
 */
public class Main {
    
     public static void main(String[] args) {
        //ejecuta el controlador y este la vista
        new Controlador_Admin( new Interfaz_Admin() ).iniciar() ;
    }
    
}
