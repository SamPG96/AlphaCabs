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
        processRequest(request, response);

        HttpSession session = request.getSession(false);

        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        User[] users = UserManager.getAllUsers(jdbc);

        String table = "";

        for (User user : users) {

            long sessID = session.getAttribute("userID");
//Can't get the session ID to long please help!
            if (user.getId() ==) {

                table += "<tr>";
                table += "<td>First Name</td><td><input type=\"text\" name=\"fName\" value=\"" + user.getCustomer().getFirstName() + "\"></td>";
                table += "</tr>";
                table += "<tr>";
                table += "<td>Last Name</td><td><input type=\"text\" name=\"lName\" value=\"" + user.getCustomer().getLastName() + "\"></td>";
                table += "</tr>";
                table += "<tr>";
table += "<td>Address</td><td><input type=\"text\" name=\"address\" value=\"" + user.getCustomer().getAddress() + "\"></td>";
                table += "</tr>";
            }

        }

                request.setAttribute("editTable", table);

        request.getRequestDispatcher("index.jsp").forward(request, response);
        
        
        if(request.getParameter("edit") != null){
             for (User user : users) {
                 
                 user.getCustomer().setFirstName("fName");
                 user.getCustomer().setLastName("lName");
                 user.getCustomer().setAddress("address");
                 
                 jdbc.update(user);
                 
             }
            
            
            
            
        }
        
        
        
    }

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
