/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.DtActividad;
import WebServices.DtImagenActividad;
import WebServices.DtSalidaTuristica;
import WebServices.PreexistingEntityException_Exception;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvSalida", urlPatterns = {"/SvSalida"})
@MultipartConfig(
        maxFileSize = 1024 * 1024, // Tamaño máximo del archivo en bytes (ejemplo: 1 MB)
        maxRequestSize = 1024 * 1024 // Tamaño máximo total de la solicitud en bytes
)

public class SvSalida extends HttpServlet {

    //Fabrica fabrica = Fabrica.getInstance();
   // IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();
        String filtro = request.getParameter("filtro");

        if ("FiltroDepartamento".equals(filtro)) {

            String departamentoSeleccionado = request.getParameter("departamento");

            List<String> listaActividadesDepartamento = port.listaActividadesTuristicasConfirmadas(departamentoSeleccionado).getLista();

            String actividades = String.join(",", listaActividadesDepartamento);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(actividades);

        } else if ("FiltroCategoria".equals(filtro)) {

            String categoriaSeleccionada = request.getParameter("categoria");

            List<String> listaActividadesCategoria = port.listaActividadesTuristicasPorCategoriaConfirmadas(categoriaSeleccionada).getLista();

            String actividades = String.join(",", listaActividadesCategoria);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(actividades);

        } else if ("FiltroSalidas".equals(filtro)) {

            HttpSession misesion = request.getSession();
            String nombreSalida = request.getParameter("actividadSalida");
            DtSalidaTuristica salidaTuristica = port.consultaSalidaTuristica(nombreSalida);
            misesion.setAttribute("salida", salidaTuristica);
            try {
                DtImagenActividad imagen = port.buscarImagenPorActividad(nombreSalida);

                //  if (imagen == null) {
                //   return;
                // } else {
                String imagenRuta = imagen.getRuta();
                //DTSalidaTuristica salT = control.ConsultaSalidaTuristica(nombreSalida);
                misesion.setAttribute("imagen", imagenRuta);
                //misesion.setAttribute("tipoUsuario", "turista");
                response.sendRedirect("perfilSalidaTuristica.jsp");
                return;
                // }

            } catch (Exception e) {

                String imagenVacia = "images/sinImagen.png";

                // DTSalidaTuristica salT = control.ConsultaSalidaTuristica(nombreSalida);
                //misesion.setAttribute("salida", salT);
                misesion.setAttribute("imagen", imagenVacia);
                response.sendRedirect("perfilSalidaTuristica.jsp");
                return;
            }

        }

        String nombreActividad = (String) request.getParameter("actividad");
        System.out.println(nombreActividad);
        DtActividad actividadConsultada = port.traerDTActividad(nombreActividad);
        List<DtSalidaTuristica> salidas = port.encontraSalidasTuristicasDeActividad(nombreActividad).getLista();

        HttpSession misesion = request.getSession();
        misesion.setAttribute("actividad", actividadConsultada);
        misesion.setAttribute("salidas", salidas);
        response.sendRedirect("listaSalidasTuristicas.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();
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

            //no importa la fecha actual, la carga la logica
            String fechaHoy = "11/11/1900";
            port.altaSalidaTuristica(nombreSalida, cantidadMaxTuristas, fechaHoy, fechaHoraSalida, lugarSalida, actividadTuristica);

            if (archivo.getSize() > 0) {
                nombreArchivo = archivo.getSubmittedFileName();
                if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
                    ServletContext context = request.getServletContext();
                    String rutaCompleta = context.getRealPath("/images/") + File.separator + nombreArchivo;

                    // Copiar el archivo a la ubicación relativa
                    Files.copy(archivo.getInputStream(), Paths.get(rutaCompleta), StandardCopyOption.REPLACE_EXISTING);

                    String rutaRelativa = "images" + File.separator + nombreArchivo;

                    try {
                        port.altaDeImagenActividad(nombreArchivo, rutaRelativa, nombreSalida, null);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        String errorMessage = "Ya existe otra salida con esta imagen, se ha dado de alta la salida sin imagen";
                        String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'altaSalidaTuristica.jsp';</script>";
                        response.getWriter().write(alertScript);

                    }
                }
            }
            response.sendRedirect("logedUser.jsp");
        } catch (PreexistingEntityException_Exception ex) {
            ex.printStackTrace();
            String errorMessage = "Ya existe otra salida con ese nombre";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'altaSalidaTuristica.jsp';</script>";
            response.getWriter().write(alertScript);

        } catch (Exception ex) {
            ex.printStackTrace();
            String errorMessage = "Ha ocurrido un error, verifique los campos.";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'altaSalidaTuristica.jsp';</script>";
            response.getWriter().write(alertScript);

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
