<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Facturación Electrónica</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <script>
        const activeSection = "<%= request.getAttribute("activeSection") != null ? request.getAttribute("activeSection") : "" %>";
    </script>
</head>

<body>
    <header>
        <div class="container">
            <h1>Facturación Electrónica</h1>
            <div class="menu-toggle" onclick="toggleMenu()">
                <span></span>
                <span></span>
                <span></span>
            </div>
            <nav id="menu">
                <a href="#" onclick="showSection('home')">Inicio</a>
                <a href="#" onclick="showSection('send_invoice')">Envío Fáctura Electrónica</a>
            </nav>
        </div>
    </header>

    <main>
        <div id="home" class="section active">
            <h2>Bienvenido a la aplicación de Facturación Electrónica</h2>
            <p>Esta es la plataforma que permite emitir facturas electrónicas de manera sencilla y eficiente.</p>
        </div>

        <%--Envio de Factura electronica--%>
        <div id="send_invoice" class="section">
            <jsp:include page="send_invoice.jsp" />
        </div>
    </main>

    <footer>
        <p>&copy; 2025 Facturación Electrónica - Todos los derechos reservados</p>
    </footer>

    <script src="<%= request.getContextPath() %>/assets/js/scripts.js"></script>

</body>

</html>