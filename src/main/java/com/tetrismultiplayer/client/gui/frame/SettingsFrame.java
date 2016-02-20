package main.java.com.tetrismultiplayer.client.gui.frame;

import main.java.com.tetrismultiplayer.client.gui.panel.SettingsPanel;

import javax.swing.*;

/**
 * Frame for user settings window.
 */
public class SettingsFrame extends JFrame
{
    private static final long serialVersionUID = -6702244127334977186L;

    private SettingsPanel settingsPanel;

    /**
     * Create the frame.
     */
    public SettingsFrame()
    {
	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	setSize(450, 300);
	setLocationRelativeTo(null);
	setTitle("Ustawienia");
	settingsPanel = new SettingsPanel();
	setContentPane(settingsPanel);
    }

}
