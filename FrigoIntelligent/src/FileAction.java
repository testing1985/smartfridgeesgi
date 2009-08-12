import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ProjetsUtils.XMLTools.XMLManager;
import ProjetsUtils.XMLTools.RSSManager.RSSManager;
import SmartFridgeAPI.SmartFridge;


public class FileAction implements ActionListener {
	
	SmartFridgeApp m_oSmartFridge;
	
	FileAction( SmartFridgeApp oApp ) {
		m_oSmartFridge = oApp;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// Quitter l'application
		if( ((JMenuItem)(e.getSource())).getText().equals("Quit") )
		{
			int answer = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter le programme ?", "Quitter ?", JOptionPane.YES_NO_OPTION );
			
			if(answer == JOptionPane.YES_OPTION) {
				m_oSmartFridge.quitAction();
			} else if(answer == JOptionPane.NO_OPTION) {
				
			}
		}
		
		// Nouvelle recette
		else if( ((JMenuItem)(e.getSource())).getText().equals("New recipe") )
		{
			m_oSmartFridge.m_oApp.newRecipeAction();
		}
		
		// Sauvegarde dans le fichier xml
		else if( ((JMenuItem)(e.getSource())).getText().equals("In file") ) {
			try {
				XMLManager.encodeToFile(m_oSmartFridge.m_oSmartFridge, "SmartFridge_db.xml");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		// Chargement depuis le fichier xml
		else if( ((JMenuItem)(e.getSource())).getText().equals("From file") ) {
			try {
				m_oSmartFridge.m_oSmartFridge  = (SmartFridge) XMLManager.decodeFromFile("SmartFridge_db.xml");
			} catch (FileNotFoundException e1) {
				System.out.println("Le fichier SmartFridge_db.xml n'existe pas");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
