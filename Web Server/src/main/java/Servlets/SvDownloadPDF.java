/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.DtInscripcion;
import WebServices.DtSalidaTuristica;
import WebServices.DtTurista;
import WebServices.WebServices;
import WebServices.WebServicesService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvDownloadPDF", urlPatterns = {"/SvDownloadPDF"})
public class SvDownloadPDF extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //llamado a wsdl
        WebServicesService service = new WebServicesService();
        WebServices port = service.getWebServicesPort();

        String nickname = request.getParameter("usuario");
        String nombreSalida = request.getParameter("nombreSalida");

        Document document = new Document();
        //String outputPath = System.getProperty("catalina.base") + File.separator + "webapps" + File.separator + "TuAppName" + File.separator + "PDFs" + File.separator + nickname + ".pdf";

        // Obtener el directorio de descargas del usuario
        String carpetaDescargas = System.getProperty("user.home") + File.separator + "Descargas";

// Crear la ruta completa del archivo de destino en la carpeta de descargas
        String rutaArchivoDestino = carpetaDescargas + File.separator + nickname + ".pdf";

        try {
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivoDestino));
            document.open();

            List<DtSalidaTuristica> listaDTSalidaInscUsuario = port.traerInscSalidasDeTurista(nickname).getLista();
            for (DtSalidaTuristica dtSalida : listaDTSalidaInscUsuario) {

                if (dtSalida.getNombre().equals(nombreSalida)) {
                    //me traigo la actividad de la salida
                    DtTurista t = port.traerDTTurista(nickname);
                    // List<DtSalidaTuristica> listaInsc = port.traerInscSalidasDeTurista(nickname).getLista();
                    List<DtInscripcion> listaIncDelTurista = port.traerDTInscSalidasDeTurista(nickname).getLista();

                    int cantInscriptosSalida = 0;
                    for (DtInscripcion insc : listaIncDelTurista) {
                        if (insc.getNombreSalidaTuristica().equals(nombreSalida)) {
                            cantInscriptosSalida = insc.getCantTurista();
                        }
                    }

                    // Agregar el título
                    String titulo = "Lista de Inscripciones:";
                    document.add(new Paragraph("        " + titulo));

                    // Saltar una línea en blanco
                    document.add(new Paragraph(" "));
                    document.add(new Paragraph(" "));

                    document.add(new Paragraph("Nombre Turista: " + t.getNombre()));
                    document.add(new Paragraph("Nombre Actividad: " + dtSalida.getNombreActividad()));
                    document.add(new Paragraph("Nombre Salida Turistica: " + dtSalida.getNombre()));
                    document.add(new Paragraph("Fecha y hora Salida Turistica: " + dtSalida.getFSalida()));
                    document.add(new Paragraph("Cantidad de Turistas: " + cantInscriptosSalida));
                    document.add(new Paragraph("\n"));
                }
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
