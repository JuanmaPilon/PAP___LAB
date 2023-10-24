package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class SalidaTuristica implements Serializable {
    @Id
    private String nombre;
    private int cantMax;
    @Temporal(TemporalType.DATE)
    private Date fAlta;
    @Temporal(TemporalType.DATE)
    private Date fSalida;
    private String lugar;
    @OneToMany(mappedBy = "salida")
    private ArrayList<Inscripcion> listaInscripciones;
    @ManyToOne
    @JoinColumn(name="ACTIVIDAD_NOMBRE")
    private Actividad actividad;

    public SalidaTuristica() {
    }

    public SalidaTuristica(String nombre, int cantMax, Date fAlta, Date fSalida, String lugar, ArrayList<Inscripcion> listaInscripciones, Actividad actividad) {
        this.nombre = nombre;
        this.cantMax = cantMax;
        this.fAlta = fAlta;
        this.fSalida = fSalida;
        this.lugar = lugar;
        this.listaInscripciones = listaInscripciones;
        this.actividad = actividad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantMax() {
        return cantMax;
    }

    public Date getfAlta() {
        return fAlta;
    }

    public Date getfSalida() {
        return fSalida;
    }

    public String getLugar() {
        return lugar;
    }

    public ArrayList<Inscripcion> getListaInscripciones() {
        return listaInscripciones;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantMax(int cantMax) {
        this.cantMax = cantMax;
    }

    public void setfAlta(Date fAlta) {
        this.fAlta = fAlta;
    }

    public void setfSalida(Date fSalida) {
        this.fSalida = fSalida;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setListaInscripciones(ArrayList<Inscripcion> listaInscripciones) {
        this.listaInscripciones = listaInscripciones;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    

    
}
