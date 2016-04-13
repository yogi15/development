package apps.window.util.propertyDialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane; 

import util.commonUTIL;
import util.cacheUtil.ReferenceDataCache;
import apps.window.util.propertyTable.ConditionalPropertyTable;
import apps.window.util.propertyUtil.CommonPropertyUtil;
import apps.window.util.propertyUtil.PropertyEnum;
import apps.window.util.propertyUtil.PropertyGenerator;
import apps.window.util.propertyUtil.Selection;
import beans.DateRule;
import beans.WindowSheet;

import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;

import constants.WindowSheetConstants;

public class ConditionalPropertyDialog extends JDialog {
	
	JPanel mainPanel = new JPanel();
	JPanel northPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel southPanel = new JPanel();
	JButton okButton = new JButton("Ok");
	JButton clearButton = new JButton("Clear");
	JButton cancelButton = new JButton("Cancel");
	JButton showButton = new JButton("Open");
	 
	PropertyTable conditionTable =null;
	JScrollPane scrollPane = new JScrollPane();
	JLabel dateRuleLabel = new JLabel("Date Source");
	Vector<DateRule> _dateRulesVector = new Vector<DateRule>(0);
	Vector<DateRule> _extDateSourceVector = new Vector<DateRule>(0);
	Vector<DateRule> rules = new Vector<DateRule>(); 
    private final int WINDOW_WIDTH = 200;
    private final int WINDOW_DEPTH = 300;
    private static final String MANUALDATESCHEDULE=  "Manual Date Schedule";
    private static final String DATERULE = "DateRule";
	public Hashtable<String,String> conditionalD  = new Hashtable<String,String>();
	
    private void createLayout(Vector<String> conditionalData, String windowName, String designType,String propertyName) throws Exception {
		   Container contentPane = getContentPane();
		   
		   contentPane.setSize(new java.awt.Dimension(WINDOW_WIDTH,WINDOW_DEPTH));
		   mainPanel.setLayout(new BorderLayout());
		   northPanel.setLayout(new FlowLayout());
		   contentPane.add(mainPanel,BorderLayout.CENTER);
		   mainPanel.add(northPanel,BorderLayout.NORTH);
		   northPanel.add(dateRuleLabel); 
		    
		 //  northPanel.add(conditionalChoice);
		  
		   
		   mainPanel.add(centerPanel,BorderLayout.CENTER);
		 
	 
		   try {
	    //	   dateRuleTable.setRowSelectionAllowed(true);  
			   Vector<WindowSheet> data = ReferenceDataCache.selectWindowSheets(windowName,designType);
				Vector<String> fieldNames = new Vector<String>();
				PropertyEnum<String> newParentProp  = null;
				if(!commonUTIL.isEmpty(data)) {
				 
					for(int i=0;i<data.size();i++) {
						WindowSheet ws = (WindowSheet)data.get(i);
						if(ws.isChildField() && ws.getParentFieldName().equalsIgnoreCase(propertyName))
					    	fieldNames.add(ws.getFieldName());
						
					}
			  Property childSelection =  CommonPropertyUtil.createMultipleSelectionListProperty("ChildSelection","ChildSelection","ChildSelection",fieldNames );
			  Property   condition =  CommonPropertyUtil.createStartUPDataProperty("Condition", "Condition","Condition", conditionalData);
			  childSelection.addPropertyChangeListener(Property.PROPERTY_VALUE,new PropertyChangeListener() {
				
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					// TODO Auto-generated method stub
					 Selection<String>  value = ( Selection<String> ) evt.getNewValue();
					Property p = 	 conditionTable.getPropertyTableModel().getProperty("Condition");
					 if(p.getValue() != null) {
						String conditionValue = (String) p.getValue();
							String values =  commonUTIL.collectionToString(value.getItems()); 
							conditionalD.put(conditionValue,values);
							//System.out.println(commonUTIL.convertHashTableKeyValuesToString(conditionalD));
					 }
						  
				}
			});
			  List<Property> p = new ArrayList<Property>();
			  p.add(condition);
			  p.add(childSelection);
			  conditionTable = new ConditionalPropertyTable( ).getConditionalPropertyTable( p );
				}
		   }
	       catch (Exception e) {
	         //  Log.error(this, e);
	       }
		   conditionTable.setPaintMarginBackground(false);
		   centerPanel.add(conditionTable);
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
    protected void init(Vector<String> conditionalData, String windowName, String designType,String propertyName){
	       try {
	    	   createLayout(conditionalData,windowName,designType,propertyName); 
	    	 
	       } catch (Exception e) {
	         //  Log.error(this, e);
	       }
	   }
    protected void init( ){
	       try {
	    	   //createLayout(conditionalData,windowName,designType); 
	       } catch (Exception e) {
	         //  Log.error(this, e);
	       }
	   }
    public ConditionalPropertyDialog(Frame parent, boolean modal, Comparator comp,
			boolean showFilter, boolean isOrderable) {
		super(parent, modal);
		init( );
	}
    public ConditionalPropertyDialog(Frame parent, boolean modal, Comparator comp,
			boolean showFilter) {
		this(parent, modal, comp, showFilter, true);
	}
    public ConditionalPropertyDialog(Frame parent, boolean modal) {
		this(parent, modal, null, false, true);
	}

    public ConditionalPropertyDialog(Frame frame, boolean modal,
			String windowName, String designType, Vector<String> conditionalData,String propertyName) {
    	super(frame, modal);
    	
		init(conditionalData,windowName,designType,propertyName);
		// TODO Auto-generated constructor stub
	}
	public JPanel getMainPanel() { return mainPanel; }
	   public  JButton getCancelButton() { return cancelButton; }
	   public  JButton getOkButton() { return okButton; }
	   public JButton getClearButton() { return clearButton; }
	//   public JButton getShowButton() { return showButton; }
    public Vector<DateRule> getDateRule() {
    
    	 
    	return rules;
    	
    	
    } 
	
}
