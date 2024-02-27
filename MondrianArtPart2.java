/**
 * MondrianArtPart2.java
 * Generates Mondrian-style art using recursion with modifications.
 *
 * @Author: Sam Clarke, JeongGyu Tak, Nick Ivancovich
 * @Date: 240226
 * @Class: CS&145
 * @Assignment: LAB#5
 * @Purpose: Using recursion to create Mondrian-style art with some modifications.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class MondrianArtPart2 {
    // CLASS CONSTANTS
    private static final int PANEL_SIZE = 700;
    private static final int MINIMUM_THRESHOLD = 50;
    private static final double RAND_FACTOR = 1.3;
    private static final double LOWER_BOUND = 0.33;
    private static final double UPPER_BOUND = 0.67;

    public static void main(String[] args) {
        // INITIALIZE OBJECTS
        DrawingPanel panel = new DrawingPanel(PANEL_SIZE, PANEL_SIZE);
        Graphics g = panel.getGraphics();
        
        // CALL RECURSION METHOD
        drawArt(g, 0, 0, PANEL_SIZE, PANEL_SIZE);
    }

    /**
     * Creates Mondrian-style art through recursion.
     *
     * @param g Graphics object.
     * @param x Origin x value of the region.
     * @param y Origin y value of the region.
     * @param width Region width.
     * @param height Region height.
     */
    public static void drawArt(Graphics g, int x, int y, int width, int height) {
        Lab2Random r = new Lab2Random(); 

        // INITIALIZE RANDOM SPLIT VALUES
        int horzSplit = r.nextInt((int)(height * LOWER_BOUND), (int)(height * UPPER_BOUND));
        int vertSplit = r.nextInt((int)(width * LOWER_BOUND), (int)(width * UPPER_BOUND));

        // START RECURSION
        if (width > PANEL_SIZE / 2 && height > PANEL_SIZE / 2) {
            // FOUR REGIONS
            fourRegions(g, x, y, width, height, horzSplit, vertSplit);
        } else if (width > PANEL_SIZE / 2) {
            // TWO REGIONS VERTICALLY
            twoRegionsVert(g, x, y, width, height, vertSplit);
        } else if (height > PANEL_SIZE / 2) {
            // TWO REGIONS HORIZONTALLY
            twoRegionsHorz(g, x, y, width, height, horzSplit);
        } else if (r.nextInt(MINIMUM_THRESHOLD, Math.max((int)(width * RAND_FACTOR), MINIMUM_THRESHOLD + 1)) < width &&
                r.nextInt(MINIMUM_THRESHOLD, Math.max((int)(height * RAND_FACTOR), MINIMUM_THRESHOLD + 1)) < height) {
            // FOUR REGIONS
            fourRegions(g, x, y, width, height, horzSplit, vertSplit);
        } else if (r.nextInt(MINIMUM_THRESHOLD, Math.max((int)(width * RAND_FACTOR), MINIMUM_THRESHOLD + 1)) < width) {
            // TWO REGIONS VERTICALLY
            twoRegionsVert(g, x, y, width, height, vertSplit);
        } else if (r.nextInt(MINIMUM_THRESHOLD, Math.max((int)(height * RAND_FACTOR), MINIMUM_THRESHOLD + 1)) < height) {
            // TWO REGIONS HORIZONTALLY
            twoRegionsHorz(g, x, y, width, height, horzSplit);
        } else {
            fillGraphicsWithColor(g);
            gradientGraphicsFromCenter(g, x, y, width, height);
            fillGraphicsWithShape(g, x, y, width, height);
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
            case 0: g.setColor(Color.RED); break;
            case 1: g.setColor(Color.MAGENTA); break;
            case 2: g.setColor(Color.ORANGE); break;
            case 3: g.setColor(Color.GREEN); break;
            case 4: g.setColor(Color.BLUE); break;
            default: g.setColor(Color.GRAY); break; // 5
        }
    }

    /** Fills graphics with shape
     * 
     * @param g Graphics object.
     * @param x Origin x value of the region.
     * @param y Origin y value of the region.
     * @param width Region width.
     * @param height Region height.
     */
    private static void fillGraphicsWithShape(Graphics g, int x, int y, int width, int height) {
        Lab2Random r = new Lab2Random();
        int shapePicker = r.nextInt(6);
        switch (shapePicker) {
            case 0: drawCross(g, x, y, width, height); break;
            case 1: drawOval(g, x, y, width, height); break;
            case 2: drawPacMan(g, x, y, width, height); break;
            case 3: drawMickeyMouse(g, x, y, width, height); break;
            default: break; // 4, 5 empty rects
        }
    }

    /** Provide a gradient to graphics depends on it's distance from center.
     * further from the center the darker it gets.
     * 
     * @param g Graphics object.
     * @param x Origin x value of the region.
     * @param y Origin y value of the region.
     * @param width Region width.
     * @param height Region height.
     */
    private static void gradientGraphicsFromCenter(Graphics g, int x, int y, int width, int height) {
        // Calculate the center of the rectangle
        int centerX = x + width / 2;
        int centerY = y + height / 2;

        // Calculate the center of the panel
        int panelCenterX = PANEL_SIZE / 2;
        int panelCenterY = PANEL_SIZE / 2;

        // Calculate the distance from the center of the rectangle to the center of the panel
        double distanceToCenter = Math.sqrt(Math.pow(centerX - panelCenterX, 2) + Math.pow(centerY - panelCenterY, 2));

        // Normalize the distance to a value between 0 and 1
        double normalizedDistance = distanceToCenter / (Math.sqrt(2) * (PANEL_SIZE / 2));

        // Determine the gradient level based on the normalized distance
        int gradientLevel = (int)(normalizedDistance * 8); // Assume 8 levels of gradient for simplicity

        // Adjust color based on the normalized distance to create a rounded gradient effect
        switch (gradientLevel) {
            case 0: g.setColor(g.getColor().brighter().brighter().brighter()); break;
            case 1: g.setColor(g.getColor().brighter().brighter()); break;
            case 2: g.setColor(g.getColor().brighter()); break;
            case 3: g.setColor(g.getColor()); break;
            case 4: g.setColor(g.getColor().darker()); break;
            case 5: g.setColor(g.getColor().darker().darker()); break;
            case 6: g.setColor(g.getColor().darker().darker().darker()); break;
            case 7: g.setColor(g.getColor().darker().darker().darker().darker()); break;
            default: g.setColor(g.getColor().darker().darker().darker().darker().darker()); break;
        }

        g.fillRect(x, y, width, height); // DRAW COLOR
        g.setColor(Color.BLACK); // BORDER COLOR
        g.drawRect(x, y, width, height); // DRAW BORDER
    }

    /** Draw a cross in rect
     * 
     * @param g Graphics object.
     * @param x Origin x value of the region.
     * @param y Origin y value of the region.
     * @param width Region width.
     * @param height Region height.
     */
    private static void drawCross(Graphics g, int x, int y, int width, int height) {
        g.drawLine(x, y, x + width, y + height);
        g.drawLine(x, y + height, x + width, y);
    }

    /** Draw a oval fills in rect
     * 
     * @param g Graphics object.
     * @param x Origin x value of the region.
     * @param y Origin y value of the region.
     * @param width Region width.
     * @param height Region height.
     */
    private static void drawOval(Graphics g, int x, int y, int width, int height) {
        g.drawOval(x, y, width, height);
    }

    /** Draw a pacman fills in rect
     * 
     * @param g Graphics object.
     * @param x Origin x value of the region.
     * @param y Origin y value of the region.
     * @param width Region width.
     * @param height Region height.
     */
    private static void drawPacMan(Graphics g, int x, int y, int width, int height) {
        int[] xPoints = {x, x + width / 2, x + width, x + width / 2, x + width, x + width / 2};
        int[] yPoints = {y + height / 2, y, y + height / 4, y + height / 2, y + 3 * height / 4, y + height};
        g.drawPolygon(xPoints, yPoints, 6);

    }

    /** Draw a Mickey Mouse fills in rect
     * 
     * @param g Graphics object.
     * @param x Origin x value of the region.
     * @param y Origin y value of the region.
     * @param width Region width.
     * @param height Region height.
     */
    private static void drawMickeyMouse(Graphics g, int x, int y, int width, int height) {
        // Main face dimensions
        int faceDiameter = Math.min(width, height) * 3 / 4; // Face diameter as 75% of the smallest dimension
        int faceX = x + (width - faceDiameter) / 2; // Center the face horizontally within the square
        int faceY = y + (height - faceDiameter) / 2; // Center the face vertically within the square

        // Draw main face circle
        g.drawOval(faceX, faceY, faceDiameter, faceDiameter);

        // Ears dimensions
        int earDiameter = faceDiameter / 2; // Each ear's diameter is half of the face's diameter

        // Randomly select an angle for ear placement: 0, 90, 180, 270 degrees
        int angle = new Random().nextInt(4) * 90; // Randomly select 0, 90, 180, 270

        // Initialize ear positions
        int ear1X = 0, ear1Y = 0, ear2X = 0, ear2Y = 0;

        // Calculate positions based on the selected angle
        switch (angle) {
            case 0: // Ears on top
                ear1X = faceX - earDiameter / 2;
                ear1Y = faceY - earDiameter / 2;
                ear2X = faceX + faceDiameter - earDiameter / 2;
                ear2Y = faceY - earDiameter / 2;
                break;
            case 90: // Ears on right
                ear1X = faceX + faceDiameter - earDiameter / 2;
                ear1Y = faceY - earDiameter / 2;
                ear2X = faceX + faceDiameter - earDiameter / 2;
                ear2Y = faceY + faceDiameter - earDiameter / 2;
                break;
            case 180: // Ears on bottom
                ear1X = faceX - earDiameter / 2;
                ear1Y = faceY + faceDiameter - earDiameter / 2;
                ear2X = faceX + faceDiameter - earDiameter / 2;
                ear2Y = faceY + faceDiameter - earDiameter / 2;
                break;
            case 270: // Ears on left
                ear1X = faceX - earDiameter / 2;
                ear1Y = faceY - earDiameter / 2;
                ear2X = faceX - earDiameter / 2;
                ear2Y = faceY + faceDiameter - earDiameter / 2;
                break;
        }
    // Draw ears
    g.drawOval(ear1X, ear1Y, earDiameter, earDiameter); // First ear
    g.drawOval(ear2X, ear2Y, earDiameter, earDiameter); // Second ear
    }

    /**
     * Splits a region into four separate regions
     *
     * @param g Graphics object.
     * @param x Origin x value of the region.
     * @param y Origin y value of the region.
     * @param width Region width.
     * @param height Region height.
     * @param horzSplit Horizontal split line
     * @param vertSplit Vertical split line
     */
    private static void fourRegions(Graphics g, int x, int y, int width, int height, int horzSplit, int vertSplit) {
        drawArt(g, x, y, vertSplit, horzSplit);
        drawArt(g, x + vertSplit, y, width - vertSplit, horzSplit);
        drawArt(g, x, y + horzSplit, vertSplit, height - horzSplit);
        drawArt(g, x + vertSplit, y + horzSplit, width - vertSplit, height - horzSplit);
    }

    /**
     * Splits a region into two separate regions vertically
     *
     * @param g Graphics object.
     * @param x Origin x value of the region.
     * @param y Origin y value of the region.
     * @param width Region width.
     * @param height Region height.
     * @param vertSplit Vertical split line
     */
    private static void twoRegionsVert(Graphics g, int x, int y, int width, int height, int vertSplit) {
        drawArt(g, x, y, vertSplit, height);
        drawArt(g, x + vertSplit, y, width - vertSplit, height);
    }

    /**
     * Splits a region into two separate regions horizontally
     *
     * @param g Graphics object.
     * @param x Origin x value of the region.
     * @param y Origin y value of the region.
     * @param width Region width.
     * @param height Region height.
     * @param horzSplit Horizontal split line
     */
    private static void twoRegionsHorz(Graphics g, int x, int y, int width, int height, int horzSplit) {
        drawArt(g, x, y, width, horzSplit);
        drawArt(g, x, y + horzSplit, width, height - horzSplit);
    }
}
