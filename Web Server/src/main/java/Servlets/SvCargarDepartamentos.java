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

import logica.Fabrica;
import logica.IControlador;

@WebServlet(name = "SvCargarDepartamentos", urlPatterns = {"/SvCargarDepartamentos"})
public class SvCargarDepartamentos extends HttpServlet {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    //retona lista string con nombres de deptos (de 10 en 10, por motivos de rendimiento)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    List<String> listaDeptos = control.listaDeptos();
    int startIndex = Integer.parseInt(request.getParameter("startIndex"));
    int endIndex = Math.min(startIndex + 10, listaDeptos.size()); // Envía 10 deptos a la vez, puedes ajustar este número según tus necesidades
    String deptosSubset = String.join(",", listaDeptos.subList(startIndex, endIndex));
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(deptosSubset);
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
