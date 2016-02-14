package com.muskalanawrot.tetrismultiplayer.client.gui.actionlistener;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.muskalanawrot.tetrismultiplayer.client.gui.frame.NewGameFrame;

public class StartNewGameActionListener implements ActionListener
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
		    NewGameFrame frame = new NewGameFrame();
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
