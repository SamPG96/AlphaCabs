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
import model.UserManager;
import model.Jdbc;
import static model.UserManager.getUserStatusObj;
import model.tableclasses.Customer;
import model.tableclasses.User;

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
        
        //Customer[] aCustomer = CustomerManager.getAllCustomers(jdbc);
        User[] aUser = UserManager.getAllUsers(jdbc);

        String message = "";
        String customerName = "";
        Customer customer;
        for (User user:aUser) {
            customer = user.getCustomer();
            if(customer == null){
                continue;
            }
            customerName = customer.getFirstName() + " " + customer.getLastName();
            message += "<tr>";
            message +="<td>" + customer.getId() + "</td>";
            message +="<td>" + customerName + "</td>";
            message +="<td>" + customer.getAddress() + "</td>";
            message +="<td>" + user.getUserStatus().getName() + "</td>";
            if (user.getUserStatus().getName().equals("Unapproved")) {
                message +="<td><button class=\"btn\" onclick=\"getUser(this)\" data-userid=" + user.getId() + " data-userstatus=" + user.getUserStatus().getName() + ">Approve</button></td>";
            }else {
                message +="<td><button class=\"btn\" onclick=\"getUser(this)\" data-userid=" + user.getId() + " data-userstatus=" + user.getUserStatus().getName() + ">Unapprove</button></td>";
            }
            
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
        
        HttpSession session = request.getSession(false);

        // Connect Jdbc to the DB
        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        long userId = Long.parseLong(request.getParameter("userid"));

        String userStatus = request.getParameter("userstatus");

        if (userStatus.equals("Unapproved")) {
           UserManager.changeUserStatus(userId, getUserStatusObj(2, jdbc), jdbc);
        }else{
           UserManager.changeUserStatus(userId, getUserStatusObj(1, jdbc), jdbc);
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
