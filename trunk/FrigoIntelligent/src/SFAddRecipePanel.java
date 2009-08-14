import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import SmartFridgeAPI.Aliment;
import SmartFridgeAPI.Recipe;
import SmartFridgeAPI.RecipeStage;


public class SFAddRecipePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 2929677289590570865L;
	
	SFWindow m_oParent;
	Vector< SFAddRecipeStageFormPanel >   m_lStageFormList   = null;
	Vector< SFAddRecipeAlimentFormPanel > m_lAlimentFormList = null;
	JPanel 	   m_oStageListPanel 	  = new JPanel	  ();	
	JPanel 	   m_oAlimentListPanel    = new JPanel    ();
	JComboBox  m_oAddRecipeType		  = new JComboBox ();
	JTextField m_oNewRecipeTitle      = null;	
	JButton    m_oAddStageBt      	  = new JButton	  ( "Ajouter une étape"  );
	JButton    m_oAddValidRecipeBt    = new JButton	  ( "Valider la recette" );
	JButton    m_oAddAlimentBt		  = new JButton   ( "Ajouter un ingrédient" );
	JButton    m_oResetFormBt		  = new JButton   ( "Reset" );
	
	
	public SFAddRecipePanel( SFWindow oParent ) {
		super( new BorderLayout() );
		m_oParent = oParent;
		setPreferredSize( new Dimension( 500 , 500 ));
		
		m_oAlimentListPanel.setLayout( new BoxLayout( m_oAlimentListPanel , BoxLayout.Y_AXIS ) );
		m_oStageListPanel.setLayout  ( new BoxLayout( m_oStageListPanel   , BoxLayout.Y_AXIS ) );
		
		m_oNewRecipeTitle  = new JTextField( "Ma recette..." , 40 );
		
		reset();
		
		JPanel oNorth = new JPanel();
		oNorth.add( new JLabel( "Ajout d'une recette :   " ) );
		m_oAddRecipeType.addItem("Entrée");
		m_oAddRecipeType.addItem("Plat");
		m_oAddRecipeType.addItem("Dessert");
		m_oAddRecipeType.addItem("Boisson");
		m_oAddRecipeType.addItem("Amuse-gueule");
		oNorth.add(m_oAddRecipeType);
		oNorth.add(m_oResetFormBt );
		add( oNorth , BorderLayout.NORTH );
		
		JPanel oCenter = new JPanel( new BorderLayout() );
		
		JPanel oTitleAndButtonPanel = new JPanel( new GridLayout( 2 , 1 ) );
		JPanel oTitlePanel = new JPanel();
		oTitlePanel.add( new JLabel( "Titre :" ) );
		oTitlePanel.add( m_oNewRecipeTitle );
		oTitleAndButtonPanel.add( oTitlePanel );		
		
		// BEGIN - Buttons
		m_oAddStageBt.addActionListener( this );
		m_oAddValidRecipeBt.addActionListener( this );
		m_oAddAlimentBt.addActionListener( this );		
		m_oResetFormBt.addActionListener(this);
		
		JPanel oButtonPanel    = new JPanel( new GridLayout(1,2));
		oButtonPanel.add( m_oAddAlimentBt );
		oButtonPanel.add( m_oAddValidRecipeBt );
		oButtonPanel.add( m_oAddStageBt );
		oTitleAndButtonPanel.add( oButtonPanel );
		// END - Buttons
		
		oCenter.add( oTitleAndButtonPanel , BorderLayout.NORTH );
		
		JPanel oStagesAndAlimentsPanel = new JPanel( new BorderLayout() );		
		
		oStagesAndAlimentsPanel.add( new JScrollPane( m_oAlimentListPanel ) , BorderLayout.NORTH );		
		oStagesAndAlimentsPanel.add( new JScrollPane( m_oStageListPanel ) , BorderLayout.CENTER );		
		oCenter.add( new JScrollPane( oStagesAndAlimentsPanel ) , BorderLayout.CENTER );		
		
		add( oCenter , BorderLayout.CENTER );
	}
	
	public void reset() {
		m_oAddRecipeType.setSelectedItem("Entrée");
		m_oNewRecipeTitle.setText( "Ma recette..." );
		
		m_lStageFormList   = new Vector< SFAddRecipeStageFormPanel >();
		m_lStageFormList.addElement( new SFAddRecipeStageFormPanel (1) );
		m_oStageListPanel.removeAll();
		m_oStageListPanel.add( m_lStageFormList.firstElement() );
		
		m_lAlimentFormList = new Vector<SFAddRecipeAlimentFormPanel >();
		m_lAlimentFormList.addElement( new SFAddRecipeAlimentFormPanel( 1 ) );
		m_oAlimentListPanel.removeAll();
		m_oAlimentListPanel.add( m_lAlimentFormList.firstElement() );
	}

	
	public void actionPerformed(ActionEvent e) {
		
		// Ajout d'une étape
		if( e.getSource().equals( m_oAddStageBt ) ) {
			m_lStageFormList.addElement( new SFAddRecipeStageFormPanel ( m_lStageFormList.size() + 1 ) );
			m_oStageListPanel.add( m_lStageFormList.lastElement() );
			super.revalidate();		
			m_oParent.repaint();
		}
		
		// Ajout d'un aliment
		else if( e.getSource().equals( m_oAddAlimentBt ) ) {
			m_lAlimentFormList.addElement( new SFAddRecipeAlimentFormPanel( m_lAlimentFormList.size() + 1 ) );
			m_oAlimentListPanel.add( m_lAlimentFormList.lastElement() );
			super.revalidate();
			m_oParent.repaint();
		}
		
		else if( e.getSource().equals(m_oResetFormBt ) ) {
			m_oParent.newRecipeAction();
		}
		
		// Validation de la recette
		else if( e.getSource().equals( m_oAddValidRecipeBt ) ) {
			Vector<RecipeStage> vStages   = new Vector<RecipeStage>();
			Vector<Aliment> 	vAliments = new Vector<Aliment>();
			
			for( int i = 0 ; i < m_lStageFormList.size() ; i++ ) {
				SFAddRecipeStageFormPanel o = m_lStageFormList.elementAt(i);
				vStages.add( new RecipeStage(Integer.parseInt(o.m_oDuree.getText()) , Integer.parseInt((String)o.m_oDifficulte.getSelectedItem()) , o.m_oContent.getText()));
			}
			
			for( int i = 0 ; i < m_lAlimentFormList.size() ; i++ ) {
				SFAddRecipeAlimentFormPanel o = m_lAlimentFormList.elementAt(i);
				vAliments.add( new Aliment(o.m_oName.getText(), Integer.parseInt( o.m_oQuantity.getText() ) , 0, 0, (String)o.m_oUnite.getSelectedItem() ));
			}
			
			m_oParent.m_oSmartFridge.addRecipe( new Recipe( m_oNewRecipeTitle.getText() , (String)m_oAddRecipeType.getSelectedItem() , vStages , vAliments , m_oParent.m_oSmartFridge.getRecipes().size() ) );
			m_oParent.listRecipeAction();
		}	
	}
}
