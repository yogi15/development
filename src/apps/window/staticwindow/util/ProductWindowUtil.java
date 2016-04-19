package apps.window.staticwindow.util;

import java.util.Vector;
import apps.window.util.tableModelUtil.TableUtils;
import util.cacheUtil.ReferenceDataCache;
import util.commonUTIL;
import apps.window.staticwindow.BasePanel;
import apps.window.referencewindow.ProductWindow;
import beans.Product;
import beans.WindowSheet;
import com.jidesoft.grid.Property;
import constants.CommonConstants;
import constants.ProductConstants;
import constants.BeanConstants;

public class ProductWindowUtil extends BaseWindowUtil {
	ProductWindow productWindow = null;
	Product product = null;
	String productName;

	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return ProductConstants.WINDOW_NAME;
	}

	/**
	 * @param windowName
	 *            the windowName to set
	 */
	public void setWindowName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		boolean flag = false;
		return validate(getProduct(), ProductConstants.WINDOW_NAME);
	}

	@Override
	public Vector<String> fillData(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionMapper(String action) {
		// TODO Auto-generated method stub
		Property prop = productWindow.propertyTable.getPropertyTable().getSelectedProperty();
		if (action.equalsIgnoreCase(CommonConstants.SAVEASNEWBUTTON)) {
			saveAsNewButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.NEWBUTTON)) {
			newButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.LOADBUTTON)) {
			loadButtonAction();
		}
		if (action.equalsIgnoreCase(ProductConstants.SEARCHTEXTBOX)) {
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
		if (action.equalsIgnoreCase(ProductConstants.LOADALLPRODUCT)) {
			loadButtonAction();
		}
	}

	@Override
	public void setWindow(BasePanel windowName) {
		// TODO Auto-generated method stub
		productWindow = (ProductWindow) windowName;
		setProduct(productWindow.getProduct());
	}

	private void hierarachicalTableAction() {
		if (productWindow.hierarchicalTable.getSelectedRow() != -1) {
			productWindow.propertyTable
					.setPropertiesValues(productWindow.model.getRow(productWindow.hierarchicalTable.getSelectedRow()));
			productWindow.setProduct(productWindow.model.getRow(productWindow.hierarchicalTable.getSelectedRow()));
			setProduct(productWindow.model.getRow(productWindow.hierarchicalTable.getSelectedRow()));
		}
	}

	private void saveButtonAction() {
		productWindow.propertyTable.setfillValues(product);
		setProduct((Product) productWindow.propertyTable.getBean());
		// if(validate( ))
		// if(ReferenceDataCache.updateProduct(getProduct())) {
		// if(productWindow.rightSideCenterTable.getSelectedRow() != -1) {
		// int i= TableUtils.getSelectedRowIndex(
		// productWindow.rightSideCenterTable);
		// productWindow.model.udpateValueAt(getProduct(), i, 0);
		// }
		// }
	}

	private void saveAsNewButtonAction() {
		Product product = new Product();
		productWindow.propertyTable.setfillValues(product);
		setProduct(product);
		// if(validate( )){
		// product = ReferenceDataCache.saveProduct(product);
		// productWindow.model.addRow(getProduct());
		// setProduct(product);
		// }
	}

	private void newButtonAction() {
		productWindow.propertyTable.clearPropertyValues();
		productWindow.model.clear();
		setProduct(null);
	}

	private void loadButtonAction() {
		newButtonAction();
		String searchText = productWindow.productSearchTextField.getText();
		if (!commonUTIL.isEmpty(searchText)) {
			Vector<Product> data = null;// ReferenceDataCache.selectProducts(searchText);
			productWindow.model.clear();
			if (!commonUTIL.isEmpty(data)) {
				Product firstRecord = data.get(0);
				for (int i = 0; i < data.size(); i++) {
					productWindow.model.addRow((Product) data.get(i));
				}
				productWindow.propertyTable.setPropertiesValues(firstRecord);
				setProduct(firstRecord);
			}
		} else {
			Vector<Product> data = (Vector<Product>) ReferenceDataCache.selectALLData(BeanConstants.PRODUCT);
			if (!commonUTIL.isEmpty(data)) {
				productWindow.model.clear();
				Product firstRecord = data.get(0);
				for (int i = 0; i < data.size(); i++) {
					productWindow.model.addRow((Product) data.get(i));
				}
				productWindow.propertyTable.setPropertiesValues(firstRecord);
				setProduct(firstRecord);
			}
		}
	}

	public void loadData(int id) {
		newButtonAction();
		Vector<Product> data = null;// ReferenceDataCache.getProduct(id);
		productWindow.model.clear();
		if (!commonUTIL.isEmpty(data)) {
			Product firstRecord = data.get(0);
			for (int i = 0; i < data.size(); i++) {
				productWindow.model.addRow((Product) data.get(i));
			}
			productWindow.propertyTable.setPropertiesValues(firstRecord);
			setProduct(firstRecord);
		}
	}

	private void rightSideCenterTableAction() {
		if (productWindow.rightSideCenterTable.getSelectedRow() != -1)
			productWindow.propertyTable.setPropertiesValues(
					productWindow.model.getRow(productWindow.rightSideCenterTable.getSelectedRow()));
		productWindow.setProduct(productWindow.model.getRow(productWindow.rightSideCenterTable.getSelectedRow()));
		setProduct(productWindow.model.getRow(productWindow.rightSideCenterTable.getSelectedRow()));
	}

	private void searchTextAction() {
		loadButtonAction();
	}

	// check Null pointerException.
	private void deleteButtonAction() {
		try {
			// if(ReferenceDataCache.deleteProduct(product)) {
			// if( productWindow.rightSideCenterTable.getSelectedRow() != -1) {
			// productWindow.model.delRow(productWindow.rightSideCenterTable.getSelectedRow());
			// }
			// setProduct(null);
			// productWindow.propertyTable.clearPropertyValues();
			// }
		} catch (Exception e) {
			commonUTIL.displayError(ProductConstants.WINDOW_NAME + "Util", "deleteButtonAction", e);
		}
	}

	@Override
	public void windowstartUpData() {

	}

	@Override
	public void clearALL() {
	}
}