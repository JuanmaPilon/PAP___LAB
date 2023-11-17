/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Endpoint;
import logica.DTActividad;
import logica.DTCategoria;
import logica.DTCompra;
import logica.DTImagenActividad;
import logica.DTImagenPerfil;
import logica.DTPaquete;
import logica.DTProveedor;
import logica.DTSalidaTuristica;
import logica.DTTurista;
import logica.DTUsuario;
import logica.Fabrica;
import logica.IControlador;
import logica.TipoEstado;
import logica.TipoPago;
import logica.exceptions.ImagenPorNicknameNoExite;
import logica.exceptions.NoExisteCompra;
import logica.exceptions.PaqueteSinActividad;
import logica.exceptions.PaqueteYaComprado;
import persistencia.exceptions.CorreoElectronicoExistenteException;
import persistencia.exceptions.NicknameExistenteException;
import persistencia.exceptions.PreexistingEntityException;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WebServices {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();
    private Endpoint endpoint = null;

    //Constructor
    public WebServices() {
    }

    //Operaciones las cuales quiero publicar
    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:9128/webservices", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public String devolverTipoUsuario(String nickname) {
        return control.devolverTipoUsuario(nickname);
    }

    @WebMethod
    public boolean validarNickname(String nickname) {
        return control.validarNickname(nickname);
    }

    @WebMethod
    public boolean validarCorreo(String correo) {
        return control.validarCorreo(correo);
    }

    @WebMethod
    public ListaString listaUsuarios() {
        ListaString result = new ListaString();
        result.setLista(control.listaUsuarios());
        return result;
    }

    @WebMethod
    public void marcarUsuarioComoFavorita(String nicknameUsuario, String nicknameUsuarioFavorito) {
        control.marcarUsuarioComoFavorita(nicknameUsuario, nicknameUsuarioFavorito);
    }

    @WebMethod
    public void desMarcarUsuarioFavorito(String nicknameUsuario, String nicknameUsuarioFavorito) {
        control.DesMarcarUsuarioFavorito(nicknameUsuario, nicknameUsuarioFavorito);
    }

    @WebMethod
    public DTUsuario traerDTUsuario(String nickname) {
        return control.traerDTUsuario(nickname);
    }

    @WebMethod
    public DTTurista traerDTTurista(String nickname) {
        return control.traerDTTurista(nickname);
    }

    @WebMethod
    public DTProveedor traerDTProveedor(String nickname) {
        return control.traerDTProveedor(nickname);
    }

    @WebMethod
    public ListaDTSalidaTuristica traerInscSalidasDeTurista(String nickname) {
        ListaDTSalidaTuristica result = new ListaDTSalidaTuristica();
        result.setLista(control.traerInscSalidasDeTurista(nickname));
        return result;
    }

    @WebMethod
    public ListaString listaPaquetesComprados(String nicknameTurista) {
        ListaString result = new ListaString();
        result.setLista(control.listaPaquetesComprados(nicknameTurista));
        return result;
    }

    @WebMethod
    public ListaDTActividad listaActividadesProveedorTodas(String nicknameProveedor) {
        ListaDTActividad result = new ListaDTActividad();
        result.setLista(control.listaActividadesProveedorTodas(nicknameProveedor));
        return result;
    }

    @WebMethod
    public ListaDTActividad listaActividadesProveedorConfirmadas(String nicknameProveedor) {
        ListaDTActividad result = new ListaDTActividad();
        result.setLista(control.listaActividadesProveedorConfirmadas(nicknameProveedor));
        return result;
    }

    @WebMethod
    public ListaDTSalidaTuristica traerSalidasDelProveedor(String nickname) {
        ListaDTSalidaTuristica result = new ListaDTSalidaTuristica();
        result.setLista(control.traerSalidasDelProveedor(nickname));
        return result;
    }

    @WebMethod
    public DTImagenPerfil buscarImagenPorNickname(String nickname) throws ImagenPorNicknameNoExite {
        try {
            return control.buscarImagenPorNickname(nickname);
        } catch (ImagenPorNicknameNoExite e) {
            throw e;
        }
    }

    @WebMethod
    public void generarPDFInscripcionSalida(String nickname, String nombreSalida) {
        control.generarPDFInscripcionSalida(nickname, nombreSalida);
    }

    @WebMethod
    public void AltaDeUsuarioTurista(String nickname, String nombre, String apellido, String contrasenia, String correo,
            XMLGregorianCalendar fNacimiento, String nacionalidad) throws NicknameExistenteException, PreexistingEntityException, CorreoElectronicoExistenteException {
        // Date fechaNacimiento = DateConverter.convertXMLGregorianCalendarToDate(fNacimiento);

        GregorianCalendar cal = fNacimiento.toGregorianCalendar();

        control.AltaDeUsuarioTurista(nickname, nombre, apellido, contrasenia, correo, cal.getTime(), nacionalidad);
    }

    @WebMethod
    public void AltaDeImagenPerfil(String imagenNombre, String imagenRuta, String nicknameUsuario) throws PreexistingEntityException {

        control.AltaDeImagenPerfil(imagenNombre, imagenRuta, nicknameUsuario);
    }

    @WebMethod
    public void AltaDeUsuarioProveedor(String nickname, String nombre, String apellido, String contrasenia, String correo,
            XMLGregorianCalendar fNacimiento, String descripcion, String link) throws CorreoElectronicoExistenteException, NicknameExistenteException, PreexistingEntityException {
        GregorianCalendar cal = fNacimiento.toGregorianCalendar();
        control.AltaDeUsuarioProveedor(nickname, nombre, apellido, contrasenia, correo, cal.getTime(), descripcion, link);
    }

    @WebMethod
    public ListaDTUsuario traerUsuarioMod() {
        ListaDTUsuario result = new ListaDTUsuario();
        result.setLista(control.traerUsuarioMod());
        return result;
    }

    @WebMethod
    public ListaDTDepartamento listaDTDepartamentos() {
        ListaDTDepartamento result = new ListaDTDepartamento();
        result.setLista(control.listaDTDepartamentos());
        return result;
    }

    @WebMethod
    public ListaString traerCategorias() {
        ListaString result = new ListaString();
        result.setLista(control.traerCategorias());
        return result;
    }

    @WebMethod
    public ListaString listaActividadesTuristicasConfirmadas(String departamentoSeleccionado) {
        ListaString result = new ListaString();
        result.setLista(control.listaActividadesTuristicasConfirmadas(departamentoSeleccionado));
        return result;
    }

    @WebMethod
    public ListaString listaActividadesTuristicasPorCategoriaConfirmadas(String categoriaSeleccionada) {
        ListaString result = new ListaString();
        result.setLista(control.listaActividadesTuristicasPorCategoriaConfirmadas(categoriaSeleccionada));
        return result;
    }

    @WebMethod
    public DTActividad traerDTActividad(String nombreActividad) {
        return control.traerDTActividad(nombreActividad);
    }

    @WebMethod
    public DTImagenActividad traerDTImagenActividad(String nombreActividad) {
        return control.traerDTImagenActividad(nombreActividad);
    }

    @WebMethod
    public ListaDTSalidaTuristica encontraSalidasTuristicasDeActividad(String nombreActividad) {
        ListaDTSalidaTuristica result = new ListaDTSalidaTuristica();
        result.setLista(control.encontraSalidasTuristicasDeActividad(nombreActividad));
        return result;
    }

    @WebMethod
    public ListaString traerCategoriasActividad(String nombreActividad) {
        ListaString result = new ListaString();
        result.setLista(control.traerCategoriasActividad(nombreActividad));
        return result;
    }

    @WebMethod
    public ListaString listaPaquetesDeActividad(String nombreActividad) {
        ListaString result = new ListaString();
        result.setLista(control.listaPaquetesDeActividad(nombreActividad));
        return result;
    }

    @WebMethod
    public boolean actividadSinSalidaVigente(String nombreActividad) {
        return control.actividadSinSalidaVigente(nombreActividad);
    }

    @WebMethod
    public void cambiarEstadoActividad(String nombreActividad, TipoEstado tipoEstado) {
        control.cambiarEstadoActividad(nombreActividad, tipoEstado);
    }

    @WebMethod
    public void marcarActividadComoFavorita(String nicknameUsuario, String nombreActividad) {
        control.marcarActividadComoFavorita(nicknameUsuario, nombreActividad);
    }

    @WebMethod
    public ListaString traerActividadesFavoritasDelTurista(String nicknameUsuario) {
        ListaString result = new ListaString();
        result.setLista(control.traerActividadesFavoritasDelTurista(nicknameUsuario));
        return result;
    }

    @WebMethod
    public void DesMarcarActividad(String nicknameUsuario, String nombreActividad) {
        control.DesMarcarActividad(nicknameUsuario, nombreActividad);
    }
