/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.DTActividad;
import logica.DTImagenActividad;
import logica.DTImagenPerfil;
import logica.DTPaquete;
import logica.Fabrica;
import logica.IControlador;

/**
 *
 * @author Pc
 */
@WebServlet(name = "SvBuscar", urlPatterns = {"/SvBuscar"})
public class SvBuscar extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //String filtro = request.getParameter("filtro");

            String consulta = request.getParameter("consulta");

            ArrayList<DTActividad> listaActividades = control.listaActividadesConfirmadas();
            
            ArrayList<DTPaquete> listaPaquetes = control.traerListaDTPaquetes();

            ArrayList<DTImagenActividad> imagenesActividades = new ArrayList();
            DTImagenActividad dtImagen = new DTImagenActividad();

            ArrayList<DTActividad> actividadesFiltradas = (ArrayList<DTActividad>) listaActividades.stream()
                    .filter(actividad -> actividad.getNombre().contains(consulta) || actividad.getDescripcion().contains(consulta))
                    .collect(Collectors.toList());
            
            ArrayList<DTPaquete> paquetesFiltrados = (ArrayList<DTPaquete>) listaPaquetes.stream().filter(paquete -> paquete.getNombre().contains(consulta) || 
                     paquete.getDescripcion().contains(consulta)) 
            .collect(Collectors.toList());
                    

            for (DTActividad actividad : actividadesFiltradas) {
                dtImagen = control.traerDTImagenActividad(actividad.getNombre());
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
