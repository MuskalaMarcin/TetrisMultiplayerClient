package main.java.com.tetrismultiplayer.client.implementation;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.engine.ServerListenerThread;
import main.java.com.tetrismultiplayer.client.engine.User;
import main.java.com.tetrismultiplayer.client.gui.panel.LeftPanel;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Marcin on 2016-02-16.
 */
public class ConnectToServer extends SwingWorker<Boolean, Object>
{
    private Main main;
    private LeftPanel leftPanel;

    /**
     * Constructor for connect/disconnect action listener.
     */
    public ConnectToServer(Main main, LeftPanel leftPanel)
    {
        this.main = main;
        this.leftPanel = leftPanel;
    }

    public Boolean doInBackground()
    {
        if (leftPanel.getNick().isEmpty())
        {
            leftPanel.getNickTxtField().setBackground(Color.RED);
            return false;
        }
        else
        {
            try
            {
                Socket socket = new Socket(Main.settings.ip, Main.settings.port);
                if (socket.isConnected() && !socket.isClosed())
                {
                    main.setSocket(socket);

                    main.sendMessage(new JSONObject().put("cmd", "connect").put("nick",
                            leftPanel.getNick()).put("identifier", socket.getLocalSocketAddress().toString()
                            .substring(1).replace(".", "").replace(":", "")));

                    String connectionStatus = main.receiveJSON().getString("state");
                    if (connectionStatus.equals("connected"))
                    {
                        main.setServerListenerThread(new ServerListenerThread(main, new User(leftPanel.getNick(),
                                socket.getLocalSocketAddress().toString().substring(1).replace(".", "").replace(":", ""))));
                        main.getServerListenerThread().execute();

                        return true;
                    }
                    else throw new IOException();
                }
                else throw new IOException();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
                return false;
            }
        }
    }
}
