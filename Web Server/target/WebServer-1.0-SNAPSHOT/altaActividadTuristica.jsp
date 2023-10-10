<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Turismo.uy - Alta de Actividad Turística</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body onload="cargarDepartamentos()">
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
        <a href="logedUser.jsp" src="logedUser.jsp">Cancelar Alta Actividad</a>
    </div>
</header>

<aside>
    <h2>Departamentos</h2>
    <ul id="departamentoList">
        <!-- La lista de departamentos se llenará aquí -->
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
    <h2>Alta de Actividad Turística</h2>
    <form action="procesar_alta_actividad.jsp" method="post" enctype="multipart/form-data">
        <label for="departamento">Departamento:</label>
        <select id="departamento" name="departamento">
            <!-- Opciones de departamento se llenarán dinámicamente aquí -->
        </select>
        <!-- Resto de los campos del formulario -->
        <label for="nombre">Nombre de la actividad:</label>
        <input type="text" id="nombre" name="nombre" required>
        <label for="descripcion">Descripcion:</label>
        <textarea id="descripcion" name="descripcion" rows="4" required></textarea>
        <label for="duracion">Duración (en horas):</label>
        <input type="number" id="duracion" name="duracion" required>
        <label for="costo">Costo (en pesos uruguayos):</label>
        <input type="number" id="costo" name="costo" required>
        <label for="ciudad">Ciudad:</label>
        <input type="text" id="ciudad" name="ciudad" required>
        <label for="imagen">Imagen (opcional):</label>
        <input type="file" id="imagen" name="imagen">
        <label for="categorias">Categorías asociadas:</label>
        <select id="categorias" name="categorias[]" multiple>
            <option value="categoria1">Categoría 1</option>
            <option value="categoria2">Categoría 2</option>
            <option value="categoria2">Todo dinámico</option>
        </select>
        <!--No se si quieren agregarle un id para traer los datos o que-->
        <button type="submit">Dar de Alta</button>
    </form>
</main>

<footer>
    <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
    <p>&copy; 2023 Turismo.uy</p>
</footer>

<script>
    function cargarDepartamentos() {
        // Usa AJAX para enviar una solicitud al servidor y obtener la lista de departamentos
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "SvDepartamento", true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // Parsea la respuesta JSON y actualiza la lista de departamentos
                var departamentos = JSON.parse(xhr.responseText);
                var select = document.getElementById("departamento");
                var ul = document.getElementById("departamentoList");

                // Llena el select y la lista de departamentos
                for (var i = 0; i < departamentos.length; i++) {
                    var option = document.createElement("option");
                    option.value = departamentos[i];
                    option.text = departamentos[i];
                    select.appendChild(option);

                    var li = document.createElement("li");
                    var a = document.createElement("a");
                    a.href = "#";
                    a.textContent = departamentos[i];
                    li.appendChild(a);
                    ul.appendChild(li);
                }
            }
        };
        xhr.send();
    }
</script>
</body>
</html>
