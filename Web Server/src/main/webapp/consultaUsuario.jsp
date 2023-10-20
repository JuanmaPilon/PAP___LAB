<%@page import="logica.Usuario" %>
<%@page import="logica.Proveedor" %>
<%@page import="logica.Turista" %>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
            <%      String usuario = (String) request.getSession().getAttribute("usuario");
            Usuario usu = (Usuario) request.getSession().getAttribute("usu");
        %>
        <meta charset="UTF-8">
        <link href="styles.css" src="styles.css"">
        <title>Turismo.uy - Reserva de Paquetes Turísticos</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <body onload="cargarUsuarios()">
        <div class="container">
        <header>
            <div id="logo">
                <h1>Turismo.uy - Consulta de Usuario</h1>
            </div>
            <div id="search">
                <form action="buscar.jsp" method="post">
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
            <form action="SvPerfilUsuario" method="GET" name="cmbUsuarios" class="desplegable-menu">
                <label for="usuario">Selecciona un usuario:</label>
                <select id="usuario" name="usuario">
                    <% List<String> listaUsuarios = (List<String>) request.getAttribute("listaUsuarios");
                        if (listaUsuarios != null) {
                            for (String u : listaUsuarios) {%>
                    <option value="<%= u%>"><%= u%></option>
                    <% }
                        }%>

                </select>
                <button type="submit">Consultar</button>
            </form>

        </main>
        <script>
            var startIndex = 0;

            function cargarUsuarios() {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "SvUsuario?startIndex=" + startIndex, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var usuarios = xhr.responseText.split(",");
                        var select = document.getElementById("usuario");
                        for (var i = 0; i < usuarios.length; i++) {
                            var option = document.createElement("option");
                            option.value = usuarios[i];
                            option.text = usuarios[i];
                            select.appendChild(option);
                        }
                        startIndex += usuarios.length; // Actualiza el índice desde el cual cargar la próxima vez
                    }
                };
                xhr.send();
            }

            // Llama a la función para cargar usuarios al cargar la página
            window.onload = cargarUsuarios;
        </script>

        <footer>
            <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
            <p>&copy; 2023 Turismo.uy</p>
        </footer>
        </div>
    </body>
</html>