/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
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
    public static final int NO_USER_FIRST_NAME_ERR_CODE = -1;
    public static final int NO_USER_LAST_NAME_ERR_CODE = -2;
    public static final int NO_USER_PASSWORD_ERR_CODE = -3;
    public static final int PASSWORDS_DONT_MATCH_ERR_CODE = -4;

    /*
    * Set a new status for the user account
    */
    public static int changeUserStatus(long userId, GenericItem newStatus, Jdbc jdbc){
        User user;
        
        user = getUser(userId, jdbc);
        
        if (user == null){
            // User could not be found
            return -1;
        }
        
        // Retrieve existing info about the user and replace the status of the
        // user to active.
        user.setUserStatus(newStatus);
        
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
        user.setUserType(getUserTypeObj(4, jdbc));
        
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
        user.setUserType(getUserTypeObj(2, jdbc));
        
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

        user.setUserStatus(userStatus);
        
        // Validate parameters for the new user account
        err = validateUserAttribs(userFirstName, userLastName, password,
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
    public static int validateUserAttribs(String userFirstName,
            String userLastName, String password, String passwordConfirm){
        if (userFirstName == null || userFirstName.isEmpty()){
            return NO_USER_FIRST_NAME_ERR_CODE;
        }
        if (userLastName == null || userLastName.isEmpty()){
            return NO_USER_LAST_NAME_ERR_CODE;
        }
        else if (password == null || password.isEmpty()){
            return NO_USER_PASSWORD_ERR_CODE;
        }
        else if (passwordConfirm == null || password.equals(passwordConfirm) == false){
            return PASSWORDS_DONT_MATCH_ERR_CODE;
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
     * Login users of any type with their login details and return the
     * associated ID of the user. If the user is unrecognised or incorrect
     * details have been given then an error code is returned.
     */
    public static long loginAnyUserType(String user, String pass, Jdbc jdbc){
        ArrayList<GenericItem> allowedUserTypes;
        
        allowedUserTypes = new ArrayList<>(Arrays.asList(UserManager.getAllUserTypes(jdbc)));
        
        return loginSpecificUserTypes(user, pass, allowedUserTypes, jdbc);
    }

    /*
     * Login users of a specific type with their login details and return the
     * associated ID of the user. If the user is unrecognised or incorrect
     * details have been given or a user was found but is not of the given types
     * then an error code is returned.
     */
    public static long loginSpecificUserTypes(String userName, String pass,
            ArrayList<GenericItem> validTypes, Jdbc jdbc){
        ArrayList<HashMap<String, String>> results;
        HashMap<String, String> userDBInfo;
        boolean userTypeIsValid;
        User user;
        
        // Retrieve user information from the DB
        results = jdbc.retrieve(TABLE_NAME_USERS, "USERNAME", userName);        
        
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
        
        user = getUser(Long.valueOf(userDBInfo.get("ID")), jdbc);
        
        // Incorrect login attempt if the password is correct
        if (user.getPassword().equals(pass) == false){
            return -1;
        }
        
        // Identify the type of user and return an error if their type is not
        // allowed to sign in.
        userTypeIsValid = false;
        for (GenericItem validType: validTypes){
            if (validType.getId() == user.getUserType().getId()){
                userTypeIsValid = true;
                break;
            }
        }
        
        // Return an error if the user is infact of a non valid type
        if (userTypeIsValid == false){
            return -1;
        }
        
        // Do not allow non active users to sign in
        if (user.getUserStatus().getId() != 2){
            return -1;
        }
        
        return user.getId();        
    }
    
    /*
    * Return all user types
    */
    public static GenericItem[] getAllUserTypes(Jdbc jdbc){
        ArrayList<HashMap<String, String>> results;
        GenericItem[] allUserTypes;
        int i;
        
        results = jdbc.retrieve(TABLE_NAME_USERTYPE);
        allUserTypes = new GenericItem[results.size()];
        
        i = 0;
        for (HashMap<String, String> row: results){
            allUserTypes[i++] = new GenericItem(
                    Long.valueOf(row.get("ID")),
                    row.get("USERTYPE")
            );
        }   
        
        return allUserTypes;
    }
    
    /*
    * Create a generic item object for a user type ID
    */
    public static GenericItem getUserTypeObj(long typeId, Jdbc jdbc){
        ArrayList<HashMap<String, String>> results;
        HashMap<String, String> userTypeMap;
        
        results = jdbc.retrieve(TABLE_NAME_USERTYPE, typeId);
        
        userTypeMap = results.get(0);
        
        return new GenericItem(
                Long.valueOf(userTypeMap.get("ID")),
                userTypeMap.get("USERTYPE"));
    }

    /*
    * Create a generic item object for a user status ID
    */
    public static GenericItem getUserStatusObj(long statusId, Jdbc jdbc){
        ArrayList<HashMap<String, String>> results;
        HashMap<String, String> userStatusMap;
        
        results = jdbc.retrieve(TABLE_NAME_USERSTATUS, statusId);
        
        userStatusMap = results.get(0);
        
        return new GenericItem(
                Long.valueOf(userStatusMap.get("ID")),
                userStatusMap.get("STATUS"));
    }
    
    /*
    * Return a list of all users in the database
    */
    public static User[] getAllUsers(Jdbc jdbc){
        ArrayList<HashMap<String, String>> usersMap;
        User[] userArr;
        
        usersMap = jdbc.retrieve(User.TABLE_NAME_USERS);
        userArr = new User[usersMap.size()];

        int i = 0;

        // Map each row to a user object
        for (HashMap<String, String> map : usersMap) {          
            userArr[i++] = generateUserObj(map, jdbc);
        }   
        
        return userArr;
    }
    
    /*
     * Queriues the DB for information about a user. All information is returned
     * as a User object.
     */
    public static User getUser(long userID, Jdbc jdbc){
        HashMap<String, String> userDBInfo;

        // Retrieve user information from the DB. Note ID is primary key so
        // their should only ever be one result.
        ArrayList<HashMap<String, String>> results = jdbc.retrieve(TABLE_NAME_USERS, userID);
        userDBInfo = results.get(0);    
        
        return generateUserObj(userDBInfo, jdbc);
    }
    
    /*
    * Retrieve a user by a driver ID.
    */
    public static User getUserByDriverId(long driverId, Jdbc jdbc){
        ArrayList<HashMap<String, String>> results;
        
        results = jdbc.retrieve(TABLE_NAME_USERS, "DRIVERID", driverId);
        
        return getUser(Long.valueOf(results.get(0).get("ID")), jdbc);
    }
    
    /*
    * Generates a user object from the output of the DB
    */
    private static User generateUserObj(HashMap<String, String> dbMap, Jdbc jdbc){
        User user;
        
        // Initialize User object
        user = new User(
                Long.valueOf(dbMap.get("ID")),
                dbMap.get("USERNAME"),
                dbMap.get("PASSWORD"),
                getUserTypeObj(Long.valueOf(dbMap.get("USERTYPEID")), jdbc),
                getUserStatusObj(Long.valueOf(dbMap.get("USERSTATUSID")), jdbc));
        
        if (user.getUserType().getId() == 2){
            user.setDriver(DriverManager.getDriver(
                    Long.valueOf(dbMap.get("DRIVERID")), jdbc));
        }
        else if (user.getUserType().getId() == 4){
            user.setCustomer(CustomerManager.getCustomer(
                    Long.valueOf(dbMap.get("CUSTOMERID")), jdbc));
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
    * Retrieves the username of a driver.
    */
    public static String getUsernameForDriver(long driverId, Jdbc jdbc){
        ArrayList<HashMap<String, String>> results;
        results = jdbc.retrieve(
                TABLE_NAME_USERS,
                "DRIVERID",
                driverId);
        
        if (results.isEmpty()){
            // Cannot find a user with the given customerID
            return null;
        }
        
        // Fetch the first result (their should only be one) and return the
        // username
        return results.get(0).get("USERNAME");
    }

}
