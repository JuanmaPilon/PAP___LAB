<%@page import="WebServices.DtSalidaTuristica"%>
<%@page import="java.util.List"%>
<%@page import="WebServices.DtActividad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
    <%
        DtActividad act = (DtActividad) request.getSession().getAttribute("actividad");//usuario del que se muestra la info
        List<DtSalidaTuristica> salidas = (List<DtSalidaTuristica>) request.getSession().getAttribute("salidas");
        String usuario = (String) request.getSession().getAttribute("usuario");
        //DTUsuario usu = (DTUsuario) request.getSession().getAttribute("usu");
        String tipoUsuario = (String) request.getSession().getAttribute("tipoUsuario");

    %>
    <head>
        <meta charset="UTF-8">
        <title>Turismo.uy - Perfil de Actividad Turistica</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>

    <body>
        <header>
            <div id="logo">
                <h1>Turismo.uy</h1>
            </div>
            <div id="search">
                <div id="search">
                    <form action="SvBuscar" method="GET">
                        <input type="text" name="consulta" placeholder="Buscar actividades o paquetes">
                        <button type="submit">Buscar</button>
                    </form>
                </div>
            </div>
            <div id="login">
                <a href="logedUser.jsp" src="logedUser.jsp">Volver al inicio</a>
            </div>
        </header>

        <aside>
            <h2>Mi perfil</h2>
            <ul>
                <%                    if (tipoUsuario.equals("proveedor")) {
                %>
                <li><a href="consultaUsuario.jsp">Consulta de Usuario</a></li> <!--Visitante, Proveedor, Turista -->
                <li><a href="SvModificarUsuario?usuario=<%= usuario%>&tipoUsuario=<%= tipoUsuario%>">Modificar mis datos</a></li>
                <li><a href="altaActividadTuristica.jsp?usuario=<%= usuario%>&tipoUsuario=<%= tipoUsuario%>">Alta Actividad Turistica</a></li> <!-- Proveedor -->
                <li><a href="consultaActividadTuristica.jsp">Consulta de Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->
                <li><a href="altaSalidaTuristica.jsp">Alta de Salida Turistica</a></li> <!-- Proveedor -->
                <li><a href="consultaSalidaTuristica.jsp">Consulta Salida Turistica</a></li> <!--Visitante, Proveedor, Turista -->
                <li><a href="consultaPaqueteActividadesTuristicas.jsp">Consulta Paquete Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->





                <% } else if (tipoUsuario.equals("turista")) {

                %>
                <li><a href="consultaUsuario.jsp">Consulta de Usuario</a></li> <!--Visitante, Proveedor, Turista -->
                <li><a href="SvModificarUsuario?usuario=<%= usuario%>&tipoUsuario=<%= tipoUsuario%>">Modificar mis datos</a></li> <!-- Proveedor, Turista --> <!-- Proveedor, Turista -->
                <li><a href="consultaActividadTuristica.jsp">Consulta de Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->
                <li><a href="consultaSalidaTuristica.jsp">Consulta Salida Turistica</a></li> <!--Visitante, Proveedor, Turista -->
                <li><a href="inscripcionSalida.jsp">Inscripcion a Salida Turistica</a></li> <!-- Turista -->
                <li><a href="consultaPaqueteActividadesTuristicas.jsp">Consulta Paquete Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->
                <li><a href="compraPaquete.jsp?usuario=<%= usuario%>&tipoUsuario=<%= tipoUsuario%>">Comprar Paquete</a></li>  <!-- Turista -->
                <li><a href="inscripcionSalida.jsp">Inscripcion Salida Turistica</a></li> <!-- Turista -->
                    <%
                    } else {
                    %>
                <li> <a href = "consultaUsuario.jsp"> Consulta de Usuario</a> </li> 
                <li> <a href = "consultaActividadTuristica.jsp" > Consulta de Actividad Turistica</a > </li>
                <li> <a href = "consultaSalidaTuristica.jsp" > Consulta de Salida Turistica</a > </li>
                <li> <a href = "consultaPaqueteActividadesTuristicas.jsp" > Consulta de Paquete de Actividades Turisticas</a > </li>
                    <%
                        }
                    %>


            </ul>
        </aside>

        <main>

            <div class="actividad">
                <h3>Salidas Asociadas</h3>
                <ul>
                    <%
                        // Verificando si la lista no está vacía
                        if (salidas != null && !salidas.isEmpty()) {
                            // Iterando sobre la lista y mostrando los nombres de las salidas
                            for (DtSalidaTuristica salida : salidas) {
                                out.println("<li> <a href='#' onclick='mostrarSalida(\"" + salida.getNombre() + "\")'>" + salida.getNombre() + "</a></li>");
                            }
                        } else {
                            out.println("<li>No hay salidas asociadas.</li>");
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
        function mostrarSalida(nombreSalida) {
            var xhr = new XMLHttpRequest();
            console.log("mostrarsalida");
            xhr.open("GET", "SvPerfilSalida?nombreSalida=" + nombreSalida, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    window.location.href = "perfilSalidaTuristica.jsp";
                }
            };
            xhr.send();
        }
    </script>

</html>