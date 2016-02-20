package main.java.com.tetrismultiplayer.client.engine;

import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;

/**
 * Class representing cooperation game.
 */
public class CooperationGame extends Game
{
    /**
     * Constructor for CooperationGame class.
     * @param gamePanel reference
     * @param ownerUser user who started game
     * @param playersNumber number of players
     */
    public CooperationGame(GamePanel gamePanel, User ownerUser, Integer playersNumber)
    {
        super(gamePanel, GameType.COOPERATION, ownerUser, playersNumber);
    }
}
