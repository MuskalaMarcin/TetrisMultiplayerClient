package main.java.com.tetrismultiplayer.client.engine;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.engine.tetromino.Brick;
import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;
import main.java.com.tetrismultiplayer.client.gui.panel.LeftPanel;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Created by Marcin on 2016-02-15.
 */
public class ServerListenerThread extends SwingWorker<Object, Object>
{
    private Main main;
    private Socket socket;
    private LeftPanel leftPanel;
    private GamePanel gamePanel;
    private Game game;
    private User localUser;
    private LinkedList<Game> waitingGamesList;

    public ServerListenerThread(Main main, User localUser)
    {
        this.localUser = localUser;
        this.main = main;
        this.leftPanel = main.getMainPanel().getLeftPanel();
        this.gamePanel = main.getMainPanel().getGamePanel();
        this.socket = main.getSocket();
    }

    public Object doInBackground() throws InterruptedException
    {
        try
        {
            JSONObject newMsg;
            while (!(newMsg = main.receiveJSON()).getString("cmd").equals("end"))
            {
                switch (newMsg.getString("cmd"))
                {
                    case "gameStarted":
			changeButtonStateToFalse();
                        startNewGame(newMsg);
                        break;
                    case "move":
                        game.performMove(newMsg);
                        break;
                    case "newTetromino":
                        game.addNewTetromino(newMsg);
                        break;
                    case "clearLine":
                        game.clearLine(newMsg);
                        break;
                    case "endGame":
                        endGame();
			changeButtonStateToTrue();
                        break;
                    case "setRanking":
                        setRanking(newMsg);
                        break;
                    case "waiting":
                        setWaitingStatus(newMsg);
                        break;
                    case "timeout":
                        waitingTimeout();
                        break;
		    case "isFullGames":
			setFullGamesStatus();
			break;
                    case "setGamesList":
                        setGamesList(newMsg);
                }
            }

            socket.close();
            leftPanel.setStatusText("Rozłączono");
            leftPanel.setButtonStatus(false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private void setFullGamesStatus()
    {
	leftPanel.setStatusText("Max. liczba gier serwera");
    }

    private void setGamesList(JSONObject newMsg)
    {
        this.waitingGamesList = new LinkedList<>();
        if (newMsg.getString("isEmpty").equals("false"))
        {
            JSONArray gamesList = newMsg.getJSONArray("gamesList");
            for (int i = 0; i < gamesList.length(); i++)
            {
                JSONObject game = gamesList.getJSONObject(i);
                Game waitingGame = null;
                JSONArray users = game.getJSONArray("users");
                for (int j = 0; j < users.length(); j++)
                {
                    JSONObject user = users.getJSONObject(j);
                    User newUser = new User(user.getString("nick"), user.getString("identifier"),
                            user.getString("ip"), user.getInt("ranking"));
                    if (j == 0)
                    {
                        switch (game.getString("type"))
                        {
                            case "CONCURRENT":
                                waitingGame = new ConcurrentGame(gamePanel, newUser, game.getInt("playersNumber"));
                                break;
                            case "COOPERATION":
                                waitingGame = new CooperationGame(gamePanel, newUser, game.getInt("playersNumber"));
                                break;
                        }
                    }
                    else
                    {
                        waitingGame.addUser(newUser);
                    }
                }
                waitingGamesList.add(waitingGame);
            }
        }
    }

    private void waitingTimeout()
    {
        leftPanel.setStatusText("Brak graczy");
    }

    private void setWaitingStatus(JSONObject newMsg)
    {
        leftPanel.setStatusText(String.valueOf(newMsg.getInt("time")));
    }

    private void startNewGame(JSONObject newMsg)
    {
        switch (newMsg.getString("type"))
        {
            case "single":
                leftPanel.setStatusText("Gra pojedyncza");
                game = new SingleGame(gamePanel, localUser);
                break;
            case "cooperation":
                leftPanel.setStatusText("Gra wspólna");
                LinkedList<User> newGameUsers = getNewGameUsers(newMsg);
                game = new CooperationGame(gamePanel, newGameUsers.getFirst(), newMsg.getInt("playersNumber"));
                newGameUsers.forEach(user -> game.addUser(user));
                break;
            case "concurrent":
                leftPanel.setStatusText("Gra rywalizacja");
                LinkedList<User> newGameUsers2 = getNewGameUsers(newMsg);
                for (int i = 0; i < newMsg.getInt("playersNumber"); i++)
                {
                    JSeparator backgroundSeparator = new JSeparator(SwingConstants.VERTICAL);
                    backgroundSeparator.setLocation(new Point(Brick.LENGTH * 10 * (i + 1), 1));
                    backgroundSeparator.setSize(new Dimension(2, Brick.LENGTH * 20 - 2));
                    gamePanel.add(backgroundSeparator);
                }
                game = new ConcurrentGame(gamePanel, newGameUsers2.getFirst(), newMsg.getInt("playersNumber"));
                newGameUsers2.forEach(user -> game.addUser(user));
                break;
        }
    }

    private LinkedList<User> getNewGameUsers(JSONObject newMsg)
    {
        main.getMainFrame().setSize(newMsg.getInt("playersNumber"));
        LinkedList<User> playingUsers = new LinkedList<>();
        JSONArray users = newMsg.getJSONArray("players");
        for (int j = 0; j < users.length(); j++)
        {
            JSONObject user = users.getJSONObject(j);
            User newUser = new User(user.getString("nick"), user.getString("identifier"),
                    user.getString("ip"), user.getInt("ranking"));
            if (newUser.getIdentifier().equals(localUser.getIdentifier()))
            {
                playingUsers.add(localUser);
            }
            else
            {
                playingUsers.add(newUser);
            }
        }
        return playingUsers;
    }

    private void endGame()
    {
        leftPanel.setStatusText("Koniec gry");
        localUser.getTetrominos().clear();
        main.getMainPanel().getGamePanel().removeAll();
        main.getMainPanel().getGamePanel().repaint();
        game = null;
    }

    private void setRanking(JSONObject newMsg)
    {

    }

    public LinkedList<Game> getWaitingGames()
    {
        return waitingGamesList;
    }

    public void changeButtonStateToFalse()
    {
	leftPanel.getComponent(4).setEnabled(false);
	leftPanel.getComponent(5).setEnabled(false);
	leftPanel.repaint();
    }

    public void changeButtonStateToTrue()
    {
	leftPanel.getComponent(4).setEnabled(true);
	leftPanel.getComponent(5).setEnabled(true);
	leftPanel.repaint();
    }
}
