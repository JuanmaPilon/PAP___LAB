

<%@page import="WebServices.DtImagenActividad"%>
<%@page import="WebServices.DtPaquete"%>
<%@page import="WebServices.DtActividad"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="styles.css" src="styles.css"">
        <title>Turismo.uy - Consultas</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
        <style>



        </style>
    </head>
    <body>
        <header>
            <div id="logo">
                <h1>Turismo.uy</h1>
            </div>
            <div id="login">
                <a href="index.jsp" src="index.jsp">Volver al inicio</a>
            </div>
        </header>

        <%
            List<DtActividad> actividadesFiltradas = (List<DtActividad>) request.getSession().getAttribute("actividadesFiltradas");
            List<DtPaquete> paquetesFiltrados = (List<DtPaquete>) request.getSession().getAttribute("paquetesFiltrados");
            List<DtImagenActividad> imagenesActividades = (List<DtImagenActividad>) request.getSession().getAttribute("imagenesActividades");
        %>



        <main>
            <label for="ordenar">Ordenar por:</label>
            <select id="ordenar" onchange="ordenarTabla()">
                <option value="nombre">A-Z Nombre</option>
                <option value="ano">Año</option>
                <option value="departamento">Departamento</option>
            </select>
            
            <h1> Actividades </h1>
            <table border="1">
                <tr>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Duración</th>
                    <th>Costo</th>
                    <th>Ciudad</th>
                    <th>Fecha Alta</th>
                    <th>Departamento</th>
                    <th>Proveedor</th>
                    <th>Imagen</th>
                </tr>
                <% for (DtActividad actividad : actividadesFiltradas) {%>
                <tr>
                    <td><%= actividad.getNombre()%></td>
                    <td><%= actividad.getDescripcion()%></td>
                    <td><%= actividad.getDuracion()%></td>
                    <td><%= actividad.getCosto()%></td>
                    <td><%= actividad.getCiudad()%></td>
                    <td><%= actividad.getFAlta()%></td>
                    <td><%= actividad.getNombreDepartamento()%></td>
                    <td><%= actividad.getNombreProveedor()%></td>
                    <td>
                        <% for (DtImagenActividad imagenActividad : imagenesActividades) { %>
                        <% if (imagenActividad.getNombreActividad().equals(actividad.getNombre()) && imagenActividad.getNombre() != null) {%>
                        <img src="<%= imagenActividad.getRuta()%>" alt="Imagen de actividad">
                        <% } %>
                        <% } %>
                    </td>
                </tr>
                <% }%>
            </table>

             <h1> Paquetes </h1>
            <table border="1">
                <tr>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Validez</th>
                    <th>Descuento</th>
                    <th>Fecha Alta</th>
                </tr>
                <% for (DtPaquete paquete : paquetesFiltrados) {%>
                <tr>
                    <td><%= paquete.getNombre()%></td>
                    <td><%= paquete.getDescripcion()%></td>
                    <td><%= paquete.getValidez()%></td>
                    <td><%= paquete.getDescuento()%></td>
                    <td><%= paquete.getFechaAlta()%></td>
                </tr>
                <% }%>
            </table>

        </main>

        <script>
            function ordenarTabla() {
                //var select = document.getElementById("ordenar");
                //var selectedValue = select.options[select.selectedIndex].value;
                // var url = "SvBuscar?filtro=" + selectedValue;
                //window.location.href = url;



            }
        </script>




</html> 
<footer>
    <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
    <p>&copy; 2023 Turismo.uy</p>
</footer>
</body>
</html>

