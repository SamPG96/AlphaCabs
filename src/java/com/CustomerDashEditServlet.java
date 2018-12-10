/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;
import model.UserManager;
import model.tableclasses.Customer;
import model.tableclasses.User;

/**
 *
 * @author yuugy
 */
@WebServlet(name = "CustomerDashEdit", urlPatterns = {"/CustomerDashEdit"})
public class CustomerDashEditServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

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
        Jdbc jdbc;
        HttpSession session;
        long userID;
        User user;
        
        processRequest(request, response);

        session = request.getSession(false);
        
        jdbc = (Jdbc) session.getAttribute("dbbean");
        userID = (long)session.getAttribute("userID");
        user = UserManager.getUser(userID, jdbc);

        setTableAttribs(user.getCustomer(), request);
        request.getRequestDispatcher("index.jsp").forward(request, response);                 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer customer;
        Jdbc jdbc;
        HttpSession session;
        long userID;
        User user;
        
        processRequest(request, response);

        session = request.getSession(false);
        
        jdbc = (Jdbc) session.getAttribute("dbbean");
        userID = (long)session.getAttribute("userID");
        user = UserManager.getUser(userID, jdbc);
        customer = user.getCustomer();
 
        // Update customer details
        if (request.getParameter("firstName").equals(customer.getFirstName()) == false){
            customer.setFirstName(request.getParameter("firstName"));
        }
        if (request.getParameter("lastName").equals(customer.getLastName()) == false){
            customer.setLastName(request.getParameter("lastName"));
        }
        if (request.getParameter("address").equals(customer.getAddress()) == false){
            customer.setAddress(request.getParameter("address"));
        }
                 
        jdbc.update(customer);
        
        setTableAttribs(user.getCustomer(), request);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /*
    * Set all neccessary table attributes
    */
    private void setTableAttribs(Customer customer, HttpServletRequest request){
        request.setAttribute("firstName", customer.getFirstName());
        request.setAttribute("lastName", customer.getLastName());
        request.setAttribute("address", customer.getAddress());
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
