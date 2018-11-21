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
            String departureTime){
        
        Booking ret = new Booking();
      
        if(customer == null){
            this.error = ERR_CUST_NULL;
            return null;
        }
        
        if(isSourceSameAsHome == null){
            this.error = ERR_SRC_HOME_NULL;
            return null;
        }
        
        if(sourceAddress == null){
            this.error = ERR_SRC_ADDR_NULL;
            return null;
        }
        
        if(destinationAddress == null){
            this.error = ERR_DEST_ADDR_NULL;
            return null;
        }
        
        if(numOfPassengers == null){
            this.error = ERR_N_PAS_NULL;
            return null;
        }
        
        if(departureTime == null){
            this.error = ERR_DEP_TIME_NULL;
            return null;
        }
        
//        if(isSourceSameAsHome && customer != null){
//            sourceAddress = customer.getAddress();
//        }
        
        GenericItem gi = new GenericItem(1, "Outstanding");
        
        return ret;
    }
    
    public static Booking generateNewBooking(String sourceAddress, 
            String destinationAddress, int numOfPassengers, 
            Timestamp timeBooked, Timestamp departureTime, 
            GenericItem bookingStatus){
        
        
        GenericItem gi = new GenericItem(1, "Outstanding");
        
        return new Booking(sourceAddress, destinationAddress, 
                numOfPassengers, timeBooked, departureTime, gi);
    }
    
    
    
    private double calcDistanceKM(String source, String dest){
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
