import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import SmartFridgeAPI.Aliment;
import SmartFridgeAPI.Menu;
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
	
	public void load( SmartFridge oSF ) {
		Connection oBDD = DBConnectionManager.getInstance().getConnection();
				
		try {
			Statement oST = oBDD.createStatement();
			String sGetRecipe = "SELECT * FROM Recipe WHERE userRecipe = " + m_iID;
			ResultSet oRS = oST.executeQuery( sGetRecipe );
			int i = 0;
			while( oRS.next() ) {
				Recipe oRecipe = new Recipe();
				int idRecipe = oRS.getInt("idRecipe");
				oRecipe.setID(i++);
				oRecipe.setName( oRS.getString("nameRecipe") );
				oRecipe.setType( oRS.getString("typeRecipe") );
				Vector<RecipeStage> vStages = new Vector<RecipeStage>();
				Vector<Aliment> vAliments = new Vector<Aliment>();
				
				Statement oST2 = oBDD.createStatement();
				String sGetAliments = "SELECT * FROM Aliment WHERE recipeAliment = " + idRecipe + " AND userAliment = " + m_iID;
				ResultSet oAlimentRS = oST2.executeQuery( sGetAliments );
				while( oAlimentRS.next() ) {
					Aliment oAliment = new Aliment();
					oAliment.setName( oAlimentRS.getString("nameAliment") );
					oAliment.setQuantity( oAlimentRS.getInt("quantityAliment") );
					oAliment.setUnite( oAlimentRS.getString("uniteAliment") );
					vAliments.add( oAliment );
				}
				
				String sGetStages = "SELECT * FROM RecipeStage WHERE recipeStage = " + idRecipe;
				ResultSet oStagesRS = oST2.executeQuery( sGetStages );
				oStagesRS.last();
				int iNbStages = oStagesRS.getRow();
				oStagesRS.beforeFirst();
				vStages.setSize( iNbStages );
				
				while( oStagesRS.next() ) {
					RecipeStage oStage = new RecipeStage();
					oStage.setDescription( oStagesRS.getString("descStage") );
					oStage.setDifficulty( oStagesRS.getInt("difficultyStage") );
					oStage.setTime( oStagesRS.getInt("timeStage") );
					vStages.setElementAt(oStage, oStagesRS.getInt("idStage") - 1 );
				}
				
				oRecipe.setAliments(vAliments);
				oRecipe.setRecipeStages(vStages);
				
				oSF.addRecipe( oRecipe );
			}
			
			String sGetMenu = "SELECT * FROM Menu WHERE userMenu = " + m_iID;
			oRS = oST.executeQuery( sGetMenu );
			while( oRS.next() ) {
				Menu oMenu = new Menu();
				oMenu.setName( oRS.getString( "nameMenu" ) );
				String sRecipeList = oRS.getString( "recipesMenu" );
				String[] lRecipeIndexList = sRecipeList.split("-");
				int[] vRecipeList = new int[lRecipeIndexList.length];
				for( int j = 0 ; j < vRecipeList.length ; j++ ) {
					vRecipeList[j] = Integer.parseInt( lRecipeIndexList[j] );
				}
				oMenu.setListRecipeID( vRecipeList );
				oSF.addMenu( oMenu );
			}
			
			oSF.createMenusFromIDs();
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		DBConnectionManager.getInstance().closeConnection();
	}
	
	public void save( SmartFridge oSF ) {
		Connection oBDD = DBConnectionManager.getInstance().getConnection();
		
		try {
			
			Statement oST = oBDD.createStatement();
			String sGetRecipeIDs = "" +
				 " SELECT idRecipe" +
				 " FROM Recipe" +
				 " WHERE userRecipe = " + m_iID;
			ResultSet oRS = oST.executeQuery( sGetRecipeIDs );
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
			
			String sDeleteMenus = "DELETE FROM Menu WHERE userMenu = " + m_iID;
			oST.executeUpdate( sDeleteMenus );
			
			Vector<Recipe> vRecipes = oSF.getRecipes();
			
			for( int i = 0 ; i < vRecipes.size() ; i++ ) {
				Recipe oRecipe = vRecipes.elementAt(i);
				String sAddRecipe = "INSERT INTO Recipe ( userRecipe , nameRecipe , typeRecipe ) VALUES( ?,?,? )";
				PreparedStatement oPST = oBDD.prepareStatement( sAddRecipe , Statement.RETURN_GENERATED_KEYS );
				oPST.setShort (1, m_iID);
				oPST.setString(2, oRecipe.getName());
				oPST.setString(3, oRecipe.getType());
				oPST.executeUpdate();
				ResultSet rsKey = oPST.getGeneratedKeys();
				if( rsKey.next() ) {
					int idRecipe = rsKey.getInt(1);
					
					Vector<Aliment> vAliments = oRecipe.getAliments();
					for( int j = 0 ; j < vAliments.size() ; j++ ) {
						Aliment oAliment = vAliments.elementAt(j);
						String sAddAliment = "INSERT INTO Aliment ( userAliment , recipeAliment , nameAliment , quantityAliment , uniteAliment ) VALUES (?,?,?,?,?)";
						oPST = oBDD.prepareStatement(sAddAliment);
						oPST.setShort (1, m_iID);
						oPST.setInt   (2, idRecipe);
						oPST.setString(3, oAliment.getName());
						oPST.setInt   (4, oAliment.getQuantity());
						oPST.setString(5, oAliment.getUnite());
						oPST.executeUpdate();
					}
					
					Vector<RecipeStage> vStages = oRecipe.getRecipeStages();
					for( int j = 0 ; j < vStages.size() ; j++ ) {
						RecipeStage oStage = vStages.elementAt(j);
						String sAddStage = "INSERT INTO RecipeStage ( idStage , recipeStage , timeStage , difficultyStage , descStage ) VALUES(?,?,?,?,?)";
						oPST = oBDD.prepareStatement(sAddStage);
						oPST.setInt(1, j+1);
						oPST.setInt(2, idRecipe);
						oPST.setInt(3, oStage.getTime());
						oPST.setInt(4, oStage.getDifficulty());
						oPST.setString(5, oStage.getDescription());
						oPST.executeUpdate();
					}
					
				}
			}
			
			Vector<Menu> vMenus = oSF.getMenus();
			for( int i = 0 ; i < vMenus.size() ; i++ ) {
				Menu oMenu = vMenus.elementAt(i);
				String sRecipeIndexList = "";
				int[] vIndexList = oMenu.getListRecipeID();
				for( int j = 0 ; j < vIndexList.length ; j++ ) {
					sRecipeIndexList += vIndexList[j] + "-";
				}
				
				String sAddMenu = "INSERT INTO Menu( userMenu , recipesMenu , nameMenu ) VALUES (?,?,?)";
				PreparedStatement oPST = oBDD.prepareStatement( sAddMenu );
				oPST.setInt( 1 , m_iID );
				oPST.setString( 2 , sRecipeIndexList );
				oPST.setString(3, oMenu.getName() );
				oPST.executeUpdate();
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
