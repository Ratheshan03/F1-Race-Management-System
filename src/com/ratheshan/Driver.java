package com.ratheshan;

/**
 * Initial class which stores the driver objects with their
 * names, location and team names.
 */
public abstract class Driver{
    // INSTANCE VARIABLES
    private final String dName;
    private final String dLocation;
    private final String dTeam;

    // DRIVER OVERLOADED CONSTRUCTOR
    public Driver(String driverName, String driverLocation, String driverTeam) {
        this.dName = driverName;
        this.dLocation = driverLocation;
        this.dTeam = driverTeam;
    }

    // GETTER METHODS
    public String getDriverName() {
        return dName;
    }
    public String getDriverLocation() {return dLocation;}
    public String getDriverTeam() {
        return dTeam;
    }

}
