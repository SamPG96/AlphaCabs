/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;

/**
 *
 * @author me-aydin
 */
public class UserServLet extends HttpServlet {

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
        String qry = "select * from users";
       
        HttpSession session = request.getSession();
        
        response.setContentType("text/html;charset=UTF-8");
        
        // Connect to database and use Jdbc to interface with it
        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection)request.getServletContext().getAttribute("connection"));
        
        // Store access to the database for the entire session
        session.setAttribute("dbbean", dbBean);
        
        // Check is connection to database was successful
        if((Connection)request.getServletContext().getAttribute("connection")==null)
            request.getRequestDispatcher("/conErr.jsp").forward(request, response);
        
        // When which option was chosen and process request
        if (request.getParameter("tbl").equals("List")){
            // List users
            String msg="No users";
            
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("query", msg);
            request.getRequestDispatcher("/results.jsp").forward(request, response);
        }
        else if(request.getParameter("tbl").equals("NewUser")){
            // Create a new user
            request.getRequestDispatcher("/user.jsp").forward(request, response);
        } 
        else if(request.getParameter("tbl").equals("Update")){
            // Update user details
            request.getRequestDispatcher("/passwdChange.jsp").forward(request, response);    
        }
        else {
            // Delete user
            request.setAttribute("msg", "del");
            request.getRequestDispatcher("/user.jsp").forward(request, response); 
        }
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
