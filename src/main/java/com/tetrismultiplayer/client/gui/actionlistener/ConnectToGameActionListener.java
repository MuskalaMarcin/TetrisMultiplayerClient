package main.java.com.tetrismultiplayer.client.gui.actionlistener;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.com.tetrismultiplayer.client.gui.frame.ConnectToGameFrame;

public class ConnectToGameActionListener implements ActionListener
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
		    ConnectToGameFrame frame = new ConnectToGameFrame();
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
