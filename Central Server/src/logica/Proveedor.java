package logica;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "nickname")
public class Proveedor extends Usuario implements Serializable {
    private String descripcion;
    private String link;
    @OneToMany(mappedBy="proveedor")
    private ArrayList<Actividad> listaActividades;  

    public Proveedor() {
        super();
    }

    public Proveedor(String descripcion, String link, ArrayList<Actividad> listaActividades, String nickname, String nombre, String apellido, String correo, Date fNacimiento) {
        super(nickname, nombre, apellido, correo, fNacimiento);
        this.descripcion = descripcion;
        this.link = link;
        this.listaActividades = listaActividades;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList<Actividad> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(ArrayList<Actividad> listaActividades) {
        this.listaActividades = listaActividades;
    }

    
    
    
}