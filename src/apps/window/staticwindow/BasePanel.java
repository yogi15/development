package apps.window.staticwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import util.CosmosException;
import util.commonUTIL;
import apps.window.staticwindow.util.BaseWindowUtil; 
import apps.window.util.propertyPane.enumsList.CurrencyDefaultPropertyEnumList;
import apps.window.util.propertyPane.enumsList.JavaScriptPropertyEnumList;
import apps.window.util.propertyPane.enumsList.WindowSheetEnumList;

import com.jidesoft.dialog.ButtonPanel;
import com.jidesoft.grid.CellStyleTable;
import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.QuickTableFilterField;
import com.jidesoft.grid.RowStripeTableStyleProvider;
import com.jidesoft.grid.SortableTable;
import com.jidesoft.grid.TableUtils;
import com.jidesoft.hints.ListDataIntelliHints;
import com.jidesoft.icons.JideIconsFactory;
import com.jidesoft.swing.JideBoxLayout;
import com.jidesoft.swing.SelectAllUtils;

import constants.CommonConstants;
import constants.CurrencyDefaultConstant;
import constants.JavaScriptConstants;
import constants.WindowSheetConstants;

public abstract class   BasePanel extends JPanel    {
	public JFrame frame = null;
	 /**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}
	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public void close() {
		
		validationActionUtil.clearALL();
		validationActionUtil  = null;
		frame.dispose();
	}
	/**
	 * 
	 */
	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;
	protected static final Color BACKGROUND1 = new Color(253, 253, 244);
	    protected static final Color BACKGROUND2 = new Color(230, 230, 255);
	    protected static final Color BACKGROUND3 = new Color(210, 255, 210);

	    protected static final Color FOREGROUND1 = new Color(0, 0, 10);

	    protected static final Color BACKGROUND4 = new Color(0, 128, 0);
	    protected static final Color FOREGROUND4 = new Color(255, 255, 255);
	    
	    public BaseWindowUtil validationActionUtil = null;
	    
	public abstract ActionMap getHotKeysActionMapper();
	public abstract JPanel getHotKeysPanel();
	public abstract ArrayList<Component> getFocusOrderList();
	 public abstract void  setWindowValidationUtil( );
	 public abstract void addTopLeftSidePanelComponents();
	 public abstract void addTopRigthSidePanelComponents();	 
	 public abstract void createPropertyPaneTable();
     
	 public abstract void addCenterRightSidePanelComponents();
	 /* add listerener to each Jcompenonts added subclass panel. */
	 public abstract void  setWindowActionListener()   ;
	public JScrollPane scrollPane = new JScrollPane();
	public SortableTable rightSideCenterTable = new    SortableTable();
	

    public final	JTextField WindowSheetSearchTextField = new JTextField("WindowSheetTextField"); // search textfield in leftTopPanel Data
	
	 
	 
