package SmartFridgeAPI;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Recipe {
	private String m_sName;
	private String m_sType;
	
	Vector< RecipeStage > m_vRecipeStages = null;
	Vector< Aliment >     m_vAliments 	  = null;
	
	public Recipe()
	{
		m_sName = "";
		m_sType = "";
		m_vRecipeStages = new Vector< RecipeStage >();
		m_vAliments 	= new Vector< Aliment >();
	}

	public Recipe( String sName, String sType, Vector< RecipeStage > vRecipeStages , Vector<Aliment> vAliments ){
		m_sName = sName;
		m_sType = sType;
		
		m_vRecipeStages = vRecipeStages;
		m_vAliments     = vAliments;
	}
	
	public Vector< Aliment > getAliments() {
		return m_vAliments;
	}
	
	public void setAliments( Vector< Aliment > vAliments ) {
		m_vAliments = vAliments;
	}
	
	public Vector<RecipeStage> getRecipeStages(){
		return m_vRecipeStages;
	}

	public void setRecipeStages( Vector<RecipeStage> recipeStages ){
		m_vRecipeStages = recipeStages;
	}
	
	public int getTime(){
		int iEtape = m_vRecipeStages.size();
		int iDuree = 0;
		for( int i = 0; i < iEtape; i++ ){
			iDuree += m_vRecipeStages.elementAt( i ).getTime();	
		}
		return iDuree;
	}
	
	public int getDifficulty(){
		int iEtape = m_vRecipeStages.size();
		int iDifficulte = 0;
		for( int i = 0; i < iEtape; i++ ){
			if( m_vRecipeStages.elementAt( i ).getDifficulty() > iDifficulte )
				iDifficulte = m_vRecipeStages.elementAt( i ).getDifficulty();
		}
		return iDifficulte;
	}
	

	public String getName(){
		return m_sName;
	}

	public void setName( String sName ){
		m_sName = sName;
	}

	public String getType(){
		return m_sType;
	}

	public void setType( String sType ){
		m_sType = sType;
	}
	
	// returns the date that is amount days after Startdate 
	public static Date addDays( Date startDate ,int amount ){
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime( startDate );
	    Calendar calendar2 = Calendar.getInstance();
	    calendar2.clear();
	    calendar2.set( calendar.get(calendar.YEAR), calendar.get(calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH) + amount );
	    return calendar2.getTime();
	}
	
	public void print(){
		System.out.println( "Recipe type is " + m_sType ) ;
		int size = m_vRecipeStages.size();
		for( int i = 0; i < size; i++ ){
			System.out.println( "Stage " + i ) ;
			m_vRecipeStages.elementAt( i ).print();
		}
	}
}
