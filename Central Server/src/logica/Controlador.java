package logica;

import logica.exceptions.ImagenPorNicknameNoExite;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static logica.TipoEstado.agregada;
import logica.exceptions.ConstraseniasDistintas;
import logica.exceptions.NoExisteCompra;
import logica.exceptions.PaqueteSinActividad;
import logica.exceptions.PaqueteYaComprado;
import persistencia.ControladoraPersistencia;
import persistencia.exceptions.CorreoElectronicoExistenteException;
import persistencia.exceptions.NicknameExistenteException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

public class Controlador implements IControlador {

    public Controlador() {
    }

//    private static Controlador control;
//    public static Controlador getInstance(){
//        if (control == null){
//            control = new Controlador();
//        }
//            return control;
//    };
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    //descomentado por una prueba:
    @Override
    public void nuevaCantTurista(Long id, int nuevaCantTurista) throws NoExisteCompra {
        Compra c = controlPersis.traerCompra(id);
        c.setCantTuristas(nuevaCantTurista);

        try {
            controlPersis.editarCompra(c);
        } catch (Exception ex) {
            throw new NoExisteCompra("La Compra id no existe");
        }

    }

    @Override
    public DTCompra traerCompraDelTurista(String nombreTurista, String nombrePaquete) {
        Compra c = controlPersis.traerCompraDelTurista(nombreTurista, nombrePaquete);
        //(Long id, String nicknameTurista, Date fCompra, int cantTuristas, float costoTotal, Date vencimiento, String nombrePaquete)
        DTCompra dtc = new DTCompra(c.getId(), c.getTurista().getNickname(), c.getfCompra(), c.getCantTuristas(),
                c.getCostoTotal(), c.getVencimiento(), c.getPaquete().getNombre());

        return dtc;
    }

    @Override
    public DTImagenActividad buscarImagenPorActividad(String nombreActividad) {
        imagenActividad imagen = controlPersis.buscarImagenActividad(nombreActividad);
        DTImagenActividad dtImagen = new DTImagenActividad(imagen.getNombre(), imagen.getnombreActividad(), imagen.getUrlVideo());
        return dtImagen;

    }

    @Override
    public DTImagenPerfil buscarImagenPorNickname(String nickname) throws ImagenPorNicknameNoExite {

        try {
            ImagenPerfil imagen = controlPersis.buscarImagen(nickname);
            DTImagenPerfil dtImagen = new DTImagenPerfil(imagen.getNombre(), imagen.getNicknameUsuario());
            return dtImagen;
        } catch (Exception e) {

            throw new ImagenPorNicknameNoExite("No hay imagen para dicha actividad");
        }

    }

    public void ModificarImagenPerfil(String imagenNombre, String nicknameUsuario) throws PreexistingEntityException {
        ImagenPerfil imagenPerfil = new ImagenPerfil(imagenNombre, nicknameUsuario);
        controlPersis.modificarImagenPerfil(imagenPerfil);
    }

    @Override
    public void AltaDeImagenPerfil(String imagenNombre, String nicknameUsuario) throws PreexistingEntityException {
        try {
            ImagenPerfil imagenPerfil = new ImagenPerfil(imagenNombre, nicknameUsuario);
            controlPersis.guardarImagenPerfil(imagenPerfil);
        } catch (PreexistingEntityException e) {
            throw new PreexistingEntityException("Imagen ya en uso por otro usuario");
        }

    }

    @Override
    public void AltaDeImagenActividad(String imagenNombre, String nombreActividad, String UrlVideo) throws PreexistingEntityException {

        imagenActividad ImagenActividad = new imagenActividad(imagenNombre, nombreActividad, UrlVideo);
        controlPersis.guardarImagenActividad(ImagenActividad);

    }

    @Override
    public void AltaDeUsuarioTurista(String nickname, String nombre, String apellido, String contrasenia, String correo,
            Date fNacimiento, String nacionalidad) throws NicknameExistenteException, PreexistingEntityException, CorreoElectronicoExistenteException {

        Turista turista = new Turista();
        turista.setNickname(nickname);
        turista.setNombre(nombre);
        turista.setApellido(apellido);
        turista.setCorreo(correo);
        turista.setfNacimiento(fNacimiento);
        turista.setNacionalidad(nacionalidad);
        turista.setContrasenia(contrasenia);

        try {
            controlPersis.guardarTurista(turista);
        } catch (NicknameExistenteException ex) {
            throw new NicknameExistenteException("Nickname ya en uso por un usuario");
        } catch (CorreoElectronicoExistenteException e) {
            throw new CorreoElectronicoExistenteException("Correo electrónico ya en uso por un usuario ");
        }
    }

    ;
   
    @Override
    public List<DTPaquete> traerListaDTPaquetes() {
        List<Paquete> listaPaquetes = controlPersis.consultaPaquete();
        List<DTPaquete> listaDtPaquetes = new ArrayList();

        for (Paquete p : listaPaquetes) {
            DTPaquete dtPaquete = new DTPaquete(p.getNombre(), p.getDescripcion(), p.getValidez(), p.getDescuento(), p.getFechaAlta());
            listaDtPaquetes.add(dtPaquete);
        }
        return listaDtPaquetes;
    }

    ;
   
    @Override
    public void AltaDeUsuarioProveedor(String nickname, String nombre, String apellido, String contrasenia, String correo,
            Date fNacimiento, String descripcion, String link) throws CorreoElectronicoExistenteException, NicknameExistenteException, PreexistingEntityException {
        Proveedor proveedor = new Proveedor();
        proveedor.setNickname(nickname);
        proveedor.setNombre(nombre);
        proveedor.setApellido(apellido);
        proveedor.setCorreo(correo);
        proveedor.setfNacimiento(fNacimiento);
        proveedor.setDescripcion(descripcion);
        proveedor.setLink(link);
        proveedor.setContrasenia(contrasenia);

        try {
            controlPersis.guardarProveedor(proveedor);
        } catch (NicknameExistenteException ex) {
            throw new NicknameExistenteException("Nickname ya en uso por un usuario");
        } catch (CorreoElectronicoExistenteException e) {
            throw new CorreoElectronicoExistenteException("Correo electrónico ya en uso por un usuario ");
        }
    }

    @Override
    public void AltaCategoria(String nombre) throws PreexistingEntityException, Exception {

        Categoria cat = new Categoria();
        cat.setNombre(nombre);

        controlPersis.guardarCategoria(cat);

    }

