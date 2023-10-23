/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Actividad;
import logica.Fabrica;
import logica.IControlador;
import logica.Paquete;
import logica.SalidaTuristica;
import logica.TipoPago;

/**
 *
 * @author Pc
 */
@WebServlet(name = "SvPaquetesDeSalida", urlPatterns = {"/SvPaquetesDeSalida"})
public class SvPaquetesDeSalida extends HttpServlet {

    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession misesion = request.getSession();

        String nombreSalida = request.getParameter("actividadSalida");
        String nombreTurista = request.getParameter("usuario");
        

        ArrayList<Paquete> nombrePaquetes = control.listaPaquetesVigentesSalida(nombreSalida);
        ArrayList<String> paquetesComprados = control.listaPaquetesComprados(nombreTurista);

        // Filtra los paquetes que tienen nombres en común con paquetesComprados
        ArrayList<Paquete> paquetesFiltrados = new ArrayList<>();
        for (Paquete paquete : nombrePaquetes) {
            if (paquetesComprados.contains(paquete.getNombre())) {
                paquetesFiltrados.add(paquete);
            }
        }

        ArrayList<String> nombresPaquetesFiltrados = new ArrayList<>();
        for (Paquete paquete : paquetesFiltrados) {
            nombresPaquetesFiltrados.add(paquete.getNombre());
        }

        String nombresPaquetesStr = String.join(",", nombresPaquetesFiltrados);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(nombresPaquetesStr);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombreSalida = request.getParameter("actividadSalida");
        
        String nombreTurista = request.getParameter("usuarioV");
        
        String nombreActividad = request.getParameter("actividad");
        
        int cantTuristas = Integer.parseInt(request.getParameter("cantTuristas"));
        
        String formaDePago = request.getParameter("formaPago");
        
        String paquete = request.getParameter("paquetes");
        
        
        SalidaTuristica salida = control.ConsultaSalidaTuristica(nombreSalida);
        
        Actividad actividad = control.ConsultaActividadTuristica( nombreActividad);
        
        float costo = actividad.getCosto();
        
        Date fecha = new Date();
        
        TipoPago pago = TipoPago.paquete;
        
        if("general".equals(formaDePago) ){
             pago = TipoPago.general;
        }
        
        if((salida.getCantMax() - cantTuristas) < 0){
            
            String errorMessage = "Salida llena";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'inscripcionSalida.jsp';</script>";
            
        } else{
            
            control.InscripcionASalidaTuristica(nombreSalida,  nombreTurista, cantTuristas, costo, fecha,  pago);
            response.sendRedirect("logedUser.jsp");
        }
        

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
