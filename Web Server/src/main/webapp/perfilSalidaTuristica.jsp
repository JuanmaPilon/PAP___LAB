<%@page import="logica.SalidaTuristica"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logica.Actividad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <%
        SalidaTuristica salida = (SalidaTuristica) request.getSession().getAttribute("salida");//usuario del que se muestra la info

    %>
<head>
    <meta charset="UTF-8">
    <title>Turismo.uy - Perfil de Salida Turística</title>
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

        <h2>Categorias</h2>
        <ul>
            <li><a href="#">Aventura y Deporte</a></li>
            <li><a href="#">Campo y Naturaleza</a></li>
            <li><a href="#">Cultura y Patrimonio</a></li>
            <li><a href="#">Gastronomia</a></li>
            <li><a href="#" target="_blank">Turismo Playas</a></li>
        </ul>
    </aside>

  
    
    
    <main>
        <h2>Perfil de Salida Turistica</h2>

        <div class="actividad">
                <img src="imagen_actividad.jpg" alt="Imagen de la actividad">
                <h3><%=salida.getNombre()%></h3>
                <p>Cantidad máxima de turistas <%=salida.getCantMax() %></p>
                <p>Fecha de la Salida <%=salida.getfSalida() %></p>
                <p>Lugar: <%=salida.getLugar() %></p>
                <p>Fecha de Alta: <%=salida.getfAlta() %> </p>
        </div>


    </main>

    <footer>
        <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
        <p>&copy; 2023 Turismo.uy</p>
    </footer>
</body>
</html>
