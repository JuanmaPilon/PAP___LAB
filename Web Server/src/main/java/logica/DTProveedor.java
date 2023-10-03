/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author natil
 */
public class DTProveedor {
    private String nickname;
    private String nombre;
    private String apellido;
    private String correo;
    private String fNacimiento;
    private String descripcion;
    private String link;

    public DTProveedor() {
    }

    public DTProveedor(String nickname, String nombre, String apellido, String correo, String fNacimiento, String descripcion, String link) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fNacimiento = fNacimiento;
        this.descripcion = descripcion;
        this.link = link;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getfNacimiento() {
        return fNacimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLink() {
        return link;
    }
    
    
}
