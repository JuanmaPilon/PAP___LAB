
package Servlets;

import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author natil
 */
@WebServlet(name = "SvMostrarImagenActividad", urlPatterns = {"/SvMostrarImagenActividad"})
public class SvMostrarImagenActividad extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();
        
        String nombreActividad = request.getParameter("nombreActividad");
        
        System.out.println("nomAct servlet: " + nombreActividad);
        
        byte[] imagenBytes = port.traerImagenActividad(nombreActividad);

        // Configura la respuesta para que devuelva una imagen
        response.setContentType("image/jpg");
        response.setContentLength(imagenBytes.length);

        // Escribe los bytes de la imagen en la respuesta
        response.getOutputStream().write(imagenBytes);
               
        
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
