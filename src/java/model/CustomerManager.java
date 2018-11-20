/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.tableclasses.Customer;
import model.tableclasses.GenericItem;

/**
 *
 * @author Sam
 */
public class CustomerManager {
    static int noCustomerFirstNameErrCode = -10;
    static int noCustomerLastNameErrCode = -11;
    static int noCustomerAddressErrCode = -12;
    static int passwordsDontMatchErrCode = -13;
    /*
     * Creates a new customer entry in the database and generates them a user
     * account.
     */
    public static long addNewCustomer(String firstName, String lastName,
            String address, String password, String passwordConfirm, Jdbc jdbc){
        Customer customer;
        long customerId;
        long userId;
        long newUserErr;
        
        // Check parameters for a new customer and user account. Note a method
        // in UserManager is used for validating parameters for a new user
        // account.
        if (firstName.isEmpty()){
            return noCustomerFirstNameErrCode;
        }
        else if (lastName.isEmpty()){
            return noCustomerLastNameErrCode;
        }
        else if (address.isEmpty()){
            return noCustomerAddressErrCode;
        }
        else if (password.equals(passwordConfirm) == false){
            return passwordsDontMatchErrCode;
        }        
        newUserErr = UserManager.validateNewUserAttribs(firstName, lastName,
                password);
        if (newUserErr < 0){
            return newUserErr;
        }
        
        // All parameters are valid if this point is reached.
        
        // Create an entry in the database for the new customer
        customer = new Customer(firstName, lastName, address);
        customerId = jdbc.insert(customer);
        customer.setId(customerId);
        
        return customerId;
    }
}
