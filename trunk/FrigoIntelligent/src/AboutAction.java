

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings( "serial" )
public class AboutAction extends AbstractAction {
	
	public AboutAction( String text ){
		super( text );
	}
	
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog( new JFrame(), "Ce logiciel a �t� cr�e par Julien Beaussier and S�bastien Libardi" );
	} 
}
