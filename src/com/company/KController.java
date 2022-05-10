package com.company;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;

/**
 *
 * @author p0073862
 */
public class KController {

    private KModel model;
    private KView view;

    public KController(KModel model, KView view){
        this.model = model;
        this.view = view;
    }

   
    
    void move() {
        model.move();
    }
    
    void reset(){
        model.reset();
    }

    void load() throws FileNotFoundException{
        model.load();
    }

    void save() throws FileNotFoundException{
        model.save();
    }
}
