package main.java.com.tetrismultiplayer.client.engine.tetromino;

import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Marcin on 2016-02-16.
 */
public abstract class Tetromino
{
    protected LinkedList<Brick> bricksList;
    private GamePanel gamePanel;
    protected Point position;

    protected Tetromino(GamePanel gamePanel, int positionX, int positionY)
    {
        this.bricksList = new LinkedList<>();
        this.gamePanel = gamePanel;
        this.position = new Point(positionX, positionY);
    }

    public abstract void rotate();

    public void moveRight()
    {
        bricksList.forEach(brick -> brick.moveBrick(Brick.LENGTH, 0));
    }

    public void moveLeft()
    {
        bricksList.forEach(brick -> brick.moveBrick(-Brick.LENGTH, 0));
    }

    public void moveDown()
    {
        bricksList.forEach(brick -> brick.moveBrick(0, Brick.LENGTH));
    }

    public void drop(int y)
    {
        bricksList.forEach(brick -> brick.moveBrick(0, y));
    }

    public void draw()
    {
        bricksList.forEach(brick -> {
            gamePanel.remove(brick);
            gamePanel.add(brick);
        });
        gamePanel.validate();
        gamePanel.repaint();
    }
}
