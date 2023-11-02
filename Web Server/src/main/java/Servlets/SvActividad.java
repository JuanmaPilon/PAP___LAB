package Servlets;

import java.io.File;
import javax.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Actividad;
import logica.DTActividad;
import logica.DTImagenActividad;
import logica.DTSalidaTuristica;
import logica.Fabrica;
import logica.IControlador;
import logica.TipoEstado;
import logica.imagenActividad;
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

            ArrayList<String> listaActividadesDepartamento = control.listaActividadesTuristicasConfirmadas(departamentoSeleccionado);
            String actividades = String.join(",", listaActividadesDepartamento);

            if (actividades.isEmpty()) {
                // Si la lista de actividades está vacía, envía una respuesta indicando que no hay actividades disponibles
                actividades = "No hay actividades disponibles para este departamento";
            }

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(actividades);

        } else if ("FiltroCategoria".equals(filtro)) {

            String categoriaSeleccionada = request.getParameter("categoria");

            ArrayList<String> listaActividadesCategoria = control.listaActividadesTuristicasPorCategoriaConfirmadas(categoriaSeleccionada);

            String actividades = String.join(",", listaActividadesCategoria);

            if (actividades.isEmpty()) {
                // Si la lista de actividades está vacía, envía una respuesta indicando que no hay actividades disponibles
                actividades = "No hay actividades disponibles para esta categoria";
            }

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(actividades);

        }
        try {
            String nombreActividad = (String) request.getParameter("actividad");
            String tipoUsuario = (String) request.getParameter("tipoUsuario");
            DTActividad actividadConsultada = control.traerDTActividad(nombreActividad);
            DTImagenActividad imagen = control.traerDTImagenActividad(nombreActividad);
            ArrayList<DTSalidaTuristica> salidas = control.encontraSalidasTuristicasDeActividad(actividadConsultada.getNombre());
            ArrayList<String> categorias = control.traerCategoriasActividad(actividadConsultada.getNombre());
            ArrayList<String> paquetes = control.listaPaquetesDeActividad(actividadConsultada.getNombre());

            HttpSession misesion = request.getSession();
            String imagenRuta = "images/sinImagen.png";
            String UrlVideo = "";

            if (imagen == null) {
            } else if ((imagen.getNombre() != null) && (imagen.getUrlVideo() != null)) {
                imagenRuta = imagen.getRuta();
                UrlVideo = imagen.getUrlVideo();
            } else if ((imagen.getNombre() != null) && (imagen.getUrlVideo() == null)) {
                imagenRuta = imagen.getRuta();
            } else if ((imagen.getNombre() == null) && (imagen.getUrlVideo() != null)) {
                UrlVideo = imagen.getUrlVideo();
            }

            misesion.setAttribute("tipoUsuario", tipoUsuario);
            misesion.setAttribute("actividad", actividadConsultada);
            misesion.setAttribute("salidas", salidas);
            misesion.setAttribute("categorias", categorias);
            misesion.setAttribute("paquetes", paquetes);
            misesion.setAttribute("imagen", imagenRuta);
            misesion.setAttribute("UrlVideo", UrlVideo);
            response.sendRedirect("perfilActividadTuristica.jsp");

        } catch (Exception ex) {

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String finalizar = request.getParameter("finalizar");
            String nombreActividad = request.getParameter("nombreActividad");
            String usuario = request.getParameter("usuario");
            String tipoUsuario = request.getParameter("tipoUsuario");
            
            if(finalizar.equals("finalizar")){
                if(control.actividadSinSalidaVigente(nombreActividad)){
                control.cambiarEstadoActividad(nombreActividad, TipoEstado.finalizada);
              // String errorMessage = "Actividad finalizada correctamente";
              // String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'logedUser.jsp';</script>";
               //response.getWriter().write(alertScript);
               response.sendRedirect("logedUser.jsp");
                } else {
                String errorMessage = "Actividad con salidas vigentes";
                String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'perfilActividadTuristica.jsp?usuario=" + usuario + "&tipoUsuario=" + tipoUsuario + "';</script>";
                response.getWriter().write(alertScript);
                }      
            } else {
        
        try {
           // String usuario = request.getParameter("usuario");
            String departamento = request.getParameter("departamento");
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            int duracion = Integer.parseInt(request.getParameter("duracion"));
            float costo = Float.parseFloat(request.getParameter("costo"));
            String ciudad = request.getParameter("ciudad");
            Date fecha = new Date();
            String[] categorias = request.getParameterValues("categoria");
            ArrayList<String> categoriasList = new ArrayList<>(Arrays.asList(categorias));
            String UrlVideo = request.getParameter("urlVideo");
            
            
           
                
            

            Part archivo = request.getPart("file");
            String nombreArchivo = null;

            control.guardarActividad(nombre, descripcion, duracion, costo, ciudad, fecha, usuario, departamento, categoriasList);

            if (archivo.getSize() > 0) {
                nombreArchivo = archivo.getSubmittedFileName();
                if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
                    ServletContext context = request.getServletContext();
                    String rutaCompleta = context.getRealPath("/images/") + File.separator + nombreArchivo;

                    // Copiar el archivo a la ubicación relativa
                    Files.copy(archivo.getInputStream(), Paths.get(rutaCompleta), StandardCopyOption.REPLACE_EXISTING);

                    String rutaRelativa = "images" + File.separator + nombreArchivo;

                    try {
                        if (UrlVideo == null) {
                            control.AltaDeImagenActividad(nombreArchivo, rutaRelativa, nombre, null);
                        } else if(UrlVideo != null){
                            control.AltaDeImagenActividad(nombreArchivo, rutaRelativa, nombre, UrlVideo);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        String errorMessage = "Ya existe otra actividad con esa imagen, se ha dado de alta la actividad sin imagen";
                        String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'logedUser.jsp';</script>";
                        response.getWriter().write(alertScript);

                    }

                }
            }

            if ((archivo.getSize() == 0) && (UrlVideo != null)) {
                control.AltaDeImagenActividad(null, null, nombre, UrlVideo);
            }
            
            
            response.sendRedirect("logedUser.jsp");
        
        } catch (PreexistingEntityException ex) {
            ex.printStackTrace();
            String errorMessage = "Ya existe otra actividad con ee nombre";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'altaActividadTuristica.jsp';</script>";
            response.getWriter().write(alertScript);

        } catch (Exception ex) {
            
            ex.printStackTrace();
            String errorMessage = "Se ha prodicido un error, porvafor verifique los campos";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'altaActividadTuristica.jsp';</script>";
            response.getWriter().write(alertScript);
        }
            }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
