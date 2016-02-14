package main.java.com.tetrismultiplayer.client.gui.panel;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettingsPanel extends JPanel
{
    private static final long serialVersionUID = 4746055900161316999L;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Create the panel.
     */
    public SettingsPanel()
    {
    	setLayout(null);
    	
    	textField = new JTextField();
    	textField.setBounds(247, 41, 86, 20);
    	add(textField);
    	textField.setColumns(10);
    	
    	textField_1 = new JTextField();
    	textField_1.setBounds(247, 85, 86, 20);
    	add(textField_1);
    	textField_1.setColumns(10);

    }
}
