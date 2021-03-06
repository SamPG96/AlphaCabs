/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tableclasses;
import java.sql.Timestamp;

/**
 *
 * @author Conor
 */
public class Booking {
    public static final String TABLE_NAME_BOOKINGS = "Bookings";
    
    private long id;
    private Customer customer;
    private Driver driver;
    private String sourceAddress;
    private String destinationAddress;
    private int numOfPassengers;
    private double distance;
    private double FareExcVAT;
    private double FareIncVAT;
    private Timestamp timeBooked;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private GenericItem bookingStatus;

    public Booking() {
    }

    public Booking(long id, Customer customer, Driver driver, 
            String sourceAddress, String destinationAddress, 
            int numOfPassengers, double distanceKM, double fareExcVAT, double fareIncVAT, Timestamp timeBooked, 
            Timestamp departureTime, Timestamp arrivalTime, 
            GenericItem bookingStatus) {
        this.id = id;
        this.customer = customer;
        this.driver = driver;
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.numOfPassengers = numOfPassengers;
        this.distance = distanceKM;
        this.FareExcVAT = fareExcVAT;
        this.FareIncVAT = fareIncVAT;
        this.timeBooked = timeBooked;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.bookingStatus = bookingStatus;
    }
    
    public Booking(Customer customer, String sourceAddress, 
            String destinationAddress, int numOfPassengers, 
            double distanceKM, double fareExcVAT, double fareIncVAT, Timestamp timeBooked, 
            Timestamp departureTime, GenericItem bookingStatus) {
        this.customer = customer;
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.numOfPassengers = numOfPassengers;
        this.distance = distanceKM;
        this.FareExcVAT = fareExcVAT;
        this.FareIncVAT = fareIncVAT;
        this.timeBooked = timeBooked;
        this.departureTime = departureTime;
        this.bookingStatus = bookingStatus;
    }
    
    public Booking(String sourceAddress, 
            String destinationAddress, int numOfPassengers, 
            double distanceKM, double fareExcVAT, double fareIncVAT, Timestamp timeBooked, 
            Timestamp departureTime, GenericItem bookingStatus) {
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.numOfPassengers = numOfPassengers;
        this.distance = distanceKM;
        this.FareExcVAT = fareExcVAT;
        this.FareIncVAT = fareIncVAT;
        this.timeBooked = timeBooked;
        this.departureTime = departureTime;
        this.bookingStatus = bookingStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Timestamp getTimeBooked() {
        return timeBooked;
    }

    public void setTimeBooked(Timestamp timeBooked) {
        this.timeBooked = timeBooked;
    }

    public Timestamp getTimeArrived() {
        return arrivalTime;
    }

    public void setTimeArrived(Timestamp timeArrived) {
        this.arrivalTime = timeArrived;
    }

    public GenericItem getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(GenericItem bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public void setNumOfPassengers(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getFareExcVAT() {
        return FareExcVAT;
    }

    public void setFareExcVAT(double FareExcVAT) {
        this.FareExcVAT = FareExcVAT;
    }

    public double getFareIncVAT() {
        return FareIncVAT;
    }

    public void setFareIncVAT(double FareIncVAT) {
        this.FareIncVAT = FareIncVAT;
    }

    
    
    
}