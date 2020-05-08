/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;
import java.util.Comparator;

/**
 *
 * @author anaco
 */
public class Proyecto implements Comparator<String>{
    
    private String titulo;
    private LocalDate fecha_Inicio;
    private LocalDate fecha_Fin;
    private String descripcion;
    private int id_Proyecto;

    public Proyecto(String titulo, LocalDate fecha_Inicio, LocalDate fecha_Fin, String descripcion, int id_Proyecto) {
        this.titulo = titulo;
        this.fecha_Inicio = fecha_Inicio;
        this.fecha_Fin = fecha_Fin;
        this.descripcion = descripcion;
        this.id_Proyecto = id_Proyecto;
    }
    
    public Proyecto(String titulo){
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getFecha_Inicio() {
        return fecha_Inicio;
    }

    public LocalDate getFecha_Fin() {
        return fecha_Fin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId_Proyecto() {
        return id_Proyecto;
    }

    //Solo devuelve el titulo de cara a que el jComboBox del panel Asociar, cuyo modelo contiene los objetos Proyecto, muestre el titulo (unique key) como atributo de referencia
    @Override
    public String toString() {
        return titulo;
    }

    @Override
    public int compare(String o1, String o2) {
        return o1.compareToIgnoreCase(o2);
    }
    
    
}
