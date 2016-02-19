package main.java.com.tetrismultiplayer.client.engine;

import java.util.HashMap;

/**
 * Created by Marcin on 2016-02-17.
 */
public abstract class Game
{
    protected HashMap<String, User> users;
    protected GameType gameType;
    protected String identifier;
    protected Integer playersNumber;

    protected Game(GameType gameType, User user, Integer playersNumber)
    {
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
}
