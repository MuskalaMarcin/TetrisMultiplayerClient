package main.java.com.tetrismultiplayer.client.gui.panel;

import main.java.com.tetrismultiplayer.client.Main;
import main.java.com.tetrismultiplayer.client.gui.actionlistener.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Vector;

/**
 * Left panel of main window with user settings and buttons.
 */
public class LeftPanel extends JPanel
{
    private static final long serialVersionUID = -1576730898183895708L;

    private JTextField nickTxtField;
    private JTextField scoreTxtField;
    private JTextField statusTxtField;
    private JPanel playersPanel;
    private JButton btnConnect;
    private JButton btnRanking;
    private JButton btnSettings;
    private JButton btnStartNewGame;
    private JButton btnConnectToGame;
    private JLabel lblNick;
    private JLabel lblScore;
    private JSeparator separator;
    private JSeparator separator_1;
    private JLabel lblStatus;

    private Main main;

    /**
     * Create the panel.
     */
    public LeftPanel(Main main)
    {
        this.main = main;
        setLayout(null);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(false);

        playersPanel = new JPanel();

        statusTxtField = new JTextField();
        nickTxtField = new JTextField();
        scoreTxtField = new JTextField();

        btnConnect = new JButton("Po\u0142\u0105cz");
        btnRanking = new JButton("Ranking");
        btnSettings = new JButton("Ustawienia");
        btnStartNewGame = new JButton("Rozpocznij now\u0105 gr\u0119");
        btnConnectToGame = new JButton("Do\u0142\u0105cz do istniej\u0105cej gry");

        lblNick = new JLabel("Nick:");
        lblScore = new JLabel("Wynik:");
        lblStatus = new JLabel("Status:");

        separator = new JSeparator();
        separator_1 = new JSeparator();

        setElementsSettings();
        setActionListeners();
        addElements();
    }

    /**
     * Method adding action listeners.
     */
    private void setActionListeners()
    {
        btnConnect.addActionListener(new ConnectActionListener(main, this));
        btnRanking.addActionListener(new RankingActionListener());
        btnSettings.addActionListener(new SettingsActionListener());
        btnStartNewGame.addActionListener(new StartNewGameActionListener(main));
        btnConnectToGame.addActionListener(new ConnectToGameActionListener(main));
    }

    /**
     * Method adding all elements to main panel.
     */
    private void addElements()
    {
        add(playersPanel);

        add(btnConnect);
        add(btnRanking);
        add(btnSettings);
        add(btnStartNewGame);
        add(btnConnectToGame);

        add(nickTxtField);
        add(statusTxtField);
        add(scoreTxtField);

        add(lblStatus);
        add(lblNick);
        add(lblScore);

        add(separator);
        add(separator_1);
    }

    /**
     * Method maintaining settings of all elements.
     */
    private void setElementsSettings()
    {
        playersPanel.setBounds(10, 290, 180, 100);
        playersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playersPanel.setLayout(new BorderLayout());

        btnConnect.setBounds(10, 215, 180, 25);
        btnConnect.setBackground(Color.WHITE);

        btnRanking.setBounds(10, 140, 180, 25);
        btnRanking.setBackground(Color.WHITE);
        btnRanking.setEnabled(false);

        btnSettings.setBounds(10, 245, 180, 25);
        btnSettings.setBackground(Color.WHITE);

        btnStartNewGame.setBounds(10, 80, 180, 25);
        btnStartNewGame.setBackground(Color.WHITE);
        btnStartNewGame.setEnabled(false);

        btnConnectToGame.setBounds(10, 110, 180, 25);
        btnConnectToGame.setBackground(Color.WHITE);
        btnConnectToGame.setEnabled(false);

        lblNick.setBounds(10, 190, 46, 14);

        lblStatus.setBounds(10, 50, 46, 14);

        lblScore.setBounds(10, 16, 46, 14);

        separator.setBounds(1, 280, 198, 2);
        separator.setForeground(Color.BLACK);

        separator_1.setBounds(1, 175, 198, 2);
        separator_1.setForeground(Color.BLACK);

        statusTxtField.setBounds(55, 45, 135, 25);
        statusTxtField.setColumns(10);
        statusTxtField.setEditable(false);
        statusTxtField.setBackground(Color.WHITE);
        statusTxtField.setText("Niepołączono");
        statusTxtField.setHorizontalAlignment(JTextField.CENTER);

        nickTxtField.setBounds(55, 185, 135, 25);
        nickTxtField.setColumns(10);
        nickTxtField.setText("Gość");
        nickTxtField.setHorizontalAlignment(JTextField.CENTER);

        scoreTxtField.setBounds(55, 10, 135, 25);
        scoreTxtField.setText("0");
        scoreTxtField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        scoreTxtField.setForeground(Color.RED);
        scoreTxtField.setColumns(10);
        scoreTxtField.setEditable(false);
        scoreTxtField.setBackground(Color.WHITE);
        scoreTxtField.setHorizontalAlignment(JTextField.CENTER);
    }

