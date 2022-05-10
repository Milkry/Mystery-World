package com.company;

import java.io.FileNotFoundException;

/*
 * Storage system
 */

/**
 * @author p0074487
 */
public interface Storage {
    public void save(String name) throws FileNotFoundException;
    public void load(String name) throws FileNotFoundException;
}
