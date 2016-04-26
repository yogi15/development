package apps.window.staticwindow.util;

import java.util.Vector;
import apps.window.util.tableModelUtil.TableUtils;
import util.cacheUtil.ReferenceDataCache;
import util.commonUTIL;
import apps.window.staticwindow.BasePanel;
import apps.window.referencewindow.CounterPartyWindow;
import beans.LegalEntity;
import beans.WindowSheet;
import com.jidesoft.grid.Property;
import constants.CommonConstants;
import constants.LegalEntityConstants;
import constants.BeanConstants;

public class CounterPartyWindowUtil extends BaseWindowUtil {
	CounterPartyWindow counterpartyWindow = null;
	LegalEntity counterparty = null;
	String counterpartyName;

	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return LegalEntityConstants.WINDOW_NAME;
	}

	/**
	 * @param windowName
	 *            the windowName to set
	 */
	public void setWindowName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}

	/**
	 * @return the counterparty
	 */
	public LegalEntity getLegalEntity() {
		return counterparty;
	}

	/**
	 * @param counterparty
	 *            the counterparty to set
	 */
	public void setLegalEntity(LegalEntity counterparty) {
		this.counterparty = counterparty;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		boolean flag = false;
		return validate(getLegalEntity(), LegalEntityConstants.WINDOW_NAME);
	}

	@Override
	public Vector<String> fillData(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionMapper(String action) {
		// TODO Auto-generated method stub
		Property prop = counterpartyWindow.propertyTable.getPropertyTable().getSelectedProperty();
		if (action.equalsIgnoreCase(CommonConstants.SAVEASNEWBUTTON)) {
			saveAsNewButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.NEWBUTTON)) {
			newButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.LOADBUTTON)) {
			loadButtonAction();
		}
		if (action.equalsIgnoreCase(LegalEntityConstants.SEARCHTEXTBOX)) {
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
		if (action.equalsIgnoreCase(LegalEntityConstants.LOADALLLEGALENTITY)) {
			loadButtonAction();
		}
	}

	@Override
	public void setWindow(BasePanel windowName) {
		// TODO Auto-generated method stub
		counterpartyWindow = (CounterPartyWindow) windowName;
		setLegalEntity(counterpartyWindow.getLegalEntity());
	}

	private void hierarachicalTableAction() {
		if (counterpartyWindow.hierarchicalTable.getSelectedRow() != -1) {
			counterpartyWindow.propertyTable.setPropertiesValues(
					counterpartyWindow.model.getRow(counterpartyWindow.hierarchicalTable.getSelectedRow()));
			counterpartyWindow.setLegalEntity(
					counterpartyWindow.model.getRow(counterpartyWindow.hierarchicalTable.getSelectedRow()));
			setLegalEntity(counterpartyWindow.model.getRow(counterpartyWindow.hierarchicalTable.getSelectedRow()));
		}
	}

	private void saveButtonAction() {
		counterpartyWindow.propertyTable.setfillValues(counterparty);
		setLegalEntity((LegalEntity) counterpartyWindow.propertyTable.getBean());
		// if(validate( ))
		if (ReferenceDataCache.updateSQL(getLegalEntity(), BeanConstants.LEGALENTITY)) {
			if (counterpartyWindow.rightSideCenterTable.getSelectedRow() != -1) {
				int i = TableUtils.getSelectedRowIndex(counterpartyWindow.rightSideCenterTable);
				counterpartyWindow.model.udpateValueAt(getLegalEntity(), i, 0);
			}
		}
	}

	private void saveAsNewButtonAction() {
		LegalEntity counterparty = new LegalEntity();
		counterpartyWindow.propertyTable.setfillValues(counterparty);
		setLegalEntity(counterparty);
		// if(validate( )){
		 counterparty = (LegalEntity) ReferenceDataCache.insertSQL(counterparty, BeanConstants.LEGALENTITY);
		 counterpartyWindow.model.addRow(getLegalEntity());
		 setLegalEntity(counterparty);
		 //}
	}

	private void newButtonAction() {
		counterpartyWindow.propertyTable.clearPropertyValues();
		counterpartyWindow.model.clear();
		setLegalEntity(null);
	}

	private void loadButtonAction() {
		newButtonAction();
		String searchText = counterpartyWindow.counterpartySearchTextField.getText();

		if (commonUTIL.isEmpty(searchText)) {
			Vector<LegalEntity> data = (Vector<LegalEntity>) ReferenceDataCache.selectWhere(searchText, BeanConstants.LEGALENTITY);

			counterpartyWindow.model.clear();
			if (!commonUTIL.isEmpty(data)) {
				LegalEntity firstRecord = data.get(0);
				for (int i = 0; i < data.size(); i++) {
					counterpartyWindow.model.addRow((LegalEntity) data.get(i));
				}
				counterpartyWindow.propertyTable.setPropertiesValues(firstRecord);
				setLegalEntity(firstRecord);
			}
		} else {
			Vector<LegalEntity> data = (Vector<LegalEntity>) ReferenceDataCache.selectALLData(BeanConstants.LEGALENTITY);
			if (!commonUTIL.isEmpty(data)) {
				counterpartyWindow.model.clear();
				LegalEntity firstRecord = data.get(0);
				for (int i = 0; i < data.size(); i++) {
					counterpartyWindow.model.addRow((LegalEntity) data.get(i));
				}
				counterpartyWindow.propertyTable.setPropertiesValues(firstRecord);
				setLegalEntity(firstRecord);
			}
		}
	}

	public void loadData(int id) {
		newButtonAction();
		Vector<LegalEntity> data =null ;//  ReferenceDataCache.getC(id);
		counterpartyWindow.model.clear();
		if (!commonUTIL.isEmpty(data)) {
			LegalEntity firstRecord = data.get(0);
			for (int i = 0; i < data.size(); i++) {
				counterpartyWindow.model.addRow((LegalEntity) data.get(i));
			}
			counterpartyWindow.propertyTable.setPropertiesValues(firstRecord);
			setLegalEntity(firstRecord);
		}
	}

	private void rightSideCenterTableAction() {
		if (counterpartyWindow.rightSideCenterTable.getSelectedRow() != -1)
			counterpartyWindow.propertyTable.setPropertiesValues(
					counterpartyWindow.model.getRow(counterpartyWindow.rightSideCenterTable.getSelectedRow()));
		counterpartyWindow.setLegalEntity(
				counterpartyWindow.model.getRow(counterpartyWindow.rightSideCenterTable.getSelectedRow()));
		setLegalEntity(counterpartyWindow.model.getRow(counterpartyWindow.rightSideCenterTable.getSelectedRow()));
	}

	private void searchTextAction() {
		loadButtonAction();
	}

	// check Null pointerException.
	private void deleteButtonAction() {
		try {
			 if(ReferenceDataCache.deleteSQL(counterparty, BeanConstants.LEGALENTITY)) {
			 if( counterpartyWindow.rightSideCenterTable.getSelectedRow() !=
			 -1) {
			 counterpartyWindow.model.delRow(counterpartyWindow.rightSideCenterTable.getSelectedRow());
			 }
			 setLegalEntity(null);
			 counterpartyWindow.propertyTable.clearPropertyValues();
			 }
		} catch (Exception e) {
			commonUTIL.displayError(LegalEntityConstants.WINDOW_NAME + "Util", "deleteButtonAction", e);
		}
	}

	@Override
	public void windowstartUpData() {
		Vector<String> CountryData = ReferenceDataCache.getStarupData("Country");
		counterpartyWindow.CountryData = commonUTIL.convertStartupVectortoStringArray(CountryData);

	}

	@Override
	public void clearALL() {
	}
}