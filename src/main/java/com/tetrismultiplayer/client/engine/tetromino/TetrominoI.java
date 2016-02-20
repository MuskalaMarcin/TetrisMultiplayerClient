package main.java.com.tetrismultiplayer.client.engine.tetromino;

import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;

import java.awt.*;

/**
 * Class representing I shaped tetromino
 */
public class TetrominoI extends Tetromino
{
    /**
     * Constructor adding bricks to I shaped tetromino.
     * @param gamePanel reference
     * @param color of tetromino
     * @param x start position
     * @param y start position
     */
    public TetrominoI(GamePanel gamePanel, Color color, int x, int y)
    {
        super(gamePanel, x, y);
        bricksList.add(new Brick(x - (Brick.LENGTH * 2), y - Brick.LENGTH, color));
        bricksList.add(new Brick(x - Brick.LENGTH, y - Brick.LENGTH, color));
        bricksList.add(new Brick(x, y - Brick.LENGTH, color));
        bricksList.add(new Brick(x + Brick.LENGTH, y - Brick.LENGTH, color));
    }
}
