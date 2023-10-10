/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controlador;
import logica.Departamento;

/**
 *
 * @author Pc
 */
@WebServlet(name = "SvDepartamento", urlPatterns = {"/SvDepartamento"})
public class SvDepartamento extends HttpServlet {
     Controlador control = Controlador.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Departamento> listaDepartamentos = control.listaDepartamentos();
        ArrayList<String> nombresDepartamentos = new ArrayList<>();

        for (Departamento departamento : listaDepartamentos) {
            nombresDepartamentos.add(departamento.getNombre());
        }

        String nombresDepartamentosJson = new Gson().toJson(nombresDepartamentos);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(nombresDepartamentosJson);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
