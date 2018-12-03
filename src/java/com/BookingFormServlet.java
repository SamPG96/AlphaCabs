/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BookingManager;
import static model.BookingManager.ERR_CUST_NULL;
import static model.BookingManager.ERR_DEP_DATE_NULL;
import static model.BookingManager.ERR_SRC_HOME_NULL;
import static model.BookingManager.ERR_SRC_ADDR_NULL;
import static model.BookingManager.ERR_DEST_ADDR_NULL;
import static model.BookingManager.ERR_N_PAS_NULL;
import static model.BookingManager.ERR_DEP_TIME_NULL;
import model.CustomerManager;

import model.Jdbc;
import model.UserManager;
import model.tableclasses.Booking;
import model.tableclasses.Customer;

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
        String prevServPath;
        HttpSession session = request.getSession();

        // Fetch the caller URL
        String[] prevURLPath =((String)request.getHeader("referer")).split("/");
        prevServPath = prevURLPath[prevURLPath.length - 1];
        
        // Fully process booking if the customer entity is known. Otherwise
        // only validate user entry and then move on to another page to identify
        // the customer. This servlet will be returned to once the customer has
        // been identified. If prevServPath equals 'AlphaCabs' then this servlet
        // has been called from the index.jsp. This mean no prior attempt has
        // been made to identify the customer if they arent already signed in.
        if (session.getAttribute("userID") != null ||
                (prevServPath.equals("AlphaCabs") == false &&
                 session.getAttribute("cachedCustomerID") != null)){
            processBookingWithIdentity(request, response, session);
        }
        else {
            processBookingWithNoIdentity(request, response, session);
        }
    }

    /*
    * Fully process a booking
    */
    private void processBookingWithIdentity(HttpServletRequest request,
            HttpServletResponse response, HttpSession session) throws ServletException, IOException{
            Booking booking;
            BookingManager bookingMan;
            Customer customer;
            Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");
        
            bookingMan = new BookingManager();
            
            // Fetch customer info
            if (session.getAttribute("userID") != null){
                // A customer user is signed in, derive customer info from this.
                long userID = (long)session.getAttribute("userID");
                customer = UserManager.getUser(userID, jdbc).getCustomer();
            }
            else{
                // The customer is not signed in but the identity of a
                // customer has been cached from registration as new user or
                // guest.
                long customerID = (long)session.getAttribute("cachedCustomerID");
                // Clear customer cache
                session.removeAttribute("cachedCustomerID");
                customer = CustomerManager.getCustomer(customerID, jdbc);
            }
            
            // Check if any booking informtion is cached. If it is then no
            // input validation is required (as this would of been done previously
            // before it was cached) and it is used to complete the booking.
            // If no booiking information is cached then generate a fresh
            // booking object and validate its entry.
            if (session.getAttribute("cachedBooking") == null) {
                // Generate a booking object and validate user entry
                booking = bookingMan.addNewBooking(
                        customer,
                        "false", // TODO: add support for arg in JSP
                        request.getParameter("source"),
                        request.getParameter("destination"),
                        request.getParameter("passengers"),
                        request.getParameter("date"),
                        request.getParameter("time"));

                if (booking == null) {
                    request.setAttribute("errMsg",
                            convertErrCodeToMessageStr(bookingMan) + "</br>");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
            else{
                // Cached booking information will not contain any customer
                // information, so this can now be added. This is because
                // bookings are only cached while the web app provides the user
                // with further input for them to identify them selves.
                booking = (Booking)session.getAttribute("cachedBooking");
                session.removeAttribute("cachedBooking");
                // Now the customer is known it can be added to the booking object
                booking.setCustomer(customer);
            }
            
            // Add booking to database and issue invoive
            jdbc.insert(booking);
            request.setAttribute("booking", booking);
            request.getRequestDispatcher("invoice.jsp").forward(request, response);        
    }
    
    /*
    * Partially process booking. Validate user input and cache booking
    * information, then move onto another page to allow the user to identify
    * themselves.
    */
    private void processBookingWithNoIdentity(HttpServletRequest request,
            HttpServletResponse response, HttpSession session) throws ServletException, IOException{
        Booking booking;
        BookingManager bookingMan;
        
        bookingMan = new BookingManager();
        
        // Generate a partial booking object that does not include any
        // details about the customer. Customer details will be added to the
        // booking once they are known.
        booking = bookingMan.addNewBooking(
                request.getParameter("source"),
                request.getParameter("destination"),
                request.getParameter("passengers"),
                request.getParameter("date"),
                request.getParameter("time"));            

        // If errors exist with the booking entry, display them. Otherwise
        // move to a page that allows the customer to identiy them selves
        if (booking == null) {
            request.setAttribute("errMsg",
                    convertErrCodeToMessageStr(bookingMan) + "</br>");
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } else {
            // Cache booking details so that it can be accessed once the
            // customer identity is known.
            session.setAttribute("cachedBooking", booking);
            request.getRequestDispatcher("bookingIdentity.jsp").forward(
                    request, response);
        }
    }
    
    /*
    * Convert error codes generated by creating a new booking to a human
    * readable message.
    */    
    private String convertErrCodeToMessageStr(BookingManager bookingMan){
        String message = "Oops! - ";

        //error with booking information
        switch (bookingMan.getError()) {
            case ERR_CUST_NULL:
                message +=  "Customer not given";
                break;
            case ERR_SRC_HOME_NULL:
                message += "Source  not given";
                break;
            case ERR_SRC_ADDR_NULL:
                message += "Source Address not given";
                break;
            case ERR_DEST_ADDR_NULL:
                message += "Destination Address not given";
                break;
            case ERR_N_PAS_NULL:
                message += "Passengers not given";
                break;
            case ERR_DEP_TIME_NULL:
                message += "Departure time not given";
                break;
            case ERR_DEP_DATE_NULL:
                message += "Departure date not given";
                break;
            default:
                message += "booking form error";
                break;
        }
        
        return message;
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
