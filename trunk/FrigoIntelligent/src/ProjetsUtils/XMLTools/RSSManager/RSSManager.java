package ProjetsUtils.XMLTools.RSSManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap; 
import java.util.Set;

public class RSSManager {
	
	private Map<String , RSSDocument> m_mDocuments = null;
	private DocumentBuilderFactory 	  m_oFactory   = null;
	private DocumentBuilder 	      m_oBuilder   = null;
	
	public int getFeedCount()
	{
		return m_mDocuments.size();
	}
	
	public String[] getFeedList()
	{
		Object[] vArray = m_mDocuments.keySet().toArray();
		String[] vResult = new String[ vArray.length ];
		for( int i = 0 ; i < vArray.length ; i++ )
			vResult[i] = (String)vArray[i];
		return vResult;
	}
	
	public void setFeedList( String[] vList )
	{
		m_mDocuments.clear();
		addFeedList( vList );
	}
	
	public void addFeedList( String[] vList )
	{
		
		for( int i = 0 ; i < vList.length ; i++ )
			addFeed( vList[i] );
	}
	
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
	
	public Boolean addFeed( String sFeedURL )
	{
		try {
			if( ! m_mDocuments.containsKey(sFeedURL) )
				m_mDocuments.put( sFeedURL , new RSSDocument ( m_oBuilder.parse( sFeedURL ) ) );
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
}
