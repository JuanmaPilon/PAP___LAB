package Servlets;

import WebServices.CorreoElectronicoExistenteException_Exception;
import WebServices.PreexistingEntityException_Exception;
import WebServices.WebServices;
import WebServices.WebServicesService;
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

@WebServlet(name = "SvProveedor", urlPatterns = {"/SvProveedor"})
@MultipartConfig(
        maxFileSize = 1024 * 1024, // Tamaño máximo del archivo en bytes (ejemplo: 1 MB)
        maxRequestSize = 1024 * 1024 // Tamaño máximo total de la solicitud en bytes
)
public class SvProveedor extends HttpServlet {

    //Fabrica fabrica = Fabrica.getInstance();
   // IControlador control = fabrica.getIControlador();

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
        try {
                    //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();
            Date fNacimiento = null;

            String nickname = request.getParameter("nickname");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String contrasenia = request.getParameter("contrasenia");
            String correo = request.getParameter("correo");
            String fechaNacimientoString = request.getParameter("fechaNacimiento");
            String descripcion = request.getParameter("descripcion");
            String link = request.getParameter("sitioWeb");

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

            port.altaDeUsuarioProveedor(nickname, nombre, apellido, contrasenia, correo, fechaNacimientoString, descripcion, link);

            if (archivo.getSize() > 0) {
                nombreArchivo = archivo.getSubmittedFileName();
                if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
                    ServletContext context = request.getServletContext();
                    String rutaCompleta = context.getRealPath("/images/") + File.separator + nombreArchivo;

                    // Copiar el archivo a la ubicación relativa
                    Files.copy(archivo.getInputStream(), Paths.get(rutaCompleta), StandardCopyOption.REPLACE_EXISTING);

                    String rutaRelativa = "images" + File.separator + nombreArchivo;

                    try {
                        port.altaDeImagenPerfil(nombreArchivo, rutaRelativa, nombre);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        String errorMessage = "Ya existe otro usuario con esa imagen, se ha dado de alta el usuario sin imagen";
                        String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'login.jsp';</script>";
                        response.getWriter().write(alertScript);

                    }
                }
            }
          response.sendRedirect("login.jsp");
        } catch (PreexistingEntityException_Exception ex) {
            ex.printStackTrace();
            String errorMessage = "Ya existe otro usuario con ese nickname";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'altaUsuario.jsp';</script>";
            response.getWriter().write(alertScript);

        } catch (CorreoElectronicoExistenteException_Exception ex) {
            ex.printStackTrace();
            String errorMessage = "Ya existe otro usuario con ese correo";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'altaUsuario.jsp';</script>";
            response.getWriter().write(alertScript);

        } catch (Exception ex) {
            ex.printStackTrace();
            String errorMessage = "Se ha producido un error, porvafor verifique los campos";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'altaUsuario.jsp';</script>";
            response.getWriter().write(alertScript);

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
