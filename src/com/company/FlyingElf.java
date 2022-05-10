package com.company;

/*
 * Flying Elf Entity
 */

/**
 * @author Panagiotis Stathopoulos (19064087)
 */
public class FlyingElf extends Elf {
    private String type = "Flying_Elf";
    private String symbol = "&";
    private int health = 100;

    public FlyingElf(String name) {
        super(name);
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

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void move() {
        int[] coords = room.generateCoordinates(room.getGridSize());
        move(coords[0], coords[1]);
    }

    /**
     * @returns a string with information about the Flying Elf entity
     */
    @Override
    public String toString() {
        String st = "[Entity Properties]\n";
        st += "Name: " + getName() + "\n" +
                "Type: " + type + "\n" +
                "Symbol: " + symbol + "\n" +
                "Health: " + health + "\n" +
                "Location: [" + getX() + "," + getY() + "]";
        return st;
    }
}
