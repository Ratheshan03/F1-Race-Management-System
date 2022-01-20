package com.ratheshan;

import java.io.*;
import java.time.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Formula1ChampionshipManager implements ChampionshipManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<F1Races> Races = new ArrayList<>();
    private static final ArrayList<F1Teams> Teams = new ArrayList<>();
    private static final File TeamsList = new File("Teams.txt");
    private static final File RacesList = new File("Races.txt");

    /**
     * Prints the Menu of the Program
     */
    @Override
    public void printMenu() {
        System.out.println("||---------------------------F1-MENU-----------------------------||");
        System.out.println("||---------------------------------------------------------------||");
        System.out.println("||           A: =>    Add a new driver                           ||");
        System.out.println("||           D: =>    Delete an existing driver                  ||");
        System.out.println("||           C: =>    Change the driver for another Team         ||");
        System.out.println("||           F: =>    Find and Display statistics of a driver    ||");
        System.out.println("||           T: =>    Display the Formula 1 driver table         ||");
        System.out.println("||           R: =>    Add a new race with its stats              ||");
        System.out.println("||           S: =>    Store information in a file                ||");
        System.out.println("||           G: =>    Graphical User Interface                   ||");
        System.out.println("||---------------------------------------------------------------||");
        System.out.println("||---------------------------------------------------------------||");
    }


    /**
     * Creates a new formula 1 driver with the given data.
     * verifies the teams are unique.
     */
    @Override
    public void createDriver(){
        System.out.println("Enter the Name of the driver: ");
        String name = scanner.next();
        System.out.println("Enter the Location of the driver: ");
        String location = scanner.next();
        System.out.println("Enter the Team driver belongs to: ");
        String team = scanner.next();
        if(checkUniqueTeam(team)){
            try {
                System.out.println("Enter No of First Places: ");
                int first = scanner.nextInt();
                System.out.println("Enter the No of Second Places: ");
                int second = scanner.nextInt();
                System.out.println("Enter the No of Third Places: ");
                int third = scanner.nextInt();
                System.out.println("Enter the No of Races: ");
                int races = scanner.nextInt();
                System.out.println("Enter the No of Points: ");
                int points = scanner.nextInt();

                System.out.println();
                System.out.println("|---------------Verify the Details----------------|");
                System.out.println("          Entered Driver Name      : " + name);
                System.out.println("          Entered Driver Location  : " + location);
                System.out.println("          Entered Driver Team      : " + team);
                System.out.println("          Entered First Places     : " + first);
                System.out.println("          Entered Second Places    : " + second);
                System.out.println("          Entered Third Places     : " + third);
                System.out.println("          Entered Points           : " + points);
                System.out.println("          Entered Races            : " + races);
                System.out.println("|-------------------------------------------------|");
                System.out.println();
                System.out.println("Confirm to add the driver to the championship (yes/no)");
                String input = scanner.next();
                if(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")){
                    Formula1Driver driverAdded = new Formula1Driver(name, location, team, first, second, third, races, points);
                    F1Teams dTeam = new F1Teams(team, driverAdded);
                    Teams.add(dTeam);
                    System.out.println("Driver and Team added to Championship!");
                }else{
                    System.out.println("Driver not Added!");
                }
            }catch (NumberFormatException exception){
                System.out.println("Please enter a Numeric value!");
            }
        }else{
            System.out.println("Team is already in the race!");
            System.out.println("Try again...");
            System.out.println("--------------------------------------------------");
            createDriver();
        }
    }


    /**
     * @param team Checks the user entered team is unique or not
     * @return boolean value true if team was unique else returns false
     */
    public Boolean checkUniqueTeam(String team){
        for (F1Teams oneF1Teams : Teams) {
            if(oneF1Teams.getTeam().equals(team)){
                return false;
            }
        }
        return true;
    }


    /**
     * Deletes a certain driver and the assigned team from formula 1 championship
     */
    @Override
    public void deleteDriver() {
        System.out.println("Select a driver to delete within the below List");
        printDriverList();
        System.out.print("Enter the name of driver to be deleted: ");
        String driverName = scanner.next();
        boolean available = false;
        for(int i = 0; i < Teams.size(); i++){
            F1Teams oneF1Teams = Teams.get(i);
            if (oneF1Teams.getDriverName().equalsIgnoreCase(driverName)){
                System.out.println("Confirm to delete driver: "  + oneF1Teams.getDriverName() + ", Enter YES-y/NO-n: ");
                String option = scanner.next();
                if(option.equalsIgnoreCase("y")){
                    Teams.remove(i);
                    available = true;
                    System.out.println(oneF1Teams.getTeam()+" and its driver "+ oneF1Teams.getDriverName() + " was successfully deleted");
                    System.out.println("|----------------------------------------------------------|");
                    break;
                }
            }
        }
        if (!available) {
            System.out.println(driverName+ " was not found in F1 Championship");
        }
    }


    /**
     * Prints the formula 1 Drivers and Teams List
     */
    public void printDriverList() {
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < Teams.size(); i++) {
            System.out.println(i + 1 + ". " + Teams.get(i).getDriverName() + " " + Teams.get(i).getTeam());
        }
        System.out.println("-------------------------------------------------");
    }


    /**
     * Displays stats of a certain driver
     */
    @Override
    public void findDriver() {
        System.out.println("Select a driver to find the statistics");
        printDriverList();
        System.out.println("Enter the driver name: ");
        String dName = scanner.next();
        boolean available = false;
        for (F1Teams driver : Teams) {
            if (driver.getDriverName().equalsIgnoreCase(dName)) {
                System.out.println("|--------------------DRIVER DETAILS--------------------|");
                System.out.println("          Driver Name         : " + driver.getDriverName());
                System.out.println("          Driver Location     : " + driver.getDriver().getDriverLocation());
                System.out.println("          Driver Team         : " + driver.getDriver().getDriverTeam());
                System.out.println("          No of First Places  : " + driver.getDriver().getFirstPlace());
                System.out.println("          No of Second Places : " + driver.getDriver().getSecondPlace());
                System.out.println("          No of Third Places  : " + driver.getDriver().getThirdPlace());
                System.out.println("          Driver Races        : " + driver.getDriver().getNoOfRaces());
                System.out.println("          Driver Points       : " + driver.getDriver().getPoints());
                System.out.println("|------------------------------------------------------|");
                available = true;
            }
        }
        if (!available) {
            System.out.println("Couldn't find the driver!");
        }
    }


    /**
     * Changes driver for an existing constructor team
     */
    @Override
    public void changeDriver(){
        boolean available = false;
        System.out.println("SELECT A TEAM TO CHANGE THE DRIVER");
        printDriverList();
        System.out.print("Enter the Team Name: ");
        String team = scanner.next();
        for( F1Teams oneF1Teams : Teams) {
            if (oneF1Teams.getTeam().equalsIgnoreCase(team)) {
                System.out.print("Enter the name of new driver: ");
                String name = scanner.next();
                System.out.print("Enter the location of the new driver: ");
                String location = scanner.next();
                try {
                    System.out.print("Enter the No of First Places: ");
                    int firstPlace = scanner.nextInt();
                    System.out.print("Enter the No of Second Places: ");
                    int secondPlace = scanner.nextInt();
                    System.out.print("Enter the No of Third Places: ");
                    int thirdPlace = scanner.nextInt();
                    System.out.print("Enter the No of Races: ");
                    int races = scanner.nextInt();
                    System.out.print("Enter the No of Points: ");
                    int points = scanner.nextInt();
                    // Adding the newly changed driver and his details
                    Formula1Driver newDriver = new Formula1Driver(name, location, team, firstPlace, secondPlace, thirdPlace, races, points);
                    oneF1Teams.setDriver(newDriver);
                    available = true;
                    System.out.println("The driver details have been updated for Team " + team);
                } catch (NumberFormatException exception) {
                    System.out.println("Please enter a correct numeric value!");
                }
            }
        }
        if(!available){
            System.out.println("The Team name entered does not exist!");
        }
    }


    /**
     * Display Formula 1 Driver Table Stats
     */
    @Override
    public void displayTable(){
        for (int i = 0; i < Teams.size(); i++) {
            for (int j = i + 1; j < Teams.size(); j++) {
                F1Teams temp;
                if (Teams.get(j).getDriver().getPoints() > Teams.get(i).getDriver().getPoints()) {
                    temp = Teams.get(i);
                    Teams.set(i, Teams.get(j));
                    Teams.set(j, temp);
                }
                if (Teams.get(j).getDriver().getPoints() == (Teams.get(i).getDriver().getPoints())) {
                    if (Teams.get(j).getDriver().getFirstPlace() > Teams.get(i).getDriver().getFirstPlace()) {
                        temp = Teams.get(i);
                        Teams.set(i, Teams.get(j));
                        Teams.set(j, temp);
                    }
                }
            }
        }

        System.out.println("|----------------------------------POINTS TABLE OF THE F1 CHAMPIONSHIP--------------------------------|");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("|      NAME      |   TEAM NAME   |    LOCATION    |  FIRST  |  SECOND  |  THIRD  |  RACES  |  POINTS  |");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        for (F1Teams temp : Teams) {
            //String Formatting for presentation purposes
            System.out.printf("| %14s | %13s | %14s | %7d | %8d | %7d | %7d |%9d |%n",temp.getDriver().getDriverName(),temp.getDriver().getDriverTeam(),temp.getDriver().getDriverLocation(),temp.getDriver().getFirstPlace(),
                    temp.getDriver().getSecondPlace(),temp.getDriver().getThirdPlace(),temp.getDriver().getNoOfRaces(),temp.getDriver().getPoints());
        }
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("|-----------------------------------------------------------------------------------------------------|");
    }


    /**
     *  Adding a new race with the stats
     */
    @Override
    public void addRace() {
        System.out.println("Enter Date of race in YYYY-MM-dd format: ");
        String dateInput = scanner.next();

        ArrayList<String> nDrivers = new ArrayList<>();
        ArrayList<Integer> nPositions = new ArrayList<>();
        printDriverList();
        for (F1Teams f1Teams : Teams) {
            System.out.print("Enter the Position of Driver " + f1Teams.getDriverName() + " : ");
            int position = scanner.nextInt();
            f1Teams.getDriver().addPoints(position);
            nDrivers.add(f1Teams.getDriverName());
            nPositions.add(position);
        }

        F1Races race = new F1Races(dateInput, nDrivers, nPositions);
        Races.add(race);
        System.out.println("New Race Data has been Successfully Added!");
        System.out.println();
    }


    /**
     * @throws IOException when storing the file and error occurs.
     * Saves Information (both Driver objects and Race objects in the files
     */
    @Override
    public void storeToFile() throws IOException{
        //Storing Driver class objects
        try {
            FileWriter myWriter = new FileWriter(TeamsList);
            for (F1Teams f1Teams : Teams) {
                Formula1Driver f1 = f1Teams.getDriver();
                myWriter.write(f1.getDriverName() + "|" + f1.getDriverLocation() + "|" + f1.getDriverTeam()+ "|" + f1.getFirstPlace() + "|"
                        + f1.getSecondPlace() + "|" + f1.getThirdPlace() + "|" + f1.getPoints() + "|" + f1.getNoOfRaces() + "\n");
            }
            myWriter.close();

            myWriter = new FileWriter(RacesList);
            for (F1Races race : Races) {
                myWriter.write(race.getDate() + "|" + race.getDrivers().toString() + "|" + race.getPositions().toString() + "\n");
            }
            myWriter.close();
            System.out.println("|---Information successfully stored in a file---|");

        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }


    }

    /**
     * Retrieves Information from the files
     */
    @Override
    public void loadTheFile(){
        // If file not created ... creates the files...
        try {
            if (TeamsList.createNewFile()) {
                File teams = new File("Teams.txt");
            }
            if (RacesList.createNewFile()) {
                File races = new File("Races.txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Scanner readerObject = new Scanner(TeamsList);

            int count = 0;
            while (readerObject.hasNextLine()) {
                readerObject.nextLine();
                count++;
            }
            readerObject.close();
            readerObject = new Scanner(TeamsList);
            Teams.removeAll(Teams);
            // Removing unwanted characters (\,/,|) and adding the retrieved data in the Team array
            for (int i = 0; i < count; i++) {
                while (readerObject.hasNextLine()) {
                    String line = readerObject.nextLine();
                    String[] load = line.split("\\|");
                    if (load[0] != null) {
                        Formula1Driver f1 = new Formula1Driver(load[0], load[1], load[2], Integer.parseInt(load[3]), Integer.parseInt(load[4]), Integer.parseInt(load[5]), Integer.parseInt(load[7]), Integer.parseInt(load[6]));
                        F1Teams f1Teams1 = new F1Teams(load[2], f1);
                        Teams.add(f1Teams1);
                    }
                }
            }
            readerObject.close();
            readerObject = new Scanner(RacesList);
            count = 0;
            while (readerObject.hasNextLine()) {
                readerObject.nextLine();
                count++;
            }
            readerObject.close();
            readerObject = new Scanner(RacesList);
            Races.removeAll(Races);
            // Removing unwanted characters in the file and adding the retrieved data in the Race array
            for (int i = 0; i < count; i++) {
                while (readerObject.hasNextLine()) {
                    String line = readerObject.nextLine();
                    String[] load = line.split("\\|");
                    // Loading the race dates, Drivers and their positions
                    if (load[0] != null) {
                        load[1] = load[1].replace("[", "");
                        load[1] = load[1].replace("]", "");
                        load[1] = load[1].replace(", ", ",");
                        load[2] = load[2].replace("[", "");
                        load[2] = load[2].replace("]", "");
                        load[2] = load[2].replace(", ", ",");

                        // Temporary arrays to store the retrieved data
                        ArrayList<String> driverA = new ArrayList<>();
                        ArrayList<Integer> positionA = new ArrayList<>();

                        String[] driverTemp = load[1].split(",");
                        // Return a new collection backed by a specified collection, and a few other odds and ends
                        Collections.addAll(driverA, driverTemp);

                        String[] positionTemp = load[2].split(",");
                        for (String s : positionTemp) {
                            int position = Integer.parseInt(s);
                            positionA.add(position);
                        }
                        F1Races race1 = new F1Races(load[0], driverA, positionA);
                        Races.add(race1);
                    }
                }
            }
            readerObject.close();
            System.out.println("Successfully Loaded Data!");

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Launches the Graphical user interface
     */
    @Override
    public void launchGui() {
        Gui mainWindow = new Gui();
        mainWindow.setLayout(new FlowLayout());
        mainWindow.setVisible(true);
        mainWindow.setTitle("FORMULA 1 CHAMPIONSHIP GUI");
        mainWindow.setSize(480,330);
        mainWindow.getContentPane().setBackground(Color.DARK_GRAY);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("LAUNCHING GRAPHICAL USER INTERFACE....");
        scanner.nextLine();
    }


    /**
     * @return F1 Teams objects ArrayList
     */
    @Override
    public ArrayList<F1Teams> getF1Teams() {
        return Teams;
    }


    /**
     * @return Races objects ArrayList
     */
    @Override
    public ArrayList<F1Races> getF1Races() {
        return Races;
    }


    /**
     * Generating Random Races with random dates and positions
     */
    @Override
    public void generateF1Race() {
        // Creating random Date and Month
        int randomDay = (int) (Math.random() * (30 - 1 + 1) + 1);
        int randomMonth = (int) (Math.random() * (12 - 1 + 1) + 1);
        String newDay = String.valueOf(randomDay);
        String newMonth = String.valueOf(randomMonth);

        if (randomDay < 10) {
            newDay = "0" + randomDay;
        }
        if (randomMonth < 10) {
            newMonth = "0" + randomMonth;
        }
        String date = 2021 + "-" + newMonth + "-" + newDay;
        // Array to add new winners list
        ArrayList<String> newDriver = new ArrayList<>();
        // Array for new race positions (unique positions)
        ArrayList<Integer> newPosition = new ArrayList<>();
        // Array that has existing race positions
        ArrayList<Integer> newTemp = new ArrayList<>();

        // Assigning random positions
        for (F1Teams f1Teams : Teams) {
            boolean finished = false;
            while (!finished) {
                int pos = (int) (Math.random() * (Formula1ChampionshipManager.Teams.size() - 1 + 1) + 1);
                if (!newTemp.contains(pos)) {
                    newTemp.add(pos);
                    f1Teams.getDriver().addPoints(pos);
                    newDriver.add(f1Teams.getDriverName());
                    newPosition.add(pos);
                    finished = true;
                }
            }
        }
        F1Races race1 = new F1Races(date, newDriver, newPosition);
        Races.add(race1);
    }

    /**
     * @param winner -> winner who is created through the probability
     *
     * Generates a random race with the given winner and assign him as
     * the champion of the race and gives others random positions.
     */
    @Override
    public void generateF1Race(String winner) {
        //Creating random dates and months
        int oneDay = (int) (Math.random() * (30 - 1 + 1) + 1);
        int oneMonth = (int) (Math.random() * (12 - 1 + 1) + 1);

        String day1 = String.valueOf(oneDay);
        String month1 = String.valueOf(oneMonth);

        if (oneDay < 10) {
            day1 = "0" + oneDay;
        }
        if (oneMonth < 10) {
            month1 = "0" + oneMonth;
        }
        String newDate = 2021 + "-" + month1 + "-" + day1;

        // Array to add new winners list
        ArrayList<String> newDrivers = new ArrayList<>();
        // Array for new race positions (unique positions)
        ArrayList<Integer> newPositions = new ArrayList<>();
        // Array that has existing race positions
        ArrayList<Integer> newTemp = new ArrayList<>();
        newTemp.add(1);

        for (F1Teams f1Teams : Teams) {
            // Assigning the winner
            if (f1Teams.getDriverName().equals(winner)) {
                newDrivers.add(winner);
                f1Teams.getDriver().addPoints(1);
                newPositions.add(1);

            } else {
                boolean finished = false;
                while (!finished) {
                    int position = (int) (Math.random() * (Formula1ChampionshipManager.Teams.size() - 1 + 1) + 1);
                    // Checks for the unique combinations driver's positions
                    if (!newTemp.contains(position)) {
                        newTemp.add(position);
                        f1Teams.getDriver().addPoints(position);
                        newDrivers.add(f1Teams.getDriverName());
                        newPositions.add(position);
                        finished = true;
                    }
                }
            }
        }
        // Adding newly created race to the formula 1 races list
        F1Races newRace = new F1Races(newDate, newDrivers, newPositions);
        Races.add(newRace);
    }

    /**
     * Sorting the formula 1 race according to the dates
     */
    @Override
    public void sortRDate() {
        ArrayList<LocalDate> newDates = new ArrayList<>();
        LocalDate TempDate;
        F1Races TempRace;
        // Retrieving the existing dates from the race array and adding it to a temporary arraylist
        for (F1Races race : Races) {
            newDates.add(LocalDate.parse(race.getDate()));
        }

        for (int i = 0; i < Races.size(); i++) {
            for (int j = i + 1; j < Races.size(); j++) {
                if (newDates.get(j).compareTo(newDates.get(i)) < 0) {
                    TempDate = newDates.get(i);
                    TempRace = Races.get(i);
                    newDates.set(i, newDates.get(j));
                    newDates.set(j, TempDate);
                    Races.set(i, Races.get(j));
                    Races.set(j, TempRace);
                }
            }
        }
    }

    /**
     *  Setting up random positions, Getting existing driver names, and saving the random positions(unique order).
     *  Sets the driver -> according to the position's probability Saving the driver names according to the
     *  probability percentage no of times in the Arraylist.
     *
     *  Getting a random number within the size of the drivers list
     *  with the use of already assigned probability array we are retrieving a random driver as the winner using
     *  the random number that we got before And creating a new race with the assigned winner.
     *
     */
    @Override
    public void getStartPosition() {
        ArrayList<String> fDriver = new ArrayList<>();
        ArrayList<Integer> sPosition = new ArrayList<>();
        ArrayList<Integer> tempPos = new ArrayList<>();

        for (F1Teams f1Team : Teams) {
            boolean finished = false;
            while (!finished) {
                int position = (int) (Math.random() * (Formula1ChampionshipManager.Teams.size() - 1 + 1) + 1);
                if (!tempPos.contains(position)) {
                    tempPos.add(position);
                    fDriver.add(f1Team.getDriverName());
                    sPosition.add(position);
                    finished = true;
                }
            }
        }

        // Sets the driver -> according to the position's probability
        // Saving the driver names according to the probability percentage no of times in the Arraylist
        ArrayList<String> dProbability = new ArrayList<>();
        for (int i = 0; i < sPosition.size(); i++) {
            if (sPosition.get(i) == 1) {
                for (int j = 0; j < 40; j++) {
                    dProbability.add(fDriver.get(i));
                }
            } else if (sPosition.get(i) == 2) {
                for (int j = 0; j < 30; j++) {
                    dProbability.add(fDriver.get(i));
                }
            } else if (sPosition.get(i) == 3) {
                for (int j = 0; j < 10; j++) {
                    dProbability.add(fDriver.get(i));
                }
            } else if (sPosition.get(i) == 4) {
                for (int j = 0; j < 10; j++) {
                    dProbability.add(fDriver.get(i));
                }
            } else if (sPosition.get(i) == 5) {
                for (int j = 0; j < 2; j++) {
                    dProbability.add(fDriver.get(i));
                }
            } else if (sPosition.get(i) == 6) {
                for (int j = 0; j < 2; j++) {
                    dProbability.add(fDriver.get(i));
                }
            } else if (sPosition.get(i) == 7) {
                for (int j = 0; j < 2; j++) {
                    dProbability.add(fDriver.get(i));
                }
            } else if (sPosition.get(i) == 8) {
                for (int j = 0; j < 2; j++) {
                    dProbability.add(fDriver.get(i));
                }
            } else if (sPosition.get(i) == 9) {
                for (int j = 0; j < 2; j++) {
                    dProbability.add(fDriver.get(i));
                }
            }
        }
        // Getting a random number within the size of the drivers list with the use of already assigned probability
        // array we are retrieving a random driver as the winner using the random number that we got before
        // And creating a new race with the assigned winner.
        int num = (int) (Math.random() * (dProbability.size() - 1 + 1) + 1);
        String winner = dProbability.get(num - 1);
        generateF1Race(winner);
    }
}
