/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;

/**
 *
 * @author Sam
 */
public abstract class User {
    private final String userID;
    private final int userType;

    public User(String userID, int userType) {
        this.userID = userID;
        this.userType = userType;
    }

    public String getUserID() {
        return userID;
    }
    
    public int getUserType() {
        return userType;
    }
    
}
