
package logica;

import java.util.Date;

public class Inscripcion {
    private Date fInscripcion;
    private int cantTurista;
    private float costo;

    public Inscripcion() {
    }

    public Inscripcion(Date fInscripcion, int cantTurista, float costo) {
        this.fInscripcion = fInscripcion;
        this.cantTurista = cantTurista;
        this.costo = costo;
    }

    public int getCantTurista() {
        return cantTurista;
    }

    public float getCosto() {
        return costo;
    }

    public Date getfInscripcion() {
        return fInscripcion;
    }

    public void setCantTurista(int cantTurista) {
        this.cantTurista = cantTurista;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public void setfInscripcion(Date fInscripcion) {
        this.fInscripcion = fInscripcion;
    }
    
    
}