    ;
    //String nombreProveedor, String nombreDep,
    @Override
    public void guardarActividad(String nombreActividad, String descripcionActividad, int duracionActividad, float costoActividad, String nombreCuidad, Date fecha, String nombreProveedor, String nombreDepartamento, List<String> listaCategorias) throws PreexistingEntityException {
        Actividad actividad = new Actividad();
        actividad.setCiudad(nombreCuidad);
        actividad.setNombre(nombreActividad);
        actividad.setDescripcion(descripcionActividad);
        actividad.setDuracion(duracionActividad);
        actividad.setCosto(costoActividad);
        actividad.setfAlta(fecha);
        actividad.setEstado(TipoEstado.agregada);

        Departamento dep = new Departamento();//creo dep auxiliar
        dep = controlPersis.traerDepartamento(nombreDepartamento);// encuentro el departamento y lo cargo en dep

        actividad.setDepartamento(controlPersis.traerDepartamento(nombreDepartamento));// hago que mi actividad apunte al departamento que me traje
        //idem con proveedor

        try {
            Proveedor pro = new Proveedor();
            pro = controlPersis.traerProveedor(nombreProveedor);
            actividad.setProveedor(pro);

            controlPersis.guardarActividad(actividad);
        } catch (PreexistingEntityException e) {
            throw new PreexistingEntityException("Ya existe ua actividad con ese nombre");
        }
        //asingnar categoria
        for (String nombre : listaCategorias) {
            //Categoria c = traerCategoria(nombre);
            controlPersis.asignarCategoriaActividad(nombre, nombreActividad);

        }
    }

    @Override
    public void AltaSalidaTuristica(String nombre, int cantMax, Date fAlta, Date fSalida, String lugar, String nombreActividad) throws PreexistingEntityException {
        SalidaTuristica salidaTuristica = new SalidaTuristica();
        salidaTuristica.setNombre(nombre);
        salidaTuristica.setCantMax(cantMax);
        salidaTuristica.setfAlta(fAlta);
        salidaTuristica.setfSalida(fSalida);
        salidaTuristica.setLugar(lugar);

        Actividad actividad = controlPersis.consultaActividad(nombreActividad);
        salidaTuristica.setActividad(actividad);
        actividad.getListaSalidaTuristica().add(salidaTuristica);
        try {
            controlPersis.guardarSalidaTuristica(salidaTuristica, actividad);
        } catch (PreexistingEntityException e) {
            throw new PreexistingEntityException("Ya existe un departamento con ese nombre");
        }
    }
////////////

    @Override
    public ArrayList<String> listaActividades() {//tiene el nombre de los departamentos, no el objeto
        return controlPersis.listaActividades();
    }

    ;
   @Override
    public List<String> listaPaquetes() {//tiene el nombre de los departamentos, no el objeto
        return controlPersis.listaPaquetes();
    }

    ;
   ///////
   @Override
    public ArrayList<String> listaSalActividadTuristica(String actividad) {

        return controlPersis.listaSalActividadTuristica(actividad);
    }

    ;
   
   @Override
    public ArrayList<Departamento> listaDepartamentos() {
        return controlPersis.listaDepartamentos();
    }

    @Override
    public ArrayList<DTDepartamento> listaDTDepartamentos() {
        ArrayList<Departamento> listaDepartamentos = controlPersis.listaDepartamentos();
        ArrayList<DTDepartamento> listaDTDepartamentos = new ArrayList<>();

        for (Departamento departamento : listaDepartamentos) {
            List<Actividad> listaActTur = departamento.getListaActTur();
            DTDepartamento dtDepartamento = new DTDepartamento(departamento.getNombre(), departamento.getDescripcion(), departamento.getUrl());
            listaDTDepartamentos.add(dtDepartamento);
        }

        return listaDTDepartamentos;
    }

    ;
   @Override
    public ArrayList<String> listaDeptos() {//tiene el nombre de los departamentos, no el objeto
        ArrayList<String> nicks = new ArrayList();

        List<Departamento> deptos = controlPersis.traerDepartamentos();
        for (Departamento d : deptos) {

            nicks.add(d.getNombre());
        }
        return nicks;
    }

    ;
   @Override
    public ArrayList<String> listaActividadesTuristicas(String departamento) {
        ArrayList<String> listaActividadesTuristicas = new ArrayList();
        for (String s : controlPersis.listaActividadesTuristicas(departamento)) {
            listaActividadesTuristicas.add(s);
        }

        return listaActividadesTuristicas;
    }

    ;
   
   @Override
    public ArrayList<String> listaUsuarios() {
        return controlPersis.listaUsuarios();
    }

    ;
   
    @Override
    public ArrayList<String> listaProveedores() {
        return controlPersis.listaProveedores();
    }

    ;
 
   @Override
    public Usuario ConsultaDeUsuario(String nickname) {
        return controlPersis.consultaUsuario(nickname);
    }

    ;
   

   
   
   @Override
    public void AltaDeActividadTuristica(String nombre, TipoEstado estado, String descripcion, int duracion, float costo, String ciudad, Date fAlta, ArrayList<SalidaTuristica> listaSalidaTuristica, ArrayList<Paquete> listaPaquete, ArrayList<Categoria> listaCategoria) {

        Actividad actividad = new Actividad();

        //falta control si nombre ya existe o si es null nombre
        actividad.setNombre(nombre);
        actividad.setEstado(TipoEstado.agregada);
        actividad.setDescripcion(descripcion);
        actividad.setDuracion(duracion);
        actividad.setCosto(costo);
        actividad.setCiudad(ciudad);
        actividad.setfAlta(fAlta);
        actividad.setListaSalidaTuristica(listaSalidaTuristica);
        actividad.setListaPaquete(listaPaquete);
        actividad.setListaCategoria(listaCategoria);

        //controlPersis.guardarActividad(actividad);
    }

    ;//Juanma
   
   //Devuelve la Salida Turistica con nombre nombreSalida
   @Override
    public DTSalidaTuristica ConsultaSalidaTuristica(String nombreSalida) {
        SalidaTuristica salida = controlPersis.consultaSalida(nombreSalida);
        DTSalidaTuristica dtSalida = new DTSalidaTuristica(salida.getNombre(), salida.getCantMax(), salida.getfAlta(), salida.getfSalida(), salida.getLugar(), salida.getActividad().getNombre());
        return dtSalida;
    }

    ;
    
    //Alta de Departamento. No requiere GUI.
    @Override
    public void AltaDeDepartamento(String nombre, String descripcion, String url) throws PreexistingEntityException, Exception {

        Departamento depto = new Departamento();
        depto.setNombre(nombre);
        depto.setDescripcion(descripcion);
        depto.setUrl(url);

        try {
            controlPersis.guardarDepartamento(depto);
        } catch (PreexistingEntityException e) {
            throw new PreexistingEntityException("Ya existe un departamento con ese nombre");
        }
    }

    ;
   
   @Override
    public List<String> llenarCmboBoxDep() {
        return controlPersis.llenarCmboBoxDepPersis();
    }

