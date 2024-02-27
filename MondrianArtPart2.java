/** 
 * MondrianArtPart2.java
 * @Author: Sam Clarke, JeongGyu Tak, Nick Ivancovich
 * @Date: 240226
 * @Class: CS&145
 * @Assignment: LAB#5
 * @Purpose: Using recursion to create mondrian style art with some modifications. 
 */

import java.awt.Color;
import java.awt.Graphics;

public class MondrianArtPart2
{
    // CLASS CONSTANTS
    private static final int PANEL_SIZE = 700;
    private static final int MINIMUM_THRESHOLD = 50;
    private static final double RAND_FACTOR = 1.3;
    private static final double LOWER_BOUND = 0.33;
    private static final double UPPER_BOUND = 0.67;

    public static void main(String[] args)
    {
        // INITIALIZE OBJECTS
        DrawingPanel panel = new DrawingPanel(PANEL_SIZE, PANEL_SIZE);
        Graphics g = panel.getGraphics();
        Lab2Random r = new Lab2Random();
        
        // CALL RECURSION METHOD
        drawArt(0, 0, PANEL_SIZE, PANEL_SIZE, g, r);
    }

    /**
     * Creates Mondrian style art through recursion.
    *
    * @param x Origin x value of the region.
    * @param y Origin y value of the region.
    * @param width Region width.
    * @param height Region height.
    * @param g Graphics object.
    * @param r Lab2Random object.
    */
    public static void drawArt(int x, int y, int width, int height, Graphics g, Lab2Random r)
    {   
        // INITIALIZE RANDOM SPLIT VALUES
        int horzSplit = r.nextInt((int)(height*LOWER_BOUND), (int)(height*UPPER_BOUND));
        int vertSplit = r.nextInt((int)(width*LOWER_BOUND), (int)(width*UPPER_BOUND));

        // START RECURSION
        if (width > PANEL_SIZE/2 && height > PANEL_SIZE/2)
        {
            // FOUR REGIONS
            fourRegions(x, y, width, height, g, r, horzSplit, vertSplit);
        }
        else if (width > PANEL_SIZE/2)
        {
            // TWO REGIONS VERTICALLY
            twoRegionsVert(x, y, width, height, g, r, vertSplit);
        }
        else if (height > PANEL_SIZE/2)
        {
            // TWO REGIONS HORIZONTALLY
            twoRegionsHorz(x, y, width, height, g, r, horzSplit);
        }
        else if (r.nextInt(MINIMUM_THRESHOLD, Math.max((int)(width * RAND_FACTOR), MINIMUM_THRESHOLD+1)) < width &&
                r.nextInt(MINIMUM_THRESHOLD, Math.max((int)(height * RAND_FACTOR), MINIMUM_THRESHOLD+1)) < height)
        {
            // FOUR REGIONS
            fourRegions(x, y, width, height, g, r, horzSplit, vertSplit);
        }
        else if (r.nextInt(MINIMUM_THRESHOLD, Math.max((int)(width * RAND_FACTOR), MINIMUM_THRESHOLD+1)) < width)
        {
            // TWO REGIONS VERTICALLY
            twoRegionsVert(x, y, width, height, g, r, vertSplit);
        }
        else if (r.nextInt(MINIMUM_THRESHOLD, Math.max((int)(height * RAND_FACTOR), MINIMUM_THRESHOLD+1)) < height)
        {
            // TWO REGIONS HORIZONTALLY
            twoRegionsHorz(x, y, width, height, g, r, horzSplit);
        }
        else
        {
            // PICK COLOR
            int colorPicker = r.nextInt(6);
            switch (colorPicker)
            {
                case 0: 
                    g.setColor(Color.RED);
                    break;
                case 1:
                    g.setColor(Color.DARK_GRAY);
                case 2:
                    g.setColor(Color.GRAY);
                    break;
                case 3:
                    g.setColor(Color.ORANGE);
                    break;
                case 4:
                    g.setColor(Color.YELLOW);
                    break;
                case 5:
                    g.setColor(Color.BLACK);
                    break;
            }

            // GRADIENT
            if (x + width/2 < PANEL_SIZE/9)
            {
                g.setColor(g.getColor().darker().darker().darker().darker());
            }
            else if (x + width/2 < 2*PANEL_SIZE/9)
            {
                g.setColor(g.getColor().darker().darker().darker());
            }
            else if (x + width/2 < 3*PANEL_SIZE/9)
            {
                g.setColor(g.getColor().darker().darker());
            }
            else if (x + width/2 < 4*PANEL_SIZE/9)
            {
                g.setColor(g.getColor().darker());
            }
            else if (x + width/2 < 5*PANEL_SIZE/9)
            {
                g.setColor(g.getColor());
            }
            else if (x + width/2 < 6*PANEL_SIZE/9)
            {
                g.setColor(g.getColor().darker());
            }
            else if (x + width/2 < 7*PANEL_SIZE/9)
            {
                g.setColor(g.getColor().darker().darker());
            }
            else if (x + width/2 < 8*PANEL_SIZE/9)
            {
                g.setColor(g.getColor().darker().darker().darker());
            }
            else
            {
                g.setColor(g.getColor().darker().darker().darker().darker());
            }

            g.fillRect(x, y, width, height); // DRAW COLOR
            g.setColor(Color.BLACK); // BORDER COLOR
            g.drawRect(x, y, width, height); // DRAW BORDER

            // PICK AND DRAW SHAPES
            int shapePicker = r.nextInt(3);
            switch (shapePicker)
            {
                case 0: // DRAW X
                    g.drawLine(x, y, x+width, y+height);
                    g.drawLine(x, y+height, x+width, y);
                    break;
                case 1: // DRAW O
                    g.drawOval(x, y, width, height);
                    break;
                case 2: // DRAW PACMAN
                    int[] xPoints = {x, x+width/2, x+width, x+width/2, x+width, x+width/2};
                    int[] yPoints = {y+height/2, y, y+height/4, y+height/2, y+3*height/4, y+height};
                    g.drawPolygon(xPoints, yPoints, 6);
                    break;
            }
        }
    }

