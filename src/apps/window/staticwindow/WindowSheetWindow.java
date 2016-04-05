package src.apps.window.staticwindow;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField; 
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.CosmosException;
import util.commonUTIL;
import apps.window.staticwindow.util.WindowSheetWindowUtil;
import apps.window.util.propertyTable.CellStyleProrpertyTable;
import apps.window.util.propertyTable.WindowSheetPropertyTable;
import apps.window.util.tableModelUtil.SampleTableModel;
import apps.window.util.tableModelUtil.WindowSheetTableModelUtil;
import beans.PropertyCellStyle;
import beans.WindowSheet;

import com.jidesoft.grid.CellStyle;
import com.jidesoft.grid.ColumnCellStyleProvider;
import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.RowStripeTableStyleProvider;
import com.jidesoft.grid.SortableTable;
import com.jidesoft.grid.TableCellStyleEditor;
import com.jidesoft.grid.TextFieldCellEditor;
import com.jidesoft.hints.ListDataIntelliHints;
import com.jidesoft.icons.JideIconsFactory;

import constants.CurrencyDefaultConstant;
import constants.WindowSheetConstants;

public class WindowSheetWindow extends BasePanel {
	   ActionMap actions = null;
	   private static final long serialVersionUID = 1L; 
	   PropertyTable cellStylepropertyTable = null;
	   public WindowSheetTableModelUtil model =null;
	  public JList  fieldNameList = new JList();
	  public CellStyleProrpertyTable cellStyle = null;
	  public String searchData [];
	     WindowSheet windowSheet = new WindowSheet(); /// used as a bean 
	  // used for Validation and save,update and delete and get Data from DB.
	     WindowSheetWindowUtil windowUtil = null;
	     Vector<WindowSheet> rightPanelJtableWindowSheetdata = new Vector<WindowSheet>(); // used maintain data in rightPanel in center area.
		
		 /**
		 * @return the data
		 */
		public Vector<WindowSheet> getData() {
			return rightPanelJtableWindowSheetdata;
		}

		/**
		 * @param data the data to set
		 */
		public void setData(Vector<WindowSheet> data) {
			//this.data = data;
			rightPanelJtableWindowSheetdata = data;
		}

		
		public JTextArea textArea = new JTextArea();
		 public JScrollPane textAreaScrollPane = new JScrollPane(textArea);
		  
	      
	     // leftTopPanel Data
	    protected JLabel windowName = new JLabel("WindowName");
	    public final	JTextField WindowSheetSearchTextField = new JTextField("WindowSheetTextField"); // search textfield in leftTopPanel Data
		

		// rightTopPanel Data
		 private JLabel windowSheetLabel = new JLabel("Window Name");
		public JButton windowDetails = new JButton("Load Scripts");
		public JButton javaDetails = new JButton("Load Java Scripts");
		public JButton previewProp = new JButton("PropertyPreView");
		// leftSide PropertyTable 
		public WindowSheetPropertyTable  propertyTable = null; 
		
		// Constructor
		public WindowSheetWindow() {
			try {
				initComponents();
			} catch (CosmosException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError(WindowSheetConstants.WINDOW_NAME, "Constructor", e);
			}
			 
					 
		}

		private void initComponents() throws CosmosException {
			/// init() data required while loading this window.
			init();
			
			final List<String> fontNames = new ArrayList<String>();
			 
			setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			setLayout(new BorderLayout()); 
	      // add  model to table 
	       model = new WindowSheetTableModelUtil(rightPanelJtableWindowSheetdata);
	       setCornerForScrollPane(model.getCol());
	       setQuickSearchOnTable(model);
	      rightSideCenterTable.setModel(model); 

	      rightSideCenterTable.getColumnModel().getColumn(1)
	  	.setCellEditor(new TextFieldCellEditor(String.class) {
	  		private static final long serialVersionUID = 2023654568542192380L;

	  		@Override
	  		protected JTextField createTextField() {
	  			JTextField cellEditorTextField = new JTextField();
	  			ListDataIntelliHints fontIntellihints = new ListDataIntelliHints<String>(
	  					cellEditorTextField, fontNames);
	  			fontIntellihints.setCaseSensitive(false);
	  			return cellEditorTextField;
	  		}
	  	});
	       
	       
		   createSingleSplitPaneLayout(CurrencyDefaultConstant.SPLITWINDOWLOCATION);	 
		 setSize(CurrencyDefaultConstant.WINDOWWIDTH , CurrencyDefaultConstant.WINDOWHIGHT); 
		}
		 
		 
		  

		 
		/**
		 * @return the WindowSheet
		 */
		public WindowSheet getWindowSheet() {
			return windowSheet;
		}

