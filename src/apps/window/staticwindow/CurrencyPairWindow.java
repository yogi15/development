package apps.window.staticwindow;  


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
import apps.window.staticwindow.util.CurrencyPairWindowUtil;
import apps.window.util.propertyTable.CurrencyPairPropertyTable;
import apps.window.util.tableModelUtil.CurrencyPairTableModelUtil;
import beans.CurrencyPair ;
import constants.CommonConstants;
import constants.CurrencyPairConstants;


public class CurrencyPairWindow    extends BasePanel {
   ActionMap actions = null;
   public String searchData [];
  private static final long serialVersionUID = 1L; 
   public CurrencyPairTableModelUtil model =null;
     CurrencyPair currencypair = new CurrencyPair(); /// used as a bean 
  // used for Validation and save,update and delete and get Data from DB.
CurrencyPairWindowUtil windowUtil = null;
Vector<CurrencyPair> rightPanelJtableCurrencyPairdata = new Vector<CurrencyPair>(); // used maintain data in rightPanel in center area.
/**
* @return the data
/
public Vector<CurrencyPair> getData() {
return rightPanelJtableCurrencyPairdata;
}
/**
 * @param data the data to set
 */
public void setData(Vector<CurrencyPair> data) {
//this.data = data;
rightPanelJtableCurrencyPairdata = data;
}
// leftTopPanel Data
protected JLabel currencypairLabelName = new JLabel("CurrencyPair Name");
public final	JTextField currencypairSearchTextField = new JTextField("CurrencyPairTextField",15); // search textfield in leftTopPanel Data
// rightTopPanel Data
 private JLabel currencypairName = new JLabel("CurrencyPair Name");
protected JButton currencypairDetails = new JButton("Load CurrencyPairDetails");
// leftSide PropertyTable 
public CurrencyPairPropertyTable  propertyTable = null; 
// Constructor
public CurrencyPairWindow() {
try {
initComponents();
} catch (CosmosException e) {
// TODO Auto-generated catch block
commonUTIL.displayError(CurrencyPairConstants.WINDOW_NAME,"Constructor", e);
}
}
private void initComponents() throws CosmosException {
/// init() data required while loading this window.
init();
setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
setLayout(new BorderLayout()); 
// add  model to table 
model = new CurrencyPairTableModelUtil(rightPanelJtableCurrencyPairdata); 
createSingleSplitPaneLayout();
setSize(CommonConstants.WINDOWWIDTH , CommonConstants.WINDOWHIGHT); 
}
/**
* @return the CurrencyPair
 */
public CurrencyPair getCurrencyPair () {
return currencypair;
}
/**
* @param CurrencyPair the CurrencyPair to set
 */
public void setCurrencyPair (CurrencyPair currencypair) {
this.currencypair = currencypair;
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
windowUtil = new CurrencyPairWindowUtil(); 
windowUtil.setWindow(this);
this. validationActionUtil = windowUtil;
}
// add listerener to panel Jcompenonts. 
@Override
public void setWindowActionListener()   {
try {
setEventListener(currencypairDetails);
setEventListener(currencypairSearchTextField);
} catch (CosmosException e) {
e.printStackTrace();
}
}
 // add lefttop panel componenonts
@Override
public void addTopLeftSidePanelComponents() {
 currencypairSearchTextField.setName(CurrencyPairConstants.SEARCHTEXTBOX); 
leftTopbuttonsPanel.add(currencypairLabelName);
setSearchOnLeftTopPanel(currencypairSearchTextField,searchData);	 
leftTopbuttonsPanel.add(currencypairSearchTextField);  
}
// add righttop panel componenonts
@Override
public void addTopRigthSidePanelComponents() {
rightTopbuttonsPanel.add(currencypairName);
 rightTopbuttonsPanel.add(currencypairDetails);
}
 // create property proprities. 
@Override
public void createPropertyPaneTable() {
propertyTable = new CurrencyPairPropertyTable(CurrencyPairConstants.WINDOW_NAME,currencypair);
 setLeftSidePropertyPanePanel(propertyTable.getPropertyTable(generateProperty(CurrencyPairConstants.WINDOW_NAME) ));
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
return CurrencyPairConstants.WINDOW_NAME;
	}
}
