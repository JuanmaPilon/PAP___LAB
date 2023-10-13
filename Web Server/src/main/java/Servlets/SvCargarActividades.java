/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controlador;

@WebServlet(name = "SvCargarActividades", urlPatterns = {"/SvCargarActividades"})
public class SvCargarActividades extends HttpServlet {
 Controlador control = Controlador.getInstance();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    //retona lista string con nombres de las actividades (de 10 en 10, por motivos de rendimiento)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    List<String> listaActividades = control.listaActividades();
    int startIndex1 = Integer.parseInt(request.getParameter("startIndex1"));
    int endIndex = Math.min(startIndex1 + 10, listaActividades.size()); // Envía 10 actividades a la vez, puedes ajustar este número según tus necesidades
    String actividadesSubset = String.join(",", listaActividades.subList(startIndex1, endIndex));
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(actividadesSubset);

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
