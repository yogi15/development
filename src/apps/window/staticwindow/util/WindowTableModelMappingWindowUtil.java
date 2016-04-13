package apps.window.staticwindow.util;

import java.util.Vector;
import util.commonUTIL;
import util.cacheUtil.ReferenceDataCache;
import apps.window.staticwindow.BasePanel;
import apps.window.staticwindow.WindowTableModelMappingWindow;
import apps.window.util.tableModelUtil.TableUtils;
import beans.WindowTableModelMapping;
import com.jidesoft.grid.Property;

import constants.BeanConstants;
import constants.CommonConstants;
import constants.WindowSheetConstants;
import constants.WindowTableModelMappingConstants;

public class WindowTableModelMappingWindowUtil extends BaseWindowUtil {
	WindowTableModelMappingWindow windowtablemodelmappingWindow = null;
	WindowTableModelMapping windowtablemodelmapping = null;
	String windowtablemodelmappingName;
    
	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return WindowTableModelMappingConstants.WINDOW_NAME;
	}

	/**
	 * @param windowName
	 *            the windowName to set
	 */
	public void setWindowName(String windowtablemodelmappingName) {
		this.windowtablemodelmappingName = windowtablemodelmappingName;
	}

	/**
	 * @return the windowtablemodelmapping
	 */
	public WindowTableModelMapping getWindowTableModelMapping() {
		return windowtablemodelmapping;
	}

	/**
	 * @param windowtablemodelmapping
	 *            the windowtablemodelmapping to set
	 */
	public void setWindowTableModelMapping(
			WindowTableModelMapping windowtablemodelmapping) {
		this.windowtablemodelmapping = windowtablemodelmapping;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		boolean flag = false;
		return validate(getWindowTableModelMapping(),
				WindowTableModelMappingConstants.WINDOW_NAME);
	}

	@Override
	public Vector<String> fillData(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionMapper(String action) {
		// TODO Auto-generated method stub
		
		if (action.equalsIgnoreCase(CommonConstants.SAVEASNEWBUTTON)) {
			saveAsNewButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.NEWBUTTON)) {
			newButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.LOADBUTTON)) {
			loadButtonAction();
		}
		if (action
				.equalsIgnoreCase(WindowTableModelMappingConstants.SEARCHTEXTBOX)) {
			searchTextAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.RIGHTSIDECENTERTABLE)) {
			rightSideCenterTableAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.DELETEBUTTON)) {
			deleteButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.SAVEBUTTON)) {
			saveButtonAction();
		}
	}

	private void saveButtonAction() {
		// TODO Auto-generated method stub
		windowtablemodelmappingWindow.propertyTable
		.setfillValues(windowtablemodelmapping);
		setWindowTableModelMapping((WindowTableModelMapping) windowtablemodelmappingWindow.propertyTable.getBean());
		if(validate( )) 
			 
		 // if(ReferenceDataCache.updateWindowTableModelMapping(getWindowTableModelMapping())) {
			if(ReferenceDataCache.updateSQL(getWindowTableModelMapping(),BeanConstants.WINDOWTABLEMODELMAPPING)) {
			  if(windowtablemodelmappingWindow.rightSideCenterTable.getSelectedRow() != -1) {
				  int i=  TableUtils.getSelectedRowIndex( windowtablemodelmappingWindow.rightSideCenterTable);
				  windowtablemodelmappingWindow.model.udpateValueAt(getWindowTableModelMapping(), i, 0);
			  }
			
			 
		  }
		
	}

	@Override
	public void setWindow(BasePanel windowName) {
		// TODO Auto-generated method stub
		windowtablemodelmappingWindow = (WindowTableModelMappingWindow) windowName;
		setWindowTableModelMapping(windowtablemodelmappingWindow
				.getWindowTableModelMapping());
	}

	private void saveAsNewButtonAction() {
		WindowTableModelMapping windowtablemodelmapping = new WindowTableModelMapping();
		windowtablemodelmappingWindow.propertyTable
				.setfillValues(windowtablemodelmapping);
		setWindowTableModelMapping(windowtablemodelmapping);
		
		 
		if (validate()) {
			windowtablemodelmapping= (WindowTableModelMapping) ReferenceDataCache
					.insertSQL(windowtablemodelmapping,BeanConstants.WINDOWTABLEMODELMAPPING) ;
		 
				windowtablemodelmappingWindow.model
						.addRow(windowtablemodelmapping);
				setWindowTableModelMapping(windowtablemodelmapping);
			}
	}

	private void newButtonAction() {
		windowtablemodelmappingWindow.propertyTable.clearPropertyValues();
		windowtablemodelmappingWindow.model.clear();
		setWindowTableModelMapping(null);
	}

	private void loadButtonAction() {
		newButtonAction();
		String searchText = windowtablemodelmappingWindow.WindowTableModelMappingSearchTextField
				.getText();
		if (!commonUTIL.isEmpty(searchText)) {
			Vector<WindowTableModelMapping> data = (Vector<WindowTableModelMapping>) ReferenceDataCache
					.selectWhere(searchText,BeanConstants.WINDOWTABLEMODELMAPPING);
			windowtablemodelmappingWindow.model.clear();
			if (!commonUTIL.isEmpty(data)) {
				WindowTableModelMapping firstRecord = data.get(0);
				for (int i = 0; i < data.size(); i++) {
					windowtablemodelmappingWindow.model
							.addRow((WindowTableModelMapping) data.get(i));
				}
				windowtablemodelmappingWindow.propertyTable
						.setPropertiesValues(firstRecord);
				setWindowTableModelMapping(firstRecord);
			}
		}
	}

	private void rightSideCenterTableAction() {
		if (windowtablemodelmappingWindow.rightSideCenterTable.getSelectedRow() != -1)
			
			windowtablemodelmappingWindow.propertyTable
					.setPropertiesValues(windowtablemodelmappingWindow.model
							.getRow(windowtablemodelmappingWindow.rightSideCenterTable
									.getSelectedRow()));
		windowtablemodelmappingWindow
				.setWindowTableModelMapping(windowtablemodelmappingWindow.model
						.getRow(windowtablemodelmappingWindow.rightSideCenterTable
								.getSelectedRow()));
		setWindowTableModelMapping(windowtablemodelmappingWindow.model
				.getRow(windowtablemodelmappingWindow.rightSideCenterTable
						.getSelectedRow()));
	}

	private void searchTextAction() {
		loadButtonAction();
	}

	// check Null pointerException.
	private void deleteButtonAction() {
		try {
			if(windowtablemodelmapping == null)
				return;
			if (ReferenceDataCache
					.deleteSQL(  windowtablemodelmapping,BeanConstants.WINDOWTABLEMODELMAPPING)) {
				if (windowtablemodelmappingWindow.rightSideCenterTable
						.getSelectedRow() != -1) {
					windowtablemodelmappingWindow.model
							.delRow(windowtablemodelmappingWindow.rightSideCenterTable
									.getSelectedRow());
				}
				setWindowTableModelMapping(null);
				windowtablemodelmappingWindow.propertyTable
						.clearPropertyValues();
			}
		} catch (Exception e) {
			commonUTIL.displayError(
					WindowTableModelMappingConstants.WINDOW_NAME + "Util",
					"deleteButtonAction", e);
		}
	}

	@Override
	public void windowstartUpData() {
		
		 Vector<String> windowS=	ReferenceDataCache.getStarupData(WindowSheetConstants.WINDOWNAME);
		 windowtablemodelmappingWindow.searchData = commonUTIL.convertStartupVectortoStringArray(windowS);
		 
		 
	}

	@Override
	public void clearALL() {
		// TODO Auto-generated method stub
		windowtablemodelmappingWindow.propertyTable = null;
	}
}