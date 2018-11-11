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
    static String userTypesTableName = "UserType";
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
     * If the username of password is invalid then -1 is returned.
     */
    public static int loginUser(String user, String pass, Jdbc jdbc){
        ArrayList<HashMap<String, String>> results;
        HashMap<String, String> userDBInfo;
        
        // Retrieve user information from the DB
        results = jdbc.retrieve(userTableName, "USERNAME", user);        
        
        // If no user was found with given username then return failure
        if (results.isEmpty()){
            return -1;
        }
        
        // Ensure only one user exists with the given user name. Usernames are
        // unique so this should never happen.
        if (results.size() > 1){
            throw new RuntimeException("more than one user exists with the " +
                                       "same username");
        }
        
        // Should only be one result, so index the first
        userDBInfo = results.get(0);
        
        // Check the password is correct
        if (userDBInfo.get("PASSWORD").equals(pass) == false){
            return -1;
        }
        
        return Integer.valueOf(userDBInfo.get("ID"));
    }

    /*
     * Queriues the DB for information about a user. All information is returned
     * as a User object.
     */
    public static User getUser(int userID, Jdbc jdbc){
        HashMap<String, String> userDBInfo;
        ArrayList<HashMap<String, String>> userTypeOpts;
        ArrayList<HashMap<String, String>> userStatusOpts;
        String userTypeName = null;
        String userStatusName = null;

        // Retrieve user information from the DB. Note ID is primary key so
        // their should only ever be one result.
        userDBInfo = jdbc.retrieve(userTableName, userID).get(0);

        // Identify the name of the user type for user
        userTypeOpts = jdbc.retrieve(userTypesTableName);
        for (HashMap<String, String> row: userTypeOpts){
            if (row.get("ID").equals(userDBInfo.get("USERTYPEID"))){
                userTypeName = row.get("USERTYPE");
            }
        }
        
        // Identify the name of status set for the user
        userStatusOpts = jdbc.retrieve(userStatusTableName);
         for (HashMap<String, String> row: userStatusOpts){
            if (row.get("ID").equals(userDBInfo.get("USERSTATUSID"))){
                userStatusName = row.get("STATUS");
            }
        }       
        
        // Initialize and return User object
        return new User(
                userID,
                userDBInfo.get("USERNAME"),
                userDBInfo.get("PASSWORD"),
                new GenericItem(Integer.valueOf(userDBInfo.get("USERTYPEID")),
                        userTypeName),
                new GenericItem(Integer.valueOf(userDBInfo.get("USERSTATUSID")),
                        userStatusName));
    }
    /*
     * Queries the database to check the status of a user account 
     */
//    public static int getUserAccountStatus(User user, Jdbc jdbc){
//        
//    }
}
