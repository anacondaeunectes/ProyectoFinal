/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.CallableStatement;
import org.junit.Test;
import static org.junit.Assert.*;
import controlador.Controlador;
import modelo.*;
import vista.Interfaz_Admin;
import controlador.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.junit.Before;

/**
 *
 * @author anaco
 */
public class ConsultasTest extends Database{
    
    private Interfaz_Admin vista;
    private Modelo modelo;
    private Controlador_Global_Admin globalController;

    public ConsultasTest() {
        this.vista = new Interfaz_Admin();
        this.modelo = new Modelo();
        this.globalController = new Controlador_Global_Admin(vista, modelo);
        globalController.iniciar();
    }

    @Test
    public void count(){
        
        int countVista = this.vista.tabla_Modificar_Empleado.getRowCount();
        int countBBDD = 0;
        
        try{
            System.out.println(modelo.getConexion());
            CallableStatement cstm = modelo.getConexion().prepareCall("{? = call contarEmpleados()}");
            cstm.registerOutParameter(1, Types.INTEGER);
            ResultSet rSet = cstm.executeQuery();
            while (rSet.next()) {                
                countBBDD = rSet.getInt(1);
            }
            
            
        }catch (SQLException ex){
            System.err.println(ex.getMessage() + "eeeee");
        }
        assertEquals(countVista, countBBDD);
    }


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
