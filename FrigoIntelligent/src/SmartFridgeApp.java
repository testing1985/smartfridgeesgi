import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import ProjetsUtils.XMLTools.XMLManager;
import ProjetsUtils.XMLTools.RSSManager.*;

import SmartFridgeAPI.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SmartFridgeApp {
	public SFWindow    m_oApp;
	private SmartFridge m_oSmartFridge;
	private RSSManager  m_oRSSManager;
	private Session     m_oSession = null;
	
	public static void main( String[] args )
	{	
		Runnable r = new Runnable(){
			public void run() { new SmartFridgeApp(); }
		};
		SwingUtilities.invokeLater(r);
	}
	
	public SmartFridgeApp()
	{
		m_oApp = new SFWindow(this);
		
		try {
			initializeConnection();
			m_oRSSManager  = (RSSManager) XMLManager.decodeFromFile("RSSManager.xml");
			m_oSmartFridge = new SmartFridge();
			//m_oSession     = new Session();
			
			/*if( m_oSession.connect("esgi", "esgi") )
				System.out.println("esgi connected");
			else System.out.println("esgi not connected");*/
			
			DBConnectionManager.getInstance().closeConnection();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void initializeConnection()
	{
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
			String sURL = "jdbc:mysql://88.191.18.27:3306/isilgardh_smartfridge";
			DBConnectionManager.getInstance().setConnectionData(sURL, "esgi", "g6f3s9j3");			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
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
	
	public void quitAction() {
		System.exit(0);
	}
}






