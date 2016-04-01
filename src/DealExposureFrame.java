import java.awt.Button;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DealExposureFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static ObjectOutputStream out;
	private static HttpURLConnection conn;

	// Labels

	private JLabel _crnLabel;
	private JLabel _dealDirectionLabel;
	private JLabel _ccy1Label;
	private JLabel _ccy2Label;
	private JLabel _typeOfDealLabel;
	private JLabel _amt1Label;
	private JLabel _amt2Label;
	private JLabel _valueDateLabel;
	private JLabel _eventLabel;
	private JLabel _limitsLabel;
	private JLabel _remarksLabel;
	private JLabel _nominalLabel;
	private JLabel _nominalPerLabel;
	private JLabel _nominalCCYLabel;
	private JLabel _rateLabel;
	private JLabel _rateCCYPairLabel;
	private JLabel _exposureAmtLabel;
	private JLabel _exposureAmtCCYLabel;
	private JLabel _balancePerLabel;
	private JLabel _exposureLabel;
	private JLabel _balanceLabel;

	// TextFields
	public JTextField _crnTextField;
	public JTextField _ccy1TextField;
	public JTextField _ccy2TextField;
	private JTextField _valueDateTextField;
	public JTextField _amt1TextField;
	public JTextField _amt2TextField;
	private JTextField _remarksTextField;
	private JTextField _nominalAmtTextField;
	private JTextField _nominalPerTextField;
	private JTextField _RateTextField;
	private JTextField _exposureAmtTextField;
	private JTextField _balanceTextField;
	private JTextField _balancePerTextField;

	// ComboBoxes
	public JComboBox _eventComboBox;
	public JComboBox _dealTypeComboBox;
	public JComboBox _dealDirComboBox;

	// Panels
	private JPanel _limitPanel;
	private JPanel _dealDetailsPanel;
	private JPanel _exposurePanel;
	private JPanel _okClosePanel;

	// Tables
	private JTable _limitsTable;
	private JTable _exposureTable;

	// Display Table

	// Buttons
	private Button _loadLimitButton;
	private Button _loadRateButton;
	private Button _addExposureButton;
	private Button _removeButton;
	private Button _removeAllButton;
	private Button _closeButton;
	private Button _okButton;

	// Separators
	private JSeparator _dealDetailSeparator;
	private JSeparator _limitSeparator;
	private JSeparator _exposureSeparator;

	// ScrollPanel
	private JScrollPane _exposureTableScrollPanel;
	private JScrollPane _limitTableScrollPanel;

	// CheckBoxes
	private JCheckBox _exceptionCheckBox;

	// Flags
	boolean isLimitTableRowSelected = false;
	boolean isExposureTableRowSelected = false;
	ArrayList<Object[]> tableData = new ArrayList<Object[]>();
	Object[][] tableDataObject = null;
	ArrayList<Object[]> exposureTableData = new ArrayList<Object[]>();
	Object[][] exposureTableDataObject = null;
	HashMap<String, Object> limitTableRowValues = new HashMap<String, Object>();
	HashMap<String, Object> exposureTableRowValues = new HashMap<String, Object>();

	TableModel exposureTableModel = null;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";

	public DealExposureFrame() {

		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		ImageIcon img = new ImageIcon("KotakLogo.png");
		setIconImage(img.getImage());
		setTitle("Deal Exposure Window");
		setResizable(false);
		setLayout(null);
		add(get_dealDetailSeparator());
		add(get_dealDetailsPanel());
		add(get_limitPanel());
		add(get_limitSeparator());
		add(get_okClosePanel());
		add(get_exposureSeparator());
		add(get_exposurePanel());
		getContentPane().setBackground(getColors());
		setSize(695, 609);
	}

	public static void showFrame(String plaf) {
		try {
			UIManager.setLookAndFeel(plaf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JFrame f = new JFrame(plaf);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		f.setSize(400, 100);
		f.setLocationByPlatform(true);
		f.setDefaultLookAndFeelDecorated(true);
		f.setVisible(true);
	}

	private Button get_okButton() {
		if (_okButton == null) {
			_okButton = new Button();
			_okButton.setLabel("OK");
			_okButton.setBounds(538, 5, 51, 24);
			// _okButton.setBackground(getColors());
			_okButton.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent event) {
					_okButtonMouseMouseClicked(event);
				}
			});
		}
		return _okButton;
	}

	private Button get_closeButton() {
		if (_closeButton == null) {
			_closeButton = new Button();
			_closeButton.setLabel("Close");
			_closeButton.setBounds(594, 5, 68, 24);
			// _closeButton.setBackground(getColors());
			_closeButton.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent event) {
					_closeButtonMouseMouseClicked(event);
				}
			});
		}
		return _closeButton;
	}

	private JPanel get_okClosePanel() {
		if (_okClosePanel == null) {
			_okClosePanel = new JPanel();
			_okClosePanel.setBounds(11, 547, 669, 34);
			_okClosePanel.setBackground(getColors());
			_okClosePanel.setLayout(null);
			_okClosePanel.add(get_closeButton());
			_okClosePanel.add(get_okButton());
		}
		return _okClosePanel;
	}

	private JSeparator get_exposureSeparator() {
		if (_exposureSeparator == null) {
			_exposureSeparator = new JSeparator();
			_exposureSeparator.setBounds(6, 546, 675, 10);
		}
		return _exposureSeparator;
	}

	private Button get_removeAllButton() {
		if (_removeAllButton == null) {
			_removeAllButton = new Button();
			_removeAllButton.setLabel("Remove All");
			_removeAllButton.setBounds(560, 125, 105, 24);
			// _removeAllButton.setBackground(getColors());
			_removeAllButton.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent event) {
					_removeAllButtonMouseMouseClicked(event);
				}
			});
		}
		return _removeAllButton;
	}

	private Button get_removeButton() {
		if (_removeButton == null) {
			_removeButton = new Button();
			_removeButton.setLabel("Remove");
			_removeButton.setBounds(465, 125, 80, 24);
			// _removeButton.setBackground(getColors());
			_removeButton.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent event) {
					_removeButtonMouseMouseClicked(event);
				}
			});
		}
		return _removeButton;
	}

	private JTextField get_balancePerTextField() {
		if (_balancePerTextField == null) {
			_balancePerTextField = new JTextField();
			_balancePerTextField.setText("1.2");
			_balancePerTextField.setBounds(196, 125, 50, 20);
		}
		return _balancePerTextField;
	}

	private JLabel get_balancePerLabel() {
		if (_balancePerLabel == null) {
			_balancePerLabel = new JLabel();
			_balancePerLabel.setText("%");
			_balancePerLabel.setBounds(184, 125, 15, 20);
		}
		return _balancePerLabel;
	}

	private JTextField get_balanceTextField() {
		if (_balanceTextField == null) {
			_balanceTextField = new JTextField();
			_balanceTextField.setText("4.2");
			_balanceTextField.setBounds(82, 125, 100, 20);
		}
		return _balanceTextField;
	}

	private JLabel get_balanceLabel() {
		if (_balanceLabel == null) {
			_balanceLabel = new JLabel();
			_balanceLabel.setText("Balance :");
			_balanceLabel.setBounds(21, 125, 59, 20);
		}
		return _balanceLabel;
	}

	private JScrollPane get_exposureTableScrollPanel() {
		if (_exposureTableScrollPanel == null) {
			_exposureTableScrollPanel = new JScrollPane();
			_exposureTableScrollPanel.setBounds(83, 5, 589, 111);

			_exposureTableScrollPanel.setViewportView(get_exposureTable());
			_exposureTableScrollPanel.getViewport().setBackground(getColors());
		}
		return _exposureTableScrollPanel;
	}

	private JTable get_exposureTable() {
		if (_exposureTable == null) {
			_exposureTable = new JTable();
			_exposureTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			_exposureTable
					.setModel(new DefaultTableModel(new Object[][] {},
							new String[] { "ExposureID", "Limit ID",
									"NominalAmt", "NominalCCY", "ExposureAmt",
									"ExposureCCY", "Rate" }) {
						private static final long serialVersionUID = 1L;
						Class<?>[] types = new Class<?>[] { Object.class,
								Object.class, Object.class, Object.class,
								Object.class, Object.class, Object.class, };

						public Class<?> getColumnClass(int columnIndex) {
							return types[columnIndex];
						}
					});

			_exposureTable.getColumnModel().getColumn(0).setPreferredWidth(80);
			_exposureTable.getColumnModel().getColumn(1).setPreferredWidth(70);
			_exposureTable.getColumnModel().getColumn(2).setPreferredWidth(100);
			_exposureTable.getColumnModel().getColumn(3).setPreferredWidth(80);
			_exposureTable.getColumnModel().getColumn(4).setPreferredWidth(100);
			_exposureTable.getColumnModel().getColumn(5).setPreferredWidth(80);
			_exposureTable.getColumnModel().getColumn(6).setPreferredWidth(80);

			_exposureTable.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent event) {
					_exposureTableMouseMouseClicked(event);
				}
			});
		}
		return _exposureTable;
	}

	private JLabel get_exposureLabel() {
		if (_exposureLabel == null) {
			_exposureLabel = new JLabel();
			_exposureLabel.setText("Exposures :");
			_exposureLabel.setBounds(10, 1, 67, 20);
		}
		return _exposureLabel;
	}

	private JPanel get_exposurePanel() {
		if (_exposurePanel == null) {
			_exposurePanel = new JPanel();
			_exposurePanel.setBounds(11, 385, 679, 158);
			_exposurePanel.setBackground(getColors());
			_exposurePanel.setLayout(null);
			_exposurePanel.add(get_exposureLabel());
			_exposurePanel.add(get_balanceLabel());
			_exposurePanel.add(get_balanceTextField());
			_exposurePanel.add(get_balancePerLabel());
			_exposurePanel.add(get_balancePerTextField());
			_exposurePanel.add(get_removeButton());
			_exposurePanel.add(get_removeAllButton());
			_exposurePanel.add(get_exposureTableScrollPanel());
		}
		return _exposurePanel;
	}

	private JSeparator get_limitSeparator() {
		if (_limitSeparator == null) {
			_limitSeparator = new JSeparator();
			_limitSeparator.setBounds(5, 380, 675, 10);
		}
		return _limitSeparator;
	}

	private JLabel get_exposureAmtCCYLabel() {
		if (_exposureAmtCCYLabel == null) {
			_exposureAmtCCYLabel = new JLabel();
			// _exposureAmtCCYLabel.setText("USD");
			_exposureAmtCCYLabel.setBounds(186, 199, 35, 20);
			_exposureAmtCCYLabel.setVisible(false);
		}
		return _exposureAmtCCYLabel;
	}

	private JTextField get_exposureAmtTextField() {
		if (_exposureAmtTextField == null) {
			_exposureAmtTextField = new JTextField();
			_exposureAmtTextField.setText("0.0");
			_exposureAmtTextField.setBounds(70, 199, 110, 20);
			_exposureAmtTextField.setEditable(false);
		}
		return _exposureAmtTextField;
	}

	private JLabel get_exposureAmtLabel() {
		if (_exposureAmtLabel == null) {
			_exposureAmtLabel = new JLabel();
			_exposureAmtLabel.setText("Exp Amt :");
			_exposureAmtLabel.setBounds(8, 201, 55, 20);
		}
		return _exposureAmtLabel;
	}

	private Button get_addExposureButton() {
		if (_addExposureButton == null) {
			_addExposureButton = new Button();
			_addExposureButton.setLabel("Add");
			_addExposureButton.setBounds(530, 172, 60, 20);
			// _addExposureButton.setBackground(getColors());
			_addExposureButton.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent event) {
					_addExposureButtonMouseMouseClicked(event);
				}
			});
		}
		return _addExposureButton;
	}

	private Button get_loadRateButton() {
		if (_loadRateButton == null) {
			_loadRateButton = new Button();
			_loadRateButton.setLabel("...");
			// _loadRateButton.setBounds(455, 172, 30, 20);
			// _loadRateButton.setBackground(getColors());
		}
		return _loadRateButton;
	}

	private JLabel get_rateCCYPairLabel() {
		if (_rateCCYPairLabel == null) {
			_rateCCYPairLabel = new JLabel();
			_rateCCYPairLabel.setBounds(389, 172, 62, 20);
			_rateCCYPairLabel.setVisible(false);
		}
		return _rateCCYPairLabel;
	}

	private JTextField get_RateTextField() {
		if (_RateTextField == null) {
			_RateTextField = new JTextField();
			_RateTextField.setText("0.0");
			_RateTextField.setBounds(322, 172, 65, 20);
			_RateTextField.addFocusListener(new FocusAdapter() {

				public void focusLost(FocusEvent event) {
					_RateTextFieldFocusFocusLost(event);
				}
			});
		}
		return _RateTextField;
	}

	private JLabel get_rateLabel() {
		if (_rateLabel == null) {
			_rateLabel = new JLabel();
			_rateLabel.setText("Rate :");
			_rateLabel.setBounds(288, 172, 40, 20);
		}
		return _rateLabel;
	}

	private JLabel get_nominalCCYLabel() {
		if (_nominalCCYLabel == null) {
			_nominalCCYLabel = new JLabel();
			_nominalCCYLabel.setText("USD");
			_nominalCCYLabel.setBounds(249, 172, 27, 20);
		}
		return _nominalCCYLabel;
	}

	private JTextField get_nominalPerTextField() {
		if (_nominalPerTextField == null) {
			_nominalPerTextField = new JTextField();
			_nominalPerTextField.setText("100.0");
			_nominalPerTextField.setBounds(206, 172, 42, 20);
			_nominalPerTextField.addFocusListener(new FocusAdapter() {

				public void focusLost(FocusEvent event) {
					_nominalPerTextFieldFocusFocusLost(event);
				}
			});
		}
		return _nominalPerTextField;
	}

	private JLabel get_nominalPerLabel() {
		if (_nominalPerLabel == null) {
			_nominalPerLabel = new JLabel();
			_nominalPerLabel.setText("%");
			_nominalPerLabel.setBounds(193, 172, 15, 20);
		}
		return _nominalPerLabel;
	}

	private JTextField get_nominalAmtTextField() {
		if (_nominalAmtTextField == null) {
			_nominalAmtTextField = new JTextField();
			_nominalAmtTextField.setText(_amt1TextField.getText());
			_nominalAmtTextField.setEditable(false);
			_nominalAmtTextField.setBounds(70, 172, 120, 20);
		}
		return _nominalAmtTextField;
	}

	private JLabel get_nominalLabel() {
		if (_nominalLabel == null) {
			_nominalLabel = new JLabel();
			_nominalLabel.setText("Nominal :");
			_nominalLabel.setBounds(10, 170, 54, 20);
		}
		return _nominalLabel;
	}

	private JTextField get_remarksTextField() {
		if (_remarksTextField == null) {
			_remarksTextField = new JTextField();
			_remarksTextField.setEditable(false);
			_remarksTextField.setText("Editable False");
			_remarksTextField.setBounds(73, 143, 585, 20);
		}
		return _remarksTextField;
	}

	private JLabel get_remarksLabel() {
		if (_remarksLabel == null) {
			_remarksLabel = new JLabel();
			_remarksLabel.setText("Remarks :");
			_remarksLabel.setBounds(8, 143, 70, 20);
		}
		return _remarksLabel;
	}

	private JCheckBox get_exceptionCheckBox() {
		if (_exceptionCheckBox == null) {
			_exceptionCheckBox = new JCheckBox();
			_exceptionCheckBox.setText("Exception");
			_exceptionCheckBox.setBounds(58, 114, 93, 20);
			_exceptionCheckBox.setBackground(getColors());
		}
		return _exceptionCheckBox;
	}

	private JScrollPane get_limitTableScrollPanel() {
		if (_limitTableScrollPanel == null) {
			_limitTableScrollPanel = new JScrollPane();
			_limitTableScrollPanel.setBounds(51, 9, 612, 97);
			_limitTableScrollPanel.setViewportView(get_limitsTable());
			_limitTableScrollPanel.getViewport().setBackground(getColors());
		}
		return _limitTableScrollPanel;
	}

	private JTable get_limitsTable() {
		if (_limitsTable == null) {
			_limitsTable = new JTable();
			_limitsTable.setOpaque(false);
			_limitsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			_limitsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			_limitsTable.setModel(new DefaultTableModel(tableDataObject,
					new String[] { "LimitID", "LimType", "LimCode", "LimCCY",
							"TotalLimit", "AvailableLimit", "CancelledLimit",
							"StartDate", "EndDate" }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class,
						Object.class, Object.class, Object.class, Object.class,
						Object.class, Object.class, Object.class, };

				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
			_limitsTable.getColumnModel().getColumn(0).setPreferredWidth(50);
			_limitsTable.getColumnModel().getColumn(1).setPreferredWidth(60);
			_limitsTable.getColumnModel().getColumn(2).setPreferredWidth(80);
			_limitsTable.getColumnModel().getColumn(3).setPreferredWidth(55);
			_limitsTable.getColumnModel().getColumn(4).setPreferredWidth(100);
			_limitsTable.getColumnModel().getColumn(5).setPreferredWidth(100);
			_limitsTable.getColumnModel().getColumn(6).setPreferredWidth(100);
			_limitsTable.getColumnModel().getColumn(7).setPreferredWidth(80);
			_limitsTable.getColumnModel().getColumn(8).setPreferredWidth(80);

			_limitsTable.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent event) {
					_limitsTableMouseMouseClicked(event);
				}
			});
		}
		return _limitsTable;
	}

	// _limitsTable.setModel(new DefaultTableModel(new Object[][] { { "0x0",
	// "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", })
	private JLabel get_limitsLabel() {
		if (_limitsLabel == null) {
			_limitsLabel = new JLabel();
			_limitsLabel.setText("Limits :");
			_limitsLabel.setBounds(4, 3, 46, 20);
		}
		return _limitsLabel;
	}

	private JPanel get_limitPanel() {
		if (_limitPanel == null) {
			_limitPanel = new JPanel();
			_limitPanel.setBounds(11, 149, 676, 222);
			_limitPanel.setLayout(null);
			_limitPanel.setBackground(getColors());
			_limitPanel.add(get_addExposureButton());
			_limitPanel.add(get_exposureAmtLabel());
			_limitPanel.add(get_exposureAmtTextField());
			_limitPanel.add(get_exceptionCheckBox());
			_limitPanel.add(get_remarksLabel());
			_limitPanel.add(get_remarksTextField());
			_limitPanel.add(get_nominalLabel());
			_limitPanel.add(get_limitsLabel());
			_limitPanel.add(get_nominalAmtTextField());
			_limitPanel.add(get_nominalPerLabel());
			_limitPanel.add(get_rateLabel());
			_limitPanel.add(get_RateTextField());
			_limitPanel.add(get_rateCCYPairLabel());
			_limitPanel.add(get_loadRateButton());
			_limitPanel.add(get_exposureAmtCCYLabel());
			_limitPanel.add(get_nominalCCYLabel());
			_limitPanel.add(get_nominalPerTextField());
			_limitPanel.add(get_limitTableScrollPanel());
		}
		return _limitPanel;
	}

	private Color getColors() {
		// TODO Auto-generated method stub
		return new Color(232, 230, 215);
	}

	private JSeparator get_dealDetailSeparator() {
		if (_dealDetailSeparator == null) {
			_dealDetailSeparator = new JSeparator();
			_dealDetailSeparator.setBounds(5, 140, 675, 10);
		}
		return _dealDetailSeparator;
	}

	private Button get_loadLimitButton() {
		if (_loadLimitButton == null) {
			_loadLimitButton = new Button();
			_loadLimitButton.setLabel("Load");
			_loadLimitButton.setBounds(576, 91, 69, 24);
			// _loadLimitButton.setBackground(getColors());
			_loadLimitButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent event) {
					_loadLimitButtonMouseMouseClicked(event);
				}
			});
		}
		return _loadLimitButton;
	}

	private Color getButtonColor() {
		// TODO Auto-generated method stub
		return new Color(182, 182, 176);
	}

	private JComboBox get_eventComboBox() {
		if (_eventComboBox == null) {
			_eventComboBox = new JComboBox();
			_eventComboBox.setModel(new DefaultComboBoxModel(new Object[] {
					"NEW", "MRET", "HRET", "HRE" }));
			_eventComboBox.setDoubleBuffered(false);
			_eventComboBox.setBorder(null);
			_eventComboBox.setBounds(460, 10, 120, 20);
			_eventComboBox.setBackground(new Color(255, 255, 255));
		}
		return _eventComboBox;
	}

	private JLabel get_eventLabel() {
		if (_eventLabel == null) {
			_eventLabel = new JLabel();
			_eventLabel.setText("Event :");
			_eventLabel.setBounds(420, 10, 40, 20);
		}
		return _eventLabel;
	}

	private JTextField get_amt2TextField() {
		if (_amt2TextField == null) {
			_amt2TextField = new JTextField();
			_amt2TextField.setText("452000");
			_amt2TextField.setBounds(260, 100, 120, 20);
		}
		return _amt2TextField;
	}

	private JTextField get_amt1TextField() {
		if (_amt1TextField == null) {
			_amt1TextField = new JTextField();
			_amt1TextField.setText("55000");
			_amt1TextField.setBounds(260, 70, 120, 20);
		}
		return _amt1TextField;
	}

	private JTextField get_valueDateTextField() {
		if (_valueDateTextField == null) {
			_valueDateTextField = new JTextField();
			_valueDateTextField.setText("24/04/2013");
			_valueDateTextField.setBounds(260, 40, 120, 20);
		}
		return _valueDateTextField;
	}

	private JLabel get_valueDateLabel() {
		if (_valueDateLabel == null) {
			_valueDateLabel = new JLabel();
			_valueDateLabel.setText("Val Dt :");
			_valueDateLabel.setBounds(215, 40, 40, 20);
		}
		return _valueDateLabel;
	}

	private JLabel get_amt2Label() {
		if (_amt2Label == null) {
			_amt2Label = new JLabel();
			_amt2Label.setText("Amt2 :");
			_amt2Label.setBounds(220, 100, 40, 20);
		}
		return _amt2Label;
	}

	private JLabel get_amt1Label() {
		if (_amt1Label == null) {
			_amt1Label = new JLabel();
			_amt1Label.setText("Amt1 :");
			_amt1Label.setBounds(220, 70, 40, 20);
		}
		return _amt1Label;
	}

	private JComboBox get_dealTypeComboBox() {
		if (_dealTypeComboBox == null) {
			_dealTypeComboBox = new JComboBox();
			_dealTypeComboBox.setModel(new DefaultComboBoxModel(new Object[] {
					"OUTRIGHT", "SWAP" }));
			_dealTypeComboBox.setDoubleBuffered(false);
			_dealTypeComboBox.setBorder(null);
			_dealTypeComboBox.setBounds(260, 10, 120, 20);
			_dealTypeComboBox.setBackground(new Color(255, 255, 255));
		}
		return _dealTypeComboBox;
	}

	private JLabel get_typeOfDealLabel() {
		if (_typeOfDealLabel == null) {
			_typeOfDealLabel = new JLabel();
			_typeOfDealLabel.setText("Type :");
			_typeOfDealLabel.setBounds(220, 10, 40, 20);
		}
		return _typeOfDealLabel;
	}

	private JTextField get_ccy1TextField() {
		if (_ccy1TextField == null) {
			_ccy1TextField = new JTextField();
			_ccy1TextField.setText("USD");
			_ccy1TextField.setBounds(55, 70, 120, 20);
		}
		return _ccy1TextField;
	}

	private JTextField get_ccy2TextField() {
		if (_ccy2TextField == null) {
			_ccy2TextField = new JTextField();
			_ccy2TextField.setText("INR");
			_ccy2TextField.setBounds(55, 100, 120, 20);
		}
		return _ccy2TextField;
	}

	private JLabel get_ccy2Label() {
		if (_ccy2Label == null) {
			_ccy2Label = new JLabel();
			_ccy2Label.setText("Ccy2 :");
			_ccy2Label.setBounds(15, 100, 40, 16);
		}
		return _ccy2Label;
	}

	private JLabel get_ccy1Label() {
		if (_ccy1Label == null) {
			_ccy1Label = new JLabel();
			_ccy1Label.setText("Ccy1 :");
			_ccy1Label.setBounds(15, 70, 40, 16);
		}
		return _ccy1Label;
	}

	private JComboBox get_dealDirComboBox() {
		if (_dealDirComboBox == null) {
			_dealDirComboBox = new JComboBox();
			_dealDirComboBox.setModel(new DefaultComboBoxModel(new Object[] {
					"BUY", "SELL" }));
			_dealDirComboBox.setDoubleBuffered(false);
			_dealDirComboBox.setBorder(null);
			_dealDirComboBox.setBounds(55, 40, 120, 20);
			_dealDirComboBox.setBackground(new Color(255, 255, 255));
		}
		return _dealDirComboBox;
	}

	private JLabel get_dealDirectionLabel() {
		if (_dealDirectionLabel == null) {
			_dealDirectionLabel = new JLabel();
			_dealDirectionLabel.setText("Dirn :");
			_dealDirectionLabel.setBounds(20, 40, 40, 16);
		}
		return _dealDirectionLabel;
	}

	private JTextField get_crnTextField() {
		if (_crnTextField == null) {
			_crnTextField = new JTextField();
			_crnTextField.setText("110011");
			_crnTextField.setBounds(55, 10, 120, 20);
		}
		return _crnTextField;
	}

	private JLabel get_crnLabel() {
		if (_crnLabel == null) {
			_crnLabel = new JLabel();
			_crnLabel.setText("CRN :");
			_crnLabel.setBounds(20, 10, 40, 20);
		}
		return _crnLabel;
	}

	private JPanel get_dealDetailsPanel() {
		if (_dealDetailsPanel == null) {
			_dealDetailsPanel = new JPanel();
			_dealDetailsPanel.setBounds(8, 11, 677, 128);
			_dealDetailsPanel.setBackground(getColors());
			_dealDetailsPanel.setLayout(null);
			_dealDetailsPanel.add(get_crnLabel());
			_dealDetailsPanel.add(get_dealDirectionLabel());
			_dealDetailsPanel.add(get_dealDirComboBox());
			_dealDetailsPanel.add(get_ccy2Label());
			_dealDetailsPanel.add(get_ccy2TextField());
			_dealDetailsPanel.add(get_ccy1TextField());
			_dealDetailsPanel.add(get_crnTextField());
			_dealDetailsPanel.add(get_typeOfDealLabel());
			_dealDetailsPanel.add(get_dealTypeComboBox());
			_dealDetailsPanel.add(get_amt1Label());
			_dealDetailsPanel.add(get_amt2Label());
			_dealDetailsPanel.add(get_valueDateTextField());
			_dealDetailsPanel.add(get_amt1TextField());
			_dealDetailsPanel.add(get_amt2TextField());
			_dealDetailsPanel.add(get_eventLabel());
			_dealDetailsPanel.add(get_loadLimitButton());
			_dealDetailsPanel.add(get_ccy1Label());
			_dealDetailsPanel.add(get_valueDateLabel());
			_dealDetailsPanel.add(get_eventComboBox());
		}
		return _dealDetailsPanel;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class. Note: This class is only created so that you can
	 * easily preview the result at runtime. It is not expected to be managed by
	 * the designer. You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DealExposureFrame frame = new DealExposureFrame();
				frame.setDefaultCloseOperation(DealExposureFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	private void _limitsTableMouseMouseClicked(MouseEvent event) {

		JTable limitTable = (JTable) event.getSource();
		int selection = limitTable.getSelectedRow();
		System.out.println("Totla Tows " + limitTable.getSelectedRows().length);

		System.out.println("selected Row :" + (selection + 1));
		System.out.println("total cols :" + limitTable.getColumnCount());
		for (int i = 0; i < limitTable.getColumnCount(); i++) {
			limitTableRowValues.put(limitTable.getColumnName(i), limitTable
					.getValueAt(selection, i));
			System.out.println(limitTable.getValueAt(selection, i));
		}
		_rateCCYPairLabel.setText(_ccy1TextField.getText() + "/"
				+ limitTableRowValues.get("LimCCY"));
		_rateCCYPairLabel.setVisible(true);
		_exposureAmtCCYLabel
				.setText((String) limitTableRowValues.get("LimCCY"));
		_exposureAmtCCYLabel.setVisible(true);
		isLimitTableRowSelected = true;

	}

	private void _loadLimitButtonMouseMouseClicked(MouseEvent event) {
		tableData.removeAll(tableData);

		String limitTobeFetched = getLimitTypeToBeFetched(_ccy1TextField
				.getText(), _ccy2TextField.getText(), _dealDirComboBox
				.getSelectedItem().toString());
		String limitServletOutput = callLimitServlet(_crnTextField.getText(),
				_ccy1TextField.getText(), "Limit");

		System.out.println("Output from Lim Servlet :" + limitServletOutput);
		if (limitServletOutput != null && !limitServletOutput.isEmpty()) {
			String[] limitRows = limitServletOutput.split("~");
			DefaultTableModel dtm = (DefaultTableModel) _limitsTable.getModel();
			tableData.removeAll(tableData);
			for (int i = 0; i < limitRows.length; i++) {
				if (limitRows[i] != null && !limitRows[i].isEmpty()) {
					tableData.add(limitRows[i].split(","));
				}
			}
			isExposureTableRowSelected = false;

			// tableData.add(data1.split(","));
			tableDataObject = new Object[tableData.size()][];
			tableData.toArray(tableDataObject);
			_limitTableScrollPanel.remove(_limitsTable);
			_rateCCYPairLabel.setVisible(false);
			_exposureAmtCCYLabel.setVisible(false);
			_RateTextField.setText("0.0");
			_exposureAmtTextField.setText("0.0");
			isLimitTableRowSelected = false;
			exposureTableData.removeAll(exposureTableData);
			_limitsTable.setModel(new DefaultTableModel(tableDataObject,
					new String[] { "LimitID", "LimType", "LimCode", "LimCCY",
							"TotalLimit", "AvailableLimit", "CancelledLimit",
							"StartDate", "EndDate" }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class,
						Object.class, Object.class, Object.class, Object.class,
						Object.class, Object.class, Object.class, };

				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
			_limitsTable.getColumnModel().getColumn(0).setPreferredWidth(50);
			_limitsTable.getColumnModel().getColumn(1).setPreferredWidth(60);
			_limitsTable.getColumnModel().getColumn(2).setPreferredWidth(80);
			_limitsTable.getColumnModel().getColumn(3).setPreferredWidth(55);
			_limitsTable.getColumnModel().getColumn(4).setPreferredWidth(100);
			_limitsTable.getColumnModel().getColumn(5).setPreferredWidth(100);
			_limitsTable.getColumnModel().getColumn(6).setPreferredWidth(100);
			_limitsTable.getColumnModel().getColumn(7).setPreferredWidth(80);
			_limitsTable.getColumnModel().getColumn(8).setPreferredWidth(80);
		} else {
			showMessage("No limits Found.");
		}
	}

	private String getLimitTypeToBeFetched(String ccy1, String ccy2,
			String dealDir) {
		String typeToBeFetched = "";
		if ((ccy1.equalsIgnoreCase("USD") && ccy2.equalsIgnoreCase("INR"))
				|| (ccy2.equalsIgnoreCase("USD") && ccy1
						.equalsIgnoreCase("INR"))) {
			typeToBeFetched = "ALL";
		} else if (!ccy1.equalsIgnoreCase("INR")
				&& !ccy2.equalsIgnoreCase("INR")) {
			typeToBeFetched = "BOTH";
		} else if (!ccy1.equalsIgnoreCase("INR")
				&& dealDir.equalsIgnoreCase("BUY")) {
			typeToBeFetched = "IMPORT";

		} else if (!ccy1.equalsIgnoreCase("INR")
				&& dealDir.equalsIgnoreCase("SELL")) {
			typeToBeFetched = "EXPORT";
		}
		return typeToBeFetched;
	}

	private String callLimitServlet(String serCRN, String serCCY,
			String RequestCode) {
		String result = "";// Initialise output variable
		try {// Location of the servlet with DNS name scheme
			// String servletURL =
			// "https://fxlive.devl.kotak.int:6443/FinServlet/CallServlet";
			String serverPath = "https://fxlive.devl.kotak.int:6443";
			String userName_ = "Shaktimaan";
			String User_Group = "AutoTrader";
			if (serverPath != null && !serverPath.isEmpty()
					&& !serverPath.equalsIgnoreCase("")) {
				String servletURL = serverPath + "/LimServlet/LimitServlet";
				/*
				 * System.out.println("serverpath :" + serverPath + " complete
				 * path:" + servletURL);// remove it
				 *//*
					 * Preparing request query for servlet passing various
					 * parameters to servlet
					 */
				String query = "CRN=" + serCRN + "&CCY=" + serCCY
						+ "&RequestCode=" + RequestCode + "&LoggedUser="
						+ userName_ + "&LoggedUserGroup=" + User_Group;
				String charEncoding = "iso-8859-1";
				/* System.out.println("Query " + query); */

				URL sendUrl = new URL(servletURL);
				// Establishing the connection with Servlet
				HttpURLConnection conn = (HttpURLConnection) sendUrl
						.openConnection();

				conn.setDoOutput(true);// Output Stream Open
				conn.setRequestMethod("POST");// Using POST method
				conn.setRequestProperty("Content-Length", Integer
						.toString(query.length()));

				conn.getOutputStream().write(query.getBytes(charEncoding));

				/*
				 * out = (ObjectOutputStream) conn.getOutputStream();
				 * out.writeObject(names);
				 */
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				// Reading the ouput received from servlet
				result = in.readLine();

			} else {
				System.out
						.println("TakerCustom_Clients_All::CallServlet::Error Fetching Server Path");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			if (RequestCode.equalsIgnoreCase("GlobalParameter")) {
				showMessage("Exception fetching parameters. Please send java console log to support team.");
			}

			System.out
					.println("TakerCustom_Clients_All::CallServlet::Exception : "
							+ e1.getMessage());
		}
		return result; // returning the output
	}

	private void _nominalPerTextFieldFocusFocusLost(FocusEvent event) {
		if (isLimitTableRowSelected) {
			double nomPer = Double.valueOf(_nominalPerTextField.getText());
			if (nomPer <= 100.0) {
				_exposureAmtTextField.setText(String
						.valueOf(Double.valueOf(_nominalAmtTextField.getText())
								* (Double.valueOf(_nominalPerTextField
										.getText()) / 100.0)
								* Double.valueOf(_RateTextField.getText())));
			} else {
				showMessage("Nominal percentage cannot be more than 100 %");
				_exposureAmtTextField.setText("0.0");
				_nominalPerTextField.setText("100.0");
			}
		} else {
			showMessage("Please select a Limit to create Exposure");
			_nominalPerTextField.setText("100.0");
		}
	}

	private void _RateTextFieldFocusFocusLost(FocusEvent event) {
		if (isLimitTableRowSelected) {
			_exposureAmtTextField.setText(String.valueOf(Double
					.valueOf(_nominalAmtTextField.getText())
					* (Double.valueOf(_nominalPerTextField.getText()) / 100)
					* Double.valueOf(_RateTextField.getText())));
		} else {
			showMessage("Please select a Limit to create Exposure");
			_RateTextField.setText("0.0");
		}
	}

	private void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	private void _addExposureButtonMouseMouseClicked(MouseEvent event) {

		if (Double.valueOf(_exposureAmtTextField.getText()) > 0) {
			createExposure(exposureTableData);
			exposureTableDataObject = new Object[exposureTableData.size()][];
			exposureTableData.toArray(exposureTableDataObject);

			_exposureTable
					.setModel(getExposureTableModel(exposureTableDataObject));
			_exposureTable.getColumnModel().getColumn(0).setPreferredWidth(80);
			_exposureTable.getColumnModel().getColumn(1).setPreferredWidth(70);
			_exposureTable.getColumnModel().getColumn(2).setPreferredWidth(100);
			_exposureTable.getColumnModel().getColumn(3).setPreferredWidth(80);
			_exposureTable.getColumnModel().getColumn(4).setPreferredWidth(100);
			_exposureTable.getColumnModel().getColumn(5).setPreferredWidth(80);
			_exposureTable.getColumnModel().getColumn(6).setPreferredWidth(80);
			isExposureTableRowSelected = false;

		} else {
			showMessage("Please create proper Exposure.");
		}
	}

	private TableModel getExposureTableModel(Object[][] exposureTableDataObject2) {
		TableModel exposureTableModel = new DefaultTableModel(
				exposureTableDataObject2, new String[] { "ExposureID",
						"Limit ID", "NominalAmt", "NominalCCY", "ExposureAmt",
						"ExposureCCY", "Rate" }) {
			private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Object.class, Object.class,
					Object.class, Object.class, Object.class, Object.class,
					Object.class, };

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		return exposureTableModel;
	}

	private void createExposure(ArrayList<Object[]> exposureTableData2) {
		Vector<Object> exposureValue = new Vector<Object>();
		String expoId = "1222";
		String limitCode = (String) limitTableRowValues.get("LimitID");
		String nominalAmt = _nominalAmtTextField.getText();
		String nominalCcy = _nominalCCYLabel.getText();
		String expAmt = _exposureAmtTextField.getText();
		String expCcy = _exposureAmtCCYLabel.getText();
		String rate = _RateTextField.getText();
		exposureValue.add(expoId);
		exposureValue.add(limitCode);
		exposureValue.add(nominalAmt);
		exposureValue.add(nominalCcy);
		exposureValue.add(expAmt);
		exposureValue.add(expCcy);
		exposureValue.add(rate);
		exposureTableData.add(exposureValue.toArray(new String[exposureValue
				.size()]));

	}

	private void _exposureTableMouseMouseClicked(MouseEvent event) {

		JTable exposureTable = (JTable) event.getSource();
		int selection = exposureTable.getSelectedRow();
		System.out.println("selected Row :" + (selection + 1));
		System.out.println("total cols :" + exposureTable.getColumnCount());
		for (int i = 0; i < exposureTable.getColumnCount(); i++) {
			exposureTableRowValues.put(exposureTable.getColumnName(i),
					exposureTable.getValueAt(selection, i));
			System.out.println(exposureTable.getValueAt(selection, i));
		}

		isExposureTableRowSelected = true;

	}

	private void _removeAllButtonMouseMouseClicked(MouseEvent event) {
		exposureTableData.removeAll(exposureTableData);
		isExposureTableRowSelected = false;
		DefaultTableModel dtm = (DefaultTableModel) _exposureTable.getModel();

		dtm.setNumRows(0);
		// _exposureTable.setModel(getExposureTableModel(null));

	}

	private void _removeButtonMouseMouseClicked(MouseEvent event) {
		if (isExposureTableRowSelected) {
			System.out.println("Rows :" + _exposureTable.getSelectedRow());
			DefaultTableModel dtm = (DefaultTableModel) _exposureTable
					.getModel();
			dtm.removeRow(_exposureTable.getSelectedRow());
			exposureTableData.removeAll(exposureTableData);
			for (int i = 0; i < dtm.getDataVector().toArray().length; i++) {
				Vector a = (Vector) dtm.getDataVector().elementAt(i);

				exposureTableData.add(a.toArray());
			}
			isExposureTableRowSelected = false;
		} else {
			showMessage("Please select Exposure to remove.");
		}
	}

	private void _closeButtonMouseMouseClicked(MouseEvent event) {
		int result = JOptionPane.showConfirmDialog(null,
				"Are you sure to close this window ?", "",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == 0) {
			this.dispose();
		}
	}

	private void _okButtonMouseMouseClicked(MouseEvent event) {
		// Save all exposures in Objects
		DefaultTableModel exposureTableModel = (DefaultTableModel) _exposureTable
				.getModel();
		if (exposureTableModel.getDataVector() == null
				|| exposureTableModel.getDataVector().isEmpty()) {
			showMessage("There are no Exposures to be save !!!");

		} else {
			/*Vector<Exposure> expoVec = new Vector<Exposure>();
			System.out.println("Data vect ::::"
					+ exposureTableModel.getDataVector());
			for (int i = 0; i < exposureTableModel.getDataVector().toArray().length; i++) {
				Vector a = (Vector) exposureTableModel.getDataVector()
						.elementAt(i);
				Exposure exp = new Exposure();
				exp.set_exposureId((String) a.elementAt(0));
				exp.set_limitId((String) a.elementAt(1));
				exp.set_exposureNominalAmount((String) a.elementAt(2));
				expoVec.add(exp);
			} */
			String uniqueCode = "12544";
			saveExposureTemp(uniqueCode, exposureTableModel.getDataVector()
					.toString());
		}
	}

	private void saveExposureTemp(String uniqueCode, String exposureVector) {
		// TODO Auto-generated method stub

		String result = "";// Initialised output variable
		try {// Location of the servlet with DNS name scheme
			// String servletURL =
			// "https://fxlive.devl.kotak.int:6443/FinServlet/CallServlet";
			String serverPath = "https://fxlive.devl.kotak.int:6443";
			String userName_ = "user1";
			String User_Group = "AutoTrader";
			if (serverPath != null && !serverPath.isEmpty()
					&& !serverPath.equalsIgnoreCase("")) {
				String servletURL = serverPath + "/LimServlet/LimitServlet";
				/*
				 * System.out.println("serverpath :" + serverPath + " complete
				 * path:" + servletURL);// remove it
				 *//*
					 * Preparing request query for servlet passing various
					 * parameters to servlet
					 */
				String query = "CRN=" + "888" + "ExposureVector="
						+ exposureVector + "UC=" + "ABC45GG" + "&RequestCode="
						+ "saveExposure" + "&LoggedUser=" + userName_
						+ "&LoggedUserGroup=" + User_Group;
				String charEncoding = "iso-8859-1";
				/* System.out.println("Query " + query); */

				URL sendUrl = new URL(servletURL);
				// Establishing the connection with Servlet
				conn = (HttpURLConnection) sendUrl.openConnection();
				conn.setDoOutput(true);// Output Stream Open
				conn.setDoInput(true);
				conn.setRequestMethod("POST");// Using POST method
				conn.setRequestProperty("Content-Length", Integer
						.toString(query.length()));

				conn.getOutputStream().write(query.getBytes(charEncoding));
				// /out = (ObjectOutputStream) conn.getOutputStream();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				// Reading the ouput received from servlet
				result = in.readLine();

			} else {
				System.out
						.println("TakerCustom_Clients_All::CallServlet::Error Fetching Server Path");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out
					.println("TakerCustom_Clients_All::CallServlet::Exception : "
							+ e1.getMessage());
		}

	}
}
