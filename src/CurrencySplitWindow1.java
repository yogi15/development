import java.awt.Color;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import util.commonUTIL;

import beans.Book;
import beans.CurrencyPair;
import beans.StartUPData;
import beans.Users;
import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;

//VS4E -- DO NOT REMOVE THIS LINE!
public class CurrencySplitWindow1 extends JFrame {

	private static final long serialVersionUID = 1L;
	RemoteReferenceData referenceData;
	 public static  ServerConnectionUtil de = null;
	 Users user = null;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JComboBox jComboBox20;
	private JComboBox jComboBox0;
	private JComboBox jComboBox2;
	private JLabel jLabel3;
	private JComboBox jComboBox1;
	private JLabel jLabel2;
	private JComboBox jComboBox3;
	private JLabel jLabel4;
	private JPanel jPanel0;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	 javax.swing.DefaultComboBoxModel currencyDataModel = new javax.swing.DefaultComboBoxModel();
	 javax.swing.DefaultComboBoxModel currencyPairDataModel = new javax.swing.DefaultComboBoxModel();
	 javax.swing.DefaultComboBoxModel bookModel = new javax.swing.DefaultComboBoxModel();
	 javax.swing.DefaultComboBoxModel bookFirstSpotModel = new javax.swing.DefaultComboBoxModel();
	 javax.swing.DefaultComboBoxModel bookSecondSpotDataModel = new javax.swing.DefaultComboBoxModel();
	 Hashtable<Integer,Book> books = new Hashtable<Integer,Book>();
	private JTabbedPane jTabbedPane0;
	private JPanel jPanel1;
	private JPanel jPanel2;
	public CurrencySplitWindow1() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel1(), new Constraints(new Leading(4, 1062, 10, 10), new Leading(6, 393, 10, 10)));
		setSize(1076, 402);
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
			jPanel1.setBorder(new LineBorder(Color.black, 1, false));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJTabbedPane0(), new Constraints(new Leading(5, 1050, 10, 10), new Leading(6, 381, 10, 10)));
		}
		return jPanel1;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
			jTabbedPane0.addTab("jPanel0", getJPanel0());
		}
		return jTabbedPane0;
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("Delete");
		}
		return jButton3;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Edit");
		}
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Add");
		}
		return jButton1;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("NEW");
		}
		return jButton0;
	}

	public JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel0(), new Constraints(new Leading(14, 91, 10, 10), new Leading(15, 25, 10, 10)));
			jPanel0.add(getJLabel1(), new Constraints(new Leading(248, 57, 10, 10), new Leading(21, 19, 50, 202)));
			jPanel0.add(getJComboBox20(), new Constraints(new Leading(323, 12, 12), new Leading(20, 50, 202)));
			jPanel0.add(getJComboBox0(), new Constraints(new Leading(114, 78, 10, 10), new Leading(15, 50, 202)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(21, 12, 12), new Leading(60, 10, 10)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(251, 98, 10, 10), new Leading(58, 50, 202)));
			jPanel0.add(getJLabel4(), new Constraints(new Leading(437, 122, 10, 10), new Leading(52, 50, 202)));
			jPanel0.add(getJComboBox1(), new Constraints(new Leading(21, 152, 10, 10), new Leading(94, 10, 10)));
			jPanel0.add(getJComboBox2(), new Constraints(new Leading(248, 154, 10, 10), new Leading(94, 50, 202)));
			jPanel0.add(getJComboBox3(), new Constraints(new Leading(437, 180, 10, 10), new Leading(94, 10, 10)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(288, 10, 10), new Trailing(208, 131, 131)));
			jPanel0.add(getJButton1(), new Constraints(new Leading(394, 61, 10, 10), new Trailing(208, 131, 131)));
			jPanel0.add(getJButton2(), new Constraints(new Leading(501, 61, 10, 10), new Trailing(208, 131, 131)));
			jPanel0.add(getJButton3(), new Constraints(new Leading(585, 77, 10, 10), new Trailing(208, 26, 131, 131)));
			jPanel0.add(getJScrollPane0(), new Constraints(new Bilateral(9, 12, 22), new Trailing(12, 178, 10, 10)));
		}
		return jPanel0;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("jLabel4");
		}
		return jLabel4;
	}

	private JComboBox getJComboBox3() {
		if (jComboBox3 == null) {
			jComboBox3 = new JComboBox();
			jComboBox3.setModel(bookSecondSpotDataModel);
			jComboBox3.setDoubleBuffered(false);
			jComboBox3.setBorder(null);
		}
		return jComboBox3;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Book");
		}
		return jLabel2;
	}

	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			jComboBox1 = new JComboBox();
			jComboBox1.setModel(bookModel);
			jComboBox1.setDoubleBuffered(false);
			jComboBox1.setBorder(null);
		}
		return jComboBox1;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("jLabel3");
		}
		return jLabel3;
	}

	private JComboBox getJComboBox2() {
		if (jComboBox2 == null) {
			jComboBox2 = new JComboBox();
			jComboBox2.setModel(bookFirstSpotModel);
			jComboBox2.setDoubleBuffered(false);
			jComboBox2.setBorder(null);
		}
		return jComboBox2;
	}

	private JComboBox getJComboBox0() {
		if (jComboBox0 == null) {
			jComboBox0 = new JComboBox();
			jComboBox0.setModel(currencyDataModel);
			jComboBox0.setDoubleBuffered(false);
			jComboBox0.setBorder(null);
		}
		return jComboBox0;
	}

	private JComboBox getJComboBox20() {
		if (jComboBox20 == null) {
			jComboBox20 = new JComboBox();
			jComboBox20.setModel(currencyDataModel);
		}
		return jComboBox20;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Currency");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Currency Pair");
		}
		return jLabel0;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	
	public void init() {
		de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
	   	 try {
	   	       referenceData = (RemoteReferenceData) de.getRMIService("ReferenceData");
	   	    Vector currency = (Vector) referenceData.getStartUPData("Currency");
	   	 Vector currencyPair = (Vector) referenceData.selectALLCurrencyPair();
	 	Vector books = (Vector) referenceData.selectALLBooks();
	 	processBookData(books,bookModel,true);
	 	processBookData(books,bookFirstSpotModel,false);
	 	processBookData(books,bookSecondSpotDataModel,false);
	 	processCurrency(currency,currencyDataModel);
	 	processCurrencyPairs(currencyPair,currencyPairDataModel);
	 	
	   	} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void processCurrency(Vector currency, DefaultComboBoxModel currencyDataModel2) {
		
		
		
		Iterator it = currency.iterator();
    	int i =0;
	while(it.hasNext()) {
		
		StartUPData boo = (StartUPData) it.next();
	
		currencyDataModel2.addElement(boo.getValue());
	
	}
	}
public void processCurrencyPairs(Vector currencypair, DefaultComboBoxModel currencyDataModel2) {
		
		
		
		Iterator it = currencypair.iterator();
    	int i =0;
	while(it.hasNext()) {
		
		CurrencyPair boo = (CurrencyPair) it.next();
	
		currencyDataModel2.addElement(boo.getPrimary_currency()+"/"+boo.getQuoting_currency());
	
	}
	}
	
	private void processBookData(Vector book,DefaultComboBoxModel cmodel,boolean flag) {
		// TODO Auto-generated method stub
		Vector vector;
		
			books.clear();
			
			
			Iterator it = book.iterator();
	    	int i =0;
		while(it.hasNext()) {
			
			Book boo = (Book) it.next();
		
			cmodel.addElement(boo.getBook_name());
		if(flag)
			books.put(i, boo);
			i++;
		}
		
		
		
		
		
	}
	private JTable getJTable0() {
		if (jTable0 == null) {
			jTable0 = new JTable();
			jTable0.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return jTable0;
	}

}
