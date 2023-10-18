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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import persistencia.exceptions.CorreoElectronicoExistenteException;
import persistencia.exceptions.PreexistingEntityException;

@WebServlet(name = "SvActividad", urlPatterns = {"/SvActividad"})
@MultipartConfig(
        maxFileSize = 1024 * 1024, // Tamaño máximo del archivo en bytes (ejemplo: 1 MB)
        maxRequestSize = 1024 * 1024 // Tamaño máximo total de la solicitud en bytes
)

public class SvActividad extends HttpServlet {

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

        }

        String nombreActividad = (String) request.getParameter("actividad");

        Actividad actividadConsultada = control.ConsultaActividadTuristica(nombreActividad);

        HttpSession misesion = request.getSession();
        misesion.setAttribute("actividad", actividadConsultada);
        response.sendRedirect("perfilActividadTuristica.jsp");

//        String nomActividad = actividadConsultada.getNombre();
//        String descripacion = actividadConsultada.getDescripcion();
//        int duracionActividad = actividadConsultada.getDuracion();
//        String ciudadActividad = actividadConsultada.getCiudad();
//        String actividadProveedor = actividadConsultada.getProveedor().getNickname();
//        ArrayList<SalidaTuristica> actividadSalidas = actividadConsultada.getListaSalidaTuristica();
//        ArrayList<Categoria> actividadPaquetes = actividadConsultada.getListaCategoria();
//
//        misesion.setAttribute("nomActividad", nomActividad);
//        misesion.setAttribute("descripcion", descripacion);
//        misesion.setAttribute("duracionActividad", duracionActividad);
//        misesion.setAttribute("ciudadActividad", ciudadActividad);
//        misesion.setAttribute("actividadProveedor", actividadProveedor);
//        misesion.setAttribute("actividadSalidas", actividadSalidas);
//        misesion.setAttribute("actividadPaquetes", actividadPaquetes);
//
//        response.sendRedirect("mostrarActividadTuristica.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorMessage = null;
        try {
            String usuario = request.getParameter("usuario");
            String departamento = request.getParameter("departamento");
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            int duracion = Integer.parseInt(request.getParameter("duracion"));
            float costo = Float.parseFloat(request.getParameter("costo"));
            String ciudad = request.getParameter("ciudad");
            Date fecha = new Date();
            String[] categorias = request.getParameterValues("categoria");
            ArrayList<String> categoriasList = new ArrayList<>(Arrays.asList(categorias));

            Part archivo = request.getPart("file");
            System.out.println("archivo:" + archivo);
            String nombreArchivo = null;

            control.guardarActividad(nombre, descripcion, duracion, costo, ciudad, fecha, usuario, departamento, categoriasList);

            if (archivo.getSize() > 0) {
                nombreArchivo = archivo.getSubmittedFileName();
                if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
                    String directorioUsuario = System.getProperty("user.home");
                    String rutaCompleta = directorioUsuario + File.separator + "PAP___LAB" + File.separator + "Web Server" + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "images" + File.separator + nombreArchivo;

                    Files.copy(archivo.getInputStream(), Paths.get(rutaCompleta), StandardCopyOption.REPLACE_EXISTING);

                    try {
                        control.AltaDeImagenActividad(nombreArchivo, rutaCompleta, nombre);
                    } catch (Exception ex) {
                        errorMessage = "Imagen ya en uso por otra actividad";
                        request.setAttribute("errorMessage", errorMessage);
                        request.getRequestDispatcher("altaActividadTuristica.jsp").forward(request, response);

                    }
                }
            }
        } catch (PreexistingEntityException ex) {
            errorMessage = "Ya hay otra actividad con ese nombre.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("altaActividadTuristica.jsp").forward(request, response);

        } catch (Exception ex) {
            errorMessage = "Se ha producido un error, compruebe los campos";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("altaActividadTuristica.jsp").forward(request, response);

        }

        if (errorMessage == null) {
            response.sendRedirect("logedUser.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
