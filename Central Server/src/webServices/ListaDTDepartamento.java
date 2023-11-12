/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webServices;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import logica.DTDepartamento;


/**
 *
 * @author natil
 */
@XmlRootElement
public class ListaDTDepartamento {
    private List<DTDepartamento> lista;
    
     @XmlElement
    public List<DTDepartamento> getLista() {
        return lista;
    }    

    public void setLista(List<DTDepartamento> lista) {
        this.lista = lista;
    }
        

}