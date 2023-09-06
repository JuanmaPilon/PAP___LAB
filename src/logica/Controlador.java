package logica;

import java.util.ArrayList;
import java.util.Date;
import persistencia.ControladoraPersistencia;

public class Controlador implements IControlador{
   public Controlador() {}
   
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
   public void AltaDeUsuarioProveedor(String nickname, String nombre, String apellido, String correo, Date fNacimiento, String descripcion, String link, Departamento depto){
   };
   
   @Override 
   public ArrayList<String> listaSalActividadTuristica(String actividad){
       return controlPersis.listaSalActividadTuristica(actividad);
   };
   
   @Override
   public ArrayList<String> listaDepartamentos(){
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
    
}

