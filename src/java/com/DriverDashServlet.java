/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;
import model.tableclasses.Booking;
import model.BookingManager;
import model.Helper;
import model.ReportManager;
import model.UserManager;
import model.tableclasses.GenericItem;
import model.tableclasses.User;

/**
 *
 * @author aj2-spooner
 */
public class DriverDashServlet extends HttpServlet {

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
        Booking[] bookings;
        Jdbc jdbc;
        long loggedInDriverId;
        HttpSession session;
        String todaysBookings;
        User user;
        boolean onlyToday = true;

        processRequest(request, response);

        session = request.getSession(false);
        jdbc = (Jdbc) session.getAttribute("dbbean");
        user = UserManager.getUser((long) session.getAttribute("userID"), jdbc);

        loggedInDriverId = user.getDriverId();

        todaysBookings = request.getParameter("checkToday");

        // Get all bookings for a driver. Bookings can be filtered for today only.
        if (todaysBookings == null || todaysBookings.equals("off")) {
            bookings = BookingManager.getAllDriverBookings(loggedInDriverId, false, jdbc);
            onlyToday = false;
        } else {
            bookings = BookingManager.getAllDriverBookings(loggedInDriverId, true, jdbc);
        }

        String message = "";
        String custName;
        for (Booking booking : bookings) {
            message += "<tr>";
            custName = booking.getCustomer().getFirstName() + " "
                    + booking.getCustomer().getLastName();
            message += "<td>" + custName + "</td>";
            message += "<td>" + booking.getSourceAddress() + "</td>";
            message += "<td>" + booking.getDestinationAddress() + "</td>";
            message += "<td>" + booking.getNumOfPassengers() + "</td>";
            if (onlyToday) {
                message += "<td>" + Helper.formatDateWithOnlyTime(booking.getDepartureTime()) + "</td>";
            } else {
                message += "<td>" + Helper.formatDateWithTime(booking.getDepartureTime()) + "</td>";
            }

            // Arrival time can be null, so handle this.
            if (booking.getTimeArrived() == null) {
                message += "<td>N/A</td>";
            } else {
                if (onlyToday) {
                    message += "<td>" + Helper.formatDateWithOnlyTime(booking.getTimeArrived()) + "</td>";
                } else {
                    message += "<td>" + Helper.formatDateWithTime(booking.getTimeArrived()) + "</td>";
                }
            }

            message += "<td>" + Helper.doubleToTwoDecPlacesString(booking.getFareIncVAT()) + "</td>";

            GenericItem bookingStatus = booking.getBookingStatus();
            if (bookingStatus != null) {
                switch ((int) bookingStatus.getId()) {
                    case 1://In Progress
                        message += "<td><button class=\"btn\" onclick=\"getBooking(this)\""
                        + " data-bookingid=" + booking.getId()
                        + " data-bookingstatus=" + booking.getBookingStatus().getName()
                        + ">In Progress</button></td>";
                        break;
                    case 2://Complete
                        message += "<td><button class=\"btn\" onclick=\"getBooking(this)\""
                        + " data-bookingid=" + booking.getId()
                        + " data-bookingstatus=" + booking.getBookingStatus().getName()
                        + ">Complete</button></td>";
                        break;
                    case 4://Completed
                        message += "<td>" + "Completed" + "</td>";
                        break;
                    case 8://Cancelled
                        message += "<td>" + "<button type=\"button\">Cancelled</button>" + "</td>";
                        break;
                }
            } else {
                message += "<td>ERROR</td>";
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
        processRequest(request, response);

        HttpSession session = request.getSession(false);

        // Connect Jdbc to the DB
        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        long bookingId = Long.parseLong(request.getParameter("bookingid"));
        String bookingStatus = request.getParameter("bookingstatus");

        Booking booking = BookingManager.getBooking(jdbc, bookingId);

        booking.setBookingStatus(new GenericItem(4));
        booking.setArrivalTime(new Timestamp(System.currentTimeMillis()));

        jdbc.update(booking);
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
