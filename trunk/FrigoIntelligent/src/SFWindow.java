
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

import SmartFridgeAPI.Aliment;
import SmartFridgeAPI.Recipe;
import SmartFridgeAPI.RecipeStage;

@SuppressWarnings("serial")
public class SFWindow extends JFrame implements ActionListener {

	SmartFridgeApp m_oParent;
	JPanel 			 m_oRecipeListPanel;
	SFAddRecipePanel m_oAddRecipePanel = new SFAddRecipePanel( this );

	// BEGIN - Affichage des recettes
	Vector< SFListRecipePanel > m_vAddRecipeList;
	JList m_lAddRecipeList;
	// END - Affichage des recettes
	
	public JList list;
	
	public SFWindow( SmartFridgeApp oSF ){
		m_oParent = oSF;
		JListRenderer oListRenderer = new JListRenderer();		
		FileAction oFileAction 		= new FileAction( m_oParent );		
		JMenuBar menuBar 	   		= new JMenuBar();
		
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
		
		m_vAddRecipeList = new Vector< SFListRecipePanel >();
		m_vAddRecipeList.addElement( new SFListRecipePanel( new Recipe() ) );
		
		m_vAddRecipeList.addElement( new SFListRecipePanel( new Recipe() ) );
		
		m_lAddRecipeList = new JList( m_vAddRecipeList );
		m_lAddRecipeList.setSelectedIndex( 0 );
		m_lAddRecipeList.setCellRenderer( oListRenderer );
		
		m_oRecipeListPanel.add( m_lAddRecipeList, BorderLayout.CENTER );
		centerPanel.add( m_oRecipeListPanel );
		
		// END - Liste des recettes : Panel du milieu
		
		// BEGIN - Ajout d'une recette : Panel du milieu				
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
		
	}
	
	public void listRecipeAction() {
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
	
	

