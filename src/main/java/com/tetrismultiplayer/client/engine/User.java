package main.java.com.tetrismultiplayer.client.engine;

import main.java.com.tetrismultiplayer.client.engine.tetromino.Tetromino;

import java.util.LinkedList;

/**
 * Created by Marcin on 2016-02-17.
 */
public class User
{
    private LinkedList<Tetromino> tetrominos;
    private Tetromino activeTetromino;

    public User()
    {
        this.tetrominos = new LinkedList<>();
    }

    public LinkedList<Tetromino> getTetrominos()
    {
        return tetrominos;
    }

    public void addTetromino(Tetromino tetromino)
    {
        tetrominos.add(tetromino);
        activeTetromino = tetromino;
        tetromino.draw();
    }

    public Tetromino getActiveTetromino()
    {
        return activeTetromino;
    }
}

