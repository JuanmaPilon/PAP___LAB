<%@page import="logica.Paquete"%>
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
        ArrayList<Paquete> paquetes = act.getListaPaquete();
        String rutaAlaImagen = (String) request.getSession().getAttribute("imagen");
        
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
                <img src="<%= rutaAlaImagen %>" alt="Imagen de la actividad" style="width: 500px; height: 500px;" />
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
                        // Verificando si la lista no está vacía
                        if (salidas != null && !salidas.isEmpty()) {
                            // Iterando sobre la lista y mostrando los nombres de las salidas
                            for (SalidaTuristica salida : salidas) {
                                out.println("<li> <a href='#' onclick='mostrarSalida(\"" + salida.getNombre() + "\")'>" + salida.getNombre() + "</a></li>");
                            }
                        } else {
                            out.println("<li>No hay salidas asociadas.</li>");
                        }
                    %>
                </ul>
            </div>
            <div class="actividad">
                <h3>Paquetes Asociados</h3>
                <ul>
                    <%
                        // Verificando si la lista no está vacía
                        if (paquetes != null && !paquetes.isEmpty()) {
                            // Iterando sobre la lista y mostrando los nombres de las salidas
                            for (Paquete nombrePaquete : paquetes) {
                                out.println("<li> <a href='#' onclick='mostrarPaquete(\"" + nombrePaquete.getNombre() + "\")'>" + nombrePaquete.getNombre() + "</a></li>");
                            }
                        } else {
                            out.println("<li>No hay paquetes asociados.</li>");
                        }
                    %>
                </ul>
            </div>
                
                    

                   <%-- <img src="images/img1.jpg" alt="alt"/> --%>
        </main>

        <footer>
            <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
            <p>&copy; 2023 Turismo.uy</p>
        </footer>
    </body>
    <script>
        function mostrarPaquete(paquete) {
            var xhr = new XMLHttpRequest();
            console.log("mostrarpaquete");
            xhr.open("GET", "SvPerfilPaquete?paquete=" + paquete, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    window.location.href = "perfilPaquete.jsp";
                }
            };
            xhr.send();
        }
        function mostrarSalida(nombreSalida) {
            var xhr = new XMLHttpRequest();
            console.log("mostrarsalida");
            xhr.open("GET", "SvPerfilSalida?nombreSalida=" + nombreSalida, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    window.location.href = "perfilSalidaTuristica.jsp";
                }
            };
            xhr.send();
        }
    </script>


</html>
