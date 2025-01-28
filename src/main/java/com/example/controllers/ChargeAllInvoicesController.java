package com.example.controllers;

import com.example.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

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
