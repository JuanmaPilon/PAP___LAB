<%@page import="java.net.URLEncoder"%>
<%@page import="WebServices.DtImagenActividad"%>
<%@page import="WebServices.DtPaquete"%>
<%@page import="WebServices.DtActividad"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="styles.css">
        <title>Turismo.uy - Consultas</title>
        <style>
        </style>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.3/js/jquery.tablesorter.min.js"></script>
        <script>
            $(function () {
                $('#mi-tabla').tablesorter();
                $('#mi-tabla2').tablesorter();
            });
        </script>
    </head>
    <body>
        <header>
            <div id="logo">
                <h1>Turismo.uy</h1>
            </div>
            <div id="login">
                <a href="index.jsp">Volver al inicio</a>
            </div>

            <%
                List<DtActividad> actividadesFiltradas = (List<DtActividad>) request.getSession().getAttribute("actividadesFiltradas");
                List<DtPaquete> paquetesFiltrados = (List<DtPaquete>) request.getSession().getAttribute("paquetesFiltrados");
                List<DtImagenActividad> imagenesActividades = (List<DtImagenActividad>) request.getSession().getAttribute("imagenesActividades");
                String nomActividadCodificado = null;
            %>
        </header>

        <main>
            <h1> Actividades </h1>
            <div>
                <table cellspacing="0" cellpadding="0" id="mi-tabla" class="tabla">
                    <thead>
                        <tr>
                            <th><span>Nombre</span></th>
                            <th><span>Descripción</span></th>
                            <th><span>Duración</span></th>
                            <th><span>Costo</span></th>
                            <th><span>Ciudad</span></th>
                            <th><span>Fecha Alta</span></th>
                            <th><span>Departamento</span></th>
                            <th><span>Proveedor</span></th>
                            <th><span>Imagen</span></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (DtActividad actividad : actividadesFiltradas) {%>
                        <tr>
                            <td class="align-left"><%= actividad.getNombre()%></td>
                            <% nomActividadCodificado = URLEncoder.encode(actividad.getNombre(), "UTF-8");%>
                            <td><%= actividad.getDescripcion()%></td>
                            <td><%= actividad.getDuracion()%></td>
                            <td><%= actividad.getCosto()%></td>
                            <td><%= actividad.getCiudad()%></td>
                            <td><%= actividad.getFAlta()%></td>
                            <td><%= actividad.getNombreDepartamento()%></td>
                            <td><%= actividad.getNombreProveedor()%></td>
                            <td>                                
                                <img src="SvMostrarImagenActividad?nombreActividad=<%=nomActividadCodificado%>" alt="alt" style="width: 250px; height: 250px;"/>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>



                <h1> Paquetes </h1>
                <table cellspacing="0" cellpadding="0" id="mi-tabla2" class="tabla">
                    <thead>
                        <tr>
                            <th><span>Nombre</span></th>
                            <th><span>Descripción</span></th>
                            <th><span>Validez</span></th>
                            <th><span>Descuento</span></th>
                            <th><span>Fecha Alta</span></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (DtPaquete paquete : paquetesFiltrados) {%>
                        <tr>
                            <td class="align-left"><%= paquete.getNombre()%></td>
                            <td><%= paquete.getDescripcion()%></td>
                            <td><%= paquete.getValidez()%></td>
                            <td><%= paquete.getDescuento()%></td>
                            <td><%= paquete.getFechaAlta()%></td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>
        </main>

        <footer>
            <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
            <p>&copy; 2023 Turismo.uy</p>
        </footer>
    </body>
</html>
