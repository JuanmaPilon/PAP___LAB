package logica;

import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

    @MappedSuperclass
    public class Usuario {
        @Id
        private String nickname;
        private String contrasenia;
        private String nombre;
        private String apellido;
        private String correo;
        @Temporal(TemporalType.DATE)
        private Date fNacimiento;
        @ElementCollection
        private List<String> listaUsuariosFavoritas;

    public Usuario() {
    }
    
 public Usuario(String nickname, String contrasenia, String nombre, String apellido, String correo, Date fNacimiento, List<String> listaUsuariosFavoritas) {
        this.nickname = nickname;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fNacimiento = fNacimiento;
        this.listaUsuariosFavoritas = listaUsuariosFavoritas;
    }

    public Usuario(String nickname, String nombre, String apellido, String contrasenia, String correo, Date fNacimiento) {
        this.nickname = nickname;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fNacimiento = fNacimiento;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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
    
    public List<String> getListaUsuariosFavoritas() {
        return listaUsuariosFavoritas;
    }

    public void setListaUsuariosFavoritas(List<String> listaUsuariosFavoritas) {
        this.listaUsuariosFavoritas = listaUsuariosFavoritas;
    }
    

}