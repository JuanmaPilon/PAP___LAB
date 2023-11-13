package Servlets;

import WebServices.DtActividad;
import WebServices.DtPaquete;
import WebServices.DtSalidaTuristica;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvInscripcion", urlPatterns = {"/SvInscripcion"})
public class SvInscripcion extends HttpServlet {

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
        String nombreActividad = (String) request.getParameter("actividad");
        DtActividad actividadConsultada = port.traerDTActividad(nombreActividad);
        List<DtSalidaTuristica> Salidas = port.encontraSalidasTuristicasDeActividad(actividadConsultada.getNombre()).getLista();
        ArrayList<String> nombreSalidas = new ArrayList();
        for (DtSalidaTuristica S : Salidas) {
            nombreSalidas.add(S.getNombre());
        }
        String Salts = String.join(",", nombreSalidas);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(Salts);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();
         try{
        String salidaSeleccionada = (String) request.getParameter("actividadSalida");
        String cantidadMaxTuristasStr = request.getParameter("cantTuristas");
        String formaPago = request.getParameter("formaPago");
        String nombreTurista = request.getParameter("nombreTurista");
        
        List<DtPaquete> paquetesVigentes = port.listaPaquetesCompradosVigentes(nombreTurista).getLista();
        
        
        int cantidadMaxTuristas = 0;
        if (cantidadMaxTuristasStr != null && !cantidadMaxTuristasStr.isEmpty()) {
            try {
                cantidadMaxTuristas = Integer.parseInt(cantidadMaxTuristasStr);
            } catch (NumberFormatException e) {
            }
        }
        
         } catch (Exception ex) {
             ex.printStackTrace();
            String errorMessage = "Ha ocurrido un error, verifique los campos.";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'inscripcionSalida.jsp';</script>";
            response.getWriter().write(alertScript);

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
