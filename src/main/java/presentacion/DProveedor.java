package presentacion;

import logica.Proveedor;
import java.util.Date;

public class DProveedor extends DUsuario {
    private String descripcion;
    private String link;

    public DProveedor(String nickname, String nombre, String apellido, String correo, Date fNacimiento) {
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
    public Proveedor parse(){
        return new Proveedor(getNickname(), getNombre(),getApellido(),getCorreo(),getfNacimiento());
    }
}