package main.java.com.tetrismultiplayer.client.engine;

import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;

/**
 * Class representing single game.
 */
public class SingleGame extends Game
{
    /**
     * Constructor for SingleGame class.
     * @param gamePanel reference
     * @param ownerUser user who started game
     */
    public SingleGame(GamePanel gamePanel, User ownerUser)
    {
        super(gamePanel, GameType.SINGLE, ownerUser, 1);
    }
}
