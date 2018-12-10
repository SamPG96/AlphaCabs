/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import model.DriverManager;
import model.Helper;
import model.tableclasses.Driver;
//import model.UserManagement;
import model.tableclasses.User;

/**
 *
 * @author tc2-buxton
 */
public class AdminDashBookingsServlet extends HttpServlet {


    private Booking assignBooking;
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

        HttpSession session = request.getSession(false);

        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        Driver[] drivers;
        String d_Display;

        if (assignBooking != null) {
            request.setAttribute("assignBooking", assignBooking);
            drivers = DriverManager.getAllAvailableDrivers(jdbc, assignBooking);
            assignBooking = null;
            String driverOptions = "";


          driverOptions += "<option value=\"\"></option>";

          for (Driver driver : drivers) {
              d_Display = "";
              d_Display += driver.getRegistration() + " ";
              d_Display += driver.getFirstName() + " ";
              d_Display += driver.getLastName();

              driverOptions += "<option value = " + d_Display + ">" + d_Display + "</option>";
          }
          request.setAttribute("driverOptions", driverOptions);


            request.getRequestDispatcher("adminDashAssignDriver.jsp").forward(
                    request, response);
        }

        //Resolve checkbox
        String box = request.getParameter("checkOutstanding");
        if (box == null) {
            box = "off";
        }

        Booking[] bookings;
        if (box.equals("on") == true) {
            bookings = BookingManager.getBookings(jdbc, 1);
        } else {
            bookings = BookingManager.getBookings(jdbc);
        }

        drivers = DriverManager.getAllDrivers(jdbc);

        //ASSIGN DRIVER process
        boolean hasUpdated = false;
        if (request.getParameter("assigndriver") != null) {
            for (Booking booking : bookings) {
                if (booking.getDriver() == null) {

                    //Select the driver from the drop down list
                    String selectedDriver = (String) request.getParameter("drivers");

                    //For every driver
                    for (Driver driver : drivers) {
                        if (selectedDriver.equals(driver.getRegistration())) {
                            BookingManager.assignDriver(driver.getId(), booking.getId(), jdbc);
                            hasUpdated = true;
                        }
                    }

                }
            }

            if (hasUpdated) {
                if (box.equals("on") == true) {
                    bookings = BookingManager.getBookings(jdbc, 1);
                } else {
                    bookings = BookingManager.getBookings(jdbc);
                }
            }
        }

        d_Display = "<tr>\n"
                + "\n"
                + "\n"
                + "\n"
                + "</tr>";

        for (Driver driver : drivers) {
            d_Display += "<tr>";
            d_Display += "<td>" + driver.getFirstName() + "</td>";
            d_Display += "<td>" + driver.getLastName() + "</td>";
            d_Display += "<td>" + driver.getRegistration() + "</td>";
        }

        request.setAttribute("availabledrivers", d_Display);

        String message = "";
        String custName;
        String driverName;
        for (Booking booking : bookings) {
            message += "<tr>";
            custName = booking.getCustomer().getFirstName() + " "
                    + booking.getCustomer().getLastName();
            message += "<td>" + custName + "</td>";
            message += "<td>" + booking.getSourceAddress() + "</td>";
            message += "<td>" + booking.getDestinationAddress() + "</td>";
            message += "<td>" + booking.getNumOfPassengers() + "</td>";
            message += "<td>" + Helper.doubleToTwoDecPlacesString(booking.getDistance()) + " </td>";
            message += "<td>" + Helper.doubleToTwoDecPlacesString(booking.getFareExcVAT()) + "</td>";
            message += "<td>" + Helper.doubleToTwoDecPlacesString(booking.getFareIncVAT()) + "</td>";
            message += "<td>" + Helper.formatDateWithTime(booking.getTimeBooked()) + "</td>";
            message += "<td>" + Helper.formatDateWithTime(booking.getDepartureTime()) + "</td>";

            // Arrival time can be null, so handle this.
            if (booking.getTimeArrived() == null) {
                message += "<td>N/A</td>";
            } else {
                message += "<td>" + Helper.formatDateWithTime(booking.getTimeArrived()) + "</td>";
            }

            // Driver ID can be null if no driver assigned, so handle this.
            if (booking.getDriver() == null) {
              message += "<td><button class=\"btn\" onclick=\"getAssignBooking(this)\""
                  + " data-bookingid=" + booking.getId()
                  + ">Assign</button></td>";
            } else {
                driverName = booking.getDriver().getFirstName() + " "
                        + booking.getDriver().getLastName();
                message += "<td>" + driverName + "</td>";
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

        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        long bookingId = Long.parseLong(request.getParameter("bookingid"));
        Booking booking = BookingManager.getBooking(jdbc, bookingId);
        this.assignBooking = booking;
        request.setAttribute("assignBooking", booking);
    }

    //-----------------------------------------------------------------
    public void listDrivers(HttpServletRequest request, HttpServletResponse response)
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
}
