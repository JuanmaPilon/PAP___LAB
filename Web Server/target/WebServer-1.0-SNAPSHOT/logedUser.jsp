<%@page import="logica.DTUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logica.Usuario" %>
<%@page import="logica.Proveedor" %>
<%@page import="logica.Turista" %>
<%@page import="java.util.ArrayList"%>
<%@page import="logica.Categoria"%>
<%@page import="logica.Departamento"%>
<!DOCTYPE html>
<html>
    <%
        ArrayList<String> listaCategorias = (ArrayList<String>) request.getSession().getAttribute("listaCategorias");
        ArrayList<String> listaDepartamentos = (ArrayList<String>) request.getSession().getAttribute("listaDepartamentos");
        String tipoUsuario = (String) request.getSession().getAttribute("tipoUsuario");
        String usuario = (String) request.getSession().getAttribute("usuario");
        DTUsuario usu = (DTUsuario) request.getSession().getAttribute("usu");
    %>
    <head>
        <meta charset="UTF-8">
        <link href="styles.css" src="styles.css"">
        <title>Turismo.uy</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <body>
        <div class="container" class="clearfix">
            <header>
                <div id="logo">
                    <h1>Turismo.uy</h1>
                </div>
                <div id="search">
                    <form action="SvBuscar" method="GET">
                        <input type="text" name="consulta" placeholder="Buscar turistas o paquetes">
                        <button type="submit">Buscar</button>
                    </form>
                </div>
                <div id="login">
                    <%
                        if (usuario != null) {
                    %>
                    <a href="#" onclick="consultarPerfil('<%= usuario%>')"><%= usuario%></a> | <a href="index.jsp">Cerrar Sesión</a>
                    <%
                    } else {
                    %>
                    <a href="login.jsp">Iniciar Sesión</a>
                    <%
                        }
                    %>
                </div>
            </header>



            <aside>
                <h2>Mi perfil</h2>
                <ul>
                    <%
                        if (tipoUsuario.equals("proveedor")) {
                    %>
                    <li><a href="consultaUsuario.jsp">Consulta de Usuario</a></li> <!--Visitante, Proveedor, Turista -->
                    <li><a href="SvModificarUsuario?usuario=<%= usuario%>&tipoUsuario=<%= tipoUsuario %>">Modificar mis datos</a></li>
                    <li><a href="altaActividadTuristica.jsp?usuario=<%= usuario%>&tipoUsuario=<%= tipoUsuario %>">Alta Actividad Turistica</a></li> <!-- Proveedor -->
                    <li><a href="consultaActividadTuristica.jsp">Consulta de Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->
                    <li><a href="altaSalidaTuristica.jsp">Alta de Salida Turistica</a></li> <!-- Proveedor -->
                    <li><a href="consultaSalidaTuristica.jsp">Consulta Salida Turistica</a></li> <!--Visitante, Proveedor, Turista -->
                    <li><a href="consultaPaqueteActividadesTuristicas.jsp">Consulta Paquete Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->


                    


                    <% } else if (tipoUsuario.equals("turista")) {
                         ArrayList<String> actividadesFavoritas = (ArrayList<String>) request.getSession().getAttribute("actividadesFavoritas");
                    %>
                    <li><a href="consultaUsuario.jsp">Consulta de Usuario</a></li> <!--Visitante, Proveedor, Turista -->
                    <li><a href="SvModificarUsuario?usuario=<%= usuario%>&tipoUsuario=<%= tipoUsuario %>">Modificar mis datos</a></li> <!-- Proveedor, Turista --> <!-- Proveedor, Turista -->
                    <li><a href="consultaActividadTuristica.jsp">Consulta de Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->
                    <li><a href="consultaSalidaTuristica.jsp">Consulta Salida Turistica</a></li> <!--Visitante, Proveedor, Turista -->
                    <li><a href="consultaPaqueteActividadesTuristicas.jsp">Consulta Paquete Actividad Turistica</a></li> <!-- Visitante, Proveedor, Turista -->
                    <li><a href="compraPaquete.jsp?usuario=<%= usuario%>&tipoUsuario=<%= tipoUsuario %>">Comprar Paquete</a></li>  <!-- Turista -->
                    <li><a href="inscripcionSalida.jsp?usuario=<%= usuario%>&tipoUsuario=<%= tipoUsuario %>">Inscripcion Salida Turistica</a></li> <!-- Turista -->
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
                <h2>Departamentos</h2>
                <ul id="miContenedor">
                    <%
                        if (listaDepartamentos != null && !listaDepartamentos.isEmpty()) {
                            for (String d : listaDepartamentos) {
                                out.println("<li> <a href='#' id='departamento-" + d + "'>" + d + "</a></li>");
                            }
                        } else {
                            out.println("<li>No hay departamentos con actividades confirmadas.</li>");
                        }
                    %>
                </ul>


                <h2>Categorías</h2>
                <ul id="miContenedor2">
                    <%
                        if (listaCategorias != null && !listaCategorias.isEmpty()) {
                            for (String c : listaCategorias) {
                                out.println("<li> <a href='#' id='categoria-" + c + "'>" + c + "</a></li>");
                            }
                        } else {
                            out.println("<li>No hay categorias con actividades confirmadas.</li>");
                        }
                    %>

                </ul>
            </aside>


            <main>
                <section id="actividadesContainer" class="asd" >

                </section>

            </main>


            <footer>
                <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
                <p>&copy; 2023 Turismo.uy</p>
            </footer>
        </div>
    </body>
    <script>
        function consultarPerfil(usuario) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "SvPerfilUsuario?usuario=" + usuario, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // Haz lo que necesites con la respuesta del servidor, si es necesario
                    // Por ejemplo, redirigir a la página de perfil del usuario
                    window.location.href = "perfilUsuario.jsp";
                }
            };
            xhr.send();
        }

        document.addEventListener("DOMContentLoaded", function () {
            // Obtener la referencia al contenedor de actividades
            var actividadesContainer = document.getElementById("actividadesContainer");

            // Obtener todos los enlaces de departamentos y categorías
            var contenedor = document.getElementById("miContenedor");

            // Obtener los enlaces solo dentro del contenedor específico
            var links = contenedor.querySelectorAll('ul li a');

            // Iterar sobre los enlaces y agregar un controlador de eventos para el clic
            links.forEach(function (link) {
                link.addEventListener("click", function (event) {
                    event.preventDefault();

                    // Obtener el id del departamento o categoría seleccionada

                    var id = link.id;
                    console.log("Elemento clickeado: " + id);

                    // Realizar una solicitud al servidor para obtener las actividades del departamento o categoría seleccionada
                    var xhr = new XMLHttpRequest();
                    var url;

                    // Verificar si es un enlace de departamento o categoría y construir la URL correspondiente
                    if (id.startsWith("departamento-")) {
                        var departamentoId = id.split("-")[1];
                        url = "SvObtenerActividades?departamentoId=" + departamentoId;
                    }

                    console.log("URL de solicitud: " + url);
                    xhr.open("GET", url, true);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            // Manejar la respuesta del servidor y mostrar las actividades en el contenedor
                            actividadesContainer.innerHTML = xhr.responseText;
                        }
                    };
                    xhr.send();
                });
            });
        });
        document.addEventListener("DOMContentLoaded", function () {
            // Obtener la referencia al contenedor de actividades
            var actividadesContainer = document.getElementById("actividadesContainer");

            // Obtener todos los enlaces de departamentos y categorías
            var contenedor = document.getElementById("miContenedor2");

            // Obtener los enlaces solo dentro del contenedor específico
            var links = contenedor.querySelectorAll('ul li a');

            // Iterar sobre los enlaces y agregar un controlador de eventos para el clic
            links.forEach(function (link) {
                link.addEventListener("click", function (event) {
                    event.preventDefault();

                    // Obtener el id del departamento o categoría seleccionada

                    var id = link.id;
                    console.log("Elemento clickeado: " + id);

                    // Realizar una solicitud al servidor para obtener las actividades del departamento o categoría seleccionada
                    var xhr = new XMLHttpRequest();
                    var url;

                    // Verificar si es un enlace de departamento o categoría y construir la URL correspondiente
                    if (id.startsWith("categoria-")) {
                        var categoriaId = id.split("-")[1];
                        url = "SvObtenerActividadesCategoria?categoriaId=" + categoriaId;
                    }

                    console.log("URL de solicitud: " + url);
                    xhr.open("GET", url, true);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            // Manejar la respuesta del servidor y mostrar las actividades en el contenedor
                            actividadesContainer.innerHTML = xhr.responseText;
                        }
                    };
                    xhr.send();
                });
            });
        });


    </script>

</html>