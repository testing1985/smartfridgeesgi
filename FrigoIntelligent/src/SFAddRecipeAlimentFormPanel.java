import java.awt.Choice;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SFAddRecipeAlimentFormPanel extends JPanel {

	int 	   m_iID;
	Choice 	   m_oUnite    = new Choice();
	JTextField m_oQuantity = new JTextField( "0" , 3);
	JTextField m_oName 	   = new JTextField( "Mon ingr�dient..." , 13);
	
	
	public SFAddRecipeAlimentFormPanel( int iID ) {
		super();
		
		m_iID = iID;
		
		m_oUnite.add("");
		m_oUnite.add("g");
		m_oUnite.add("cl");
		m_oUnite.add("1/2 sachets");
		m_oUnite.add("Cuilli�re � caf�");
		m_oUnite.add("Cuilli�re � soupe");
		m_oUnite.add("Pinc�e");
		m_oUnite.select("g");
		
		this.setBorder( BorderFactory.createEtchedBorder() );
		this.add( new JLabel("Ingr�dient n�" + m_iID + " : ") );
		this.add( m_oName );
		this.add( new JLabel(" Quantit�:") );
		this.add( m_oQuantity );
		this.add( m_oUnite );
	}
}
