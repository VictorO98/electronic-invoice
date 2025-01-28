package com.example.controllers;

import com.example.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ChargeInvoicesIds")
public class ChargeInvoicesIdsController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String period = request.getParameter("period");
        String[] idsInvoices = request.getParameterValues("ids-invoices[]");

        String message = "Facturas Procesadas Exitosamente.";
        String messageType = "success";

        if (period == null || period.isEmpty()) {
            message = "El campo 'Periodo' es obligatorio.";
            messageType = "error";
        } else if (idsInvoices == null || idsInvoices.length == 0) {
            message = "Debes agregar al menos un ID de factura.";
            messageType = "error";
        } else {
            int periodInt = Integer.parseInt(period);

            for (String invoiceId : idsInvoices) {
                int idInvoice = Integer.parseInt(invoiceId);
                try {
                    databaseConnection.getConnection();
                    databaseConnection.executeProcedure("{call EB_PROCARGA(?, ?)}", periodInt, idInvoice);
                } catch (SQLException e) {
                    System.out.println("Database connection error: " + e);
                    message = "Ha ocurrido un error inesperado al procesar la solicitud. Por favor, intente nuevamente más tarde.";
                    messageType = "error";
                } catch (ClassNotFoundException  e) {
                    System.out.println("Oracle JDBC Driver not found: " + e);
                    message = "Ha ocurrido un error inesperado al procesar la solicitud. Por favor, intente nuevamente más tarde.";
                    messageType = "error";
                } catch (Exception e) {
                    System.out.println("Close Connection Exception : " + e);
                    message = "Ha ocurrido un error inesperado al procesar la solicitud. Por favor, intente nuevamente más tarde.";
                    messageType = "error";
                }
            }

        }

        request.setAttribute("messageCharge", message);
        request.setAttribute("messageTypeCharge", messageType);
        request.setAttribute("activeSection", "send_invoice");

        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("ChargeInvoicesIds: Method Get Not Implemented.");
    }
}
