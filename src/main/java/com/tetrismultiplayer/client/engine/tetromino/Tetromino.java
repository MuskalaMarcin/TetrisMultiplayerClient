package main.java.com.tetrismultiplayer.client.engine.tetromino;

import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;

import java.awt.*;
import java.util.LinkedList;

/**
 * Common abstract class for all tetrominos.
 */
public abstract class Tetromino
{
    protected LinkedList<Brick> bricksList;
    private GamePanel gamePanel;
    protected Point position;

    protected Tetromino(GamePanel gamePanel, int row, int column)
    {
        this.bricksList = new LinkedList<>();
        this.position = new Point(row, column);
        this.gamePanel = gamePanel;
    }

    /**
     * Rotates tetromino clockwise.
     */
    public void rotate()
    {
        bricksList.forEach(brick -> {
            int newX = position.x + position.y - brick.getPosition().y - Brick.LENGTH;
            int newY = brick.getPosition().x + position.y - position.x;
            brick.setNewLocation(newX, newY);
        });
    }

    /**
     * Moves tetromino right.
     */
    public void moveRight()
    {
        bricksList.forEach(brick -> brick.moveBrick(1, 0));
        position.move(position.x + Brick.LENGTH, position.y);
    }

    /**
     * Moves tetromino left.
     */
    public void moveLeft()
    {
        bricksList.forEach(brick -> brick.moveBrick(-1, 0));
        position.move(position.x - Brick.LENGTH, position.y);
    }

    /**
     * Moves tetromino down.
     */
    public void moveDown()
    {
        bricksList.forEach(brick -> brick.moveBrick(0, 1));
        position.move(position.x, position.y + Brick.LENGTH);
    }

    /**
     * Drops tetromino selected number of rows down.
     *
     * @param rowsNumber number of rows to drop down
     */
    public void drop(int rowsNumber)
    {
        bricksList.forEach(brick -> brick.moveBrick(0, rowsNumber));
        position.move(position.x, position.y + (rowsNumber * Brick.LENGTH));
    }

    /**
     * Draws tetromino on game panel.
     */
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
