package main.java.com.tetrismultiplayer.client.engine.tetromino;

import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;

import java.awt.*;

/**
 * Class representing T shaped tetromino
 */
public class TetrominoT extends Tetromino
{
    /**
     * Constructor adding bricks to T shaped tetromino.
     * @param gamePanel reference
     * @param color of tetromino
     * @param x start position
     * @param y start position
     */
    public TetrominoT(GamePanel gamePanel, Color color, int x, int y)
    {
        super(gamePanel, x, y);
        bricksList.add(new Brick(x, y - Brick.LENGTH, color));
        bricksList.add(new Brick(x - Brick.LENGTH, y, color));
        bricksList.add(new Brick(x, y, color));
        bricksList.add(new Brick(x + Brick.LENGTH, y, color));
    }
}
