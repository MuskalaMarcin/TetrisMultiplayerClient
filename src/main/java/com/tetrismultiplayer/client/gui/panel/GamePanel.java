package main.java.com.tetrismultiplayer.client.gui.panel;

import javax.swing.*;
import java.awt.*;

/**
 * Game panel class for displaying game.
 */
public class GamePanel extends JPanel
{
    public GamePanel()
    {
        setBackground(Color.WHITE);
        setFocusable(false);
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
