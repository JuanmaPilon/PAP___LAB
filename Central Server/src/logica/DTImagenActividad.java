/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTImagenActividad {
    
    private String nombre; 
    
    
    private String nombreActividad;
    
    private String UrlVideo;
    
    public DTImagenActividad() {
    }

    public DTImagenActividad(String nombre, String nombreActividad, String UrlVideo) {
        this.nombre = nombre;
        this.nombreActividad = nombreActividad;
        this.UrlVideo = UrlVideo;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }
    
    public String getUrlVideo() {
        return UrlVideo;
    }
    
}