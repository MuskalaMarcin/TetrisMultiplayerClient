package com.muskalanawrot.tetrismultiplayer.client;

import com.muskalanawrot.tetrismultiplayer.client.gui.frame.MainFrame;
import com.muskalanawrot.tetrismultiplayer.client.gui.panel.MainPanel;

public class Main implements Runnable
{
    private MainFrame mainFrame;
    private MainPanel mainPanel;

    private Main()
    {
	this.mainFrame = new MainFrame();
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
	try
	{
	    Thread.sleep(5000);
	    mainFrame.setSize(2);
	    Thread.sleep(5000);
	    mainFrame.setSize(3);
	    Thread.sleep(5000);
	    mainFrame.setSize(4);
	    Thread.sleep(5000);
	    mainFrame.setSize(1);
	}
	catch (InterruptedException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    public MainPanel getMainPanel()
    {
	return mainPanel;
    }
}
