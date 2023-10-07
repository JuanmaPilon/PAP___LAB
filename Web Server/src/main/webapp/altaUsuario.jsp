<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="styles.css" src="styles.css"">
    <title>Turismo.uy - Reserva de Paquetes Turisticos</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
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
        <a href="index.jsp" src="index.jsp">Cancelar Alta Usuario</a>
        </div>
    </header>

    <main>
        <h2>Alta de Usuario</h2>
        <script>
function mostrarCampos() {
    var tipoUsuario = document.getElementById("tipoUsuario").value;
    var camposTurista = document.getElementById("camposTurista");
    var camposProveedor = document.getElementById("camposProveedor");

    if (tipoUsuario === "turista") {
        camposTurista.style.display = "block";
        camposProveedor.style.display = "none";
    } else if (tipoUsuario === "proveedor") {
        camposTurista.style.display = "none";
        camposProveedor.style.display = "block";
    }

    // Cambiar el valor del atributo action del formulario
    var formulario = document.getElementById("formularioRegistro");
    if (tipoUsuario === "turista") {
        formulario.action = "SvTurista";
                } else if (tipoUsuario === "proveedor") {
                    formulario.action = "SvProveedor"; // Cambia "SvProveedor" al servlet correspondiente para proveedores
                }
            }

// Esta función se ejecutará cuando se cargue la página
            window.onload = function () {
                // Llamamos a mostrarCampos() para asegurarnos de que el formulario tenga la acción correcta al cargar la página
                mostrarCampos();
            };
        </script>

        <form id="formularioRegistro" action=SvTurista method="post" accept-charset="UTF-8">
            <label for="nickname">Nickname:</label>
            <input placeholder="JuanitoKpo777" type="text" id="nickname" name="nickname" required>

            <label for="nombre">Nombre:</label>
            <input placeholder="Juan" type="text" id="nombre" name="nombre" required>

            <label for="apellido">Apellido:</label>
            <input placeholder="Sito" type="text" id="apellido" name="apellido" required>

            <label for="contrasenia">Contraseña:</label>
            <input type="password" id="contrasenia" name="contrasenia" required>

            <label for="confirmacion">Confirmar Contrasenia:</label>
            <input type="password" id="confirmacion" name="confirmacion" required>

            <label for="correo">Correo Electronico:</label>
            <input placeholder= "juanitopotter777@sucio.com" type="email" id="correo" name="correo" required>

            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>

            <label for="imagen">Imagen (opcional):</label>
            <input type="file" id="imagen" name="imagen">

            <label for="tipoUsuario">Tipo de Usuario:</label>
            
            <select id="tipoUsuario" name="tipoUsuario" required onchange="mostrarCampos()">
                <option value="turista">Turista</option>
                <option value="proveedor">Proveedor</option>
            </select>

            <!-- Turista -->
            <div id="camposTurista" style="display:none;">
                <label for="nacionalidad">Nacionalidad:</label>
                <input placeholder="Chile" type="text" id="nacionalidad" name="nacionalidad">
            </div>
            
            <!-- Proveedor -->
            <div id="camposProveedor" style="display:none;">
                <label for="descripcion">Descripcion general:</label>
                <textarea placeholder="Escribe aquï¿½ tu descripciï¿½n..." id="descripcion" name="descripcion" rows="4"></textarea>
                
                <label for="sitioWeb">Link a Sitio Web:</label>
                <input type="url" id="sitioWeb" name="sitioWeb">
            </div>
            
            <button type="submit">Registrar</button>
        </form>
    </main>
    
    <footer>
        <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
        <p>&copy; 2023 Turismo.uy</p>
    </footer>


</body>
</html>
