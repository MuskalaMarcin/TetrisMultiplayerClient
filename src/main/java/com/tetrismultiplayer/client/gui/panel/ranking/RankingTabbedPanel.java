package main.java.com.tetrismultiplayer.client.gui.panel.ranking;

import javax.swing.JTabbedPane;

public class RankingTabbedPanel extends JTabbedPane
{
    private static final long serialVersionUID = 1327792069279300334L;

    /**
     * Create ranking tabbed panel.
     */
    public RankingTabbedPanel()
    {
	super();

	addTab("Gra pojedy�cza", new SinglePlayerRankingPanel());

	addTab("Gra wsp�lna", new CooperationRankingPanel());

	addTab("Gra przeciwko", new CompetitionRankingPanel());
    }
}
