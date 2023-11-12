package Servlets;

import WebServices.WebServices;
import WebServices.WebServicesService;
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

    //Fabrica fabrica = Fabrica.getInstance();
    //IControlador control = fabrica.getIControlador();

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
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();
        String nickname = request.getParameter("nickname");

        boolean nicknameDisponible = port.validarNickname(nickname);

        if (nicknameDisponible) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("nickname disponible");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("nickname NO disponible");
        }

    }

}
