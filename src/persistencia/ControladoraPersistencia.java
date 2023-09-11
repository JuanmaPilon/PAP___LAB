
package persistencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logica.Actividad;
import logica.DTActividad;
import logica.DTUsuario;
import logica.Departamento;
import logica.Turista;
import logica.Proveedor;
import logica.Usuario;
import logica.SalidaTuristica;
import logica.Paquete;
import persistencia.exceptions.CorreoElectronicoExistenteException;

public class ControladoraPersistencia {
    //creo las controladoras de persistencia de cada clase
    PaqueteJpaController paqueteJpa = new PaqueteJpaController();
    ActividadJpaController actividadJpa = new ActividadJpaController();
    SalidaTuristicaJpaController salidaTuristicaJpa = new SalidaTuristicaJpaController();
    DepartamentoJpaController departamentoJpa = new DepartamentoJpaController();
    TuristaJpaController turistaJpa = new TuristaJpaController();
    ProveedorJpaController proveedorJpa = new ProveedorJpaController();
    
    
    //Consultas
    public SalidaTuristica consultaSalida(String nombreSalida){
        return salidaTuristicaJpa.findSalidaTuristica(nombreSalida);
    }
    public Actividad consultaActividad(String nombreActividad){
      return   actividadJpa.findActividad(nombreActividad);
    };
    public ArrayList<String> listaSalActividadTuristica(String actividad){
       ArrayList<String> salidas = new ArrayList();
       List<String> salida = salidaTuristicaJpa.findByActividad(actividad);
       for(int i = 0; i < salida.size();i++){
           salidas.add(salida.get(i));
       }
       return salidas; 
    };
    public ArrayList<Departamento> listaDepartamentos(){
        ArrayList<Departamento> departamentos = new ArrayList();
        try {
            List<Departamento> departamento = departamentoJpa.findDepartamentoEntities();
            for (int i = 0; i < departamento.size(); i++) {
                departamentos.add(departamento.get(i));
            }
        }catch(Exception ex){
                Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departamentos;
    }
 
     public ArrayList<String> listaDeptos(){
    ArrayList<String> nicks = new ArrayList<String>();
  
    try {
        List<Departamento> deptos = departamentoJpa.findDepartamentoEntities();
        for (int i = 0; i < deptos.size(); i++) {
            nicks.add(deptos.get(i).getNombre());
        }
    }catch(Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
    return nicks;
    }
 //////////////////////
         public ArrayList<String> listaActividades(){
    ArrayList<String> nombre = new ArrayList<String>();
  
    try {
        List<Actividad> actividades = actividadJpa.findActividadEntities();
        for (int i = 0; i < actividades.size(); i++) {
            nombre.add(actividades.get(i).getNombre());
        }
    }catch(Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
    return nombre;
    }
     ////////////////////
       public ArrayList<String> listaPaquetes(){
    ArrayList<String> nombrep = new ArrayList<String>();
  
    try {
        List<Paquete> paquetes = paqueteJpa.findPaqueteEntities();
        for (int i = 0; i < paquetes.size(); i++) {
            nombrep.add(paquetes.get(i).getNombre());
        }
    }catch(Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
    return nombrep;
    }
     /////////////////////////
    public ArrayList<String> listaActividadesTuristicas(String departamento){
       ArrayList<String> actividades = new ArrayList();
       List<String> actividad = actividadJpa.findByDepartamento(departamento);
       for(int i = 0; i < actividad.size();i++){
           actividades.add(actividad.get(i));
       }
       return actividades;
    }
    public Usuario consultaUsuario(String nickname){
        Usuario usuario=null;
        boolean esProveedor=true;
        try {
            usuario = turistaJpa.findTurista(nickname);
            if (usuario != null){
                esProveedor = !esProveedor;
            }
        }catch(Exception ex){
                Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (esProveedor){
            try {
            usuario = proveedorJpa.findProveedor(nickname);
            }catch(Exception ex){
                Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuario; 
    }
    public List<Paquete> consultaPaquete(){
        return paqueteJpa.findPaqueteEntities();
    };
    public ArrayList<String> listaUsuarios(){
    ArrayList<String> nicknames = new ArrayList<String>();
    try {
        List<Turista> turistas = turistaJpa.findTuristaEntities();
        for (int i = 0; i < turistas.size(); i++) {
            nicknames.add(turistas.get(i).getNickname());
        }
    }catch(Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
        List<Proveedor> proveedores = proveedorJpa.findProveedorEntities();
        for (int i = 0; i < proveedores.size(); i++) {
            nicknames.add(proveedores.get(i).getNickname());
        }
    }catch(Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
    return nicknames;
    }

   public ArrayList<String> listaProveedores(){
    ArrayList<String> nicknames = new ArrayList<String>();
  
    try {
        List<Proveedor> proveedores = proveedorJpa.findProveedorEntities();
        for (int i = 0; i < proveedores.size(); i++) {
            nicknames.add(proveedores.get(i).getNickname());
        }
    }catch(Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
    return nicknames;
    }    

    

public ArrayList<DTUsuario> traerProveedores() {

    ArrayList<DTUsuario> listaDTUsuario = new ArrayList<DTUsuario>();
    try {
        List<Proveedor> proveedores = proveedorJpa.findProveedorEntities();
        for (Proveedor p : proveedores) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fnac = sdf.format(p.getfNacimiento());
            DTUsuario dtusuario = new DTUsuario(p.getNickname(), p.getNombre(), p.getApellido(), p.getCorreo(), fnac);
            listaDTUsuario.add(dtusuario);
        }
    } catch (Exception ex) {
        Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
    return listaDTUsuario;

}



public ArrayList<DTUsuario> traerUsuarios(){
        
    ArrayList<DTUsuario> listaDTUsuario = new ArrayList<DTUsuario>();
    
    try {
        List<Turista> turistas = turistaJpa.findTuristaEntities();
        for (Turista t : turistas) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fnac = sdf.format(t.getfNacimiento());
            DTUsuario dtusuario = new DTUsuario(t.getNickname(), t.getNombre(), t.getApellido(), t.getCorreo(), fnac );
            listaDTUsuario.add(dtusuario);
        }
    }catch(Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
        List<Proveedor> proveedores = proveedorJpa.findProveedorEntities();
        for (Proveedor p : proveedores) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fnac = sdf.format(p.getfNacimiento());
            DTUsuario dtusuario = new DTUsuario(p.getNickname(), p.getNombre(), p.getApellido(), p.getCorreo(), fnac );
            listaDTUsuario.add(dtusuario);
        }
    }catch(Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        return listaDTUsuario;
        
        
    }
    
 
//Guardar Departamento
    public void guardarDepartamento(Departamento depto) {
        try {
            //crear Departamento en BD
            departamentoJpa.create(depto);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarActividad(Actividad actividad) {
        try {
            actividadJpa.create(actividad);
            JOptionPane.showMessageDialog(null, "Alta realizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //public void guardarActividad(string nombreProveedor,string nombreDep,string nombreActividad,string descripcionActividad,string duracionActividad,string costoActividad,string nombreCuidad,int dia,int mes,int anio);

    public void guardarTurista(Turista turista) {
    try {
        turistaJpa.create(turista);
         JOptionPane.showMessageDialog(null, "Alta realizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (CorreoElectronicoExistenteException e) {
       JOptionPane.showMessageDialog(null, "El correo ya está en uso por otro usuario", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
       JOptionPane.showMessageDialog(null, "El nickname ya está en uso por otro usuario", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
    public void guardarProveedor(Proveedor proveedor){
        try{
        proveedorJpa.create(proveedor);
         JOptionPane.showMessageDialog(null, "Alta realizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
       } catch (CorreoElectronicoExistenteException e) {
       JOptionPane.showMessageDialog(null, "El correo ya está en uso por otro usuario", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
       JOptionPane.showMessageDialog(null, "El nickname ya está en uso por otro usuario", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
    public List<String> llenarCmboBoxDepPersis(){
        return departamentoJpa.obtenerNombresDepartamentos();
    }
    
     public void guardarSalidaTuristica(SalidaTuristica salidaTuristica){
       try{
        salidaTuristicaJpa.create(salidaTuristica);
        JOptionPane.showMessageDialog(null, "Alta realizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
       }catch (Exception ex){
           JOptionPane.showMessageDialog(null, "El nombre ya esta ocupado por otra salida turistica", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }
    public void guardarPaqueteActividadTuristica(Paquete paquete) {
        try {
            paqueteJpa.create(paquete);
            JOptionPane.showMessageDialog(null, "Alta realizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Turista traerTurista(String nickname) {
        return turistaJpa.findTurista(nickname);
    }

    public Proveedor traerProveedor(String nickname) {
        return proveedorJpa.findProveedor(nickname);
    }
    public Departamento traerDepartamento(String nickname) {
        return departamentoJpa.findDepartamento(nickname);
    }

    public void modificarProveedor(Proveedor p) {
        try {
            proveedorJpa.edit(p);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void modificarTurista(Turista t) {
        try {
            turistaJpa.edit(t);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<String> findSalidasTuristicasDepartamentoPersis(String departamentoSeleccionado) {
        return actividadJpa.findByDepartamento(departamentoSeleccionado);
    }
   
    public List<DTActividad> encontraSalidasTuristicasDepartamentoPersis(String departamentoSeleccionado){
         return actividadJpa.findActividadByDepartamento(departamentoSeleccionado);
     }
    
     public void asignarActividadPaquetePersis(String paqueteSeleccionado,String actividadSeleccionada){
     Paquete paquete = paqueteJpa.findPaquete(paqueteSeleccionado);
     Actividad actividad = actividadJpa.findActividad(actividadSeleccionada);
     
     paquete.getListaActividades().add(actividad);
        try {
            paqueteJpa.edit(paquete);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
}
