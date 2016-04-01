


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
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

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import productPricing.BONDPricing;
import productPricing.Pricer;
import productPricing.pricingUtil.BondCashFlow;
import util.NumericTextField;
import util.commonUTIL;
import util.common.DateU;
import apps.window.referencewindow.DateCellEditor12;
import apps.window.tradewindow.CommonPanel;
import beans.Coupon;
import beans.Product;
import beans.StartUPData;
import beans.Trade;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;


//VS4E -- DO NOT REMOVE THIS LINE!
public class BONDPanel  extends CommonPanel  {

	private static final long serialVersionUID = 1L;
	private JTable JTable1;
	private JScrollPane jScrollPane0;
	private JPanel jPanel0;
	 DecimalFormat format = new DecimalFormat("##,###,#######.##");
	
	private JTextField jTextField7;
	private JComboBox bondProduct;
	private JLabel buysell;
	private JLabel Nominal;
	public NumericTextField nominal;
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
	 public static  ServerConnectionUtil de = null;
	 Vector cashFlows = null;
	 BONDPricing pricing =  new BONDPricing();
	 
	 BONDTradePanel tradevalue = null;
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
	    DefaultCellEditor currencyData = null;
		DefaultCellEditor dce13 = null;
		DefaultCellEditor perioddce13 = null;
		DefaultCellEditor amortizingdce13  = null;
		DefaultCellEditor repoType = null;
		DefaultCellEditor currencydce13 = null;
		DefaultCellEditor dayCount = null;
		DefaultCellEditor tenorce13 = null;
		DefaultCellEditor callType= null;
		DefaultCellEditor termAsDatece13 = null;
		DefaultCellEditor termAsLoandce13 = null;
	 
	
	    Vector v1 = null;
	 	Vector ve1 = null;
	 	Vector ve2 = null;
	 	Vector ve3 = null;
	 	Vector ve4 = null;
	 	Vector ve5 = null;
	 	Vector ve6 = null;
	 	Vector dateRoll = null;
	 	Vector ve7 = null;
	//	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	
	
