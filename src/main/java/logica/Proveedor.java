package logica;

import presentacion.DProveedor;
import java.util.Date;

public class Proveedor extends Usuario {
    private String descripcion;
    private String link;

    public Proveedor(String nickname, String nombre, String apellido, String correo, Date fNacimiento) {
        super(nickname, nombre, apellido, correo, fNacimiento);
        this.descripcion = descripcion;
        this.link = link;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    @Override
    public DProveedor parse(){
        return new DProveedor(getNickname(), getNombre(),getApellido(),getCorreo(),getfNacimiento());
    }
}