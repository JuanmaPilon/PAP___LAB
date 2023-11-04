package logica;



import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "nickname")
public class Turista extends Usuario implements Serializable {
    private String nacionalidad;
    @OneToMany(mappedBy="turista")
    private List<Compra> listaCompras;
    @OneToMany(mappedBy="turista")
    private List<Inscripcion> listaInscripcion;
    public Turista(){
    super();
    }
    public Turista(String nickname, String contrasenia, String nombre, String apellido, String correo, Date fNacimiento) {
        super(nickname, nombre, apellido, contrasenia, correo, fNacimiento);
    }
    

    public Turista(String nacionalidad, List<Compra> listaCompras, List<Inscripcion> listaInscripcion, String nickname, String contrasenia, String nombre, String apellido, String correo, Date fNacimiento) {
        super(nickname, contrasenia, nombre, apellido, correo, fNacimiento);
        this.nacionalidad = nacionalidad;
        this.listaCompras = listaCompras;
        this.listaInscripcion = listaInscripcion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public List<Compra> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }

    public List<Inscripcion> getListaInscripcion() {
        return listaInscripcion;
    }

    public void setListaInscripcion(List<Inscripcion> listaInscripcion) {
        this.listaInscripcion = listaInscripcion;
    }


}

