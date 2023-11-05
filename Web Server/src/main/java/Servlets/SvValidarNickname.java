package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Fabrica;
import logica.IControlador;
import org.json.simple.JSONObject;

@WebServlet(name = "SvValidarNickname", urlPatterns = {"/SvValidarNickname"})
public class SvValidarNickname extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("application/json");
        String nickname = request.getParameter("nickname");

        boolean nicknameDisponible = control.validarNickname(nickname);

        // Crea un objeto JSON para la respuesta
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("disponible", nicknameDisponible);

        // Envía la respuesta JSON al cliente
        
        response.getWriter().write(jsonResponse.toString());
         
        
        
    }

}
