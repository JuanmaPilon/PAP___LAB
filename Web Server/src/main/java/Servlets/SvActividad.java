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
import logica.Fabrica;
import logica.IControlador;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            String rutaImagenNueva = null;

            if (archivo != null) {
                nombreArchivo = archivo.getSubmittedFileName();
                if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
                    rutaImagenNueva = "/images/" + nombreArchivo;

                    String directorioUsuario = System.getProperty("user.home");

                    String rutaCompleta = directorioUsuario + File.separator + "PAP___LAB" + File.separator + "imagenes" + File.separator + "imagenesActividad" + File.separator + nombreArchivo;

                    Files.copy(archivo.getInputStream(), Paths.get(rutaCompleta), StandardCopyOption.REPLACE_EXISTING);
                }
                control.guardarActividad(nombre, descripcion, duracion, costo, ciudad, fecha, usuario, departamento, categoriasList);
                response.sendRedirect("logedUser.jsp");
                try {
                    if (archivo != null) {
                        control.AltaDeImagenActividad(nombreArchivo, rutaImagenNueva, nombre);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (PreexistingEntityException ex) {
            response.setStatus(HttpServletResponse.SC_CONFLICT); // Código de respuesta HTTP 409 (conflicto)
            response.getWriter().write("Nombre de la actividad ya en uso.");
        }  catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Código de respuesta HTTP 500 (error interno del servidor)
            response.getWriter().write("Departamento y categoria coinciden con otra actividad");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
