<%@page import="logica.Usuario" %>
<%@page import="logica.Proveedor" %>
<%@page import="logica.Turista" %>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <% String usuario = (String) request.getSession().getAttribute("usuario");
            Usuario usu = (Usuario) request.getSession().getAttribute("usu");
        %>
        <meta charset="UTF-8">
        <link href="styles.css" rel="stylesheet">
        <title>Turismo.uy - Alta de Salida Turística</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <body>
        <header>
            <div id="logo">
                <h1>Turismo.uy - Alta de Salida Turistica</h1>
            </div>
            <div id="search">
                <form action="svlet" method="get">
                    <input type="text" name="query" placeholder="Buscar turistas o paquetes">
                    <button type="submit">Buscar</button>
                </form>
            </div>
            <div id="login">
                <a href="logedUser.jsp">Volver a Inicio</a>
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
            
             <%
                String errorMensaje = (String) request.getAttribute("errorMensaje");
            %>
            
            <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                <% if (errorMessage != null) {%>
                <div class="error-message">
                    <%= errorMessage%>
                </div>
                <% }%>   

            <form action="SvAltaSalidaTuristica" method="post" enctype="multipart/form-data">
                <label for="departamento">Seleccionar Departamento:</label>
                <select id="departamento" name="departamento">
                </select>

                <label for="actividadTuristica">Seleccionar Actividad Turística:</label>
                <select id="actividadTuristica" name="actividadTuristica">
                </select>

                <label for="nombreSalida">Nombre de la Salida:</label>
                <input type="text" id="nombreSalida" name="nombreSalida" required>

                <label for="fechaHoraSalida">Fecha y Hora de Salida:</label>
                <input type="datetime-local" id="fechaHoraSalida" name="fechaHoraSalida" required>

                <label for="lugarSalida">Lugar de Salida:</label>
                <input type="text" id="lugarSalida" name="lugarSalida" required>

                <label for="cantidadMaxTuristas">Cantidad Máxima de Turistas:</label>
                <input type="number" id="cantidadMaxTuristas" name="cantidadMaxTuristas" min="1" required>

                <label for="imagenSalida">Imagen (opcional):</label>
                <input type="file" id="file" name="file">

                <button type="submit">Dar de Alta</button>
            </form>
        </main>
        <script>
            var startIndex = 0;
            var startIndex1 = 0;

            function cargarDeptos() {
                var xhr = new XMLHttpRequest();
                var select = document.getElementById("departamento");

                xhr.open("GET", "SvCargarDepartamentos?startIndex=" + startIndex, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var departamentos = xhr.responseText.split(",");
                        for (var i = 0; i < departamentos.length; i++) {
                            var option = document.createElement("option");
                            option.value = departamentos[i];
                            option.text = departamentos[i];
                            select.appendChild(option);
                        }

                        select.onchange = function () {
                            startIndex1 = 0; // Reinicia el índice de actividades cuando se selecciona un nuevo departamento
                            cargarActividades(select.value, startIndex1);
                        };

                        startIndex += departamentos.length;
                        if (select.value) {
                            cargarActividades(select.value, startIndex1);
                        } else if (departamentos.length > 0) {
                            cargarActividades(departamentos[0], startIndex1);
                        }
                    }
                };
                xhr.send();
            }


            function cargarActividades(departamento, startIndex1) {
                var xhr = new XMLHttpRequest();
                var select = document.getElementById("actividadTuristica");
                select.innerHTML = "";
                xhr.open("GET", "SvCargarActividades?departamento=" + departamento + "&startIndex1=" + startIndex1, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var actividades = xhr.responseText.split(",");
                        for (var i = 0; i < actividades.length; i++) {
                            var option = document.createElement("option");
                            option.value = actividades[i];
                            option.text = actividades[i];
                            select.appendChild(option);
                        }
                        startIndex1 += actividades.length;
                    }
                };
                xhr.send();
            }


            window.onload = function () {
                cargarDeptos(); // Cargar departamentos al cargar la página
            };
        </script>
        <footer>
            <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
            <p>&copy; 2023 Turismo.uy</p>
        </footer>

    </body>

</html>
