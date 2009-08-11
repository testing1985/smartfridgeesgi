import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class FileAction implements ActionListener {
	
	SmartFridgeApp m_oSmartFridge;
	SFWindow m_oFrame;
	
	FileAction( SmartFridgeApp oApp , SFWindow oFrame ) {
		m_oSmartFridge = oApp;
		m_oFrame = oFrame;
	}
	
	public void actionPerformed(ActionEvent e) {
		if( ((JMenuItem)(e.getSource())).getText().equals("Quit") )
		{
			int answer = JOptionPane.showConfirmDialog(null, "Do you really want to quit ?", "Quit", JOptionPane.YES_NO_OPTION );
			
			if(answer == JOptionPane.YES_OPTION) {
				m_oSmartFridge.quitAction();
			} else if(answer == JOptionPane.NO_OPTION) {
				
			}
		}
		
		else if( ((JMenuItem)(e.getSource())).getText().equals("New recipe") )
		{
			m_oFrame.newRecipeAction();
		}
	}

}
