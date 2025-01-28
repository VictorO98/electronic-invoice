package com.example.controllers;

import com.example.DatabaseConnection;

import java.io.IOException;
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
        String period = request.getParameter("period");
        String[] idsInvoices = request.getParameterValues("ids-invoices[]");

        String message;
        String messageType;

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
                DatabaseConnection.callProcedure(periodInt, idInvoice);
            }
            message = "Facturas Procesadas Exitosamente.";
            messageType = "success";
        }

        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
        request.setAttribute("activeSection", "send_invoice");

        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("ChargeInvoicesIds: Method Get Not Implemented.");
    }
}
