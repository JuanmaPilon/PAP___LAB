package Servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controlador;
import persistencia.exceptions.CorreoElectronicoExistenteException;
import persistencia.exceptions.NicknameExistenteException;
import persistencia.exceptions.PreexistingEntityException;

@WebServlet(name = "SvTurista", urlPatterns = {"/SvTurista"})
public class SvTurista extends HttpServlet {
Controlador control = Controlador.getInstance();

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
        Date fNacimiento = null;
        String nickname = request.getParameter("nickname");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String contrasenia = request.getParameter("contrasenia");
        String correo = request.getParameter("correo");
        String fechaNacimientoString = request.getParameter("fechaNacimiento");
        String imagen = request.getParameter("");
        String nacionalidad = request.getParameter("nacionalidad");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); // Define el formato de la fecha
            fNacimiento = sdf.parse(fechaNacimientoString); // Convierte la cadena a Date
        } catch (ParseException e) {
            // Maneja la excepción si la cadena no se puede convertir a Date
            e.printStackTrace(); // O usa un logger para registrar el error
        }

      
    try { 
        control.AltaDeUsuarioTurista(nickname, nombre, apellido,contrasenia, correo, fNacimiento, nacionalidad);
    } catch (PreexistingEntityException ex) {
        Logger.getLogger(SvTurista.class.getName()).log(Level.SEVERE, null, ex);
    } catch (CorreoElectronicoExistenteException ex) {
        Logger.getLogger(SvTurista.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        Logger.getLogger(SvTurista.class.getName()).log(Level.SEVERE, null, ex);
    }
      
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