	 protected JPanel splitLeftPanel = new JPanel(new BorderLayout());
	 protected JPanel splitRightPanel = new JPanel(new BorderLayout());
	 protected JPanel centerRightSidePanel = new JPanel( new BorderLayout() );
	 protected JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitLeftPanel, splitRightPanel);
	 
	 protected JPanel rightTopbuttonsPanel = new JPanel();
	 protected JPanel leftBottombuttonsPanel = new JPanel();
	 protected JPanel leftTopbuttonsPanel = new JPanel();
	
	 protected JPanel leftCenterPanel = new JPanel(new BorderLayout());
	 
	 
	 protected JButton loadButton = new JButton("Load");
		protected JButton newButton = new JButton("New");
		protected JButton deleteButton = new JButton("Delete");
		protected JButton saveButton = new JButton("Save");
		protected JButton saveAsNewButton = new JButton("Save As New");
		protected JButton closeButton = new JButton("Close");
		
		EventAction lSymAction = new EventAction();
		
		
		
		protected  void createSingleSplitPaneLayout(int splitPaneLocation) {
			
			validationActionUtil.windowstartUpData();
			addTopLeftSidePanelComponents();
			addTopRigthSidePanelComponents();
			createPropertyPaneTable();
			splitPane.setDividerLocation(splitPaneLocation);
			   scrollPane.getViewport().add(rightSideCenterTable);
		 
			   
		       splitRightPanel.add(centerRightSidePanel, BorderLayout.CENTER);
		       rightSideCenterTable.setTableStyleProvider(new RowStripeTableStyleProvider(new Color[]{BACKGROUND1, BACKGROUND3}));
			splitLeftPanel.add(leftTopbuttonsPanel, BorderLayout.NORTH);
		       splitLeftPanel.add(leftCenterPanel, BorderLayout.CENTER);
		       splitLeftPanel.add(leftBottombuttonsPanel, BorderLayout.SOUTH);
		       
		       splitRightPanel.add(rightTopbuttonsPanel, BorderLayout.NORTH);
		       rightSideCenterTable.setName(CommonConstants.RIGHTSIDECENTERTABLE);
			createleftBottomsPanel();
	        setRightPanelTopComp();
	      addCenterRightSidePanelComponents();
	      rightSideCenterTable.setRowSelectionAllowed(true);
	        setEventActionListener();
	      
	        add(splitPane  , BorderLayout.CENTER);
		}
		public void init() {
			  setWindowValidationUtil( ); 
		}
		
		protected void setEventActionListener( ) {
			loadButton.addActionListener(lSymAction);
			newButton.addActionListener(lSymAction);
			deleteButton.addActionListener(lSymAction);
			saveAsNewButton.addActionListener(lSymAction);
			saveButton.addActionListener(lSymAction);
			
			closeButton.addActionListener(lSymAction);
			rightSideCenterTable.addMouseListener(lSymAction); 
			rightSideCenterTable.addKeyListener(lSymAction);
			setWindowActionListener();
		}
		
		protected void setEventListener(JComponent comp) throws CosmosException {
			if(comp instanceof AbstractButton) {
				  (( JButton)comp).addActionListener(lSymAction);
			   } else 		   if(comp instanceof JTextField) {
						  (( JTextField )comp).addKeyListener(lSymAction);
						  
					   }else 		   if(comp instanceof JComponent) {
							  (( JComboBox<String>)comp).addItemListener(lSymAction);
					   }
			
		}
		
		protected void createleftBottomsPanel() {
		 JPanel buttonPanel = new ButtonPanel(SwingConstants.RIGHT);

			buttonPanel.add(buttons2ColumnForLeftBottonPanel(loadButton, newButton));
			buttonPanel.add(buttons2ColumnForLeftBottonPanel(saveAsNewButton, saveButton));
			buttonPanel.add(buttons2ColumnForLeftBottonPanel(deleteButton, closeButton)); 
			leftBottombuttonsPanel.add(buttonPanel, JideBoxLayout.FLEXIBLE);

		}
		protected void setRightPanelTopComp() {
			FlowLayout layout = new FlowLayout();
	    	layout.setAlignment(FlowLayout.LEFT);
	    	rightTopbuttonsPanel.setLayout(layout);
	    	
	    	
	    	
	     
		}
	    protected void setSearchOnLeftTopPanel(JTextField textField,String [] values) {
	    	 
	    	 
	    	SelectAllUtils.install(textField);
	    	if(!commonUTIL.isEmpty(values)) {
	    	  ListDataIntelliHints<String> fontIntelliHints = new ListDataIntelliHints<String>(
	    			textField, values);
	    	  fontIntelliHints.setCaseSensitive(false);
	    	}
	    	  
	    }
	   /* protected void setSearchOnLeftTopPanel(JTextField textField,List<String> values) {
	    	 
	    	 
	    	SelectAllUtils.install(textField);
	    	if(!commonUTIL.isEmpty(values)) {
	    	  ListDataIntelliHints<String> fontIntelliHints = new ListDataIntelliHints<String>(	textField, values);
	    	  fontIntelliHints.setCaseSensitive(false);
	    	}
	    	 
	    	  
	    }*/
		private ButtonPanel buttons2ColumnForLeftBottonPanel(JButton topButton, JButton botButton) {
			ButtonPanel buttonPanel = new ButtonPanel(SwingConstants.TOP);
			buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
			buttonPanel.add(topButton, ButtonPanel.AFFIRMATIVE_BUTTON);
			buttonPanel.add(botButton, ButtonPanel.AFFIRMATIVE_BUTTON);
			return buttonPanel;

		}
		public   List<Property>   generateProperty(String windowName) {
			List<Property> prope= validationActionUtil.generateProperty( windowName) ;
			
			if(commonUTIL.isEmpty(prope)) {
				if(windowName.equalsIgnoreCase("JavaScriptWindow"))
					prope = JavaScriptPropertyEnumList.JAVASCRIPT.getPropertyList(JavaScriptConstants.WINDOW_NAME);
				else 
				prope = WindowSheetEnumList.WINDOWSHEET.getPropertyList(WindowSheetConstants.WINDOW_NAME);
			}
			return prope;
		}
		
		
	 protected void setLeftSidePropertyPanePanel(PropertyTable propertyTable) {
		 JPanel quickSearchPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		 
	        QuickTableFilterField filterField = new QuickTableFilterField(propertyTable.getModel());
	        quickSearchPanel.add(filterField);
	        filterField.setHintText("Type here to filter properties");
	        filterField.setObjectConverterManagerEnabled(true);
	       
	        propertyTable.setModel(filterField.getDisplayTableModel()); 
	       
	      
	        propertyTable.setTableStyleProvider(new RowStripeTableStyleProvider());
	JPanel basePanel = new JPanel();
	JScrollPane tableScrollPane = new JScrollPane();
	tableScrollPane.getViewport().add(propertyTable);
	basePanel.setLayout(new BorderLayout());
	 
	basePanel.add(quickSearchPanel, BorderLayout.BEFORE_FIRST_LINE);

 basePanel.add(tableScrollPane, BorderLayout.CENTER);

 leftCenterPanel.add(basePanel, BorderLayout.CENTER);
	 }
	 
	 class EventAction
     implements java.awt.event.ActionListener,java.awt.event.ItemListener , java.awt.event.MouseListener,java.awt.event.KeyListener {
		@Override
		public void actionPerformed(ActionEvent event) {
            Cursor origCursor = getCursor();
            if(event.getActionCommand().equalsIgnoreCase(CommonConstants.CLOSEBUTTON)) {
				  close();
				  return;
			  }
            validationActionUtil.actionMapper(event.getActionCommand());
            setCursor(origCursor);
            
        }

		@Override
		public void itemStateChanged(ItemEvent event) {
			// TODO Auto-generated method stub
			 
	            Cursor origCursor = getCursor();
	            validationActionUtil.actionMapper(event.getItem().toString());
	            setCursor(origCursor);
	            
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			// window
			  Cursor origCursor = getCursor();
			  if(e.getComponent().getName().equalsIgnoreCase(CommonConstants.CLOSEBUTTON)) {
				  close();
			  }
			  
			  validationActionUtil.actionMapper(e.getComponent().getName());
	            setCursor(origCursor);
	            
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_UP ||e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_ENTER) {
				  Cursor origCursor = getCursor();
				  validationActionUtil.actionMapper(e.getComponent().getName());
		            setCursor(origCursor);
			}
		}

		 
		 

	 }

		 

		 

	 
		 
	 
}
