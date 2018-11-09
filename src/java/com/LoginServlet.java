/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.usermanagement.AlphacabListener;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserManagement;

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

        HttpSession session = request.getSession(false);

        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection)request.getServletContext().getAttribute("connection"));
        session.setAttribute("dbbean", dbBean);
        
        String message = null;

        User loggedInUser = UserManagement.loginUser(request.getParameter("username"), request.getParameter("password"), (Jdbc)session.getAttribute("dbbean"));

        //String user = AlphacabListener.login(loginUser);
        UserManagement userType = new UserManagement();
        loggedInUser.getUserType;// Not sure how to do this.

        if (loginUser.equals(null)) {

            message = "Please try again";

        } else {

            if (loggedInUser == 1) {

                message = "You have successfully logged in.";
                request.getRequestDispatcher("loginAdmin.jsp").forward(request, response);

            } else if (loggedInUser == 2) {

                message = "You have successfully logged in.";
                request.getRequestDispatcher("loginDriver.jsp").forward(request, response);

            } else if (loggedInUser == 3) {

                message = "You have successfully logged in.";
                request.getRequestDispatcher("loginCustomer.jsp").forward(request, response);

            }

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
