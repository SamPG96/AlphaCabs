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
import model.AdminManager;
import model.Jdbc;
import model.tableclasses.Configuration;
import model.tableclasses.User;

/**
 *
 * @author Tom
 */
public class AdminDashSettingsServlet extends HttpServlet {

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

        // Connect Jdbc to the DB
        //Jdbc dbBean = new Jdbc();
        //dbBean.connect((Connection) sc.getAttribute("connection"));
        Configuration[] allConfigs = AdminManager.getConfigurations(jdbc);

        String table = "<tr>\n"
                + "                    <th>Config ID</th>\n"
                + "                    <th>Config Name</th>\n"
                + "                    <th>Config Value</th>\n"
                + "                </tr>";

        for (Configuration config : allConfigs) {

            table += "<tr>";
            table += "<td>" + config.getId() + "</td>";
            table += "<td>" + config.getConfigName() + "</td>";
            table += "<td>" + config.getConfigValue() + "</td>";
            table += "</tr>";
        }

        request.setAttribute("configTable", table);

        request.getRequestDispatcher("index.jsp").forward(request, response);

        //allConfigs = AdminManager.getConfigurations(dbBean);
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

        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        String x = request.getParameter("price_change");

        AdminManager.updatePricePerMile(
                Double.valueOf(request.getParameter("newValue")),
                jdbc);

        response.sendRedirect(returnPage);
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
