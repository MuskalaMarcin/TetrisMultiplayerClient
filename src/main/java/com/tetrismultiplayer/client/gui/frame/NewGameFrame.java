package main.java.com.tetrismultiplayer.client.gui.frame;

import javax.swing.JFrame;

import main.java.com.tetrismultiplayer.client.gui.panel.NewGamePanel;

public class NewGameFrame extends JFrame
{
    private static final long serialVersionUID = -6770175343284490332L;

    private NewGamePanel newGamePanel;

    /**
     * Create the frame.
     */
    public NewGameFrame()
    {
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(450, 300);
	setLocationRelativeTo(null);
	setTitle("Rozpocznij now¹ grê");
	newGamePanel = new NewGamePanel();
	setContentPane(newGamePanel);
    }

}