    @Override
    public void crearPaqueteActividadTuristica(String nombreDePaquete, String descripcionDePaquete, int validezDePaquete, Date altaDePaquete, int descuentoDePaquete) throws PreexistingEntityException, Exception {
        Paquete paquete = new Paquete();
        paquete.setNombre(nombreDePaquete);
        paquete.setDescripcion(descripcionDePaquete);
        paquete.setValidez(validezDePaquete);
        paquete.setDescuento(descuentoDePaquete);
        paquete.setFechaAlta(altaDePaquete);//le seteo fechaAlta al paquete
        try {
            controlPersis.guardarPaqueteActividadTuristica(paquete);
        } catch (PreexistingEntityException e) {
            throw new PreexistingEntityException("Ya existe un paquete con ese nombre");
        }
    }

    //Lista auxiliar de DTUsuarios para mostrar los usuarios (turista y proveevor) registrados en la BD.
    @Override
    public ArrayList<DTUsuario> traerUsuarioMod() {
        return controlPersis.traerUsuarios();
    }

    @Override
    public DTUsuario traerDTUsuario(String nickname) {
        Usuario usuario = controlPersis.consultaUsuario(nickname);

        if (usuario instanceof Turista) {
            DTTurista dtTurista = traerDTTurista(nickname);
            dtTurista.setListaUsuariosFavoritas(usuario.getListaUsuariosFavoritas());
            return dtTurista;
        } else {
            DTProveedor dtProveedor = traerDTProveedor(nickname);
            dtProveedor.setListaUsuariosFavoritas(usuario.getListaUsuariosFavoritas());
            return dtProveedor;
        }

    }

    @Override
    public String devolverTipoUsuario(String nickname) {
        Usuario usuario = controlPersis.consultaUsuario(nickname);
        if (usuario instanceof Turista) {
            return "turista";
        } else {
            return "proveedor";
        }
    }

    //Lista auxiliar para traer los DT de Turista registrados en la BD.
    @Override
    public ArrayList<DTTurista> traerUsuarioTurista() {
        return controlPersis.traerUsuariosTurista();
    }

    //Devuelve DTTurista del Turista a partir del nickname
    @Override
    public DTTurista traerDTTurista(String nickname) {

        Turista t = controlPersis.traerTurista(nickname);
        //conversion date a String
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fnac = sdf.format(t.getfNacimiento());

        return new DTTurista(t.getNickname(), t.getNombre(), t.getApellido(), t.getCorreo(),
                fnac, t.getNacionalidad(), t.getContrasenia());
    }
    //Devuelve DTProveedor del Proveedor a partir del nickname

    @Override
    public DTProveedor traerDTProveedor(String nickname) {
        Proveedor t = controlPersis.traerProveedor(nickname);
        //conversion date a String
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fnac = sdf.format(t.getfNacimiento());

        return new DTProveedor(t.getNickname(), t.getNombre(), t.getApellido(), t.getCorreo(),
                fnac, t.getContrasenia(), t.getDescripcion(), t.getLink());
    }

    //Devuelve el DTSalidaTuristica a partir del nombre de la Salida Turistica
    @Override
    public DTSalidaTuristica traerDTSalidaTuristica(String nombreSalida) {
        SalidaTuristica salida = controlPersis.consultaSalida(nombreSalida);
        DTSalidaTuristica dtSalida = new DTSalidaTuristica(salida.getNombre(), salida.getCantMax(),
                salida.getfAlta(), salida.getfSalida(), salida.getLugar(), salida.getActividad().getNombre());

        return dtSalida;
    }

    @Override
    public DTImagenActividad traerDTImagenActividad(String nombreActividad) {
        imagenActividad imagen = controlPersis.buscarImagenActividad(nombreActividad);
        if (imagen != null) {
            DTImagenActividad dtImagen = new DTImagenActividad(imagen.getNombre(), imagen.getnombreActividad(), imagen.getUrlVideo());
            return dtImagen;
        } else {
            return null;
        }
    }

    //Persiste la modificacion de Usuario Proveedor. nickname y correo no se modifican. 
    @Override
    public void ModificarDatosDeUsuarioProveedor(String nickname, String nombre, String apellido, String correo, Date fecha, String descripcion, String url) {
        Proveedor p = (Proveedor) ConsultaDeUsuario(nickname);

        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setDescripcion(descripcion);
        p.setLink(url);
        p.setfNacimiento(fecha);

        controlPersis.modificarProveedor(p);
    }

    //Persiste la modificacion de Usuario Turista. nickname y correo no se modifican. 
    @Override
    public void ModificarDatosDeUsuarioTurista(String nickname, String nombre, String apellido, String correo, Date fecha, String nacionalidad) {
        Turista t = (Turista) ConsultaDeUsuario(nickname);

        t.setNombre(nombre);
        t.setApellido(apellido);
        t.setNacionalidad(nacionalidad);
        t.setfNacimiento(fecha);

        controlPersis.modificarTurista(t);
    }

    //
//    @Override
//    public List<String> findSalidasTuristicasDepartamento(String departamentoSeleccionado) {
//        return controlPersis.findSalidasTuristicasDepartamentoPersis(departamentoSeleccionado);
//    }
    //Devuelve lista de DT Actividad para un nombre de Departamento dado
    @Override
    public ArrayList<DTActividad> encontraActividadDepartamento(String departamentoSeleccionado) {
        return controlPersis.encontraActividadDepartamentoPersis(departamentoSeleccionado);
    }

    //Devuelve lista de DT Salida Turistica para un nombre de Actividad dado
    @Override
    public ArrayList<DTSalidaTuristica> encontraSalidasTuristicasDeActividad(String actividadSeleccionado) {
        return controlPersis.encontraSalidasTuristicasDeActividadPersis(actividadSeleccionado);
    }

    //
    @Override
    public void asignarActividadPaquete(String paqueteSeleccionado, String actividadSeleccionada) throws NonexistentEntityException, Exception {
        try {
            controlPersis.asignarActividadPaquetePersis(paqueteSeleccionado, actividadSeleccionada);
        } catch (NonexistentEntityException ex) {
            throw new NonexistentEntityException("El paquete con ese nombre no existe");
        }
    }

