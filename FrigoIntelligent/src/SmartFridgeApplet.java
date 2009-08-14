import java.awt.Graphics;
import javax.swing.JApplet;
import javax.swing.SwingUtilities;

@SuppressWarnings( "serial" )
public class SmartFridgeApplet extends JApplet implements Runnable{
		
    public void init(){
    	super.init();
    	Runnable r = new Runnable(){
			public void run() { new SmartFridgeApp(); }
		};
		SwingUtilities.invokeLater(r);
    }

    public void run(){
		
	}
   
    /*public void run(){
    	while( true )
    		repaint();
	}

    public void start(){
    }

    public void update( Graphics g ){
    	paint( g );
    }
    
    public void paint( Graphics g ){
    	super.paint(g);
    }*/

}
