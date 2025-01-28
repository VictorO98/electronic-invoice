package com.example.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller/")
public class FrontControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String actionType = request.getParameter("actionType");

        if ("chargeInvoices".equals(action)) {
            request.setAttribute("activeSection", "send_invoice");
            if ("load".equals(actionType)) {
                System.out.println("Button 'Charge' pressed.");
                request.getRequestDispatcher("/ChargeInvoicesIds").forward(request, response);
            } else if ("loadAll".equals(actionType)) {
                System.out.println("Button 'Charge All' pressed");
            }
        } else {
            request.getRequestDispatcher("/views/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("FrontControllerServlet: Method get not implemented yet.");
    }
}

