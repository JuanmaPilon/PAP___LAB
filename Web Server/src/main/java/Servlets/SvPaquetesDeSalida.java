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
import logica.Compra;
import logica.DTPaquete;
import logica.Fabrica;
import logica.IControlador;
import logica.Paquete;
import logica.SalidaTuristica;
import logica.TipoPago;
import persistencia.exceptions.NonexistentEntityException;

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
        
        //DTPaquete  traerDTPaquete(String nombrePaquete);
        
        
        
       // float costo = actividad.getCosto();
        
        Date fecha = new Date();
        
        TipoPago pago = TipoPago.paquete;
        
        try{
        
        if("paquete".equals(formaDePago) ){
             pago = TipoPago.paquete;
             
             if(!control.salidaTuristicaLlena( nombreSalida, cantTuristas)){
                 
                 
                 boolean YaInscripto =  control.turistaYaInscriptoSalidaTuristica( nombreSalida, nombreTurista);
                 if(!YaInscripto){
                     
                     Compra compraDelTurista = control.traerCompraDelTurista( nombreTurista,  paquete);
                     
                     if(compraDelTurista.getCantTuristas() <= cantTuristas){
                         
                         int nuevaCantidad = (compraDelTurista.getCantTuristas() - cantTuristas);
                         compraDelTurista.setCantTuristas(nuevaCantidad);
                         
                         DTPaquete paquete2 = control.traerDTPaquete(paquete);
                         float costo = (float) ((100 - paquete2.getDescuento()) * (0.01 * cantTuristas * actividad.getCosto()));
                         control.nuevaCantTurista(compraDelTurista);
                         control.InscripcionASalidaTuristica(nombreSalida,  nombreTurista, cantTuristas, costo, fecha,  pago);
                          String errorMessage = "¡Se ha inscripto correctamente! Costo = " + costo;
                          String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'logedUser.jsp';</script>";
                         response.getWriter().write(alertScript);
                     } else{
                         
                          String errorMessage = "Compra exede la cantidad de inscriptos";
                          String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'inscripcionSalida.jsp';</script>";
                         response.getWriter().write(alertScript);
                     }
                     
                 } else {
                      String errorMessage = "Usted ya esta inscripto a esta salida";
                      String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'inscripcionSalida.jsp';</script>";
                      response.getWriter().write(alertScript);
                        }
                 
            
        } else{
            String errorMessage = "Salida llena";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'inscripcionSalida.jsp';</script>";
            response.getWriter().write(alertScript);
             }
             
      
             //if general
        } else {
            
            if(!control.salidaTuristicaLlena( nombreSalida, cantTuristas)){
                 
                 
                 boolean YaInscripto =  control.turistaYaInscriptoSalidaTuristica( nombreSalida, nombreTurista);
                 if(!YaInscripto){
                     
                 
                         float costo2 = (float) ((cantTuristas * actividad.getCosto()));
                         control.InscripcionASalidaTuristica(nombreSalida,  nombreTurista, cantTuristas, costo2, fecha,  pago);
                          String errorMessage = "¡Se ha inscripto correctamente! Costo = " + costo2;
                          String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'logedUser.jsp';</script>";
                         response.getWriter().write(alertScript);
                    
                     
                 } else {
                      String errorMessage = "Usted ya esta inscripto a esta salida";
                      String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'inscripcionSalida.jsp';</script>";
                      response.getWriter().write(alertScript);
                        }
                 
            
        } else{
            String errorMessage = "Salida llena";
            String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'inscripcionSalida.jsp';</script>";
            response.getWriter().write(alertScript);
             }
            
        }
        
        }catch(NonexistentEntityException ex){
             String errorMessage = "Error: NonexistentEntityException";
             String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'inscripcionSalida.jsp';</script>";
             response.getWriter().write(alertScript);
            
        } catch(Exception e){
             String errorMessage = "Error: Exception";
             String alertScript = "<script type='text/javascript'>alert('" + errorMessage + "'); window.location.href = 'inscripcionSalida.jsp';</script>";
             response.getWriter().write(alertScript);
            
        }
        
        

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
