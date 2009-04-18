import java.io.FileNotFoundException;
import java.io.IOException;

import ProjetsUtils.XMLTools.XMLManager;
import ProjetsUtils.XMLTools.RSSManager.*;

public class SmartFridgeApp {

	private RSSManager m_oRSSManager;
	
	public static void main(String[] args) {
		SmartFridgeApp oApp = new SmartFridgeApp();
	}
	
	public SmartFridgeApp()
	{
		m_oRSSManager = new RSSManager();
		m_oRSSManager.addFeed( "http://www.developpez.com/rss.php" );
		
		try {
			XMLManager.encodeToFile(m_oRSSManager, "RSSManager.xml");
			m_oRSSManager = (RSSManager) XMLManager.decodeFromFile("RSSManager.xml");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
