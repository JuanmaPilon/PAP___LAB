package Servlets;

import WebServices.DtImagenActividad;
import WebServices.DtSalidaTuristica;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

@WebServlet(name = "SvConsultaSalidaMovil", urlPatterns = {"/SvConsultaSalidaMovil"})
public class SvConsultaSalidaMovil extends HttpServlet {

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
        String actividadSalidaId = request.getParameter("actividadSalidaId");
        DtSalidaTuristica salT = port.consultaSalidaTuristica(actividadSalidaId);
        StringBuilder htmlResponse = new StringBuilder();
        //DTImagenActividad imagen = null;
        try {
            // cambioar eso por la ruta de la imagen por que de la forma que esta revienta, tiene que estar comentado
            DtImagenActividad imagen = port.buscarImagenPorActividad(actividadSalidaId);
            String imagenRuta = "images/sinImagen.png";

//            if (imagen == null) {
//                imagenRuta = "images/sinImagen.png";
//
//            } else {
//                imagenRuta = imagen.getRuta();
//            }
           
            htmlResponse.append("<div class='Salida'>");
            htmlResponse.append("<h2>Detalles de la Salida Turstica:</h2>");
//            htmlResponse.append("<img src=\"" + imagenRuta + "\" alt=\"Imagen de la salida\" style=\"width: 300px; height: 300px;\">");
            htmlResponse.append("<p>").append(salT.getNombre()).append("</p>");
            htmlResponse.append("<p>").append(salT.getFSalida()).append("</p>");
            htmlResponse.append("<p>").append(salT.getLugar()).append("</p>");
            htmlResponse.append("</div>");
            
        } catch (Exception ex) {
            String imagenRuta = "images/sinImagen.png";
            imagenRuta = "images/sinImagen.png";
            htmlResponse.append("<div class='Salida'>");
            htmlResponse.append("<h2>Detalles de la Salida Turstica:</h2>");
//            htmlResponse.append("<img src=\"" + imagenRuta + "\" alt=\"Imagen de la salida\" style=\"width: 300px; height: 300px;\">");
            htmlResponse.append("<p>").append(salT.getNombre()).append("</p>");
            htmlResponse.append("<p>").append(salT.getFSalida()).append("</p>");
            htmlResponse.append("<p>").append(salT.getLugar()).append("</p>");
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
