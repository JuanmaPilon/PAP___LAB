/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.DtPaquete;
import WebServices.PaqueteSinActividad_Exception;
import WebServices.PaqueteYaComprado_Exception;
import WebServices.WebServices;
import WebServices.WebServicesService;
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


@WebServlet(name = "SvPaquete", urlPatterns = {"/SvPaquete"})
public class SvPaquete extends HttpServlet {

    //Fabrica fabrica = Fabrica.getInstance();
    //IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();

        HttpSession misesion = request.getSession();
        String actividadesParam = request.getParameter("actividades");

        List<String> listaPaquetes = port.listaPaquetes().getLista();

        List<DtPaquete> infoPaquetes = port.traerListaDTPaquetes().getLista();
        ArrayList<String> paquetesConActividad = new ArrayList<>();

        for (DtPaquete paquete : infoPaquetes) {
            if (!port.listaActividadesDelPaquete(paquete.getNombre()).getLista().isEmpty()) {
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
                        //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();
        try {
            String nombrePaquete = request.getParameter("paquete");

            String usuario = request.getParameter("usuario");

            int cantTuristas = Integer.parseInt(request.getParameter("cantTuristas"));

           Calendar calendar = Calendar.getInstance();
           Date fechaDeHoy = calendar.getTime();
           //no importa la fecha, la setea en lógica
           String fecha = "11/11/1900";

            port.compraDePaquete(usuario, nombrePaquete, cantTuristas, fecha);  //throws PaqueteSinActividad, PaqueteYaComprado
            response.sendRedirect("logedUser.jsp");
        } catch (PaqueteSinActividad_Exception e) {
            e.printStackTrace(); 
            String errorMessage = "El paquete no tiene actividad. Por favor, elija otro paquete.";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "');</script>";
            response.getWriter().write(alertScript);
        } catch (PaqueteYaComprado_Exception ex) {
            ex.printStackTrace();  
            String errorMessage = "El paquete ya ha sido comprado. Por favor, elija otro paquete.";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "');</script>";
            response.getWriter().write(alertScript);
        } 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}