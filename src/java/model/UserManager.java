/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import model.tableclasses.User;
import java.util.HashMap;
import model.tableclasses.Customer;
import model.tableclasses.Driver;
import model.tableclasses.GenericItem;
import static model.tableclasses.GenericItem.TABLE_NAME_USERSTATUS;
import static model.tableclasses.GenericItem.TABLE_NAME_USERTYPE;
import static model.tableclasses.User.TABLE_NAME_USERS;

/**
 *
 * @author Sam
 */
public class UserManager {
    public static final long noFirstNameErrCode = -1;
    public static final long noLastNameErrCode = -2;
    public static final long noPasswordErrCode = -3;
    public static final int passwordsDontMatchErrCode = -4;

    /*
    * Set the status of a user account to 'active'
    */
    public static int approveUser(long userId, Jdbc jdbc){
        User user;
        
        user = getUser(userId, jdbc);
        
        if (user == null){
            // User could not be found
            return -1;
        }
        
        // Retrieve existing info about the user and replace the status of the
        // user to active.
        user.setUserStatus(new GenericItem(2));
        
        // Update the database
        jdbc.update(user);
        
        return 0;
    }
    
    /*
     * Creates a user account for a customer. The ID of the new user is returned
     * or an error code if their is an issue with the paramers.
     */
    public static long newCustomerUser(String password, String passwordConfirm,
            long customerID, GenericItem userStatus, Jdbc jdbc){
        User user;
        Customer customer;
        
        user = new User();
        user.setPassword(password);
        user.setUserType(new GenericItem(4, "Customer"));
        
        customer = CustomerManager.getCustomer(customerID, jdbc);
        user.setCustomer(customer);
        
        return newUser(user, customer.getFirstName(), customer.getLastName(),
                password, passwordConfirm, userStatus, jdbc);
    }

    /*
     * Creates a user account for a driver. The ID of the new user is returned
     * or an error code if their is an issue with the paramers.
     */
    public static long newDriverUser(String password, String passwordConfirm,
            long driverId, GenericItem userStatus, Jdbc jdbc){
        User user;
        Driver driver;
        
        user = new User();
        user.setPassword(password);
        user.setUserType(new GenericItem(2, "Driver"));
        
        driver = DriverManager.getDriver(driverId, jdbc);
        user.setDriver(driver);
        
        return newUser(user, driver.getFirstName(), driver.getLastName(),
                password, passwordConfirm, userStatus, jdbc);
    }
    
    /*
     * Creates a new user in the database. The ID of the new user is returned or
     * an error code if their is an issue with the paramers.
     */
    public static long newUser(User user, String userFirstName,
            String userLastName, String password, String passwordConfirm,
            GenericItem userStatus, Jdbc jdbc){
        long err;

        // TODO: set user status here!
        user.setUserStatus(userStatus);
        
        // Validate parameters for the new user account
        err = validateNewUserAttribs(userFirstName, userLastName, password,
                passwordConfirm);
        if (err < 0){
            return err;
        }
        
        // Generate a username for the new User
        user.setUsername(generateUsername(userFirstName, userLastName, jdbc));
        
        return jdbc.insert(user);
    }    

    /*
     * Validates parameters required for a new user account.
     */
    public static long validateNewUserAttribs(String userFirstName,
            String userLastName, String password, String passwordConfirm){
        if (userFirstName.isEmpty()){
            return noFirstNameErrCode;
        }
        if (userLastName.isEmpty()){
            return noLastNameErrCode;
        }
        else if (password.isEmpty()){
            return noPasswordErrCode;
        }
        else if (password.equals(passwordConfirm) == false){
            return passwordsDontMatchErrCode;
        }   
        return 0;
    }
    
