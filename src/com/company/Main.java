package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    private static String[] numbLocations = new String[22];

    public static void main(String[] args) {
        arrayInitializer();
        uniqueNumbAllocation(playerNames());
    }

    public static void arrayInitializer() {
        for (int i = 0; i < 22; i++) {
            numbLocations[i] = "";
        }
    }

    public static int ComputerRandNumb() {
        //gets the computers number
        Random random = new Random();
        int generatedNumb = random.nextInt(22);
        boolean duplicateNumber = true;
        //checks the generated number by the computer hasn't been used already to make the game more realistic
        while (duplicateNumber) {
            if (numbLocations[generatedNumb].equals("Computer")) {
                generatedNumb = random.nextInt(22);
            } else {
                duplicateNumber = false;
            }
        }
        return generatedNumb;
    }

    public static String[] playerNames() {
        //gets the player names and the number of players
        Scanner input = new Scanner(System.in);
        String[] playerNames = new String[0];
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("How many players are there?");
                int players = (input.nextInt()) + 1;
                playerNames = new String[players];
                playerNames[0] = "Computer"; //adds the computer to the array of names
                System.out.println("Enter the name of the players in order of play");
                for (int i = 1; i < players; i++) {
                    playerNames[i] = input.next();
                }
                if (players != 1) {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("You have not typed a number.");
                input.next();
            }
        }
        return playerNames;
    }

    public static void uniqueNumbAllocation(String[] playerNames) {
        Scanner input = new Scanner(System.in);
        int players = playerNames.length;
        int guessedNumb;
        int turn = 0;
        while (turn < 5 * players) {
            try {
                System.out.println("It is " + playerNames[turn % players] + "'s turn");
                if (turn % players != 0) {
                    System.out.println("The numbers are from 0-21. Guess a number.");
                    guessedNumb = input.nextInt();
                } else {
                    System.out.println("The computer has put a number into a slot.");
                    guessedNumb = ComputerRandNumb();
                }
                if (numbLocations[guessedNumb].equals("")) {
                    numbLocations[guessedNumb] = playerNames[turn % players];
                } else {
                    if (turn % players != 0) {
                        System.out.println("Unfortunately the number " + guessedNumb + " is taken.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("You have not typed a number");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("This number is not in the acceptable range.");
            }
            turn++;
        }
        //totals the amount of numbers 'owned' and prints the sum of the players
        int[] totalCount = new int[players];
        for (int i = 0; i < players; i++) {
            for (int j = 0; j < 22; j++) {
                if (numbLocations[j].equals(playerNames[i])) {
                    totalCount[i]++;
                }
            }
        }
        for (int i = 0; i < players; i++) {
            System.out.println("The total for the player " + playerNames[i] + " is " + totalCount[i] + ".");
        }
    }
}
