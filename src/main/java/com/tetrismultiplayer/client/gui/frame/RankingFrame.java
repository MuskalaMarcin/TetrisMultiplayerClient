package main.java.com.tetrismultiplayer.client.gui.frame;

import main.java.com.tetrismultiplayer.client.gui.panel.ranking.RankingTabbedPanel;

import javax.swing.*;

/**
 * Frame for ranking window.
 */
public class RankingFrame extends JFrame
{
    private static final long serialVersionUID = -1588581509786784374L;
    
    private RankingTabbedPanel rankingTabbedPanel;

    /**
     * Create the frame.
     */
    public RankingFrame()
    {
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(450, 300);
	setLocationRelativeTo(null);
	setTitle("Ranking");
	rankingTabbedPanel = new RankingTabbedPanel();
	setContentPane(rankingTabbedPanel);
    }

}
