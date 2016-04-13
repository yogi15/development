import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.commonUTIL;
import util.cacheUtil.ReferenceDataCache;
import beans.AttributeContainer;
import beans.BaseBean;
import beans.Book;
import beans.Product;
import beans.Coupon;
import beans.BookAttribute;
import beans.LegalEntity;
import constants.BeanConstants;
import dbSQL.dsSQL;
import dsServices.RemoteReferenceData;

//VS4E -- DO NOT REMOVE THIS LINE!
public class MenuConfigTable extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private JPanel jPanel0;
	
	private JButton jButton0;
	RemoteReferenceData remoteRef;
	ReferenceDataCache rdc = new ReferenceDataCache();
	private JButton jButton1;
	private JTree jTree0;
	private JScrollPane jScrollPane1;
	private JButton jButton2;
	private JTextField jTextField0;
	private JButton jButton3;
	private JTextField jTextField1;
	private JButton jButton4;
	private JPanel jPanel1;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	Object[] cNames = { " ID", " PRODUCTNAME", "PRODUCTTYPE", "PRODUCTSUBTYPE", "COUNTRY", "CURRENCY", "Status",
			"Exchange", "CUSIP", "SEDOL", "ISIN", "Symbol", "Fixed Rate", " Face Value", "Rate Type", "Rate Index",
			"Day Count", "BusinessDayConvention", "Withhold tax", "Roll Date ", "Period Adjusted", "Coupon Freq",
			"PayDay Lag ", "Issue Date", "Date Date", "Maturity Date" };
	Object[] insert1 = { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
			" ", " ", " ", " ", " ", " ", " " };
	DefaultTableModel tm = new DefaultTableModel(null, cNames);
	@SuppressWarnings("unchecked")
	public MenuConfigTable() throws RemoteException {
		initComponents();
		Collection<Book> p = ReferenceDataCache.selectALLData(BeanConstants.BOOK);
		//Collection<Coupon> c = ReferenceDataCache.selectALLData(BeanConstants.COUPON);
		Iterator<Book> i = p.iterator();
		//Iterator<Coupon> j = c.iterator();
		while (i.hasNext() /*&& j.hasNext()*/) {
			Book p1 = i.next();
			/*Coupon c1 = j.next();*/
			Vector<String> rowData = new Vector<String>();
			rowData.add(p1.getBookno() + "");
			rowData.add(p1.getBook_name());
			rowData.add(p1.getLe_id()+ "");
			rowData.add(p1.getFolderID() + "");
			rowData.add(p1.getAttributes());
			rowData.add(p1.getTimezone());
			rowData.add(p1.getHolidaycode());
			rowData.add(p1.getCurrency() + "");
			rowData.add(p1.getCurrency() + "");
			/*rowData.add(p1.getCUSIP());
			rowData.add(p1.getSEDOL());
			rowData.add(p1.getISIN());
			rowData.add(p1.getSYMBOL());
			rowData.add(c1.getFixedRate() + "");
			rowData.add(p1.getFaceValue() + "");
			rowData.add(c1.getRATETYPE());
			rowData.add(c1.getRATETYPE());
			rowData.add(c1.getRateIndex() + "");
			rowData.add(c1.getDayCount());
			rowData.add(c1.getBusinessDayConvention());
			rowData.add(c1.getWithholdingTax());
			rowData.add(c1.getROLLDATE());
			rowData.add(c1.getPERIODADJUSTED());
			rowData.add(c1.getCouponFrequency());
			rowData.add(c1.getPAYDAYLAG());
			rowData.add(p1.getIssueDate());
			rowData.add(p1.getDateDate());
			rowData.add(p1.getMarturityDate());*/
			//rowData.add(c1.getId() + "");
			
			
			// insert blank row added
			tm.addRow(rowData);
		}
		
		
		tm.addRow(insert1);
		// JOptionPane.showMessageDialog(null, tm, "InfoBox: " ,
		// JOptionPane.INFORMATION_MESSAGE);
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Bilateral(12, 12, 966), new Leading(20, 281, 10, 10)));
		add(getJPanel1(), new Constraints(new Leading(21, 256, 10, 10), new Leading(274, 96, 10, 10)));
		setSize(1187, 494);
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJTextField1(), new Constraints(new Leading(14, 111, 10, 10), new Leading(33, 30, 10, 10)));
			jPanel1.add(getJButton4(), new Constraints(new Leading(152, 10, 10), new Leading(35, 12, 12)));
		}
		return jPanel1;
	}

	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("SEARCH");
		}
		return jButton4;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setText("jTextField1");
		}
		return jTextField1;
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("jButton3");
			jButton3.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent event) {
					jButton3ActionActionPerformed(event);
				}
			});
		}
		return jButton3;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
			jTextField0.setText("jTextField0");
			jTextField0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jTextField0.addInputMethodListener(new InputMethodListener() {

				public void inputMethodTextChanged(InputMethodEvent event) {
					jTextField0InputMethodInputMethodTextChanged(event);
				}

				@Override
				public void caretPositionChanged(InputMethodEvent arg0) {

				}
			});
		}
		return jTextField0;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("INSERT");
			jButton2.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent event) {
					try {
						jButton2ActionActionPerformed(event);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		return jButton2;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTree0());
		}
		return jScrollPane1;
	}

	// JTREE CODING.................................

	private void selectIn(JTree jTree0) {

		/*
		 * try {
		 * 
		 * Collection<LegalEntity> name = remoteRef.selectALL();
		 * Iterator<LegalEntity> i = name.iterator();
		 * 
		 * MutableTreeNode MENUCONFIG = new DefaultMutableTreeNode(
		 * "MENUCONFIG TABLE"); MutableTreeNode WindowName = new
		 * DefaultMutableTreeNode( "WINDOWNAME"); MutableTreeNode ParentName =
		 * new DefaultMutableTreeNode( "PARENTNAME"); MutableTreeNode ChildName
		 * = new DefaultMutableTreeNode("CHILDNAME"); MutableTreeNode Height =
		 * new DefaultMutableTreeNode("HEIGHT"); MutableTreeNode Width = new
		 * DefaultMutableTreeNode("WIDTH"); MENUCONFIG.insert(WindowName, 0);
		 * MENUCONFIG.insert(ParentName, 1); MENUCONFIG.insert(ChildName, 2);
		 * MENUCONFIG.insert(Height, 3); MENUCONFIG.insert(Width, 4);
		 * 
		 * while (i.hasNext()) { Menuconfig cr = i.next(); int it = 0;
		 * WindowName.insert( new DefaultMutableTreeNode(cr.getWindowName()),
		 * it); ParentName.insert( new
		 * DefaultMutableTreeNode(cr.getParentName()), it); ChildName.insert(new
		 * DefaultMutableTreeNode(cr.getChildName()), it); Height.insert(new
		 * DefaultMutableTreeNode(cr.getHeight() + ""), it); Width.insert(new
		 * DefaultMutableTreeNode(cr.getWidth() + ""), it); it++; }
		 * DefaultTreeModel td = new DefaultTreeModel(MENUCONFIG);
		 * 
		 * jTree0.setModel(td); deleteAllRows(tm); selectTable();
		 * 
		 * } catch (RemoteException e) { e.printStackTrace(); }
		 */
	}

	public static void deleteAllRows(final DefaultTableModel model) {
		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

	// --------------------------------\/Search----------------------------------------//

	public final DefaultMutableTreeNode findNode(String searchString) {

		List<DefaultMutableTreeNode> searchNodes = getSearchNodes((DefaultMutableTreeNode) jTree0.getModel().getRoot());
		DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) jTree0.getLastSelectedPathComponent();

		DefaultMutableTreeNode foundNode = null;
		int LegalEntitymark = -1;

		if (currentNode != null) {
			for (int index = 0; index < searchNodes.size(); index++) {
				if (searchNodes.get(index) == currentNode) {
					LegalEntitymark = index;
					break;
				}
			}
		}

		for (int index = LegalEntitymark + 1; index < searchNodes.size(); index++) {
			if (searchNodes.get(index).toString().toLowerCase().contains(searchString.toLowerCase())) {
				foundNode = searchNodes.get(index);
				break;
			}
		}

		if (foundNode == null) {
			for (int index = 0; index <= LegalEntitymark; index++) {
				if (searchNodes.get(index).toString().toLowerCase().contains(searchString.toLowerCase())) {
					foundNode = searchNodes.get(index);
					break;
				}
			}
		}
		return foundNode;
	}

	private final List<DefaultMutableTreeNode> getSearchNodes(DefaultMutableTreeNode root) {
		List<DefaultMutableTreeNode> searchNodes = new ArrayList<DefaultMutableTreeNode>();
		Enumeration<?> e = root.preorderEnumeration();
		while (e.hasMoreElements()) {
			searchNodes.add((DefaultMutableTreeNode) e.nextElement());
		}
		return searchNodes;
	}

	// ----------------------------------------

	private void jTextField0InputMethodInputMethodTextChanged(InputMethodEvent event) {

	}

	private void jButton3ActionActionPerformed(ActionEvent event) {

		String search = jTextField0.getText();
		if (search.trim().length() > 0) {

			DefaultMutableTreeNode node = findNode(search);
			if (node != null) {
				TreePath path = new TreePath(node.getPath());
				jTree0.setSelectionPath(path);
				jTree0.scrollPathToVisible(path);
			}
		}

	}

	// .....................................................................^^Search^^...........................................................//

	private JTree getJTree0() {
		if (jTree0 == null) {
			jTree0 = new JTree();
			jTree0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));

			selectIn(jTree0);
		}
		return jTree0;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("UPDATE");
		}

		jButton0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selRow = jTable0.getSelectedRow();
				boolean isRowSelected = jTable0.isRowSelected(selRow);
				if (selRow != -1) {
					if (isRowSelected == true) {
						Product m = new Product();
						Coupon c2 = new Coupon();
						m.setId(Integer.parseInt(((jTable0.getValueAt(selRow, 0)).toString()).trim()));
						m.setProductname((jTable0.getValueAt(selRow,1)).toString());
						m.setProductType((jTable0.getValueAt(selRow,2)).toString());
						m.setProductSubType((jTable0.getValueAt(selRow,3)).toString());
						m.setCountry((jTable0.getValueAt(selRow,4)).toString());
						m.setCurrency((jTable0.getValueAt(selRow,5)).toString());
						m.setSTATUS((jTable0.getValueAt(selRow,6)).toString());
						m.setEXCHANGE(Integer.parseInt(((jTable0.getValueAt(selRow, 7)).toString()).trim()));
						m.setCUSIP((jTable0.getValueAt(selRow,8)).toString());
						m.setSEDOL((jTable0.getValueAt(selRow,9)).toString());
						m.setISIN((jTable0.getValueAt(selRow,10)).toString());
						m.setSYMBOL((jTable0.getValueAt(selRow,11)).toString());
						m.setFaceValue(Integer.parseInt(((jTable0.getValueAt(selRow, 12)).toString()).trim()));
						m.setIssueDate((jTable0.getValueAt(selRow,13)).toString());
						m.setDateDate((jTable0.getValueAt(selRow,14)).toString());
						m.setMarturityDate((jTable0.getValueAt(selRow,15)).toString());
						c2.setId(Integer.parseInt(((jTable0.getValueAt(selRow, 0)).toString()).trim()));
						c2.setFixedRate(Integer.parseInt(((jTable0.getValueAt(selRow, 16)).toString()).trim()));
						c2.setRATETYPE((jTable0.getValueAt(selRow,17)).toString());
						c2.setRATETYPE((jTable0.getValueAt(selRow,18)).toString());
						c2.setRATEINDEX(Integer.parseInt(((jTable0.getValueAt(selRow, 19)).toString()).trim()));
						c2.setDayCount((jTable0.getValueAt(selRow,20)).toString());
						c2.setBusinessDayConvention((jTable0.getValueAt(selRow,21)).toString());
						c2.setWithholdingTax((jTable0.getValueAt(selRow,22)).toString());
						c2.setROLLDATE((jTable0.getValueAt(selRow,23)).toString());
						c2.setPERIODADJUSTED((jTable0.getValueAt(selRow,24)).toString());
						c2.setPAYDAYLAG((jTable0.getValueAt(selRow,25)).toString());
						c2.setCouponFrequency((jTable0.getValueAt(selRow,26)).toString());
						
						/*
						 * m.setState((jTable0.getValueAt(selRow,
						 * 8)).toString());
						 * m.setCountry((jTable0.getValueAt(selRow,
						 * 9)).toString());
						 * m.setMailingAddress1((jTable0.getValueAt(selRow,
						 * 10)).toString());
						 * m.setMailingAddress2((jTable0.getValueAt(selRow,
						 * 11)).toString());
						 * m.setEmailID((jTable0.getValueAt(selRow,
						 * 12)).toString());
						 * m.setPhone((jTable0.getValueAt(selRow,
						 * 13)).toString());
						 * m.setFax((jTable0.getValueAt(selRow,
						 * 14)).toString());
						 * m.setSwift((jTable0.getValueAt(selRow,
						 * 15)).toString());
						 */
						isRowSelected = ReferenceDataCache.updateSQL(m, BeanConstants.PRODUCT);
						isRowSelected = ReferenceDataCache.updateSQL(c2, BeanConstants.COUPON);
					}
				}
			}
		});

		return jButton0;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("DELETE");
		}

		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = jTable0.getSelectedRow();
				if (selRow != -1) {
					boolean isRowdeleted = false;

					Product m = new Product();
					Coupon c2 = new Coupon();
					m.setId(Integer.parseInt(((jTable0.getValueAt(selRow, 0)).toString()).trim()));
					m.setProductname((jTable0.getValueAt(selRow,1)).toString());
					m.setProductType((jTable0.getValueAt(selRow,2)).toString());
					m.setProductSubType((jTable0.getValueAt(selRow,3)).toString());
					m.setCountry((jTable0.getValueAt(selRow,4)).toString());
					m.setCurrency((jTable0.getValueAt(selRow,5)).toString());
					m.setSTATUS((jTable0.getValueAt(selRow,6)).toString());
					m.setEXCHANGE(Integer.parseInt(((jTable0.getValueAt(selRow, 7)).toString()).trim()));
					m.setCUSIP((jTable0.getValueAt(selRow,8)).toString());
					m.setSEDOL((jTable0.getValueAt(selRow,9)).toString());
					m.setISIN((jTable0.getValueAt(selRow,10)).toString());
					m.setSYMBOL((jTable0.getValueAt(selRow,11)).toString());
					m.setFaceValue(Integer.parseInt(((jTable0.getValueAt(selRow, 12)).toString()).trim()));
					m.setIssueDate((jTable0.getValueAt(selRow,13)).toString());
					m.setDateDate((jTable0.getValueAt(selRow,14)).toString());
					m.setMarturityDate((jTable0.getValueAt(selRow,15)).toString());
					c2.setId(Integer.parseInt(((jTable0.getValueAt(selRow, 0)).toString()).trim()));
					c2.setFixedRate(Integer.parseInt(((jTable0.getValueAt(selRow, 16)).toString()).trim()));
					c2.setRATETYPE((jTable0.getValueAt(selRow,17)).toString());
					c2.setRATETYPE((jTable0.getValueAt(selRow,18)).toString());
					c2.setRATEINDEX(Integer.parseInt(((jTable0.getValueAt(selRow, 19)).toString()).trim()));
					c2.setDayCount((jTable0.getValueAt(selRow,20)).toString());
					c2.setBusinessDayConvention((jTable0.getValueAt(selRow,21)).toString());
					c2.setWithholdingTax((jTable0.getValueAt(selRow,22)).toString());
					c2.setROLLDATE((jTable0.getValueAt(selRow,23)).toString());
					c2.setPERIODADJUSTED((jTable0.getValueAt(selRow,24)).toString());
					c2.setPAYDAYLAG((jTable0.getValueAt(selRow,25)).toString());
					c2.setCouponFrequency((jTable0.getValueAt(selRow,26)).toString());
					/*
					 * m.setLeLastName((jTable0.getValueAt(selRow,
					 * 5)).toString()); m.setCity((jTable0.getValueAt(selRow,
					 * 6)).toString()); m.setZipCode((jTable0.getValueAt(selRow,
					 * 7)).toString()); m.setState((jTable0.getValueAt(selRow,
					 * 8)).toString()); m.setCountry((jTable0.getValueAt(selRow,
					 * 9)).toString());
					 * m.setMailingAddress1((jTable0.getValueAt(selRow,
					 * 10)).toString());
					 * m.setMailingAddress2((jTable0.getValueAt(selRow,
					 * 11)).toString());
					 * m.setEmailID((jTable0.getValueAt(selRow,
					 * 12)).toString()); m.setPhone((jTable0.getValueAt(selRow,
					 * 13)).toString()); m.setFax((jTable0.getValueAt(selRow,
					 * 14)).toString()); m.setSwift((jTable0.getValueAt(selRow,
					 * 15)).toString());
					 */
					isRowdeleted = ReferenceDataCache.deleteSQL(m, BeanConstants.PRODUCT);
					isRowdeleted = ReferenceDataCache.deleteSQL(c2, BeanConstants.COUPON);
					// selectIn(jTree0);
					if (isRowdeleted) {
						tm.removeRow(selRow);
					}
				} else {
					commonUTIL.showAlertMessage("Select one row");
				}
			}
		});

		return jButton1;

	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJScrollPane1(), new Constraints(new Leading(35, 150, 10, 10), new Leading(27, 200, 10, 10)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(264, 10, 10), new Leading(194, 12, 12)));
			jPanel0.add(getJButton2(), new Constraints(new Leading(411, 10, 10), new Leading(194, 12, 12)));
			jPanel0.add(getJButton1(), new Constraints(new Leading(547, 10, 10), new Leading(194, 12, 12)));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(37, 86, 10, 10), new Leading(1, 25, 10, 10)));
			jPanel0.add(getJButton3(), new Constraints(new Leading(133, 20, 10, 10), new Leading(6, 15, 12, 12)));
			jPanel0.add(getJScrollPane0(),
					new Constraints(new Leading(199, 946, 10, 10), new Leading(26, 150, 10, 10)));
		}
		return jPanel0;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	private JTable getJTable0() {
		if (jTable0 == null) {
			jTable0 = new JTable();
			jTable0.setModel(tm);
		}
		return jTable0;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL + " on this platform:" + e.getMessage());
		}
	}

	private void jButton2ActionActionPerformed(ActionEvent event) throws RemoteException

	{

		int selRow = jTable0.getSelectedRow();
		BaseBean isRowSelected;
		if (selRow != -1) {

			/*
			 * Book m = new Book();
			 * m.setLe_id(Integer.parseInt(((jTable0.getValueAt(selRow,
			 * 0)).toString()).trim()));
			 * m.setBookno(Integer.parseInt(((jTable0.getValueAt(selRow,
			 * 1)).toString()).trim()));
			 * m.setBook_name((jTable0.getValueAt(selRow, 2)).toString());
			 * m.setFolderID(Integer.parseInt(((jTable0.getValueAt(selRow,
			 * 3)).toString()).trim()));
			 * m.setAttributes((jTable0.getValueAt(selRow, 4)).toString());
			 * 
			 * m.setAlias((jTable0.getValueAt(selRow, 5)).toString());
			 * m.setContact((jTable0.getValueAt(selRow, 6)).toString());
			 * m.setCountry((jTable0.getValueAt(selRow, 7)).toString());
			 * 
			 * AttributeContainer att = new AttributeContainer();
			 * att.addAttribute("Attribute1", "testing1");
			 * att.addAttribute("Attribute2", "testing2");
			 * m.setAttributeContainer(att);
			 */
			Product m = new Product();
			Coupon c2 = new Coupon();
			m.setId(Integer.parseInt(((jTable0.getValueAt(selRow, 0)).toString()).trim()));
			m.setProductname((jTable0.getValueAt(selRow,1)).toString());
			m.setProductType((jTable0.getValueAt(selRow,2)).toString());
			m.setProductSubType((jTable0.getValueAt(selRow,3)).toString());
			m.setCountry((jTable0.getValueAt(selRow,4)).toString());
			m.setCurrency((jTable0.getValueAt(selRow,5)).toString());
			m.setSTATUS((jTable0.getValueAt(selRow,6)).toString());
			m.setEXCHANGE(Integer.parseInt(((jTable0.getValueAt(selRow, 7)).toString()).trim()));
			m.setCUSIP((jTable0.getValueAt(selRow,8)).toString());
			m.setSEDOL((jTable0.getValueAt(selRow,9)).toString());
			m.setISIN((jTable0.getValueAt(selRow,10)).toString());
			m.setSYMBOL((jTable0.getValueAt(selRow,11)).toString());
			m.setFaceValue(Integer.parseInt(((jTable0.getValueAt(selRow, 12)).toString()).trim()));
			m.setIssueDate((jTable0.getValueAt(selRow,13)).toString());
			m.setDateDate((jTable0.getValueAt(selRow,14)).toString());
			m.setMarturityDate((jTable0.getValueAt(selRow,15)).toString());
			c2.setId(Integer.parseInt(((jTable0.getValueAt(selRow, 0)).toString()).trim()));
			c2.setFixedRate(Integer.parseInt(((jTable0.getValueAt(selRow, 16)).toString()).trim()));
			c2.setRATETYPE((jTable0.getValueAt(selRow,17)).toString());
			c2.setRATETYPE((jTable0.getValueAt(selRow,18)).toString());
			c2.setRATEINDEX(Integer.parseInt(((jTable0.getValueAt(selRow, 19)).toString()).trim()));
			c2.setDayCount((jTable0.getValueAt(selRow,20)).toString());
			c2.setBusinessDayConvention((jTable0.getValueAt(selRow,21)).toString());
			c2.setWithholdingTax((jTable0.getValueAt(selRow,22)).toString());
			c2.setROLLDATE((jTable0.getValueAt(selRow,23)).toString());
			c2.setPERIODADJUSTED((jTable0.getValueAt(selRow,24)).toString());
			c2.setPAYDAYLAG((jTable0.getValueAt(selRow,25)).toString());
			c2.setCouponFrequency((jTable0.getValueAt(selRow,26)).toString());
			isRowSelected = ReferenceDataCache.insertSQL(m, BeanConstants.PRODUCT);
			isRowSelected = ReferenceDataCache.insertSQL(c2, BeanConstants.COUPON);
			selectIn(jTree0);

		}
	}

	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MenuConfigTable frame;
				try {
					frame = new MenuConfigTable();
					
					Product m = new Product();
					Coupon c2 = new Coupon();
					m.setId(1);
					c2.setProductId(1);
					/*m.setId(1);
					m.setProductname("23");
					m.setProductType("4");
					m.setProductSubType("234");
					m.setCountry("23424");
					m.setCurrency("wer");
					m.setSTATUS("w32");
					m.setEXCHANGE(2);
					m.setCUSIP("56");
					m.setSEDOL("6");
					m.setISIN("34");
					m.setSYMBOL("India");
					m.setFaceValue(3);
					m.setIssueDate("r2sjt");
					m.setDateDate("23");
					m.setMarturityDate("rs324tj");
					c2.setProductId(1);
					c2.setFixedRate(2);
					c2.setRATETYPE("6");
					c2.setRATETYPE("546");
					c2.setRATEINDEX(3);
					c2.setDayCount("47");
					c2.setBusinessDayConvention("78");
					c2.setWithholdingTax("79");
					c2.setROLLDATE("7");
					c2.setPERIODADJUSTED("89");
					c2.setPAYDAYLAG("love");
					c2.setCouponFrequency("65");*/
					 ReferenceDataCache.deleteSQL(m, BeanConstants.PRODUCT);
					 ReferenceDataCache.deleteSQL(c2, BeanConstants.COUPON);
					
					frame.setDefaultCloseOperation(MenuConfigTable.EXIT_ON_CLOSE);
					frame.setTitle("MenuConfigTable");
					frame.getContentPane().setPreferredSize(frame.getSize());
					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					LegalEntity le = new LegalEntity();
					int i = ReferenceDataCache.count("13", BeanConstants.LEGALENTITY);
					JOptionPane.showMessageDialog(null, i, "InfoBox: " + i, JOptionPane.INFORMATION_MESSAGE);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

}
