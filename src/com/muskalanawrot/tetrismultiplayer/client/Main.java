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
    }

    public MainPanel getMainPanel()
    {
	return mainPanel;
    }
}
