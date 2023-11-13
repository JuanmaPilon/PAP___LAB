
<%@page import="java.util.List"%>
<%@page import="WebServices.DtPaquete"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <%DtPaquete dtpaquete = (DtPaquete) request.getSession().getAttribute("dtpaquete");
        List<String> listaActividadesPaquete = (List) request.getSession().getAttribute("listaActividadesPaquete");
    %> 

    <head>
        <meta charset="UTF-8">
        <title>Turismo.uy - Perfil de Paquete</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>

    <body>
        <header>
            <div id="logo">
                <h1>Turismo.uy</h1>
            </div>
            <div id="search">
                <form action="svlet" method="post">
                    <input type="text" name="query" placeholder="Buscar turistas o paquetes">
                    <button type="submit">Buscar</button>
                </form>
            </div>
            <div id="login">
                <a href="logedUser.jsp" src="logedUser.jsp">Volver al inicio</a>
            </div>
        </header>


        <main>
            <div class="actividad">
                <h3><%=dtpaquete.getNombre()%></h3>
                <p>Descripcion: <%=dtpaquete.getDescripcion()%></p>
                <p>Validez (días): <%=dtpaquete.getValidez()%></p>
                <p>Descuento: <%=dtpaquete.getDescuento()%></p>
                <p>Fecha de Alta: <%=dtpaquete.getFechaAlta()%> </p>


            </div>
            <div class="actividad">
                <h3>Actividades del Paquete</h3>
                <ul>
                    <%
                        if (listaActividadesPaquete != null && !listaActividadesPaquete.isEmpty()) {
                            for (String actividad : listaActividadesPaquete) {

                                out.println("<li> <a href='#' onclick='mostrarActividad(\"" + actividad + "\")'>" + actividad + "</a></li>");
                            }
                        } else {
                            out.println("<li>No hay actividades disponibles para este paquete.</li>");
                        }
                    %>

                </ul>
            </div>



        </main>

        <footer>
            <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
            <p>&copy; 2023 Turismo.uy</p>
        </footer>
    </body>

    <script>

        function mostrarActividad(nombreActividad) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "SvPerfilActividad?nombreActividad=" + nombreActividad, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    window.location.href = "perfilActividadTuristica.jsp";
                }
            };
            xhr.send();
        }
    </script>
</html>