//problema

    @WebMethod
    public void guardarActividad(String nombreActividad, String descripcionActividad, int duracionActividad, float costoActividad, String nombreCuidad, XMLGregorianCalendar fecha, String nombreProveedor, String nombreDepartamento, ListaString listaCategorias) throws PreexistingEntityException {
        Date fechaActual = new Date();

        control.guardarActividad(nombreActividad, descripcionActividad, duracionActividad, costoActividad, nombreCuidad, fechaActual, nombreProveedor, nombreDepartamento, listaCategorias.getLista());

    }

//    @WebMethod
//    public void AltaDeImagenActividad(String imagenNombre, String imagenRuta, String nombreActividad, String UrlVideo) throws PreexistingEntityException {
//        control.AltaDeImagenActividad(imagenNombre, imagenRuta, nombreActividad, UrlVideo);
//    }

    @WebMethod
    public ListaString listaDeptos() {
        ListaString result = new ListaString();
        result.setLista(control.listaDeptos());
        return result;
    }

    @WebMethod
    public ListaDTActividad listaActividadesConfirmadas() {
        ListaDTActividad result = new ListaDTActividad();
        result.setLista(control.listaActividadesConfirmadas());
        return result;
    }

    @WebMethod
    public void ModificarDatosDeUsuarioTurista(String nickname, String nombre, String apellido, String correo, XMLGregorianCalendar fNacimiento, String nacionalidad) {
        GregorianCalendar cal = fNacimiento.toGregorianCalendar();
        control.ModificarDatosDeUsuarioTurista(nickname, nombre, apellido, correo, cal.getTime(), nacionalidad);
    }

    @WebMethod
    public void ModificarDatosDeUsuarioProveedor(String nickname, String nombre, String apellido, String correo, XMLGregorianCalendar fNacimiento, String descripcion, String url) {
        GregorianCalendar cal = fNacimiento.toGregorianCalendar();
        control.ModificarDatosDeUsuarioProveedor(nickname, nombre, apellido, correo, cal.getTime(), descripcion, url);
    }

    @WebMethod
    public void ModificarImagenPerfil(String imagenNombre, String imagenRuta, String nicknameUsuario) throws PreexistingEntityException {
        control.ModificarImagenPerfil(imagenNombre, imagenRuta, nicknameUsuario);

    }

    @WebMethod
    public ListaDTActividad listaActividadesConfirmadasDepartamento(String departamento) {
        ListaDTActividad result = new ListaDTActividad();
        result.setLista(control.listaActividadesConfirmadasDepartamento(departamento));
        return result;
    }

    public DTImagenActividad buscarImagenPorActividad(String departamento) {
        return control.buscarImagenPorActividad(departamento);
    }

    @WebMethod
    public DTCategoria traerCategoria(String categoria) {
        return control.traerCategoria(categoria);
    }

    @WebMethod
    public ListaString listaPaquetes() {
        ListaString result = new ListaString();
        result.setLista(control.listaPaquetes());
        return result;
    }

    @WebMethod
    public ListaDTPaquete traerListaDTPaquetes() {
        ListaDTPaquete result = new ListaDTPaquete();
        result.setLista(control.traerListaDTPaquetes());
        return result;
    }

    @WebMethod
    public void CompraDePaquete(String nickname, String nombrePaquete, int cantTurista, XMLGregorianCalendar fechaCompra) throws PaqueteSinActividad, PaqueteYaComprado {

        Date fechaHoy = new Date();
        control.CompraDePaquete(nickname, nombrePaquete, cantTurista, fechaHoy);
    }

    @WebMethod
    public ListaString listaActividadesDelPaquete(String nombrePaquete) {
        ListaString result = new ListaString();
        result.setLista(control.listaActividadesDelPaquete(nombrePaquete));
        return result;
    }

    @WebMethod
    public ListaDTPaquete listaPaquetesCompradosVigentes(String nicknameTurista) {
        ListaDTPaquete result = new ListaDTPaquete();
        result.setLista(control.listaPaquetesCompradosVigentes(nicknameTurista));
        return result;
    }

    @WebMethod
    public boolean salidaTuristicaLlena(String salida, int cantAInscribir) {
        return control.salidaTuristicaLlena(salida, cantAInscribir);
    }

    @WebMethod
    public boolean turistaYaInscriptoSalidaTuristica(String salida, String turistaAlta) {
        return control.turistaYaInscriptoSalidaTuristica(salida, turistaAlta);
    }

    @WebMethod
    public DTPaquete traerDTPaquete(String nombrePaquete) {
        return control.traerDTPaquete(nombrePaquete);
    }

    @WebMethod
    public void nuevaCantTurista(Long id, int nuevaCantTurista) throws NoExisteCompra {
        control.nuevaCantTurista(id, nuevaCantTurista);
    }

    @WebMethod
    public void InscripcionASalidaTuristica(String nombreSalidaSeleccionada, String nicknameTurista, int cantTurista, float costo, String fecha, TipoPago tipoPago) {
        Date fechaHoy = new Date();
        control.InscripcionASalidaTuristica(nombreSalidaSeleccionada, nicknameTurista, cantTurista, costo, fechaHoy, tipoPago);
    }

    @WebMethod
    public DTCompra traerCompraDelTurista(String nombreTurista, String nombrePaquete) {
        return control.traerCompraDelTurista(nombreTurista, nombrePaquete);
    }
 @WebMethod
    public DTSalidaTuristica ConsultaSalidaTuristica(String nombreSalida){
    return control.ConsultaSalidaTuristica(nombreSalida);
    }
    
    @WebMethod
