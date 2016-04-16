package apps.window.staticwindow.util; 


 import java.util.Vector;
import apps.window.util.tableModelUtil.TableUtils;import util.cacheUtil.ReferenceDataCache;
import util.commonUTIL;
import apps.window.staticwindow.BasePanel;
import apps.window.staticwindow.CurrencyPairWindow;
import beans.CurrencyPair; 
import beans.WindowSheet;
import com.jidesoft.grid.Property;
import constants.CommonConstants;
import constants.CurrencyPairConstants;
import constants.BeanConstants;
public class CurrencyPairWindowUtil extends BaseWindowUtil {
 CurrencyPairWindow currencypairWindow= null;
CurrencyPair currencypair = null;
 String currencypairName;
/**
 * @return the windowName
 */
public String getWindowName() {
	return CurrencyPairConstants.WINDOW_NAME;
}
	/**
 * @param windowName the windowName to set
 */
public void setWindowName(String currencypairName) {
	this.currencypairName = currencypairName;
}
/**
 * @return the currencypair
 */
	public CurrencyPair getCurrencyPair() {
	return currencypair;
	}
		/**
 * @param currencypair the currencypair to set
	 */
public void setCurrencyPair(CurrencyPair  currencypair) {
	  this.currencypair = currencypair;
}@Override
public boolean validate( ) {
		// TODO Auto-generated method stub
		boolean flag = false;
			return validate(getCurrencyPair(),CurrencyPairConstants.WINDOW_NAME);
			}
	@Override
					public Vector<String> fillData(String action) {
				// TODO Auto-generated method stub
			return null;
}
	@Override
public void actionMapper(String action) {
	// TODO Auto-generated method stub
Property prop = currencypairWindow.propertyTable.getPropertyTable().getSelectedProperty(); 
if(action.equalsIgnoreCase(CommonConstants.SAVEASNEWBUTTON)) {
saveAsNewButtonAction();
}
if(action.equalsIgnoreCase(CommonConstants.NEWBUTTON)) {
newButtonAction();
	}
		if(action.equalsIgnoreCase(CommonConstants.LOADBUTTON)) {
loadButtonAction();
	}
	if(action.equalsIgnoreCase(CurrencyPairConstants.SEARCHTEXTBOX)) {
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
 if (action.equalsIgnoreCase(CurrencyPairConstants.LOADALLCURRENCYPAIR)) {
 	loadButtonAction();
 }
	}				
@Override
public void setWindow(BasePanel windowName) {
	// TODO Auto-generated method stub
	currencypairWindow = (CurrencyPairWindow)windowName;
setCurrencyPair(currencypairWindow.getCurrencyPair()); 
}
private void hierarachicalTableAction() {
	if (currencypairWindow.hierarchicalTable.getSelectedRow() != -1) {
			currencypairWindow.propertyTable.setPropertiesValues(currencypairWindow.model.getRow(currencypairWindow.hierarchicalTable.getSelectedRow()));
		currencypairWindow.setCurrencyPair(currencypairWindow.model.getRow(currencypairWindow.hierarchicalTable.getSelectedRow()));
		setCurrencyPair(currencypairWindow.model.getRow(currencypairWindow.hierarchicalTable.getSelectedRow()));
		}
	}
private void saveButtonAction() {
currencypairWindow.propertyTable
.setfillValues(currencypair);
setCurrencyPair((CurrencyPair) currencypairWindow.propertyTable.getBean());
//if(validate( )) 
// if(ReferenceDataCache.updateCurrencyPair(getCurrencyPair())) {
//if(currencypairWindow.rightSideCenterTable.getSelectedRow() != -1) {
	 // int i=  TableUtils.getSelectedRowIndex( currencypairWindow.rightSideCenterTable);
	  //currencypairWindow.model.udpateValueAt(getCurrencyPair(), i, 0);
 //}
 //}
}		
private void saveAsNewButtonAction() { 
CurrencyPair currencypair = new CurrencyPair();
	currencypairWindow.propertyTable.setfillValues(currencypair);
	 setCurrencyPair(currencypair);
//if(validate( )){
// currencypair = ReferenceDataCache.saveCurrencyPair(currencypair); 
 // currencypairWindow.model.addRow(getCurrencyPair());
 // setCurrencyPair(currencypair);
  //}
}
private void newButtonAction() {
currencypairWindow.propertyTable.clearPropertyValues();
currencypairWindow.model.clear();
setCurrencyPair(null);
	}
private void loadButtonAction() {
	newButtonAction();
String searchText =   currencypairWindow.currencypairSearchTextField.getText();
 if(!commonUTIL.isEmpty(searchText)) {
Vector<CurrencyPair> data = null;//ReferenceDataCache.selectCurrencyPairs(searchText);
currencypairWindow.model.clear();
if(!commonUTIL.isEmpty(data)) {
CurrencyPair firstRecord = data.get(0);
for(int i=0;i<data.size();i++) {
 currencypairWindow.model.addRow((CurrencyPair)data.get(i));
				}
				currencypairWindow.propertyTable.setPropertiesValues(firstRecord);
				 setCurrencyPair(firstRecord);
		} 
}else {
		Vector<CurrencyPair> data = (Vector<CurrencyPair>) ReferenceDataCache.selectALLData(BeanConstants.CURRENCYPAIR);
		if (!commonUTIL.isEmpty(data)) {
currencypairWindow.model.clear();
			CurrencyPair firstRecord = data.get(0);
			for (int i = 0; i < data.size(); i++) {
					currencypairWindow.model.addRow((CurrencyPair) data.get(i));
				}
				currencypairWindow.propertyTable.setPropertiesValues(firstRecord);
		setCurrencyPair(firstRecord);
			}
	 }
}
public void loadData(int id) {
		newButtonAction();
		Vector<CurrencyPair> data = null;// ReferenceDataCache.getCurrencyPair(id);
			currencypairWindow.model.clear();
			if (!commonUTIL.isEmpty(data)) {
				CurrencyPair firstRecord = data.get(0);
				for (int i = 0; i < data.size(); i++) {
			currencypairWindow.model.addRow((CurrencyPair) data.get(i));
				}
				currencypairWindow.propertyTable.setPropertiesValues(firstRecord);
				setCurrencyPair(firstRecord);
	}
	}
 private void rightSideCenterTableAction() {
	if(currencypairWindow.rightSideCenterTable.getSelectedRow() != -1) 
		 currencypairWindow.propertyTable.setPropertiesValues( currencypairWindow.model.getRow(currencypairWindow.rightSideCenterTable.getSelectedRow()));
	currencypairWindow.setCurrencyPair(currencypairWindow.model.getRow(currencypairWindow.rightSideCenterTable .getSelectedRow()));
	setCurrencyPair(currencypairWindow.model .getRow(currencypairWindow.rightSideCenterTable .getSelectedRow()));
}
private void searchTextAction() {
	loadButtonAction();
}
// check Null pointerException.
private void deleteButtonAction() {
	try {
//if(ReferenceDataCache.deleteCurrencyPair(currencypair)) {
//if( currencypairWindow.rightSideCenterTable.getSelectedRow() != -1) {
	//currencypairWindow.model.delRow(currencypairWindow.rightSideCenterTable.getSelectedRow()); 
	//}
//setCurrencyPair(null);
 //currencypairWindow.propertyTable.clearPropertyValues();
//}	
} catch(Exception e) {
commonUTIL.displayError(CurrencyPairConstants.WINDOW_NAME+"Util" , "deleteButtonAction", e);
}
}
@Override
public void windowstartUpData() {
 

}@Override
public void clearALL() {
}
}