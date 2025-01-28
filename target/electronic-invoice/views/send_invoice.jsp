<%--
  Created by IntelliJ IDEA.
  User: vospi
  Date: 17/01/2025
  Time: 1:25 p. m.
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container-charge">
    <h1>Información Procarga</h1>

    <% if (request.getAttribute("messageCharge") != null) { %>
    <div class="<%= "success".equals(request.getAttribute("messageTypeCharge")) ? "alert-success" : "alert-error" %>">
        <%= request.getAttribute("messageCharge") %>
    </div>
    <% } %>

    <form action="<%= request.getContextPath() %>/controller/" method="post">
        <input type="hidden" name="action" value="chargeInvoices">

        <div class="input-group-individual">
            <input type="number" id="period-charge" name="period" placeholder="Periodo" required class="input-large">
        </div>

        </br>

        <div id="dynamic-fields-charge">
            <div class="input-group">
                <button type="button" id="add-field-charge" class="add-button">+</button>
                <input type="number" id="id-invoices-charges" name="ids-invoices[]" placeholder="Id Factura" class="input-large">
            </div>
        </div>

        <button type="submit" name="actionType" value="load" class="cta-button">Cargar</button>
        <button type="submit" name="actionType" value="loadALl" class="cta-button">Cargar Todo</button>
    </form>
</div>

</br>

<div class="container-json">
    <h1>Generar Json</h1>

    <% if (request.getAttribute("message") != null) { %>
    <div class="<%= "success".equals(request.getAttribute("messageType")) ? "alert-success" : "alert-error" %>">
        <%= request.getAttribute("message") %>
    </div>
    <% } %>

    <form action="<%= request.getContextPath() %>/controller/" method="post">
        <input type="hidden" name="action" value="chargeInvoices">

        <label>Facturas</label>
        <input type="number" id="period-json" name="period" placeholder="Periodo" required class="input-individual">

        <p></p>

        <div id="dynamic-fields-json">
            <div class="input-group">
                <button type="button" id="add-field-json" class="add-button">+</button>
                <input type="number" id="id-invoices-json" name="ids-invoices[]" placeholder="Id Factura" class="input-large">
            </div>
        </div>

        <button type="submit" name="actionType" value="load" class="cta-button">Cargar</button>
        <button type="submit" name="actionType" value="loadAll" class="cta-button">Cargar Todo</button>
    </form>
</div>

