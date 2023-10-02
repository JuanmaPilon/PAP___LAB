/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;


public class DTTurista {
    private String nickname;
    private String nombre;
    private String apellido;
    private String correo;
    private String fNacimiento;
    private String nacionalidad;

    public DTTurista() {
    }

    public DTTurista(String nickname, String nombre, String apellido, String correo, String fNacimiento, String nacionalidad) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fNacimiento = fNacimiento;
        this.nacionalidad = nacionalidad;
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

    public String getNacionalidad() {
        return nacionalidad;
    }
    
    
    
}
