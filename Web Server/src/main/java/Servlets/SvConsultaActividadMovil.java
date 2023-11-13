package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.DTActividad;
import logica.DTImagenActividad;
import logica.Fabrica;
import logica.IControlador;

@WebServlet(name = "SvConsultaActividadMovil", urlPatterns = {"/SvConsultaActividadMovil"})
public class SvConsultaActividadMovil extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actividad = request.getParameter("actividadSalidaId");
        DTActividad act = control.traerDTActividad(actividad);
        StringBuilder htmlResponse = new StringBuilder();
        //DTImagenActividad imagen = null;
        try {
            // cambioar eso por la ruta de la imagen por que de la forma que esta revienta, tiene que estar comentado
            DTImagenActividad imagen = control.buscarImagenPorActividad(actividad);
            String imagenRuta = "images/sinImagen.png";

            if (imagen == null) {
                imagenRuta = "images/sinImagen.png";

            } else {
                imagenRuta = imagen.getRuta();
            }

            htmlResponse.append("<div class='Actividad'>");
            htmlResponse.append("<h2>Detalles de la Actividad: </h2>");
            htmlResponse.append("<img src=\"" + imagenRuta + "\" alt=\"Imagen de la salida\" style=\"width: 300px; height: 300px;\">");
            htmlResponse.append("<p> Nombre de la Actividad: ").append(act.getNombre()).append("</p>");
            htmlResponse.append("<p> Ciudad: ").append(act.getCiudad()).append("</p>");
            htmlResponse.append("<p> Descripcion: ").append(act.getDescripcion()).append("</p>");
            htmlResponse.append("<p> Costo: ").append(String.valueOf(act.getCosto())).append("</p>");
            htmlResponse.append("</div>");

        } catch (Exception ex) {
            String imagenRuta = "images/sinImagen.png";
            imagenRuta = "images/sinImagen.png";
            htmlResponse.append("<div class='Actividad'>");
            htmlResponse.append("<h2>Detalles de la Actividad: </h2>");
            htmlResponse.append("<img src=\"" + imagenRuta + "\" alt=\"Imagen de la salida\" style=\"width: 300px; height: 300px;\">");
            htmlResponse.append("<p> Nombre de la Actividad: ").append(act.getNombre()).append("</p>");
            htmlResponse.append("<p> Ciudad: ").append(act.getCiudad()).append("</p>");
            htmlResponse.append("<p> Descripcion: ").append(act.getDescripcion()).append("</p>");
            htmlResponse.append("<p> Costo: ").append(String.valueOf(act.getCosto())).append("</p>");
            htmlResponse.append("</div>");
            Logger.getLogger(SvObtenerActividadesCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Configurar la respuesta como HTML
        response.setContentType("text/html");

        // Enviar el fragmento de HTML como respuesta al cliente
        PrintWriter out = response.getWriter();
        out.println(htmlResponse.toString());

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
