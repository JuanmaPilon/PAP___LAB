package Servlets;

import WebServices.DtActividad;
import WebServices.DtImagenActividad;
import WebServices.DtSalidaTuristica;
import WebServices.ListaString;
import WebServices.PreexistingEntityException_Exception;
import WebServices.TipoEstado;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.File;
import javax.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


@WebServlet(name = "SvActividad", urlPatterns = {"/SvActividad"})
@MultipartConfig(
        maxFileSize = 1024 * 1024, // Tamaño máximo del archivo en bytes (ejemplo: 1 MB)
        maxRequestSize = 1024 * 1024 // Tamaño máximo total de la solicitud en bytes
)

public class SvActividad extends HttpServlet {

   // Fabrica fabrica = Fabrica.getInstance();
    //IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String filtro = request.getParameter("filtro");
        //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();

        if ("FiltroDepartamento".equals(filtro)) {

            String departamentoSeleccionado = request.getParameter("departamento");

            List<String> listaActividadesDepartamento = port.listaActividadesTuristicasConfirmadas(departamentoSeleccionado).getLista();
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

            List<String> listaActividadesCategoria = port.listaActividadesTuristicasPorCategoriaConfirmadas(categoriaSeleccionada).getLista();

            String actividades = String.join(",", listaActividadesCategoria);

            if (actividades.isEmpty()) {
                // Si la lista de actividades está vacía, envía una respuesta indicando que no hay actividades disponibles
                actividades = "No hay actividades disponibles para esta categoria";
            }

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(actividades);

        }
        //try {
        String nombreActividad = (String) request.getParameter("actividad");
        String tipoUsuario = (String) request.getParameter("tipoUsuario");
        DtActividad actividadConsultada = port.traerDTActividad(nombreActividad);
        DtImagenActividad imagen = port.traerDTImagenActividad(nombreActividad);
        List<DtSalidaTuristica> salidas = port.encontraSalidasTuristicasDeActividad(actividadConsultada.getNombre()).getLista();
        List<String> categorias = port.traerCategoriasActividad(actividadConsultada.getNombre()).getLista();
        List<String> paquetes = port.listaPaquetesDeActividad(actividadConsultada.getNombre()).getLista();

        HttpSession misesion = request.getSession();
        String imagenRuta = "images/sinImagen.png";
        String UrlVideo = "";

        if (imagen != null) {
            if ((imagen.getNombre() != null) && (!imagen.getUrlVideo().equals(""))) {
                imagenRuta = imagen.getRuta();
                UrlVideo = imagen.getUrlVideo();
            } else if ((imagen.getNombre() != null) && (imagen.getUrlVideo().equals(""))) {
                imagenRuta = imagen.getRuta();
            } else if ((imagen.getNombre() == null) && (!imagen.getUrlVideo().equals(""))) {
                UrlVideo = imagen.getUrlVideo();
            }

        }

        if (tipoUsuario != null) { //INSCRIPCION, NO FUNCIONA SI SE TRAE TIPO USUARIO
            misesion.setAttribute("tipoUsuario", tipoUsuario);
        } else {
            misesion.setAttribute("tipoUsuario", "turista");
        }
        misesion.setAttribute("actividad", actividadConsultada);
        misesion.setAttribute("salidas", salidas);
        misesion.setAttribute("categorias", categorias);
        misesion.setAttribute("paquetes", paquetes);
        misesion.setAttribute("imagen", imagenRuta);
        misesion.setAttribute("UrlVideo", UrlVideo);
        response.sendRedirect("perfilActividadTuristica.jsp");

        //  } catch (Exception ex) {
        //     ex.printStackTrace();
        //  }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String finalizar = request.getParameter("finalizar"); //FINALIZAR ACTIVIDAD
        String marcarActividad = request.getParameter("marcarActividad"); //MARCAR ACTIVIDAD COMO FAVORITA
        String DesMarcarActividad = request.getParameter("DesMarcarActividad");
        String nombreActividad = request.getParameter("nombreActividad");
        String usuario = request.getParameter("usuario");
        String tipoUsuario = request.getParameter("tipoUsuario");
        //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();
        if (finalizar == null) {
            finalizar = "NULL";
        }
        if (marcarActividad == null) {
            marcarActividad = "NULL";
        }
        if (DesMarcarActividad == null) {
            DesMarcarActividad = "NULL";
        }

