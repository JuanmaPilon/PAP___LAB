//mio

package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import persistencia.ControladoraPersistencia;

public class Controlador implements IControlador{
    public Controlador() {}
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
   public void guardarActividad(String nombreActividad, String descripcionActividad, int duracionActividad, float costoActividad, String nombreCuidad, Date fecha,String nombreProveedor, String nombreDepartamento){
   Actividad actividad = new Actividad();
   actividad.setCiudad(nombreCuidad);
   actividad.setNombre(nombreActividad);
   actividad.setDescripcion(descripcionActividad);
   actividad.setDuracion(duracionActividad);
   actividad.setCosto(costoActividad);
   actividad.setfAlta(fecha);
   
   
   Departamento dep = new Departamento();//creo dep auxiliar
   dep = controlPersis.traerDepartamento(nombreDepartamento);// encuentro el departamento y lo cargo en dep
  // dep.getListaActTur().add(actividad);
   actividad.setDepartamento(controlPersis.traerDepartamento(nombreDepartamento));// hago que mi actividad apunte al departamento que me traje
   //idem con proveedor
   Proveedor pro = new Proveedor();
   pro = controlPersis.traerProveedor(nombreProveedor);
   actividad.setProveedor(pro);
   
   controlPersis.guardarActividad(actividad);
   
   
   }
   
   @Override
   public void AltaSalidaTuristica(String nombre, int cantMax, Date fAlta, Date fSalida, String lugar, String nombreActividad) {
       SalidaTuristica salidaTuristica = new SalidaTuristica();
        salidaTuristica.setNombre(nombre);
        salidaTuristica.setCantMax(cantMax);
        salidaTuristica.setfAlta(fAlta);
        salidaTuristica.setfSalida(fSalida);
        salidaTuristica.setLugar(lugar);
        
        Actividad actividad = ConsultaActividadTuristica(nombreActividad);
        actividad.getListaSalidaTuristica().add(salidaTuristica);
        
        controlPersis.guardarSalidaTuristica(salidaTuristica, actividad);
    }
////////////
   @Override
   public ArrayList<String> listaActividades(){//tiene el nombre de los departamentos, no el objeto
       return controlPersis.listaActividades();
   };
   @Override
   public ArrayList<String> listaPaquetes(){//tiene el nombre de los departamentos, no el objeto
       return controlPersis.listaPaquetes();
   };
   ///////
   @Override 
   public ArrayList<String> listaSalActividadTuristica(String actividad){
       return controlPersis.listaSalActividadTuristica(actividad);
   };
   
   @Override
   public ArrayList<Departamento> listaDepartamentos(){
       return controlPersis.listaDepartamentos();
   };
   @Override
   public ArrayList<String> listaDeptos(){//tiene el nombre de los departamentos, no el objeto
       return controlPersis.listaDeptos();
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
   public ArrayList<String> listaProveedores(){
     return controlPersis.listaProveedores();
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
public void crearPaqueteActividadTuristica(String nombreDePaquete, String descripcionDePaquete, int validezDePaquete, String altaDePaquete, int descuentoDePaquete) {
    Paquete paquete = new Paquete();
    paquete.setNombre(nombreDePaquete);
    paquete.setDescripcion(descripcionDePaquete);
    paquete.setValidez(validezDePaquete);
    paquete.setDescuento(descuentoDePaquete);

    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Ajusta el formato según tus necesidades
        Date fechaAlta = dateFormat.parse(altaDePaquete);
        paquete.setFechaAlta(fechaAlta);//le seteo fechaAlta al paquete
    } catch (ParseException e) { //excepción si el formato del String no es válido
        throw new IllegalArgumentException("Ingrese la fecha en este formato dd/MM/yyyy", e);
    }

    controlPersis.guardarPaqueteActividadTuristica(paquete);
}

   
   @Override
   public ArrayList<DTUsuario> traerUsuarioMod(){
       return controlPersis.traerUsuarios();
   }

   @Override
   public DTTurista traerDTTurista(String nickname){
   
       Turista t = controlPersis.traerTurista(nickname);
       //conversion date a String
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       String fnac = sdf.format(t.getfNacimiento());
       
       
       return new DTTurista(t.getNickname(), t.getNombre(), t.getApellido(), t.getCorreo(),
               fnac, t.getNacionalidad());
   }
   @Override
   public DTProveedor traerDTProveedor(String nickname){
   
       Proveedor t = controlPersis.traerProveedor(nickname);
       //conversion date a String
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       String fnac = sdf.format(t.getfNacimiento());
       
       
       return new DTProveedor(t.getNickname(), t.getNombre(), t.getApellido(), t.getCorreo(),
               fnac, t.getDescripcion(), t.getLink());
   }
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

    @Override
    public void ModificarDatosDeUsuarioTurista(String nickname, String nombre, String apellido, String correo, Date fecha, String nacionalidad) {
        Turista t =  (Turista) ConsultaDeUsuario(nickname);
        
        t.setNombre(nombre);
        t.setApellido(apellido);
        t.setNacionalidad(nacionalidad);
        t.setfNacimiento(fecha);
        
        controlPersis.modificarTurista(t);
    }
    
    @Override
    public List<String> findSalidasTuristicasDepartamento(String departamentoSeleccionado) {
        return controlPersis.findSalidasTuristicasDepartamentoPersis(departamentoSeleccionado);
    }
    @Override
    public ArrayList<DTActividad> encontraActividadDepartamento(String departamentoSeleccionado) {
        return controlPersis.encontraActividadDepartamentoPersis(departamentoSeleccionado);
    }
   
    @Override
     public void asignarActividadPaquete(String paqueteSeleccionado,String actividadSeleccionada){
         controlPersis.asignarActividadPaquetePersis(paqueteSeleccionado, actividadSeleccionada);
     }
    
    @Override
    public ArrayList<Turista> listaTurista(){
        return controlPersis.listaTuristas();
    }
}

