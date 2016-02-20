package main.java.com.tetrismultiplayer.client.engine;

import main.java.com.tetrismultiplayer.client.engine.tetromino.*;
import main.java.com.tetrismultiplayer.client.gui.panel.GamePanel;
import org.json.JSONObject;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Common class for all game types.
 */
public abstract class Game
{
    protected HashMap<String, User> users;
    protected GameType gameType;
    protected String identifier;
    protected Integer playersNumber;
    protected GamePanel gamePanel;

    protected Game(GamePanel gamePanel, GameType gameType, User user, Integer playersNumber)
    {
        this.gamePanel = gamePanel;
        this.users = new HashMap<>();
        this.identifier = user.getIdentifier();
        this.playersNumber = playersNumber;
        this.gameType = gameType;
        users.put(user.getIdentifier(), user);
    }

    public enum GameType
    {
        SINGLE, CONCURRENT, COOPERATION
    }

    public HashMap<String, User> getUsers()
    {
        return users;
    }

    public User getUser(String key)
    {
        return users.get(key);
    }

    public void addUser(User user)
    {
        users.put(user.getIdentifier(), user);
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public GameType getGameType()
    {
        return gameType;
    }

    public Integer getPlayersNumber()
    {
        return playersNumber;
    }

    /**
     * Receives move in JSON object and perfroms move on selected tetromino.
     * @param newMsg JSON message
     */
    public void performMove(JSONObject newMsg)
    {
        Tetromino movedTetromino = getUser(newMsg.getString("identifier")).getActiveTetromino();
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

    /**
     * Receives adding new tetromino request in JSON object and adds it to selected user.
     * @param newMsg JSON message
     */
    public void addNewTetromino(JSONObject newMsg)
    {
        User newTetrominoUser = getUser(newMsg.getString("identifier"));
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

    /**
     * Method clearing one line from game panel
     * @param newMsg JSON object
     */
    public void clearLine(JSONObject newMsg)
    {
        int position = newMsg.getInt("position");
        int minColumn;
        int maxColumn;
        if (position == -1)
        {
            minColumn = 0;
            maxColumn = getUsers().size() * 10 * Brick.LENGTH;
        }
        else
        {
            minColumn = position * 10 * Brick.LENGTH;
            maxColumn = (position + 1) * 10 * Brick.LENGTH;
        }
        int rowWidth = newMsg.getInt("row") * Brick.LENGTH;
        getUsers().entrySet().stream().map(Map.Entry::getValue).forEach(user -> {
            LinkedList<Tetromino> tetrominos = user.getTetrominos();
            for (int i = 0; i < tetrominos.size(); i++)
            {
                if (tetrominos.get(i).getPosition().x >= minColumn && tetrominos.get(i).getPosition().x <= maxColumn)
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
                                for (int k = 0; k < tetrominos.get(i).getBricksList().size(); k++)
                                {
                                    gamePanel.remove(tetrominos.get(i).getBricksList().get(k));
                                    tetrominos.get(i).removeBrick(tetrominos.get(i).getBricksList().get(k));
                                }
                                user.removeTetromino(tetrominos.get(i));
                                i--;
                            }
                        }
                    }
                }
            }
        });

        getUsers().entrySet().stream().map(Map.Entry::getValue).forEach(user -> {
            user.getTetrominos().forEach(tetromino -> {
                if (tetromino.getPosition().x >= minColumn && tetromino.getPosition().x <= maxColumn)
                {
                    LinkedList<Brick> bricks = tetromino.getBricksList();
                    bricks.sort((brick1, brick2) -> Integer.valueOf(brick1.getPosition().y).compareTo(brick2.getPosition().y));
                    if (bricks.getLast().getPosition().y < rowWidth)
                    {
                        tetromino.moveDown();
                    }
                }
            });
        });
        gamePanel.validate();
        gamePanel.repaint();
    }

}
