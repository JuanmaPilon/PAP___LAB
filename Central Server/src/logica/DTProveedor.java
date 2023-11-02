/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author natil
 */
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
