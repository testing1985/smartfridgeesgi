import java.awt.BorderLayout;
import java.awt.Choice;
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

import SmartFridgeAPI.Aliment;
import SmartFridgeAPI.Recipe;
import SmartFridgeAPI.RecipeStage;


public class SFAddRecipePanel extends JPanel implements ActionListener {
	
	SFWindow m_oParent;
	Vector< SFAddRecipeStageFormPanel > m_lStageFormList = new Vector< SFAddRecipeStageFormPanel >();
	JPanel 	   m_oStageListPanel 	  = new JPanel	 ();	
	Choice     m_oAddRecipeType		  = new Choice 	 ();
	JTextField m_oNewRecipeTitle      = new JTextField( 40 );	
	JButton    m_oAddStageFormBt      = new JButton	 ( "Ajouter une étape" );
	JButton    m_oAddValidRecipeBt    = new JButton	 ( "Valider la recette");
	
	
	public SFAddRecipePanel( SFWindow oParent ) {
		super( new BorderLayout() );
		m_oParent = oParent;
		setPreferredSize( new Dimension( 500 , 500 ));
		
		JPanel oNorth = new JPanel();
		oNorth.add( new JLabel( "Ajout d'une recette :   " ) );
		m_oAddRecipeType.add("Entrée");
		m_oAddRecipeType.add("Plat");
		m_oAddRecipeType.add("Dessert");
		m_oAddRecipeType.add("Boisson");
		m_oAddRecipeType.add("Amuse-gueule");
		oNorth.add(m_oAddRecipeType);		
		add( oNorth , BorderLayout.NORTH );
		
		JPanel oCenter = new JPanel( new BorderLayout() );
		
		JPanel oTitlePanel = new JPanel();
		oTitlePanel.add( new JLabel( "Titre :" ) );
		oTitlePanel.add( m_oNewRecipeTitle );
		oCenter.add( oTitlePanel , BorderLayout.NORTH );
		
		m_oAddStageFormBt.addActionListener( this );
		m_oAddValidRecipeBt.addActionListener( this );
		
		JPanel oStagesAndButtonsPanel = new JPanel( new BorderLayout() );
		JPanel oButtonPanel    = new JPanel( new GridLayout(1,2));
		oButtonPanel.add( m_oAddValidRecipeBt );
		oButtonPanel.add( m_oAddStageFormBt );		
		oStagesAndButtonsPanel.add( oButtonPanel , BorderLayout.NORTH );
		
		m_lStageFormList.addElement( new SFAddRecipeStageFormPanel (1) );
		m_oStageListPanel.setLayout( new BoxLayout( m_oStageListPanel , BoxLayout.Y_AXIS ) );
		m_oStageListPanel.add( m_lStageFormList.elementAt(0) );
		oStagesAndButtonsPanel.add( new JScrollPane( m_oStageListPanel , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) , BorderLayout.CENTER );
		oCenter.add( oStagesAndButtonsPanel , BorderLayout.CENTER );		
		add( oCenter , BorderLayout.CENTER );
	}

	
	public void actionPerformed(ActionEvent e) {
		if( e.getSource().equals( m_oAddStageFormBt ) ) {
			m_lStageFormList.addElement( new SFAddRecipeStageFormPanel ( m_lStageFormList.size() + 1 ) );
			m_oStageListPanel.add( m_lStageFormList.lastElement() );
			super.repaint();
		}
		
		else if( e.getSource().equals( m_oAddValidRecipeBt ) ) {
			Vector<RecipeStage> v = new Vector<RecipeStage>();
			Vector<Aliment> vA 	  = new Vector<Aliment>();
			for( int i = 0 ; i < m_lStageFormList.size() ; i++ ) {
				SFAddRecipeStageFormPanel o = m_lStageFormList.elementAt(i);
				v.add( new RecipeStage(Integer.parseInt(o.m_oDuree.getText()) , Integer.parseInt(o.m_oDifficulte.getSelectedItem()) , o.m_oContent.getText()));
			}
			m_oParent.m_oParent.m_oSmartFridge.addRecipe( new Recipe( m_oNewRecipeTitle.getText() , m_oAddRecipeType.getSelectedItem() , v , vA ) );
			m_oParent.listRecipeAction();
		}	
	}
}
