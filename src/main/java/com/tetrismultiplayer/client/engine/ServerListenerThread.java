package main.java.com.tetrismultiplayer.client.engine;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;
import main.java.com.tetrismultiplayer.client.gui.panel.LeftPanel;
import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Marcin on 2016-02-15.
 */
public class ServerListenerThread extends SwingWorker<Object, Object>
{
    private Main main;
    private BufferedReader in;
    private Socket socket;
    private LeftPanel leftPanel;
    private GamePanel gamePanel;

    public ServerListenerThread(Main main)
    {
        this.main = main;
        this.leftPanel = main.getMainPanel().getLeftPanel();
        this.gamePanel = main.getMainPanel().getGamePanel();
        this.socket = main.getSocket();
        try
        {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Object doInBackground()
    {
        JSONObject newMsg;
        try
        {
            while (!(newMsg = new JSONObject(in.readLine())).getString("cmd").equals("end"))
            {
                switch (newMsg.getString("cmd"))
                {
                    case "gameStarted":
                        switch (newMsg.getString("type"))
                        {
                            case "single":
                                leftPanel.setStatusText("Gra pojedyncza");
                        }
                }
                System.out.println(newMsg);


            }
            socket.close();
            leftPanel.setStatusText("Rozłączono");
            leftPanel.setButtonStatus(false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
