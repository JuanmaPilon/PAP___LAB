package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import persistencia.ControladoraPersistencia;
import java.util.Calendar;

public class Controlador implements IControlador{
    private Controlador() {}
    private static Controlador control;
    public static Controlador getInstance(){
    if (control == null){
        control = new Controlador();
    }
    return control;
   };
   
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
   //descomentado por una prueba:
   @Override
   public void AltaDeUsuarioTurista(String nickname, String nombre, String apellido, String correo, 
           Date fNacimiento, String nacionalidad) {
    
    Turista turista = new Turista(); 
    turista.setNickname(nickname);
    turista.setNombre(nombre);
    turista.setApellido(apellido);
    turista.setCorreo(correo);
    turista.setfNacimiento(fNacimiento);
    turista.setNacionalidad(nacionalidad);
    controlPersis.guardarTurista(turista);
   };
   @Override
   public List<Paquete> consultaPaquetes(){
       return controlPersis.consultaPaquete();
   };
   
   @Override
   public void AltaDeUsuarioProveedor(String nickname, String nombre, String apellido, String correo, 
           Date fNacimiento, String descripcion, String link){
   Proveedor proveedor = new Proveedor();
   proveedor.setNickname(nickname);
   proveedor.setNombre(nombre);
   proveedor.setApellido(apellido);
   proveedor.setCorreo(correo);
   proveedor.setfNacimiento(fNacimiento);
   proveedor.setDescripcion(descripcion);
   proveedor.setLink(link);
   controlPersis.guardarProveedor(proveedor);
   };
   

   
   //String nombreProveedor, String nombreDep,
   @Override
   public void guardarActividad(String nombreActividad, String descripcionActividad, int duracionActividad, float costoActividad, String nombreCuidad, Date fecha){
   Actividad actividad = new Actividad();
   actividad.setCiudad(nombreCuidad);
   actividad.setNombre(nombreActividad);
   actividad.setDescripcion(descripcionActividad);
   actividad.setDuracion(duracionActividad);
   actividad.setCosto(costoActividad);
   actividad.setfAlta(fecha);
   actividad.setProveedor(null);
   actividad.setDepartamento(null);
   controlPersis.guardarActividad(actividad);
   }
   
   @Override
   public void AltaSalidaTuristica(String nombre, int cantMax, Date fAlta, Date fSalida, String lugar) {
       SalidaTuristica salidaTuristica = new SalidaTuristica();
        salidaTuristica.setNombre(nombre);
        salidaTuristica.setCantMax(cantMax);
        salidaTuristica.setfAlta(fAlta);
        salidaTuristica.setfSalida(fSalida);
        salidaTuristica.setLugar(lugar);
        controlPersis.guardarSalidaTuristica(salidaTuristica);
    }

   
   @Override 
   public ArrayList<String> listaSalActividadTuristica(String actividad){
       return controlPersis.listaSalActividadTuristica(actividad);
   };
   
   @Override
   public ArrayList<Departamento> listaDepartamentos(){
       return controlPersis.listaDepartamentos();
   };
   
   @Override
   public ArrayList<String> listaActividadesTuristicas(String departamento){
       return controlPersis.listaActividadesTuristicas(departamento);
   };
   
   @Override
   public ArrayList<String> listaUsuarios(){
     return controlPersis.listaUsuarios();
   };
   
   @Override
   public Usuario ConsultaDeUsuario(String nickname){
       return controlPersis.consultaUsuario(nickname);
   };
   
   @Override
   public Actividad ConsultaActividadTuristica(String nombreActividad){
       return controlPersis.consultaActividad(nombreActividad);
   };
//    public void ModificarDatosDeUsuario();//Nati
   @Override
   public void AltaDeActividadTuristica(String nombre, String descripcion, int duracion, float costo, String ciudad, Date fAlta, ArrayList<SalidaTuristica> listaSalidaTuristica, ArrayList<Paquete> listaPaquete){

    Actividad actividad = new Actividad();
       
    //falta control si nombre ya existe o si es null nombre
    actividad.setNombre(nombre);
    actividad.setDescripcion(descripcion);
    actividad.setDuracion(duracion);
    actividad.setCosto(costo);
    actividad.setCiudad(ciudad);
    actividad.setfAlta(fAlta);
    actividad.setListaSalidaTuristica(listaSalidaTuristica);
    actividad.setListaPaquete(listaPaquete);
    
       
    controlPersis.guardarActividad(actividad);


};//Juanma
//    public void AltaDeSalidaTuristica();
   @Override
    public SalidaTuristica ConsultaSalidaTuristica(String nombreSalida){
        return controlPersis.consultaSalida(nombreSalida);
    };
//    public void InscripcionDeSalidaTuristica();
//    public void CrearPaqueteDeActividadesTuristicas();
//    public void AgregarActividadTuristicaAPaquete();
//    public void ConsultaDePaqueteDeActividadTuristicas();
  
   @Override
   public void AltaDeDepartamento(String nombre, String descripcion, String url){
       
       Departamento depto = new Departamento();
       
       //falta control si nombre ya existe o si es null nombre
       depto.setNombre(nombre);
       depto.setDescripcion(descripcion);
       depto.setUrl(url);
       
       controlPersis.guardarDepartamento(depto);
   };
   
   @Override
    public List<String> llenarCmboBoxDep(){
       return controlPersis.llenarCmboBoxDepPersis();
   }
    
   @Override 
   public void crearPaqueteActividadTuristica(String nombreDePaquete, String descripcionDePaquete, int validezDePaquete, int altaDePaquete){
      Paquete paquete = new Paquete();
      paquete.setNombre(nombreDePaquete);
      paquete.setDescripcion(nombreDePaquete);
      paquete.setValidez(validezDePaquete);
      
      controlPersis.guardarPaqueteActividadTuristica(paquete);
   }
   
   @Override
   public ArrayList<DTUsuario> traerUsuarioMod(){
       return controlPersis.traerUsuarios();
   }


   
   
}

