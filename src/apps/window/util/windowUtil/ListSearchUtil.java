package apps.window.util.windowUtil;

import java.awt.event.KeyEvent;
import java.util.Vector;
import java.awt.*;

import java.awt.event.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ListSearchUtil {
	
	private JTextField jTextField0;
	private JList jList0;
	private JScrollPane jScrollPane0;

	
	String test = "";
	
	
	public ListSearchUtil(DefaultListModel listModel,final JList list,final JTextField textfield,final JScrollPane jScrollPane0) {
		
		list.setModel(listModel);
		textfield.addKeyListener(new KeyAdapter() {

		    @Override
		    public void keyTyped(KeyEvent e) {
		        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
		            // Do stuff
		        	if(list.getSelectedIndex() == -1) {
		        		textfield.setText("");
		        		return;
		        	}
		        	textfield.setText(list.getSelectedValue().toString());
		        	list.setVisible(false);
		        	jScrollPane0.setVisible(false);
		        	test = "";
		        } else {
		        	jScrollPane0.setVisible(true);
		        	
		        	list.setVisible(true);
		        	keyPressHandler(e,list);
		        	//search(textfield.getText(),list);
		        }
		    } @Override
		    public void keyPressed(KeyEvent key) {
		    	boolean found = false;
		    	if(key.getKeyCode()==8)
			    {
		    		
			        if(test.length() > 0)
			    		test = test.substring(0, test.length()-1);
			       
			        for (int i=0; i < list.getModel().getSize(); i++) {    
				    	String str = ((String)list.getModel().getElementAt(i)).toLowerCase();  
				    	
				    	if(str.trim().equalsIgnoreCase(test.trim())) {
				    		
				    		list.setSelectedIndex(i);
				    		list.ensureIndexIsVisible(i);
				    		found = true;
				    		break;
				    	} 
			        }
			        if(!found) {
			        	for (int i=0; i < list.getModel().getSize(); i++) {    
					    	String str = ((String)list.getModel().getElementAt(i)).toLowerCase();  
					    	if(str.startsWith(test)) {  
					    		list.setSelectedIndex(i);
					    		list.ensureIndexIsVisible(i);
					    		break;  
					    	}
			        	}
			        }
				    	
			    
			    	
			    }
		    	
		    	
		    }
		    
		}); 
		
		
		
		
	}
	private void keyPressHandler(java.awt.event.KeyEvent evt,JList list) {    
		char ch = evt.getKeyChar(); 
		long m_time;
		String m_key ="";
		//ignore searches for non alpha-numeric characters   
		if (!Character.isLetterOrDigit(ch)) {       
			return;    
			}     // reset string if too much time has elapsed   
		//if (m_time+CHAR_DELTA < System.currentTimeMillis()) {  
		//	m_key = "";   
			//}   
		    m_time = System.currentTimeMillis();  
		    m_key += Character.toLowerCase(ch);   
		    test  = test + Character.toLowerCase(ch);  
		    // Iterate through items in the list until a matching prefix is found.    
		    // This technique is fine for small lists, however, doing a linear   
		    // search over a very large list with additional string manipulation    
		    // (eg: toLowerCase) within the tight loop would be quite slow.    // In that case, pre-processing the case-conversions, and storing the  
		    // strings in a more search-efficient data structure such as a Trie    // or a Ternary Search Tree would lead to much faster find.   
		    for (int i=0; i < list.getModel().getSize(); i++) {    
		    	String str = ((String)list.getModel().getElementAt(i)).toLowerCase();       
		    	if (str.startsWith(test)) {       
		    		list.setSelectedIndex(i);
		    		//break;//change selected item in list          
		    		list.ensureIndexIsVisible(i);
		    		//change listbox scroll-position           
		    		break;       
		    		}    
		    	
		    }
	}
	private void search(String text,JList list) {  
		DefaultListModel model = (DefaultListModel)list.getModel();     
		// Case–sensitive.    
		
		if(model.contains(text)) {   
			list.setSelectedValue(text, true);
			int index = model.indexOf(text);   
			
			list.setSelectedIndex(index);       
		//	label.setText(text + " found at index " + index);    
			} else {         
				list.clearSelection();    
				//jList0.setText(text + " not found");     
				}   
		}

}
