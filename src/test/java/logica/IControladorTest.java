/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package logica;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.exceptions.ConstraseniasDistintas;
import logica.exceptions.PaqueteSinActividad;
import logica.exceptions.PaqueteYaComprado;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
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
        System.out.println("TestAltaDeCategoria");
        String nombre = "Cat1";
        control.AltaCategoria("Cat1");
        Categoria c1 = control.traerCategoria("Cat1");
        assertEquals(c1.getNombre(), nombre);
        ArrayList<String> listaCategoriasS = control.traerCategorias();
        assertEquals( listaCategoriasS.get(0), nombre);
        
        Categoria c2 = new Categoria("Cat2");

        Throwable exception1 = assertThrows(PreexistingEntityException.class, () -> {
            control.AltaCategoria("Cat1");
        });

        c2.setListaActividad(null);

        assertEquals(c2.getNombre(), "Cat2");
        assertEquals(c2.getListaActividad(), null);

        Categoria c3 = new Categoria("Cat3", null);

        assertEquals(c3.getNombre(), "Cat3");
    }

    @Test
    public void testAltaDepartamento() throws Exception {
        System.out.println("TestAltaDeDepartamento");
        String nombre = "Rochaa";
        String descripcion = "Rocha de fiesta";
        String url = "www.rocha.gub.uy";

        control.AltaDeDepartamento(nombre, descripcion, url);

        ArrayList<Departamento> listaDeptos1 = control.listaDepartamentos();
        assertEquals("Coinciden los nombres", listaDeptos1.get(0).getNombre(), "Rochaa");

        Throwable exception1 = assertThrows(PreexistingEntityException.class, () -> {
            control.AltaDeDepartamento(nombre, descripcion, url);
        });

        ArrayList<String> listaDeptos2 = control.listaDeptos();
        assertEquals("Coinciden los nombres", listaDeptos2.get(0), "Rochaa");

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
    public void testAltaTurista() throws Exception {
        System.out.println("testAltaTurista");
        String nickname1 = "T1";
        String nombre1 = "T1nom";
        String apellido1 = "T1ape";
        String contrasenia1 = "T1contra";
        String correo1 = "T1@correo";
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fNacimiento1 = fecha.parse("23/2/1927");
        String nacionalidad1 = "T1nacio";

        control.AltaDeUsuarioTurista(nickname1, nombre1, apellido1, contrasenia1, correo1, fNacimiento1, nacionalidad1);
        
        Usuario u = control.ConsultaDeUsuario(nickname1);
        assertEquals(u.getNickname(), nickname1);
        
        ArrayList<String> listaUsuarios = control.listaUsuarios();
        assertEquals(listaUsuarios.get(0), nickname1);
        
        ArrayList<DTUsuario> listaDTUsuario = control.traerUsuarioMod();
        assertEquals(listaDTUsuario.get(0).getNickname(), nickname1);
        assertEquals(listaDTUsuario.get(0).getNombre(), nombre1);
        assertEquals(listaDTUsuario.get(0).getApellido(), apellido1);
        assertEquals(listaDTUsuario.get(0).getContrasenia(), contrasenia1);
        listaDTUsuario.get(0).setContrasenia("contra11");
        assertEquals(listaDTUsuario.get(0).getCorreo(), correo1);
        
        SimpleDateFormat fecha111 = new SimpleDateFormat("dd/MM/yyyy");
        String fechaNacimientoFormateada = fecha111.format(fNacimiento1);
        assertEquals(listaDTUsuario.get(0).getfNacimiento(), fechaNacimientoFormateada);
        
        ArrayList<DTTurista> listaDTTurista =  control.traerUsuarioTurista();
        assertEquals(listaDTTurista.get(0).getNickname(), nickname1);
        
        DTTurista dtTurista = new DTTurista();
        dtTurista = control.traerDTTurista(nickname1);
        assertEquals(dtTurista.getNickname(), nickname1);
        
        assertEquals(dtTurista.getNickname(), nickname1);
        assertEquals(dtTurista.getNombre() , nombre1);
        assertEquals(dtTurista.getApellido() , apellido1);
        assertEquals(dtTurista.getCorreo(), correo1);
        assertEquals(dtTurista.getNacionalidad(), nacionalidad1);
        
        SimpleDateFormat fecha11 = new SimpleDateFormat("dd/MM/yyyy");
        String fechaNacimientoFormateada1 = fecha11.format(fNacimiento1);
        assertEquals(dtTurista.getfNacimiento(), fechaNacimientoFormateada1);
        
        
        
        Throwable exception1 = assertThrows(PreexistingEntityException.class, () -> {
            control.AltaDeUsuarioTurista(nickname1, nombre1, apellido1, contrasenia1, "correo11", fNacimiento1, nacionalidad1);
        });

        Throwable exception2 = assertThrows(CorreoElectronicoExistenteException.class, () -> {
            control.AltaDeUsuarioTurista("nickname", nombre1, apellido1, contrasenia1, correo1, fNacimiento1, nacionalidad1);
        });
        
        
        control.ModificarDatosDeUsuarioTurista(nickname1, nombre1, apellido1, correo1, fNacimiento1, nacionalidad1);

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
        t2.setListaCompras(null);
        t2.setListaInscripcion(null);

        assertEquals(t2.getNickname(), nickname2);
        assertEquals(t2.getContrasenia(), contrasenia2);
        assertEquals(t2.getCorreo(), correo2);
        assertEquals(t2.getNombre(), nombre2);
        assertEquals(t2.getApellido(), apellido2);
        assertEquals(t2.getfNacimiento(), fNacimiento2);
        assertEquals(t2.getNacionalidad(), nacionalidad2);
        assertEquals(t2.getListaCompras(), null);
        assertEquals(t2.getListaInscripcion(), null);

        String nickname3 = "T3";
        String nombre3 = "T3nom";
        String apellido3 = "T3ape";
        String contrasenia3 = "T3contra";
        String correo3 = "T3@correo";
        SimpleDateFormat fecha3 = new SimpleDateFormat("dd/MM/yyyy");
        Date fNacimiento3 = fecha3.parse("23/2/1927");

        Turista t3 = new Turista(nickname3, contrasenia3, nombre3, apellido3, correo3, fNacimiento3);

        assertEquals(t3.getNickname(), nickname3);

        String nickname4 = "T4";
        String nombre4 = "T4nom";
        String apellido4 = "T4ape";
        String contrasenia4 = "T4contra";
        String correo4 = "T4@correo";
        SimpleDateFormat fecha4 = new SimpleDateFormat("dd/MM/yyyy");
        Date fNacimiento4 = fecha4.parse("24/2/1927");
        String nacionalidad4 = "nacionalidad4";

        Turista t4 = new Turista(nacionalidad4, null, null, nickname4, contrasenia4, nombre4, apellido4, correo4, fNacimiento4);

        assertEquals(t4.getNickname(), nickname4);

    }

    @Test
    public void testAltaProveedor() throws Exception {
         System.out.println("testAltaProveedor");
        String nickname1 = "P1";
        String nombre1 = "P1nom";
        String apellido1 = "P1ape";
        String contrasenia1 = "P1contra";
        String correo1 = "P1@correo";
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fNacimiento1 = fecha.parse("23/2/1927");
        String descripcion1 = "P1desc";
        String link1 = "P1link";

        control.AltaDeUsuarioProveedor(nickname1, nombre1, apellido1, contrasenia1, correo1, fNacimiento1, descripcion1, link1);
        
        DTProveedor dtProveedor = new DTProveedor();
        dtProveedor = control.traerDTProveedor( nickname1);
        assertEquals(dtProveedor.getNickname(), nickname1);
        assertEquals(dtProveedor.getNombre() , nombre1);
        assertEquals(dtProveedor.getApellido() , apellido1);
        assertEquals(dtProveedor.getCorreo(), correo1);
        assertEquals(dtProveedor.getDescripcion(), descripcion1);
        assertEquals(dtProveedor.getLink(), link1);
        
        SimpleDateFormat fecha11 = new SimpleDateFormat("dd/MM/yyyy");
        String fechaNacimientoFormateada = fecha11.format(fNacimiento1);
        assertEquals(dtProveedor.getfNacimiento(), fechaNacimientoFormateada);
        
        Usuario u = control.ConsultaDeUsuario(nickname1);
        assertEquals(u.getNickname(), nickname1);

        ArrayList<String> listaProveedores = control.listaProveedores();
        assertEquals(listaProveedores.get(0), nickname1);        
        
        control.ModificarDatosDeUsuarioProveedor(nickname1, nombre1, apellido1, correo1, fNacimiento1, descripcion1, link1);
        
        String nickname2 = "P2";
        String nombre2 = "P2nom";
        String apellido2 = "P2ape";
        String contrasenia2 = "P2contra";
        String correo2 = "P2@correo";
        SimpleDateFormat fecha2 = new SimpleDateFormat("dd/MM/yyyy");
        Date fNacimiento2 = fecha2.parse("23/2/2927");
        String descripcion2 = "P2desc";
        String link2 = "P2link";

        Proveedor p2 = new Proveedor();
        p2.setNickname(nickname2);
        p2.setContrasenia(contrasenia2);
        p2.setCorreo(correo2);
        p2.setNombre(nombre2);
        p2.setApellido(apellido2);
        p2.setfNacimiento(fNacimiento2);
        p2.setDescripcion(descripcion2);
        p2.setLink(link2);
        p2.setListaActividades(null);

        assertEquals(p2.getNickname(), nickname2);
        assertEquals(p2.getContrasenia(), contrasenia2);
        assertEquals(p2.getCorreo(), correo2);
        assertEquals(p2.getNombre(), nombre2);
        assertEquals(p2.getApellido(), apellido2);
        assertEquals(p2.getfNacimiento(), fNacimiento2);
        assertEquals(p2.getLink(), link2);
        assertEquals(p2.getDescripcion(), descripcion2);
        assertEquals(p2.getListaActividades(), null);
        
       control.ValidarContrasenias(contrasenia2, contrasenia2);
        
        Throwable exception4 = assertThrows(ConstraseniasDistintas.class, () -> {
            control.ValidarContrasenias(contrasenia2, "otraContrasenia");
        });
       
        String nickname3 = "P3";
        String nombre3 = "P3nom";
        String apellido3 = "P3ape";
        String contrasenia3 = "P3contra";
        String correo3 = "P3@correo";
        SimpleDateFormat fecha3 = new SimpleDateFormat("dd/MM/yyyy");
        Date fNacimiento3 = fecha3.parse("33/3/3937");
        String descripcion3 = "P3desc";
        String link3 = "P3link";

        Proveedor p3 = new Proveedor(descripcion3, link3, null, nickname3, contrasenia3, nombre3, apellido3, correo3, fNacimiento3);
    }

    @Test
    public void testAltaActividad() throws Exception {
        System.out.println("TestAltaActividadsssssssssssssssssssssss");
        String nombre1 = "A1nom";
        String descripcion1 = "A1desc";
        int duracion1 = 1;
        float costo1 = 1;
        String ciudad1 = "A1ciu";
        TipoEstado estado1 = TipoEstado.confirmada;
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fAlta1 = fecha.parse("23/2/1927");
 
        control.AltaDeDepartamento("dAct", "deptoDesc", "deptoURL");
        control.AltaCategoria("catAct");
        ArrayList<String> listcat1 = new ArrayList();
        listcat1.add("catAct");
        
        control.AltaDeUsuarioProveedor("pAct", "pnom", "Pape", "contra", "PActcorreo",fAlta1, "PActdesc", "PActlink" );
        
        control.guardarActividad(nombre1, descripcion1, duracion1, costo1, ciudad1, fAlta1, "pAct", "dAct", listcat1);
        
        DTActividad dtasola = control.traerDTActividad(nombre1);
        
        Throwable exception1 = assertThrows(PreexistingEntityException.class, () -> {
            control.guardarActividad(nombre1, descripcion1, duracion1, costo1, ciudad1, fAlta1, "pAct", "dAct", listcat1);
        });
        
        control.cambiarEstadoActividad(nombre1, estado1);
        //ArrayList<Actividad> listaActConfirmadas = control.listaActividadesConfirmadas();
        String nomDepto = control.traerDepartamentoSalida(nombre1);
        ArrayList<String> listaActConfirmadasDepto = control.listaActividadesTuristicasConfirmadas("dAct");
        
        ArrayList<Actividad> listaActConfirmadasAct = control.listaActividadesConfirmadasDepartamento("dAct");
        
       ArrayList<String> listaActividadesProveedorConfirmadas = control.listaActividadesProveedorConfirmadas("pAct");
        
        Actividad a = new Actividad();
        a.setListaCategoria(null);
        a = control.ConsultaActividadTuristica(nombre1);
        a.setListaPaquete(null);
        a.setListaSalidaTuristica(null);
        a.getListaPaquete();
        a.getListaSalidaTuristica();
        
        //System.out.println("acaaaa"+a.getDepartamento().getNombre());
        ArrayList<String> listaAct = control.listaActividades();
        ArrayList<String> listaAct2 = control.listaActividadesTuristicas("dAct");
        
        
        ArrayList<DTActividad> dta = control.traerActividadesDelProveedor("pAct");        
        ArrayList<DTActividad> dta2 = control.encontraActividadDepartamento("dAct");
        assertEquals(a.getCiudad(), dta.get(0).getCiudad());
        //assertEquals(a.getCosto(), dta.get(0).getCosto());
        assertEquals(a.getDepartamento().getNombre(), dta.get(0).getNombreDepartamento());
        assertEquals(a.getDescripcion(), dta.get(0).getDescripcion());
        assertEquals(a.getEstado(), TipoEstado.confirmada);
        assertEquals(a.getNombre(), dta.get(0).getNombre());
        assertEquals(a.getProveedor().getNickname(),dta.get(0).getNombreProveedor());
        float tolerancia = 0.0001f;
        assertEquals(a.getDuracion(), dta.get(0).getDuracion());
        assertEquals(a.getCosto(), dta.get(0).getCosto(), tolerancia);
        Actividad a1= new Actividad("act11nom", "act2desc", 2, 2, "act2ciu", fAlta1, null, null, a.getDepartamento(), a.getProveedor());
        Actividad a2 = new Actividad("act22nom", "act22desc", 2, 2, "act22ciu",estado1, fAlta1, null, null, a.getDepartamento(), a.getProveedor(), a.getListaCategoria());
        a.setListaSalidaTuristica(null);
        a.setListaPaquete(null);
        SimpleDateFormat fechaA = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat fechadtA = new SimpleDateFormat("dd/MM/yyyy");
        String fAlta = fechaA.format(a.getfAlta());
        String fAlta2 = fechadtA.format(dta.get(0).getfAlta());
        assertEquals(fAlta, fAlta2);
    }
        @Test
        public void testAltaSalidaTuristica() throws Exception {
        System.out.println("TestAltaSalidaTursitica");
        
        String nombre1 = "Salnom";
        String lugar1 = "Sallugar";
        
        SimpleDateFormat fecha0 = new SimpleDateFormat("dd/MM/yyyy");
        Date fAlta1 = fecha0.parse("23/2/1927");
        SimpleDateFormat fecha1 = new SimpleDateFormat("dd/MM/yyyy");
        Date fSalida1 = fecha1.parse("23/2/1928");
 
        control.AltaDeDepartamento("dActSal", "deptoDesc", "deptoURL");
        control.AltaCategoria("catActSal");
        ArrayList<String> listcat1 = new ArrayList();
        listcat1.add("catActSal");
        
        control.AltaDeUsuarioProveedor("pActSal", "pnom", "Pape", "contra", "PActSalcorreo",fAlta1, "PActdesc", "PActlink" );
        
        control.guardarActividad("ActSal", "ActSalDesc", 1, 1, "ActSalCiu", fAlta1, "pActSal", "dActSal", listcat1);
        
        control.AltaSalidaTuristica("Sal", 2, fAlta1, fSalida1, lugar1,"ActSal" );
        
        Throwable exception1 = assertThrows(PreexistingEntityException.class, () -> {
            control.AltaSalidaTuristica("Sal", 2, fAlta1, fSalida1, lugar1,"ActSal" );
        });
        
        SalidaTuristica s = control.ConsultaSalidaTuristica("Sal");
        
        ArrayList<DTSalidaTuristica> dts = control.encontraSalidasTuristicasDeActividad("ActSal");
        ArrayList<String> listaSalidaAct = control.listaSalActividadTuristica("ActSal");
        DTSalidaTuristica dtsnull = new DTSalidaTuristica();
        
        assertEquals(s.getNombre(), dts.get(0).getNombre());
        assertEquals(s.getActividad().getNombre(), dts.get(0).getNombreActividad());
        assertEquals(s.getCantMax(), dts.get(0).getCantMax());
        assertEquals(s.getLugar(), dts.get(0).getLugar());
        SimpleDateFormat fechaS1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat fechadtS2 = new SimpleDateFormat("dd/MM/yyyy");
        String fAlta = fechaS1.format(s.getfAlta());
        String fAlta2 = fechadtS2.format(dts.get(0).getfAlta());
        assertEquals(fAlta, fAlta2);
        fAlta = fechaS1.format(s.getfSalida());
        fAlta2 = fechadtS2.format(dts.get(0).getfSalida());
        assertEquals(fAlta, fAlta2);
        
        assertTrue(control.salidaTuristicaLlena("Sal", 3));
        
        DTSalidaTuristica dts2 = control.traerDTSalidaTuristica("Sal");
        
        ArrayList<DTSalidaTuristica> listaDtSalida = control.traerSalidasDelProveedor("pActSal");
        
        assertEquals(dts2.getNombre(), listaDtSalida.get(0).getNombre());
        
        }    
        @Test
        public void testAltaPaquete() throws Exception {
            System.out.println("TestAltaPaquetessssssssssssssssssssssss");
            
            SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
            Date fAlta1 = fecha.parse("23/2/1927");
 
            control.AltaDeDepartamento("dActPaq", "deptoDesc", "deptoURL");
            control.AltaCategoria("catActPaq");
            ArrayList<String> listcat1 = new ArrayList();
            listcat1.add("catActPaq");
            control.AltaDeUsuarioProveedor("pActSalPaq", "pnom", "Pape", "contra", "PActSalPaqcorreo",fAlta1, "PActdesc", "PActlink" );
            control.guardarActividad("ActSalPaq", "ActSalPaqDesc", 1, 1, "ActSalPaqCiu", fAlta1, "pActSalPaq", "dActPaq", listcat1);
            control.AltaSalidaTuristica("SalPaq", 2, fAlta1, fAlta1, "ActSalPaqCiu","ActSalPaq" );
            
            control.crearPaqueteActividadTuristica("Paq", "PaqDesc", 10, fAlta1, 20);
            control.asignarActividadPaquete( "Paq", "ActSalPaq");
            
            Throwable exception1 = assertThrows(PreexistingEntityException.class, () -> {
                control.crearPaqueteActividadTuristica("Paq", "PaqDesc", 10, fAlta1, 20);
        });
            
            Throwable exception2 = assertThrows(NullPointerException.class, () -> {
                 control.asignarActividadPaquete( "Paq", "ActSalPaq111");
        });
            
            DTPaquete dtpaq = control.traerDTPaquete("Paq");
            
            List<Paquete> listPaqS = control.consultaPaquetes();
            ArrayList<String> listaPaqS = control.listaPaquetes();
            
            assertEquals(dtpaq.getNombre(), (String) listPaqS.get(0).getNombre());
            assertEquals(dtpaq.getNombre(), listaPaqS.get(0));
            
            ArrayList<String> listaActPaqS = control.listaPaquetesDeActividad("ActSalPaq");
            
            assertEquals("Paq", listaActPaqS.get(0));
            
            ArrayList<DTPaquete> listaDTPaq = control.traerListaDTPaquetes();
            
            assertEquals(dtpaq.getDescripcion(), listaDTPaq.get(0).getDescripcion());
            assertEquals(dtpaq.getDescuento(), listaDTPaq.get(0).getDescuento()); 
            assertEquals(dtpaq.getValidez(), listaDTPaq.get(0).getValidez());
            
            
    }
}//fin
