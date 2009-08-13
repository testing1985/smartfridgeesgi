import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings( "serial" )
public class SFAddAlimentPanel extends JPanel implements ActionListener{

	SFWindow m_oParent;
	JButton m_oGoBackButton = new JButton( "Retour" );
	/*  m_sName = name;
		m_iQuantity = quantity;
		m_iPrice = price;
		m_iPeremption = peremption;
		m_sUnite = sUnite;
	*/
	JComboBox  m_oUnite    = new JComboBox();
	JTextField m_oQuantity = new JTextField( "0" , 3);
	JTextField m_oName 	   = new JTextField( "Mon ingrédient..." , 13);
	

	SFAddAlimentPanel(SFWindow oParent ){
		super( new BorderLayout() );
		m_oParent = oParent;
		setPreferredSize( new Dimension( 500 , 500 ) );
			
		m_oUnite.addItem("");
		m_oUnite.addItem("g");
		m_oUnite.addItem("cl");
		m_oUnite.addItem("1/2 sachets");
		m_oUnite.setSelectedItem("g");
		
		
		
		JPanel oCenterPanel = new JPanel();
		oCenterPanel.setLayout( new GridLayout( 3, 2 ) );
		
		
		oCenterPanel.add( new JLabel( "Ajout d'un aliment" ) );
		oCenterPanel.add( new JLabel() );
		oCenterPanel.add( new JLabel( "Nom :" ) );
		oCenterPanel.add( m_oName );
		oCenterPanel.add( m_oQuantity );
		oCenterPanel.add( m_oUnite );
		
		
		m_oGoBackButton.addActionListener( this );
		add( oCenterPanel, BorderLayout.NORTH );
		add( m_oGoBackButton, BorderLayout.SOUTH );
				
	}
	
	

	public void actionPerformed( ActionEvent e ) {
		if( e.getSource().equals( m_oGoBackButton ) ){	
			m_oParent.SeeRecipeAction();
		}		
	}
}
