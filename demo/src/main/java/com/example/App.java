package com.example;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import org.json.simple.*;

public class App {

    public static Stack<String> undoStack = new Stack<String>();

    public static void main(String[] args) {
        // file path for json file of commands
        String fileName = "C:/Users/Brendan/Documents/GitHub/General-Cavazos-Commander-App/demo/src/main/java/com/example/commands.json";

        // creates scanner
        Scanner input = new Scanner(System.in);

        // read commands
        JSONArray commandJSONArray = JSONFile.readArray(fileName);
        String[] commandArray = getCommandArray(commandJSONArray);

        Stack<String> redoStack = new Stack<String>();
        String selection = "";
        String command = "";

        while (!selection.equalsIgnoreCase("q")) {
            // prints menu
            System.out.println(
                    "---------------------------------------------------------------------------------\nGeneral Cavazos Commander App\n---------------------------------------------------------------------------------");
            System.out.println("i\tIssue a command");
            System.out.println("l\tList all of the commands");
            System.out.println("u\tUndo the last command that was issued");
            System.out.println("r\tRedo the last command that was issued");
            System.out.println("q\tQuit");
            System.out.printf(
                    "---------------------------------------------------------------------------------\nEnter a command: ");
            // retrieve user input
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
                    if (!undoStack.empty()) {
                        System.out.printf("[UNDO COMMAND ISSUED]: General Cavazos orders the troops to undo: ");
                        command = undoStack.pop();
                        redoStack.push(command);
                        System.out.println(command);
                    } else { // prints error if stack is empty
                        System.out.println("[ERROR]: There are no commands to undo. Please issue a command.");
                    }
                    break;
                // Redo the last command that was issued
                case "r":
                    if (!redoStack.empty()) {
                        System.out.printf("[REDO COMMAND ISSUED]: General Cavazos orders the troops to redo: ");
                        command = redoStack.pop();
                        undoStack.push(command);
                        System.out.println(command);
                    } else { // prints error if stack is empty
                        System.out.println("[ERROR]: There are no commands to redo. Please issue a command.");
                    }

                    break;
            }
        }

        // prompt for when program closes
        System.out.println("Thank you General Cavazos!");

        // closes scanner
        input.close();
    }

    // randomly issues a command from General Cavazos
    public static void randomCommand(String[] commandArray, int numCommand) {
        Random rand = new Random();
        int randIndex = rand.nextInt(commandArray.length);
        undoStack.push(commandArray[randIndex]);
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
