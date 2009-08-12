package SmartFridgeAPI;

import java.util.Vector;

public class SmartFridge {
	private Vector< Menu > 	  m_vMenus = null;
	private Vector< Aliment > m_vAliments = null;
	private Vector< Recipe >  m_vRecipes = null;
	
	public SmartFridge(){
		try{
			m_vAliments = new Vector<Aliment>();
			m_vMenus = new Vector<Menu>();
			m_vMenus.addElement( new Menu() );
			m_vRecipes = new Vector<Recipe>();
		}catch( Exception e ){
			e.printStackTrace();
		}
	}
	
	public Vector<Menu> getMenus() {
		return m_vMenus;
	}

	public void setMenus(Vector<Menu> menus) {
		m_vMenus = menus;
	}
	
	public void addRecipe( Recipe oR ) {
		m_vRecipes.add( oR );
	}
	
	public void addMenu( Menu oM ) {
		m_vMenus.add( oM );
	}

	public Vector<Aliment> getAliments() {
		return m_vAliments;
	}

	public void setAliments(Vector<Aliment> aliments) {
		m_vAliments = aliments;
	}

	public void printAliments()
	{
		int size = m_vAliments.size();
		for( int i = 0; i < size; i++ ){
			m_vAliments.elementAt( i ).print();
		}
	}
	
	public void printMenus()
	{
		int size = m_vMenus.size();
		for( int i = 0; i < size; i++ ){
			m_vMenus.elementAt( i ).print();
		}
	}
		
}
