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
import logica.DTUsuario;
import logica.Fabrica;
import logica.IControlador;
import logica.Usuario;


@WebServlet(name = "SvAutenticarUsuario", urlPatterns = {"/SvAutenticarUsuario"})
public class SvAutenticarUsuario extends HttpServlet {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String usuario = request.getParameter("username"); //Obtengo el nombre ingresado
    String contrasenia = request.getParameter("password"); //Obtengo la contrasenia ingresada

    boolean autenticado = autenticarUsuario(usuario, contrasenia);

    if (autenticado) {
        request.getSession().setAttribute("usuario", usuario);  // Si el usuario es autenticado, puedes almacenar información de sesión
        Usuario usu = control.ConsultaDeUsuario(usuario);
        request.getSession().setAttribute("usu", usu);
        response.sendRedirect("logedUser.jsp"); // Redirige al usuario a la página de inicio
    } else {
        request.getSession().setAttribute("errorMensaje", "Usuario y/o contrasenia incorrectas"); // Almacena un mensaje de error en la sesión
        response.sendRedirect("login.jsp"); // Redirige al usuario nuevamente a la página de inicio de sesión
    }
}

    
     private boolean autenticarUsuario(String username, String password) {
        ArrayList<DTUsuario> listaUsuarios = control.traerUsuarioMod(); // Obtén la lista de usuarios con nombres de usuario y contraseñas
        
        // Recorre la lista de usuarios para verificar las credenciales
    for (DTUsuario usuario : listaUsuarios) {
        if (usuario.getNickname().equals(username) && usuario.getContrasenia().equals(password)) {
            // Las credenciales son correctas
            return true;
        }
    }

    // Si llegamos aquí, las credenciales son incorrectas
    return false;
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
