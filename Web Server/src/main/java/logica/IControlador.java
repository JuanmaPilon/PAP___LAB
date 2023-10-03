
package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import persistencia.exceptions.CorreoElectronicoExistenteException;
import persistencia.exceptions.NicknameExistenteException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;
public interface IControlador {

public abstract void AltaDeImagenPerfil(String imagenNombre,String imagenRuta, String nicknameUsuario) throws PreexistingEntityException, Exception;
public abstract ImagenPerfil buscarImagenPorNickname(String nickname) throws Exception;
public abstract void AltaDeUsuarioTurista(String nickname, String nombre, String apellido,String Contrasenia, String correo, Date fNacimiento, String nacionalidad) throws NicknameExistenteException, PreexistingEntityException, CorreoElectronicoExistenteException, Exception;
public abstract void AltaDeUsuarioProveedor(String nickname, String nombre, String apellido, String correo, Date fNacimiento, String descripcion, String link) throws NicknameExistenteException, PreexistingEntityException, CorreoElectronicoExistenteException, Exception;
public abstract Usuario ConsultaDeUsuario(String nickname); //Devuelve el usuario 
public abstract ArrayList listaUsuarios();  //devuelve una lista de todos los usuarios sin discriminar su tipo
public abstract ArrayList listaProveedores(); //devuelve una lista de todos los proveedores
public abstract ArrayList listaDepartamentos(); //devuelve una lista de todos los departamentos
public abstract ArrayList listaDeptos();//lista que contiene solo los nombres de los departamentos
public abstract ArrayList listaPaquetes();
public abstract ArrayList listaActividades();
public abstract void ModificarDatosDeUsuarioProveedor(String nickname, String nombre, String apellido, String correo, Date fecha, String descripcion, String url);
public abstract void ModificarDatosDeUsuarioTurista(String nickname, String nombre, String apellido, String correo, Date fecha, String nacionalidad);//Nati
public abstract void AltaDeActividadTuristica(String nombre, String descripcion, int duracion, float costo, String ciudad, Date fAlta, ArrayList<SalidaTuristica> listaSalidaTuristica, ArrayList<Paquete> listaPaquete);
public abstract Actividad ConsultaActividadTuristica(String nombreActividad);
public abstract ArrayList listaActividadesTuristicas(String departamento);
public abstract ArrayList<DTSalidaTuristica> encontraSalidasTuristicasDeActividad(String actividadSeleccionado);
public abstract ArrayList listaSalActividadTuristica(String actividad);
public abstract List consultaPaquetes();
public abstract SalidaTuristica ConsultaSalidaTuristica(String nombreSalida);
public abstract void crearPaqueteActividadTuristica(String nombreDePaquete, String descripcionDePaquete, int validezDePaquete, Date altaDePaquete, int descuentoDePaquete) throws PreexistingEntityException, Exception;
public abstract void AltaDeDepartamento(String nombre, String descripcion, String url) throws PreexistingEntityException, Exception;
public abstract List<String> llenarCmboBoxDep();
public abstract void AltaSalidaTuristica(String nombre, int cantMax, Date fAlta, Date fSalida, String lugar, String nombreActividad) throws PreexistingEntityException, Exception;
public abstract void guardarActividad(String nombreActividad,String descripcionActividad,int duracionActividad,float costoActividad,String nombreCuidad,Date fecha,String nombreProveedor, String nombreDepartamento) throws PreexistingEntityException, Exception;
public ArrayList<DTUsuario> traerUsuarioMod();
public ArrayList<DTTurista> traerUsuarioTurista();
public DTTurista traerDTTurista(String nickname);
public DTProveedor traerDTProveedor(String nickname);
public abstract void asignarActividadPaquete(String paqueteSeleccionado,String actividadSeleccionada) throws NonexistentEntityException, Exception;
//public abstract List findSalidasTuristicasDepartamento(String departamentoSeleccionado);//trae lista de strings con los nombres de las actividades asociadas a un depto att:carlangas
public abstract ArrayList<DTActividad> encontraActividadDepartamento(String departamentoSeleccionado);//trae dtactividad asociados a un depto
public abstract void InscripcionASalidaTuristica(String nombreSalidaSeleccionada, String nicknameTurista, int cantTurista, int costo, Date fecha );
public abstract boolean salidaTuristicaLlena(String salida, int cantAInscribir);
public abstract boolean turistaYaInscriptoSalidaTuristica(String salida, String turistaAlta);
public abstract DTSalidaTuristica traerDTSalidaTuristica(String nombreSalida);
public abstract ArrayList<DTSalidaTuristica> traerInscSalidasDeTurista(String nickname);
public abstract ArrayList<DTActividad> traerInscActividadesDeTurista(String nickname);
public abstract ArrayList<String> listaPaquetesDeActividad(String nombreActividad);
public abstract ArrayList<DTSalidaTuristica> traerSalidasDelProveedor(String nickname);
public abstract ArrayList<DTActividad> traerActividadesDelProveedor(String nickname);

public abstract void cargarDatosDePrueba();
}