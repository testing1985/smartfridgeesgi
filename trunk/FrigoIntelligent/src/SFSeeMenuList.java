import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import SmartFridgeAPI.Aliment;
import SmartFridgeAPI.Menu;
import SmartFridgeAPI.Recipe;

@SuppressWarnings( "serial" )
public class SFSeeMenuList extends JPanel implements ActionListener {

	SFWindow 	m_oParent;
	JButton 	m_oSeeMenu					 = new JButton( "Voir ce menu" );
	
	JTable 		m_oTable					 = new JTable();
	JScrollPane m_oScrollPane				 = new JScrollPane();
	
	SFSeeMenuList( SFWindow oParent ){
		super( new BorderLayout() );
		m_oParent = oParent;
		setPreferredSize( new Dimension( 500 , 500 ) );
		
		m_oTable.setModel( new JMenuTableModel() );
		m_oTable.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
		m_oTable.getTableHeader().setReorderingAllowed( false );
	    
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>( m_oTable.getModel() );
	    m_oTable.setRowSorter( sorter );
    
	    m_oScrollPane = new JScrollPane( m_oTable );
		
	    add( m_oScrollPane, BorderLayout.CENTER );
	    
	    m_oSeeMenu.addActionListener( this );
		
		add( m_oSeeMenu, BorderLayout.SOUTH );
	}
	
	public void Refresh(){
		DefaultTableModel model = (DefaultTableModel)m_oTable.getModel();
		
		int size = model.getRowCount();
		for( int i = 0; i < size; i++ ){
			model.removeRow( 0 );
		}
				
		size = m_oParent.m_oParent.m_oSmartFridge.getMenus().size();
		for( int i = 0; i < size; i++ ){
		    	Vector < String > menu = new Vector < String >();
		    	Menu m = m_oParent.m_oParent.m_oSmartFridge.getMenus().elementAt( i );
		    	int stages = m.getRecipes().size();
		    	int entree = 0, plat = 0, dessert = 0, boisson = 0, AmuseGueule = 0;
		    	for( int j = 0; j < stages; j++ ){
		    		Recipe r = m.getRecipes().elementAt( j );
		    		if( r.getType().equals( "Entrée" ) )
		    			entree++;
		    		else if( r.getType().equals( "plat" ) )
		    			plat++;
		    		else if( r.getType().equals( "dessert" ) )
		    			dessert++;
		    		else if( r.getType().equals( "boisson" ) )
		    			boisson++;
		    		else if( r.getType().equals( "Amuse-gueule" ) )
		    			AmuseGueule++;
		    	}
		    	menu.addElement( "" + ( i + 1 ) );
		    	menu.addElement( m.getName() );
		    	menu.addElement( "" + entree );
		    	menu.addElement( "" + plat );
		    	menu.addElement( "" + dessert );
		    	menu.addElement( "" + boisson );
		    	menu.addElement( "" + AmuseGueule );
		    	
				model.addRow( menu );
		}
	}
	
	public JTable getTable() {
		return m_oTable;
	}

	public void setTable(JTable mOTable) {
		m_oTable = mOTable;
	}
	
	public void actionPerformed(ActionEvent e){
		if( e.getSource().equals( m_oSeeMenu ) ){	
			m_oParent.m_oParent.m_oApp.SeeMenuAction();
		}
	}
}

