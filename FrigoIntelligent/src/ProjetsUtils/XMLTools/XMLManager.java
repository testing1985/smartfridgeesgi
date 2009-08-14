package ProjetsUtils.XMLTools;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public final class XMLManager {

    private XMLManager() {}
    
    public static void encodeToFile(Object oObject, String sFileName) throws FileNotFoundException, IOException {
        XMLEncoder oEncoder = new XMLEncoder(new FileOutputStream(sFileName));
        try {
            oEncoder.writeObject(oObject);
            oEncoder.flush();
        } finally {
            oEncoder.close();
        }
    }
    
    public static Object decodeFromFile(String sFileName) throws FileNotFoundException, IOException {
    	XMLDecoder oDecoder = null;
    	Object 	   oObject  = null;
    	try {
    		oDecoder = new XMLDecoder( new FileInputStream( sFileName ) );
    		oDecoder.setExceptionListener(new ExceptionListener() {
    		    public void exceptionThrown(Exception ex) {}
    		});
	        oObject = oDecoder.readObject();
	    }  catch( IOException e ) {
	    	return null;
	    }
	    finally {
	    	if( oDecoder != null ) oDecoder.close();
	    }
        
        return oObject;
    }
}
