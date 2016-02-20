package main.java.com.tetrismultiplayer.client.engine;

import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;

/**
 * Created by Marcin on 2016-02-17.
 */
public class ConcurrentGame extends Game
{
    public ConcurrentGame(GamePanel gamePanel, User ownerUser, Integer playersNumber)
    {
        super(gamePanel, GameType.CONCURRENT, ownerUser, playersNumber);
    }
}
