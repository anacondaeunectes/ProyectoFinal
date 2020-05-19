package controlador;

import javax.swing.JPanel;
import modelo.Modelo;
import vista.Interfaz_Admin;

/**
 * Clase abstracta que sirve como modelo para los controladores.
 * @author anaco
 */
public abstract  class Controlador implements ControladorInterfaz{

    Interfaz_Admin vista;
    
    Modelo modelo;

    /**
     * Constructor por defecto de los controladores. Recoge la vista a usar y el modelo logico a seguir
     * @param vista Vista a controlar por el controlador
     * @param modelo Modelo logico a seguir
     */
    public Controlador(Interfaz_Admin vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
    
    /**
     * Este metodo permite cambiar entre los paneles de la vista. Como los controladores se han estructurado de manera que existe un controlador por cada menu, tiene sentido que todos los controladores que hereden de este hereden este metodo para poder "llamar" a sus paneles.
     * @param panel Panel a presentar
     */
    public void switchPanels (JPanel panel){
        this.vista.jLayeredPane1.removeAll();
        this.vista.jLayeredPane1.add(panel);
        this.vista.jLayeredPane1.repaint();
        this.vista.jLayeredPane1.revalidate();
    }
    
}
