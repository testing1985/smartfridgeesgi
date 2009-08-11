import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;


public class SFAddRecipeStageFormPanel extends JPanel /*implements ListCellRenderer*/ {
	
	public int m_iID;
	public JTextArea m_oContent;
	public JList m_lDifficulte;
	
	SFAddRecipeStageFormPanel( int iID ) {
		super( new BorderLayout() );
		m_iID = iID;
		
		this.add( new JLabel( "Etape n°" + m_iID ) , BorderLayout.NORTH );
		m_oContent = new JTextArea( 3 , 40 );
		m_oContent.setSize( 490 , 50 );
		this.add( m_oContent , BorderLayout.CENTER );
	}
}
