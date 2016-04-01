import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.NumericTextField;
import util.commonUTIL;
import util.common.DateU;
import util.common.NumberFormatUtil;

import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.combobox.DateSpinnerComboBox;
import com.jidesoft.combobox.TableExComboBox;
import com.jidesoft.combobox.TableExComboBoxSearchable;
import com.jidesoft.converter.ConverterContext;
import com.jidesoft.grid.ContextSensitiveTableModel;
import com.jidesoft.grid.EditorContext;
import com.jidesoft.grid.SortableTable;
import com.jidesoft.swing.JideBoxLayout;


//VS4E -- DO NOT REMOVE THIS LINE!
public class testsearch extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField jTextField0;
	private JList jList0;
	private JScrollPane jScrollPane0;
	private JPanel jPanel0;

	public testsearch() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(58, 405, 10, 10), new Leading(31, 367, 10, 10)));
		setSize(510, 416);
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setText("jTextField1");
		}
		return jTextField1;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getDate(), new Constraints(new Bilateral(5, 12, 4), new Leading(10, 29, 10, 10)));
		}
		return jPanel1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJTableList(), new Constraints(new Leading(9, 203, 10, 10), new Leading(227, 62, 10, 10)));
			jPanel0.add(getDate(), new Constraints(new Leading(23, 292, 10, 10), new Leading(24, 40, 10, 10)));
		}
		return jPanel0;
	}

	public JPanel getJTableList() {
		TableExComboBox tableComboBox2 = new TableExComboBox(
				new QuoteTableModel()) {
			@Override
			protected JTable createTable(TableModel model) {
				return new SortableTable(model);
			}
		};
		tableComboBox2.setEditable(true);
		tableComboBox2.setEnabled(true);
		tableComboBox2.setValueColumnIndex(1);
		tableComboBox2.setSelectedItem("ALCOA INC");
		
		tableComboBox2.setEditable(false);
		new TableExComboBoxSearchable(tableComboBox2);
		
		return createTitledComponent("TableExComboBox",
				"ExComboBox which has a table as the selection list",
				tableComboBox2);
		
	}
	static public Date checkDate(String s,Date date) {
	      int idx = s.indexOf("d");
	      if (idx == -1)
	          idx = s.indexOf("D");
	      if (idx > 0) {
	          int days = commonUTIL.converStringToInteger(s.substring(0, idx));
	          
	        	 
	   			DateU dated  = 	DateU.valueOf(date);
	   			dated.addDays(days);
	   			
	              //return Util.numberToString(m*1000.);
	          return dated.getDate();
	      }
	       idx = s.indexOf("w");
	      if (idx == -1)
	          idx = s.indexOf("W");
	      if (idx > 0) {
	    	  int days = commonUTIL.converStringToInteger(s.substring(0, idx));
	          
	        	 
	   			DateU dated  = 	DateU.valueOf(date);
	   			dated.addDays(days * 7);
	   			
	              //return Util.numberToString(m*1000.);
	          return dated.getDate();
	      }
	      idx = s.indexOf("m");
	      if (idx == -1)
	          idx = s.indexOf("M");
	      if (idx > 0) {
	    	  int days = commonUTIL.converStringToInteger(s.substring(0, idx));
	          
	        	 
	   			DateU dated  = 	DateU.valueOf(date);
	   			dated.addMonths(days);
	   			
	              //return Util.numberToString(m*1000.);
	          return dated.getDate();
	      }
	      idx = s.indexOf("y");
	      if (idx == -1)
	          idx = s.indexOf("Y");
	      if (idx > 0) {
	    	  int days = commonUTIL.converStringToInteger(s.substring(0, idx));
	          
	        	 
	   			DateU dated  = 	DateU.valueOf(date);
	   			dated.addYears(days);
	   			
	              //return Util.numberToString(m*1000.);
	          return dated.getDate();
	      }
	      
	     
	      return date;
	  }
	private JPanel createTitledComponent(String label, String toolTip,
			JComponent component) {
		JPanel panel = new JPanel();
		panel.setLayout(new JideBoxLayout(panel, JideBoxLayout.Y_AXIS, 3));
		component.setToolTipText(toolTip);
		component.setName(label);
		panel.add(new JLabel(label));
		panel.add(component);
		panel.add(Box.createGlue(), JideBoxLayout.VARY);
		return panel;
	}


	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJList0());
			jScrollPane0.setAutoscrolls(false);
			jList0.setAutoscrolls(false);
			jScrollPane0.setVisible(true);
			jScrollPane0.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane0.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		}
		return jScrollPane0;
	}

	static String[] QUOTE_COLUMNS = new String[] { "Symbol", "Name", "Last",
		"Change", "Volume" };
