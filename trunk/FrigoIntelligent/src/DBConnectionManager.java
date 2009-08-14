
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

   private static DBConnectionManager m_oInstance;
   
   private String 	  m_sURL    = "";
   private String 	  m_sUser   = "";
   private String 	  m_sPasswd = "";
   private Connection m_oConnection = null;

   public static DBConnectionManager getInstance() {
       if (null == m_oInstance) {
           m_oInstance = new DBConnectionManager();
       }
       return m_oInstance;
   }
   
   private DBConnectionManager() {}
   
   public void setURL   (String sURL)   {m_sURL    = sURL;}
   public void setUser  (String sUser)  {m_sUser   = sUser;}
   public void setPasswd(String sPasswd){m_sPasswd = sPasswd;}
   public void setConnectionData(String sURL , String sUser , String sPasswd)
   {
	   setURL(sURL);
	   setUser(sUser);
	   setPasswd(sPasswd);
   }
   
   public Connection getConnection()
   {
	   if( m_oConnection != null )
		   return m_oConnection;
	   
	   try {
		   return DriverManager.getConnection( m_sURL , m_sUser , m_sPasswd );
	   } catch (SQLException e) {
		   System.err.println("Erreur de connexion MySql: " + e.getMessage());
		   return null;
	  }
   }
   
   public void closeConnection()
   {
	   if( m_oConnection != null )
	   {
		   try {
			   m_oConnection.close();
		   } catch (SQLException e) {
			   e.printStackTrace();
		   }
		   
		   m_oConnection = null;
	   }
   }
}
