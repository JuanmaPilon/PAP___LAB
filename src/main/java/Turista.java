import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Turista extends Usuario {
    private int nacionalidad;
    private List<Compra> compras = new ArrayList<>();

    public Turista (String nickname, String nombre, String apellido, String correo, Date fNacimiento) {
        super(nickname, nombre, apellido, correo, fNacimiento);
       this.nacionalidad = nacionalidad;
    }

    public void setNacionalidad(int nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getNacionalidad() {
        return nacionalidad;
    }











}

