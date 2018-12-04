/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;
import model.tableclasses.Booking;
import model.BookingManager;
//import model.UserManagement;
import model.tableclasses.User;

/**
 *
 * @author tc2-buxton
 */
public class AdminDashBookingsServlet extends HttpServlet {

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
        System.out.println("test test 1 2 3");
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

        String x = request.getParameter("checkOutstanding");
        if (x == null) {
            x = "off";
        }

        HttpSession session = request.getSession(false);

        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");
        //Jdbc jdbc = (Jdbc) session.getAttribute("jdbc");

        //DISPLAY All Bookings
        Booking[] bookings;

        if (x.equals("on")) {
            bookings = BookingManager.getBookings(jdbc, 1);
        } else {
            bookings = BookingManager.getBookings(jdbc);
        }

        String message = "<tr>\n"
                + "                    <th>Source address</th>\n"
                + "                    <th>Destination address</th>\n"
                + "                    <th>Passengers</th>\n"
                + "                    <th>Distance</th>\n"
                + "                    <th>Price ex. VAT</th>\n"
                + "                    <th>Price inc. VAT</th>\n"
                + "                    <th>Date</th>\n"
                + "                    <th>Depature time</th>\n"
                + "                    <th>Arrival time</th>\n"
                + "                    <th>Customer lastname</th>\n"
                + "                    <th>Driver</th>\n"
                + "                </tr>";

        for (Booking booking : bookings) {

            message += "<tr>";
            message += "<td>" + booking.getSourceAddress() + "</td>";
            message += "<td>" + booking.getDestinationAddress() + "</td>";
            message += "<td>" + booking.getNumOfPassengers() + "</td>";
            message += "<td>" + booking.getDistanceKM() + " KM</td>";
            message += "<td>£ " + booking.getFareExcVAT() + "</td>";
            message += "<td>£ " + booking.getFareIncVAT() + "</td>";
            message += "<td>" + booking.getTimeBooked() + "</td>";
            message += "<td>" + booking.getDepartureTime() + "</td>";

            // Arrival time can be null, so handle this.
            if (booking.getTimeArrived() == null) {
                message += "<td>N/A</td>";
            } else {
                message += "<td>" + booking.getTimeArrived() + "</td>";
            }

            message += "<td>" + booking.getCustomer().getLastName() + "</td>";

            // Driver ID can be null if no driver assigned, so handle this.
            if (booking.getDriver() == null) {
                message += "<td>Not assigned</td>";
            } else {
                message += "<td>" + booking.getDriver().getLastName() + "</td>";
            }

            message += "</tr>";
        }

        request.setAttribute("bookingsTable", message);

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

    private String BookingsTable() {
        return "Some output";
    }

}
