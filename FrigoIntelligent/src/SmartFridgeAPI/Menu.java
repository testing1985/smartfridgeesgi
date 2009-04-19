package SmartFridgeAPI;

import java.util.Vector;

public class Menu {
	private String m_sName;
	
	private Vector<Recipe> m_vRecipes = null;
	
	public Menu()
	{
		m_sName = "Menu";
		
		m_vRecipes = new Vector<Recipe>();		
		m_vRecipes.add( new Recipe() );
	}

	public Menu( String name, Vector<Recipe> recipes )
	{
		m_sName = name;
		m_vRecipes = recipes;
	}

	public String getM_sName() {
		return m_sName;
	}

	public void setM_sName(String name) {
		m_sName = name;
	}

	public Vector<Recipe> getM_Recipes() {
		return m_vRecipes;
	}

	public void setM_Recipes(Vector<Recipe> recipes) {
		m_vRecipes = recipes;
	}
}
