package SmartFridgeAPI;

import java.util.Vector;

public class Menu {
	private String m_sName;	
	private int[] 		   m_vTempRecipeID = null;
	private Vector<Recipe> m_vRecipes 	   = null;
	
	public Menu(){
		m_sName 		= "Menu";
		m_vRecipes 	    = new Vector<Recipe>();		
		m_vRecipes.add( new Recipe() );
	}

	public Menu( String name, Vector<Recipe> recipes ){
		m_sName    = name;
		m_vRecipes = recipes;
	}

	public void setListRecipeID( int[] vList ) {
		m_vTempRecipeID = vList;
	}
	
	public void setRecipeListFromIDs( Vector<Recipe> vRecipes ) {
		m_vRecipes = new Vector<Recipe>();
		for( int i = 0 ; i < m_vTempRecipeID.length ; i++ ) {
			m_vRecipes.add( vRecipes.elementAt(m_vTempRecipeID[i]-1) );
		}
	}
	
	public int[] getListRecipeID() {
		int[] vIDs = new int[m_vRecipes.size()];
		for( int i = 0 ; i < vIDs.length ; i++ ) {
			vIDs[i] = m_vRecipes.elementAt(i).getID()+1;
		}
		return vIDs;
	}
	
	public String getName() {
		return m_sName;
	}

	public void setName(String name){
		m_sName = name;
	}

	public Vector<Recipe> getRecipes(){
		return m_vRecipes;
	}

	public void setRecipes(Vector<Recipe> recipes){
		m_vRecipes = recipes;
	}
	
	public void print(){
		System.out.println( m_sName ) ;
		int size = m_vRecipes.size();
		for( int i = 0; i < size; i++ ){
			m_vRecipes.elementAt( i ).print();
		}
	}
}
