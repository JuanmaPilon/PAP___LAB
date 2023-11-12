
package webServices;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ListaString {
    private List<String> lista;


    @XmlElement
    public List<String> getLista() {
        return lista;
    }    

    public void setLista(List<String> lista) {
        this.lista = lista;
    }
}