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

        String selection = "";

        while (!selection.equalsIgnoreCase("q")) {
            System.out.println(
                    "---------------------------------------------------------------------------------\nGeneral Cavazos Commander App\n---------------------------------------------------------------------------------");
            System.out.println("i\tIssue a command");
            System.out.println("l\tList all of the commands");
            System.out.println("u\tUndo the last command that was issued");
            System.out.println("r\tRedo the last command that was issued");
            System.out.println("q\tQuit");
            System.out.printf(
                    "---------------------------------------------------------------------------------\nEnter a command: ");
            selection = input.nextLine().trim();
            switch (selection.toLowerCase()) {
                // Issue a command
                case "i":
                    System.out.printf("[COMMAND ISSUED]: General Cavazos orders the troops to do: ");
                    randomCommand(commandArray, 1);
                    break;
                // List all of the commands
                case "l":
                    System.out.println("[LIST OF COMMANDS]: ");
                    print(commandArray);
                    break;
                // Undo the last command that was issued
                case "u":
                    System.out.printf("[UNDO COMMAND ISSUED]: General Cavazos orders the troops to undo: ");
                    break;
                // Redo the last command that was issued
                case "r":
                    System.out.printf("[REDO COMMAND ISSUED]: General Cavazos orders the troops to redo: ");
                    break;
            }
        }

        System.out.println("Thank you General Cavazos!");

        input.close();
    }

    // randomly issues a command from General Cavazos
    public static void randomCommand(String[] commandArray, int numCommand) {
        Random rand = new Random();
        int randIndex = rand.nextInt(commandArray.length);
        System.out.println(commandArray[randIndex]);
    }

    // print command array
    public static void print(String[] commandArray) {
        System.out.printf("Number\tCommand\n");
        System.out.printf("---------------------------------------------------------------------------------\n");
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
