package SmartFridgeAPI;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class MenuBeanInfo extends SimpleBeanInfo {
	
	private PropertyDescriptor[] pDescriptors;
	
	public MenuBeanInfo() {
		try {
			            
            PropertyDescriptor oListRecipeDescriptor = new PropertyDescriptor( "listRecipeID", Menu.class , "getListRecipeID" , "setListRecipeID" );
            PropertyDescriptor oMenuNameDescriptor   = new PropertyDescriptor( "name"		 , Menu.class , "getName" 		  , "setName" );
            PropertyDescriptor oRecipesDescriptor    = new PropertyDescriptor( "recipes"	 , Menu.class , "getRecipes" 	  , "setRecipes" );
            oRecipesDescriptor.setValue( "transient", Boolean.TRUE );
            
            pDescriptors = new PropertyDescriptor[] {
            		oListRecipeDescriptor ,
            		oMenuNameDescriptor ,
            		oRecipesDescriptor
            };
            
		} catch(IntrospectionException ex) {
            ex.printStackTrace();
        }
	}
	
	public PropertyDescriptor[] getPropertyDescriptors() {
        return pDescriptors;
    }
}
