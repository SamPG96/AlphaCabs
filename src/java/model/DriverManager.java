/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import static model.UserManager.NO_USER_FIRST_NAME_ERR_CODE;
import static model.UserManager.NO_USER_LAST_NAME_ERR_CODE;
import static model.UserManager.NO_USER_PASSWORD_ERR_CODE;
import static model.UserManager.PASSWORDS_DONT_MATCH_ERR_CODE;
import model.tableclasses.Driver;
import model.tableclasses.User;


/**
 *
 * @author Sam
 */
public class DriverManager {
    public static final int NO_DRIVER_FIRST_NAME_ERR_CODE = -20,
            NO_DRIVER_LAST_NAME_ERR_CODE = -21,
            NO_DRIVER_REGISTRATION_ERR_CODE = -22;
    
    /*
    * Add a new record driver to the DB
    */
    public static long addNewDriver(String firstName, String lastName,
            String registration, Jdbc jdbc){
        Driver driver;
        long driverId; 
                
        // Create an entry in the database for the new driver
        driver = new Driver(firstName, lastName, registration);
        driverId = jdbc.insert(driver);
        driver.setId(driverId);
        
        return driverId;
    }

    /*
    * Update the details of the driver.
    */
    public static void updateDriver(long driverId, String firstName,
            String lastName, String registration, Jdbc jdbc){
        Driver driver;
        
        driver = new Driver(driverId, firstName, lastName, registration);
        
        jdbc.update(driver);
    }
    
    /*
    * Soft delete a driver by setting its status to inactive.
    */
    public static void softRemoveDriver(long driverId, Jdbc jdbc){
        User user;
        
        user = UserManager.getUserByDriverId(driverId, jdbc);
        user.setUserStatus(UserManager.getUserStatusObj(4, jdbc));
        
        jdbc.update(user);
    }
    
    /*
    * Returns a driver record for a given driver ID in the form of a
    * customer object.
    */
    public static Driver getDriver(long driverID, Jdbc jdbc){
        ArrayList<HashMap<String, String>> results;
        HashMap<String, String> driverRecord;
        
        results = jdbc.retrieve(Driver.TABLE_NAME_DRIVERS, driverID);
        
        if (results.isEmpty()){
            // No record was found with driver ID
            return null;
        }
        
        // We are retriving by ID so their should only be one item in the
        // array list.
        driverRecord = results.get(0);
        
        return new Driver(driverID,
                          driverRecord.get("FIRSTNAME"),
                          driverRecord.get("LASTNAME"),
                          driverRecord.get("REGISTRATION"));
    }
    
    public static Driver getDriverByReg(String reg, Jdbc jdbc){
        Driver[] allDrivers = getAllDrivers(jdbc);
        
        for(Driver driver : allDrivers){
            if(reg.equals(driver.getRegistration())){
                return driver;
            }
        }
        return null;
     }
    
    /*
    * Return a list of all drivers in the database
    */
    public static Driver[] getAllDrivers(Jdbc jdbc){
        ArrayList<HashMap<String, String>> driversMap = jdbc.retrieve(Driver.TABLE_NAME_DRIVERS);
        Driver[] customerArr = new Driver[driversMap.size()];

        int i = 0;

        // Map each row to a driver object
        for (HashMap<String, String> map : driversMap) {
            customerArr[i++] = new Driver(Long.parseLong(map.get("ID")),
                     map.get("FIRSTNAME"),
                     map.get("LASTNAME"),
                     map.get("REGISTRATION"));
        }   
        
        return customerArr;
    }
    
    /*
     * Validates parameters required for a new user account.
     */
    public static int validateDriverAttribs(String firstName,
            String lastName, String registration, String password, String passwordConfirm){
        if (firstName == null || firstName.isEmpty()){
            return NO_USER_FIRST_NAME_ERR_CODE;
        }
        if (lastName == null || lastName.isEmpty()){
            return NO_USER_LAST_NAME_ERR_CODE;
        }
        if(registration == null || registration.isEmpty()){
            return NO_DRIVER_REGISTRATION_ERR_CODE;
        }
        if (password == null || password.isEmpty()){
            return NO_USER_PASSWORD_ERR_CODE;
        }
        if (passwordConfirm == null || !passwordConfirm.equals(password)){
            return PASSWORDS_DONT_MATCH_ERR_CODE;
        }   
        return 0;
    }
}