	public BONDPanel () {
		init();
	 startDateC = new DateCellEditor12();
  	   endDateC = new DateCellEditor12();
  	  
  	 try{
  		 
  	  	  
  				v1 = (Vector)	  referenceData.getStartUPData("Currency");
  				ve2=(Vector)	  referenceData.getStartUPData("DayCount");
  				ve3       =    (Vector)   referenceData.getStartUPData("RepoType");
  				JComboBox currencycomboBox11 = new JComboBox(
  						convertVectortoSringArray(v1));
  				JComboBox dayCountcomboBox11 = new JComboBox(
  						convertVectortoSringArray(ve2));
  				 JComboBox repoTypecomboBox11 = new JComboBox(
  						convertVectortoSringArray(ve3));
  				 JComboBox callableType = new JComboBox();
  				callableType.addItem(new String("Lender"));
  				callableType.addItem(new String("BOTH"));
  				callableType.addItem(new String("None"));
  				currencyData = new DefaultCellEditor( currencycomboBox11 );
  		  	    dayCount = new DefaultCellEditor( dayCountcomboBox11 );
  		  	  repoType = new DefaultCellEditor( repoTypecomboBox11 );
  		 callType= new DefaultCellEditor( callableType );
  				}catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	  
  	 
		initComponents();
		
		setTableValuesBlank();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel1(), new Constraints(new Bilateral(5, 2, 1218), new Bilateral(8, 17, 10)));
		setSize(1225, 362);
		
	}

	private void init() {
		 coupon = new Coupon();
		 product = new Product();
		 de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
	   	 try {
	   		remoteProduct = (RemoteProduct) de.getRMIService("Product");
	   	 referenceData = (RemoteReferenceData) de.getRMIService("ReferenceData");
	   	productData = new javax.swing.DefaultComboBoxModel();
	     processDataCombo1(productData,productID);
	    
	      
				//System.out.println(remoteProduct);
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
			String priceName [] =    {"TradeLeg ", " Value "};
	         tradePrice = new DefaultTableModel(priceName,0);
			jTable2  = new javax.swing.JTable(tradePrice);
			   processTableData(tradePrice);
					
		}
		return jTable2;
	}

	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			
			jTabbedPane1.addTab("Coupon", getJPanel9());
			jTabbedPane1.addTab("TradePrice", getJPanel4());
		}
		return jTabbedPane1;
	}

	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setLayout(new GroupLayout());
			jPanel4.add(getJScrollPane2(), new Constraints(new Bilateral(7, 5, 415), new Bilateral(6, 4, 10, 280)));
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
			 String col1[] = {"Repo Details       ", "Value      "};
			 t1model = new DefaultTableModel (col1,0);
			jTable1 =  jTable1  = new javax.swing.JTable(t1model) {
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
				if (modelColumn == 1 && (row == 3)) {
					TableCellEditor t1 = ((TableCellEditor) currencyData);
					return (TableCellEditor) t1;
				}
				if (modelColumn == 1 && (row == 6)) {
					TableCellEditor t1 = ((TableCellEditor) dce13);
					return (TableCellEditor) t1;
				}
				if (modelColumn == 1 && (row == 7)) {
					TableCellEditor t1 = ((TableCellEditor) repoType);
					return (TableCellEditor) t1;
				}
				
				 else {
					return super.getCellEditor(1, 0);
				}
			}
				
		};

		}
			// jTable1.setValueAt(commonUTIL.getDateFormat(commonUTIL.getCurrentDate()), 1, 1);
		
		return jTable1;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
			jTabbedPane0.addTab("Repo", getJPanel3());
		
		}
		return jTabbedPane0;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GroupLayout());
			jPanel3.add(getJScrollPane1(), new Constraints(new Leading(7, 744, 10, 10), new Trailing(12, 235, 12, 12)));
		}
		return jPanel3;
	}

	JPanel jPanel9 = null;
	private JPanel getJPanel9() {
		if (jPanel9 == null) {
			jPanel9 = new JPanel();
			jPanel9.setLayout(new GroupLayout());
			jPanel9.add(getJScrollPane3(), new Constraints(new Bilateral(7, 5, 415), new Bilateral(6, 4, 10, 280)));
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
			 String col3[] = {" Floating Data   ", "Value      "};
		     couponModel = new DefaultTableModel (col3,0);
			jTable3  = new javax.swing.JTable(couponModel) ;
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
			jPanel1.add(getJTabbedPane0(), new Constraints(new Leading(6, 761, 12, 12), new Bilateral(51, 8, 10)));
			jPanel1.add(getJTabbedPane1(), new Constraints(new Bilateral(776, 10, 418), new Bilateral(9, 10, 10, 316)));
			jPanel1.add(getNominal(), new Constraints(new Leading(635, 132, 448, 485), new Leading(14, 27, 46, 46)));
			jPanel1.add(getBuysell(), new Constraints(new Leading(12, 89, 10, 10), new Leading(20, 21, 12, 12)));
			jPanel1.add(getBondproduct(), new Constraints(new Leading(89, 437, 10, 10), new Leading(14, 27, 46, 46)));
		}
		return jPanel1;
	}

	private JLabel getJLabel14() {
		if (Nominal == null) {
			Nominal = new JLabel();
		//	Nominal.setFont(new Font("Dialog", Font.PLAIN, 13));
			Nominal.setText("QTY");
		}
		return Nominal;
	}

	private JLabel getBuysell() {
		if (buysell == null) {
			buysell = new JLabel();
			buysell.setFont(new Font("Arial", Font.BOLD, 13));
			buysell.setText("Collateral");
		}
		return buysell;
	}

	private JComboBox getBondproduct() {
		if (bondProduct == null) {
			bondProduct = new JComboBox();
		//	bondProduct.setFont(new Font("SansSerif", Font.PLAIN, 13));
			  bondProduct.setEditable(false);
			  bondProduct.setModel(productData);
		}bondProduct.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int index = bondProduct.getSelectedIndex();
				if(index >= 0) {
				 product = returnProducID(index,productID);
				 setTableValues(product); 
				} else {
					product = null;
					setTableValuesBlank();
					nominal.setText("0");
				}
				 
				
			}
			
		});
		return bondProduct;
	}

	private void setTableValuesBlank() {
		jTable1.removeAll();
		int r = t1model.getRowCount();
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Start Date", ""});
		
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "End Date",""});
		
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Principal",""});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Currency",""});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Open Term",""});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Notice Period",""});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Callable By",""});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Type",""});
		
		
		/*	tradePrice.insertRow(tradePrice.getRowCount(),  new Object[]{ "Type", ""});
		tradePrice.insertRow(tradePrice.getRowCount(),  new Object[]{ "Ccy", ""});
		tradePrice.insertRow(tradePrice.getRowCount(),  new Object[]{ "Rate", ""});
		tradePrice.insertRow(tradePrice.getRowCount(),  new Object[]{ "DayCount", ""});
		tradePrice.insertRow(tradePrice.getRowCount(),  new Object[]{ "SalesMargin", ""});
		tradePrice.insertRow(tradePrice.getRowCount(),  new Object[]{ "Cmp Frq", ""});
		tradePrice.insertRow(tradePrice.getRowCount(),  new Object[]{ "Tenor", ""});
		
		couponModel.insertRow(couponModel.getRowCount(),  new Object[]{ "Spread", ""});
		couponModel.insertRow(couponModel.getRowCount(),  new Object[]{ "Index", ""});
		
		couponModel.insertRow(couponModel.getRowCount(),  new Object[]{ "points", ""});
		
		amorModel.insertRow(amorModel.getRowCount(),  new Object[]{ "Amortization", ""});
		amorModel.insertRow(amorModel.getRowCount(),  new Object[]{ "Base Amt", ""});
		amorModel.insertRow(amorModel.getRowCount(),  new Object[]{ "Term As Date", ""});
		amorModel.insertRow(amorModel.getRowCount(),  new Object[]{ "Start Date", ""});
		amorModel.insertRow(amorModel.getRowCount(),  new Object[]{ "End Date", ""});
		amorModel.insertRow(amorModel.getRowCount(),  new Object[]{ "Rate", ""});
		amorModel.insertRow(amorModel.getRowCount(),  new Object[]{ "Frequency", ""});
		amorModel.insertRow(amorModel.getRowCount(),  new Object[]{ "Loan as Term", ""});
		
		jTable1.setValueAt(commonUTIL.getDateFormat(commonUTIL.getCurrentDate()), 0, 1); */
		
	}

	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
			jTextField7.setBackground(Color.white);
