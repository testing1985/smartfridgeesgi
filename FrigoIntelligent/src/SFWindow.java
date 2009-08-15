
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
	SFSeeRecipePanel m_oSFSeeRecipePanel = new SFSeeRecipePanel( this );
	// END - Affichage d'une recette
		
	// BEGIN - Affichage du contenu du frigo
	SFSeeFridgeContentPanel m_oSFSeeFridgeContent = new SFSeeFridgeContentPanel( this );
	// END - Affichage du contenu du frigo
	
	// BEGIN - Ajout d'un aliment
	SFAddAlimentPanel m_oSFAddAliment = new SFAddAlimentPanel( this );
	// END - Ajout d'un aliment
	
	// BEGIN - Affichage des menus
	SFSeeMenuList m_oSFSeeMenuListPanel = new SFSeeMenuList( this );
	// END - Affichage des menus
	
	// BEGIN - Affichage d'un menu
	SFSeeMenuPanel m_oSFSeeMenuPanel = new SFSeeMenuPanel( this );
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
		centerPanel.add( m_oSFSeeRecipePanel );
		m_oSFSeeRecipePanel.setVisible( false );
		// END - Affichage d'une recette : Panel du milieu
				
		// BEGIN - Affichage du contenu du frigo : Panel du milieu
		centerPanel.add( m_oSFSeeFridgeContent );
		m_oSFSeeFridgeContent.setVisible( false );
		// END - Affichage du contenu du frigo : Panel du milieu
		
		// BEGIN - Ajout d'un aliment : Panel du milieu		
		centerPanel.add( m_oSFAddAliment );
		m_oSFAddAliment.setVisible( false );
		// END - Ajout d'un aliment : Panel du milieu
		
		// Begin - Ajout d'un menu : Panel du milieu
		centerPanel.add( m_oSFSeeMenuListPanel );
		m_oSFSeeMenuListPanel.setVisible( false );
		// END - Ajout d'un menu : Panel du milieu
		
		
		
		
		// Begin - Affichage d'un menu : Panel du milieu		
		centerPanel.add( m_oSFSeeMenuPanel );
		m_oSFSeeMenuPanel.setVisible( false );
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
				m_oSFSeeRecipePanel.setVisible( true );
				
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

	public void refreshRecipePanel(){
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
		refreshRecipePanel();
		m_oRecipeListPanel.setVisible( true );
	}
		
	public void seeRecipeAction() {
		hideAllPanel();
		m_oRecipeListPanel.setVisible( true );
	}
	
	public void seeFridgeAction() {
		hideAllPanel();
		m_oSFSeeFridgeContent.setVisible( true );
		m_oSFSeeFridgeContent.Refresh();
	}
	
	public void addAlimentAction() {
		hideAllPanel();
		m_oSFAddAliment.setVisible( true );
	}
	
	public void hideAllPanel() {
		m_oAddRecipePanel.setVisible( false );
		m_oRecipeListPanel.setVisible( false );
		m_oSFSeeRecipePanel.setVisible( false );
		m_oSFSeeFridgeContent.setVisible( false );
		m_oSFAddAliment.setVisible( false );
		m_oSFSeeMenuListPanel.setVisible( false );
		m_oSFSeeMenuPanel.setVisible( false );
		m_oSaveInternetPanel.setVisible( false );
		m_oLoadFromInternetPanel.setVisible( false );
		m_oCreateMenuPanel.setVisible( false );
	}
	
	public void newRecipeAction() {
		hideAllPanel();
		m_oAddRecipePanel.reset();
		m_oAddRecipePanel.setVisible( true );
	}
	
	public void seeMenuListAction(){
		hideAllPanel();
		m_oSFSeeMenuListPanel.setVisible( true );
		
		m_oSFSeeMenuListPanel.Refresh();
	}
	
	public void seeMenuAction(){		
		DefaultTableModel model = (DefaultTableModel)m_oSFSeeMenuListPanel.getTable().getModel();
		int iRow = m_oSFSeeMenuListPanel.getTable().getSelectedRow();
		if( iRow != -1 ) {
			hideAllPanel();
			m_oSFSeeMenuPanel.ChangeID( (String) model.getValueAt( iRow , 0 ) );
			m_oSFSeeMenuPanel.Refresh();
			m_oSFSeeMenuPanel.setVisible( true );
		}
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
	
	

