package main.java.com.tetrismultiplayer.client.gui.panel;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.gui.actionlistener.GameKeysListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Main application panel
 */
public class MainPanel extends JPanel
{
    private static final long serialVersionUID = -4369013203321168390L;

    private LeftPanel leftPanel;
    private GamePanel gamePanel;

    /**
     * Create the panel.
     */
    public MainPanel(Main main)
    {
        setLayout(null);
        setFocusable(true);

        addKeyListener(new GameKeysListener(main));
        setBackground(new Color(0, 128, 255));
        leftPanel = new LeftPanel(main);
        leftPanel.setLocation(10, 10);
        leftPanel.setSize(200, 400);
        add(leftPanel);

        gamePanel = new GamePanel();
        gamePanel.setLocation(220, 10);
        gamePanel.setSize(200, 400);
        add(gamePanel);

        addMouseListener(new MouseListener()
        {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getX() < leftPanel.getX() || e.getX() > leftPanel.getX() + leftPanel.getSize().getWidth()
                        || e.getY() < leftPanel.getY() || e.getY() > leftPanel.getY() + leftPanel.getSize().getHeight())
                {
                    requestFocus();
                    leftPanel.getNickTxtField().setBackground(Color.WHITE);
                }
            }

            @Override
            public void mousePressed(MouseEvent e)
            {

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {

            }

        });
    }

    public void setSize(int playersNumber)
    {
        switch (playersNumber)
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

    public LeftPanel getLeftPanel()
    {
        return leftPanel;
    }

    public GamePanel getGamePanel()
    {
        return gamePanel;
    }
}
