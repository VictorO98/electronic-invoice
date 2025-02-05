package com.example.controllers;

import com.example.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChargeAllInvoices")
public class ChargeAllInvoicesController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String period = request.getParameter("period");

        String message = "Facturas Procesadas Exitosamente.";
        String messageType = "success";

        if (period == null || period.isEmpty()) {
            message = "El campo 'Periodo' es obligatorio.";
            messageType = "error";
        } else {
            int periodInt = Integer.parseInt(period);
            try {
                databaseConnection.getConnection();
                databaseConnection.executeProcedure("{call EB_PROCARGA(?, ?)}", periodInt, -1);
            } catch (Exception e) {
                System.err.println("Error Charge All Invoices: " + e.getMessage());
                message = "Ha ocurrido un error inesperado al procesar la solicitud. Por favor, intente nuevamente más tarde.";
                messageType = "error";
            }
        }

        request.setAttribute("messageCharge", message);
        request.setAttribute("messageTypeCharge", messageType);
        request.setAttribute("activeSection", "send_invoice");

        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
    }
}
