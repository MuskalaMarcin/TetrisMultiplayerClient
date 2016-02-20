package main.java.com.tetrismultiplayer.client.gui.actionlistener;

import main.java.com.tetrismultiplayer.client.gui.frame.SettingsFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for settings button.
 */
public class SettingsActionListener implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e)
    {
	EventQueue.invokeLater(new Runnable()
	{
	    public void run()
	    {
		try
		{
		    SettingsFrame frame = new SettingsFrame();
		    frame.setVisible(true);
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
	    }
	});
    }

}
