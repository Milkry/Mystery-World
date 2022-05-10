package com.company;

/*
 * Main template class with the menu with all the options
 */

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Panagiotis Stathopoulos (19064087)
 */
public class GameController {

    static void menu(){
        System.out.println("Choose an option...");
        System.out.println("1. Display Room");
        System.out.println("2. Display Entity Information");
        System.out.println("3. Reset the room");
        System.out.println("4. Move all entities");
        System.out.println("5. Load game");
        System.out.println("6. Save game");
        System.out.println("0. Exit");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        Room world = new Room();

        world.generateEntities();
        System.out.println(world.toString());
     
        Scanner kb = new Scanner(System.in);
        int option;

        do {
	        menu();
	        System.out.print("> ");
            option = kb.nextInt();

            switch(option) {
                case 1: System.out.println("\n" + world.toString());
                        break;

                case 2: System.out.println("Enter the position of the entity you want to display details about...");
                        System.out.print("Column: ");
                        int column = kb.nextInt();
                        System.out.print("Row: ");
                        int row = kb.nextInt();
                        if ( (column >= world.getGridSize() || column < 0) || (row >= world.getGridSize() || row < 0) ) {
                            System.out.println("\n" + "Coordinates out of bounds." + "\n");
                            break;
                        }
                        System.out.println("\n" + world.displayEntity(column, row) + "\n");
                        break;

                case 3: System.out.println("\n" + "Room is resetting...");
                        world.resetRoom();
                        System.out.println("Room successfully reset!" + "\n");
                        break;

                case 4: System.out.println("\n" + "Entities moved..." + "\n");
                        world.move();
                        break;

                case 5: System.out.println("\n" + "Loading save file...");
                        world.load("lor.txt");
                        System.out.println("Save file successfully loaded!" + "\n");
                        break;

                case 6: System.out.println("\n" + "Saving game...");
                        world.save("lor.txt");
                        System.out.println("Game saved!" + "\n");
                        break;

                case 0: System.out.println("\n" + " ** Game Over ** ");
                        break;

                default: System.out.println("Invalid option...");
            }
        } while (option != 0);
    }
 }