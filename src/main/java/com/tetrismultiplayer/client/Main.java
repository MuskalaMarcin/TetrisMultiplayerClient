package main.java.com.tetrismultiplayer.client;

import main.java.com.tetrismultiplayer.client.engine.ServerListenerThread;
import main.java.com.tetrismultiplayer.client.gui.frame.MainFrame;
import main.java.com.tetrismultiplayer.client.gui.panel.MainPanel;
import main.java.com.tetrismultiplayer.client.keys.KeysGetter;
import main.java.com.tetrismultiplayer.client.keys.Settings;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class Main implements Runnable
{
    private MainFrame mainFrame;
    private MainPanel mainPanel;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private ServerListenerThread serverListenerThread;
    public static Settings settings;
    private static ObjectInputStream instream;

    private Main()
    {
        this.mainFrame = new MainFrame(this);
        this.mainPanel = mainFrame.getMainPanel();
    }

    public static void main(String args[])
    {
	try
	{
	    KeysGetter.loadKeys();
	    File f = new File("D:\\settings.config");
	    if(f.exists())
	    {
		instream = new ObjectInputStream(new FileInputStream("D:\\settings.config"));
		settings = (Settings) instream.readObject();
		instream.close();
	    }
	    else
	    {
		settings = new Settings();
	    }
	}
	catch (Exception e)
	{
	    settings = new Settings();
	}
	finally
	{
	    new Main().run();
	}
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
