package com.ratheshan;

import java.io.IOException;
import java.util.ArrayList;

public interface ChampionshipManager {
    void printMenu();
    void createDriver();
    void deleteDriver();
    void findDriver();
    void changeDriver();
    void displayTable();
    void addRace();
    void storeToFile() throws IOException;
    void loadTheFile() throws IOException, ClassNotFoundException;
    void launchGui();
    void generateF1Race();
    void generateF1Race(String winner);
    void sortRDate();
    ArrayList<F1Teams> getF1Teams();
    ArrayList<F1Races> getF1Races();
    void getStartPosition();
}
