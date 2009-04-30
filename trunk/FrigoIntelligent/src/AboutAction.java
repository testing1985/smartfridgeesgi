

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AboutAction extends AbstractAction {
	
	public AboutAction( String text ){
		super( text );
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog( new JFrame(), "This Software has been developped by Julien Beaussier and Sébastien Libardi" );
	} 
}
