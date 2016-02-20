package main.java.com.tetrismultiplayer.client.implementation;

/**
 * Exception to be thrown if server denies connection request ie. too many players.
 */
public class ConnectingException extends Exception
{
    public String toString()
    {
        return "Podczas łączenia z serwerem wystąpił błąd";
    }
}
