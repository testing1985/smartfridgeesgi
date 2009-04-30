
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
public class Window extends JFrame implements ActionListener {

	public JPanel leftPanel, centerPanel, rightPanel;
	public JButton testButton1;
	public JList list;
	
	public Window(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuFile = new JMenu( "File" );

		JMenuItem newFile = new JMenuItem( "New" );
		JMenuItem quit = new JMenuItem( new QuitAction( "Quit" ) );
		
		menuFile.add( newFile );
		menuFile.add( quit );

		JMenu menuAliment = new JMenu( "Aliment" );
		JMenuItem alimentMenu = new JMenuItem( "New" );
		menuAliment.add( alimentMenu );
		
		JMenu menuRecipe = new JMenu( "Recipe" );
		JMenuItem newRecipe = new JMenuItem( "New" );
		menuRecipe.add( newRecipe );
		
		
		JMenu menuMenu = new JMenu( "Menu" );
		JMenuItem newMenu = new JMenuItem( "New" );
		menuMenu.add( newMenu );
		
		JMenu menuAbout = new JMenu( "About" );

		JMenuItem aboutMenu = new JMenuItem( new AboutAction( "About" ) );
		
		menuAbout.add( aboutMenu );
				
		menuBar.add( menuFile );
		menuBar.add( menuAliment );
		menuBar.add( menuRecipe );
		menuBar.add( menuMenu );
		menuBar.add( menuAbout );
		
		setJMenuBar( menuBar );

		
		setSize( 800, 600 );
		setLocation( 100, 100 );
		setLayout( new BorderLayout() );
	
		leftPanel = new JPanel();
		centerPanel = new JPanel();
		rightPanel = new JPanel();
		
		leftPanel.setPreferredSize( new Dimension( 100, 600 ) );
		centerPanel.setPreferredSize( new Dimension( 600, 600 ) );
		rightPanel.setPreferredSize( new Dimension( 100, 600 ) );
		
		centerPanel.setBackground( new Color( 255, 255, 255 ) );
		
		testButton1 = new JButton( "Vider le Frigo" );
		testButton1.setPreferredSize( new Dimension( 90, 25 ) );
		testButton1.addActionListener( this );
		
			
		list = new JList( );
		list.setPreferredSize( new Dimension( 90, 100 ) );
		
		leftPanel.add( testButton1 );
		leftPanel.add( list );
		
		add( leftPanel, BorderLayout.WEST );
		add( centerPanel, BorderLayout.CENTER );
		add( rightPanel, BorderLayout.EAST );
		
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
	
	

