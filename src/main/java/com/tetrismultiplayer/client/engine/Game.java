package main.java.com.tetrismultiplayer.client.engine;

import java.util.HashMap;

/**
 * Created by Marcin on 2016-02-17.
 */
public abstract class Game
{
    protected HashMap<String, User> users;

    protected Game()
    {
        this.users = new HashMap<>();
    }

    public HashMap<String, User> getUsers()
    {
        return users;
    }

    public User getUser(String key)
    {
        return users.get(key);
    }
}
