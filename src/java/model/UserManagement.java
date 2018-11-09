/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import model.tableclasses.User;
import java.util.HashMap;
import model.tableclasses.GenericItem;

/**
 *
 * @author Sam
 */
public class UserManagement {
    static String userTableName = "Users";
    static String userTypesTableName = "UserTypes";
    static String userStatusTableName = "UserStatus";
    
    public static void newCustomer(String username, String pass, String name,
                                   String address, Jdbc jdbc){
        
    }

    public static void newDriver(String username, String pass, String name,
                                 String vehicleReg, Jdbc jdbc){
        
    }
    
    public static void updateUserDetails(User user, Jdbc jdbc){
        
    }
    
    /*
     * Verify a users login details and return the associated ID of the user,
     * If the username of password is invalid then null is returned.
     */
    public static String loginUser(String user, String pass, Jdbc jdbc){
        ArrayList<HashMap<String, String>> results;
        HashMap<String, String> userDBInfo;
        
        // Retrieve user information from the DB
        results = jdbc.retrieve(userTableName, "username", user);        
        
        // Ensure only one user exists with the given user name. Usernames are
        // unique so this should never happen.
        if (results.size() != 1){
            throw new RuntimeException("more than one user ecists with the " +
                                       "same username");
        }
        
        // Should only be one result, so index the first
        userDBInfo = results.get(0);
        
        // Check the password is correct
        if (userDBInfo.get("PASSWORD").equals(pass) == false){
            return null;
        }
        
        return userDBInfo.get("usertype");
    }

    /*
     * Queriues the DB for information about a user. All information is returned
     * as a User object.
     */
    public static User getUser(String userID, Jdbc jdbc){
        HashMap<String, String> userDBInfo;
        ArrayList<HashMap<String, String>> userTypeOpts;
        ArrayList<HashMap<String, String>> userStatusOpts;
        String userTypeName = null;
        String userStatusName = null;

        // Retrieve user information from the DB. Note ID is primary key so
        // their should only ever be one result.
        userDBInfo = jdbc.retrieve(userTableName, "id", userID).get(0);

        // Identify the name of the user type for user
        userTypeOpts = jdbc.retrieve(userTypesTableName);
        for (HashMap<String, String> row: userTypeOpts){
            if (row.get("Id").equals(userDBInfo.get("UserTypeID"))){
                userTypeName = row.get("UserType");
            }
        }
        
        // Identify the name of status set for the user
        userStatusOpts = jdbc.retrieve(userStatusTableName);
         for (HashMap<String, String> row: userStatusOpts){
            if (row.get("Id").equals(userDBInfo.get("UserStatusID"))){
                userStatusName = row.get("Status");
            }
        }       
        
        // Initialize and return User object
        return new User(
                Integer.valueOf(userID),
                userDBInfo.get("username"),
                userDBInfo.get("password"),
                new GenericItem(Integer.valueOf(userDBInfo.get("UserTypeId")),
                        userTypeName),
                new GenericItem(Integer.valueOf(userDBInfo.get("UserStatusID")),
                        userStatusName));
    }
    /*
     * Queries the database to check the status of a user account 
     */
//    public static int getUserAccountStatus(User user, Jdbc jdbc){
//        
//    }
}
