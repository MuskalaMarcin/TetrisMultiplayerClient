package main.java.com.tetrismultiplayer.client.gui.button;

import main.java.com.tetrismultiplayer.client.Main;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;

    private String label;
    private Main main;

    public ButtonEditor(JCheckBox checkBox, Main main) {

	super(checkBox);
	button = new JButton();
	button.setOpaque(true);
	this.main = main;
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
		    boolean isSelected, int row, int column) {
	if (isSelected) {
	    button.setForeground(table.getSelectionForeground());
	    button.setBackground(table.getSelectionBackground());
	} else {
	    button.setForeground(table.getForeground());
	    button.setBackground(table.getBackground());
	}
	String[] jakasnazwa = value.toString().split(" ");
	label = (value == null) ? "" : jakasnazwa[0];
	button.setText(label);
	button.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		main.sendMessage(new JSONObject().put("cmd", "connectToGame").put("identifier", jakasnazwa[1]));
		main.getMainPanel().requestFocus();
	    }
	});
	return button;
    }
}