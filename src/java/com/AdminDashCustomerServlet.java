/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BookingManager;
import model.CustomerManager;
import model.UserManager;
import model.Jdbc;
import model.tableclasses.Customer;
import model.tableclasses.Booking;

/**
 *
 * @author jakec
 */
public class AdminDashCustomerServlet extends HttpServlet {

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
        
        Customer[] aCustomer = CustomerManager.getAllCustomers(jdbc);

        String message = "";
        
        for (Customer customer:aCustomer) {
            
            message += "<tr>";
            message +="<td>" + customer.getFirstName() + "</td>";
            message +="<td>" + customer.getAddress() + "</td>";
            
            message += "</tr>";    
        }
        
        request.setAttribute("customersTable", message);

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
        
         ServletContext sc = request.getServletContext();
        
//        // Go straight to an error page if their where problems connecting to
//        // the DB.
//        if (sc.getAttribute("dBConnectionError") != null){
//            request.getRequestDispatcher("conErr.jsp").forward(request, response);
//        }
//        
//        // Connect Jdbc to the DB
//        Jdbc dbBean = new Jdbc();
//        dbBean.connect((Connection)sc.getAttribute("connection"));
//         // Values from Booking.jsp
//        
//        if (request.getAttribute("buttonHit").equals("approve")) {
//          int CustomertoApprove = UserManager.approveUser(
//                request.getParameter("userId"),
//                dbBean);
//          
//          request.getRequestDispatcher("AdminDashCustomer.jsp").forward(request, response);
//          
//        }else if (request.getAttribute("buttonHit").equals("edit")){
//            int CustomertoEdit = UserManager.editUser(
//                request.getParameter("userId"),
//                dbBean);
//            
//            request.getRequestDispatcher("EditCustomer.jsp").forward(request, response);
//        }
        
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
