import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
public class SearchTest {  
	JList list;   
	JLabel label;    
	private void search(String text) {  
		DefaultListModel model = (DefaultListModel)list.getModel();     
		// Case–sensitive.    
		
		if(model.contains(text)) {   
			list.setSelectedValue(text, true);
			int index = model.indexOf(text);   
			
			list.setSelectedIndex(index);       
			label.setText(text + " found at index " + index);    
			} else {         
				list.clearSelection();    
				label.setText(text + " not found");     
				}   
		}  
	private JScrollPane getListComponent() {     
		String[] items = { "Bob", "Ted", "Carol", "Alice","pppo","king","jay","pank","king3","ling","linong","jayu","second","firt","hart","kin" };    
		DefaultListModel model = new DefaultListModel();      
		for(int j = 0; j < items.length; j++)           
			model.add(j, items[j]);      
		list = new JList(model);      
		list.setVisible(false);
		return new JScrollPane(list);   
		}   
	private void keyPressHandler(java.awt.event.KeyEvent evt) {    
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
		    // Iterate through items in the list until a matching prefix is found.    
		    // This technique is fine for small lists, however, doing a linear   
		    // search over a very large list with additional string manipulation    
		    // (eg: toLowerCase) within the tight loop would be quite slow.    // In that case, pre-processing the case-conversions, and storing the  
		    // strings in a more search-efficient data structure such as a Trie    // or a Ternary Search Tree would lead to much faster find.   
		    for (int i=0; i < list.getModel().getSize(); i++) {    
		    	String str = ((String)list.getModel().getElementAt(i)).toLowerCase();       
		    	if (str.startsWith(m_key)) {       
		    		list.setSelectedIndex(i);     //change selected item in list            jList1.ensureIndexIsVisible(i); //change listbox scroll-position            break;        }     }}
		    	}
		    }
	}
	private JPanel getFirst() {      
		final JTextField textField = new JTextField(12);     
		textField.addKeyListener(new KeyAdapter() {

		    @Override
		    public void keyTyped(KeyEvent e) {
		        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
		            // Do stuff
		        	list.setVisible(false);
		        } else {
		        	keyPressHandler(e);
		        	list.setVisible(true);
		        	search(textField.getText());
		        }
		    }
		});
		JButton button = new JButton("Search");       
		ActionListener al = new ActionListener() {       
			public void actionPerformed(ActionEvent e) {  
			
				String text = textField.getText();               
				search(text);       
				}        
			};      
			textField.addActionListener(al);     
			button.addActionListener(al);     
			JPanel panel = new JPanel();     
			panel.add(textField);     
			panel.add(button);     
			return panel;   
			}    
	private JLabel getLabel() {     
		label = new JLabel();        
		label.setHorizontalAlignment(JLabel.CENTER);   
		Dimension d = label.getPreferredSize();      
		d.height = 25;    
		label.setPreferredSize(d);       
		return label;    
		}     
	public static void main(String[] args) {     
		SearchTest test = new SearchTest();      
		JFrame f = new JFrame();      
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		f.add(test.getListComponent());      
		f.add(test.getFirst(), "First");      
		f.add(test.getLabel(), "Last");     
		f.setSize(360,240);     
		f.setLocation(200,200);   
		f.setVisible(true); 
		}
	}
	
