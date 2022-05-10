package com.company;

/*
 * Class that stores all the entities in an instance of a room
 * Methods to add entities, display the room, display room information, etc.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Panagiotis Stathopoulos (19064087)
 */
public class Room implements Storage {
    private static ArrayList<Entity> entities = new ArrayList<Entity>();
    private int gridSize = 10;

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public int getGridSize() {
        return gridSize;
    }

    /**
     * Generates a set of random coordinates within grid size boundaries.
     * Also does proper checks if those random coordinates point to an occupied cell.
     * And if so it generates different ones.
     * @param boundary the number to generate up to
     * @returns a set of random coordinates (Array = {x, y})
     */
    public int[] generateCoordinates(int boundary) {
        Random rnd = new Random();
        int x, y;

        do {
            x = rnd.nextInt(boundary);
            y = rnd.nextInt(boundary);
        } while (!isFree(x, y)); // Keeps generating random coordinates until it finds a free cell

        int[] coords = {x, y};
        return coords;
    }

    /**
     * Method that generates entities in random free cells.
     */
    public void generateEntities() {
        int[] coords;

        coords = generateCoordinates(gridSize);
        addNewEntity(new Wizard("Gandalf"), coords[0], coords[1]);
        coords = generateCoordinates(gridSize);
        addNewEntity(new Wizard("Saruman"), coords[0], coords[1]);
        coords = generateCoordinates(gridSize);
        addNewEntity(new Hobbit("Frodo", 50), coords[0], coords[1]);
        coords = generateCoordinates(gridSize);
        addNewEntity(new Hobbit("Bilbo", 128), coords[0], coords[1]);
        coords = generateCoordinates(gridSize);
        addNewEntity(new Hobbit("Sam", 38), coords[0], coords[1]);
        coords = generateCoordinates(gridSize);
        addNewEntity(new FlyingElf("Galadriel"), coords[0], coords[1]);
        coords = generateCoordinates(gridSize);
        addNewEntity(new Elf("Legolas"), coords[0], coords[1]);
        coords = generateCoordinates(gridSize);
        addNewEntity(new Elf("Elrond"), coords[0], coords[1]);
    }

    /**
     * Repositions current entities to random free cells.
     */
    public void resetRoom() {
        for (Entity e : entities) {
            e.reset();
        }
    }
   
    /**
     * Method that adds a new entity in the entities list at a specific position.
     * PRE: position (x, y) must be empty
     * @param e the entity
     * @param x column 0 <= x <= 9
     * @param y row 0 <= y <= 9
     */
    public void addNewEntity(Entity e, int x, int y ) {
        entities.add(e);
        e.move(x, y);
    }

    /**
     * Empties the list of entities (Deletes all entities)
     */
    public void clearRoom() {
        entities.clear();
    }

    /**
     * Method that checks if a cell at specific coordinates is occupied by an entity or not.
     * @param x column 0 <= x <= 9
     * @param y row 0 <= y <= 9
     * @returns FALSE if cell is occupied | TRUE if cell is not occupied
     */
    public boolean isFree(int x, int y) {
        for (Entity e : entities) {
            if (e.getX() == x && e.getY() == y)
                return false;
        }
        return true;
    }

    /**
     * Method that checks if a set of coordinates point outside the grid.
     * @param x column
     * @param y row
     * @returns TRUE if they point outside | FALSE if they're inside
     */
    public boolean isOutside(int x, int y) {
        return (x >= gridSize || x < 0) || (y >= gridSize || y < 0);
    }

    /**
     * Method that returns the index position of an entity (from the entities arraylist) given its coordinates.
     * @param x column 0 <= x <= 9
     * @param y row 0 <= y <= 9
     * @returns index from the list | -1 if the coordinates do not point at an entity
     */
    private int getPosition(int x, int y) {
        if (!isFree(x, y)) {
            for (Entity e : entities) {
                if (e.getX() == x && e.getY() == y) {
                    return entities.indexOf(e);
                }
            }
        }
        return -1;
    }

    /**
     * Displays all the properties of an entity that occupies a specific cell.
     * PRE: Cell must not be empty
     * @param x column 0 <= x <= 9
     * @param y row 0 <= y <= 9
     * @returns a string with the properties of the entity | returns an appropriate message if there is no entity
     */
    public String displayEntity(int x, int y) {
        if (getPosition(x, y) != -1) {
            return entities.get(getPosition(x, y)).toString();
        }
        else
            return "No entity occupies that cell...";
    }

    /**
     * Searches for an entity from the entities list, given its coordinates.
     * @param x column 0 <= x <= 9
     * @param y row 0 <= y <= 9
     * @returns the entity from the list | returns null if not found
     */
    public Entity getEntity(int x, int y) {
        if (getPosition(x, y) != -1) {
            return entities.get(getPosition(x, y));
        }
        return null;
    }

