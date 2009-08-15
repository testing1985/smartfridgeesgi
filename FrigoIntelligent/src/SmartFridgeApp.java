import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SmartFridgeApp {
	public  SFWindow     m_oApp;
	
	public static void main( String[] args )
	{	
		Runnable r = new Runnable(){
			public void run() { new SmartFridgeApp(); }
			
		};
		SwingUtilities.invokeLater(r);
	}
	
	public SmartFridgeApp()
	{
		m_oApp 		   = new SFWindow( false );
		m_oApp.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
}
