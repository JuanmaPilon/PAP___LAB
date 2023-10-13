package Servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controlador;
import javax.servlet.http.HttpSession;
import logica.Fabrica;
import logica.IControlador;

@WebServlet(name = "SvUsuario", urlPatterns = {"/SvUsuario"})
public class SvUsuario extends HttpServlet {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
// este doget de usuarios solo devuelve una lista de strings (de 10 en 10, por motivos de rendimiento)
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<String> listaUsuarios = control.listaUsuarios();
    int startIndex = Integer.parseInt(request.getParameter("startIndex"));
    int endIndex = Math.min(startIndex + 10, listaUsuarios.size()); // Envía 10 usuarios a la vez, puedes ajustar este número según tus necesidades
    String usuariosSubset = String.join(",", listaUsuarios.subList(startIndex, endIndex));
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(usuariosSubset);
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}