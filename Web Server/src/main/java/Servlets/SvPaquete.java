/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Fabrica;
import logica.IControlador;
import logica.Paquete;
import logica.exceptions.PaqueteSinActividad;
import logica.exceptions.PaqueteYaComprado;

@WebServlet(name = "SvPaquete", urlPatterns = {"/SvPaquete"})
public class SvPaquete extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession misesion = request.getSession();
        String actividadesParam = request.getParameter("actividades");

        ArrayList<String> listaPaquetes = control.listaPaquetes();

        List<Paquete> infoPaquetes = control.consultaPaquetes();
        ArrayList<String> paquetesConActividad = new ArrayList<>();

        for (Paquete paquete : infoPaquetes) {
            if (paquete.getListaActividades() != null && !paquete.getListaActividades().isEmpty()) {
                paquetesConActividad.add(paquete.getNombre());
            }
        }

        if ("true".equals(actividadesParam)) { //En inscripcion necesito los paquetes con una o mas actividades asociadas

            misesion.setAttribute("paquetesConActividad", paquetesConActividad);

            String paquetesConActividadRespond = String.join(",", paquetesConActividad);

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().write(paquetesConActividadRespond);

        } else if (actividadesParam != "true") {
            misesion.setAttribute("listaPaquetes", listaPaquetes);
            String paq = String.join(",", listaPaquetes);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(paq);
        }

        //response.sendRedirect("consultaPaqueteActividadesTuristicas.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombrePaquete = request.getParameter("paquete");

            String usuario = request.getParameter("usuario");

            int cantTuristas = Integer.parseInt(request.getParameter("cantTuristas"));

           Calendar calendar = Calendar.getInstance();
           Date fechaDeHoy = calendar.getTime();

            control.CompraDePaquete(usuario, nombrePaquete, cantTuristas, fechaDeHoy);  //throws PaqueteSinActividad, PaqueteYaComprado
            response.sendRedirect("logedUser.jsp");
        } catch (PaqueteSinActividad e) {
            e.printStackTrace(); 
            String errorMessage = "El paquete no tiene actividad. Por favor, elija otro paquete.";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "');</script>";
            response.getWriter().write(alertScript);
        } catch (PaqueteYaComprado ex) {
            ex.printStackTrace();  
            String errorMessage = "El paquete ya ha sido comprado. Por favor, elija otro paquete.";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "');</script>";
            response.getWriter().write(alertScript);
        } catch (Exception exep) {
            exep.printStackTrace();  
            String errorMessage = "Ocurrió un error inesperado. Por favor, inténtelo de nuevo.";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "');</script>";
            response.getWriter().write(alertScript);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}