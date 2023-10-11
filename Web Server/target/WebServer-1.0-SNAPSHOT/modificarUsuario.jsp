<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="styles.css" rel="stylesheet">
    <title>Turismo.uy - Modificar Usuario</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

    <header>
        <div id="logo">
            <h1>Turismo.uy</h1>
        </div>
        <div id="search">
            <form action="svletCarlngas" method="get">
                <input type="text" name="query" placeholder="Buscar turistas o paquetes">
                <button type="submit">Buscar</button>
            </form>
        </div>
        <div id="login">
            <a href="logedUser.jsp">Volver a inicio</a>
        </div>
    </header>

    <main>
        <h2>Modificar Usuario</h2>

        <form action="svletCarglangas" method="post" accept-charset="UTF-8">

            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required>
            
            <label for="apellido">Apellido:</label>
            <input type="text" id="apellido" name="apellido" required>
     
            <img src="laimagendeliuser" alt="Imagen Actual">

            <label for="imagen">Nueva Imagen (opcional):</label>
            <input type="file" id="imagen" name="imagen">

            <button type="submit">Guardar Cambios</button>
        </form>
    </main>

    <footer>
        <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
        <p>&copy; 2023 Turismo.uy</p>
    </footer>

</body>
</html>
