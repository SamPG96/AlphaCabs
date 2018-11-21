/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static model.CustomerManager.addNewCustomer;

/**
 *
 * @author Sam
 */
public class TesterTOREMOVE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String dbpath = "jdbc:derby://localhost:1527/alphacabsdb";
        Connection conn;
        Jdbc jdbc = new Jdbc();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection(dbpath, "ateam", "ateam");
            jdbc.connect(conn);

        } catch (ClassNotFoundException | SQLException e) {
            // unable to connect to database 
            System.out.println("BROKEN!");
        }
        
        long resp = addNewCustomer(
                        "Dave",
                        "Watson",
                        "Some house",
                        "pass",
                        "pass",
                        jdbc);

        System.out.println("CUSTOMER ID: " + resp);
        
        String username = UserManager.getUsernameForCustomer(resp, jdbc);
        System.out.println("USERNAME: " + username);
    }
    
}
