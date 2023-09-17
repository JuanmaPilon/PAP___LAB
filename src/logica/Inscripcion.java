
package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Inscripcion implements Serializable{ 
    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="TURISTA_NICKNAME")
    private Turista turista;
    @ManyToOne
    @JoinColumn(name="SALIDA_NOMBRE")
    private SalidaTuristica salida;
    @Temporal(TemporalType.DATE)
    private Date fInscripcion;
    private int cantTurista;
    private float costo;

    public Inscripcion() {
    }

    public Inscripcion(Turista turista, SalidaTuristica salida, Date fInscripcion, int cantTurista, float costo) {
        this.turista = turista;
        this.salida = salida;
        this.fInscripcion = fInscripcion;
        this.cantTurista = cantTurista;
        this.costo = costo;
    }

    public Long getId() {
        return id;
    }

    public Turista getTurista() {
        return turista;
    }

    public SalidaTuristica getSalida() {
        return salida;
    }

    public Date getfInscripcion() {
        return fInscripcion;
    }

    public int getCantTurista() {
        return cantTurista;
    }

    public float getCosto() {
        return costo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTurista(Turista turista) {
        this.turista = turista;
    }

    public void setSalida(SalidaTuristica salida) {
        this.salida = salida;
    }

    public void setfInscripcion(Date fInscripcion) {
        this.fInscripcion = fInscripcion;
    }

    public void setCantTurista(int cantTurista) {
        this.cantTurista = cantTurista;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    
}
