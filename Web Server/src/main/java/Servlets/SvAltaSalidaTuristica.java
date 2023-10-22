package Servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import logica.Fabrica;
import logica.IControlador;
import persistencia.exceptions.CorreoElectronicoExistenteException;
import persistencia.exceptions.PreexistingEntityException;

@WebServlet(name = "SvAltaSalidaTuristica", urlPatterns = {"/SvAltaSalidaTuristica"})
@MultipartConfig(
        maxFileSize = 1024 * 1024, // Tamaño máximo del archivo en bytes (ejemplo: 1 MB)
        maxRequestSize = 1024 * 1024 // Tamaño máximo total de la solicitud en bytes
)

public class SvAltaSalidaTuristica extends HttpServlet {

    String errorMessage = null;

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String actividadTuristica = request.getParameter("actividadTuristica");
            String nombreSalida = request.getParameter("nombreSalida");
            String fechaHoraSalida = request.getParameter("fechaHoraSalida");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime fechaHoraLocal = LocalDateTime.parse(fechaHoraSalida, formatter);
            Date fechaHoraDate = java.util.Date.from(fechaHoraLocal.atZone(java.time.ZoneId.systemDefault()).toInstant());

            String lugarSalida = request.getParameter("lugarSalida");
            String cantidadMaxTuristasStr = request.getParameter("cantidadMaxTuristas");

            Part archivo = request.getPart("file");
            String nombreArchivo = null;

            int cantidadMaxTuristas = 0;
            if (cantidadMaxTuristasStr != null && !cantidadMaxTuristasStr.isEmpty()) {
                try {
                    cantidadMaxTuristas = Integer.parseInt(cantidadMaxTuristasStr);
                } catch (NumberFormatException e) {
                }
            }

            Date fechaHoraActual = Date.from(fechaHoraLocal.atZone(ZoneId.systemDefault()).toInstant());

            control.AltaSalidaTuristica(nombreSalida, cantidadMaxTuristas, fechaHoraActual, fechaHoraDate, lugarSalida, actividadTuristica);

            if (archivo.getSize() > 0) {
                nombreArchivo = archivo.getSubmittedFileName();
                if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
                    ServletContext context = request.getServletContext();
                    String rutaCompleta = context.getRealPath("/images/") + File.separator + nombreArchivo;

                    // Copiar el archivo a la ubicación relativa
                    Files.copy(archivo.getInputStream(), Paths.get(rutaCompleta), StandardCopyOption.REPLACE_EXISTING);

                    String rutaRelativa = "images" + File.separator + nombreArchivo;

                    try {
                        control.AltaDeImagenActividad(nombreArchivo, rutaRelativa, nombreSalida);
                    } catch (Exception ex) {
                        errorMessage = "Imagen ya en uso por otra actividad";
                        request.setAttribute("errorMessage", errorMessage);
                        request.getRequestDispatcher("altaActividadTuristica.jsp").forward(request, response);

                    }
                }
            }

        } catch (PreexistingEntityException ex) {
            errorMessage = "Ya hay un salida con ese nombre";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("altaSalidaTuristica.jsp").forward(request, response);

        } catch (Exception ex) {
            errorMessage = "Se ha producido un error. Verifique los campos";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("altaSalidaTuristica.jsp").forward(request, response);

        }

        if (errorMessage == null) {
            response.sendRedirect("logedUser.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
