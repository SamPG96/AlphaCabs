/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tableclasses;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Conor
 */
public class Booking {
    private long id;
    private User customer;
    private User driver;
    private String sourceAddress;
    private String destinationAddress;
    private double distanceKM;
    private Timestamp timeBooked;
    private Timestamp timeArrived;
    private GenericItem bookingStatus;

    public Booking() {
    }
    
    public Booking(long id, User customer, String sourceAddress, String destinationAddress, Timestamp timeBooked, GenericItem bookingStatus) {
        this.id = id;
        this.customer = customer;
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.timeBooked = timeBooked;
        this.bookingStatus = bookingStatus;
    }

    //public Booking(ResultSet resultSet) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
        
        //this.id = resultSet.getLong("id");
        //CUSTOMER OR CUSTOMER ID?
        //The database has customer id, but this constructor has customer as User type
        //this.customer = resultSet.getUser("customer");
        
    //}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public double getDistanceKM() {
        return distanceKM;
    }

    public void setDistanceKM(double distanceKM) {
        this.distanceKM = distanceKM;
    }

    public Timestamp getTimeBooked() {
        return timeBooked;
    }

    public void setTimeBooked(Timestamp timeBooked) {
        this.timeBooked = timeBooked;
    }

    public Timestamp getTimeArrived() {
        return timeArrived;
    }

    public void setTimeArrived(Timestamp timeArrived) {
        this.timeArrived = timeArrived;
    }

    public GenericItem getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(GenericItem bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    
    
}
