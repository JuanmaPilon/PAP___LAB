package logica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTImagenPerfil {

    private String nombre; 
    private String nicknameUsuario;

    public DTImagenPerfil() {
    }

    public String getNombre() {
        return nombre;
    }


    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    public DTImagenPerfil(String nombre, String nicknameUsuario) {
        this.nombre = nombre;
        this.nicknameUsuario = nicknameUsuario;
    }
}
