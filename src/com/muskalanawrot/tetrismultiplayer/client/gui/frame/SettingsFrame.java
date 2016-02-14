package com.muskalanawrot.tetrismultiplayer.client.gui.frame;

import javax.swing.JFrame;

import com.muskalanawrot.tetrismultiplayer.client.gui.panel.SettingsPanel;

public class SettingsFrame extends JFrame
{
    private static final long serialVersionUID = -6702244127334977186L;

    private SettingsPanel settingsPanel;

    /**
     * Create the frame.
     */
    public SettingsFrame()
    {
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	setTitle("Ustawienia");
	settingsPanel = new SettingsPanel();
	setContentPane(settingsPanel);
    }

}
