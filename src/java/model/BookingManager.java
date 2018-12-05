/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import ServiceClients.AlphaCabsServicesClient;
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
            ERR_DEP_TIME_NULL = -7,
            ERR_ADDR_NOT_FOUND = -8,
            ERR_WITH_WEB_SERVICE = -9;
    //Driver Assignment Error Codes
    public static final int ERR_DRIVER_NULL = -1,
            ERR_BOOKING_NULL = -2;

    public int error;

    public Booking generateNewBooking(Customer customer,
            String isSourceSameAsHome, String sourceAddress,
            String destinationAddress, String numOfPassengers,
            String departureDate, String departureTime, Jdbc jdbc) {
        HashMap<String, String> fareResponse;
        
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

        fareResponse = AlphaCabsServicesClient.calculateFare(sourceAddress, destinationAddress);
        
        if ("-1".equals(fareResponse.get("status"))){
            this.error = ERR_ADDR_NOT_FOUND;
            return null;
        }
        else if ("-2".equals(fareResponse.get("status"))){
            this.error = ERR_WITH_WEB_SERVICE;
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
        
        //Distance
        double distance = Double.valueOf(fareResponse.get("distance"));
        
        //Charge
        double fairExcVAT = Double.valueOf(fareResponse.get("fareNoVAT"));
        double fairIncVAT = Double.valueOf(fareResponse.get("fareWithVAT"));

        //Departure Time
        String depDateTime = departureDate + " " + departureTime + ":00";
        Timestamp depTimestamp = Timestamp.valueOf(depDateTime);
        
        //Booking Status
        GenericItem bookingStatus = new GenericItem(1, "Outstanding");

        return new Booking(customer, sourceAddress, destinationAddress,
                nPassengers, distance, fairExcVAT, fairIncVAT, new Timestamp(System.currentTimeMillis()),
                depTimestamp, bookingStatus);
    }

    public Booking generateNewBooking(String sourceAddress,
            String destinationAddress, String numOfPassengers,
            String departureDate, String departureTime, Jdbc jdbc) {
        HashMap<String, String> fareResponse;
        
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

        fareResponse = AlphaCabsServicesClient.calculateFare(sourceAddress, destinationAddress);
        
        if ("-1".equals(fareResponse.get("status"))){
            this.error = ERR_ADDR_NOT_FOUND;
            return null;
        }
        else if ("-2".equals(fareResponse.get("status"))){
            this.error = ERR_WITH_WEB_SERVICE;
            return null;
        }          
        
        //Resolve data types from string params
        //Num of Passengers
        int nPassengers = Integer.parseInt(numOfPassengers);
       
        //Distance
        double distance = Double.valueOf(fareResponse.get("distance"));
        
        //Charge
        double fairExcVAT = Double.valueOf(fareResponse.get("fareNoVAT"));
        double fairIncVAT = Double.valueOf(fareResponse.get("fareWithVAT"));
        //Departure Time
        String depDateTime = departureDate + " " + departureTime + ":00";
        Timestamp depTimestamp = Timestamp.valueOf(depDateTime);
    
        //Booking Status
        GenericItem bookingStatus = new GenericItem(1, "Outstanding");

        return new Booking(sourceAddress, destinationAddress,
                nPassengers, distance, fairExcVAT, fairIncVAT,
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
            } else {
                driver = null;
            }

            arrivalStr = map.get("ARRIVALTIME");
            // Arrival time can be null, so handle this.
            if (arrivalStr != null) {
                arrivalTime = Timestamp.valueOf(arrivalStr);
            } else {
                arrivalTime = null;
            }

            bookingsArr[i++] = new Booking(Long.parseLong(map.get("ID")),
                    customer,
                    driver,
                    map.get("SOURCEADDRESS"),
                    map.get("DESTINATIONADDRESS"),
                    Integer.parseInt(map.get("NUMOFPASSENGERS")),
                    Double.parseDouble(map.get("DISTANCE")), 
                    Double.parseDouble(map.get("FAREEXCVAT")),
                    Double.parseDouble(map.get("FAREINCVAT")),
                    Timestamp.valueOf(map.get("TIMEBOOKED")),
                    Timestamp.valueOf(map.get("DEPARTURETIME")),
                    arrivalTime,
                    bookingStatus);
        }

        return bookingsArr;
    }

    public static Booking[] getBookings(Jdbc jdbc, int bookingStatusId) {
        ArrayList<HashMap<String, String>> bookingsMaps = jdbc.retrieve(Booking.TABLE_NAME_BOOKINGS);
        ArrayList<Booking> bookingsList = new ArrayList<>();

        //Map bookingsMaps to BookingsArr
        Customer customer;
        String driverIdStr, arrivalStr;
        Driver driver = null;
        Timestamp arrivalTime = null;
        GenericItem bookingStatus;
        for (HashMap<String, String> map : bookingsMaps) {

            bookingStatus = new GenericItem(
                    Integer.parseInt(map.get("BOOKINGSTATUSID")));

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

            if (bookingStatusId == 4) {
                arrivalStr = map.get("ARRIVALTIME");
                if (driverIdStr != null) {
                    arrivalTime = Timestamp.valueOf(arrivalStr);
                }
            }

            bookingsList.add(new Booking(Long.parseLong(map.get("ID")),
                    customer,
                    driver,
                    map.get("SOURCEADDRESS"),
                    map.get("DESTINATIONADDRESS"),
                    Integer.parseInt(map.get("NUMOFPASSENGERS")),
                    Double.parseDouble(map.get("DISTANCE")),
                    Double.parseDouble(map.get("FAREEXCVAT")),
                    Double.parseDouble(map.get("FAREINCVAT")),
                    Timestamp.valueOf(map.get("TIMEBOOKED")),
                    Timestamp.valueOf(map.get("DEPARTURETIME")),
                    arrivalTime,
                    bookingStatus));
        }

        Booking[] bookingsArr = new Booking[bookingsList.size()];

        for (int i = 0; i < bookingsArr.length; i++) {
            bookingsArr[i] = bookingsList.get(i);
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
                Double.parseDouble(bookingMap.get("DISTANCE")),
                Double.parseDouble(bookingMap.get("FAREEXCVAT")),
                Double.parseDouble(bookingMap.get("FAREINCVAT")),
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

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

}
