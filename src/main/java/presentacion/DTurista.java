package presentacion;

import logica.Turista;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class DTurista extends DUsuario {
    private String nacionalidad;
    private List<DCompra> compras = new ArrayList<>();

    public DTurista (String nickname, String nombre, String apellido, String correo, Date fNacimiento) {
        super(nickname, nombre, apellido, correo, fNacimiento);
       this.nacionalidad = nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    @Override
    public Turista parse(){
        return new Turista(getNickname(), getNombre(), getApellido(), getCorreo(), getfNacimiento());
    }









}

