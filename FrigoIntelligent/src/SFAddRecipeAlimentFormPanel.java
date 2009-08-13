import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SFAddRecipeAlimentFormPanel extends JPanel {

	int 	   m_iID;
	JComboBox  m_oUnite    = new JComboBox();
	JTextField m_oQuantity = new JTextField( "0" , 3);
	JTextField m_oName 	   = new JTextField( "Mon ingr�dient..." , 13);
	
	
	public SFAddRecipeAlimentFormPanel( int iID ) {
		super();
		
		m_iID = iID;
		
		m_oUnite.addItem("");
		m_oUnite.addItem("g");
		m_oUnite.addItem("cl");
		m_oUnite.addItem("1/2 sachets");
		m_oUnite.addItem("Cuilli�re � caf�");
		m_oUnite.addItem("Cuilli�re � soupe");
		m_oUnite.addItem("Pinc�e");
		m_oUnite.setSelectedItem("g");
		
		this.setBorder( BorderFactory.createEtchedBorder() );
		this.add( new JLabel("Ingr�dient n�" + m_iID + " : ") );
		this.add( m_oName );
		this.add( new JLabel(" Quantit�:") );
		this.add( m_oQuantity );
		this.add( m_oUnite );
	}
}
