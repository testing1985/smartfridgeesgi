import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import SmartFridgeAPI.Aliment;
import SmartFridgeAPI.Recipe;
import SmartFridgeAPI.RecipeStage;

@SuppressWarnings( "serial" )
public class SFSeeRecipePanel extends JPanel{

	SFWindow m_oParent;
	int m_iID;
	JScrollPane m_oScrollPane = new JScrollPane();
	JPanel m_oCenterPanel = new JPanel();
	JLabel m_oTitle = new JLabel();
	JTextArea m_oDescription = new JTextArea();
	
	public SFSeeRecipePanel( SFWindow oParent ){
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
		//m_iID--;
	}
	
	public void Refresh(){
		Recipe r = m_oParent.m_oParent.m_oSmartFridge.getRecipes().elementAt( m_iID - 1 );
		m_oTitle.setText( r.getType() + " : " + r.getName() );
		
		m_oDescription.setText( "" );
		
		int AlimentSize = r.getAliments().size();
		for( int i = 0; i < AlimentSize; i++ ){
			Aliment a = r.getAliments().elementAt( i );
			m_oDescription.append( a.getQuantity() + " " + a.getUnite() + " : " + a.getName() + "\n" );
		}
		
		m_oDescription.append( "\n" );
		int size = r.getRecipeStages().size();
		for( int i = 0; i < size; i++ ){
			RecipeStage rs = r.getRecipeStages().elementAt( i );
			
			m_oDescription.append( "Etape " + ( i + 1 ) + "\n"  ) ;
			m_oDescription.append( "Difficulté " + rs.getDifficulty() + "\n"  );
			m_oDescription.append( rs.getDescription() + "\n" );
			m_oDescription.append( "\n" + "\n" );
		}
	}
	
}

