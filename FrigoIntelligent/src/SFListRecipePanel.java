import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import SmartFridgeAPI.Recipe;

@SuppressWarnings("serial")
public class SFListRecipePanel extends JPanel implements ActionListener {
		
	public SFListRecipePanel( Recipe oRecipe ){
		super( new GridBagLayout() );
				
		GridBagConstraints c  = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 2;
		add( new JLabel( oRecipe.getName() ), c );
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		add( new JLabel( " Type : " + oRecipe.getType() ), c );
		
		c.gridx = 2;
		c.gridy = 0;
		add( new JLabel( " " + String.valueOf( oRecipe.getTime() ) + " minutes" ), c );
		
		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 0.5;
		add( new JLabel( " diffculté : " + String.valueOf( oRecipe.getDifficulty() ) ), c );
									
		c.gridx = 4;
		c.gridy = 0;
		c.weightx = 0.3;
		c.weighty = 0.3;
		add( new JButton( "Voir" ) );
	}
	
	 public void actionPerformed( ActionEvent e ){
		 System.out.println( "CLIC") ;
	 }
	
}
