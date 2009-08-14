

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class SFLoadFromInternetPanel extends JPanel implements ActionListener {
	
	SFWindow   m_oParent = null;
	JTextField m_oLogin  = null;
	JTextField m_oPasswd = null;
	JButton    m_oLoad   = new JButton( "Load" );
	
	public SFLoadFromInternetPanel( SFWindow oParent ) {
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
		
		m_oLoad.addActionListener( this );
		add( m_oLoad , BorderLayout.CENTER );
	}

	public void reset() {
		m_oLogin.setText("");
		m_oPasswd.setText("");
	}

	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource().equals( m_oLoad )) {
			String sLogin  = m_oLogin.getText();
			String sPasswd = m_oPasswd.getText();
			if( ! m_oParent.m_oSession.connect( sLogin , sPasswd ) ) {
				JOptionPane.showMessageDialog( this, "Cet utilisateur n'existe pas ou le mot de passe est incorrect !" , "Erreur de connexion" , JOptionPane.ERROR_MESSAGE );
			}
			else {
				m_oParent.loadFromInternet();
				m_oParent.listRecipeAction();
			}			
		}		
	}

}
