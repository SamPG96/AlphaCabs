/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tableclasses;
import java.util.Date;  

/**
 *
 * @author Conor
 */
public class Booking {
    private int id;
    private User customer;
    private User driver;
    private String sourceAddress;
    private String destinationAddress;
    private double distanceKM;
    private Date timeBooked;
    private Date timeArrived;
    private GenericItem bookingStatus;

    public Booking() {
    }
    
    public Booking(int id, User customer, String sourceAddress, String destinationAddress, Date timeBooked, GenericItem bookingStatus) {
        this.id = id;
        this.customer = customer;
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.timeBooked = timeBooked;
        this.bookingStatus = bookingStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getTimeBooked() {
        return timeBooked;
    }

    public void setTimeBooked(Date timeBooked) {
        this.timeBooked = timeBooked;
    }

    public Date getTimeArrived() {
        return timeArrived;
    }

    public void setTimeArrived(Date timeArrived) {
        this.timeArrived = timeArrived;
    }

    public GenericItem getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(GenericItem bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    
    
}
