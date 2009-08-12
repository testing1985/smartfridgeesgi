package SmartFridgeAPI;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import ProjetsUtils.XMLTools.RSSManager.RSSManager;

public class SmartFridgeBeanInfo {
	
	private PropertyDescriptor[] pDescriptors;
	
	public SmartFridgeBeanInfo() {
		try {
            Class<SmartFridge> c = SmartFridge.class;
            
            PropertyDescriptor recipeListDescriptor = new PropertyDescriptor( "recipeArray" , SmartFridge.class , "getRecipeArray" , "setRecipeArray" );
           
            pDescriptors = new PropertyDescriptor[] {
            		recipeListDescriptor
            };
            
        } catch(IntrospectionException ex) {
            ex.printStackTrace();
        }
	}
	
	public PropertyDescriptor[] getPropertyDescriptors() {
        return pDescriptors;
    }

}
