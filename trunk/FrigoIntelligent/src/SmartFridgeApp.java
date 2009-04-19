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
			System.out.println( Fridge.m_vAliments.elementAt(i).getM_sName() );
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
		
	public Vector<Menu> getM_Menus() {
		return m_vMenus;
	}

	public void setM_Menus(Vector<Menu> menus) {
		m_vMenus = menus;
	}

	public Vector<Aliment> getM_Aliments() {
		return m_vAliments;
	}

	public void setM_Aliments(Vector<Aliment> aliments) {
		m_vAliments = aliments;
	}

	public RSSManager getM_oRSSManager() {
		return m_oRSSManager;
	}

	public void setM_oRSSManager(RSSManager manager) {
		m_oRSSManager = manager;
	}
		
}