    //Chequea si ya no se supero el limite de Inscripcion para un nombre Salida Turistica. 
    //Devuelve true la Salida Turistica esta llena o se supera el max de la cantidad de turista para la Salida Turistica. 
    //Devuelve false se puede Inscribir la cantidad a Incribir a la Salida Turistica
    @Override
    public boolean salidaTuristicaLlena(String salida, int cantAInscribir) {

        boolean resultado = true;
        int cantTotal = 0;
        //busco los obj salidaTuristica
        SalidaTuristica salidaTuristica = controlPersis.consultaSalida(salida);
        //busco las inscripciones para ese obj salidaTuristica
        ArrayList<Inscripcion> inscripcionesSalidaTuristica = controlPersis.listarInscripcionesDeSalidaTuristica(salida);
        //me fijo en las inscripciones la cantidad de inscriptos
        for (Inscripcion i : inscripcionesSalidaTuristica) {
            cantTotal = cantTotal + i.getCantTurista();
        }
        //controlo cantidad actual de inscriptos y la cantidad a inscribir no supere el max de la salida turistica
        if ((cantTotal + cantAInscribir) <= salidaTuristica.getCantMax()) {
            resultado = false;//no llena se puede inscribir
        }

        return resultado;
    }

    //chequea si el turista ya esta inscripto a la Salida Turistica. 
    //Devuelve true si el Turistica esta ya inscripto a la Salida Turistica. 
    //Devuelve false si el Turistica se puede Inscribir a la Salida Turistica
    @Override
    public boolean turistaYaInscriptoSalidaTuristica(String salida, String turistaAlta) {
        boolean resultado = false;
        //busco los obj de turista y salidaTuristica
        Turista turista = (Turista) ConsultaDeUsuario(turistaAlta);
        //me fijo en la lista de inscripciones del turista por si ya esta inscripto. obs.: no controlo fechas
        for (Inscripcion insc : turista.getListaInscripcion()) {
            if (insc.getSalida().getNombre().equals(salida)) {
                return true;
            }
        }

        return resultado;
    }

    //Alta de Inscripcion. Pre-condicion: exite salida turistica y turista con los respectivos nombres; los parametros vienen en el formato correcto.
    @Override
    public void InscripcionASalidaTuristica(String nombreSalidaSeleccionada, String nicknameTurista, int cantTurista, float costo, Date fecha, TipoPago tipoPago) {
        SalidaTuristica salida = controlPersis.consultaSalida(nombreSalidaSeleccionada);;
        Turista turista = (Turista) ConsultaDeUsuario(nicknameTurista);
        Inscripcion inscripcion = new Inscripcion(turista, salida, fecha, cantTurista, costo, tipoPago);

        controlPersis.guardarInscripcion(inscripcion);
    }

    //Devuelve una lista de salidas turisticas a la que se inscribio el turista con el nickname de entrada.
    @Override
    public ArrayList<DTSalidaTuristica> traerInscSalidasDeTurista(String nickname) {
        Turista t = (Turista) ConsultaDeUsuario(nickname);
        ArrayList<DTSalidaTuristica> listaInscSalidasDeTurista = new ArrayList();

        //DTSalidaTuristica(String nombre, int cantMax, Date fAlta, Date fSalida, String lugar) 
        for (Inscripcion insc : t.getListaInscripcion()) {
            DTSalidaTuristica dtSalida = new DTSalidaTuristica(insc.getSalida().getNombre(), insc.getSalida().getCantMax(),
                    insc.getSalida().getfAlta(), insc.getSalida().getfSalida(), insc.getSalida().getLugar(),
                    insc.getSalida().getActividad().getNombre());

            listaInscSalidasDeTurista.add(dtSalida);
        }
        return listaInscSalidasDeTurista;
    }

    @Override
    public ArrayList<DTActividad> traerInscActividadesDeTurista(String nickname) {
        Turista t = (Turista) ConsultaDeUsuario(nickname);
        ArrayList<DTActividad> listaInscActividadesDeTurista = new ArrayList();
        ArrayList<Actividad> actividades = new ArrayList();

        //DTActividad(String nombre, String descripcion, int duracion, float costo, String ciudad, Date fAlta, String nombreProveedor)
        //armo una lista auxiliar con las actividades de las salidas a la que se inscribio el turista
        for (Inscripcion insc : t.getListaInscripcion()) {
            if (!actividades.contains(insc.getSalida().getActividad())) {
                actividades.add(insc.getSalida().getActividad());

            }
        }
        //paso la lista de actividades a DT (asi no se repite la actividad, si el turista esta inscpto a dos salidas de la misma actividad.

        for (Actividad a : actividades) {
            DTActividad dtActividad = new DTActividad(a.getNombre(), a.getDescripcion(),
                    a.getDuracion(),
                    a.getCosto(), a.getCiudad(),
                    a.getfAlta(),
                    a.getEstado(),
                    a.getDepartamento().getNombre(),
                    a.getProveedor().getNickname());

            listaInscActividadesDeTurista.add(dtActividad);
        }
        return listaInscActividadesDeTurista;
    }

    @Override
    public ArrayList<DTActividad> traerActividadesDelProveedor(String nickname) {

        List<Actividad> listaActividades = controlPersis.traerActividades();
        ArrayList<DTActividad> listaActividadesDelProveedor = new ArrayList();
        for (Actividad a : listaActividades) {
            if (a.getProveedor().getNickname().equals(nickname)) {
                DTActividad dtactividad = new DTActividad(a.getNombre(), a.getDescripcion(),
                        a.getDuracion(),
                        a.getCosto(), a.getCiudad(),
                        a.getfAlta(),
                        a.getEstado(),
                        a.getDepartamento().getNombre(),
                        a.getProveedor().getNickname());
                listaActividadesDelProveedor.add(dtactividad);
            }

        }
        return listaActividadesDelProveedor;

    }

    @Override
    public ArrayList<DTSalidaTuristica> traerSalidasDelProveedor(String nickname) {
        List<SalidaTuristica> listaSalidasTuristicas = controlPersis.traerSalidasTuristicas();
        ArrayList<DTSalidaTuristica> listaSalidasDelProveedor = new ArrayList();

        for (SalidaTuristica s : listaSalidasTuristicas) {
            if (s.getActividad().getProveedor().getNickname().equals(nickname)) {
                DTSalidaTuristica dtSalida = new DTSalidaTuristica(s.getNombre(), s.getCantMax(),
                        s.getfAlta(), s.getfSalida(), s.getLugar(), s.getActividad().getNombre());
                listaSalidasDelProveedor.add(dtSalida);
            }
        }

        return listaSalidasDelProveedor;
    }

    @Override
    public String traerDepartamentoSalida(String nombreActividad) {
        Actividad a = controlPersis.consultaActividad(nombreActividad);
        return a.getDepartamento().getNombre();
    }

    @Override
    public ArrayList<String> listaPaquetesDeActividad(String nombreActividad) {
        Actividad a = controlPersis.consultaActividad(nombreActividad);
        ArrayList<String> listaPaquetesDeActividad = new ArrayList();

        for (Paquete p : a.getListaPaquete()) {
            listaPaquetesDeActividad.add(p.getNombre());
        }

        return listaPaquetesDeActividad;
    }

