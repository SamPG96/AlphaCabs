/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import model.tableclasses.Booking;

/**
 *
 * @author Conor
 */
public class ReportManager {

    private final double dailyTurnover;
    private final int nCustomersToday;
    private final Booking[] todaysBookings;

    public ReportManager(Jdbc jdbc) {
        this.todaysBookings = GetTodaysBookings(jdbc);
        this.nCustomersToday = this.todaysBookings.length;
        this.dailyTurnover = calcDailyTurnover();
    }

    public static Booking[] GetTodaysBookings(Jdbc jdbc) {
        Booking[] allBookings = BookingManager.getBookings(jdbc);
        ArrayList<Booking> todaysBookings = new ArrayList<>();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Calendar today = Calendar.getInstance();
        Calendar bookingCal = Calendar.getInstance();
        today.setTime(now);
        
        boolean sameDay;
        for (Booking booking : allBookings) {
            Timestamp ts = booking.getDepartureTime();
            bookingCal.setTime(ts);
            sameDay = today.get(Calendar.DAY_OF_YEAR) == bookingCal.get(Calendar.DAY_OF_YEAR)
                && today.get(Calendar.YEAR) == bookingCal.get(Calendar.YEAR);
            if(sameDay){
                todaysBookings.add(booking);
            }
        }

        Booking[] ret = new Booking[todaysBookings.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = todaysBookings.get(i);
        }
        return ret;
    }
    
    private double calcDailyTurnover(){
        double ret = 0;
        
        for(Booking booking : this.todaysBookings){
            ret += booking.getCharge();
        }
        
        return ret;
    }

    public double getDailyTurnover() {
        return dailyTurnover;
    }

    public int getnCustomersToday() {
        return nCustomersToday;
    }

    public Booking[] getTodaysBookings() {
        return todaysBookings;
    }
    
    
}
