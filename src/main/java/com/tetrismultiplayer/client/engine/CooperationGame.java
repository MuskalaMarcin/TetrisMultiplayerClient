package main.java.com.tetrismultiplayer.client.engine;

/**
 * Created by Marcin on 2016-02-17.
 */
public class CooperationGame extends Game
{
    public CooperationGame(User ownerUser, Integer playersNumber)
    {
        super(GameType.COOPERATION, ownerUser, playersNumber);
    }
}
