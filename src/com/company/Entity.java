package com.company;

/*
 * Main Entity class that all other entities will inherit from
 */

/**
 * @author Panagiotis Stathopoulos (19064087)
 */
public abstract class Entity implements Moveable {
    Room room = new Room();

    private String name;
    private String symbol; // symbol that represents the entity
    private String type; // every entity is of a type
    private int health;
    private int x; // x (column) coordinate in the room
    private int y; // y (row) coordinate in the room

    public Entity() {
        type = "entity";
        symbol = "E";
        x = -1;
        y = -1;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getType() {
        return type;
    }

    public int getX() {
        return x;
    }
 
    public int getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public void setSymbol(String s) {
        symbol = s;
    }
 
    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Moves an entity to specific coordinates
     * @param x coordinate X
     * @param y coordinate Y
     */
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Resets the entity by repositioning it to random coordinates
     */
    public void reset() {
        int[] coords = room.generateCoordinates(room.getGridSize());
        move(coords[0], coords[1]);
    }

    /**
    * @returns a string with information about an abstract entity
    */
    @Override
    public String toString() {
        String st = "\n[Entity Properties]\n";
        st += "TYPE: " + type + "\n" +
                "NAME: " + name + "\n" +
                "SYMBOL: " + symbol + "\n" +
                "LOCATION: [" + x + "," + y + "]";
        return st;
    }
}
