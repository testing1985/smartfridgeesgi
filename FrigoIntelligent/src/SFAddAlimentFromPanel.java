
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings( "serial" )
public class SFAddAlimentFromPanel extends JPanel {

	JComboBox  m_oUnite    = new JComboBox();
	JTextField m_oQuantity = new JTextField( "0" , 3);
	JTextField m_oName 	   = new JTextField( "Mon ingrédient..." , 13);
	
	public SFAddAlimentFromPanel(  ) {
		super();
	
		m_oUnite.addItem("");
		m_oUnite.addItem("g");
		m_oUnite.addItem("cl");
		m_oUnite.addItem("1/2 sachets");
		m_oUnite.setSelectedItem("g");
		
		this.setBorder( BorderFactory.createEtchedBorder() );
		this.add( new JLabel("Aliment : ") );
		this.add( m_oName );
		this.add( new JLabel(" Quantité:") );
		this.add( m_oQuantity );
		this.add( m_oUnite );
	}
	
	public JComboBox get_Unite() {
		return m_oUnite;
	}

	public void set_Unite(JComboBox mOUnite) {
		m_oUnite = mOUnite;
	}

	public JTextField get_Quantity() {
		return m_oQuantity;
	}

	public void set_Quantity(JTextField mOQuantity) {
		m_oQuantity = mOQuantity;
	}

	public JTextField get_Name() {
		return m_oName;
	}

	public void set_Name(JTextField mOName) {
		m_oName = mOName;
	}
		
}

