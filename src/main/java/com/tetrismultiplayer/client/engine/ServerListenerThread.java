package main.java.com.tetrismultiplayer.client.engine;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.engine.tetromino.*;
import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;
import main.java.com.tetrismultiplayer.client.gui.panel.LeftPanel;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Marcin on 2016-02-15.
 */
public class ServerListenerThread extends SwingWorker<Object, Object>
{
    private Main main;
    private BufferedReader in;
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
        try
        {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Object doInBackground()
    {
        try
        {
            JSONObject newMsg;
            while (!(newMsg = new JSONObject(in.readLine())).getString("cmd").equals("end"))
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
                System.out.println(newMsg);
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

    private void clearLine(JSONObject newMsg)
    {

    }

    private void endGame(JSONObject newMsg)
    {

    }

    private void setRanking(JSONObject newMsg)
    {

    }
}
