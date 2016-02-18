package main.java.com.tetrismultiplayer.client;

import main.java.com.tetrismultiplayer.client.engine.ServerListenerThread;
import main.java.com.tetrismultiplayer.client.gui.frame.MainFrame;
import main.java.com.tetrismultiplayer.client.gui.panel.MainPanel;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static main.java.com.tetrismultiplayer.client.keys.KeysGetter.loadSettings;

public class Main implements Runnable
{
    private MainFrame mainFrame;
    private MainPanel mainPanel;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private ServerListenerThread serverListenerThread;

    private Main()
    {
        this.mainFrame = new MainFrame(this);
        this.mainPanel = mainFrame.getMainPanel();
    }

    public static void main(String args[])
    {
	loadSettings();
	new Main().run();
    }

    @Override
    public void run()
    {
        mainFrame.setVisible(true);
    }

    public MainPanel getMainPanel()
    {
        return mainPanel;
    }

    public Socket getSocket()
    {
        return socket;
    }

    /**
     * Sets connection socket and initializes buffered reader and print writer.
     *
     * @param socket reference
     */
    public void setSocket(Socket socket)
    {
        try
        {
            this.socket = socket;
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Sends message to server
     *
     * @param msg to send
     */
    public void sendMessage(JSONObject msg)
    {
        sendMessage(msg.toString());
    }

    /**
     * Sends message to server
     *
     * @param msg to send
     */
    public void sendMessage(String msg)
    {
        synchronized (out)
        {
            System.out.println("wyslano: " + msg);
            out.println(msg);
        }
    }

    /**
     * Receives message from server.
     *
     * @return String with message
     */
    public String receiveMessage()
    {
        synchronized (in)
        {
            try
            {
                return in.readLine();
            }
            catch (IOException e)
            {
                return "Error";
            }
        }
    }

    public JSONObject receiveJSON()
    {
        JSONObject newObject = new JSONObject(receiveMessage());
        System.out.println("odebrano: "+newObject);
        return newObject;
    }

    public ServerListenerThread getServerListenerThread()
    {
        return serverListenerThread;
    }

    public void setServerListenerThread(ServerListenerThread serverListenerThread)
    {
        this.serverListenerThread = serverListenerThread;
    }
}
