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

        //response.setContentType("text/html;charset=UTF-8");
        //HttpSession session = request.getSession(false);
        //Jdbc jdbc = (Jdbc)session.getAttribute("dbean");
        //if (jdbc == null)
        //    request.getRequestDispatcher("/conErr.jsp").forward(request, reponse);
        //else {
        //}
        
        //System.out.println("TESTING TESTING 1 2 3 - processRequest");
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
        processRequest(request, response);

        //PrintWriter out = response.getWriter();
        //String registration = request.getParameter();
        
        //System.out.println("TESTING TESTING 1 2 3 - doGet");
        
        //Check if DB connection is still active
        //response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);

        Jdbc jdbc = (Jdbc) session.getAttribute("dbean");

        request.getRequestDispatcher("/admindashdrivers.jsp").forward(request, response);
        
        String qryStr = "";
        Statement statement = null;
        //ResultSet resultSet = null;
        Booking booking = null;
        
        PrintWriter out = response.getWriter();
        Connection connection = null;
        
        //try {
        qryStr = "SELECT * from booking";
        
        //resultSet = statement.executeQuery(qryStr);
        //booking = statement.executeQuery(qryStr);
        
        //if (resultSet.next()) {
        //if (booking.next()) {
        
            //Id
            //CustomerId
            //SourceAddress
            //DestinationAddress
            //TimeBooked
            //BookingStatusId
            
            //Booking booking = new Booking(Booking);
            
            //booking.setId(booking.getId());
            booking.getId();
            //booking.setId(resultSet.getLong("Id"));
            //booking.setCustomer(booking.getCustomer());
            booking.getCustomer();
            //booking.setSourceAddress(booking.getSourceAddress());
            booking.getSourceAddress();
            //booking.setDestinationAddress(booking.getDestinationAddress());
            booking.getDestinationAddress();
            //booking.setTimeBooked(booking.getTimeBooked());
            booking.getTimeBooked();
            //booking.setBookingStatus(booking.getBookingStatus());
            booking.getBookingStatus();
            
            System.out.println("TESTING 1 2 3");
  
            //booking.setBookingId(resultSet.getLong("Id"));
            //booking.setBookingCustomerId(resultSet.getInt("CustomerId"));
            //booking.setBookingDriverId(resultSet.getInt("DriverId"));
            //booking.setBookingSourceAddress(resultSet.getString("SourceAddress"));
            //booking.setBookingDestinationAddress(resultSet.getString("DestinationAddress"));
            //booking.setBookingDistanceKM(resultSet.getDouble("DistanceKM"));
            //booking.setBookingTimeBooked(resultSet.getTimestamp("TimeBooked"));
            //booking.setBookingTimeArrived(resultSet.getTimestamp("TimeArrived"));
            //booking.setBookingStatusId(resultSet.getInt("BookingStatusId"));
            
        //}
        
        //} catch (SQLException ex) {
            //Logger.getLogger(AdminDashBookingsServlet.class.getName()).log(Level.SEVERE, null, ex);
        //}
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
