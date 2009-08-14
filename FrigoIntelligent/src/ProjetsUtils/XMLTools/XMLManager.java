package ProjetsUtils.XMLTools;

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
        Object oObject = null;
        XMLDecoder oDecoder = new XMLDecoder(new FileInputStream(sFileName));
        try {
            oObject = oDecoder.readObject();
        } finally {
            oDecoder.close();
        }
        return oObject;
    }
}
