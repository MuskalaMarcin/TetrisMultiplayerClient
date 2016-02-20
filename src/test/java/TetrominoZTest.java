package test.java;

import main.java.com.tetrismultiplayer.client.engine.tetromino.Brick;
import main.java.com.tetrismultiplayer.client.engine.tetromino.TetrominoZ;
import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class TetrominoZTest
{
    TetrominoZ tetromino;

    @Before
    public void setUp() throws Exception
    {
	tetromino = new TetrominoZ(new GamePanel(), Color.BLUE, 100, 100);
    }

    @Test
    public void testRotate() throws Exception
    {
	LinkedList<Brick> all = tetromino.getBricksList();
	assertEquals(80, all.get(0).getPosition().x);
	assertEquals(100, all.get(1).getPosition().x);
	assertEquals(100, all.get(2).getPosition().x);
	assertEquals(120, all.get(3).getPosition().x);

	assertEquals(80, all.get(0).getPosition().y);
	assertEquals(80, all.get(1).getPosition().y);
	assertEquals(100, all.get(2).getPosition().y);
	assertEquals(100, all.get(3).getPosition().y);

	tetromino.rotate();

	assertEquals(100, all.get(0).getPosition().x);
	assertEquals(100, all.get(1).getPosition().x);
	assertEquals(80, all.get(2).getPosition().x);
	assertEquals(80, all.get(3).getPosition().x);

	assertEquals(80, all.get(0).getPosition().y);
	assertEquals(100, all.get(1).getPosition().y);
	assertEquals(100, all.get(2).getPosition().y);
	assertEquals(120, all.get(3).getPosition().y);
    }

    @Test
    public void testMoveRight() throws Exception
    {
	assertEquals(100, tetromino.getPosition().x);
	tetromino.moveRight();
	assertEquals(120, tetromino.getPosition().x);
    }

    @Test
    public void testMoveLeft() throws Exception
    {
	assertEquals(100, tetromino.getPosition().x);
	tetromino.moveLeft();
	assertEquals(80, tetromino.getPosition().x);
    }

    @Test
    public void testMoveDown() throws Exception
    {
	assertEquals(100, tetromino.getPosition().y);
	tetromino.moveDown();
	assertEquals(120, tetromino.getPosition().y);
    }

    @Test
    public void testDrop() throws Exception
    {
	assertEquals(100, tetromino.getPosition().y);
	tetromino.drop(10);
	assertEquals(300, tetromino.getPosition().y);
    }
}