/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tableclasses;

/**
 *
 * @author Conor
 */
public class Driver {
    public static final String TABLE_NAME_DRIVERS = "Drivers";
    private long id;
    private String firstName;
    private String lastName;
    private String registration;
    
    /*
    * Constructor that takes all driver information apart from the driver ID
    */
    public Driver(String firstName, String lastName, String registration) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registration = registration;
    }

    /*
    * Constructor that takes all driver information
    */
    public Driver(long id, String firstName, String lastName, String registration) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registration = registration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
    
    
}
