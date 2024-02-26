/*
Sam Clarke
2/26/2024
CS145

This program draws Mondrian style art using recursion
*/

import java.awt.*;

public class preLab5
{
    public static void main(String[] args)
    {
        DrawingPanel panel = new DrawingPanel(700, 700);
        Graphics g = panel.getGraphics();

        g.drawRect(50, 200, 300, 150);
        g.setColor(Color.BLUE);
        g.fillRect(100, 400, 100, 300);
    }


    //Draws Mondrian style art through recursion
    //public static void drawArt(int x, int y, int width, int height, Graphics g, Random r)
    //int x: region origin x value
    //int y: region origin y value
    //int width: region's width
    //int height: region's height
    //Graphics g: Graphics object
    //Random r: Random object
    //If a given condition is met (width > half of panel size, height > half of panel size, etc...),
    //Call drawArt method again a specified number of times (2 or 4), creating that many more new regions.
    //If no conditions are met, we will fill in the regions with a random color


    //Splits a region into four random parts
    //public static void fourRegions(int x, int y, int width, int height, Graphics g, Random r, int horzSplit, int vertSplit)
    //Using the random horzSplit and vertSplit, split the region into 4 parts, calling a new drawArt for each one.


    //Splits a region into 2 random parts horizontally
    //public static void twoRegionsHorz(int x, int y, int width, int height, Graphics g, Random r, int horzSplit)
    //Using the random horzSplit, split the region into 2 parts, calling a new drawArt for each one.


    //Splits a region into 2 random parts vertically
    //public static void twoRegionsVert(int x, int y, int width, int height, Graphics g, Random r, int vertSplit)
    //Using the random vertSplit, split the region into 2 parts, calling a new drawArt for each one.
}