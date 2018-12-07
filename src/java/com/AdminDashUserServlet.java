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
import model.UserManager;
import model.Jdbc;
import model.tableclasses.User;
import model.UserManager;
import model.tableclasses.GenericItem;

/**
 *
 * @author jakec
 */
public class AdminDashUserServlet extends HttpServlet {

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

        HttpSession session = request.getSession(false);

        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        User[] aUser = UserManager.getAllUsers(jdbc);

        String message = "";
        String name = "";
        for (User user : aUser) {
            message += "<tr>";
            message += "<td class=\"username\">" + user.getUsername() + "</td>";
            message += "<td>" + user.getUserType().getName() + "</td>";

            //Find name
            switch ((int) user.getUserType().getId()) {
                case 1://Admin
                    name = "N/A";
                    break;
                case 2://Driver
                    name = user.getDriver().getFirstName() + " "
                            + user.getDriver().getLastName();
                    break;
                case 4://Customer
                    name = user.getCustomer().getFirstName() + " "
                            + user.getCustomer().getLastName();
                    break;
            }
            message += "<td>" + name + "</td>";

            message += "<td>" + user.getUserStatus().getName() + "</td>";
            if (user.getUserStatus().getName().equals("Unapproved")) {
                message +="<td><button class=\"btn\" onclick=\"getUser(this)\"
                data-userid=" + user.getId() + " data-userstatus=" +
                user.getUserStatus().getName() + ">Approve</button></td>";
            }else {
                message +="<td><button class=\"btn\" onclick=\"getUser(this)\"
                data-userid=" + user.getId() + " data-userstatus=" +
                user.getUserStatus().getName() + ">Unapprove</button></td>";
            }

            message += "</tr>";
        }
        request.setAttribute("userTable", message);

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

        String userId = request.getParameter("userid");

        String userStatus = request.getParameter("userstatus");

        long longid = Long.parseLong(userId);


        if (userStatus.equals("Unapproved")) {
           UserManager.approveUser(longid, jdbc);
        }else{
           UserManager.unapproveUser(longid, jdbc);
        }


        request.getRequestDispatcher("index.jsp").forward(request, response);
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
