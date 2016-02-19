package main.java.com.tetrismultiplayer.client.gui.panel;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.engine.Game;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ConnectToGamePanel extends JPanel
{
    private static final long serialVersionUID = -216317464444204835L;

    private LinkedList<Game> waitingGames;

    /**
     * Create the panel.
     */
    public ConnectToGamePanel(LinkedList<Game> waitingGames, Main main)
    {
        setLayout(new FlowLayout());
        this.waitingGames = waitingGames;

        waitingGames.forEach(game -> {
            JButton newButton = new JButton(game.getIdentifier());
            newButton.setSize(50, 50);
            newButton.addActionListener(action -> {
                main.sendMessage(new JSONObject().put("cmd", "connectToGame").put("identifier", game.getIdentifier()));
                main.getMainPanel().requestFocus();
                SwingUtilities.getWindowAncestor(this).dispose();
            });
            add(newButton);
        });
    }

}
