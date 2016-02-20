package main.java.com.tetrismultiplayer.client.engine.tetromino;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

/**
 * Class brick extends JPanel and represents one of bricks making tetromino.
 */
public class Brick extends JPanel
{

    public static final Integer LENGTH = 20;

    public Brick(int positionX, int positionY, Color color)
    {
        super();
        setLocation(new Point(positionX, positionY));
        setSize(new Dimension(LENGTH, LENGTH));
        setBorder(new CompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createRaisedBevelBorder()));
        setBackground(color);
        setVisible(true);
    }

    /**
     * Moves brick location by selected values
     * @param x number of columns
     * @param y number of rows
     */
    public void moveBrick(int x, int y)
    {
        setLocation(getLocation().x + x * LENGTH, getLocation().y + y * LENGTH);
    }

    /**
     * Sets new brick location
     * @param x column
     * @param y row
     */
    public void setNewLocation(int x, int y)
    {
        setLocation(x, y);
    }

    /**
     * Returns point with brick position.
     * @return Point with coordinates
     */
    public Point getPosition()
    {
        return getLocation();
    }
}
