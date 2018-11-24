/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import model.tableclasses.Booking;
import model.tableclasses.GenericItem;
import model.tableclasses.Customer;
import model.tableclasses.Driver;

/**
 *
 * @author Conor
 */
public class BookingManager {

    public static final int ERR_CUST_NULL = -1,
            ERR_SRC_HOME_NULL = -2,
            ERR_SRC_ADDR_NULL = -3,
            ERR_DEST_ADDR_NULL = -4,
            ERR_N_PAS_NULL = -5,
            ERR_DEP_DATE_NULL = -6,
            ERR_DEP_TIME_NULL = -7;

    private int error;

    public Booking generateNewBooking(Customer customer,
            String isSourceSameAsHome, String sourceAddress,
            String destinationAddress, String numOfPassengers,
            String departureDate, String departureTime) {

        //SET appropriate error value and return null if a param is null or empty
        if (customer == null) {
            this.error = ERR_CUST_NULL;
            return null;
        }
        if (isSourceSameAsHome == null || isSourceSameAsHome.isEmpty()) {
            this.error = ERR_SRC_HOME_NULL;
            return null;
        }
        if (sourceAddress == null || sourceAddress.isEmpty()) {
            this.error = ERR_SRC_ADDR_NULL;
            return null;
        }
        if (destinationAddress == null || destinationAddress.isEmpty()) {
            this.error = ERR_DEST_ADDR_NULL;
            return null;
        }
        if (numOfPassengers == null || numOfPassengers.isEmpty()) {
            this.error = ERR_N_PAS_NULL;
            return null;
        }
        if (departureDate == null || departureDate.isEmpty()) {
            this.error = ERR_DEP_DATE_NULL;
            return null;
        }
        if (departureTime == null || departureTime.isEmpty()) {
            this.error = ERR_DEP_TIME_NULL;
            return null;
        }
        
        //Resolve if source destination is the customers home address
        boolean isSSAH = Boolean.parseBoolean(isSourceSameAsHome);
        if (isSSAH) {
            sourceAddress = customer.getAddress();
        }
        //Resolve data types from string params
        //Num of Passengers
        int nPassengers = Integer.parseInt(numOfPassengers);
        //Departure Time
        String depDateTime = departureDate + " " + departureTime;
        Timestamp depTimestamp = Timestamp.valueOf(depDateTime);
        //Booking Status
        GenericItem bookingStatus = new GenericItem(1, "Outstanding");
        
        return new Booking(customer, sourceAddress, destinationAddress,
                nPassengers, new Timestamp(System.currentTimeMillis()),
                depTimestamp, bookingStatus);
    }

    public Booking generateNewBooking(String sourceAddress,
            String destinationAddress, String numOfPassengers, 
            String departureDate, String departureTime) {

        //SET appropriate error value and return null if a param is null or empty
        if (sourceAddress == null || sourceAddress.isEmpty()) {
            this.error = ERR_SRC_ADDR_NULL;
            return null;
        }
        if (destinationAddress == null || destinationAddress.isEmpty()) {
            this.error = ERR_DEST_ADDR_NULL;
            return null;
        }
        if (numOfPassengers == null || numOfPassengers.isEmpty()) {
            this.error = ERR_N_PAS_NULL;
            return null;
        }
        if (departureDate == null || departureDate.isEmpty()) {
            this.error = ERR_DEP_DATE_NULL;
            return null;
        }
        if (departureTime == null || departureTime.isEmpty()) {
            this.error = ERR_DEP_TIME_NULL;
            return null;
        }
        
        //Resolve data types from string params
        //Num of Passengers
        int nPassengers = Integer.parseInt(numOfPassengers);
        //Departure Time
        String depDateTime = departureDate + " " + departureTime;
        Timestamp depTimestamp = Timestamp.valueOf(depDateTime);
        //Booking Status
        GenericItem bookingStatus = new GenericItem(1, "Outstanding");
        
        return new Booking(sourceAddress, destinationAddress,
                nPassengers, new Timestamp(System.currentTimeMillis()),
                depTimestamp, bookingStatus);
    }

    public static Booking[] getBookings(Jdbc jdbc) {
        ArrayList<HashMap<String, String>> bookingsMaps = jdbc.retrieve(Booking.TABLE_NAME_BOOKINGS);
        Booking[] bookingsArr = new Booking[bookingsMaps.size()];

        //Map bookingsMaps to BookingsArr
        int i = 0;
        Customer customer;
        Driver driver;
        GenericItem bookingStatus;
        for (HashMap<String, String> map : bookingsMaps) {
            customer = CustomerManager.getCustomer(
                    Long.parseLong(map.get("CUSTOMERID")), jdbc);
            
            driver = DriverManager.getDriver(
                    Long.parseLong(map.get("DRIVERID")), jdbc);
            
            bookingStatus = new GenericItem(
                    Integer.parseInt(map.get("BOOKINGSTATUS")));
            
            bookingsArr[i++] = new Booking(Long.parseLong(map.get("ID")),
                    customer,
                    driver,
                    map.get("SOURCEADDRESS"),
                    map.get("DESTINATIONADDRESS"),
                    Integer.parseInt(map.get("NUMOFPASSENGERS")),
                    Double.parseDouble(map.get("DISTANCEKM")),
                    Timestamp.valueOf(map.get("TIMEBOOKED")),
                    Timestamp.valueOf(map.get("DEPARTURETIME")),
                    Timestamp.valueOf(map.get("ARRIVALTIME")),
                    bookingStatus);
        }

        return bookingsArr;
    }

    public static Booking[] getBookings(Jdbc jdbc, int bookingStatusId) {
        ArrayList<HashMap<String, String>> bookingsMaps = jdbc.retrieve(Booking.TABLE_NAME_BOOKINGS);
        Booking[] bookingsArr = new Booking[bookingsMaps.size()];

        //Map bookingsMaps to BookingsArr
        int i = 0;
        Customer customer;
        Driver driver;
        GenericItem bookingStatus;
        for (HashMap<String, String> map : bookingsMaps) {
            
            bookingStatus = new GenericItem(
                    Integer.parseInt(map.get("BOOKINGSTATUS")));
            
            if(bookingStatus.getId() != bookingStatusId){
                continue;
            }
            
            customer = CustomerManager.getCustomer(
                    Long.parseLong(map.get("CUSTOMERID")), jdbc);
            
            driver = DriverManager.getDriver(
                    Long.parseLong(map.get("DRIVERID")), jdbc);
            
            
            
            bookingsArr[i++] = new Booking(Long.parseLong(map.get("ID")),
                    customer,
                    driver,
                    map.get("SOURCEADDRESS"),
                    map.get("DESTINATIONADDRESS"),
                    Integer.parseInt(map.get("NUMOFPASSENGERS")),
                    Double.parseDouble(map.get("DISTANCEKM")),
                    Timestamp.valueOf(map.get("TIMEBOOKED")),
                    Timestamp.valueOf(map.get("DEPARTURETIME")),
                    Timestamp.valueOf(map.get("ARRIVALTIME")),
                    bookingStatus);
        }

        return bookingsArr;
    }
    
    private double calcDistanceKM(String source, String dest) {
        //TODO with Google Maps API
        return 0.0;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

}
