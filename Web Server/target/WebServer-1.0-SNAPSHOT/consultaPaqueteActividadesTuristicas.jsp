


<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            String usuario = (String) request.getSession().getAttribute("usuario");
            String tipoUsuario = (String) request.getSession().getAttribute("tipoUsuario");
        %>
        <meta charset="UTF-8">
        <title>Turismo.uy - Consulta de Paquete de Actividades Turisticas</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>

    <body onload="cargarPaquetes()">
        <header>
            <div id="logo">
                <h1>Turismo.uy - Consulta de Paquete de Actividades Turisticas</h1>
            </div>
            <div id="search">
                <form action="svlet" method="post">
                    <input type="text" name="query" placeholder="Buscar turistas o paquetes">
                    <button type="submit">Buscar</button>
                </form>
            </div>
            <div id="login">
                <a href="logedUser.jsp">Volver al inicio</a>
            </div>
        </header>

        <aside>
            <h2>Mi perfil</h2>
            <ul>
                <%
                    if (tipoUsuario != null) {
                    if (tipoUsuario.equals("proveedor")) {
                %>
                <li><a href="consultaUsuario.jsp">Consulta de Usuario</a></li> <!--Visitante, Proveedor, Turista -->
                <li><a href="SvModificarUsuario?usuario=<%= usuario%>&tipoUsuario=<%= tipoUsuario%>">Modificar mis datos</a></li>
                <li><a href="altaActividadTuristica.jsp?usuario=<%= usuario%>&tipoUsuario=<%= tipoUsuario%>">Alta Actividad Turistica</a></li> <!-- Proveedor -->
                <li><a href="consultaActividadTuristica.jsp">Consulta de Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->
                <li><a href="altaSalidaTuristica.jsp">Alta de Salida Turistica</a></li> <!-- Proveedor -->
                <li><a href="consultaSalidaTuristica.jsp">Consulta Salida Turistica</a></li> <!--Visitante, Proveedor, Turista -->
                <li><a href="consultaPaqueteActividadesTuristicas.jsp">Consulta Paquete Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->





                <% } else  {

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
                    } 
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
            <form action="SvPerfilPaquete" method="GET" name="perfilPaquete">
                <label for="paquete">Selecciona un paquete</label>
                <select id="paquete" name="paquete">
                </select>
                <button type="submit">Consultar
                </button>

            </form>
        </main>

        <footer>
            <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
            <p>&copy; 2023 Turismo.uy</p>
        </footer>
    </body>
    <script>
        function cargarPaquetes() {
            fetch("SvPaquete")
                    .then(response => response.text())
                    .then(data => {
                        const departamentos = data.split(",");
                        const select = document.getElementById("paquete");
                        departamentos.forEach(departamento => {
                            const option = document.createElement("option");
                            option.value = departamento;
                            option.text = departamento;
                            select.appendChild(option);
                        });
                    })
                    .catch(error => console.error("Error al paquetes: " + error));
        }


    </script>    

</html>