    /**
     * Method that moves all entities.
     * Calls each entity's move method.
     */
    public void move() {
        for (Entity e : entities) {
            e.move();
        }
    }

    /**
     * Displays the room in ASCII form (Includes placed entities).
     * @returns a string representation of the room (MysteryWorld)
     */
    public String toString() {
        String room = "";
        boolean found = false;

        // Places the top numbers (very top row)
        room += "   ";
        for (int i = 0; i < gridSize; i++)
            room += i + "  ";
        room += "\n";

        // Places the rest (from row 0 - 9)
        for (int column = 0; column < gridSize; column++) { // Rows (column = x)
            room += column + " ";
            for (int row = 0; row < gridSize; row++) { // Columns (row = y)
                for (Entity e : entities) {
                    if (e.getX() == row && e.getY() == column) {
                        room += " " + e.getSymbol() + " ";
                        found = true;
                    }
                }
                if (!found)
                    room += " . ";
                found = false;
            }
            room += "\n";
        }
        return room;
    }

    /**
     * Method that returns a string with basic entity properties to be written in a file.
     * @param entity Entity to display info about
     * @returns a string with basic properties
     */
    public String saveBasic(Entity entity) {
        String basic = "";

        basic += entity.getType() + " ";
        basic += entity.getName() + " ";
        basic += entity.getHealth() + " ";
        basic += entity.getX() + " ";
        basic += entity.getY() + " ";

        return basic;
    }

    /**
     * Saves the state of the game to a file called "lor.txt".
     *
     * Save Format: {type} {name} {health} {x} {y} {special attributes}
     * Special attributes refer to wisdom, age, etc.
     *
     * @param file file name
     * @throws FileNotFoundException
     */
    @Override
    public void save(String file) throws FileNotFoundException {
        PrintWriter save = new PrintWriter(file);

        ArrayList<Wizard> wizards = new ArrayList<Wizard>();
        ArrayList<Hobbit> hobbits = new ArrayList<Hobbit>();
        ArrayList<Elf> elves = new ArrayList<Elf>();
        ArrayList<FlyingElf> flyingElves = new ArrayList<FlyingElf>();

        // Adds each entity to their own list type
        for (Entity entity : entities) {
            if (entity.getType().equals("Wizard"))
                wizards.add((Wizard) entity);
            else if (entity.getType().equals("Hobbit"))
                hobbits.add((Hobbit) entity);
            else if (entity.getType().equals("Elf"))
                elves.add((Elf) entity);
            else if (entity.getType().equals("Flying_Elf"))
                flyingElves.add((FlyingElf) entity);
        }

        for (Wizard e : wizards) {
            save.print(saveBasic(e));
            save.print(e.getWisdom());
            save.println();
        }

        for (Hobbit e : hobbits) {
            save.print(saveBasic(e));
            save.print(e.getAge());
            save.println();
        }

        for (Elf e : elves) {
            save.print(saveBasic(e));
            save.println();
        }

        for (FlyingElf e : flyingElves) {
            save.print(saveBasic(e));
            save.println();
        }

        save.close();
    }

    /**
     * Loads a game save from a save file.
     *
     * @param file file to load from
     * @throws FileNotFoundException
     */
    @Override
    public void load(String file) throws FileNotFoundException {
        FileInputStream save = new FileInputStream(file);
        Scanner scan = new Scanner(save);
        entities.clear();

        while (scan.hasNext()) {
            String entity = scan.next();
            if (entity.equals("Wizard")) {
                //load wizard entity
                Wizard wizard = new Wizard(scan.next());
                wizard.setHealth(scan.nextInt());
                wizard.move(scan.nextInt(), scan.nextInt());
                wizard.setWisdom(scan.nextInt());
                entities.add(wizard);
            }
            else if (entity.equals("Hobbit")) {
                //load hobbit entity
                String entityName = scan.next();
                int entityHealth = scan.nextInt();
                int entityX = scan.nextInt();
                int entityY = scan.nextInt();
                int entityAge = scan.nextInt();
                Hobbit hobbit = new Hobbit(entityName, entityAge);
                hobbit.setHealth(entityHealth);
                hobbit.move(entityX, entityY);
                entities.add(hobbit);
            }
            else if (entity.equals("Elf")) {
                //load elf entity
                Elf elf = new Elf(scan.next());
                elf.setHealth(scan.nextInt());
                elf.move(scan.nextInt(), scan.nextInt());
                entities.add(elf);
            }
            else if (entity.equals("Flying_Elf")) {
                //load flying elf entity
                FlyingElf flyingElf = new FlyingElf(scan.next());
                flyingElf.setHealth(scan.nextInt());
                flyingElf.move(scan.nextInt(), scan.nextInt());
                entities.add(flyingElf);
            }
        }
    }
}