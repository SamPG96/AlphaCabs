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
public class BookingFormServlet extends HttpServlet {

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
        int bookingIncomplete = BookingManager.generateNewBooking(
                request.getParameter("firstName"), //whatever alex has named these
                request.getParameter("lastName"),
                request.getParameter("source"),
                request.getParameter("sourceequalshome"), // need somesort of tick box on JSP
                request.getParameter("destination"),
                request.getParameter("Date Booked"),
                request.getParameter("passengers"),
                dbBean);

        // Handle result of login attempt
        if (bookingIncomplete == -1) {
            // Login failure!
            String message = "form incomplete. Please try again";
            request.setAttribute("errMsg", message + "</br>");
            request.getRequestDispatcher("Booking.jsp").forward(request, response);

        } else {
            if (loggedInUser ==) {
                //User Logged in
                request.getRequestDispatcher("Invoice.jsp").forward(request, response);
                //pass booking info to Booking Manager?
            } else {
                //User not logged in yet
                request.getRequestDispatcher("bookingIdentity.jsp").forward(request, response);
                //Login and pass booking info to a Session whilst they login?
            }
            //What happens to info that is needed for guest logins?

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
