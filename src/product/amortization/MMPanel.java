package  product.amortization;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import productPricing.MMPricing;
import productPricing.Pricer;
import productPricing.pricingUtil.MMCashFlow;
import productPricing.pricingUtil.frequencyUtil;
import util.NumericTextField;
import util.commonUTIL;
import util.common.DateU;
import apps.window.referencewindow.DateCellEditor12;
import apps.window.tradewindow.CommonPanel;
import apps.window.tradewindow.MMTradePanel;
import apps.window.util.windowUtil.AmortizationPanel;
import beans.Coupon;
import beans.Product;
import beans.Trade;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;
import beans.StartUPData;

//VS4E -- DO NOT REMOVE THIS LINE!
public class MMPanel extends CommonPanel {

	private static final long serialVersionUID = 1L;
	private JTable JTable1;
	private JScrollPane jScrollPane0;
	private JPanel jPanel0;

	DecimalFormat format = new DecimalFormat("##,###,#######.##");

	private JTextField jTextField7;
	private JTextField bondProduct;
	private JLabel buysell;
	private JButton PriceButton;
	private JLabel Nominal;
	private NumericTextField nominal;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JTabbedPane jTabbedPane0;
	private JTable jTable1;
	private JScrollPane jScrollPane1;
	private JPanel jPanel4;
	private JTabbedPane jTabbedPane1;
	private JTable jTable2;
	private JScrollPane jScrollPane2;

	RemoteReferenceData remoteBORef;
	RemoteReferenceData referenceData;
	RemoteProduct remoteProduct;
	public static ServerConnectionUtil de = null;
	Vector cashFlows = null;
	MMPricing pricing = new MMPricing();

	MMTradePanel tradevalue = null;
	Hashtable productID = new Hashtable();

	Product product = null;
	Coupon coupon = null;

	DefaultTableModel t1model;
	DefaultTableModel t2model;
	DefaultTableModel couponModel;
	DefaultTableModel amorModel;
	DefaultTableModel tradePrice = null;
	javax.swing.DefaultComboBoxModel productData = null;

	DateCellEditor12 startDateC = null;
	DateCellEditor12 endDateC = null;
	DefaultCellEditor dce12 = null;
	DefaultCellEditor dce13 = null;
	DefaultCellEditor perioddce13 = null;
	DefaultCellEditor amortizingdce13 = null;
	DefaultCellEditor couponTypedce13 = null;
	DefaultCellEditor currencydce13 = null;
	DefaultCellEditor dayCountdce13 = null;
	DefaultCellEditor tenorce13 = null;
	DefaultCellEditor indexdce13 = null;
	DefaultCellEditor termAsDatece13 = null;
	DefaultCellEditor termAsLoandce13 = null;

	AmortizationPanel amortizatwindow = null;
	Vector v1 = null;
	Vector ve1 = null;
	Vector ve2 = null;
	Vector ve3 = null;
	Vector ve4 = null;
	Vector ve5 = null;
	Vector ve6 = null;
	Vector dateRoll = null;
	Vector ve7 = null;

	// private static final String PREFERRED_LOOK_AND_FEEL =
	// "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	public MMPanel() {
		init();
		startDateC = new DateCellEditor12();
		endDateC = new DateCellEditor12();

