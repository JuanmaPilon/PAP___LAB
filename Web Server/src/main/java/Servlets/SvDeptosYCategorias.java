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

        HttpSession misesion = request.getSession();

        ArrayList<Categoria> categoriasConActividadesConfirmadas = new ArrayList<>();       
        ArrayList<Actividad> listaActividadesConfirmadas = control.listaActividadesConfirmadas();
        for (Actividad actividad : listaActividadesConfirmadas) {
                ArrayList<Categoria> categoriasDeActividad = actividad.getListaCategoria();
                for (Categoria categoria : categoriasDeActividad) {
                    if (!categoriasConActividadesConfirmadas.contains(categoria)) {
                        categoriasConActividadesConfirmadas.add(categoria);
                        System.out.println("nombrecat"+ categoria.getNombre());
                    }
                }
        }
        misesion.setAttribute("listaCategorias", categoriasConActividadesConfirmadas);

        ArrayList<Departamento> departamentosConActividadesConfirmadas = new ArrayList<>();
        
        ArrayList<Actividad> listaActividades2 = control.listaActividadesConfirmadas();
        for (Actividad actividad : listaActividades2) {
                Departamento departamento = actividad.getDepartamento();
                if (!departamentosConActividadesConfirmadas.contains(departamento)) {
                    departamentosConActividadesConfirmadas.add(departamento);
                    System.out.println("nombredepto"+ departamento.getNombre());
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
