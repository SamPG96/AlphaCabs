/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.sql.Connection;
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
 * @author Sam,Jake
 */
public class LoginServlet extends HttpServlet {

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
        
        // Go straight to an error page if their where problems connecting to
        // the DB.
        if (sc.getAttribute("dBConnectionError") != null){
            request.getRequestDispatcher("conErr.jsp").forward(request, response);
        }
        
        // Connect Jdbc to the DB
        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection)sc.getAttribute("connection"));
        
        // Attempt to login the user, -1 is returned if the password or
        // username is inccorect  
        int loggedInUserID = UserManagement.loginUser(
                request.getParameter("username"),
                request.getParameter("password"),
                dbBean);

        // Handle result of login attempt
        if (loggedInUserID == -1) {
            // Login failure!
            String message = "Incorrect username or password, try again";
            request.setAttribute("errMsg", message + "</br>");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {
            // Login success!
            // Create a new session
            HttpSession session = request.getSession();
            session.setAttribute("userID", loggedInUserID);
            session.setAttribute("dbbean", dbBean);
            
            User user = UserManagement.getUser(loggedInUserID, dbBean);
            
            // Move to the page associated with the user type
            switch (user.getUserType().getId()) {
                case 1:
                    request.getRequestDispatcher("loginAdmin.jsp").forward(
                            request, response);
                    break;
                case 2:
                    request.getRequestDispatcher("loginDriver.jsp").forward(
                            request, response);
                    break;
                case 4:
                    request.getRequestDispatcher("loginCustomer.jsp").forward(
                            request, response);
                    break;
                default:
                    throw new RuntimeException("user type not recognised");
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
