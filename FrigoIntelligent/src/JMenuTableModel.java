import javax.swing.table.DefaultTableModel;


@SuppressWarnings( "serial" )
public class JMenuTableModel  extends DefaultTableModel{
	
	public JMenuTableModel(){
		addColumn( "ID" );
		addColumn( "Nom" );
	    addColumn( "Entrée" );
	    addColumn( "Plat" );
	    addColumn( "Dessert" );
	    addColumn( "Boisson" );
	    addColumn( "Amuse-gueule" );
	}
	
	public boolean isCellEditable( int row, int col ){
		return false;
	}
}
