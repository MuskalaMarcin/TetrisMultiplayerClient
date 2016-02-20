package main.java.com.tetrismultiplayer.client.engine;

import main.java.com.tetrismultiplayer.client.engine.tetromino.Tetromino;

import java.util.LinkedList;

/**
 * Class representing one of application users
 */
public class User
{
    private LinkedList<Tetromino> tetrominos;
    private Tetromino activeTetromino;
    private String nick;
    private String identifier;
    private String ip;
    private Integer ranking;
    private Integer score;

    public User(String nick, String identifier, String ip, Integer ranking)
    {
        this.identifier = identifier;
        this.nick = nick;
        this.ip = ip;
        this.ranking = ranking;
        this.tetrominos = new LinkedList<>();
        this.score = 0;
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

    public void removeTetromino(Tetromino tetromino)
    {
        tetrominos.remove(tetromino);
    }

    public Tetromino getActiveTetromino()
    {
        return activeTetromino;
    }

    public String getNick()
    {
        return nick;
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public Integer getRanking()
    {
        return ranking;
    }

    public void setRanking(Integer ranking)
    {
        this.ranking = ranking;
    }

    public Integer getScore()
    {
        return score;
    }

    public void resetScore()
    {
        this.score = 0;
    }

    public void addScore(Integer score)
    {
        this.score += score;
    }
}

