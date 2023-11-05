package logica;



import java.io.Serializable;
import java.util.List;
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
    private List<Actividad> listaActividades;  

    public Proveedor() {
        super();
    }

    public Proveedor(String descripcion, String link, List<Actividad> listaActividades, String nickname, String contrasenia, String nombre, String apellido, String correo, Date fNacimiento, List<String> listaUsuariosFavoritos) {
        super(nickname,  nombre, apellido, contrasenia, correo, fNacimiento, listaUsuariosFavoritos);
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

    public List<Actividad> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(List<Actividad> listaActividades) {
        this.listaActividades = listaActividades;
    }

    
    
    
}