    public JTextField getNickTxtField()
    {
        return nickTxtField;
    }

    public JTextField getScoreTxtField()
    {
        return scoreTxtField;
    }

    public void setScoreTxtField(Integer score)
    {
        scoreTxtField.setText(score.toString());
    }

    public JTextField getStatusTxtField()
    {
        return statusTxtField;
    }

    public void setStatusText(String text)
    {
        synchronized (statusTxtField)
        {
            statusTxtField.setText(text);
        }
    }

    /**
     * Method setting up players panel.
     *
     * @param isConcurrent
     * @param values
     */
    public void setPlayersPanel(boolean isConcurrent, LinkedList<String> values)
    {
        clearPlayersPanel();
        Vector<Object> columnNames = new Vector<>();
        columnNames.add("Gracz");
        if (!isConcurrent)
        {
            columnNames.add("Ranking");
        }
        else
        {
            columnNames.add("Wynik");
        }
        Vector<Object> data = new Vector<>();
        for (int i = 0; i < values.size(); i += 2)
        {
            Vector<Object> row = new Vector<>();
            row.add(values.get(i));
            row.add(values.get(i + 1));
            data.add(row);
        }

        JTable playersTable = new JTable(data, columnNames)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        playersTable.setFillsViewportHeight(true);
        playersPanel.add(new JScrollPane(playersTable));
        playersPanel.validate();
        playersPanel.repaint();
    }

    public void clearPlayersPanel()
    {
        playersPanel.removeAll();
    }


    /**
     * Sets connect button enabled value to selected state.
     *
     * @param isActive true if button should be enabled.
     */
    public void setConnectBtnStatus(boolean isActive)
    {
        btnConnect.setEnabled(isActive);
    }

    /**
     * Sets ranking button enabled value to selected state.
     *
     * @param isActive true if button should be enabled.
     */
    public void setRankingBtnStatus(boolean isActive)
    {
        btnRanking.setEnabled(isActive);
    }

    /**
     * Sets settings button enabled value to selected state.
     *
     * @param isActive true if button should be enabled.
     */
    public void setSettingsBtnStatus(boolean isActive)
    {
        btnSettings.setEnabled(isActive);
    }

    /**
     * Sets start new game button enabled value to selected state.
     *
     * @param isActive true if button should be enabled.
     */
    public void setStartNewGameBtnStatus(boolean isActive)
    {
        btnStartNewGame.setEnabled(isActive);
    }

    /**
     * Sets connect to game button enabled value to selected state.
     *
     * @param isActive true if button should be enabled.
     */
    public void setConnectToGameBtnStatus(boolean isActive)
    {
        btnConnectToGame.setEnabled(isActive);
    }

    public void switchConnectBtn(Boolean status)
    {
        if (status)
        {
            btnConnect.setText("Rozłącz");
        }
        else
        {
            btnConnect.setText("Połącz");
        }
    }

    public void setButtonStatus(Boolean status)
    {
        switchConnectBtn(status);
        setConnectToGameBtnStatus(status);
        setRankingBtnStatus(status);
        setStartNewGameBtnStatus(status);
        getNickTxtField().setEditable(!status);
    }

    public String getNick()
    {
        return nickTxtField.getText();
    }
}