    /*
     * Generate a username from a first and last name
     */
    public static String generateUsername(String firstName, String lastName,
                                          Jdbc jdbc){
        int counter = 0;
        String username;
        String usernameWithInt;

        // Generate a username with the first letter of the firstname and
        // the entire lastname
        username = (firstName.substring(0,1).toLowerCase() +
                lastName.toLowerCase());
        
        if (usernameExists(username, jdbc) == false){
            return username;
        }
        
        // The username exists so add and increment an integer to the end
        // of the username until it is unique.
        do {
            usernameWithInt = username + String.valueOf(counter);
            counter++;

        }
        while (usernameExists(usernameWithInt, jdbc) == true);
        
        return usernameWithInt;
    }
    
    /*
     * Checks if the username exists in the DB
     */
    private static boolean usernameExists(String username, Jdbc jdbc){
        ArrayList<HashMap<String, String>> results;
        
        // Query the DB for the existance of the username
        results = jdbc.retrieve(TABLE_NAME_USERS, "USERNAME", username); 
        
        return results.isEmpty() == false;
    }
    
    /*
     * Verify a users login details and return the associated ID of the user,
     * If the username of password is invalid then -1 is returned.
     */
    public static long loginUser(String user, String pass, Jdbc jdbc){
        ArrayList<HashMap<String, String>> results;
        HashMap<String, String> userDBInfo;
        
        // Retrieve user information from the DB
        results = jdbc.retrieve(TABLE_NAME_USERS, "USERNAME", user);        
        
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
        
        return Long.valueOf(userDBInfo.get("ID"));
    }

    /*
     * Queriues the DB for information about a user. All information is returned
     * as a User object.
     */
    public static User getUser(long userID, Jdbc jdbc){
        HashMap<String, String> userDBInfo;
        ArrayList<HashMap<String, String>> userTypeOpts;
        ArrayList<HashMap<String, String>> userStatusOpts;
        String userTypeName = null;
        String userStatusName = null;
        User user;

        // Retrieve user information from the DB. Note ID is primary key so
        // their should only ever be one result.
        ArrayList<HashMap<String, String>> results = jdbc.retrieve(TABLE_NAME_USERS, userID);
        userDBInfo = results.get(0);

        // Identify the name of the user type for user
        userTypeOpts = jdbc.retrieve(TABLE_NAME_USERTYPE);
        for (HashMap<String, String> row: userTypeOpts){
            if (row.get("ID").equals(userDBInfo.get("USERTYPEID"))){
                userTypeName = row.get("USERTYPE");
            }
        }
        
        // Identify the name of status set for the user
        userStatusOpts = jdbc.retrieve(TABLE_NAME_USERSTATUS);
         for (HashMap<String, String> row: userStatusOpts){
            if (row.get("ID").equals(userDBInfo.get("USERSTATUSID"))){
                userStatusName = row.get("STATUS");
            }
        }       
        
        // Initialize User object
        user = new User(
                userID,
                userDBInfo.get("USERNAME"),
                userDBInfo.get("PASSWORD"),
                new GenericItem(Integer.valueOf(userDBInfo.get("USERTYPEID")),
                        userTypeName),
                new GenericItem(Integer.valueOf(userDBInfo.get("USERSTATUSID")),
                        userStatusName));
        
        if (user.getUserType().getId() == 2){
            user.setDriver(DriverManager.getDriver(
                    Long.valueOf(userDBInfo.get("DRIVERID")), jdbc));
        }
        else if (user.getUserType().getId() == 4){
            user.setCustomer(CustomerManager.getCustomer(
                    Long.valueOf(userDBInfo.get("CUSTOMERID")), jdbc));
        }
        
        return user;
    }
    
    /*
    * Retrieves the username of a customer.
    */
    public static String getUsernameForCustomer(long customerId, Jdbc jdbc){
        ArrayList<HashMap<String, String>> results;
        results = jdbc.retrieve(
                TABLE_NAME_USERS,
                "CUSTOMERID",
                customerId);
        
        if (results.isEmpty()){
            // Cannot find a user with the given customerID
            return null;
        }
        
        // Fetch the first result (their should only be one) and return the
        // username
        return results.get(0).get("USERNAME");
    }
    /*
     * Queries the database to check the status of a user account 
     */
//    public static int getUserAccountStatus(User user, Jdbc jdbc){
//        
//    }
}
