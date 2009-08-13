import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;


public class FridgeContentAction implements ActionListener {

	SmartFridgeApp m_oSmartFridge;
	
	FridgeContentAction( SmartFridgeApp oApp ){
		m_oSmartFridge = oApp;
	}

	public void actionPerformed( ActionEvent e ) {
		// Voir le contenu
		if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Voir le contenu" ) ){
			m_oSmartFridge.m_oApp.SeeFridgeAction();
		}
		// Ajouter un aliment
		else if( ( (JMenuItem)( e.getSource() ) ).getText().equals( "Ajouter un aliment" )  ){
			
			
		}
		
	}

}
