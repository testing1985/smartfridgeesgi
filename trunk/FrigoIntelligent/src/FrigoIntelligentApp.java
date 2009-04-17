import ProjetsUtils.XMLTools.RSSManager.*;

public class FrigoIntelligentApp {

	private RSSManager m_oRSSManager;
	
	public static void main(String[] args) {
		FrigoIntelligentApp oApp = new FrigoIntelligentApp();
	}
	
	public FrigoIntelligentApp()
	{
		m_oRSSManager = new RSSManager();
		
		m_oRSSManager.AddFeed( "http://www.developpez.com/rss.php" );
	}

}
