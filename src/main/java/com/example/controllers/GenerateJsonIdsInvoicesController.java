package com.example.controllers;

import com.example.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GenerateJsonIdsInvoices")
public class GenerateJsonIdsInvoicesController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String period = request.getParameter("period");
        String[] idsInvoices = request.getParameterValues("ids-invoices[]");

        String message = "Json Generado Exitosamente.";
        String messageType = "success";

        if (period == null || period.isEmpty()) {
            message = "El campo 'Periodo' es obligatorio.";
            messageType = "error";
        } else if (idsInvoices == null || idsInvoices.length == 0) {
            message = "Debes agregar al menos un ID de factura.";
            messageType = "error";
        } else {
            try {
                int periodInt = Integer.parseInt(period);
                List<Integer> validInvoices = new ArrayList<>();

                // Validate Ids Invoices
                for (String invoiceId : idsInvoices) {
                    try {
                        int idInvoice = Integer.parseInt(invoiceId.trim());
                        validInvoices.add(idInvoice);
                    } catch (NumberFormatException e) {
                        System.err.println("Error GenerateJsonAllInvoicess: " + e.getMessage());
                    }
                }

                if (validInvoices.isEmpty()) {
                    message = "Todos los IDs de factura ingresados son inválidos. Debes ingresar al menos un ID válido.";
                    messageType = "error";
                } else {
                    for (int idInvoice : validInvoices) {
                        try {
                            databaseConnection.getConnection();
                            databaseConnection.executeProcedure("{call EB_JSON(?, ?, ?)}", 1, periodInt, idInvoice);
                        } catch (Exception e) {
                            System.err.println("Error GenerateJsonAllInvoicess: " + e.getMessage());
                            message = "Ha ocurrido un error inesperado al procesar la solicitud. Por favor, intente nuevamente más tarde.";
                            messageType = "error";
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("Error GenerateJsonAllInvoicess: " + e.getMessage());
                message = "El campo 'Periodo' debe ser un número válido.";
                messageType = "error";
            }
        }

        request.setAttribute("messageJson", message);
        request.setAttribute("messageTypeJson", messageType);
        request.setAttribute("activeSection", "send_invoice");

        request.getRequestDispatcher("/views/index.jsp").forward(request, response);

    }
}
