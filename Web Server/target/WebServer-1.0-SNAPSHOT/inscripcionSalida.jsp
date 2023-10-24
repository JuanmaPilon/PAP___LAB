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
        <title>Turismo.uy - Inscripción a Salida Turistica</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>
    <body onload="cargarDatos()">
        <header>
            <div id="logo">
                <h1>Turismo.uy - Inscripcion a Salida Turistica  </h1>
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

        <%
            String filtro = "";

        %>

        <main>
            <form action="SvPaquetesDeSalida" method="POST">
                <input type="hidden" name="usuarioV" id="usuarioV" value="<%= usuario%>"> 



                <label for="departamento">Departamento:</label>
                <select id="departamento" name="departamento"></select>
                <button type="button" onclick="filtrarPorDepartamento()">Filtrar por Departamento</button>


                <label for="categoria">Categoria:</label>
                <select id="categoria" name="categoria"></select>
                <button type="button" onclick="filtrarPorCategoria()">Filtrar por Categoria</button>


                <label for="actividad">Actividades:</label>
                <select id="actividad" name="actividad"></select>
                <button type="button" onclick="verDetalles()">Ver Detalles Actividad</button>


                <label for="actividadSalida">Salidas:</label>
                <select id="actividadSalida" name="actividadSalida"></select>
                <button type="button" onclick="cargarSalidasDeActividad()">Cargar Salidas de la Actividad</button>


                <button type="button" onclick="verDetallesSalida()">Ver Detalles Salida</button> 


                <label for="cantTuristas">Nº Turistas:</label>
                <input type="number" id="cantTuristas" name="cantTuristas" min="1">

                <label for="paquetes">Paquetes:</label>
                <select id="paquetes" name="paquetes"></select>
                <button type="button" onclick="cargarPaquetes()">Obtener paquetes</button>

                <label for="formaPago">Forma de pago:</label>
                <select name="formaPago" id="formaPago">
                    <option value="general">General</option>
                    <option value="por_paquete">Por Paquete</option>
                </select>

                <button type="submit">Inscribirse</button>
            </form>


        </main>

        <script>

            function cargarDepartamentos() {
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

            function cargarCategorias() {
                fetch("SvCategoria")
                        .then(response => response.text())
                        .then(data => {
                            const categorias = data.split(",");
                            const select = document.getElementById("categoria");
                            categorias.forEach(categoria => {
                                const option = document.createElement("option");
                                option.value = categoria;
                                option.text = categoria;
                                select.appendChild(option);
                            });
                            // Permite múltiples selecciones
                        })
                        .catch(error => console.error("Error al cargar categorías: " + error));
            }



            function filtrarPorDepartamento() {
                const filtro = "FiltroDepartamento";
                const departamentoSeleccionado = document.getElementById("departamento").value;

                // Construir la URL con el valor seleccionado del departamento
                const url = "SvActividad?filtro=" + filtro + "&departamento=" + encodeURIComponent(departamentoSeleccionado);

                fetch(url)
                        .then(response => response.text())
                        .then(data => {
                            const select = document.getElementById("actividad");
                            select.innerHTML = ''; // Limpia las opciones anteriores

                            const actividades = data.split(",");
                            actividades.forEach(actividad => {
                                const option = document.createElement("option");
                                option.value = actividad;
                                option.text = actividad;
                                select.appendChild(option);
                            });
                        })
                        .catch(error => console.error("Error al filtrar por departamento: " + error));
            }


            function filtrarPorCategoria() {
                const filtro = "FiltroCategoria";
                const categoriaSeleccionada = document.getElementById("categoria").value;

                // Construir la URL con el valor seleccionado de la categoría
                const url = "SvActividad?filtro=" + filtro + "&categoria=" + encodeURIComponent(categoriaSeleccionada);

                fetch(url)
                        .then(response => response.text())
                        .then(data => {
                            const select = document.getElementById("actividad");
                            select.innerHTML = ''; // Limpia las opciones anteriores

                            const actividades = data.split(",");
                            actividades.forEach(actividad => {
                                const option = document.createElement("option");
                                option.value = actividad;
                                option.text = actividad;
                                select.appendChild(option);
                            });
                        })
                        .catch(error => console.error("Error al filtrar por categoría: " + error));
            }

            function verDetalles() {
                var actividadSeleccionada = document.getElementById("actividad").value;

                // Verifica si actividadSeleccionada es null o está vacía
                if (actividadSeleccionada === null || actividadSeleccionada === "") {
                    return; // No hace nada si es null o vacía
                }

                var url = "SvActividad?actividad=" + actividadSeleccionada;
                window.open(url, '_blank');
            }

            function cargarSalidasDeActividad() {
                //  const filtro = "FiltroSalidasPorNomActividad";
                const actividadSeleccionada = document.getElementById("actividad").value;
                const url = "SvInscripcion?actividad=" + encodeURIComponent(actividadSeleccionada);

                fetch(url)
                        .then(response => response.text()) // Espera datos de texto
                        .then(data => {
                            const select = document.getElementById("actividadSalida");

                            select.innerHTML = ''; // Limpia las opciones anteriores

                            const actividadSalidas = data.split(",");
                            actividadSalidas.forEach(actividadSalida => {
                                if (actividadSalida) { // Evita opciones vacías
                                    const option = document.createElement("option");
                                    option.value = actividadSalida;
                                    option.text = actividadSalida;
                                    select.appendChild(option);
                                }
                            });
                        })
                        .catch(error => console.error("Error al cargar las salidas: " + error));
            }

            function verDetallesSalida() {
                console.log("verDetalleSalida");
                const filtro = "FiltroSalidas";
                var salidaSeleccionada = document.getElementById("actividadSalida").value;
                console.log("antes del if");
                // Verifica si salidaSeleccionada es null o está vacía
                if (salidaSeleccionada === null || salidaSeleccionada === "") {
                    console.log("return");
                    return; // No hace nada si es null o vacía
                }
                console.log("antes del var");
                var url = "SvSalida?actividadSalida=" + salidaSeleccionada + "&filtro=" + filtro;
                window.open(url, '_blank');
            }

            function cargarPaquetes() {
                const actividadSeleccionada = document.getElementById("actividadSalida").value;
                const usuarioSeleccionado = document.getElementById("usuarioV").value;
                console.log(usuarioSeleccionado);
                const url = "SvPaquetesDeSalida?actividadSalida=" + encodeURIComponent(actividadSeleccionada) + "&usuario=" + encodeURIComponent(usuarioSeleccionado);

                fetch(url)
                        .then(response => response.text())
                        .then(data => {
                            const select = document.getElementById("paquetes");
                            select.innerHTML = ''; // Limpia las opciones anteriores

                            const nombresPaquetesFiltrados = data.split(",");
                            nombresPaquetesFiltrados.forEach(paquete => {
                                if (paquete) { // Evita opciones vacías
                                    const option = document.createElement("option");
                                    option.value = paquete;
                                    option.text = paquete;
                                    select.appendChild(option);
                                }
                            });

                            if (paquetesSelect == null) {
                                // Si está vacío, habilita la opción "General" y deshabilita la opción "Por Paquete"
                                formaPagoSelect.querySelector("option[value='general']").disabled = false;
                                formaPagoSelect.querySelector("option[value='por_paquete']").disabled = true;
                            } else {
                                // Si no está vacío, habilita ambas opciones
                                formaPagoSelect.querySelector("option[value='general']").disabled = false;
                                formaPagoSelect.querySelector("option[value='por_paquete']").disabled = false;
                            }
                        })
                        .catch(error => console.error("Error al cargar los paquetes: " + error));
            }

            function cargarDatos() {
                cargarDepartamentos();
                cargarCategorias();
            }
        </script>


        <footer>
            <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
            <p>&copy; 2023 Turismo.uy</p>
        </footer>
    </body>
</html>
