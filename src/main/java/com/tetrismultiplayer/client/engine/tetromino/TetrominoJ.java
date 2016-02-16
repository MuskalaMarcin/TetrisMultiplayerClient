package main.java.com.tetrismultiplayer.client.engine.tetromino;

import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;

import java.awt.*;

/**
 * Created by Marcin on 2016-02-16.
 */
public class TetrominoJ extends Tetromino
{
    public TetrominoJ(GamePanel gamePanel, Color color, int x, int y)
    {
        super(gamePanel, x, y);
        bricksList.add(new Brick(x, y - (Brick.LENGTH + Brick.LENGTH / 2), color));
        bricksList.add(new Brick(x, y - (Brick.LENGTH / 2), color));
        bricksList.add(new Brick(x, y + (Brick.LENGTH / 2), color));
        bricksList.add(new Brick(x - Brick.LENGTH, y + (Brick.LENGTH / 2), color));
    }

    public void rotate()
    {

    }
}
