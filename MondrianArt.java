import java.awt.*;

public class MondrianArt
{
    //Class constants
    private static final int PANEL_SIZE = 700;
    private static final int MINIMUM_THRESHOLD = 100;
    public static void main(String[] args)
    {
        DrawingPanel panel = new DrawingPanel(PANEL_SIZE, PANEL_SIZE);
        Graphics g = panel.getGraphics();

        // g.drawRect(0,0,200,200);
        // g.setColor(Color.BLUE);
        // g.fillRect(300, 300, 200, 200);

        drawArt(0, 0, PANEL_SIZE, PANEL_SIZE, g);
    }

    public static void drawArt(int x, int y, int width, int height, Graphics g)
    {
        if (width < MINIMUM_THRESHOLD || height < MINIMUM_THRESHOLD)
        {
            g.drawRect(x, y, width, height);
        }
        else
        {
            drawArt(x, y, width/2, height/2, g);
            drawArt(x + width/2, y, width/2, height/2, g);
            drawArt(x, y + height/2, width/2, height/2, g);
            drawArt(x + width/2, y + height/2, width/2, height/2, g);
        }
    }
}