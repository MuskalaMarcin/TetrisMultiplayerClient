package main.java.com.tetrismultiplayer.client.engine.tetromino;

import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;

import java.awt.*;

/**
 * Created by Marcin on 2016-02-16.
 */
public class TetrominoZ extends Tetromino
{
    public TetrominoZ(GamePanel gamePanel, Color color, int x, int y)
    {
        super(gamePanel, x, y);
        bricksList.add(new Brick(x - (Brick.LENGTH / 2 + Brick.LENGTH), y - Brick.LENGTH, color));
        bricksList.add(new Brick(x - (Brick.LENGTH / 2), y - Brick.LENGTH, color));
        bricksList.add(new Brick(x - (Brick.LENGTH / 2), y, color));
        bricksList.add(new Brick(x + (Brick.LENGTH / 2), y, color));
    }
}
