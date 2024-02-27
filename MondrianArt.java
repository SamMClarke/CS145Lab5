/** 
 * MondrianArt.java
 * @Author: Sam Clarke, JeongGyu Tak, Nick Ivancovich
 * @Date: 240224
 * @Class: CS&145
 * @Assignment: LAB#5
 * @Purpose: Using recursion to create Mondrian-style art. 
 */

import java.awt.Color;
import java.awt.Graphics;

public class MondrianArt {
    // CLASS CONSTANTS
    private static final int PANEL_SIZE = 700;
    private static final int MINIMUM_THRESHOLD = 100;
    private static final double RAND_FACTOR = 1.5;
    private static final double LOWER_BOUND = 0.33;
    private static final double UPPER_BOUND = 0.67;

    public static void main(String[] args) {
        // INITIALIZE OBJECTS
        DrawingPanel panel = new DrawingPanel(PANEL_SIZE, PANEL_SIZE);
        Graphics g = panel.getGraphics();
        
        // CALL RECURSION METHOD
        drawArt(0, 0, PANEL_SIZE, PANEL_SIZE, g);
    }

    /**
     * Creates Mondrian-style art through recursion.
     *
     * @param x Origin x value of the region.
     * @param y Origin y value of the region.
     * @param width Region width.
     * @param height Region height.
     * @param g Graphics object.
     * @param r Lab2Random object.
     */
    public static void drawArt(int x, int y, int width, int height, Graphics g) {   
        Lab2Random r = new Lab2Random();
        // INITIALIZE RANDOM SPLIT VALUES
        int horzSplit = r.nextInt((int)(height * LOWER_BOUND), (int)(height * UPPER_BOUND));
        int vertSplit = r.nextInt((int)(width * LOWER_BOUND), (int)(width * UPPER_BOUND));

        // START RECURSION
        if (width > PANEL_SIZE / 2 && height > PANEL_SIZE / 2) {
            fourRegions(x, y, width, height, g, horzSplit, vertSplit); // FOUR REGIONS
        } else if (width > PANEL_SIZE / 2) {
            twoRegionsVert(x, y, width, height, g, vertSplit); // TWO REGIONS VERTICALLY
        } else if (height > PANEL_SIZE / 2) {
            twoRegionsHorz(x, y, width, height, g, horzSplit); // TWO REGIONS HORIZONTALLY
        } else if (r.nextInt(MINIMUM_THRESHOLD, Math.max((int)(width * RAND_FACTOR), MINIMUM_THRESHOLD + 1)) < width &&
                r.nextInt(MINIMUM_THRESHOLD, Math.max((int)(height * RAND_FACTOR), MINIMUM_THRESHOLD + 1)) < height) {
            fourRegions(x, y, width, height, g, horzSplit, vertSplit); // FOUR REGIONS
        } else if (r.nextInt(MINIMUM_THRESHOLD, Math.max((int)(width * RAND_FACTOR), MINIMUM_THRESHOLD + 1)) < width) {
            twoRegionsVert(x, y, width, height, g, vertSplit); // TWO REGIONS VERTICALLY
        } else if (r.nextInt(MINIMUM_THRESHOLD, Math.max((int)(height * RAND_FACTOR), MINIMUM_THRESHOLD + 1)) < height) {
            twoRegionsHorz(x, y, width, height, g, horzSplit); // TWO REGIONS HORIZONTALLY
        } else {
            fillGraphicsWithColor(g);
            g.fillRect(x, y, width, height); // DRAW COLOR
            g.setColor(Color.BLACK); // BORDER COLOR
            g.drawRect(x, y, width, height); // DRAW BORDER
            }
        }
    
    /** Fills the graphics with colors
     * 
     * @param g Graphics object.
     */
    private static void fillGraphicsWithColor(Graphics g) {
        Lab2Random r = new Lab2Random();
        int colorPicker = r.nextInt(6);
        switch (colorPicker) {
            case 0: 
            case 1:
            case 2: // NO COLOR
                g.setColor(Color.WHITE);
                break;
            case 3: // RED
                g.setColor(Color.RED);
                break;
            case 4: // BLUE
                g.setColor(Color.BLUE);
                break;
            case 5: // YELLOW
                g.setColor(Color.YELLOW);
                break;
        }
        
    }

    /**
     * Splits a region into four separate regions
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
    private static void fourRegions(int x, int y, int width, int height, Graphics g, int horzSplit, int vertSplit) {
        drawArt(x, y, vertSplit, horzSplit, g);
        drawArt(x + vertSplit, y, width - vertSplit, horzSplit, g);
        drawArt(x, y + horzSplit, vertSplit, height - horzSplit, g);
        drawArt(x + vertSplit, y + horzSplit, width - vertSplit, height - horzSplit, g);
    }

    /**
     * Splits a region into two separate regions vertically
     *
     * @param x Origin x value of the region.
     * @param y Origin y value of the region.
     * @param width Region width.
     * @param height Region height.
     * @param g Graphics object.
     * @param r Lab2Random object.
     * @param vertSplit Vertical split line
     */
    private static void twoRegionsVert(int x, int y, int width, int height, Graphics g, int vertSplit) {
        drawArt(x, y, vertSplit, height, g);
        drawArt(x + vertSplit, y, width - vertSplit, height, g);
    }

    /**
     * Splits a region into two horizontal parts.
     *
     * @param x Origin x value of the region.
     * @param y Origin y value of the region.
     * @param width Region width.
     * @param height Region height.
     * @param g Graphics object.
     * @param r Lab2Random object.
     * @param horzSplit Horizontal split line
     */
    private static void twoRegionsHorz(int x, int y, int width, int height, Graphics g, int horzSplit) {
        drawArt(x, y, width, horzSplit, g);
        drawArt(x, y + horzSplit, width, height - horzSplit, g);
    }
}
