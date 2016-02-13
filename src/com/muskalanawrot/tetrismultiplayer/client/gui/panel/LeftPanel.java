package com.muskalanawrot.tetrismultiplayer.client.gui.panel;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;

public class LeftPanel extends JPanel
{
    private static final long serialVersionUID = -1576730898183895708L;
    private JTextField textField_1;
    private JTextField textField_4;
    private JTextField textField;

    /**
     * Create the panel.
     */
    public LeftPanel()
    {
    	setLayout(null);
    	setBackground(Color.WHITE);
    	setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	
    	JPanel panel = new JPanel();
    	panel.setBounds(10, 290, 180, 100);
    	panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	add(panel);
    	
    	textField_1 = new JTextField();
    	textField_1.setBounds(55, 185, 135, 25);
    	add(textField_1);
    	textField_1.setColumns(10);

    	JButton btnPocz = new JButton("Po\u0142\u0105cz");
    	btnPocz.setBounds(10, 215, 180, 25);
    	btnPocz.setBackground(Color.WHITE);
    	add(btnPocz);
    	
    	JButton btnRanking = new JButton("Ranking");
    	btnRanking.setBounds(10, 140, 180, 25);
    	btnRanking.setBackground(Color.WHITE);
    	add(btnRanking);
    	
    	JButton btnUstawienia = new JButton("Ustawienia");
    	btnUstawienia.setBounds(10, 245, 180, 25);
    	btnUstawienia.setBackground(Color.WHITE);
    	add(btnUstawienia);
    	
    	JButton btnRozpocznijNowGr = new JButton("Rozpocznij now\u0105 gr\u0119");
    	btnRozpocznijNowGr.setBounds(10, 80, 180, 25);
    	btnRozpocznijNowGr.setBackground(Color.WHITE);
    	add(btnRozpocznijNowGr);
    	
    	JButton btnDoczDoIstniejcej = new JButton("Do\u0142\u0105cz do istniej\u0105cej gry");
    	btnDoczDoIstniejcej.setBounds(10, 110, 180, 25);
    	btnDoczDoIstniejcej.setBackground(Color.WHITE);
    	add(btnDoczDoIstniejcej);
    	
    	textField_4 = new JTextField();
    	textField_4.setBounds(55, 10, 135, 25);
 	textField_4.setText("0");
    	textField_4.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
    	textField_4.setForeground(Color.RED);
    	textField_4.setColumns(10);
    	add(textField_4);
    	
    	JLabel lblNick = new JLabel("Nick:");
    	lblNick.setBounds(10, 190, 46, 14);
    	add(lblNick);
    	
    	JLabel lblWynik = new JLabel("Wynik:");
    	lblWynik.setBounds(10, 16, 46, 14);
    	add(lblWynik);
    	
    	JSeparator separator = new JSeparator();
    	separator.setBounds(1, 280, 198, 2);
    	separator.setForeground(Color.BLACK);
    	add(separator);
    	
    	JSeparator separator_1 = new JSeparator();
    	separator_1.setBounds(1, 175, 198, 2);
    	separator_1.setForeground(Color.BLACK);
    	add(separator_1);
    	
    	textField = new JTextField();
    	textField.setBounds(55, 45, 135, 25);
    	add(textField);
    	textField.setColumns(10);
    	
    	JLabel lblStatus = new JLabel("Status:");
    	lblStatus.setBounds(10, 50, 46, 14);
    	add(lblStatus);
    }
}
