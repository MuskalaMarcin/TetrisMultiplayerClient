package main.java.com.tetrismultiplayer.client.gui.panel;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.keys.KeysGetter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SettingsPanel extends JPanel
{
    private static final long serialVersionUID = 4746055900161316999L;

    private static Choice left, right, rotate, drop, down;
    private static JTextField poleIp, polePort;
    private static JLabel labelIp, labelPort;

    /**
     * Create the panel.
     */
    public SettingsPanel()
    {
	try
	{
	    setLayout(null);

	    KeysGetter.loadKeys();
	    left = addChoice("Przesuń w lewo", 80, 30);
	    left.select(Main.settings.left);
	    right = addChoice("Przesuń w prawo", 240, 30);
	    right.select(Main.settings.right);
	    down = addChoice("Przesuń w dół", 80, 80);
	    down.select(Main.settings.down);
	    rotate = addChoice("Obróć", 240, 80);
	    rotate.select(Main.settings.rotate);
	    drop = addChoice("Upuść", 80, 130);
	    drop.select(Main.settings.drop);

	    labelIp = new JLabel("IP serwera");
	    labelIp.setBounds(80, 160, 100, 20);
	    add(labelIp);

	    poleIp = new JTextField();
	    poleIp.setText(Main.settings.ip);
	    poleIp.setBounds(80, 180, 100, 25);
	    add(poleIp);

	    labelPort = new JLabel("Port serwera");
	    labelPort.setBounds(240, 160, 100, 20);
	    add(labelPort);

	    polePort = new JTextField();
	    polePort.setText(Main.settings.port.toString());
	    polePort.setBounds(240, 180, 100, 25);
	    add(polePort);

	    JButton saveChanges = new JButton("Zapisz");
	    saveChanges.setBounds(160, 220, 100, 25);
	    saveChanges.setBackground(Color.WHITE);
	    saveChanges.addActionListener(new ActionListener()
	    {
		public void actionPerformed(ActionEvent e)
		{
		    saveConfig();
		    SwingUtilities.getWindowAncestor(getParent()).dispose();
		}
	    });
	    add(saveChanges);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

    private Choice addChoice(String name, int x, int y)
    {
	JLabel label = new JLabel(name);
	label.setBounds(x, y-20, 100, 20);
	Choice key = new Choice();
	for(String s: KeysGetter.keyNames)
	{
	    key.add(s);
	}
	key.setBounds(x, y, 100, 20);
	add(key);
	add(label);
	add(key);
	return key;
    }

    private void saveConfig()
    {
	Main.settings.setLeft(left.getSelectedItem());
	Main.settings.setRight(right.getSelectedItem());
	Main.settings.setDown(down.getSelectedItem());
	Main.settings.setRotate(rotate.getSelectedItem());
	Main.settings.setDrop(drop.getSelectedItem());
	Main.settings.setIp(poleIp.getText());
	Main.settings.setPort(Integer.parseInt(polePort.getText()));

	saveConfigToFile();
    }

    private void saveConfigToFile()
    {
	try
	{
	    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\settings.config"));
	    out.writeObject(Main.settings);
	    out.close();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
    }
}
