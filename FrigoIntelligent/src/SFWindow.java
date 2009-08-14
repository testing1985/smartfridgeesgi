
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ProjetsUtils.XMLTools.RSSManager.RSSManager;
import SmartFridgeAPI.Recipe;
import SmartFridgeAPI.SmartFridge;

@SuppressWarnings( "serial" )
public class SFWindow extends JFrame implements ActionListener {

	RSSManager   m_oRSSManager;
	SmartFridge  m_oSmartFridge;
	Session      m_oSession = null;
	
	JPanel m_oRecipeListPanel;
	
	SFAddRecipePanel 		  m_oAddRecipePanel        = null;
	SFSaveOnInternetFormPanel m_oSaveInternetPanel     = null;
	SFLoadFromInternetPanel   m_oLoadFromInternetPanel = null;
	SFCreateMenuPanel         m_oCreateMenuPanel	   = null;
	
	// BEGIN - Affichage des recettes
	JTable m_oTable						 = new JTable();
	JScrollPane m_oScrollpane			 = new JScrollPane();
	JButton m_oSeeRecipe 				 = new JButton( "Voir la recette" );
	// END - Affichage des recettes
	
	// BEGIN - Affichage d'une recette
	JPanel m_oSeeRecipePanel = new JPanel();
	SFSeeRecipePanel m_oSFSeeRecipePanel;
	// END - Affichage d'une recette
	
	// BEGIN - Affichage du contenu du frigo
	JPanel m_oSeeFridgeContent = new JPanel();
	SFSeeFridgeContentPanel m_oSFSeeFridgeContent;
	// END - Affichage du contenu du frigo
	
	// BEGIN - Ajout d'un aliment
	JPanel m_oAddAliment = new JPanel();
	SFAddAlimentPanel m_oSFAddAliment;
	// END - Ajout d'un aliment

	// BEGIN - Affichage des menus
	JPanel m_oSeeMenuListPanel = new JPanel();
	SFSeeMenuList m_oSFSeeMenuListPanel;
	// END - Affichage des menus
	
	// BEGIN - Affichage d'un menu
	JPanel m_oSeeMenuPanel = new JPanel();
	SFSeeMenuPanel m_oSFSeeMenuPanel;
	// END - Affichage d'un menu
	
	public JList list;
	
