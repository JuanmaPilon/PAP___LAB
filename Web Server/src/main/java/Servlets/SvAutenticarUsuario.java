package Servlets;

import WebServices.DtUsuario;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.IOException;
import java.io.PrintWriter;
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

        if (autenticado) {
            request.getSession().setAttribute("usuario", usuario);  // Si el usuario es autenticado, puedes almacenar información de sesión

            DtUsuario usu = port.traerDTUsuario(usuario);

            // DTUsuario usu = control.traerDTUsuario(usuario);
            String tipoUsuario = port.devolverTipoUsuario(usu.getNickname());

            System.out.println("LLAMADO DE WSDL TIPO USUARIO" + tipoUsuario);

            //String tipoUsuario = control.devolverTipoUsuario(usu.getNickname());
            if (tipoUsuario.equals("turista")) {
                List<String> actividadesFavoritas = port.traerActividadesFavoritasDelTurista(usuario).getLista();
                request.getSession().setAttribute("actividadesFavoritas", actividadesFavoritas);
            }
            request.getSession().setAttribute("usu", usu);
            //request.getSession().setAttribute("usuariosFavoritos", usuariosFavoritos);
            request.getSession().setAttribute("tipoUsuario", tipoUsuario);
            response.sendRedirect("logedUser.jsp"); // Redirige al usuario a la página de inicio
        } else {
            request.getSession().setAttribute("errorMensaje", "Usuario y/o contrasenia incorrectas"); // Almacena un mensaje de error en la sesión
            response.sendRedirect("login.jsp"); // Redirige al usuario nuevamente a la página de inicio de sesión
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
    }// </editor-fold>

}
