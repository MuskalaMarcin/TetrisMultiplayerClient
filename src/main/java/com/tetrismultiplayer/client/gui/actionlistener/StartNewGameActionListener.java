package main.java.com.tetrismultiplayer.client.gui.actionlistener;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.gui.frame.NewGameFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for start new game button.
 */
public class StartNewGameActionListener implements ActionListener
{
    private Main main;

    public StartNewGameActionListener(Main main)
    {
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    NewGameFrame frame = new NewGameFrame(main);
                    frame.setVisible(true);
                }
                catch (Exception e2)
                {
                    e2.printStackTrace();
                }
            }
        });
    }

}
