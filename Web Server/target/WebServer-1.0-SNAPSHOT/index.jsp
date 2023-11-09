<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logica.Categoria"%>
<%@page import="logica.Departamento"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="styles.css" src="styles.css"">
        <title>Turismo.uy - Reserva de Paquetes Turísticos</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    <script>
  
        function redirigirSiEsMovil() {
            if (window.innerWidth <= 800) {
                window.location.href = 'homeMovil.jsp';
            }
        }
        document.addEventListener("DOMContentLoaded", redirigirSiEsMovil);
        window.addEventListener("resize", redirigirSiEsMovil);
    </script>
    </head>
    <body >
        <%
            ArrayList<String> listaCategorias = (ArrayList<String>) request.getSession().getAttribute("listaCategorias");

            ArrayList<String> listaDepartamentos = (ArrayList<String>) request.getSession().getAttribute("listaDepartamentos");
        %>
        <header>
            <div id="logo">
                <h1>Turismo.uy</h1>
            </div>
            <div id="search">
                <form action="buscar.jsp" method="get">
                    <input type="text" calss="no-buscar" name="query" placeholder="Buscar turistas o paquetes">
                    <button type="submit">Buscar</button>
                </form>
            </div>
            <div id="login">
                <a href="login.jsp">Iniciar Sesión</a> | <a href="altaUsuario.jsp">Alta Usuario</a> | <a href="homeMovil.jsp">Inicio Movil</a>
            </div>
            
        </header>


        <aside>
            <h2>Mis acciones</h2>
            <ul>
                <li><a href="consultaUsuario.jsp">Consulta de Usuario</a></li>
                <li><a href="consultaActividadTuristica.jsp">Consulta de Actividad Turistica</a></li>
                <li><a href="consultaSalidaTuristica.jsp">Consulta de Salida Turistica</a></li>
                <li><a href="consultaPaqueteActividadesTuristicas.jsp">Consulta de Paquete de Actividades Turisticas</a></li>
            </ul>
            <h2>Departamentos</h2>
            <ul>
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
    </body>
    <script>
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