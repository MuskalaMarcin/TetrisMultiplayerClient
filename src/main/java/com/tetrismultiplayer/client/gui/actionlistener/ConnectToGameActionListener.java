package main.java.com.tetrismultiplayer.client.gui.actionlistener;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.engine.Game;
import main.java.com.tetrismultiplayer.client.gui.frame.ConnectToGameFrame;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ConnectToGameActionListener implements ActionListener
{
    private Main main;

    public ConnectToGameActionListener(Main main)
    {
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    main.sendMessage(new JSONObject().put("cmd", "getWaitingGames"));
                    LinkedList<Game> waitingGames = null;
                    while (waitingGames == null)
                    {
                        waitingGames = main.getServerListenerThread().getWaitingGames();
                    }
                    ConnectToGameFrame frame = new ConnectToGameFrame(waitingGames, main);
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

}
