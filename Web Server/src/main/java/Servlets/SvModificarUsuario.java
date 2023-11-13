/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.DtImagenPerfil;
import WebServices.DtProveedor;
import WebServices.DtTurista;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@WebServlet(name = "SvModificarUsuario", urlPatterns = {"/SvModificarUsuario"})
@MultipartConfig(
        maxFileSize = 1024 * 1024, // Tamaño máximo del archivo en bytes (ejemplo: 1 MB)
        maxRequestSize = 1024 * 1024 // Tamaño máximo total de la solicitud en bytes
)
public class SvModificarUsuario extends HttpServlet {

    //Fabrica fabrica = Fabrica.getInstance();
    //IControlador control = fabrica.getIControlador();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            //llamado a wsdl
            WebServicesService service = new WebServicesService();
            WebServices port = service.getWebServicesPort();

            String tipoUsuario = request.getParameter("tipoUsuario");
            String usuario = (String) request.getParameter("usuario"); // Obtener el nombre del usuario de logedUser

            if (tipoUsuario.equals("turista")) {
                tipoUsuario = "turista";

                DtTurista infoTurista = port.traerDTTurista(usuario);

                HttpSession misesion = request.getSession();
                misesion.setAttribute("infoTurista", infoTurista);
            } else {
                tipoUsuario = "proveedor";
                DtProveedor infoProveedor = port.traerDTProveedor(usuario);
                HttpSession misesion = request.getSession();
                misesion.setAttribute("infoProveedor", infoProveedor);
            }

            try {
                DtImagenPerfil imagenPerfil = port.buscarImagenPorNickname(usuario);
                String rutaImagen = imagenPerfil.getRuta();

                HttpSession misesion = request.getSession();
                misesion.setAttribute("tipoUsuario", tipoUsuario);
                misesion.setAttribute("rutaImagen", rutaImagen);
                response.sendRedirect("modificarUsuario.jsp?usuario=" + usuario + "&tipoUsuario=" + tipoUsuario);
            } catch (Exception e) {
                String rutaImagen = "";
                HttpSession misesion = request.getSession();
                misesion.setAttribute("tipoUsuario", tipoUsuario);
                misesion.setAttribute("rutaImagen", rutaImagen);
                response.sendRedirect("modificarUsuario.jsp?usuario=" + usuario + "&tipoUsuario=" + tipoUsuario);
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Código de respuesta HTTP 500 (error interno del servidor)
            response.getWriter().write("Se ha producido un error doGet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();

        String errorMessage = null;
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

            Part archivo = request.getPart("file");
            String nombreArchivo = null;

            if ("turista".equals(tipoUsuario)) {

                String nacionalidad = request.getParameter("nacionalidad");

                port.modificarDatosDeUsuarioTurista(nickname, nombre, apellido, correo, fechaNacimientoString, nacionalidad);
                try {
                    if (archivo.getSize() > 0) {
                        nombreArchivo = archivo.getSubmittedFileName();
                        ServletContext context = request.getServletContext();
                        String rutaCompleta = context.getRealPath("/images/") + File.separator + nombreArchivo;

                        // Copiar el archivo a la ubicación relativa
                        Files.copy(archivo.getInputStream(), Paths.get(rutaCompleta), StandardCopyOption.REPLACE_EXISTING);
                        String rutaRelativa = "images" + File.separator + nombreArchivo;
                        port.modificarImagenPerfil(nombreArchivo, rutaRelativa, nickname);
                    }
                } catch (Exception ex) {
                    errorMessage = "Imagen ya en uso por otro usuario. Se modifico el resto de atributos.";
                    request.setAttribute("errorMessage", errorMessage);
                    request.getRequestDispatcher("modificarUsuario.jsp").forward(request, response);
                }
            } else {

                String descripcion = request.getParameter("descripcion");
                String link = request.getParameter("sitioWeb");
                port.modificarDatosDeUsuarioProveedor(nickname, nombre, apellido, correo, fechaNacimientoString, descripcion, link);

                try {
                    if (archivo.getSize() > 0) {
                        nombreArchivo = archivo.getSubmittedFileName();
                        ServletContext context = request.getServletContext();
                        String rutaCompleta = context.getRealPath("/images/") + File.separator + nombreArchivo;

                        // Copiar el archivo a la ubicación relativa
                        Files.copy(archivo.getInputStream(), Paths.get(rutaCompleta), StandardCopyOption.REPLACE_EXISTING);
                        String rutaRelativa = "images" + File.separator + nombreArchivo;

                        port.modificarImagenPerfil(nombreArchivo, rutaRelativa, nickname);
                    }
                } catch (Exception ex) {
                    errorMessage = "Imagen ya en uso por otro usuario. Se modifico el resto de atributos.";
                    request.setAttribute("errorMessage", errorMessage);
                    request.getRequestDispatcher("modificarUsuario.jsp").forward(request, response);
                }
            }

        } catch (ParseException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Código de respuesta HTTP 500 (error interno del servidor)
            response.getWriter().write("Error con el formato de las fechas.");
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Código de respuesta HTTP 500 (error interno del servidor)
            response.getWriter().write("Se ha producido un error interno. Por favor, compruebe los campos.");
        }

        if (errorMessage == null) {
            response.sendRedirect("logedUser.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
