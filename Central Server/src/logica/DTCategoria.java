/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class DTCategoria {
    private String nombre;
    private List<DTActividad> listaActividad;

    public DTCategoria() {
    }

    public DTCategoria(String nombre) {
        this.nombre = nombre;
    }

    public DTCategoria(String nombre, List<DTActividad> listaActividad) {
        this.nombre = nombre;
        this.listaActividad = listaActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public List<DTActividad> getListaActividad() {
        return listaActividad;
    }

}