//			jTextField7.setFont(new Font("Dialog", Font.PLAIN, 13));
		}
		return jTextField7;
	}

	

	

	private NumericTextField getNominal() {
		if (nominal == null) {
			nominal = new NumericTextField(15,format);
			nominal.setBackground(Color.white);
			nominal.setFont(new Font("Dialog", Font.PLAIN, 13));
		}
		return nominal;
	}

	
	private String []  convertVectortoSringArray(Vector v) {
    	String name [] = null;
    	int i=0;
    	if(v != null ) {
    		name = new String[v.size()];
    		Iterator its = v.iterator();
    		while(its.hasNext()) {
    			name [i] = ( (StartUPData) its.next()).getName();
    			i++;
    		}
    	}
		return name;                                           
        // TODO add your handling code here:
    } 

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	private JTable getJTable0() {
		if (JTable1 == null) {
			JTable1 = new JTable();
			JTable1.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return JTable1;
	}
	private void processDataCombo1( javax.swing.DefaultComboBoxModel combodata,Hashtable ids) {
		Vector vector;
		try {
			String sql = " producttype ='BOND' and   productname like 'BOND%'";
			vector = (Vector) remoteProduct.selectProductWhereClaus(sql);
			Iterator it = vector.iterator();
	    	int i =0;
	    	while(it.hasNext()) {
    		Product product = (Product) it.next();
    		combodata.insertElementAt(product.getProductname(), i);
    		ids.put(i, product);
    		i++;
    	}	
		}catch (RemoteException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	
    	
    }
	
	public int getProductKey(Hashtable t , int id) {
		int i = 0;
		Set set = t.entrySet();
	    Iterator it = set.iterator();
	    while (it.hasNext()) {
	      Map.Entry entry = (Map.Entry) it.next();
	      Product le =  ((Product) entry.getValue());
	     if(id == le.getId())
	    	 i = ((Integer) entry.getKey()).intValue();
	    }
       return i;	    

	
		
	}
	public void buildTrade1(Trade trade,String actionType) {
		
		
	}
	private Product returnProducID(int indexid,Hashtable h) {
    	return ((Product) h.get(indexid));
    	
   
    }
	private Product getProdcutToOpenTrade(int prouductID,Hashtable products) {
		Enumeration keys;
	    keys=products.elements();
	    product = null;
	    while(keys.hasMoreElements()) {
	    	Product prod = (Product) keys.nextElement();
	    	if(prod.getId() == prouductID) {
	    		product = prod;
	    		break;
	    	}
	    		
	    }
	    if(product == null ) {
	    	try {
				product = (Product) remoteProduct.selectProduct(prouductID);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 productID.put(productID.size()+1, product);
			
			
	    }
    	return product;
   
    }
	@Override
	public void buildTrade(Trade trade, String actionType) {
		if(actionType.equalsIgnoreCase("NEW")) {
			bondProduct.setSelectedIndex(-1);
			trade.setProductId(0);
			trade.setQuantity(0);
			trade.setType(buysell.getText());
			trade.setDelivertyDate("");
			trade.setEffectiveDate("");
		    trade.setTradedesc("");
		    trade.setTradedesc1("");
		    nominal.setText("0");
			
		} else {
		Product prod = returnProducID(bondProduct.getSelectedIndex(),productID);
		trade.setProductId(prod.getId());
		trade.setQuantity(new Double(nominal.getText()).doubleValue());   // due to new screen design nomial text value will be treaded as quantity for Bond Trade 
		trade.setType(buysell.getText());
		//trade.setDelivertyDate(jTable1.getValueAt(0, 1).toString());
		trade.setEffectiveDate(trade.getTradeDate());
	    trade.setTradedesc(bondProduct.getSelectedItem().toString());
	    trade.setTradedesc1(prod.getProdcutShortName());
		}
	}
	
	@Override
	public void openTrade(Trade trade) {
		// TODO Auto-generated method stub
		
			
		// TODO Auto-generated method stub
		bondProduct.setSelectedIndex(getProductKey(productID,trade.getProductId()));
		buysell.setText(trade.getType());
	//	System.out.println(trade.getQuantity());
	//	startDate.setText(trade);   //ppppppppppppppppppppppp
		nominal.setText(new Double(trade.getQuantity()).toString());
		setTableValues(getProdcutToOpenTrade(trade.getProductId(),productID));
		
		
		
	}
	public void setTableValues(Product product) {
		Vector coupons = null;
		
		try {
			coupons = (Vector) remoteProduct.getCoupon(product.getId());
				coupon = (Coupon) coupons.elementAt(0);
				//System.out.println(remoteProduct);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		int r = t1model.getRowCount();
	//	System.out.println(r);
		for(int rows =r;rows > 0;rows--)  {
			t1model.removeRow(rows-1);
		}
		jTable1.repaint();
          
		
		/*t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Maturity Date",product.getMarturityDate()});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Dated Date",product.getDatedDate()});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Issue Date",product.getIssueDate()});
		
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Total Issue ",product.getQuantity()});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Issue Price",product.getIssuePrice()});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Face Value",product.getFaceValue()});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Issuing Currency",product.getIssueCurrency()});*/
		
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Maturity Date",product.getMarturityDate()});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Dated Date",product.getDatedDate()});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Issue Date",product.getIssueDate()});
		
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Total Issue ",product.getQuantity()});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Issue Price",product.getIssuePrice()});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Face Value",product.getFaceValue()});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Issuing Currency",product.getIssueCurrency()});
		
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Coupon Type",coupon.getCouponType()});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "Frequency",coupon.getCouponFrequency()});
		t1model.insertRow(t1model.getRowCount(),  new Object[]{ "DayCount",coupon.getDayCount()});
	
		
		 r = couponModel.getRowCount();
		//System.out.println(r);
		for(int rows =r;rows > 0;rows--)  {
			couponModel.removeRow(rows-1);
		}
		jTable3.repaint();
		couponModel.insertRow(couponModel.getRowCount(),  new Object[]{ "Accural Days", ""});
		couponModel.insertRow(couponModel.getRowCount(),  new Object[]{ "Accural Rate", ""});
		couponModel.insertRow(couponModel.getRowCount(),  new Object[]{ "Accural Amt", ""});
		
