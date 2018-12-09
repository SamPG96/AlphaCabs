/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BookingManager;
import model.CustomerManager;
import model.Helper;
import model.UserManager;
import model.Jdbc;
import model.ReportManager;
import model.tableclasses.Booking;
import model.tableclasses.Customer;
import model.tableclasses.User;

/**
 *
 * @author jakec
 */
public class CustDashPreviousJourneysServlet extends HttpServlet {

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

        HttpSession session = request.getSession(false);

        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        long userId = (long) session.getAttribute("userID");
        
        User user = UserManager.getUser(userId, jdbc);
        Booking[] bookings = BookingManager.getCustomersPreviousBookings(jdbc, user.getCustomerId());
        
        String message = "";
        for (Booking booking : bookings) {
            message += "<tr>";
            message += "<td>" + booking.getSourceAddress() + "</td>";
            message += "<td>" + booking.getDestinationAddress() + "</td>";
            message += "<td>" + Helper.doubleToTwoDecPlacesString(booking.getDistance()) + "</td>";
            message += "<td>" + booking.getNumOfPassengers() + "</td>";
            message += "<td>" + Helper.formatDateWithTime(booking.getDepartureTime()) + "</td>";
            message += "<td>" + Helper.formatDateWithTime(booking.getArrivalTime()) + "</td>";
            message += "<td>" + Helper.doubleToTwoDecPlacesString(booking.getFareIncVAT()) + "</td>";
            message += "</tr>";
        }
        request.setAttribute("upcomingBookingsTable", message);

        request.getRequestDispatcher("index.jsp").forward(request, response);

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
        HttpSession session = request.getSession(false);

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
