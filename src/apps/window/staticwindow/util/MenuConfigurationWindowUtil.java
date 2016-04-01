package apps.window.staticwindow.util;

import java.util.Vector;
import apps.window.util.tableModelUtil.TableUtils;
import apps.window.util.windowUtil.DynamicMenu;
import util.ReferenceDataCache;
import util.commonUTIL;
import apps.window.staticwindow.BasePanel;
import apps.window.staticwindow.MenuConfigurationWindow;
import beans.MenuConfiguration; 
import com.jidesoft.grid.Property;
import constants.CommonConstants;
import constants.MenuConfigurationConstants;

public class MenuConfigurationWindowUtil extends BaseWindowUtil {
	MenuConfigurationWindow menuconfigurationWindow = null;
	MenuConfiguration menuconfiguration = null;
	String menuconfigurationName;

	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return MenuConfigurationConstants.WINDOW_NAME;
	}

	/**
	 * @param windowName
	 *            the windowName to set
	 */
	public void setWindowName(String menuconfigurationName) {
		this.menuconfigurationName = menuconfigurationName;
	}

	/**
	 * @return the menuconfiguration
	 */
	public MenuConfiguration getMenuConfiguration() {
		return menuconfiguration;
	}

	/**
	 * @param menuconfiguration
	 *            the menuconfiguration to set
	 */
	public void setMenuConfiguration(MenuConfiguration menuconfiguration) {
		this.menuconfiguration = menuconfiguration;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		boolean flag = false;
		return validate(getMenuConfiguration(),
				MenuConfigurationConstants.WINDOW_NAME);
	}

	@Override
	public Vector<String> fillData(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionMapper(String action) {
		// TODO Auto-generated method stub
		Property prop = menuconfigurationWindow.propertyTable
				.getPropertyTable().getSelectedProperty();
		if (action.equalsIgnoreCase(CommonConstants.SAVEASNEWBUTTON)) {
			saveAsNewButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.NEWBUTTON)) {
			newButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.LOADBUTTON)) {
			loadButtonAction();
		}
		if (action.equalsIgnoreCase(MenuConfigurationConstants.SEARCHTEXTBOX)) {
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
		if (action.equalsIgnoreCase(MenuConfigurationConstants.LOADMENU)) {
			showDemoMenuAction();
		}
	}

	private void showDemoMenuAction() {
		// TODO Auto-generated method stub
		Vector<MenuConfiguration> menu = ReferenceDataCache.selectMenuConfigurations( );
		DynamicMenu  dym = new DynamicMenu();
		dym.setMenuData(menu);
		dym.showFrame(dym);
		
	}

	@Override
	public void setWindow(BasePanel windowName) {
		// TODO Auto-generated method stub
		menuconfigurationWindow = (MenuConfigurationWindow) windowName;
		setMenuConfiguration(menuconfigurationWindow.getMenuConfiguration());
	}

	private void saveButtonAction() {
		menuconfigurationWindow.propertyTable.setfillValues(menuconfiguration);
		setMenuConfiguration((MenuConfiguration) menuconfigurationWindow.propertyTable
				.getBean());
		if (validate())
			if (ReferenceDataCache
					.updateMenuConfiguration(getMenuConfiguration())) {
				if (menuconfigurationWindow.rightSideCenterTable
						.getSelectedRow() != -1) {
					int i = TableUtils
							.getSelectedRowIndex(menuconfigurationWindow.rightSideCenterTable);
					menuconfigurationWindow.model.udpateValueAt(
							getMenuConfiguration(), i, 0);
				}
			}
	}

	private void saveAsNewButtonAction() {
		MenuConfiguration menuconfiguration = new MenuConfiguration();
		menuconfigurationWindow.propertyTable.setfillValues(menuconfiguration);
		setMenuConfiguration(menuconfiguration);
		if(validate( )){
		  menuconfiguration =   ReferenceDataCache.saveMenuConfiguration(menuconfiguration);
	 
		 menuconfigurationWindow.model.addRow(menuconfiguration);
		 setMenuConfiguration(menuconfiguration);
		 }
	}

	private void newButtonAction() {
		menuconfigurationWindow.propertyTable.clearPropertyValues();
		menuconfigurationWindow.model.clear();
		setMenuConfiguration(null);
	}

	private void loadButtonAction() {
		newButtonAction();
		String searchText = menuconfigurationWindow.MenuConfigurationSearchTextField
				.getText();
		if (!commonUTIL.isEmpty(searchText)) {
			Vector<MenuConfiguration> data = null;// ReferenceDataCache.selectMenuConfigurations(searchText);
			menuconfigurationWindow.model.clear();
			if (!commonUTIL.isEmpty(data)) {
				MenuConfiguration firstRecord = data.get(0);
				for (int i = 0; i < data.size(); i++) {
					menuconfigurationWindow.model
							.addRow((MenuConfiguration) data.get(i));
				}
				menuconfigurationWindow.propertyTable
						.setPropertiesValues(firstRecord);
				setMenuConfiguration(firstRecord);
			}
		}
	}

	private void rightSideCenterTableAction() {
		if (menuconfigurationWindow.rightSideCenterTable.getSelectedRow() != -1)
			menuconfigurationWindow.propertyTable
					.setPropertiesValues(menuconfigurationWindow.model
							.getRow(menuconfigurationWindow.rightSideCenterTable
									.getSelectedRow()));
		menuconfigurationWindow
				.setMenuConfiguration(menuconfigurationWindow.model
						.getRow(menuconfigurationWindow.rightSideCenterTable
								.getSelectedRow()));
		setMenuConfiguration(menuconfigurationWindow.model
				.getRow(menuconfigurationWindow.rightSideCenterTable
						.getSelectedRow()));
	}

	private void searchTextAction() {
		loadButtonAction();
	}

	// check Null pointerException.
	private void deleteButtonAction() {
		try {
			 if(ReferenceDataCache.deleteMenuConfiguration(menuconfiguration))
			{
			 if( menuconfigurationWindow.rightSideCenterTable.getSelectedRow()
			 != -1) {
			 menuconfigurationWindow.model.delRow(menuconfigurationWindow.rightSideCenterTable.getSelectedRow());
			 }
			 setMenuConfiguration(null);
			 menuconfigurationWindow.propertyTable.clearPropertyValues();
		 }
		} catch (Exception e) {
			commonUTIL.displayError(MenuConfigurationConstants.WINDOW_NAME
					+ "Util", "deleteButtonAction", e);
		}
	}

	@Override
	public void windowstartUpData() {

	}

	@Override
	public void clearALL() {
	}
}