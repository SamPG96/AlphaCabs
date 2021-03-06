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
    public static final int NO_CUSTOMER_FIRST_NAME_ERR_CODE = -10;
    public static final int NO_CUSTOMER_LAST_NAME_ERR_CODE = -11;
    public static final int NO_CUSTOMER_ADDRESS_ERR_CODE = -12;
    /*
     * Creates a new customer entry in the database and generates them a user
     * account.
     */
    public static long addNewCustomer(String firstName, String lastName,
            String address, Jdbc jdbc){
        Customer customer;
        long customerId; 
        int err;
        
        // Validate parameters for the new customer entry
        err = validateNewCustomerAttribs(firstName, lastName, address);
        if (err < 0){
            return err;
        }
        // Create an entry in the database for the new customer
        customer = new Customer(firstName, lastName, address);
        customerId = jdbc.insert(customer);
        customer.setId(customerId);
        
        return customerId;
    }
 
    /*
     * Validates parameters required for a new customer entry.
     */
    public static int validateNewCustomerAttribs(String firstName,
            String lastName, String address){
        // Check parameters for a new customer.
        if (firstName == null || firstName.isEmpty()){
            return NO_CUSTOMER_FIRST_NAME_ERR_CODE;
        }
        else if (lastName == null || lastName.isEmpty()){
            return NO_CUSTOMER_LAST_NAME_ERR_CODE;
        }
        else if (address == null || address.isEmpty()){
            return NO_CUSTOMER_ADDRESS_ERR_CODE;
        }    
        return 0;
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
    
    /*
    * Return a list of all customers in the database
    */
    public static Customer[] getAllCustomers(Jdbc jdbc){
        ArrayList<HashMap<String, String>> customersMap = jdbc.retrieve(Customer.TABLE_NAME_CUSTOMERS);
        Customer[] customerArr = new Customer[customersMap.size()];

        int i = 0;

        // Map each row to a customer object
        for (HashMap<String, String> map : customersMap) {
            customerArr[i++] = new Customer(Long.parseLong(map.get("ID")),
                     map.get("FIRSTNAME"),
                     map.get("LASTNAME"),
                     map.get("ADDRESS"));
        }   
        
        return customerArr;
    }
}
