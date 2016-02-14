package com.muskalanawrot.tetrismultiplayer.client.gui.frame;

import javax.swing.JFrame;

import com.muskalanawrot.tetrismultiplayer.client.gui.panel.ConnectToGamePanel;

public class ConnectToGameFrame extends JFrame
{
    private static final long serialVersionUID = 1489948780010762329L;

    private ConnectToGamePanel connectToGamePanel;

    /**
     * Create the frame.
     */
    public ConnectToGameFrame()
    {
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	setTitle("Do³¹cz do gry");
	connectToGamePanel = new ConnectToGamePanel();
	setContentPane(connectToGamePanel);
    }

}
