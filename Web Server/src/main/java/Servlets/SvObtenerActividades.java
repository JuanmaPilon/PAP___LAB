package Servlets;

import WebServices.DtActividad;
import WebServices.DtImagenActividad;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvObtenerActividades", urlPatterns = {"/SvObtenerActividades"})
public class SvObtenerActividades extends HttpServlet {
    //Fabrica fabrica = Fabrica.getInstance();
    // IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();

        // Obtener el ID del departamento desde los parámetros de la solicitud
        String departamentoId = request.getParameter("departamentoId");
        System.out.println("Departamento ID recibido: " + departamentoId);
        // Lógica para obtener las actividades del departamento con el ID proporcionado       
        List<DtActividad> actividades = port.listaActividadesConfirmadasDepartamento(departamentoId).getLista();

        // Generar el fragmento de HTML con las actividades del departamento
        StringBuilder htmlResponse = new StringBuilder();
        for (DtActividad actividad : actividades) {
            DtImagenActividad imagen;

            try {
                imagen = port.buscarImagenPorActividad(actividad.getNombre());
                String nomActividad = imagen.getNombreActividad();
                String nomActividadCodificado = URLEncoder.encode(nomActividad, "UTF-8");
                htmlResponse.append("<div class='actividad'>");
                htmlResponse.append("<img src=SvMostrarImagenActividad?nombreActividad=")
                        .append(nomActividadCodificado)
                        .append(" alt=\"Imagen de la salida\" style=\"width: 300px; height: 300px;\">");
                htmlResponse.append("<h3>").append(actividad.getNombre()).append("</h3>");
                htmlResponse.append("<p>").append(actividad.getDescripcion()).append("</p>");
                htmlResponse.append("</div>");
            } catch (Exception ex) {
                Logger.getLogger(SvObtenerActividadesCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }

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
