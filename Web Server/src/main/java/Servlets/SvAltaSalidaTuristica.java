package Servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controlador;

@WebServlet(name = "SvAltaSalidaTuristica", urlPatterns = {"/SvAltaSalidaTuristica"})
public class SvAltaSalidaTuristica extends HttpServlet {

    Controlador control = Controlador.getInstance();

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
    String departamento = request.getParameter("departamento");
    String actividadTuristica = request.getParameter("actividadTuristica");
    String nombreSalida = request.getParameter("nombreSalida");
    String fechaHoraSalida = request.getParameter("fechaHoraSalida");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    LocalDateTime fechaHoraLocal = LocalDateTime.parse(fechaHoraSalida, formatter);
    Date fechaHoraDate = java.util.Date.from(fechaHoraLocal.atZone(java.time.ZoneId.systemDefault()).toInstant());

    String lugarSalida = request.getParameter("lugarSalida");
    String cantidadMaxTuristasStr = request.getParameter("cantidadMaxTuristas");
    int cantidadMaxTuristas = 0;
    if (cantidadMaxTuristasStr != null && !cantidadMaxTuristasStr.isEmpty()) {
        try {
            cantidadMaxTuristas = Integer.parseInt(cantidadMaxTuristasStr);
        } catch (NumberFormatException e) {
        }
    }

    String imagenSalida = request.getParameter("imagenSalida");

    Date fechaHoraActual = Date.from(fechaHoraLocal.atZone(ZoneId.systemDefault()).toInstant());
    try {
        control.AltaSalidaTuristica(nombreSalida, cantidadMaxTuristas, fechaHoraActual, fechaHoraDate, lugarSalida, actividadTuristica);
    } catch (Exception ex) {
        Logger.getLogger(SvAltaSalidaTuristica.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
