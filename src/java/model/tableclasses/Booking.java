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
    
}
