import java.io.FileNotFoundException;
import java.io.IOException;

import ProjetsUtils.XMLTools.XMLManager;
import ProjetsUtils.XMLTools.RSSManager.*;

import SmartFridgeAPI.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class SmartFridgeApp {
	private Vector< Menu > m_vMenus = null;
	private Vector< Aliment > m_vAliments = null;
	private RSSManager m_oRSSManager;
	
	public static void main( String[] args )
	{		
	
		SmartFridgeApp Fridge = new SmartFridgeApp();
		for( int i = 0; i < Fridge.m_vAliments.size() ; i++ )
		{
			System.out.println( Fridge.m_vAliments.elementAt(i).getName() );
		}
		
	}
	
	public SmartFridgeApp()
	{
		try {
			XMLManager.encodeToFile(m_oRSSManager, "RSSManager.xml");
			m_oRSSManager = (RSSManager) XMLManager.decodeFromFile("RSSManager.xml");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		try{
			m_vAliments = new Vector<Aliment>();	
			m_vMenus = new Vector<Menu>();
		}catch( Exception e ){
			e.printStackTrace();
		}		
		
	}
		
	public Vector<Menu> getMenus() {
		return m_vMenus;
	}

	public void setMenus(Vector<Menu> menus) {
		m_vMenus = menus;
	}

	public Vector<Aliment> getAliments() {
		return m_vAliments;
	}

	public void setAliments(Vector<Aliment> aliments) {
		m_vAliments = aliments;
	}

	public RSSManager getRSSManager() {
		return m_oRSSManager;
	}

	public void setRSSManager(RSSManager manager) {
		m_oRSSManager = manager;
	}
		
}






