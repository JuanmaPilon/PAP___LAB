/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webServices;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import logica.DTUsuario;

/**
 *
 * @author natil
 */
@XmlRootElement
public class ListaDTUsuario {
    private List<DTUsuario> lista;
    
     @XmlElement
    public List<DTUsuario> getLista() {
        return lista;
    }    

    public void setLista(List<DTUsuario> lista) {
        this.lista = lista;
    }
        

}