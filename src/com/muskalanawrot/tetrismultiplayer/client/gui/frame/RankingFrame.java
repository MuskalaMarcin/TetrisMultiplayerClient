package com.muskalanawrot.tetrismultiplayer.client.gui.frame;

import javax.swing.JFrame;

import com.muskalanawrot.tetrismultiplayer.client.gui.panel.ranking.RankingTabbedPanel;

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
	setBounds(100, 100, 450, 300);
	setTitle("Ranking");
	rankingTabbedPanel = new RankingTabbedPanel();
	setContentPane(rankingTabbedPanel);
    }

}
