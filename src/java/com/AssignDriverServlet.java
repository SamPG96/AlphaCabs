/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BookingManager;
import model.CustomerManager;
import static model.CustomerManager.NO_CUSTOMER_FIRST_NAME_ERR_CODE;
import static model.CustomerManager.NO_CUSTOMER_LAST_NAME_ERR_CODE;
import static model.CustomerManager.NO_CUSTOMER_ADDRESS_ERR_CODE;
import model.DriverManager;
import static model.DriverManager.NO_DRIVER_REGISTRATION_ERR_CODE;
import model.Jdbc;
import model.UserManager;
import static model.UserManager.NO_USER_FIRST_NAME_ERR_CODE;
import static model.UserManager.NO_USER_LAST_NAME_ERR_CODE;
import static model.UserManager.NO_USER_PASSWORD_ERR_CODE;
import static model.UserManager.PASSWORDS_DONT_MATCH_ERR_CODE;
import model.tableclasses.Booking;
import model.tableclasses.Driver;
import model.tableclasses.GenericItem;
import model.tableclasses.User;

/**
 *
 * @author jakec
 */
//TO DO:
// Take first and last names from booking servlet then create username
// Pass Username and Password onto model
// 
public class AssignDriverServlet extends HttpServlet {

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
        int validationCode;
        String errMsgStr;
        Booking booking;
        long bookingId = 0;
        Driver driver = null;
        long driverId;

        ServletContext sc = request.getServletContext();

        // Go straight to an error page if their where problems connecting to
        // the DB. Normally this would be checked by the login servlet, but as
        // the customer hasnt logged in to this point the check is never made.
        if (sc.getAttribute("dBConnectionError") != null) {
            request.getRequestDispatcher("conErr.jsp").forward(request, response);
        }

        // Connect Jdbc to the DB
        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection) sc.getAttribute("connection"));

        String sBookingId = request.getParameter("bookingid");
        if (sBookingId != null) {
            bookingId = Long.parseLong(sBookingId);
        }
        String reg = request.getParameter("driverselector");
        driver = DriverManager.getDriverByReg(reg, dbBean);

        if (bookingId != 0) {//Update
            booking = BookingManager.getBooking(dbBean, bookingId);
            booking.setBookingStatus(new GenericItem(2));
            booking.setDriver(driver);
            dbBean.update(booking);
        }

        response.sendRedirect("index.jsp");

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
