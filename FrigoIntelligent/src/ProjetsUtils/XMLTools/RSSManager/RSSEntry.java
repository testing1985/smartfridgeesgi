package ProjetsUtils.XMLTools.RSSManager;

public class RSSEntry {
	private String m_sTitle;
	private String m_sURL;
	private String m_sContent;
	private String m_sPubDate;
	
	public RSSEntry(String sTitle, String sURL, String sContent, String sPubDate) {
		super();
		m_sTitle   = sTitle;
		m_sURL 	   = sURL;
		m_sContent = sContent;
		m_sPubDate = sPubDate;
	}
	
	public String getTitle() {
		return m_sTitle;
	}

	public void setTitle(String sTitle) {
		m_sTitle = sTitle;
	}

	public String getURL() {
		return m_sURL;
	}

	public void setURL(String sUrl) {
		m_sURL = sUrl;
	}

	public String getContent() {
		return m_sContent;
	}

	public void setContent(String sContent) {
		m_sContent = sContent;
	}

	public String getPubDate() {
		return m_sPubDate;
	}

	public void setPubDate(String sPubDate) {
		m_sPubDate = sPubDate;
	}

	public String toString()
	{
		return "Title: " + m_sTitle + "\nURL: " + m_sURL + "\nPubDate: " + m_sPubDate + "\n" + m_sContent + "\n";
	}
}
