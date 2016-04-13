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

import util.CosmosException;
import util.commonUTIL;
import apps.window.staticwindow.util.SearchConfigWindowUtil;
import apps.window.util.propertyTable.SearchConfigPropertyTable;
import apps.window.util.tableModelUtil.SearchConfigTableModelUtil;
import beans.SearchConfig;
import constants.CommonConstants;
import constants.SearchConfigConstants;
import constants.WindowSheetConstants;

public class SearchConfigWindow extends BasePanel {
	ActionMap actions = null;
	public String searchData[];
	private static final long serialVersionUID = 1L;
	public SearchConfigTableModelUtil model = null;
	SearchConfig searchconfig = new SearchConfig(); // / used as a bean
	// used for Validation and save,update and delete and get Data from DB.
	SearchConfigWindowUtil windowUtil = null;
	Vector<SearchConfig> rightPanelJtableSearchConfigdata = new Vector<SearchConfig>(); // used
																						// maintain
																						// data
																						// in
																						// rightPanel
																						// in
																						// center
																						// area.

	/**
	 * @return the data / public Vector<SearchConfig> getData() { return
	 *         rightPanelJtableSearchConfigdata; } /**
	 * @param data
	 *            the data to set
	 */
	public void setData(Vector<SearchConfig> data) {
		// this.data = data;
		rightPanelJtableSearchConfigdata = data;
	}

	// leftTopPanel Data
	protected JLabel searchconfigLabelName = new JLabel("SearchConfig Name");
	public final JTextField searchconfigSearchTextField = new JTextField(
			"SearchConfigTextField", 15); // search textfield in leftTopPanel
											// Data
	// rightTopPanel Data
	private JLabel searchconfigName = new JLabel("SearchConfig Name");
	protected JButton searchconfigDetails = new JButton(
			"Load SearchConfigDetails");
	// leftSide PropertyTable
	public SearchConfigPropertyTable propertyTable = null;

	// Constructor
	public SearchConfigWindow() {
		try {
			initComponents();
		} catch (CosmosException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError(SearchConfigConstants.WINDOW_NAME,
					"Constructor", e);
		}
	}

	private void initComponents() throws CosmosException {
		// / init() data required while loading this window.
		init();
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null,
				null));
		setLayout(new BorderLayout());
		// add model to table
		model = new SearchConfigTableModelUtil(rightPanelJtableSearchConfigdata);
		setCornerForScrollPane(model.getCol());
		setQuickSearchOnTable(model, model.getCol().length);
		createSingleSplitPaneLayout(CommonConstants.SPLITWINDOWLOCATION);
		setSize(CommonConstants.WINDOWWIDTH, CommonConstants.WINDOWHIGHT);
	}

	/**
	 * @return the SearchConfig
	 */
	public SearchConfig getSearchConfig() {
		return searchconfig;
	}

	/**
	 * @param SearchConfig
	 *            the SearchConfig to set
	 */
	public void setSearchConfig(SearchConfig searchconfig) {
		this.searchconfig = searchconfig;
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
		windowUtil = new SearchConfigWindowUtil();
		windowUtil.setWindow(this);
		this.validationActionUtil = windowUtil;
	}

	// add listerener to panel Jcompenonts.
	@Override
	public void setWindowActionListener() {
		try {
			setEventListener(searchconfigDetails);
			setEventListener(searchconfigSearchTextField);
		} catch (CosmosException e) {
			e.printStackTrace();
		}
	}

	// add lefttop panel componenonts
	@Override
	public void addTopLeftSidePanelComponents() {
		searchconfigSearchTextField
				.setName(SearchConfigConstants.SEARCHTEXTBOX);
		leftTopbuttonsPanel.add(searchconfigLabelName);
		setSearchOnLeftTopPanel(searchconfigSearchTextField, searchData);
		leftTopbuttonsPanel.add(searchconfigSearchTextField);
	}

	// add righttop panel componenonts
	@Override
	public void addTopRigthSidePanelComponents() {
		rightTopbuttonsPanel.add(searchconfigName);
		rightTopbuttonsPanel.add(searchconfigDetails);
	}

	// create property proprities.
	@Override
	public void createPropertyPaneTable() {
		propertyTable = new SearchConfigPropertyTable(
				SearchConfigConstants.WINDOW_NAME, searchconfig);
		setLeftSidePropertyPanePanel(propertyTable
				.getPropertyTable(generateProperty(SearchConfigConstants.WINDOW_NAME)));
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
		// TODO Auto-generated method stub
		return SearchConfigConstants.WINDOW_NAME;
	}
}
