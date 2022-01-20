package com.ratheshan;

/**
 * Stores Driver objects with the correspondent teams
 */
public class F1Teams {
    private String driverTeam;
    private Formula1Driver raceDriver;

    // OVERLOADED CONSTRUCTOR
    public F1Teams(String team, Formula1Driver driver) {
        this.driverTeam = team;
        this.raceDriver = driver;
    }

    // SETTERS
    public void setDriver(Formula1Driver driver) {
        this.raceDriver = driver;
    }

    public void setTeam(String team) {
        this.driverTeam = team;
    }

    // GETTERS
    public Formula1Driver getDriver() {
        return raceDriver;
    }

    public String getTeam() {
        return driverTeam;
    }

    public String getDriverName() {
        return raceDriver.getDriverName();
    }

}
