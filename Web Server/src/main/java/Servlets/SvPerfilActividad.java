package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Actividad;
import logica.Categoria;
import logica.DTSalidaTuristica;
import logica.Fabrica;
import logica.IControlador;

@WebServlet(urlPatterns = {"/SvPerfilActividad"})
public class SvPerfilActividad extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession misesion = request.getSession();
        String nombreActividad = request.getParameter("nombreActividad");
        System.out.println("Nombre de la actividad: " + nombreActividad);
        Actividad act = control.ConsultaActividadTuristica(nombreActividad);
        ArrayList<String> listaCategoria = control.traerCategoriasActividad(act.getNombre()); // categorias de la actividad
        
        
        ArrayList<DTSalidaTuristica> listaDtSalidas = control.encontraSalidasTuristicasDeActividad(act.getNombre() );
       
                
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
