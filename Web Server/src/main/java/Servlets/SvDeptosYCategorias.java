package Servlets;

import WebServices.DtActividad;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvDeptosYCategorias", urlPatterns = {"/SvDeptosYCategorias"})
public class SvDeptosYCategorias extends HttpServlet {

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
        
        // Obtener el agente de usuario del header
    String userAgent = request.getHeader("User-Agent");

    // Verificar si el agente de usuario indica un dispositivo móvil (puedes ajustar la condición según tus necesidades)
    if (userAgent != null && (userAgent.contains("Android") || userAgent.contains("iPhone") || userAgent.contains("Mobile"))) {
        // Redirigir a la versión móvil
        response.sendRedirect("loginMovil.jsp");
        return;  // Asegúrate de terminar la ejecución del servlet después de la redirección
    }
        
        HttpSession misesion = request.getSession();

        ArrayList<String> categoriasConActividadesConfirmadas = new ArrayList<>();       
        List<DtActividad> listaActividadesConfirmadas = port.listaActividadesConfirmadas().getLista();
        for (DtActividad actividad : listaActividadesConfirmadas) {
                List<String> categoriasDeActividad = port.traerCategoriasActividad(actividad.getNombre()).getLista();
                for (String categoria : categoriasDeActividad) {
                    if (!categoriasConActividadesConfirmadas.contains(categoria)) {
                        categoriasConActividadesConfirmadas.add(categoria);
                    }
                }
        }
        misesion.setAttribute("listaCategorias", categoriasConActividadesConfirmadas);

        ArrayList<String> departamentosConActividadesConfirmadas = new ArrayList<>();
        
        List<DtActividad> listaActividades2 = port.listaActividadesConfirmadas().getLista();
        for (DtActividad actividad : listaActividades2) {
                String departamento = actividad.getNombreDepartamento();
                if (!departamentosConActividadesConfirmadas.contains(departamento)) {
                    departamentosConActividadesConfirmadas.add(departamento);
                }
        }
        misesion.setAttribute("listaDepartamentos", departamentosConActividadesConfirmadas);
        String tipoUsuario = "visitante";
        misesion.setAttribute("tipoUsuario", tipoUsuario);
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
