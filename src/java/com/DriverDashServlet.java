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
        processRequest(request, response);

        HttpSession session = request.getSession(false);

        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        Booking[] bookings = BookingManager.getBookings(jdbc);

        User user = UserManager.getUser((long) session.getAttribute("userID"), jdbc);
        long loggedInDriver = 0;
        loggedInDriver = user.getDriverId();

        String x = request.getParameter("checkToday");
        if (x == null) {
            x = "off";
        }

        if (x.equals("on")) {
            ReportManager repMan = new ReportManager(jdbc);
            bookings = repMan.getTodaysBookings();
        }

        String message = "";
        String custName;
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
                message += "<td>" + Helper.formatDateWithTime(booking.getDepartureTime()) + "</td>";

                // Arrival time can be null, so handle this.
                if (booking.getTimeArrived() == null) {
                    message += "<td>N/A</td>";
                } else {
                    message += "<td>" + Helper.formatDateWithTime(booking.getTimeArrived()) + "</td>";
                }

                message += "<td>" + Helper.doubleToTwoDecPlacesString(booking.getFareIncVAT()) + "</td>";

                GenericItem bookingStatus = booking.getBookingStatus();
                if (bookingStatus != null) {
                    switch((int) bookingStatus.getId()){
                        case 1://In Progress
                            message += "<td>" + "<input type=\"submit\" name=\"" + booking.getId() + "\" value=\"In Progress\"/>" + "</td>";
                            break;
                        case 2://Complete
                            message += "<td>" + "<input type=\"submit\" name=\"" + booking.getId() + "\" value=\"Complete\"/>" + "</td>";
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
