/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webServices;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;
import logica.Fabrica;
import logica.IControlador;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WebServices {
            Fabrica fabrica = Fabrica.getInstance();
            IControlador control = fabrica.getIControlador();
    private Endpoint endpoint = null;
    //Constructor
    public WebServices(){}

    //Operaciones las cuales quiero publicar

    @WebMethod(exclude = true)
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:9128/webservices", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }

    @WebMethod
    public String devolverTipoUsuario(String nickname) {
        
        return control.devolverTipoUsuario(nickname);
    }

}