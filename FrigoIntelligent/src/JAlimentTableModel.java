import javax.swing.table.DefaultTableModel;

@SuppressWarnings( "serial" )
public class JAlimentTableModel extends DefaultTableModel{
	
	public JAlimentTableModel(){
		addColumn( "ID" );
		addColumn( "Nom" );
	    addColumn( "Quantité" );
	    addColumn( "Prix" );
	    addColumn( "Peremption" );
	}
	
	public boolean isCellEditable( int row, int col ){
		return false;
	}
	
}
