package logica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTImagenPerfil {

    private String nombre; // Nombre como clave primaria
    private String ruta;
    private String nicknameUsuario;

    public DTImagenPerfil() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    public DTImagenPerfil(String nombre, String ruta, String nicknameUsuario) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.nicknameUsuario = nicknameUsuario;
    }
}
