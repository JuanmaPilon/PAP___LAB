/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Actividad;
import logica.DTSalidaTuristica;
import logica.Fabrica;
import logica.IControlador;
import logica.SalidaTuristica;

/**
 *
 * @author Pc
 */
@WebServlet(name = "SvSalida", urlPatterns = {"/SvSalida"})
public class SvSalida extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String nombreActividad = request.getParameter("actividad");
    String nombreSalida = request.getParameter("actividadSalida");

    if (nombreActividad != null) {
        // Realizar búsqueda por nombre de actividad
        
        ArrayList<DTSalidaTuristica> listaSalidas = control.encontraSalidasTuristicasDeActividad(nombreActividad);
        
        ArrayList<String> nombresSalidas = new ArrayList<>();
        for (DTSalidaTuristica salida : listaSalidas) {
            nombresSalidas.add(salida.getNombre());
        }

        String respuesta = String.join(",", nombresSalidas);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(respuesta);
        
        
    } else if (nombreSalida != null) {
        // Realizar búsqueda por nombre de salida
        
        SalidaTuristica salidaTuristica = control.ConsultaSalidaTuristica(nombreSalida);

        HttpSession misesion = request.getSession();
        misesion.setAttribute("salida", salidaTuristica);

        response.sendRedirect("perfilSalidaTuristica.jsp");
    } else {
        // Manejar el caso en el que no se proporciona ni "actividad" ni "actividadSalida"
        response.getWriter().write("Por favor, proporcione un parámetro válido.");
    }
}


    /*
     String nombreActividad = request.getParameter("actividad");

        ArrayList<DTSalidaTuristica> listaSalidas = control.encontraSalidasTuristicasDeActividad(nombreActividad);

        ArrayList<String> nombresSalidas = new ArrayList<>();
        for (DTSalidaTuristica salida : listaSalidas) {
            nombresSalidas.add(salida.getNombre());
        }

        String respuesta = String.join(",", nombresSalidas);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(respuesta);

        String nombreSalida = request.getParameter("actividadSalida");

        SalidaTuristica salidaTuristica = control.ConsultaSalidaTuristica(nombreSalida);

        HttpSession misesion = request.getSession();
        misesion.setAttribute("salida", salidaTuristica);
        response.sendRedirect("perfilSalidaTuristica.jsp");
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
