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
                    <input type="text" name="query" placeholder="Buscar turistas o paquetes">
                    <button type="submit">Buscar</button>
                </form>
            </div>
            <div id="login">
                <%
                    String usuario = (String) request.getSession().getAttribute("usuario");
                    if (usuario != null) {
                %>
                <a href="#" onclick="consultarPerfil('<%= usuario%>')"><%= usuario%></a> | <a href="index.jsp">Cerrar Sesión</a>
                <%
                } else {
                %>
                <a href="login.jsp">Iniciar Sesión</a>
                <%
                    }
                %>
            </div>
        </header>



        <aside>
            <h2>Mi perfil</h2>
            <ul>
                <li><a href="#">Inscripcion a Salida</a></li>
                <li><a href="#">Otro caso de uso</a></li>
                <li><a href="#">Otros...</a></li>
                <li><a href="consultaUsuario.jsp">Consulta Usuario</a></li>
                <li><a href="altaActividadTuristica.jsp">Alta Actividad</a></li>
                <li><a href="modificarUsuario.jsp">Modificareishon mai iuser</a></li>
                <li><a href="altaSalidaTuristica.jsp">Alta Salida Turistica</a></li>
                <li><a href="consultaActividadTuristica.jsp">Consulta Actividad Turistica</a></li>
                <li><a href="perfilActividadTuristica.jsp">Perfil Actividad Turistica</a></li>

            </ul>
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
                <p>Disfruta de nuestras playas en Maldonado</p>
                <a href="paquete.jsp?id=3">Ver paquetes</a>
            </section>


        </main>


        <footer>
            <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
            <p>&copy; 2023 Turismo.uy</p>
        </footer>
    </body>
    <script>
function consultarPerfil(usuario) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "SvPerfilUsuario?usuario=" + usuario, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Haz lo que necesites con la respuesta del servidor, si es necesario
            // Por ejemplo, redirigir a la página de perfil del usuario
            window.location.href = "perfilUsuario.jsp";
        }
    };
    xhr.send();
}
</script>

</html>