
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import SmartFridgeAPI.Aliment;
import SmartFridgeAPI.Recipe;
import SmartFridgeAPI.RecipeStage;

@SuppressWarnings("serial")
public class SFWindow extends JFrame implements ActionListener {

	SmartFridgeApp m_oParent;
	JPanel m_oRecipeListPanel;
	
	// BEGIN - Ajout d'une recette
	Vector< SFAddRecipeStageFormPanel > m_vAddRecipeStageFormList = new Vector< SFAddRecipeStageFormPanel >();
	JPanel 	   m_oAddRecipePanel	 = new JPanel();
	JPanel 	   m_oAddStageList 	     = new JPanel();
	JTextField m_oNewRecipeTitle     = new JTextField();	
	JButton    m_oAddStageFormBt     = new JButton("Ajouter une étape");
	JButton    m_oAddValidRecipeBt   = new JButton("Valider la recette");
	Choice     m_oAddRecipeType		 = new Choice();
	// END - Ajout d'une recette
	
	// BEGIN - Affichage des recettes
	Vector < Vector< String > > elements = new Vector < Vector< String > >();
	JTable table						 = new JTable();
	JScrollPane scrollpane				 = new JScrollPane();
	JButton m_oSeeRecipe 				 = new JButton( "Voir la recette" );
	// END - Affichage des recettes
	
	// BEGIN - Affichage d'une recette
	JPanel m_oSeeRecipePanel = new JPanel();
	
	// END - Affichage d'une recette
	
	public JList list;
	
