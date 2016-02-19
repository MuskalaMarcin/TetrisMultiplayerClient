package main.java.com.tetrismultiplayer.client.gui.panel;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.engine.Game;
import main.java.com.tetrismultiplayer.client.gui.button.ButtonEditor;
import main.java.com.tetrismultiplayer.client.gui.button.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

public class ConnectToGamePanel extends JPanel
{
    private static final long serialVersionUID = -216317464444204835L;

    private LinkedList<Game> waitingGames;

    /**
     * Create the panel.
     */
    public ConnectToGamePanel(LinkedList<Game> waitingGames, Main main)
    {
	setLayout(new FlowLayout());
	this.waitingGames = waitingGames;

	DefaultTableModel dm = new DefaultTableModel();
	dm.addColumn("Typ");
	dm.addColumn("Gracze");
	dm.addColumn(" ");
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

	waitingGames.forEach(game -> {
	    String[] row = { game.getGameType().name(), game.getUsers().size() + "/" + game.getPlayersNumber().toString(), "Dołącz " + game.getIdentifier() };
	    dm.addRow(row);
	});

	JTable table = new JTable(dm);
	table.getColumn(" ").setCellRenderer(new ButtonRenderer());
	table.getColumn(" ").setCellEditor(
			new ButtonEditor(new JCheckBox(), main));
	table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	JScrollPane scroll = new JScrollPane(table);
	scroll.setSize(450, 300);
	add(scroll);
    }


}
