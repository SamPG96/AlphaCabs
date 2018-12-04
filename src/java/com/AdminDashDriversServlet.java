/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DriverManager;
import model.Jdbc;
import model.UserManager;
import static model.UserManager.NO_USER_FIRST_NAME_ERR_CODE;
import static model.UserManager.NO_USER_LAST_NAME_ERR_CODE;
import static model.UserManager.NO_USER_PASSWORD_ERR_CODE;
import static model.UserManager.PASSWORDS_DONT_MATCH_ERR_CODE;
//import static model.UserManager.NO_FIRST_NAME_ERR_CODE;
//import static model.UserManager.NO_LAST_NAME_ERR_CODE;
//import static model.UserManager.NO_PASSWORD_ERR_CODE;
//import static model.UserManager.PASSWORDS_DONT_MATCH_ERR_CODE;
import model.tableclasses.Driver;

/**
 *
 * @author Tom
 */
public class AdminDashDriversServlet extends HttpServlet {

    private String returnPage = "index.jsp";

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
        //Jdbc jdbc = (Jdbc) session.getAttribute("jdbc");  

        //GET All Drivers
        Driver[] aDriver = DriverManager.getAllDrivers(jdbc);

        String table = "<tr>\n"
                + "<th>Driver ID</th>\n"
                + "<th>Forename</th>\n"
                + "<th>Surname</th>\n"
                + "<th>Registration</th>\n"
                //+ "<th></th>"
                + "</tr>";

        for (Driver driver : aDriver) {
            table += "<tr>";
            table += "<td>" + driver.getId() + "</td>";
            table += "<td>" + driver.getFirstName() + "</td>";
            table += "<td>" + driver.getLastName() + "</td>";
            table += "<td>" + driver.getRegistration() + "</td>";
            //table += "<td>" + "<input type='button' id='btnUpdate' value='Update'/>" + "</td>";
            //table += "<td>" + "Delete" + "</td>";
        }

        request.setAttribute("driversTable", table);

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

        //-------------------------------VARIABLES----------------------------------
        long driverId;
        long userId;
        int newUserErrCode;
        String errMsgStr;
        
        String changeDriver;
        long softDelDriver;

        //-------------------------------ADD DRIVER----------------------------------
        newUserErrCode = UserManager.validateNewUserAttribs(
                request.getParameter("forename"),
                request.getParameter("surname"),
                request.getParameter("password"),
                request.getParameter("confirmation"));

        if (newUserErrCode != 0) {
            errMsgStr = convertNewUserErrCodeToMessageStr(newUserErrCode);

            request.setAttribute("errMsg", errMsgStr + "</br>");
            request.getRequestDispatcher("adminDashDrivers.jsp").forward(request, response);
        }

        ServletContext sc = request.getServletContext();

        // Go straight to an error page if their where problems connecting to
        // the DB.
        if (sc.getAttribute("dBConnectionError") != null) {
            request.getRequestDispatcher("conErr.jsp").forward(request, response);
        }

        // Connect Jdbc to the DB
        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection) sc.getAttribute("connection"));

        driverId = DriverManager.addNewDriver(request.getParameter("forename"),
                request.getParameter("surname"),
                request.getParameter("registration"),
                dbBean);

        userId = UserManager.newDriverUser(
                request.getParameter("password"),
                request.getParameter("confirmation"),
                driverId,
                UserManager.getUserStatusObj(2, dbBean),
                dbBean);

        String userName = UserManager.getUsernameForDriver(driverId, dbBean);

        response.sendRedirect(returnPage);

        //--------------------------------------------------------------------------------------------------
        //REMOVE DRIVER
        //Wait for method
        //DriverManager.removeDriver(request.setAttribute());
        
        //Driver selectedDriver = DriverManager.getDriver(driverId, dbBean);
        //softDelDriver = DriverManager.softRemoveDriver(driverId, dbBean);
        
        
        //--------------------------------------------------------------------------------------------------
        //CHANGE DRIVER DETAILS
        /*
        changeDriver = DriverManager.updateDriver(driverId,
                request.getParameter("forenameC"),
                request.getParameter("surnameC"),
                request.getParameter("registrationC"),
                dbBean);
        */
    }

    /*
    * Convert error codes generated by creating a new user to a human
    * readable message.
     */
    private String convertNewUserErrCodeToMessageStr(int errCode) {
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