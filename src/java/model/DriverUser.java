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
public class DriverUser extends User{
    private final String driverID;

    public DriverUser(String driverID, String userID, int userType) {
        super(userID, userType);
        this.driverID = driverID;
    }

    public String getDriverID() {
        return driverID;
    }
    
}
