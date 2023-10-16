/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "nombre")
public class ImagenPerfil implements Serializable {
    
    @Id
    private String nombre; // Nombre como clave primaria
    
    private String ruta;
    
    
    private String nicknameUsuario;

    // Constructor, getters y setters
    
    public ImagenPerfil() {
    }
    
    public ImagenPerfil(String nombre, String ruta, String nicknameUsuario) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.nicknameUsuario = nicknameUsuario;
    }

    // Getters y setters para nombre y ruta
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    public void setNicknameUsuario(Usuario usuario) {
        this.nicknameUsuario = nicknameUsuario;
    }
}
