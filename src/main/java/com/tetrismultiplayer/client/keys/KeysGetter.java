package main.java.com.tetrismultiplayer.client.keys;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public class KeysGetter
{
    public static HashMap<Integer, String> keys;
    public static ArrayList<String> keyNames;

    public static void loadKeys()
    {
	keyNames = new ArrayList<String>();
	keys = new HashMap<Integer, String>();
	Field[] fields = KeyEvent.class.getFields();
	for(Field f: fields)
	{
	    if(Modifier.isStatic(f.getModifiers()))
	    {
		if(f.getName().startsWith("VK"))
		{
		    try
		    {
			int keyCode = f.getInt(null);
			String name = KeyEvent.getKeyText(keyCode);
			keys.put(keyCode, name);
			keyNames.add(name);
			if(name.equalsIgnoreCase("F12")) break;
		    }
		    catch (Exception e){
			e.printStackTrace();
		    }
		}
	    }
	}
    }

}