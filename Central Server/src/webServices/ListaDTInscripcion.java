/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webServices;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import logica.DTInscripcion;


@XmlRootElement
public class ListaDTInscripcion {
    private List<DTInscripcion> lista;
    
     @XmlElement
    public List<DTInscripcion> getLista() {
        return lista;
    }    

    public void setLista(List<DTInscripcion> lista) {
        this.lista = lista;
    }
        

}