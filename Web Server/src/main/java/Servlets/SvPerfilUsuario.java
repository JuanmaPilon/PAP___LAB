package Servlets;

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
import logica.DTSalidaTuristica;
import logica.Fabrica;
import logica.IControlador;
import logica.Proveedor;
import logica.Turista;
import logica.Usuario;
import logica.ImagenPerfil;

@WebServlet(name = "SvPerfilUsuario", urlPatterns = {"/SvPerfilUsuario"})
public class SvPerfilUsuario extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    //doget con los datos de usuario para cargar en el perfil
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession misesion = request.getSession();
        String usuarioSeleccionado = request.getParameter("usuario");
        Usuario usu = control.ConsultaDeUsuario(usuarioSeleccionado);

        if (usu instanceof Turista) {
            Turista turista = (Turista) usu;
            misesion.setAttribute("usuPerfil", turista);
            //salidas a las que se inscribio
            System.out.println(turista.getNickname() + ": es turista");
            String usuario = (String) request.getSession().getAttribute("usuario");
            String tur = turista.getNickname();
            if (tur.equals(usuario)) {

                List<DTSalidaTuristica> listaSalidas = control.traerInscSalidasDeTurista(turista.getNickname());
                ArrayList<String> nombresSalidasTurista = new ArrayList<>();
                for (DTSalidaTuristica dt : listaSalidas) {
                    nombresSalidasTurista.add(dt.getNombre());
                }
                misesion.setAttribute("nombresSalidas", nombresSalidasTurista);

                ArrayList<String> paquetesComprados = control.listaPaquetesComprados(tur);
                misesion.setAttribute("nombresPaquetes", paquetesComprados);
            }
        } else if (usu instanceof Proveedor) {
            Proveedor proveedor = (Proveedor) usu;
            misesion.setAttribute("usuPerfil", proveedor);
            System.out.println(proveedor.getNickname() + ": es proveedor");

            String usuario = (String) request.getSession().getAttribute("usuario");// nombre del usuario logueado

            String prov = proveedor.getNickname();
            if (prov.equals(usuario)) {//si es proveedor y esta mirando su propio perfil
                System.out.println(proveedor.getNickname() + ": es proveedor y esta mirando su perfil");
                ArrayList<String> listaActividadesProveedor = control.listaActividadesProveedorTodas(prov);
                misesion.setAttribute("listaActividadesProveedor", listaActividadesProveedor);
                // se muestran todas sus actividades, no solo las confirmadas

            } else {//si es proveedor pero no esta mirando su propio perfil
                //actividades turisticas que ofrece en estado confirmado           
                ArrayList<String> listaActividadesProveedorConfirmadas = control.listaActividadesProveedorConfirmadas(proveedor.getNickname());
                misesion.setAttribute("listaActividadesProveedor", listaActividadesProveedorConfirmadas);
            }

            //salidas asociadas a el
            List<DTSalidaTuristica> listaSalidas = control.traerSalidasDelProveedor(proveedor.getNickname());
            ArrayList<String> nombresSalidasProveedor = new ArrayList<>();
            for (DTSalidaTuristica dt : listaSalidas) {
                nombresSalidasProveedor.add(dt.getNombre());
            }
            misesion.setAttribute("nombresSalidas", nombresSalidasProveedor);
        }

        ImagenPerfil imagen;
        try {
            imagen = control.buscarImagenPorNickname(usu.getNickname());
            if (imagen == null) {
                String imagenVacia = "images/usuarioSinFoto.png";
                misesion.setAttribute("imagen", imagenVacia);

            } else {
                String imagenRuta = imagen.getRuta();
                misesion.setAttribute("imagen", imagenRuta);
            }
        } catch (Exception ex) {
            Logger.getLogger(SvPerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect("perfilUsuario.jsp");
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

