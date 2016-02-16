package main.java.com.tetrismultiplayer.client.engine.teromino;

import java.awt.*;

/**
 * Created by Marcin on 2016-02-16.
 */
public class Brick
{
    private Dimension position;
    private Dimension size;
    private Color color;

    public Brick(int x, int y, Color color)
    {
        this.position = new Dimension(x, y);
        this.color = color;
        this.size = new Dimension(10, 10);
    }

    public void changePosition(int x, int y)
    {
        position.setSize(x, y);
    }

    public Dimension getPosition()
    {
        return position;
    }

    public Dimension getSize()
    {
        return size;
    }
}
