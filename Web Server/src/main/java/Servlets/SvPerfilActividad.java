package Servlets;

import WebServices.DtActividad;
import WebServices.DtSalidaTuristica;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/SvPerfilActividad"})
public class SvPerfilActividad extends HttpServlet {

    //Fabrica fabrica = Fabrica.getInstance();
    //IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();
        HttpSession misesion = request.getSession();
        String nombreActividad = request.getParameter("nombreActividad");
       // System.out.println("Nombre de la actividad: " + nombreActividad);
        DtActividad act = port.traerDTActividad(nombreActividad);
        List<String> listaCategoria = port.traerCategoriasActividad(act.getNombre()).getLista(); // categorias de la actividad
        
        
        List<DtSalidaTuristica> listaDtSalidas = port.encontraSalidasTuristicasDeActividad(act.getNombre()).getLista();

                
        misesion.setAttribute("nombresSalidasActividad", listaDtSalidas);
        misesion.setAttribute("actividad", act);//datos de la actividad
        misesion.setAttribute("categorias", listaCategoria);//categorias de la actividad (lista de string)
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