static Object[][] QUOTES = new Object[][] {
		new Object[] { "AA", "ALCOA INC", "32.88", "+0.53 (1.64%)",
				"4156200" },
		new Object[] { "AIG", "AMER INTL GROUP", "69.53", "-0.58 (0.83%)",
				"4369200" },
		new Object[] { "AXP", "AMER EXPRESS CO", "48.90", "-0.35 (0.71%)",
				"4103600" },
		new Object[] { "BA", "BOEING CO", "49.14", "-0.18 (0.36%)",
				"3573700" },
		new Object[] { "C", "CITIGROUP", "44.21", "-0.89 (1.97%)",
				"28594900" },
		new Object[] { "CAT", "CATERPILLAR INC", "79.40", "+0.62 (0.79%)",
				"1458200" },
		new Object[] { "DD", "DU PONT CO", "42.62", "-0.14 (0.33%)",
				"1832700" },
		new Object[] { "DIS", "WALT DISNEY CO", "23.87", "-0.32 (1.32%)",
				"4443600" },
		new Object[] { "GE", "GENERAL ELEC CO", "33.37", "+0.24 (0.72%)",
				"31429500" },
		new Object[] { "GM", "GENERAL MOTORS", "43.94", "-0.20 (0.45%)",
				"3722100" },
		new Object[] { "HD", "HOME DEPOT INC", "34.33", "-0.18 (0.52%)",
				"5367900" },
		new Object[] { "HON", "HONEYWELL INTL", "35.70", "+0.23 (0.65%)",
				"4092100" },
		new Object[] { "HPQ", "HEWLETT-PACKARD", "19.65", "-0.25 (1.26%)",
				"11003000" },
		new Object[] { "IBM", "INTL BUS MACHINE", "84.02", "-0.11 (0.13%)",
				"6880500" },
		new Object[] { "INTC", "INTEL CORP", "23.15", "-0.23 (0.98%)",
				"95177008" },
		new Object[] { "JNJ", "JOHNSON&JOHNSON", "55.35", "-0.57 (1.02%)",
				"5428000" },
		new Object[] { "JPM", "JP MORGAN CHASE", "36.00", "-0.45 (1.23%)",
				"12135300" },
		new Object[] { "KO", "COCA COLA CO", "50.84", "-0.32 (0.63%)",
				"4143600" },
		new Object[] { "MCD", "MCDONALDS CORP", "27.91", "+0.12 (0.43%)",
				"6110800" },
		new Object[] { "MMM", "3M COMPANY", "88.62", "+0.43 (0.49%)",
				"2073800" },
		new Object[] { "MO", "ALTRIA GROUP", "48.20", "-0.80 (1.63%)",
				"6005500" },
		new Object[] { "MRK", "MERCK & CO", "44.71", "-0.97 (2.12%)",
				"5472100" },
		new Object[] { "MSFT", "MICROSOFT CP", "27.87", "-0.26 (0.92%)",
				"46717716" },
		new Object[] { "PFE", "PFIZER INC", "32.58", "-1.43 (4.20%)",
				"28783200" },
		new Object[] { "PG", "PROCTER & GAMBLE", "55.01", "-0.07 (0.13%)",
				"5538400" },
		new Object[] { "SBC", "SBC COMMS", "23.00", "-0.54 (2.29%)",
				"6423400" },
		new Object[] { "UTX", "UNITED TECH CP", "91.00", "+1.16 (1.29%)",
				"1868600" },
		new Object[] { "VZ", "VERIZON COMMS", "34.81", "-0.35 (1.00%)",
				"4182600" },
		new Object[] { "WMT", "WAL-MART STORES", "52.33", "-0.25 (0.48%)",
				"6776700" },
		new Object[] { "XOM", "EXXON MOBIL", "45.32", "-0.14 (0.31%)",
				"7838100" } };

