import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import SmartFridgeAPI.Recipe;

@SuppressWarnings("serial")
public class SFListRecipePanel extends JPanel {
		
	public SFListRecipePanel( Recipe oRecipe ){
		int etape = oRecipe.getRecipeStages().size();
		int duree = 0, difficulte = 0;	
		for( int i = 0; i < etape; i++ ){
			duree += oRecipe.getRecipeStages().elementAt( i ).getTime();
			
			if( oRecipe.getRecipeStages().elementAt( i ).getDifficulty() > difficulte )
				difficulte = oRecipe.getRecipeStages().elementAt( i ).getDifficulty();
		
		}
		
		setLayout( new BorderLayout() );
		add( new JLabel( oRecipe.getName() +
						" Type : " + oRecipe.getType() +
						" " + String.valueOf( duree ) + " minutes" +
						" diffculté maximale : " + String.valueOf( difficulte )
						), BorderLayout.CENTER  );		
		
	}
}
