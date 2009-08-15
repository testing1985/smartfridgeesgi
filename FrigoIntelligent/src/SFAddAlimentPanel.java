import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import SmartFridgeAPI.Aliment;


@SuppressWarnings( "serial" )
public class SFAddAlimentPanel extends JPanel implements ActionListener{

	SFWindow m_oParent;
	JButton m_oValidateAliment = new JButton( "Valider" );
	
	SFAddAlimentFromPanel m_oPanel;
	
	SFAddAlimentPanel( SFWindow oParent ){
		super( new BorderLayout() );
		m_oParent = oParent;
		setPreferredSize( new Dimension( 500 , 500 ) );
					
		JPanel oCenterPanel = new JPanel();
		oCenterPanel.setLayout( new BorderLayout() );
		
		m_oPanel = new SFAddAlimentFromPanel();
		
		oCenterPanel.add( m_oPanel, BorderLayout.CENTER );
		oCenterPanel.add( m_oValidateAliment, BorderLayout.SOUTH );
		
		m_oValidateAliment.addActionListener( this );
		add( oCenterPanel, BorderLayout.NORTH );
				
	}
	
	public void reset(){
		m_oPanel.get_Unite().setSelectedItem( "g" );
		m_oPanel.get_Quantity().setText( "0" );
		m_oPanel.get_Name().setText( "Mon ingrédient..." );
	}
	
	public void actionPerformed( ActionEvent e ) {
		if( e.getSource().equals( m_oValidateAliment ) ){	
			
			if( m_oPanel.m_oName.getText().trim().equals("") ) {
				JOptionPane.showMessageDialog( this , "Vous n'avez pas mis de nom à l'aliment !" , "Erreur de vérification du formulaire" , JOptionPane.ERROR_MESSAGE );
				return;
			}
			
			try {
				Aliment oAliment = new Aliment();
				oAliment.setName( m_oPanel.get_Name().getText() );
				oAliment.setQuantity( Integer.parseInt( m_oPanel.get_Quantity().getText() ) );
				oAliment.setPrice( 1.0f );
				oAliment.setPeremption( 0 );
				oAliment.setUnite( (String)m_oPanel.get_Unite().getSelectedItem() );				
				m_oParent.m_oSmartFridge.getAliments().addElement( oAliment );
				reset();
			} catch ( NumberFormatException ex ) {
				JOptionPane.showMessageDialog( this , "Vérifiez les champs numériques !" , "Erreur de vérification du formulaire" , JOptionPane.ERROR_MESSAGE );
			}
		}	
	}
}