    @Override
    public DTActividad traerDTActividad(String nombreActividad) {
        Actividad a = controlPersis.consultaActividad(nombreActividad);

        // Crea un nuevo objeto DTActividad usando el constructor con todos los atributos
        DTActividad dtactividad = new DTActividad(
                a.getNombre(),
                a.getDescripcion(),
                a.getDuracion(),
                a.getCosto(),
                a.getCiudad(),
                a.getfAlta(),
                a.getEstado(),
                a.getDepartamento().getNombre(),
                a.getProveedor().getNickname()
        );

        // Para listaNombresSalidaTuristica, debes convertir la lista de objetos SalidaTuristica a una lista de nombres
        List<String> listaNombresSalidaTuristica = new ArrayList<>();
        for (SalidaTuristica salida : a.getListaSalidaTuristica()) {
            listaNombresSalidaTuristica.add(salida.getNombre());
        }
        //dtactividad.setListaNombresSalidaTuristica((ArrayList<String>) listaNombresSalidaTuristica);

        // Para listaNombresPaquete, de manera similar, convierte la lista de objetos Paquete a una lista de nombres
        List<String> listaNombresPaquete = new ArrayList<>();
        for (Paquete paquete : a.getListaPaquete()) {
            listaNombresPaquete.add(paquete.getNombre());
        }
        //dtactividad.setListaNombresPaquete((ArrayList<String>) listaNombresPaquete);

        // Para listaNombresCategoria, convierte la lista de objetos Categoria a una lista de nombres
        List<String> listaNombresCategoria = new ArrayList<>();
        for (Categoria categoria : a.getListaCategoria()) {
            listaNombresCategoria.add(categoria.getNombre());
        }
        //dtactividad.setListaNombresCategoria((ArrayList<String>) listaNombresCategoria);

        return dtactividad;
    }

    @Override
    public ArrayList<String> listaActividadesDelPaquete(String nombrePaquete) {
        Paquete p = controlPersis.traerPaquete(nombrePaquete);

        ArrayList<String> listaActividadesDelPaquete = new ArrayList();

        for (Actividad a : p.getListaActividades()) {
            listaActividadesDelPaquete.add(a.getNombre());
        }

        return listaActividadesDelPaquete;
    }

    @Override
    public DTPaquete traerDTPaquete(String nombrePaquete) {
        Paquete p = controlPersis.traerPaquete(nombrePaquete);

        DTPaquete dtpaquete = new DTPaquete(p.getNombre(), p.getDescripcion(), p.getValidez(), p.getDescuento(), p.getFechaAlta());

        return dtpaquete;
    }

    @Override
    public void ValidarContrasenias(String contrasenia, String confirmarContrasenia) throws ConstraseniasDistintas {

        if (!contrasenia.equals(confirmarContrasenia)) {
            throw new ConstraseniasDistintas("Las constrasenias no coinciden");
        }
    }

    @Override
    public ArrayList<String> traerCategorias() {
        return controlPersis.traerCategoria();
    }

    @Override
    public ArrayList<String> listaActividadesPorEstado(TipoEstado estado) {
        ArrayList<String> listaActividadesPorEstado = new ArrayList();

        List<Actividad> listaActividades = controlPersis.traerActividades();
        for (Actividad a : listaActividades) {
            if (a.getEstado() == estado) {
                listaActividadesPorEstado.add(a.getNombre());
            }
        }

        return listaActividadesPorEstado;
    }

    @Override
    public void cambiarEstadoActividad(String nombreActividad, TipoEstado tipoEstado) {
        Actividad a = controlPersis.consultaActividad(nombreActividad);
        a.setEstado(tipoEstado);
        controlPersis.modificarActividad(a);
    }

    @Override
    public ArrayList<String> traerCategoriasActividad(String actividad) {
        Actividad a = controlPersis.consultaActividad(actividad);
        ArrayList<String> listaCategoriasActividad = new ArrayList();
        for (Categoria c : a.getListaCategoria()) {
            listaCategoriasActividad.add(c.getNombre());
        }
        return listaCategoriasActividad;
    }

    //son la agregacion de categorias de las actividades que pertenece al paquete
    @Override
    public ArrayList<String> traerCategoriasPaquete(String paquete) {
        Paquete p = controlPersis.traerPaquete(paquete);

        ArrayList<Categoria> listaCategoriasPaquete = new ArrayList();
        ArrayList<String> listaCategoriasPaqueteString = new ArrayList();
        for (Actividad a : p.getListaActividades()) {
            for (Categoria c : a.getListaCategoria()) {

                if (!listaCategoriasPaquete.contains(c)) {
                    listaCategoriasPaquete.add(c);
                }
            }
        }
        //paso a string los nombres de las categorias
        for (Categoria c : listaCategoriasPaquete) {
            listaCategoriasPaqueteString.add(c.getNombre());
        }

        return listaCategoriasPaqueteString;

    }

    @Override
    public void CompraDePaquete(String nickname, String nombrePaquete, int cantTurista, Date fechaCompra) throws PaqueteSinActividad, PaqueteYaComprado {
        Turista t = controlPersis.traerTurista(nickname);
        Paquete p = controlPersis.traerPaquete(nombrePaquete);

        if (t.getListaCompras() != null) {
            //me fijo si ya ha comprado el paquete 
            for (Compra compra : t.getListaCompras()) {
                if (compra.getPaquete().getNombre().equals(nombrePaquete)) {
                    throw new PaqueteYaComprado("El paqeute seleccionado " + p.getNombre() + " ya fue comprado por el turista");
                }
            }

        }
        if (p.getListaActividades() == null) {
            throw new PaqueteSinActividad("El paqeute seleccionado " + p.getNombre() + " no tiene actividades agregadas");
        } else {
            float costoTotal = 0;
            float costoActividades = 0;
            for (Actividad a : p.getListaActividades()) {

                costoActividades = (float) costoActividades + a.getCosto();
            }

            Compra c = new Compra(t, p, cantTurista, fechaCompra);

            costoTotal = (float) ((costoActividades) * (0.01) * (100 - p.getDescuento()) * (cantTurista));

            c.setCostoTotal(costoTotal);

            Date fechaVencimiento = new Date();
            Calendar d = Calendar.getInstance();
            d.setTime(fechaCompra);
            d.add(Calendar.DATE, p.getValidez());
            fechaVencimiento = d.getTime();
            c.setVencimiento(fechaVencimiento);

            controlPersis.guardarCompra(c);
        }

    }

