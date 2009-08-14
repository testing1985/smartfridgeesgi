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
				oFC.addChoosableFileFilter( new SFXmlFilter(".xml" , ".xml") );
				
				if( oFC.showSaveDialog( m_oParent ) == JFileChooser.APPROVE_OPTION ) {
					File fSelected = oFC.getSelectedFile();
					if( fSelected != null ) {
						if( fSelected.length() > 0 ) {
							int iRes = JOptionPane.showConfirmDialog( m_oParent , "Le fichier n'est pas vide, voulez vous l'écraser ?", "Confirmation d'enregistrement", JOptionPane.YES_NO_OPTION );
							if( iRes == JOptionPane.YES_OPTION ) {
								XMLManager.encodeToFile( m_oParent.m_oSmartFridge, fSelected.getName() );
							}
						}
					}
				}
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
				oFC.addChoosableFileFilter( new SFXmlFilter(".xml" , ".xml") );
				
				if( oFC.showOpenDialog( m_oParent ) == JFileChooser.APPROVE_OPTION ) {
					File fSelected = oFC.getSelectedFile();
					if( fSelected != null ) {
						SmartFridge oTemp = (SmartFridge) XMLManager.decodeFromFile( fSelected.getName() );
						if( oTemp == null ) {
							JOptionPane.showMessageDialog( m_oParent , "Impossible de lire ce fichier !" , "Erreur de chargement de la sauvegarde" , JOptionPane.ERROR_MESSAGE );
						}
						else {
							m_oParent.m_oSmartFridge = oTemp;
							m_oParent.m_oSmartFridge.createMenusFromIDs();	
						}
					}
				}
				m_oParent.listRecipeAction();
			} catch ( Exception e2 ) {
				JOptionPane.showMessageDialog( m_oParent , "Impossible de lire ce fichier !" , "Erreur de chargement de la sauvegarde" , JOptionPane.ERROR_MESSAGE );
			}
		}
		
		// Voir le contenu du frigo
		if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Voir le contenu" ) ){
			m_oParent.seeFridgeAction();
		}
		// Ajouter un aliment
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Ajouter un aliment" )  ){
			m_oParent.addAlimentAction();
		}
		
		// Voir les menus enregistrés
		if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Voir les menus" ) ){
			m_oParent.seeMenuListAction();
		}
		// Ajouter un menu
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Créer un menu" )  ){
			m_oParent.CreateMenuAction();
		}
	}
}
