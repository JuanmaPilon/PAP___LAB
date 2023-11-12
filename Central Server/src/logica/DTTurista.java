
package logica;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTTurista extends DTUsuario{
    private String nacionalidad;



    public DTTurista(String nickname, String nombre, String apellido, String correo, String fNacimiento, String nacionalidad, String contrasenia){
        super ( nickname,  nombre,  apellido,  correo,  fNacimiento, contrasenia);
        this.nacionalidad = nacionalidad;
    }


    public String getNacionalidad() {
        return nacionalidad;
    }
    
    
    
}
