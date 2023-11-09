package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Actividad;
import logica.Categoria;
import logica.DTActividad;
import logica.DTCategoria;
import logica.Departamento;
import logica.Fabrica;
import logica.IControlador;

@WebServlet(name = "SvDeptosYCategorias", urlPatterns = {"/SvDeptosYCategorias"})
public class SvDeptosYCategorias extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener el agente de usuario del header
    String userAgent = request.getHeader("User-Agent");

    // Verificar si el agente de usuario indica un dispositivo móvil (puedes ajustar la condición según tus necesidades)
    if (userAgent != null && (userAgent.contains("Android") || userAgent.contains("iPhone") || userAgent.contains("Mobile"))) {
        // Redirigir a la versión móvil
        response.sendRedirect("homeMovil.jsp");
        return;  // Asegúrate de terminar la ejecución del servlet después de la redirección
    }
        
        HttpSession misesion = request.getSession();

        ArrayList<String> categoriasConActividadesConfirmadas = new ArrayList<>();       
        ArrayList<DTActividad> listaActividadesConfirmadas = control.listaActividadesConfirmadas();
        for (DTActividad actividad : listaActividadesConfirmadas) {
                ArrayList<String> categoriasDeActividad = control.traerCategoriasActividad(actividad.getNombre());
                for (String categoria : categoriasDeActividad) {
                    if (!categoriasConActividadesConfirmadas.contains(categoria)) {
                        categoriasConActividadesConfirmadas.add(categoria);
                    }
                }
        }
        misesion.setAttribute("listaCategorias", categoriasConActividadesConfirmadas);

        ArrayList<String> departamentosConActividadesConfirmadas = new ArrayList<>();
        
        ArrayList<DTActividad> listaActividades2 = control.listaActividadesConfirmadas();
        for (DTActividad actividad : listaActividades2) {
                String departamento = actividad.getNombreDepartamento();
                if (!departamentosConActividadesConfirmadas.contains(departamento)) {
                    departamentosConActividadesConfirmadas.add(departamento);
                }
        }
        misesion.setAttribute("listaDepartamentos", departamentosConActividadesConfirmadas);
       
     request.getRequestDispatcher("index.jsp").forward(request, response);
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
