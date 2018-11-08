/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContext;
import model.UserManagement;

/**
 * Web application lifecycle listener.
 *
 * @author me-aydin
 */
@WebListener()
public class AlphacabListener implements ServletContextListener {

    private Connection conn = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        String db = sc.getInitParameter("dbname");
        String dbpath = "jdbc:derby://localhost:1527/" + db.trim();
        String dbuser = sc.getInitParameter("dbuser");
        String dbpass = sc.getInitParameter("dbpass");

        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // TODO: make user and password variable
            conn = DriverManager.getConnection(dbpath, dbuser, dbpass);

        } catch (ClassNotFoundException | SQLException e) {
            sc.setAttribute("error", e);
        }
        sc.setAttribute("connection", conn);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            conn.close();
        } catch (SQLException e) {
        }
    }
}
