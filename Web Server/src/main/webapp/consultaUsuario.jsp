<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Turismo.uy - Consulta de Usuario</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
  
    <header>
        <div id="logo">
            <h1>Turismo.uy</h1>
        </div>
     
    </header>
    
 
    <main>
        <h2>Consulta de Usuario</h2>
        <form action="procesar_consulta_usuario.jsp" method="post">
            <label for="usuario">Selecciona un usuario:</label>
            <select id="usuario" name="usuario">
                <option value="usuario1">Nombre de Usuario 1</option>
                <option value="usuario2">Nombre de Usuario 2</option>
           
            </select>
            <button type="submit">Consultar</button>
        </form>
        
   
        
    </main>
    

    <footer>
        <p>&copy; 2023 Turismo.uy</p>
    </footer>
</body>
</html>
