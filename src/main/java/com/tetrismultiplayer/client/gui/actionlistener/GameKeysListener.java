package main.java.com.tetrismultiplayer.client.gui.actionlistener;

import main.java.com.tetrismultiplayer.client.Main;
import org.json.JSONObject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeysListener implements KeyListener
{
    private Main main;

    public GameKeysListener(Main main)
    {
        this.main = main;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {


    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (main.getServerListenerThread() != null && !main.getServerListenerThread().isCancelled())
        {
            JSONObject key = new JSONObject().put("cmd", "move");
            switch (e.getKeyCode())
            {
                case 38:
                    key.put("key", "up");
                    main.sendMessage(key);
                    break;
                case 40:
                    key.put("key", "down");
                    main.sendMessage(key);
                    break;
                case 37:
                    key.put("key", "left");
                    main.sendMessage(key);
                    break;
                case 39:
                    key.put("key", "right");
                    main.sendMessage(key);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        // TODO Auto-generated method stub

    }

}
