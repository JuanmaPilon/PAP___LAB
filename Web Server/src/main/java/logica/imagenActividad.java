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

@Entity
public class imagenActividad implements Serializable {
    
    @Id
    private String nombre; // Nombre como clave primaria
    
    private String ruta;
    
    private String nombreActividad;

    // Constructor, getters y setters
    
    public imagenActividad() {
    }
    
    public imagenActividad(String nombre, String ruta, String nombreActividad) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.nombreActividad = nombreActividad;
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
    
    public String getnombreActividad() {
        return nombreActividad;
    }

    public void setnombreActividad(Actividad actividad) {
        this.nombreActividad = nombreActividad;
    }
}
