package com.company;

/*
 * Elf Entity
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Panagiotis Stathopoulos (19064087)
 */
public class Elf extends Entity {
    private String name;
    private String type = "Elf";
    private String symbol = "#";
    private int health = 100;
    private int moveDistance = 1; // cells it can move

    public Elf(String name) {
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

    /**
     * Checks which directions the entity is able to move at
     * @returns a list of available directions the entity can move at
     */
    public ArrayList<String> availableDirections() {
        ArrayList<String> availableDirections = new ArrayList<>();

        if (room.isFree(getX(), getY() - moveDistance) && !room.isOutside(getX(), getY() - moveDistance))
            availableDirections.add("TOP");
        if (room.isFree(getX(), getY() + moveDistance) && !room.isOutside(getX(), getY() + moveDistance))
            availableDirections.add("BOTTOM");
        if (room.isFree(getX() - moveDistance, getY()) && !room.isOutside(getX() - moveDistance, getY()))
            availableDirections.add("LEFT");
        if (room.isFree(getX() + moveDistance, getY()) && !room.isOutside(getX() + moveDistance, getY()))
            availableDirections.add("RIGHT");
        if (room.isFree(getX() + moveDistance, getY() - moveDistance) && !room.isOutside(getX() + moveDistance, getY() - moveDistance))
            availableDirections.add("TOP_RIGHT");
        if (room.isFree(getX() - moveDistance, getY() - moveDistance) && !room.isOutside(getX() - moveDistance, getY() - moveDistance))
            availableDirections.add("TOP_LEFT");
        if (room.isFree(getX() - moveDistance, getY() + moveDistance) && !room.isOutside(getX() - moveDistance, getY() + moveDistance))
            availableDirections.add("BOTTOM_LEFT");
        if (room.isFree(getX() + moveDistance, getY() + moveDistance) && !room.isOutside(getX() + moveDistance, getY() + moveDistance))
            availableDirections.add("BOTTOM_RIGHT");

        return availableDirections;
    }

    @Override
    public void move() {
        Random rnd = new Random();

        ArrayList<String> directions = availableDirections();
        String direction = directions.get(rnd.nextInt(directions.size()));

        if (direction.equals("TOP"))
            move(getX(), getY() - moveDistance);
        else if (direction.equals("BOTTOM"))
            move(getX(), getY() + moveDistance);
        else if (direction.equals("LEFT"))
            move(getX() - moveDistance, getY());
        else if (direction.equals("RIGHT"))
            move(getX() + moveDistance, getY());
        else if (direction.equals("TOP_RIGHT"))
            move(getX() + moveDistance, getY() - moveDistance);
        else if (direction.equals("TOP_LEFT"))
            move(getX() - moveDistance, getY() - moveDistance);
        else if (direction.equals("BOTTOM_LEFT"))
            move(getX() - moveDistance, getY() + moveDistance);
        else if (direction.equals("BOTTOM_RIGHT"))
            move(getX() + moveDistance, getY() + moveDistance);

        health -= rnd.nextInt(10);
    }

    /**
     * @returns a string with information about the Elf entity
     */
    @Override
    public String toString() {
        String st = "[Entity Properties]\n";
        st += "Name: " + name + "\n" +
                "Type: " + type + "\n" +
                "Symbol: " + symbol + "\n" +
                "Health: " + health + "\n" +
                "Location: [" + getX() + "," + getY() + "]";
        return st;
    }
}
