import java.io.FileNotFoundException;
import java.io.IOException;

import ProjetsUtils.XMLTools.XMLManager;
import ProjetsUtils.XMLTools.RSSManager.*;

import SmartFridgeAPI.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class SmartFridgeApp {
	private SmartFridge m_oSmartFridge;
	private RSSManager m_oRSSManager;
	
	public static void main( String[] args )
	{		
		SmartFridgeApp sma = new SmartFridgeApp();
		
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
		
		try {
			m_oSmartFridge = new SmartFridge();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	public RSSManager getRSSManager() {
		return m_oRSSManager;
	}

	public void setRSSManager(RSSManager manager) {
		m_oRSSManager = manager;
	}

	public SmartFridge getSmartFridge() {
		return m_oSmartFridge;
	}

	public void setSmartFridge(SmartFridge fridge) {
		m_oSmartFridge = fridge;
	}
		
}






