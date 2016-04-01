package apps.window.staticwindow;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.CosmosException;
import util.commonUTIL;
import apps.window.staticwindow.util.JavaFileGeneratorWindowUtil;
import apps.window.util.propertyTable.JavaFileGeneratorPropertyTable;
import apps.window.util.tableModelUtil.JavaFileGeneratorTableModelUtil;
import beans.JavaFileGenerator;
import constants.CommonConstants;
import constants.CurrencyDefaultConstant;
import constants.JavaFileGeneratorConstants;

public class JavaFileGeneratorWindow extends BasePanel {
	   ActionMap actions = null;
	   private static final long serialVersionUID = 1L; 
	   public JavaFileGeneratorTableModelUtil model =null;
	     JavaFileGenerator JavaFileGenerator = new JavaFileGenerator(); /// used as a bean 
	  // used for Validation and save,update and delete and get Data from DB.
	     JavaFileGeneratorWindowUtil windowUtil = null;
	     Vector<JavaFileGenerator> rightPanelJtableJavaFileGeneratordata = new Vector<JavaFileGenerator>(); // used maintain data in rightPanel in center area.
		
		 /**
		 * @return the data
		 */
		public Vector<JavaFileGenerator> getData() {
			return rightPanelJtableJavaFileGeneratordata;
		}

		/**
		 * @param data the data to set
		 */
		public void setData(Vector<JavaFileGenerator> data) {
			//this.data = data;
			rightPanelJtableJavaFileGeneratordata = data;
		}

		
		
		  
	      
	     // leftTopPanel Data
	    protected JLabel windowName = new JLabel("WindowName");
	    public final	JTextField JavaFileGeneratorSearchTextField = new JTextField("JavaFileGeneratorTextField"); // search textfield in leftTopPanel Data
		

		// rightTopPanel Data
		 private JLabel JavaFileGeneratorLabel = new JLabel("Window Name");
		
		 
		 public JTextArea textArea = new JTextArea();
		 public JScrollPane textAreaScrollPane = new JScrollPane(textArea);
		protected JButton windowDetails = new JButton("Load Script");
		// leftSide PropertyTable 
		public JavaFileGeneratorPropertyTable  propertyTable = null; 
		
		// Constructor
		public JavaFileGeneratorWindow() {
			try {
				initComponents();
			} catch (CosmosException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError(JavaFileGeneratorConstants.WINDOW_NAME, "Constructor", e);
			}
			 
					 
		}

		private void initComponents() throws CosmosException {
			/// init() data required while loading this window.
			init();
			
			
			setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			setLayout(new BorderLayout());
			 
			
			
	       
	        
	       
	      
	      // add  model to table 
	       model = new JavaFileGeneratorTableModelUtil(rightPanelJtableJavaFileGeneratordata);
	       rightSideCenterTable.setModel(model); 
	       
	       
		   createSingleSplitPaneLayout(CommonConstants.SPLITWINDOWLOCATION);	 
		 setSize(CommonConstants.WINDOWWIDTH , CommonConstants.WINDOWHIGHT); 
		}
		 
		 
		  

		 
		/**
		 * @return the JavaFileGenerator
		 */
		public JavaFileGenerator getJavaFileGenerator() {
			return JavaFileGenerator;
		}

		/**
		 * @param JavaFileGenerator the JavaFileGenerator to set
		 */
		public void setJavaFileGenerator(JavaFileGenerator JavaFileGenerator) {
			this.JavaFileGenerator = JavaFileGenerator;
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
			windowUtil = new JavaFileGeneratorWindowUtil(); 
			  windowUtil.setWindow(this);
				this. validationActionUtil = windowUtil;
			
			
		}
		// add listerener to panel Jcompenonts. 
		@Override
		public void setWindowActionListener()   {
			// TODO Auto-generated method stub
			try {
				setEventListener(windowDetails);
				setEventListener(loadButton);
				setEventListener(JavaFileGeneratorSearchTextField);
			} catch (CosmosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		 // add lefttop panel componenonts
		@Override
		public void addTopLeftSidePanelComponents() {
			// TODO Auto-generated method stub
			
		       JavaFileGeneratorSearchTextField.setName(JavaFileGeneratorConstants.SEARCHTEXTBOX); 
		      leftTopbuttonsPanel.add(JavaFileGeneratorLabel);
		      setSearchOnLeftTopPanel(JavaFileGeneratorSearchTextField,windowUtil.getWindowNameOnStartUP());	      
		      leftTopbuttonsPanel.add(JavaFileGeneratorSearchTextField);  
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
			
		       propertyTable = new JavaFileGeneratorPropertyTable(JavaFileGeneratorConstants.WINDOW_NAME,JavaFileGenerator);
		       setLeftSidePropertyPanePanel(propertyTable.getJavaFileGeneratorPropertyTable(generateProperty(JavaFileGeneratorConstants.WINDOW_NAME) ));
			   
			
		}

		@Override
		public void addCenterRightSidePanelComponents() {
			// TODO Auto-generated method stub
		 
			JPanel   jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(textAreaScrollPane, new Constraints(new Leading(7, 674, 10, 10), new Leading(223, 231, 10, 10)));
			jPanel1.add(scrollPane,new Constraints(new Leading(4, 680, 12, 12), new Leading(3, 214, 12, 12)));
		    centerRightSidePanel.add(jPanel1);
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