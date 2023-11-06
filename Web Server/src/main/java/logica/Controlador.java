package logica;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.FileOutputStream;
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
    public void nuevaCantTurista(Compra compraTurista) throws Exception {
        controlPersis.nuevaCantTurista(compraTurista);

    }

    @Override
    public Compra traerCompraDelTurista(String nombreTurista, String nombrePaquete) {
        return controlPersis.traerCompraDelTurista(nombreTurista, nombrePaquete);
    }

    @Override
    public DTImagenActividad buscarImagenPorActividad(String nombreActividad) throws Exception {
        imagenActividad imagen = controlPersis.buscarImagenActividad(nombreActividad);
        DTImagenActividad dtImagen = new DTImagenActividad(imagen.getNombre(), imagen.getRuta(), imagen.getnombreActividad(), imagen.getUrlVideo());
        return dtImagen;

    }

    @Override
    public DTImagenPerfil buscarImagenPorNickname(String nickname) throws Exception {
        ImagenPerfil imagen = controlPersis.buscarImagen(nickname);
        DTImagenPerfil dtImagen = new DTImagenPerfil(imagen.getNombre(), imagen.getRuta(), imagen.getNicknameUsuario());
        return dtImagen;

    }

    public void ModificarImagenPerfil(String imagenNombre, String imagenRuta, String nicknameUsuario) throws PreexistingEntityException, Exception {
        ImagenPerfil imagenPerfil = new ImagenPerfil(imagenNombre, imagenRuta, nicknameUsuario);
        controlPersis.modificarImagenPerfil(imagenPerfil);
    }

    @Override
    public void AltaDeImagenPerfil(String imagenNombre, String imagenRuta, String nicknameUsuario) throws PreexistingEntityException, Exception {
        try {
            ImagenPerfil imagenPerfil = new ImagenPerfil(imagenNombre, imagenRuta, nicknameUsuario);
            controlPersis.guardarImagenPerfil(imagenPerfil);
        } catch (PreexistingEntityException e) {
            throw new PreexistingEntityException("Imagen ya en uso por otro usuario");
        } catch (Exception ex) {
            throw new Exception("Imagen ya en uso por otro usuario");
        }

    }

    @Override
    public void AltaDeImagenActividad(String imagenNombre, String imagenRuta, String nombreActividad, String UrlVideo) throws PreexistingEntityException, Exception {

        imagenActividad ImagenActividad = new imagenActividad(imagenNombre, imagenRuta, nombreActividad, UrlVideo);
        controlPersis.guardarImagenActividad(ImagenActividad);

    }

    @Override
    public void AltaDeUsuarioTurista(String nickname, String nombre, String apellido, String contrasenia, String correo,
            Date fNacimiento, String nacionalidad) throws NicknameExistenteException, PreexistingEntityException, CorreoElectronicoExistenteException, Exception {

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
    public List<Paquete> consultaPaquetes() {
        return controlPersis.consultaPaquete();
    }

    ;
   
    @Override
    public void AltaDeUsuarioProveedor(String nickname, String nombre, String apellido, String contrasenia, String correo,
            Date fNacimiento, String descripcion, String link) throws CorreoElectronicoExistenteException, NicknameExistenteException, PreexistingEntityException, Exception {
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
    public void guardarActividad(String nombreActividad, String descripcionActividad, int duracionActividad, float costoActividad, String nombreCuidad, Date fecha, String nombreProveedor, String nombreDepartamento, List<String> listaCategorias) throws PreexistingEntityException, Exception {
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
    public void AltaSalidaTuristica(String nombre, int cantMax, Date fAlta, Date fSalida, String lugar, String nombreActividad) throws PreexistingEntityException, Exception {
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
    public ArrayList<String> listaPaquetes() {//tiene el nombre de los departamentos, no el objeto
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
            DTDepartamento dtDepartamento = new DTDepartamento(departamento.getNombre(), departamento.getDescripcion(), departamento.getUrl(), listaActTur);
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
                fnac, t.getContrasenia(), t.getNacionalidad());
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
            DTImagenActividad dtImagen = new DTImagenActividad(imagen.getNombre(), imagen.getRuta(), imagen.getnombreActividad(), imagen.getUrlVideo());
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
    public ArrayList<DTPaquete> traerListaDTPaquetes() {
        List<Paquete> listaPaquetes = consultaPaquetes();

        ArrayList<DTPaquete> listaDTPaquete = new ArrayList();
        //DTPaquete(String nombre, String descripcion, int validez, int descuento, Date fechaAlta)
        for (Paquete p : listaPaquetes) {
            DTPaquete dtpaquete = new DTPaquete(p.getNombre(), p.getDescripcion(), p.getValidez(), p.getDescuento(), p.getFechaAlta());
            listaDTPaquete.add(dtpaquete);
        }

        return listaDTPaquete;
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
    public ArrayList<Actividad> listaActividadesConfirmadasDepartamento(String nombreDepartamento) {
        ArrayList<Actividad> listaActividadesTuristicas = new ArrayList();
        for (Actividad a : controlPersis.traerActividades()) {
            if (a.getDepartamento().getNombre().equals(nombreDepartamento) && a.getEstado().equals(TipoEstado.confirmada)) {
                listaActividadesTuristicas.add(controlPersis.consultaActividad(a.getNombre()));
            }
        }
        return listaActividadesTuristicas;
    }

    //Carga de los Datos de Prueba
    @Override
    public void cargarDatosDePrueba() {
        //Usuarios y Proveedores:
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

        try {
            AltaDeUsuarioTurista("lachiqui", "Rosa Marıa", "Martınez", "contra", "mirtha.legrand.ok@hotmail.com.ar", fecha.parse("23/2/1927"), "argentina");
            AltaDeUsuarioTurista("isabelita", "Elizabeth", "Windsor", "contra", "isabelita@thecrown.co.uk", fecha.parse("21/04/1926"), "inglesa");
            AltaDeUsuarioTurista("anibal", "Anıbal", "Lecter", "contra", "anibal@fing.edu.uy", fecha.parse("31/12/1937"), "lituana");
            AltaDeUsuarioTurista("waston", "Emma", "Waston", "contra", "e.waston@gmail.com", fecha.parse("15/4/1990"), "inglesa");
            AltaDeUsuarioTurista("elelvis", "Elvis", "Lacio", "contra", "suavemente@hotmail.com", fecha.parse("30/07/1971"), "estadounidense");
            AltaDeUsuarioTurista("eleven11", "Eleven", "Once", "contra", "eleven11@gmail.com", fecha.parse("19/02/2004"), "espanola");
            AltaDeUsuarioTurista("bobesponja", "Bob", "Esponja", "contra", "bobesponja@nickelodeon.com", fecha.parse("01/05/1999"), "japonesa");
            AltaDeUsuarioTurista("tony", "Antonio", "Pacheco", "contra", "eltony@manya.org.uy", fecha.parse("11/04/1976"), "uruguaya");
            AltaDeUsuarioTurista("chino", "Alvaro", "Recoba", "contra", "chino@trico.org.uy", fecha.parse("17/03/1976"), "uruguaya");
            AltaDeUsuarioTurista("mastropiero", "Johann Sebastian", "Mastropiero", "contra", "johann.sebastian@gmail.com", fecha.parse("07/02/1922"), "austrıaca");
            AltaDeUsuarioProveedor("washington", "Washington", "Rocha", "contra", "washington@turismorocha.gub.uy", fecha.parse("14/09/1970"), "Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha - Uruguay", "http://turismorocha.gub.uy/");
            AltaDeUsuarioProveedor("eldiez", "Pablo", "Bengoechea", "contra", "eldiez@socfomturriv.org.uy", fecha.parse("27/06/1965"), "Pablo es el presidente de la Sociedad de Fomento Turıstico de Rivera (conocida como Socfomturriv)", "http://wwww.socfomturriv.org.uy");
            AltaDeUsuarioProveedor("meche", "Mercedes", "Venn", "contra", "meche@colonia.gub.uy", fecha.parse("31/12/1990"), "Departamento de Turismo del Departamento de Colonia", "https://colonia.gub.uy/turismo/");

        } catch (ParseException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CorreoElectronicoExistenteException ex) {
            JOptionPane.showMessageDialog(null, "El correo ya está en uso por otro usuario", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NicknameExistenteException ex) {
            JOptionPane.showMessageDialog(null, "El nickname ya está en uso por otro usuario", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (PreexistingEntityException ex) {
            // Manejo de la excepción PreexistingEntityException
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        //Departamentos
        try {
            AltaDeDepartamento("Canelones", "Division Turismo de la Intendencia", "https://www.imcanelones.gub.uy/es");
            AltaDeDepartamento("Maldonado", "Division Turismo de la Intendencia", "https://www.maldonado.gub.uy/");
            AltaDeDepartamento("Rocha", "La Organizacion de Gestion del Destino (OGD) Rocha es un ambito de articulacion publico – privada en el sector turıstico que integran la Corporacion Rochense de Turismo y la Intendencia de Rocha a traves de su Direccion de Turismo.", "www.turismorocha.gub.uy");
            AltaDeDepartamento("Treinta y Tres", "Division Turismo de la Intendencia", "https://treintaytres.gub.uy/");
            AltaDeDepartamento("Cerro Largo", "Division Turismo de la Intendencia", "https://www.gub.uy/intendenciacerro-largo/");
            AltaDeDepartamento("Rivera", "Promociona e implementa proyectos e iniciativas sostenibles de interes turıstico con la participaci´on institucional publica – privada en bien del desarrollo socioecon´omico de la comunidad.", "www.rivera.gub.uy/social/turismo/");
            AltaDeDepartamento("Artigas", "Division Turismo de la Intendencia", "http://www.artigas.gub.uy");
            AltaDeDepartamento("Salto", "Division Turismo de la Intendencia", "https://www.salto.gub.uy");
            AltaDeDepartamento("Paysandu", "Division Turismo de la Intendencia", "https://www.paysandu.gub.uy");
            AltaDeDepartamento("Rıo Negro", "Division Turismo de la Intendencia", "https://www.rionegro.gub.uy");
            AltaDeDepartamento("Soriano", "Division Turismo de la Intendencia", "https://www.soriano.gub.uy");
            AltaDeDepartamento("Colonia", "La propuesta del Departamento de Colonia divide en cuatro actos su espectaculo anual. Cada acto tiene su magia. Desde su naturaleza y playas hasta sus tradiciones y el patrimonio mundial. Todo el a˜no se disfruta.", "https://colonia.gub.uy/turismo/");
            AltaDeDepartamento("San Jose", "Division Turismo de la Intendencia", "https://sanjose.gub.uy");
            AltaDeDepartamento("Flores", "Division Turismo de la Intendencia", "https://flores.gub.uy");
            AltaDeDepartamento("Florida", "Division Turismo de la Intendencia", "http://www.florida.gub.uy");
            AltaDeDepartamento("Lavalleja", "Division Turismo de la Intendencia", "http://www.lavalleja.gub.uy");
            AltaDeDepartamento("Durazno", "Division Turismo de la Intendencia", "https://durazno.uy");
            AltaDeDepartamento("Tacuarembo", "Division Turismo de la Intendencia", "https://tacuarembo.gub.uy");
            AltaDeDepartamento("Montevideo", "Division Turismo de la Intendencia", "https://montevideo.gub.uy/areastematicas/turismo");
        } catch (Exception ex) {
            try {
                throw new Exception("Ha ocurrido un error");
            } catch (Exception ex1) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        try {
            //Categorias
            AltaCategoria("Aventura y Deporte");
            AltaCategoria("Campo y Naturaleza");
            AltaCategoria("Cultura y Patrimonio");
            AltaCategoria("Gastronomia");
            AltaCategoria("Turismo Playas");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Categoria: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        //Actividades Turisticas y Salidas Turisticas
        try {

            ArrayList<String> lcat1 = new ArrayList();
            lcat1.add("Gastronomia");

            ArrayList<String> lcat2 = new ArrayList();
            lcat2.add("Cultura y Patrimonio");
            lcat2.add("Gastronomia");

            ArrayList<String> lcat3 = new ArrayList();
            lcat3.add("Cultura y Patrimonio");

            ArrayList<String> lcat4 = new ArrayList();
            lcat4.add("Gastronomia");

            ArrayList<String> lcat5 = new ArrayList();
            lcat5.add("Campo y Naturaleza");
            lcat5.add("Gastronomia");

            ArrayList<String> lcat6 = new ArrayList();
            lcat6.add("Campo y Naturaleza");

            ArrayList<String> lcat7 = new ArrayList();
            lcat7.add("Cultura y Patrimonio");

            ArrayList<String> lcat8 = new ArrayList();
            lcat8.add("Cultura y Patrimonio");

            ArrayList<String> lcat9 = new ArrayList();
            lcat9.add("Aventura y Deporte");
            lcat9.add("Turismo Playas");

            ArrayList<String> lcat10 = new ArrayList();
            lcat10.add("Cultura y Patrimonio");

            guardarActividad("Degusta", "Festival gastronomico de productos locales en Rocha", 3, 800, "Rocha", fecha.parse("20/7/2022"), "washington", "Rocha", lcat1);
            cambiarEstadoActividad("Degusta", TipoEstado.confirmada);
            guardarActividad("Teatro con Sabores", "En el mes aniversario del Club Deportivo Uni´on de Rocha te invitamos a una merienda deliciosa.", 3, 500, "Rocha", fecha.parse("21/7/2022"), "washington", "Rocha", lcat2);
            cambiarEstadoActividad("Teatro con Sabores", TipoEstado.confirmada);
            guardarActividad("Tour por Colonia del Sacramento", "Con guia especializado y en varios idiomas. Varios circuitos posibles.", 2, 400, "Colonia del Sacramento Colonia", fecha.parse("1/8/2022"), "meche", "Colonia", lcat3);
            cambiarEstadoActividad("Tour por Colonia del Sacramento", TipoEstado.confirmada);
            guardarActividad("Almuerzo en el Real de San Carlos", "Restaurante en la renovada Plaza de Toros con menu internacional", 2, 800, "Colonia del Sacramento", fecha.parse("1/8/2022"), "meche", "Colonia", lcat4);
            cambiarEstadoActividad("Almuerzo en el Real de San Carlos", TipoEstado.confirmada);
            guardarActividad("Almuerzo en Valle del Lunarejo", "Almuerzo en la Posada con ticket fijo. Menu que incluye bebida y postre casero.", 2, 300, "Tranqueras", fecha.parse("1/8/2022"), "eldiez", "Rivera", lcat5);
            cambiarEstadoActividad("Almuerzo en Valle del Lunarejo", TipoEstado.confirmada);
            guardarActividad("Cabalgata en Valle del Lunarejo", "Cabalgata por el ´area protegida. Varios recorridos para elegir.", 2, 150, "Tranqueras", fecha.parse("1/8/2022"), "eldiez", "Rivera", lcat6);
            cambiarEstadoActividad("Cabalgata en Valle del Lunarejo", TipoEstado.confirmada);

            //estado agregado
            guardarActividad("Bus turıstico Colonia", "Recorrida por los principales atractivos de la ciudad", 3, 600, "Colonia del Sacramento", fecha.parse("1/9/2022"), "meche", "Colonia", lcat7);

            guardarActividad("Colonia Premium Tour", "Visita lugares exclusivos y relevantes", 4, 2600, "Colonia del Sacramento", fecha.parse("3/9/2022"), "meche", "Colonia", lcat8);
            cambiarEstadoActividad("Colonia Premium Tour", TipoEstado.rechazada);

            //estado agregado
            guardarActividad("Deportes nauticos sin uso de motor", "kitsurf - windsurf - kayakismo - canotaje en Rocha", 3, 1200, "Rocha", fecha.parse("3/9/2022"), "washington", "Rocha", lcat9);

            guardarActividad("Descubre Rivera", "Rivera es un departamento de extraordinaria riqueza natural patrimonial y cultural con una ubicacion geografica privilegiada", 2, 650, "Rivera", fecha.parse("16/9/2022"), "eldiez", "Rivera", lcat10);
            cambiarEstadoActividad("Descubre Rivera", TipoEstado.rechazada);

        } catch (PreexistingEntityException ex) {
            JOptionPane.showMessageDialog(null, "El nombre ya está en uso por otra actividad", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Actividad: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        SimpleDateFormat fechahora = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            AltaSalidaTuristica("Degusta Agosto", 20, fecha.parse("21/07/2022"), fechahora.parse("20/08/2022 17:00"), "Sociedad Agropecuaria de Rocha", "Degusta");
            AltaSalidaTuristica("Degusta Setiembre", 20, fecha.parse("22/07/2022"), fechahora.parse("03/09/2022 17:00"), "Sociedad Agropecuaria de Rocha", "Degusta");
            AltaSalidaTuristica("Teatro con Sabores 1", 30, fecha.parse("23/07/2022"), fechahora.parse("04/09/2022 18:00"), "Club Deportivo Union", "Teatro con Sabores");
            AltaSalidaTuristica("Teatro con Sabores 2", 30, fecha.parse("23/07/2022"), fechahora.parse("11/09/2022 18:00"), "Club Deportivo Union", "Teatro con Sabores");
            AltaSalidaTuristica("Tour Colonia del Sacramento 11-09", 5, fecha.parse("05/08/2022"), fechahora.parse("11/09/2022 10:00"), "Encuentro en la base del Faro", "Tour por Colonia del Sacramento");
            AltaSalidaTuristica("Tour Colonia del Sacramento 18-09", 5, fecha.parse("05/08/2022"), fechahora.parse("18/09/2022 10:00"), "Encuentro en la base del Faro", "Tour por Colonia del Sacramento");
            AltaSalidaTuristica("Almuerzo 1", 5, fecha.parse("04/08/2022"), fechahora.parse("18/09/2022 12:00"), "Restaurante de la Plaza de Toros", "Almuerzo en el Real de San Carlos");
            AltaSalidaTuristica("Almuerzo 2", 5, fecha.parse("04/08/2022"), fechahora.parse("25/09/2022 12:00"), "Restaurante de la Plaza de Toros", "Almuerzo en el Real de San Carlos");
            AltaSalidaTuristica("Almuerzo 3", 4, fecha.parse("15/08/2022"), fechahora.parse("10/09/2022 12:00"), "Posada Del Lunarejo", "Almuerzo en Valle del Lunarejo");
            AltaSalidaTuristica("Almuerzo 4", 4, fecha.parse("15/08/2022"), fechahora.parse("11/09/2022 12:00"), "Posada Del Lunarejo", "Almuerzo en Valle del Lunarejo");
            AltaSalidaTuristica("Cabalgata 1", 4, fecha.parse("15/08/2022"), fechahora.parse("10/09/2022 16:00"), "Posada del Lunarejo", "Cabalgata en Valle del Lunarejo");
            AltaSalidaTuristica("Cabalgata 2", 4, fecha.parse("15/08/2022"), fechahora.parse("11/09/2022 16:00"), "Posada del Lunarejo", "Cabalgata en Valle del Lunarejo");

        } catch (PreexistingEntityException ex) {
            JOptionPane.showMessageDialog(null, "El nombre ya está en uso por otra salida", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Salida Turistica: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        //crear paquetes
        try {
            crearPaqueteActividadTuristica("Disfrutar Rocha", "Actividades para hacer en familia y disfrutar arte y gastronomıa", 60, fecha.parse("10/08/2022"), 20);
            crearPaqueteActividadTuristica("Un dıa en Colonia", "Paseos por el casco historico y se puede terminar con Almuerzo en la Plaza de Toros", 45, fecha.parse("01/08/2022"), 15);
            crearPaqueteActividadTuristica("Valle Del Lunarejo", "Visite un area protegida con un paisaje natural hermoso", 60, fecha.parse("15/09/2022"), 15);
        } catch (PreexistingEntityException ex) {
            JOptionPane.showMessageDialog(null, "El nombre ya está en uso por otro paquete", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al crear paquete." + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        //asignar Actividad turistica a paquete
        try {
            asignarActividadPaquete("Disfrutar Rocha", "Degusta");
            asignarActividadPaquete("Disfrutar Rocha", "Teatro con Sabores");
            asignarActividadPaquete("Un dıa en Colonia", "Tour por Colonia del Sacramento");
            asignarActividadPaquete("Un dıa en Colonia", "Almuerzo en el Real de San Carlos");
            asignarActividadPaquete("Valle Del Lunarejo", "Almuerzo en Valle del Lunarejo");
            asignarActividadPaquete("Valle Del Lunarejo", "Cabalgata en Valle del Lunarejo");
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(null, "El paquete con ese nombre no existe", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al asignar actividad al paquete. " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        //compra
        try {

            CompraDePaquete("lachiqui", "Disfrutar Rocha", 2, fecha.parse("15/8/2022"));
            CompraDePaquete("lachiqui", "Un dıa en Colonia", 5, fecha.parse("20/8/2022"));
            CompraDePaquete("waston", "Un dıa en Colonia", 1, fecha.parse("15/9/2022"));
            CompraDePaquete("elelvis", "Disfrutar Rocha", 10, fecha.parse("1/9/2022"));
            CompraDePaquete("elelvis", "Un dıa en Colonia", 2, fecha.parse("18/9/2022"));
            CompraDePaquete("mastropiero", "Un dıa en Colonia", 6, fecha.parse("2/9/2022"));

        } catch (ParseException | PaqueteSinActividad | PaqueteYaComprado e) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
        }

        //inscripcion
        /*
        try {

            InscripcionASalidaTuristica("Degusta Agosto", "lachiqui", 3, 2400, fecha.parse("15/8/2022"));
            InscripcionASalidaTuristica("Degusta Agosto", "elelvis", 5, 4000, fecha.parse("16/8/2022"));
            InscripcionASalidaTuristica("Tour Colonia del Sacramento 18-09", "lachiqui", 3, 1200, fecha.parse("18/8/2022"));
            InscripcionASalidaTuristica("Tour Colonia del Sacramento 18-09", "isabelita", 1, 400, fecha.parse("19/8/2022"));
            InscripcionASalidaTuristica("Almuerzo 2", "mastropiero", 2, 1600, fecha.parse("19/8/2022"));
            InscripcionASalidaTuristica("Teatro con Sabores 1", "chino", 1, 500, fecha.parse("19/8/2022"));
            InscripcionASalidaTuristica("Teatro con Sabores 2", "chino", 10, 5000, fecha.parse("20/8/2022"));
            InscripcionASalidaTuristica("Teatro con Sabores 2", "bobesponja", 2, 1000, fecha.parse("20/8/2022"));
            InscripcionASalidaTuristica("Teatro con Sabores 2", "anibal", 1, 500, fecha.parse("21/8/2022"));
            InscripcionASalidaTuristica("Degusta Setiembre", "tony", 11, 8800, fecha.parse("21/8/2022"));

        } catch (ParseException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
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
        String outputPath = "C:\\Users\\natil\\Documents\\GitHub\\PAP___LAB\\Web Server\\src\\main\\webapp\\PDFs\\"+nickname+".pdf";
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
    public Categoria traerCategoria(String categoria) {
        return controlPersis.traerCategoria(categoria);

    }

    @Override
    //devuelve false si el nickname ya existe en la BD
    public boolean validarNickname(String nickname){
        
        ArrayList<String> listaUsuariosTotal = controlPersis.listaUsuarios();
        if (!listaUsuariosTotal.contains(nickname))
            return true;
        else return false;
    
    }
    
    
    @Override
    public boolean validarCorreo(String correo){
        
        ArrayList<String> listaUsuariosCorreoTotal = controlPersis.listaUsuariosCorreo();
        if (!listaUsuariosCorreoTotal.contains(correo))
            return true;
        else return false;
    
    }
    
    
}
