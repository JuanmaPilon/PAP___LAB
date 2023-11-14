/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.DtActividad;
import WebServices.DtImagenActividad;
import WebServices.DtPaquete;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Pc
 */
@WebServlet(name = "SvBuscar", urlPatterns = {"/SvBuscar"})
public class SvBuscar extends HttpServlet {

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
        try {
            //String filtro = request.getParameter("filtro");

            String consulta = request.getParameter("consulta");

            List<DtActividad> listaActividades = port.listaActividadesConfirmadas().getLista();
            
            List<DtPaquete> listaPaquetes = port.traerListaDTPaquetes().getLista();

            List<DtImagenActividad> imagenesActividades = new ArrayList();
            DtImagenActividad dtImagen = new DtImagenActividad();

            List<DtActividad> actividadesFiltradas = (List<DtActividad>) listaActividades.stream()
                    .filter(actividad -> actividad.getNombre().contains(consulta) || actividad.getDescripcion().contains(consulta))
                    .collect(Collectors.toList());
            
            List<DtPaquete> paquetesFiltrados = (List<DtPaquete>) listaPaquetes.stream().filter(paquete -> paquete.getNombre().contains(consulta) || 
                     paquete.getDescripcion().contains(consulta)) 
            .collect(Collectors.toList());
                    

            for (DtActividad actividad : actividadesFiltradas) {
                dtImagen = port.traerDTImagenActividad(actividad.getNombre());
                if (dtImagen != null) {
                    imagenesActividades.add(dtImagen);
                }

            }

            HttpSession misesion = request.getSession();
            misesion.setAttribute("imagenesActividades", imagenesActividades);
            misesion.setAttribute("actividadesFiltradas", actividadesFiltradas);
            misesion.setAttribute("paquetesFiltrados", paquetesFiltrados);
            
            response.sendRedirect("tablaConsulta.jsp");
//            if (filtro == null) {
//                misesion.setAttribute("actividadesFiltradas", actividadesFiltradas);
//                response.sendRedirect("tablaConsulta.jsp");
//            } else if (filtro == "nombre") {
//                misesion.setAttribute("actividadesFiltradas", actividadesFiltradas);
//                response.sendRedirect("tablaConsulta.jsp");
//            } else if (filtro == "ano") {
//
//            } else if (filtro == "departamento") {
//
//            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
