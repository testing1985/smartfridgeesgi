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

	SFWindow m_oParent = null;
	
	public SFMenuBarActionListener( SFWindow oApp ) {
		m_oParent = oApp;
	}
	
	public void actionPerformed(ActionEvent e) {
				
		// Quitter l'application
		if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Quitter" ) )
		{
			int answer = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter le programme ?", "Quitter ?", JOptionPane.YES_NO_OPTION );
			
			if(answer == JOptionPane.YES_OPTION) {
				m_oParent.quitAction();
			} else if(answer == JOptionPane.NO_OPTION) {
				
			}
		}
		
		// Voir les recettes
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Voir les recettes" ) )
		{
			m_oParent.listRecipeAction();
		}
		
		// Nouvelle recette
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Ajouter une recette" ) )
		{
			m_oParent.newRecipeAction();
		}
		
		// Sauvegarde sur internet
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Sur Internet" ) ) {
			m_oParent.saveInternetShowPanel();
		}
		
		// Chargement depuis internet
		else if( ((JMenuItem)(e.getSource())).getText().equals("Depuis Internet") ) {
			m_oParent.loadInternetShowPanel();
		}
		
		// Sauvegarde dans le fichier xml
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Dans un fichier..." ) ) {
			try {
				JFileChooser oFC = new JFileChooser( new File( "." ).getCanonicalPath() );
				oFC.showSaveDialog( m_oParent );
				File fSelected = oFC.getSelectedFile();
				if( fSelected != null )
					XMLManager.encodeToFile( m_oParent.m_oSmartFridge, fSelected.getName() );
			} catch ( FileNotFoundException e1 ) {
				e1.printStackTrace();
			} catch ( IOException e1 ) {
				e1.printStackTrace();
			}
		}
		
		// Chargement depuis le fichier xml
		else if( ( (JMenuItem)(e.getSource())).getText().equals( "Depuis un fichier..." ) ) {
			try {
				JFileChooser oFC = new JFileChooser( new File( "." ).getCanonicalPath() );
				oFC.showOpenDialog( m_oParent );
				File fSelected = oFC.getSelectedFile();
				if( fSelected != null ) {
					m_oParent.m_oSmartFridge  = (SmartFridge) XMLManager.decodeFromFile( fSelected.getName() );
					m_oParent.m_oSmartFridge.createMenusFromIDs();
				}
				m_oParent.listRecipeAction();
			} catch( FileNotFoundException e1 ){
				System.out.println( "Le fichier SmartFridge_db.xml n'existe pas" );
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		// Voir le contenu du frigo
		if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Voir le contenu" ) ){
			m_oParent.SeeFridgeAction();
		}
		// Ajouter un aliment
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Ajouter un aliment" )  ){
			m_oParent.AddAlimentAction();
		}
		
		// Voir les menus enregistrés
		if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Voir les menus" ) ){
			m_oParent.SeeMenuListAction();
		}
		// Ajouter un menu
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Créer un menu" )  ){
			m_oParent.CreateMenuAction();
		}
	}
}
