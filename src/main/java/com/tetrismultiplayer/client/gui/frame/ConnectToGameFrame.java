package main.java.com.tetrismultiplayer.client.gui.frame;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.engine.Game;
import main.java.com.tetrismultiplayer.client.gui.panel.ConnectToGamePanel;

import javax.swing.*;
import java.util.LinkedList;

/**
 * Frame for connect to game window.
 */
public class ConnectToGameFrame extends JFrame
{
    private static final long serialVersionUID = 1489948780010762329L;

    private ConnectToGamePanel connectToGamePanel;

    /**
     * Create the frame.
     */
    public ConnectToGameFrame(LinkedList<Game> waitingGames, Main main)
    {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setTitle("Dołącz do gry");
        connectToGamePanel = new ConnectToGamePanel(waitingGames, main);
        setContentPane(connectToGamePanel);
    }

}
