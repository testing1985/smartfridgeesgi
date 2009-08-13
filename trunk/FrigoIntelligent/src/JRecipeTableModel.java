import javax.swing.table.DefaultTableModel;

@SuppressWarnings( "serial" )
public class JRecipeTableModel extends DefaultTableModel{
	
	public JRecipeTableModel(){
		addColumn( "ID" );
	    addColumn( "Nom" );
	    addColumn( "Type" );
	    addColumn( "Temps" );
	    addColumn( "Difficulté" );
	    
	}
	public boolean isCellEditable( int row, int col ){
		return false;
	}
}