public static TableModel createQuoteTableModel() {
	return new QuoteTableModel();
}

public JPanel  getDate() {
	JPanel panel = new JPanel();
	final DateComboBox dateSpinner = new com.jidesoft.combobox.DateComboBox();
	 dateSpinner.setFormat(new SimpleDateFormat("dd/MM/yyyy"));
	Calendar currentDate = Calendar.getInstance();
	dateSpinner.setDate(currentDate.getTime());
	//dateSpinner.set
	String kk = "";
   
	dateSpinner.addFocusListener(new FocusListener() {
        public void focusLost(java.awt.event.FocusEvent event) {
        	DateComboBox t = (DateComboBox) event.getSource();
        	String dateTxt = getDateText();
        	 setDateTextEmpty();
        	 if(!commonUTIL.isEmpty(dateTxt)) {
        		// if(dateTxt.equalsIgnoreCase("1m")) {
        			 Date date = dateSpinner.getDate();
        			 dateSpinner.setDate(DateU.valueOf(date).addMonthsC(1).getDate());
        			 
        		// }
        	 }
            
        }

        public void focusGained(java.awt.event.FocusEvent event) {
        
        }


    }
    );
   
dateSpinner.addKeyListener(new KeyListener() {
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			String dateTxt = getDateText();
			setDateTextEmpty();
			Date date = dateSpinner.getDate();
			Date d1 = ((Calendar) dateSpinner.getSelectedItem()).getTime();
			if(date == null)
				date = commonUTIL.getCurrentDate();
			dateSpinner.setDate(checkDate(dateTxt.trim(),date));
			
		/*	if(dateTxt.equalsIgnoreCase("1m")) {
   			 Date date = dateSpinner.getDate();
   			DateU dated  = 	DateU.valueOf(date);
   			dated.addMonths(1);
   			 dateSpinner.setDate(dated.getDate()); */
   			 
   		// }
			
			
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			String dateT = getDateText();
			setDateTextEmpty();
		}
	//	System.out.println(arg0.getKeyChar());
		setDateText(arg0.getKeyChar());
	//	dateSpinner.setName(dateSpinner.getName()+arg0.getKeyChar());
	//	System.out.println(dateSpinner.getName());
		//setDateText(arg0.getKeyChar());
	}
    	
    });
    return createTitledComponent("DateSpinnerExComboBox",
            "ExComboBox to choose a month", dateSpinner);

} 
	String dateText = "";
	 public String getDateText() {
		  
		   return dateText.trim();
	   }
   public String setDateText(Character kk) {
	   dateText =dateText.trim() +kk;
	   return dateText;
   }
   public void setDateTextEmpty() {
	   dateText = "";
   }
	public static void main(String args[]) {
		testsearch e33= new testsearch();
		e33.setVisible(true);
	}
	private JList getJList0() {
		if (jList0 == null) {
			jList0 = new JList();
			
			DefaultListModel listModel = new DefaultListModel();
			
			listModel.addElement("item0");
			listModel.addElement("item1");
			listModel.addElement("item2");
			listModel.addElement("item3");
			listModel.addElement( "Bob");
					listModel.addElement( "Ted");
							listModel.addElement( "Carol");
									listModel.addElement( "Alice");
											listModel.addElement("pppo");
						listModel.addElement("jay");
								listModel.addElement("pank");
										listModel.addElement("king3");
												listModel.addElement("ling");
														listModel.addElement("linong");
																listModel.addElement("jayu");
																		listModel.addElement("second");
																				listModel.addElement("firt");
																						listModel.addElement("hart");
																								listModel.addElement("kin");
			jList0.setModel(listModel);
			jList0.setVisible(true);
		}
		return jList0;
	}
