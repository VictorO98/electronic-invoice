package com.example.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller/")
public class FrontControllerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("sendInvoices".equals(action)) {
            request.getRequestDispatcher("/sendElectronicInvoices").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/index.jsp").forward(request, response);
        }
    }
}

