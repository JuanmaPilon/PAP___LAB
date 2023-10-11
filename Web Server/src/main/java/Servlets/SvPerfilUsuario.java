/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controlador;
import logica.Usuario;


@WebServlet(name = "SvPerfilUsuario", urlPatterns = {"/SvPerfilUsuario"})
public class SvPerfilUsuario extends HttpServlet {
    Controlador control = Controlador.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    
    
    
    //doget con los datos de usuario para cargar en el perfil
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String usuarioSeleccionado = request.getParameter("usuario");
            Usuario usu = control.ConsultaDeUsuario(usuarioSeleccionado);
            HttpSession misesion = request.getSession();
            misesion.setAttribute("usuPerfil",usu);
            response.sendRedirect("perfilUsuario.jsp");
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
