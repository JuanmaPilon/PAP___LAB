
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <%      String usuario = (String) request.getSession().getAttribute("usuario");
        %>
        <title>Consulta de Salida Turistica</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body onload="cargarDatos()">
        
        <div class="container-fluid p-5 bg-primary text-white text-center">
            <h1>Turismo.uy - Consulta de Salida Turistica</h1>
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
            <div class="row">
                <div class="col-12">
                    <h2>Seleccionar Departamento o Categoría</h2>
                    <form id="consultaForm" >
                        <div class="mb-3">
                            <label for="departamento" class="form-label">Departamento:</label>
                            <select class="form-select" id="departamento" name="departamento">
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
                        <div class="mb-3">
                            <label for="actividad" class="form-label">Actividades:</label>
                            <select class="form-select" id="actividad" name="actividad">
                            </select>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="cargarSalidasDeActividad()">Cargar Salidas de la Actividad</button>
                        <div class="mb-3">
                            <label for="actividadSalida" class="form-label">Salidas:</label>
                            <select class="form-select" id="actividadSalida" name="actividadSalida">
                            </select>
                        </div>


                        <button id="consultar" type="submit" class="btn btn-primary">Consultar</button>
                    </form>
                </div>
            </div>
        </div>







        <section id="datosSalida" >

        </section>


        <%
            String filtro = "";
        %>

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
            function cargarSalidasDeActividad() {
                //  const filtro = "FiltroSalidasPorNomActividad";
                const actividadSeleccionada = document.getElementById("actividad").value;
                const url = "SvInscripcion?actividad=" + encodeURIComponent(actividadSeleccionada);
                console.log("cargar salidas");
                fetch(url)
                        .then(response => response.text()) // Espera datos de texto
                        .then(data => {
                            const select = document.getElementById("actividadSalida");

                            select.innerHTML = ''; // Limpia las opciones anteriores

                            const actividadSalidas = data.split(",");
                            actividadSalidas.forEach(actividadSalida => {
                                if (actividadSalida) { // Evita opciones vacías
                                    const option = document.createElement("option");
                                    option.value = actividadSalida;
                                    option.text = actividadSalida;
                                    select.appendChild(option);
                                }
                            });
                        })
                        .catch(error => console.error("Error al cargar las salidas: " + error));
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
                console.log("Filtrandopor Depto");

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


            }

            document.addEventListener("DOMContentLoaded", function () {
                // Obtén el formulario de consulta
                var consultaForm = document.getElementById("consultaForm");

                // Agrega un controlador de eventos para el envío del formulario
                consultaForm.addEventListener("submit", function (event) {
                    // Previene el envío del formulario
                    event.preventDefault();

                    // Crea una nueva instancia de XMLHttpRequest
                    var xhr = new XMLHttpRequest();

                    // Obtiene el valor seleccionado del campo de salida turística
                    var actividadSalidaId = document.getElementById("actividadSalida").value;
                    var url = "SvConsultaSalidaMovil?actividadSalidaId=" + actividadSalidaId;
                    console.log("url: " + url);
                    // Realiza una solicitud al servidor para obtener los datos de la salida turística
                    xhr.open("GET", url, true);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            // Manejar la respuesta del servidor y mostrar las actividades en el contenedor
                            var datosSalida = document.getElementById("datosSalida");
                            datosSalida.innerHTML = xhr.responseText;
                        }
                    };
                    xhr.send();
                });
            });



        </script>

        <div style="background-color:#e5e5e5;text-align:center;padding:10px;margin-top:7px;">Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola - &copy; 2023 Turismo.uy</div>

    </body>
</html>
