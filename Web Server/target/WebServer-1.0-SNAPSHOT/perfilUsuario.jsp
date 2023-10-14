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

        <header>
            <div id="logo">
                <h1>Turismo.uy</h1>
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
        <%Usuario usu = (Usuario) request.getSession().getAttribute("usuPerfil");%>

        <h1>Informacion del Usuario</h1>
        <h2><%=usu.getNombre()%></h2>
        <div class="tabs">
            <ul class="tab-links">
        <li class="active"><a href="#tab1">Perfil</a></li>
        <%
            if (usu instanceof Proveedor) {
        %>
            <li><a href="#tab2">Actividades</a></li>
        <%
            }
        %>
        <li><a href="#tab3">Salidas</a></li>
        <li><a href="#tab4">Paquetes</a></li>
    </ul>

            <div class="tab-content">
                <div id="tab1" class="tab active">
                    <p><b>Nombre:</b> <%=usu.getNickname()%></p>
                    <p><b>Nombre:</b> <%=usu.getNombre()%></p>
                    <p><b>Apellido:</b> <%=usu.getApellido()%></p>
                    <p><b>Email:</b> <%=usu.getCorreo()%></p>
                    <p><b>Fecha de Nacimiento:</b> <%=usu.getfNacimiento()%></p>
                </div>

                <div id="tab2" class="tab">
                    <ul>
                        <%
                        // Obteniendo la lista de salidas turísticas desde la sesión
                        ArrayList<String> listaActividadesProveedorConfirmadas = (ArrayList<String>) request.getSession().getAttribute("listaActividadesProveedorConfirmadas");
                
                        // Verificando si la lista no está vacía
                        if (listaActividadesProveedorConfirmadas != null && !listaActividadesProveedorConfirmadas.isEmpty()) {
                            // Iterando sobre la lista y mostrando los nombres de las salidas
                            for (String nombreActividad : listaActividadesProveedorConfirmadas) {
                                 out.println("<li><a href='#'>" + nombreActividad + "</a></li>");
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
                
                        // Verificando si la lista no está vacía
                        if (nombresSalidasProveedor != null && !nombresSalidasProveedor.isEmpty()) {
                            // Iterando sobre la lista y mostrando los nombres de las salidas
                            for (String nombreSalida : nombresSalidasProveedor) {
                                 out.println("<li><a href='#'>" + nombreSalida + "</a></li>");
                            }
                        } else {
                            out.println("<li>No hay salidas disponibles para este proveedor.</li>");
                        }
                        %>
                    </ul>
                </div>

                <div id="tab4" class="tab">
                    <p>Tab4</p>
                    <p>Texto4</p>
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
</body>
</html>