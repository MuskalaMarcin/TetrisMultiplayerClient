package com.muskalanawrot.tetrismultiplayer.client.gui.actionlistener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONObject;

import com.muskalanawrot.tetrismultiplayer.client.Main;
import com.muskalanawrot.tetrismultiplayer.client.gui.panel.LeftPanel;

public class ConnectActionListener implements ActionListener
{
    private Main main;
    private LeftPanel leftPanel;

    public ConnectActionListener(Main main)
    {
	this.main = main;
    }

    /**
     * Connects or disconnects from server.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
	Socket socket = null;
	leftPanel = main.getMainPanel().getLeftPanel();
	if (e.getActionCommand().equals("Po³¹cz"))
	{
	    if (leftPanel.getNick().isEmpty())
	    {
		leftPanel.getNickTxtField().setBackground(Color.RED);
	    }
	    else
	    {
		try
		{
		    socket = new Socket("127.0.0.1", 65534);
		    if (socket.isConnected() && !socket.isClosed())
		    {
			main.setSocket(socket);
		    }
		    else
		    {
			main.getMainPanel().getLeftPanel().setStatusText("B³¹d");
		    }
		    new PrintWriter(socket.getOutputStream(), true)
			    .println(new JSONObject().put("nick", leftPanel.getNick()));
		    String connectionStatus = new JSONObject(
			    new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine())
				    .getString("state");
		    if (connectionStatus.equals("connected"))
		    {
			leftPanel.setStatusText("Po³¹czono");
			setButtonStatus(true);

		    }
		    else if (connectionStatus.equals("rejected"))
		    {
			leftPanel.setStatusText("Odrzucono");
		    }
		    else
		    {
			leftPanel.setStatusText("B³¹d");
		    }
		}
		catch (IOException e1)
		{
		    e1.printStackTrace();
		}
	    }
	}
	else
	{
	    try
	    {
		main.getSocket().close();
		leftPanel.setStatusText("Roz³¹czono");
		setButtonStatus(false);
	    }
	    catch (IOException e1)
	    {

		e1.printStackTrace();
	    }
	}
    }

    private void setButtonStatus(Boolean status)
    {
	leftPanel.switchConnectBtn();
	leftPanel.setConnectToGameBtnStatus(status);
	leftPanel.setRankingBtnStatus(status);
	leftPanel.setStartNewGameBtnStatus(status);
	leftPanel.getNickTxtField().setEditable(!status);
    }
}
