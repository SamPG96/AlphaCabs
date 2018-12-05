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
import model.tableclasses.User;

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

        //GET All Driver users
        User[] users = UserManager.getAllUsers(jdbc);
        
        String table = "";
        Driver driver;
        String driverName;
        for (User user : users) {
            driver = user.getDriver();
            if(driver == null){
                continue;
            }
            
            table += "<tr>";
            table += "<td>" + driver.getId() + "</td>";
            driverName = driver.getFirstName()
                    + driver.getLastName();
            table += "<td>" + driverName + "</td>";
            table += "<td>" + driver.getRegistration() + "</td>";
            table += "<td>" + user.getUserStatus().getName() + "</td>";
            table += "<td>" + "<button class=\"btn\" onclick=\"document.getElementById('id02').style.display = 'block'\">Update</button>" + "</td>";
            table += "<td>" + "<button class=\"btn\" onclick=\"document.getElementById('id03').style.display = 'block'\">Remove</button>" + "</td>";
            table += "</tr>";
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

        long changeDriver;
        long softDelDriver;

        ServletContext sc = request.getServletContext();

        // Go straight to an error page if their where problems connecting to
        // the DB.
        if (sc.getAttribute("dBConnectionError") != null) {
            request.getRequestDispatcher("conErr.jsp").forward(request, response);
        }

        // Connect Jdbc to the DB
        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection) sc.getAttribute("connection"));

        String x = request.getParameter("button_click");
        //String x = request.getParameter("buttonA_click");
        //String i;

        //----------------------------CHANGE DRIVER DETAILS-------------------------------
        //if (null != request.getParameter("buttonA_click")) {
        if (x.equals("changedetails") == true) {

            DriverManager.updateDriver(
                    Long.valueOf(request.getParameter("idnumber")),
                    request.getParameter("forenameC"),
                    request.getParameter("surnameC"),
                    request.getParameter("registrationC"),
                    dbBean);

            //-------------------------------ADD DRIVER----------------------------------
            //} else if (null != request.getParameter("buttonB_click")) {
        } else if (x.equals("newdriver") == true) {

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

            //-----------------------------REMOVE DRIVER----------------------------
        } else if (x.equals("removedriver")) {
            //Wait for method
            //DriverManager.removeDriver(request.setAttribute());
            //Driver selectedDriver = DriverManager.getDriver(driverId, dbBean);
            //softDelDriver = DriverManager.softRemoveDriver(driverId, dbBean);
            //}

            DriverManager.softRemoveDriver(
                    Long.valueOf(request.getParameter("id")),
                    dbBean);

        }
        response.sendRedirect(returnPage);
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
