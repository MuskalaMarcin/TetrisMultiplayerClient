package com.muskalanawrot.tetrismultiplayer.client.gui.frame;

import javax.swing.JFrame;

import com.muskalanawrot.tetrismultiplayer.client.gui.panel.MainPanel;

public class MainFrame extends JFrame
{
    private static final long serialVersionUID = -7224073051112865922L;

    private MainPanel mainPanel;

    /**
     * Create the frame.
     */
    public MainFrame()
    {
	mainPanel = new MainPanel();
	init();
    }

    private void init()
    {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(437, 450);
	setLocationRelativeTo(null);
	setTitle("Tetris Multiplayer - Client");
	setContentPane(mainPanel);
	setResizable(false);
	setVisible(true);
    }

    public MainPanel getMainPanel()
    {
	return mainPanel;
    }
    
    public void setSize(int size)
    {
	mainPanel.setSize(size);
	switch(size)
	{
	case 1:
	    super.setSize(437, 450);
	    break;
	case 2:
	    super.setSize(637, 450);
	    break;
	case 3:
	    super.setSize(837, 450);
	    break;
	case 4:
	    super.setSize(1037, 450);
	    break;
	}
	setLocationRelativeTo(null);
	validate();
    }
}