	public SFWindow(){
		m_oSmartFridge = new SmartFridge();
		m_oSession	   = new Session();
		initializeConnection();
		
		SFMenuBarActionListener oMenuBarActionListener = new SFMenuBarActionListener( this );
				
		JMenuBar menuBar = new JMenuBar();
		
		// BEGIN - MENU FILE
		JMenu menuFile 			= new JMenu( "Fichier" );
		JMenu menuFridgeRecipe  = new JMenu( "Recettes" );
		JMenu menuFridgeContent = new JMenu( "Contenu du frigo" );
		JMenu menuFridgeMenu 	= new JMenu( "Menus" );
		
			// BEGIN - SAVE MENU
		JMenu saveMenu = new JMenu ("Sauvegarder");
		JMenuItem saveXmlMI = new JMenuItem( "Dans un fichier..." );
		saveXmlMI.addActionListener( oMenuBarActionListener );
		saveMenu.add( saveXmlMI );
		
		JMenuItem saveDbMI = new JMenuItem( "Sur Internet" );
		saveDbMI.addActionListener( oMenuBarActionListener );
		saveMenu.add( saveDbMI );
		menuFile.add( saveMenu );
			// END - SAVE MENU
		
			// BEGIN - LOAD MENU
		JMenu loadMenu = new JMenu ( "Charger" );
		JMenuItem loadXmlMI = new JMenuItem( "Depuis un fichier..." );
		loadXmlMI.addActionListener( oMenuBarActionListener );
		loadMenu.add( loadXmlMI );
		
		JMenuItem loadDbMI = new JMenuItem( "Depuis Internet" );
		loadDbMI.addActionListener( oMenuBarActionListener );
		loadMenu.add( loadDbMI );		
		menuFile.add( loadMenu );
			// END - LOAD MENU
		
		JMenuItem quitMI = new JMenuItem( "Quitter" );
		quitMI.addActionListener( oMenuBarActionListener );		
		menuFile.add( quitMI );
		// END - MENU FILE
						
		// BEGIN - FridgeRecipe MENU
		JMenuItem seeRecipe = new JMenuItem(  "Voir les recettes"  );
		seeRecipe.addActionListener( oMenuBarActionListener );
		menuFridgeRecipe.add( seeRecipe );
		
		JMenuItem addRecipe = new JMenuItem( "Ajouter une recette" );
		addRecipe.addActionListener( oMenuBarActionListener );
		menuFridgeRecipe.add( addRecipe );
		// END - FridgeRecipe MENU
		
		// BEGIN - FridgeContent MENU
		JMenuItem seeContent = new JMenuItem(  "Voir le contenu"  );
		seeContent.addActionListener( oMenuBarActionListener );
		menuFridgeContent.add( seeContent );
		
		JMenuItem addAliment = new JMenuItem( "Ajouter un aliment" );
		addAliment.addActionListener( oMenuBarActionListener );
		menuFridgeContent.add( addAliment );
		// END - FridgeContent MENU
		
		// BEGIN - FridgeMenu MENU
		JMenuItem seeMenus = new JMenuItem(  "Voir les menus"  );
		seeMenus.addActionListener( oMenuBarActionListener );
		menuFridgeMenu.add( seeMenus );
		
		JMenuItem createMenu = new JMenuItem( "Créer un menu" );
		createMenu.addActionListener( oMenuBarActionListener );
		menuFridgeMenu.add( createMenu );
		// END - FridgeMenu MENU
		
		menuBar.add( menuFile );	
		menuBar.add( menuFridgeRecipe );
		menuBar.add( menuFridgeContent );
		menuBar.add( menuFridgeMenu );
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
		 	    	    	    
		m_oTable = new JTable();

		m_oTable.setModel( new JRecipeTableModel () );
	    m_oTable.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
	    m_oTable.getTableHeader().setReorderingAllowed( false );
	    
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>( m_oTable.getModel() );
	    m_oTable.setRowSorter( sorter );
    
	    m_oScrollpane = new JScrollPane( m_oTable );
		
	    m_oRecipeListPanel.add( m_oScrollpane, BorderLayout.CENTER );
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
				
		// BEGIN - Affichage du contenu du frigo : Panel du milieu
		m_oSFSeeFridgeContent = new SFSeeFridgeContentPanel( this );
	
		//m_oSeeFridgeContent.add( new JLabel( "Liste des aliments" ), BorderLayout.NORTH );
		m_oSeeFridgeContent.add( m_oSFSeeFridgeContent, BorderLayout.CENTER );
		
		centerPanel.add( m_oSeeFridgeContent );
		m_oSeeFridgeContent.setVisible( false );
		// END - Affichage du contenu du frigo : Panel du milieu
		
		// BEGIN - Ajout d'un aliment : Panel du milieu		
		m_oSFAddAliment = new SFAddAlimentPanel( this );
		m_oAddAliment.add( m_oSFAddAliment, BorderLayout.CENTER );
		
		centerPanel.add( m_oAddAliment );
		m_oAddAliment.setVisible( false );
		// END - Ajout d'un aliment : Panel du milieu
			
		// Begin - Ajout d'un menu : Panel du milieu
		m_oSFSeeMenuListPanel = new SFSeeMenuList( this );
	
		m_oSeeMenuListPanel.add( m_oSFSeeMenuListPanel, BorderLayout.CENTER );
		
		centerPanel.add( m_oSeeMenuListPanel );
		m_oSeeMenuListPanel.setVisible( false );
		// END - Ajout d'un menu : Panel du milieu
				
		// Begin - Affichage d'un menu : Panel du milieu
		m_oSFSeeMenuPanel = new SFSeeMenuPanel( this );
	
		m_oSeeMenuPanel.add( m_oSFSeeMenuPanel, BorderLayout.CENTER );
		
		centerPanel.add( m_oSeeMenuPanel );
		m_oSeeMenuPanel.setVisible( false );
		// END - Affichage d'un menu : Panel du milieu
				
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
		
		// BEGIN - Enregistrement internet : Panel du milieu
		m_oLoadFromInternetPanel = new SFLoadFromInternetPanel( this );
		centerPanel.add( m_oLoadFromInternetPanel );
		m_oLoadFromInternetPanel.setVisible( false );
		// END - Enregistrement internet : Panel du milieu
		
		// BEGIN - Création de menu : Panel du milieu
		m_oCreateMenuPanel = new SFCreateMenuPanel( this );
		centerPanel.add( m_oCreateMenuPanel );
		m_oCreateMenuPanel.setVisible( false );
		// END - Création de menu : Panel du milieu
		
		this.add( centerPanel , BorderLayout.CENTER );		
		
		setResizable( false );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setVisible( true );
		pack();
	}

	public void actionPerformed( ActionEvent e ) {
		if( e.getSource().equals( m_oSeeRecipe ) ){
			if( m_oTable.getSelectedRow() != -1 ){
				hideAllPanel();
								
				m_oSFSeeRecipePanel.ChangeID( m_oTable.getValueAt( m_oTable.getSelectedRow(), 0 ).toString() );
				m_oSFSeeRecipePanel.Refresh();
				m_oSeeRecipePanel.setVisible( true );
				
			}
		}
	}
	
