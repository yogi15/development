package apps.window.staticwindow.util;

import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.TableColumn;

import util.CosmosException;
import util.commonUTIL;
import util.cacheUtil.ReferenceDataCache;
import apps.window.staticwindow.BasePanel;
import apps.window.staticwindow.TestingSampleProperty;
import apps.window.staticwindow.WindowSheetWindow;
import apps.window.util.propertyTable.CellStyleProrpertyTable;
import apps.window.util.tableModelUtil.TableUtils;
import beans.PropertyCellStyle;
import beans.WindowSheet;

import com.jidesoft.grid.Property;

import constants.BeanConstants;
import constants.CommonConstants;
import constants.WindowSheetConstants;
import constants.WindowTableModelMappingConstants;

public class WindowSheetWindowUtil extends BaseWindowUtil {
	 WindowSheetWindow windowSheetWindow= null;
	   WindowSheet windowSheet = null;
      String windowName = "";
	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return WindowSheetConstants.WINDOW_NAME;
	}

	/**
	 * @param windowName the windowName to set
	 */
	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}

	/**
	 */
	public WindowSheet getWindowSheet() {
		return windowSheet;
	}

	/**
	 * @param windowSheet the windowSheet to set
	 */
	public void setWindowSheet(WindowSheet windowS) {
		  windowSheet = windowS;
	}

	@Override
	public boolean validate( ) {
		// TODO Auto-generated method stub
		boolean flag = false;
		 
		if(commonUTIL.isEmpty(getWindowSheet().getWindowName())) {
			commonUTIL.showAlertMessage("Window Name Required " );
			return flag;
		}
		if(commonUTIL.isEmpty(getWindowSheet().getFieldName())) {
			commonUTIL.showAlertMessage("Field Name Required " );
			return flag;
		}
		if(commonUTIL.isEmpty(getWindowSheet().getDataType())) {
			commonUTIL.showAlertMessage("Data Type Required " );
			return flag;
		}
		if(commonUTIL.isEmpty(getWindowSheet().getCategory())) {
			commonUTIL.showAlertMessage("Category Type Required " );
			return flag;
		}
		if((getWindowSheet().getIsStartupdata()== 1) && (commonUTIL.isEmpty(getWindowSheet().getStartUpDataName()))) {
			commonUTIL.showAlertMessage("Select Startupdata Name " );
			return flag;
		}
		Vector<WindowSheet> recordexist = ReferenceDataCache.getWindowSheet(getWindowSheet());
		 
		for(int i=0;i<recordexist.size();i++) {
			 
			    commonUTIL.showAlertMessage(windowSheet.getFieldName() + " Field Already Exist " );
			    return flag;
			
			
		}
		    flag = true;
		
		return flag;
	}

	@Override
	public Vector<String> fillData(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionMapper(String action) {
		// TODO Auto-generated method stub 
		if(action.equalsIgnoreCase(CommonConstants.SAVEASNEWBUTTON)) {
			saveAsNewButtonAction();
		}
		if(action.equalsIgnoreCase(CommonConstants.NEWBUTTON)) {
			newButtonAction();
		}
		if(action.equalsIgnoreCase(CommonConstants.LOADBUTTON)) {
			 loadButtonAction();
		 
		}
		if(action.equalsIgnoreCase(CommonConstants.CLOSEBUTTON)) {
			 closeButtonAction();
		 
		}
		if(action.equalsIgnoreCase(WindowSheetConstants.SEARCHTEXTBOX)) {
			searchTextAction();
		}
		
		if(action.equalsIgnoreCase(CommonConstants.RIGHTSIDECENTERTABLE)) {
			rightSideCenterTableAction();
		}
		if(action.equalsIgnoreCase(CommonConstants.DELETEBUTTON)) {
			deleteButtonAction();
		}
		if(action.equalsIgnoreCase(WindowSheetConstants.LOADSCRIPTS)) {
			loadScriptAction();
		}
		if(action.equalsIgnoreCase(CommonConstants.SAVEBUTTON)) {
			saveButtonAction();
		}
		if(action.equalsIgnoreCase(WindowSheetConstants.LOADJAVASCRIPTS)) {
			genearteJavaScript();
		}
		if(action.equalsIgnoreCase(WindowSheetConstants.PROPERTYPREVIEW)) {
			previewProperty();
		}
	}

	private void previewProperty() {
		// TODO Auto-generated method stub
		String windowName = windowSheetWindow.propertyTable.getwSheet().getWindowName();
		if(commonUTIL.isEmpty(windowName))
			return;
		List<Property> preProps =null;
		try {
			preProps = generateProperty(windowName);
		} catch (CosmosException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError(WindowTableModelMappingConstants.WINDOW_NAME, "previewProperty", e);
		}
		if(preProps != null) {
		TestingSampleProperty frame = new TestingSampleProperty(preProps,windowName);
		 
	 
	 
	//frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	frame.setTitle(windowName +"Preview ");
 
	frame.setSize(250, 300);
	frame.getContentPane().setPreferredSize(frame.getSize());
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setVisible(true); 
		}
	}

	private void genearteJavaScript() {
		// TODO Auto-generated method stub
		String windowName = windowSheetWindow.propertyTable.getwSheet().getWindowName();
		if(windowSheetWindow.packageD.getSelectedIndex() != -1); {
		String packName = windowSheetWindow.packageD.getSelectedItem().toString();
		 if(!commonUTIL.isEmpty(windowName)&&!commonUTIL.isEmpty(packName)) {
			 JavaScriptWindowUtil.loadJavaScripts(windowName,packName);
			 
		 }
		}
		
	}

	private void saveButtonAction() {
		// TODO Auto-generated method stub
		int key =  windowSheetWindow.propertyTable.getwSheet().getColumnSerialNo();
		windowSheetWindow.propertyTable.setfillValues();
		windowSheetWindow.propertyTable.getwSheet().setColumnSerialNo(key);
		 setWindowSheet(windowSheetWindow.propertyTable.getwSheet());
		 windowSheet = windowSheetWindow.propertyTable.getwSheet();
		if(validate( )) 
			windowSheet.setColumnSerialNo(key);
		  if(ReferenceDataCache.updateWindowSheet(windowSheet)) {
			  if(windowSheetWindow.rightSideCenterTable.getSelectedRow() != -1) {
				  int i=  TableUtils.getSelectedRowIndex( windowSheetWindow.rightSideCenterTable);
				  windowSheetWindow.model.udpateValueAt(windowSheet, i, 0);
			  }
			
			 
		  }
		
	}

	private void loadScriptAction() {
		// TODO Auto-generated method stub
		String searchText =(String) windowSheetWindow.propertyTable.getPropertyTable().getPropertyTableModel().getProperty(WindowSheetConstants.WINDOWNAME).getValue();
		Vector<WindowSheet> data = ReferenceDataCache.selectWindowSheets(searchText);
		String insertSQL =WindowSheetConstants.INSERTSQL;
		windowSheetWindow.textArea.setText("");
		if(!commonUTIL.isEmpty(data)) {
			for(int i=0;i<data.size();i++) {
				WindowSheet ws = data.get(i);
				insertSQL = insertSQL + "'"+ws.getWindowName() + "',";
				insertSQL = insertSQL + "'"+ws.getFieldName() + "',";
				insertSQL = insertSQL + "'"+ws.getDataType() + "',";
				insertSQL = insertSQL + "'"+ws.getIsStartupdata() + "',";
				insertSQL = insertSQL + "'"+ws.getStartUpDataName() + "',";

				insertSQL = insertSQL + "'"+ws.getCustomPanelName() + "',";
               
				insertSQL = insertSQL + "'"+ws.getDefaultValue() + "',";
				if(ws.isNullChecked()) 
					insertSQL = insertSQL + "'Y',";
				else 
					insertSQL = insertSQL + "'N',";
				insertSQL = insertSQL + "'"+ws.getCategory() + "',";
				insertSQL = insertSQL + ws.getColumnSerialNo()+");\n";
				windowSheetWindow.textArea.append(insertSQL);
			}
		}
		windowSheetWindow.model.clear();
	}

	private void closeButtonAction() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void setWindow(BasePanel windowName) {
		// TODO Auto-generated method stub
		windowSheetWindow = (WindowSheetWindow)windowName;
		setWindowSheet(windowSheetWindow.getWindowSheet()); 
		
			
	}
	
	
	private void saveAsNewButtonAction() { 
		windowSheetWindow.propertyTable.setfillValues();
		 setWindowSheet(windowSheetWindow.propertyTable.getwSheet());
		 windowSheet = windowSheetWindow.propertyTable.getwSheet();
		if(validate( )) {
			windowSheet = ReferenceDataCache.saveWindowSheet(windowSheet);
		     windowSheetWindow.model.setColumnName(windowSheet.getDesignType() + "Name");
			  windowSheetWindow.model.addRow(windowSheet);
			  setWindowSheet(windowSheet);
		  }
	}
	private void newButtonAction() {
	List<Property> prop=	windowSheetWindow.propertyTable.getPropertyTable().getPropertyTableModel().getProperties();
	//for()
	 windowSheetWindow.propertyTable.clearPropertyValues();
	  windowSheetWindow.model.clear();
	  setWindowSheet(null);
	}
	
	
	private void loadButtonAction() {
		
		String searchText =   windowSheetWindow.WindowSheetSearchTextField.getText();
		String designType = (String) windowSheetWindow.propertyTable.getPropertyTable().getPropertyTableModel().getProperty(WindowSheetConstants.DESIGNTYPE).getValue();
		newButtonAction();
		if(commonUTIL.isEmpty(designType)) {
			 commonUTIL.showAlertMessage("Enter DesignType");
			 return;
		 }
			 
		if(!commonUTIL.isEmpty(searchText) && !commonUTIL.isEmpty(designType)) {
			Vector<WindowSheet> data = ReferenceDataCache.selectWindowSheets(searchText,designType);
			windowSheetWindow.model.clear();
			// windowSheetWindow.model.setColumnNames(windowSheet.getDesignType() + "Name");
			if(!commonUTIL.isEmpty(data)) {
				WindowSheet firstRecord = data.get(0);
				for(int i=0;i<data.size();i++) {
					windowSheetWindow.model.addRow((WindowSheet)data.get(i));
					windowSheetWindow.propertyTable.addFieldName(((WindowSheet)data.get(i)).getFieldName());
					
				}
				windowSheetWindow.propertyTable.setPropertiesValues(firstRecord);
				 setWindowSheet(firstRecord);
			}
			
		 }
	}
	
	
	private void rightSideCenterTableAction() {
		if(windowSheetWindow.rightSideCenterTable.getSelectedRow() != -1) 
			 windowSheetWindow.propertyTable.setPropertiesValues( windowSheetWindow.model.getRow(windowSheetWindow.rightSideCenterTable.getSelectedRow()));
		windowSheetWindow.setWindowSheet( windowSheetWindow.propertyTable.getwSheet());
		setWindowSheet(windowSheetWindow.propertyTable.getwSheet());
	}
	
	private void searchTextAction() {
		 
		loadButtonAction();
		DefaultListModel<String> newFieldNames = new DefaultListModel<String>();
		windowSheetWindow.cellStyle.clearFieldNames();
		Vector<String> rows = new Vector<String>();
		String col [] = new String[windowSheetWindow.model.getRowCount()];
		for(int i=0;i<windowSheetWindow.model.getRowCount();i++) {
			newFieldNames.add(0,  ((WindowSheet)windowSheetWindow.model.getRow(i)).getFieldName());
			 
		}
		windowSheetWindow.cellStyle.addFieldNames(newFieldNames);
		setCellStyles(windowSheetWindow.cellStyle);
		 
	}
	
	 
	// check Null pointerException.
	private void deleteButtonAction() {
		

		if(ReferenceDataCache.deleteWindowSheet(windowSheet)) {
			if( windowSheetWindow.rightSideCenterTable.getSelectedRow() != -1) {
				windowSheetWindow.model.delRow(windowSheetWindow.rightSideCenterTable.getSelectedRow()); 
			}
			setWindowSheet(null);
			clearAllPropertiesFields = true;
			 windowSheetWindow.propertyTable.clearPropertyValues();

			 clearAllPropertiesFields = false;
			}
		 

	}
	 

	@Override
	public void windowstartUpData() {
		// TODO Auto-generated method stub
	 
		String curr [] = {""};
		  Vector<String> windowS=	ReferenceDataCache.getStarupData(WindowSheetConstants.WINDOWNAME);
		  windowSheetWindow.searchData = commonUTIL.convertStartupVectortoStringArray(windowS);
		  Vector<String> packageData=	ReferenceDataCache.getStarupData(WindowSheetConstants.PACKAGENAME);
		  windowSheetWindow.packageData = commonUTIL.convertStartupVectortoStringArray(packageData);
		  DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(windowSheetWindow.packageData);
		  windowSheetWindow.packageD.setModel(model);
		 
		  
		 
	}
    
	@Override
	public void clearALL() {
		// TODO Auto-generated method stub 
		
	}

	 
	 
	public void savePropertyStyle(Vector<PropertyCellStyle> styleCells) {
		// TODO Auto-generated method stub
	String windowName= 	(String) windowSheetWindow.propertyTable.getPropertyTable().getPropertyTableModel().getProperty(WindowSheetConstants.WINDOWNAME).getValue();
		if(!commonUTIL.isEmpty(styleCells)) {
			for(int i=0;i<styleCells.size();i++) {
			PropertyCellStyle styleCell = styleCells.get(i);
			styleCell.setWindowname(windowName);
		ReferenceDataCache.insertSQL(styleCell, BeanConstants.PROPERTYCELLSTYLE);
			}
		}
		
	}

	public void setCellStyles(CellStyleProrpertyTable cellStyle) {
		// TODO Auto-generated method stub
		String searchText =   windowSheetWindow.WindowSheetSearchTextField.getText();
		  String sql = "WindowName = '"+searchText+"'";
		     Vector<PropertyCellStyle> propCellSty =   (Vector<PropertyCellStyle>) ReferenceDataCache.selectWhere(sql, BeanConstants.PROPERTYCELLSTYLE);
		     if(!commonUTIL.isEmpty(propCellSty)) {
		    	 cellStyle.setCellStyle(propCellSty);
		     }
		
	}
}
