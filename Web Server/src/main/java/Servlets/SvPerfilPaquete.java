package Servlets;

import WebServices.DtPaquete;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author natil
 */
@WebServlet(name = "SvPerfilPaquete", urlPatterns = {"/SvPerfilPaquete"})
public class SvPerfilPaquete extends HttpServlet {
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
        
        String paquete = request.getParameter("paquete");
        
        DtPaquete dtpaquete = port.traerDTPaquete(paquete);
        
        List<String> listaActividadesPaquete = port.listaActividadesDelPaquete(paquete).getLista();
        System.out.println("aca svperfilpaque" + paquete);
        HttpSession misesion = request.getSession();
        misesion.setAttribute("dtpaquete", dtpaquete);
        misesion.setAttribute("listaActividadesPaquete", listaActividadesPaquete);
        response.sendRedirect("perfilPaquete.jsp");
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
