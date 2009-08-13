import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import SmartFridgeAPI.Recipe;
import SmartFridgeAPI.RecipeStage;

@SuppressWarnings( "serial" )
public class SFSeeRecipePanel extends JPanel implements ActionListener {

	SFWindow m_oParent;
	JButton m_oGoBackButton = new JButton( "Retour" );
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
		
		m_oGoBackButton.addActionListener( this );
		add( m_oGoBackButton , BorderLayout.SOUTH );
	}
	
	public void ChangeID( String s ){
		m_iID = Integer.parseInt( s );
	}
	
	public void Refresh(){
		Recipe r = m_oParent.m_oParent.m_oSmartFridge.getRecipes().elementAt( m_iID );
		m_oTitle.setText( r.getType() + " : " + r.getName() );
		
		m_oDescription.setText( "" );
		int size = r.getRecipeStages().size();
		for( int i = 0; i < size; i++ ){
			RecipeStage rs = r.getRecipeStages().elementAt( i );
			
			System.out.println( String.valueOf( rs.getDifficulty() ) );
			m_oDescription.append( "Etape " + ( i + 1 ) + "\n"  ) ;
			m_oDescription.append( "Difficulté " + rs.getDifficulty() + "\n"  );
			m_oDescription.append( rs.getDescription() + "\n" );
			m_oDescription.append( "\n" + "\n" );
		}
	}
	
	@Override
	public void actionPerformed( ActionEvent e ){
		if( e.getSource().equals( m_oGoBackButton ) ){	
			m_oParent.SeeRecipeAction();
		}
	}

}

