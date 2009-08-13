import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SFSaveOnInternetFormPanel extends JPanel implements ActionListener {
	
	SFWindow   m_oParent = null;
	JTextField m_oLogin  = null;
	JTextField m_oPasswd = null;
	JButton    m_oSave   = new JButton( "Save" );
	
	public SFSaveOnInternetFormPanel( SFWindow oParent ) {
		super( new BorderLayout() );
		m_oParent = oParent;		
		m_oLogin  = new JTextField( "" , 15 );
		m_oPasswd = new JTextField( "" , 15 );
		
		reset();
		
		JPanel oNorth = new JPanel( new GridLayout(2,2) );
		oNorth.add( new JLabel( "Login : " ) );
		oNorth.add( m_oLogin );
		oNorth.add( new JLabel( "Password : " ) );
		oNorth.add( m_oPasswd );		
		add( oNorth , BorderLayout.NORTH );
		
		m_oSave.addActionListener( this );
		add( m_oSave , BorderLayout.CENTER );
	}
	
	public void reset() {
		m_oLogin.setText("");
		m_oPasswd.setText("");
	}

	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource().equals( m_oSave )) {
			String sLogin  = m_oLogin.getText();
			String sPasswd = m_oPasswd.getText();
			if( ! m_oParent.m_oParent.m_oSession.connect( sLogin , sPasswd ) ) {
				System.out.println("Identifiant incorrect");
			}
			else {
				System.out.println(m_oLogin.getText() + " connected" );
				m_oParent.m_oParent.saveOnInternet();
				m_oParent.listRecipeAction();
			}
			
		}
		
	} 
}
