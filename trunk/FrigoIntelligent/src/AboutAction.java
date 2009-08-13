

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings( "serial" )
public class AboutAction extends AbstractAction {
	
	public AboutAction( String text ){
		super( text );
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog( new JFrame(), "Ce logiciel a été crée par Julien Beaussier and Sébastien Libardi" );
	} 
}
