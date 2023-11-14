
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
   <%  String usuario = (String) request.getSession().getAttribute("usuario"); 
   request.getSession().setAttribute("errorMensaje", "");
   %>
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
      <a class="navbar-brand" href="#">Bienvenido, <%=usuario%></a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample01" aria-controls="navbarsExample01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample01">
        <ul class="navbar-nav me-auto mb-2">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="consultaActividadTuristicaMovil.jsp">Ver Actividades</a>
			<a class="nav-link active" aria-current="page" href="consultaSalidaTuristicaMovil.jsp">Ver Salidas Turisticas</a>
			<a class="nav-link active" aria-current="page" href="loginMovil.jsp">Salir</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
    
  <div class="container mt-5">
      <h1>BIENVENIDO! ;) </h1>
</div>  
    

<div style="background-color:#e5e5e5;text-align:center;padding:10px;margin-top:7px;">Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola - &copy; 2023 Turismo.uy</div>


</body>
</html>
