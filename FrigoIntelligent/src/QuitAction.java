

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class QuitAction extends AbstractAction {
	public QuitAction( String text ){
		super( text );
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		int answer = JOptionPane.showConfirmDialog(null, "Do you really want to quit ?", "Quit", JOptionPane.YES_NO_OPTION );
			
		if(answer == JOptionPane.YES_OPTION)
		{
			System.exit(0);
		}else if(answer == JOptionPane.NO_OPTION){
			
		}
		
	}
}
