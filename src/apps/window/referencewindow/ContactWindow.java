package apps.window.referencewindow;  


  import java.awt.BorderLayout;
import java.awt.Component;
import apps.window.staticwindow.BasePanel;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Vector;
import javax.swing.JScrollPane;
import com.jidesoft.grid.TextFieldCellEditor;
import com.jidesoft.hints.ListDataIntelliHints;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import util.CosmosException;
import util.commonUTIL;
import apps.window.staticwindow.util.ContactWindowUtil;
import apps.window.util.propertyTable.ContactPropertyTable;
import apps.window.util.tableModelUtil.ContactTableModelUtil;
import beans.LeContacts ;
import constants.CommonConstants;
import constants.LeContactsConstants;


public class ContactWindow    extends BasePanel {
   ActionMap actions = null;
   public String searchData [];
  private static final long serialVersionUID = 1L; 
   public ContactTableModelUtil model =null;
     LeContacts contact = new LeContacts(); /// used as a bean 
  // used for Validation and save,update and delete and get Data from DB.
ContactWindowUtil windowUtil = null;
Vector<LeContacts> rightPanelJtableContactdata = new Vector<LeContacts>(); // used maintain data in rightPanel in center area.
/**
* @return the data
/
public Vector<LeContacts> getData() {
return rightPanelJtableContactdata;
}
/**
 * @param data the data to set
 */
public void setData(Vector<LeContacts> data) {
//this.data = data;
rightPanelJtableContactdata = data;
}
// leftTopPanel Data
protected JLabel contactLabelName = new JLabel("Contact Name");
public final	JTextField contactSearchTextField = new JTextField("LeContactsTextField",15); // search textfield in leftTopPanel Data
// rightTopPanel Data
 private JLabel contactName = new JLabel("Contact Name");
protected JButton contactDetails = new JButton("Load LeContactsDetails");
// leftSide PropertyTable 
public ContactPropertyTable  propertyTable = null; 
// Constructor
public ContactWindow() {
try {
initComponents();
} catch (CosmosException e) {
// TODO Auto-generated catch block
commonUTIL.displayError(LeContactsConstants.WINDOW_NAME,"Constructor", e);
}
}
private void initComponents() throws CosmosException {
/// init() data required while loading this window.
init();
setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
setLayout(new BorderLayout()); 
// add  model to table 
model = new ContactTableModelUtil(rightPanelJtableContactdata); 
createSingleSplitPaneLayout();
setSize(CommonConstants.WINDOWWIDTH , CommonConstants.WINDOWHIGHT); 
}
/**
* @return the LeContacts
 */
public LeContacts getLeContacts () {
return contact;
}
/**
* @param LeContacts the LeContacts to set
 */
public void setLeContacts (LeContacts contact) {
this.contact = contact;
}
@Override
public ActionMap getHotKeysActionMapper() {
ActionMap action = new ActionMap();
return action;
}
 @Override
public JPanel getHotKeysPanel() {
 return  rightTopbuttonsPanel;
}
@Override
public ArrayList<Component> getFocusOrderList() {
ArrayList<Component> comps = new ArrayList<Component>();
comps.add(loadButton);
return comps;
}
 // add Window Validation util for search,save,new,saveAsNew,close and other custom components. 
 @Override
public void setWindowValidationUtil( ) {
windowUtil = new ContactWindowUtil(); 
windowUtil.setWindow(this);
this. validationActionUtil = windowUtil;
}
// add listerener to panel Jcompenonts. 
@Override
public void setWindowActionListener()   {
try {
setEventListener(contactDetails);
setEventListener(contactSearchTextField);
} catch (CosmosException e) {
e.printStackTrace();
}
}
 // add lefttop panel componenonts
@Override
public void addTopLeftSidePanelComponents() {
 contactSearchTextField.setName(LeContactsConstants.SEARCHTEXTBOX); 
leftTopbuttonsPanel.add(contactLabelName);
setSearchOnLeftTopPanel(contactSearchTextField,searchData);	 
leftTopbuttonsPanel.add(contactSearchTextField);  
}
// add righttop panel componenonts
@Override
public void addTopRigthSidePanelComponents() {
rightTopbuttonsPanel.add(contactName);
 rightTopbuttonsPanel.add(contactDetails);
}
 // create property proprities. 
@Override
public void createPropertyPaneTable() {
propertyTable = new ContactPropertyTable(LeContactsConstants.WINDOW_NAME,contact);
 setLeftSidePropertyPanePanel(propertyTable.getPropertyTable(generateProperty(LeContactsConstants.WINDOW_NAME) ));
}
@Override
public void addCenterRightSidePanelComponents() {
// TODO Auto-generated method stub
scrollPane.getViewport().add(hierarchicalTable);
centerRightSidePanel.add(scrollPane, BorderLayout.CENTER);
}
@Override
public JPanel createChildPanel(int parentID) {
JPanel panel = new JPanel();
panel.setLayout(new BorderLayout());
 windowUtil.loadData(parentID); // setting data in Model for all details record of parent id.
 rightSideCenterTable.setModel(model);  // rightSideCenterTable is used to show all details records of Parent id.
 panel.add(new JScrollPane(rightSideCenterTable), BorderLayout.CENTER);
return panel;
}
@Override
public JPanel createChildPanel(String id) {
	return null;
}
	@Override
public String getWindowName() {
return LeContactsConstants.WINDOW_NAME;
	}
}
