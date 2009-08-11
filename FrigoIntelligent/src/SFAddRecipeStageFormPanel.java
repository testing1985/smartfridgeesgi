import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class SFAddRecipeStageFormPanel extends JPanel {
	
	public int m_iID;
	public JTextArea m_oContent;
	
	SFAddRecipeStageFormPanel( int iID ) {
		super( new BorderLayout() );
		m_iID = iID;
		
		this.add( new JLabel( "Etape n°" + m_iID ) , BorderLayout.NORTH );
		m_oContent = new JTextArea();
		this.add( m_oContent , BorderLayout.CENTER );
	}
}
