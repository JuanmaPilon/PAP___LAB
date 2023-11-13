package Servlets;

import WebServices.DtActividad;
import WebServices.DtImagenPerfil;
import WebServices.DtProveedor;
import WebServices.DtSalidaTuristica;
import WebServices.DtTurista;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvPerfilUsuario", urlPatterns = {"/SvPerfilUsuario"})
public class SvPerfilUsuario extends HttpServlet {

    //Fabrica fabrica = Fabrica.getInstance();
    //IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    //doget con los datos de usuario para cargar en el perfil
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();
        HttpSession misesion = request.getSession();
        String usuarioSeleccionado = request.getParameter("usuario");
        String tipoUsuarioConsultado = port.devolverTipoUsuario(usuarioSeleccionado);

        if (tipoUsuarioConsultado.equals("turista")) {
            
            //DtTurista dtTurista = (DtTurista) port.traerDTTurista(usuarioSeleccionado);
            DtTurista dtTurista = (DtTurista) port.traerDTUsuario(usuarioSeleccionado);
            misesion.setAttribute("usuPerfil", dtTurista);

            //salidas a las que se inscribio
            String usuario = (String) request.getSession().getAttribute("usuario");
            String tur = dtTurista.getNickname();
            //System.out.println("Servlets.SvPerfilUsuario.doGet()" + usuario +" tur " + tur);
            if (tur.equals(usuario)) {
                 System.out.println("Servlets.SvPerfilUsuario.doGet()ACAAA" + usuario +" tur " + tur);
                List<DtSalidaTuristica> listaSalidas = port.traerInscSalidasDeTurista(dtTurista.getNickname()).getLista();
                ArrayList<String> nombresSalidasTurista = new ArrayList<>();
                for (DtSalidaTuristica dt : listaSalidas) {
                    nombresSalidasTurista.add(dt.getNombre());
                }
                misesion.setAttribute("nombresSalidas", nombresSalidasTurista);

                List<String> paquetesComprados = port.listaPaquetesComprados(tur).getLista();
                misesion.setAttribute("nombresPaquetes", paquetesComprados);
            }
        } else if (tipoUsuarioConsultado.equals("proveedor")) {
            DtProveedor dtProveedor = port.traerDTProveedor(usuarioSeleccionado);
            misesion.setAttribute("usuPerfil", dtProveedor);
            System.out.println(dtProveedor.getNickname() + ": es proveedor");

            String usuario = (String) request.getSession().getAttribute("usuario");// nombre del usuario logueado

            String prov = dtProveedor.getNickname();
            if (prov.equals(usuario)) {//si es proveedor y esta mirando su propio perfil
                List<DtActividad> listaActividadesProveedor = port.listaActividadesProveedorTodas(prov).getLista();
                misesion.setAttribute("listaActividadesProveedor", listaActividadesProveedor);
                // se muestran todas sus actividades, no solo las confirmadas

            } else {//si es proveedor pero no esta mirando su propio perfil
                //actividades turisticas que ofrece en estado confirmado           
                List<DtActividad> listaActividadesProveedorConfirmadas = port.listaActividadesProveedorConfirmadas(dtProveedor.getNickname()).getLista();
                misesion.setAttribute("listaActividadesProveedor", listaActividadesProveedorConfirmadas);
            }

            //salidas asociadas a el
            List<DtSalidaTuristica> listaSalidas = port.traerSalidasDelProveedor(dtProveedor.getNickname()).getLista();
            ArrayList<String> nombresSalidasProveedor = new ArrayList<>();
            for (DtSalidaTuristica dt : listaSalidas) {
                nombresSalidasProveedor.add(dt.getNombre());
            }
            misesion.setAttribute("nombresSalidas", nombresSalidasProveedor);
        }

        DtImagenPerfil imagen;
        try {
            imagen = port.buscarImagenPorNickname(usuarioSeleccionado);

            String imagenRuta = imagen.getRuta();
            misesion.setAttribute("imagen", imagenRuta);
        } catch (Exception ex) {
            String imagenVacia = "images/usuarioSinFoto.png";
            misesion.setAttribute("imagen", imagenVacia);
        }
        misesion.setAttribute("tipoUsuarioConsultado", tipoUsuarioConsultado);
        response.sendRedirect("perfilUsuario.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();
        HttpSession misesion = request.getSession();
        String usuario = request.getParameter("usuario");
        String nombreSalida = request.getParameter("nombreSalida");
        
        port.generarPDFInscripcionSalida(usuario, nombreSalida);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
