package main.java.com.tetrismultiplayer.client.gui.actionlistener;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.gui.panel.LeftPanel;
import main.java.com.tetrismultiplayer.client.implementation.ConnectToServer;
import main.java.com.tetrismultiplayer.client.implementation.ConnectingException;
import org.json.JSONObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

/**
 * Action listener for connect/disconnect button.
 */
public class ConnectActionListener implements ActionListener
{
    private Main main;
    private LeftPanel leftPanel;

    /**
     * Constructor for connect/disconnect action listener.
     */
    public ConnectActionListener(Main main, LeftPanel leftPanel)
    {
        this.main = main;
        this.leftPanel = leftPanel;
    }

    /**
     * Connects or disconnects from server.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("Połącz"))
        {
            ConnectToServer connectToServer = new ConnectToServer(main, leftPanel);
            connectToServer.addPropertyChangeListener(propertyChangeEvent -> {
                if (connectToServer.isDone())
                {
                    try
                    {
                        if (connectToServer.get())
                        {
                            leftPanel.setStatusText("Połączono");
                            leftPanel.setButtonStatus(true);
                        }
                        else throw new ConnectingException();
                    }
                    catch (ConnectingException | InterruptedException | ExecutionException e1)
                    {
                        leftPanel.setStatusText("Błąd");
                        leftPanel.setButtonStatus(false);
                    }
                }
            });
            connectToServer.execute();
        }
        else
        {
            main.sendMessage(new JSONObject().put("cmd", "end"));
        }
    }


}
