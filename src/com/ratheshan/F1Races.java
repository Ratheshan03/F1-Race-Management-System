package com.ratheshan;

import java.util.ArrayList;

/**
 *  Stores Array lists of Drivers and their positions with the race dates
 */
public class F1Races {
    // INSTANCE VARIABLES
    private String raceDate;
    private ArrayList<String> raceDriver;
    private ArrayList<Integer> driverPosition;

    // OVERLOADED CONSTRUCTOR
    public F1Races(String date, ArrayList<String> driver, ArrayList<Integer> position) {
        this.raceDate = date;
        this.raceDriver = driver;
        this.driverPosition = position;
    }

    // GET METHODS
    public String getDate() {
        return raceDate;
    }

    public ArrayList getDrivers() {
        return raceDriver;
    }

    public ArrayList getPositions() {
        return driverPosition;
    }
}

