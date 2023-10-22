/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package logica;

import java.text.SimpleDateFormat;
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
        Categoria c1 = control.traerCategoria("Cat1");
        assertEquals(c1.getNombre(), nombre);

        Categoria c2 = new Categoria("Cat2");
        
        c2.setListaActividad(null);
        
        assertEquals(c2.getNombre(), "Cat2");
        assertEquals(c2.getListaActividad(), null);
        
        Categoria c3 = new Categoria("Cat3", null);
        
        assertEquals(c3.getNombre(), "Cat3");        
	}
    
    @Test
    public void testAltaDepartamento() throws Exception {
        System.out.println("AltaDeDepartamento");
        String nombre = "Rocha";
        String descripcion = "Rocha de fiesta";
        String url = "www.rocha.gub.uy";
        
        control.AltaDeDepartamento(nombre,  descripcion,  url);
        
        
        ArrayList<String> listaDeptos2 = control.listaDeptos();
        

        assertEquals("Coinciden los nombres",listaDeptos2.get(0),"Rocha" );
        
        System.out.println("testGetYSet");
        String nombre3 = "Salto";
        String descripcion3 = "Salto de fiesta";
        String url3 = "www.salto.gub.uy";
        
        Departamento d = new Departamento();
        d.setNombre(nombre3);
        d.setDescripcion(descripcion3);
        d.setUrl(url3);
        d.setListaActTur(null);

        assertEquals(d.getNombre(), nombre3);
        assertEquals(d.getDescripcion(), descripcion3);
        assertEquals(d.getUrl(), url3);
        assertEquals(d.getListaActTur(), null);
        
        String nombre2 = "Salto2";
        String descripcion2 = "Salto de fiesta2";
        String url2 = "www.salto2.gub.uy";
        
        Departamento d2 = new Departamento(nombre2, descripcion2, url2, null);
        assertEquals(d2.getNombre(), nombre2);
        assertEquals(d2.getDescripcion(), descripcion2);
        assertEquals(d2.getUrl(), url2);
        

                
	}

        @Test
        public void testAltaUsuario() throws Exception {
            String nickname1 = "T1";
            String nombre1 = "T1nom";
            String apellido1 = "T1ape";
            String contrasenia1 = "T1contra";
            String correo1 = "T1@correo";
            SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
            Date fNacimiento1 = fecha.parse("23/2/1927");
            String nacionalidad1 = "T1nacio";
    
            control.AltaDeUsuarioTurista( nickname1,   nombre1,  apellido1,  contrasenia1,  correo1,  fNacimiento1,  nacionalidad1);
            
            String nickname2 = "T2";
            String nombre2 = "T2nom";
            String apellido2 = "T2ape";
            String contrasenia2 = "T2contra";
            String correo2 = "T2@correo";
            SimpleDateFormat fecha2 = new SimpleDateFormat("dd/MM/yyyy");
            Date fNacimiento2 = fecha2.parse("23/2/1927");
            String nacionalidad2 = "T2nacio";
            
            Turista t2 = new Turista();
            t2.setNickname(nickname2);
            t2.setContrasenia(contrasenia2);
            t2.setCorreo(correo2);
            t2.setNombre(nombre2);
            t2.setApellido(apellido2);
            t2.setfNacimiento(fNacimiento2);
            t2.setNacionalidad(nacionalidad2);
            
            
            
            
            
    }
    

}
