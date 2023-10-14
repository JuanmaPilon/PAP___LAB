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
import logica.DTSalidaTuristica;
import logica.Fabrica;
import logica.IControlador;
import logica.Proveedor;
import logica.Turista;
import logica.Usuario;

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

            List<DTSalidaTuristica> listaSalidas = control.traerInscSalidasDeTurista(turista.getNickname());
            ArrayList<String> nombresSalidasTurista = new ArrayList<>();
            for (DTSalidaTuristica dt : listaSalidas) {
                nombresSalidasTurista.add(dt.getNombre());
            }
            misesion.setAttribute("nombresSalidas", nombresSalidasTurista);

        } else if (usu instanceof Proveedor) {
            Proveedor proveedor = (Proveedor) usu;
            misesion.setAttribute("usuPerfil", proveedor);
            System.out.println(proveedor.getNickname() + ": es proveedor");
            
            //actividades turisticas que ofrece en estado confirmado           
            ArrayList<String> listaActividadesProveedorConfirmadas = control.listaActividadesProveedorConfirmadas (proveedor.getNickname());
            misesion.setAttribute("listaActividadesProveedorConfirmadas", listaActividadesProveedorConfirmadas);
            
            
            //salidas asociadas a el
            List<DTSalidaTuristica> listaSalidas = control.traerSalidasDelProveedor(proveedor.getNickname());
            ArrayList<String> nombresSalidasProveedor = new ArrayList<>();
            for (DTSalidaTuristica dt : listaSalidas) {
                nombresSalidasProveedor.add(dt.getNombre());
            }
            misesion.setAttribute("nombresSalidas", nombresSalidasProveedor);
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
