<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="styles.css" src="styles.css"">
        <title>Turismo.uy - Reserva de Paquetes Turísticos</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <body onload="cargarUsuarios()">

        <header>
            <div id="logo">
                <h1>Turismo.uy</h1>
            </div>
            <div id="search">
                <form action="buscar.jsp" method="post">
                    <input type="text" name="query" placeholder="Buscar turistas o paquetes">
                    <button type="submit">Buscar</button>
                </form>
            </div>
            <div id="login">
                <a href="logedUser.jsp" src="logedUser.jsp">Cancelar Consulta Usuario</a>
            </div>
        </header>


        <aside>
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
                <li><a href="#">Gastronomia</a></li>
                <li><a href="#" target="_blank">Turismo Playas</a></li>




            </ul>
        </aside>

        <main>
            <h2>Consulta de Usuario</h2>
            <form action="SvPerfilUsuario" method="GET" name="cmbUsuarios">
                <label for="usuario">Selecciona un usuario:</label>
                <select id="usuario" name="usuario">
                    <% List<String> listaUsuarios = (List<String>) request.getAttribute("listaUsuarios");
                        if (listaUsuarios != null) {
                            for (String usuario : listaUsuarios) {%>
                    <option value="<%= usuario%>"><%= usuario%></option>
                    <% }
                        }%>

                </select>
                <button type="submit">Consultar</button>
            </form>

        </main>
        <script>
            // Función para cargar usuarios en el select
            function cargarUsuarios() {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "SvUsuario", true);
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
    </body>
</html>