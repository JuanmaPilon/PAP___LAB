package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.DTImagenActividad;
import logica.DTSalidaTuristica;
import logica.Fabrica;
import logica.IControlador;


@WebServlet(name = "SvPerfilSalida", urlPatterns = {"/SvPerfilSalida"})
public class SvPerfilSalida extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession misesion = request.getSession();
        String nombreSalida = request.getParameter("nombreSalida");
        DTSalidaTuristica salT = control.ConsultaSalidaTuristica(nombreSalida);
        misesion.setAttribute("salida", salT);
        try {
            DTImagenActividad ImagenActividad = control.buscarImagenPorActividad(nombreSalida);
            String imagenRuta = ImagenActividad.getRuta();
            misesion.setAttribute("imagen", imagenRuta);

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
