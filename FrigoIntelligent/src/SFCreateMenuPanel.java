import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import SmartFridgeAPI.Menu;
import SmartFridgeAPI.Recipe;


@SuppressWarnings( "serial" )
public class SFCreateMenuPanel extends JPanel implements ActionListener {

	Vector<SFAddRecipeToMenuPanel> m_lRecipePanelList = null;
	
	JPanel     m_oRecipeList  = new JPanel();
	SFWindow   m_oParent 	  = null;
	JTextField m_oName   	  = new JTextField( 15 );
	JButton    m_oAddRecipeBt = new JButton( "Ajouter une recette" );
	JButton    m_oValidMenuBt = new JButton( "Ajouter le menu" );	
	JButton    m_oAutoBt      = new JButton( "Menu automatique" );

	public SFCreateMenuPanel( SFWindow oParent ) {
		
		super( new BorderLayout() );
		m_oParent = oParent;
		
		setPreferredSize( new Dimension( 500 , 500 ));
		m_oRecipeList.setLayout( new BoxLayout( m_oRecipeList , BoxLayout.Y_AXIS ) );
		m_oAddRecipeBt.addActionListener( this );
		m_oValidMenuBt.addActionListener( this );
		m_oAutoBt.addActionListener( this );
		
		reset();
		
		JPanel oNorth = new JPanel( new GridLayout(2,1) );
		
		JPanel oNorthTitle = new JPanel();
		oNorthTitle.add( new JLabel( "Création d'un menu :   " ) );
		oNorthTitle.add( m_oName );
		oNorth.add( oNorthTitle );
		
		JPanel oNorthButtons = new JPanel();
		oNorthButtons.add( m_oValidMenuBt );
		oNorthButtons.add( m_oAddRecipeBt );
		oNorthButtons.add( m_oAutoBt );
		oNorth.add( oNorthButtons );
		
		JPanel oCenter = new JPanel( new BorderLayout() );
		oCenter.add( m_oRecipeList , BorderLayout.NORTH );
		
		add( oNorth  , BorderLayout.NORTH );
		add( new JScrollPane( oCenter ) , BorderLayout.CENTER );
		add( new JPanel() , BorderLayout.SOUTH );
		
	}
	
	public void reset() {
		m_oName.setText("Son nom...");
		
		m_lRecipePanelList = new Vector<SFAddRecipeToMenuPanel>();
		m_lRecipePanelList.addElement( new SFAddRecipeToMenuPanel ( m_oParent.m_oParent.m_oSmartFridge , 1 ) );
		m_oRecipeList.removeAll();
		m_oRecipeList.add( m_lRecipePanelList.firstElement() );
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// Ajout d'une recette dans le menu
		if( e.getSource().equals( m_oAddRecipeBt ) ) {
			m_lRecipePanelList.addElement( new SFAddRecipeToMenuPanel ( m_oParent.m_oParent.m_oSmartFridge , m_lRecipePanelList.size() + 1 ) );
			m_oRecipeList.add( m_lRecipePanelList.lastElement() );
			super.revalidate();		
			m_oParent.repaint();
		}
		
		// Ajout du menu dans le SmartFridge
		else if( e.getSource().equals( m_oValidMenuBt ) ) {
			if( ! m_oParent.m_oParent.m_oSmartFridge.getRecipes().isEmpty() ) {
				Menu oMenu = new Menu();
				oMenu.setName( m_oName.getText() );
			
				Vector<Recipe> vRecipes = new Vector<Recipe>();
				for( int i = 0 ; i < m_lRecipePanelList.size() ; i++ ) {
					vRecipes.add( m_oParent.m_oParent.m_oSmartFridge.getRecipes().elementAt( m_lRecipePanelList.elementAt(i).m_oRecipe.getSelectedIndex() ) );
				}
				oMenu.setRecipes( vRecipes );
				m_oParent.m_oParent.m_oSmartFridge.addMenu( oMenu );
				m_oParent.SeeMenuAction();
			}
		}
		
		else if( e.getSource().equals( m_oAutoBt ) ) {
			
		}
	}

}
