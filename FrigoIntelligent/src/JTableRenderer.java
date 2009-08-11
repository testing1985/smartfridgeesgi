import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class JTableRenderer implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable arg0, Object value, boolean isSelected, boolean arg3, int arg4, int arg5) {
        return (Component)value;
	}
}



/*



class MessageListTableCellRenderer implements TableCellRenderer {  
	  public Component getTableCellRendererComponent(JTable table,  
	    Object value, boolean isSelected, boolean hasFocus, int line, int column) {  
	     
              //as a column can be a JButton or a JLabel I define a Component 
              Component comp = null; 
	     
	    //if it's the first column and there is a chained msg i put a button 
	    if((table.getColumnName(column).equals("Chained Msgs")) && (value.toString().equalsIgnoreCase(" + ")))  
	    {  
	    	button.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent evt) { 
					chaineMsgActionPerformed(evt); 
				} 
			}); 
  		button.setVisible(true); 
  		button.setEnabled(true); 
	    	 
  		//in this case comp is a JButton 
	    	comp = button; 
	    } 
	    else 
	    { 
	    	//i define the colors of the content 
	    	//if the line is selected the severity is a little darker and the rest is light blue 
	    	if (isSelected == true) 
	    	{ 
	    		//i define the colors of the severity column 
	    		if(table.getColumnName(column).equals("Severity"))  
		    	{ 
			    	label.setHorizontalAlignment(SwingConstants.CENTER);  
			    	 
			    	label.setBackground(Temp.severityColorsSelected[i][0]);    //this is a table of Colors 
			      	label.setForeground(Temp.severityColorsSelected[i][1]); 
		    	} 
		    	else 
		    	{ 
		    		label.setBackground( new java.awt.Color(157, 206, 255)); 
			    	label.setForeground( new java.awt.Color(0, 0, 0)); 
		    	} 
	    	} 
	    	//if the line is selected the severity is as defined in the class Message and the rest is white		    	 
	    	else 
	    	{ 
	    		//i define the colors of the severity column 
	    		if(table.getColumnName(column).equals("Severity"))  
		    	{   
			    	label.setHorizontalAlignment(SwingConstants.CENTER);  
			    	 
			    	label.setBackground(Temp.severityColors[i][0]); 
			      	label.setForeground(Temp.severityColors[i][1]); 
		    	} 
		    	else 
		    	{ 
		    		label.setBackground( new java.awt.Color(255, 255, 255)); 
			    	label.setForeground( new java.awt.Color(0, 0, 0)); 
		    	} 
	    	} 
	    			    	 
	    	// JLable est transparent pas défaut  
		    label.setOpaque(true);  

		    label.setText(value.toString()); 	 
		  //in this case comp is a JLabel 
	    	comp = label; 
	    } 	     

	    //i return the component 
	    return comp;  
	  }  
	   
	  private JLabel label = new JLabel();  
	  private JButton button = new JButton(" + "); 
	}*/