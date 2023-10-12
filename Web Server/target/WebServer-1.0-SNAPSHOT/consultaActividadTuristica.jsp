<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="styles.css" src="styles.css">
    <title>Turismo.uy - Consulta de Actividad Turistica</title>
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
    
    <main>
        <h2>Consulta de Actividad Turistica</h2>
        <form action="svlet" method="post">
            <label for="departamento">Seleccionar Departamento:</label>
            <select id="departamento" name="departamento">
          
                <option value="montevideo">Montevideo</option>
                <option value="canelones">Canelones</option>
             
            </select>
            
            <label for="categoria">Seleccionar Categoria:</label>
            <select id="categoria" name="categoria">
           
                <option value="aventura">Aventura</option>
                <option value="naturaleza">Naturaleza</option>
             
            </select>
            
            <button type="submit">Consultar</button>
        </form>
        
    
    </main>
    
    <footer>
        <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
        <p>&copy; 2023 Turismo.uy</p>
    </footer>
</body>
</html>
