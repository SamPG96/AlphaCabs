/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CustomerManager;
import model.Helper;
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
    
    private String resolveDelta(double d){
        String ret = "";
        String textColour;
        if(d != 0){
            String sDelta = String.valueOf(Math.round(d));
            if(d > 0){
                textColour = "green";
            }else{
                textColour = "red";
            }
            
            ret =  "<font color=\"" + textColour + "\">&nbsp; " + sDelta +"%</font>";
        }
        return ret;
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
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String todaysDate = "Date Today: "
                + dtf.format(now);
        request.setAttribute("todaysDate", todaysDate);
        
        //Resolve deltas
        String turnoverDelta = resolveDelta(reportManager.getTurnoverDelta());
        String nCustDelta = resolveDelta(reportManager.getnCustomersDelta());
        
        String dailyTurnover = "Daily Turnover: £"
                + Helper.doubleToCurrencyFormat(reportManager.getDailyTurnover())
                + turnoverDelta;
        request.setAttribute("todaysTurnover", dailyTurnover);

        String numCustServed = "Number of Customers served today: "
                + reportManager.getnCustomersToday()
                + nCustDelta;
        request.setAttribute("numCustServed", numCustServed);

        String message = "";
        String custName;
        for (Booking booking : todaysBookings) {
            message += "<tr>";
            custName = booking.getCustomer().getFirstName() + " "
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
