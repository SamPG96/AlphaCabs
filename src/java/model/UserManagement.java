/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sam
 */
public class UserManagement {
    Jdbc jdbc;
    String userTableName;

    public UserManagement(Jdbc jdbc) {
        this.jdbc = jdbc;
        this.userTableName = "Users";
    }
    
    public void newCustomer(String username, String pass, String name,
                                String address){
        
    }

    public void newDriver(String username, String pass, String name,
                              String vehicleReg){
        
    }
    
    public void updateUserDetails(User user){
        
    }
    
    /*
     * Verify a users login details and return a User object than represents
     * their user entity. If the username of password is invalid then null is
     * returned.
     */
    public User loginUser(String user, String pass){
        User loggedInUser;
        String existanceFieldsToCheck[] = {"Username", "Password"};
        String existanceFieldVals[] = {user, pass};
        Map <String, String> userDBInfo = new HashMap<>();
        
        // Checks if user exists and its password is valid.
        if (this.jdbc.exists(
                this.userTableName,
                existanceFieldsToCheck,
                existanceFieldVals) == false){
            return null;
        };
        
        // Retrieve information on the user and feed it to a class that
        // represents its user type.
        userDBInfo = this.jdbc.retrieve(this.userTableName, user);
        
        switch (Integer.parseInt(userDBInfo.get("UserType"))){
            case 1:
                loggedInUser = new AdminUser(
                        userDBInfo.get("Id"),
                        1
                );
                break;
                
            case 2:
                loggedInUser = new DriverUser(
                        userDBInfo.get("DriverId"),
                        userDBInfo.get("Id"),
                        2
                );
                break;

            case 3:
                loggedInUser = new CustomerUser(
                        userDBInfo.get("CustomerId"),
                        userDBInfo.get("Id"),
                        2
                );
                break;
            
            default:
                throw new RuntimeException("user ID not valid");
        }
        
        return loggedInUser;
    }
    
    /*
     * Queries the database to check the status of a user account 
     */
    public int getUserAccountStatus(User user){
        
    }
}