public void AltaSalidaTuristica(String nombre, int cantMax, String fAlta, String fSalida, String lugar, String nombreActividad) throws PreexistingEntityException, ParseException {
    System.out.println(" ****************** " + fSalida+ "*******************");
    try {
        // Formato de la cadena de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime fechaHoraLocal = LocalDateTime.parse(fSalida, formatter);
        Date fechaHoraDate = java.util.Date.from(fechaHoraLocal.atZone(java.time.ZoneId.systemDefault()).toInstant());
        
        System.out.println(" fechaHoraDate " + fechaHoraDate+ "*******************");

  

       

        // Obtén la fecha actual
        Date fechaHoy = new Date();

        // Llama al método con los objetos de fecha adecuados
        control.AltaSalidaTuristica(nombre, cantMax, fechaHoy, fechaHoraDate, lugar, nombreActividad);
    } catch (Exception e) {
        // Manejar la excepción si la cadena de fecha no puede ser parseada
        e.printStackTrace();
        System.out.println(" ******************AQUI*******************");
    }
}
    
    @WebMethod
    public void subirImagenActividad(byte[] imagen, String nombreArchivo, String actividad, String UrlVideo){
        control.subirImagenActividad(imagen, nombreArchivo, actividad, UrlVideo);
    }
    
     @WebMethod 
    public byte [] traerImagenActividad(String nombreActividad) {
    	return control.traerImagenActividad(nombreActividad);
    }
    
    
}//finWS
