package com.company;

/*
 * Wizard Entity
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Panagiotis Stathopoulos (19064087)
 */
public class Wizard extends Entity {
    Random rnd = new Random();

    private String name;
    private String type = "Wizard";
    private String symbol = "*";
    private int wisdom = rnd.nextInt(6);
    private int health = 100;
    private int moveDistance = wisdom; // cells it can move

    public Wizard(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    /**
     * Checks which directions the entity is able to move at
     * @returns a list of available directions the entity can move at
     */
    public ArrayList<String> availableDirections() {
        ArrayList<String> availableDirections = new ArrayList<>();

        if (room.isFree(getX(), getY() + 1) && !room.isOutside(getX(), getY() + 1))
            availableDirections.add("BOTTOM");
        if (room.isFree(getX() - 1, getY()) && !room.isOutside(getX() - 1, getY()))
            availableDirections.add("LEFT");
        if (room.isFree(getX() + 1, getY()) && !room.isOutside(getX() + 1, getY()))
            availableDirections.add("RIGHT");
        if (room.isFree(getX() + 1, getY() - 1) && !room.isOutside(getX() + 1, getY() - 1))
            availableDirections.add("TOP_RIGHT");
        if (room.isFree(getX() - 1, getY() - 1) && !room.isOutside(getX() - 1, getY() - 1))
            availableDirections.add("TOP_LEFT");
        if (room.isFree(getX() - 1, getY() + 1) && !room.isOutside(getX() - 1, getY() + 1))
            availableDirections.add("BOTTOM_LEFT");
        if (room.isFree(getX() + 1, getY() + 1) && !room.isOutside(getX() + 1, getY() + 1))
            availableDirections.add("BOTTOM_RIGHT");

        return availableDirections;
    }

    @Override
    public void move() {
        Random rnd = new Random();

        // If can move UP
        if (room.isFree(getX(), getY() - moveDistance) && !room.isOutside(getX(), getY() - moveDistance)) {
            move(getX(), getY() - moveDistance);
        }
        else {
            ArrayList<String> directions = availableDirections();
            String direction = directions.get(rnd.nextInt(directions.size()));

            if (direction.equals("BOTTOM"))
                move(getX(), getY() + 1);
            else if (direction.equals("LEFT"))
                move(getX() - 1, getY());
            else if (direction.equals("RIGHT"))
                move(getX() + 1, getY());
            else if (direction.equals("TOP_RIGHT"))
                move(getX() + 1, getY() - 1);
            else if (direction.equals("TOP_LEFT"))
                move(getX() - 1, getY() - 1);
            else if (direction.equals("BOTTOM_LEFT"))
                move(getX() - 1, getY() + 1);
            else if (direction.equals("BOTTOM_RIGHT"))
                move(getX() + 1, getY() + 1);
        }

        health -= wisdom;
    }

    /**
     * @returns a string with information about the Wizard entity
     */
    @Override
    public String toString() {
        String st = "[Entity Properties]\n";
        st += "Name: " + name + "\n" +
                "Type: " + type + "\n" +
                "Symbol: " + symbol + "\n" +
                "Health: " + health + "\n" +
                "Wisdom: " + wisdom + "\n" +
                "Location: [" + getX() + "," + getY() + "]";
        return st;
    }
}
