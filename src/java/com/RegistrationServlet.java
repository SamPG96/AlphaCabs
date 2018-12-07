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
import model.Jdbc;
import model.UserManager;
import static model.UserManager.NO_USER_FIRST_NAME_ERR_CODE;
import static model.UserManager.NO_USER_LAST_NAME_ERR_CODE;
import static model.UserManager.NO_USER_PASSWORD_ERR_CODE;
import static model.UserManager.PASSWORDS_DONT_MATCH_ERR_CODE;
import model.tableclasses.GenericItem;


/**
 *
 * @author jakec
 */
//TO DO:
// Take first and last names from booking servlet then create username
// Pass Username and Password onto model
// 
public class RegistrationServlet extends HttpServlet {

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
        int newCustErrCode;
        int newUserErrCode;
        String errMsgStr;
        long userId;
        long customerId;
        HttpSession session;
       
        // Validate entry for a new user and customer before it is inserted into
        // the DB.
        newCustErrCode = CustomerManager.validateNewCustomerAttribs(
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("homeAddress"));
        
        newUserErrCode = UserManager.validateNewUserAttribs(
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("password"),
                request.getParameter("passwordConfirm"));  
        
        // Display an error message if their is a problem the user entry
        if (newCustErrCode != 0 || newUserErrCode != 0){
            if (newCustErrCode != 0){
                // Input error related to creating a new customer
                errMsgStr = convertNewCustErrCodeToMessageStr(newCustErrCode);
            }
            else{
                // Input error related to creating a new user
                errMsgStr = convertNewUserErrCodeToMessageStr(newUserErrCode);
            }
            
            request.setAttribute("errMsg", errMsgStr + "</br>");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

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
        
        // Create an entry in the database for this customer
        customerId = CustomerManager.addNewCustomer(
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("homeAddress"),
                dbBean);
            String activateAccount = request.getParameter("activateAccount");
        // Create user account for customer. Set account to require approval.
        if (activateAccount == null) {
        userId = UserManager.newCustomerUser(
                request.getParameter("password"),
                request.getParameter("passwordConfirm"),
                customerId,
                UserManager.getUserStatusObj(1, dbBean),
                dbBean);
        }else if (activateAccount.equals("on")) {
        userId = UserManager.newCustomerUser(
                request.getParameter("password"),
                request.getParameter("passwordConfirm"),
                customerId,
                UserManager.getUserStatusObj(2, dbBean),
                dbBean);
        }

        
        // By default the user account for the customer is not active. So cache
        // the customer ID for the booking servlet to access.
        session = request.getSession();
        session.setAttribute("cachedCustomerID", customerId);
        session.setAttribute("dbbean", dbBean);
        
        // Go to a page that informs the customer of their registration success,
        // also inform them of there automated username.
        String username = UserManager.getUsernameForCustomer(customerId, dbBean);
        request.setAttribute("newUsername", username);
        
        if (session != null && session.getAttribute("userID") != null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("regConfirm.jsp").forward(request, response);
        }

        processRequest(request, response);
    }

    /*
    * Convert error codes generated by creating a new customer to a human
    * readable message.
    */
    private String convertNewCustErrCodeToMessageStr(int errCode){
        String errMsg = "Oops! - ";
        
        switch(errCode){
            case NO_CUSTOMER_FIRST_NAME_ERR_CODE:
                // First name not entered
                errMsg += "First name not entered";
                break;

            case NO_CUSTOMER_LAST_NAME_ERR_CODE:
                // Last name not entered
                errMsg += "Last name not entered";
                break;

            case NO_CUSTOMER_ADDRESS_ERR_CODE:
                // Address not entered
                errMsg += "Address not entered";
                break;

            default:
                errMsg += "registration form error";
                break;
        }
        
        return errMsg;
    }

    /*
    * Convert error codes generated by creating a new user to a human
    * readable message.
    */
    private String convertNewUserErrCodeToMessageStr(int errCode){
        String errMsg = "Oops! - ";
        
        switch(errCode){
            case NO_USER_FIRST_NAME_ERR_CODE:
                // First name not entered
                errMsg += "First name not entered";
                break;

            case NO_USER_LAST_NAME_ERR_CODE:
                // Last name not entered
                errMsg += "Last name not entered";
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
