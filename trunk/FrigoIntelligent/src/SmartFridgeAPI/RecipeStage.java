package SmartFridgeAPI;

import java.util.Vector;

public class RecipeStage {
	private int m_iTime; 
	
	private int m_iDifficulty;
	
	private Vector< Aliment > m_vAliments;
	
	private String m_sDescription;
	
	public RecipeStage()
	{
		m_iTime = 1;
		m_iDifficulty = 0;
		
		m_vAliments = new Vector< Aliment >();
		m_vAliments.add( new Aliment( "Oeuf" ) );
		m_sDescription = "Cassez l'oeuf dans un bol";
	}
	
	public RecipeStage( int time, int difficulty, Vector< Aliment > aliments, String description  )
	{
		m_iTime = time;
		m_iDifficulty = difficulty;
		m_vAliments = aliments;
		m_sDescription = description;
	}

	public int getM_iTime() {
		return m_iTime;
	}

	public void setM_iTime(int time) {
		m_iTime = time;
	}

	public int getM_iDifficulty() {
		return m_iDifficulty;
	}

	public void setM_iDifficulty(int difficulty) {
		m_iDifficulty = difficulty;
	}

	public Vector<Aliment> getM_Aliments() {
		return m_vAliments;
	}

	public void setM_Aliments(Vector<Aliment> aliments) {
		m_vAliments = aliments;
	}

	public String getM_sDescription() {
		return m_sDescription;
	}

	public void setM_sDescription(String description) {
		m_sDescription = description;
	}
}
