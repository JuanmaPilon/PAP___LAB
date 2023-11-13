/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.DtActividad;
import WebServices.DtCompra;
import WebServices.DtPaquete;
import WebServices.NoExisteCompra_Exception;
import WebServices.TipoPago;
import WebServices.WebServices;
import WebServices.WebServicesService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Pc
 */
@WebServlet(name = "SvPaquetesDeSalida", urlPatterns = {"/SvPaquetesDeSalida"})
public class SvPaquetesDeSalida extends HttpServlet {

   // Fabrica fabrica = Fabrica.getInstance();
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
        HttpSession misesion = request.getSession();

        String nombreSalida = request.getParameter("actividadSalida");
        String nombreTurista = request.getParameter("usuario");
        


        List<DtPaquete> paquetesComprados = port.listaPaquetesCompradosVigentes(nombreTurista).getLista();


        ArrayList<String> nombresPaquetesFiltrados = new ArrayList<>();
        for (DtPaquete paquete : paquetesComprados) {
            nombresPaquetesFiltrados.add(paquete.getNombre());
        }
        misesion.setAttribute("nombreTurista", nombreTurista);
        String nombresPaquetesStr = String.join(",", nombresPaquetesFiltrados);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(nombresPaquetesStr);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();
        String nombreSalida = request.getParameter("actividadSalida");
        
        String nombreTurista = request.getParameter("usuarioV");
        
        String nombreActividad = request.getParameter("actividad");
        
        int cantTuristas = Integer.parseInt(request.getParameter("cantTuristas"));
        
        String formaDePago = request.getParameter("formaPago");
        
        String paquete = request.getParameter("paquetes");
        
        
        //DTSalidaTuristica salida = control.ConsultaSalidaTuristica(nombreSalida);
        
        DtActividad actividad = port.traerDTActividad(nombreActividad);
        
        //DTPaquete  traerDTPaquete(String nombrePaquete);
        
        
        
       // float costo = actividad.getCosto();
        
        Date fecha = new Date();
        
        TipoPago pago = TipoPago.PAQUETE;
        
        try{
        
        if("paquete".equals(formaDePago) ){
             pago = TipoPago.PAQUETE;
             
             if(!port.salidaTuristicaLlena( nombreSalida, cantTuristas)){
                 
                 
                 boolean YaInscripto =  port.turistaYaInscriptoSalidaTuristica( nombreSalida, nombreTurista);
                 if(!YaInscripto){
                     
                     DtCompra compraDelTurista = port.traerCompraDelTurista( nombreTurista,  paquete);
                     
                     if(compraDelTurista.getCantTuristas() <= cantTuristas){
                         
                         int nuevaCantidad = (compraDelTurista.getCantTuristas() - cantTuristas);
                         compraDelTurista.setCantTuristas(nuevaCantidad);
                         
                         DtPaquete paquete2 = port.traerDTPaquete(paquete);
                         float costo = (float) ((100 - paquete2.getDescuento()) * (0.01 * cantTuristas * actividad.getCosto()));
                         port.nuevaCantTurista(compraDelTurista.getId(), nuevaCantidad);
                         //no importa la fecha, la setea la lógica
                         String fechaString = "11/11/1900";
                         port.inscripcionASalidaTuristica(nombreSalida,  nombreTurista, cantTuristas, costo, fechaString,  pago);
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
             pago = pago.GENERAL;
            if(!port.salidaTuristicaLlena( nombreSalida, cantTuristas)){
                 
                 
                 boolean YaInscripto =  port.turistaYaInscriptoSalidaTuristica( nombreSalida, nombreTurista);
                 if(!YaInscripto){
                     
                 
                         float costo2 = (float) ((cantTuristas * actividad.getCosto()));
                                                  //no importa la fecha, la setea la lógica
                         String fechaString = "11/11/1900";
                         port.inscripcionASalidaTuristica(nombreSalida,  nombreTurista, cantTuristas, costo2, fechaString,  pago);
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
        
        } catch(NoExisteCompra_Exception e){
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
