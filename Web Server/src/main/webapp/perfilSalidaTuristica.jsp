<%@page import="logica.SalidaTuristica"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logica.Actividad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logica.Usuario" %>
<%@page import="logica.Proveedor" %>
<%@page import="logica.Turista" %>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
        <%
        SalidaTuristica salida = (SalidaTuristica) request.getSession().getAttribute("salida");//usuario del que se muestra la info
        String rutaImagenPerfil =  (String) request.getSession().getAttribute("imagen");
        String usuario = (String) request.getSession().getAttribute("usuario");
            Usuario usu = (Usuario) request.getSession().getAttribute("usu");

    %>
<head>
    <meta charset="UTF-8">
    <title>Turismo.uy - Perfil de Salida Turística</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>
    <header>
        <div id="logo">
            <h1>Turismo.uy - Perfil de Salida Turistica</h1>
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




                    <% } else if (usu    instanceof Turista) {

                    %>
                    <li><a href="consultaUsuario.jsp">Consulta de Usuario</a></li> <!--Visitante, Proveedor, Turista -->
                    <li><a href="SvModificarUsuario?usuario=<%= usuario%>">Modificar mis datos</a></li> <!-- Proveedor, Turista -->
                    <li><a href="consultaActividadTuristica.jsp">Consulta de Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->
                    <li><a href="consultaSalidaTuristica.jsp">Consulta Salida Turistica</a></li> <!--Visitante, Proveedor, Turista -->
                    <li><a href="inscripcionSalida.jsp">Inscripcion a Salida Turistica</a></li> <!-- Turista -->
                    <li><a href="consultaPaqueteActividadesTuristicas.jsp">Consulta Paquete Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->
                    <li><a href="compraPaquete.jsp?usuario=<%= usuario%>">Comprar Paquete</a></li>  <!-- Turista -->
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
                 <img src="<%= rutaImagenPerfil%>" alt="alt" style="width: 100px; height: 100px;" />
                <h3><%=salida.getNombre()%></h3>
                <p>Cantidad máxima de turistas <%=salida.getCantMax() %></p>
                <p>Fecha de la Salida <%=salida.getfSalida() %></p>
                <p>Lugar: <%=salida.getLugar() %></p>
                <p>Fecha de Alta: <%=salida.getfAlta() %> </p>
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
