<%--
  Created by IntelliJ IDEA.
  User: vospi
  Date: 17/01/2025
  Time: 1:25 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="send_invoice" class="section">
    <div class="container">
        <h1>Información Procarga Procarga</h1>
        <form action="#" method="post">
            <label>Facturas</label>
            <input type="number" id="periodo-procargas" placeholder="Periodo" required class="input-individual">
            <br>
            <div id="dynamic-fields">
                <div class="input-group">
                    <button type="button" id="add-field" class="add-button">+</button>
                    <input type="number" id="id-facturas-procargas" name="monto[]" placeholder="Id Factura" required class="input-large">
                    <button type="submit" class="cta-button">Cargar</button>
                </div>
            </div>
        </form>
    </div>
</div>
