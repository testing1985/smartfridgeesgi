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

@SuppressWarnings( "serial" )
public class SFSeeFridgeContentPanel extends JPanel implements ActionListener {

	SFWindow 	m_oParent;
	JButton 	m_oDeleteAlimentButton 	  	 = new JButton( "Supprimer un aliment" );
	JPanel 		m_oCenterPanel				 = new JPanel();
	
	JTable 		m_oTable					 = new JTable();
	
	SFSeeFridgeContentPanel( SFWindow oParent ){
		super( new BorderLayout() );
		m_oParent = oParent;
		setPreferredSize( new Dimension( 500 , 500 ) );
		
		m_oCenterPanel.setLayout( new BorderLayout() );
		
		m_oTable.setModel( new JAlimentTableModel() );
		m_oTable.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
		m_oTable.getTableHeader().setReorderingAllowed( false );
	    
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>( m_oTable.getModel() );
	    m_oTable.setRowSorter( sorter );
    
	    add( new JScrollPane( m_oTable ), BorderLayout.CENTER );
		m_oDeleteAlimentButton.addActionListener( this );
		add( m_oDeleteAlimentButton , BorderLayout.SOUTH );
	}
	
	public void Refresh(){
		DefaultTableModel model = (DefaultTableModel)m_oTable.getModel();
		
		int size = model.getRowCount();
		for( int i = 0; i < size; i++ ){
			model.removeRow( 0 );
		}
		
		size = m_oParent.m_oSmartFridge.getAliments().size();
		for( int i = 0; i < size; i++ ){
		    	Vector < String > aliment = new Vector < String >();
		    	Aliment a = m_oParent.m_oSmartFridge.getAliments().elementAt( i );
		    	aliment.addElement( "" + ( i + 1 ) );
		    	aliment.addElement( a.getName() );
		    	aliment.addElement( a.getQuantity() + " " +  a.getUnite() );
		    	aliment.addElement( "" + a.getPrice() + "�" );
		    	aliment.addElement( "" + a.getPeremption() );
			  				    	
				model.addRow( aliment );
		}
	}
	
	public void actionPerformed(ActionEvent e){
		if( e.getSource().equals( m_oDeleteAlimentButton ) ){	
			if( m_oParent.m_oSmartFridge.getAliments().size() > 0 && m_oTable.getSelectedRow() != -1 ) {
				m_oParent.m_oSmartFridge.getAliments().remove( m_oTable.getSelectedRow() );
				Refresh();
			}
		}
	}
	
}
