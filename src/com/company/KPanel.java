package com.company;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author p0073862
 */
public class KPanel extends JPanel {

    private final int cellSize;
    private final int width;
    private static final Color OUTSIDE_COLOR = Color.WHITE;
    private KModel model;
    private java.awt.Shape clipShape;
   

    public KPanel(KModel model, int cellSize) {
        this.cellSize = cellSize;
        this.width = 10 * cellSize;
        this.model = model;
        setPreferredSize(new Dimension(width, width));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

       
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(OUTSIDE_COLOR);
        g2.fillRect(0, 0, width, width);
        
        drawRoom(g2);

       
    }

    private void drawRoom(Graphics2D g2) {
        for (Entity e : model.getShapes()) {
            System.out.print(e.toString());
            switch (e.getSymbol()){

                case "*":
                    // Wizard is a blue square
                    g2.setColor(new Color(0,0,250));
                    g2.fillRect(e.getX()*cellSize,e.getY()*cellSize, cellSize/2, cellSize/2);
                    break;

                case "&":
                    // Flying elves identified by a green square
                    g2.setColor(new Color(0, 255, 0));
                    g2.fillRect(e.getX()*cellSize,e.getY()*cellSize, cellSize/2, cellSize/2);
                    break;

                case "#":
                    // Elves identified by a red square
                    g2.setColor(new Color(255,0,0));
                    g2.fillRect(e.getX()*cellSize, e.getY()*cellSize, cellSize/2, cellSize/2);
                    break;
                   
                case "@":
                    // Hobbit a grey circle
                    g2.setColor(new Color(125,125,125));
                    g2.fillOval(e.getX()*cellSize, e.getY()*cellSize, cellSize/2, cellSize/2);
                    break;
                 
              
            }
        }
    }
}


