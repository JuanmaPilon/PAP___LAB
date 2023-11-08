
package logica;

import java.util.List;


public class DTUsuario {

    private String nickname;
    private String nombre;
    private String apellido;
    private String correo;
    private String fNacimiento;
    private String contrasenia;
    private List<String> listaUsuariosFavoritas;

        public DTUsuario(String nickname, String nombre, String apellido, String correo, String fNacimiento, String contrasenia) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fNacimiento = fNacimiento;
        this.contrasenia = contrasenia;
    }

    public DTUsuario(String nickname, String nombre, String apellido, String correo, String fNacimiento, String contrasenia, List<String> listaUsuariosFavoritas) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fNacimiento = fNacimiento;
        this.contrasenia = contrasenia;
        this.listaUsuariosFavoritas = listaUsuariosFavoritas;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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
    
    public List<String> getListaUsuariosFavoritas() {
        return listaUsuariosFavoritas;
    }

    public void setListaUsuariosFavoritas(List<String> listaUsuariosFavoritas) {
        this.listaUsuariosFavoritas = listaUsuariosFavoritas;
    }


    
    
}
