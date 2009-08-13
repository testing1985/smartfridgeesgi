import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings( "serial" )
public class SFSeeFridgeContentPanel extends JPanel implements ActionListener {

	SFWindow 	m_oParent;
	JButton 	m_oGoBackButton 			 = new JButton( "Retour" );
	JPanel 		m_oCenterPanel				 = new JPanel();
	
	JTable 		m_oTable					 = new JTable();
	JScrollPane m_oScrollPane				 = new JScrollPane();
	
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
    
	    m_oScrollPane = new JScrollPane( m_oTable );
		
	    add( m_oScrollPane, BorderLayout.CENTER );
		m_oGoBackButton.addActionListener( this );
		add( m_oGoBackButton , BorderLayout.SOUTH );
	}
	
	public void actionPerformed(ActionEvent e){
		if( e.getSource().equals( m_oGoBackButton ) ){	
			m_oParent.SeeRecipeAction();
		}
	}
	
}
