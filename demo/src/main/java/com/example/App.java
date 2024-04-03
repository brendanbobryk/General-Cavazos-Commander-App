package com.example;

import java.util.Random;
import java.util.Scanner;

import org.json.simple.*;

public class App {

    public static void main(String[] args) {
        String fileName = "C:/Users/Brendan/Documents/GitHub/General-Cavazos-Commander-App/demo/src/main/java/com/example/commands.json";

        Scanner input = new Scanner(System.in);

        // read commands
        JSONArray commandJSONArray = JSONFile.readArray(fileName);
        String[] commandArray = getCommandArray(commandJSONArray);
        // System.out.println(commandArray);

        String selection = "";

        while (!selection.equalsIgnoreCase("q")) {
            System.out.printf(
                    "---------------------------------------------------\nGeneral Cavazos Commander App\n---------------------------------------------------\n");
            System.out.println("i\tIssue a command");
            System.out.println("l\tList all of the commands");
            System.out.println("u\tUndo the last command that was issued");
            System.out.println("r\tRedo the last command that was issued");
            System.out.println("q\tQuit");
            System.out.printf(
                    "---------------------------------------------------\nEnter a command: ");
            selection = input.nextLine().trim();
        }

        /*
         * // print list of all commands
         * System.out.println("----- List of all commands -----");
         * print(commandArray);
         * 
         * System.out.println(
         * "----- Issuing 5 random commands from General Cavazos -----");
         * randomCommand(commandArray, 5);
         */

        input.close();
    }

    // randomly issue commands from General Cavazos
    public static void randomCommand(String[] commandArray, int numCommand) {
        Random rand = new Random();
        System.out.printf("Number\tCommand\n");
        System.out.printf("------\t---------------\n");
        for (int i = 0; i < numCommand; i++) {
            int randIndex = rand.nextInt(commandArray.length);
            System.out.printf("%04d\t%s\n", i, commandArray[randIndex]);
        }
    }

    // print command array
    public static void print(String[] commandArray) {
        System.out.printf("Number\tCommand\n");
        System.out.printf("------\t---------------\n");
        for (int i = 0; i < commandArray.length; i++) {
            System.out.printf("%04d\t%s\n", i, commandArray[i]);
        }
    }

    // get array of commands
    public static String[] getCommandArray(JSONArray commandArray) {
        String[] arr = new String[commandArray.size()];

        // get names from json object
        for (int i = 0; i < commandArray.size(); i++) {
            String command = commandArray.get(i).toString();
            arr[i] = command;
        }
        return arr;
    }
}