    @Override
    public ArrayList<String> listaPaquetesSinCompra() {
        ArrayList<String> listaPaquetesSinCompra = controlPersis.listaPaquetes();
        List<Compra> listaPaquetesComprados = controlPersis.listarCompras();

        //recorre las compras y va sacando los paquetes comprados de la lista "total" de paquetes
        for (Compra c : listaPaquetesComprados) {
            listaPaquetesSinCompra.remove(c.getPaquete().getNombre());
        }
        return listaPaquetesSinCompra;
    }

    @Override
    public ArrayList<String> listaActividadesTuristicasConfirmadas(String departamentoSeleccionado) {
        ArrayList<String> listaActividadesTuristicas = new ArrayList();
        for (Actividad a : controlPersis.traerActividades()) {
            if (a.getDepartamento().getNombre().equals(departamentoSeleccionado) && a.getEstado().equals(TipoEstado.confirmada)) {
                listaActividadesTuristicas.add(a.getNombre());
            }
        }

        return listaActividadesTuristicas;
    }

    @Override
    public ArrayList<DTActividad> listaActividadesProveedorConfirmadas(String nicknameProveedor) {

        ArrayList<DTActividad> listaActividadesProveedorConfirmadas = new ArrayList();
        //me traigo las actividades de la bd
        List<Actividad> listaActividades = controlPersis.traerActividades();
        //recorro la lista de actividades y agrego a la lista a devolver la que tienen el proveedor buscado y este confirmada
        for (Actividad a : listaActividades) {
            if (a.getProveedor().getNickname().equals(nicknameProveedor) && a.getEstado().equals(TipoEstado.confirmada)) {
                DTActividad dta = new DTActividad(a.getNombre(), a.getDescripcion(), a.getDuracion(), a.getCosto(), a.getCiudad(), a.getfAlta(),
                        a.getEstado(), a.getDepartamento().getNombre(), a.getProveedor().getNickname());

                listaActividadesProveedorConfirmadas.add(dta);
            }
        }

        return listaActividadesProveedorConfirmadas;
    }

    @Override
    public ArrayList<DTActividad> listaActividadesProveedorTodas(String nicknameProveedor) {

        ArrayList<DTActividad> listaActividadesProveedorTodas = new ArrayList();
        //me traigo las actividades de la bd
        List<Actividad> listaActividades = controlPersis.traerActividades();
        //recorro la lista de actividades y agrego a la lista a devolver la que tienen el proveedor buscado y este confirmada
        for (Actividad a : listaActividades) {
            if (a.getProveedor().getNickname().equals(nicknameProveedor)) {
                //DTActividad(String nombre, String descripcion, int duracion, float costo, String ciudad, Date fAlta,
                //TipoEstado estado, String nombreDepartamento, String nombreProveedor)
                DTActividad dta = new DTActividad(a.getNombre(), a.getDescripcion(), a.getDuracion(), a.getCosto(), a.getCiudad(), a.getfAlta(),
                        a.getEstado(), a.getDepartamento().getNombre(), a.getProveedor().getNickname());

                listaActividadesProveedorTodas.add(dta);
            }
        }

        return listaActividadesProveedorTodas;
    }

    @Override
    public ArrayList<String> listaPaquetesComprados(String nicknameTurista) {
        Turista t = controlPersis.traerTurista(nicknameTurista);
        ArrayList<String> listaPaquetesTurista = new ArrayList();
        for (Compra c : t.getListaCompras()) {
            listaPaquetesTurista.add(c.getPaquete().getNombre());
        }
        return listaPaquetesTurista;
    }

    @Override
    public ArrayList<DTPaquete> listaPaquetesCompradosVigentes(String nicknameTurista) {
        Turista t = controlPersis.traerTurista(nicknameTurista);
        ArrayList<DTPaquete> listaPaquetesTuristaVigentesDT = new ArrayList();
        ArrayList<Paquete> listaPaquetesTuristaVigentes = new ArrayList();
        Date fechaActual = new Date();
        for (Compra c : t.getListaCompras()) {
            if (!c.getVencimiento().before(fechaActual)) {//  !(si la fecha de vencimiento ya paso) osea, si aun no vencio
                Paquete p = c.getPaquete();
                if (!listaPaquetesTuristaVigentes.contains(p)) {// si no esta en el arraylist                   
                    listaPaquetesTuristaVigentes.add(p); //lo agrego
                }
            }
        }
        // pasaje a dt
        for (Paquete p : listaPaquetesTuristaVigentes) {
            DTPaquete dtPaquete = new DTPaquete(p.getNombre(), p.getDescripcion(), p.getValidez(), p.getDescuento(), p.getFechaAlta());
            listaPaquetesTuristaVigentesDT.add(dtPaquete);
        }
        return listaPaquetesTuristaVigentesDT;
    }

    @Override
    public boolean actividadSinSalidaVigente(String nombreActividad) { // true = act sin salidas vigentes, act false = con salidas vigentes
        Actividad actividad = controlPersis.consultaActividad(nombreActividad);
        Date fechaActual = new Date();
        boolean noHaySalidasAFuturo = true;
        for (SalidaTuristica s : actividad.getListaSalidaTuristica()) {
            if (s.getfSalida().after(fechaActual)) {
                noHaySalidasAFuturo = false;
            }

        }
        return noHaySalidasAFuturo;
    }

    @Override
    public ArrayList<DTActividad> listaActividadesConfirmadas() {
        List<Actividad> listaActividades = controlPersis.traerActividades();
        ArrayList<DTActividad> listaDTActividadesConfirmadas = new ArrayList<>();

        for (Actividad actividad : listaActividades) {
            if (actividad.getEstado().equals(TipoEstado.confirmada)) {
                Proveedor proveedor = actividad.getProveedor();
                String nombreProveedor = proveedor.getNickname();

                DTActividad dtActividad = new DTActividad(actividad.getNombre(), actividad.getDescripcion(), actividad.getDuracion(),
                        actividad.getCosto(), actividad.getCiudad(), actividad.getfAlta(), actividad.getEstado(), actividad.getDepartamento().getNombre(), nombreProveedor);

                listaDTActividadesConfirmadas.add(dtActividad);
            }
        }

        return listaDTActividadesConfirmadas;
    }

    @Override
    public ArrayList<DTActividad> listaActividadesConfirmadasDepartamento(String nombreDepartamento) {
        ArrayList<DTActividad> listaActividadesTuristicas = new ArrayList();
        for (Actividad actividad : controlPersis.traerActividades()) {
            if (actividad.getDepartamento().getNombre().equals(nombreDepartamento) && actividad.getEstado().equals(TipoEstado.confirmada)) {
                DTActividad a = new DTActividad(actividad.getNombre(), actividad.getDescripcion(), actividad.getDuracion(),
                        actividad.getCosto(), actividad.getCiudad(), actividad.getfAlta(), actividad.getEstado(), actividad.getDepartamento().getNombre(), actividad.getProveedor().getNombre());

                listaActividadesTuristicas.add(a);
            }
        }
        return listaActividadesTuristicas;
    }

