import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ProjetsUtils.XMLTools.XMLManager;
import SmartFridgeAPI.SmartFridge;


public class SFMenuBarActionListener implements ActionListener {

	SFWindow m_oApp = null;
	
	public SFMenuBarActionListener( SFWindow oApp ) {
		m_oApp = oApp;
	}
	
	public void actionPerformed(ActionEvent e) {
				
		// Quitter l'application
		if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Quit" ) )
		{
			int answer = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter le programme ?", "Quitter ?", JOptionPane.YES_NO_OPTION );
			
			if(answer == JOptionPane.YES_OPTION) {
				m_oApp.m_oParent.quitAction();
			} else if(answer == JOptionPane.NO_OPTION) {
				
			}
		}
		
		// Voir les recettes
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Voir les recettes" ) )
		{
			m_oApp.listRecipeAction();
		}
		
		// Nouvelle recette
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Ajouter une recette" ) )
		{
			m_oApp.newRecipeAction();
		}
		
		// Sauvegarde sur internet
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "On internet" ) ) {
			m_oApp.saveInternetShowPanel();
		}
		
		// Chargement depuis internet
		else if( ((JMenuItem)(e.getSource())).getText().equals("From internet") ) {
			m_oApp.loadInternetShowPanel();
		}
		
		// Sauvegarde dans le fichier xml
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "In file" ) ) {
			try {
				JFileChooser oFC = new JFileChooser( new File( "." ).getCanonicalPath() );
				oFC.showSaveDialog( m_oApp );
				File fSelected = oFC.getSelectedFile();
				if( fSelected != null )
					XMLManager.encodeToFile( m_oApp.m_oParent.m_oSmartFridge, fSelected.getName() );
			} catch ( FileNotFoundException e1 ) {
				e1.printStackTrace();
			} catch ( IOException e1 ) {
				e1.printStackTrace();
			}
		}
		
		// Chargement depuis le fichier xml
		else if( ( (JMenuItem)(e.getSource())).getText().equals( "From file" ) ) {
			try {
				JFileChooser oFC = new JFileChooser( new File( "." ).getCanonicalPath() );
				oFC.showOpenDialog( m_oApp );
				File fSelected = oFC.getSelectedFile();
				if( fSelected != null ) {
					m_oApp.m_oParent.m_oSmartFridge  = (SmartFridge) XMLManager.decodeFromFile( fSelected.getName() );
					m_oApp.m_oParent.m_oSmartFridge.createMenusFromIDs();
				}
				m_oApp.listRecipeAction();
			} catch( FileNotFoundException e1 ){
				System.out.println( "Le fichier SmartFridge_db.xml n'existe pas" );
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		// Voir le contenu du frigo
		if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Voir le contenu" ) ){
			m_oApp.SeeFridgeAction();
		}
		// Ajouter un aliment
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Ajouter un aliment" )  ){
			m_oApp.AddAlimentAction();
		}
		
		// Voir les menus enregistrés
		if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Voir les menus" ) ){
			m_oApp.SeeMenuListAction();
		}
		// Ajouter un menu
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Créer un menu" )  ){
			m_oApp.CreateMenuAction();
		}
	}
}
