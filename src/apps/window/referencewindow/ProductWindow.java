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
import apps.window.staticwindow.util.ProductWindowUtil;
import apps.window.util.propertyTable.ProductPropertyTable;
import apps.window.util.tableModelUtil.ProductTableModelUtil;
import beans.Product ;
import constants.CommonConstants;
import constants.ProductConstants;


public class ProductWindow    extends BasePanel {
   ActionMap actions = null;
   public String searchData [];
  private static final long serialVersionUID = 1L; 
   public ProductTableModelUtil model =null;
     Product product = new Product(); /// used as a bean 
  // used for Validation and save,update and delete and get Data from DB.
ProductWindowUtil windowUtil = null;
Vector<Product> rightPanelJtableProductdata = new Vector<Product>(); // used maintain data in rightPanel in center area.
/**
* @return the data
/
public Vector<Product> getData() {
return rightPanelJtableProductdata;
}
/**
 * @param data the data to set
 */
public void setData(Vector<Product> data) {
//this.data = data;
rightPanelJtableProductdata = data;
}
// leftTopPanel Data
protected JLabel productLabelName = new JLabel("Product Name");
public final	JTextField productSearchTextField = new JTextField("ProductTextField",15); // search textfield in leftTopPanel Data
// rightTopPanel Data
 private JLabel productName = new JLabel("Product Name");
protected JButton productDetails = new JButton("Load ProductDetails");
// leftSide PropertyTable 
public ProductPropertyTable  propertyTable = null; 
// Constructor
public ProductWindow() {
try {
initComponents();
} catch (CosmosException e) {
// TODO Auto-generated catch block
commonUTIL.displayError(ProductConstants.WINDOW_NAME,"Constructor", e);
}
}
private void initComponents() throws CosmosException {
/// init() data required while loading this window.
init();
setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
setLayout(new BorderLayout()); 
// add  model to table 
model = new ProductTableModelUtil(rightPanelJtableProductdata); 
	setCornerForScrollPane(model.getCol()); 
	setQuickSearchOnTable(model,model.getCol().length);createSingleSplitPaneLayout(CommonConstants.SPLITWINDOWLOCATION);	
setSize(CommonConstants.WINDOWWIDTH , CommonConstants.WINDOWHIGHT); 
}
/**
* @return the Product
 */
public Product getProduct () {
return product;
}
/**
* @param Product the Product to set
 */
public void setProduct (Product product) {
this.product = product;
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
windowUtil = new ProductWindowUtil(); 
windowUtil.setWindow(this);
this. validationActionUtil = windowUtil;
}
// add listerener to panel Jcompenonts. 
@Override
public void setWindowActionListener()   {
try {
setEventListener(productDetails);
setEventListener(productSearchTextField);
} catch (CosmosException e) {
e.printStackTrace();
}
}
 // add lefttop panel componenonts
@Override
public void addTopLeftSidePanelComponents() {
 productSearchTextField.setName(ProductConstants.SEARCHTEXTBOX); 
leftTopbuttonsPanel.add(productLabelName);
setSearchOnLeftTopPanel(productSearchTextField,searchData);	 
leftTopbuttonsPanel.add(productSearchTextField);  
}
// add righttop panel componenonts
@Override
public void addTopRigthSidePanelComponents() {
rightTopbuttonsPanel.add(productName);
 rightTopbuttonsPanel.add(productDetails);
}
 // create property proprities. 
@Override
public void createPropertyPaneTable() {
propertyTable = new ProductPropertyTable(ProductConstants.WINDOW_NAME,product);
 setLeftSidePropertyPanePanel(propertyTable.getPropertyTable(generateProperty(ProductConstants.WINDOW_NAME) ));
}
@Override
public void addCenterRightSidePanelComponents() {
// TODO Auto-generated method stub
centerRightSidePanel.add(scrollPane, BorderLayout.CENTER);
}
@Override
public JPanel createChildPanel(int parentID) {
return null;
}
@Override
public JPanel createChildPanel(String id) {
	return null;
}
	@Override
public String getWindowName() {
return ProductConstants.WINDOW_NAME;
	}
}
