
<%@page import="java.util.ArrayList"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <% String usuario = (String) request.getSession().getAttribute("usuario");
            String tipoUsuario = (String) request.getSession().getAttribute("tipoUsuario");
        %>
        <meta charset="UTF-8">
        <title>Turismo.uy - Alta de Actividad Turística</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
        <script>
            // Función para cargar departamentos en el select
            function cargarDepartamentos() {
                // Realiza una solicitud AJAX para obtener la lista de departamentos
                fetch("SvDepartamento")
                        .then(response => response.text())
                        .then(data => {
                            const departamentos = data.split(",");
                            const select = document.getElementById("departamento");
                            departamentos.forEach(departamento => {
                                const option = document.createElement("option");
                                option.value = departamento;
                                option.text = departamento;
                                select.appendChild(option);
                            });
                        })
                        .catch(error => console.error("Error al cargar departamentos: " + error));
            }

            // Función para cargar categorías en el select
            function cargarCategorias() {
                // Realiza una solicitud AJAX para obtener la lista de categorías
                fetch("SvCategoria")
                        .then(response => response.text())
                        .then(data => {
                            const categorías = data.split(",");
                            const select = document.getElementById("categoria");
                            categorías.forEach(categoria => {
                                const option = document.createElement("option");
                                option.value = categoria;
                                option.text = categoria;
                                select.appendChild(option);
                            });
                            // Permite múltiples selecciones
                            select.multiple = true;
                        })
                        .catch(error => console.error("Error al cargar categorías: " + error));
            }

            function cargarDatos() {
                cargarDepartamentos();
                cargarCategorias();
            }

            // Función para obtener las categorías seleccionadas
            function obtenerCategoriasSeleccionadas() {
                const select = document.getElementById("categoria");
                const categoriasSeleccionadas = Array.from(select.selectedOptions).map(option => option.value);
                return categoriasSeleccionadas;
            }

            // Manejar el envío del formulario
            document.querySelector("form").addEventListener("submit", function (event) {
                event.preventDefault(); // Evita el envío por defecto del formulario
                const categoriasSeleccionadas = obtenerCategoriasSeleccionadas();
                console.log("Categorías seleccionadas:", categoríasSeleccionadas);

                // Aquí puedes enviar las categorías seleccionadas al servidor como una lista
                // Puedes usar fetch o cualquier otro método para enviar los datos al servidor.
            });
        </script>
    </head>
    <body onload="cargarDatos()">
        <header>
            <div id="logo">
                <h1>Turismo.uy - Alta Actividad Turistica</h1>
            </div>
            <div id="search">
                <form action="buscar.jsp" method="post">
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
                    if (tipoUsuario.equals("proveedor")) {
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
                        System.out.println(tipoUsuario);
                    %>
                <li> <a href = "consultaUsuario.jsp"> Consulta de Usuario</a> </li> 
                <li> <a href = "consultaActividadTuristica.jsp" > Consulta de Actividad Turistica</a > </li>
                <li> <a href = "consultaSalidaTuristica.jsp" > Consulta de Salida Turistica</a > </li>
                <li> <a href = "consultaPaqueteActividadesTuristicas.jsp" > Consulta de Paquete de Actividades Turisticas</a > </li>
                    <%
                        }
                    %>


            </ul>

            <%--

            <h2>Departamentos</h2>
            <ul>
                <li><a href="#">Montevideo</a></li>
                <li><a href="#">Canelones</a></li>
                <li><a href="#">Maldonado</a></li>
                <li><a href="#">Colonia</a></li>
                <li><a href="#">Rocha</a></li>
            </ul>

            <h2>Categorías</h2>
            <ul>
                <li><a href="#">Aventura y Deporte</a></li>
                <li><a href="#">Campo y Naturaleza</a></li>
                <li><a href="#">Cultura y Patrimonio</a></li>
                <li><a href="#">Gastronomía</a></li>
                <li><a href="#">Turismo Playas</a></li>
               
            </ul>
            --%>
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
            <div class="error-message">
                <%= (errorMensaje != null) ? errorMensaje : ""%>
            </div>
            <form action="SvActividad" method="POST" enctype="multipart/form-data" >
                <label for="departamento">Departamento:</label>
                <select id="departamento" name="departamento"></select>
                <label for="nombre">Nombre de la actividad:</label>
                <input type="text" id="nombre" name="nombre" required>
                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion" rows="4" required></textarea>
                <label for="duracion">Duración (en horas):</label>
                <input type="number" id="duracion" name="duracion" min="1" required>
                <label for "costo">Costo (en pesos uruguayos):</label>
                <input type="number" id="costo" name="costo" required min="1">
                <label for="ciudad">Ciudad:</label>
                <input type="text" id="ciudad" name="ciudad" required>
                <label for="imagen">Imagen (opcional):</label>
                <input type="file" name="file" id="file">
                <label for="categoria">Categoría:</label>
                <select id="categoria" name="categoria" multiple></select>
                <p class="instructions">Mantén presionada la tecla Ctrl (o Cmd en Mac) y haz clic para seleccionar múltiples categorías.</p>
                <input type="hidden" id="usuario" name="usuario" value="<%= usuario%>">
                <label for="urlVideo">URL Video (Opcional)</label>
                <input type="url" id="urlVideo" name="urlVideo">
                <button type="submit">Dar de Alta</button>
            </form>
        </main>

        <footer>
            <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
            <p>&copy; 2023 Turismo.uy</p>
        </footer>
    </body>
</html>

