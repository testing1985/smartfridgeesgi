package SmartFridgeAPI;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Recipe {
	private String m_sName;
	
	// type of Recipe
	private int m_iType;
	
	Vector< RecipeStage > m_vRecipeStages = null;
	
	public Recipe()
	{
		m_sName = "Recette";
		m_iType = 0;
		
		m_vRecipeStages = new Vector< RecipeStage >();
		
		m_vRecipeStages.add( new RecipeStage() );
	}

	public Vector<RecipeStage> getM_RecipeStages() {
		return m_vRecipeStages;
	}

	public void setM_RecipeStages(Vector<RecipeStage> recipeStages) {
		m_vRecipeStages = recipeStages;
	}

	public String getM_sName() {
		return m_sName;
	}

	public void setM_sName(String name) {
		m_sName = name;
	}

	public int getM_iType() {
		return m_iType;
	}

	public void setM_iType(int type) {
		m_iType = type;
	}
	
	// returns the date that is amount days after Startdate 
	public static Date addDays( Date startDate ,int amount){
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime( startDate );
	    Calendar calendar2 = Calendar.getInstance();
	    calendar2.clear();
	    calendar2.set( calendar.get(calendar.YEAR), calendar.get(calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH) + amount );
	    return calendar2.getTime();
	}
}
