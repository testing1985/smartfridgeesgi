import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.util.Vector;

@SuppressWarnings("serial")
public class SFAddRecipeStageFormPanel extends JPanel /*implements ListCellRenderer*/ {
	
	public int m_iID;
	public JTextArea  m_oContent    = new JTextArea( 4 , 40 );
	public Choice 	  m_oDifficulte = new Choice();
	public JTextField m_oDuree      = new JTextField( "15" , 3 );
	
	SFAddRecipeStageFormPanel( int iID ) {
		super( new BorderLayout() );
		m_iID = iID;
		setBorder( BorderFactory.createEtchedBorder() );		
		
		JPanel oNorth = new JPanel();
		oNorth.add( new JLabel("Etape n°" + m_iID + "                   " ) );
		
		oNorth.add( new JLabel( "Difficulté : ") );
		for( int i = 0 ; i < 5 ; i++ )
			m_oDifficulte.add("" + (i+1) );
		oNorth.add( m_oDifficulte );		
		
		oNorth.add( new JLabel("            Durée : ") );
		oNorth.add( m_oDuree );
		oNorth.add( new JLabel("(min)") );
		this.add( oNorth , BorderLayout.NORTH );
		
		m_oContent.setSize( 490 , 50 );
		this.add( new JScrollPane(m_oContent) , BorderLayout.CENTER );
	}
}
