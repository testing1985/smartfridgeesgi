package ProjetsUtils.XMLTools.RSSManager;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;


public class RSSManagerBeanInfo {

	private PropertyDescriptor[] pDescriptors;
    
    public RSSManagerBeanInfo() {
        try {
            PropertyDescriptor feedListDescriptor = new PropertyDescriptor("feedList", RSSManager.class, "getFeedList" , "setFeedList");
            pDescriptors = new PropertyDescriptor[] { feedListDescriptor };
            
        } catch(IntrospectionException ex) {
            ex.printStackTrace();
        }
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        return pDescriptors;
    }
	
}
