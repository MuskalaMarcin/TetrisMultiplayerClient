package main.java.com.tetrismultiplayer.client.engine;

import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;

/**
 * Created by Marcin on 2016-02-17.
 */
public class SingleGame extends Game
{
    public SingleGame(GamePanel gamePanel, User user)
    {
        super(gamePanel, GameType.SINGLE, user, 1);
    }
}
