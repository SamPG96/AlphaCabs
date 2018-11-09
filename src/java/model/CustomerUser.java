/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Sam
 */
public class CustomerUser extends User{
    private final String customerID;

    public CustomerUser(String customerID, String userID, int userType) {
        super(userID, userType);
        this.customerID = customerID;
    }

    public String getCustomerID() {
        return customerID;
    }
    
    
}
