package apps.window.util.propertyDialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import apps.window.util.propertyPane.panel.ProductSelectionOptionPropertyPanel;

import com.jidesoft.combobox.PopupPanel;

import util.commonUTIL;

public class ProductSelectionOptionPropertyDialog extends JDialog {
	
	JPanel mainPanel = new JPanel(); 
	JButton okButton = new JButton("Ok");
	JButton clearButton = new JButton("Clear");
	JButton cancelButton = new JButton("Cancel");
	JButton showButton = new JButton("Open");
	ProductSelectionOptionPropertyPanel popPanel=null;
    private final int WINDOW_WIDTH = 200;
    private final int WINDOW_DEPTH = 300;  
	public ButtonGroup group = new ButtonGroup();
	JPanel radioButton = new JPanel();
    private void createLayout(Vector<String> productType ) throws Exception {
		   Container contentPane = getContentPane();
		   
		   contentPane.setSize(new java.awt.Dimension(WINDOW_WIDTH,WINDOW_DEPTH));
		   mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); 
		     
		    
		 //  northPanel.add(conditionalChoice);
		  
		   
		 
		   mainPanel.setBorder(BorderFactory.createTitledBorder(null,""  , TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, null));
		 
		   mainPanel.setSize(188, 240);
		 
	 
		   try {
	    //	   dateRuleTable.setRowSelectionAllowed(true);  
				ActionListener alisten = new VoteActionListener( );

				if(!commonUTIL.isEmpty(productType)) {
					for(int i=0;i<productType.size();i++) {
						JRadioButton ptype = new JRadioButton(productType.get(i));
						ptype.setActionCommand(productType.get(i));
						ptype.addActionListener(alisten);

						group.add(ptype);
						mainPanel.add(ptype );
					}
				  
			     
				}
		   }
	       catch (Exception e) {
	         //  Log.error(this, e);
	       }
		   
		   cancelButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	setVisible(false);
	                dispose();
	            }
	        });
		   
    }
    protected void init(Vector<String> productTypeData ){
	       try {
	    	   createLayout(productTypeData ); 
	    	    
	    	 
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
    public ProductSelectionOptionPropertyDialog(Frame parent, boolean modal, Comparator comp,
			boolean showFilter, boolean isOrderable) {
		super(parent, modal);
		init( );
	}
    public ProductSelectionOptionPropertyDialog(Frame parent, boolean modal, Comparator comp,
			boolean showFilter) {
		this(parent, modal, comp, showFilter, true);
	}
    public ProductSelectionOptionPropertyDialog(Frame parent, boolean modal) {
		this(parent, modal, null, false, true);
	}

    public ProductSelectionOptionPropertyDialog(Frame frame,  boolean modal,Vector<String> productTypeData,ProductSelectionOptionPropertyPanel pop ) {
    	super(frame, modal);
    	popPanel=pop;
		init(productTypeData );
		
		// TODO Auto-generated constructor stub
	}
	public JPanel getMainPanel() { return mainPanel; }
	   public  JButton getCancelButton() { return cancelButton; }
	   public  JButton getOkButton() { return okButton; }
	   public JButton getClearButton() { return clearButton; }
	//   public JButton getShowButton() { return showButton; }
	   String radioButtonSelection;
	   
	/**
	 * @return the radioButtonSelection
	 */
	public String getRadioButtonSelection() {
		return radioButtonSelection;
	}
	/**
	 * @param radioButtonSelection the radioButtonSelection to set
	 */
	public void setRadioButtonSelection(String radioButtonSelection) {
		this.radioButtonSelection = radioButtonSelection;
		popPanel.setSelectedObject(radioButtonSelection);
		//popPanel._
		
	}
	public String getSelectedRadioButton() {
		// TODO Auto-generated method stub
		return radioButtonSelection;
	}
	 class VoteActionListener implements ActionListener {
	      public void actionPerformed(ActionEvent ev) {
	        String choice = group.getSelection( ).getActionCommand( );
	        setRadioButtonSelection(choice);
	      }
	    }
	 

	
}
