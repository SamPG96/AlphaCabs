/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tableclasses;

/**
 *
 * @author Sam
 */
public class User {
    public static final String TABLE_NAME_USERS = "Users";
    private long id;
    private String username;
    private String password;
    private GenericItem userType;
    private Customer customer;
    private Driver driver;
    private GenericItem userStatus;

    /*
    * Empty constructor
    */
    public User() {
    }
    
    /*
    * Constructor that takes information about a User that is common to all user
    * types.
    */
    public User(long id, String username, String password, GenericItem userType,
            GenericItem userStatus) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.customer = null;
        this.driver = null;
        this.userStatus = userStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GenericItem getUserType() {
        return userType;
    }

    public void setUserType(GenericItem userType) {
        this.userType = userType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public long getCustomerId() {
        if(customer != null){
            return customer.getId();
        }
        return 0;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    
    public long getDriverId() {
        if(driver != null){
            return driver.getId();
        }
        return 0;
    }

    public GenericItem getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(GenericItem userStatus) {
        this.userStatus = userStatus;
    }
    
    
}
