/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webServices;

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
import logica.DTImagenActividad;
import logica.DTImagenPerfil;
import logica.DTProveedor;
import logica.DTSalidaTuristica;
import logica.DTTurista;
import logica.DTUsuario;
import logica.Fabrica;
import logica.IControlador;
import logica.TipoEstado;
import logica.exceptions.ImagenPorNicknameNoExite;
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
    
    @WebMethod
    public void AltaDeImagenActividad(String imagenNombre, String imagenRuta, String nombreActividad, String UrlVideo) throws PreexistingEntityException {
        control.AltaDeImagenActividad(imagenNombre, imagenRuta, nombreActividad, UrlVideo);
    }
    
}//finWS
