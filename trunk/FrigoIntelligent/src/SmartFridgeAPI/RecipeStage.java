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
	
	public RecipeStage( int time, int difficulty, Vector< Aliment > aliments, String description ){
		m_iTime = time;
		m_iDifficulty = difficulty;
		m_vAliments = aliments;
		m_sDescription = description;
	}

	public int getTime(){
		return m_iTime;
	}

	public void setTime( int time ){
		m_iTime = time;
	}

	public int getDifficulty() {
		return m_iDifficulty;
	}

	public void setDifficulty( int difficulty ){
		m_iDifficulty = difficulty;
	}

	public Vector<Aliment> getAliments(){
		return m_vAliments;
	}

	public void setAliments( Vector<Aliment> aliments ){
		m_vAliments = aliments;
	}

	public String getDescription(){
		return m_sDescription;
	}

	public void setDescription( String description ){
		m_sDescription = description;
	}
	
	public void print()
	{
		System.out.println( "This stage takes " + m_iTime + " minutes and its difficulty is " + m_iDifficulty );
		System.out.println( m_sDescription );
	}
}
