package apps.window.staticwindow;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import apps.window.staticwindow.util.CurrencyDefaultWindowUtil;
import apps.window.util.propertyTable.CurrencyDefaultPropertyTable;
import apps.window.util.tableModelUtil.CurrencyDefaultTableModelUtil;
import beans.CurrencyDefault;
import constants.CommonConstants;
import constants.CurrencyDefaultConstants;
import util.CosmosException;
import util.commonUTIL;

public class CurrencyDefaultWindow extends BasePanel {
	ActionMap actions = null;
	public String searchData[];
	private static final long serialVersionUID = 1L;
	public CurrencyDefaultTableModelUtil model = null;
	CurrencyDefault currencydefault = new CurrencyDefault(); /// used as a bean
	// used for Validation and save,update and delete and get Data from DB.
	CurrencyDefaultWindowUtil windowUtil = null;
	Vector<CurrencyDefault> rightPanelJtableCurrencyDefaultdata = new Vector<CurrencyDefault>(); // used
																									// maintain
																									// data
																									// in
																									// rightPanel
																									// in
																									// center
																									// area.

	/**
	 * @return the data / public Vector<CurrencyDefault> getData() { return
	 *         rightPanelJtableCurrencyDefaultdata; } /**
	 * @param data
	 *            the data to set
	 */
	public void setData(Vector<CurrencyDefault> data) {
		// this.data = data;
		rightPanelJtableCurrencyDefaultdata = data;
	}

	// leftTopPanel Data
	protected JLabel currencydefaultLabelName = new JLabel("CurrencyDefault Name");
	public final JTextField currencydefaultSearchTextField = new JTextField("CurrencyDefaultTextField", 15); // search
																												// textfield
																												// in
																												// leftTopPanel
																												// Data
	// rightTopPanel Data
	private JLabel currencydefaultName = new JLabel("CurrencyDefault Name");
	protected JButton currencydefaultDetails = new JButton("Load CurrencyDefaultDetails");
	// leftSide PropertyTable
	public CurrencyDefaultPropertyTable propertyTable = null;

	// Constructor
	public CurrencyDefaultWindow() {
		try {
			initComponents();
		} catch (CosmosException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError(CurrencyDefaultConstants.WINDOW_NAME, "Constructor", e);
		}
	}

	private void initComponents() throws CosmosException {
		/// init() data required while loading this window.
		init();
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new BorderLayout());
		// add model to table
		//
		model = new CurrencyDefaultTableModelUtil(rightPanelJtableCurrencyDefaultdata);
		// adding model
		setCornerForScrollPane(model.getCol());
		setQuickSearchOnTable(model, model.getColumnCount());
		hierarchicalTable = createTable(model, new CurrencyPairWindow());
		setEventListener(hierarchicalTable);
		createSingleSplitPaneLayout(CommonConstants.SPLITWINDOWLOCATION);
		setSize(CommonConstants.WINDOWWIDTH, CommonConstants.WINDOWHIGHT);
	}

	/**
	 * @return the CurrencyDefault
	 */
	public CurrencyDefault getCurrencyDefault() {
		return currencydefault;
	}

	/**
	 * @param CurrencyDefault
	 *            the CurrencyDefault to set
	 */
	public void setCurrencyDefault(CurrencyDefault currencydefault) {
		this.currencydefault = currencydefault;
	}

	@Override
	public ActionMap getHotKeysActionMapper() {
		ActionMap action = new ActionMap();
		return action;
	}

	@Override
	public JPanel getHotKeysPanel() {
		return rightTopbuttonsPanel;
	}

	@Override
	public ArrayList<Component> getFocusOrderList() {
		ArrayList<Component> comps = new ArrayList<Component>();
		comps.add(loadButton);
		return comps;
	}

	// add Window Validation util for search,save,new,saveAsNew,close and other
	// custom components.
	@Override
	public void setWindowValidationUtil() {
		windowUtil = new CurrencyDefaultWindowUtil();
		windowUtil.setWindow(this);
		this.validationActionUtil = windowUtil;
	}

	// add listerener to panel Jcompenonts.
	@Override
	public void setWindowActionListener() {
		try {
			setEventListener(currencydefaultDetails);
			setEventListener(currencydefaultSearchTextField);
		} catch (CosmosException e) {
			e.printStackTrace();
		}
	}

	// add lefttop panel componenonts
	@Override
	public void addTopLeftSidePanelComponents() {
		currencydefaultSearchTextField.setName(CurrencyDefaultConstants.SEARCHTEXTBOX);
		leftTopbuttonsPanel.add(currencydefaultLabelName);
		setSearchOnLeftTopPanel(currencydefaultSearchTextField, searchData);
		leftTopbuttonsPanel.add(currencydefaultSearchTextField);
	}

	// add righttop panel componenonts
	@Override
	public void addTopRigthSidePanelComponents() {
		rightTopbuttonsPanel.add(currencydefaultName);
		rightTopbuttonsPanel.add(currencydefaultDetails);
	}

	// create property proprities.
	@Override
	public void createPropertyPaneTable() {
		propertyTable = new CurrencyDefaultPropertyTable(CurrencyDefaultConstants.WINDOW_NAME, currencydefault);
		setLeftSidePropertyPanePanel(
				propertyTable.getPropertyTable(generateProperty(CurrencyDefaultConstants.WINDOW_NAME)));
	}

	@Override
	public void addCenterRightSidePanelComponents() {
		// TODO Auto-generated method stub
		scrollPane.getViewport().add(hierarchicalTable);
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
		return CurrencyDefaultConstants.WINDOW_NAME;
	}
}