		try {
			v1 = (Vector) referenceData.getStartUPData("Currency");
			ve2 = (Vector) referenceData.getStartUPData("DayCount");
			ve3 = (Vector) referenceData.getStartUPData("Period");
			ve4 = (Vector) referenceData.getStartUPData("Amortizing");
			ve5 = (Vector) referenceData.getStartUPData("PaymentFRQ");
			ve6 = (Vector) referenceData.getStartUPData("Tenor");
			ve7 = (Vector) referenceData.getStartUPData("INDEX");
			dateRoll = (Vector) referenceData.getStartUPData("DateRoll");
			amortizatwindow = new AmortizationPanel(ve5, ve6);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// jTable1.setModel(t1model);
		JComboBox currencycomboBox11 = new JComboBox(
				convertVectortoSringArray(v1));
		JComboBox dayCountcomboBox11 = new JComboBox(
				convertVectortoSringArray(ve2));
		final JComboBox tenorcomboBox11 = new JComboBox(
				convertVectortoSringArray(ve6));
		JComboBox indexcomboBox11 = new JComboBox(
				convertVectortoSringArray(ve7));
		JComboBox comboBox11 = new JComboBox(convertVectortoSringArray(ve5));
		JComboBox comboBox21 = new JComboBox(
				convertVectortoSringArray(dateRoll));
		JComboBox Period = new JComboBox(convertVectortoSringArray(ve3));
		final JComboBox Amortizing = new JComboBox(
				convertVectortoSringArray(ve4));
		Amortizing.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
			
				if (Amortizing.getSelectedItem().toString()
						.equalsIgnoreCase("Schedule")) {
					amortizatwindow.jButton1.setVisible(true);
				} else {
					amortizatwindow.jButton1.setVisible(false);
				}
			}
		});
		JCheckBox termAsDate = new JCheckBox("Term As Date");
		termAsDate.setSelected(true);
		JCheckBox termAsLoan = new JCheckBox("Term As Loan");

		JComboBox couponType = new JComboBox();
		couponType.addItem(new String("Fixed"));
		couponType.addItem(new String("Float"));

		dce12 = new DefaultCellEditor(comboBox11);
		dce13 = new DefaultCellEditor(comboBox21);
		perioddce13 = new DefaultCellEditor(Period);

		amortizingdce13 = new DefaultCellEditor(Amortizing);
		couponTypedce13 = new DefaultCellEditor(couponType);
		currencydce13 = new DefaultCellEditor(currencycomboBox11);
		dayCountdce13 = new DefaultCellEditor(dayCountcomboBox11);
		tenorce13 = new DefaultCellEditor(tenorcomboBox11);
		indexdce13 = new DefaultCellEditor(indexcomboBox11);
		termAsDatece13 = new DefaultCellEditor(termAsDate);
		termAsLoandce13 = new DefaultCellEditor(termAsLoan);

		tenorcomboBox11.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String tenor = (String) tenorcomboBox11.getSelectedItem();
				// Period period = Period.valueOf(tenor);
				String date = "";
				if (startDateC.getCellEditorValue() != null) {
					date = startDateC.getCellEditorValue().toString();
				} else {
					date = (String) jTable1.getValueAt(0, 1);
					if (date == null || (date.trim().isEmpty())
							|| date.length() <= 0) {
						commonUTIL.showAlertMessage("Enter Issue Date ");
						return;
					}
				}

				DateU dateissueDate = DateU.valueOf(commonUTIL.stringToDate(
						date, false));
				dateissueDate.convertToCode(tenor);
				endDateC.setCellEditorValue(dateissueDate.getDate());
				jTable1.setValueAt(
						commonUTIL.getDateFormat(dateissueDate.getDate()), 1, 1);
			}

		});
		initComponents();
		String amorcol[] = { "Field Details    ", "Value      " };
		amorModel = new DefaultTableModel(amorcol, 0);
		setTableValuesBlank();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel1(), new Constraints(new Bilateral(5, 2, 1218),
				new Bilateral(8, 17, 10)));
		setSize(1225, 362);
	}

	private void init() {
		coupon = new Coupon();
		product = new Product();
		de = ServerConnectionUtil.connect("localhost", 1099,
				commonUTIL.getServerIP());
		try {
			remoteProduct = (RemoteProduct) de.getRMIService("Product");
			referenceData = (RemoteReferenceData) de
					.getRMIService("ReferenceData");
			productData = new javax.swing.DefaultComboBoxModel();
			processDataCombo1(productData, productID);

			// System.out.println(remoteProduct);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getJTable2());
		}
		return jScrollPane2;
	}

	private JTable getJTable2() {
		if (jTable2 == null) {
			jTable2 = new JTable();
			String priceName[] = { "TradeLeg ", " Value " };
			tradePrice = new DefaultTableModel(priceName, 0);
			jTable2 = new javax.swing.JTable(tradePrice) {
				// Determine editor to be used by row
				public TableCellEditor getCellEditor(int row, int column) {
					int modelColumn = convertColumnIndexToModel(column);
					if (modelColumn == 1 && (row == 0)) {
						TableCellEditor t1 = ((TableCellEditor) couponTypedce13);
						return (TableCellEditor) t1;
					}
					if (modelColumn == 1 && (row == 1)) {
						TableCellEditor t1 = ((TableCellEditor) currencydce13);
						return (TableCellEditor) t1;
					}
					if (modelColumn == 1 && (row == 3)) {
						TableCellEditor t1 = ((TableCellEditor) dayCountdce13);
						return (TableCellEditor) t1;
					}
					if (modelColumn == 1 && (row == 6)) {
						TableCellEditor t1 = ((TableCellEditor) tenorce13);
						return (TableCellEditor) t1;
					} else {
						return super.getCellEditor(1, 0);
					}

				}

			};
		}
		return jTable2;
	}

	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.addTab("TradePrice", getJPanel4());
			jTabbedPane1.addTab("Coupon", getJPanel9());
		}
		return jTabbedPane1;
	}

	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setLayout(new GroupLayout());
			jPanel4.add(getJScrollPane2(), new Constraints(new Bilateral(7, 5,
					415), new Bilateral(6, 4, 10, 280)));
		}
		return jPanel4;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}

	private JTable getJTable1() {
		if (jTable1 == null) {
			String col1[] = { "Product Details    ", "Value      " };
			t1model = new DefaultTableModel(col1, 0);
			jTable1 = jTable1 = new javax.swing.JTable(t1model) {
				public TableCellEditor getCellEditor(int row, int column) {
					int modelColumn = convertColumnIndexToModel(column);
					if (modelColumn == 1 && row == 0) {
						TableCellEditor t1 = ((TableCellEditor) startDateC);
						return (TableCellEditor) t1;
					}
					if (modelColumn == 1 && (row == 1)) {
						TableCellEditor t1 = ((TableCellEditor) endDateC);
						return (TableCellEditor) t1;
					}
					if (modelColumn == 1 && (row == 2)) {
						TableCellEditor t1 = ((TableCellEditor) dce12);
						return (TableCellEditor) t1;
					}
					if (modelColumn == 1 && (row == 3)) {
						TableCellEditor t1 = ((TableCellEditor) dce13);
						return (TableCellEditor) t1;
					}
					if (modelColumn == 1 && (row == 4)) {
						TableCellEditor t1 = ((TableCellEditor) perioddce13);
						return (TableCellEditor) t1;
					}
					if (modelColumn == 1 && (row == 5)) {
						TableCellEditor t1 = ((TableCellEditor) amortizingdce13);
						return (TableCellEditor) t1;
					} else {
						return super.getCellEditor(1, 0);
					}
				}
			};

			// jTable1.setValueAt(commonUTIL.getDateFormat(commonUTIL.getCurrentDate()),
			// 1, 1);
		}
		return jTable1;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
			jTabbedPane0.addTab("ProductDetails", getJPanel3());
			jTabbedPane0.addTab("Amortization", amortizatwindow);
		} 
		return jTabbedPane0;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GroupLayout());
			jPanel3.add(getJScrollPane1(), new Constraints(new Bilateral(6, 6,
					744), new Bilateral(6, 9, 10, 233)));
		}
		return jPanel3;
	}

	JPanel jPanel9 = null;

	private JPanel getJPanel9() {
		if (jPanel9 == null) {
			jPanel9 = new JPanel();
			jPanel9.setLayout(new GroupLayout());
			jPanel9.add(getJScrollPane3(), new Constraints(new Bilateral(7, 5,
					415), new Bilateral(6, 4, 10, 280)));
		}
		return jPanel9;
	}

	JScrollPane jScrollPane3 = null;

	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setViewportView(getJTable3());
		}
		return jScrollPane3;
	}

	JTable jTable3;

	private JTable getJTable3() {
		if (jTable3 == null) {
			String col3[] = { " Floating Data   ", "Value      " };
			couponModel = new DefaultTableModel(col3, 0);
			jTable3 = new javax.swing.JTable(couponModel) {
				// Determine editor to be used by row
				public TableCellEditor getCellEditor(int row, int column) {
					int modelColumn = convertColumnIndexToModel(column);
					if (modelColumn == 1 && (row == 1)) {
						TableCellEditor t1 = ((TableCellEditor) indexdce13);
						return (TableCellEditor) t1;
					} else {
						return super.getCellEditor(1, 0);
					}

				}
			};
		}

		return jTable3;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GroupLayout());
		}
		return jPanel2;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getBuysell(), new Constraints(new Leading(12, 42, 10,
					10), new Leading(20, 21, 12, 12)));
			jPanel1.add(getBondproduct(), new Constraints(new Leading(69, 432,
					10, 10), new Leading(14, 27, 46, 46)));
			jPanel1.add(getJTabbedPane0(), new Constraints(new Leading(6, 761,
					12, 12), new Bilateral(51, 8, 10)));
			jPanel1.add(getJTabbedPane1(), new Constraints(new Bilateral(776,
					10, 418), new Bilateral(9, 10, 10, 316)));
			jPanel1.add(getJButton0(), new Constraints(
					new Leading(510, 10, 10), new Leading(14, 27, 67, 290)));
			jPanel1.add(getNominal(), new Constraints(new Leading(635, 132,
					448, 485), new Leading(14, 27, 46, 46)));
			jPanel1.add(getJLabel14(), new Constraints(new Leading(577, 50,
					448, 485), new Leading(20, 21, 67, 290)));
		}
		commonUTIL.setLabelFont(buysell);
		commonUTIL.setLabelFont(Nominal);
		commonUTIL.setLabelFont(jTabbedPane0);
		commonUTIL.setLabelFont(jTabbedPane1);
		return jPanel1;
	}

	private JLabel getJLabel14() {
		if (Nominal == null) {
			Nominal = new JLabel();
			Nominal.setFont(new Font("Dialog", Font.PLAIN, 13));
			Nominal.setText("Nominal");
		}
		return Nominal;
	}

	private JButton getJButton0() {
		if (PriceButton == null) {
			PriceButton = new JButton();
			PriceButton.setText("Price");
		}
		PriceButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (product != null) {
					Trade trade = new Trade();
					trade.setTradeAmount(new Double(tradevalue.tradeamount.getText()));
					trade.setType(buysell.getText());
					trade.setTradeDate(tradevalue.tradeDate.getText());

					trade.setDelivertyDate(tradevalue.tsettlement.getText());
					trade.setQuantity(product.getQuantity());// in cash we have
																// trade nominal
																// equal to
																// product
																// nominal;
					trade.setNominal(product.getQuantity());
					validateProductData();
					trade.setAmoritizationData(amortizatwindow.getAmoritization());
					calculatePrice(pricing, trade, product, coupon);

				} else {
					product = new Product();
					coupon = new Coupon();
					if (!validateProductData()) {
						Trade trade = new Trade();
						// commonUTIL.showAlertMessage(amortizatwindow.getAmoritization());
						trade.setTradeAmount(new Double(tradevalue.tradeamount.getText()));
						trade.setType(buysell.getText());
						trade.setTradeDate(tradevalue.tradeDate.getText());
						trade.setDelivertyDate(tradevalue.tsettlement.getText());
						trade.setQuantity(product.getQuantity());// in cash we
																	// have
																	// trade
																	// nominal
																	// equal to
																	// product
																	// nominal;
						trade.setNominal(product.getQuantity());
						trade.setAmoritizationData(amortizatwindow.getAmoritization());
						calculatePrice(pricing, trade, product, coupon);
					}

				}

			}

		});

		return PriceButton;
	}

	private JLabel getBuysell() {
		if (buysell == null) {
			buysell = new JLabel();
			buysell.setFont(new Font("Dialog", Font.PLAIN, 13));
			buysell.setText("LOAN");
		}
		buysell.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (buysell.getText().equalsIgnoreCase("LOAN"))
					buysell.setText("DEPOSIT");
				else
					buysell.setText("LOAN");

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		return buysell;
	}

	private JTextField getBondproduct() {
		if (bondProduct == null) {
			bondProduct = new JTextField();
			bondProduct.setFont(new Font("SansSerif", Font.PLAIN, 13));
			bondProduct.setEditable(false);

		}
		return bondProduct;
	}

	private void setTableValuesBlank() {
		t1model.insertRow(t1model.getRowCount(), new Object[] { "Issue Date",
				"" });

		t1model.insertRow(t1model.getRowCount(),
				new Object[] { "End Date", "" });

		t1model.insertRow(t1model.getRowCount(),
				new Object[] { "Frequency", "" });
		t1model.insertRow(t1model.getRowCount(),
				new Object[] { "Date Roll", "" });
		t1model.insertRow(t1model.getRowCount(), new Object[] { "Period", "" });
		t1model.insertRow(t1model.getRowCount(), new Object[] { "Amortizing",
				"" });

		tradePrice.insertRow(tradePrice.getRowCount(), new Object[] { "Type",
				"" });
		tradePrice.insertRow(tradePrice.getRowCount(),
				new Object[] { "Ccy", "" });
		tradePrice.insertRow(tradePrice.getRowCount(), new Object[] { "Rate",
				"" });
		tradePrice.insertRow(tradePrice.getRowCount(), new Object[] {
				"DayCount", "" });
		tradePrice.insertRow(tradePrice.getRowCount(), new Object[] {
				"SalesMargin", "" });
		tradePrice.insertRow(tradePrice.getRowCount(), new Object[] {
				"Cmp Frq", "" });
		tradePrice.insertRow(tradePrice.getRowCount(), new Object[] { "Tenor",
				"" });

		couponModel.insertRow(couponModel.getRowCount(), new Object[] {
				"Spread", "" });
		couponModel.insertRow(couponModel.getRowCount(), new Object[] {
				"Index", "" });

		couponModel.insertRow(couponModel.getRowCount(), new Object[] {
				"points", "" });

		amorModel.insertRow(amorModel.getRowCount(), new Object[] {
				"Amortization", "" });
		amorModel.insertRow(amorModel.getRowCount(), new Object[] { "Base Amt",
				"" });
		amorModel.insertRow(amorModel.getRowCount(), new Object[] {
				"Term As Date", "" });
		amorModel.insertRow(amorModel.getRowCount(), new Object[] {
				"Start Date", "" });
		amorModel.insertRow(amorModel.getRowCount(), new Object[] { "End Date",
				"" });
		amorModel.insertRow(amorModel.getRowCount(),
				new Object[] { "Rate", "" });
		amorModel.insertRow(amorModel.getRowCount(), new Object[] {
				"Frequency", "" });
		amorModel.insertRow(amorModel.getRowCount(), new Object[] {
				"Loan as Term", "" });

		jTable1.setValueAt(
				commonUTIL.getDateFormat(commonUTIL.getCurrentDate()), 0, 1);

	}

	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
			jTextField7.setBackground(Color.white);
			jTextField7.setFont(new Font("Dialog", Font.PLAIN, 13));
		}
		return jTextField7;
	}

	private NumericTextField getNominal() {
		if (nominal == null) {
			nominal = new NumericTextField(10, format);
			nominal.setBackground(Color.white);
			nominal.setFont(new Font("Dialog", Font.PLAIN, 13));
		}
		return nominal;
	}

	private String[] convertVectortoSringArray(Vector v) {
		String name[] = null;
		int i = 0;
		if (v != null) {
			name = new String[v.size()];
			Iterator its = v.iterator();
			while (its.hasNext()) {
				name[i] = ((StartUPData) its.next()).getName();
				i++;
			}
		}
		return name;
		// TODO add your handling code here:
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setBorder(BorderFactory.createBevelBorder(
					BevelBorder.LOWERED, null, null, null, null));
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	private JTable getJTable0() {
		if (JTable1 == null) {
			JTable1 = new JTable();
			JTable1.setModel(new DefaultTableModel(new Object[][] {
					{ "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] {
					"Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };

				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return JTable1;
	}

	private void processDataCombo1(javax.swing.DefaultComboBoxModel combodata,
			Hashtable ids) {
		Vector vector;
		try {
			String sql = " producttype ='MM' and   productname like 'CASH%'";
			vector = (Vector) remoteProduct.selectProductWhereClaus(sql);
			Iterator it = vector.iterator();
			int i = 0;
			while (it.hasNext()) {
				Product product = (Product) it.next();
				combodata.insertElementAt(product.getProductname(), i);
				ids.put(i, product);
				i++;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean validateProductData() {
		boolean flag = false;
		String issueDate = (String) jTable1.getValueAt(0, 1);
		if (commonUTIL.isEmpty(issueDate)
				|| (!commonUTIL.isStringDate(issueDate))) {
			commonUTIL.showAlertMessage("Select Issue Date  ");
			return flag;
		}
		String endDate = jTable1.getValueAt(1, 1).toString();
		if (commonUTIL.isEmpty(endDate) || (!commonUTIL.isStringDate(endDate))) {
			commonUTIL.showAlertMessage("Select End Date  ");
			return flag;
		}
		String freq = (String) jTable1.getValueAt(2, 1).toString();

		if (commonUTIL.isEmpty(freq)) {
			commonUTIL.showAlertMessage("Select frequency ");
			return flag;
		}
		coupon.setCouponFrequency(freq);
		String DateRoll = jTable1.getValueAt(3, 1).toString();
		if (commonUTIL.isEmpty(DateRoll)) {
			commonUTIL.showAlertMessage("Select DateRoll ");
			return flag;
		}
		coupon.setBusinessDayConvention(DateRoll);

		String period = jTable1.getValueAt(4, 1).toString();
		if (commonUTIL.isEmpty(period)) {
			commonUTIL.showAlertMessage("Select period ");
			return flag;
		}
		coupon.setActivefrom(period);
		String amorType = jTable1.getValueAt(5, 1).toString();
		if (commonUTIL.isEmpty(amorType)) {
			commonUTIL.showAlertMessage("Select amorType ");
			return flag;
		}
		product.setAttributes(amorType);
		if (!commonUTIL.isEmpty(amortizatwindow.getAmoritization())) {
			// product.setAttributes(amortizatwindow.getAmoritization());
		}

		String couponType = jTable2.getValueAt(0, 1).toString();
		if (commonUTIL.isEmpty(couponType)) {
			commonUTIL.showAlertMessage("Select Leg Type ");
			return flag;
		}
		coupon.setCouponType(couponType);
		String ccy = jTable2.getValueAt(1, 1).toString();
		if (commonUTIL.isEmpty(ccy)) {
			commonUTIL.showAlertMessage("Select Currency ");
			return flag;
		}
		coupon.setCCY(ccy);
		product.setIssueCurrency(ccy);
		String rate = jTable2.getValueAt(2, 1).toString();
		if (commonUTIL.isEmpty(rate) || (!commonUTIL.isNumeric(rate))) {
			commonUTIL.showAlertMessage("Select Rate in numbers");
			return flag;
		}

		coupon.setFixedRate(new Double(rate).doubleValue());
		String dc = jTable2.getValueAt(3, 1).toString();
		if (commonUTIL.isEmpty(dc)) {
			commonUTIL.showAlertMessage("Select Daycount in numbers");
			return flag;
		}
		coupon.setDayCount(dc);
		String nominalnumber = nominal.getText();
		if (commonUTIL.isEmpty(nominalnumber)
				|| (!commonUTIL.isNumeric(nominalnumber))) {
			commonUTIL.showAlertMessage("Select Nominal in numbers");
			return flag;
		}
		product.setQuantity(new Double(nominalnumber).doubleValue());
		if (!couponType.equalsIgnoreCase("Fixed")) {
			String spread = jTable3.getValueAt(0, 1).toString();
			if (commonUTIL.isEmpty(spread) || (!commonUTIL.isNumeric(spread))) {
				commonUTIL.showAlertMessage("Select spread in numbers");
				return flag;
			}
			coupon.setYieldDecimals(new Double(spread).doubleValue()); // yieldDecimals
																		// treated
																		// as
																		// spread
																		// as
																		// don't
																		// want
																		// to
																		// create
																		// unneccessary
																		// columns.
			String index = jTable3.getValueAt(1, 1).toString();
			if (commonUTIL.isEmpty(index)) {
				commonUTIL.showAlertMessage("Select Index");
				return flag;
			}
			coupon.setYieldMethod(index);

		}
		String tenor = jTable2.getValueAt(6, 1).toString();
		if (commonUTIL.isEmpty(tenor)) {
			commonUTIL.showAlertMessage("Select tenor");
			return flag;
		}
		util.common.Period periods = new util.common.Period(tenor);

		int f = frequencyUtil.fromString(freq);
		int peri = periods.getCode();
		int count = peri / f;
		if (count <= 0) {
			commonUTIL.showAlertMessage("Frequency not in Tenor Range");
			jTable1.setValueAt("", 2, 1);

			return flag;
		}
		product.setTenor(tenor);

		product.setIssueDate(issueDate);
		product.setMarturityDate(endDate);
		product.setProductType("MM");
		product.setProdcutShortName("CASH." + buysell.getText());
		product.setProductname(product.getProdcutShortName() + "."
				+ coupon.getCouponFrequency() + "." + product.getIssueDate()
				+ "." + product.getMarturityDate() + "."
				+ product.getIssueCurrency() + "."
				+ (coupon.getFixedRate() / 100) + "."
				+ coupon.getCouponType().substring(0, 2));
		// product.setId(0);
		productName = product.getProductname();
		flag = true;
		return flag;
		// product.set

	}

	public int getProductKey(Hashtable t, int id) {
		int i = 0;
		Set set = t.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Product le = ((Product) entry.getValue());
			if (id == le.getId())
				i = ((Integer) entry.getKey()).intValue();
		}
		return i;

	}

	public void buildTrade1(Trade trade, String actionType) {

	}

	private Product returnProducID(int indexid, Hashtable h) {
		return ((Product) h.get(indexid));

	}

	private Product getProdcutToOpenTrade(int prouductID, Hashtable products) {
		Enumeration keys;
		keys = products.elements();
		product = null;
		while (keys.hasMoreElements()) {
			Product prod = (Product) keys.nextElement();
			if (prod.getId() == prouductID) {
				product = prod;
				break;
			}

		}
		if (product == null) {
			try {
				product = (Product) remoteProduct.selectProduct(prouductID);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			productID.put(productID.size() + 1, product);

		}
		return product;

	}

	@Override
	public void buildTrade(Trade trade, String actionType) {
		if (actionType.equalsIgnoreCase("NEW")) {
			product = new Product();
			coupon = new Coupon();
			// /bondProduct.setSelectedIndex(-1);
			trade.setProductId(0);
			trade.setQuantity(0);
			trade.setType(buysell.getText());
			trade.setDelivertyDate("");
			trade.setEffectiveDate("");
			trade.setTradedesc("");
			trade.setTradedesc1("");
			nominal.setText("0");
			jTable1.setValueAt(
					new String(commonUTIL.dateToString(commonUTIL
							.getCurrentDate())), 0, 1);
			jTable1.setValueAt(new String(""), 1, 1);
			jTable1.setValueAt(new String("0"), 2, 1);
			jTable1.setValueAt(new String("PA"), 3, 1);
			jTable1.setValueAt(new String(""), 4, 1);
			jTable1.setValueAt(new String(""), 5, 1);

			jTable2.setValueAt(new String(""), 0, 1);
			jTable2.setValueAt(new String(""), 1, 1);
			jTable2.setValueAt(new String(""), 2, 1);
			jTable2.setValueAt(new String(""), 3, 1);
			jTable2.setValueAt(new String(""), 4, 1);
			jTable2.setValueAt(new String(""), 5, 1);
			jTable2.setValueAt(new String(""), 6, 1);

			jTable3.setValueAt(new String("0"), 0, 1);
			jTable3.setValueAt(new String(""), 1, 1);
			jTable3.setValueAt(new String(""), 2, 1);
			// jTable3.setValueAt(new String("0"), 4, 1);

		} else {
			if (!validateProductData()) {
				trade.setProductId(0);
				return;
			}

			try {
				int productId = 0;
				if (actionType.equalsIgnoreCase("SAVE")) {

					productId = product.getId();

					remoteProduct.updateProduct(product, coupon);

				} else {
					product.setId(0);
					productId = remoteProduct.saveProduct(product, coupon);
				}
				if (productId > 0) {
					product.setId(productId);
					bondProduct.removeAll();
					productData.insertElementAt(product.getProductname(),
							productData.getSize());
					bondProduct.setText(product.getProductname());
					trade.setProductId(product.getId());
					trade.setNominal(product.getQuantity());
					trade.setQuantity(product.getQuantity());
					if (buysell.getText().equalsIgnoreCase("LOAN")) {
						trade.setType("SELL");
					} else {
						trade.setType("BUY");
					}

					trade.setDelivertyDate(jTable1.getValueAt(0, 1).toString());
					trade.setEffectiveDate(trade.getTradeDate());
					trade.setTradedesc(product.getProductname());
					trade.setTradedesc1(product.getProdcutShortName());
					if (!commonUTIL.isEmpty(amortizatwindow.getAmoritization())) {
						trade.setAmoritizationData(amortizatwindow
								.getAmoritization());
					}

				}

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void openTrade(Trade trade) {
		// TODO Auto-generated method stub

		if (trade.getType().equalsIgnoreCase("SELL")) {
			buysell.setText("LOAN");
		} else {
			buysell.setText("DEPOSIT");
		}
		// System.out.println(trade.getQuantity());
		// startDate.setText(trade); //ppppppppppppppppppppppp
		nominal.setText(new Double(trade.getNominal()).toString());
		setTableValues(getProdcutToOpenTrade(trade.getProductId(), productID));
		bondProduct.setText(product.getProductname());
		amortizatwindow.setvalues(trade.getAmoritizationData());

	}

	public void setTableValues(Product product) {
		Vector coupons = null;

		try {
			coupons = (Vector) remoteProduct.getCoupon(product.getId());
			coupon = (Coupon) coupons.elementAt(0);
			// System.out.println(remoteProduct);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		jTable1.setValueAt(product.getIssueDate(), 0, 1);

		jTable1.setValueAt(product.getMarturityDate(), 1, 1);
		jTable1.setValueAt(coupon.getCouponFrequency(), 2, 1);
		jTable1.setValueAt(coupon.getBusinessDayConvention(), 3, 1);
		jTable1.setValueAt(coupon.getActivefrom(), 4, 1);
		jTable1.setValueAt(product.getAttributes(), 5, 1);
		jTable2.setValueAt(coupon.getCouponType(), 0, 1);
		jTable2.setValueAt(coupon.getCCY(), 1, 1);
		jTable2.setValueAt(product.getTenor(), 6, 1);
		jTable2.setValueAt(coupon.getFixedRate(), 2, 1);
		jTable2.setValueAt(coupon.getDayCount(), 3, 1);
		jTable2.setValueAt(new String(""), 4, 1);
		jTable2.setValueAt(new String(""), 5, 1);

		jTable3.setValueAt(coupon.getYieldDecimals(), 0, 1);
		jTable3.setValueAt(coupon.getYieldMethod(), 1, 1);

		jTable3.setValueAt(new String("0"), 2, 1);
		nominal.setText(new Double(product.getQuantity()).toString());

	}

	@Override
	public void setPanelValue(CommonPanel tradeValue) {
		// TODO Auto-generated method stub
		tradevalue = (MMTradePanel) tradeValue;
	}

	public MMPricing getPricing() {
		return pricing;
	}

	public void setPricing(MMPricing pricing) {
		this.pricing = pricing;
	}

	@Override
	public Pricer getPricer() {
		// TODO Auto-generated method stub
		return getPricing();
	}

	@Override
	public void setTradePanelValue(CommonPanel tradeValue) {
		// TODO Auto-generated method stub
		tradeValue = (MMTradePanel) tradeValue;
	}

	@Override
	public Collection getCashFlows() {
		// TODO Auto-generated method stub
		return cashFlows;
	}

	public void setCashFlows(Vector cashFlows) {
		this.cashFlows = cashFlows;
	}

	public void calculatePrice(MMPricing price, Trade trade, Product product,
			Coupon coupon) {

		pricing.price(trade, product, coupon);
		MMCashFlow cashFlow = pricing.generateCashFlow();

		setCashFlows(cashFlow.getFlows());
		setPricing(pricing);

	}

	private String productName = "";
}
