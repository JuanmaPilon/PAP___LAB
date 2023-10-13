<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Turismo.uy - Perfil de Actividad Turistica</title>
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
            <a href="logedUser.jsp" src="logedUser.jsp">Volver al inicio</a>
        </div>
    </header>
    
    <main>
        <h2>Perfil de Actividad Turistica</h2>
     
        <div class="actividad">
            <img src="imagen_actividad.jpg" alt="Imagen de la actividad">
            <h3>Nombre de la Actividad</h3>
            <p>Descripción de la actividad</p>
            <p>Fecha: Fecha X</p>
            <p>Lugar: Lugar X</p>
            <p>Categorías: Lista X</p>
        </div>
        
      
        <h3>Salidas Asociadas</h3>
        <ul>
            <li><a href="perfilSalidaTuristica.jsp">Salida 1</a></li>
            <li><a href="perfilSalidaTuristica.jsp">Salida X</a></li>
        </ul>
        
        <h3>Paquetes Asociados</h3>
        <ul>
            <li><a href="perfilPaqueteActividades.jsp">Paquete 1</a></li>
            <li><a href="perfilPaqueteActividades.jsp">Paquete X</a></li>
        </ul>
    </main>
    
    <footer>
        <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
        <p>&copy; 2023 Turismo.uy</p>
    </footer>
</body>
</html>
