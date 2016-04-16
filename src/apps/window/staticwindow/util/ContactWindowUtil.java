package apps.window.staticwindow.util; 


 import java.util.Vector;
import apps.window.util.tableModelUtil.TableUtils;import util.cacheUtil.ReferenceDataCache;
import util.commonUTIL;
import apps.window.staticwindow.BasePanel;
import apps.window.referencewindow.ContactWindow;
import beans.LeContacts; 
import beans.WindowSheet;
import com.jidesoft.grid.Property;
import constants.CommonConstants;
import constants.LeContactsConstants;
import constants.BeanConstants;
public class ContactWindowUtil extends BaseWindowUtil {
 ContactWindow contactWindow= null;
LeContacts contact = null;
 String contactName;
/**
 * @return the windowName
 */
public String getWindowName() {
	return LeContactsConstants.WINDOW_NAME;
}
	/**
 * @param windowName the windowName to set
 */
public void setWindowName(String contactName) {
	this.contactName = contactName;
}
/**
 * @return the contact
 */
	public LeContacts getLeContacts() {
	return contact;
	}
		/**
 * @param contact the contact to set
	 */
public void setLeContacts(LeContacts  contact) {
	  this.contact = contact;
}@Override
public boolean validate( ) {
		// TODO Auto-generated method stub
		boolean flag = false;
			return validate(getLeContacts(),LeContactsConstants.WINDOW_NAME);
			}
	@Override
					public Vector<String> fillData(String action) {
				// TODO Auto-generated method stub
			return null;
}
	@Override
public void actionMapper(String action) {
	// TODO Auto-generated method stub
Property prop = contactWindow.propertyTable.getPropertyTable().getSelectedProperty(); 
if(action.equalsIgnoreCase(CommonConstants.SAVEASNEWBUTTON)) {
saveAsNewButtonAction();
}
if(action.equalsIgnoreCase(CommonConstants.NEWBUTTON)) {
newButtonAction();
	}
		if(action.equalsIgnoreCase(CommonConstants.LOADBUTTON)) {
loadButtonAction();
	}
	if(action.equalsIgnoreCase(LeContactsConstants.SEARCHTEXTBOX)) {
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
 if (action.equalsIgnoreCase(LeContactsConstants.LOADALLLECONTACTS)) {
 	loadButtonAction();
 }
	}				
@Override
public void setWindow(BasePanel windowName) {
	// TODO Auto-generated method stub
	contactWindow = (ContactWindow)windowName;
setLeContacts(contactWindow.getLeContacts()); 
}
private void hierarachicalTableAction() {
	if (contactWindow.hierarchicalTable.getSelectedRow() != -1) {
			contactWindow.propertyTable.setPropertiesValues(contactWindow.model.getRow(contactWindow.hierarchicalTable.getSelectedRow()));
		contactWindow.setLeContacts(contactWindow.model.getRow(contactWindow.hierarchicalTable.getSelectedRow()));
		setLeContacts(contactWindow.model.getRow(contactWindow.hierarchicalTable.getSelectedRow()));
		}
	}
private void saveButtonAction() {
contactWindow.propertyTable
.setfillValues(contact);
setLeContacts((LeContacts) contactWindow.propertyTable.getBean());
//if(validate( )) 
// if(ReferenceDataCache.updateContact(getLeContacts())) {
//if(contactWindow.rightSideCenterTable.getSelectedRow() != -1) {
	 // int i=  TableUtils.getSelectedRowIndex( contactWindow.rightSideCenterTable);
	  //contactWindow.model.udpateValueAt(getLeContacts(), i, 0);
 //}
 //}
}		
private void saveAsNewButtonAction() { 
LeContacts contact = new LeContacts();
	contactWindow.propertyTable.setfillValues(contact);
	 setLeContacts(contact);
//if(validate( )){
// contact = ReferenceDataCache.saveLeContacts(contact); 
 // contactWindow.model.addRow(getLeContacts());
 // setLeContacts(contact);
  //}
}
private void newButtonAction() {
contactWindow.propertyTable.clearPropertyValues();
contactWindow.model.clear();
setLeContacts(null);
	}
private void loadButtonAction() {
	newButtonAction();
String searchText =   contactWindow.contactSearchTextField.getText();
 if(!commonUTIL.isEmpty(searchText)) {
Vector<LeContacts> data = null;//ReferenceDataCache.selectLeContactss(searchText);
contactWindow.model.clear();
if(!commonUTIL.isEmpty(data)) {
LeContacts firstRecord = data.get(0);
for(int i=0;i<data.size();i++) {
 contactWindow.model.addRow((LeContacts)data.get(i));
				}
				contactWindow.propertyTable.setPropertiesValues(firstRecord);
				 setLeContacts(firstRecord);
		} 
}else {
		Vector<LeContacts> data = (Vector<LeContacts>) ReferenceDataCache.selectALLData(BeanConstants.LECONTACTS);
		if (!commonUTIL.isEmpty(data)) {
contactWindow.model.clear();
			LeContacts firstRecord = data.get(0);
			for (int i = 0; i < data.size(); i++) {
					contactWindow.model.addRow((LeContacts) data.get(i));
				}
				contactWindow.propertyTable.setPropertiesValues(firstRecord);
		setLeContacts(firstRecord);
			}
	 }
}
public void loadData(int id) {
		newButtonAction();
		Vector<LeContacts> data = null;// ReferenceDataCache.getLeContacts(id);
			contactWindow.model.clear();
			if (!commonUTIL.isEmpty(data)) {
				LeContacts firstRecord = data.get(0);
				for (int i = 0; i < data.size(); i++) {
			contactWindow.model.addRow((LeContacts) data.get(i));
				}
				contactWindow.propertyTable.setPropertiesValues(firstRecord);
				setLeContacts(firstRecord);
	}
	}
 private void rightSideCenterTableAction() {
	if(contactWindow.rightSideCenterTable.getSelectedRow() != -1) 
		 contactWindow.propertyTable.setPropertiesValues( contactWindow.model.getRow(contactWindow.rightSideCenterTable.getSelectedRow()));
	contactWindow.setLeContacts(contactWindow.model.getRow(contactWindow.rightSideCenterTable .getSelectedRow()));
	setLeContacts(contactWindow.model .getRow(contactWindow.rightSideCenterTable .getSelectedRow()));
}
private void searchTextAction() {
	loadButtonAction();
}
// check Null pointerException.
private void deleteButtonAction() {
	try {
//if(ReferenceDataCache.deleteContact(contact)) {
//if( contactWindow.rightSideCenterTable.getSelectedRow() != -1) {
	//contactWindow.model.delRow(contactWindow.rightSideCenterTable.getSelectedRow()); 
	//}
//setLeContacts(null);
 //contactWindow.propertyTable.clearPropertyValues();
//}	
} catch(Exception e) {
commonUTIL.displayError(LeContactsConstants.WINDOW_NAME+"Util" , "deleteButtonAction", e);
}
}
@Override
public void windowstartUpData() {
 

}@Override
public void clearALL() {
}
}