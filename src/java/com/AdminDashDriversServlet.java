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
import model.DriverManager;
import model.Jdbc;
import model.UserManager;
import static model.UserManager.NO_USER_FIRST_NAME_ERR_CODE;
import static model.UserManager.NO_USER_LAST_NAME_ERR_CODE;
import static model.UserManager.NO_USER_PASSWORD_ERR_CODE;
import static model.UserManager.PASSWORDS_DONT_MATCH_ERR_CODE;
import static model.UserManager.getUserStatusObj;
import model.tableclasses.Driver;
import model.tableclasses.GenericItem;
import model.tableclasses.User;

/**
 *
 * @author Tom
 */
public class AdminDashDriversServlet extends HttpServlet {

    private String returnPage = "index.jsp";

    private User driverUser;
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
        
        
        if (driverUser != null) {
            request.setAttribute("driverUser", driverUser);
            driverUser = null;
            request.getRequestDispatcher("adminDashUpdateDriver.jsp").forward(
                    request, response);
            //response.sendRedirect("adminDashUpdateDriver.jsp");
        } 

        //GET All Driver users
        User[] users = UserManager.getAllUsers(jdbc);
        

        String table = "";
        Driver driver;
        String driverName;
        for (User user : users) {
            driver = user.getDriver();
            if (driver == null) {
                continue;
            }

            table += "<tr>";
            table += "<td>" + driver.getId() + "</td>";
            driverName = driver.getFirstName() + " "
                    + driver.getLastName();
            table += "<td>" + driverName + "</td>";
            table += "<td>" + driver.getRegistration() + "</td>";
            table += "<td>" + user.getUserStatus().getName() + "</td>";
            //table += "<td>" + "<button class=\"btn\" onclick=\"document.location.href = 'adminDashChangeDriverDetails.jsp'\">Update</button>" + "</td>";
            //table += "<td>" + "<button class=\"btn\" onclick=\"document.location.href = 'adminDashRemoveDriver.jsp'\">Remove</button>" + "</td>";
            table += "<td><button class=\"btn\" onclick=\"getDriverUser(this)\""
                    + " data-userid=" + user.getId()
                    + " data-userstatus=" + user.getUserStatus().getName()
                    + " data-action=update"
                    + ">Update</button></td>";
            if (user.getUserStatus().getId() == 2) {//Active
                table += "<td><button class=\"btn\" onclick=\"getDriverUser(this)\""
                        + " data-userid=" + user.getId()
                        + " data-userstatus=" + user.getUserStatus().getName()
                        + " data-action=remove"
                        + ">Remove</button></td>";
            } else {
                table += "<td><button class=\"btn\" onclick=\"getDriverUser(this)\""
                        + " data-userid=" + user.getId()
                        + " data-userstatus=" + user.getUserStatus().getName()
                        + " data-action=remove"
                        + ">Reinstate</button></td>";
            }
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

        processRequest(request, response);

        HttpSession session = request.getSession(false);

        // Connect Jdbc to the DB
        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        long userId = Long.parseLong(request.getParameter("userid"));
        String userStatus = request.getParameter("userstatus");
        String action = request.getParameter("action");

        if (action.equals("update")) {
            User user = UserManager.getUser(userId, jdbc);
            this.driverUser = user;
            request.setAttribute("driverUser", user);
            request.getRequestDispatcher("adminDashUpdateDriver.jsp").forward(
                    request, response);
            response.sendRedirect("adminDashUpdateDriver.jsp");
        } else if (action.equals("remove")) {
            if (userStatus.equals("Active")) {
                UserManager.changeUserStatus(userId, new GenericItem(4), jdbc);
            } else {
                UserManager.changeUserStatus(userId, new GenericItem(2), jdbc);
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
