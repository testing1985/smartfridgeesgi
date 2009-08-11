
import javax.swing.*;

import java.awt.BorderLayout;
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

import SmartFridgeAPI.RecipeStage;

@SuppressWarnings("serial")
public class SFWindow extends JFrame implements ActionListener {

	SmartFridgeApp m_oParent;
	JPanel m_oRecipeListPanel;
	
	// BEGIN - Ajout d'une recette
	JPanel m_oAddRecipePanel;
	JTextField m_oNewRecipeTitle;
	Vector< SFAddRecipeStageFormPanel > m_vAddRecipeStageFormList;
	JList m_lAddRecipeStageList;
	// END - Ajout d'une recette
	
	public JList list;
	
	public SFWindow( SmartFridgeApp oSF ){
		m_oParent = oSF;
		JListRenderer oListRenderer = new JListRenderer();
		
		FileAction oFileAction = new FileAction( m_oParent );
		
		JMenuBar menuBar = new JMenuBar();
		
		// BEGIN - MENU FILE
		JMenu menuFile = new JMenu( "File" );
		
			// BEGIN - NEW MENU
		JMenu newMenu  = new JMenu( "New" );
		JMenuItem newRecipeMI = new JMenuItem("New recipe");
		newRecipeMI.addActionListener( oFileAction );		
		newMenu.add( newRecipeMI );
		
		JMenuItem newMenuMI = new JMenuItem("New menu");
		newMenuMI.addActionListener( oFileAction );		
		newMenu.add( newMenuMI );
				
		menuFile.add( newMenu );
			// END - NEW MENU

		JMenuItem quitMI = new JMenuItem("Quit");
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
		m_oRecipeListPanel.setPreferredSize( new Dimension( 500 , 500 ));
		m_oRecipeListPanel.setLayout( new BorderLayout() );
		m_oRecipeListPanel.add( new JLabel( "Liste des recettes" ) , BorderLayout.NORTH );
		centerPanel.add( m_oRecipeListPanel );
		// END - Liste des recettes : Panel du milieu
		
		// BEGIN - Ajout d'une recette : Panel du milieu
		m_oAddRecipePanel = new JPanel();
		m_oAddRecipePanel.setPreferredSize( new Dimension( 500 , 500 ));
		m_oAddRecipePanel.setLayout( new BorderLayout() );
		m_oAddRecipePanel.add( new JLabel( "Ajout d'une recette" ) , BorderLayout.NORTH );
		JPanel oAddRecipePanelCenter = new JPanel();
		oAddRecipePanelCenter.setLayout( new BorderLayout() );
		
		JPanel oAddRecipeTitlePanel = new JPanel();
		oAddRecipeTitlePanel.setLayout( new GridLayout(1,2) );
		oAddRecipeTitlePanel.add( new JLabel("Titre :"));
		m_oNewRecipeTitle = new JTextField();
		oAddRecipeTitlePanel.add(m_oNewRecipeTitle);
		oAddRecipePanelCenter.add( oAddRecipeTitlePanel , BorderLayout.NORTH );
		m_vAddRecipeStageFormList = new Vector< SFAddRecipeStageFormPanel >();
		m_vAddRecipeStageFormList.addElement( new SFAddRecipeStageFormPanel(1) );
		m_lAddRecipeStageList = new JList( m_vAddRecipeStageFormList );
		m_lAddRecipeStageList.setSelectedIndex(0);
		m_lAddRecipeStageList.setCellRenderer( oListRenderer );
		oAddRecipePanelCenter.add( m_lAddRecipeStageList , BorderLayout.CENTER );
		
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
	
	

