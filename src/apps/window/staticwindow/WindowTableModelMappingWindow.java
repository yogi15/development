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
import apps.window.staticwindow.util.WindowTableModelMappingWindowUtil;
import apps.window.util.propertyTable.WindowTableModelMappingPropertyTable;
import apps.window.util.tableModelUtil.WindowTableModelMappingTableModelUtil;
import beans.WindowTableModelMapping;
import constants.CommonConstants;
import constants.WindowTableModelMappingConstants;

public class WindowTableModelMappingWindow extends BasePanel {
	ActionMap actions = null;
	private static final long serialVersionUID = 1L;
	public WindowTableModelMappingTableModelUtil model = null;
	public String[] searchData;
	WindowTableModelMapping windowtablemodelmapping = new WindowTableModelMapping(); // /
																						// used
																						// as
																						// a
																						// bean
	// used for Validation and save,update and delete and get Data from DB.
	WindowTableModelMappingWindowUtil windowUtil = null;
	Vector<WindowTableModelMapping> rightPanelJtableWindowTableModelMappingdata = new Vector<WindowTableModelMapping>(); // used
																															// maintain
																															// data
																															// in
																															// rightPanel
																															// in
																															// center
																															// area.

	/**
	 * @return the data / public Vector<WindowTableModelMapping> getData() {
	 *         return rightPanelJtableWindowTableModelMappingdata; } /**
	 * @param data
	 *            the data to set
	 */
	public void setData(Vector<WindowTableModelMapping> data) {
		// this.data = data;
		rightPanelJtableWindowTableModelMappingdata = data;
	}

	// leftTopPanel Data
	protected JLabel windowName = new JLabel("WindowName");
	public final JTextField WindowTableModelMappingSearchTextField = new JTextField(
			"WindowTableModelMappingTextField"); // search textfield in
													// leftTopPanel Data
	// rightTopPanel Data
	private JLabel windowtablemodelmappingLabel = new JLabel("Window Name");
	protected JButton windowDetails = new JButton("Load WindowDetails");
	// leftSide PropertyTable
	public WindowTableModelMappingPropertyTable propertyTable = null;
	

	// Constructor
	public WindowTableModelMappingWindow() {
		try {
			initComponents();
		} catch (CosmosException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError(
					WindowTableModelMappingConstants.WINDOW_NAME,
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
		model = new WindowTableModelMappingTableModelUtil(
				rightPanelJtableWindowTableModelMappingdata);
		rightSideCenterTable.setModel(model);
		createSingleSplitPaneLayout(CommonConstants.SPLITWINDOWLOCATION);
		setSize(CommonConstants.WINDOWWIDTH, CommonConstants.WINDOWHIGHT);
	}

	/**
	 * @return the WindowTableModelMapping
	 */
	public WindowTableModelMapping getWindowTableModelMapping() {
		return windowtablemodelmapping;
	}

	/**
	 * @param WindowTableModelMapping
	 *            the WindowTableModelMapping to set
	 */
	public void setWindowTableModelMapping(
			WindowTableModelMapping windowtablemodelmapping) {
		this.windowtablemodelmapping = windowtablemodelmapping;
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
		windowUtil = new WindowTableModelMappingWindowUtil();
		windowUtil.setWindow(this);
		this.validationActionUtil = windowUtil;
	}

	// add listerener to panel Jcompenonts.
	@Override
	public void setWindowActionListener() {
		try {
			setEventListener(windowDetails);
			setEventListener(WindowTableModelMappingSearchTextField);
		} catch (CosmosException e) {
			e.printStackTrace();
		}
	}

	// add lefttop panel componenonts
	@Override
	public void addTopLeftSidePanelComponents() {
		WindowTableModelMappingSearchTextField
				.setName(WindowTableModelMappingConstants.SEARCHTEXTBOX);
		leftTopbuttonsPanel.add(windowtablemodelmappingLabel);
		setSearchOnLeftTopPanel(WindowTableModelMappingSearchTextField, searchData);
		leftTopbuttonsPanel.add(WindowTableModelMappingSearchTextField);
	}

	// add righttop panel componenonts
	@Override
	public void addTopRigthSidePanelComponents() {
		rightTopbuttonsPanel.add(windowName);
		rightTopbuttonsPanel.add(windowDetails);
	}

	// create property proprities.
	@Override
	public void createPropertyPaneTable() {
		propertyTable = new WindowTableModelMappingPropertyTable(
				WindowTableModelMappingConstants.WINDOW_NAME,
				windowtablemodelmapping);
		setLeftSidePropertyPanePanel(propertyTable
				.getPropertyTable(generateProperty(WindowTableModelMappingConstants.WINDOW_NAME)));
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
