package webServices;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import logica.DTPaquete;


/**
 *
 * @author natil
 */
@XmlRootElement
public class ListaDTPaquete {
    private List<DTPaquete> lista;
    
     @XmlElement
    public List<DTPaquete> getLista() {
        return lista;
    }    

    public void setLista(List<DTPaquete> lista) {
        this.lista = lista;
    }
        

}