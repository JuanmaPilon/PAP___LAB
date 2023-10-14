<%@page import="java.util.Locale"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="logica.DTProveedor"%>
<%@page import="logica.DTTurista"%>
<%@page import="logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="styles.css" rel="stylesheet">
        <title>Turismo.uy - Modificar Usuario</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <body>

        <header>
            <div id="logo">
                <h1>Turismo.uy - Modificar mis Datos</h1>
            </div>
            <div id="login">
                <a href="logedUser.jsp">Volver a inicio</a>
            </div>
        </header>
        <main>
            <%
                String usuario = request.getParameter("usuario"); // Para tener el nickname del usuario al acceder a la página
                Usuario usu = (Usuario) request.getSession().getAttribute("usuarioConsulta"); // Usuario devuelto del servlet SvModificarUsuario
                String tipoUsuario = (String) request.getSession().getAttribute("tipoUsuario"); // Usuario: turista o proveedor
                String errorMensaje = (String) request.getAttribute("errorMensaje");
            %>




            <%
                if (tipoUsuario.equals("turista")) {
                    DTTurista infoTurista = (DTTurista) request.getSession().getAttribute("infoTurista");
                    Date fNacimiento = null;
                    String fechaNacimientoString = infoTurista.getfNacimiento();

                    // Realiza la conversión de "dd/MM/yyyy" a "yyyy-MM-dd"
                    SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaNacimientoDate = formatoEntrada.parse(fechaNacimientoString);
                    String fechaFormateada = formatoSalida.format(fechaNacimientoDate);
                    fNacimiento = formatoSalida.parse(fechaFormateada);
                    System.out.print("fNcimiento: " + fNacimiento);
                    System.out.print("fechaFormateada " + fechaFormateada);
            %>   
            <form action="SvModificarUsuario" method="post" id="formularioTurista">

                <label for="TipoUsuario">Tipo Usuario: </label>
                <input type="text" id="TipoUsuario" name="TipoUsuario" value="<%= tipoUsuario%>" readonly>

                <label for="nickname">Nickname</label>
                <input type="text" id="nickname" name="nickname" value="<%= infoTurista.getNickname()%>" readonly>

                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre" value="<%= infoTurista.getNombre()%>">

                <label for="apellido">Apellido</label>
                <input type="text" id="apellido" name="apellido" value="<%= infoTurista.getApellido()%>">

                <label for="correo">Correo</label>
                <input type="email" id="correo" name="correo" value="<%= infoTurista.getCorreo()%>" readonly> 

                <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%= fechaFormateada%>">

                <label for="nacionalidad">Nacionalidad</label>
                <input type="text" id="nacionalidad" name="nacionalidad" value="<%= infoTurista.getNacionalidad()%>">

                <button type="submit">Modificar</button>
            </form>




            <%
            } else {
                DTProveedor infoProveedor = (DTProveedor) request.getSession().getAttribute("infoProveedor");
                Date fNacimiento = null;
                String fechaNacimientoString = infoProveedor.getfNacimiento();

                // Realiza la conversión de "dd/MM/yyyy" a "yyyy-MM-dd"
                SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimientoDate = formatoEntrada.parse(fechaNacimientoString);
                String fechaFormateada = formatoSalida.format(fechaNacimientoDate);
                fNacimiento = formatoSalida.parse(fechaFormateada);
                System.out.print("fNcimiento: " + fNacimiento);
                System.out.print("fechaFormateada " + fechaFormateada);

            %>
            <form action="SvModificarUsuario" method="post" id="formularioProveedor">
                <label for="TipoUsuario">Tipo Usuario: </label>
                <input type="text" id="TipoUsuario" name="TipoUsuario" value="<%= tipoUsuario%>" readonly>

                <label for="nickname">Nickname</label>
                <input type="text" id="nickname" name="nickname" value="<%= infoProveedor.getNickname()%>" readonly>

                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre" value="<%= infoProveedor.getNombre()%>">

                <label for="apellido">Apellido</label>
                <input type="text" id="apellido" name="apellido" value="<%= infoProveedor.getApellido()%>">

                <label for="correo">Correo</label>
                <input type="email" id="correo" name="correo" value="<%= infoProveedor.getCorreo()%>" readonly>  

                <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%= fechaFormateada%>">

                <label for="descripcion">Descripcion</label>
                <input type="text" id="descripcion" name="descripcion" value="<%= infoProveedor.getDescripcion()%>">

                <label for="link">Link a Sitio Web:</label>
                <input type="url" id="sitioWeb" name="sitioWeb" value="<%= infoProveedor.getLink()%>">

                <button type="submit">Modificar</button>
            </form>



            <%
                }
            %>

        </main>

        <footer>
            <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
            <p>&copy; 2023 Turismo.uy</p>
        </footer>

    </body>
</html>