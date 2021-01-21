package w1790317.demo;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;

import java.awt.*;
import java.net.URI;
import java.util.Scanner;

public class Console {

    private static PremierLeagueManager pm = new PremierLeagueManager();
    private static FxGUI gui = new FxGUI();

    public static void main(String  [] args) throws Exception {
        pm.loadData("src/clubs.txt");
        pm.loadMatch("src/matches.txt");
        pm.loadName();
        pm.loadDate();
        System.out.println("---- WELCOME TO PREMIER LEAGUE MANAGEMENT----");
        System.out.println("---------------------------------------------");
        System.out.println();
        menu();
    }

    private static void menu() throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1 : Create a New Football Club");
        System.out.println("Press 2 : Delete a Football Club");
        System.out.println("Press 3 : Display Season Statistics");
        System.out.println("Press 4 : Display Club Table");
        System.out.println("Press 5 : Add a Played Match");
        System.out.println("Press 6 : Open JavaFX GUI");
        System.out.println("Press 7 : Open Spring Server");
        System.out.println("Press 8 : Open Web App");
        System.out.println("Press 9 : To Exit");
        System.out.println("---------------------------------------");
        System.out.print("Enter Your Requirement: ");
        String choice = sc.next();

        switch (choice) {
            case "1":
                addNewClub();
                break;
            case "2":
                deleteClub();
                break;
            case "3":
                pm.leagueStatistics();
                menu();
                break;
            case "4":
                displaySelectedClub();
                menu();
                break;
            case "5":
                updateClub();
                break;
            case "6":
                Application.launch(FxGUI.class);
                menu();
                break;
            case "7":
                SpringApplication.run(W1790317Application.class);
                menu();
            case "8":
                openAngularApp();
                break;
            case "9":
                pm.saveData("src/clubs.txt");
                pm.saveMatch("src/matches.txt");
                System.out.println("Data Saved Successfully");
                break;
            default:
                System.out.println("Invalid User Input. Try Again");
                menu();
        }
    }

    private static void addNewClub() throws Exception {
        System.out.println("---------------------------------------");
        if(PremierLeagueManager.clubs.size() >= 10) {
            System.out.println("League Full. New Club Can Not be Added.");
            menu();
        }
        else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the Name of the Club: ");
            String name = sc.nextLine();
            System.out.print("Enter the Club Location: ");
            String location = sc.next();
            System.out.println("---------------------------------------");
            System.out.print("If You Wish to Add More Details of the Club, Press 'Y' else Press 'N': ");
            String proceed = sc.next();
            System.out.println("---------------------------------------");

            if (proceed.equalsIgnoreCase("Y")) {

                System.out.print("Enter Number of Wins: ");
                int wins = sc.nextInt();
                System.out.print("Enter Number of Draws: ");
                int draws = sc.nextInt();
                System.out.print("Enter Number of Defeats: ");
                int defeats = sc.nextInt();
                System.out.print("Enter Number of Goals Scored: ");
                int goalsScored = sc.nextInt();
                System.out.print("Enter Number of Goals Received: ");
                int goalsReceived = sc.nextInt();
                System.out.print("Enter Number of Matches Played: ");
                int matchesPlayed = sc.nextInt();
                System.out.print("Enter Number of Points: ");
                int points = sc.nextInt();

                int day = 0;
                int month = 0;
                boolean isDayValid;
                boolean isMonthValid;

                do {
                    isDayValid = true;
                    try {
                        System.out.print("Enter the Day: ");
                        day = sc.nextInt();
                        if(day < 0  || day > 31) {
                            isDayValid = false;
                            System.out.println("Day should be an integer between 1 and 31.");
                        }
                    }catch (NumberFormatException e) {
                        isDayValid = false;
                        System.out.println("Try Again");
                    }
                } while (!isDayValid);

                do {
                    isMonthValid = true;
                    try {
                        System.out.print("Enter the Month: ");
                        month = sc.nextInt();
                        if(month < 0 || month > 12) {
                            isMonthValid = false;
                            System.out.println("Month should be an integer between 1 and 12.");
                        }
                    }catch (NumberFormatException e) {
                        isMonthValid = false;
                        System.out.println("Try Again");
                    }
                } while (!isMonthValid);

                System.out.print("Enter the Year: ");
                int year = sc.nextInt();
                Date date = new Date(day,month,year);
                FootballClub footballClub = new FootballClub(name,location,wins,draws,defeats,goalsScored,goalsReceived,points,matchesPlayed,date);
                pm.addNewClub(footballClub);
                System.out.println("---------------------------------------");
                menu();
            }
            else if (proceed.equalsIgnoreCase("N")) {
                FootballClub fc = new FootballClub(name,location);
                pm.addNewClub(fc);
                System.out.println("---------------------------------------");
                menu();
            }
            else {
                System.out.println("Invalid User Input");
                System.out.println("---------------------------------------");
                menu();
            }
        }
    }

    private static void updateClub() throws Exception {
        Scanner sc = new Scanner(System.in);
        pm.displayClub();

        System.out.print("Enter Club A: ");
        String clubOne = sc.nextLine();

        System.out.print("Enter Club B: ");
        String clubTwo = sc.nextLine();

        System.out.print("Enter Club A Points: ");
        int clubOnePoints = sc.nextInt();

        System.out.print("Enter Club B Points: ");
        int clubTwoPoints = sc.nextInt();

        System.out.print("Enter Club A Goals: ");
        int clubOneGoals = sc.nextInt();

        System.out.print("Enter Club B Goals: ");
        int clubTwoGoals = sc.nextInt();

        int day = 0;
        int month = 0;
        boolean isDayValid;
        boolean isMonthValid;

        do {
            isDayValid = true;
            try {
                System.out.print("Enter the Day: ");
                day = sc.nextInt();
                if(day < 0  || day > 31) {
                    isDayValid = false;
                    System.out.println("Day should be an integer between 1 and 31.");
                }
            }catch (NumberFormatException e) {
                isDayValid = false;
                System.out.println("Try Again");
            }
        } while (!isDayValid);

        do {
            isMonthValid = true;
            try {
                System.out.print("Enter the Month: ");
                month = sc.nextInt();
                if(month < 0 || month > 12) {
                    isMonthValid = false;
                    System.out.println("Month should be an integer between 1 and 12.");
                }
            }catch (NumberFormatException e) {
                isMonthValid = false;
                System.out.println("Try Again");
            }
        } while (!isMonthValid);

        System.out.print("Enter the Year: ");
        int year = sc.nextInt();
        Date date = new Date(day,month,year);
        pm.updateMatch(clubOne,clubTwo,clubOnePoints,clubTwoPoints,clubOneGoals,clubTwoGoals,date);
        System.out.println("---------------------------------------");
        menu();
    }

    private static void deleteClub() throws Exception {
        pm.displayClub();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Club Name: ");
        String removedClub = sc.nextLine();
        pm.deleteClub(removedClub);
        System.out.println("---------------------------------------");
        menu();
    }

    private static void displaySelectedClub() throws Exception {
        pm.displayClub();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Club Name: ");
        String name = sc.nextLine();
        pm.displayClub(name);
    }

    private static void openAngularApp() throws Exception {
        System.setProperty("java.awt.headless", "false");
        Desktop d = Desktop.getDesktop();
        d.browse(URI.create("http://localhost:4200/"));
        menu();
    }
}
