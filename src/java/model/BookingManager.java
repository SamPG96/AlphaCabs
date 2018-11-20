/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import model.tableclasses.Booking;
import model.tableclasses.GenericItem;
import model.tableclasses.Customer;

/**
 *
 * @author Conor
 */
public class BookingManager {
    
    public Booking generateNewBooking(String firstName, String lastName, 
            String address, boolean isSourceSameAsHome, String sourceAddress, 
            String destinationAddress, int numOfPassengers, 
            Timestamp timeBooked, Timestamp departureTime, 
            GenericItem bookingStatus){
        
        Customer newCustomer = new Customer(firstName, lastName, address);
        if(isSourceSameAsHome){
            sourceAddress = address;
        }
        
        GenericItem gi = new GenericItem(1, "Outstanding");
        
        return new Booking(newCustomer, sourceAddress, destinationAddress, 
                numOfPassengers, timeBooked, departureTime, gi);
    }
    
    public Booking generateNewBooking(Customer customer, boolean isSourceSameAsHome, String sourceAddress, 
            String destinationAddress, int numOfPassengers, 
            Timestamp timeBooked, Timestamp departureTime, 
            GenericItem bookingStatus){
        
        if(isSourceSameAsHome && customer != null){
            sourceAddress = customer.getAddress();
        }
        
        GenericItem gi = new GenericItem(1, "Outstanding");
        
        return new Booking(customer, sourceAddress, destinationAddress, 
                numOfPassengers, timeBooked, departureTime, gi);
    }
    
    private double calcDistanceKM(String source, String dest){
        //TODO with Google Maps API
        return 0.0;
    }
}
