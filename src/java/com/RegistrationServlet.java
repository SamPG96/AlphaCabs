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
import model.Jdbc;
import model.UserManagement;
import model.tableclasses.User;

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
        processRequest(request, response);
        
                ServletContext sc = request.getServletContext();
        
        // Go straight to an error page if their where problems connecting to
        // the DB.
        if (sc.getAttribute("dBConnectionError") != null){
            request.getRequestDispatcher("conErr.jsp").forward(request, response);
        }
        
        // Connect Jdbc to the DB
        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection)sc.getAttribute("connection"));
         // Values from Booking.jsp
        long errorCodes = CustomerManager.addNewCustomer (
                request.getParameter("firstname"), //whatever alex has named them
                request.getParameter("lastname"),
                request.getParameter("address"),
                request.getParameter("password"),
                request.getParameter("passwordConfirm"),
                dbBean);

        if (errorCodes == -10) {
            // Firstname not entered
            String message = "Firstname not entered. Please try again";
            request.setAttribute("errMsg", message + "</br>");
            request.getRequestDispatcher("register.jsp").forward(request, response);

        } else if (errorCodes == -11){
            // lastname not entered
            String message = "Lastname not entered. Please try again";
            request.setAttribute("errMsg", message + "</br>");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (errorCodes == -12){
            // Address not entered
            String message = "Address not entered. Please try again";
            request.setAttribute("errMsg", message + "</br>");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (errorCodes == -13){
            // passwords do not match
            String message = "passwords do not match. Please try again";
            request.setAttribute("errMsg", message + "</br>");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        else {
            // Registration success!
            String username = UserManager.generateUsername();
            request.getParameter("Username", username);
            request.getRequestDispatcher("registerConfirmation.jsp").forward(request, response);

        }
        
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