String test = "";
private JPanel jPanel1;
private JTextField jTextField1;
private void keypres(java.awt.event.KeyEvent evt) {
	
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
	//	System.out.println("pppp"+evt.getKeyChar());
		    m_time = System.currentTimeMillis();  
		    test  = test + Character.toLowerCase(ch);     
		    
		    
		    System.out.println("key++++ " + test);
		    // Iterate through items in the list until a matching prefix is found.    
		    // This technique is fine for small lists, however, doing a linear   
		    // search over a very large list with additional string manipulation    
		    // (eg: toLowerCase) within the tight loop would be quite slow.    // In that case, pre-processing the case-conversions, and storing the  
		    // strings in a more search-efficient data structure such as a Trie    // or a Ternary Search Tree would lead to much faster find.   
		    for (int i=0; i < jList0.getModel().getSize(); i++) {    
		    	String str = ((String)jList0.getModel().getElementAt(i)).toLowerCase();       
		    	if (str.startsWith(test)) {       
		    		jList0.setSelectedIndex(i);
		    	//	System.out.println(str);
		    		//break;//change selected item in list          
		    		jList0.ensureIndexIsVisible(i);
		    		//change listbox scroll-position           
		    		break;       
		    		}    
		    	}
		    
		    
		    
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		//	jTextField0.setText("jTextField0");
		}/*jTextField0.addKeyListener(new KeyAdapter() {
			

		    @Override
		    public void keyTyped(KeyEvent e) {
		    	
		    	
		        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
		            // Do stuff
		        	if(jList0.getSelectedIndex() == -1) {
		        		jTextField0.setText("");
		        		return;
		        	}
		        	jTextField0.setText(jList0.getSelectedValue().toString());
		        	jList0.setVisible(false);
		        	jScrollPane0.setVisible(false);
		        	test = "";
		        } else {
		        	jScrollPane0.setVisible(true);
		        	
		        	jList0.setVisible(true);
		        	keyPressHandler(e);
		        	//search(jTextField0.getText());
		        }  
		        
		    }
		    @Override
		    public void keyPressed(KeyEvent key) {
		    	boolean found = false;
		    	if(key.getKeyCode()==8)
			    {
		    		
			        if(test.length() > 0)
			    		test = test.substring(0, test.length()-1);
			       
			        for (int i=0; i < jList0.getModel().getSize(); i++) {    
				    	String str = ((String)jList0.getModel().getElementAt(i)).toLowerCase();  
				    	
				    	if(str.trim().equalsIgnoreCase(test.trim())) {
				    		
				    		jList0.setSelectedIndex(i);
				    		jList0.ensureIndexIsVisible(i);
				    		found = true;
				    		break;
				    	} 
			        }
			        if(!found) {
			        	for (int i=0; i < jList0.getModel().getSize(); i++) {    
					    	String str = ((String)jList0.getModel().getElementAt(i)).toLowerCase();  
					    	if(str.startsWith(test)) {  
					    		jList0.setSelectedIndex(i);
					    		jList0.ensureIndexIsVisible(i);
					    		break;  
					    	}
			        	}
			        }
				    	
			    
			    	
			    }
		    	
		    	
		    }
		    
		}); */
		return jTextField0;
	}
	private void search(String text) {  
		DefaultListModel model = (DefaultListModel)jList0.getModel();     
		// Case–sensitive.    
		
		if(model.contains(text)) {   
			jList0.setSelectedValue(text, true);
			int index = model.indexOf(text);   
			
			jList0.setSelectedIndex(index);       
		//	label.setText(text + " found at index " + index);    
			} else {         
				jList0.clearSelection();    
				//jList0.setText(text + " not found");     
				}   
		}
	

static class QuoteTableModel extends DefaultTableModel implements
		ContextSensitiveTableModel {
	private static final long serialVersionUID = 7487309284053329041L;

	public QuoteTableModel() {
		super(QUOTES, QUOTE_COLUMNS);
		
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public ConverterContext getConverterContextAt(int rowIndex,
			int columnIndex) {
		return null;
	}

	public EditorContext getEditorContextAt(int rowIndex, int columnIndex) {
		return null;
	}

	public Class<?> getCellClassAt(int rowIndex, int columnIndex) {
		return getColumnClass(columnIndex);
	}
}
	
}
