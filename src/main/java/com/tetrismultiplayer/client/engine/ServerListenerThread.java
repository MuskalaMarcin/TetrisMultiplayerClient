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
import java.util.Map;

/**
 * Class listening to communication from server and performing selected methods.
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

    /**
     * Main method listening on server socket constantly and forwarding messages to selected methods.
     */
    public Object doInBackground()
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
			changeButtonStateToTrue();
                        endGame();
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
                    case "setGamesList":
                        setGamesList(newMsg);
                        break;
                    case "addScore":
                        addScore(newMsg);
                        break;
		    case "isFullGames":
			setFullGamesStatus();
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

    /**
     * Method adding score to selected user.
     *
     * @param newMsg
     */
    private void addScore(JSONObject newMsg)
    {
        game.getUser(newMsg.getString("identifier")).addScore(newMsg.getInt("score"));
        if (game.getGameType() == Game.GameType.CONCURRENT)
        {
            LinkedList<String> panelValues = new LinkedList<>();
            game.getUsers().entrySet().stream().map(Map.Entry::getValue).forEach(user -> {
                panelValues.add(user.getNick());
                panelValues.add(String.valueOf(user.getScore()));
            });
            leftPanel.setPlayersPanel(true, panelValues);
        }
        else
        {
            leftPanel.setScoreTxtField(game.getUser(newMsg.getString("identifier")).getScore());
        }
    }

    /**
     * Method informing user about full games list on server
     */
    private void setFullGamesStatus()
    {
	leftPanel.setStatusText("Max. liczba gier serwera");
    }

    /**
     * Method setting up waiting games list.
     *
     * @param newMsg
     */
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

    /**
     * Method performing timeout after not enough players in game
     */
    private void waitingTimeout()
    {
        leftPanel.setStatusText("Brak graczy");
    }

    private void setWaitingStatus(JSONObject newMsg)
    {
        leftPanel.setStatusText(String.valueOf(newMsg.getInt("time")));
    }

    /**
     * Method starting new game after receiving confirmation from server.
     *
     * @param newMsg
     */
    private void startNewGame(JSONObject newMsg)
    {
        switch (newMsg.getString("type"))
        {
            case "single":
                leftPanel.setStatusText("Gra pojedyncza");
                LinkedList<String> panelValues1 = new LinkedList<>();
                panelValues1.add(localUser.getNick());
                panelValues1.add(String.valueOf(localUser.getRanking()));
                leftPanel.setPlayersPanel(false, panelValues1);
                game = new SingleGame(gamePanel, localUser);
                break;
            case "cooperation":
                leftPanel.setStatusText("Gra wspólna");
                LinkedList<User> newGameUsers = getNewGameUsers(newMsg);
                LinkedList<String> panelValues = new LinkedList<>();
                newGameUsers.forEach(user -> {
                    panelValues.add(user.getNick());
                    panelValues.add(String.valueOf(user.getRanking()));
                });
                leftPanel.setPlayersPanel(false, panelValues);
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
                LinkedList<String> panelValues2 = new LinkedList<>();
                newGameUsers2.forEach(user -> {
                    panelValues2.add(user.getNick());
                    panelValues2.add("0");
                });
                leftPanel.setPlayersPanel(true, panelValues2);
                game = new ConcurrentGame(gamePanel, newGameUsers2.getFirst(), newMsg.getInt("playersNumber"));
                newGameUsers2.forEach(user -> game.addUser(user));
                break;
        }
    }

    /**
     * Method getting users from new game.
     *
     * @param newMsg
     * @return
     */
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

    /**
     * Method ending game.
     */
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

    /**
     * Method changing buttons state to false
     */
    public void changeButtonStateToFalse()
    {
	leftPanel.getComponent(4).setEnabled(false);
	leftPanel.getComponent(5).setEnabled(false);
	leftPanel.repaint();
    }

    /**
     * Method changing buttons state to true
     */
    public void changeButtonStateToTrue()
    {
	leftPanel.getComponent(4).setEnabled(true);
	leftPanel.getComponent(5).setEnabled(true);
	leftPanel.repaint();
    }
}
