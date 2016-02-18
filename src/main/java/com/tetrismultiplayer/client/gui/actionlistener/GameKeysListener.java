package main.java.com.tetrismultiplayer.client.gui.actionlistener;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.keys.KeysGetter;
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
	    if(KeysGetter.keys.get(e.getKeyCode()).equalsIgnoreCase(Main.settings.rotate))
	    {
		key.put("key", "rotate");
		main.sendMessage(key);
	    }
	    if(KeysGetter.keys.get(e.getKeyCode()).equalsIgnoreCase(Main.settings.down))
	    {
		key.put("key", "down");
		main.sendMessage(key);
	    }
	    if(KeysGetter.keys.get(e.getKeyCode()).equalsIgnoreCase(Main.settings.left))
	    {
		key.put("key", "left");
		main.sendMessage(key);
	    }
	    if(KeysGetter.keys.get(e.getKeyCode()).equalsIgnoreCase(Main.settings.right))
	    {
		key.put("key", "right");
		main.sendMessage(key);
	    }
	    if(KeysGetter.keys.get(e.getKeyCode()).equalsIgnoreCase(Main.settings.drop))
	    {
		key.put("key", "drop");
		main.sendMessage(key);
	    }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        // TODO Auto-generated method stub

    }

}
