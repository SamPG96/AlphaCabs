/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CustomerManager;
import model.Jdbc;
import model.tableclasses.Customer;

/**
 *
 * @author jakec
 */
public class AdminDashUserServlet extends HttpServlet {

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
        //Jdbc jdbc = (Jdbc) session.getAttribute("jdbc");

        User[] aUser = UserManager.getAllUsers(jdbc);
        
            
        String message = "<tr>\n"
                + "                    <th>Username</th>\n"
                + "                    <th>UserType</th>\n"
                + "                    <th>CustomerId</th>\n"
                + "                    <th>DriverId</th>\n"
                + "                    <th>UserStatus</th>\n"
                + "                    <th>Approve</th>\n"
                + "                </tr>";
        
        
        for (User user:aUser) {
            
            
            
            message +=  "<tr>";
            message +="<td>" + user.getUserName() + "</td>";
            message +="<td>" + user.getUserType() + "</td>";
            message +="<td>" + user.getCustomerId() + "</td>";
            message +="<td>" + user.getDriverId() + "</td>";
            message +="<td>" + user.getUserStatus() + "</td>";
            message +="<td input type='checkbox' value='approve'></td>";
          
            message += "</tr>";
        }

        request.setAttribute("userTable", message);

     
//
//        request.setAttribute("bookingsTable", message + "</br>");
        //response.setIntHeader("Refresh", 0);
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
