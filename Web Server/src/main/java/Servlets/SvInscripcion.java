package Servlets;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Actividad;
import logica.Fabrica;
import logica.IControlador;
import logica.SalidaTuristica;

@WebServlet(name = "SvInscripcion", urlPatterns = {"/SvInscripcion"})
public class SvInscripcion extends HttpServlet {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession misesion = request.getSession();
        String nombreActividad = (String) request.getParameter("actividad");
        Actividad actividadConsultada = control.ConsultaActividadTuristica(nombreActividad);
        ArrayList<SalidaTuristica> Salidas = actividadConsultada.getListaSalidaTuristica();
        ArrayList<String> nombreSalidas = new ArrayList();
        for(SalidaTuristica S: Salidas){
            nombreSalidas.add(S.getNombre());
        }
         String Salts = String.join(",", nombreSalidas);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(Salts);

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
