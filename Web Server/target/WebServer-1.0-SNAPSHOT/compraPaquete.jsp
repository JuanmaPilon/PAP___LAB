<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Turismo.uy - Compra de Paquete</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <body onload="cargarPaquetes()">
        <header>
            <div id="logo">
                <h1>Turismo.uy - Compra de Paquete</h1>
            </div>
            <div id="login">
                <a href="logedUser.jsp" src="logedUser.jsp">Volver al inicio</a>
            </div>
        </header>

        <main>
            
            <%
                String usuario = request.getParameter("usuario"); 
            %>

            <form id="consultaPaqueteForm" action="SvPaquete" method="POST">
                
                <input type="hidden" id="usuario" name="usuario" value="<%= usuario%>">
                
                <label for="paquete">Paquetes:</label>
                <select id="paquete" name="paquete"></select>
                
                <label for="cantTuristas">Cantidad Turistas:</label>
                <input type="number" id="cantTuristas" name="cantTuristas">

                <button type="submit">Comprar Paquete</button>
            </form>
        </main>

       <script>

            function cargarPaquetes(){
                fetch("SvPaquete?actividades=true")
                        .then(response => response.text())
                        .then(data => {
                            const paquetesConActividadRespond = data.split(",");
                            const select = document.getElementById("paquete");
                            paquetesConActividadRespond.forEach(paquete => {
                                const option = document.createElement("option");
                                option.value = paquete;
                                option.text = paquete;
                                select.appendChild(option);
                            });
                        })
                        .catch(error => console.error("Error al cargar departamentos: " + error));
            }

        </script>    

        <footer>
            <p>Creado por Juan Martin Pilon - Carlos Santana - Natalia Lopez - Santiago Badiola</p>
            <p>&copy; 2023 Turismo.uy</p>
        </footer>
    </body>
</html>

