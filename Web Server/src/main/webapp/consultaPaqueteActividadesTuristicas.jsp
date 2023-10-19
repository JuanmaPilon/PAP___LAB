<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Turismo.uy - Consulta de Paquete de Actividades Turisticas</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body onload="cargarPaquetes()">
    <header>
        <div id="logo">
            <h1>Turismo.uy</h1>
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
        

            <h2>Consulta de Paquetes</h2>
            <form action="SvPerfilPaquete" method="GET" name="perfilPaquete">
                <label for="paquete">Selecciona un paquete</label>
                <select id="paquete" name="paquete">
                    <% ArrayList<String> listaPaquetes = (ArrayList<String>) request.getSession().getAttribute("listaPaquetes");
                        if (listaPaquetes != null) {
                       
                            for (String paquete : listaPaquetes) {%>
                    <option value="<%= paquete%>"><%= paquete%></option>
                    
                    <% }
                        }%>

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
                        .catch(error => console.error("Error al cargar departamentos: " + error));
            }
    
    
</script>    

</html>
