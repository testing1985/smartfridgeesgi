import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;


public class FridgeMenuAction implements ActionListener {

	SmartFridgeApp m_oSmartFridge;
	
	FridgeMenuAction( SmartFridgeApp oApp ) {
		m_oSmartFridge = oApp;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Voir les menus" ) ){
			m_oSmartFridge.m_oApp.SeeMenuListAction();
		}
		// Ajouter un aliment
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Créer un menu" )  ){
			m_oSmartFridge.m_oApp.CreateMenuAction();
		}
	}

}
