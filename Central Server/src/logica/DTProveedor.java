/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author natil
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DTProveedor extends DTUsuario{
    private String descripcion;
    private String link;

    public DTProveedor(String nickname, String nombre, String apellido, String correo, String fNacimiento, String contrasenia, String descripcion, String link) {
        super ( nickname,  nombre,  apellido,  correo,  fNacimiento, contrasenia);
        this.descripcion = descripcion;
        this.link = link;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLink() {
        return link;
    }
    
    
}
