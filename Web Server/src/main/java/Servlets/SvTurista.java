package Servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import logica.Controlador;
import logica.Fabrica;
import logica.IControlador;
import persistencia.exceptions.CorreoElectronicoExistenteException;
import persistencia.exceptions.PreexistingEntityException;

@WebServlet(name = "SvTurista", urlPatterns = {"/SvTurista"})
@MultipartConfig(
        maxFileSize = 1024 * 1024, // Tamaño máximo del archivo en bytes (ejemplo: 1 MB)
        maxRequestSize = 1024 * 1024 // Tamaño máximo total de la solicitud en bytes
)
public class SvTurista extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String errorMessage = null;
        try {
            Date fNacimiento = null;

            String nickname = request.getParameter("nickname");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String contrasenia = request.getParameter("contrasenia");
            String correo = request.getParameter("correo");
            String fechaNacimientoString = request.getParameter("fechaNacimiento");
            String nacionalidad = request.getParameter("nacionalidad");

            if (fechaNacimientoString != null) {

                SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat formatoSalida = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaNacimientoDate = formatoEntrada.parse(fechaNacimientoString);
                String fechaFormateada = formatoSalida.format(fechaNacimientoDate);
                fNacimiento = formatoSalida.parse(fechaFormateada);
            }

            Part archivo = request.getPart("file");
            String nombreArchivo = null;
            String rutaImagenNueva = null;

            control.AltaDeUsuarioTurista(nickname, nombre, apellido, contrasenia, correo, fNacimiento, nacionalidad);

            if (archivo.getSize() > 0) {
                nombreArchivo = archivo.getSubmittedFileName();
                if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
                    ServletContext context = request.getServletContext();
                    String rutaCompleta = context.getRealPath("/images/") + File.separator + nombreArchivo;

                    // Copiar el archivo a la ubicación relativa
                    Files.copy(archivo.getInputStream(), Paths.get(rutaCompleta), StandardCopyOption.REPLACE_EXISTING);

                    String rutaRelativa = "images" + File.separator + nombreArchivo;

                    try {
                        control.AltaDeImagenPerfil(nombreArchivo, rutaRelativa, nombre);
                    } catch (Exception ex) {
                        errorMessage = "Imagen ya en uso por otro usuario";
                        request.setAttribute("errorMessage", errorMessage);
                        request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);

                    }
                }
            }

        } catch (PreexistingEntityException ex) {
            errorMessage = "El usuario ya existe. Por favor, elige otro nombre de usuario.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);

        } catch (CorreoElectronicoExistenteException ex) {
            errorMessage = "La dirección de correo electrónico ya está en uso. Utiliza otra dirección de correo electrónico.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);

        } catch (Exception ex) {
            errorMessage = "Se ha producido un error. Verifique los campos";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);

        }

        if (errorMessage == null) {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
