package apps.window.util.propertyDialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import util.ReferenceDataCache;
import util.RemoteServiceUtil;

import beans.DateRule;


public class DateRulePropertyDialog extends JDialog {
	JPanel mainPanel = new JPanel();
	JPanel northPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel southPanel = new JPanel();
	JButton okButton = new JButton("Ok");
	JButton clearButton = new JButton("Clear");
	JButton cancelButton = new JButton("Cancel");
	JButton showButton = new JButton("Open");
	DateRuleModel _dateRuleModel;
	JTable dateRuleTable = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	JLabel dateRuleLabel = new JLabel("Date Source");
	Vector<DateRule> _dateRulesVector = new Vector<DateRule>(0);
	Vector<DateRule> _extDateSourceVector = new Vector<DateRule>(0);
	Vector<DateRule> rules = new Vector<DateRule>();
	private DateRule[] daterule;
	JComboBox dateRuleChoice = new JComboBox();
    private final int WINDOW_WIDTH = 200;
    private final int WINDOW_DEPTH = 200;
    private static final String MANUALDATESCHEDULE=  "Manual Date Schedule";
    private static final String DATERULE = "DateRule";
	
	
    private void createLayout() throws Exception {
		   Container contentPane = getContentPane();
		   dateRuleChoice.addItem("DateRule");
		   dateRuleChoice.addItem("ManulRule");
		   contentPane.setSize(new java.awt.Dimension(WINDOW_WIDTH,WINDOW_DEPTH));
		   mainPanel.setLayout(new BorderLayout());
		   northPanel.setLayout(new FlowLayout());
		   contentPane.add(mainPanel,BorderLayout.CENTER);
		   mainPanel.add(northPanel,BorderLayout.NORTH);
		   northPanel.add(dateRuleLabel);
		   dateRuleChoice.setSize(60,24);
		   
		   northPanel.add(dateRuleChoice);
		   northPanel.add(showButton);
		   
		   mainPanel.add(centerPanel,BorderLayout.CENTER);
		   dateRuleTable.setPreferredScrollableViewportSize(new java.awt.Dimension(200,150));
		   String col [] = {"Name"};
		   try {
	    	   dateRuleTable.setRowSelectionAllowed(true);
	    	   _dateRuleModel= new DateRuleModel(getDateRule(),col);
	    	   dateRuleTable.setModel(_dateRuleModel);
	    	   dateRuleTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    	   dateRuleTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		   }
	       catch (Exception e) {
	         //  Log.error(this, e);
	       }
		   scrollPane.getViewport().add(dateRuleTable);
		   centerPanel.add(scrollPane);
		   
		   mainPanel.add(southPanel,BorderLayout.SOUTH);
		   southPanel.setLayout(new FlowLayout());
		   southPanel.add(okButton);
		   southPanel.add(clearButton);
		   southPanel.add(cancelButton);
		   cancelButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	setVisible(false);
	                dispose();
	            }
	        });
		   
		   
    }
    private void initDateRuleVector() throws Exception{
		   
			//	   daterule = getAllDateRules();
	    	 
			   _dateRulesVector = getDateRule();
		   
	   }
    protected void init(){
	       try {
	    	   createLayout();
	    	   initDateRuleVector();
	    	//   initExtDateSourceVector();
	         // setupdateRuleChoiceCalypsoComboBox();
	         //  packTableDateRule();
	       } catch (Exception e) {
	         //  Log.error(this, e);
	       }
	   }
    public DateRulePropertyDialog(Frame parent, boolean modal, Comparator comp,
			boolean showFilter, boolean isOrderable) {
		super(parent, modal);
		init();
	}
    public DateRulePropertyDialog(Frame parent, boolean modal, Comparator comp,
			boolean showFilter) {
		this(parent, modal, comp, showFilter, true);
	}
    public DateRulePropertyDialog(Frame parent, boolean modal) {
		this(parent, modal, null, false, true);
	}

    public JPanel getMainPanel() { return mainPanel; }
	   public  JButton getCancelButton() { return cancelButton; }
	   public  JButton getOkButton() { return okButton; }
	   public JButton getClearButton() { return clearButton; }
	   public JButton getShowButton() { return showButton; }
    public Vector<DateRule> getDateRule() {
    
    	/*DateRule rule1 = new DateRule();
    	rule1.setId(1);
    	rule1.setName("Rule 1 Name");
    	DateRule rule2 = new DateRule();
    	rule2.setId(2);
    	rule2.setName("Rule 2 Name");
    	DateRule rule3 = new DateRule();
    	rule3.setId(3);
    	rule3.setName("Rule 3 Name");
    	rules.add(rule1);
    	rules.add(rule2);
    	rules.add(rule3);
    	_dateRulesVector = rules; */
    	try {
			rules = RemoteServiceUtil.getRemoteReferenceDataService().getallDateRules();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return rules;
    	
    	
    }
    
    public DateRule getDateRule(String name) {
    	DateRule rule = null;
    	for(int i=0;i<rules.size();i++) {
    		DateRule r1 = rules.get(i);
    		if(r1.getName().equalsIgnoreCase(name)) {
    			rule = r1;
    			break;
    		}
    	}
    	return rule;
    }
    public void setSelectedDateRule(DateRule daterule) {
		   if (daterule == null) return;
		   if (daterule.getName() == DateRule.MANUAL_DATE_SCHEDULE) {
			   dateRuleChoice.setSelectedItem(MANUALDATESCHEDULE);
		   } else {
			   dateRuleChoice.setSelectedItem(DATERULE);
		   }

		   String name = daterule.getName();
		   for (int i = 0; i < dateRuleTable.getRowCount(); i++) {
			   String curr = (String) dateRuleTable.getValueAt(i, 0);
			   if (curr.equals(name)) {
				   dateRuleTable.setRowSelectionInterval(i, i);
				   break;
			   }
		   }
		   
		   
		   
		   
	   }
    public DateRule getSelectedDateRule() {
		   int row = dateRuleTable.getSelectedRow();
		   if(row == -1)
			   return null;
		   String drName = (String) dateRuleTable.getValueAt(row, 0);
		  // if (Util.isEmpty(drName)) return null;
		   for (DateRule rule : _dateRulesVector) {
			   if (rule.getName().equals(drName)) {
				   return rule;
			   }
		   }
		   for (DateRule rule : _extDateSourceVector) {
			   if (rule.getName().equals(drName)) {
				   return rule;
			   }
		   }
		   return null;
	   }
    
	class DateRuleModel extends AbstractTableModel {
		 final String[] columnNames;  
		    
		 final Vector<DateRule> data; 
		 DateRuleModel
		 ( Vector<DateRule> myData,String col [] ) {   
			 	this.columnNames = col;
			this.data = myData;  
		 }
		 public int getColumnCount() {   
		     return columnNames.length;   
		 }   
		    
		 public int getRowCount() {   
		     return data.size();   
		 }   
		 public String getColumnName(int col) {   
		     return columnNames[col];   
		 }  
		
public void setValueAt(Object value, int row, int col) {   
	         
	         if(value instanceof DateRule) {
	     data.set(row,(DateRule) value) ;
	     this.fireTableDataChanged();   
	         }
	         
	        
	 }   
	    
	 public void addRow(Object value) {   
	    
		 data.add((DateRule) value) ;
	 this.fireTableDataChanged();   
	   
	 }   
	    
	 public void delRow(int row) {   
	    
	 data.remove(row);          
	 this.fireTableDataChanged();   
	    
	 }   
	 
	 public void udpateValueAt(Object value, int row, int col) {   
	    
	  
	     data.set(row,(DateRule) value) ;
	 fireTableCellUpdated(row, col);   
	     System.out.println("New value of data:");   
	    
	}   
		@Override
		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			Object value = null;  	 
			 
			DateRule book = (DateRule) data.get(row);
			 switch (col) {
		     case 0:
		         value = book.getName();
		         break;
		     
			 }
		     return value;
		   
	   }
	 }
	
}
