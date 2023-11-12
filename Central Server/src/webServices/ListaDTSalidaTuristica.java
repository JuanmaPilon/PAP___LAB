/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webServices;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import logica.DTSalidaTuristica;

/**
 *
 * @author natil
 */
@XmlRootElement
public class ListaDTSalidaTuristica {
    private List<DTSalidaTuristica> lista;
    
        @XmlElement
    public List<DTSalidaTuristica> getLista() {
        return lista;
    }    

    public void setLista(List<DTSalidaTuristica> lista) {
        this.lista = lista;
    }

}







