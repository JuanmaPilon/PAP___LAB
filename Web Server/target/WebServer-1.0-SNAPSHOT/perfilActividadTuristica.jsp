<%@page import="logica.SalidaTuristica"%>
<%@page import="logica.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logica.Actividad" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <%
        Actividad act = (Actividad) request.getSession().getAttribute("actividad");//usuario del que se muestra la info
        //ArrayList<String> cat = (ArrayList<String>) request.getSession().getAttribute("categorias");
        ArrayList<Categoria> categorias = act.getListaCategoria();
        ArrayList<SalidaTuristica> salidas = act.getListaSalidaTuristica();
    %>
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
            <div class="actividad">
                <img src="imagen_actividad.jpg" alt="Imagen de la actividad">
                <h3><%=act.getNombre()%></h3>
                <p>Descripcion: <%=act.getDescripcion()%></p>
                <p>Fecha: <%=act.getfAlta()%></p>
                <p>Lugar: <%=act.getCiudad()%></p>
                <p>Categorias: </p>
                <ul>
                    <%
                        if (categorias != null && !categorias.isEmpty()) {
                            for (Categoria categoria : categorias) {
                    %>
                    <li> <a href='#'><%= categoria.getNombre()%></a></li>
                        <%
                                }
                            }
                        %>
                </ul>


            </div>
            <div class="actividad">
                <h3>Salidas Asociadas</h3>
                <ul>
                    <%
                        if (salidas != null && !salidas.isEmpty()) {
                            for (SalidaTuristica salida : salidas) {
                    %>
                    <li><a href='#'><%= salida.getNombre()%></a></li>
                        <%
                            }
                        } else {
                        %>
                    <li>No hay salidas asociadas.</li>
                        <%
                            }
                        %>
                </ul>
            </div>

            <h3>Paquetes Asociados</h3>
            <ul class="list-asociadas">
                <li>
                    <div class="asociada">
                        <img src="paquete1.jpg" alt="Paquete 1">
                        <h4><a href="perfilPaqueteActividades.jsp">Paquete 1</a></h4>
                    </div>
                </li>
                <li>
                    <div class="asociada">
                        <img src="paqueteX.jpg" alt="Paquete X">
                        <h4><a href="perfilPaqueteActividades.jsp">Paquete X</a></h4>
                    </div>
                </li>
            </ul>

        </main>

        <footer>
            <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
            <p>&copy; 2023 Turismo.uy</p>
        </footer>
    </body>
</html>