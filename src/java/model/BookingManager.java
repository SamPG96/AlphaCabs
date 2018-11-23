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
            ERR_DEP_TIME_NULL = -6;

    private int error;

    public Booking generateNewBooking(Customer customer,
            String isSourceSameAsHome, String sourceAddress,
            String destinationAddress, String numOfPassengers,
            String departureTime) {

        boolean isSSAH;
        int nPassengers;
        Timestamp depTime;
        GenericItem bookingStatus;

        if (customer == null) {
            this.error = ERR_CUST_NULL;
            return null;
        }

        if (isSourceSameAsHome == null || isSourceSameAsHome.isEmpty()) {
            this.error = ERR_SRC_HOME_NULL;
            return null;
        } else {
            isSSAH = Boolean.parseBoolean(isSourceSameAsHome);
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
        } else {
            nPassengers = Integer.parseInt(numOfPassengers);
        }

        if (departureTime == null || departureTime.isEmpty()) {
            this.error = ERR_DEP_TIME_NULL;
            return null;
        } else {
            depTime = Timestamp.valueOf(departureTime);
        }

        if (isSSAH) {
            sourceAddress = customer.getAddress();
        }

        bookingStatus = new GenericItem(1, "Outstanding");

        return new Booking(customer, sourceAddress, destinationAddress,
                nPassengers, new Timestamp(System.currentTimeMillis()),
                depTime, bookingStatus);
    }

    public Booking generateNewBooking(String sourceAddress,
            String destinationAddress, String numOfPassengers,
            String timeBooked, String departureTime) {

        int nPassengers;
        Timestamp depTime;
        GenericItem bookingStatus;

        if (sourceAddress == null) {
            this.error = ERR_SRC_ADDR_NULL;
            return null;
        }

        if (destinationAddress == null) {
            this.error = ERR_DEST_ADDR_NULL;
            return null;
        }

        if (numOfPassengers == null) {
            this.error = ERR_N_PAS_NULL;
            return null;
        } else {
            nPassengers = Integer.parseInt(numOfPassengers);
        }

        if (departureTime == null) {
            this.error = ERR_DEP_TIME_NULL;
            return null;
        } else {
            depTime = Timestamp.valueOf(departureTime);
        }

        bookingStatus = new GenericItem(1, "Outstanding");

        return new Booking(sourceAddress, destinationAddress,
                nPassengers, new Timestamp(System.currentTimeMillis()),
                depTime, bookingStatus);
    }
    
    public static Booking[] getAllBookings(Jdbc jdbc){
        ArrayList<HashMap<String, String>> bookingsMap = jdbc.retrieve(Booking.TABLE_NAME_BOOKINGS);
        Booking[] bookingsArr = new Booking[bookingsMap.size()];
        
        //Map bookingsMaps to BookingsArr
        
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
