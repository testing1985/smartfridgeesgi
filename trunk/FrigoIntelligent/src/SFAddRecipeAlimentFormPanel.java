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
	JTextField m_oName 	   = new JTextField( "Mon ingrédient..." , 13);
	
	
	public SFAddRecipeAlimentFormPanel( int iID ) {
		super();
		
		m_iID = iID;
		
		m_oUnite.add("");
		m_oUnite.add("g");
		m_oUnite.add("cl");
		m_oUnite.add("1/2 sachets");
		m_oUnite.add("Cuillière à café");
		m_oUnite.add("Cuillière à soupe");
		m_oUnite.add("Pincée");
		m_oUnite.select("g");
		
		this.setBorder( BorderFactory.createEtchedBorder() );
		this.add( new JLabel("Ingrédient n°" + m_iID + " : ") );
		this.add( m_oName );
		this.add( new JLabel(" Quantité:") );
		this.add( m_oQuantity );
		this.add( m_oUnite );
	}
}
