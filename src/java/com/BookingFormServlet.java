/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BookingManager;
import static model.BookingManager.ERR_CUST_NULL;
import static model.BookingManager.ERR_SRC_HOME_NULL;
import static model.BookingManager.ERR_SRC_ADDR_NULL;
import static model.BookingManager.ERR_DEST_ADDR_NULL;
import static model.BookingManager.ERR_N_PAS_NULL;
import static model.BookingManager.ERR_DEP_TIME_NULL;

import model.Jdbc;
import model.UserManager;
import model.tableclasses.Booking;
import model.tableclasses.Customer;
import model.tableclasses.User;

/**
 *
 * @author jakec
 */
public class BookingFormServlet extends HttpServlet {

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
        
        ServletContext sc = request.getServletContext();
        
        BookingManager bookingMan = new BookingManager();
        
        // Go straight to an error page if their where problems connecting to
        // the DB.
       // if (sc.getAttribute("dBConnectionError") != null){
        //    request.getRequestDispatcher("conErr.jsp").forward(request, response);
        //}
        
        // Connect Jdbc to the DB
        //Jdbc dbBean = new Jdbc();
        //dbBean.connect((Connection)sc.getAttribute("connection"));
        HttpSession session = request.getSession(false);
        
        //Already logged in.
        if (session != null && session.getAttribute("userID") != null) {
            Jdbc jdbc = (Jdbc)session.getAttribute("dbbean");
            long userID = (long)session.getAttribute("userID");
            Customer customer = UserManager.getUser(userID, jdbc).getCustomer();
        
            Booking booking = bookingMan.generateNewBooking(
                customer,
                request.getParameter("source"),
                request.getParameter("destination"),
                request.getParameter("date"),
                request.getParameter("time"),
                request.getParameter("passengers"));

        // Handle result of login attempt
        //check for errors from booking manager
        if (booking == null) {
            String message;
                //error with booking information
                switch (bookingMan.getError()) {
                    case ERR_CUST_NULL:
                        message = "Customer not given";
                        break;
                    case ERR_SRC_HOME_NULL:
                        message = "Source  not given";
                        break;
                    case ERR_SRC_ADDR_NULL:
                        message = "Source Address not given";
                        break;
                    case ERR_DEST_ADDR_NULL:
                        message = "Destination Address not given";
                        break;
                    case ERR_N_PAS_NULL:
                        message = "Passengers not given";
                        break;
                    case ERR_DEP_TIME_NULL:
                        message = "Departure Time not given";
                        break;
                    default:
                        message = "booking form error";
                        break;
                }
            
            request.setAttribute("errMsg", message + "</br>");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else{
            jdbc.insert(booking);
            request.getRequestDispatcher("invoice.jsp").forward(request, response);
        }
        
        }
        
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
