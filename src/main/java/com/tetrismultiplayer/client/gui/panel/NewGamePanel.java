package main.java.com.tetrismultiplayer.client.gui.panel;

import main.java.com.tetrismultiplayer.client.Main;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;

public class NewGamePanel extends JPanel
{
    private static final long serialVersionUID = -8857678844077213832L;

    private Main main;

    private JButton startGameBtn;
    private JComboBox<String> gameTypeComboBox;
    private JComboBox<Integer> playersNumberComboBox;
    private JComboBox<String> difficultyLevelComboBox;

    private final String[] gameType = {"Wspolpraca", "Konkurencja"};
    private final Integer[] playersNumber = {1, 2, 3, 4};
    private final String[] difficultyLevel = {"Łatwy", "Normalny", "Trudny"};

    /**
     * Create the panel.
     */
    public NewGamePanel(Main main)
    {
        super();
        setLayout(new FlowLayout());

        this.main = main;

        startGameBtn = new JButton("Rozpocznij");
        gameTypeComboBox = new JComboBox<>(gameType);
        playersNumberComboBox = new JComboBox<>(playersNumber);
        difficultyLevelComboBox = new JComboBox<>(difficultyLevel);

        addElementsToPanel();
        setElementsSettings();
    }

    private void addElementsToPanel()
    {
        add(difficultyLevelComboBox);
        add(playersNumberComboBox);
        add(startGameBtn);
    }

    private void setElementsSettings()
    {
        startGameBtn.setSize(50, 50);
        startGameBtn.addActionListener(action -> {
            JSONObject startCmd = new JSONObject().put("cmd", "newGame");
            Integer playersNumber = (Integer) playersNumberComboBox.getSelectedItem();
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
            String difficult = (String) difficultyLevelComboBox.getSelectedItem();
            if (difficult.equals(difficultyLevel[0])) startCmd.put("difficultyLvl", "easy");
            else if (difficult.equals(difficultyLevel[1])) startCmd.put("difficultyLvl", "normal");
            else if (difficult.equals(difficultyLevel[2])) startCmd.put("difficultyLvl", "hard");
            main.sendMessage(startCmd);
            main.getMainPanel().requestFocus();
            SwingUtilities.getWindowAncestor(this).dispose();
        });

        gameTypeComboBox.setSize(50, 50);

        difficultyLevelComboBox.setSize(50, 50);

        playersNumberComboBox.setSize(50, 50);
        playersNumberComboBox.addActionListener(action -> {
            if (((JComboBox) action.getSource()).getSelectedItem().equals(1))
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
