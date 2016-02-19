package main.java.com.tetrismultiplayer.client.gui.panel;

import main.java.com.tetrismultiplayer.client.engine.Game;

import javax.swing.JPanel;
import java.util.LinkedList;

public class ConnectToGamePanel extends JPanel
{
    private static final long serialVersionUID = -216317464444204835L;

    private LinkedList<Game> waitingGames;

    /**
     * Create the panel.
     */
    public ConnectToGamePanel(LinkedList<Game> waitingGames)
    {
        waitingGames.forEach(game -> {
        });
    }

}
