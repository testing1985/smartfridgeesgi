import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import SmartFridgeAPI.Aliment;
import SmartFridgeAPI.Menu;
import SmartFridgeAPI.Recipe;
import SmartFridgeAPI.RecipeStage;

@SuppressWarnings( "serial" )
public class SFSeeMenuPanel extends JPanel{

	SFWindow m_oParent;
	int m_iID;
	
	JScrollPane m_oScrollPane = new JScrollPane();
	JPanel m_oCenterPanel = new JPanel();
	JLabel m_oTitle = new JLabel();
	JTextArea m_oDescription = new JTextArea();
	
	public SFSeeMenuPanel( SFWindow oParent ){
		super( new BorderLayout() );
		m_oParent = oParent;
		setPreferredSize( new Dimension( 500 , 500 ) );
		
		m_oCenterPanel.setLayout( new BorderLayout() );
				
		m_oDescription.setEditable( false );
		
		m_oCenterPanel.add( m_oDescription, BorderLayout.CENTER );
		
		m_oScrollPane = new JScrollPane( m_oCenterPanel );
		add( m_oTitle, BorderLayout.NORTH );
		add( m_oScrollPane, BorderLayout.CENTER );
		
	}
	
	public void ChangeID( String s ){
		m_iID = Integer.parseInt( s );
	}
	
	public void Refresh(){
	
			Menu m = m_oParent.m_oSmartFridge.getMenus().elementAt( m_iID - 1 );
			
			m_oTitle.setText( m.getName() );
			
			m_oDescription.setText( "" );
			int size = m.getRecipes().size();
			for( int i = 0; i < size; i++ ){
				Recipe r = m.getRecipes().elementAt( i );
				m_oDescription.append( r.getName() + "\n"  ) ;
				
				int AlimentSize = r.getAliments().size();
				for( int j = 0; j < AlimentSize; j++ ){
					Aliment a = r.getAliments().elementAt( j );
					m_oDescription.append( a.getQuantity() + " " + a.getUnite() + " : " + a.getName() + "\n" );
				}
				
				m_oDescription.append( "\n" );
				
				
				int recipeStages = r.getRecipeStages().size();
				for( int j = 0; j < recipeStages; j++ ){
					RecipeStage rs = r.getRecipeStages().elementAt( j ); 
					m_oDescription.append( "Etape " + ( j + 1 ) + "\n"  ) ;
					m_oDescription.append( "Difficulté " + rs.getDifficulty() + "\n"  );
					m_oDescription.append( rs.getDescription() + "\n" );
					m_oDescription.append( "\n" + "\n" );	
				}
				m_oDescription.append( "--------------------------------------------------\n" );
			}
		
	}
	
}

