/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controlador;
import logica.DTSalidaTuristica;
import logica.Departamento;
import logica.Proveedor;
import logica.Turista;
import logica.Usuario;

@WebServlet(name = "SvPerfilUsuario", urlPatterns = {"/SvPerfilUsuario"})
public class SvPerfilUsuario extends HttpServlet {

    Controlador control = Controlador.getInstance();

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

            List<DTSalidaTuristica> listaSalidas = control.traerInscSalidasDeTurista(turista.getNickname());
            ArrayList<String> nombresSalidasTurista = new ArrayList<>();
            for (DTSalidaTuristica dt : listaSalidas) {
                nombresSalidasTurista.add(dt.getNombre());
            }
            misesion.setAttribute("nombresSalidasTurista", nombresSalidasTurista);

        } else if (usu instanceof Proveedor) {
            Proveedor proveedor = (Proveedor) usu;
            misesion.setAttribute("usuPerfil", proveedor);
            //actividades turisticas qwue ofrece en estado confirmado
            //(falta funcion de esto en la controladora)
            //salidas asociadas a el
            System.out.println(proveedor.getNickname() + ": es proveedor");
            control.traerSalidasDelProveedor(proveedor.getNickname());

            List<DTSalidaTuristica> listaSalidas = control.traerSalidasDelProveedor(proveedor.getNickname());
            ArrayList<String> nombresSalidasProveedor = new ArrayList<>();
            for (DTSalidaTuristica dt : listaSalidas) {
                nombresSalidasProveedor.add(dt.getNombre());
            }
            misesion.setAttribute("nombresSalidasProveedor", nombresSalidasProveedor);
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