/*-			couponModel.insertRow(couponModel.getRowCount(),  new Object[]{ "Coupon Type",coupon.getCouponType()});
		couponModel.insertRow(couponModel.getRowCount(),  new Object[]{ "Frequency",coupon.getCouponFrequency()});
		couponModel.insertRow(couponModel.getRowCount(),  new Object[]{ "DayCount",coupon.getDayCount()});
		
		
		couponModel.insertRow(couponModel.getRowCount(),  new Object[]{ "Coupon Currency ",coupon.getCCY()});
		couponModel.insertRow(couponModel.getRowCount(),  new Object[]{ "Coupon Rate ",coupon.getFixedRate()});
*/			
		couponModel.insertRow(couponModel.getRowCount(),  new Object[]{ "Next Coupon Date", ""});
		
		couponModel.insertRow(couponModel.getRowCount(),  new Object[]{ "Previous Coupon Date", ""});
//-			couponModel.insertRow(couponModel.getRowCount(),  new Object[]{ "Coupon Days", ""});
		       	      	     
        
	//	nominal.setText(Double.toString(product.getFaceValue()));
		String attri = product.getAttributes();
		String name = "";
		String value = "";
		if(attri.contains("SettlementType")) {
		String [] attributes = attri.split(";");
		for(int i=0;i < attributes.length;i++) {
			if( attributes[i].contains("=") ) {
			 name = attributes[i].substring(0, attributes[i].indexOf("="));
			 value = attributes[i].substring(name.length()+1,attributes[i].length() );
			}
			if(name.equalsIgnoreCase("SettlementType")) 
				break;
			   
		}
			int days = Integer.valueOf((value.substring(value.length()-1, value.length()))).intValue();
			Date date = commonUTIL.getCurrentDate();
			DateU dat = DateU.valueOf(date);
			 dat.addDays(days);
			 
			if(!dat.isWeekEndDay())  {
				tradevalue.tsettlement.setText(commonUTIL.getDateFormat(dat.getDate()));
			
		} else {
			dat.nextBussinessDay();
			tradevalue.tsettlement.setText(commonUTIL.getDateFormat(dat.getDate()));
		}
		tradevalue.tcurrency.setSelectedItem(coupon.getCCY());
	//	tradevalue.tsettlement.setText(coupon.get)
	}
	}
	@Override
	public void setPanelValue(CommonPanel tradeValue) {
		// TODO Auto-generated method stub
		tradevalue = (BONDTradePanel) tradeValue;
	}

	public BONDPricing getPricing() {
		return pricing;
	}

	public void setPricing(BONDPricing pricing) {
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
	private void processCouponTable(String name,String  value ) {
		// TODO Auto-generated method stub
		 int rowCount = couponModel.getRowCount();
		 for(int i=0;i<rowCount;i++) {
			// System.out.println((String) couponModel.getValueAt(i, 0));
			 if(((String) couponModel.getValueAt(i, 0)).equalsIgnoreCase(name)) {
				 couponModel.setValueAt(value, i, 1);
				 break;
			 }
			 
		 }
	    		
		 jTable3.setModel(couponModel);
		jTable3.repaint();
	}
		
	@Override
	public Collection getCashFlows() {
		// TODO Auto-generated method stub
		return cashFlows;
	}
	public void setCashFlows(Vector cashFlows) {
		this.cashFlows = cashFlows;
			}
	private void processPriceTable(String name,String value) {
		// TODO Auto-generated method stub
		 int rowCount = tradePrice.getRowCount();
		 for(int i=0;i<rowCount;i++) {
			// System.out.println((String) tradePrice.getValueAt(i, 0));
			 if(((String) tradePrice.getValueAt(i, 0)).equalsIgnoreCase(name)) {
				 tradePrice.setValueAt(value, i, 1);
				 break;
			 }
			 
		 }
	    		
		 jTable2.setModel(tradePrice);
		jTable2.repaint();
	}
    
	private void processTableData(DefaultTableModel model) {
		// TODO Auto-generated method stub
    	Vector vector;
		try {
			vector = (Vector) referenceData.getStartUPData("TradePrice");
			Iterator it = vector.iterator();
	    	int i =0;
	    	while(it.hasNext()) {
	    		
	    		StartUPData tradeAttributes = (StartUPData) it.next();
	    	
	    		model.insertRow(i, new Object[]{tradeAttributes.getName()," "});
	    		i++;
	    		}
	    		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void calculatePrice(BONDPricing price,Trade trade,Product product,Coupon coupon) {
			
		pricing.price(trade, product, coupon);
		
		BondCashFlow cashFlow = pricing.generateCashFlow();
        setCashFlows(cashFlow.getFlows());
	        
		pricing.setTradeData(cashFlow);
       
        setPricing(pricing);
        
		/*pricing.price(trade, product, coupon);
         BondCashFlow cashFlow = pricing.generateCashFlow();
         
         setCashFlows(cashFlow.getFlows());
         setPricing(pricing);
         if(cashFlow.getAccuralFlow() != null) {
         processCouponTable("Accural Amt",commonUTIL.doubleFormat(pricing.getAccuralFlow().getAccuralAmount()));
         processCouponTable("Accural Int",commonUTIL.doubleFormat(pricing.getAccuralFlow().getAccuralInterest()).toString());
         processCouponTable("Accural Days",commonUTIL.doubleFormat(pricing.getAccuralFlow().getAccuralDays()).toString());
	}
         processCouponTable("Coupon Days",commonUTIL.doubleFormat(pricing.getPrincipalFlow().getFlowsdays()).toString());
         processCouponTable("Next Coupon Date",commonUTIL.dateToString(pricing.getPrincipalFlow().getEndDate()));
         processCouponTable("Current Coupon Date",commonUTIL.dateToString(pricing.getPrincipalFlow().getStartDate()));
         processPriceTable("DirtyPrice", commonUTIL.doubleFormat(pricing.getDirtyPrice()));
         processPriceTable("CleanPrice",commonUTIL.doubleFormat( pricing.getCleanPrice()));
         processPriceTable("Price",commonUTIL.doubleFormat( pricing.getPrice()));
         
         tradevalue.tradeyield.setText(commonUTIL.doubleFormat(pricing.getYield()));
         tradevalue.tradeamount.setText(commonUTIL.doubleFormat(pricing.getCleanPrice() + pricing.getAccuralFlow().getAccuralAmount()));
         
         */
        
        processCouponTable("Accural Amt",commonUTIL.doubleFormat(pricing.getAccrualAmount()));
   	 	processCouponTable("Accural Rate",commonUTIL.doubleFormat(pricing.getAccrualInterest()).toString());
   	 	processCouponTable("Accural Days",commonUTIL.doubleFormat(pricing.getAccrualDays()).toString());
        	      	         
        processCouponTable("Coupon Days",commonUTIL.doubleFormat(pricing.getCouponDays()));
        processCouponTable("Next Coupon Date", commonUTIL.dateToString(pricing.getNextCouponDate()));
        processCouponTable("Previous Coupon Date", commonUTIL.dateToString(pricing.getPreviousCouponDate()));
        
        processPriceTable("DirtyPrice", commonUTIL.doubleFormat(pricing.getDirtyPrice()));
        processPriceTable("CleanPrice",commonUTIL.doubleFormat( pricing.getCleanPrice()));
        processPriceTable("TotalAmount",commonUTIL.doubleFormat( pricing.getTotalAmount()));
        processPriceTable("Quantity",commonUTIL.doubleFormat( pricing.getQuantity()) );
        
        tradevalue.tradeyield.setText(commonUTIL.doubleFormat(pricing.getYield()));
        nominal.setText(commonUTIL.doubleFormat( pricing.getQuantity()));
        
	}
	 private String productName = "";
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
}

