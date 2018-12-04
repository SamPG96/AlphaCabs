/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.AlphacabListener;
import com.fasterxml.classmate.GenericType;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import model.Jdbc;
import model.UserManager;
import model.tableclasses.GenericItem;
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
        ArrayList<GenericItem> allowedUserTypes;
        String nextPageAfterSuccessfulLogin;
        String nextPageAfterFailedLogin;
        long loggedInUserID;
        
        ServletContext sc = request.getServletContext();
        
        // Go straight to an error page if their where problems connecting to
        // the DB.
        if (sc.getAttribute("dBConnectionError") != null){
            request.getRequestDispatcher("conErr.jsp").forward(request, response);
        }
        
        // Connect Jdbc to the DB
        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection)sc.getAttribute("connection"));
        
        // The pages to go to upon successful login or failed login and the
        // types of user allowed to login is dependant upon where login was
        // called. Default operation is to return to 'index.jsp' and allow
        // users of any type to login
        if (request.getParameter("reDirectOnSuccess").equals("BookingFormServlet.do")){
            // Login is being called by the booking process. Once complete
            // the booking servlet should be returned to otherwise if login is
            // unsuccessful then the booking identity JSP should be returned to
            // (which includes the login.jsp file)
            nextPageAfterSuccessfulLogin = "BookingFormServlet.do";
            nextPageAfterFailedLogin = "bookingIdentity.jsp";
            // Only allow customers to sign in
            allowedUserTypes = new ArrayList();
            allowedUserTypes.add(UserManager.getUserTypeObj(4, dbBean));
            
        }
        else{
            // Default action
            nextPageAfterSuccessfulLogin = "index.jsp";
            nextPageAfterFailedLogin = "login.jsp";
            // Allow all user types to login
            allowedUserTypes = new ArrayList<>(Arrays.asList(UserManager.getAllUserTypes(dbBean)));
        }
        
        // Attempt to login the user, -1 is returned if the password or
        // username is incorect or if the user does exist but its user type
        // is not allowed to sign at this stage.
        loggedInUserID = UserManager.loginSpecificUserTypes(
                request.getParameter("username"),
                request.getParameter("password"),
                allowedUserTypes,
                dbBean);         

        
        // Handle result of login attempt
        if (loggedInUserID == -1) {
            // Login failure!
            String message = "Incorrect username or password, try again";
            request.setAttribute("errMsg", message + "</br>");
            request.getRequestDispatcher(nextPageAfterFailedLogin).forward(request, response);

        }
        
        // Login success!
        User user = UserManager.getUser(loggedInUserID, dbBean);

        HttpSession session = request.getSession();
        session.setAttribute("userID", loggedInUserID);
        session.setAttribute("dbbean", dbBean);
        session.setAttribute("userType", user.getUserType());

        request.getRequestDispatcher(nextPageAfterSuccessfulLogin).forward(
                request, response);
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
