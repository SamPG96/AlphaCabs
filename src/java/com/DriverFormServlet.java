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
import model.CustomerManager;
import static model.CustomerManager.NO_CUSTOMER_FIRST_NAME_ERR_CODE;
import static model.CustomerManager.NO_CUSTOMER_LAST_NAME_ERR_CODE;
import static model.CustomerManager.NO_CUSTOMER_ADDRESS_ERR_CODE;
import model.DriverManager;
import static model.DriverManager.NO_DRIVER_REGISTRATION_ERR_CODE;
import model.Jdbc;
import model.UserManager;
import static model.UserManager.NO_USER_FIRST_NAME_ERR_CODE;
import static model.UserManager.NO_USER_LAST_NAME_ERR_CODE;
import static model.UserManager.NO_USER_PASSWORD_ERR_CODE;
import static model.UserManager.PASSWORDS_DONT_MATCH_ERR_CODE;
import model.tableclasses.Driver;
import model.tableclasses.GenericItem;
import model.tableclasses.User;

/**
 *
 * @author jakec
 */
//TO DO:
// Take first and last names from booking servlet then create username
// Pass Username and Password onto model
// 
public class DriverFormServlet extends HttpServlet {

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
        int validationCode;
        String errMsgStr;
        long userId = 0;
        User user;
        Driver driver;
        long driverId;
        
        ServletContext sc = request.getServletContext();

        // Go straight to an error page if their where problems connecting to
        // the DB. Normally this would be checked by the login servlet, but as
        // the customer hasnt logged in to this point the check is never made.
        if (sc.getAttribute("dBConnectionError") != null) {
            request.getRequestDispatcher("conErr.jsp").forward(request, response);
        }

        // Connect Jdbc to the DB
        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection) sc.getAttribute("connection"));

        String sUserId = request.getParameter("userid");
        if(sUserId != null){
            userId = Long.parseLong(sUserId);
        }
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String registration = request.getParameter("registration");
        String password = request.getParameter("password");
        String confPassword = request.getParameter("confpassword");
        
        // Validate entry for a new user and customer before it is inserted into
        // the DB.
        validationCode = DriverManager.validateDriverAttribs(
                firstName,
                lastName,
                registration,
                password,
                confPassword);

        // Display an error message if their is a problem the user entry
        if (validationCode != 0) {
            // Input error related to creating a new user
            errMsgStr = findErrorMessage(validationCode);

            request.setAttribute("errMsg", errMsgStr + "</br>");
            request.getRequestDispatcher("adminDashUpdateDriver.jsp").forward(request, response);
        }
        
        user = UserManager.getUser(userId, dbBean);
        user.setPassword(password);
        dbBean.update(user);
        
        driver = DriverManager.getDriver(user.getDriverId(), dbBean);
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setRegistration(registration);
        dbBean.update(driver);
        
        response.sendRedirect("index.jsp");

        processRequest(request, response);
    }

    private String findErrorMessage(int errCode) {
        String errMsg = "Oops! - ";

        switch (errCode) {
            case NO_USER_FIRST_NAME_ERR_CODE:
                // First name not entered
                errMsg += "First name not entered";
                break;
            case NO_USER_LAST_NAME_ERR_CODE:
                // Last name not entered
                errMsg += "Last name not entered";
                break;
            case NO_DRIVER_REGISTRATION_ERR_CODE:
                errMsg += "Registration not entered";
                break;
            case NO_USER_PASSWORD_ERR_CODE:
                // Password not entered
                errMsg += "Password not entered";
                break;
            case PASSWORDS_DONT_MATCH_ERR_CODE:
                // Passwords dont match
                errMsg += "Passwords do not match";
                break;
            default:
                errMsg += "registration form error";
                break;
        }

        return errMsg;
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
