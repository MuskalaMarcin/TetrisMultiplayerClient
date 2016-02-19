package main.java.com.tetrismultiplayer.client.engine;

/**
 * Created by Marcin on 2016-02-17.
 */
public class ConcurrentGame extends Game
{
    public ConcurrentGame(User ownerUser, Integer playersNumber)
    {
        super(GameType.CONCURRENT, ownerUser, playersNumber);
    }
}
