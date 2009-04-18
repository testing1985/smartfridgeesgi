import ProjetsUtils.XMLTools.RSSManager.*;

public class SmartFridgeApp {

	private RSSManager m_oRSSManager;
	
	public static void main(String[] args) {
		SmartFridgeApp oApp = new SmartFridgeApp();
	}
	
	public SmartFridgeApp()
	{
		m_oRSSManager = new RSSManager();
		
		m_oRSSManager.AddFeed( "http://www.developpez.com/rss.php" );
	}

}
