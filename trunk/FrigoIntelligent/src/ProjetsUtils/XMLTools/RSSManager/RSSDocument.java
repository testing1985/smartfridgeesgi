package ProjetsUtils.XMLTools.RSSManager;

import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RSSDocument {

	private Document 		 m_oDoc     = null;
	private Vector<RSSEntry> m_vEntries = null;
	
	public RSSDocument( Document oDoc ) {
		m_oDoc 	   = oDoc;
		m_vEntries = new Vector<RSSEntry>();

		String   sTitle   = null;
		String   sContent = null;
		String   sPubDate = null;
		String 	 sURL     = null;
		
		NodeList vNodeEntries = m_oDoc.getElementsByTagName("item");
		int iNbEntries 		  = vNodeEntries.getLength();
		
		for (int i = 0; i < iNbEntries ; i++)
		{
			NodeList vEntryDesc = vNodeEntries.item(i).getChildNodes();
			int 	 iSize  	= vEntryDesc.getLength();
			
			for (int j = 0; j < iSize ; j++) {
				Node oNode = vEntryDesc.item(j);
				String sNodeName = oNode.getNodeName();
				
				if( sNodeName == "title" )
					sTitle = oNode.getTextContent();
				else if( sNodeName == "pubDate" )
					sPubDate = oNode.getTextContent();
				else if( sNodeName == "link" )
					sURL = oNode.getTextContent();
				else if( sNodeName == "description" )
					sContent = oNode.getTextContent();
			}
			
			m_vEntries.add( new RSSEntry( sTitle , sURL , sContent , sPubDate ) );
			System.out.println( m_vEntries.lastElement() );
		}
	}
}
