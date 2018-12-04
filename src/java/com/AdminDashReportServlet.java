/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CustomerManager;
import model.UserManager;
import model.Jdbc;
import model.ReportManager;
import model.tableclasses.Booking;
import model.tableclasses.Customer;

/**
 *
 * @author jakec
 */
public class AdminDashReportServlet extends HttpServlet {

    public double todaysTurnover = 100.2;

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

        ReportManager reportManager = new ReportManager(jdbc);

        Booking[] todaysBookings = reportManager.getTodaysBookings();

        //Resolve double to display to 2 decimal places
        double turnover = reportManager.getDailyTurnover();
        String sTurnover = String.valueOf(turnover);
        String[] strArr = sTurnover.split("\\.");
        if(strArr[1].endsWith("0") || strArr[1].length() == 1){
            sTurnover += "0";
        }
        String dailyTurnover = "Daily Turnover: £"
                + sTurnover;
        
        request.setAttribute("todaysTurnover", dailyTurnover);

        String numCustServed = "Number of Customers served today: "
                + reportManager.getnCustomersToday();
        request.setAttribute("numCustServed", numCustServed);

        String message = "";
        String custName;
        for (Booking booking : todaysBookings) {
            message += "<tr>";
            custName = booking.getCustomer().getFirstName() 
                    + booking.getCustomer().getLastName();
            message += "<td>" + custName + "</td>";
            message += "<td>" + booking.getSourceAddress() + "</td>";
            message += "<td>" + booking.getDestinationAddress() + "</td>";
            message += "<td>" + booking.getDepartureTime() + "</td>";
            message += "<td>" + booking.getFareExcVAT() + "</td>";
            message += "</tr>";
        }
        request.setAttribute("todaysBookingsTable", message);

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

        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        ReportManager reportManager = new ReportManager(jdbc);

        Booking[] todaysBookings = reportManager.getTodaysBookings();

        String dailyTurnover = "Daily Turnover: £"
                + reportManager.getDailyTurnover();
        request.setAttribute("todaysTurnover", dailyTurnover);

        String numCustServed = "Number of Customers served today: "
                + reportManager.getnCustomersToday();
        request.setAttribute("numCustServed", numCustServed);

//        String message = "<tr>\n"
//                + "                    <th>First name</th>\n"
//                + "                    <th>Last name</th>\n"
//                + "                    <th>Address</th>\n"
//                + "                </tr>";
//        for (Customer customer : customers) {
//            message += "<tr>";
//            message += "<td>" + customer.getFirstName() + "</td>";
//            message += "<td>" + customer.getLastName() + "</td>";
//            message += "<td>" + customer.getAddress() + "</td>";
//            message += "</tr>";
//        }
//        request.setAttribute("bookingsTable", message);
        //response.setIntHeader("Refresh", 0);
        request.getRequestDispatcher("adminDashReport.jsp").forward(request, response);
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
