package logica;

import presentacion.DUsuario;
import java.util.Date;

    public class Usuario {
        private String nickname;
        private String nombre;
        private String apellido;
        private String correo;
        private Date fNacimiento;

    public Usuario(String nickname, String nombre, String apellido, String correo, Date fNacimiento) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fNacimiento = fNacimiento;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setfNacimiento(Date fNacimiento) {
        this.fNacimiento = fNacimiento;
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

    public Date getfNacimiento() {
        return fNacimiento;
    }
    
    public DUsuario parse(){
        return null;
    }
}