package com.ratheshan;

public class Formula1Driver extends Driver {
    // INSTANCE VARIABLES
    private int firstPlace;
    private int secondPlace;
    private int thirdPlace;
    private int points;
    private int noOfRaces;

    // OVERLOADED CONSTRUCTOR
    public Formula1Driver(String driverName, String driverLocation, String driverTeam, int first, int second, int third, int noRaces, int point) {
        super(driverName, driverLocation, driverTeam);
        this.firstPlace = first;
        this.secondPlace = second;
        this.thirdPlace = third;
        this.noOfRaces = noRaces;
        this.points = point;
    }

    // GETTER METHODS
    public int getFirstPlace() {
        return firstPlace;
    }

    public int getSecondPlace() {
        return secondPlace;
    }

    public int getThirdPlace() {
        return thirdPlace;
    }

    public int getPoints() {
        return points;
    }

    public int getNoOfRaces() {
        return noOfRaces;
    }

    // ADD POINTS METHOD
    // POINTS SCHEME => 1:25 2:18 3:15 4:12 5:10 6:8 7:6 8:4 9:2 10:1
    public void addPoints(int position) {
        if (position == 1){
            this.points += 25;
            this.firstPlace += 1;
            this.noOfRaces += 1;
        }else if(position == 2){
            this.points += 18;
            this.secondPlace += 1;
            this.noOfRaces += 1;
        }else if(position == 3){
            this.points += 15;
            this.thirdPlace += 1;
            this.noOfRaces += 1;
        }else if(position == 4){
            this.points += 12;
            this.noOfRaces += 1;
        }else if(position == 5){
            this.points += 10;
            this.noOfRaces += 1;
        }else if(position == 6){
            this.points += 8;
            this.noOfRaces += 1;
        }else if(position == 7){
            this.points += 6;
            this.noOfRaces += 1;
        }else if(position == 8){
            this.points += 4;
            this.noOfRaces += 1;
        }else if(position == 9){
            this.points += 2;
            this.noOfRaces += 1;
        }else if(position == 10){
            this.points += 1;
            this.noOfRaces += 1;
        }else{
            this.noOfRaces +=1;
        }
    }
}
