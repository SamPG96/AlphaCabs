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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import model.Helper;
import model.ReportManager;
import model.UserManager;
import model.tableclasses.GenericItem;
//import model.UserManagement;
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
        processRequest(request, response);

        HttpSession session = request.getSession(false);

        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");
        //Jdbc jdbc = (Jdbc) session.getAttribute("jdbc");

        Booking[] bookings = BookingManager.getBookings(jdbc);

        User user = UserManager.getUser((long) session.getAttribute("userID"), jdbc);
        long loggedInDriver = 0;
        loggedInDriver = user.getDriverId();
        
        String x = request.getParameter("checkToday");
        if (x == null) {
            x = "off";
        }
        
        if(x.equals("on")){
            ReportManager repMan = new ReportManager(jdbc);
            bookings = repMan.getTodaysBookings();
        }
        
        String message = "";
        String custName;
        double roundOffPostVAT;
        for (Booking booking : bookings) {
            if (booking.getDriver() == null) {
                continue;
            }
            
            if (loggedInDriver == booking.getDriver().getId()) {
                message += "<tr>";
                custName = booking.getCustomer().getFirstName() + " "
                    + booking.getCustomer().getLastName();
                message += "<td>" + custName + "</td>";
                message += "<td>" + booking.getSourceAddress() + "</td>";
                message += "<td>" + booking.getDestinationAddress() + "</td>";
                message += "<td>" + booking.getNumOfPassengers() + "</td>";
                message += "<td>" + booking.getDepartureTime() + "</td>";
                
                // Arrival time can be null, so handle this.
                if (booking.getTimeArrived() == null) {
                    message += "<td>N/A</td>";
                } else {
                    message += "<td>" + booking.getTimeArrived() + "</td>";
                }

                roundOffPostVAT = Math.round(booking.getFareIncVAT() * 100.0) / 100.0;
                message += "<td>" + Helper.doubleToTwoDecPlacesString(roundOffPostVAT) + "</td>";
                
                GenericItem bookingStatus = booking.getBookingStatus();
                if (bookingStatus == null) {
                    message += "<td>N/A</td>";

                } else {

                    if (bookingStatus.getId() == 8) {

                        message += "<td>" + "<button type=\"button\">Cancelled</button>" + "</td>";

                    }

                    if (bookingStatus.getId() == 4) {

                        message += "<td>" + "Completed" + "</td>";

                    }
                    if (bookingStatus.getId() == 2) {

                        message += "<td>" + "<input type=\"submit\" name=\"" + booking.getId() + "\" value=\"Complete\"/>" + "</td>";

                    }
                    if (bookingStatus.getId() == 1) {

                        message += "<td>" + "<input type=\"submit\" name=\"" + booking.getId() + "\" value=\"In Progress\"/>" + "</td>";

                    }
                }
                message += "</tr>";
            }
        }
        request.setAttribute("bookingsTable", message);
        
//        request.setAttribute("bookingsTable", message + "</br>");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
//JDBC.update 

        // <input type="submit" name="Complete" value="delete"/>
//<input type="submit" name="In Progress" value="update"/>
        for (Booking booking : bookings) {

            long i = booking.getId();

            String j = Long.toString(i);
            String status = request.getParameter(j);

            GenericItem bookingStatus = booking.getBookingStatus();

            if (status != null) {
                System.out.println("button not null");
//                bookingStatus.setId(4);
//              jdbc.update(booking); //4
//              
//              request.getRequestDispatcher("/login.jsp").forward(request, response);
                if (status.equals("Complete")) {
                    bookingStatus.setId(4);
                    booking.setBookingStatus(bookingStatus);

                    //TIMESTAMP OF NOW
                    booking.setArrivalTime(new Timestamp(System.currentTimeMillis()));
                    jdbc.update(booking); //4

                } else if (status.equals("In Progress")) {

                    bookingStatus.setId(2);
                    booking.setBookingStatus(bookingStatus);

                    jdbc.update(booking);

                }

            }

        }

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
