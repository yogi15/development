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
import javax.swing.table.TableModel;

import util.CosmosException;
import util.commonUTIL;
import apps.window.reportwindow.jideReport.PivotReport;
import apps.window.reportwindow.jideReport.SwingReportDemo;
import apps.window.staticwindow.util.SearchPropertyWindowUtil;
import apps.window.util.propertyTable.SearchPropertyPropertyTable;
import apps.window.util.tableModelUtil.SearchPropertyTableModelUtil;
import beans.SearchProperty;
import constants.CommonConstants;
import constants.SearchPropertyConstants;
import constants.WindowSheetConstants;

public class SearchPropertyWindow extends BasePanel {
	ActionMap actions = null;
	public String searchData[];
	public String searchType = "Product";
	public String attributeSearchType;
	private static final long serialVersionUID = 1L;
	public TableModel model = null;
	public  SwingReportDemo  demo = null;
	    public PivotReport pReport = null;
	SearchProperty searchproperty = new SearchProperty(); // / used as a bean
	// used for Validation and save,update and delete and get Data from DB.
	SearchPropertyWindowUtil windowUtil = null;
	Vector<SearchProperty> rightPanelJtableSearchPropertydata = new Vector<SearchProperty>(); // used
																								// maintain
																								// data
																								// in
																								// rightPanel
																								// in
																								// center
																								// area.

	/**
	 * @return the data / public Vector<SearchProperty> getData() { return
	 *         rightPanelJtableSearchPropertydata; } /**
	 * @param data
	 *            the data to set
	 */
	public void setData(Vector<SearchProperty> data) {
		// this.data = data;
		rightPanelJtableSearchPropertydata = data;
	}

	// leftTopPanel Data
	protected JLabel searchpropertyLabelName = new JLabel("ID");
	public final JTextField searchpropertySearchTextField = new JTextField(
			" ", 10); // search textfield in leftTopPanel
											// Data
	// rightTopPanel Data
	private JLabel searchpropertyName = new JLabel("SearchProperty Name");
	protected JButton searchpropertyDetails = new JButton(
			"Load SearchPropertyDetails");
	// leftSide PropertyTable
	public SearchPropertyPropertyTable propertyTable = null;

	// Constructor
	public SearchPropertyWindow(String searchType) {
		try {
			initComponents(searchType);
		} catch (CosmosException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError(SearchPropertyConstants.WINDOW_NAME,
					"Constructor", e);
		}
	}

	private void initComponents(String searchType) throws CosmosException {
		// / init() data required while loading this window.
		this.searchType = searchType;
		    pReport =  new PivotReport(null); 
	    	  demo =  new SwingReportDemo(pReport);
	    	
		init();
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null,
				null));
		setLayout(new BorderLayout());
		// add model to table
		model = new SearchPropertyTableModelUtil(
				rightPanelJtableSearchPropertydata);
	//	model = demo.getTableSortView().getModel();
		if( demo.getSortTable() != null) {
		rightSideCenterTable = demo.getSortTable();
	}
		//setCornerForScrollPane(model.getCol());
		rightSideCenterTable.setModel(model);
	//setQuickSearchOnTable(model,model.getColumnCount());
		createSearchSingleSplitPaneLayout(CommonConstants.SPLITWINDOWLOCATION);
		setSize(SearchPropertyConstants.WINDOWWIDTH, SearchPropertyConstants.WINDOWHIGHT);
	}

	/**
	 * @return the SearchProperty
	 */
	public SearchProperty getSearchProperty() {
		return searchproperty;
	}

	/**
	 * @param SearchProperty
	 *            the SearchProperty to set
	 */
	public void setSearchProperty(SearchProperty searchproperty) {
		this.searchproperty = searchproperty;
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
		windowUtil = new SearchPropertyWindowUtil();
		windowUtil.setWindow(this);
		this.validationActionUtil = windowUtil;
	}

	// add listerener to panel Jcompenonts.
	@Override
	public void setWindowActionListener() {
		try {
			setEventListener(searchpropertyDetails);
			setEventListener(searchpropertySearchTextField);
		} catch (CosmosException e) {
			e.printStackTrace();
		}
	}

	// add lefttop panel componenonts
	@Override
	public void addTopLeftSidePanelComponents() {
		searchpropertySearchTextField
				.setName(SearchPropertyConstants.SEARCHTEXTBOX);
		leftTopbuttonsPanel.add(searchpropertyLabelName);
		//setSearchOnLeftTopPanel(searchpropertySearchTextField, searchData);
		leftTopbuttonsPanel.add(searchpropertySearchTextField);
	}

	// add righttop panel componenonts
	@Override
	public void addTopRigthSidePanelComponents() {
		rightTopbuttonsPanel.add(searchpropertyName);
		rightTopbuttonsPanel.add(searchpropertyDetails);
	}

	// create property proprities.
	@Override
	public void createPropertyPaneTable() {
		String propertyName = SearchPropertyConstants.WINDOW_NAME;
		if(windowUtil.searchConfig  != null && !commonUTIL.isEmpty(windowUtil.searchConfig.getSearchAttributes()) ) {
			propertyName = windowUtil.searchConfig.getSearchAttributes();
		}
		propertyTable = new SearchPropertyPropertyTable(
				SearchPropertyConstants.WINDOW_NAME, searchproperty);
		setLeftSidePropertyPanePanel(propertyTable
				.getPropertyTable(generateSearchAttributeProperty(propertyName)));
	}

	@Override
	public void addCenterRightSidePanelComponents() {
		// TODO Auto-generated method stub 
		JPanel	  jPanel1  = demo.run();
	
		// setQuickSearchOnTable(rightSideCenterTable.getModel(), rightSideCenterTable.getModel().getColumnCount());
	 
		jPanel1.add(rightSideCenterTable, BorderLayout.CENTER);
		centerRightSidePanel.add(jPanel1, BorderLayout.CENTER);
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
		// TODO Auto-generated method stub
		return SearchPropertyConstants.WINDOW_NAME;
	}
}
