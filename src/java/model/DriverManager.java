/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import model.tableclasses.Driver;


/**
 *
 * @author Sam
 */
public class DriverManager {
    public static int noDriverFirstNameErrCode = -20;
    public static int noDriverLastNameErrCode = -21;
    public static int noDriverRegistrationErrCode = -22;
    
    /*
    * Add a new record driver to the DB
    */
    public static long addNewDriver(String firstName, String lastName,
            String registration, Jdbc jdbc){
        Driver driver;
        long driverId;
        
        // Check parameters for a new driver.
        if (firstName.isEmpty()){
            return noDriverFirstNameErrCode;
        }
        else if (lastName.isEmpty()){
            return noDriverLastNameErrCode;
        }
        else if (registration.isEmpty()){
            return noDriverRegistrationErrCode;
        }     
        
        // All parameters are valid if this point is reached.
        
        // Create an entry in the database for the new driver
        driver = new Driver(firstName, lastName, registration);
        driverId = jdbc.insert(driver);
        driver.setId(driverId);
        
        return driverId;
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
}
