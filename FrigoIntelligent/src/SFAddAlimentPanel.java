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

import SmartFridgeAPI.Aliment;


@SuppressWarnings( "serial" )
public class SFAddAlimentPanel extends JPanel implements ActionListener{

	SFWindow m_oParent;
	JButton m_oGoBackButton = new JButton( "Retour" );
	JButton m_oValidateAliment = new JButton( "Valider" );

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
		oCenterPanel.setLayout( new GridLayout( 4, 2 ) );
		
		oCenterPanel.add( new JLabel( "Ajout d'un aliment" ) );
		oCenterPanel.add( new JLabel() );
		oCenterPanel.add( new JLabel( "Nom :" ) );
		oCenterPanel.add( m_oName );
		oCenterPanel.add( m_oQuantity );
		oCenterPanel.add( m_oUnite );
		oCenterPanel.add( m_oValidateAliment );
		
		m_oGoBackButton.addActionListener( this );
		m_oValidateAliment.addActionListener( this );
		add( oCenterPanel, BorderLayout.NORTH );
		add( m_oGoBackButton, BorderLayout.SOUTH );
				
	}
	
	public void reset(){
		m_oUnite.setSelectedItem("g");
		m_oQuantity.setText( "0" );
		m_oName.setText( "Mon ingrédient..." );
	}
	
	public void actionPerformed( ActionEvent e ) {
		if( e.getSource().equals( m_oValidateAliment ) ){	
			m_oParent.m_oParent.m_oSmartFridge.getAliments().addElement( new Aliment( m_oName.getText(), Integer.parseInt( m_oQuantity.getText() ), (float)1.0, 0, (String)m_oUnite.getSelectedItem() )	);
			reset();
		}	
		if( e.getSource().equals( m_oGoBackButton ) ){	
			m_oParent.SeeRecipeAction();
		}		
	}
}
