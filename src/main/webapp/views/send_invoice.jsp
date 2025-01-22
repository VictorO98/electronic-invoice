<%--
  Created by IntelliJ IDEA.
  User: vospi
  Date: 17/01/2025
  Time: 1:25 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="send_invoice" class="section">
  <h2>Nuestros Servicios</h2>
  <ul>
    <li>Ingrese la data a cargar:</li>
    <form action="#" method="post">
      <label for="monto">Data:</label>
      <div id="dynamic-fields">
        <div class="input-group">
          <input type="number" id="monto" name="monto[]" placeholder="Ingrese la data" required class="input-large">
        </div>
      </div>
      <button type="button" id="add-field" class="cta-button">+</button>
      <button type="submit" class="cta-button">Cargar Data</button>
    </form>

    <br>

    <li>Seleccione para Generar Json</li>
    <form action="#" method="post">
      <button type="submit" class="cta-button">Generar</button>
    </form>
  </ul>
</div>
