package main.java.com.tetrismultiplayer.client.gui.actionlistener;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.engine.ServerListenerThread;
import main.java.com.tetrismultiplayer.client.gui.panel.LeftPanel;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

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
            if (leftPanel.getNick().isEmpty())
            {
                leftPanel.getNickTxtField().setBackground(Color.RED);
            }
            else
            {
                try
                {
                    Socket socket = new Socket("127.0.0.1", 65534);
                    if (socket.isConnected() && !socket.isClosed())
                    {
                        main.setSocket(socket);

                        sendConnectionMsg();
                        String connectionStatus = main.receiveJSON().getString("state");
                        if (connectionStatus.equals("connected"))
                        {
                            leftPanel.setStatusText("Połączono");
                            leftPanel.setButtonStatus(true);
                            main.setServerListenerThread(new ServerListenerThread(main));

                            main.getServerListenerThread().execute();

                        }
                        else if (connectionStatus.equals("rejected"))
                        {
                            leftPanel.setStatusText("Odrzucono");
                        }
                        else throw new IOException();
                    }
                    else throw new IOException();
                }
                catch (IOException e1)
                {
                    leftPanel.setStatusText("Błąd");
                }
            }
        }
        else
        {
            sendEndMsg();
        }
    }

    /**
     * Sends connection message to server.
     */
    private void sendConnectionMsg()
    {
        main.sendMessage(new JSONObject().put("cmd", "connect").put("nick", leftPanel.getNick()));
    }

    /**
     * Sends message ending connection to server.
     */
    private void sendEndMsg()
    {
        main.sendMessage(new JSONObject().put("cmd", "end"));
    }
}
