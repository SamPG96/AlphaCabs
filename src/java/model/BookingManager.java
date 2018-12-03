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

    //Generator Error codes
    public static final int ERR_CUST_NULL = -1,
            ERR_SRC_HOME_NULL = -2,
            ERR_SRC_ADDR_NULL = -3,
            ERR_DEST_ADDR_NULL = -4,
            ERR_N_PAS_NULL = -5,
            ERR_DEP_DATE_NULL = -6,
            ERR_DEP_TIME_NULL = -7;
    //Driver Assignment Error Codes
    public static final int ERR_DRIVER_NULL = -1,
            ERR_BOOKING_NULL = -2;
    
    public int error;

    public Booking generateNewBooking(Customer customer,
            String isSourceSameAsHome, String sourceAddress,
            String destinationAddress, String numOfPassengers,
            String departureDate, String departureTime, Jdbc jdbc) {

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
        //Distance KM
        double distanceKM = calcDistanceKM(sourceAddress, destinationAddress);
        //Charge
        double charge = calcCharge(distanceKM, jdbc);
        //Departure Time
        String depDateTime = departureDate + " " + departureTime + ":00";
        Timestamp depTimestamp = Timestamp.valueOf(depDateTime);
        //Booking Status
        GenericItem bookingStatus = new GenericItem(1, "Outstanding");

        return new Booking(customer, sourceAddress, destinationAddress,
                nPassengers, distanceKM, charge, new Timestamp(System.currentTimeMillis()),
                depTimestamp, bookingStatus);
    }

    public Booking generateNewBooking(String sourceAddress,
            String destinationAddress, String numOfPassengers,
            String departureDate, String departureTime, Jdbc jdbc) {

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
        //Distance KM
        double distanceKM = calcDistanceKM(sourceAddress, destinationAddress);
        //Charge
        double charge = calcCharge(distanceKM, jdbc);
        //Departure Time
        String depDateTime = departureDate + " " + departureTime + ":00";
        Timestamp depTimestamp = Timestamp.valueOf(depDateTime);
        //Booking Status
        GenericItem bookingStatus = new GenericItem(1, "Outstanding");

        return new Booking(sourceAddress, destinationAddress,
                nPassengers, distanceKM, charge,
                new Timestamp(System.currentTimeMillis()),
                depTimestamp, bookingStatus);
    }

    public static Booking[] getBookings(Jdbc jdbc) {
        ArrayList<HashMap<String, String>> bookingsMaps = jdbc.retrieve(Booking.TABLE_NAME_BOOKINGS);
        Booking[] bookingsArr = new Booking[bookingsMaps.size()];

        //Map bookingsMaps to BookingsArr
        int i = 0;
        Customer customer;
        String driverIdStr, arrivalStr;
        Driver driver;
        Timestamp arrivalTime;
        GenericItem bookingStatus;
        for (HashMap<String, String> map : bookingsMaps) {
            customer = CustomerManager.getCustomer(
                    Long.parseLong(map.get("CUSTOMERID")), jdbc);

            bookingStatus = new GenericItem(
                    Integer.parseInt(map.get("BOOKINGSTATUSID")));

            driverIdStr = map.get("DRIVERID");
            // Driver ID can be null ifno driver assigned, so handle this.
            if (driverIdStr != null) {
                driver = DriverManager.getDriver(
                        Long.parseLong(driverIdStr), jdbc);
            }
            else{
                driver = null;
            }
            
            arrivalStr = map.get("ARRIVALTIME");
            // Arrival time can be null, so handle this.
            if (arrivalStr != null) {
                arrivalTime = Timestamp.valueOf(arrivalStr);
            }
            else{
                arrivalTime = null;
            }

            bookingsArr[i++] = new Booking(Long.parseLong(map.get("ID")),
                    customer,
                    driver,
                    map.get("SOURCEADDRESS"),
                    map.get("DESTINATIONADDRESS"),
                    Integer.parseInt(map.get("NUMOFPASSENGERS")),
                    Double.parseDouble(map.get("DISTANCEKM")),
                    Double.parseDouble(map.get("CHARGE")),
                    Timestamp.valueOf(map.get("TIMEBOOKED")),
                    Timestamp.valueOf(map.get("DEPARTURETIME")),
                    arrivalTime,
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
        String driverIdStr, arrivalStr;
        Driver driver = null;
        Timestamp arrivalTime = null;
        GenericItem bookingStatus;
        for (HashMap<String, String> map : bookingsMaps) {

            bookingStatus = new GenericItem(
                    Integer.parseInt(map.get("BOOKINGSTATUS")));

            if (bookingStatus.getId() != bookingStatusId) {
                continue;
            }

            customer = CustomerManager.getCustomer(
                    Long.parseLong(map.get("CUSTOMERID")), jdbc);

            driverIdStr = map.get("DRIVERID");
            if (driverIdStr != null) {
                driver = DriverManager.getDriver(
                        Long.parseLong(driverIdStr), jdbc);
            }
            
            arrivalStr = map.get("ARRIVALTIME");
            if (driverIdStr != null) {
                arrivalTime = Timestamp.valueOf(arrivalStr);
            }

            bookingsArr[i++] = new Booking(Long.parseLong(map.get("ID")),
                    customer,
                    driver,
                    map.get("SOURCEADDRESS"),
                    map.get("DESTINATIONADDRESS"),
                    Integer.parseInt(map.get("NUMOFPASSENGERS")),
                    Double.parseDouble(map.get("DISTANCEKM")),
                    Double.parseDouble(map.get("CHARGE")),
                    Timestamp.valueOf(map.get("TIMEBOOKED")),
                    Timestamp.valueOf(map.get("DEPARTURETIME")),
                    arrivalTime,
                    bookingStatus);
        }

        return bookingsArr;
    }

    public static Booking getBooking(Jdbc jdbc, long bookingId) {
        ArrayList<HashMap<String, String>> results;
        HashMap<String, String> bookingMap;

        results = jdbc.retrieve(Booking.TABLE_NAME_BOOKINGS, bookingId);

        if (results.isEmpty()) {
            //No record was found with bookingId
            return null;
        }

        bookingMap = results.get(0);

        //Map bookingsMap to Booking object
        Customer customer = CustomerManager.getCustomer(
                Long.parseLong(bookingMap.get("CUSTOMERID")), jdbc);

        Driver driver = DriverManager.getDriver(
                Long.parseLong(bookingMap.get("DRIVERID")), jdbc);

        GenericItem bookingStatus = new GenericItem(
                Integer.parseInt(bookingMap.get("BOOKINGSTATUS")));

        return new Booking(Long.parseLong(bookingMap.get("ID")),
                customer,
                driver,
                bookingMap.get("SOURCEADDRESS"),
                bookingMap.get("DESTINATIONADDRESS"),
                Integer.parseInt(bookingMap.get("NUMOFPASSENGERS")),
                Double.parseDouble(bookingMap.get("DISTANCEKM")),
                Double.parseDouble(bookingMap.get("CHARGE")),
                Timestamp.valueOf(bookingMap.get("TIMEBOOKED")),
                Timestamp.valueOf(bookingMap.get("DEPARTURETIME")),
                Timestamp.valueOf(bookingMap.get("ARRIVALTIME")),
                bookingStatus);
    }

    public static long assignDriver(long driverId, long bookingId, Jdbc jdbc) {
        Driver driver = DriverManager.getDriver(driverId, jdbc);
        if (driver == null) {
            return ERR_DRIVER_NULL;
        }
        Booking booking = getBooking(jdbc, bookingId);
        if (booking == null) {
            return ERR_BOOKING_NULL;
        }

        booking.setDriver(driver);

        long updBookingId = jdbc.update(booking);

        booking = getBooking(jdbc, updBookingId);
        if (booking == null) {
            return ERR_BOOKING_NULL;
        }

        return jdbc.update(booking);
    }

    private static double calcDistanceKM(String source, String dest) {
        //TODO with Google Maps API
        return 10.0;
    }

    private static double calcCharge(double distanceKM, Jdbc jdbc){
        int shortDistance = AdminManager.getShortDistance(jdbc);
        double shortDistPrice = AdminManager.getShortDistPrice(jdbc);
        
        if(calcKMToMiles(distanceKM) > shortDistance){
            double pricePerKM = AdminManager.getPricePerKM(jdbc);
            return shortDistPrice + distanceKM * pricePerKM;
        }else{
            return shortDistPrice;
        }
    }
    
    private static double calcKMToMiles(double km){
        return km * 0.621371;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
    
}
