<%@page import="logica.Usuario" %>
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
        <p>nombre del user: <%=usu.getNombre()%></p>
        <div class="tabs">
            <ul class="tab-links">
                <li class="active"><a href="#tab1">Perfil</a></li>
                <li><a href="#tab2">Salidas</a></li>
                <li><a href="#tab3">Paquetes</a></li>
                <li><a href="#tab4">Otra Tab</a></li>
            </ul>

            <div class="tab-content">
                <div id="tab1" class="tab active">
                    <p>Tab1</p>
                    <p>Texto1</p>
                </div>

                <div id="tab2" class="tab">
                    <p>Tab2</p>
                    <p>Texto2</p>
                </div>

                <div id="tab3" class="tab">
                    <p>Tab3</p>
                    <p>Texto3</p>
                </div>

                <div id="tab4" class="tab">
                    <p>Tab4</p>
                    <p>Texto4</p>
                </div>
            </div>
        </div>
        <script>
            jQuery(document).ready(function () {
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