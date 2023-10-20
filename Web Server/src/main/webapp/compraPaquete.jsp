<%@page import="logica.Usuario" %>
<%@page import="logica.Proveedor" %>
<%@page import="logica.Turista" %>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%      String usuario = (String) request.getSession().getAttribute("usuario");
            Usuario usu = (Usuario) request.getSession().getAttribute("usu");
        %>
        <meta charset="UTF-8">
        <title>Turismo.uy - Compra de Paquete</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <body>
        <header>
            <div id="logo">
                <h1>Turismo.uy</h1>
            </div>
            <div id="search">
                <form action="svletalguno" method="post">
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
            <h2>Compra de Paquete</h2>

            <form action="procesarCompraPaquete.jsp" method="post">
                <label for="paquete">Seleccionar Paquete:</label>
                <select name="paquete" id="paquete">
                    <option value="paquete1">Paquete 1</option>
                    <option value="paquete2">Paquete XX</option>

                </select>

                <label for="cantidadTuristas">Cantidad de Turistas:</label>
                <input type="number" name="cantidadTuristas" id="cantidadTuristas" min="1">

                <button type="submit">Comprar Paquete</button>
            </form>
        </main>

        <footer>
            <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
            <p>&copy; 2023 Turismo.uy</p>
        </footer>
    </body>
</html>
