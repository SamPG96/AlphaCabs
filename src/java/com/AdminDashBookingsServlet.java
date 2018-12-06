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
        Driver[] drivers;

        String displayDrivers = null;

        //request.getSession().setAttribute("displayMode", 1);
        //String displayMode = request.getSession().getAttribute("displayMode").toString();
        //-----------------------------------------------------------------------------------------
        if (x.equals("on") == true) {
            bookings = BookingManager.getBookings(jdbc, 1);
            drivers = DriverManager.getAllDrivers(jdbc);
            //----------------------------------------------------
            /*
            drivers = DriverManager.getAllDrivers(jdbc);

            for (Driver driver : drivers) {
                displayDrivers += "<td>" + driver.getFirstName() + "</td>";
                displayDrivers += "<td>" + driver.getLastName() + "</td>";
                displayDrivers += "<td>" + driver.getRegistration() + "</td>";
            }
             */
            String d_Display = "<tr>\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "</tr>";

            String message = "<tr>\n"
                    + "                    <th>Source address</th>\n"
                    + "                    <th>Destination address</th>\n"
                    + "                    <th>Passengers</th>\n"
                    + "                    <th>Distance (Miles)</th>\n"
                    + "                    <th>Price ex. VAT (£)</th>\n"
                    + "                    <th>Price inc. VAT (£)</th>\n"
                    + "                    <th>Date</th>\n"
                    + "                    <th>Depature time</th>\n"
                    + "                    <th>Arrival time</th>\n"
                    + "                    <th>Customer lastname</th>\n"
                    + "                    <th>Driver</th>\n"
                    + "                </tr>";

            for (Driver driver : drivers) {
                d_Display += "<tr>";
                d_Display += "<td>" + driver.getFirstName() + "</td>";
                d_Display += "<td>" + driver.getLastName() + "</td>";
                d_Display += "<td>" + driver.getRegistration() + "</td>";
            }

            request.setAttribute("availabledrivers", d_Display);

            for (Booking booking : bookings) {

                message += "<tr>";
                message += "<td>" + booking.getSourceAddress() + "</td>";
                message += "<td>" + booking.getDestinationAddress() + "</td>";
                message += "<td>" + booking.getNumOfPassengers() + "</td>";
                message += "<td>" + booking.getDistance() + " </td>";
                message += "<td>" + Helper.doubleToCurrencyFormat(booking.getFareExcVAT()) + "</td>";
                message += "<td>" + Helper.doubleToCurrencyFormat(booking.getFareIncVAT()) + "</td>";
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

                    message += "<td><select name='drivers'><option value=\"\"></option>";
                    for (Driver driver : drivers) {
                        d_Display = "";
                        d_Display += driver.getFirstName() + " ";
                        d_Display += driver.getLastName() + " ";
                        d_Display += driver.getRegistration();

                        message += "<option value = " + d_Display + ">" + d_Display + "</option>";

                    }
                    message += "</select></td>";

                } else {
                    message += "<td>" + booking.getDriver().getLastName() + "</td>";
                }

                message += "</tr>";
            }

            request.setAttribute("bookingsTable", message);
            //request.getRequestDispatcher("index.jsp").forward(request, response);

            //---------------------------------------------------------------------------------
        } else if (x.equals("off") == true) {

            bookings = BookingManager.getBookings(jdbc);
            drivers = DriverManager.getAllDrivers(jdbc);

            //ArrayList<String> d_Display = new ArrayList<String>();
            //Map<Integer, String> d_Display = new HashMap<Integer, String>();
            //for (Driver driver : drivers) {
            //   put(1, driver.getFirstName() driver.getLastName())
            //}
            //for (Driver driver : drivers) {
            //    d_Display.add(driver.getFirstName());
            //    d_Display.add(driver.getLastName());
            //    d_Display.add(driver.getRegistration());
            //}
            String d_Display = "";
//            String d_Display = "<tr>\n"
//                    + "\n"
//                    + "\n"
//                    + "\n"
//                    + "</tr>";

            String message = "<tr>\n"
                    + "                    <th>Source address</th>\n"
                    + "                    <th>Destination address</th>\n"
                    + "                    <th>Passengers</th>\n"
                    + "                    <th>Distance (Miles)</th>\n"
                    + "                    <th>Price ex. VAT (£)</th>\n"
                    + "                    <th>Price inc. VAT (£)</th>\n"
                    + "                    <th>Date</th>\n"
                    + "                    <th>Depature time</th>\n"
                    + "                    <th>Arrival time</th>\n"
                    + "                    <th>Customer lastname</th>\n"
                    + "                    <th>Driver</th>\n"
                    + "                </tr>";

//            for (Driver driver : drivers) {
//                d_Display += driver.getFirstName();
//                d_Display += driver.getLastName();
//                d_Display += driver.getRegistration();
//            }
            request.setAttribute("availabledrivers", d_Display);

            for (Booking booking : bookings) {

                message += "<tr>";
                message += "<td>" + booking.getSourceAddress() + "</td>";
                message += "<td>" + booking.getDestinationAddress() + "</td>";
                message += "<td>" + booking.getNumOfPassengers() + "</td>";
                message += "<td>" + booking.getDistance() + " </td>";
                message += "<td>" + Helper.doubleToCurrencyFormat(booking.getFareExcVAT()) + "</td>";
                message += "<td>" + Helper.doubleToCurrencyFormat(booking.getFareIncVAT()) + "</td>";
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

                    message += "<td><select name='drivers'><option value=\"\"></option>";
                    for (Driver driver : drivers) {
                        d_Display = "";
                        d_Display += driver.getRegistration()+ " ";
                        d_Display += driver.getFirstName()+ " ";
                        d_Display += driver.getLastName();

                        message += "<option value = " + d_Display + ">" + d_Display + "</option>";

                    }
                    message += "</select></td>";

                    //message += "<td><button onclick=\"getid(this)\" name=" + booking.getId() + ">Assign Driver</button></td>";
                    // message += "<td><select name='drivers'><option value = 'availabledrivers'>" + d_Display + "</option></select></td>";
                    //message += "<td>"
                } else {
                    message += "<td>" + booking.getDriver().getLastName() + "</td>";
                }

                message += "</tr>";
            }

            request.setAttribute("bookingsTable", message);
            //request.getRequestDispatcher("index.jsp").forward(request, response);

        }

        //String i = request.getParameter("assigndriver");
        //if (i.equals("driverassignment") == true) {
        //    request.setAttribute("availableDrivers", displayDrivers);
        //} else {
        //}
        //request.setAttribute("bookingsTable", message);
        //--------------------------------------------------------------------
        //request.getRequestDispatcher("index.jsp").forward(request, response);
        if (request.getParameter("assigndriver") != null) {
            bookings = BookingManager.getBookings(jdbc);
            drivers = DriverManager.getAllDrivers(jdbc);
            for (Booking booking : bookings) {
                if (booking.getDriver() == null) {

                    String selectedDriver = (String) request.getParameter("drivers");

                    //String[] splitStr = selectedDriver.split("\\s+");

                    //String registration = splitStr[2];

                    for (Driver driver : drivers) {
                        if (selectedDriver.equals(driver.getRegistration())) {
                            BookingManager.assignDriver(driver.getId(), booking.getId(), jdbc);
                        }
                    }

                }
            }

        }

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

        Long id = Long.parseLong(request.getParameter("id"));
        //Booking[] bookings;

        //BookingManager.assignDriver(, id, jdbc);
        //if () {
        //    BookingManager.assignDriver(
        //            id,
        //            ,
        //            jdbc);
        //}
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
