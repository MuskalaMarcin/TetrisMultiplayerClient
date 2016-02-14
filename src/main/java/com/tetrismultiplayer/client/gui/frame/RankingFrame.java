package main.java.com.tetrismultiplayer.client.gui.frame;

import javax.swing.JFrame;

import main.java.com.tetrismultiplayer.client.gui.panel.ranking.RankingTabbedPanel;

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
