<%-- Created by IntelliJ IDEA. User: vospi Date: 17/01/2025 Time: 1:25 p. m. To change this template use File | Settings
    | File Templates. --%>

    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

        <style>
            .loading-overlay {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.5);
                z-index: 9999;
                justify-content: center;
                align-items: center;
            }

            .loading-spinner {
                border: 4px solid #f3f3f3;
                border-top: 4px solid #3498db;
                border-radius: 50%;
                width: 40px;
                height: 40px;
                animation: spin 1s linear infinite;
            }

            @keyframes spin {
                0% {
                    transform: rotate(0deg);
                }

                100% {
                    transform: rotate(360deg);
                }
            }

            /* Estilos de alertas */
            .mensaje-exito {
                color: #155724 !important;
                background-color: #d4edda !important;
                border: 1px solid #c3e6cb !important;
                padding: 15px !important;
                margin: 10px auto !important;
                border-radius: 5px !important;
                max-width: 80% !important;
                text-align: center !important;
            }

            .mensaje-error {
                color: #721c24 !important;
                background-color: #f8d7da !important;
                border: 1px solid #f5c6cb !important;
                padding: 15px !important;
                margin: 10px auto !important;
                border-radius: 5px !important;
                max-width: 80% !important;
                text-align: center !important;
            }
        </style>

        <div class="loading-overlay" id="loadingOverlay">
            <div class="loading-spinner"></div>
        </div>

        <div class="container-charge">
            <h1>Información Procarga</h1>

            <% if (request.getAttribute("messageCharge") !=null) { String messageType=(String)
                request.getAttribute("messageTypeCharge"); boolean isSuccess="success" .equals(messageType); %>
                <!-- Debug info -->
                <div style="display:none">
                    Message Type: <%= messageType %><br>
                        Is Success: <%= isSuccess %>
                </div>

                <div class="<%= isSuccess ? " mensaje-exito" : "mensaje-error" %>">
                    <%= request.getAttribute("messageCharge") %>
                </div>
                <% } %>

                    <form action="<%= request.getContextPath() %>/controller/" method="post">
                        <input type="hidden" name="action" value="chargeInvoices">

                        <div class="input-group-individual">
                            <input type="number" id="period-charge" name="period" placeholder="Periodo" required
                                class="input-large">
                        </div>

                        </br>

                        <div id="dynamic-fields-charge">
                            <div class="input-group">
                                <button type="button" id="add-field-charge" class="add-button">+</button>
                                <input type="number" id="id-invoices-charges" name="ids-invoices[]"
                                    placeholder="Id Factura" class="input-large">
                            </div>
                        </div>

                        <button type="submit" name="actionType" value="load" class="cta-button">Cargar</button>
                        <button type="submit" name="actionType" value="loadAll" class="cta-button">Cargar Todo</button>
                    </form>
        </div>

        </br>

        <div class="container-json">
            <h1>Generar Json</h1>

            <% if (request.getAttribute("messageJson") !=null) { String messageTypeJson=(String)
                request.getAttribute("messageTypeJson"); boolean isSuccessJson="success" .equals(messageTypeJson); %>
                <!-- Debug info -->
                <div style="display:none">
                    Message Type: <%= messageTypeJson %><br>
                        Is Success: <%= isSuccessJson %>
                </div>

                <div class="<%= isSuccessJson ? " mensaje-exito" : "mensaje-error" %>">
                    <%= request.getAttribute("messageJson") %>
                </div>
                <% } %>

                    <form action="<%= request.getContextPath() %>/controller/" method="post">
                        <input type="hidden" name="action" value="generateJson">

                        <div class="input-group-individual">
                            <input type="number" id="period-json" name="period" placeholder="Periodo" required
                                class="input-individual">
                        </div>

                        </br>

                        <div id="dynamic-fields-json">
                            <div class="input-group">
                                <button type="button" id="add-field-json" class="add-button">+</button>
                                <input type="number" id="id-invoices-json" name="ids-invoices[]"
                                    placeholder="Id Factura" class="input-large">
                            </div>
                        </div>

                        <button type="submit" name="actionType" value="load" class="cta-button">Cargar</button>
                        <button type="submit" name="actionType" value="loadAll" class="cta-button">Cargar Todo</button>
                    </form>
        </div>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const forms = document.querySelectorAll('form');
                const loadingOverlay = document.getElementById('loadingOverlay');

                forms.forEach(form => {
                    form.addEventListener('submit', function () {
                        loadingOverlay.style.display = 'flex';
                    });
                });
            });
        </script>