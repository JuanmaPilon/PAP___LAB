<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Turismo.uy - Compra de Paquete</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <header>
        <div id="logo">
            <h1>Turismo.uy</h1>
        </div>
        <div id="search">
            <form action="svletalguno" method="post">
                <input type="text" name="query" placeholder="Buscar turistas o paquetes">
                <button type="submit">Buscar</button>
            </form>
        </div>
        <div id="login">
            <a href="logedUser.jsp" src="logedUser.jsp">Volver al inicio</a>
        </div>
    </header>
    
    <main>
        <h2>Compra de Paquete</h2>
     
        <form action="procesarCompraPaquete.jsp" method="post">
            <label for="paquete">Seleccionar Paquete:</label>
            <select name="paquete" id="paquete">
                <option value="paquete1">Paquete 1</option>
                <option value="paquete2">Paquete XX</option>
              
            </select>
            
            <label for="cantidadTuristas">Cantidad de Turistas:</label>
            <input type="number" name="cantidadTuristas" id="cantidadTuristas" min="1">
            
            <button type="submit">Comprar Paquete</button>
        </form>
    </main>
    
    <footer>
        <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
        <p>&copy; 2023 Turismo.uy</p>
    </footer>
</body>
</html>
