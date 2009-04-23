import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Session {

	private short   m_iID;
	private boolean m_bIsConnected = false;
	private String  m_sLogin = "";
	private String  m_sPasswd = "";
	
	public Session(){}
	public Session( String sLogin , String sPasswd )
	{
		connect( sLogin , sPasswd );
	}
	
	public boolean connect( String sLogin , String sPasswd )
	{
		m_sLogin  = sLogin;
		m_sPasswd = sPasswd;
		short iID = Session.userExists( sLogin, sPasswd );
		
		if( iID != -1 )
		{
			m_iID = iID;
			m_bIsConnected = true;
		}
		
		return m_bIsConnected;
	}
	
	public static short userExists( String sLogin , String sPasswd )
	{
		Connection oBDD = DBConnectionManager.getInstance().getConnection();
		try {
			Statement oSt = oBDD.createStatement();
			String sReq = "" +
					" SELECT *" +
					" FROM User" +
					" WHERE loginUser = \"" + sLogin + "\"" +
					" AND pwdUser = SHA(\"" + sPasswd + "\")";
			ResultSet oRS = oSt.executeQuery(sReq);
			oRS.last();
			if( oRS.getRow() == 1 )
			{
				oRS.first();
				return oRS.getShort("idUser");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public boolean isConnected(){return m_bIsConnected;}
}
