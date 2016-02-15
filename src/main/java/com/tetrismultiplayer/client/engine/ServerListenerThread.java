package main.java.com.tetrismultiplayer.client.engine;

import main.java.com.tetrismultiplayer.client.Main;
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

    public ServerListenerThread(Main main)
    {
        this.main = main;
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
                String command = newMsg.getString("cmd");
                System.out.println(command);

            }
            socket.close();
            main.getMainPanel().getLeftPanel().setStatusText("Rozłączono");
            main.getMainPanel().getLeftPanel().setButtonStatus(false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
