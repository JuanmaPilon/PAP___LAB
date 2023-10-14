<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Turismo.uy - Consulta de Paquete de Actividades Turisticas</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>
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
        <h1>Consulta de Paquete de Actividades Turisticas</h1>

        <div class="actividad">
            <img src="imagen_paquete.jpg" alt="Imagen del paquete">
            <h3>Nombre del Paquete</h3>
            <p>Descripción del paquete</p>
            <p>Duracion del paquete en días</p>
            <p>Precio: $</p>
        </div>

        <h3>Actividades Incluidas en el Paquete</h3>
        <ul>
            <li>
                <a href="perfilActividadTuristica.jsp">Nombre de la Actividad 1</a>
                <p>Descripción de la Actividad 1</p>
            </li>
            <li>
                <a href="perfilActividadTuristica.jsp">Nombre de la Actividad X</a>
                <p>Descripción de la Actividad X</p>
            </li>
        </ul>
    </main>

    <footer>
        <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
        <p>&copy; 2023 Turismo.uy</p>
    </footer>
</body>
</html>
