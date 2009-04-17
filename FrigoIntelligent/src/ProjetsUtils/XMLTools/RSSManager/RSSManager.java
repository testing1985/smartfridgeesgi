package ProjetsUtils.XMLTools.RSSManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap; 

public class RSSManager {
	
	private Map<String , RSSDocument> m_mDocuments = null;
	private DocumentBuilderFactory 	  m_oFactory	= null;
	private DocumentBuilder 	      m_oBuilder   = null;
	
	public RSSManager()
	{
		m_mDocuments = new HashMap<String, RSSDocument>();
		m_oFactory   = DocumentBuilderFactory.newInstance();
		try
		{
			m_oBuilder   = m_oFactory.newDocumentBuilder();
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public Boolean AddFeed( String sFeedURL )
	{
		try {
			m_mDocuments.put( sFeedURL , new RSSDocument ( m_oBuilder.parse( sFeedURL ) ) );
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
}
