package com.muskalanawrot.tetrismultiplayer.client;

import java.net.Socket;

import com.muskalanawrot.tetrismultiplayer.client.gui.frame.MainFrame;
import com.muskalanawrot.tetrismultiplayer.client.gui.panel.MainPanel;

public class Main implements Runnable
{
    private MainFrame mainFrame;
    private MainPanel mainPanel;
    private Socket socket;

    private Main()
    {
	this.mainFrame = new MainFrame(this);
	this.mainPanel = mainFrame.getMainPanel();
    }

    public static void main(String args[])
    {
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

    public void setSocket(Socket socket)
    {
        this.socket = socket;
    }
}
