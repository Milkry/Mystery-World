package com.company;

import java.util.ArrayList;
import java.util.Observable;
import java.io.FileNotFoundException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author p0073862
 */
public class KModel extends Observable {

    private static final int WIDTH = 600;
   
    Room room = new Room();
  
    public KModel () throws FileNotFoundException {
    }

    public ArrayList<Entity> getShapes() {
        return room.getEntities();
    }

    public void reset(){
        room.resetRoom();
        sendUpdate();
    }

    private void sendUpdate() {
        setChanged();
        notifyObservers();
    }

    void move() {
        room.move();
        sendUpdate();
    }

    // Entities are assumned to be stored in a file called "lor.txt"
    void load() throws FileNotFoundException {
        room.load("lor.txt");
        sendUpdate();
    }

    void save() throws FileNotFoundException{
        room.save("lor.txt");
        sendUpdate();
    }
}
