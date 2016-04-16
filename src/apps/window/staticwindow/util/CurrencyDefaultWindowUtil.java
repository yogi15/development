package apps.window.staticwindow.util; 


 import java.util.Vector;
import apps.window.util.tableModelUtil.TableUtils;import util.cacheUtil.ReferenceDataCache;
import util.commonUTIL;
import apps.window.staticwindow.BasePanel;
import apps.window.staticwindow.CurrencyDefaultWindow;
import beans.CurrencyDefault; 
import beans.WindowSheet;
import com.jidesoft.grid.Property;
import constants.CommonConstants;
import constants.CurrencyDefaultConstants;
import constants.BeanConstants;
public class CurrencyDefaultWindowUtil extends BaseWindowUtil {
 CurrencyDefaultWindow currencydefaultWindow= null;
CurrencyDefault currencydefault = null;
 String currencydefaultName;
/**
 * @return the windowName
 */
public String getWindowName() {
	return CurrencyDefaultConstants.WINDOW_NAME;
}
	/**
 * @param windowName the windowName to set
 */
public void setWindowName(String currencydefaultName) {
	this.currencydefaultName = currencydefaultName;
}
/**
 * @return the currencydefault
 */
	public CurrencyDefault getCurrencyDefault() {
	return currencydefault;
	}
		/**
 * @param currencydefault the currencydefault to set
	 */
public void setCurrencyDefault(CurrencyDefault  currencydefault) {
	  this.currencydefault = currencydefault;
}@Override
public boolean validate( ) {
		// TODO Auto-generated method stub
		boolean flag = false;
			return validate(getCurrencyDefault(),CurrencyDefaultConstants.WINDOW_NAME);
			}
	@Override
					public Vector<String> fillData(String action) {
				// TODO Auto-generated method stub
			return null;
}
	@Override
public void actionMapper(String action) {
	// TODO Auto-generated method stub
Property prop = currencydefaultWindow.propertyTable.getPropertyTable().getSelectedProperty(); 
if(action.equalsIgnoreCase(CommonConstants.SAVEASNEWBUTTON)) {
saveAsNewButtonAction();
}
if(action.equalsIgnoreCase(CommonConstants.NEWBUTTON)) {
newButtonAction();
	}
		if(action.equalsIgnoreCase(CommonConstants.LOADBUTTON)) {
loadButtonAction();
	}
	if(action.equalsIgnoreCase(CurrencyDefaultConstants.SEARCHTEXTBOX)) {
searchTextAction();
	}
if(action.equalsIgnoreCase(CommonConstants.RIGHTSIDECENTERTABLE)) {
	rightSideCenterTableAction();
}
	if(action.equalsIgnoreCase(CommonConstants.DELETEBUTTON)) {
	deleteButtonAction();
}
if(action.equalsIgnoreCase(CommonConstants.SAVEBUTTON)) {
saveButtonAction();
}
if (action.equalsIgnoreCase(CommonConstants.HIERARACHICALTABLE)) {
hierarachicalTableAction();
 }
 if (action.equalsIgnoreCase(CurrencyDefaultConstants.LOADALLCURRENCYDEFAULT)) {
 	loadButtonAction();
 }
	}				
@Override
public void setWindow(BasePanel windowName) {
	// TODO Auto-generated method stub
	currencydefaultWindow = (CurrencyDefaultWindow)windowName;
setCurrencyDefault(currencydefaultWindow.getCurrencyDefault()); 
}
private void hierarachicalTableAction() {
	if (currencydefaultWindow.hierarchicalTable.getSelectedRow() != -1) {
			currencydefaultWindow.propertyTable.setPropertiesValues(currencydefaultWindow.model.getRow(currencydefaultWindow.hierarchicalTable.getSelectedRow()));
		currencydefaultWindow.setCurrencyDefault(currencydefaultWindow.model.getRow(currencydefaultWindow.hierarchicalTable.getSelectedRow()));
		setCurrencyDefault(currencydefaultWindow.model.getRow(currencydefaultWindow.hierarchicalTable.getSelectedRow()));
		}
	}
private void saveButtonAction() {
currencydefaultWindow.propertyTable
.setfillValues(currencydefault);
setCurrencyDefault((CurrencyDefault) currencydefaultWindow.propertyTable.getBean());
//if(validate( )) 
// if(ReferenceDataCache.updateCurrencyDefault(getCurrencyDefault())) {
//if(currencydefaultWindow.rightSideCenterTable.getSelectedRow() != -1) {
	 // int i=  TableUtils.getSelectedRowIndex( currencydefaultWindow.rightSideCenterTable);
	  //currencydefaultWindow.model.udpateValueAt(getCurrencyDefault(), i, 0);
 //}
 //}
}		
private void saveAsNewButtonAction() { 
CurrencyDefault currencydefault = new CurrencyDefault();
	currencydefaultWindow.propertyTable.setfillValues(currencydefault);
	 setCurrencyDefault(currencydefault);
//if(validate( )){
// currencydefault = ReferenceDataCache.saveCurrencyDefault(currencydefault); 
 // currencydefaultWindow.model.addRow(getCurrencyDefault());
 // setCurrencyDefault(currencydefault);
  //}
}
private void newButtonAction() {
currencydefaultWindow.propertyTable.clearPropertyValues();
currencydefaultWindow.model.clear();
setCurrencyDefault(null);
	}
private void loadButtonAction() {
	newButtonAction();
String searchText =   currencydefaultWindow.currencydefaultSearchTextField.getText();
 if(!commonUTIL.isEmpty(searchText)) {
Vector<CurrencyDefault> data = null;//ReferenceDataCache.selectCurrencyDefaults(searchText);
currencydefaultWindow.model.clear();
if(!commonUTIL.isEmpty(data)) {
CurrencyDefault firstRecord = data.get(0);
for(int i=0;i<data.size();i++) {
 currencydefaultWindow.model.addRow((CurrencyDefault)data.get(i));
				}
				currencydefaultWindow.propertyTable.setPropertiesValues(firstRecord);
				 setCurrencyDefault(firstRecord);
		} 
}else {
		Vector<CurrencyDefault> data = (Vector<CurrencyDefault>) ReferenceDataCache.selectALLData(BeanConstants.CURRENCYDEFAULT);
		if (!commonUTIL.isEmpty(data)) {
currencydefaultWindow.model.clear();
			CurrencyDefault firstRecord = data.get(0);
			for (int i = 0; i < data.size(); i++) {
					currencydefaultWindow.model.addRow((CurrencyDefault) data.get(i));
				}
				currencydefaultWindow.propertyTable.setPropertiesValues(firstRecord);
		setCurrencyDefault(firstRecord);
			}
	 }
}
public void loadData(int id) {
		newButtonAction();
		Vector<CurrencyDefault> data = null;// ReferenceDataCache.getCurrencyDefault(id);
			currencydefaultWindow.model.clear();
			if (!commonUTIL.isEmpty(data)) {
				CurrencyDefault firstRecord = data.get(0);
				for (int i = 0; i < data.size(); i++) {
			currencydefaultWindow.model.addRow((CurrencyDefault) data.get(i));
				}
				currencydefaultWindow.propertyTable.setPropertiesValues(firstRecord);
				setCurrencyDefault(firstRecord);
	}
	}
 private void rightSideCenterTableAction() {
	if(currencydefaultWindow.rightSideCenterTable.getSelectedRow() != -1) 
		 currencydefaultWindow.propertyTable.setPropertiesValues( currencydefaultWindow.model.getRow(currencydefaultWindow.rightSideCenterTable.getSelectedRow()));
	currencydefaultWindow.setCurrencyDefault(currencydefaultWindow.model.getRow(currencydefaultWindow.rightSideCenterTable .getSelectedRow()));
	setCurrencyDefault(currencydefaultWindow.model .getRow(currencydefaultWindow.rightSideCenterTable .getSelectedRow()));
}
private void searchTextAction() {
	loadButtonAction();
}
// check Null pointerException.
private void deleteButtonAction() {
	try {
//if(ReferenceDataCache.deleteCurrencyDefault(currencydefault)) {
//if( currencydefaultWindow.rightSideCenterTable.getSelectedRow() != -1) {
	//currencydefaultWindow.model.delRow(currencydefaultWindow.rightSideCenterTable.getSelectedRow()); 
	//}
//setCurrencyDefault(null);
 //currencydefaultWindow.propertyTable.clearPropertyValues();
//}	
} catch(Exception e) {
commonUTIL.displayError(CurrencyDefaultConstants.WINDOW_NAME+"Util" , "deleteButtonAction", e);
}
}
@Override
public void windowstartUpData() {
 

}@Override
public void clearALL() {
}
}