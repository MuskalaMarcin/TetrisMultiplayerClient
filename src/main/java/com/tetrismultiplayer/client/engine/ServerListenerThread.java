package main.java.com.tetrismultiplayer.client.engine;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.engine.tetromino.*;
import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;
import main.java.com.tetrismultiplayer.client.gui.panel.LeftPanel;
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
                        endGame(newMsg);
                        break;
                    case "setRanking":
                        setRanking(newMsg);
                        break;
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

    private void startNewGame(JSONObject newMsg)
    {
        switch (newMsg.getString("type"))
        {
            case "single":
                leftPanel.setStatusText("Gra pojedyncza");
                game = new SingleGame(localUser);
                break;
        }
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

    private void endGame(JSONObject newMsg)
    {

    }

    private void setRanking(JSONObject newMsg)
    {

    }
}
