package Servlets;

import WebServices.DtImagenActividad;
import WebServices.DtSalidaTuristica;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet(name = "SvPerfilSalida", urlPatterns = {"/SvPerfilSalida"})
public class SvPerfilSalida extends HttpServlet {

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
        String nombreSalida = request.getParameter("nombreSalida");
        DtSalidaTuristica salT = port.consultaSalidaTuristica(nombreSalida);
        misesion.setAttribute("salida", salT);
        try {
            DtImagenActividad ImagenActividad = port.buscarImagenPorActividad(nombreSalida);
//            String imagenRuta = ImagenActividad.getRuta();
//            misesion.setAttribute("imagen", imagenRuta);

        } catch (Exception e) {
            String imagenVacia = "images/usuarioSinFoto.png";
            misesion.setAttribute("imagen", imagenVacia);

        }
        //response.sendRedirect("perfilSalida.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
