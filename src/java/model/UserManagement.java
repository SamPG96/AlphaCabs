/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.tableclasses.User;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sam
 */
public class UserManagement {
    static String userTableName = "Users";
    
    public static void newCustomer(String username, String pass, String name,
                                   String address, Jdbc jdbc){
        
    }

    public static void newDriver(String username, String pass, String name,
                                 String vehicleReg, Jdbc jdbc){
        
    }
    
    public static void updateUserDetails(User user, Jdbc jdbc){
        
    }
    
    /*
     * Verify a users login details and return a User object than represents
     * their user entity. If the username of password is invalid then null is
     * returned.
     */
    public static User loginUser(String user, String pass, Jdbc jdbc){
        User loggedInUser;
        String existanceFieldsToCheck[] = {"Username", "Password"};
        String existanceFieldVals[] = {user, pass};
        Map <String, String> userDBInfo = new HashMap<>();
        
        // Checks if user exists and its password is valid.
        if (jdbc.exists(
                this.userTableName,
                existanceFieldsToCheck,
                existanceFieldVals) == false){
            return null;
        };
        
        // Retrieve information on the user and feed it to a class that
        // represents its user type.
        userDBInfo = jdbc.retrieve(this.userTableName, user);
        
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
    public static int getUserAccountStatus(User user, Jdbc jdbc){
        
    }
}
