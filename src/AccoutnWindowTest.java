import java.awt.BorderLayout;
import java.awt.Container;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.ReferenceDataCache;
import util.RemoteServiceUtil;
import util.commonUTIL;

import apps.window.referencewindow.FutureProductWindow;

import apps.window.util.propertyPane.combox.ContractSelectorComboBox;
import apps.window.util.propertyTable.AccountingPropertyTable;
import apps.window.util.propertyTable.FutureContractPropertyTable;

import beans.FutureContract;
import beans.FutureProduct;
import beans.LegalEntity;
import beans.Product;

import com.jidesoft.dialog.ButtonPanel;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.swing.JideBoxLayout;

import constants.ProductConstants;
import constants.ReferenceConstants;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;

public class AccoutnWindowTest extends JFrame {

	Vector<FutureContract> data = new Vector<FutureContract>();
	String col[] = { "ExpirationDate", "LastTradeDate", "FirstDeliveryDate",
			"LastDeliveryDate", "FirstNotificationDate",
			"LastNotificationDate", "QuoteName", "CUSIP", "ISIN" };
	private final String DISPLAYABLEOBJECT = ProductConstants.FUTURECONTRACT;
	private JPanel ContractStatusBar = new JPanel();
	private final int WINDOW_WIDTH = 1200;// 900
	private final int WINDOW_DEPTH = 600;
	private final int CONTRACT_PANEL_SPLIT_LOCATION = 105;
	private final int CONTRACT_FUTURE_PANEL_SPLIT_LOCATION = 370;
	private JPanel splitLeftPanel = new JPanel(new BorderLayout());
	private JPanel splitRightPanel = new JPanel(new BorderLayout());
	private JLabel contractLabel = new JLabel("FutureContract");
	private JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
			splitLeftPanel, splitRightPanel);

	private JPanel oldPropertyTablePanel = null;
	private JPanel _futureContractDetailPropertyTable = null;
	private JPanel _futureUnderlyingPanel = null;
	private JPanel futureContractTablePanel = new JPanel(new BorderLayout());
	private final String CONTRACTPROPERTIESPANELNAME = "Contract Details";
	private JPanel contractPropertiesPanel = new JPanel(new BorderLayout());
	// TableModelFutureContractUtil model = null;
	protected JButton loadButton = new JButton("Load...");
	protected JButton newButton = new JButton("New");
	protected JButton deleteButton = new JButton("Delete");
	protected JButton saveButton = new JButton("Save");
	protected JButton saveAsNewButton = new JButton("Save As New");
	protected JButton closeButton = new JButton("Close");
	RemoteProduct remoteProduct = null;
	RemoteReferenceData remoteRef = null;
	FutureProduct futureProduct = null;
	protected JLabel dateLabel = new JLabel("Date");
	protected JTextField dateText = new JTextField("12/06/2014  ");
	protected JButton loadFuture = new JButton("Load Future");
	protected JButton saveFuture = new JButton("Save Futures");
	protected JButton deleteFuture = new JButton("Delete Futures");

	private JPanel buttonsPanel = new JPanel();
	private JPanel rightbuttonsPanel = new JPanel(new BorderLayout());
	private JScrollPane scrollPane = new JScrollPane();
	JTable futureContracts = new JTable();

	private PropertyTable propertyTable = null;

	private void createLayout() {
		Container contentPane = getContentPane();
		setSize(WINDOW_WIDTH, WINDOW_DEPTH);

		contentPane.setLayout(new BorderLayout());
		splitLeftPanel.setLayout(new BorderLayout());
		contentPane.add(splitPane, BorderLayout.CENTER);

		splitPane.setDividerLocation(CONTRACT_FUTURE_PANEL_SPLIT_LOCATION);
		// splitLeftPanel.add(contractStatusBar, BorderLayout.NORTH);
		splitLeftPanel.add(contractPropertiesPanel, BorderLayout.CENTER);
		splitLeftPanel.add(buttonsPanel, BorderLayout.SOUTH);
		// rightbuttonsPanel.add(getControlPanel() ,BorderLayout.NORTH );

		splitRightPanel.add(rightbuttonsPanel, BorderLayout.NORTH);
		// setupFutureContractStatusBar();
		// model = new TableModelFutureContractUtil(data,col);
		// futureContracts.setModel(model);
		// futureContracts.setSelectionMode(SelectionMode.MULTIPLE_INTERVAL);
		// scrollPane.getViewport().add(futureContracts);
		splitRightPanel.add(scrollPane, BorderLayout.CENTER);
		// splitPane.add(splitLeftPanel);

		createButtonsPanel();
		createPropertyTablesForFutureContract();
	}

	private void createButtonsPanel() {

		// setButtonDetails();
		JPanel buttonPanel = new ButtonPanel(SwingConstants.RIGHT);

		buttonPanel.add(buttons2Column(loadButton, newButton));
		buttonPanel.add(buttons2Column(saveAsNewButton, saveButton));
		buttonPanel.add(buttons2Column(deleteButton, closeButton));

		initBottomButtonsActionListeners();

		getRootPane().setDefaultButton(loadButton);
		buttonsPanel.add(buttonPanel, JideBoxLayout.FLEXIBLE);

	}

	private ButtonPanel buttons2Column(JButton topButton, JButton botButton) {

		ButtonPanel buttonPanel = new ButtonPanel(SwingConstants.TOP);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		buttonPanel.add(topButton, ButtonPanel.AFFIRMATIVE_BUTTON);
		buttonPanel.add(botButton, ButtonPanel.AFFIRMATIVE_BUTTON);
		return buttonPanel;

	}

	private void initBottomButtonsActionListeners() {

		saveButton.setToolTipText(saveButton.getName());
		saveButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("Save");

			}

		});

		closeButton.setToolTipText(closeButton.getName());
		closeButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("Close");
			}

		});

		saveAsNewButton.setToolTipText(saveAsNewButton.getName());
		saveAsNewButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// System.out.println("Save As New");
				System.out.println(propertyTable.getValueAt(1, 1) + "++++++");
				System.out.println(propertyTable.getValueAt(2, 1) + "----");

			}

		});

		deleteButton.setToolTipText(deleteButton.getName());
		deleteButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("delete");
			}

		});

		newButton.setToolTipText(newButton.getName());
		newButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("New");
			}

		});

		loadButton.setToolTipText(loadButton.getName());
		loadButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("Load");
			}

		});

	}

	private void createPropertyTablesForFutureContract() {

		JPanel propertyTablePanel = getAccountingPropertyTablePanel();
		if (oldPropertyTablePanel != null) {
			futureContractTablePanel.remove(oldPropertyTablePanel);
		}
		oldPropertyTablePanel = propertyTablePanel;
		contractPropertiesPanel.add(oldPropertyTablePanel, BorderLayout.CENTER);

	}

	public JPanel getAccountingPropertyTablePanel() {

		propertyTable = new AccountingPropertyTable(
				ReferenceConstants.REFERENCE_WINDOW_ACCOUNTING)
				.getAccountingPropertyTable();

		for (int i = 0; i < propertyTable.getRowCount(); i++) {

			propertyTable.setValueAt("", i, 1);

		}

		JPanel basePanel = new JPanel();
		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.getViewport().add(propertyTable);
		basePanel.setLayout(new BorderLayout());
		basePanel.add(tableScrollPane, BorderLayout.CENTER);

		return basePanel;

	}

	public static void main(String args[]) {
		AccoutnWindowTest c1 = new AccoutnWindowTest();
		// c1.setSize(900, 900);
		c1.setVisible(true);
		try {
			c1.jbInit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void jbInit() throws Exception {
		// super();
		remoteProduct = RemoteServiceUtil.getRemoteProductService();
		remoteRef = RemoteServiceUtil.getRemoteReferenceDataService();
		createLayout();
		setupMainComponents();
	}

	private void setupMainComponents() {
		String TITLE = "Accounting Window";
		setTitle(TITLE);
		// setupContractStatusBar();
		// setupFutureContractStatusBar();

	}
}
