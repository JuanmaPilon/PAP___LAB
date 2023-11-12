/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webServices;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import logica.DTActividad;

/**
 *
 * @author natil
 */
@XmlRootElement
public class ListaDTActividad {
    private List<DTActividad> lista;
    
     @XmlElement
    public List<DTActividad> getLista() {
        return lista;
    }    

    public void setLista(List<DTActividad> lista) {
        this.lista = lista;
    }
        

}