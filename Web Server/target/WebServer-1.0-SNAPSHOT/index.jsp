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
                <%
                    if (listaDepartamentos != null && !listaDepartamentos.isEmpty()) {
                        for (Departamento d : listaDepartamentos) {
                            out.println("<li> <a href='#'>" + d.getNombre() + "</a></li>");
                        }
                    } else {
                        out.println("<li>No hay departamentos con actividades confirmadas.</li>");
                    }
                %>
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