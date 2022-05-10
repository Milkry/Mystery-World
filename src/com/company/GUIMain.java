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
public class GUIMain {
    public static void main(String[] args) throws FileNotFoundException{
        KModel model = new KModel();
        KView view = new KView(model);
        KController controller = new KController(model, view);
        view.setController(controller);
        model.addObserver(view);
        view.update(model, null);
    }
}
