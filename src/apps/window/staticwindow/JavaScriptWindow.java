package apps.window.staticwindow;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import util.CosmosException;
import util.commonUTIL;
import apps.window.staticwindow.util.JavaScriptWindowUtil;
import apps.window.util.propertyTable.JavaScriptPropertyTable;
import apps.window.util.tableModelUtil.JavaScriptTableModelUtil;
import beans.JavaScript;
import constants.CommonConstants;
import constants.JavaScriptConstants;

public class JavaScriptWindow  extends BasePanel {
	   ActionMap actions = null;
	   private static final long serialVersionUID = 1L; 
	   public JavaScriptTableModelUtil model =null;
	     JavaScript template = new JavaScript(); /// used as a bean 
	  // used for Validation and save,update and delete and get Data from DB.
	     JavaScriptWindowUtil windowUtil = null;
	     Vector<JavaScript> rightPanelJtableJavaScriptdata = new Vector<JavaScript>(); // used maintain data in rightPanel in center area.
	     public JTextArea textarea = new JTextArea();
		 JScrollPane textAreaScrollPane = new JScrollPane(textarea);
		 /**
		 * @return the data
		 */
		public Vector<JavaScript> getData() {
			return rightPanelJtableJavaScriptdata;
		}

		/**
		 * @param data the data to set
		 */
		public void setData(Vector<JavaScript> data) {
			//this.data = data;
			rightPanelJtableJavaScriptdata = data;
		}

		
		
		  
	      
	     // leftTopPanel Data
	    protected JLabel windowName = new JLabel("WindowName");
	    public final	JTextField JavaScriptSearchTextField = new JTextField("JavaScriptTextField"); // search textfield in leftTopPanel Data
		

		// rightTopPanel Data
		 private JLabel templateLabel = new JLabel("Window Name");
		protected JButton windowDetails = new JButton("Load WindowDetails");
		// leftSide PropertyTable 
		public JavaScriptPropertyTable  propertyTable = null; 
		
		// Constructor
		public JavaScriptWindow() {
			try {
				initComponents();
			} catch (CosmosException e) {
				// TODO Auto-generated catch block
				 
				commonUTIL.displayError(JavaScriptConstants.WINDOW_NAME, "Constructor", e);
			}
			 
					 
		}

		private void initComponents() throws CosmosException {
			/// init() data required while loading this window.
			init();
			
			
			setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			setLayout(new BorderLayout()); 
	      // add  model to table 
	       model = new JavaScriptTableModelUtil(rightPanelJtableJavaScriptdata);
	       rightSideCenterTable.setModel(model); 
	       
	       
		   createSingleSplitPaneLayout(CommonConstants.SPLITWINDOWLOCATION);	 
		 setSize(CommonConstants.WINDOWWIDTH , CommonConstants.WINDOWHIGHT); 
		}
		 
		 
		  

		 
		/**
		 * @return the JavaScript
		 */
		public JavaScript getJavaScript() {
			return template;
		}

		/**
		 * @param JavaScript the JavaScript to set
		 */
		public void setJavaScript(JavaScript template) {
			this.template = template;
		}
		 
		  
		
	    	
	       
		
		 
		@Override
		public ActionMap getHotKeysActionMapper() {
			// TODO Auto-generated method stub
			ActionMap action = new ActionMap();
			 
			return action;
		}

		@Override
		public JPanel getHotKeysPanel() {
			// TODO Auto-generated method stub
			return  rightTopbuttonsPanel;
		}

		@Override
		public ArrayList<Component> getFocusOrderList() {
			// TODO Auto-generated method stub
			ArrayList<Component> comps = new ArrayList<Component>();
			comps.add(loadButton);
			return comps;
		}
     // add Window Validation util for search,save,new,saveAsNew,close and other custom components. 
		@Override
		public void setWindowValidationUtil( ) {
			// TODO Auto-generated method stub
			windowUtil = new JavaScriptWindowUtil(); 
			  windowUtil.setWindow(this);
				this. validationActionUtil = windowUtil;
			
			
		}
		// add listerener to panel Jcompenonts. 
		@Override
		public void setWindowActionListener()   {
			// TODO Auto-generated method stub
			try {
				setEventListener(windowDetails);
				setEventListener(JavaScriptSearchTextField);
			} catch (CosmosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		 // add lefttop panel componenonts
		@Override
		public void addTopLeftSidePanelComponents() {
			// TODO Auto-generated method stub
			
		       JavaScriptSearchTextField.setName(JavaScriptConstants.SEARCHTEXTBOX); 
		      leftTopbuttonsPanel.add(templateLabel);
		      setSearchOnLeftTopPanel(JavaScriptSearchTextField,null);	      
		      leftTopbuttonsPanel.add(JavaScriptSearchTextField);  
		}
		// add righttop panel componenonts
		@Override
		public void addTopRigthSidePanelComponents() {
			// TODO Auto-generated method stub
		
		       rightTopbuttonsPanel.add(windowName);
		       rightTopbuttonsPanel.add(windowDetails);
		}
		 // create property proprities. 
		@Override
		public void createPropertyPaneTable() {
			// TODO Auto-generated method stub
			
		       propertyTable = new JavaScriptPropertyTable(JavaScriptConstants.WINDOW_NAME,template);
		       setLeftSidePropertyPanePanel(propertyTable.getPropertyTable(generateProperty(JavaScriptConstants.WINDOW_NAME) ));
			   
			
		}
		 

		@Override
		public void addCenterRightSidePanelComponents() {
			// TODO Auto-generated method stub
			centerRightSidePanel.add(textAreaScrollPane, BorderLayout.CENTER);
		}

		@Override
		public JPanel createChildPanel(int id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public JPanel createChildPanel(String id) {
			// TODO Auto-generated method stub
			return null;
		}
}

