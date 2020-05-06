/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Modelo;
import vista.Interfaz_Admin;

/**
 *
 * @author anaco
 */
public class Controlador_Admin {
    
    Interfaz_Admin vista;
    
    Modelo modelo;
    
    public Controlador_Admin (Interfaz_Admin vista){
        this.vista = vista;
    }
    
}