	public void loadInternetShowPanel() {
		if( ! m_oSession.isConnected() ) {
			hideAllPanel();
			m_oLoadFromInternetPanel.reset();
			m_oLoadFromInternetPanel.setVisible( true );
		}
		else {
			loadFromInternet();
			listRecipeAction();
		}
	}
	
	public void saveInternetShowPanel() {
		if( ! m_oSession.isConnected() ) {
			hideAllPanel();
			m_oSaveInternetPanel.reset();
			m_oSaveInternetPanel.setVisible( true );
		}
		else {
			saveOnInternet();
		}
	}

	public void DrawRecipePanel(){
		DefaultTableModel model = (DefaultTableModel)m_oTable.getModel();
			
		int size = model.getRowCount();
		for( int i = 0; i < size; i++ ){
			model.removeRow( 0 );
		}
		
		size = m_oSmartFridge.getRecipes().size();
		for( int i = 0; i < size; i++ ){
		    	Vector < String > recipe = new Vector < String >();
		    	Recipe r = m_oSmartFridge.getRecipes().elementAt( i );
		    	recipe.addElement( "" + ( i + 1 ) );
		    	recipe.addElement( r.getName() );
		    	recipe.addElement( r.getType() );
		    	recipe.addElement( "" + r.getTime() + " minutes" );
		    	recipe.addElement( "" + r.getDifficulty() );
			  		
				model.addRow( recipe );
		}
	}
		
	public void listRecipeAction() {
		hideAllPanel();
		DrawRecipePanel();
		m_oRecipeListPanel.setVisible( true );
	}
		
	public void SeeRecipeAction() {
		hideAllPanel();
		m_oRecipeListPanel.setVisible( true );
	}
	
	public void SeeFridgeAction() {
		hideAllPanel();
		m_oSeeFridgeContent.setVisible( true );
		m_oSFSeeFridgeContent.Refresh();
	}
	
	public void AddAlimentAction() {
		hideAllPanel();
		m_oAddAliment.setVisible( true );
	}
	
	public void hideAllPanel() {
		m_oAddRecipePanel.setVisible( false );
		m_oRecipeListPanel.setVisible( false );
		m_oSeeRecipePanel.setVisible( false );
		m_oSeeFridgeContent.setVisible( false );
		m_oAddAliment.setVisible( false );
		m_oSeeMenuListPanel.setVisible( false );
		m_oSeeMenuPanel.setVisible( false );
		m_oSaveInternetPanel.setVisible( false );
		m_oLoadFromInternetPanel.setVisible( false );
		m_oCreateMenuPanel.setVisible( false );
	}
	
	public void newRecipeAction() {
		hideAllPanel();
		m_oAddRecipePanel.reset();
		m_oAddRecipePanel.setVisible( true );
	}
	
	public void SeeMenuListAction(){
		hideAllPanel();
		m_oSeeMenuListPanel.setVisible( true );
		
		m_oSFSeeMenuListPanel.Refresh();
	}
	
	public void SeeMenuAction(){
		hideAllPanel();
		DefaultTableModel model = (DefaultTableModel)m_oSFSeeMenuListPanel.getTable().getModel();

		m_oSFSeeMenuPanel.ChangeID( (String) model.getValueAt(m_oSFSeeMenuListPanel.getTable().getSelectedRow() , 0 ) );
		m_oSFSeeMenuPanel.Refresh();
		m_oSeeMenuPanel.setVisible( true );
	}
	

	public void CreateMenuAction() {
		hideAllPanel();
		m_oCreateMenuPanel.reset();
		m_oCreateMenuPanel.setVisible( true );
	}
	
	public RSSManager getRSSManager() {
		return m_oRSSManager;
	}

	public void setRSSManager(RSSManager manager) {
		m_oRSSManager = manager;
	}

	public SmartFridge getSmartFridge() {
		return m_oSmartFridge;
	}

	public void setSmartFridge(SmartFridge fridge) {
		m_oSmartFridge = fridge;
	}
	
	public void quitAction() {
		System.exit( 0 );
	}
	
	public void saveOnInternet() {
		if( m_oSession.isConnected() ) {
			m_oSession.save( m_oSmartFridge );
		}
	}
	
	public void loadFromInternet() {
		if( m_oSession.isConnected() ) {
			m_oSmartFridge = new SmartFridge();
			m_oSession.load( m_oSmartFridge );
		}
	}
	
	public void initializeConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String sURL = "jdbc:mysql://88.191.18.27:3306/smartfridge";
			DBConnectionManager.getInstance().setConnectionData(sURL, "esgi", "g6f3s9j3");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
	
	

