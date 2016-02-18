package main.java.com.tetrismultiplayer.client.gui.panel;

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

	    left = addChoice("Przesuń w lewo", 80, 30);
	    left.select(KeysGetter.settings.left);
	    right = addChoice("Przesuń w prawo", 240, 30);
	    right.select(KeysGetter.settings.right);
	    down = addChoice("Przesuń w dół", 80, 80);
	    down.select(KeysGetter.settings.down);
	    rotate = addChoice("Obróć", 240, 80);
	    rotate.select(KeysGetter.settings.rotate);
	    drop = addChoice("Upuść", 80, 130);
	    drop.select(KeysGetter.settings.drop);

	    labelIp = new JLabel("IP serwera");
	    labelIp.setBounds(80, 160, 100, 20);
	    add(labelIp);

	    poleIp = new JTextField();
	    poleIp.setText(KeysGetter.settings.ip);
	    poleIp.setBounds(80, 180, 100, 25);
	    add(poleIp);

	    labelPort = new JLabel("Port serwera");
	    labelPort.setBounds(240, 160, 100, 20);
	    add(labelPort);

	    polePort = new JTextField();
	    polePort.setText(KeysGetter.settings.port.toString());
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
	KeysGetter.settings.setLeft(left.getSelectedItem());
	KeysGetter.settings.setRight(right.getSelectedItem());
	KeysGetter.settings.setDown(down.getSelectedItem());
	KeysGetter.settings.setRotate(rotate.getSelectedItem());
	KeysGetter.settings.setDrop(drop.getSelectedItem());
	KeysGetter.settings.setIp(poleIp.getText());
	KeysGetter.settings.setPort(Integer.parseInt(polePort.getText()));

	saveConfigToFile();
    }

    private void saveConfigToFile()
    {
	try
	{
	    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\settings.config"));
	    out.writeObject(KeysGetter.settings);
	    out.close();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
    }
}
