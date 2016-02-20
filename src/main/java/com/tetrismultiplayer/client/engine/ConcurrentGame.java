package main.java.com.tetrismultiplayer.client.engine;

import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;

/**
 * Class representing concurrent game.
 */
public class ConcurrentGame extends Game
{
    /**
     * Constructor for ConcurrentGame class.
     * @param gamePanel reference
     * @param ownerUser user who started game
     * @param playersNumber number of players
     */
    public ConcurrentGame(GamePanel gamePanel, User ownerUser, Integer playersNumber)
    {
        super(gamePanel, GameType.CONCURRENT, ownerUser, playersNumber);
    }
}
