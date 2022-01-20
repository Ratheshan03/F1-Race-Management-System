package com.ratheshan;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Formula1ChampionshipManager f1Manager = new Formula1ChampionshipManager();
        //If available -> loads the file that has been store previously
        f1Manager.loadTheFile();
        System.out.println("||---------------WELCOME TO FORMULA 1 CHAMPIONSHIP---------------||");
        while (true){
            System.out.println();
            new Formula1ChampionshipManager().printMenu();
            System.out.println();
            System.out.print("Choose Your Option: ");
            String option = scanner.nextLine();
            option = option.toUpperCase();
            // Different options for various functions
            switch (option) {
                case "A":
                    f1Manager.createDriver();
                    break;
                case "D":
                    f1Manager.deleteDriver();
                    break;
                case "C":
                    f1Manager.changeDriver();
                    break;
                case "F":
                    f1Manager.findDriver();
                    break;
                case "T":
                    f1Manager.displayTable();
                    break;
                case "R":
                    f1Manager.addRace();
                    break;
                case "S":
                    f1Manager.storeToFile();
                    break;
                case "G":
                    f1Manager.launchGui();
                    break;
                default:
                    System.out.println("Enter a correct option!");
                    break;
            }

            System.out.println();
            // Prompting user whether he needs to continue the program.
            System.out.println("Would you like to continue? answer(Y/y[yes] or N/n[no]): ");
            String answer = scanner.nextLine();
            answer = answer.toLowerCase();
            if (answer.equals("y")) {
                System.out.println();
            }
            else {
                System.out.println("|-------------------------------THANK YOU----------------------------------|");
                break;
            }
        }
    }
}

