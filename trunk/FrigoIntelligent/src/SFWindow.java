
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class SFWindow extends JFrame implements ActionListener {

	SmartFridgeApp m_oParent;
	public JPanel leftPanel, centerPanel, rightPanel;
	public JButton testButton1;
	public JList list;
	
	public SFWindow( SmartFridgeApp oSF ){
		m_oParent = oSF;
		FileAction oFileAction = new FileAction( m_oParent );
		
		JMenuBar menuBar = new JMenuBar();
		
		// BEGIN - MENU FILE
		JMenu menuFile = new JMenu( "File" );
		
			// BEGIN - NEW MENU
		JMenu newMenu  = new JMenu( "New" );
		JMenuItem newRecipeMI = new JMenuItem(" New recipe");
		newRecipeMI.addActionListener( oFileAction );		
		newMenu.add( newRecipeMI );
		
		JMenuItem newMenuMI = new JMenuItem("New menu");
		newMenuMI.addActionListener( oFileAction );		
		newMenu.add( newMenuMI );
				
		menuFile.add( newMenu );
			// END - NEW MENU

		JMenuItem quitMI = new JMenuItem("Quit");
		quitMI.addActionListener( oFileAction );		
		menuFile.add( quitMI );
		// END - MENU FILE
		
				
		menuBar.add( menuFile );		
		setJMenuBar( menuBar );
		
		setSize( 800, 600 );
		setLocation( 100, 100 );
		setLayout( new BorderLayout() );
		
		JPanel oListeRecettesPanel = new JPanel();
		oListeRecettesPanel.setPreferredSize( new Dimension( 500 , 500 ));
		
		this.add( oListeRecettesPanel , BorderLayout.CENTER );
		
		setResizable( false );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setVisible( true );
		pack();
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if( e.getSource() == testButton1 ){
			
		}		
	}
	
}
	
	

