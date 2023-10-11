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
import logica.Categoria;
import logica.Controlador;
import logica.Departamento;

/**
 *
 * @author Pc
 */
@WebServlet(name = "SvCategoria", urlPatterns = {"/SvCategoria"})
public class SvCategoria extends HttpServlet {
Controlador control = Controlador.getInstance();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    List<Categoria> listaCategorias = control.listaCategorias(); // Cambiar listaDepartamentos a listaCategorias
    ArrayList<String> nombresCategorias = new ArrayList<>(); // Cambiar nombresDepartamentos a nombresCategorias
    for (Categoria categoria : listaCategorias) { // Cambiar Departamento a Categoria
        nombresCategorias.add(categoria.getNombre()); // Cambiar getNombre() a un método adecuado para obtener el nombre de la categoría
    }

    String categorias = String.join(",", nombresCategorias);
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(categorias);
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
