/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;
import model.UserManagement;
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
        
        //PrintWriter out = response.getWriter();
        //String registration = request.getParameter();
        
        
        
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

        //Check if DB connection is still active
        //response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
        
        Jdbc jdbc = (Jdbc)session.getAttribute("dbean");
        
        request.getRequestDispatcher("/admindashdrivers.jsp").forward(request, response);
        
        
            
        //}
        
            //Display all active jobs that DO NOT have a driver currently assigned
            //(Or display all jobs and allow admin to change things as they see fit)
            //Check how many active jobs are in stage 1 (OUTSTANDING)
            //FOR all jobs active
            //(int x = 0; x < Bookings.size(); x++) {
            //IF BookingStatusId == 1 display job details
            //if (Bookings.getBooking(x).getBookingStatusId()) {
            //}
        //}

        //String [] query = new String[x];
        //???????????????
        //Display all drivers that are currently NOT assigned to a job already
        //(Should a driver be able to have multiple jobs assigned to them? - E.g. if the driver is nearby to two)        
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