		/**
		 * @param WindowSheet the WindowSheet to set
		 */
		public void setWindowSheet(WindowSheet windowSheet) {
			this.windowSheet = windowSheet;
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
			windowUtil = new WindowSheetWindowUtil(); 
			  windowUtil.setWindow(this);
				this. validationActionUtil = windowUtil;
			
			
		}
		// add listerener to panel Jcompenonts. 
		@Override
		public void setWindowActionListener()   {
			// TODO Auto-generated method stub
			try {
				setEventListener(windowDetails);
				setEventListener(javaDetails);
				setEventListener(previewProp);
				setEventListener(WindowSheetSearchTextField);
			} catch (CosmosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		 // add lefttop panel componenonts
		@Override
		public void addTopLeftSidePanelComponents() {
			// TODO Auto-generated method stub
			
		       WindowSheetSearchTextField.setName(WindowSheetConstants.SEARCHTEXTBOX); 
		      leftTopbuttonsPanel.add(windowSheetLabel);
		      setSearchOnLeftTopPanel(WindowSheetSearchTextField,searchData);	      
		      leftTopbuttonsPanel.add(WindowSheetSearchTextField);  
		}
		// add righttop panel componenonts
		@Override
		public void addTopRigthSidePanelComponents() {
			// TODO Auto-generated method stub
		
		       rightTopbuttonsPanel.add(windowName);
		       rightTopbuttonsPanel.add(windowDetails);
		       rightTopbuttonsPanel.add(javaDetails);
		       rightTopbuttonsPanel.add( previewProp);
		}
		 // create property proprities. 
		@Override
		public void createPropertyPaneTable() {
			// TODO Auto-generated method stub
			
		       propertyTable = new WindowSheetPropertyTable(WindowSheetConstants.WINDOW_NAME,windowSheet);
		       setLeftSidePropertyPanePanel(propertyTable.getWindowSheetPropertyTable(generateProperty(WindowSheetConstants.WINDOW_NAME) ));
			   
			
		}
		 
    public JPanel jPanel1 =null;  
		@Override
		public void addCenterRightSidePanelComponents() {
			// TODO Auto-generated method stub
			//centerRightSidePanel.add(scrollPane, BorderLayout.CENTER);
			 
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel1.setLayout(new GroupLayout()); 
		     JPanel jj  = getJPanel2();  
			        JButton bt = new JButton(new AbstractAction("Update the Cell Styles") {
			            public void actionPerformed(ActionEvent e) { 
			            	if(model.getRowCount() > 0)
			            	windowUtil.savePropertyStyle(cellStyle.getPropertyCellStyles());
			            	else 
			            		commonUTIL.showAlertMessage("Select Window");
			            }
			        }); 
			        jj.add(getJPanel3(),BorderLayout.CENTER); 
			        jj.add(bt,BorderLayout.SOUTH); 
			jPanel1.add(scrollPane, new Constraints(new Leading(4, 1018, 10, 10), new Leading(3, 214, 12, 12))); 
			jPanel1.add(jj, new Constraints(new Leading(4, 397, 12, 12), new Leading(221, 233, 12, 12))); 
			centerRightSidePanel.add(jPanel1);
			 
		}
		private JPanel getJPanel2() {
			JPanel 	jPanel2  = null;
			if (jPanel2 == null) {
				jPanel2 = new JPanel();
				jPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
				jPanel2.setLayout(new BorderLayout());
			}
			return jPanel2;
		}
		private JPanel getJPanel3() {
			if (jPanel3 == null) {
				jPanel3 = new JPanel();
				jPanel3.setBorder(BorderFactory.createTitledBorder(null, "PropertyCellStyle", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, null));
				jPanel3.setLayout(new GroupLayout());
				
				jPanel3.add(getJScrollPane1(), new Constraints(new Leading(3, 100, 10, 10), new Leading(-3, 169, 12, 12)));
				jPanel3.add(getCellStyleTablePanel(fieldNameList), new Constraints(new Bilateral(109, 0, 0), new Leading(-3, 169, 10, 10)));
			}
			return jPanel3;
		}
		private JScrollPane getJScrollPane1() {
			if (jScrollPane1 == null) {
				jScrollPane1 = new JScrollPane();
				jScrollPane1.setViewportView(getJList0());
			}
			return jScrollPane1;
		}
		JScrollPane jScrollPane1 = null;
		 
		JPanel jPanel4 = null;
		JPanel jPanel3= null;
		private JList getJList0() {
			if (fieldNameList == null) {
				
				DefaultListModel listModel = new DefaultListModel();
				 
				fieldNameList.setModel(listModel);
			}
			return fieldNameList;
		}
		private JPanel getJPanel4() {
			if (jPanel4 == null) {
				jPanel4 = new JPanel();
				jPanel4.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
				jPanel4.setLayout(new GroupLayout());
			}
			return jPanel4;
		}
		public JScrollPane getPanel(JScrollPane scrollPane,WindowSheetTableModelUtil model ) {
			 
			 
			  final List<String> fontNames = new ArrayList<String>();
			 
			//model.addRow(new Object[] { "Arial" });
			//model.addRow(new Object[] { "Tahoma" });
			SortableTable table = new SortableTable(model);
		 
			table.getColumnModel().getColumn(0)
					.setCellEditor(new TextFieldCellEditor(String.class) {
						private static final long serialVersionUID = 2023654568542192380L;

						@Override
						protected JTextField createTextField() {
							JTextField cellEditorTextField = new JTextField();
							ListDataIntelliHints fontIntellihints = new ListDataIntelliHints<String>(
									cellEditorTextField, fontNames);
							fontIntellihints.setCaseSensitive(false);
							return cellEditorTextField;
						}
					});
			table.setPreferredScrollableViewportSize(new Dimension(100, 100));
			  scrollPane.getViewport().add(table);
			scrollPane.add(table);
			return scrollPane;
			//jPanel0.add(new JScrollPane(table),BorderLayout.CENTER );
		}
		
		 
		private JPanel getCellStyleTablePanel(JList jList0) {
			  cellStyle = new   CellStyleProrpertyTable ("Style",jList0);
			  cellStylepropertyTable = cellStyle.getCellStyleProrpertyTable();
			cellStylepropertyTable.setPaintMarginBackground(false);
			cellStylepropertyTable.setMarginRenderer(new DefaultTableCellRenderer() {
	             @Override
	             public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                 JLabel label = (JLabel) super.getTableCellRendererComponent(table, "", false, false, row, column);
	                 label.setHorizontalAlignment(SwingConstants.CENTER);
	                 label.setOpaque(false);
	                 if (!((Property) value).hasChildren() && isSelected) {
	                     label.setIcon(JideIconsFactory.getImageIcon(JideIconsFactory.Arrow.RIGHT));
	                 }
	                 else {
	                     label.setIcon(null);
	                 }
	                 return label;
	             }
			
	         });
		        
		
			cellStylepropertyTable.setTableStyleProvider(new RowStripeTableStyleProvider());
		JPanel basePanel = new JPanel();
		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.getViewport().add(cellStylepropertyTable);
		basePanel.setLayout(new BorderLayout());
		 basePanel.add(tableScrollPane, BorderLayout.CENTER);

			return basePanel;
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