	public SFWindow( SmartFridgeApp oSF ){
		m_oParent = oSF;
		
		FileAction oFileAction = new FileAction( m_oParent );
		
		JMenuBar menuBar = new JMenuBar();
		
		// BEGIN - MENU FILE
		JMenu menuFile = new JMenu( "File" );
		
			// BEGIN - NEW MENU
		JMenu newMenu  = new JMenu( "New" );
		JMenuItem newRecipeMI = new JMenuItem( "New recipe" );
		newRecipeMI.addActionListener( oFileAction );		
		newMenu.add( newRecipeMI );
		
		JMenuItem newMenuMI = new JMenuItem( "New menu" );
		newMenuMI.addActionListener( oFileAction );		
		newMenu.add( newMenuMI );
				
		menuFile.add( newMenu );
			// END - NEW MENU

			// BEGIN - SAVE MENU
		JMenu saveMenu = new JMenu ("Save");
		JMenuItem saveXmlMI = new JMenuItem( "In file" );
		saveXmlMI.addActionListener( oFileAction );
		saveMenu.add( saveXmlMI );
		menuFile.add( saveMenu );
			// END - SAVE MENU
		
			// BEGIN - LOAD MENU
		JMenu loadMenu = new JMenu ("Load");
		JMenuItem loadXmlMI = new JMenuItem( "From file" );
		loadXmlMI.addActionListener( oFileAction );
		loadMenu.add( loadXmlMI );
		menuFile.add( loadMenu );
			// END - LOAD MENU
		
		JMenuItem quitMI = new JMenuItem( "Quit" );
		quitMI.addActionListener( oFileAction );		
		menuFile.add( quitMI );
		// END - MENU FILE
						
		menuBar.add( menuFile );		
		setJMenuBar( menuBar );
		
		setSize( 800, 600 );
		setLocation( 100, 100 );
		setLayout( new BorderLayout() );
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout( new FlowLayout() );
		
		// BEGIN - Liste des recettes : Panel du milieu
		m_oRecipeListPanel = new JPanel();
		m_oRecipeListPanel.setPreferredSize( new Dimension( 500 , 500 ) );
		m_oRecipeListPanel.setLayout( new BorderLayout() );
		m_oRecipeListPanel.add( new JLabel( "Liste des recettes" ), BorderLayout.NORTH );
		 	    	    	    
		table = new JTable();
	    DefaultTableModel model = (DefaultTableModel)table.getModel();
	    model.addColumn( "ID" );
	    model.addColumn( "Nom" );
	    model.addColumn( "Type" );
	    model.addColumn( "Temps" );
	    model.addColumn( "Difficulté" );
	    
	    table.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
	    
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>( table.getModel() );
	    table.setRowSorter( sorter );
    
	    scrollpane = new JScrollPane( table );
		
	    m_oRecipeListPanel.add( scrollpane, BorderLayout.CENTER );
	    m_oRecipeListPanel.add( m_oSeeRecipe, BorderLayout.SOUTH );
	    
		centerPanel.add( m_oRecipeListPanel );
		// END - Liste des recettes : Panel du milieu
		
		// BEGIN - Affichage d'une recette : Panel du milieu
		m_oSeeRecipePanel = new JPanel();
		m_oRecipeListPanel.setPreferredSize( new Dimension( 500 , 500 ) );
		m_oRecipeListPanel.setLayout( new BorderLayout() );
		
		
		m_oRecipeListPanel.add( new JLabel( " Recette " ), BorderLayout.NORTH );
		
		
		
		centerPanel.add( m_oRecipeListPanel );
		m_oRecipeListPanel.setVisible( false );
		// END - Affichage d'une recette : Panel du milieu
		
		// BEGIN - Ajout d'une recette : Panel du milieu
		m_oAddRecipePanel.setPreferredSize( new Dimension( 500 , 500 ) );
		m_oAddRecipePanel.setLayout( new BorderLayout() );
		JPanel oAddRecipePanelTop = new JPanel();
		oAddRecipePanelTop.add( new JLabel( "Ajout d'une recette :   " ), BorderLayout.NORTH );
		m_oAddRecipeType.add("Entrée");
		m_oAddRecipeType.add("Plat");
		m_oAddRecipeType.add("Dessert");
		m_oAddRecipeType.add("Boisson");
		m_oAddRecipeType.add("Amuse-gueule");
		oAddRecipePanelTop.add(m_oAddRecipeType);
		
		m_oAddRecipePanel.add( oAddRecipePanelTop , BorderLayout.NORTH );
		JPanel oAddRecipePanelCenter = new JPanel();
		oAddRecipePanelCenter.setLayout( new BorderLayout() );
		
		JPanel oAddRecipeTitlePanel = new JPanel();
		oAddRecipeTitlePanel.add( new JLabel( "Titre :" ) );
		m_oNewRecipeTitle.setColumns( 40 );
		oAddRecipeTitlePanel.add( m_oNewRecipeTitle );
		oAddRecipePanelCenter.add( oAddRecipeTitlePanel , BorderLayout.NORTH );
		m_oAddStageFormBt.addActionListener( this );
		m_oAddValidRecipeBt.addActionListener( this );
		JPanel lAddRecipeStageList = new JPanel( new BorderLayout() );
		JPanel oTopAddRecipe 	   = new JPanel( new GridLayout(1,2));
		oTopAddRecipe.add( m_oAddValidRecipeBt );
		oTopAddRecipe.add( m_oAddStageFormBt );		
		lAddRecipeStageList.add( oTopAddRecipe , BorderLayout.NORTH );
		m_vAddRecipeStageFormList.addElement( new SFAddRecipeStageFormPanel (1) );
		m_oAddStageList.setLayout( new BoxLayout( m_oAddStageList , BoxLayout.Y_AXIS ));
		m_oAddStageList.add( m_vAddRecipeStageFormList.elementAt(0) );
		lAddRecipeStageList.add( new JScrollPane( m_oAddStageList , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) , BorderLayout.CENTER );
		oAddRecipePanelCenter.add( lAddRecipeStageList , BorderLayout.CENTER );		
		m_oAddRecipePanel.add( oAddRecipePanelCenter , BorderLayout.CENTER );
				
		centerPanel.add( m_oAddRecipePanel );
		m_oAddRecipePanel.setVisible( false );
		// END - Ajout d'une recette : Panel du milieu		
		
		this.add( centerPanel , BorderLayout.CENTER );		
		
		setResizable( false );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setVisible( true );
		pack();
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if( e.getSource().equals( m_oAddStageFormBt ) ) {
			m_vAddRecipeStageFormList.addElement( new SFAddRecipeStageFormPanel ( m_vAddRecipeStageFormList.size() + 1 ) );
			m_oAddStageList.add( m_vAddRecipeStageFormList.lastElement() );
			m_oAddRecipePanel.revalidate();
			super.repaint();
		}
		
		else if( e.getSource().equals( m_oAddValidRecipeBt ) ) {
			Vector<RecipeStage> v = new Vector<RecipeStage>();
			for( int i = 0 ; i < m_vAddRecipeStageFormList.size() ; i++ ) {
				SFAddRecipeStageFormPanel o = m_vAddRecipeStageFormList.elementAt( i );
				Vector<Aliment> vA = new Vector<Aliment>();
				v.add( new RecipeStage( Integer.parseInt( o.m_oDuree.getText() ), Integer.parseInt( o.m_oDifficulte.getSelectedItem() ), o.m_oContent.getText() ) );
			}
			m_oParent.m_oSmartFridge.addRecipe( new Recipe( m_oNewRecipeTitle.getText() , m_oAddRecipeType.getSelectedItem() , v ) );
			listRecipeAction();
		}
		
		else if(  e.getSource().equals( m_oSeeRecipe ) ){
			
		}
	}
	
	public void DrawRecipePanel(){
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		int size = m_oParent.m_oSmartFridge.getRecipes().size();
	    
    	Vector < String > recipe = new Vector < String >();
    	Recipe r = m_oParent.m_oSmartFridge.getRecipes().elementAt( size-1 );
    	recipe.addElement( String.valueOf( size-1 ) );
    	recipe.addElement( r.getName() );
    	recipe.addElement( r.getType() );
    	recipe.addElement( String.valueOf( r.getTime() + " minutes") );
    	recipe.addElement( String.valueOf( r.getDifficulty() ) );
	  		
		model.addRow( recipe );
	}
	
	public void listRecipeAction() {
		hideAllPanel();
		m_oRecipeListPanel.setVisible( true );
		DrawRecipePanel();
	}
	
	public void SeeRecipeAction() {
		hideAllPanel();
		m_oRecipeListPanel.setVisible( true );
	}
	
	
	public void hideAllPanel() {
		m_oAddRecipePanel.setVisible( false );
		m_oRecipeListPanel.setVisible( false );
	}
	
	public void newRecipeAction() {
		hideAllPanel();
		m_oAddRecipePanel.setVisible( true );
	}
	
}
	
	

