<%@page import="logica.Paquete"%>
<%@page import="logica.SalidaTuristica"%>
<%@page import="logica.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logica.Actividad" %>
<%@ page import="java.util.ArrayList" %>

<%@page import="logica.Usuario" %>
<%@page import="logica.Proveedor" %>
<%@page import="logica.Turista" %>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
    <%
        Actividad act = (Actividad) request.getSession().getAttribute("actividad");//usuario del que se muestra la info
        ArrayList<SalidaTuristica> salidas = act.getListaSalidaTuristica();
        String usuario = (String) request.getSession().getAttribute("usuario");
        Usuario usu = (Usuario) request.getSession().getAttribute("usu");


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
                <form action="svlet" method="post">
                    <input type="text" name="query" placeholder="Buscar turistas o paquetes">
                    <button type="submit">Buscar</button>
                </form>
            </div>
            <div id="login">
                <a href="logedUser.jsp" src="logedUser.jsp">Volver al inicio</a>
            </div>
        </header>
        
         <aside>
                      <h2>Mi perfil</h2>
            <ul>
                <%
                    if (usu instanceof Proveedor) {
                %>
                <li><a href="consultaUsuario.jsp">Consulta de Usuario</a></li> <!--Visitante, Proveedor, Turista -->
                <li><a href="SvModificarUsuario?usuario=<%= usuario%>">Modificar mis datos</a></li> <!-- Proveedor, Turista -->
                <li><a href="altaActividadTuristica.jsp?usuario=<%= usuario%>">Alta Actividad Turistica</a></li> <!-- Proveedor -->
                <li><a href="consultaActividadTuristica.jsp">Consulta de Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->
                <li><a href="altaSalidaTuristica.jsp">Alta de Salida Turistica</a></li> <!-- Proveedor -->
                <li><a href="consultaSalidaTuristica.jsp">Consulta Salida Turistica</a></li> <!--Visitante, Proveedor, Turista -->
                <li><a href="consultaPaqueteActividadesTuristicas.jsp">Consulta Paquete Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->
                <li><a href="inscripcionSalida.jsp">Inscripcion Salida Turistica</a></li> <!-- Visitante, Proveedor, Turista -->

                <%
                    }
                %>


                <%
                    if (usu instanceof Turista) {
                %>
                <li><a href="consultaUsuario.jsp">Consulta de Usuario</a></li> <!--Visitante, Proveedor, Turista -->
                <li><a href="SvModificarUsuario?usuario=<%= usuario%>">Modificar mis datos</a></li> <!-- Proveedor, Turista -->
                <li><a href="consultaActividadTuristica.jsp">Consulta de Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->
                <li><a href="consultaSalidaTuristica.jsp">Consulta Salida Turistica</a></li> <!--Visitante, Proveedor, Turista -->
                <li><a href="inscripcionSalida.jsp">Inscripcion a Salida Turistica</a></li> <!-- Turista -->
                <li><a href="consultaPaqueteActividadesTuristicas.jsp">Consulta Paquete Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->
                <li><a href="compraPaquete.jsp">Compra de Paquete</a></li> <!-- Turista -->
                    <%
                        }
                    %>


            </ul>
        </aside>

        <main>

            <div class="actividad">
                <h3>Salidas Asociadas</h3>
                <ul>
                    <%                        if (salidas != null && !salidas.isEmpty()) {
                            for (SalidaTuristica nombreSalida : salidas) {
                                System.out.println(nombreSalida.getNombre());
                                out.println("<li> <a href='#' onclick='mostrarSalida(\"" + nombreSalida.getNombre() + "\")'>" + nombreSalida.getNombre() + "</a></li>");
                            }
                        } else {
                            out.println("<li>No hay salidas disponibles para esta actividad.</li>");
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
            xhr.open("GET", "SvPerfilSalida?nombreSalida=" + nombreSalida, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    window.location.href = "perfilSalidaTuristica.jsp";
                }
                ;
                xhr.send();
            }
    </script>

</html>