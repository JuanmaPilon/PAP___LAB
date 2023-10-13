<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="styles.css" rel="stylesheet">
    <title>Turismo.uy - Alta de Salida Turística</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

    <header>
        <div id="logo">
            <h1>Turismo.uy</h1>
        </div>
        <div id="search">
            <form action="svlet" method="get">
                <input type="text" name="query" placeholder="Buscar turistas o paquetes">
                <button type="submit">Buscar</button>
            </form>
        </div>
        <div id="login">
            <a href="logedUser.jsp">Volver a Inicio</a>
        </div>
    </header>

    <main>
        <h2>Alta de Salida Turística</h2>
        <form action="procesarAltaSalida.jsp" method="post" accept-charset="UTF-8">
            <label for="departamento">Seleccionar Departamento:</label>
            <select id="departamento" name="departamento">
      
                <option value="montevideo">Montevideo</option>
                <option value="canelones">Canelones</option>
     
            </select>
            
            <label for="actividadTuristica">Seleccionar Actividad Turística:</label>
            <select id="actividadTuristica" name="actividadTuristica">
     
                <option value="actividad1">Actividad Colonia</option>
                <option value="actividad2">Actividad Rocha</option>
                <option value="actividad2">Carlangas pasame los datos</option>
          
            </select>
            
            <label for="nombreSalida">Nombre de la Salida:</label>
            <input type="text" id="nombreSalida" name="nombreSalida" required>
            
            <label for="fechaHoraSalida">Fecha y Hora de Salida:</label>
            <input type="datetime-local" id="fechaHoraSalida" name="fechaHoraSalida" required>
            
            <label for="lugarSalida">Lugar de Salida:</label>
            <input type="text" id="lugarSalida" name="lugarSalida" required>
            
            <label for="cantidadMaxTuristas">Cantidad Máxima de Turistas:</label>
            <input type="number" id="cantidadMaxTuristas" name="cantidadMaxTuristas" required>
            
            <label for="imagenSalida">Imagen (opcional):</label>
            <input type="file" id="imagenSalida" name="imagenSalida">
            
            <button type="submit">Dar de Alta</button>
        </form>
    </main>

    <footer>
        <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
        <p>&copy; 2023 Turismo.uy</p>
    </footer>

    <script>
        document.querySelector('form').addEventListener('submit', function (event) {
            const cantidadMaxTuristas = document.getElementById('cantidadMaxTuristas').value;
            if (cantidadMaxTuristas < 0) {
                alert('La cantidad máxima de turistas no puede ser negativa');
                event.preventDefault();
            }
        });

// una vez hecha la validacion se puede usar este control para no tener que mandar el formulario.
//         <%
// String cantidadMaxTuristasStr = request.getParameter("cantidadMaxTuristas");
// int cantidadMaxTuristas = Integer.parseInt(cantidadMaxTuristasStr);

// if (cantidadMaxTuristas < 0) {
// %>
//     <p style="color: red;">La cantidad máxima de turistas no puede ser negativa.</p>
// <%
// } else {
//    
//    
// }
// %>
    </script>

</body>
</html>
