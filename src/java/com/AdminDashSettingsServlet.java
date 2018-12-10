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
import model.Helper;
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

        double pricePerMile, shortDistPrice, shortDist, vat;

        String ppm = (String) request.getParameter("pricePerMile");
        String sdp = (String) request.getParameter("shortDistPrice");
        String sd = (String) request.getParameter("shortDist");
        String sVat = (String) request.getParameter("vat");

        if (ppm == null) {
            pricePerMile = AdminManager.getPricePerMile(jdbc);
            shortDistPrice = AdminManager.getShortDistPrice(jdbc);
            shortDist = AdminManager.getShortDistance(jdbc);
            vat = AdminManager.getVAT(jdbc);
        } else {
            pricePerMile = (double) Double.valueOf(ppm);
            shortDistPrice = (double) Double.valueOf(sdp);
            shortDist = (double) Double.valueOf(sd);
            vat = (double) Double.valueOf(sVat);

            AdminManager.updatePricePerMile(pricePerMile, jdbc);
            AdminManager.updateShortDistPrice(shortDistPrice, jdbc);
            AdminManager.updateShortDistance(shortDist, jdbc);
            AdminManager.updateVAT(vat, jdbc);
        }

        request.setAttribute("pricePerMile", Helper.doubleToTwoDecPlacesString(pricePerMile));
        request.setAttribute("shortDistPrice", Helper.doubleToTwoDecPlacesString(shortDistPrice));
        request.setAttribute("shortDist", String.valueOf(shortDist));
        request.setAttribute("vat", String.valueOf(vat));

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

        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        String ppm = (String) request.getParameter("pricePerMile");
        double pricePerMile = Double.valueOf(ppm);

        AdminManager.updatePricePerMile(pricePerMile, jdbc);

        //AdminManager.updatePricePerMile(
        //        Double.valueOf(request.getParameter("newValue")),
        //        jdbc);
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
