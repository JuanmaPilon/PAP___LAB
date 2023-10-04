<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="styles.css" src="styles.css"">
    <title>Turismo.uy - Reserva de Paquetes Turísticos</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
    #login-section {
        max-width: 400px; /* Establece el ancho máximo del formulario */
        margin: 0 auto; /* Centra horizontalmente el formulario */
        padding: 20px; /* Añade espacio alrededor del formulario */
        border: 1px solid #ccc; /* Añade un borde alrededor del formulario (opcional) */
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Añade una sombra al formulario (opcional) */
    }
</style>
</head>
<body>

    
    <main>
    <section id="login-section">
        <h2>Iniciar Sesión</h2>
        <form action="procesar_login.jsp" method="post" onsubmit="return validarFormulario()">
            <div class="form-group">
                <label for="username">Nombre de Usuario:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Iniciar Sesión</button>
        </form>
    </section>
   </main>

<script>
    function validarFormulario() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;

        if (username === "" || password === "") {
            alert("Por favor, complete todos los campos.");
            return false; // Evita que el formulario se envíe si hay campos vacíos.
        }
        window.location.href = "logedUser.jsp";
        return false; // Envía el formulario si no hay campos vacíos.
    }
</script>
</html>

 
    
      

    
  
    <footer>
        <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
        <p>&copy; 2023 Turismo.uy</p>
    </footer>
</body>
</html>