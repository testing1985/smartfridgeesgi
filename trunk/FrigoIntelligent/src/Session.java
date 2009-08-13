import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import SmartFridgeAPI.Aliment;
import SmartFridgeAPI.Recipe;
import SmartFridgeAPI.RecipeStage;
import SmartFridgeAPI.SmartFridge;


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
	
	public short getID() {
		return m_iID;
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
	
	public void save( SmartFridge oSF ) {
		Connection oBDD = DBConnectionManager.getInstance().getConnection();
		
		try {
			
			Statement oSt = oBDD.createStatement();
			String sGetRecipeIDs = "" +
				 " SELECT idRecipe" +
				 " FROM Recipe" +
				 " WHERE userRecipe = " + m_iID;
			ResultSet oRS = oSt.executeQuery( sGetRecipeIDs );
			while( oRS.next() ) {
				int idRecipe = oRS.getInt("idRecipe");
				
				Statement oST2 = oBDD.createStatement();
				String sDelete = "" +
				" DELETE FROM RecipeStage" +
				" WHERE recipeStage = " + idRecipe;
				oST2.executeUpdate(sDelete);
				
				sDelete = "" +
				" DELETE FROM Aliment" +
				" WHERE recipeAliment = " + idRecipe;
				oST2.executeUpdate(sDelete);
				
				sDelete = "" +
				" DELETE FROM Recipe" +
				" WHERE idRecipe = " + idRecipe;
				oST2.executeUpdate(sDelete);
				oST2.close();
			}
			
			Vector<Recipe> vRecipes = oSF.getRecipes();
			
			for( int i = 0 ; i < vRecipes.size() ; i++ ) {
				Recipe oRecipe = vRecipes.elementAt(i);
				String sAddRecipe = "" +
					" INSERT INTO Recipe ( userRecipe , nameRecipe , typeRecipe )" +
					" VALUES( " + m_iID + " , '"+ oRecipe.getName() +"' , '"+ oRecipe.getType() +"' )";
				oSt.executeUpdate( sAddRecipe , Statement.RETURN_GENERATED_KEYS );
				ResultSet rsKey = oSt.getGeneratedKeys();
				if( rsKey.next() ) {
					int idRecipe = rsKey.getInt(1);
					
					Vector<Aliment> vAliments = oRecipe.getAliments();
					for( int j = 0 ; j < vAliments.size() ; j++ ) {
						Aliment oAliment = vAliments.elementAt(j);
						String sAddAliment = "" +
							" INSERT INTO Aliment ( userAliment , recipeAliment , nameAliment , quantityAliment , priceAliment , uniteAliment , peremptionAliment ) " +
							" VALUES( "  +
								m_iID    + "," + 
								idRecipe + "," +
								"'" + oAliment.getName()  + "'," + 
								oAliment.getQuantity()    + ","  +
								oAliment.getPrice() 	  + ","  +
								"'" + oAliment.getUnite() + "'," +
								oAliment.getPeremption()  +
							")";
						oSt.executeUpdate( sAddAliment );
					}
					
					Vector<RecipeStage> vStages = oRecipe.getRecipeStages();
					for( int j = 0 ; j < vStages.size() ; j++ ) {
						RecipeStage oStage = vStages.elementAt(j);
						String sAddStage = "" +
								"INSERT INTO RecipeStage VALUES("+(j+1)+" , "+idRecipe+" , "+oStage.getTime()+" , "+oStage.getDifficulty()+" , '"+oStage.getDescription()+"')";
						oSt.executeUpdate( sAddStage );
					}
					
				}
			}
												
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		DBConnectionManager.getInstance().closeConnection();
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
					" AND pwdUser = SHA1(\"" + sPasswd + "\")";
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
