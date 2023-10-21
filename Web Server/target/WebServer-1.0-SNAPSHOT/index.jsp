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
    </head>
    <body >
        <%
            ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>) request.getSession().getAttribute("listaCategorias");

            ArrayList<Departamento> listaDepartamentos = (ArrayList<Departamento>) request.getSession().getAttribute("listaDepartamentos");
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
                <a href="login.jsp">Iniciar Sesión</a> | <a href="altaUsuario.jsp">Alta Usuario</a>
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
                <ul>
                    <%
                        if (listaDepartamentos != null && !listaDepartamentos.isEmpty()) {
                            for (Departamento d : listaDepartamentos) {
                                out.println("<li> <a href='#' id='" + d.getNombre() + "'>" + d.getNombre() + "</a></li>");
                            }
                        } else {
                            out.println("<li>No hay departamentos con actividades confirmadas.</li>");
                        }
                    %>
                </ul>

            </ul>

            <h2>Categorías</h2>
            <ul>
                <%
                    if (listaCategorias != null && !listaCategorias.isEmpty()) {
                        for (Categoria c : listaCategorias) {
                            out.println("<li> <a href='#'>" + c.getNombre() + "</a></li>");
                        }
                    } else {
                        out.println("<li>No hay categorias con actividades confirmadas.</li>");
                    }
                %>

            </ul>
        </aside>


        <main>
            <section class="actividad">
                <img src="./images/paseoColonia.jpg" alt="PaseoColonia">
                <h3>Paseo por Colonia</h3>
                <p>Disfruta de nuestros paseos en Colonia.</p>
                <a href="paquete.jsp?id=1">Ver paquetes</a>
            </section>

            <section class="actividad">
                <img src="./images/aventuraActividad.jpg" alt="Aventura">
                <h3>Excursiones de Aventura</h3>
                <p>Vive emocionantes aventuras en la naturaleza de Uruguay.</p>
                <a href="paquete.jsp?id=2">Ver paquetes</a>
            </section>

            <section id="actividadesContainer" class="actividadesContainer">

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

            // Obtener todos los enlaces de departamentos
            var links = document.querySelectorAll('ul li a');

            // Iterar sobre los enlaces y agregar un controlador de eventos para el clic
            links.forEach(function (link) {
                link.addEventListener("click", function (event) {
                    event.preventDefault();

                    // Obtener el id del departamento seleccionado
                    var departamentoId = link.id;
                    console.log("Departamento clickeado: "+departamentoId);

                    // Realizar una solicitud al servidor para obtener las actividades del departamento seleccionado
                    var xhr = new XMLHttpRequest();
                    var url = "SvObtenerActividades?departamentoId=" + departamentoId;
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