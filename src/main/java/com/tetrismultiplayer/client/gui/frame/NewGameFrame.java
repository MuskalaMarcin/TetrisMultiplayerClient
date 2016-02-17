package main.java.com.tetrismultiplayer.client.gui.frame;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.gui.panel.NewGamePanel;

import javax.swing.*;

public class NewGameFrame extends JFrame
{
    private static final long serialVersionUID = -6770175343284490332L;

    private NewGamePanel newGamePanel;

    /**
     * Create the frame.
     */
    public NewGameFrame(Main main)
    {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setTitle("Rozpocznij nową grę");
        newGamePanel = new NewGamePanel(main);
        setContentPane(newGamePanel);
    }

}
