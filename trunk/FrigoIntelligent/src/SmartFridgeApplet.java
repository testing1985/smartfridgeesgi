import javax.swing.JApplet;

@SuppressWarnings( "serial" )
public class SmartFridgeApplet extends JApplet {
		
    public void init(){
    	super.init();
    	new SFWindow( true );
    }
}