    /**
     * Splits a region into four sperate regions
    *
    * @param x Origin x value of the region.
    * @param y Origin y value of the region.
    * @param width Region width.
    * @param height Region height.
    * @param g Graphics object.
    * @param r Lab2Random object.
    * @param horzSplit Horizontal split line
    * @param vertSplit Vertical split line
    */
    private static void fourRegions(int x, int y, int width, int height, Graphics g, Lab2Random r, int horzSplit, int vertSplit)
    {
        drawArt(x, y, vertSplit, horzSplit, g, r);
        drawArt(x + vertSplit, y, width - vertSplit, horzSplit, g, r);
        drawArt(x, y + horzSplit, vertSplit, height - horzSplit, g, r);
        drawArt(x + vertSplit, y + horzSplit, width - vertSplit, height - horzSplit, g, r);
    }

    /**
     * Splits a region into two sperate regions vertically
    *
    * @param x Origin x value of the region.
    * @param y Origin y value of the region.
    * @param width Region width.
    * @param height Region height.
    * @param g Graphics object.
    * @param r Lab2Random object.
    * @param vertSplit Vertical split line
    */
    private static void twoRegionsVert(int x, int y, int width, int height, Graphics g, Lab2Random r, int vertSplit)
    {
        drawArt(x, y, vertSplit, height, g, r);
        drawArt(x + vertSplit, y, width - vertSplit, height, g, r);
    }

    /**
     * Splits a region into two sperate regions horizontally
    *
    * @param x Origin x value of the region.
    * @param y Origin y value of the region.
    * @param width Region width.
    * @param height Region height.
    * @param g Graphics object.
    * @param r Lab2Random object.
    * @param horzSplit Horizontal split line
    */
    private static void twoRegionsHorz(int x, int y, int width, int height, Graphics g, Lab2Random r, int horzSplit)
    {
        drawArt(x, y, width, horzSplit, g, r);
        drawArt(x, y + horzSplit, width, height - horzSplit, g, r);
    }
}