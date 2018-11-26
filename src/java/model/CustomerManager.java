/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import model.tableclasses.Customer;

/**
 *
 * @author Sam
 */
public class CustomerManager {
    public static int NO_CUSTOMER_FIRST_NAME_ERR_CODE = -10;
    public static int NO_CUSTOMER_LAST_NAME_ERR_CODE = -11;
    public static int NO_CUSTOMER_ADDRESS_ERR_CODE = -12;
    /*
     * Creates a new customer entry in the database and generates them a user
     * account.
     */
    public static long addNewCustomer(String firstName, String lastName,
            String address, Jdbc jdbc){
        Customer customer;
        long customerId;
        
        // Check parameters for a new customer.
        if (firstName.isEmpty()){
            return NO_CUSTOMER_FIRST_NAME_ERR_CODE;
        }
        else if (lastName.isEmpty()){
            return NO_CUSTOMER_LAST_NAME_ERR_CODE;
        }
        else if (address.isEmpty()){
            return NO_CUSTOMER_ADDRESS_ERR_CODE;
        }     
        
        // All parameters are valid if this point is reached.
        
        // Create an entry in the database for the new customer
        customer = new Customer(firstName, lastName, address);
        customerId = jdbc.insert(customer);
        customer.setId(customerId);
        
        return customerId;
    }
    
    /*
    * Returns a customer record for a given customer ID in the form of a
    * customer object.
    */
    public static Customer getCustomer(long customerID, Jdbc jdbc){
        ArrayList<HashMap<String, String>> results;
        HashMap<String, String> customerRecord;
        
        results = jdbc.retrieve(Customer.TABLE_NAME_CUSTOMERS, customerID);
        
        if (results.isEmpty()){
            // No record was found with customer ID
            return null;
        }
        
        // We are retriving by ID so their should only be one item in the
        // array list.
        customerRecord = results.get(0);
        
        return new Customer(customerID,
                            customerRecord.get("FIRSTNAME"),
                            customerRecord.get("LASTNAME"),
                            customerRecord.get("ADDRESS"));
    }
}
