package SmartFridgeAPI;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Recipe {
	private String m_sName;
	
	// type of Recipe
	private String m_iType;
	
	Vector< RecipeStage > m_vRecipeStages = null;
	
	public Recipe()
	{
		m_sName = "Recette";
		m_iType = "Entrée";
		
		m_vRecipeStages = new Vector< RecipeStage >();
		
		m_vRecipeStages.add( new RecipeStage() );
	}

	public Recipe( String name, String type, Vector< RecipeStage > RecipeStages ){
		m_sName = name;
		m_iType = type;
		
		m_vRecipeStages = RecipeStages;
		
	}
	
	public Vector<RecipeStage> getRecipeStages() {
		return m_vRecipeStages;
	}

	public void setRecipeStages( Vector<RecipeStage> recipeStages ) {
		m_vRecipeStages = recipeStages;
	}

	public String getName() {
		return m_sName;
	}

	public void setName( String name ) {
		m_sName = name;
	}

	public String getType() {
		return m_iType;
	}

	public void setType( String type ) {
		m_iType = type;
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
	
	public void print()
	{
		System.out.println( "Recipe type is " + m_iType ) ;
		int size = m_vRecipeStages.size();
		for( int i = 0; i < size; i++ ){
			System.out.println( "Stage " + i ) ;
			m_vRecipeStages.elementAt( i ).print();
		}
	}
}
