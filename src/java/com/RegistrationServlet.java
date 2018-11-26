/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CustomerManager;
import static model.CustomerManager.NO_CUSTOMER_FIRST_NAME_ERR_CODE;
import static model.CustomerManager.NO_CUSTOMER_LAST_NAME_ERR_CODE;
import static model.CustomerManager.NO_CUSTOMER_ADDRESS_ERR_CODE;
import model.Jdbc;
import model.UserManager;
import static model.UserManager.NO_PASSWORD_ERR_CODE;
import static model.UserManager.PASSWORDS_DONT_MATCH_ERR_CODE;
import model.tableclasses.GenericItem;


/**
 *
 * @author jakec
 */
//TO DO:
// Take first and last names from booking servlet then create username
// Pass Username and Password onto model
// 
public class RegistrationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long customerId;
        long userId;

        ServletContext sc = request.getServletContext();

        // Go straight to an error page if their where problems connecting to
        // the DB.
        if (sc.getAttribute("dBConnectionError") != null) {
            request.getRequestDispatcher("conErr.jsp").forward(request, response);
        }

        // Connect Jdbc to the DB
        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection) sc.getAttribute("connection"));
        // Values from Booking.jsp
        customerId = CustomerManager.addNewCustomer(
                request.getParameter("firstname"), //whatever alex has named them
                request.getParameter("lastname"),
                request.getParameter("address"),
                dbBean);
        if (customerId == NO_CUSTOMER_FIRST_NAME_ERR_CODE) {
            // Firstname not entered
            String message = "Firstname not entered. Please try again";
            request.setAttribute("errMsg", message + "</br>");
            request.getRequestDispatcher("register.jsp").forward(request, response);

        } else if (customerId == NO_CUSTOMER_LAST_NAME_ERR_CODE) {
            // lastname not entered
            String message = "Lastname not entered. Please try again";
            request.setAttribute("errMsg", message + "</br>");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (customerId == NO_CUSTOMER_ADDRESS_ERR_CODE) {
            // Address not entered
            String message = "Address not entered. Please try again";
            request.setAttribute("errMsg", message + "</br>");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

        userId = UserManager.newCustomerUser(request.getParameter("password"),
                request.getParameter("passwordConfirm"), customerId, new GenericItem(1), dbBean);

        if (userId == NO_PASSWORD_ERR_CODE) {
            // passwords do not match
            String message = "No password given. Please try again";
            request.setAttribute("errMsg", message + "</br>");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (userId == PASSWORDS_DONT_MATCH_ERR_CODE) {
            String message = "No password given. Please try again";
            request.setAttribute("errMsg", message + "</br>");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        //create session for logged in customer user.
        HttpSession session = request.getSession();
            session.setAttribute("userID", userId);
            session.setAttribute("dbbean", dbBean);
            session.setAttribute("userType", new GenericItem(4));
        
        // Registration success!
        String username = UserManager.getUsernameForCustomer(customerId, dbBean);
        request.setAttribute("username", username);
        request.getRequestDispatcher("registerConfirmation.jsp").forward(request, response);

        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
