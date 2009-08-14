import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SmartFridgeAPI.Recipe;
import SmartFridgeAPI.SmartFridge;


@SuppressWarnings("serial")
public class SFAddRecipeToMenuPanel extends JPanel implements ActionListener {

	int m_iID;
	JComboBox   m_oRecipe     = new JComboBox();
	JLabel      m_oRecipeType = new JLabel();
	SmartFridge m_oSF 		  = null;
	
	public SFAddRecipeToMenuPanel( SmartFridge oSF , int iID ) {
		
		m_iID = iID;
		m_oSF = oSF;
		Vector<Recipe> vRecipes = oSF.getRecipes();
		for( int i = 0 ; i < vRecipes.size() ; i++ ) {
			m_oRecipe.addItem( vRecipes.elementAt(i).getName() );
		}
		
		add( new JLabel( "Recette n°" + iID + "     " ) );
		add( m_oRecipe );
		add( new JLabel( " - " ) );
		add( m_oRecipeType );
		if( ! vRecipes.isEmpty() )
			m_oRecipeType.setText( vRecipes.firstElement().getType() );
		
		m_oRecipe.addActionListener(this);
	}

	public void actionPerformed( ActionEvent e ) {
		if( e.getSource().equals( m_oRecipe ) ) {
			int iID = m_oRecipe.getSelectedIndex();
			m_oRecipeType.setText( m_oSF.getRecipes().elementAt(iID).getType() );
		}
	}
	
	
	
}
