<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Turismo.uy - Inscripción a Salida Turistica</title>
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
            <a href="logedUser.jsp" src="logedUser.jsp">Volver al inicio</a>
        </div>
    </header>
    
    <main>
        <h2>Inscripción a Salida Turistica</h2>
     
        <form action="procesarInscripcion.jsp" method="post">
            <label for="departamento">Departamento:</label>
            <select name="departamento" id="departamento">
                <option value="departamento1">Departamento 1</option>
                <option value="departamento2">Departamento X</option>
              
            </select>
            
            <label for="categoria">Categoría:</label>
            <select name="categoria" id="categoria">
                <option value="categoria1">Categoria 1</option>
                <option value="categoria2">Categoria X</option>
           
            </select>
            
            <!-- Si esta confirmado -->
            
            <label for="salida">Salida:</label>
            <select name="salida" id="salida">
                <option value="salida1">Salida 1</option>
                <option value="salida2">Salida X</option>
                <!-- Muestra segun cat seleccionada -->
            </select>
            
            <label for="cantidadTuristas">Cantidad de Turistas:</label>
            <input type="number" name="cantidadTuristas" id="cantidadTuristas" min="1">
            
            <label for="formaPago">Forma de Pago:</label>
            <select name="formaPago" id="formaPago">
                <option value="general">Pago General</option>
                <option value="paquete">Pago por Paquete</option>
            </select>
            
          <!-- Pago por paqueteeeee-->
            
            <button type="submit">Inscribirse</button>
        </form>
    </main>
    
    <footer>
        <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
        <p>&copy; 2023 Turismo.uy</p>
    </footer>
</body>
</html>
