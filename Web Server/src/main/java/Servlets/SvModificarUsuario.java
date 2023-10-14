/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.DTProveedor;
import logica.DTTurista;
import logica.Usuario;
import logica.Fabrica;
import logica.IControlador;
import logica.Proveedor;
import logica.Turista;

@WebServlet(name = "SvModificarUsuario", urlPatterns = {"/SvModificarUsuario"})
@MultipartConfig(
        maxFileSize = 1024 * 1024, // Tamaño máximo del archivo en bytes (ejemplo: 1 MB)
        maxRequestSize = 1024 * 1024 // Tamaño máximo total de la solicitud en bytes
)
public class SvModificarUsuario extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipoUsuario;
        String usuario = request.getParameter("usuario"); // Obtener el nombre del usuario de logedUser
        Usuario usuarioConsulta = control.ConsultaDeUsuario(usuario); // Consultar el usuario

        if (usuarioConsulta instanceof Turista) {
            tipoUsuario = "turista";
            DTTurista infoTurista = control.traerDTTurista(usuario);
            HttpSession misesion = request.getSession();
            misesion.setAttribute("infoTurista", infoTurista);
        } else {
            tipoUsuario = "proveedor";
            DTProveedor infoProveedor = control.traerDTProveedor(usuario);
            HttpSession misesion = request.getSession();
            misesion.setAttribute("infoProveedor", infoProveedor);
        }

        HttpSession misesion = request.getSession();
        misesion.setAttribute("tipoUsuario", tipoUsuario);

        response.sendRedirect("modificarUsuario.jsp?usuario=" + usuario + "&tipoUsuario=" + tipoUsuario);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nickname = request.getParameter("nickname");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String correo = request.getParameter("correo");
            String fechaNacimientoString = request.getParameter("fechaNacimiento");
            String tipoUsuario = request.getParameter("TipoUsuario");
            Date fNacimiento = null;

            SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoSalida = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaNacimientoDate = formatoEntrada.parse(fechaNacimientoString);
            String fechaFormateada = formatoSalida.format(fechaNacimientoDate);
            fNacimiento = formatoSalida.parse(fechaFormateada);

            if ("turista".equals(tipoUsuario)) {
                String nacionalidad = request.getParameter("nacionalidad");

                control.ModificarDatosDeUsuarioTurista(nickname, nombre, apellido, correo, fNacimiento, nacionalidad);
                response.sendRedirect("logedUser.jsp");
            } else {
                String descripcion = request.getParameter("descripcion");
                String link = request.getParameter("sitioWeb");
                control.ModificarDatosDeUsuarioProveedor(nickname, nombre, apellido, correo, fNacimiento, descripcion, link);
                response.sendRedirect("logedUser.jsp");
            }

        } catch (ParseException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Código de respuesta HTTP 500 (error interno del servidor)
            response.getWriter().write("Error con el formato de las fechas.");
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Código de respuesta HTTP 500 (error interno del servidor)
            response.getWriter().write("Se ha producido un error interno. Por favor, inténtalo de nuevo más tarde.");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
