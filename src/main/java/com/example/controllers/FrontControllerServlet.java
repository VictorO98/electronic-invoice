package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller/")
public class FrontControllerServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(FrontControllerServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String actionType = request.getParameter("actionType");

        if ("chargeInvoices".equals(action)) {
            request.setAttribute("activeSection", "send_invoice");
            if ("load".equals(actionType)) {
                logger.info("Load Charge Invoices");
                request.getRequestDispatcher("/ChargeInvoicesIds").forward(request, response);
            } else if ("loadAll".equals(actionType)) {
                logger.info("Load All Charge Invoices");
                request.getRequestDispatcher("/ChargeAllInvoices").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/views/index.jsp").forward(request, response);
        }
    }
}

