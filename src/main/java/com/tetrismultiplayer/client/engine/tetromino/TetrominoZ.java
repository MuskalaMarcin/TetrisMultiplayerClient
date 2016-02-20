package main.java.com.tetrismultiplayer.client.engine.tetromino;

import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;

import java.awt.*;

/**
 * Class representing Z shaped tetromino
 */
public class TetrominoZ extends Tetromino
{
    /**
     * Constructor adding bricks to Z shaped tetromino.
     * @param gamePanel reference
     * @param color of tetromino
     * @param x start position
     * @param y start position
     */
    public TetrominoZ(GamePanel gamePanel, Color color, int x, int y)
    {
        super(gamePanel, x, y);
        bricksList.add(new Brick(x - Brick.LENGTH, y - Brick.LENGTH, color));
        bricksList.add(new Brick(x, y - Brick.LENGTH, color));
        bricksList.add(new Brick(x, y, color));
        bricksList.add(new Brick(x + Brick.LENGTH, y, color));
    }
}
