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
import apps.window.staticwindow.util.MenuConfigurationWindowUtil;
import apps.window.util.propertyTable.MenuConfigurationPropertyTable;
import apps.window.util.tableModelUtil.MenuConfigurationTableModelUtil;
import beans.MenuConfiguration;
import constants.CommonConstants;
import constants.MenuConfigurationConstants;

public class MenuConfigurationWindow extends BasePanel {
	ActionMap actions = null;
	public String searchData[];
	private static final long serialVersionUID = 1L;
	public MenuConfigurationTableModelUtil model = null;
	MenuConfiguration menuconfiguration = new MenuConfiguration(); // / used as
																	// a bean
	// used for Validation and save,update and delete and get Data from DB.
	MenuConfigurationWindowUtil windowUtil = null;
	Vector<MenuConfiguration> rightPanelJtableMenuConfigurationdata = new Vector<MenuConfiguration>(); // used
																										// maintain
																										// data
																										// in
																										// rightPanel
																										// in
																										// center
																										// area.

	/**
	 * @return the data / public Vector<MenuConfiguration> getData() { return
	 *         rightPanelJtableMenuConfigurationdata; } /**
	 * @param data
	 *            the data to set
	 */
	public void setData(Vector<MenuConfiguration> data) {
		// this.data = data;
		rightPanelJtableMenuConfigurationdata = data;
	}

	// leftTopPanel Data
	protected JLabel windowName = new JLabel("WindowName");
	public final JTextField MenuConfigurationSearchTextField = new JTextField(
			"MenuConfigurationTextField"); // search textfield in leftTopPanel
											// Data
	// rightTopPanel Data
	private JLabel menuconfigurationLabel = new JLabel("Search MainMenu");
	protected JButton MenuConfigurationDetails = new JButton(
			"ShowMenuDemo");
	// leftSide PropertyTable
	public MenuConfigurationPropertyTable propertyTable = null;

	// Constructor
	public MenuConfigurationWindow() {
		try {
			initComponents();
		} catch (CosmosException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError(MenuConfigurationConstants.WINDOW_NAME,
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
		model = new MenuConfigurationTableModelUtil(
				rightPanelJtableMenuConfigurationdata);
		rightSideCenterTable.setModel(model);
		createSingleSplitPaneLayout(CommonConstants.SPLITWINDOWLOCATION);
		setSize(CommonConstants.WINDOWWIDTH, CommonConstants.WINDOWHIGHT);
	}

	/**
	 * @return the MenuConfiguration
	 */
	public MenuConfiguration getMenuConfiguration() {
		return menuconfiguration;
	}

	/**
	 * @param MenuConfiguration
	 *            the MenuConfiguration to set
	 */
	public void setMenuConfiguration(MenuConfiguration menuconfiguration) {
		this.menuconfiguration = menuconfiguration;
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
		windowUtil = new MenuConfigurationWindowUtil();
		windowUtil.setWindow(this);
		this.validationActionUtil = windowUtil;
	}

	// add listerener to panel Jcompenonts.
	@Override
	public void setWindowActionListener() {
		try {
			setEventListener(MenuConfigurationDetails);
			setEventListener(MenuConfigurationSearchTextField);
		} catch (CosmosException e) {
			e.printStackTrace();
		}
	}

	// add lefttop panel componenonts
	@Override
	public void addTopLeftSidePanelComponents() {
		MenuConfigurationSearchTextField
				.setName(MenuConfigurationConstants.SEARCHTEXTBOX);
		leftTopbuttonsPanel.add(menuconfigurationLabel);
		setSearchOnLeftTopPanel(MenuConfigurationSearchTextField, searchData);
		leftTopbuttonsPanel.add(MenuConfigurationSearchTextField);
	}

	// add righttop panel componenonts
	@Override
	public void addTopRigthSidePanelComponents() {
		rightTopbuttonsPanel.add(windowName);
		rightTopbuttonsPanel.add(MenuConfigurationDetails);
	}

	// create property proprities.
	@Override
	public void createPropertyPaneTable() {
		propertyTable = new MenuConfigurationPropertyTable(
				MenuConfigurationConstants.WINDOW_NAME, menuconfiguration);
		setLeftSidePropertyPanePanel(propertyTable
				.getPropertyTable(generateProperty(MenuConfigurationConstants.WINDOW_NAME)));
	}

	@Override
	public void addCenterRightSidePanelComponents() {
		// TODO Auto-generated method stub
		centerRightSidePanel.add(scrollPane, BorderLayout.CENTER);
	}

	@Override
	public JPanel createChildPanel(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel createChildPanel(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
