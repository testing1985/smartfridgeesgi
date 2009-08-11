import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class FileAction implements ActionListener {
	
	SmartFridgeApp m_oSmartFridge;
	
	FileAction( SmartFridgeApp oApp ) {
		m_oSmartFridge = oApp;
	}
	
	public void actionPerformed(ActionEvent e) {
		if( ((JMenuItem)(e.getSource())).getText().equals("Quit") )
		{
			int answer = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter le programme ?", "Quitter ?", JOptionPane.YES_NO_OPTION );
			
			if(answer == JOptionPane.YES_OPTION) {
				m_oSmartFridge.quitAction();
			} else if(answer == JOptionPane.NO_OPTION) {
				
			}
		}
		
		else if( ((JMenuItem)(e.getSource())).getText().equals("New recipe") )
		{
			m_oSmartFridge.m_oApp.newRecipeAction();
		}
	}

}
