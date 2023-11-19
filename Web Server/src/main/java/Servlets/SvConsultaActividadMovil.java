package Servlets;

import WebServices.DtActividad;
import WebServices.DtImagenActividad;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvConsultaActividadMovil", urlPatterns = {"/SvConsultaActividadMovil"})
public class SvConsultaActividadMovil extends HttpServlet {

    //Fabrica fabrica = Fabrica.getInstance();
    //IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();
        String actividad = request.getParameter("actividadSalidaId");
        String nomActividadCodificado = URLEncoder.encode(actividad, "UTF-8");
        DtActividad act = port.traerDTActividad(actividad);
        StringBuilder htmlResponse = new StringBuilder();
        port.sumarVisitaActividad(actividad);
        //DTImagenActividad imagen = null;
        try {
            // cambioar eso por la ruta de la imagen por que de la forma que esta revienta, tiene que estar comentado
            DtImagenActividad imagen = port.buscarImagenPorActividad(actividad);
            String imagenRuta = "images/sinImagen.png";

//            if (imagen == null) {
//                imagenRuta = "images/sinImagen.png";
//
//            } else {
//               // imagenRuta = imagen.getRuta();
//            }

            htmlResponse.append("<div class='Actividad'>");
            htmlResponse.append("<h2>Detalles de la Actividad: </h2>");
            htmlResponse.append("<img src=SvMostrarImagenActividad?nombreActividad=")
            .append(nomActividadCodificado)
            .append( " alt=\"Imagen de la salida\" style=\"width: 300px; height: 300px;\">");
            htmlResponse.append("<p> Nombre de la Actividad: ").append(act.getNombre()).append("</p>");
            htmlResponse.append("<p> Ciudad: ").append(act.getCiudad()).append("</p>");
            htmlResponse.append("<p> Descripcion: ").append(act.getDescripcion()).append("</p>");
            htmlResponse.append("<p> Costo: ").append(String.valueOf(act.getCosto())).append("</p>");
            htmlResponse.append("</div>");

        } catch (Exception ex) {

            htmlResponse.append("<div class='Actividad'>");
            htmlResponse.append("<h2>Detalles de la Actividad: </h2>");
            //htmlResponse.append("<img src=SvMostrarImagenActividad?nombreActividad=\"" + nomActividadCodificado + "\" alt=\"Imagen de la salida\" style=\"width: 300px; height: 300px;\">");
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
