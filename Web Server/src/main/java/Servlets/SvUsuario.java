package Servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.DTUsuario;
import logica.Fabrica;
import logica.IControlador;

@WebServlet(name = "SvUsuario", urlPatterns = {"/SvUsuario"})
public class SvUsuario extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
// este doget de usuarios solo devuelve una lista de string de los usuarios concatenados

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> listaUsuarios = control.listaUsuarios();
        int startIndex = Integer.parseInt(request.getParameter("startIndex"));
        int endIndex = Math.min(startIndex + 40, listaUsuarios.size()); // Envía 40 usuarios a la vez
        String usuariosSubset = String.join(",", listaUsuarios.subList(startIndex, endIndex));
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(usuariosSubset);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuarioConsultado = request.getParameter("nickname");
        String usuario = request.getParameter("usuario");
        String marcarUsuario = request.getParameter("marcarUsuario"); //MARCAR ACTIVIDAD COMO FAVORITA
        String DesmarcarUsuario = request.getParameter("DesmarcarUsuario");

        if (marcarUsuario == null) {
            marcarUsuario = "NULL";
        }
        if (DesmarcarUsuario == null) {
            DesmarcarUsuario = "NULL";
        }

        if (marcarUsuario.equals("marcarUsuario")) {
            control.marcarUsuarioComoFavorita(usuario, usuarioConsultado);
            DTUsuario restUsu = control.traerDTUsuario(usuario);
            request.getSession().setAttribute("usu", restUsu);
            String errorMessage = "Usuario agregado como favorito";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'perfilUsuario.jsp';</script>";
            response.getWriter().write(alertScript);
        }
        if (DesmarcarUsuario.equals("DesmarcarUsuario")) {
            control.DesMarcarUsuarioFavorito(usuario, usuarioConsultado);
            DTUsuario restUsu = control.traerDTUsuario(usuario);
            request.getSession().setAttribute("usu", restUsu);
            String errorMessage = "Usuario eliminado como favorito";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'perfilUsuario.jsp';</script>";
            response.getWriter().write(alertScript);

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
