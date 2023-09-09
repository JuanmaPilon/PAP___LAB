
package logica;


public class DTUsuario {

    private String nickname;
    private String nombre;
    private String apellido;
    private String correo;
    private String fNacimiento;

    public DTUsuario(String nickname, String nombre, String apellido, String correo, String fNacimiento) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
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

    public String getfNacimiento() {
        return fNacimiento;
    }
    
    
    
    
}
