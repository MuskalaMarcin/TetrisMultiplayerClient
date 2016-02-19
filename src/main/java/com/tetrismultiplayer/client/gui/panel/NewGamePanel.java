package main.java.com.tetrismultiplayer.client.gui.panel;

import main.java.com.tetrismultiplayer.client.Main;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class NewGamePanel extends JPanel
{
    private static final long serialVersionUID = -8857678844077213832L;

    private Main main;

    private JButton startGameBtn;
    private JComboBox<String> gameTypeComboBox;
    private JComboBox<Integer> playersNumberComboBox;
    private JComboBox<String> difficultyLevelComboBox;
    private JLabel gameTypeLabel, playersNumberLabel, difficultyLevelLabel;
    private JSlider playersNumberSlider, difficultyLevelSlider;

    private final String[] gameType = {"Wspolpraca", "Konkurencja"};
    private final Integer[] playersNumber = {1, 2, 3, 4};
    private final String[] difficultyLevel = {"Łatwy", "Normalny", "Trudny"};
    private final ArrayList<String> difficultyLevelList = new ArrayList<String>()
    {{
        add("Easy");
        add("Normal");
        add("Hard");
    }};
    private Hashtable playersLabelTable, levelLabelTable;

    /**
     * Create the panel.
     */
    public NewGamePanel(Main main)
    {
        setLayout(new GridBagLayout());
        this.main = main;
        GridBagConstraints c = new GridBagConstraints();

        playersLabelTable = new Hashtable();
        playersLabelTable.put(new Integer(1), new JLabel("1"));
        playersLabelTable.put(new Integer(2), new JLabel("2"));
        playersLabelTable.put(new Integer(3), new JLabel("3"));
        playersLabelTable.put(new Integer(4), new JLabel("4"));

        levelLabelTable = new Hashtable();
        levelLabelTable.put(new Integer(1), new JLabel(difficultyLevel[0]));
        levelLabelTable.put(new Integer(2), new JLabel(difficultyLevel[1]));
        levelLabelTable.put(new Integer(3), new JLabel(difficultyLevel[2]));

        gameTypeLabel = new JLabel("Typ gry: ");
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 25;
        c.ipadx = 25;
        add(gameTypeLabel, c);
        gameTypeComboBox = new JComboBox<>(gameType);
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 10;
        add(gameTypeComboBox, c);

        playersNumberLabel = new JLabel("Ilość graczy: ");
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 25;
        add(playersNumberLabel, c);
        playersNumberSlider = new JSlider(JSlider.HORIZONTAL, 1, 4, 2);
        c.gridx = 1;
        c.gridy = 1;
        playersNumberSlider.setLabelTable(playersLabelTable);
        playersNumberSlider.setPaintLabels(true);
        add(playersNumberSlider, c);

        difficultyLevelLabel = new JLabel("Poziom trudności: ");
        c.gridx = 0;
        c.gridy = 2;
        add(difficultyLevelLabel, c);
        difficultyLevelSlider = new JSlider(JSlider.HORIZONTAL, 1, 3, 1);
        c.gridx = 1;
        c.gridy = 2;
        difficultyLevelSlider.setLabelTable(levelLabelTable);
        difficultyLevelSlider.setPaintLabels(true);
        add(difficultyLevelSlider, c);

        startGameBtn = new JButton("Rozpocznij grę");
        c.gridx = 1;
        c.gridy = 3;
        c.ipady = 10;
        add(startGameBtn, c);

        setElementsSettings();
    }

    private void setElementsSettings()
    {
        startGameBtn.addActionListener(action -> {
            JSONObject startCmd = new JSONObject().put("cmd", "newGame");
            Integer playersNumber = playersNumberSlider.getValue();
            startCmd.put("pNumber", playersNumber);
            if (playersNumber.equals(1))
            {
                startCmd.put("gameType", "single");
            }
            else
            {
                if (gameTypeComboBox.getSelectedItem().equals(gameType[0]))
                {
                    startCmd.put("gameType", "cooperation");
                }
                else if (gameTypeComboBox.getSelectedItem().equals(gameType[1]))
                {
                    startCmd.put("gameType", "concurrent");
                }
            }

            startCmd.put("difficultyLvl", difficultyLevelList.get(difficultyLevelSlider.getValue() - 1).toLowerCase());
            main.sendMessage(startCmd);
            main.getMainPanel().requestFocus();
            SwingUtilities.getWindowAncestor(this).dispose();
        });

        playersNumberSlider.addChangeListener(action -> {
            if (((JSlider) action.getSource()).getValue() == 1)
            {
                remove(gameTypeComboBox);
                validate();
                repaint();
            }
            else
            {
                add(gameTypeComboBox);
                validate();
                repaint();
            }
        });
    }
}
