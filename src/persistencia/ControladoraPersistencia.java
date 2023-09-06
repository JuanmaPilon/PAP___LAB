
package persistencia;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logica.Actividad;
import logica.Departamento;
import logica.Turista;
import persistencia.exceptions.CorreoElectronicoExistenteException;

public class ControladoraPersistencia {
    //creo las controladoras de persistencia de cada clase
    PaqueteJpaController paqueteJpa = new PaqueteJpaController();
    ActividadJpaController actividadJpa = new ActividadJpaController();
    SalidaTuristicaJpaController salidaTuristicaJpa = new SalidaTuristicaJpaController();
    DepartamentoJpaController departamentoJpa = new DepartamentoJpaController();
    TuristaJpaController turistaJpa = new TuristaJpaController();
    ProveedorJpaController proveedorJpa = new ProveedorJpaController();
    
    
    
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
