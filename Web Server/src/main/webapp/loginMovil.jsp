<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Turismo.uy Movil</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container-fluid p-5 bg-primary text-white text-center">
  <h1>Turismo.uy</h1>
</div>

<nav class="navbar navbar-dark bg-dark" aria-label="First navbar example">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Bienvenido!</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample01" aria-controls="navbarsExample01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExample01">
      <ul class="navbar-nav me-auto mb-2">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Ver Actividades</a>
          <a class="nav-link active" aria-current="page" href="#">Ver Salidas Turisticas</a>
          <a class="nav-link active" aria-current="page" href="#">Salir</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
    

<div class="container mt-5">
  <div class="row">
    <div class="col-12">
      <h2>Iniciar Sesion</h2>
      <form action="SvAutenticarUsuario" method="GET" onsubmit="return validarFormulario()">
        <div class="mb-3">
          <label for="username" class="form-label">Usuario (nick o mail)</label>
          <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Contraseña</label>
          <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary">Iniciar Sesion</button>
      </form>
    </div>
  </div>
</div>

<div style="background-color:#e5e5e5;text-align:center;padding:10px;margin-top:7px;">Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola - &copy; 2023 Turismo.uy</div>

</body>
<script>
    function validarFormulario() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;

        if (username === "" || password === "") {
            alert("Por favor, complete todos los campos.");
            return false; // Evita que el formulario se envíe si hay campos vacíos.
        }
        // El formulario se enviará al servlet SvBuscarUsuario para la autenticación.
        return true;
    }
</script>
</html>
