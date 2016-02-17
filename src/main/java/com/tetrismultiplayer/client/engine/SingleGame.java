package main.java.com.tetrismultiplayer.client.engine;

/**
 * Created by Marcin on 2016-02-17.
 */
public class SingleGame extends Game
{
    public SingleGame(User user)
    {
        super();
        users.put(user.getIdentifier(), user);
    }
}
