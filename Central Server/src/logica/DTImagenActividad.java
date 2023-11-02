/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

public class DTImagenActividad {
    
    private String nombre; 
    
    private String ruta;
    
    private String nombreActividad;
    
    public DTImagenActividad() {
    }

    public DTImagenActividad(String nombre, String ruta, String nombreActividad) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.nombreActividad = nombreActividad;
    }


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

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }
    
    
    
}
