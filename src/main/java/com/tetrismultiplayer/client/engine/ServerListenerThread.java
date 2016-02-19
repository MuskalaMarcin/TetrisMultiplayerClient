package main.java.com.tetrismultiplayer.client.engine;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.engine.tetromino.*;
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
                        startNewGame(newMsg);
                        break;
                    case "move":
                        performMove(newMsg);
                        break;
                    case "newTetromino":
                        addNewTetromino(newMsg);
                        break;
                    case "clearLine":
                        clearLine(newMsg);
                        break;
                    case "endGame":
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
                                waitingGame = new ConcurrentGame(newUser, game.getInt("playersNumber"));
                                break;
                            case "COOPERATION":
                                waitingGame = new CooperationGame(newUser, game.getInt("playersNumber"));
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
                game = new SingleGame(localUser);
                break;
            case "cooperation":
                leftPanel.setStatusText("Gra wspólna");
                LinkedList<User> newGameUsers = getNewGameUsers(newMsg);
                game = new CooperationGame(newGameUsers.getFirst(), newMsg.getInt("playersNumber"));
                newGameUsers.forEach(user -> game.addUser(user));
                System.out.println("tutaj");
                break;
            case "concurrent":
                leftPanel.setStatusText("Gra rywalizacja");
                LinkedList<User> newGameUsers2 = getNewGameUsers(newMsg);
                game = new ConcurrentGame(newGameUsers2.getFirst(), newMsg.getInt("playersNumber"));
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

    private void performMove(JSONObject newMsg)
    {
        Tetromino movedTetromino = game.getUser(newMsg.getString("identifier")).getActiveTetromino();
        switch (newMsg.getString("key"))
        {
            case "down":
                movedTetromino.moveDown();
                break;
            case "left":
                movedTetromino.moveLeft();
                break;
            case "right":
                movedTetromino.moveRight();
                break;
            case "drop":
                movedTetromino.drop(newMsg.getInt("amount"));
                break;
            case "rotate":
                movedTetromino.rotate();
                break;
        }
    }

    private void addNewTetromino(JSONObject newMsg)
    {
        User newTetrominoUser = game.getUser(newMsg.getString("identifier"));
        Tetromino newTetromino = null;
        int positionX = newMsg.getInt("row") * Brick.LENGTH;
        int positionY = newMsg.getInt("column") * Brick.LENGTH;
        Color color = new Color(newMsg.getInt("color"));
        switch (newMsg.getString("tetrominoType"))
        {
            case "I":
                newTetromino = new TetrominoI(gamePanel, color, positionX, positionY);
                break;
            case "J":
                newTetromino = new TetrominoJ(gamePanel, color, positionX, positionY);
                break;
            case "L":
                newTetromino = new TetrominoL(gamePanel, color, positionX, positionY);
                break;
            case "O":
                newTetromino = new TetrominoO(gamePanel, color, positionX, positionY);
                break;
            case "S":
                newTetromino = new TetrominoS(gamePanel, color, positionX, positionY);
                break;
            case "T":
                newTetromino = new TetrominoT(gamePanel, color, positionX, positionY);
                break;
            case "Z":
                newTetromino = new TetrominoZ(gamePanel, color, positionX, positionY);
                break;
        }
        newTetrominoUser.addTetromino(newTetromino);
    }

    private void clearLine(JSONObject newMsg) throws InterruptedException
    {
        User user = game.getUser(newMsg.getString("identifier"));
        int rowWidth = newMsg.getInt("row") * Brick.LENGTH;
        LinkedList<Tetromino> tetrominos = user.getTetrominos();
        for (int i = 0; i < tetrominos.size(); i++)
        {
            LinkedList<Brick> bricks = tetrominos.get(i).getBricksList();
            for (int j = 0; j < bricks.size(); j++)
            {
                if (bricks.get(j).getPosition().y == rowWidth)
                {
                    tetrominos.get(i).removeBrick(bricks.get(j));
                    j--;
                    if (tetrominos.get(i).getBricksList().isEmpty())
                    {
                        user.removeTetromino(tetrominos.get(i));
                        i--;
                    }
                }
            }
        }

        user.getTetrominos().stream().filter(tetromino -> tetromino.getPosition().y <= rowWidth)
                .forEach(tetromino1 -> tetromino1.moveDown());
    }

    private void endGame()
    {
        leftPanel.setStatusText("Koniec gry");
        main.getMainPanel().getGamePanel().removeAll();
        main.getMainPanel().getGamePanel().repaint();
    }

    private void setRanking(JSONObject newMsg)
    {

    }

    public LinkedList<Game> getWaitingGames()
    {
        return waitingGamesList;
    }
}
