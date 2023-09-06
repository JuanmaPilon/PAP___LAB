
package persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logica.Actividad;
import logica.Departamento;
import logica.Turista;
import logica.Proveedor;
import logica.Usuario;
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
    public Actividad consultaActividad(String nombreActividad){
      return   actividadJpa.findActividad(nombreActividad);
    };
    public ArrayList<String> listaDepartamentos(){
        ArrayList<String> departamentos = new ArrayList<String>();
        try {
            List<Departamento> departamento = departamentoJpa.findDepartamentoEntities();
            for (int i = 0; i < departamento.size(); i++) {
                departamentos.add(departamento.get(i).getNombre());
            }
        }catch(Exception ex){
                Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departamentos;
    }
    public ArrayList<String> listaActividadesTuristicas(String departamento){
       ArrayList<String> actividades = new ArrayList<String>();
        List<String> actividad = actividadJpa.findByDepartamento(departamento);
        for(int i = 0; i < actividad.size();i++){
            actividades.add(actividad.get(i));
        };
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
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
    
}
