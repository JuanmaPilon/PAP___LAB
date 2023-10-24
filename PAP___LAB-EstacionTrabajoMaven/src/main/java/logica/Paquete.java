package logica;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Paquete implements Serializable {
    @Id
    private String nombre;
    private String descripcion;
    private int validez;
    private int descuento;
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @ManyToMany(mappedBy="listaPaquete")
    private ArrayList<Actividad> listaActividades;

public Paquete() {
}

    public Paquete(String nombre, String descripcion, int validez, int descuento, ArrayList<Actividad> listaActividades) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.validez = validez;
        this.descuento = descuento;
        this.listaActividades = listaActividades;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String desc) {
        this.descripcion = desc;
    }

    public int getValidez() {
        return validez;
    }

    public void setValidez(int validez) {
        this.validez = validez;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public ArrayList<Actividad> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(ArrayList<Actividad> listaActividades) {
        this.listaActividades = listaActividades;
    }


}
