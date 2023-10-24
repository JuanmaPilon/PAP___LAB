/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

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
import java.util.Arrays;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Actividad;
import logica.Categoria;
import logica.Fabrica;
import logica.IControlador;
import logica.SalidaTuristica;
import logica.imagenActividad;
import persistencia.exceptions.CorreoElectronicoExistenteException;
import persistencia.exceptions.PreexistingEntityException;

@WebServlet(name = "SvSalida", urlPatterns = {"/SvSalida"})
@MultipartConfig(
        maxFileSize = 1024 * 1024, // Tamaño máximo del archivo en bytes (ejemplo: 1 MB)
        maxRequestSize = 1024 * 1024 // Tamaño máximo total de la solicitud en bytes
)

public class SvSalida extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String filtro = request.getParameter("filtro");

        if ("FiltroDepartamento".equals(filtro)) {

            String departamentoSeleccionado = request.getParameter("departamento");

            ArrayList<String> listaActividadesDepartamento = control.listaActividadesTuristicas(departamentoSeleccionado);

            String actividades = String.join(",", listaActividadesDepartamento);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(actividades);

        } else if ("FiltroCategoria".equals(filtro)) {

            String categoriaSeleccionada = request.getParameter("categoria");

            ArrayList<String> listaActividadesCategoria = control.listaActividadesTuristicasPorCategoria(categoriaSeleccionada);

            String actividades = String.join(",", listaActividadesCategoria);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(actividades);

        } else  if ("FiltroSalidas".equals(filtro)) {
            
             try {
            HttpSession misesion = request.getSession();
            String nombreSalida = request.getParameter("actividadSalida");
            SalidaTuristica salidaTuristica = control.ConsultaSalidaTuristica(nombreSalida);
            imagenActividad imagen = control.buscarImagenPorActividad(nombreSalida);

            imagenActividad ImagenActividad = control.buscarImagenPorActividad(nombreSalida);
             if (ImagenActividad == null) {
                
                String imagenVacia = "images/sinImagen.png";
               
                
                SalidaTuristica salT = control.ConsultaSalidaTuristica(nombreSalida);
                 misesion.setAttribute("salida", salT);
                 misesion.setAttribute("imagen", imagenVacia);
                response.sendRedirect("perfilSalidaTuristica.jsp");
                return;
            } else {
                String imagenRuta = ImagenActividad.getRuta();
                SalidaTuristica salT = control.ConsultaSalidaTuristica(nombreSalida);
                 misesion.setAttribute("imagen", imagenRuta);
                 misesion.setAttribute("salida", salT);
                response.sendRedirect("perfilSalidaTuristica.jsp");
                return;
            }

        } catch (Exception e) {

        }     
            
        }

        String nombreActividad = (String) request.getParameter("actividad");

        Actividad actividadConsultada = control.ConsultaActividadTuristica(nombreActividad);

        HttpSession misesion = request.getSession();
        misesion.setAttribute("actividad", actividadConsultada);
        response.sendRedirect("listaSalidasTuristicas.jsp");

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                        ex.printStackTrace();
                        String errorMessage = "Ya existe otra salida con esta imagen, se ha dado de alta la salida sin imagen";
                        String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'altaSalidaTuristica.jsp';</script>";
                        response.getWriter().write(alertScript);

                    }
                }
            }
         response.sendRedirect("logedUser.jsp");
        } catch (PreexistingEntityException ex) {
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