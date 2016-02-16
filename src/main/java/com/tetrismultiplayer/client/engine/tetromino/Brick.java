package main.java.com.tetrismultiplayer.client.engine.tetromino;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marcin on 2016-02-16.
 */
public class Brick extends JPanel
{

    public static final Integer LENGTH = 20;

    public Brick(int positionX, int positionY, Color color)
    {
        super();
        setLocation(new Point(positionX, positionY));
        setSize(new Dimension(LENGTH, LENGTH));
        setBorder(BorderFactory.createRaisedBevelBorder());
        setBackground(color);
        setVisible(true);
    }

    public void moveBrick(int x, int y)
    {
        setLocation(getLocation().x + x * LENGTH, getLocation().y + y * LENGTH);
    }

    public void setNewLocation(int x, int y)
    {
        setLocation(x, y);
    }

    public Point getPosition()
    {
        return getLocation();
    }
}
