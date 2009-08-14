import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
			m_oParent.m_oSmartFridge.getAliments().addElement( 
			new Aliment( 	m_oPanel.get_Name().getText(), 
							Integer.parseInt( m_oPanel.get_Quantity().getText() ), 
							(float)1.0, 
							0,
							(String)m_oPanel.get_Unite().getSelectedItem() )
			
			);
			reset();
		}	
	}
}
