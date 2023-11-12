/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class DTDepartamento {
    private String nombre;
    private String descripcion;
    private String url;
    private List<Actividad> listaActTur;

    public DTDepartamento() {
    }

    public DTDepartamento(String nombre, String descripcion, String url, List<Actividad> listaActTur) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
        this.listaActTur = listaActTur;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUrl() {
        return url;
    }


    public List<Actividad> getListaActTur() {
        return listaActTur;
    }
  
}
