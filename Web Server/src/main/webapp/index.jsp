<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="styles.css" src="styles.css"">
    <title>Turismo.uy - Reserva de Paquetes Turísticos</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

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
        <h2>Departamentos</h2>
        <ul>
            <li><a class="departamento" href="#">Montevideo</a></li>
            <li><a class="departamento" href="#">Canelones</a></li>
            <li><a class="departamento" href="#">Maldonado</a></li>
            <li><a class="departamento" href="#">Colonia</a></li>
            <li><a class="departamento" href="#">Rocha</a></li>

            <h2>Caso de Uso</h2>
            <ul>
                <li><a href="consultaUsuario.jsp">Consulta Usuario</a></li>
                <li><a href="consultaPaqueteActividadesTuristicas.jsp">Consulta Paquete Actividad Turistica</a></li>
            </ul>
       
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

        <section class="actividad">
            <img src="./images/playaFoto.jpg" alt="VisitaPlaya">
            <h3>Visita las Playas</h3>
            <p>Disfruta de nuestras playas en Maldonado.</p>
            <a href="paquete.jsp?id=3">Ver paquetes</a>
        </section>
        
    </main>
    
  
    <footer>
        <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
        <p>&copy; 2023 Turismo.uy</p>
    </footer>
</body>
</html>