package logica;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Categoria implements Serializable {
    @Id
    private String nombre;
    @ManyToMany(mappedBy="listaCategoria")
    private ArrayList<Actividad> listaActividad;

    public Categoria() {
    }

    public Categoria(String nombre, ArrayList<Actividad> listaActividad) {
        this.nombre = nombre;
        this.listaActividad = listaActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Actividad> getListaActividad() {
        return listaActividad;
    }

    public void setListaActividad(ArrayList<Actividad> listaActividad) {
        this.listaActividad = listaActividad;
    }
    

    
    
}
