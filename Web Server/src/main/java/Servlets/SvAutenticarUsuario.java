package Servlets;

import WebServices.DtProveedor;
import WebServices.DtUsuario;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvAutenticarUsuario", urlPatterns = {"/SvAutenticarUsuario"})
public class SvAutenticarUsuario extends HttpServlet {

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
        String usuario = request.getParameter("username"); //Obtengo el nombre ingresado
        String contrasenia = request.getParameter("password"); //Obtengo la contrasenia ingresada

        boolean autenticado = autenticarUsuario(usuario, contrasenia);
        String userAgent = request.getHeader("User-Agent");

        if (autenticado) {

            String tipoUsuario = port.devolverTipoUsuario(usuario);
            
            //si es proveedor y estamos en movil no se puede loguear
            if (userAgent != null && (userAgent.contains("Android") || userAgent.contains("iPhone") || userAgent.contains("Mobile"))) {
                if (tipoUsuario.equals("proveedor")) {
                    request.getSession().setAttribute("errorMensaje", "Usuario del tipo Proveedor"); // Almacena un mensaje de error en la sesión
                    response.sendRedirect("loginMovil.jsp");
                    return;  // Asegúrate de terminar la ejecución del servlet después de la redirección
                }
            }
            
            request.getSession().setAttribute("usuario", usuario);  // Si el usuario es autenticado, puedes almacenar información de sesión

            DtUsuario usu = port.traerDTUsuario(usuario);
            
            //String tipoUsuario = control.devolverTipoUsuario(usu.getNickname());
            if (tipoUsuario.equals("turista")) {
                List<String> actividadesFavoritas = port.traerActividadesFavoritasDelTurista(usuario).getLista();
                request.getSession().setAttribute("actividadesFavoritas", actividadesFavoritas);
            }
            request.getSession().setAttribute("usu", usu);
            //request.getSession().setAttribute("usuariosFavoritos", usuariosFavoritos);
            request.getSession().setAttribute("tipoUsuario", tipoUsuario);

            // Verificar si el agente de usuario indica un dispositivo móvil (puedes ajustar la condición según tus necesidades)
            if (userAgent != null && (userAgent.contains("Android") || userAgent.contains("iPhone") || userAgent.contains("Mobile"))) {
                // Redirigir a la versión móvil
                response.sendRedirect("homeMovil.jsp");
                return;  // Asegúrate de terminar la ejecución del servlet después de la redirección
            } else {
                response.sendRedirect("logedUser.jsp");
            }
        } else {

            request.getSession().setAttribute("errorMensaje", "Usuario y/o contrasenia incorrectas"); // Almacena un mensaje de error en la sesión
            if (userAgent != null && (userAgent.contains("Android") || userAgent.contains("iPhone") || userAgent.contains("Mobile"))) {
                // Redirigir a la versión móvil
                response.sendRedirect("loginMovil.jsp");
                return;  // Asegúrate de terminar la ejecución del servlet después de la redirección
            } else {
                response.sendRedirect("login.jsp");
            }
        }
    }

    private boolean autenticarUsuario(String username, String password) {
        //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();
        List<DtUsuario> listaUsuarios = port.traerUsuarioMod().getLista(); // Obtén la lista de usuarios con nombres de usuario y contraseñas

        // Recorre la lista de usuarios para verificar las credenciales
        for (DtUsuario usuario : listaUsuarios) {
            if (usuario.getNickname().equals(username) && usuario.getContrasenia().equals(password)) {
                // Las credenciales son correctas
                return true;
            }
        }

        // Si llegamos aquí, las credenciales son incorrectas
        return false;
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