    //Carga de los Datos de Prueba
    @Override
    public void cargarDatosDePrueba() {
        DatosDePrueba dp = new DatosDePrueba();
        dp.cargarDatosDePrueba();
    }

    @Override
    public ArrayList<String> listaActividadesTuristicasPorCategoria(String categoria) {
        ArrayList<String> listaActividadesTuristicas = new ArrayList();
        for (String s : controlPersis.listaActividadesTuristicasPorCategoria(categoria)) {
            listaActividadesTuristicas.add(s);
        }

        return listaActividadesTuristicas;

    }

    @Override
    public ArrayList<String> listaActividadesTuristicasPorCategoriaConfirmadas(String categoria) {
        List<Actividad> listaActividades = controlPersis.traerActividades();
        ArrayList<String> listaActividadesTuristicas = new ArrayList();
        for (Actividad actividad : listaActividades) {
            List<Categoria> categorias = actividad.getListaCategoria();

            for (Categoria cat : categorias) {
                if (cat.getNombre().equals(categoria) && actividad.getEstado().equals(TipoEstado.confirmada)) {
                    listaActividadesTuristicas.add(actividad.getNombre());
                }
            }
        }

        return listaActividadesTuristicas;

    }

    @Override
    public void marcarActividadComoFavorita(String nicknameUsuario, String nombreActividad) {
        try {
            Turista turista = (Turista) controlPersis.traerTurista(nicknameUsuario);
            List<String> listaActividadesFavoritas = turista.getListaActividadesFavoritas();
            if (listaActividadesFavoritas == null) {
                listaActividadesFavoritas = new ArrayList<>();
            } else if (!listaActividadesFavoritas.contains(nombreActividad)) {
                listaActividadesFavoritas.add(nombreActividad);
                turista.setListaActividadesFavoritas(listaActividadesFavoritas);
                controlPersis.marcarActividadComoFavorita(turista);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al marcar la actividad como favorita: " + ex.getMessage());

        }
    }

    @Override
    public void marcarUsuarioComoFavorita(String nicknameUsuario, String nickanmeUsuarioFavorito) {
        try {
            Usuario usuario = controlPersis.consultaUsuario(nicknameUsuario);
            List<String> listaUsuariosFavoritos = usuario.getListaUsuariosFavoritas();
            if (listaUsuariosFavoritos == null) {
                listaUsuariosFavoritos = new ArrayList<>();
            } else if (!listaUsuariosFavoritos.contains(nickanmeUsuarioFavorito)) {
                listaUsuariosFavoritos.add(nickanmeUsuarioFavorito);
                usuario.setListaUsuariosFavoritas(listaUsuariosFavoritos);
                controlPersis.marcarUsuarioComoFavorito(usuario);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al marcar el usuario como favorito: " + ex.getMessage());
        }
    }

    @Override
    public void DesMarcarUsuarioFavorito(String nickname, String nicknameUsuarioFavorito) {
        try {
            Usuario usuario = controlPersis.consultaUsuario(nickname);
            List<String> listaUsuariosFavoritos = usuario.getListaUsuariosFavoritas();
            if (listaUsuariosFavoritos == null) {
                listaUsuariosFavoritos = new ArrayList<>();
            } else if (listaUsuariosFavoritos.contains(nicknameUsuarioFavorito)) {
                listaUsuariosFavoritos.remove(nicknameUsuarioFavorito);
                usuario.setListaUsuariosFavoritas(listaUsuariosFavoritos);
                controlPersis.marcarUsuarioComoFavorito(usuario);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al desmarcar el usuario como favorito: " + ex.getMessage());
        }
    }

    @Override
    public ArrayList<String> traerActividadesFavoritasDelTurista(String nicknameTurista) {
        Turista turista = (Turista) controlPersis.traerTurista(nicknameTurista);
        List<String> listaActividadesFavoritas = turista.getListaActividadesFavoritas();
        ArrayList<String> arrayListActividadesFavoritas = new ArrayList<>(listaActividadesFavoritas);
        return arrayListActividadesFavoritas;
    }

//    @Override
//    public ArrayList<String> traerUsuariosFavoritosDelUsuario(String nicknameUsuario){
//        
//    }
//    
    public void DesMarcarActividad(String usuario, String nombreActividad) {
        try {
            Turista turista = (Turista) controlPersis.traerTurista(usuario);
            List<String> listaActividadesFavoritas = turista.getListaActividadesFavoritas();
            listaActividadesFavoritas.remove(nombreActividad);
            turista.setListaActividadesFavoritas(listaActividadesFavoritas);
            controlPersis.DesMarcarActividad(turista);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al desmarcar la actividad como favorita: " + ex.getMessage());

        }
    }

    @Override
    public void generarPDFInscripcionSalida(String nickname, String nombreSalida) {
        Document document = new Document();
        //String outputPath = System.getProperty("catalina.base") + File.separator + "webapps" + File.separator + "TuAppName" + File.separator + "PDFs" + File.separator + nickname + ".pdf";

        String outputPath = "\\PDFs\\" + nickname + ".pdf";
        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();

            ArrayList<DTSalidaTuristica> listaDTSalidaInscUsuario = traerInscSalidasDeTurista(nickname);
            for (DTSalidaTuristica dtSalida : listaDTSalidaInscUsuario) {

                if (dtSalida.getNombre().equals(nombreSalida)) {
                    //me traigo la actividad de la salida
                    Turista t = controlPersis.traerTurista(nickname);
                    List<Inscripcion> listaIncDelTurista = t.getListaInscripcion();
                    int cantInscriptosSalida = 0;
                    for (Inscripcion insc : listaIncDelTurista) {
                        if (insc.getSalida().getNombre().equals(nombreSalida)) {
                            cantInscriptosSalida = insc.getCantTurista();
                        }
                    }
                    // Agregar el título
                    String titulo = "Lista de Inscripciones:";
                    document.add(new Paragraph("        " + titulo));

                    // Saltar una línea en blanco
                    document.add(new Paragraph(" "));
                    document.add(new Paragraph(" "));

                    document.add(new Paragraph("Nombre Turista: " + t.getNombre()));
                    document.add(new Paragraph("Nombre Actividad: " + dtSalida.getNombreActividad()));
                    document.add(new Paragraph("Nombre Salida Turistica: " + dtSalida.getNombre()));
                    document.add(new Paragraph("Fecha y hora Salida Turistica: " + dtSalida.getfSalida()));
                    document.add(new Paragraph("Cantidad de Turistas: " + cantInscriptosSalida));
                    document.add(new Paragraph("\n"));
                }
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public DTCategoria traerCategoria(String categoria) {
        Categoria c = controlPersis.traerCategoria(categoria);
        List<DTActividad> listDTAct = new ArrayList();

        for (Actividad actividad : c.getListaActividad()) {
            DTActividad a = new DTActividad(actividad.getNombre(), actividad.getDescripcion(), actividad.getDuracion(),
                    actividad.getCosto(), actividad.getCiudad(), actividad.getfAlta(), actividad.getEstado(), actividad.getDepartamento().getNombre(), actividad.getProveedor().getNombre());

            listDTAct.add(a);

        }
        DTCategoria dtc = new DTCategoria(c.getNombre(), listDTAct);
        return dtc;

    }

    @Override
    //devuelve false si el nickname ya existe en la BD
    public boolean validarNickname(String nickname) {

        ArrayList<String> listaUsuariosTotal = controlPersis.listaUsuarios();
        if (!listaUsuariosTotal.contains(nickname)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean validarCorreo(String correo) {

        ArrayList<String> listaUsuariosCorreoTotal = controlPersis.listaUsuariosCorreo();
        if (!listaUsuariosCorreoTotal.contains(correo)) {
            return true;
        } else {
            return false;
        }

    }

    public void subirImagenActividad(byte[] imagen, String nombreArchivo, String actividad, String UrlVideo) {
        try {
            if (nombreArchivo.equals("sinImagen")) {
                nombreArchivo = "sinImagen.png";
//            if(UrlVideo == null){
//                imagenActividad ImagenActividad = new imagenActividad(nombreArchivo, actividad, UrlVideo);
//                controlPersis.guardarImagenActividad(ImagenActividad);
//            }
                imagenActividad ImagenActividad = new imagenActividad(nombreArchivo, actividad, UrlVideo);
                controlPersis.guardarImagenActividad(ImagenActividad);
            } else {
                imagenActividad ImagenActividad = new imagenActividad(nombreArchivo, actividad, UrlVideo);
                controlPersis.guardarImagenActividad(ImagenActividad);

                // Obtener el directorio de trabajo actual
                String directorioTrabajo = System.getProperty("user.dir");

                // Definir una carpeta para las imágenes dentro del directorio de trabajo
                String carpetaImagenes = directorioTrabajo + File.separator + "src" + File.separator + "images";

                // Crear la ruta completa del archivo de destialtno
                String rutaArchivoDestino = carpetaImagenes + File.separator + nombreArchivo;

                // Escribir los bytes en el archivo de destino
                Files.write(FileSystems.getDefault().getPath(rutaArchivoDestino), imagen);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public byte[] traerImagenActividad(String nombreActividad) {
        try {
            imagenActividad imagen = controlPersis.buscarImagenActividad(nombreActividad);

            // Obtener el directorio de trabajo actual
            String directorioTrabajo = System.getProperty("user.dir");

            // Definir una carpeta para las imágenes dentro del directorio de trabajo
            String carpetaImagenes = directorioTrabajo + File.separator + "src" + File.separator + "images";

            // Obtener la ruta completa del archivo de destino
            String rutaArchivoImagen = carpetaImagenes + File.separator + imagen.getNombre();  // Ajusta la ruta según tu estructura

            File img = new File(rutaArchivoImagen);

            // Manejar el caso en que la imagen no existe
            if (!img.exists()) {
                return new byte[0];
            }

            Path path = Paths.get(rutaArchivoImagen);
            return Files.readAllBytes(path);

        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }

    }

    @Override
    public void subirImagenPerfil(byte[] imagen, String nombreArchivo, String nickname) {
        try {
            if (nombreArchivo.equals("usuarioSinFoto")) {
                nombreArchivo = "usuarioSinFoto.png";

                ImagenPerfil imagenPerfil = new ImagenPerfil(nombreArchivo, nickname);
                controlPersis.guardarImagenPerfil(imagenPerfil);
            } else {
                ImagenPerfil imagenPerfil = new ImagenPerfil(nombreArchivo, nickname);
                controlPersis.guardarImagenPerfil(imagenPerfil);

                // Obtener el directorio de trabajo actual
                String directorioTrabajo = System.getProperty("user.dir");

                // Definir una carpeta para las imágenes dentro del directorio de trabajo
                String carpetaImagenes = directorioTrabajo + File.separator + "src" + File.separator + "images";

                // Crear la ruta completa del archivo de destialtno
                String rutaArchivoDestino = carpetaImagenes + File.separator + nombreArchivo;

                // Escribir los bytes en el archivo de destino
                Files.write(FileSystems.getDefault().getPath(rutaArchivoDestino), imagen);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public byte[] traerImagenPerfil(String nickname) {
        try {
            ImagenPerfil imagen = controlPersis.buscarImagen(nickname);

            // Obtener el directorio de trabajo actual
            String directorioTrabajo = System.getProperty("user.dir");

            // Definir una carpeta para las imágenes dentro del directorio de trabajo
            String carpetaImagenes = directorioTrabajo + File.separator + "src" + File.separator + "images";

            // Obtener la ruta completa del archivo de destino
            String rutaArchivoImagen = carpetaImagenes + File.separator + imagen.getNombre();  // Ajusta la ruta según tu estructura

            File img = new File(rutaArchivoImagen);

            // Manejar el caso en que la imagen no existe
            if (!img.exists()) {
                return new byte[0];
            }

            Path path = Paths.get(rutaArchivoImagen);
            return Files.readAllBytes(path);

        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }

    }

    @Override
    public void modificarImagenPerfil(byte[] imagen, String nombreArchivo, String nickname) {

        try {
            if (nombreArchivo.equals("usuarioSinFoto")) {
                nombreArchivo = "usuarioSinFoto.png";
                ImagenPerfil imagenPerfilNueva = new ImagenPerfil(nombreArchivo, nickname);
                try {
                    controlPersis.modificarImagenPerfil(imagenPerfilNueva);
                } catch (PreexistingEntityException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                ImagenPerfil imagenPerfilNueva = new ImagenPerfil(nombreArchivo, nickname);
                try {
                    controlPersis.modificarImagenPerfil(imagenPerfilNueva);
                } catch (PreexistingEntityException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Obtener el directorio de trabajo actual
                String directorioTrabajo = System.getProperty("user.dir");

                // Definir una carpeta para las imágenes dentro del directorio de trabajo
                String carpetaImagenes = directorioTrabajo + File.separator + "src" + File.separator + "images";

                // Crear la ruta completa del archivo de destialtno
                String rutaArchivoDestino = carpetaImagenes + File.separator + nombreArchivo;

                // Escribir los bytes en el archivo de destino
                Files.write(FileSystems.getDefault().getPath(rutaArchivoDestino), imagen);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
