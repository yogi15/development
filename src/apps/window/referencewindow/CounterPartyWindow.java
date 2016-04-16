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
import apps.window.staticwindow.util.CounterPartyWindowUtil;
import apps.window.util.propertyTable.CounterPartyPropertyTable;
import apps.window.util.tableModelUtil.CounterPartyTableModelUtil;
import beans.LegalEntity;
import constants.CommonConstants;
import constants.LegalEntityConstants;

public class CounterPartyWindow extends BasePanel {
	ActionMap actions = null;
	public String searchData[];
	public String[] CountryData;
	private static final long serialVersionUID = 1L;
	public CounterPartyTableModelUtil model = null;
	LegalEntity counterparty = new LegalEntity(); /// used as a bean
	// used for Validation and save,update and delete and get Data from DB.
	CounterPartyWindowUtil windowUtil = null;
	Vector<LegalEntity> rightPanelJtableCounterPartydata = new Vector<LegalEntity>(); // used
																						// maintain
																						// data
																						// in
																						// rightPanel
																						// in
																						// center
																						// area.

	/**
	 * @return the data / public Vector<LegalEntity> getData() { return
	 *         rightPanelJtableCounterPartydata; } /**
	 * @param data
	 *            the data to set
	 */
	public void setData(Vector<LegalEntity> data) {
		// this.data = data;
		rightPanelJtableCounterPartydata = data;
	}

	// leftTopPanel Data
	protected JLabel counterpartyLabelName = new JLabel("CounterParty Name");
	public final JTextField counterpartySearchTextField = new JTextField("LegalEntityTextField", 15); // search
																										// textfield
																										// in
																										// leftTopPanel
																										// Data
	// rightTopPanel Data
	private JLabel counterpartyName = new JLabel("CounterParty Name");
	protected JButton counterpartyDetails = new JButton("Load LegalEntityDetails");
	// leftSide PropertyTable
	public CounterPartyPropertyTable propertyTable = null;

	// Constructor
	public CounterPartyWindow() {
		try {
			initComponents();
		} catch (CosmosException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError(LegalEntityConstants.WINDOW_NAME, "Constructor", e);
		}
	}

	private void initComponents() throws CosmosException {
		/// init() data required while loading this window.
		init();
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new BorderLayout());
		// add model to table
		//
		model = new CounterPartyTableModelUtil(rightPanelJtableCounterPartydata);
		// adding model
		setCornerForScrollPane(model.getCol());
		setQuickSearchOnTable(model, model.getColumnCount());
		hierarchicalTable = createTable(model, new ContactWindow());
		setEventListener(hierarchicalTable);
		createSingleSplitPaneLayout(CommonConstants.SPLITWINDOWLOCATION);
		hierarchicalTable.getColumnModel().getColumn(3).setCellEditor(new TextFieldCellEditor(String.class) {
			@Override
			protected JTextField createTextField() {
				JTextField cellEditorTextField = new JTextField();
				ListDataIntelliHints fontIntellihints = new ListDataIntelliHints<String>(cellEditorTextField,
						CountryData);
				fontIntellihints.setCaseSensitive(false);
				return cellEditorTextField;
			}
		});
		setSize(CommonConstants.WINDOWWIDTH, CommonConstants.WINDOWHIGHT);
	}

	/**
	 * @return the LegalEntity
	 */
	public LegalEntity getLegalEntity() {
		return counterparty;
	}

	/**
	 * @param LegalEntity
	 *            the LegalEntity to set
	 */
	public void setLegalEntity(LegalEntity counterparty) {
		this.counterparty = counterparty;
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
		windowUtil = new CounterPartyWindowUtil();
		windowUtil.setWindow(this);
		this.validationActionUtil = windowUtil;
	}

	// add listerener to panel Jcompenonts.
	@Override
	public void setWindowActionListener() {
		try {
			setEventListener(counterpartyDetails);
			setEventListener(counterpartySearchTextField);
		} catch (CosmosException e) {
			e.printStackTrace();
		}
	}

	// add lefttop panel componenonts
	@Override
	public void addTopLeftSidePanelComponents() {
		counterpartySearchTextField.setName(LegalEntityConstants.SEARCHTEXTBOX);
		leftTopbuttonsPanel.add(counterpartyLabelName);
		setSearchOnLeftTopPanel(counterpartySearchTextField, searchData);
		leftTopbuttonsPanel.add(counterpartySearchTextField);
	}

	// add righttop panel componenonts
	@Override
	public void addTopRigthSidePanelComponents() {
		rightTopbuttonsPanel.add(counterpartyName);
		rightTopbuttonsPanel.add(counterpartyDetails);
	}

	// create property proprities.
	@Override
	public void createPropertyPaneTable() {
		propertyTable = new CounterPartyPropertyTable(LegalEntityConstants.WINDOW_NAME, counterparty);
		setLeftSidePropertyPanePanel(
				propertyTable.getPropertyTable(generateProperty(LegalEntityConstants.WINDOW_NAME)));
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
		return LegalEntityConstants.WINDOW_NAME;
	}
}
