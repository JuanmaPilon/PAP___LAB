/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logica.exceptions.ConstraseniasDistintas;
import logica.exceptions.PaqueteSinActividad;
import logica.exceptions.PaqueteYaComprado;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import persistencia.exceptions.CorreoElectronicoExistenteException;
import persistencia.exceptions.NicknameExistenteException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author natil
 */
public class IControladorTest {
    private static IControlador control;
    public IControladorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Fabrica fabrica = Fabrica.getInstance();
        control = fabrica.getIControlador();
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testAltaCategoria() throws Exception {
        System.out.println("AltaDeCategoria");
        String nombre = "Cat1";
        control.AltaCategoria("Cat1");
        Categoria c = control.traerCategoria("Cat1");
        assertEquals("Coinciden los nombres",c.getNombre(), nombre);
        System.out.println("Ya existe nombre de Categoria");
		//esta es la prueba
                

	}

    

}