        if (finalizar.equals("finalizar")) {
            if (port.actividadSinSalidaVigente(nombreActividad)) {
                port.cambiarEstadoActividad(nombreActividad, TipoEstado.FINALIZADA);
                String errorMessage = "Actividad finalizada correctamente";
                String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'logedUser.jsp';</script>";
                response.getWriter().write(alertScript);
            } else {
                String errorMessage = "Actividad con salidas vigentes";
                String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'perfilActividadTuristica.jsp';</script>";
                response.getWriter().write(alertScript);
            }
        } else if (marcarActividad.equals("marcarActividad")) {
            port.marcarActividadComoFavorita(usuario, nombreActividad);
            //REINICIAR Y SETTER LA NUEVA LISTA
            List<String> actividadesFavoritas = port.traerActividadesFavoritasDelTurista(usuario).getLista();
            request.getSession().setAttribute("actividadesFavoritas", actividadesFavoritas);
            String errorMessage = "Actividad marcada como favorita";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'perfilActividadTuristica.jsp';</script>";
            response.getWriter().write(alertScript);
        } else if (DesMarcarActividad.equals("DesMarcarActividad")) {
            port.desMarcarActividad(usuario, nombreActividad);
            //REINICIAR Y SETTER LA NUEVA LISTA
            List<String> actividadesFavoritas = port.traerActividadesFavoritasDelTurista(usuario).getLista();
            request.getSession().setAttribute("actividadesFavoritas", actividadesFavoritas);
            String errorMessage = "Actividad desmarcada como favorita";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'perfilActividadTuristica.jsp';</script>";
            response.getWriter().write(alertScript);
        } else {

            try {
                // String usuario = request.getParameter("usuario");
                String departamento = request.getParameter("departamento");
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                int duracion = Integer.parseInt(request.getParameter("duracion"));
                float costo = Float.parseFloat(request.getParameter("costo"));
                String ciudad = request.getParameter("ciudad");
                //Date fecha = new Date();
                String[] categorias = request.getParameterValues("categoria");
                List<String> categoriasList = new ArrayList<>(Arrays.asList(categorias));

                ListaString result = new ListaString();
                for (String cat : categoriasList) {
                    result.getLista().add(cat);
                }

                String UrlVideo = request.getParameter("urlVideo");

                Part archivo = request.getPart("file");
                String nombreArchivo = null;

                //XMLGregorianCalendar xmlFecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
                //paso cualquier string porque la fecha la setea con la actual en la logica
                String fecha = "11/11/1950";

                port.guardarActividad(nombre, descripcion, duracion, costo, ciudad, fecha, usuario, departamento, result);

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
                                port.altaDeImagenActividad(nombreArchivo, rutaRelativa, nombre, null);
                            } else if (UrlVideo != null) {
                                port.altaDeImagenActividad(nombreArchivo, rutaRelativa, nombre, UrlVideo);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            String errorMessage = "Ya existe otra actividad con esa imagen, se ha dado de alta la actividad sin imagen";
                            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'logedUser.jsp';</script>";
                            response.getWriter().write(alertScript);

                        }

                    }
                }

                if ((archivo.getSize() == 0) && (!UrlVideo.equals(""))) {
                    port.altaDeImagenActividad(null, null, nombre, UrlVideo);
                } else if ((archivo.getSize() == 0) && (UrlVideo == null)) {

                }

                response.sendRedirect("logedUser.jsp");

            } catch (PreexistingEntityException_Exception ex) {
                ex.printStackTrace();
                String errorMessage = "Ya existe otra actividad con ee nombre";
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
