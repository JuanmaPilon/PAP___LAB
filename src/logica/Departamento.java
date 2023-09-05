
package logica;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departamento implements Serializable {
    @Id
    private String nombre;
    private String descripcion;
    private String url;
    @OneToMany
    private ArrayList<Actividad> listaActTur;

    public Departamento() {
    }

    public Departamento(String nombre, String descripcion, String url, ArrayList<Actividad> listaActTur) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
        this.listaActTur = listaActTur;
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<Actividad> getListaActTur() {
        return listaActTur;
    }

    public void setListaActTur(ArrayList<Actividad> listaActTur) {
        this.listaActTur = listaActTur;
    }

    

}

