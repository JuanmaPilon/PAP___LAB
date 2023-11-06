<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Consulta de Salida Turistica</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container-fluid p-5 bg-primary text-white text-center">
  <h1>Consulta de Salida Turística</h1>
</div>

<nav class="navbar navbar-dark bg-dark" aria-label="First navbar example">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Bienvenido, Turist X</a>
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
      <h2>Seleccionar Departamento o Categoría</h2>
      <form action="elsvlet" method="GET">
        <div class="mb-3">
          <label for="departamento" class="form-label">Departamento:</label>
          <select class="form-select" id="departamento" name="departamento">
            <option value="departamento1">Departamento</option>
          </select>
        </div>
        <button type="button" class="btn btn-primary" onclick="filtrarPorDepartamento()">Filtrar por Departamento</button>
        <div class="mb-3">
          <label for="categoria" class="form-label">Categoría:</label>
          <select class="form-select" id="categoria" name="categoria">
            <option value="categoria1">Categoria</option>
          </select>
        </div>
        <button type="button" class="btn btn-primary" onclick="filtrarPorCategoria()">Filtrar por Categoría</button>
        <button type="submit" class="btn btn-primary">Consultar</button>
      </form>
    </div>
  </div>
</div>

<div class="container mt-5">
  <div class="row">
    <div class="col-12">
      <h2>Salidas Turisticas Disponibles</h2>
      <select class="form-select" id="actividad" name="actividad">
      </select>
    </div>
  </div>
</div>


<div class="container mt-5">
  <div class="row">
    <div class="col-12">
      <h2>Detalles de la Salida Turstica</h2>

      <p>Nombre:</p>
      <p>Fecha: </p>
      <p>Lugar:</p>
      <img src="imagen_salida_turistica.jpg" alt="Salida Turística 1">
    </div>
  </div>
</div>

        <script>

            function cargarDepartamentos() {
                fetch("SvDepartamento")
                        .then(response => response.text())
                        .then(data => {
                            const departamentos = data.split(",");
                            const select = document.getElementById("departamento");
                            departamentos.forEach(departamento => {
                                const option = document.createElement("option");
                                option.value = departamento;
                                option.text = departamento;
                                select.appendChild(option);
                            });
                        })
                        .catch(error => console.error("Error al cargar departamentos: " + error));
            }

            function cargarCategorias() {
                fetch("SvCategoria")
                        .then(response => response.text())
                        .then(data => {
                            const categorias = data.split(",");
                            const select = document.getElementById("categoria");
                            categorias.forEach(categoria => {
                                const option = document.createElement("option");
                                option.value = categoria;
                                option.text = categoria;
                                select.appendChild(option);
                            });
                            // Permite múltiples selecciones
                        })
                        .catch(error => console.error("Error al cargar categorías: " + error));
            }



            function filtrarPorDepartamento() {
                const filtro = "FiltroDepartamento";
                const departamentoSeleccionado = document.getElementById("departamento").value;

                // Construir la URL con el valor seleccionado del departamento
                const url = "SvActividad?filtro=" + filtro + "&departamento=" + encodeURIComponent(departamentoSeleccionado);

                fetch(url)
                        .then(response => response.text())
                        .then(data => {
                            const select = document.getElementById("actividad");
                            select.innerHTML = ''; // Limpia las opciones anteriores

                            const actividades = data.split(",");
                            actividades.forEach(actividad => {
                                const option = document.createElement("option");
                                option.value = actividad;
                                option.text = actividad;
                                select.appendChild(option);
                            });
                        })
                        .catch(error => console.error("Error al filtrar por departamento: " + error));
            }


            function filtrarPorCategoria() {
                const filtro = "FiltroCategoria";
                const categoriaSeleccionada = document.getElementById("categoria").value;

                // Construir la URL con el valor seleccionado de la categoría
                const url = "SvActividad?filtro=" + filtro + "&categoria=" + encodeURIComponent(categoriaSeleccionada);

                fetch(url)
                        .then(response => response.text())
                        .then(data => {
                            const select = document.getElementById("actividad");
                            select.innerHTML = ''; // Limpia las opciones anteriores

                            const actividades = data.split(",");
                            actividades.forEach(actividad => {
                                const option = document.createElement("option");
                                option.value = actividad;
                                option.text = actividad;
                                select.appendChild(option);
                            });
                        })
                        .catch(error => console.error("Error al filtrar por categoría: " + error));
            }



            function cargarDatos() {
                cargarDepartamentos();
                cargarCategorias();

                const consultaForm = document.getElementById("consultaForm");

                consultaForm.addEventListener("submit", function (event) {
                    const actividadSeleccionada = document.getElementById("actividad").value;
                    if (actividadSeleccionada === "No hay actividades disponibles para esta categoria" || actividadSeleccionada === "No hay actividades disponibles para este departamento" || actividadSeleccionada === "" || actividadSeleccionada === null) {
                        event.preventDefault(); // Prevenir el envío del formulario
                        alert("Por favor, selecciona una actividad válida."); // Mostrar mensaje de error
                    }
                });
            }
        </script>

<div style="background-color:#e5e5e5;text-align:center;padding:10px;margin-top:7px;">Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola - &copy; 2023 Turismo.uy</div>

</body>
</html>
