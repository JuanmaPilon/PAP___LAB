/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import logica.DTActividad;
import logica.Fabrica;
import logica.IControlador;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author natil
 */
public class ControladorTest {
    private static IControlador control;
    
    @BeforeAll
    public static void iniciar(){
        Fabrica fabrica = Fabrica.getInstance();
        control = fabrica.getIControlador();
    }
    
    @Test
    void testActividad(){
        DTActividad dtactividad = control.traerDTActividad("Almuerzo en el Real de San Carlos");
        
        assertEquals(dtactividad.getNombre(), "Almuerzo en el Real de San Carlos");
        
    
    }
}
