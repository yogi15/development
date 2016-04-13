package apps.window.staticwindow.util;

import java.util.Vector;
import apps.window.util.tableModelUtil.TableUtils;
import util.cacheUtil.ReferenceDataCache;
import util.commonUTIL;
import apps.window.staticwindow.BasePanel;
import apps.window.staticwindow.SearchConfigWindow;
import beans.SearchConfig;
import beans.WindowSheet;
import com.jidesoft.grid.Property;
import constants.CommonConstants;
import constants.SearchConfigConstants;
import constants.BeanConstants;

public class SearchConfigWindowUtil extends BaseWindowUtil {
	SearchConfigWindow searchconfigWindow = null;
	SearchConfig searchconfig = null;
	String searchconfigName;

	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return SearchConfigConstants.WINDOW_NAME;
	}

	/**
	 * @param windowName
	 *            the windowName to set
	 */
	public void setWindowName(String searchconfigName) {
		this.searchconfigName = searchconfigName;
	}

	/**
	 * @return the searchconfig
	 */
	public SearchConfig getSearchConfig() {
		return searchconfig;
	}

	/**
	 * @param searchconfig
	 *            the searchconfig to set
	 */
	public void setSearchConfig(SearchConfig searchconfig) {
		this.searchconfig = searchconfig;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		boolean flag = false;
		return validate(getSearchConfig(), SearchConfigConstants.WINDOW_NAME);
	}

	@Override
	public Vector<String> fillData(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionMapper(String action) {
		// TODO Auto-generated method stub
		Property prop = searchconfigWindow.propertyTable.getPropertyTable()
				.getSelectedProperty();
		if (action.equalsIgnoreCase(CommonConstants.SAVEASNEWBUTTON)) {
			saveAsNewButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.NEWBUTTON)) {
			newButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.LOADBUTTON)) {
			loadButtonAction();
		}
		if (action.equalsIgnoreCase(SearchConfigConstants.SEARCHTEXTBOX)) {
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
		if (action.equalsIgnoreCase(CommonConstants.HIERARACHICALTABLE)) {
			hierarachicalTableAction();
		}
		if (action.equalsIgnoreCase(SearchConfigConstants.LOADALLSEARCHCONFIG)) {
			loadButtonAction();
		}
	}

	@Override
	public void setWindow(BasePanel windowName) {
		// TODO Auto-generated method stub
		searchconfigWindow = (SearchConfigWindow) windowName;
		setSearchConfig(searchconfigWindow.getSearchConfig());
	}

	private void hierarachicalTableAction() {
		if (searchconfigWindow.hierarchicalTable.getSelectedRow() != -1) {
			searchconfigWindow.propertyTable
					.setPropertiesValues(searchconfigWindow.model
							.getRow(searchconfigWindow.hierarchicalTable
									.getSelectedRow()));
			searchconfigWindow.setSearchConfig(searchconfigWindow.model
					.getRow(searchconfigWindow.hierarchicalTable
							.getSelectedRow()));
			setSearchConfig(searchconfigWindow.model
					.getRow(searchconfigWindow.hierarchicalTable
							.getSelectedRow()));
		}
	}

	private void saveButtonAction() {
		searchconfigWindow.propertyTable.setfillValues(searchconfig);
		setSearchConfig((SearchConfig) searchconfigWindow.propertyTable
				.getBean());
		// if(validate( ))
		// if(ReferenceDataCache.updateSearchConfig(getSearchConfig())) {
		// if(searchconfigWindow.rightSideCenterTable.getSelectedRow() != -1) {
		// int i= TableUtils.getSelectedRowIndex(
		// searchconfigWindow.rightSideCenterTable);
		// searchconfigWindow.model.udpateValueAt(getSearchConfig(), i, 0);
		// }
		// }
	}

	private void saveAsNewButtonAction() {
		SearchConfig searchconfig = new SearchConfig();
		searchconfigWindow.propertyTable.setfillValues(searchconfig);
		setSearchConfig(searchconfig);
		 if(validate( )){
				 searchconfig = (SearchConfig) ReferenceDataCache.insertSQL(searchconfig, BeanConstants.SEARCHCONFIG);
				 searchconfigWindow.model.addRow(getSearchConfig());
				 setSearchConfig(searchconfig);
		 }
	}

	private void newButtonAction() {
		searchconfigWindow.propertyTable.clearPropertyValues();
		searchconfigWindow.model.clear();
		setSearchConfig(null);
	}

	private void loadButtonAction() {
		newButtonAction();
		String searchText = searchconfigWindow.searchconfigSearchTextField
				.getText();
		if (!commonUTIL.isEmpty(searchText)) {
			Vector<SearchConfig> data = null;// ReferenceDataCache.selectSearchConfigs(searchText);
			searchconfigWindow.model.clear();
			if (!commonUTIL.isEmpty(data)) {
				SearchConfig firstRecord = data.get(0);
				for (int i = 0; i < data.size(); i++) {
					searchconfigWindow.model.addRow((SearchConfig) data.get(i));
				}
				searchconfigWindow.propertyTable
						.setPropertiesValues(firstRecord);
				setSearchConfig(firstRecord);
			}
		} else {
			Vector<SearchConfig> data = (Vector<SearchConfig>) ReferenceDataCache
					.selectALLData(BeanConstants.SEARCHCONFIG);
			if (!commonUTIL.isEmpty(data)) {
				searchconfigWindow.model.clear();
				SearchConfig firstRecord = data.get(0);
				for (int i = 0; i < data.size(); i++) {
					searchconfigWindow.model.addRow((SearchConfig) data.get(i));
				}
				searchconfigWindow.propertyTable
						.setPropertiesValues(firstRecord);
				setSearchConfig(firstRecord);
			}
		}
	}

	public void loadData(int id) {
		newButtonAction();
		Vector<SearchConfig> data = null;// ReferenceDataCache.getSearchConfig(id);
		searchconfigWindow.model.clear();
		if (!commonUTIL.isEmpty(data)) {
			SearchConfig firstRecord = data.get(0);
			for (int i = 0; i < data.size(); i++) {
				searchconfigWindow.model.addRow((SearchConfig) data.get(i));
			}
			searchconfigWindow.propertyTable.setPropertiesValues(firstRecord);
			setSearchConfig(firstRecord);
		}
	}

	private void rightSideCenterTableAction() {
		if (searchconfigWindow.rightSideCenterTable.getSelectedRow() != -1)
			searchconfigWindow.propertyTable
					.setPropertiesValues(searchconfigWindow.model
							.getRow(searchconfigWindow.rightSideCenterTable
									.getSelectedRow()));
		searchconfigWindow.setSearchConfig(searchconfigWindow.model
				.getRow(searchconfigWindow.rightSideCenterTable
						.getSelectedRow()));
		setSearchConfig(searchconfigWindow.model
				.getRow(searchconfigWindow.rightSideCenterTable
						.getSelectedRow()));
	}

	private void searchTextAction() {
		loadButtonAction();
	}

	// check Null pointerException.
	private void deleteButtonAction() {
		try {
			// if(ReferenceDataCache.deleteSearchConfig(searchconfig)) {
			// if( searchconfigWindow.rightSideCenterTable.getSelectedRow() !=
			// -1) {
			// searchconfigWindow.model.delRow(searchconfigWindow.rightSideCenterTable.getSelectedRow());
			// }
			// setSearchConfig(null);
			// searchconfigWindow.propertyTable.clearPropertyValues();
			// }
		} catch (Exception e) {
			commonUTIL.displayError(SearchConfigConstants.WINDOW_NAME + "Util",
					"deleteButtonAction", e);
		}
	}

	@Override
	public void windowstartUpData() {

	}

	@Override
	public void clearALL() {
	}
}