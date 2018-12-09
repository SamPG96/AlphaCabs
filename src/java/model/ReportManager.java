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

    private final double dailyTurnover, yesterdaysTurnover;
    private final double turnoverDelta, nCustomersDelta;
    private final int nCustomersToday, nCustomersYesterday;
    private final Booking[] todaysBookings, yesterdaysBookings;

    public ReportManager(Jdbc jdbc) {
        //Today
        this.todaysBookings = findTodaysBookings(jdbc);
        this.nCustomersToday = this.todaysBookings.length;
        this.dailyTurnover = calcTurnover(this.todaysBookings);
        //Yesterday
        this.yesterdaysBookings = findYesterdaysBookings(jdbc);
        this.nCustomersYesterday = this.yesterdaysBookings.length;
        this.yesterdaysTurnover = calcTurnover(this.yesterdaysBookings);
        //Deltas
        if(this.yesterdaysTurnover != 0 && this.dailyTurnover != 0 
                && this.yesterdaysTurnover != this.dailyTurnover){
            this.turnoverDelta = 100 / this.yesterdaysTurnover * this.dailyTurnover - 100;
        }else{
            this.turnoverDelta = 0;
        }
        if(this.nCustomersYesterday != 0 && this.nCustomersToday != 0
                && this.nCustomersYesterday != this.nCustomersToday){
            this.nCustomersDelta = 100 / this.nCustomersYesterday * this.nCustomersToday - 100;
        }else{
            this.nCustomersDelta = 0;
        }
    }

    public static Booking[] findTodaysBookings(Jdbc jdbc) {
        Booking[] allBookings = BookingManager.getBookings(jdbc);
        ArrayList<Booking> todaysBookings = new ArrayList<>();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Calendar today = Calendar.getInstance();
        Calendar bookingCal = Calendar.getInstance();
        today.setTime(now);
        
        boolean sameDay;
        for (Booking booking : allBookings) {
            Timestamp ts = booking.getTimeBooked();
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
    
    public static Booking[] findYesterdaysBookings(Jdbc jdbc) {
        Booking[] allBookings = BookingManager.getBookings(jdbc);
        ArrayList<Booking> yesterdaysBookings = new ArrayList<>();
        
        //Calc yesterday calendar
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Calendar yesterday = Calendar.getInstance();
        yesterday.setTime(now);
        yesterday.set(Calendar.DAY_OF_YEAR, 
                yesterday.get(Calendar.DAY_OF_YEAR) - 1);
        
        Calendar bookingCal = Calendar.getInstance();
        boolean sameDay;
        for (Booking booking : allBookings) {
            Timestamp ts = booking.getTimeBooked();
            bookingCal.setTime(ts);
            sameDay = yesterday.get(Calendar.DAY_OF_YEAR) == bookingCal.get(Calendar.DAY_OF_YEAR)
                && yesterday.get(Calendar.YEAR) == bookingCal.get(Calendar.YEAR);
            if(sameDay){
                yesterdaysBookings.add(booking);
            }
        }

        Booking[] ret = new Booking[yesterdaysBookings.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = yesterdaysBookings.get(i);
        }
        return ret;
    }
    
    private double calcTurnover(Booking[] bookings){
        double ret = 0;
        
        for(Booking booking : bookings){
            ret += booking.getFareExcVAT();
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

    public double getYesterdaysTurnover() {
        return yesterdaysTurnover;
    }

    public double getTurnoverDelta() {
        return turnoverDelta;
    }

    public double getnCustomersDelta() {
        return nCustomersDelta;
    }

    public int getnCustomersYesterday() {
        return nCustomersYesterday;
    }

    public Booking[] getYesterdaysBookings() {
        return yesterdaysBookings;
    }
    
    
    
}
