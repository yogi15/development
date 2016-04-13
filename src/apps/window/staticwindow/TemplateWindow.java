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
import apps.window.staticwindow.util.TemplateWindowUtil;
import apps.window.util.propertyTable.TemplatePropertyTable;
import apps.window.util.tableModelUtil.TemplateTableModelUtil;
import beans.Template;
import constants.CommonConstants;
import constants.TemplateConstants;

public class TemplateWindow extends BasePanel {
	ActionMap actions = null;
	private static final long serialVersionUID = 1L;
	public TemplateTableModelUtil model = null;
	Template template = new Template(); // / used as a bean
	// used for Validation and save,update and delete and get Data from DB.
	TemplateWindowUtil windowUtil = null;

	Vector<Template> rightPanelJtableTemplatedata = new Vector<Template>(); // used
																			// maintain
																			// data
																			// in
																			// rightPanel
																			// in
																			// center
																			// area.

	/**
	 * @return the data
	 */
	public Vector<Template> getData() {
		return rightPanelJtableTemplatedata;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Vector<Template> data) {
		// this.data = data;
		rightPanelJtableTemplatedata = data;
	}

	// leftTopPanel Data
	protected JLabel windowName = new JLabel("WindowName");
	public final JTextField templateSearchTextField = new JTextField(
			"TemplateTextField"); // search textfield in leftTopPanel Data
	// rightTopPanel Data

	private JLabel templateLabel = new JLabel("Window Name");
	protected JButton templateDetails = new JButton("Load tDetails");
	// leftSide PropertyTable
	public TemplatePropertyTable propertyTable = null;

	// Constructor
	public TemplateWindow() {
		try {
			initComponents();
		} catch (CosmosException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError(TemplateConstants.WINDOW_NAME,
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
		model = new TemplateTableModelUtil(rightPanelJtableTemplatedata);
		rightSideCenterTable.setModel(model);
		createSingleSplitPaneLayout(CommonConstants.SPLITWINDOWLOCATION);
		setSize(CommonConstants.WINDOWWIDTH, CommonConstants.WINDOWHIGHT);
	}

	/**
	 * @return the Template
	 */
	public Template getTemplate() {
		return template;
	}

	/**
	 * @param Template
	 *            the Template to set
	 */
	public void setTemplate(Template template) {
		this.template = template;
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
		windowUtil = new TemplateWindowUtil();
		windowUtil.setWindow(this);
		this.validationActionUtil = windowUtil;
	}

	// add listerener to panel Jcompenonts.
	@Override
	public void setWindowActionListener() {
		try {
			setEventListener(windowDetails);
			setEventListener(templateSearchTextField);
		} catch (CosmosException e) {
			e.printStackTrace();
		}
	}

	// add lefttop panel componenonts
	@Override
	public void addTopLeftSidePanelComponents() {
		templateSearchTextField.setName(TemplateConstants.SEARCHTEXTBOX);
		leftTopbuttonsPanel.add(templateLabel);
		setSearchOnLeftTopPanel(TemplateSearchTextField, null);
		leftTopbuttonsPanel.add(TemplateSearchTextField);
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
		propertyTable = new TemplatePropertyTable(
				TemplateConstants.WINDOW_NAME, template);
		setLeftSidePropertyPanePanel(propertyTable
				.getPropertyTable(generateProperty(TemplateConstants.WINDOW_NAME)));
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
