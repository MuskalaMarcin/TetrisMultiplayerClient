package main.java.com.tetrismultiplayer.client.gui.frame;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.gui.panel.MainPanel;

import javax.swing.*;

/**
 * Main application frame
 */
public class MainFrame extends JFrame
{
    private static final long serialVersionUID = -7224073051112865922L;

    private MainPanel mainPanel;

    /**
     * Create the frame.
     */
    public MainFrame(Main main)
    {
        mainPanel = new MainPanel(main);
        init();
    }

    /**
     * Initializes elements
     */
    private void init()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(437, 450);
        setLocationRelativeTo(null);
        setTitle("Tetris Multiplayer - Client");
        setContentPane(mainPanel);
        setResizable(false);
        setVisible(true);
        setFocusable(false);
    }

    public MainPanel getMainPanel()
    {
        return mainPanel;
    }

    /**
     * Sets MainFrame and gamePanel to selected size based on players number
     * @param playersNumber number of players
     */
    public void setSize(int playersNumber)
    {
        mainPanel.setSize(playersNumber);
        switch (playersNumber)
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
