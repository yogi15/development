package FutureContract;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import util.RemoteServiceUtil;
import util.commonUTIL;

import beans.FutureProduct;

import com.jidesoft.dialog.ButtonPanel;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.swing.JideBoxLayout;

import dsServices.RemoteProduct;

public class ContractDefinitionWindow extends JFrame {
	
	private JPanel contractStatusBar = new JPanel();
	private final String DISPLAYABLEOBJECT = "FutureContract";
	//private JPanel ContractStatusBar = new JPanel();
	private final int WINDOW_WIDTH = 1200;// 900
	private final int WINDOW_DEPTH = 600;
	//private final int CONTRACT_PANEL_SPLIT_LOCATION = 105;
	private final int CONTRACT_FUTURE_PANEL_SPLIT_LOCATION = 370;
	private JPanel splitLeftPanel = new JPanel(new BorderLayout());
	private JPanel splitRightPanel = new JPanel(new BorderLayout());
	private JLabel contractLabel = new JLabel("FutureContract");
	private JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
			splitLeftPanel, splitRightPanel);
	private ContractSelectorComboBox contractSelectorComboBox = null;
	ContractSelectorComboBoxListener contractSelectorComboBoxActionListener = null;
	private JPanel oldPropertyTablePanel = null;
	//private JPanel _futureContractDetailPropertyTable = null;
	//private JPanel _futureUnderlyingPanel = null;
	private JPanel futureContractTablePanel = new JPanel(new BorderLayout());
	//private final String CONTRACTPROPERTIESPANELNAME = "Coontract Details";
	private JPanel contractPropertiesPanel = new JPanel(new BorderLayout());

	protected JButton loadButton = new JButton("Load");
	protected JButton newButton = new JButton("New");
	protected JButton deleteButton = new JButton("Delete");
	protected JButton saveButton = new JButton("Save");
	protected JButton saveAsNewButton = new JButton("Save As New");
	protected JButton closeButton = new JButton("Close");

	private JPanel buttonsPanel = new JPanel();

	private PropertyTable propertyTable = null;

	private void createLayout() {
		
		Container contentPane = getContentPane();
		setSize(WINDOW_WIDTH, WINDOW_DEPTH);

		contentPane.setLayout(new BorderLayout());
		splitLeftPanel.setLayout(new BorderLayout());
		contentPane.add(splitPane, BorderLayout.CENTER);

		splitPane.setDividerLocation(CONTRACT_FUTURE_PANEL_SPLIT_LOCATION);
		splitLeftPanel.add(contractStatusBar, BorderLayout.NORTH);
		splitLeftPanel.add(contractPropertiesPanel, BorderLayout.CENTER);
		splitLeftPanel.add(buttonsPanel, BorderLayout.SOUTH);
		// splitPane.add(splitLeftPanel);

		createButtonsPanel();
		createPropertyTablesForFutureContract();
	
	}

	protected ActionListener getContractSelectorComboBoxActionListener() {
		if (contractSelectorComboBoxActionListener == null) {
			contractSelectorComboBoxActionListener = new ContractSelectorComboBoxListener();
		}
		return contractSelectorComboBoxActionListener;
	}

	class ContractSelectorComboBoxListener implements ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent event) {
			Object object = event.getSource();
			if (!(object instanceof ContractSelectorComboBox))
				return;
			ContractSelectorComboBox obj = (ContractSelectorComboBox) object;
			EntityObject futcon = (EntityObject) obj.getSelectedItem();

			if (futcon != null && futcon.getId() > 0) {

			}
			// setAndShowFutureContract(futcon);
		}
	}

	private void setupMainComponents() {
		String TITLE = "Future Contract Definition";
		setTitle(TITLE);
		setupContractStatusBar();
		// setupFutureContractStatusBar();

	}

	public ContractDefinitionWindow() {
		try {
			jbInit();
		} catch (Exception e) {
			// Log.error(this, e);
		}
		// initDomains();
	}

	private void createPropertyTablesForFutureContract() {

		JPanel propertyTablePanel = getFutureContractPropertyTablePanel();
		if (oldPropertyTablePanel != null) {
			futureContractTablePanel.remove(oldPropertyTablePanel);
		}
		oldPropertyTablePanel = propertyTablePanel;
		contractPropertiesPanel.add(oldPropertyTablePanel, BorderLayout.CENTER);
	}

	public JPanel getFutureContractPropertyTablePanel() {

		// FutureContractPropertyTable contractPropertyTable = new
		// FutureContractPropertyTable("FUTURES_FX");

		propertyTable = new FutureContractPropertyTable("FUTURES_FX")
				.getFutureProductPropertyTable();

		JPanel basePanel = new JPanel();
		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.getViewport().add(propertyTable);
		basePanel.setLayout(new BorderLayout());
		basePanel.add(tableScrollPane, BorderLayout.CENTER);

		return basePanel;

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

				FutureProduct futuresProduct = new FutureProduct();

				futuresProduct.setID(1);
				// futuresProduct.setID(Integer.parseInt(propertyTable.getValueAt(1,
				// 1).toString()));
				futuresProduct.setSettlement_currency(propertyTable.getValueAt(
						2, 1).toString());
				futuresProduct.setUnderlying_productID(1);
				// futuresProduct.setUnderlying_productID(Integer.parseInt(propertyTable.getValueAt(4,
				// 1).toString()));
				// futuresProduct.setCcyPair(propertyTable.getValueAt(6,
				// 1).toString());
				futuresProduct.setQuote_type(propertyTable.getValueAt(7, 1)
						.toString());
				futuresProduct.setQuote_decimals(Double
						.parseDouble(propertyTable.getValueAt(8, 1).toString()));
				futuresProduct.setContract_size(Integer.parseInt(propertyTable
						.getValueAt(9, 1).toString()));
				futuresProduct.setLots(Integer.parseInt(propertyTable
						.getValueAt(10, 1).toString()));
				futuresProduct.setTicksize(Integer.parseInt(propertyTable
						.getValueAt(11, 1).toString()));
				futuresProduct.setTradecontract_no(Integer
						.parseInt((propertyTable.getValueAt(12, 1).toString())));
				futuresProduct.setSettlement_method(propertyTable.getValueAt(
						13, 1).toString());
				futuresProduct.setExpiry_Date_Rule(propertyTable.getValueAt(14,
						1).toString());

				// futuresProduct.setTime_zone(propertyTable.getValueAt(15,
				// 1).toString());
				// futuresProduct.setTime_minute(propertyTable.getValueAt(16,
				// 1).toString());
				futuresProduct.setLast_Trading_rule(propertyTable.getValueAt(
						17, 1).toString());
				futuresProduct.setFirst_delivery_Trading_rule(propertyTable
						.getValueAt(18, 1).toString());
				futuresProduct.setLast_delivery_Trading_rule(propertyTable
						.getValueAt(19, 1).toString());
				futuresProduct.setFirst__notification_rule(propertyTable
						.getValueAt(20, 1).toString());
				futuresProduct.setLast_notification_rule(propertyTable
						.getValueAt(21, 1).toString());

				/*
				 * futuresProduct.setLastTradingDay(Integer.parseInt((propertyTable
				 * .getValueAt(17, 1).toString())));
				 * futuresProduct.setLastTradingTime
				 * (propertyTable.getValueAt(18, 1).toString());
				 * futuresProduct.setDailySettlementType
				 * (propertyTable.getValueAt(19, 1).toString());
				 * futuresProduct.setFinalSettlementType
				 * (propertyTable.getValueAt(20, 1).toString());
				 * futuresProduct.setProductAttributes
				 * (propertyTable.getValueAt(22, 1).toString());
				 */

				RemoteProduct remoteProduct = RemoteServiceUtil
						.getRemoteProductService();

				int id = 0;
				try {
					id = remoteProduct.saveFutureProduct(futuresProduct);
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				if (id > 0) {

					commonUTIL.showAlertMessage("Future Product Saved");

				} else {

					commonUTIL
							.showAlertMessage("There was an error while saving Future Product");

				}

				propertyTable.getValueAt(1, 1);
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

	public static void main(String args[]) {
		ContractDefinitionWindow c1 = new ContractDefinitionWindow();
		c1.setSize(900, 900);
		c1.setVisible(true);
	}

	protected void jbInit() throws Exception {
		// super();
		createLayout();
		setupMainComponents();
	}

	private void setupContractStatusBar() {
		contractStatusBar.add(contractLabel);
		if (contractSelectorComboBox == null) {
			contractSelectorComboBox = new ContractSelectorComboBox(
					DISPLAYABLEOBJECT);
		}
		contractSelectorComboBox
				.addActionListener(getContractSelectorComboBoxActionListener());
		contractSelectorComboBox.setPreferredSize(new Dimension(200,
				contractSelectorComboBox.getPreferredSize().height));
		contractStatusBar.add(contractSelectorComboBox);
		// contractStatusBar.add(futureButtons.get(FutureButtonType.NEW));
	}

}
