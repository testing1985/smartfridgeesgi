
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
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import SmartFridgeAPI.Aliment;
import SmartFridgeAPI.Recipe;
import SmartFridgeAPI.RecipeStage;

@SuppressWarnings("serial")
public class SFWindow extends JFrame implements ActionListener {

	SmartFridgeApp m_oParent;
	JPanel m_oRecipeListPanel;
	
	SFAddRecipePanel 		  m_oAddRecipePanel    = null;
	SFSaveOnInternetFormPanel m_oSaveInternetPanel = null;
	
	// BEGIN - Affichage des recettes
	Vector < Vector< String > > elements = new Vector < Vector< String > >();
	JTable table						 = new JTable();
	JScrollPane scrollpane				 = new JScrollPane();
	JButton m_oSeeRecipe 				 = new JButton( "Voir la recette" );
	// END - Affichage des recettes
	
	// BEGIN - Affichage d'une recette
	JPanel m_oSeeRecipePanel = new JPanel();
	SFSeeRecipePanel m_oSFSeeRecipePanel;
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
		
		JMenuItem saveDbMI = new JMenuItem( "On internet" );
		saveDbMI.addActionListener( oFileAction );
		saveMenu.add( saveDbMI );
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
	    table.getTableHeader().setReorderingAllowed( false );
	    
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>( table.getModel() );
	    table.setRowSorter( sorter );
    
	    scrollpane = new JScrollPane( table );
		
	    m_oRecipeListPanel.add( scrollpane, BorderLayout.CENTER );
	    m_oSeeRecipe.addActionListener( this );
	    m_oRecipeListPanel.add( m_oSeeRecipe, BorderLayout.SOUTH );
	    
		centerPanel.add( m_oRecipeListPanel );
		// END - Liste des recettes : Panel du milieu
		
		// BEGIN - Affichage d'une recette : Panel du milieu
		m_oSFSeeRecipePanel = new SFSeeRecipePanel( this );
		
		m_oSeeRecipePanel.add( m_oSFSeeRecipePanel, BorderLayout.CENTER );
		
		centerPanel.add( m_oSeeRecipePanel );
		m_oSeeRecipePanel.setVisible( false );
		// END - Affichage d'une recette : Panel du milieu
		
		// BEGIN - Ajout d'une recette : Panel du milieu
		m_oAddRecipePanel = new SFAddRecipePanel( this );
		centerPanel.add( m_oAddRecipePanel );
		m_oAddRecipePanel.setVisible( false );
		// END - Ajout d'une recette : Panel du milieu		
		
		// BEGIN - Enregistrement internet : Panel du milieu
		m_oSaveInternetPanel = new SFSaveOnInternetFormPanel( this );
		centerPanel.add( m_oSaveInternetPanel );
		m_oSaveInternetPanel.setVisible( false );
		// END - Enregistrement internet : Panel du milieu
		
		this.add( centerPanel , BorderLayout.CENTER );		
		
		setResizable( false );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setVisible( true );
		pack();
	}

	public void actionPerformed( ActionEvent e ) {
		if( e.getSource().equals( m_oSeeRecipe ) ){
			if( table.getSelectedRow() != -1 ){
				hideAllPanel();
								
				m_oSFSeeRecipePanel.ChangeID( table.getValueAt( table.getSelectedRow(), 0 ).toString() );
				m_oSFSeeRecipePanel.Refresh();
				m_oSeeRecipePanel.setVisible( true );
				
			}
		}
	}
	
	public void saveInternetShowPanel() {
		if( ! m_oParent.m_oSession.isConnected() ) {
			hideAllPanel();
			m_oSaveInternetPanel.reset();
			m_oSaveInternetPanel.setVisible( true );
		}
		else {
			m_oParent.saveOnInternet();
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
		m_oSeeRecipePanel.setVisible( false );
	}
	
	public void newRecipeAction() {
		hideAllPanel();
		m_oAddRecipePanel.reset();
		m_oAddRecipePanel.setVisible( true );
	}
	
}
	
	

