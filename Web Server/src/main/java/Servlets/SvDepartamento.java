/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.DTDepartamento;
import logica.Departamento;
import logica.Fabrica;
import logica.IControlador;

/**
 *
 * @author Pc
 */
@WebServlet(name = "SvDepartamento", urlPatterns = {"/SvDepartamento"})
public class SvDepartamento extends HttpServlet {
    
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<DTDepartamento> listaDepartamentos = control.listaDTDepartamentos();
        ArrayList<String> nombresDepartamentos = new ArrayList<>();
        for (DTDepartamento departamento : listaDepartamentos) {
            nombresDepartamentos.add(departamento.getNombre());
        }

        String departamentos = String.join(",", nombresDepartamentos);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(departamentos);

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
