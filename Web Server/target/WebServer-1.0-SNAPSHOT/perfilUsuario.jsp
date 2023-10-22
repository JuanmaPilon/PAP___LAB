<%@page import="logica.Usuario" %>
<%@page import="logica.Proveedor" %>
<%@page import="logica.Turista" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="styles.css" src="styles.css"">
        <title>Turismo.uy - Reserva de Paquetes Turísticos</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>

    <body>
        <div class="container">

            <header>
                <div id="logo">
                    <h1>Turismo.uy - Informacion de Usuario</h1>
                </div>
                <div id="search">
                    <form action="buscar.jsp" method="post">
                        <input type="text" name="query" placeholder="Buscar turistas o paquetes">
                        <button type="submit">Buscar</button>
                    </form>
                </div>
                <div id="login">
                    <a href="logedUser.jsp" src="logedUser.jsp">Volver al inicio</a>
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
            <%Usuario usu = (Usuario) request.getSession().getAttribute("usuPerfil");//usuario del que se muestra la info
                String rutaImg = (String) request.getSession().getAttribute("imagen");


            %>

            <h1>Usuario: <%=usu.getNombre()%></h1>
            <div class="tabs">
                <ul class="tab-links">
                    <li class="active"><a href="#tab1">Perfil</a></li>
                        <%
                            if (usu instanceof Proveedor) {
                        %>
                    <li><a href="#tab2">Actividades</a></li>
                    <li><a href="#tab3">Salidas</a></li>
                        <%
                            }
                        %>


                    <%
                        if (usu instanceof Turista) {
                            String usuario = (String) request.getSession().getAttribute("usuario");
                            String tur = usu.getNickname();
                            if (tur.equals(usuario)) {
                    %>
                    <li><a href="#tab3">Salidas</a></li>
                    <li><a href="#tab4">Paquetes</a></li>
                        <%
                                }
                            }
                        %>


                </ul>

                <div class="tab-content">
                    <div id="tab1" class="tab active">
                        <img src="<%= rutaImg%>" alt="alt" style="width: 100px; height: 100px;" />
                        <p><b>Nick:</b> <%=usu.getNickname()%></p>
                        <p><b>Nombre:</b> <%=usu.getNombre()%></p>
                        <p><b>Apellido:</b> <%=usu.getApellido()%></p>
                        <p><b>Email:</b> <%=usu.getCorreo()%></p>
                        <p><b>Fecha de Nacimiento:</b> <%=usu.getfNacimiento()%></p>
                    </div>

                    <div id="tab2" class="tab">
                        <ul>
                            <%
                                ArrayList<String> listaActividadesProveedor = (ArrayList<String>) request.getSession().getAttribute("listaActividadesProveedor");

                                if (listaActividadesProveedor != null && !listaActividadesProveedor.isEmpty()) {
                                    for (String nombreActividad : listaActividadesProveedor) {
                                        out.println("<li> <a href='#' onclick='mostrarActividad(\"" + nombreActividad + "\")'>" + nombreActividad + "</a></li>");
                                    }
                                } else {
                                    out.println("<li>No hay salidas disponibles para este proveedor.</li>");
                                }
                            %>
                        </ul>
                    </div>

                    <div id="tab3" class="tab">
                        <ul>
                            <%
                                // Obteniendo la lista de salidas turísticas desde la sesión
                                ArrayList<String> nombresSalidasProveedor = (ArrayList<String>) request.getSession().getAttribute("nombresSalidas");

                                if (nombresSalidasProveedor != null && !nombresSalidasProveedor.isEmpty()) {
                                    for (String nombreSalida : nombresSalidasProveedor) {
                                        out.println("<li> <a href='#' onclick='mostrarSalida(\"" + nombreSalida + "\")'>" + nombreSalida + "</a></li>");
                                    }
                                } else {
                                    out.println("<li>No hay salidas disponibles para este proveedor.</li>");
                                }
                            %>
                        </ul>
                    </div>

                    <div id="tab4" class="tab">
                        <ul>
                            <%
                                // Obteniendo la lista de salidas turísticas desde la sesión
                                ArrayList<String> nombresPaquetes = (ArrayList<String>) request.getSession().getAttribute("nombresPaquetes");

                                // Verificando si la lista no está vacía
                                if (nombresPaquetes != null && !nombresPaquetes.isEmpty()) {
                                    // Iterando sobre la lista y mostrando los nombres de las salidas
                                    for (String nombrePaquete : nombresPaquetes) {
                                        out.println("<li> <a href='#' onclick='mostrarPaquete(\"" + nombrePaquete + "\")'>" + nombrePaquete + "</a></li>");
                                    }
                                } else {
                                    out.println("<li>No hay paquetes comprados.</li>");
                                }
                            %>
                        </ul>
                    </div>


                </div>
            </div>
            <script>
                jQuery(document).ready(function () {
                    jQuery('.tab-links li:first').addClass('active');
                    jQuery('.tab-content .tab:first').show();
                    jQuery('.tab-links a').on('click', function (e) {
                        var currVal = jQuery(this).attr('href');
                        jQuery('.tabs ' + currVal).show().siblings().hide();
                        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');
                        e.preventDefault();
                    });
                });
            </script>





        </main>

        <footer>
            <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
            <p>&copy; 2023 Turismo.uy</p>
        </footer>
    </div>
</body>

<script>

    function mostrarActividad(nombreActividad) {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "SvPerfilActividad?nombreActividad=" + nombreActividad, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                window.location.href = "perfilActividadTuristica.jsp";
            }
        };
        xhr.send();
    }

    function mostrarSalida(nombreSalida) {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "SvPerfilSalida?nombreSalida=" + nombreSalida, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                window.location.href = "perfilSalidaTuristica.jsp";
            }
        };
        xhr.send();
    }

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



</script>
</html>
