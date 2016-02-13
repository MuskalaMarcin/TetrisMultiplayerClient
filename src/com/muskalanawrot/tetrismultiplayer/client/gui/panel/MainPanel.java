package com.muskalanawrot.tetrismultiplayer.client.gui.panel;

import java.awt.Color;

import javax.swing.JPanel;

public class MainPanel extends JPanel
{
    private static final long serialVersionUID = -4369013203321168390L;

    private LeftPanel leftPanel;
    private JPanel gamePanel;

    /**
     * Create the panel.
     */
    public MainPanel()
    {
	setLayout(null);
	setBackground(new Color(0, 128, 255));
	leftPanel = new LeftPanel();
	leftPanel.setLocation(10, 10);
	leftPanel.setSize(200, 400);
	add(leftPanel);

	gamePanel = new JPanel();
	gamePanel.setLocation(220, 10);
	gamePanel.setSize(200, 400);
	gamePanel.setBackground(Color.BLUE);
	add(gamePanel);
    }

    public void setSize(int size)
    {
	switch (size)
	{
	case 1:
	    gamePanel.setSize(200, 400);
	    break;
	case 2:
	    gamePanel.setSize(400, 400);
	    break;
	case 3:
	    gamePanel.setSize(600, 400);
	    break;
	case 4:
	    gamePanel.setSize(800, 400);
	    break;
	}
    }
}
