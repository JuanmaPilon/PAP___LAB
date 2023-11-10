package turismo;

import logica.Fabrica;
import logica.IControlador;
import presentacion.Principal;
import webServices.WebServices;


public class TurismoUy {
        public static void main(String args[]) {
            
            // Inicialización de la Fabrica
            Fabrica fabrica = Fabrica.getInstance();
            IControlador control = fabrica.getIControlador();
                WebServices p = new WebServices();
        p.publicar();
        
               Principal princ = new Principal(control);
               princ.setVisible(true);
               princ.setLocationRelativeTo(null);
               
                 

 
      
     
        
    }
}
