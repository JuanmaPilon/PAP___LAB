package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Actividad;
import logica.Categoria;
import logica.Fabrica;
import logica.IControlador;


@WebServlet(name = "SvObtenerActividadesCategoria", urlPatterns = {"/SvObtenerActividadesCategoria"})
public class SvObtenerActividadesCategoria extends HttpServlet {
 Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener el ID del departamento desde los parámetros de la solicitud
        String categoriaId = request.getParameter("categoriaId");
        System.out.println("Departamento ID recibido: " + categoriaId);
        // Lógica para obtener las actividades de la categoria con el ID proporcionado   
        Categoria cat = control.traerCategoria(categoriaId);
        ArrayList<Actividad> actividades = cat.getListaActividad();

        // Generar el fragmento de HTML con las actividades de la categoria
        StringBuilder htmlResponse = new StringBuilder();
        for (Actividad actividad : actividades) {
            htmlResponse.append("<div class='actividad'>");
            htmlResponse.append("<h3>").append(actividad.getNombre()).append("</h3>");
            htmlResponse.append("<p>").append(actividad.getDescripcion()).append("</p>");
            htmlResponse.append("</div>");
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
